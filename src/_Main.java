import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.io.*;

public class _Main {
	
	ArrayList<ArrayList<Integer>> bla;
	
	public static void main(String[] args_) {	
		
//		aufgabe_a();
//		aufgabe_b();
//		aufgabe_c();
		aufgabe_d();
		
		space(5);
		System.out.println("Fertig!");
	}
	
	public static void space(int rows_) {
		for (int ii = 1; ii <= rows_; ii++) {
			System.out.println();
		}
	}
	
	public static void aufgabe_a() {

		for (int aa = 0; aa < 1000; aa++) {

			ConnectedGraph g = new ConnectedGraph(8, 5, false);	
			
			System.out.println(g.getAdjacencyList());
			System.out.println(g.stringCheckGraph(false));
			space(2);
		}		
	}
	public static void aufgabe_b() {
		int sumGraphs = 0;
		Random r = new Random();
		int s = 0;
		
		for (int ii = 0; ii < 15; ii++) {
			ConnectedGraph g = new ConnectedGraph(20, 20, true);	
			do {
				s = r.nextInt(20);
			} while(s == 0);
	
			Graph c = g.clone(); //
			ArrayList<Integer> removed_nodes = c.removeRandomNodes(s);
			c.setComponents();
			
			// Anzahl entfernter Ecken < Anzahl komponenten mit ungrader eckenzahl?
			if (s < c.getNumberOfOddComponents()) {
				System.out.println("Graph:");
				System.out.println(g.getAdjacencyList());
				System.out.println("Entfernte Ecken:");
				System.out.println(Arrays.toString(removed_nodes.toArray()));
				System.out.println("Komponenten:");
				for (Component component : c.components) {
					System.out.println();
					System.out.print("Komponente: " + component.id + " - Ecken ");
					System.out.println(component.getNodesList());
					System.out.println(component.getAdjacencyList());
				}
				System.out.println();
				sumGraphs++;
			}
		}		
		System.out.println("Anzahl der Kontellationen: " + sumGraphs);
	}
	public static void aufgabe_c() {
		for (int yy = 1; yy <= 200; yy++) {
			System.out.println("  " + yy + ")  --------------------------------------------------");
			space(1);
			
			ConnectedGraph g = new ConnectedGraph(20, 20, true);		
			System.out.println(g.getAdjacencyList());
						
			ArrayList<ArrayList<Integer>> allCombinations_S = new ArrayList<ArrayList<Integer>>();
			ArrayList<Integer> nodeIndexes = new ArrayList<Integer>();
			nodeIndexes = g.getSortedNodesList(); 
			
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
						
						System.out.println(Arrays.toString(arr));
					}
				}
			}
			space(2);	
		}
	}
	public static void aufgabe_d() {
		for (int yy = 1; yy <= 2; yy++) {
			System.out.println("  " + yy + ")  --------------------------------------------------");
		
			ConnectedGraph g = new ConnectedGraph(35, 20, true);
						
			System.out.println(g.getAdjacencyList());
			
			space(1);
			ArrayList<ArrayList<Integer>> allCombinations_S = new ArrayList<ArrayList<Integer>>();
			ArrayList<Integer> nodeIndexes = new ArrayList<Integer>();
			nodeIndexes = g.getSortedNodesList(); 
			
			for (int aa = 1; aa < 2; aa++) {
				System.out.println("Länge: " + aa);
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
			space(2);	
		}
	}
}