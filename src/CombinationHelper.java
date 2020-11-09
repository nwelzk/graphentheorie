import java.util.ArrayList;
import java.util.Arrays;

public class CombinationHelper {
	
	public ArrayList<ArrayList<Integer>> combinations;
	private int laenge;
	
	private Integer[] nodes;
	
	public CombinationHelper(int laenge, Integer[] indexArr) {
//		aufgabe_a();
//		aufgabe_b();
//		aufgabe_c();
	
		this.laenge = laenge;
		this.nodes = indexArr;
		this.combinations = new ArrayList<ArrayList<Integer>>();
		// Füllt eine Liste mit Listen, in denen der Wurzelknoten für den baum steckt.
		
		if (laenge == 1) {
			for (Integer ii : indexArr) {
				ArrayList<Integer> p = new ArrayList<Integer>();
				p.add(ii);
				this.combinations.add(p);
			}
		}else {
			for (int node : indexArr) {
				ArrayList<Integer> start = new ArrayList<Integer>();
				start.add(node);
				this.routine(start);
			}
		}

	}
	
	public void routine(ArrayList<Integer> start) {
		
		for (int node : this.nodes) {	
			if (start.get(start.size()-1) < node) {
				ArrayList<Integer> newList = new ArrayList<Integer>();
				newList.addAll(start);
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
	
//	public ArrayList<Node[]> proceedCombinations(int currentLevel, int maxLevel, ArrayList<Node> nodes, ArrayList<Node[]> lists) {
//		ArrayList<Node[]> newLists = new ArrayList<Node[]>();
//		
//		if(currentLevel >= maxLevel) {
//			return lists;
//		}
//		
//		for (Node[] nodesArray : lists) {
//			
//		}
//		ArrayList<Node[]> newLists = proceedCombinations(currentLevel + 1, maxLevel, nodes, lists);
//		
//		
//	}
//	
//	
//	public void proceed(Node[] s) {
//		if(this.graph.checkGraphBreakIntoMoreOddSizedComponentsAsGivenRemoteNodes(s)) {
//			this.inclMinRemovedNodes.add(s);
//			
//			for (Node n : s) {
//				if (this.nodes.contains(n)) {
//					this.nodes.remove(n);
//				}
//			}
//		}
//	}
//	public void proceed(Node node) {
//		Node[] c = new Node[1];
//		c[0] = node;
//		this.proceed(c);
//	}
