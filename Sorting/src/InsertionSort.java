import java.util.ArrayList;

public class InsertionSort implements SortStrategy{
	@Override
	public void sort(ArrayList<Integer> a) {
		for (int i = 1; i < a.size(); i++) {
			int x = a.get(i);
			int j = i;
			while(j > 0 && a.get(j-1) > x) {
				a.set(j, a.get(j-1));
				j--;
			}
			a.set(j,x);
		}
	}

}
