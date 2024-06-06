package Plugin.antlr4;// Generated from ./Grammar.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link GrammarParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface GrammarVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link GrammarParser#start}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStart(GrammarParser.StartContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#requirement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRequirement(GrammarParser.RequirementContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#jobOpeningReference}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJobOpeningReference(GrammarParser.JobOpeningReferenceContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#body}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBody(GrammarParser.BodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#requirementAnswer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRequirementAnswer(GrammarParser.RequirementAnswerContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#minimumRequirement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMinimumRequirement(GrammarParser.MinimumRequirementContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#specificRequirement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSpecificRequirement(GrammarParser.SpecificRequirementContext ctx);
}