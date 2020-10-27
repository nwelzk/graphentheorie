
public class _Main {

	public static void main(String[] args) {
		
		
		Graph g = new Graph("[[1], [0, 2], [1, 3, 5, 6], [2, 4], [3, 5], [2, 4], [2, 7], [6, 8], [7, 9], [8]]");
		
		g.printAdjacencyMatrix();
		s();
		s();
		
		
		g.printCheckGraph(true);
	}
	
	private static void s() {
		System.out.println();
	}
	
}