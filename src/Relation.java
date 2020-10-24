

public class Relation {
	
	public boolean is_edge; 	// Gibt an, ob die Beziehgun eine Ecke ist, oder nicht.
	public boolean is_marked; 	// Gibt an, ob eine Kante ausgewählt ist.
	public Node pointer;		// Ausgangsecke der ungerichtete (Nicht-) Kante. 
	public Node receiver; 		// Ecke, auf die ungerichtete (Nicht-) Kante zeigt.
	
	public Relation(Node pointer_, Node receiver_, Boolean is_edge_) {
		this.pointer = pointer_;
		this.receiver = receiver_;
		this.is_edge = is_edge_;
	}
	public int[] getIndexArray() {
		int[] arr = new int[2];
		arr[0] = pointer.index;
		arr[1] = receiver.index;
		return arr;		
	}
}
