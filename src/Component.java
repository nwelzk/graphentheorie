import java.util.ArrayList;

public class Component extends Graph {

	public int id;
	public ArrayList<Node>	nodes;		// Liste aller Ecken der Komponente.
	public ArrayList<Relation>	edges;	// Liste aller Kanten.
	
	public Component(int id_, Node start_node_) {
		super();		
		this.id = id_;		
		this.expand(start_node_);
	}
	
	public void expand(Node start_node_) {	
		if (! this.nodes.contains(start_node_)) { 
			this.nodes.add(start_node_);
			ArrayList<Node>	neighbors = start_node_.neighbors;
			for (Node neighbor : neighbors) {
				this.edges.add(new Relation(start_node_, neighbor, true));
				this.expand(neighbor);
			}
		}
	}
}