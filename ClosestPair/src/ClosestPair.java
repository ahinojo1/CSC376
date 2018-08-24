import java.util.ArrayList;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.Math;

public class ClosestPair {
	static ArrayList<Point> S = new ArrayList<Point>();
	static ArrayList<Point> X = new ArrayList<Point>();
	static ArrayList<Point> Y = new ArrayList<Point>();
	
	static String fileName1 = "\\src\10points.txt";
	static String fileName2 = "\\src\100points.txt";
	static String fileName3 = "\\src\1000points.txt";
	
	public static Pair findClosestPair(ArrayList<Point> S, ArrayList<Point> X, ArrayList<Point> Y) {
		parseInputFile(fileName1);
		parseInputFile(fileName2);
		parseInputFile(fileName3);
		//sortByX();
		//sortByY();
		int l = X.get(X.size()/2).x;//getMid();
		if (S.size() <= 3) {
			float minDist = distance(S.get(0),S.get(1)); //distance between point1 and point2
			Pair minPair = new Pair(S.get(0), S.get(1));
			for (int i = 0; i < S.size(); i++) {
				for (int j = i; j < S.size(); j++) {
					if (distance(S.get(i),S.get(j)) < minDist) {
						minDist = distance(S.get(i),S.get(j));
						minPair = new Pair(S.get(i),S.get(j));
						
					}
				}
			}
			return minPair;
		}
		return null;
		
	}
	
	
	public static float distance(Point p1, Point p2) {
		float d = (float) Math.sqrt(Math.pow((p2.x - p1.x), 2) + Math.pow(p2.y - p1.y, 2));
		return d;
	}
	
	public static void parseInputFile(String fileName) throws IOException {
		File file = new File(fileName);
		try {
			FileInputStream reader = new FileInputStream(file);
			
			reader.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		findClosestPair(S, X, Y);
		
	}
}
