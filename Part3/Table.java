import java.util.*;


public class Table{
	public String tital;
	public ArrayList<ArrayList<String>> datas;
	
	//d needs to be a vector of vectors, [0] is name of vector,
	//[1] is the char limit, null when int
	public Table(String name,ArrayList<ArrayList<String>> d){ //d needs to be a vector of vectors
		tital = name;
		datas=d;
	}
	
	public void addElement(ArrayList<String> ele){ // ele holds the input elements in order
		for(int i = 0;i<ele.size();i++){
			datas.get(i).add(ele.get(i));
		}
	}

	public void tablePrint(){
		System.out.println(tital);
		System.out.println(datas);
	}
	
	public static void main(String[] args) {
		String name = "dog";
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
		test.tablePrint();
	}
}