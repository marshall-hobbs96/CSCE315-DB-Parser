import java.util.*;
import java.io.*;


public class Table{
	public String title;
	public ArrayList<ArrayList<String>> datas;
	public ArrayList<Integer> pKey;
	public boolean isopen;
	private String outString;
	
	//d needs to be a vector of vectors, [0] is name of vector,
	//[1] is the char limit, null when int
	public Table(String name,ArrayList<ArrayList<String>> d, ArrayList<String> Key){ //d needs to be a vector of vectors
		title = name;
		datas=d;
		ArrayList<Integer> p = new ArrayList<Integer>();
		for(int i = 0; i < Key.size();i++){
			for(int j = 0;j < datas.size();j++){
				if(Key.get(i) == datas.get(j).get(0)){
					p.add(j);
				}
			}
		}
		pKey = p;
		isopen = false;

	}
	
	
	public String getName(){
		return title;
	}
	
	public void addElement(ArrayList<String> ele){ // ele holds the input elements in order
		if(ele.size() == datas.size()){
			for(int j = 0;j<ele.size();j++){
				if(Integer.parseInt(datas.get(j).get(1))!= -1 && Integer.parseInt(datas.get(j).get(1)) < ele.get(j).length()){
					ele.set(j,ele.get(j).substring(0,Integer.parseInt(datas.get(j).get(1))));
				}
			}
			if(isUnique(ele)){
				for(int i = 0;i<ele.size();i++){
					datas.get(i).add(ele.get(i));
				}
			}
			else{
				System.out.println(ele+" is not a unique element");
			}
		}
		else{
			System.out.println("Error: incorrect number of attributes");
		}
	}
	
	public Boolean isUnique(ArrayList<String> ele){  //check the primary keys for each row and make sure there are no duplicates
		for(int j = 0; j<datas.get(0).size();j++){
			Boolean notSame = true;
			for(int k = 0; k<pKey.size();k++){
				if(notSame == true && ele.get(k) != datas.get(k).get(j)){
					notSame = true;
				}
				else{
					return false;
				}
			}
		}
		return true;
	}

	public void tablePrint(){	//print a table to the command line
		System.out.println(title);
		for(int i = 0; i<datas.get(0).size(); i++){
			for(int j = 0; j < datas.size(); j++){
				System.out.print(datas.get(j).get(i));
				System.out.print("\t");
			}
			System.out.print("\n");
		}
	}
	
	//VERY IMPORTANT: if you are removing multiple elements from the list delete from bottom up so 6 to 0 not 0 to 6
	public void deleteElement(int index){
		if(index<datas.get(0).size() && index>1){
			for(int i = 0; i<datas.size();i++){
				if(index<datas.get(i).size()){
					datas.get(i).remove(index);
				}
			}
		}
		else{
			System.out.println("Error: tried to remove value out of index");
		}
	}
	
	public void changeElement(int index,ArrayList<ArrayList<String>> newAttribute){
		for(int i = 0; i<newAttribute.size(); i++){	//update the values of a row
			for(int k = 0; k<datas.size(); k++){
				if(datas.get(k).get(0).equals(newAttribute.get(i).get(0)) && index < datas.get(k).size()){
					datas.get(k).set(index,newAttribute.get(i).get(1));
				}
			}
		}
		return;
	}
	
	
	public void printToFile(){
		String fileName = title + ".db";
		try{
			File file = new File(fileName);
			if(file.exists() == false) {	//checks if file is exist and makes it if it isn't
				boolean success = file.createNewFile();	
			}	
			if((file.canRead() && file.canWrite()) != true) {	
				System.out.println("Error: Cannot read/write to file");
				return; 
			}
			else {		//parse text file for tables, create new tables, populate tables with data, put new tables in Tables data struct???	
				PrintWriter writer = new PrintWriter(file);
				writer.write('"' + title + '"' +'\t');
				for(int i = 0; i<pKey.size();i++){
					writer.write('"'+String.valueOf(pKey.get(i))+'"' +'\t');
				}
				writer.write('\n');
				for(int i = 0; i<datas.get(0).size(); i++){
					for(int j = 0; j < datas.size(); j++){
							writer.printf("%-15s",'"' + datas.get(j).get(i)+'"');
						
					}
					writer.write("\n");
				}
				writer.close();
			}
				
		}
		catch(IOException ioe){
			System.out.println("Error: Something went wrong");
		}
	}
}