import java.util.ArrayList;
import java.util.Random;

public class ConnectedGraph extends Graph {

	
	public ConnectedGraph(int max_nodes_count_, int min_edges_combined_, boolean checkIfNotMatchingPerfect_) {
		super();
		
		Random r = new Random();
		int n;
		int v;
		boolean check = true;
		
		do {
			this.nodes = new ArrayList<Node>();
			this.edges = new ArrayList<Relation>();
			
			do {
				n = r.nextInt(max_nodes_count_);
				v = r.nextInt(min_edges_combined_);
				
				// Anzahl der Ecken und Kanten darf nicht 0 sein.
				// Die Anzahl der Kanten drf nicht größer sein, als die Anzahl der max. möglichen Kanten.
			} while( n < 4 || v == 0  || v > (n * (n - 1))/2);

			for (int ii = 0; ii < n; ii++) {
				this.nodes.add(new Node(ii));
			}
			this.addRelations(v);
			for (Node node : nodes) {
				if (node.getVertexDegree() == 0) {
					this.createUndirectedRelation(node, this.getRandomConnectedNode(node.index));
				}
			}
			
			if(checkIfNotMatchingPerfect_) {
				check = this.checkGraphIsMatchingPerfect(false);
			}
			
			
		} while(checkIfNotMatchingPerfect_ == true && check == false );

	}
	
	public ConnectedGraph(int nodes_count_, int min_edges_count_) {
		super(nodes_count_);
		
		this.addRelations(min_edges_count_);
		for (Node node : nodes) {
			if (node.getVertexDegree() == 0) {
				this.createUndirectedRelation(node, this.getRandomConnectedNode(node.index));
			}
		}
	}	
	public void addRelations(int edges_count_) {
		Node pointer = this.getRandomNode();
		int aim = this.edges.size() + edges_count_;
		while (this.edges.size() < aim) {
			Node receiver = this.getRandomNode(pointer.index);
			this.createUndirectedRelation(pointer, receiver);
			pointer = receiver;
		}
	}
	public void addRelations(Node pointer_, int edges_count_) {
		Node pointer = pointer_;
		int aim = this.edges.size() + edges_count_;
		while (this.edges.size() < aim) {
			Node receiver = this.getRandomNode(pointer.index);
			this.createUndirectedRelation(pointer, receiver);
			pointer = receiver;
		}
	}
	
}
