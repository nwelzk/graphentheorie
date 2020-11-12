import java.util.ArrayList;
import java.util.Arrays;

public class CombinationHelper {
	
	public ArrayList<ArrayList<Integer>> combinations;
	private int laenge;
	
	private Integer[] nodes;
	
	public CombinationHelper(int laenge_, Integer[] indexArr_) {
//		aufgabe_a();
//		aufgabe_b();
//		aufgabe_c();
	
		this.laenge = laenge_;
		this.nodes = indexArr_;
		this.combinations = new ArrayList<ArrayList<Integer>>();
		// Füllt eine Liste mit Listen, in denen der Wurzelknoten für den baum steckt.
		
		if (laenge_ == 1) {
			for (Integer ii : indexArr_) {
				ArrayList<Integer> p = new ArrayList<Integer>();
				p.add(ii);
				this.combinations.add(p);
			}
		}else {
			for (int node : indexArr_) {
				ArrayList<Integer> start = new ArrayList<Integer>();
				start.add(node);
				this.routine(start);
			}
		}

	}
	
	public void routine(ArrayList<Integer> start_) {
		
		for (int node : this.nodes) {	
			if (start_.get(start_.size()-1) < node) {
				ArrayList<Integer> newList = new ArrayList<Integer>();
				newList.addAll(start_);
				// Reihe ist aufsteigend
				newList.add(node);
				if (newList.size() == this.laenge) {
					if (! this.combinations.contains(newList)) {
						this.combinations.add(newList);
					}
				}
				else {
					this.routine(newList);
				}
			}
		}
	}
	public void printCombinations() {
		for (ArrayList<Integer> arrayList : this.combinations) {
			System.out.println(Arrays.toString(arrayList.toArray()));
		}
	}
	
}
