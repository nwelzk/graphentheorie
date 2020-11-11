import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Graph implements Cloneable {
	
	public ArrayList<Node>	nodes;		// Liste aller Ecken.
	public ArrayList<Relation>	edges;	// Liste aller Kanten.
	public Graph clone;
	
	public ArrayList<Component>	components;
	
	public Graph() {
		this.nodes = new ArrayList<Node>();
		this.edges = new ArrayList<Relation>();
	}
	public Graph(int nodes_count_) {
		this.nodes = new ArrayList<Node>();
		this.edges = new ArrayList<Relation>();
		for (int ii = 0; ii < nodes_count_; ii++) {
			this.nodes.add(new Node(ii));
		}
	}
	public Graph(int nodes_count_, int propability_) {
		Random r = new Random();
		this.nodes = new ArrayList<Node>();
		this.edges = new ArrayList<Relation>();
		for (int ii = 0; ii < nodes_count_; ii++) {
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
		this.nodes = new ArrayList<Node>();
		this.edges = new ArrayList<Relation>();
		for (int vv = 0; vv < adjacency_matrix_.length; vv++) {
			nodes.add(new Node(vv));
		}
		for (int jj = 0; jj < adjacency_matrix_.length; jj++) {
			for (int ii = 0; ii < adjacency_matrix_.length; ii++) {
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
		for (int ii = 0; ii < parsedString.length; ii++) {
			String[] values = parsedString[ii].replaceAll("\\[", "").split(", ");
			for (String value : values) {
				this.createDirectedRelation(this.nodes.get(ii), this.nodes.get(Integer.parseInt(value)));
			}
		}
	}	
	
	@Override
	public Graph clone() {	
		return new Graph(this.getAdjacencyList());
	}
	
	public void createRandomRelations(int amount_) {
		for (int ii = 0; ii < amount_; ii++) {
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
	}
	protected void createDirectedRelation(Node node_left_, Node node_right_) {
		Relation r = node_left_.relateEdge(node_right_);
		if(r != null) { 
			this.edges.add(r);
		}		
	}	
	protected Node getRandomNode() {
		Random r = new Random();
		if (this.nodes.isEmpty()) {
			return null;
		}
		return this.nodes.get(r.nextInt(this.nodes.size()));
	}
	protected Node getRandomNode(int exclusion_) {
		Random r = new Random();
		int index = exclusion_;
		if (this.nodes.isEmpty()) {
			return null;
		}
		while(index == exclusion_) {	
			index = r.nextInt(this.nodes.size());
		}		
		return this.nodes.get(index);
	}
	protected Node getRandomConnectedNode(int exclusion_) {
		Random r = new Random();
		Node focused_node = null;
		if (this.nodes.isEmpty()) {
			return null;
		}
		int index = exclusion_;
		while(index == exclusion_) {
			focused_node = this.nodes.get(r.nextInt(this.nodes.size()));
			if (focused_node.getVertexDegree() > 0) {
				index = focused_node.index;
			}
		}		
		return focused_node;
	}
	protected int[][] createAdjacencyMatrix() {
		int[][] matrix = new int[this.nodes.size()][this.nodes.size()];
		
		for (int jj = 0; jj < this.nodes.size(); jj++) {
			int[] arr = new int[this.nodes.size()];
			Node currentNode = this.nodes.get(jj);
			for (int ii = 0; ii < this.nodes.size(); ii++) {
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
	
	public void removeNodeWithNeighbors(Node node_) {
		ArrayList<Relation> detected_relations = new ArrayList<Relation>();
		ArrayList<Node> detected_nodes = new ArrayList<Node>();
		
		if (node_.neighbors != null && node_.neighbors.size() > 0) {
			Node receiver = node_.neighbors.get(0);
			
			// Lösche alle Kanten, die mit dem Blatt oder seinem Nachbarn verbunden sind.
			for (Relation relation : this.edges) {
				if (relation.pointer == node_ || relation.receiver == node_ || relation.pointer == receiver || relation.receiver == receiver) {
						detected_relations.add(relation);				
				}
			}
			
			for (Node node : this.nodes) {
				if(node == node_ || node == receiver) {
					detected_nodes.add(node);		
				}				
			}
			
			this.edges.removeAll(detected_relations);
			this.nodes.removeAll(detected_nodes);
			
			// Lösche das Blatt und seine Nachbarn.	
			for (Node node : detected_nodes) {
				node.kill();
			}
		}else {
			this.nodes.remove(node_);
		}		
	}
	public void removeNode(Node node_) {
		ArrayList<Relation> detected_relations = new ArrayList<Relation>();
		for (Relation relation : this.edges) {
			if (relation.pointer == node_ || relation.receiver == node_) {
					detected_relations.add(relation);				
			}
		}
		
		for (Relation relation : detected_relations) {
			this.edges.remove(relation);
		}
		node_.kill();
		this.nodes.remove(node_);
	}
	public void removeNode(int index_) {
		Node node = null;
		for (Node nn : this.nodes) {
			if (nn.index == index_) {
				node = nn;
			}
		}
		if (node != null) {
			ArrayList<Relation> detected_relations = new ArrayList<Relation>();
			for (Relation relation : this.edges) {
				if (relation.pointer == node || relation.receiver == node) {
						detected_relations.add(relation);				
				}
			}
			
			for (Relation relation : detected_relations) {
				this.edges.remove(relation);
			}
			node.kill();
			this.nodes.remove(node);
		}
	}
	public ArrayList<Integer> removeRandomNodes(int amount_) {
		Random r = new Random();
		ArrayList<Integer> selected_nodes = new ArrayList<Integer>();
		
		for (int ii = 0; ii < amount_; ii++) {
			while(selected_nodes.size() < this.nodes.size()) {
				if (this.nodes.isEmpty()) {
					return selected_nodes;
				}
				int index = r.nextInt(this.nodes.size());
				if(! selected_nodes.contains(index)) {
					selected_nodes.add(index);
					break;
				}
			}
		}
		for (Integer index : selected_nodes) {
			this.removeNode(index);
		}
		return selected_nodes;
	}

	public void setComponents() {
		this.components = new ArrayList<Component>();
		ArrayList<Node> nodes_copy = new ArrayList<Node>();
		nodes_copy.addAll(this.nodes);
		int counter = 0;
		
		while(nodes_copy.size() > 0) {
			Component component = new Component(counter, nodes_copy.get(0));
			this.components.add(component);
			nodes_copy.removeAll(component.nodes);
			counter++;
		}
	}
	public int getNumberOfEvenComponents() {
		int counter = 0;
		for (Component component : this.components) {
			if(component.nodes.size() % 2 == 0) {
				counter++;
			}
		}
		return counter;
	}
	public int getNumberOfOddComponents() {
		return this.components.size() - this.getNumberOfEvenComponents();
	}
	
	public ArrayList<Integer> getSortedNodesList() {
//		int[] arr = this.getNodesArray();
//		Arrays.sort(arr);
//		ArrayList<Integer> list = new ArrayList<Integer>();

		ArrayList<Integer> intList = new ArrayList<Integer>();
		
		for (int ii = 0; ii < this.nodes.size(); ii++) {			
			intList.add(this.nodes.get(ii).index);
		} 
		Collections.sort(intList);
		
		return intList;
	}
	public int[] getNodesArray() {
		int[] intArray = new int[this.nodes.size()];
		
		for (int ii = 0; ii < this.nodes.size(); ii++) {			
			intArray[ii] = this.nodes.get(ii).index;
		}   
		return intArray;
	}
	public String getNodesList() {
		String[] strArray = new String[this.nodes.size()];
		
		for (int ii = 0; ii < this.nodes.size(); ii++) {			
			strArray[ii] = String.valueOf(this.nodes.get(ii).index);
		}   
		return Arrays.toString(strArray);
	}
	public String getNodeNameList() {
		String[] strArray = new String[this.nodes.size()];
		
		for (int ii = 0; ii < this.nodes.size(); ii++) {			
			strArray[ii] = this.nodes.get(ii).name;
		}   
		return Arrays.toString(strArray);
	}
	public String getEdgeList() {
		String[] strArray = new String[this.edges.size()];
		
		for (int ii = 0; ii < this.edges.size(); ii++) {			
			strArray[ii] = Arrays.toString(this.edges.get(ii).getIndexArray());
		}   
		Arrays.sort(strArray);
		return Arrays.toString(strArray);
	}
	public String getAdjacencyList() {
		String[] strArray = new String[this.nodes.size()];
		
		for (int ii = 0; ii < this.nodes.size(); ii++) {	
			int[] arr = this.nodes.get(ii).getNeighborIndexArray(); ///
			Arrays.sort(arr);
			strArray[ii] = Arrays.toString(arr);
		}   
		return Arrays.toString(strArray);
	}
	public void printAdjacencyMatrix() {
		int[][] matrix = this.createAdjacencyMatrix(); 
		for (int[] arr : matrix) {
			System.out.println(Arrays.toString(arr));
		}
	}
	public String getArray() {
		int[][] matrix = this.createAdjacencyMatrix();
		String[] str = new String[this.nodes.size()];
		for (int ii = 0; ii < this.nodes.size(); ii++) {
			str[ii] = Arrays.toString(matrix[ii]);
		}		
		return Arrays.toString(str).replace('[', '{').replace(']', '}');			
	}
	public String stringCheckGraph(boolean print_details_) {
		if (this.checkGraphIsMatchingPerfect(print_details_)) {
			return "Der Graph ist paarungsunperfekt.";
		}
		return "Der Graph ist nicht paarungsunperfekt.";
	}
	
	public boolean checkGraphIsMatchingPerfect(boolean print_details_) {
		Graph clone = (Graph) this.clone();
		this.clone = clone;
		
		// Wenn der Graph weniger als 2 Ecken hat, ist er trevialer Weise paarungsunperfekt.
		if (this.nodes.size() < 2) {
			return true;
		}
		
		while(true){
			if(print_details_) {
				System.out.println(this.clone.getAdjacencyList());
			}
			
			// Wenn der Graph leer ist, war er paarungsperfekt.
			if (this.clone.nodes.size() == 0) {
				break;
			}
			
			// Wenn der Graph eine ungrade Anzahl an Ecken hat, kann er trevialer Weise nicht (mehr) paarungsperfekt sein.
			if(this.clone.nodes.size() % 2 > 0) {
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

			// Lösche alle Blätter und ihre benachbarten Ecken, sowie alle angrenzenden Kanten.
			for (Node leaf : leafs) {
				this.clone.removeNodeWithNeighbors(leaf);
			}
		}		
		return false;
	}
	
	public boolean checkGraphBreakIntoMoreOddSizedComponentsAsGivenRemoteNodes(ArrayList<Node> s) {
		
		for (Node ii : s) {
			this.removeNode(ii);
		}
		this.setComponents();
		
		if (s.size() < this.getNumberOfOddComponents()) {
			return true;
		}		
		return false;
	}
	public boolean checkGraphBreakIntoMoreOddSizedComponentsAsGivenRemoteNodes(Integer[] s) {
		
		for (Integer ii : s) {
			this.removeNode(ii);
		}
		this.setComponents();
		
		if (s.length < this.getNumberOfOddComponents()) {
			return true;
		}		
		return false;
	}
	
	public boolean checkGraphBreakIntoMoreOddSizedComponentsAsGivenRemoteNodes(Node[] s) {
		
		for (Node ii : s) {
			this.removeNode(ii);
		}
		this.setComponents();
		
		if (s.length < this.getNumberOfOddComponents()) {
			return true;
		}		
		return false;
	}
	
}