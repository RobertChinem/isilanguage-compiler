package compiler.main;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import compiler.exceptions.SemanticException;
import compiler.parser.IsiLangLexer;
import compiler.parser.IsiLangParser;

public class MainClass {
    public static void main(String[] args) {
		String pathInput = "input.isi";
		String pathOutput = "MainClass.java";

		if(args.length >= 2) {
			pathInput = args[0];
			pathOutput = args[1];
		}

		try {
			IsiLangLexer lexer = new IsiLangLexer(CharStreams.fromFileName(pathInput));

			CommonTokenStream tokenStream = new CommonTokenStream(lexer);
			IsiLangParser parser = new IsiLangParser(tokenStream);
			parser.prog();
			parser.generateCode(pathOutput);
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
