import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.io.*;

public class _Main {
	
	public static void main(String[] args) {
//		aufgabe_a();
//		aufgabe_b();
//		aufgabe_c();
		
		int[] gesamt = {0,1,2,3,4};
//		ArrayList<int[]> bla = getKombinations(gesamt, 2);
		
//		for (int aa = 1; aa <= gesamt.length; aa++) { //bis zur maximallänge der kette
//			ArrayList<int[]> kombinationen = getKombinations();
//			
//		}
	}	
	
	public static void check() {
		Graph g = new Graph("[[10], [5, 8], [13], [5], [5, 7], [1, 3, 4, 10, 13], [11, 13], [4], [1], [10, 13], [0, 5, 9], [6, 12], [11], [2, 5, 6, 9]]");
		System.out.println(g.stringCheckGraph(true));
	}
	public static void s() {
		System.out.println();
	}
	
	public static void aufgabe_a() throws IOException {
		File file = new File("Aufgabe_a.txt"); 
		Random r = new Random();
		int n = 0;
		int v = 0;
			 
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			for (int ii = 0; ii < 1000; ii++) {
				// Anzahl der Ecken und Kanten darf nicht 0 sein.
				// Die Anzahl der Kanten drf nicht größer sein, als die Anzahl der max. möglichen Kanten.
				do {
					n = r.nextInt(8);
					v = r.nextInt(7);
				} while( n < 4 || v == 0  || v > (n * (n - 1))/2);
			 		
				System.out.println(" " + n + " Ecken, min " + v + " Kanten.");
				ConnectedGraph g = new ConnectedGraph(n, v);
				System.out.println(g.getAdjacencyList());
				
				writer.write(g.getAdjacencyList());
				writer.newLine();
				writer.write(g.stringCheckGraph(false));
				writer.newLine();			
			}		
			writer.close();
		} catch (IOException e) {
			// TODO Automatisch generierter Erfassungsblock
			e.printStackTrace();
		}
		
		System.out.println("Fertig!");
	}

	public static void aufgabe_b() {
		int sumGraphs = 0;
		Random r = new Random();
		int n = 0;
		int v = 0;
		int s = 0;
		boolean check;
		ConnectedGraph g;
		
		for (int ii = 0; ii < 1500; ii++) {
			do {
				// Anzahl der Ecken und Kanten darf nicht 0 sein.
				// Die Anzahl der Kanten drf nicht größer sein, als die Anzahl der max. mölichen Kanten.
				do {
					n = r.nextInt(20);
					v = r.nextInt(20);
				} while( n < 4 || v == 0  || v > (n * (n - 1))/2);
				do {
					s = r.nextInt(n);
				} while(s == 0);
				
				g = new ConnectedGraph(n, v);
				check = g.checkGraphIsMatchingPerfect(false);
			} while(! check);
			
			Graph c = g.clone();
			
			
			ArrayList<Integer> removed_nodes = g.removeRandomNodes(s);
			g.setComponents();
			
			// Anzahl entfernter Ecken < Anzahl komponenten mit ungrader eckenzahl?
			if (s < g.getNumberOfOddComponents()) {
				System.out.println("Graph:");
				System.out.println(c.getAdjacencyList());
				System.out.println("Entfernte Ecken:");
				System.out.println(Arrays.toString(removed_nodes.toArray()));
				System.out.println("Komponenten:");
				for (Component component : g.components) {
					System.out.println();
					System.out.print("Komponente: " + component.id + " - Ecken ");
					System.out.println(component.getNodeList());
					System.out.println(component.getAdjacencyList());
				}
				System.out.println();
				sumGraphs++;
			}
			
				
		}		
		System.out.println("Anzahl der Kontellationen: " + sumGraphs);
		System.out.println("Fertig!");
	}

	public static void aufgabe_c() {
		
 
		
//		
//		for (int ii = 1; ii < g.nodes.size(); ii++) { // Anzahl der entfernten Ecken;
//			for (int jj = 1; jj <= ii; jj++) {
//				ArrayList<Integer> arr = new ArrayList<Integer>();
//				
//				for (int aa = 0; aa < g.nodes.size(); aa++) {
//					//
//				}
//				
////					arr.add(node.index);
////					System.out.println(node.index);
//			}
////				if(g.checkGraphBreakIntoMoreOddSizedComponentsAsivenRemoteNodes(arr)) {
////					System.out.println(arr.toString());
////				}
//		}		
	}

//	Graph g = new Graph("[[1], [0, 2], [1, 3, 5, 6], [2, 4], [3, 5], [2, 4], [2, 7], [6, 8], [7, 9], [8]]"); // nicht paarungsunperfekt
//	Graph g = new Graph("[[2], [2], [0, 1, 3], [2, 4, 5], [3, 6, 7], [3, 8], [4, 8], [4, 9], [5, 6], [7]]");
}