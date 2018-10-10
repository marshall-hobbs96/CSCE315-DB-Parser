import java.util.*;
import java.io.*;
import org.antlr.v4.runtime.tree.*;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.Tree;

public class ParserMasterListener extends ParserBaseListener {
	
	/*@Override 
	public void enterProgram(ParserParser.ProgramContext ctx) {
		
		
		
	}
	public void enterExit_cmd(ParserParser.ProgramContext ctx) {
		
		DatabaseEngine.exitD();
		
	}
	
	public void enterCreate_cmd(ParserParser.ProgramContext ctx) {
		
		List<ParseTree> children = ctx.children;
		
		ParseTree create_words = children.get(0);
		ParseTree relationName = children.get(1);
		
		System.out.println(create_words.getText());
		System.out.println(relationName.getText());
		
	}*/
	
	@Override public void exitExpr(ParserParser.ExprContext ctx) { 
	
		List<ParseTree> tree = ctx.children;
		
		if((tree.get(0) instanceof ParserParser.SelectionContext) || (tree.get(0) instanceof ParserParser.ProjectionContext) || (tree.get(0) instanceof ParserParser.RenamingContext)) { //must be a selection, rename, or projection
			
			
			
		}
		
		else if((tree.get(0) instanceof ParserParser.UnionContext) || (tree.get(0) instanceof ParserParser.DifferenceContext) || (tree.get(0) instanceof ParserParser.ProductContext)){ //must be a union or difference or product
			
			
			
		}
		
		else{	//must be an atomic expression, don't really need to do anything.
			
			
			
			
		}
	
	}
	
	
	
	@Override public void exitQuery(ParserParser.QueryContext ctx) { 
	
		List<ParseTree> tree = ctx.children;
		String new_relation = tree.get(0).getText();
		
		if((tree.get(2).getChild(0).getPayload() instanceof ParserParser.SelectionContext) || (tree.get(2).getChild(0).getPayload() instanceof ParserParser.ProjectionContext) || (tree.get(2).getChild(0).getPayload() instanceof ParserParser.RenamingContext)) { //must be a selection, rename, or projection
			
			if(tree.get(2).getChild(0).getPayload() instanceof ParserParser.SelectionContext) {
				
				
				
			}
			
			else if(tree.get(2).getChild(0).getPayload() instanceof ParserParser.ProjectionContext){
				
				ParseTree renaming_tree = tree.get(2).getChild(0);
				String table_name = renaming_tree.getChild(4).getText();
				ParseTree operands = renaming_tree.getChild(2);
				ArrayList<String> columns = new ArrayList<String>(); 
				
				for(int i = 0; i < operands.getChildCount(); i++){
					
					String operand = operands.getChild(i).getText();
					columns.add(operand);
					
				}
				
				//DatabaseEngine.query(new_relation, table_name);
				ArrayList<ArrayList<String>> data = DatabaseEngine.projectionD(table_name, columns);
				ArrayList<String> pkey = new ArrayList<String>();
				DatabaseEngine.createD(new_relation, data, pkey);
				return;
				
			}
			
			else if(tree.get(2).getChild(0).getPayload() instanceof ParserParser.RenamingContext){
				
				ParseTree renaming_tree = tree.get(2).getChild(0);
				String table_name = renaming_tree.getChild(4).getText();
				ParseTree operands = renaming_tree.getChild(2);
				String old_name = operands.getChild(0).getText();
				String new_name = operands.getChild(2).getText();
				DatabaseEngine.query(new_relation, table_name);
				DatabaseEngine.renamingD(new_relation, old_name, new_name);
				return;
				
				
			}
			
		}
		
		else if((tree.get(2).getChild(0).getPayload() instanceof ParserParser.UnionContext) || (tree.get(2).getChild(0).getPayload() instanceof ParserParser.DifferenceContext) || (tree.get(2).getChild(0).getPayload() instanceof ParserParser.ProductContext)){ //must be a union or difference or product
			
			String table_name = tree.get(0).getText();
			DatabaseEngine.queryUDP(table_name);
		}
		
		else{	//must be an atomic expression, don't really need to do anything.
			
			String table_name1 = tree.get(0).getText();
			String table_name2 = tree.get(2).getText();
			DatabaseEngine.query(table_name1, table_name2);
			
		}
	
	}


	@Override public void enterSelection(ParserParser.SelectionContext ctx) {

		List<ParseTree> tree = ctx.children;
		String table_name = tree.get(4).getText();
		
		DatabaseEngine.enterSelect(table_name);
		return;
	
	}

	@Override public void exitSelection(ParserParser.SelectionContext ctx) { 
	
		List<ParseTree> tree = ctx.children;
		
		String table_name = tree.get(4).getText();
		
		DatabaseEngine.selectionD(table_name);
		
		return;
	
	}
	
	@Override public void enterProjection(ParserParser.ProjectionContext ctx) { 
	
		List<ParseTree> tree = ctx.children;
		String table_name = tree.get(4).getText();
		
		DatabaseEngine.enterSelect(table_name);
		return;
	
	}
	
	@Override public void exitProjection(ParserParser.ProjectionContext ctx) { }
	
	@Override public void enterRenaming(ParserParser.RenamingContext ctx) {


	}
	
	@Override public void exitRenaming(ParserParser.RenamingContext ctx) { }

	@Override public void enterCondition(ParserParser.ConditionContext ctx) { }

	@Override public void exitCondition(ParserParser.ConditionContext ctx) { 
	
		int i = ctx.getChildCount();
		
		if(i == 1){
			
			//dont need to do anything, only one conjunction present in condition
			DatabaseEngine.or2();
			
		}
		
		else {	//Here we do things, because our condition contains two or more conunctions with one or more condidition operators
		
			i = (i - 1) / 2;
		
			for(int j = 1; j < i; j++){
				
				DatabaseEngine.or1();
			
			}
			
			DatabaseEngine.or();
			
		}
	
	}

	@Override public void enterConjunction(ParserParser.ConjunctionContext ctx) { }
	
	@Override public void exitConjunction(ParserParser.ConjunctionContext ctx) { 
	
		int i = ctx.getChildCount();
		
		if(i == 1){
			
			//We don't need to do anything, only one comparison present in conjunction
			DatabaseEngine.and2();
			
		}
		
		else { //Here we do things, because our conjunction contains two or more comparisons with one or more conjunction operations
			
			i = (i - 1) / 2;
			
			for(int j = 1; j < i; j++){
				
				DatabaseEngine.and1();
				
			}
			
			DatabaseEngine.and();
			
		}
		
	
	}

	@Override public void enterComparison(ParserParser.ComparisonContext ctx) { }

	@Override public void exitComparison(ParserParser.ComparisonContext ctx) {

		List<ParseTree> tree = ctx.children;
		ArrayList<String> result = new ArrayList<String>();
		
		String op = tree.get(1).getText();
		String operand1 = tree.get(0).getText();
		String operand2 = tree.get(2).getText();
		
		if(tree.get(2).getChild(0).getChildCount() > 1){
			
			if(tree.get(2).getChild(0).getChild(0).getText().equals("\"")) {
				
				operand2 = tree.get(2).getChild(0).getChild(1).getText();
				
			}
			
			else{
				
				operand2 = "";
				
				for(int i = 0; i < tree.get(2).getChild(0).getChildCount(); i++) {
					
					operand2 = operand2 + tree.get(2).getChild(0).getChild(i).getText();
					
				}
				
			}
			
		}
		
		
		if(op.equals("==")) {
			
			result = DatabaseEngine.equal(operand1, operand2);
			
		}
		
		else if(op.equals("!=")) {
			
			result = DatabaseEngine.notEqual(operand1, operand2);
			
		}
		
		else if(op.equals("<")) {
			
			result = DatabaseEngine.less(operand1, operand2);
			
		}
		
		else if(op.equals(">")) {
			
			result = DatabaseEngine.greater(operand1, operand2);
			
		}
		
		else if(op.equals("<=")) {
			
			result = DatabaseEngine.lessEqual(operand1, operand2);
			
		}
		
		else if(op.equals(">=")) {
			
			result = DatabaseEngine.greaterEqual(operand1, operand2);
		
		}
		
		else {
			
			//should never happen
			
		}
	
	}


	@Override public void enterUnion(ParserParser.UnionContext ctx) { }

	@Override public void exitUnion(ParserParser.UnionContext ctx) { 
	
		List<ParseTree> tree = ctx.children;
		
		ArrayList<ArrayList<String>> new_datas = new ArrayList<ArrayList<String>>();
		
		if((tree.get(0).getChildCount() <= 1) && (tree.get(2).getChildCount() <= 1)) {
			
			new_datas = DatabaseEngine.unionD(tree.get(0).getText(), tree.get(2).getText());

		}
		
		else if ((tree.get(0).getChildCount() > 1) && (tree.get(2).getChildCount() > 1)) {
			
			new_datas = DatabaseEngine.unionD(null, null);
			
		} 
		
		else if((tree.get(0).getChildCount() > 1) && (tree.get(2).getChildCount() <= 1)) {
			
			new_datas = DatabaseEngine.unionD(tree.get(2).getText(), null);
			
		}
		
		else if((tree.get(0).getChildCount() <= 1) && (tree.get(0).getChildCount() > 1)) {
			
			new_datas = DatabaseEngine.unionD(tree.get(0).getText(), null);
			
		}
		
		else {
			
			//shouldnt happen
			
		}
		
		return;
	
	}

	@Override public void enterDifference(ParserParser.DifferenceContext ctx) { }

	@Override public void exitDifference(ParserParser.DifferenceContext ctx) { 
	
		List<ParseTree> tree = ctx.children;
		
		ArrayList<ArrayList<String>> new_datas = new ArrayList<ArrayList<String>>();
		
		if((tree.get(0).getChildCount() <= 1) && (tree.get(2).getChildCount() <= 1)) {
			
			new_datas = DatabaseEngine.differenceD(tree.get(0).getText(), tree.get(2).getText());

		}
		
		else if ((tree.get(0).getChildCount() > 1) && (tree.get(2).getChildCount() > 1)) {
			
			new_datas = DatabaseEngine.differenceD(null, null);
			
		} 
		
		else if((tree.get(0).getChildCount() > 1) && (tree.get(2).getChildCount() <= 1)) {
			
			new_datas = DatabaseEngine.differenceD(tree.get(2).getText(), null);
			
		}
		
		else if((tree.get(0).getChildCount() <= 1) && (tree.get(0).getChildCount() > 1)) {
			
			new_datas = DatabaseEngine.differenceD(tree.get(0).getText(), null);
			
		}
		
		else {
			
			//shouldnt happen
			
		}
		
		return;
	
	}

	@Override public void enterProduct(ParserParser.ProductContext ctx) { }

	@Override public void exitProduct(ParserParser.ProductContext ctx) { 
	
		List<ParseTree> tree = ctx.children;
		
		ArrayList<ArrayList<String>> new_datas = new ArrayList<ArrayList<String>>();
		
		if((tree.get(0).getChildCount() <= 1) && (tree.get(2).getChildCount() <= 1)) {
			
			new_datas = DatabaseEngine.productD(tree.get(0).getText(), tree.get(2).getText());

		}
		
		else if ((tree.get(0).getChildCount() > 1) && (tree.get(2).getChildCount() > 1)) {
			
			new_datas = DatabaseEngine.productD(null, null);
			
		} 
		
		else if((tree.get(0).getChildCount() > 1) && (tree.get(2).getChildCount() <= 1)) {
			
			new_datas = DatabaseEngine.productD(tree.get(2).getText(), null);
			
		}
		
		else if((tree.get(0).getChildCount() <= 1) && (tree.get(0).getChildCount() > 1)) {
			
			new_datas = DatabaseEngine.productD(tree.get(0).getText(), null);
			
		}
		
		else {
			
			//shouldnt happen
			
		}
		
		return;
	
	}

	@Override public void enterNatural_join(ParserParser.Natural_joinContext ctx) { }

	@Override public void exitNatural_join(ParserParser.Natural_joinContext ctx) { }


	@Override public void enterOpen_cmd(ParserParser.Open_cmdContext ctx) { 
	
		ParseTree parseTree = ctx.getChild(1);
		String input = parseTree.getText();
		
		System.out.println("Opening " + input);
		DatabaseEngine.openD(input);
		return;
	
	}

	@Override public void exitOpen_cmd(ParserParser.Open_cmdContext ctx) { }
	
	@Override public void enterClose_cmd(ParserParser.Close_cmdContext ctx) {
		
		ParseTree parseTree = ctx.getChild(1);
		String input = parseTree.getText();
		
		System.out.println("Closing " + input);
		DatabaseEngine.closeD(input);
		return;

	}

	@Override public void exitClose_cmd(ParserParser.Close_cmdContext ctx) { }

	@Override public void enterWrite_cmd(ParserParser.Write_cmdContext ctx) {

		ParseTree parseTree = ctx.getChild(1);
		String input = parseTree.getText();
		
		System.out.println("Writing " + input + " to file");
		DatabaseEngine.writeD(input);
		return;
	
	}

	@Override public void exitWrite_cmd(ParserParser.Write_cmdContext ctx) { }

	@Override public void enterExit_cmd(ParserParser.Exit_cmdContext ctx) { 
	
		System.out.println("Exiting...");
		DatabaseEngine.exitD();
		return;
	
	}

	@Override public void exitExit_cmd(ParserParser.Exit_cmdContext ctx) { }

	@Override public void enterShow_cmd(ParserParser.Show_cmdContext ctx) { 
	
		ParseTree parseTree = ctx.getChild(1);
		String input = parseTree.getText();
		
		System.out.println("Showing " + input);
		DatabaseEngine.showD(input);
	
	}

	@Override public void exitShow_cmd(ParserParser.Show_cmdContext ctx) { }

	@Override public void enterCreate_cmd(ParserParser.Create_cmdContext ctx) {

		List<ParseTree> children = ctx.children;
		ParseTree relation = children.get(1);
		ParseTree attribute_list = children.get(3);
		ParseTree primary_key_list = children.get(7);

		System.out.println("Creating table " + relation.getText());
		
		ArrayList<ArrayList<String>> datas = new ArrayList<ArrayList<String>>();
		ArrayList<String> keys = new ArrayList<String>();
		int count = 0; 
		
		for(int i = 0; i < (((attribute_list.getChildCount() + 1) / 3)); i++){
			
			ArrayList<String> newArrayList = new ArrayList<String>();
			datas.add(i, newArrayList);
			datas.get(i).add(0, attribute_list.getChild(count).getText());
			ParseTree varchar = attribute_list.getChild(count + 1);
			
			if(varchar.getText().equals("INTEGER")) {
				
				datas.get(i).add(1, "-1");
				
			}
			
			else {
				
				datas.get(i).add(1, varchar.getChild(2).getText());
				
			}
			
			count = count + 3; 
			
			
		}
		
		count = 0;
		
		for(int i = 0; i < ((primary_key_list.getChildCount() + 1) / 2) - 1; i++) {
			
			ArrayList<String> newArrayList = new ArrayList<String>();
			keys.add(0, primary_key_list.getChild(count).getText());
			count = count + 2; 
			
		}
		
		
		DatabaseEngine.createD(relation.getText(), datas, keys);
		
		
	
		return;
	
	}

	@Override public void exitCreate_cmd(ParserParser.Create_cmdContext ctx) { }

	@Override public void enterUpdate_cmd(ParserParser.Update_cmdContext ctx) { 
	
	
	}

	@Override public void exitUpdate_cmd(ParserParser.Update_cmdContext ctx) { 
	
		
	
	}

	@Override public void enterInsert_cmd(ParserParser.Insert_cmdContext ctx) {
		
	}

	@Override public void exitInsert_cmd(ParserParser.Insert_cmdContext ctx) {

		List<ParseTree> tree = ctx.children;
		String relation_name = tree.get(1).getText();
		
		String fork = tree.get(2).getText();
		if(fork.equals("VALUES FROM")){
			
			
			int count = 4;
			
			ArrayList<String> element = new ArrayList<String>();
			
			for(int i = 0; i < ((tree.size() - 4) / 2); i++ ){
				
				ParseTree literal = tree.get(count);
				
				if((literal.getChild(0).getText().equals("\"") != true)) {
					
					element.add(literal.getText());
					count = count + 2;
					
				}
				
				else {
					
					element.add(literal.getChild(1).getText());
					count = count + 2;
					
				}
				
			}
			
			DatabaseEngine.insertSingleD(relation_name, element);
			
			return;
		
		}
		
		else if(fork.equals("VALUES FROM RELATION")) {
			
			String from_relation = tree.get(3).getText();
			
		}
	}

	@Override public void enterDelete_cmd(ParserParser.Delete_cmdContext ctx) {	}

	@Override public void exitDelete_cmd(ParserParser.Delete_cmdContext ctx) {			//works for very simple conditions, I.E one conditions and two operands.

		List<ParseTree> children = ctx.children;
		ParseTree relation = children.get(1);
		String relation_name = relation.getText();
		/*ParserParser.ConditionContext conditions = (ParserParser.ConditionContext) children.get(3).getPayload();
		List<ParseTree> condition_tree = conditions.children;
		String relation_name = relation.getText();*/
		
		/*for(int i = 0; i < (conditions.getChildCount()); i++) {
			
			System.out.println(conditions.depth());
			System.out.println(conditions.getChildCount());
			
		}*/
		
		ParseTree conditions = children.get(3);
		
		System.out.println("Deleting from " + relation_name + " where " + conditions.getText());
		String op1 = conditions.getChild(0).getChild(0).getChild(0).getText();
		String op2 = conditions.getChild(0).getChild(0).getChild(2).getText();
		String cond = conditions.getChild(0).getChild(0).getChild(1).getText();
		
		if(cond.equals("==")){
			
			ArrayList<String> to_delete = DatabaseEngine.equal(relation_name, op1, op2);
			DatabaseEngine.deleteD(relation_name, to_delete);
			return;
			
		}
		
		else if(cond.equals("!=")) {
			
			ArrayList<String> to_delete = DatabaseEngine.notEqual(relation_name, op1, op2);
			DatabaseEngine.deleteD(relation_name, to_delete);
			return;
		}
		
		else if(cond.equals("<")) {
			
			ArrayList<String> to_delete = DatabaseEngine.less(relation_name, op1, op2);
			DatabaseEngine.deleteD(relation_name, to_delete);
			return;
		}
		
		else if (cond.equals(">")) {
			
			ArrayList<String> to_delete = DatabaseEngine.greater(relation_name, op1, op2);
			DatabaseEngine.deleteD(relation_name, to_delete);
			return;
		}
		
		else if (cond.equals("<=")) {
			
			ArrayList<String> to_delete = DatabaseEngine.lessEqual(relation_name, op1, op2);
			DatabaseEngine.deleteD(relation_name, to_delete);
			return;
		}
		
		else if (cond.equals(">=")) {
			
			ArrayList<String> to_delete = DatabaseEngine.greaterEqual(relation_name, op1, op2);
			DatabaseEngine.deleteD(relation_name, to_delete);
			return;
		}
		
		else {
			
			//This shouldnt happen
			
		}
		
		
		return;


	}
	
	/*@Override public void enterQuery(ParserParser.QueryContext ctx) {									//*******************Add this again
		
		ParseTree relation = ctx.getChild(0);
		ParseTree condition = ctx.getChild(2);
		ParserParser.ExprContext condition_tree = (ParserParser.ExprContext) condition.getPayload();
		
		//System.out.println(relation.getText() + " " + condition.getText());
		//System.out.println(condition_tree.depth());
		
		ArrayList<String> list = new ArrayList<String>();
		
		list = populateList(condition, list);
		//list = convert_to_postfix(list);
		
		ArrayList<ArrayList<String>> comparison_results = new ArrayList<ArrayList<String>>();
		
		for(int i = 0; i < list.size(); i++) {
			
			String op1 = list.get(i);
			System.out.print(op1);
			
		}	
		
		System.out.println(" ");
		
		return;
		
	}*/

	public ArrayList<String> populateList(ParseTree tree, ArrayList<String> list) {
	
		while(tree.getChildCount() != 0) {
			
			
			if(tree.getChildCount() == 1) {
				
				tree = tree.getChild(0);
				
			}
			
			else if (tree.getChildCount() == 2) {
				
				list = populateList(tree.getChild(0), list);
				list = populateList(tree.getChild(1), list);
				break;
				
			}
			
			else if(tree.getChildCount() == 3) {
				
				list = populateList(tree.getChild(0), list);
				list = populateList(tree.getChild(1), list);
				list = populateList(tree.getChild(2), list);
				break;
				
			}
			
			else if(tree.getChildCount() == 4) {
				
				list = populateList(tree.getChild(0), list);
				list = populateList(tree.getChild(1), list);
				list = populateList(tree.getChild(2), list);
				list = populateList(tree.getChild(3), list);
				break;
				
			}
			
			else if(tree.getChildCount() == 5) {
				
				list = populateList(tree.getChild(0), list);
				list = populateList(tree.getChild(1), list);
				list = populateList(tree.getChild(2), list);
				list = populateList(tree.getChild(3), list);
				list = populateList(tree.getChild(4), list);
				break;
				
			}
			
			else{
				
				//shouldnt happen
				break;
				
			}
		
		}
		
		if(tree.getText().equals("\"")) {
			
			return list; 
			
		}
		
		else {
			
			if(tree.getChildCount() == 0) {
				
				list.add(tree.getText());
				
			}
			
		}
		
		return list;
		
	}
	
	public void run_commands(ArrayList<String> arguments) {
		
		String first = arguments.get(0);
		
		if(first.equals("select") || first.equals("project") || first.equals("rename")) {
			
			int count = 1;
			int start = 2;
			int end = 2; 
			
			while(count > 0) {
				
				String op = arguments.get(end);
				
				if(op.equals(")")) {
					
					count --; 
					
				}
				
				else if (op.equals("(")) {
					
					count ++; 
				
				}
				
				else{
					
					end++;
					
				}
			}
		}	
	}
	
	public ArrayList<String> convert_to_postfix(ArrayList<String> arguments) {
		
		ArrayList<String> output = new ArrayList<String>();
		Stack<String> opstack = new Stack<String>();
		
		for(int i = 0; i < arguments.size(); i ++) {
			
			String instance = arguments.get(i);
			
			if(instance.equals("select") || instance.equals("project") || instance.equals("rename") || instance.equals("+") || instance.equals("-") || instance.equals("*") || instance.equals("&") || instance.equals("(")) {
				
				opstack.push(instance);
				
			}
			
			else if (instance.equals(")")) {
				
				String op = opstack.pop();
				output.add(op);
				
				while((op.equals("select") || op.equals("project") || op.equals("rename")) != true) {
					
					op = opstack.pop();
					output.add(op);
					
				}
				
			}
			
			else {
				
				output.add(instance);
				
			}
			
		}
		
		for(int i = 0; i < opstack.size(); i++) {
			
			output.add(opstack.pop());
			
		}
		
		return output; 
		
	}

	@Override public void visitTerminal(TerminalNode node) { }

	@Override public void visitErrorNode(ErrorNode node) { }
}