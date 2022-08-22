// Generated from transpiler/IsiLang.g4 by ANTLR 4.7.1
package compiler.parser;

    import java.util.HashSet;
    import java.util.ArrayList;
    import java.util.Stack;
    import compiler.exceptions.SemanticException;
    import compiler.datastructures.SymbolTable;
    import compiler.datastructures.Symbol;
    import compiler.datastructures.Variable;
    import compiler.ast.CommandRead;
    import compiler.ast.CommandWrite;
    import compiler.ast.CommandDecision;
    import compiler.ast.AbstractCommand;
    import compiler.ast.CommandAssignment;
    import compiler.ast.CommandSwitchCase;
    import compiler.ast.CommandSwitch;
    import compiler.ast.CommandLoop;
    import compiler.ast.Program;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link IsiLangParser}.
 */
public interface IsiLangListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link IsiLangParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterProg(IsiLangParser.ProgContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLangParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitProg(IsiLangParser.ProgContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLangParser#declara}.
	 * @param ctx the parse tree
	 */
	void enterDeclara(IsiLangParser.DeclaraContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLangParser#declara}.
	 * @param ctx the parse tree
	 */
	void exitDeclara(IsiLangParser.DeclaraContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLangParser#criacaoVariavel}.
	 * @param ctx the parse tree
	 */
	void enterCriacaoVariavel(IsiLangParser.CriacaoVariavelContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLangParser#criacaoVariavel}.
	 * @param ctx the parse tree
	 */
	void exitCriacaoVariavel(IsiLangParser.CriacaoVariavelContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLangParser#bloco}.
	 * @param ctx the parse tree
	 */
	void enterBloco(IsiLangParser.BlocoContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLangParser#bloco}.
	 * @param ctx the parse tree
	 */
	void exitBloco(IsiLangParser.BlocoContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLangParser#cmd}.
	 * @param ctx the parse tree
	 */
	void enterCmd(IsiLangParser.CmdContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLangParser#cmd}.
	 * @param ctx the parse tree
	 */
	void exitCmd(IsiLangParser.CmdContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLangParser#cmdLeitura}.
	 * @param ctx the parse tree
	 */
	void enterCmdLeitura(IsiLangParser.CmdLeituraContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLangParser#cmdLeitura}.
	 * @param ctx the parse tree
	 */
	void exitCmdLeitura(IsiLangParser.CmdLeituraContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLangParser#cmdEscrita}.
	 * @param ctx the parse tree
	 */
	void enterCmdEscrita(IsiLangParser.CmdEscritaContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLangParser#cmdEscrita}.
	 * @param ctx the parse tree
	 */
	void exitCmdEscrita(IsiLangParser.CmdEscritaContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLangParser#cmdIf}.
	 * @param ctx the parse tree
	 */
	void enterCmdIf(IsiLangParser.CmdIfContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLangParser#cmdIf}.
	 * @param ctx the parse tree
	 */
	void exitCmdIf(IsiLangParser.CmdIfContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLangParser#cmdEscolha}.
	 * @param ctx the parse tree
	 */
	void enterCmdEscolha(IsiLangParser.CmdEscolhaContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLangParser#cmdEscolha}.
	 * @param ctx the parse tree
	 */
	void exitCmdEscolha(IsiLangParser.CmdEscolhaContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLangParser#caso}.
	 * @param ctx the parse tree
	 */
	void enterCaso(IsiLangParser.CasoContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLangParser#caso}.
	 * @param ctx the parse tree
	 */
	void exitCaso(IsiLangParser.CasoContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLangParser#cmdRepeticao}.
	 * @param ctx the parse tree
	 */
	void enterCmdRepeticao(IsiLangParser.CmdRepeticaoContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLangParser#cmdRepeticao}.
	 * @param ctx the parse tree
	 */
	void exitCmdRepeticao(IsiLangParser.CmdRepeticaoContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLangParser#cmdExpr}.
	 * @param ctx the parse tree
	 */
	void enterCmdExpr(IsiLangParser.CmdExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLangParser#cmdExpr}.
	 * @param ctx the parse tree
	 */
	void exitCmdExpr(IsiLangParser.CmdExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLangParser#op_rel}.
	 * @param ctx the parse tree
	 */
	void enterOp_rel(IsiLangParser.Op_relContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLangParser#op_rel}.
	 * @param ctx the parse tree
	 */
	void exitOp_rel(IsiLangParser.Op_relContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(IsiLangParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(IsiLangParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLangParser#termo}.
	 * @param ctx the parse tree
	 */
	void enterTermo(IsiLangParser.TermoContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLangParser#termo}.
	 * @param ctx the parse tree
	 */
	void exitTermo(IsiLangParser.TermoContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLangParser#tipo}.
	 * @param ctx the parse tree
	 */
	void enterTipo(IsiLangParser.TipoContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLangParser#tipo}.
	 * @param ctx the parse tree
	 */
	void exitTipo(IsiLangParser.TipoContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLangParser#op_mat}.
	 * @param ctx the parse tree
	 */
	void enterOp_mat(IsiLangParser.Op_matContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLangParser#op_mat}.
	 * @param ctx the parse tree
	 */
	void exitOp_mat(IsiLangParser.Op_matContext ctx);
}