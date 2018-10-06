import java.util.*;
import java.lang.*;
import java.io.*;


public class DatabaseEngine{
	public ArrayList<Table> tables;
	
	public DatabaseEngine(){
		ArrayList<Table> name = new ArrayList<Table>();
		tables = name;
	}
	
	public void openD(String name){
		try{
			FileReader fr = 
			new FileReader("dogs.db"); 

			int i; 
			boolean middleWord = false;
			String word = "";
			ArrayList<String> temp = new ArrayList<String>();
			boolean line1 = true;
			boolean line2 = true;
			boolean line3 = true;
			String name1 = "";
			Table table;
			ArrayList<String> columName = new ArrayList<String>();
			ArrayList<String> limits = new ArrayList<String>();
			ArrayList<Integer> key = new ArrayList<Integer>();
			ArrayList<ArrayList<String>> elements = new ArrayList<ArrayList<String>>();
			while ((i=fr.read()) != -1){ 
				if(i == 34 && middleWord == false){
					middleWord = true;
				}
				else if(i==34 &&middleWord == true){
					middleWord = false;
					temp.add(word);
					word = "";
				}
				else if(middleWord){
					word = word + (char)i;
				}
				else if((char)i == '\n'){
					if(line1){
						name1 = temp.get(0);
						for(int k = 1; k<temp.size();k++){
							key.add(Integer.valueOf(temp.get(k)));
						}
						temp.clear();
						line1 = false;
					}
					else if(line2){
						for(int k = 0; k<temp.size();k++){
							columName.add(temp.get(k));
						}
						temp.clear();
						line2 = false;
						
					}
					else if(line3){
						for(int k = 0; k<temp.size();k++){
							limits.add(temp.get(k));
						}
						temp.clear();
						line3 = false;
					}
					else{
						ArrayList<String> ele = new ArrayList<String>();
						for(int k =0; k<temp.size();k++){
							 ele.add(temp.get(k));
						}
						elements.add(ele);
						temp.clear();
					}
				}
			}
			ArrayList<ArrayList<String>> columns = new ArrayList<ArrayList<String>>();
			for(int k = 0; k<limits.size(); k++){
				ArrayList<String> column = new ArrayList<String>();
				column.add(columName.get(k));
				column.add(limits.get(k));
				columns.add(column);
			}
			ArrayList<String> Key = new ArrayList<String>();
			for(int k = 0; k<key.size(); k++){
				Key.add(columns.get(key.get(k)).get(0));
			}
			table = new Table(name1,columns,Key);

			
			for(int k = 0; k<elements.size();k++){
				table.addElement(elements.get(k));
			}
			tables.add(table);
		}
		catch(IOException ioe){
			System.out.println("Error: Something went wrong");
		}
	}
		
	
	public void closeD(String name){
		for(int i = 0; i<tables.size();i++){
			if(tables.get(i).title.equals(name)){
				tables.get(i).printToFile();
				tables.remove(i);
				return;
			}
		}

	}
	
	public void writeD(String name){
		for(int i = 0; i<tables.size();i++){
			if(tables.get(i).title.equals(name)){
				tables.get(i).printToFile();
				return;
			}
		}
	}
	
	public void exitD(){
		System.exit(0);
		return;
	}
	
	public void showD(String name){
		for(int i = 0; i<tables.size();i++){
			if(tables.get(i).title.equals(name)){
				tables.get(i).tablePrint();
				return;
			}
		}
	}
	public void showD(Table table){
				table.tablePrint();
				return;
	}
	
	public void createD(String name, ArrayList<ArrayList<String>> columns, ArrayList<String> pKey){	
		tables.add(new Table(name,columns, pKey));
	}
	
	public void updataD(String name,ArrayList<String> satisfyCondition,ArrayList<ArrayList<String>> newAttribute){//first string is column name, second string is new literal
		for(int i = 0; i<tables.size();i++){
			if(tables.get(i).title.equals(name)){
				for(int j = 0; j < satisfyCondition.size();j++){
					if(Integer.valueOf(satisfyCondition.get(j)) != 0 && Integer.valueOf(satisfyCondition.get(j)) != 1){
						tables.get(i).changeElement(Integer.valueOf(satisfyCondition.get(j)), newAttribute);
					}
				}
			}
		}
	}
	
	public void insertD(String name, ArrayList<String> element){
		for(int i = 0; i<tables.size();i++){
			if(tables.get(i).title.equals(name)){
				tables.get(i).addElement(element);
				return;
			}
		}
		System.out.println("Error: table does not exist");
	}
	
	public void deleteD(String name,ArrayList<String> leavingElements){ 
		for(int i = 0; i<tables.size();i++){
			if(tables.get(i).title.equals(name)){
				for(int j = leavingElements.size()-1; j>=0;j--)
					if(Integer.valueOf(leavingElements.get(j)) != 0 && Integer.valueOf(leavingElements.get(j)) != 1){
						tables.get(i).deleteElement(Integer.valueOf(leavingElements.get(j)));
					}
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
		//starts DatabaseEngine
		DatabaseEngine engine = new DatabaseEngine();
		
		
		//all information needed to Create
		String name = "people";
		
		//calls open
		engine.openD("dogs");
		
		//calls show on dogs
		engine.showD("dogs");
		
		ArrayList<ArrayList<String>> v = new ArrayList<ArrayList<String>>();
		ArrayList<String> d1 = new ArrayList<String>();
		d1.add("first name");//column title
		d1.add(String.valueOf(18));//VARCHAR(18)
		ArrayList<String> d2 = new ArrayList<String>();
		d2.add("last name");//column title
		d2.add(String.valueOf(10));//VARCHAR(10)
		ArrayList<String> d3 = new ArrayList<String>();
		d3.add("year");//column title
		d3.add(String.valueOf(-1));//-1 means integer
		v.add(d1);
		v.add(d2);
		v.add(d3);
		ArrayList<String> key = new ArrayList<String>();//primary key
		key.add("first name");
		key.add("last name");
		
		//calling Create
		engine.createD(name,v,key);
		
		//all information needed for an insert
		ArrayList<String> first = new ArrayList<String>();
		first.add("Sam");
		first.add("Hay");
		first.add(String.valueOf(12));
		
		//calling insert
		engine.insertD(name,first);
		
		//all information needed for an insert
		ArrayList<String> second = new ArrayList<String>();
		second.add("Hannah");
		second.add("Hutton");
		second.add(String.valueOf(4));
		
		//calling insert
		engine.insertD(name,second);
		
		//call show on people
		engine.showD(name);
		
		//removes element 2 sam
		ArrayList<String> removing = new ArrayList<String>();
		removing.add("2");
		engine.deleteD(name,removing);
		
		
		//change element 2 (hannah) to have 20 year
		ArrayList<String> indexChange = new ArrayList<String>();
		indexChange.add("2");
		ArrayList<String> attributeChange = new ArrayList<String>();
		attributeChange.add("year");
		attributeChange.add("20");
		ArrayList<ArrayList<String>> attributeChanges = new ArrayList<ArrayList<String>>();
		attributeChanges.add(attributeChange);
		engine.updataD(name,indexChange,attributeChanges);
		
		
		
		//calls write
		engine.writeD(name);
		
		//calls close removes people
		System.out.println("Before Close is called on dogs the engine holds "+ String.valueOf(engine.tables.size())+ " relations");
		engine.closeD(name);
		System.out.println("After Close is called on dogs the engine holds "+ String.valueOf(engine.tables.size())+ " relations");
		
		//calls exit
		System.out.println();
		System.out.println("Calling Exit...");
		engine.exitD();
	}
	
	
	
	
	
	
	
	
}