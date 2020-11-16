import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class _Main {
	
	ArrayList<ArrayList<Integer>> bla;
	
	public static void main(String[] args_) {
		
		for (int aa = 1; aa <= 10; aa++) {
//			aufgabe_a(8, 5);
//			aufgabe_b(20, 20, 20);
//			aufgabe_c(20, 20);
			aufgabe_d(45, 30);
		
			space(2);
		}
		System.out.println("Fertig!");
	}

	public static void aufgabe_a(int max_n_, int min_v_) {
		ConnectedGraph g = new ConnectedGraph(max_n_, min_v_, false);		
		System.out.println(g.getAdjacencyList());
		System.out.println(g.stringCheckGraph(false));	
	}
	public static void aufgabe_b(int max_n_, int min_v_, int max_s_) {
		int sumGraphs = 0;
		Random r = new Random();
		int s = 0;
		
		ConnectedGraph g = new ConnectedGraph(max_n_, min_v_, true);	
		Graph clone = g.clone();
		
		do {
			s = r.nextInt(max_s_);
		} while(s == 0);

		ArrayList<Integer> removed_nodes = g.removeRandomNodes(s);
		g.setComponents();
		
		// Anzahl entfernter Ecken < Anzahl komponenten mit ungrader eckenzahl?
		if (s < g.getNumberOfOddComponents()) {
			System.out.println("Graph:");
			System.out.println(clone.getAdjacencyList());
			System.out.println("Entfernte Ecken:");
			System.out.println(Arrays.toString(removed_nodes.toArray()));
			System.out.println("Komponenten:");
			for (Component component : g.components) {
				System.out.println();
				System.out.print("Komponente: " + component.id + " - Ecken ");
				System.out.println(component.getNodesList());
				System.out.println(component.getAdjacencyList());
			}
			System.out.println();
			sumGraphs++;
		}		
		System.out.println("Anzahl der Kontellationen: " + sumGraphs);
	}
	public static void aufgabe_c(int max_n_, int min_v_) {
		ConnectedGraph g = new ConnectedGraph(max_n_, min_v_, true);
		System.out.println("Graph:");
		System.out.println(g.getAdjacencyList());
		space(1);
		
		ArrayList<ArrayList<Integer>> allCombinations_S = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> nodeIndexes = new ArrayList<Integer>();
		nodeIndexes = g.getSortedNodesList(); 
		
		System.out.println("Ecken, die den Graph in mehr Komponenten mit ungrader Eckenzahl teilen, als Ecken entfernt wurden:");
		for (int ii = 1; ii <= 2; ii++) {
			Integer[] indexArr = new Integer[nodeIndexes.size()]; 
			indexArr = nodeIndexes.toArray(indexArr); 
			CombinationHelper ch = new CombinationHelper(ii, indexArr);

			for (ArrayList<Integer> combination : ch.combinations) {
				Integer[] arr = new Integer[combination.size()]; 
		        arr = combination.toArray(arr);   
		        Graph copiedGraph = new Graph();
		        copiedGraph = g.clone();
		        
		        if(copiedGraph.checkGraphBreakIntoMoreOddSizedComponentsAsGivenRemoteNodes(arr)) {
					allCombinations_S.add(combination);
					nodeIndexes.removeAll(combination);	
					System.out.println(Arrays.toString(arr));
				}
			}
		}
	}
	public static void aufgabe_d(int max_n_, int min_v_) {
		ConnectedGraph g = new ConnectedGraph(max_n_, min_v_, true);
		System.out.println("Graph:");
		System.out.println(g.getAdjacencyList());
		space(1);
	
		ArrayList<ArrayList<Integer>> allCombinations_S = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> nodeIndexes = new ArrayList<Integer>();
		nodeIndexes = g.getSortedNodesList(); 
		
		System.out.println("Details:");
		for (int aa = 1; aa <= 2; aa++) {
			Integer[] indexArr = new Integer[nodeIndexes.size()]; 
			indexArr = nodeIndexes.toArray(indexArr); 
			CombinationHelper ch = new CombinationHelper(aa, indexArr);

			for (ArrayList<Integer> combination : ch.combinations) {				
				Integer[] arr = new Integer[combination.size()]; 
		        arr = combination.toArray(arr); 
		        Graph copiedGraph = new Graph();
		        copiedGraph = g.clone();
		        
		        if(copiedGraph.checkGraphBreakIntoMoreOddSizedComponentsAsGivenRemoteNodes(arr)) {
					allCombinations_S.add(combination);
					nodeIndexes.removeAll(combination);
					System.out.println(Arrays.toString(arr) + " => benachbarte Komponenten: " + copiedGraph.components.size() + " davon ungrade: " + copiedGraph.getNumberOfOddComponents());
		        }
			}
		}
	}
	public static void space(int rows_) {
		for (int ii = 1; ii <= rows_; ii++) {
			System.out.println();
		}
	}
}