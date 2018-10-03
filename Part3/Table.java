import java.util.*;


public class Table{
	public String title;
	public ArrayList<ArrayList<String>> datas;
	public ArrayList<Integer> pKey;
	
	//d needs to be a vector of vectors, [0] is name of vector,
	//[1] is the char limit, null when int
	public Table(String name,ArrayList<ArrayList<String>> d, ArrayList<String> pKey){ //d needs to be a vector of vectors
		title = name;
		datas=d;
		//add primary key
		
	}
	public String getName(){
		return title;
	}
	
	public void addElement(ArrayList<String> ele){ // ele holds the input elements in order
		//check for uniqueness
		
		for(int i = 0;i<ele.size();i++){
			datas.get(i).add(ele.get(i));
		}
	}

	public void tablePrint(){
		System.out.println(title);
		System.out.println(datas);
	}
	
	//VERY IMPORTANT: if you are removing multiple elements from the list delete from bottom up so 6 to 0 not 0 to 6
	public void deleteElement(int index){
		if(index<datas.get(0).size() && index>1){
			for(int i = 0; i<datas.size();i++){
				datas.get(i).remove(index);
			}
		}
		else{
			System.out.println("Error: tried to remove value out of index");
		}
	}
	
	public ArrayList<Integer> searchColumn(String colHead, String searchFor){
		ArrayList<Integer> indexes = new ArrayList<Integer>();
		for(int i = 0; i<datas.size();i++){
			if(datas.get(i).get(0).equals(colHead)){
				for(int j = 2; j<datas.get(i).size();j++){
					if(datas.get(i).get(j).equals(searchFor)){
						indexes.add(j);
					}
				}
				return indexes;
			}
		}
		return indexes;
	}
	
	public static void main(String[] args) {
	/*	String name = "dog";
		ArrayList<ArrayList<String>> v = new ArrayList<ArrayList<String>>();
		ArrayList<String> d1 = new ArrayList<String>();
		d1.add("name");
		d1.add(String.valueOf(18));
		ArrayList<String> d2 = new ArrayList<String>();
		d2.add("kind");
		d2.add("null");
		v.add(d1);
		v.add(d2);
		Table test = new Table(name,v);
		ArrayList<String> first = new ArrayList<String>();
		first.add("fido");
		first.add("poodle");
		test.addElement(first);
		ArrayList<String> second = new ArrayList<String>();
		second.add("Rex");
		second.add("German Sheperd");
		test.addElement(second);
		test.addElement(second);
		//test.deleteElement(3);
		System.out.println(test.searchColumn("name","ex"));
		test.tablePrint();
		*/
	}
}