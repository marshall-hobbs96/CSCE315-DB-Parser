import java.util.*;
import java.io.File;
import java.io.PrintWriter;
import java.io.IOException;


public class DatabaseEngineMarshall{
	public ArrayList<Table> tables;
	
	public PrintWriter openD(String file_name){			//Returns a File object that can be read from or written to
		
		try{
		
			File file = new File(file_name);
			
			if(file.exists() == false) {
				
				boolean success = file.createNewFile();
				
				if(success != true) {
					
					System.out.println("Error: Error creating new file");
					return null;
					
				}
				
				else {
					
					if((file.canRead() && file.canWrite()) != true) {
						
						System.out.println("Error: Cannot read/write to file");
						return null; 
						
					}
					
					else {
						
						PrintWriter writer = new PrintWriter(file);
						return writer; 
						
					}
					
				}
				
			}
			
			else{
				
				if((file.canRead() && file.canWrite()) != true) {
						
					System.out.println("Error: Cannot read/write to file");
					return null; 
						
				}
					
				else {		//parse text file for tables, create new tables, populate tables with data, put new tables in Tables data struct???
					
					PrintWriter writer = new PrintWriter(file);
					return writer; 
					
				}
				
			}
		
		}
		
		catch(IOException success) {
			
			System.err.println("Error: " + success.getMessage());
			return null;
			
		}
		
	}
	
	public boolean closeD(PrintWriter writer){		//not sure what this is really suppose to do right now but whateva

		for(int i = 0; i <tables.size(); i++) {
			
			writeD(writer, tables.get(i));
			
		} 	
			
			return true; 
	
	}
	
	public void writeD(PrintWriter writer, Table table){
		
		writer.println(table.title);
		
		for(int i = 0; i<table.datas.get(0).size(); i++) {
			
			for(int j = 0; j<table.datas.size(); j++) {
				
				writer.print(table.datas.get(j).get(i));
				writer.print("\t");
				
			}
			
			writer.print("\n");
			
		}
		
	}
	
	public void exitD(){		//system call to exit JVM, ezpz
	
		System.exit(0);
		return;
	
	}
	
	public void showD(Table table){		//assuming it means show a table? p.s., thanks for writing a print function for the tables, basically did this for me
		
		table.tablePrint();
		return;
		
	}
	
	public void createD(String name, ArrayList<ArrayList<String>> columns, ArrayList<String> pKey){	
		tables.add(new Table(name,columns, pKey));
	}
	
	public void updataD(){			//not entirely sure what this is suppose to do
	}
	
	public void insertD(String name, ArrayList<String> element){
		for(int i = 0; i<tables.size();i++){
			if(tables.get(i).getName() == name){
				tables.get(i).addElement(element);
				return;
			}
		}
		System.out.println("Error: table does not exist");
	}
	
	public void deleteD(String name){
		for(int i = 0; i<tables.size();i++){
			if(tables.get(i).getName() == name){
				tables.remove(i);
				return;
			}
		}
	}
	
	public void selectionD(){
	}
	
	public void projectionD(){
	}
	
	public void renamingD(){
	}
	
	public void unionD(){
	}
	
	public void differencecD(){
	}
	
	public void productD(){
	}
	
	
	
	
	public static void main(String[] args) {
		
	}
	
}