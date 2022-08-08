package compiler.main;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import compiler.exceptions.SemanticException;
import compiler.parser.IsiLangLexer;
import compiler.parser.IsiLangParser;

public class MainClass {
    public static void main(String[] args) {
		try {
			IsiLangLexer lexer = new IsiLangLexer(CharStreams.fromFileName("input.isi"));
			CommonTokenStream tokenStream = new CommonTokenStream(lexer);
			IsiLangParser parser = new IsiLangParser(tokenStream);
			parser.prog();
			parser.generateCode();
		}
		catch(SemanticException e) {
			System.err.println("Semantic error - " + e.getMessage());
		}
		catch(Exception e) {
			e.printStackTrace();
			System.err.println("ERROR " + e.getMessage());
		}
	}
}
