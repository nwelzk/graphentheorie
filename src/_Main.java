
public class _Main {

	public static void main(String[] args) {
		
		//Graph zufaelligerGraph = new Graph("[[2, 4], [2], [0, 1, 3], [2, 4, 5], [0, 3], [3]]");
		
		//[[0, 1], [0, 2], [1, 0], [1, 2], [2, 0], [2, 1], [2, 3], [2, 6], [3, 2], [4, 5], [4, 6], [5, 4], [5, 7], [6, 2], [6, 4], [6, 7], [7, 5], [7, 6]]


		//int[][] ma = {{0, 0, 1, 0, 1, 0}, {0, 0, 1, 0, 0, 0}, {1, 1, 0, 1, 0, 0}, {0, 0, 1, 0, 1, 1}, {1, 0, 0, 1, 0, 0}, {0, 0, 0, 1, 0, 0}};
		//Graph zufaelligerGraph2 = new Graph(ma);
	
		Graph zg = new ConnectedGraph(8, 18);
		zg.printAdjacencyList();		
		
		System.out.println();
		System.out.println(zg.edges_count);
		
	}
}