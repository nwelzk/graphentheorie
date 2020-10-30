import java.util.Random;
import java.io.*;

public class _Main {
	
	public static void main(String[] args) {
	
		try {
			aufgabe_a();
		} catch (IOException e) {
			// TODO Automatisch generierter Erfassungsblock
			e.printStackTrace();
		}
//		check();
//		Graph g = new Graph("[[2], [2], [0, 1, 3], [2, 4, 5], [3, 6, 7], [3, 8], [4, 8], [4, 9], [5, 6], [7]]");
//		g.printAdjacencyList();
//		g.removeNode(3);
//		g.removeNode(4);
//		g.printAdjacencyList();
//		
//		g.setComponents();
//		
//		System.out.println("Anzahl der Komponenten: " + g.components.size());
//		
//		for (Component c : g.components) {
//			if(c.nodes.size() % 2 == 1) {
//				// Ungrade anzahl an knoten
//			}
//		}
		
		// Graph Aufgabe b: Anzahl entfernter Ecken < Anzahl komponenten mit ungrader eckenzahl?
//		Graph g = new Graph("[[1], [0, 2], [1, 3, 5, 6], [2, 4], [3, 5], [2, 4], [2, 7], [6, 8], [7, 9], [8]]"); // nicht paarungsunperfekt
//		Graph g = new Graph("[[2], [2], [0, 1, 3], [2, 4, 5], [3, 6, 7], [3, 8], [4, 8], [4, 9], [5, 6], [7]]");
	}
	
	public static void check() {
		Graph g = new Graph("[[10], [5, 8], [13], [5], [5, 7], [1, 3, 4, 10, 13], [11, 13], [4], [1], [10, 13], [0, 5, 9], [6, 12], [11], [2, 5, 6, 9]]");
		System.out.println(g.stringCheckGraph(true));
	}
	
	public static void aufgabe_a() throws IOException {
		File file = new File("Aufgabe_a.txt"); 
		Random r = new Random();
		int n = 0;
		int v = 0;
			 
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		for (int ii = 0; ii < 1000; ii++) {
			// Anzahl der Ecken und Kanten darf nicht 0 sein.
			// Die Anzahl der Kanten drf nicht gr��er sein, als die Anzahl der max. m�glichen Kanten.
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
		System.out.println("Fertig!");
	}

	public static void aufgabe_b() {
		Random r = new Random();
		int n = 0;
		int v = 0;
		int s = 0;
		boolean check;
		ConnectedGraph g;
		
		for (int ii = 0; ii < 5; ii++) {
			do {
				// Anzahl der Ecken und Kanten darf nicht 0 sein.
				// Die Anzahl der Kanten drf nicht gr��er sein, als die Anzahl der max. m�glichen Kanten.
				// Die Anzahl der Kanten drf nicht gr��er sein, als die Anzahl der max. m�glichen Kanten.
				do {
					n = r.nextInt(12);
					s = r.nextInt(n);
					v = r.nextInt(7);
				} while( n < 4 || v == 0  || v > (n * (n - 1))/2);
				g = new ConnectedGraph(n, v);
				check = g.checkGraph(false);
			} while(! check);
			
			System.out.println(g.getAdjacencyList());
			g.removeRandomNodes(s);
			g.setComponents();
			
			int compCount = g.components.size();
			g.
			
				
		}		
		
		System.out.println("Fertig!");
	}
}