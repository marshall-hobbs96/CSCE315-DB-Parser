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
	



	@Override public void enterSelection(ParserParser.SelectionContext ctx) { }

	@Override public void exitSelection(ParserParser.SelectionContext ctx) { }

	@Override public void enterCondition(ParserParser.ConditionContext ctx) { }

	@Override public void exitCondition(ParserParser.ConditionContext ctx) { }

	@Override public void enterConjunction(ParserParser.ConjunctionContext ctx) { }
	
	@Override public void exitConjunction(ParserParser.ConjunctionContext ctx) { }

	@Override public void enterComparison(ParserParser.ComparisonContext ctx) { }

	@Override public void exitComparison(ParserParser.ComparisonContext ctx) { }

	@Override public void enterProjection(ParserParser.ProjectionContext ctx) { }

	@Override public void exitProjection(ParserParser.ProjectionContext ctx) { }

	@Override public void enterRenaming(ParserParser.RenamingContext ctx) { }

	@Override public void exitRenaming(ParserParser.RenamingContext ctx) { }

	@Override public void enterUnion(ParserParser.UnionContext ctx) { }

	@Override public void exitUnion(ParserParser.UnionContext ctx) { }

	@Override public void enterDifference(ParserParser.DifferenceContext ctx) { }

	@Override public void exitDifference(ParserParser.DifferenceContext ctx) { }

	@Override public void enterProduct(ParserParser.ProductContext ctx) { }

	@Override public void exitProduct(ParserParser.ProductContext ctx) { }

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
		
		for(int i = 0; i < (((attribute_list.getChildCount() + 1) / 3) - 1); i++){
			
			ArrayList<String> newArrayList = new ArrayList<String>();
			datas.add(i, newArrayList);
			datas.get(i).add(0, attribute_list.getChild(count).getText());
			ParseTree varchar = attribute_list.getChild(count + 1);
			datas.get(i).add(1, varchar.getChild(2).getText());
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

	@Override public void enterUpdate_cmd(ParserParser.Update_cmdContext ctx) { }

	@Override public void exitUpdate_cmd(ParserParser.Update_cmdContext ctx) { }

	@Override public void enterInsert_cmd(ParserParser.Insert_cmdContext ctx) { }

	@Override public void exitInsert_cmd(ParserParser.Insert_cmdContext ctx) { }

	@Override public void enterDelete_cmd(ParserParser.Delete_cmdContext ctx) {	}

	@Override public void exitDelete_cmd(ParserParser.Delete_cmdContext ctx) {			//works for very simple conditions, I.E one conditions and two operands.

		List<ParseTree> children = ctx.children;
		ParseTree relation = children.get(1);
		String relation_name = relation.getText();
		/*ParserParser.ConditionContext conditions = (ParserParser.ConditionContext) children.get(3).getPayload();
		List<ParseTree> condition_tree = conditions.children;
		String relation_name = relation.getText();
		System.out.println("Deleting from " + relation_name + " where " + conditions.getText());
		
		for(int i = 0; i < (conditions.getChildCount()); i++) {
			
			System.out.println(conditions.depth());
			System.out.println(conditions.getChildCount());
			
		}*/
		
		ParseTree conditions = children.get(3);
		String op1 = conditions.getChild(0).getChild(0).getChild(0).getText();
		String op2 = conditions.getChild(0).getChild(0).getChild(2).getText();
		String cond = conditions.getChild(0).getChild(0).getChild(1).getText();
		
		if(cond.equals("==")){
			
			DatabaseEngine.equal(relation_name, op1, op2);
			return;
			
		}
		
		else if(cond.equals("!=")) {
			
			DatabaseEngine.notEqual(relation_name, op1, op2);
			return;
		}
		
		else if(cond.equals("<")) {
			
			DatabaseEngine.less(relation_name, op1, op2);
			return;
		}
		
		else if (cond.equals(">")) {
			
			DatabaseEngine.greater(relation_name, op1, op2);
			return;
		}
		
		else if (cond.equals("<=")) {
			
			DatabaseEngine.lessEqual(relation_name, op1, op2);
			return;
		}
		
		else if (cond.equals(">=")) {
			
			DatabaseEngine.greaterEqual(relation_name, op1, op2);
			return;
		}
		
		else {
			
			//This shouldnt happen
			
		}
		
		
		return;


	}



	@Override public void visitTerminal(TerminalNode node) { }

	@Override public void visitErrorNode(ErrorNode node) { }
}
	
	
	
	
	
