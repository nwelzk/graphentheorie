import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Graph implements Cloneable{
	
	public int nodes_count;
	public int edges_count;
	
	public ArrayList<Node>	nodes;		// Liste aller Ecken.
	public ArrayList<Relation>	edges;	// Liste aller Kanten.
	public Graph clone;
	
	public Graph() {
		
	}
	public Graph(int nodes_count_) {
		this.nodes_count = nodes_count_;
		this.edges_count = 0;
		this.nodes = new ArrayList<Node>();
		this.edges = new ArrayList<Relation>();
		for (int ii = 0; ii < this.nodes_count; ii++) {
			nodes.add(new Node(ii));
		}
	}
	public Graph(int nodes_count_, int propability_) {
		Random r = new Random();
		this.nodes_count = nodes_count_;
		this.nodes = new ArrayList<Node>();
		this.edges = new ArrayList<Relation>();
		for (int ii = 0; ii < this.nodes_count; ii++) {
			nodes.add(new Node(ii));
		}
		for (Node pointer : nodes) {
			for (Node receiver : nodes) {
				if(pointer == receiver) {
					continue;
				}
				int p = r.nextInt(200);
				if(p <= propability_) {
					this.createUndirectedRelation(pointer, receiver);
				}
			}
		}
	}
	public Graph(int[][] adjacency_matrix_) {
		this.nodes_count = adjacency_matrix_.length;
		this.edges_count = 0;
		this.nodes = new ArrayList<Node>();
		this.edges = new ArrayList<Relation>();
		for (int vv = 0; vv < this.nodes_count; vv++) {
			nodes.add(new Node(vv));
		}
		for (int jj = 0; jj < this.nodes_count; jj++) {
			for (int ii = 0; ii < this.nodes_count; ii++) {
				if(adjacency_matrix_[jj][ii] == 1) {
					this.createDirectedRelation(this.nodes.get(ii), this.nodes.get(jj));
				}
			}
		}
	}
	public Graph(String adjacency_list_) {
		this.nodes = new ArrayList<Node>();
		this.edges = new ArrayList<Relation>();
		
		String[] parsedString = adjacency_list_.replaceAll("]", "").split(", \\[");
		for (int vv = 0; vv < parsedString.length; vv++) {
			nodes.add(new Node(vv));
		}
		this.nodes_count = parsedString.length;
		this.edges_count = 0;
		
		for (int ii = 0; ii < parsedString.length; ii++) {
			String[] values = parsedString[ii].replaceAll("\\[", "").split(", ");
			for (String value : values) {
				this.createDirectedRelation(this.nodes.get(ii), this.nodes.get(Integer.parseInt(value)));
			}
		}
	}	
		
	@Override
	public Graph clone() {
		try {
			Graph new_graph = (Graph) super.clone();
			
			ArrayList<Node> new_nodes= new ArrayList<Node>();
			for (Node item : this.nodes) new_nodes.add(item);
			ArrayList<Relation>	new_edges = new ArrayList<Relation>();
			for (Relation item : this.edges) new_edges.add(item);
			
			new_graph.nodes = new_nodes;
			new_graph.edges = new_edges;
			
			return new_graph;
			
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void createRandomRelations(int relations_count_) {
		for (int ii = 0; ii < relations_count_; ii++) {
			Node pointer = this.getRandomNode();
			Node receiver = this.getRandomNode(pointer.index);
			this.createUndirectedRelation(pointer, receiver);
		}
	}
	protected void createUndirectedRelation(Node node_left_, Node node_right_) {
		Relation r1 = node_left_.relateEdge(node_right_);
		Relation r2 = node_right_.relateEdge(node_left_);
		if(r1 != null) { 
			this.edges.add(r1);
		}
		if(r2 != null) {
			this.edges.add(r2);
		}
		this.edges_count = this.edges.size();
	}
	protected void createDirectedRelation(Node node_left_, Node node_right_) {
		Relation r = node_left_.relateEdge(node_right_);
		if(r != null) { 
			this.edges.add(r);
		}		
		this.edges_count = this.edges.size();
	}	
	protected Node getRandomNode() {
		Random r = new Random();
		return this.nodes.get(r.nextInt(this.nodes_count));
	}
	protected Node getRandomNode(int exclusion_) {
		Random r = new Random();
		int index = exclusion_;
		while(index == exclusion_) {
			index = r.nextInt(this.nodes_count);
		}		
		return this.nodes.get(index);
	}
	protected int[][] createAdjacencyMatrix() {
		int[][] matrix = new int[this.nodes_count][this.nodes_count];
		
		for (int jj = 0; jj < this.nodes_count; jj++) {
			int[] arr = new int[this.nodes_count];
			Node currentNode = this.nodes.get(jj);
			for (int ii = 0; ii < this.nodes_count; ii++) {
				if(currentNode.hasNeighbor(ii)) {
					arr[ii] = 1;
				}else {
					arr[ii] = 0;
				}
			}
			matrix[jj] = arr;			
		} 
		return matrix;
	}
	
	public boolean hasLeafs() {
		for (Node node : this.nodes) {
			if (node.isLeaf()) {
				return true;
			}
		}
		return false;
	}
	public ArrayList<Node> getLeafs() {
		ArrayList<Node> leafs = new ArrayList<Node>();
		for (Node node : this.nodes) {
			if (node.isLeaf()) {
				leafs.add(node);
			}
		}
		return leafs;
	}
	
	public void removeNodeWithNeighbor(Node node_) {
		Node receiver = node_.neighbors.get(0);
		
		// Lösche alle Kanten, die mit dem Blatt oder seinem Nachbarn verbunden sind.
		for (Relation relation : this.edges) {
			if (relation.pointer == node_ || relation.receiver == node_ || relation.pointer == receiver || relation.receiver == receiver) {
				this.removeEdge(relation);
			}
		}
		// Lösche das Blatt und seine Nachbarn.
		for (Node node : this.nodes) {
			if(node == node_ || node == receiver) {
				this.removeNode(node);
				node.kill();
			}
		}	
	}
	public void removeNode(Node node_) {
		this.nodes.remove(node_);
	}
	public void removeEdge(Relation edge_) {
		this.edges.remove(edge_);
	}
	
	public void printNodeList() {
		String[] strArray = new String[this.nodes_count];
		
		for (int ii = 0; ii < this.nodes_count; ii++) {			
			strArray[ii] = String.valueOf(this.nodes.get(ii).index);
		}   
		System.out.println(Arrays.toString(strArray));
	}
	public void printNodeNameList() {
		String[] strArray = new String[this.nodes_count];
		
		for (int ii = 0; ii < this.nodes_count; ii++) {			
			strArray[ii] = this.nodes.get(ii).name;
		}   
		System.out.println(Arrays.toString(strArray));
	}
	public void printEdgeList() {
		String[] strArray = new String[this.edges_count];
		
		for (int ii = 0; ii < this.edges_count; ii++) {			
			strArray[ii] = Arrays.toString(this.edges.get(ii).getIndexArray());
		}   
		Arrays.sort(strArray);
		System.out.println(Arrays.toString(strArray));
	}
	public void printAdjacencyList() {
		String[] strArray = new String[this.nodes_count];
		
		for (int ii = 0; ii < this.nodes_count; ii++) {	
			int[] arr = this.nodes.get(ii).getNeighborIndexArray();
			Arrays.sort(arr);
			strArray[ii] = Arrays.toString(arr);
		}   
		System.out.println(Arrays.toString(strArray));
	}
	public void printAdjacencyMatrix() {
		int[][] matrix = this.createAdjacencyMatrix(); 
		for (int[] arr : matrix) {
			System.out.println(Arrays.toString(arr));
		}
	}
	public void printArray() {
		int[][] matrix = this.createAdjacencyMatrix();
		String[] str = new String[this.nodes_count];
		for (int ii = 0; ii < this.nodes_count; ii++) {
			str[ii] = Arrays.toString(matrix[ii]);
		}		
		System.out.println(Arrays.toString(str).replace('[', '{').replace(']', '}'));			
	}
	public void printCheckGraph() {
		if (this.checkGraph()) {
			System.out.println("Der Graph ist paarungsunperfekt.");
		}else {
			System.out.println("Der Graph ist nicht paarungsunperfekt.");
		}
	}
	
	public boolean checkGraph() {
		Graph clone = (Graph) this.clone();
		this.clone = clone;
		
		// Wenn der Graph weniger als 2 Ecken hat, ist er trevialer Weise paarungsunperfekt.
		if (this.nodes_count < 2) {
			return true;
		}
		
		while(true){
			// Wenn der Graph leer ist, war er paarungsperfekt.
			if (this.clone.nodes_count == 0) {
				break;
			}
			
			// Wenn der Graph eine ungrade Anzahl an Ecken hat, kann er trevialer Weise nicht (mehr) paarungsperfekt sein.
			if(this.clone.nodes_count % 2 > 0) {
				return true;
			}		
			
			//Wenn eine der Ecken mehr als ein Blatt hat, kann der Graph nicht paarungsperfekt sein.
			for (Node node : this.clone.nodes) {
				if (node.leafs_count() > 1) {
					return true;
				}
			}
			
			ArrayList<Node> leafs = this.clone.getLeafs();
			
			// Wenn der Graph keine Blätter mehr hat, ist er paarungsperfekt.
			if(leafs.size() == 0) {
				break;
			}
			
			// Lösche alle Blätter mit den verbundenen Ecken und Kanten.
			for (Node leaf : leafs) {
				this.clone.removeNodeWithNeighbor(leaf);
			}
		}		
		return false;
	}
}