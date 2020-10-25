
public class _Main {

	public static void main(String[] args) {
		
		//Graph mit Adjazenzlisteerstellen.
//		Graph zufaelligerGraph = new Graph("[[2, 4], [2], [0, 1, 3], [2, 4, 5], [0, 3], [3]]");

		
		// Graph mit Array (alt. Darstellung der Adjazenzmatrix) erstellen.
		int[][] ma = {{0, 1, 1, 0, 0, 0, 0, 0}, {1, 0, 0, 0, 0, 0, 0, 0}, {1, 0, 0, 1, 0, 1, 1, 0}, {0, 0, 1, 0, 1, 0, 0, 0}, {0, 0, 0, 1, 0, 0, 0, 0}, {0, 0, 1, 0, 0, 0, 0, 0}, {0, 0, 1, 0, 0, 0, 0, 1}, {0, 0, 0, 0, 0, 0, 1, 0}};
		Graph zg2 = new Graph(ma);
		zg2.printEdgeList();
		System.out.println();
		System.out.println();

		//Erstellt n zufällige Graphen und gibt sie aus, wenn sie paarungsunperfekt sind.
		for (int ii = 0; ii < 1000; ii++) {
			Graph zg = new ConnectedGraph(8, 5);
			
			if (zg.checkGraph()) {
				zg.printArray();
				zg.clone.printArray();
			}
		}
		
		
		
		
		
//		zg.printNodeNameList();
//		System.out.println();
//		
//		zg.printNodeList();
//		System.out.println();
//		
//		zg.printEdgeList();
//		System.out.println();
//		
//		zg.printAdjacencyList();	
//		System.out.println();
//		
//		zg.printAdjacencyMatrix();
//		System.out.println();
//		
//		zg.printCheckGraph();
//		System.out.println();
//			
	}
}