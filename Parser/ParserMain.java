import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
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
			BufferedWriter bufferout = null;
			try{
				int lineNumber = 1;
				File outfile = new File("output.txt");
				if(!outfile.exists()){
					outfile.createNewFile();
				}
				FileWriter outwriter = new FileWriter(outfile);
				bufferout = new BufferedWriter(outwriter);
				//Use isValid() to check the validity of each nonempty line in the input, and then print the results
				while (keyboard.hasNextLine()) {
					if(keyboard.hasNext()){
						String inputLine = keyboard.nextLine();
						//Make sure the line isn't empty
						if (!inputLine.isEmpty()){
							//Check validity
							Boolean valid = isValid(inputLine);
							//Make sure the line ends in a semicolon
							String lastChar = inputLine.substring(inputLine.length() - 1);
							//Print appropriate result
							if (valid == true) {
								if (lastChar.equals(";")) {
									System.out.print("Line ");
									bufferout.write("Line ");
									System.out.print(lineNumber);
									bufferout.write(lineNumber+"");
									System.out.println(" is valid");
									bufferout.write(" is valid\n");
								}
								else if (!lastChar.equals(";")) {
									System.out.print("Line ");
									bufferout.write("Line ");
									System.out.print(lineNumber);
									bufferout.write(lineNumber+"");
									System.out.println(" is invalid");
									bufferout.write(" is invalid\n");
								}
							}
							else if (valid == false) {
								System.out.print("Line ");
								bufferout.write("Line ");
								System.out.print(lineNumber);
								bufferout.write(lineNumber+"");
								System.out.println(" is invalid");
								bufferout.write(" is invalid\n");
							}
							//Move to the next line
							lineNumber = lineNumber + 1;
						}
					}
				}
			}
			catch(IOException ioe){
				ioe.printStackTrace();
			}
			finally
			{
				try{
					if(bufferout!=null)
						bufferout.close();
				}
				catch(Exception ex){
					System.out.println("error");
				}
			}
		}
}










