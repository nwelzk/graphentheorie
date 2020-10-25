
public class ConnectedGraph extends Graph {

	
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
		int aim = this.edges_count + edges_count_;
		while (this.edges_count < aim) {
			Node receiver = this.getRandomNode(pointer.index);
			this.createUndirectedRelation(pointer, receiver);
			pointer = receiver;
		}
	}
	public void addRelations(Node pointer_, int edges_count_) {
		Node pointer = pointer_;
		int aim = this.edges_count + edges_count_;
		while (this.edges_count < aim) {
			Node receiver = this.getRandomNode(pointer.index);
			this.createUndirectedRelation(pointer, receiver);
			pointer = receiver;
		}
	}
}
