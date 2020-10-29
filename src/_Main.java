import java.util.ArrayList;

public class _Main {

	public static void main(String[] args) {
		

		
		
		Graph g = new Graph("[[2], [2], [0, 1, 3], [2, 4, 5], [3, 6, 7], [3, 8], [4, 8], [4, 9], [5, 6], [7]]");
		g.printAdjacencyList();
		g.removeNode(3);
		g.removeNode(4);
		g.printAdjacencyList();
		
		g.setComponents();
		
		System.out.println("Anzahl der Komponenten: " + g.components.size());
		
		for (Component c : g.components) {
			if(c.nodes.size() % 2 == 1) {
				// Ungrade anzahl an knoten
			}
		}
		
		// Graph Aufgabe b: Anzahl entfernter Ecken < Anzahl komponenten mit ungrader eckenzahl?
		
//		g.printNodeList();
//		g.printNodeNameList();
//		s();
//		g.printAdjacencyList();
//		s();
		
	}
	
	public void aufgaba_a() {
//		Graph g = new Graph("[[1], [0, 2], [1, 3, 5, 6], [2, 4], [3, 5], [2, 4], [2, 7], [6, 8], [7, 9], [8]]"); // nicht paarungsunperfekt
		Graph g = new Graph("[[2], [2], [0, 1, 3], [2, 4, 5], [3, 6, 7], [3, 8], [4, 8], [4, 9], [5, 6], [7]]");
		g.printCheckGraph(true);
		
		s();
		s();
		g.printAdjacencyList();
		s();
		g.removeNode(3);
		g.removeNode(4);
//		ArrayList<Integer> removed_indexes = g.removeRandomNodes(2);
//		
//		System.out.println("Entfernt: ");
//		for (Integer index : removed_indexes) {
//			System.out.println(index);
//		}
		s();
		
		g.printNodeList();
		g.printNodeNameList();
		s();
		g.printAdjacencyList();
		s();
		
	}
		
	private static void s() {
		System.out.println();
	}
}