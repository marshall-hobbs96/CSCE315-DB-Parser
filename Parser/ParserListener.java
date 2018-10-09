// Generated from Parser.g4 by ANTLR 4.7.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ParserParser}.
 */
public interface ParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ParserParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(ParserParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link ParserParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(ParserParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link ParserParser#query}.
	 * @param ctx the parse tree
	 */
	void enterQuery(ParserParser.QueryContext ctx);
	/**
	 * Exit a parse tree produced by {@link ParserParser#query}.
	 * @param ctx the parse tree
	 */
	void exitQuery(ParserParser.QueryContext ctx);
	/**
	 * Enter a parse tree produced by {@link ParserParser#relation_name}.
	 * @param ctx the parse tree
	 */
	void enterRelation_name(ParserParser.Relation_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link ParserParser#relation_name}.
	 * @param ctx the parse tree
	 */
	void exitRelation_name(ParserParser.Relation_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link ParserParser#identifier}.
	 * @param ctx the parse tree
	 */
	void enterIdentifier(ParserParser.IdentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link ParserParser#identifier}.
	 * @param ctx the parse tree
	 */
	void exitIdentifier(ParserParser.IdentifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link ParserParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(ParserParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link ParserParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(ParserParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link ParserParser#atomic_expression}.
	 * @param ctx the parse tree
	 */
	void enterAtomic_expression(ParserParser.Atomic_expressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ParserParser#atomic_expression}.
	 * @param ctx the parse tree
	 */
	void exitAtomic_expression(ParserParser.Atomic_expressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ParserParser#two_identifiers}.
	 * @param ctx the parse tree
	 */
	void enterTwo_identifiers(ParserParser.Two_identifiersContext ctx);
	/**
	 * Exit a parse tree produced by {@link ParserParser#two_identifiers}.
	 * @param ctx the parse tree
	 */
	void exitTwo_identifiers(ParserParser.Two_identifiersContext ctx);
	/**
	 * Enter a parse tree produced by {@link ParserParser#selection}.
	 * @param ctx the parse tree
	 */
	void enterSelection(ParserParser.SelectionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ParserParser#selection}.
	 * @param ctx the parse tree
	 */
	void exitSelection(ParserParser.SelectionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ParserParser#condition}.
	 * @param ctx the parse tree
	 */
	void enterCondition(ParserParser.ConditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ParserParser#condition}.
	 * @param ctx the parse tree
	 */
	void exitCondition(ParserParser.ConditionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ParserParser#conjunction}.
	 * @param ctx the parse tree
	 */
	void enterConjunction(ParserParser.ConjunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ParserParser#conjunction}.
	 * @param ctx the parse tree
	 */
	void exitConjunction(ParserParser.ConjunctionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ParserParser#comparison}.
	 * @param ctx the parse tree
	 */
	void enterComparison(ParserParser.ComparisonContext ctx);
	/**
	 * Exit a parse tree produced by {@link ParserParser#comparison}.
	 * @param ctx the parse tree
	 */
	void exitComparison(ParserParser.ComparisonContext ctx);
	/**
	 * Enter a parse tree produced by {@link ParserParser#op}.
	 * @param ctx the parse tree
	 */
	void enterOp(ParserParser.OpContext ctx);
	/**
	 * Exit a parse tree produced by {@link ParserParser#op}.
	 * @param ctx the parse tree
	 */
	void exitOp(ParserParser.OpContext ctx);
	/**
	 * Enter a parse tree produced by {@link ParserParser#operand}.
	 * @param ctx the parse tree
	 */
	void enterOperand(ParserParser.OperandContext ctx);
	/**
	 * Exit a parse tree produced by {@link ParserParser#operand}.
	 * @param ctx the parse tree
	 */
	void exitOperand(ParserParser.OperandContext ctx);
	/**
	 * Enter a parse tree produced by {@link ParserParser#attribute_name}.
	 * @param ctx the parse tree
	 */
	void enterAttribute_name(ParserParser.Attribute_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link ParserParser#attribute_name}.
	 * @param ctx the parse tree
	 */
	void exitAttribute_name(ParserParser.Attribute_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link ParserParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(ParserParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link ParserParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(ParserParser.LiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link ParserParser#projection}.
	 * @param ctx the parse tree
	 */
	void enterProjection(ParserParser.ProjectionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ParserParser#projection}.
	 * @param ctx the parse tree
	 */
	void exitProjection(ParserParser.ProjectionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ParserParser#attribute_list}.
	 * @param ctx the parse tree
	 */
	void enterAttribute_list(ParserParser.Attribute_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link ParserParser#attribute_list}.
	 * @param ctx the parse tree
	 */
	void exitAttribute_list(ParserParser.Attribute_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link ParserParser#renaming}.
	 * @param ctx the parse tree
	 */
	void enterRenaming(ParserParser.RenamingContext ctx);
	/**
	 * Exit a parse tree produced by {@link ParserParser#renaming}.
	 * @param ctx the parse tree
	 */
	void exitRenaming(ParserParser.RenamingContext ctx);
	/**
	 * Enter a parse tree produced by {@link ParserParser#union}.
	 * @param ctx the parse tree
	 */
	void enterUnion(ParserParser.UnionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ParserParser#union}.
	 * @param ctx the parse tree
	 */
	void exitUnion(ParserParser.UnionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ParserParser#difference}.
	 * @param ctx the parse tree
	 */
	void enterDifference(ParserParser.DifferenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link ParserParser#difference}.
	 * @param ctx the parse tree
	 */
	void exitDifference(ParserParser.DifferenceContext ctx);
	/**
	 * Enter a parse tree produced by {@link ParserParser#product}.
	 * @param ctx the parse tree
	 */
	void enterProduct(ParserParser.ProductContext ctx);
	/**
	 * Exit a parse tree produced by {@link ParserParser#product}.
	 * @param ctx the parse tree
	 */
	void exitProduct(ParserParser.ProductContext ctx);
	/**
	 * Enter a parse tree produced by {@link ParserParser#natural_join}.
	 * @param ctx the parse tree
	 */
	void enterNatural_join(ParserParser.Natural_joinContext ctx);
	/**
	 * Exit a parse tree produced by {@link ParserParser#natural_join}.
	 * @param ctx the parse tree
	 */
	void exitNatural_join(ParserParser.Natural_joinContext ctx);
	/**
	 * Enter a parse tree produced by {@link ParserParser#command}.
	 * @param ctx the parse tree
	 */
	void enterCommand(ParserParser.CommandContext ctx);
	/**
	 * Exit a parse tree produced by {@link ParserParser#command}.
	 * @param ctx the parse tree
	 */
	void exitCommand(ParserParser.CommandContext ctx);
	/**
	 * Enter a parse tree produced by {@link ParserParser#open_cmd}.
	 * @param ctx the parse tree
	 */
	void enterOpen_cmd(ParserParser.Open_cmdContext ctx);
	/**
	 * Exit a parse tree produced by {@link ParserParser#open_cmd}.
	 * @param ctx the parse tree
	 */
	void exitOpen_cmd(ParserParser.Open_cmdContext ctx);
	/**
	 * Enter a parse tree produced by {@link ParserParser#close_cmd}.
	 * @param ctx the parse tree
	 */
	void enterClose_cmd(ParserParser.Close_cmdContext ctx);
	/**
	 * Exit a parse tree produced by {@link ParserParser#close_cmd}.
	 * @param ctx the parse tree
	 */
	void exitClose_cmd(ParserParser.Close_cmdContext ctx);
	/**
	 * Enter a parse tree produced by {@link ParserParser#write_cmd}.
	 * @param ctx the parse tree
	 */
	void enterWrite_cmd(ParserParser.Write_cmdContext ctx);
	/**
	 * Exit a parse tree produced by {@link ParserParser#write_cmd}.
	 * @param ctx the parse tree
	 */
	void exitWrite_cmd(ParserParser.Write_cmdContext ctx);
	/**
	 * Enter a parse tree produced by {@link ParserParser#exit_cmd}.
	 * @param ctx the parse tree
	 */
	void enterExit_cmd(ParserParser.Exit_cmdContext ctx);
	/**
	 * Exit a parse tree produced by {@link ParserParser#exit_cmd}.
	 * @param ctx the parse tree
	 */
	void exitExit_cmd(ParserParser.Exit_cmdContext ctx);
	/**
	 * Enter a parse tree produced by {@link ParserParser#show_cmd}.
	 * @param ctx the parse tree
	 */
	void enterShow_cmd(ParserParser.Show_cmdContext ctx);
	/**
	 * Exit a parse tree produced by {@link ParserParser#show_cmd}.
	 * @param ctx the parse tree
	 */
	void exitShow_cmd(ParserParser.Show_cmdContext ctx);
	/**
	 * Enter a parse tree produced by {@link ParserParser#create_cmd}.
	 * @param ctx the parse tree
	 */
	void enterCreate_cmd(ParserParser.Create_cmdContext ctx);
	/**
	 * Exit a parse tree produced by {@link ParserParser#create_cmd}.
	 * @param ctx the parse tree
	 */
	void exitCreate_cmd(ParserParser.Create_cmdContext ctx);
	/**
	 * Enter a parse tree produced by {@link ParserParser#update_cmd}.
	 * @param ctx the parse tree
	 */
	void enterUpdate_cmd(ParserParser.Update_cmdContext ctx);
	/**
	 * Exit a parse tree produced by {@link ParserParser#update_cmd}.
	 * @param ctx the parse tree
	 */
	void exitUpdate_cmd(ParserParser.Update_cmdContext ctx);
	/**
	 * Enter a parse tree produced by {@link ParserParser#insert_cmd}.
	 * @param ctx the parse tree
	 */
	void enterInsert_cmd(ParserParser.Insert_cmdContext ctx);
	/**
	 * Exit a parse tree produced by {@link ParserParser#insert_cmd}.
	 * @param ctx the parse tree
	 */
	void exitInsert_cmd(ParserParser.Insert_cmdContext ctx);
	/**
	 * Enter a parse tree produced by {@link ParserParser#delete_cmd}.
	 * @param ctx the parse tree
	 */
	void enterDelete_cmd(ParserParser.Delete_cmdContext ctx);
	/**
	 * Exit a parse tree produced by {@link ParserParser#delete_cmd}.
	 * @param ctx the parse tree
	 */
	void exitDelete_cmd(ParserParser.Delete_cmdContext ctx);
	/**
	 * Enter a parse tree produced by {@link ParserParser#typed_attribute_list}.
	 * @param ctx the parse tree
	 */
	void enterTyped_attribute_list(ParserParser.Typed_attribute_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link ParserParser#typed_attribute_list}.
	 * @param ctx the parse tree
	 */
	void exitTyped_attribute_list(ParserParser.Typed_attribute_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link ParserParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(ParserParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link ParserParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(ParserParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link ParserParser#integer}.
	 * @param ctx the parse tree
	 */
	void enterInteger(ParserParser.IntegerContext ctx);
	/**
	 * Exit a parse tree produced by {@link ParserParser#integer}.
	 * @param ctx the parse tree
	 */
	void exitInteger(ParserParser.IntegerContext ctx);
}