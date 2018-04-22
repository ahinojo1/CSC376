import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
public class SortManager {
	static String f1 = "//src//10_Random.txt";
	static String f2 = "10_Reverse.txt";
	static String f3 = "10_Sorted.txt";
	static String f4 = "100_Random.txt";
	static String f5 = "100_Reverse.txt";
	static String f6 = "100_Sorted.txt";
	static String f7 = "1000_Random.txt";
	static String f8 = "1000_Reverse.txt";
	static String f9 = "1000_Sorted.txt";
	
	static InsertionSort insertion;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String fileName = f1;
		FileReader file = new FileReader(fileName);
		BufferedReader reader = new BufferedReader(file);
		String line;
		
		ArrayList<Integer> InsertionList = new ArrayList<Integer>();
		ArrayList<Integer> MergeList = new ArrayList<Integer>();
		ArrayList<Integer> QuickList = new ArrayList<Integer>();
		
		while((line = reader.readLine()) != null) {
			InsertionList.add(Integer.parseInt(line));
			MergeList.add(Integer.parseInt(line));
			QuickList.add(Integer.parseInt(line));
		}
		reader.close();
		insertion.sort(InsertionList);
		for (int i = 0; i < InsertionList.size(); i++) {
			System.out.println(InsertionList.get(i));
		}
	}

}
