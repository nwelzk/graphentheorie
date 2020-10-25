
public class _Main {

	public static void main(String[] args) {
		
//		Graph zufaelligerGraph = new Graph("[[2, 4], [2], [0, 1, 3], [2, 4, 5], [0, 3], [3]]");
//	
//		int[][] ma = {{0, 0, 1, 0, 1, 0}, {0, 0, 1, 0, 0, 0}, {1, 1, 0, 1, 0, 0}, {0, 0, 1, 0, 1, 1}, {1, 0, 0, 1, 0, 0}, {0, 0, 0, 1, 0, 0}};
//		Graph zufaelligerGraph2 = new Graph(ma);
	
		Graph zg = new ConnectedGraph(9, 18);
		
//		zg.printNodeNameList();
//		System.out.println();
//		
//		zg.printNodeList();
//		System.out.println();
//		
		zg.printEdgeList();
		System.out.println();
		
		zg.printAdjacencyList();	
		System.out.println();
//		
//		zg.printAdjacencyMatrix();
//		System.out.println();
//		
//		zg.printCheckGraph();
//		System.out.println();
//		
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		zg.printArray();
		
	}
}