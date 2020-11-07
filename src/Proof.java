import java.util.ArrayList;

public class Proof {
	public Graph graph;
	
	public ArrayList<Node> nodes;
	public ArrayList<Node> checkNodes;
	
	public ArrayList<Node[]> inclMinRemovedNodes;
	
	public Proof(Graph g) {
		this.graph = new Graph();
		this.nodes = new ArrayList<Node>();
		this.checkNodes = new ArrayList<Node>();
		this.inclMinRemovedNodes= new ArrayList<Node[]>(); 
		
		this.graph = g.clone();
		this.nodes.addAll(graph.nodes);
		this.checkNodes.addAll(this.nodes);
		
		// Alle Einzelknoten	
		for (Node node : this.checkNodes) {
			this.proceed(node);
		}
		
		this.checkNodes.clear();
		this.checkNodes.addAll(this.nodes);
		
	}
	
	
	public void proceed(Node[] s) {
		if(this.graph.checkGraphBreakIntoMoreOddSizedComponentsAsGivenRemoteNodes(s)) {
			this.inclMinRemovedNodes.add(s);
			
			for (Node n : s) {
				if (this.nodes.contains(n)) {
					this.nodes.remove(n);
				}
			}
		}
	}
	public void proceed(Node node) {
		Node[] c = new Node[1];
		c[0] = node;
		this.proceed(c);
	}
	
}