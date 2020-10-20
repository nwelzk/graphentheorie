
public class Main {

	public static void main(String[] args) {
		
		// Creating a random graph with a given amount of nodes.
		// nodes, probability
		Graph zufaelligerGraph = new Graph(4, 60);
		
		System.out.println();
		zufaelligerGraph.printNodeList();
		
		System.out.println();
		System.out.println();
		zufaelligerGraph.printRelatedNodeList();
		
		System.out.println();
		System.out.println();
		zufaelligerGraph.printNodeNames();
		zufaelligerGraph.print();	
		
		System.out.println();
		System.out.println();
		zufaelligerGraph.printAsArray();
		
		// Creating a graph with given relations.
		// int[][] inhalt2 = {{0,1,0,1,0}, {1,0,1,1,1}, {0,1,0,0,1}, {1,1,0,0,1}, {0,1,1,1,0}};	
		// Graph testgraph = new Graph(inhalt2);
		
		// Prints a graph into console.
		// testgraph.print();
		
		// Show if a graph is a imperfect matching graph.
		// System.out.println(testgraph.check());
		
		// Prints the amount of nodes into console.
		// System.out.println(testgraph.getNodeCount());
		
	}
}