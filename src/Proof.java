import java.util.ArrayList;
import java.util.Arrays;

public class Proof {
	
	public ArrayList<ArrayList<Integer>> bla;
	private int laenge;
	
	public Proof(int laenge) {
//		aufgabe_a();
//		aufgabe_b();
//		aufgabe_c();
		
		this.laenge = laenge;
		this.bla = new ArrayList<ArrayList<Integer>>();
		// Füllt eine Liste mit Listen, in denen der Wurzelknoten für den baum steckt.
		
		int[] gesamt = {0,1,2,3};
		
		for (int node : gesamt) {
			ArrayList<Integer> start = new ArrayList<Integer>();
			start.add(node);
			this.routine(gesamt, start);
		}

	}	
	
	public void routine(int[] gesamt, ArrayList<Integer> start) {
		
		for (int node : gesamt) {	
			if (start.get(start.size()-1) < node) {
				ArrayList<Integer> newList = new ArrayList<Integer>();
				newList.addAll(start);
				// Reihe ist aufsteigend
				newList.add(node);
				if (newList.size() == this.laenge) {
					if (! this.bla.contains(newList)) {
						this.bla.add(newList);
					}
				}
				else {
					this.routine(gesamt, newList);
				}
			}
		}
	}
	public void printBla() {
		for (ArrayList<Integer> arrayList : this.bla) {
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
