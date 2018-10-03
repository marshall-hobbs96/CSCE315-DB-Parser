import java.util.*;


public class DatabaseEngine{
	public ArrayList<Table> tables;
	
	public void openD(){
	}
	
	public void closeD(){

	}
	
	public void writeD(){
		
	}
	
	public void exitD(){
		
	}
	
	public void showD(){
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
		
	}
	
}