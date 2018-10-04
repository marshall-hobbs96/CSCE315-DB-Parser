import java.util.*;


public class DatabaseEngine{
	public ArrayList<Table> tables;
	
	public DatabaseEngine(){
		ArrayList<Table> name = new ArrayList<Table>();
		tables = name;
	}
	
	public void openD(){
	}
	
	public void closeD(){

	}
	
	public void writeD(){
		
	}
	
	public void exitD(){
		
	}
	
	public void showD(String name){
		for(int i = 0; i<tables.size();i++){
			if(tables.get(i).getName() == name){
				tables.get(i).tablePrint();
				return;
			}
		}
	}
	
	public void createD(String name, ArrayList<ArrayList<String>> columns, ArrayList<String> pKey){	
		tables.add(new Table(name,columns, pKey));
	}
	
	public void updataD(){
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
		//starts DatabaseEngine
		DatabaseEngine engine = new DatabaseEngine();
		
		
		//all information needed to Create
		String name = "dogs";
		ArrayList<ArrayList<String>> v = new ArrayList<ArrayList<String>>();
		ArrayList<String> d1 = new ArrayList<String>();
		d1.add("name");//column title
		d1.add(String.valueOf(18));//VARCHAR(18)
		ArrayList<String> d2 = new ArrayList<String>();
		d2.add("kind");//column title
		d2.add(String.valueOf(10));//VARCHAR(10)
		ArrayList<String> d3 = new ArrayList<String>();
		d3.add("year");//column title
		d3.add(String.valueOf(-1));//-1 means integer
		v.add(d1);
		v.add(d2);
		v.add(d3);
		ArrayList<String> key = new ArrayList<String>();//primary key
		key.add("name");
		key.add("kind");
		
		//calling Create
		engine.createD(name,v,key);
		
		//all information needed for an insert
		ArrayList<String> first = new ArrayList<String>();
		first.add("fido");
		first.add("poodle");
		first.add(String.valueOf(12));
		
		//calling insert
		engine.insertD(name,first);
		
		//all information needed for an insert
		ArrayList<String> second = new ArrayList<String>();
		second.add("Rex");
		second.add("German Sheperd");
		second.add(String.valueOf(4));
		
		//calling insert
		engine.insertD(name,second);
		
		//call show on dogs
		engine.showD(name);
		
		//deminstrating delete
		System.out.println();
		System.out.println("Before Delete is called on dogs the engine holds "+ String.valueOf(engine.tables.size())+ " relations");
		engine.deleteD(name);
		System.out.println("After Delete is called on dogs the engine holds "+ String.valueOf(engine.tables.size())+ " relations");
	}
	
	
	
	
	
	
	
	
}