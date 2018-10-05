import java.util.*;
import java.lang.*;


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
		System.exit(0);
		return;
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
	
		
	public ArrayList<String> equal(String tableName, String column, String value){
		ArrayList<String> equalRows = new ArrayList<String>();
		for (int i = 0; i < tables.size(); i++) {
			if (tables.get(i).getName().equals(tableName)) {
				for (int j = 0; j < ((tables.get(i)).datas).size(); j++) {
					if ((tables.get(i).datas.get(j).get(0)).equals(column)) {
						for (int k = 2; k < (tables.get(i).datas.get(j)).size(); k++) {
							if (tables.get(i).datas.get(j).get(k).equals(value)) {
								equalRows.add(Integer.toString(k));		//help from http://javadevnotes.com/java-integer-to-string-examples
							}
						}
					}
				}
			}
		}
		return equalRows;
	}
	
	public ArrayList<String> notEqual(String tableName, String column, String value){
		ArrayList<String> notEqualRows = new ArrayList<String>();
		for (int i = 0; i < tables.size(); i++) {
			if (tables.get(i).getName().equals(tableName)) {
				for (int j = 0; j < ((tables.get(i)).datas).size(); j++) {
					if ((tables.get(i).datas.get(j).get(0)).equals(column)) {
						for (int k = 2; k < (tables.get(i).datas.get(j)).size(); k++) {
							if (!tables.get(i).datas.get(j).get(k).equals(value)) {
								notEqualRows.add(Integer.toString(k));
							}
						}
					}
				}
			}
		}
		return notEqualRows;
	}
	
	public ArrayList<String> greater(String tableName, String column, String value){
		ArrayList<String> greaterRows = new ArrayList<String>();
		int numValue = Integer.parseInt(value);
		for (int i = 0; i < tables.size(); i++) {
			if (tables.get(i).getName().equals(tableName)) {
				for (int j = 0; j < ((tables.get(i)).datas).size(); j++) {
					if ((tables.get(i).datas.get(j).get(0)).equals(column)) {
						for (int k = 2; k < (tables.get(i).datas.get(j)).size(); k++) {
							if (Integer.parseInt(tables.get(i).datas.get(j).get(k)) > numValue) {
								greaterRows.add(Integer.toString(k));
								//System.out.print(k);
							}
						}
					}
				}
			}
		}
		return greaterRows;
	}
	
	public ArrayList<String> lesser(String tableName, String column, String value){
		ArrayList<String> lesserRows = new ArrayList<String>();
		int numValue = Integer.parseInt(value);
		for (int i = 0; i < tables.size(); i++) {
			if (tables.get(i).getName().equals(tableName)) {
				for (int j = 0; j < ((tables.get(i)).datas).size(); j++) {
					if ((tables.get(i).datas.get(j).get(0)).equals(column)) {
						for (int k = 2; k < (tables.get(i).datas.get(j)).size(); k++) {
							if (Integer.parseInt(tables.get(i).datas.get(j).get(k)) < numValue) {
								lesserRows.add(Integer.toString(k));
								//System.out.print(k);
							}
						}
					}
				}
			}
		}
		return lesserRows;
	}
	
	public ArrayList<String> lessEqual(String tableName, String column, String value){
		ArrayList<String> lessEqRows = new ArrayList<String>();
		int numValue = Integer.parseInt(value);
		for (int i = 0; i < tables.size(); i++) {
			if (tables.get(i).getName().equals(tableName)) {
				for (int j = 0; j < ((tables.get(i)).datas).size(); j++) {
					if ((tables.get(i).datas.get(j).get(0)).equals(column)) {
						for (int k = 2; k < (tables.get(i).datas.get(j)).size(); k++) {
							if (Integer.parseInt(tables.get(i).datas.get(j).get(k)) <= numValue) {
								lessEqRows.add(Integer.toString(k));
								//System.out.print(k);
							}
						}
					}
				}
			}
		}
		return lessEqRows;
	}
	
	public ArrayList<String> greatEqual(String tableName, String column, String value){
		ArrayList<String> greatEqRows = new ArrayList<String>();
		int numValue = Integer.parseInt(value);
		for (int i = 0; i < tables.size(); i++) {
			if (tables.get(i).getName().equals(tableName)) {
				for (int j = 0; j < ((tables.get(i)).datas).size(); j++) {
					if ((tables.get(i).datas.get(j).get(0)).equals(column)) {
						for (int k = 2; k < (tables.get(i).datas.get(j)).size(); k++) {
							if (Integer.parseInt(tables.get(i).datas.get(j).get(k)) >= numValue) {
								greatEqRows.add(Integer.toString(k));
								//System.out.print(k);
							}
						}
					}
				}
			}
		}
		return greatEqRows;
	}
	
	public ArrayList<String> and(ArrayList<String> arg1, ArrayList<String> arg2){
		ArrayList<String> both = new ArrayList<String>();
		for (int i = 0; i < arg1.size(); i++) {
			for (int j = 0; j < arg2.size(); j++) {
				if (arg1.get(i).equals(arg2.get(j))) {
					both.add(arg1.get(i));
				}
			}
		}
		return both;
	}
	
	public ArrayList<String> or(ArrayList<String> arg1, ArrayList<String> arg2){
		ArrayList<String> atLeastOne = new ArrayList<String>();
		int i = 0; //use to increment arg1
		int j = 0; //use to increment arg2
		int value1 = 0; //use to compare int value from arg1
		int value2 = 0; //use to compare int value from arg2
		while ((i < arg1.size())&&(j < arg2.size())) {
			value1 = Integer.parseInt(arg1.get(i));
			value2 = Integer.parseInt(arg2.get(j));
			if (value1 < value2) { 
				atLeastOne.add(arg1.get(i));
				i++;
			}
			else if (value1 > value2) { 
				atLeastOne.add(arg2.get(i));
				j++;
			}
			else if (value1 == value2) { 
				atLeastOne.add(arg1.get(i));
				i++;
				j++;
			}
		}
		return atLeastOne;
	}
	
	//public ArrayList<ArrayList<String>> selectionD(String tableName, String column, String value, String comparison){
		
	//}
	
	public ArrayList<String> projectionD(String tableName, String columnName){
		ArrayList<String> projectedColumn = new ArrayList<String>();
		projectedColumn.add("empty");
		for (int i = 0; i < tables.size(); i++) {
			if (tables.get(i).getName().equals(tableName)) {
				ArrayList<ArrayList<String>> columns = (tables.get(i)).datas;
				for (int j = 0; j < columns.size(); j++) {
					ArrayList<String> column = columns.get(j);
					if ((column).equals(columnName)){
						projectedColumn = column;
					}
				}
			}
		}
		return projectedColumn;
	}
	
	public void renamingD(String tableName, String origName, String newName){
		for (int i = 0; i < tables.size(); i++) {
			if (tables.get(i).getName().equals(tableName)) {
				for (int j = 0; j < ((tables.get(i)).datas).size(); j++) {
					if ((tables.get(i).datas.get(j).get(0)).equals(origName)) {
						tables.get(i).datas.get(j).set(0, newName);
					}
				}
			}
		}
	}
	
/* 	public ArrayList<ArrayList<String>> unionD(String tableName1, String tableName2){
		ArrayList<ArrayList<String>> union = new ArrayList<ArrayList<String>>();
		ArrayList<ArrayList<String>> table1 = new ArrayList<ArrayList<String>>();
		ArrayList<ArrayList<String>> table2 = new ArrayList<ArrayList<String>>();
		for (int i = 0; i < tables.size(); i++) {
			if (tables.get(i).getName().equals(table1Name
		}
	} */
	
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
		
		//show all equal to poodle
		engine.notEqual(name,"kind","poodle");
		
		//call rename on kind
		System.out.println();
		System.out.println("Rename 'kind' to 'akind'...");
		engine.renamingD(name, "kind", "akind");
		
		//call show on dogs to show kind change
		System.out.println();
		engine.showD(name);
		
		//deminstrating delete
		System.out.println();
		System.out.println("Before Delete is called on dogs the engine holds "+ String.valueOf(engine.tables.size())+ " relations");
		engine.deleteD(name);
		System.out.println("After Delete is called on dogs the engine holds "+ String.valueOf(engine.tables.size())+ " relations");
		
		//calls exit
		System.out.println();
		System.out.println("Calling Exit...");
		engine.exitD();
	}
	
	
	
	
	
	
	
	
}