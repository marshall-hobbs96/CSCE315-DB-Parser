import java.util.Scanner;
import org.antlr.v4.runtime.*;
import org.antlr.v4.tool.*;
import org.antlr.v4.runtime.tree.*;

public class ParserMain {
		public static Boolean isValid(String testString) {
				Boolean valid = true;
				//If no exception is thrown, the input is valid...
				try{
					//Check the input against the lexer and parser grammar
					//Help from https://www.baeldung.com/java-antlr
					ANTLRInputStream input = new ANTLRInputStream(testString);
					ParserLexer lexer = new ParserLexer(input);
					lexer.removeErrorListeners();
					CommonTokenStream tokens = new CommonTokenStream(lexer);
					ParserParser parser = new ParserParser(tokens);
					//Allow the input to throw an error if it doesn't match the grammar.
					//Help from https://github.com/antlr/antlr4/issues/351
					parser.setErrorHandler(
						new DefaultErrorStrategy() {
							@Override
							public void recover(Parser recognizer, RecognitionException e) {
								throw new RuntimeException();
							}
						}
					);
					//Do not print that there was an error
					parser.removeErrorListeners();
					//Create a tree.
					ParseTree tree = parser.program();
					ParseTreeWalker walker = new ParseTreeWalker();
					ParserBaseListener listener = new ParserBaseListener();
					walker.walk(listener, tree);
				}
				//...otherwise, the input is invalid.
				catch (Exception e) {
					valid = false;
				}
				return valid;
		}
		
		public static void main(String[] args) {
			Scanner keyboard = new Scanner(System.in);
			int lineNumber = 1;
			//Use isValid() to check the validity of each nonempty line in the input, and then print the results
			while (keyboard.hasNextLine()) {
				if(keyboard.hasNext()){
					String inputLine = keyboard.nextLine();
					//Make sure the line isn't empty
					if (!inputLine.isEmpty()){
						//Check validity
						Boolean valid = isValid(inputLine);
						//Print appropriate result
						if (valid == true) {
							System.out.print("Line ");
							System.out.print(lineNumber);
							System.out.println(" is valid");
						}
						else if (valid == false) {
							System.out.print("Line ");
							System.out.print(lineNumber);
							System.out.println(" is invalid");
						}
						//Move to the next line
						lineNumber = lineNumber + 1;
					}
				}
			}
		}
}