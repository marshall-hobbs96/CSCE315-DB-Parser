import java.util.*;


public class Table{
	public String title;
	public ArrayList<ArrayList<String>> datas;
	public ArrayList<Integer> pKey;
	
	//d needs to be a vector of vectors, [0] is name of vector,
	//[1] is the char limit, null when int
	public Table(String name,ArrayList<ArrayList<String>> d, ArrayList<String> Key){ //d needs to be a vector of vectors
		title = name;
		datas=d;
		ArrayList<Integer> p = new ArrayList<Integer>();
		for(int i = 0; i < Key.size();i++){
			for(int j = 0;j < datas.size();j++){
				if(Key.get(i) == datas.get(j).get(0)){
					p.add(1);
				}
			}
		}
		pKey = p;
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
	
	public Boolean isUnique(ArrayList<String> ele){
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

	public void tablePrint(){
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
		String name = "dogs";
		ArrayList<ArrayList<String>> v = new ArrayList<ArrayList<String>>();
		ArrayList<String> d1 = new ArrayList<String>();
		d1.add("name");
		d1.add(String.valueOf(18));
		ArrayList<String> d2 = new ArrayList<String>();
		d2.add("kind");
		d2.add(String.valueOf(10));
		ArrayList<String> d3 = new ArrayList<String>();
		d3.add("year");
		d3.add(String.valueOf(-1));
		v.add(d1);
		v.add(d2);
		v.add(d3);
		ArrayList<String> key = new ArrayList<String>();
		key.add("name");
		key.add("kind");
		Table test = new Table(name,v,key);
		ArrayList<String> first = new ArrayList<String>();
		first.add("fido");
		first.add("poodle");
		first.add(String.valueOf(12));
		test.addElement(first);
		ArrayList<String> second = new ArrayList<String>();
		second.add("Rex");
		second.add("German Sheperd");
		second.add(String.valueOf(4));
		test.addElement(second);
		test.addElement(second);
		//test.deleteElement(3);
		test.tablePrint();
		
	}
}