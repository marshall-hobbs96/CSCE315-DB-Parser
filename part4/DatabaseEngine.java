import java.util.*;
import java.lang.*;
import java.io.*;


public class DatabaseEngine{
	//hold all relations in main core
	public static ArrayList<Table> tables;
	
	public DatabaseEngine(){
		ArrayList<Table> name = new ArrayList<Table>();
		tables = name;
	}
	
	public static void openD(String name){
		try{
			FileReader fr = new FileReader("dogs.db"); 	//reads input file

			int i; 
			boolean middleWord = false;
			String word = "";
			ArrayList<String> temp = new ArrayList<String>();
			boolean line1 = true;
			boolean line2 = true;
			boolean line3 = true;
			String name1 = "";
			Table table;	//add input data to table
			ArrayList<String> columName = new ArrayList<String>();
			ArrayList<String> limits = new ArrayList<String>();
			ArrayList<Integer> key = new ArrayList<Integer>();
			ArrayList<ArrayList<String>> elements = new ArrayList<ArrayList<String>>();
			while ((i=fr.read()) != -1){ 	//take input as long as there's data in the file
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
		
	
	public static void closeD(String name){	//remove file from tables
	
		for(int i = 0; i<tables.size();i++){
			if(tables.get(i).title.equals(name)){
				tables.get(i).printToFile();
				tables.remove(i);				
				return;
			}
		}
		
		

	}
	
	public static void writeD(String name){	//write to output file
		for(int i = 0; i<tables.size();i++){
			if(tables.get(i).title.equals(name)){
				tables.get(i).printToFile();
				return;
			}
		}
	}
	
	public static void exitD(){	//quit program
		System.exit(0);
		return;
	}
	
	public static void showD(String name){	//find table and print to command line
		for(int i = 0; i<tables.size();i++){
			if(tables.get(i).title.equals(name)){
				tables.get(i).tablePrint();
				return;
			}
		}
	}
	public static void showD(Table table){	//print table to command line
				table.tablePrint();
				return;
	}
	
	public static void createD(String name, ArrayList<ArrayList<String>> columns, ArrayList<String> pKey){	
		tables.add(new Table(name,columns, pKey)); //create a new relation and add it to tables
	}
	
	/*public static void createD(String name, ParseTree columns, ParseTree pKey){
		
		//System.out.println(columns.)
		
	}*/
	
	public static void updataD(String name,ArrayList<String> satisfyCondition,ArrayList<ArrayList<String>> newAttribute){//first string is column name, second string is new literal
		for(int i = 0; i<tables.size();i++){  //change the values of all elements that meet a condition
			if(tables.get(i).title.equals(name)){
				for(int j = 0; j < satisfyCondition.size();j++){
					if(Integer.valueOf(satisfyCondition.get(j)) != 0 && Integer.valueOf(satisfyCondition.get(j)) != 1){
						tables.get(i).changeElement(Integer.valueOf(satisfyCondition.get(j)), newAttribute);
					}
				}
			}
		}
	}
	
	public static void insertD(String name, ArrayList<String> element){  //add a row to a relation
		for(int i = 0; i<tables.size();i++){
			if(tables.get(i).title.equals(name)){
				tables.get(i).addElement(element);
				return;
			}
		}
		System.out.println("Error: table does not exist");
	}
	
	public static void deleteD(String name,ArrayList<String> leavingElements){ 
		for(int i = 0; i<tables.size();i++){  //search for rows and remove them from relation
			if(tables.get(i).title.equals(name)){
				for(int j = leavingElements.size()-1; j>=0;j--)
					if(Integer.valueOf(leavingElements.get(j)) != 0 && Integer.valueOf(leavingElements.get(j)) != 1){
						tables.get(i).deleteElement(Integer.valueOf(leavingElements.get(j)));
					}
				return;
			}
		}
	}

	public static ArrayList<String> equal(String tableName, String column, String value){
		ArrayList<String> equalRows = new ArrayList<String>();
		ArrayList<ArrayList<String>> columns = new ArrayList<ArrayList<String>>();
		int twoColumns = 0;	//use to check if comparing two columns or values
		for (int i = 0; i < tables.size(); i++) {	//find table
			if (tables.get(i).getName().equals(tableName)) {
				columns = tables.get(i).datas;
			}
		}
		ArrayList<String> column1 = new ArrayList<String>();
		ArrayList<String> column2 = new ArrayList<String>();
		for (int j = 0; j < columns.size(); j++) {	//see if value matches a column name
			if (value.equals(columns.get(j).get(0))) {
				column2 = columns.get(j);
				twoColumns = 1;
			}
			if (column.equals(columns.get(j).get(0))) {
				column1 = columns.get(j);
			}
		}
		if (twoColumns == 1) {
			for (int k = 2; k < column1.size(); k++) {  //if two columns, compare the column values in each row
				if (column1.get(k).equals(column2.get(k))) {
					equalRows.add(String.valueOf(k));	//add the matches to output
				}	
			}
		}
		else {
			for (int k = 2; k < column1.size(); k++) { //compare value to each row in that column and add matches to output
				if (column1.get(k).equals(value)) {
					equalRows.add(String.valueOf(k));
				}
			}
		}
		return equalRows;
	}
	
	public static ArrayList<String> notEqual(String tableName, String column, String value){ //same as equals, except finding values that aren't equal
		ArrayList<String> notEqualRows = new ArrayList<String>();
		ArrayList<ArrayList<String>> columns = new ArrayList<ArrayList<String>>();
		int twoColumns = 0;
		for (int i = 0; i < tables.size(); i++) {
			if (tables.get(i).getName().equals(tableName)) {
				columns = tables.get(i).datas;
			}
		}
		ArrayList<String> column1 = new ArrayList<String>();
		ArrayList<String> column2 = new ArrayList<String>();
		for (int j = 0; j < columns.size(); j++) {
			if (value.equals(columns.get(j).get(0))) {
				column2 = columns.get(j);
				twoColumns = 1;
			}
			if (column.equals(columns.get(j).get(0))) {
				column1 = columns.get(j);
			}
		}
		if (twoColumns == 1) {
			for (int k = 2; k < column1.size(); k++) {
				if (!column1.get(k).equals(column2.get(k))) {
					notEqualRows.add(String.valueOf(k));
				}	
			}
		}
		else {
			for (int k = 2; k < column1.size(); k++) {
				if (!column1.get(k).equals(value)) {
					notEqualRows.add(String.valueOf(k));
				}
			}
		}
		return notEqualRows;
	}
	
	public static ArrayList<String> greater(String tableName, String column, String value){  //same as equals, except finding values that are greater than
		ArrayList<String> greaterRows = new ArrayList<String>();
		ArrayList<ArrayList<String>> columns = new ArrayList<ArrayList<String>>();
		int twoColumns = 0;
		for (int i = 0; i < tables.size(); i++) {
			if (tables.get(i).getName().equals(tableName)) {
				columns = tables.get(i).datas;
			}
		}
		ArrayList<String> column1 = new ArrayList<String>();
		ArrayList<String> column2 = new ArrayList<String>();
		for (int j = 0; j < columns.size(); j++) {
			if (value.equals(columns.get(j).get(0))) {
				column2 = columns.get(j);
				twoColumns = 1;
			}
			if (column.equals(columns.get(j).get(0))) {
				column1 = columns.get(j);
			}
		}
		if (twoColumns == 1) {
			for (int k = 2; k < column1.size(); k++) {
				if (Integer.parseInt(column1.get(k)) > Integer.parseInt(column2.get(k))) {
					greaterRows.add(String.valueOf(k));
				}	
			}
		}
		else {
			for (int k = 2; k < column1.size(); k++) {
				if (Integer.parseInt(column1.get(k)) > Integer.parseInt(value)) {
					greaterRows.add(String.valueOf(k));
				}
			}
		}
		return greaterRows;
	}
	
	public static ArrayList<String> less(String tableName, String column, String value){  //same as equals, except finding values that are less than
		ArrayList<String> lessRows = new ArrayList<String>();
		ArrayList<ArrayList<String>> columns = new ArrayList<ArrayList<String>>();
		int twoColumns = 0;
		for (int i = 0; i < tables.size(); i++) {
			if (tables.get(i).getName().equals(tableName)) {
				columns = tables.get(i).datas;
			}
		}
		ArrayList<String> column1 = new ArrayList<String>();
		ArrayList<String> column2 = new ArrayList<String>();
		for (int j = 0; j < columns.size(); j++) {
			if (value.equals(columns.get(j).get(0))) {
				column2 = columns.get(j);
				twoColumns = 1;
			}
			if (column.equals(columns.get(j).get(0))) {
				column1 = columns.get(j);
			}
		}
		if (twoColumns == 1) {
			for (int k = 2; k < column1.size(); k++) {
				if (Integer.parseInt(column1.get(k)) < Integer.parseInt(column2.get(k))) {
					lessRows.add(String.valueOf(k));
				}	
			}
		}
		else {
			for (int k = 2; k < column1.size(); k++) {
				if (Integer.parseInt(column1.get(k)) < Integer.parseInt(value)) {
					lessRows.add(String.valueOf(k));
				}
			}
		}
		return lessRows;
	}
	
	public static ArrayList<String> greaterEqual(String tableName, String column, String value){ ////same as equals, except finding values that are greater than or equal
		ArrayList<String> greaterEqRows = new ArrayList<String>();
		ArrayList<ArrayList<String>> columns = new ArrayList<ArrayList<String>>();
		int twoColumns = 0;
		for (int i = 0; i < tables.size(); i++) {
			if (tables.get(i).getName().equals(tableName)) {
				columns = tables.get(i).datas;
			}
		}
		ArrayList<String> column1 = new ArrayList<String>();
		ArrayList<String> column2 = new ArrayList<String>();
		for (int j = 0; j < columns.size(); j++) {
			if (value.equals(columns.get(j).get(0))) {
				column2 = columns.get(j);
				twoColumns = 1;
			}
			if (column.equals(columns.get(j).get(0))) {
				column1 = columns.get(j);
			}
		}
		if (twoColumns == 1) {
			for (int k = 2; k < column1.size(); k++) {
				if (Integer.parseInt(column1.get(k)) >= Integer.parseInt(column2.get(k))) {
					greaterEqRows.add(String.valueOf(k));
				}	
			}
		}
		else {
			for (int k = 2; k < column1.size(); k++) {
				if (Integer.parseInt(column1.get(k)) >= Integer.parseInt(value)) {
					greaterEqRows.add(String.valueOf(k));
				}
			}
		}
		return greaterEqRows;
	}
	
	public static ArrayList<String> lessEqual(String tableName, String column, String value){  //same as equals, except finding values that are less than or equal
		ArrayList<String> lessEqRows = new ArrayList<String>();
		ArrayList<ArrayList<String>> columns = new ArrayList<ArrayList<String>>();
		int twoColumns = 0;
		for (int i = 0; i < tables.size(); i++) {
			if (tables.get(i).getName().equals(tableName)) {
				columns = tables.get(i).datas;
			}
		}
		ArrayList<String> column1 = new ArrayList<String>();
		ArrayList<String> column2 = new ArrayList<String>();
		for (int j = 0; j < columns.size(); j++) {
			if (value.equals(columns.get(j).get(0))) {
				column2 = columns.get(j);
				twoColumns = 1;
			}
			if (column.equals(columns.get(j).get(0))) {
				column1 = columns.get(j);
			}
		}
		if (twoColumns == 1) {
			for (int k = 2; k < column1.size(); k++) {
				if (Integer.parseInt(column1.get(k)) <= Integer.parseInt(column2.get(k))) {
					lessEqRows.add(String.valueOf(k));
				}	
			}
		}
		else {
			for (int k = 2; k < column1.size(); k++) {
				if (Integer.parseInt(column1.get(k)) <= Integer.parseInt(value)) {
					lessEqRows.add(String.valueOf(k));
				}
			}
		}
		return lessEqRows;
	}
	
	public static ArrayList<String> and(ArrayList<String> arg1, ArrayList<String> arg2){
		ArrayList<String> both = new ArrayList<String>();  //search through lists of row numbers and output the common elements of the lists
		for (int i = 0; i < arg1.size(); i++) {
			for (int j = 0; j < arg2.size(); j++) {
				if (arg1.get(i).equals(arg2.get(j))) {
					both.add(arg1.get(i));
				}
			}
		}
		return both;
	}
	
	public static ArrayList<String> or(ArrayList<String> arg1, ArrayList<String> arg2){
		ArrayList<String> atLeastOne = new ArrayList<String>(); //search through lists of row numbers and output the union of the lists
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
	
	public static ArrayList<ArrayList<String>> selectionD(String tableName, ArrayList<String> rows){
		ArrayList<Integer> rowNums = new ArrayList<Integer>(); //for output
		ArrayList<ArrayList<String>> columns = new ArrayList<ArrayList<String>>();
		ArrayList<ArrayList<String>> selectedRows = new ArrayList<ArrayList<String>>();
		for (int i = 0; i < rows.size(); i++) {	//convert from string to vector
			rowNums.add(Integer.parseInt(rows.get(i)));
		}
		for (int j = 0; j < tables.size(); j++) {
			if (tableName.equals(tables.get(j).getName())) {
				columns = tables.get(j).datas;	//get data from table
			}
		}
		int numColumns = columns.size();
		for (int k = 0; k < numColumns; k++) {	//add the correct elements from each column to their vectors
			ArrayList<String> newColumn = new ArrayList<String>();
			ArrayList<String> tempColumn = new ArrayList<String>();
			tempColumn = columns.get(k);
			newColumn.add(tempColumn.get(0));
			newColumn.add(tempColumn.get(1));
			for (int m = 0; m < rows.size(); m++) {
				newColumn.add(tempColumn.get(rowNums.get(m)));
			}
			selectedRows.add(newColumn); //add column with all of the rows it should have
		}
		return selectedRows;
	}
	
	public static ArrayList<ArrayList<String>> projectionD(String tableName, ArrayList<String> columns){
		ArrayList<ArrayList<String>> projectedColumn = new ArrayList<ArrayList<String>>();
		String columnName;
		for (int i = 0; i < tables.size(); i++) {
			if (tableName.equals(tables.get(i).getName())) {
				for (int j = 0; j < columns.size(); j++) {
					columnName = columns.get(j);
					for (int k = 0; k < tables.get(i).datas.size(); k++) {
						if (columnName.equals(tables.get(i).datas.get(k).get(0))) {	//if the column name is in the list, add this column to output relation
							projectedColumn.add(new ArrayList<String>(tables.get(i).datas.get(k)));
						}
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
					if ((tables.get(i).datas.get(j).get(0)).equals(origName)) { //find columns with correct name
						tables.get(i).datas.get(j).set(0, newName); //change name to new name
					}
				}
			}
		}
	}
	
	public static ArrayList<ArrayList<String>> unionD(String table1Name, String table2Name){
		//this will be the output
		ArrayList<ArrayList<String>> union = new ArrayList<ArrayList<String>>();
		//these will hold the data from the input tables
		ArrayList<ArrayList<String>> columns1 = new ArrayList<ArrayList<String>>();
		ArrayList<ArrayList<String>> columns2 = new ArrayList<ArrayList<String>>();
		//find the input tables in the database list
		for (int i = 0; i < tables.size(); i++) {
			if (tables.get(i).getName().equals(table1Name)) {
				columns1 = new ArrayList<ArrayList<String>>(tables.get(i).datas);
			}
			if (tables.get(i).getName().equals(table2Name)) {
				columns2 = new ArrayList<ArrayList<String>>(tables.get(i).datas);
			}
		}
		//check if the tables are union compatable
		if (columns1.size() != columns2.size()) {
			System.out.println("Unable to perform union - unnequal number of columns.");
		}
		else {	//only continue if they are union compatable
			//true if all columns have the same type
			int sameTypes = 1;
			int type1 = 0;
			int type2 = 0;
			//a max char value of -1 indicates the type is integer
			for (int j = 0; j < columns1.size(); j++) {
				type1 = Integer.valueOf(new String(columns1.get(j).get(1)));
				type2 = Integer.valueOf(columns2.get(j).get(1));
				if (type1 > 0) { type1 = 1; }
				else if (type1 < 0) { type1 = -1; }
				if (type2 > 0) { type2 = 1; }
				else if (type2 < 0) { type2 = -1; }
				//if the types were not equal, don't continue
				if (type1 != type2) {
					System.out.println("Unable to perform union - incompatible types.");
				}
				else {	//continue if the types are equal
						//create new columns holding the name from the first table and the greatest char max
					ArrayList<String> newColumn = new ArrayList<String>();
					int largestChar = 0;
					if (type1 != -1) {	//if they aren't integers
						//find the largest max char value
						if (Integer.parseInt(columns1.get(j).get(1)) >= Integer.parseInt(columns2.get(j).get(1))) {
							largestChar = Integer.parseInt(new String(columns1.get(j).get(1)));
						}
						else { 
							largestChar = Integer.parseInt(columns2.get(j).get(1)); 
						}
					}
					//keep the type as integer
					else { 
						largestChar = -1; 
					}
						//use the name from the first table and largest char max
					newColumn.add(new String(columns1.get(j).get(0)));
					newColumn.add(String.valueOf(largestChar));
					union.add(newColumn);
					//check if there are equal rows to avoid duplicates
					int colCount = 0;
					for (int n = 2; n < columns1.get(colCount).size(); n++) {	//for each row in table1
						union.get(j).add(new String(columns1.get(j).get(n)));
					}
				}
			}		
			for(int t = 2; t<columns2.get(0).size(); t++){	//column2 row
				boolean completeUniqe = true;
				 for(int p = 2; p < union.get(0).size(); p++){// rows of union
					boolean uniqe = false;
					for(int u = 0; u<union.size(); u++){//columns of union
						if(columns2.get(u).get(t) != union.get(u).get(p) && !uniqe){
							uniqe = true;
						}
					}
					if(!uniqe){
						completeUniqe = false;
					}
				}
				if(completeUniqe){
					for(int u = 0; u<union.size(); u++){//columns of union
						union.get(u).add(new String(columns2.get(u).get(t)));
					}

				}
			}
		}
		return union;
	} 
	
 	public static ArrayList<ArrayList<String>> differenceD(String table1Name, String table2Name){
		//this will be the output
		ArrayList<ArrayList<String>> difference = new ArrayList<ArrayList<String>>();
		//these will hold the data from the input tables
		ArrayList<ArrayList<String>> columns1 = new ArrayList<ArrayList<String>>();
		ArrayList<ArrayList<String>> columns2 = new ArrayList<ArrayList<String>>();
		//find the input tables in the database list
		for (int i = 0; i < tables.size(); i++) {
			if (tables.get(i).getName().equals(table1Name)) {
				columns1 = new ArrayList<ArrayList<String>>(tables.get(i).datas);
			}
			if (tables.get(i).getName().equals(table2Name)) {
				columns2 = new ArrayList<ArrayList<String>>(tables.get(i).datas);
			}
		}
		//check if the tables are difference compatable
		if (columns1.size() != columns2.size()) {
			System.out.println("Unable to perform difference - unnequal number of columns.");
		}
		else {	//only continue if they are difference compatable
			//true if all columns have the same type
			int sameTypes = 1;
			int type1 = 0;
			int type2 = 0;
			//a max char value of -1 indicates the type is integer
			for (int j = 0; j < columns1.size(); j++) {
				type1 = Integer.parseInt(new String(columns1.get(j).get(1)));
				type2 = Integer.parseInt(new String(columns2.get(j).get(1)));
				if (type1 > 0) { type1 = 1; }
				else if (type1 < 0) { type1 = -1; }
				if (type2 > 0) { type2 = 1; }
				else if (type2 < 0) { type2 = -1; }
				//if the types were not equal, don't continue
				if (type1 != type2) {
					System.out.println("Unable to perform difference - incompatible types.");
				}
				else {	//continue if the types are equal
					ArrayList<String> newColumn = new ArrayList<String>();
					int largestChar = 0;
					if (type1 != -1) {	//if they aren't integers
						//find the largest max char value
						if (Integer.parseInt(columns1.get(j).get(1)) >= Integer.parseInt(columns2.get(j).get(1))) {
							largestChar = Integer.parseInt(new String(columns1.get(j).get(1)));
						}
						else { 
							largestChar = Integer.parseInt(columns2.get(j).get(1)); 
						}
					}
					//keep the type as integer
					else { 
						largestChar = -1; 
					}
						//use the name from the first table and largest char max
					newColumn.add(new String(columns1.get(j).get(0)));
					newColumn.add(String.valueOf(largestChar));
					difference.add(newColumn);
				}
			}
				for(int t = 2; t<columns2.get(0).size(); t++){//column2 row
					boolean completeUniqe = true;
					for(int p = 2; p < columns1.get(0).size(); p++){// rows of difference
						boolean uniqe = false;
						for(int u = 0; u<columns1.size(); u++){//columns of difference
							if(!columns2.get(u).get(t).equals(columns1.get(u).get(p)) && !uniqe){
								uniqe = true;
							}
						}
						if(!uniqe){
							completeUniqe = false;
						}
					}
					if(completeUniqe){
						for(int u = 0; u<difference.size(); u++){//columns of difference
							difference.get(u).add(new String(columns2.get(u).get(t)));
						}
					}
				}
				for(int t = 2; t<columns1.get(0).size(); t++){//column2 row
					boolean completeUniqe = true;
					for(int p = 2; p < columns2.get(0).size(); p++){// rows of difference
						boolean uniqe = false;
						for(int u = 0; u<columns2.size(); u++){//columns of difference
							if(!columns1.get(u).get(t).equals(columns2.get(u).get(p)) && !uniqe){
								uniqe = true;
							}
						}
						if(!uniqe){
							completeUniqe = false;
						}
					}
					if(completeUniqe){
						for(int u = 0; u<difference.size(); u++){//columns of difference
							difference.get(u).add(new String(columns1.get(u).get(t)));
						}
					}
				}
		}
		return difference;
	}
	
	public static ArrayList<ArrayList<String>> productD(String tableName1,String tableName2){
		ArrayList<ArrayList<String>> product = new ArrayList<ArrayList<String>>();  //use for output
		ArrayList<ArrayList<String>> columns1 = new ArrayList<ArrayList<String>>();  //use to hold table1 data
		ArrayList<ArrayList<String>> columns2 = new ArrayList<ArrayList<String>>();  //use to hold table2 data
		for (int i = 0; i < tables.size(); i++) {  //assign data to lists
			if (tableName1.equals(tables.get(i).getName())) {
				for (int j = 0; j < tables.get(i).datas.size(); j++) {
					columns1.add(new ArrayList<String>(tables.get(i).datas.get(j)));
				}
			}
			if (tableName2.equals(tables.get(i).getName())) {
				for (int j = 0; j < tables.get(i).datas.size(); j++) {
					columns2.add(new ArrayList<String>(tables.get(i).datas.get(j)));
				}
			}
		}
		for (int j = 0; j < columns1.size(); j++)			{  //add columns from the tables to product
			ArrayList<String> tempList = new ArrayList<String>();
			for (int k = 0; k < 2; k++) {
				tempList.add(columns1.get(j).get(k));
			}
			product.add(tempList);
		}
		for (int j = 0; j < columns2.size(); j++) {
			ArrayList<String> tempList = new ArrayList<String>();
			for (int k = 0; k < 2; k++) {
				tempList.add(columns2.get(j).get(k));
			}
			product.add(tempList);
		}
		for (int m = 2; m < columns1.get(0).size(); m++) {	//for each row in table1
			for (int n = 2; n < columns2.get(0).size(); n++) {	//for each row in table2
				for (int o = 0; o < columns1.size(); o++) {	//add values from columns from table1
					product.get(o).add(columns1.get(o).get(m));
				}
				for (int p = columns1.size(); p < (columns1.size() + columns2.size()); p++) {	//add values from columns from table2
					product.get(p).add(columns2.get(p - columns1.size()).get(n));
				}
			}
		}
		return product;
	}
	
	public static void query(String tableName, ArrayList<ArrayList<String>> data) {
		ArrayList<String> key = new ArrayList<String>();
		Table created = new Table(tableName,data,key);
		tables.add(created);
	}
	
	/*public static void main(String[] args) {
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
		System.out.println();
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
		
		//a <- rename (aname, akind) (project (name, kind) dogs);
		ArrayList<String> nameKind = new ArrayList<String>();
		nameKind.add("name");
		nameKind.add("kind");
		engine.query("a", projectionD("dogs", nameKind));
		engine.renamingD("a", "name", "aname");
		engine.renamingD("a", "kind", "akind");
		System.out.println();
		engine.showD("a");
		System.out.println();
		
		//common_names <- project(name) (select (aname == name && akind != kind) (a*animals))
		engine.query("crossprod", productD("a", "dogs"));
		//engine.showD("crossprod");
		engine.query("EQ",selectionD("crossprod",and(notEqual("crossprod","akind","kind"), equal("crossprod","aname","name"))));
		System.out.println();
		//engine.showD("EQ");
		ArrayList<String> names = new ArrayList<String>();
		names.add("name");
		engine.query("common names", projectionD("EQ", names));
		engine.showD("common names");
		System.out.println();
		
		//show difference using delete
		
		//calls close removes people
		System.out.println();
		System.out.println("Before Close is called on dogs the engine holds "+ String.valueOf(engine.tables.size())+ " relations");
		engine.closeD(name);
		System.out.println("After Close is called on dogs the engine holds "+ String.valueOf(engine.tables.size())+ " relations");
		
		//calls exit
		System.out.println();
		System.out.println("Calling Exit...");
		engine.exitD();
	}*/
}