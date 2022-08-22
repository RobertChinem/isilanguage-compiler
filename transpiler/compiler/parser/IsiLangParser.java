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

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class IsiLangParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		T__31=32, Id=33, Num=34, Decimal=35, Inteiro=36, Texto=37, Espaco=38;
	public static final int
		RULE_prog = 0, RULE_declara = 1, RULE_criacaoVariavel = 2, RULE_bloco = 3, 
		RULE_cmd = 4, RULE_cmdLeitura = 5, RULE_cmdEscrita = 6, RULE_cmdIf = 7, 
		RULE_cmdEscolha = 8, RULE_caso = 9, RULE_cmdRepeticao = 10, RULE_cmdExpr = 11, 
		RULE_op_rel = 12, RULE_expr = 13, RULE_termo = 14, RULE_tipo = 15, RULE_op_mat = 16;
	public static final String[] ruleNames = {
		"prog", "declara", "criacaoVariavel", "bloco", "cmd", "cmdLeitura", "cmdEscrita", 
		"cmdIf", "cmdEscolha", "caso", "cmdRepeticao", "cmdExpr", "op_rel", "expr", 
		"termo", "tipo", "op_mat"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'programa'", "'fimprog.'", "'declare'", "','", "'.'", "'leia'", 
		"'('", "')'", "'escreva'", "'se'", "'entao'", "'{'", "'}'", "'senao'", 
		"'escolha'", "'caso'", "'enquanto'", "':='", "'<'", "'<='", "'>'", "'>='", 
		"'=='", "'!='", "'Inteiro'", "'Decimal'", "'Texto'", "'+'", "'-'", "'*'", 
		"'/'", "'**'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, "Id", "Num", "Decimal", 
		"Inteiro", "Texto", "Espaco"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "IsiLang.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }


	    private int _variableType;
	    private String _variableName;
	    private String _variableValue;
	    private SymbolTable _symbolTable = new SymbolTable();
	    private HashSet<String> _usedVariables = new HashSet<String>();
	    private Program program = new Program();
	    private ArrayList<AbstractCommand> scopes;
	    private Stack<ArrayList<AbstractCommand>> scopeStack = new Stack<ArrayList<AbstractCommand>>();
	    private Stack<String> _exprDecisionStack = new Stack<String>();
	    private Stack<ArrayList<CommandSwitchCase>> _switchCaseStack = new Stack<ArrayList<CommandSwitchCase>>();
	    private String _readID;
		private String _writeID;
		private String _exprID;
		private String _exprContent;
	    private ArrayList<AbstractCommand> listTrue;
		private ArrayList<AbstractCommand> listFalse;

	    public void checkID(String id) {
	        if(!_symbolTable.contains(id)) {
	            throw new SemanticException("Undeclared variable: " + id);
	        }
	    }

	    public void generateCode(String pathOutput){
	        program.generateTarget(pathOutput);
	    }

	    public boolean checkTypes(String targetID, String ID){
	        boolean targetIsNum = hasType(targetID, Variable.INT) || hasType(targetID, Variable.DOUBLE);
	        boolean id = hasType(ID, Variable.INT) || hasType(ID, Variable.DOUBLE);

	        if(targetIsNum && id){
	            return true;
	        }

	        if(hasType(targetID, Variable.TEXT) && hasType(ID, Variable.TEXT)){
	            return true;
	        }

	        return false;
	    }

	    public boolean isOperator(char ch){
	        return ch == '+' || ch == '-' || ch == '*' || ch == '/';
	    }

	    public String getLastOperator(String expr){
	        int i = expr.length() - 1;
	        String lastOperator = "";   
	        while(i >= 0 && isOperator(expr.charAt(i))){
	            lastOperator = expr.charAt(i) + lastOperator;
	            i--;
	        }
	        return lastOperator;
	    }

	    public String replacePower(String exprContent, String currentId){
	        int i = exprContent.length() - 3;
	        String aux = "";
	        while(i >= 0 && !isOperator(exprContent.charAt(i))){
	            aux = exprContent.charAt(i) + aux;
	            i--;
	        }
	        return exprContent.replace(aux+"**", "") + String.format("Math.pow((double)%s, (double)%s)", aux, currentId);
	    }

	    public boolean hasType(String ID, int type){
	        return ((Variable)_symbolTable.get(ID)).getType() == type;
	    }

	public IsiLangParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ProgContext extends ParserRuleContext {
		public DeclaraContext declara() {
			return getRuleContext(DeclaraContext.class,0);
		}
		public BlocoContext bloco() {
			return getRuleContext(BlocoContext.class,0);
		}
		public ProgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prog; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterProg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitProg(this);
		}
	}

	public final ProgContext prog() throws RecognitionException {
		ProgContext _localctx = new ProgContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_prog);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(34);
			match(T__0);
			setState(35);
			declara();
			setState(36);
			bloco();
			setState(37);
			match(T__1);

			                program.setSymbolTable(_symbolTable);
			                program.setCommands(scopeStack.pop());
			                program.setUsedVariables(_usedVariables);
			            
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeclaraContext extends ParserRuleContext {
		public List<CriacaoVariavelContext> criacaoVariavel() {
			return getRuleContexts(CriacaoVariavelContext.class);
		}
		public CriacaoVariavelContext criacaoVariavel(int i) {
			return getRuleContext(CriacaoVariavelContext.class,i);
		}
		public DeclaraContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declara; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterDeclara(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitDeclara(this);
		}
	}

	public final DeclaraContext declara() throws RecognitionException {
		DeclaraContext _localctx = new DeclaraContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_declara);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(40);
			match(T__2);
			setState(41);
			criacaoVariavel();
			setState(46);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__3) {
				{
				{
				setState(42);
				match(T__3);
				setState(43);
				criacaoVariavel();
				}
				}
				setState(48);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(49);
			match(T__4);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CriacaoVariavelContext extends ParserRuleContext {
		public TipoContext tipo() {
			return getRuleContext(TipoContext.class,0);
		}
		public TerminalNode Id() { return getToken(IsiLangParser.Id, 0); }
		public CriacaoVariavelContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_criacaoVariavel; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterCriacaoVariavel(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitCriacaoVariavel(this);
		}
	}

	public final CriacaoVariavelContext criacaoVariavel() throws RecognitionException {
		CriacaoVariavelContext _localctx = new CriacaoVariavelContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_criacaoVariavel);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(51);
			tipo();
			setState(52);
			match(Id);

			                        _variableName = _input.LT(-1).getText();
			                        _variableValue = null;
			                        if(_symbolTable.contains(_variableName)){
			                            throw new SemanticException("Variable " + _variableName + " already declared");
			                        }
			                        _symbolTable.add(new Variable(_variableName, _variableType, _variableValue));
			                    
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BlocoContext extends ParserRuleContext {
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public BlocoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bloco; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterBloco(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitBloco(this);
		}
	}

	public final BlocoContext bloco() throws RecognitionException {
		BlocoContext _localctx = new BlocoContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_bloco);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{

			                scopeStack.push(new ArrayList<AbstractCommand>());
			            
			setState(57); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(56);
				cmd();
				}
				}
				setState(59); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__8) | (1L << T__9) | (1L << T__14) | (1L << T__16) | (1L << Id))) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmdContext extends ParserRuleContext {
		public CmdLeituraContext cmdLeitura() {
			return getRuleContext(CmdLeituraContext.class,0);
		}
		public CmdEscritaContext cmdEscrita() {
			return getRuleContext(CmdEscritaContext.class,0);
		}
		public CmdExprContext cmdExpr() {
			return getRuleContext(CmdExprContext.class,0);
		}
		public CmdIfContext cmdIf() {
			return getRuleContext(CmdIfContext.class,0);
		}
		public CmdRepeticaoContext cmdRepeticao() {
			return getRuleContext(CmdRepeticaoContext.class,0);
		}
		public CmdEscolhaContext cmdEscolha() {
			return getRuleContext(CmdEscolhaContext.class,0);
		}
		public CmdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmd; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterCmd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitCmd(this);
		}
	}

	public final CmdContext cmd() throws RecognitionException {
		CmdContext _localctx = new CmdContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_cmd);
		try {
			setState(67);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__5:
				enterOuterAlt(_localctx, 1);
				{
				setState(61);
				cmdLeitura();
				}
				break;
			case T__8:
				enterOuterAlt(_localctx, 2);
				{
				setState(62);
				cmdEscrita();
				}
				break;
			case Id:
				enterOuterAlt(_localctx, 3);
				{
				setState(63);
				cmdExpr();
				}
				break;
			case T__9:
				enterOuterAlt(_localctx, 4);
				{
				setState(64);
				cmdIf();
				}
				break;
			case T__16:
				enterOuterAlt(_localctx, 5);
				{
				setState(65);
				cmdRepeticao();
				}
				break;
			case T__14:
				enterOuterAlt(_localctx, 6);
				{
				setState(66);
				cmdEscolha();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmdLeituraContext extends ParserRuleContext {
		public TerminalNode Id() { return getToken(IsiLangParser.Id, 0); }
		public CmdLeituraContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdLeitura; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterCmdLeitura(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitCmdLeitura(this);
		}
	}

	public final CmdLeituraContext cmdLeitura() throws RecognitionException {
		CmdLeituraContext _localctx = new CmdLeituraContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_cmdLeitura);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(69);
			match(T__5);
			setState(70);
			match(T__6);
			setState(71);
			match(Id);
			   _readID = _input.LT(-1).getText();  
			setState(73);
			match(T__7);
			setState(74);
			match(T__4);

			                    checkID(_readID);
			                    _usedVariables.add(_readID);
			                    CommandRead cmd = new CommandRead(_readID, (Variable)_symbolTable.get(_readID));
			                    scopeStack.peek().add(cmd);
			                
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmdEscritaContext extends ParserRuleContext {
		public TerminalNode Texto() { return getToken(IsiLangParser.Texto, 0); }
		public TerminalNode Id() { return getToken(IsiLangParser.Id, 0); }
		public CmdEscritaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdEscrita; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterCmdEscrita(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitCmdEscrita(this);
		}
	}

	public final CmdEscritaContext cmdEscrita() throws RecognitionException {
		CmdEscritaContext _localctx = new CmdEscritaContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_cmdEscrita);
		try {
			setState(91);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(77);
				match(T__8);
				setState(78);
				match(T__6);
				setState(79);
				match(Texto);
				   _exprContent = _input.LT(-1).getText(); 
				setState(81);
				match(T__7);
				setState(82);
				match(T__4);

				                    scopeStack.peek().add(new CommandWrite(_exprContent));
				                
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(84);
				match(T__8);
				setState(85);
				match(T__6);
				setState(86);
				match(Id);
				   _readID = _input.LT(-1).getText();  
				setState(88);
				match(T__7);
				setState(89);
				match(T__4);

				                    checkID(_readID);
				                    scopeStack.peek().add(new CommandWrite(_readID));
				                
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmdIfContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public CmdIfContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdIf; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterCmdIf(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitCmdIf(this);
		}
	}

	public final CmdIfContext cmdIf() throws RecognitionException {
		CmdIfContext _localctx = new CmdIfContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_cmdIf);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(93);
			match(T__9);

			                _exprID = null;
			                _exprContent = "";
			            
			setState(95);
			match(T__6);
			setState(96);
			expr();
			setState(97);
			match(T__7);
			setState(98);
			match(T__10);

			                _exprDecisionStack.push(_exprContent);
			                scopeStack.push(new ArrayList<AbstractCommand>()); 
			                listTrue = null;
			                listFalse = null;
			            
			setState(100);
			match(T__11);
			setState(102); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(101);
				cmd();
				}
				}
				setState(104); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__8) | (1L << T__9) | (1L << T__14) | (1L << T__16) | (1L << Id))) != 0) );
			setState(106);
			match(T__12);
			setState(118);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__13) {
				{
				setState(107);
				match(T__13);
				   scopeStack.push(new ArrayList<AbstractCommand>());  
				setState(109);
				match(T__11);
				setState(111); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(110);
					cmd();
					}
					}
					setState(113); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__8) | (1L << T__9) | (1L << T__14) | (1L << T__16) | (1L << Id))) != 0) );
				setState(115);
				match(T__12);

				                    listFalse = scopeStack.pop();
				                
				}
			}

			   
			                _exprContent = _exprDecisionStack.pop();
			                listTrue = scopeStack.pop();
			                CommandDecision cmd = new CommandDecision(_exprContent, listTrue, listFalse);
			                scopeStack.peek().add(cmd);
			                listTrue = null;
			                listFalse = null;
			            
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmdEscolhaContext extends ParserRuleContext {
		public TerminalNode Id() { return getToken(IsiLangParser.Id, 0); }
		public List<CasoContext> caso() {
			return getRuleContexts(CasoContext.class);
		}
		public CasoContext caso(int i) {
			return getRuleContext(CasoContext.class,i);
		}
		public CmdEscolhaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdEscolha; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterCmdEscolha(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitCmdEscolha(this);
		}
	}

	public final CmdEscolhaContext cmdEscolha() throws RecognitionException {
		CmdEscolhaContext _localctx = new CmdEscolhaContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_cmdEscolha);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(122);
			match(T__14);

			                    _exprID = null;
			                    _exprContent = "";
			                
			setState(124);
			match(T__6);
			setState(125);
			match(Id);

			                    _exprID = _input.LT(-1).getText();
			                    checkID(_exprID);

			                    if(!hasType(_exprID, Variable.INT) && !hasType(_exprID, Variable.TEXT)){
			                        throw new SemanticException("Variable " + _exprID + " must be of type int or text");
			                    }
			                
			setState(127);
			match(T__7);
			setState(128);
			match(T__11);

			                    _exprDecisionStack.push(_exprID);
			                    _switchCaseStack.push(new ArrayList<CommandSwitchCase>());
			                    listTrue = null;
			                    listFalse = null;
			                
			setState(131); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(130);
				caso();
				}
				}
				setState(133); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__15 );
			setState(135);
			match(T__12);

			                    _exprContent = _exprDecisionStack.pop();
			                    ArrayList<CommandSwitchCase> cases = _switchCaseStack.pop();
			                    CommandSwitch cmd = new CommandSwitch(_exprContent, cases);
			                    scopeStack.peek().add(cmd);
			                
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CasoContext extends ParserRuleContext {
		public TerminalNode Texto() { return getToken(IsiLangParser.Texto, 0); }
		public TerminalNode Num() { return getToken(IsiLangParser.Num, 0); }
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public CasoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_caso; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterCaso(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitCaso(this);
		}
	}

	public final CasoContext caso() throws RecognitionException {
		CasoContext _localctx = new CasoContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_caso);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(138);
			match(T__15);
			setState(139);
			match(T__6);
			setState(144);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Texto:
				{
				setState(140);
				match(Texto);

				                    String switchVariable = _exprDecisionStack.peek();
				                    _exprContent = _input.LT(-1).getText();

				                    if(hasType(switchVariable, Variable.INT)){
				                        throw new SemanticException("Expected a number, found a text: " + _exprContent);
				                    }
				                
				}
				break;
			case Num:
				{
				setState(142);
				match(Num);

				                    String switchVariable = _exprDecisionStack.peek();
				                    _exprContent = _input.LT(-1).getText();

				                    if(hasType(switchVariable, Variable.TEXT)){
				                        throw new SemanticException("Expected a text, found a number: " + _exprContent);
				                    }
				                
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(146);
			match(T__7);
			setState(147);
			match(T__11);

			                _exprDecisionStack.push(_exprContent);
			                scopeStack.push(new ArrayList<AbstractCommand>()); 
			                listTrue = null;
			            
			setState(150); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(149);
				cmd();
				}
				}
				setState(152); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__8) | (1L << T__9) | (1L << T__14) | (1L << T__16) | (1L << Id))) != 0) );
			setState(154);
			match(T__12);

			                _exprContent = _exprDecisionStack.pop();
			                listTrue = scopeStack.pop();
			                CommandSwitchCase cmd = new CommandSwitchCase(_exprContent, listTrue);
			                _switchCaseStack.peek().add(cmd);
			            
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmdRepeticaoContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public CmdRepeticaoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdRepeticao; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterCmdRepeticao(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitCmdRepeticao(this);
		}
	}

	public final CmdRepeticaoContext cmdRepeticao() throws RecognitionException {
		CmdRepeticaoContext _localctx = new CmdRepeticaoContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_cmdRepeticao);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(157);
			match(T__16);

			                        _exprID = null;
			                        _exprContent = "";
			                    
			setState(159);
			match(T__6);
			setState(160);
			expr();
			setState(161);
			match(T__7);

			                        _exprDecisionStack.push(_exprContent);
			                        scopeStack.push(new ArrayList<AbstractCommand>()); 
			                        listTrue = null;
			                    
			setState(163);
			match(T__11);
			setState(165); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(164);
				cmd();
				}
				}
				setState(167); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__8) | (1L << T__9) | (1L << T__14) | (1L << T__16) | (1L << Id))) != 0) );
			setState(169);
			match(T__12);

			                        _exprContent = _exprDecisionStack.pop();
			                        listTrue = scopeStack.pop();
			                        CommandLoop cmd = new CommandLoop(_exprContent, listTrue);
			                        scopeStack.peek().add(cmd);
			                        listTrue = null;
			                    
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmdExprContext extends ParserRuleContext {
		public TerminalNode Id() { return getToken(IsiLangParser.Id, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode Texto() { return getToken(IsiLangParser.Texto, 0); }
		public CmdExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterCmdExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitCmdExpr(this);
		}
	}

	public final CmdExprContext cmdExpr() throws RecognitionException {
		CmdExprContext _localctx = new CmdExprContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_cmdExpr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(172);
			match(Id);

			                _exprID = _input.LT(-1).getText();
			                checkID(_exprID);
			            
			setState(174);
			match(T__17);
			  _exprContent = "";  
			setState(181);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Id:
			case Num:
				{
				setState(176);
				expr();

				                        if(hasType(_exprID, Variable.INT)){
				                            _exprContent = String.format("(int)(%s)", _exprContent);
				                        }
				                        
				                        if(hasType(_exprID, Variable.DOUBLE)){
				                            _exprContent = String.format("(double)(%s)", _exprContent);
				                        }

				                        CommandAssignment cmd = new CommandAssignment(_exprID, _exprContent);
				                        scopeStack.peek().add(cmd);
				                    
				}
				break;
			case Texto:
				{
				setState(179);
				match(Texto);

				                        _exprContent += _input.LT(-1).getText();
				                        CommandAssignment cmd = new CommandAssignment(_exprID, _exprContent);
				                        scopeStack.peek().add(cmd);
				                    
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			   _usedVariables.add(_exprID);    
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Op_relContext extends ParserRuleContext {
		public Op_relContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_op_rel; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterOp_rel(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitOp_rel(this);
		}
	}

	public final Op_relContext op_rel() throws RecognitionException {
		Op_relContext _localctx = new Op_relContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_op_rel);
		try {
			setState(197);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__18:
				enterOuterAlt(_localctx, 1);
				{
				setState(185);
				match(T__18);
				  _exprContent += _input.LT(-1).getText(); 
				}
				break;
			case T__19:
				enterOuterAlt(_localctx, 2);
				{
				setState(187);
				match(T__19);
				  _exprContent += _input.LT(-1).getText(); 
				}
				break;
			case T__20:
				enterOuterAlt(_localctx, 3);
				{
				setState(189);
				match(T__20);
				  _exprContent += _input.LT(-1).getText(); 
				}
				break;
			case T__21:
				enterOuterAlt(_localctx, 4);
				{
				setState(191);
				match(T__21);
				  _exprContent += _input.LT(-1).getText(); 
				}
				break;
			case T__22:
				enterOuterAlt(_localctx, 5);
				{
				setState(193);
				match(T__22);
				  _exprContent += _input.LT(-1).getText(); 
				}
				break;
			case T__23:
				enterOuterAlt(_localctx, 6);
				{
				setState(195);
				match(T__23);
				  _exprContent += _input.LT(-1).getText(); 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprContext extends ParserRuleContext {
		public List<TermoContext> termo() {
			return getRuleContexts(TermoContext.class);
		}
		public TermoContext termo(int i) {
			return getRuleContext(TermoContext.class,i);
		}
		public List<Op_matContext> op_mat() {
			return getRuleContexts(Op_matContext.class);
		}
		public Op_matContext op_mat(int i) {
			return getRuleContext(Op_matContext.class,i);
		}
		public List<Op_relContext> op_rel() {
			return getRuleContexts(Op_relContext.class);
		}
		public Op_relContext op_rel(int i) {
			return getRuleContext(Op_relContext.class,i);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitExpr(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_expr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(199);
			termo();
			setState(208);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__18) | (1L << T__19) | (1L << T__20) | (1L << T__21) | (1L << T__22) | (1L << T__23) | (1L << T__27) | (1L << T__28) | (1L << T__29) | (1L << T__30) | (1L << T__31))) != 0)) {
				{
				{
				setState(202);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__27:
				case T__28:
				case T__29:
				case T__30:
				case T__31:
					{
					setState(200);
					op_mat();
					}
					break;
				case T__18:
				case T__19:
				case T__20:
				case T__21:
				case T__22:
				case T__23:
					{
					setState(201);
					op_rel();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(204);
				termo();
				}
				}
				setState(210);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TermoContext extends ParserRuleContext {
		public TerminalNode Id() { return getToken(IsiLangParser.Id, 0); }
		public TerminalNode Num() { return getToken(IsiLangParser.Num, 0); }
		public TermoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_termo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterTermo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitTermo(this);
		}
	}

	public final TermoContext termo() throws RecognitionException {
		TermoContext _localctx = new TermoContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_termo);
		try {
			setState(215);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Id:
				enterOuterAlt(_localctx, 1);
				{
				setState(211);
				match(Id);

				                checkID(_input.LT(-1).getText());
				                if(_exprID == null || checkTypes(_exprID, _input.LT(-1).getText())){
				                    if(getLastOperator(_exprContent).equals("**")){
				                        _exprContent = replacePower(_exprContent, _input.LT(-1).getText());
				                    }
				                    else _exprContent += _input.LT(-1).getText();
				                }
				                else {
				                    throw new SemanticException("Type mismatch: " + _exprID + " and " + _input.LT(-1).getText());
				                }
				            
				}
				break;
			case Num:
				enterOuterAlt(_localctx, 2);
				{
				setState(213);
				match(Num);

				                if(_exprID == null || hasType(_exprID, Variable.INT) || hasType(_exprID, Variable.DOUBLE)){
				                    if(getLastOperator(_exprContent).equals("**")){
				                        _exprContent = replacePower(_exprContent, _input.LT(-1).getText());
				                    }
				                    else _exprContent += _input.LT(-1).getText();
				                }
				                else {
				                    throw new SemanticException("Type mismatch: " + _exprID + " and " + _input.LT(-1).getText());
				                }
				            
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TipoContext extends ParserRuleContext {
		public TipoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tipo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterTipo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitTipo(this);
		}
	}

	public final TipoContext tipo() throws RecognitionException {
		TipoContext _localctx = new TipoContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_tipo);
		try {
			setState(223);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__24:
				enterOuterAlt(_localctx, 1);
				{
				setState(217);
				match(T__24);
				   _variableType = Variable.INT;     
				}
				break;
			case T__25:
				enterOuterAlt(_localctx, 2);
				{
				setState(219);
				match(T__25);
				   _variableType = Variable.DOUBLE;  
				}
				break;
			case T__26:
				enterOuterAlt(_localctx, 3);
				{
				setState(221);
				match(T__26);
				   _variableType = Variable.TEXT;    
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Op_matContext extends ParserRuleContext {
		public Op_matContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_op_mat; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterOp_mat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitOp_mat(this);
		}
	}

	public final Op_matContext op_mat() throws RecognitionException {
		Op_matContext _localctx = new Op_matContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_op_mat);
		try {
			setState(235);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__27:
				enterOuterAlt(_localctx, 1);
				{
				setState(225);
				match(T__27);
				  _exprContent += _input.LT(-1).getText(); 
				}
				break;
			case T__28:
				enterOuterAlt(_localctx, 2);
				{
				setState(227);
				match(T__28);
				  _exprContent += _input.LT(-1).getText(); 
				}
				break;
			case T__29:
				enterOuterAlt(_localctx, 3);
				{
				setState(229);
				match(T__29);
				  _exprContent += _input.LT(-1).getText(); 
				}
				break;
			case T__30:
				enterOuterAlt(_localctx, 4);
				{
				setState(231);
				match(T__30);
				  _exprContent += _input.LT(-1).getText(); 
				}
				break;
			case T__31:
				enterOuterAlt(_localctx, 5);
				{
				setState(233);
				match(T__31);
				  _exprContent += _input.LT(-1).getText(); 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3(\u00f0\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\7\3/\n\3\f\3\16\3\62\13\3\3\3"+
		"\3\3\3\4\3\4\3\4\3\4\3\5\3\5\6\5<\n\5\r\5\16\5=\3\6\3\6\3\6\3\6\3\6\3"+
		"\6\5\6F\n\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3"+
		"\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\5\b^\n\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3"+
		"\t\3\t\6\ti\n\t\r\t\16\tj\3\t\3\t\3\t\3\t\3\t\6\tr\n\t\r\t\16\ts\3\t\3"+
		"\t\3\t\5\ty\n\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\6\n\u0086"+
		"\n\n\r\n\16\n\u0087\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\5\13\u0093"+
		"\n\13\3\13\3\13\3\13\3\13\6\13\u0099\n\13\r\13\16\13\u009a\3\13\3\13\3"+
		"\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\6\f\u00a8\n\f\r\f\16\f\u00a9\3\f\3"+
		"\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\5\r\u00b8\n\r\3\r\3\r\3\16"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\5\16\u00c8\n\16"+
		"\3\17\3\17\3\17\5\17\u00cd\n\17\3\17\3\17\7\17\u00d1\n\17\f\17\16\17\u00d4"+
		"\13\17\3\20\3\20\3\20\3\20\5\20\u00da\n\20\3\21\3\21\3\21\3\21\3\21\3"+
		"\21\5\21\u00e2\n\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22"+
		"\5\22\u00ee\n\22\3\22\2\2\23\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \""+
		"\2\2\2\u00fc\2$\3\2\2\2\4*\3\2\2\2\6\65\3\2\2\2\b9\3\2\2\2\nE\3\2\2\2"+
		"\fG\3\2\2\2\16]\3\2\2\2\20_\3\2\2\2\22|\3\2\2\2\24\u008c\3\2\2\2\26\u009f"+
		"\3\2\2\2\30\u00ae\3\2\2\2\32\u00c7\3\2\2\2\34\u00c9\3\2\2\2\36\u00d9\3"+
		"\2\2\2 \u00e1\3\2\2\2\"\u00ed\3\2\2\2$%\7\3\2\2%&\5\4\3\2&\'\5\b\5\2\'"+
		"(\7\4\2\2()\b\2\1\2)\3\3\2\2\2*+\7\5\2\2+\60\5\6\4\2,-\7\6\2\2-/\5\6\4"+
		"\2.,\3\2\2\2/\62\3\2\2\2\60.\3\2\2\2\60\61\3\2\2\2\61\63\3\2\2\2\62\60"+
		"\3\2\2\2\63\64\7\7\2\2\64\5\3\2\2\2\65\66\5 \21\2\66\67\7#\2\2\678\b\4"+
		"\1\28\7\3\2\2\29;\b\5\1\2:<\5\n\6\2;:\3\2\2\2<=\3\2\2\2=;\3\2\2\2=>\3"+
		"\2\2\2>\t\3\2\2\2?F\5\f\7\2@F\5\16\b\2AF\5\30\r\2BF\5\20\t\2CF\5\26\f"+
		"\2DF\5\22\n\2E?\3\2\2\2E@\3\2\2\2EA\3\2\2\2EB\3\2\2\2EC\3\2\2\2ED\3\2"+
		"\2\2F\13\3\2\2\2GH\7\b\2\2HI\7\t\2\2IJ\7#\2\2JK\b\7\1\2KL\7\n\2\2LM\7"+
		"\7\2\2MN\b\7\1\2N\r\3\2\2\2OP\7\13\2\2PQ\7\t\2\2QR\7\'\2\2RS\b\b\1\2S"+
		"T\7\n\2\2TU\7\7\2\2U^\b\b\1\2VW\7\13\2\2WX\7\t\2\2XY\7#\2\2YZ\b\b\1\2"+
		"Z[\7\n\2\2[\\\7\7\2\2\\^\b\b\1\2]O\3\2\2\2]V\3\2\2\2^\17\3\2\2\2_`\7\f"+
		"\2\2`a\b\t\1\2ab\7\t\2\2bc\5\34\17\2cd\7\n\2\2de\7\r\2\2ef\b\t\1\2fh\7"+
		"\16\2\2gi\5\n\6\2hg\3\2\2\2ij\3\2\2\2jh\3\2\2\2jk\3\2\2\2kl\3\2\2\2lx"+
		"\7\17\2\2mn\7\20\2\2no\b\t\1\2oq\7\16\2\2pr\5\n\6\2qp\3\2\2\2rs\3\2\2"+
		"\2sq\3\2\2\2st\3\2\2\2tu\3\2\2\2uv\7\17\2\2vw\b\t\1\2wy\3\2\2\2xm\3\2"+
		"\2\2xy\3\2\2\2yz\3\2\2\2z{\b\t\1\2{\21\3\2\2\2|}\7\21\2\2}~\b\n\1\2~\177"+
		"\7\t\2\2\177\u0080\7#\2\2\u0080\u0081\b\n\1\2\u0081\u0082\7\n\2\2\u0082"+
		"\u0083\7\16\2\2\u0083\u0085\b\n\1\2\u0084\u0086\5\24\13\2\u0085\u0084"+
		"\3\2\2\2\u0086\u0087\3\2\2\2\u0087\u0085\3\2\2\2\u0087\u0088\3\2\2\2\u0088"+
		"\u0089\3\2\2\2\u0089\u008a\7\17\2\2\u008a\u008b\b\n\1\2\u008b\23\3\2\2"+
		"\2\u008c\u008d\7\22\2\2\u008d\u0092\7\t\2\2\u008e\u008f\7\'\2\2\u008f"+
		"\u0093\b\13\1\2\u0090\u0091\7$\2\2\u0091\u0093\b\13\1\2\u0092\u008e\3"+
		"\2\2\2\u0092\u0090\3\2\2\2\u0093\u0094\3\2\2\2\u0094\u0095\7\n\2\2\u0095"+
		"\u0096\7\16\2\2\u0096\u0098\b\13\1\2\u0097\u0099\5\n\6\2\u0098\u0097\3"+
		"\2\2\2\u0099\u009a\3\2\2\2\u009a\u0098\3\2\2\2\u009a\u009b\3\2\2\2\u009b"+
		"\u009c\3\2\2\2\u009c\u009d\7\17\2\2\u009d\u009e\b\13\1\2\u009e\25\3\2"+
		"\2\2\u009f\u00a0\7\23\2\2\u00a0\u00a1\b\f\1\2\u00a1\u00a2\7\t\2\2\u00a2"+
		"\u00a3\5\34\17\2\u00a3\u00a4\7\n\2\2\u00a4\u00a5\b\f\1\2\u00a5\u00a7\7"+
		"\16\2\2\u00a6\u00a8\5\n\6\2\u00a7\u00a6\3\2\2\2\u00a8\u00a9\3\2\2\2\u00a9"+
		"\u00a7\3\2\2\2\u00a9\u00aa\3\2\2\2\u00aa\u00ab\3\2\2\2\u00ab\u00ac\7\17"+
		"\2\2\u00ac\u00ad\b\f\1\2\u00ad\27\3\2\2\2\u00ae\u00af\7#\2\2\u00af\u00b0"+
		"\b\r\1\2\u00b0\u00b1\7\24\2\2\u00b1\u00b7\b\r\1\2\u00b2\u00b3\5\34\17"+
		"\2\u00b3\u00b4\b\r\1\2\u00b4\u00b8\3\2\2\2\u00b5\u00b6\7\'\2\2\u00b6\u00b8"+
		"\b\r\1\2\u00b7\u00b2\3\2\2\2\u00b7\u00b5\3\2\2\2\u00b8\u00b9\3\2\2\2\u00b9"+
		"\u00ba\b\r\1\2\u00ba\31\3\2\2\2\u00bb\u00bc\7\25\2\2\u00bc\u00c8\b\16"+
		"\1\2\u00bd\u00be\7\26\2\2\u00be\u00c8\b\16\1\2\u00bf\u00c0\7\27\2\2\u00c0"+
		"\u00c8\b\16\1\2\u00c1\u00c2\7\30\2\2\u00c2\u00c8\b\16\1\2\u00c3\u00c4"+
		"\7\31\2\2\u00c4\u00c8\b\16\1\2\u00c5\u00c6\7\32\2\2\u00c6\u00c8\b\16\1"+
		"\2\u00c7\u00bb\3\2\2\2\u00c7\u00bd\3\2\2\2\u00c7\u00bf\3\2\2\2\u00c7\u00c1"+
		"\3\2\2\2\u00c7\u00c3\3\2\2\2\u00c7\u00c5\3\2\2\2\u00c8\33\3\2\2\2\u00c9"+
		"\u00d2\5\36\20\2\u00ca\u00cd\5\"\22\2\u00cb\u00cd\5\32\16\2\u00cc\u00ca"+
		"\3\2\2\2\u00cc\u00cb\3\2\2\2\u00cd\u00ce\3\2\2\2\u00ce\u00cf\5\36\20\2"+
		"\u00cf\u00d1\3\2\2\2\u00d0\u00cc\3\2\2\2\u00d1\u00d4\3\2\2\2\u00d2\u00d0"+
		"\3\2\2\2\u00d2\u00d3\3\2\2\2\u00d3\35\3\2\2\2\u00d4\u00d2\3\2\2\2\u00d5"+
		"\u00d6\7#\2\2\u00d6\u00da\b\20\1\2\u00d7\u00d8\7$\2\2\u00d8\u00da\b\20"+
		"\1\2\u00d9\u00d5\3\2\2\2\u00d9\u00d7\3\2\2\2\u00da\37\3\2\2\2\u00db\u00dc"+
		"\7\33\2\2\u00dc\u00e2\b\21\1\2\u00dd\u00de\7\34\2\2\u00de\u00e2\b\21\1"+
		"\2\u00df\u00e0\7\35\2\2\u00e0\u00e2\b\21\1\2\u00e1\u00db\3\2\2\2\u00e1"+
		"\u00dd\3\2\2\2\u00e1\u00df\3\2\2\2\u00e2!\3\2\2\2\u00e3\u00e4\7\36\2\2"+
		"\u00e4\u00ee\b\22\1\2\u00e5\u00e6\7\37\2\2\u00e6\u00ee\b\22\1\2\u00e7"+
		"\u00e8\7 \2\2\u00e8\u00ee\b\22\1\2\u00e9\u00ea\7!\2\2\u00ea\u00ee\b\22"+
		"\1\2\u00eb\u00ec\7\"\2\2\u00ec\u00ee\b\22\1\2\u00ed\u00e3\3\2\2\2\u00ed"+
		"\u00e5\3\2\2\2\u00ed\u00e7\3\2\2\2\u00ed\u00e9\3\2\2\2\u00ed\u00eb\3\2"+
		"\2\2\u00ee#\3\2\2\2\24\60=E]jsx\u0087\u0092\u009a\u00a9\u00b7\u00c7\u00cc"+
		"\u00d2\u00d9\u00e1\u00ed";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}