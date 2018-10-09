import java.util.*;
import java.lang.*;
import java.io.*;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

public class dbmain {


	public static void main(String[] args) {

		System.out.println("Hello, welcome to our database management system. Please Enter a command or query");
		Scanner reader = new Scanner(System.in);
		DatabaseEngine engine = new DatabaseEngine();
		
		
		while(true) {
			
			if(reader.hasNextLine()){
				
				String input = reader.nextLine();
				
				try{
					if(input == "10101010101"){
						
						break;
						
					}
					
					/*else if(input.equals(null)){
						
						
						
					}*/
					
					else {
						
						runProgram(input);

					}
					
				}
				
				
				catch(NullPointerException e) {
					
					
					System.out.println("Error: " + e.toString());
					return;
					
				}
			}
			
			else {}
		}

	}

	public static void runProgram(String input) {
		
		
		CharStream charStream = CharStreams.fromString(input);
		
		ParserLexer lexer = new ParserLexer(charStream);
		
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		
		ParserParser parser = new ParserParser(tokens);
		
		ParserParser.ProgramContext programContext = parser.program();
		
		//ParseTree parseTree = parser.program();
		
		//String output = parseTree.toStringTree(parser);
		//System.out.println(output);
		
		ParseTreeWalker walker = new ParseTreeWalker();
		ParserMasterListener listener = new ParserMasterListener();
		walker.walk(listener, programContext);
		
		return;
		
	};
	
};