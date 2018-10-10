import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.io.*;
import java.lang.String; 

public class classParse {
	public static void main(String[] args) {
			//help from https://www.tutorialspoint.com/java/java_filereader_class.htm
			//help from https://stackoverflow.com/questions/5868369/how-to-read-a-large-text-file-line-by-line-using-java
		try {
			BufferedReader reader = new BufferedReader(new FileReader("schedule.txt"));
			String line;
				File output = new File("ACCT.db");
				output.createNewFile();
				FileWriter writer = new FileWriter(output);
				writer.write("ACCT");
				writer.write("\n");
				writer.write("\"CRN\"\t\"Subj\"\t\"Crse\"\t\"Sec\"\t\"Cmp\"\t\"Cred\"\t\"Title\"\t\"Days\"\t\"Time\"\t\"Cap\"\t\"Act\"\t\"Rem\"\t\"Instructor\"\t\"Date\"\t\"Location\"\t\"Attribute\"\n");
				writer.write("\"5\"\t\"4\"\t\"5\"\t\"5\"\t\"2\"\t\"5\"\t\"40\"\t\"20\"\t\"20\"\t\"5\"\t\"5\"\t\"5\"\t\"40\"\t\"20\"\t\"20\"\t\"40\"\n");
			int size = 0;
			int fos = 0;
			//while ((line = reader.readLine()) != null) {
				for (int i = 0; i < 3; i++) {
					line = reader.readLine();
				}
				System.out.println("top done");
				int k = 0;
				while (k < 29149) { //14803
				//while ((line = reader.readLine()) != null) { //15010
					line = reader.readLine();
					if ((!Character.isDigit(line.charAt(1)))&&(line.charAt(1) != '(')&&(line.charAt(1) != ',')&&(line.charAt(1) != ' ')) {
						String name = line.substring(1,5);
						if (name.equals("CRN,")) { 
							//System.out.println("heeeeeere");
							continue;
						}
						System.out.println(name);
						//System.out.println(name.length());
						output = new File(name + ".db");
						output.createNewFile();
						writer.flush();
						writer = new FileWriter(output);
						writer.write("\"");
						writer.write(name);
						writer.write("\"");
						writer.write("\n");
						writer.write("\"CRN\"\t\"Subj\"\t\"Crse\"\t\"Sec\"\t\"Cmp\"\t\"Cred\"\t\"Title\"\t\"Days\"\t\"Time\"\t\"Cap\"\t\"Act\"\t\"Rem\"\t\"Instructor\"\t\"Date\"\t\"Location\"\t\"Attribute\"\n");
						writer.write("\"5\"\t\"4\"\t\"5\"\t\"5\"\t\"2\"\t\"5\"\t\"40\"\t\"20\"\t\"20\"\t\"5\"\t\"5\"\t\"5\"\t\"40\"\t\"20\"\t\"20\"\t\"40\"\n");
						for (int j = 0; j < 2; j++) {
							line = reader.readLine();
							//System.out.println(line);
							size++;
						}
						size++;
					}
					else {
						size++;
						int i = 1;
						if (line.charAt(i) != ' ') {
							int track = 0;
							if (fos == 0) {
								//System.out.println("fos2");
								writer.write("\"");
								int crn = 0;
								while (line.charAt(i) != ',') {
									writer.write(line.charAt(i));
									i++;
									crn++;
								}
								if (crn < 5) { continue; }
								i++;
								writer.write("\"\t");
								//System.out.println("crn");
								writer.write("\"");
								//System.out.println(line.charAt(i));
								while (line.charAt(i) != ',') {
									writer.write(line.charAt(i));
									i++;
								}
								i++;
								writer.write("\"\t");
								//System.out.println("subj");
								writer.write("\"");
								while (line.charAt(i) != ',') {
									writer.write(line.charAt(i));
									i++;
								}
								i++;
								writer.write("\"\t");
								//System.out.println("crse");
								writer.write("\"");
								while (line.charAt(i) != ',') {
									writer.write(line.charAt(i));
									i++;
								}
								i++;
								writer.write("\"\t");
								//System.out.println("sec");
								writer.write("\"");
								while (line.charAt(i) != ',') {
									writer.write(line.charAt(i));
									i++;
								}
								i++;
								writer.write("\"\t");
								//System.out.println("cmp");
								writer.write("\"");
								while (line.charAt(i) != ',') {
									writer.write(line.charAt(i));
									i++;
								}
								i++;
								writer.write("\"\t");
								//System.out.println("cred");
								writer.write("\"");
								while ((line.charAt(i) != ',')&&(line.charAt(i) != '(')) {
									writer.write(line.charAt(i));
									i++;
								}
								i++;
								writer.write("\"\t");
								//System.out.println("title");
								//System.out.println("good");
								fos = 1;
							}
							else if (fos == 1) {
								//System.out.println("fos1");
								writer.write("\"");
								if (line.charAt(i) == '(') {
									//i++;
									//System.out.println("catcha");
									while (line.charAt(i) != ',') {
										//System.out.println(line.charAt(i));
										i++;
									}
									i++;
								}
								while (line.charAt(i) != ',') {
									writer.write(line.charAt(i));
									i++;
								}
								i++;
								writer.write("\"\t");
								//System.out.println("days");
								writer.write("\"");
								//System.out.println(line.charAt(i));
								while (line.charAt(i) != ',') {
									writer.write(line.charAt(i));
									i++;
								}
								i++;
								writer.write("\"\t");
								//System.out.println("time");
								writer.write("\"");
								while (line.charAt(i) != ',') {
									writer.write(line.charAt(i));
									i++;
								}
								i++;
								writer.write("\"\t");
								//System.out.println("cap");
								writer.write("\"");
								while (line.charAt(i) != ',') {
									writer.write(line.charAt(i));
									i++;
								}
								i++;
								writer.write("\"\t");
								//System.out.println("act");
								writer.write("\"");
								while (line.charAt(i) != ',') {
									writer.write(line.charAt(i));
									i++;
								}
								i++;
								writer.write("\"\t");
								//System.out.println("rem");
								writer.write("\"");
								while (line.charAt(i) != ',') {
									writer.write(line.charAt(i));
									i++;
								}
								i++;
								writer.write("\"\t");
								//System.out.println("instructor");
								writer.write("\"");
								while (line.charAt(i) != ',') {
									writer.write(line.charAt(i));
									i++;
								}
								i++;
								writer.write("\"\t");
								//System.out.println("date");
								writer.write("\"");
								while (line.charAt(i) != ',') {
									writer.write(line.charAt(i));
									i++;
								}
								i++;
								writer.write("\"\t");
								//System.out.println("location");
								writer.write("\"");
								while (line.charAt(i) != ','){
									writer.write(line.charAt(i));
									i++;
								}
								i++;
								writer.write("\"\n");
								//System.out.println("attribute");
								//System.out.println("good");
								fos = 0;
							}
							//i++;
							//System.out.println(i);
						}
					}
					k++;
				}
				System.out.println(k);
				writer.flush();
				writer.close();
			
		}
		catch (IOException ex) {
			System.out.println("error");
		}
			//}
			
			//File output = new File("classSchedule.db");
			//output.createNewFile();
			//FileWriter writer = new FileWriter(output);
			//writer.write(content);
			//writer.flush();
			//writer.close();
			
			//HPCH
			//"28341,HPCH,603,697,CS,3,SOC BEHAV DETERMNTS OF HLTH ,,,,,,,,,"
			//"(Restrictions/Details),WEB,WEB,75,2,73,Thomas Tai-Seale (P),08/27-12/12,WEB,Kingsville and Distance Education and NonTraditional Format Approved and 3peat Exclusion,,,,,,"
		}
}