package Plugin.antlr4;// Generated from ./Grammar.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link GrammarParser}.
 */
public interface GrammarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link GrammarParser#start}.
	 * @param ctx the parse tree
	 */
	void enterStart(GrammarParser.StartContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#start}.
	 * @param ctx the parse tree
	 */
	void exitStart(GrammarParser.StartContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#requirement}.
	 * @param ctx the parse tree
	 */
	void enterRequirement(GrammarParser.RequirementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#requirement}.
	 * @param ctx the parse tree
	 */
	void exitRequirement(GrammarParser.RequirementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#jobOpeningReference}.
	 * @param ctx the parse tree
	 */
	void enterJobOpeningReference(GrammarParser.JobOpeningReferenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#jobOpeningReference}.
	 * @param ctx the parse tree
	 */
	void exitJobOpeningReference(GrammarParser.JobOpeningReferenceContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#body}.
	 * @param ctx the parse tree
	 */
	void enterBody(GrammarParser.BodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#body}.
	 * @param ctx the parse tree
	 */
	void exitBody(GrammarParser.BodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#requirementAnswer}.
	 * @param ctx the parse tree
	 */
	void enterRequirementAnswer(GrammarParser.RequirementAnswerContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#requirementAnswer}.
	 * @param ctx the parse tree
	 */
	void exitRequirementAnswer(GrammarParser.RequirementAnswerContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#minimumRequirement}.
	 * @param ctx the parse tree
	 */
	void enterMinimumRequirement(GrammarParser.MinimumRequirementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#minimumRequirement}.
	 * @param ctx the parse tree
	 */
	void exitMinimumRequirement(GrammarParser.MinimumRequirementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#specificRequirement}.
	 * @param ctx the parse tree
	 */
	void enterSpecificRequirement(GrammarParser.SpecificRequirementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#specificRequirement}.
	 * @param ctx the parse tree
	 */
	void exitSpecificRequirement(GrammarParser.SpecificRequirementContext ctx);
}