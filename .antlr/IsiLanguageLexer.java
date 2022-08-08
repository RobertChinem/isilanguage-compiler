// Generated from /Users/robertchinem/github/isilanguage-compiler/isiLang.g4 by ANTLR 4.9.2



import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class IsiLanguageLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		Prog=1, Declara=2, CriacaoVariavel=3, Bloco=4, Cmd=5, CmdLeitura=6, CmdEscrita=7, 
		CmdIf=8, CmdExpr=9, Op_rel=10, Expr=11, Termo=12, Id=13, Tipo=14, Num=15, 
		Texto=16, Operador=17, Espaco=18;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"Prog", "Declara", "CriacaoVariavel", "Bloco", "Cmd", "CmdLeitura", "CmdEscrita", 
			"CmdIf", "CmdExpr", "Op_rel", "Expr", "Termo", "Id", "Tipo", "Num", "Texto", 
			"Operador", "Espaco"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "Prog", "Declara", "CriacaoVariavel", "Bloco", "Cmd", "CmdLeitura", 
			"CmdEscrita", "CmdIf", "CmdExpr", "Op_rel", "Expr", "Termo", "Id", "Tipo", 
			"Num", "Texto", "Operador", "Espaco"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
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





	public IsiLanguageLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "isiLang.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch (ruleIndex) {
		case 0:
			Prog_action((RuleContext)_localctx, actionIndex);
			break;
		}
	}
	private void Prog_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0:


			            
			break;
		}
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\24\u00d7\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3"+
		"\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\7\3I\n\3\f\3\16\3L\13\3\3\3\3\3\3\4\3\4\3\4\3\5\3\5\3\5\6\5V"+
		"\n\5\r\5\16\5W\3\6\3\6\3\6\3\6\5\6^\n\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3"+
		"\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\5\bu\n\b\3\b\3"+
		"\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t"+
		"\6\t\u008a\n\t\r\t\16\t\u008b\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\5\13\u00a0\n\13\3\f\3\f\3\f\3"+
		"\f\7\f\u00a6\n\f\f\f\16\f\u00a9\13\f\3\r\3\r\3\r\5\r\u00ae\n\r\3\16\5"+
		"\16\u00b1\n\16\3\16\7\16\u00b4\n\16\f\16\16\16\u00b7\13\16\3\17\3\17\5"+
		"\17\u00bb\n\17\3\20\6\20\u00be\n\20\r\20\16\20\u00bf\3\20\3\20\6\20\u00c4"+
		"\n\20\r\20\16\20\u00c5\5\20\u00c8\n\20\3\21\3\21\6\21\u00cc\n\21\r\21"+
		"\16\21\u00cd\3\21\3\21\3\22\3\22\3\23\3\23\3\23\3\23\2\2\24\3\3\5\4\7"+
		"\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22"+
		"#\23%\24\3\2\b\4\2C\\c|\5\2\62;C\\c|\3\2\62;\6\2\"\"\62;C\\c|\5\2,-//"+
		"\61\61\5\2\13\f\17\17\"\"\2\u00eb\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2"+
		"\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3"+
		"\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2"+
		"\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\3\'\3\2\2\2\5=\3\2\2"+
		"\2\7O\3\2\2\2\tU\3\2\2\2\13]\3\2\2\2\r_\3\2\2\2\17i\3\2\2\2\21y\3\2\2"+
		"\2\23\u008f\3\2\2\2\25\u009f\3\2\2\2\27\u00a1\3\2\2\2\31\u00ad\3\2\2\2"+
		"\33\u00b0\3\2\2\2\35\u00ba\3\2\2\2\37\u00bd\3\2\2\2!\u00c9\3\2\2\2#\u00d1"+
		"\3\2\2\2%\u00d3\3\2\2\2\'(\7r\2\2()\7t\2\2)*\7q\2\2*+\7i\2\2+,\7t\2\2"+
		",-\7c\2\2-.\7o\2\2./\7c\2\2/\60\3\2\2\2\60\61\5\5\3\2\61\62\5\t\5\2\62"+
		"\63\7h\2\2\63\64\7k\2\2\64\65\7o\2\2\65\66\7r\2\2\66\67\7t\2\2\678\7q"+
		"\2\289\7i\2\29:\7\60\2\2:;\3\2\2\2;<\b\2\2\2<\4\3\2\2\2=>\7f\2\2>?\7g"+
		"\2\2?@\7e\2\2@A\7n\2\2AB\7c\2\2BC\7t\2\2CD\7g\2\2DE\3\2\2\2EJ\5\7\4\2"+
		"FG\7.\2\2GI\5\7\4\2HF\3\2\2\2IL\3\2\2\2JH\3\2\2\2JK\3\2\2\2KM\3\2\2\2"+
		"LJ\3\2\2\2MN\7\60\2\2N\6\3\2\2\2OP\5\35\17\2PQ\5\33\16\2Q\b\3\2\2\2RS"+
		"\5\13\6\2ST\7\60\2\2TV\3\2\2\2UR\3\2\2\2VW\3\2\2\2WU\3\2\2\2WX\3\2\2\2"+
		"X\n\3\2\2\2Y^\5\r\7\2Z^\5\17\b\2[^\5\23\n\2\\^\5\21\t\2]Y\3\2\2\2]Z\3"+
		"\2\2\2][\3\2\2\2]\\\3\2\2\2^\f\3\2\2\2_`\7n\2\2`a\7g\2\2ab\7k\2\2bc\7"+
		"c\2\2cd\3\2\2\2de\7*\2\2ef\5\33\16\2fg\7+\2\2gh\7\60\2\2h\16\3\2\2\2i"+
		"j\7g\2\2jk\7u\2\2kl\7e\2\2lm\7t\2\2mn\7g\2\2no\7x\2\2op\7c\2\2pq\3\2\2"+
		"\2qt\7*\2\2ru\5!\21\2su\5\33\16\2tr\3\2\2\2ts\3\2\2\2uv\3\2\2\2vw\7+\2"+
		"\2wx\7\60\2\2x\20\3\2\2\2yz\7u\2\2z{\7g\2\2{|\3\2\2\2|}\7*\2\2}~\5\27"+
		"\f\2~\177\5\25\13\2\177\u0080\5\27\f\2\u0080\u0081\7+\2\2\u0081\u0082"+
		"\7g\2\2\u0082\u0083\7p\2\2\u0083\u0084\7v\2\2\u0084\u0085\7c\2\2\u0085"+
		"\u0086\7q\2\2\u0086\u0087\3\2\2\2\u0087\u0089\7}\2\2\u0088\u008a\5\13"+
		"\6\2\u0089\u0088\3\2\2\2\u008a\u008b\3\2\2\2\u008b\u0089\3\2\2\2\u008b"+
		"\u008c\3\2\2\2\u008c\u008d\3\2\2\2\u008d\u008e\7\177\2\2\u008e\22\3\2"+
		"\2\2\u008f\u0090\5\33\16\2\u0090\u0091\7<\2\2\u0091\u0092\7?\2\2\u0092"+
		"\u0093\3\2\2\2\u0093\u0094\5\27\f\2\u0094\24\3\2\2\2\u0095\u00a0\7>\2"+
		"\2\u0096\u0097\7>\2\2\u0097\u00a0\7?\2\2\u0098\u00a0\7@\2\2\u0099\u009a"+
		"\7@\2\2\u009a\u00a0\7?\2\2\u009b\u009c\7?\2\2\u009c\u00a0\7?\2\2\u009d"+
		"\u009e\7#\2\2\u009e\u00a0\7?\2\2\u009f\u0095\3\2\2\2\u009f\u0096\3\2\2"+
		"\2\u009f\u0098\3\2\2\2\u009f\u0099\3\2\2\2\u009f\u009b\3\2\2\2\u009f\u009d"+
		"\3\2\2\2\u00a0\26\3\2\2\2\u00a1\u00a7\5\31\r\2\u00a2\u00a3\5#\22\2\u00a3"+
		"\u00a4\5\31\r\2\u00a4\u00a6\3\2\2\2\u00a5\u00a2\3\2\2\2\u00a6\u00a9\3"+
		"\2\2\2\u00a7\u00a5\3\2\2\2\u00a7\u00a8\3\2\2\2\u00a8\30\3\2\2\2\u00a9"+
		"\u00a7\3\2\2\2\u00aa\u00ae\5\33\16\2\u00ab\u00ae\5\37\20\2\u00ac\u00ae"+
		"\5!\21\2\u00ad\u00aa\3\2\2\2\u00ad\u00ab\3\2\2\2\u00ad\u00ac\3\2\2\2\u00ae"+
		"\32\3\2\2\2\u00af\u00b1\t\2\2\2\u00b0\u00af\3\2\2\2\u00b1\u00b5\3\2\2"+
		"\2\u00b2\u00b4\t\3\2\2\u00b3\u00b2\3\2\2\2\u00b4\u00b7\3\2\2\2\u00b5\u00b3"+
		"\3\2\2\2\u00b5\u00b6\3\2\2\2\u00b6\34\3\2\2\2\u00b7\u00b5\3\2\2\2\u00b8"+
		"\u00bb\5\37\20\2\u00b9\u00bb\5!\21\2\u00ba\u00b8\3\2\2\2\u00ba\u00b9\3"+
		"\2\2\2\u00bb\36\3\2\2\2\u00bc\u00be\t\4\2\2\u00bd\u00bc\3\2\2\2\u00be"+
		"\u00bf\3\2\2\2\u00bf\u00bd\3\2\2\2\u00bf\u00c0\3\2\2\2\u00c0\u00c7\3\2"+
		"\2\2\u00c1\u00c3\7\60\2\2\u00c2\u00c4\t\4\2\2\u00c3\u00c2\3\2\2\2\u00c4"+
		"\u00c5\3\2\2\2\u00c5\u00c3\3\2\2\2\u00c5\u00c6\3\2\2\2\u00c6\u00c8\3\2"+
		"\2\2\u00c7\u00c1\3\2\2\2\u00c7\u00c8\3\2\2\2\u00c8 \3\2\2\2\u00c9\u00cb"+
		"\7$\2\2\u00ca\u00cc\t\5\2\2\u00cb\u00ca\3\2\2\2\u00cc\u00cd\3\2\2\2\u00cd"+
		"\u00cb\3\2\2\2\u00cd\u00ce\3\2\2\2\u00ce\u00cf\3\2\2\2\u00cf\u00d0\7$"+
		"\2\2\u00d0\"\3\2\2\2\u00d1\u00d2\t\6\2\2\u00d2$\3\2\2\2\u00d3\u00d4\t"+
		"\7\2\2\u00d4\u00d5\3\2\2\2\u00d5\u00d6\b\23\3\2\u00d6&\3\2\2\2\24\2JW"+
		"]t\u008b\u009f\u00a7\u00ad\u00b0\u00b3\u00b5\u00ba\u00bf\u00c5\u00c7\u00cb"+
		"\u00cd\4\3\2\2\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}