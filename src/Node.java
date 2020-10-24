import java.util.ArrayList;
import java.util.Iterator;

public class Node {
	
	public int index; 					// Index der Ecke im Ausgangsgraphen.
	public int currentIndex; 			// Index der Ecke im veränderten Graphen.
	public boolean is_paired; 			// Gibt an, ob eine Ecke gepaart ist.
	public Node partner;				// Gibt die Ecke, an mit der die Ecke gepaart ist.
	public String name; 				// Name der Ecke;
	public ArrayList<Node> neighbors; 	// Liste der benachbarten Ecken.
	public int vertex_degree; 			// Eckengrad.
	public boolean is_leaf; 			// Angabe, ob die Ecke ein Blatt ist.
		
	public Node(int index_) {
		this.index = index_;
		this.currentIndex = index_;
		this.name = identifyName();
		this.neighbors = new ArrayList<Node>();
		this.vertex_degree = 0;
		//this.leafs = new ArrayList<Node>();
		//this.leafs_count = 0;
		this.is_leaf = true; 
	}
	public Relation relateEdge(Node new_neighbor_) {	
		if(! this.hasNeighbor(new_neighbor_)) {
			this.neighbors.add(new_neighbor_);
			Relation edge = new Relation(this, new_neighbor_, true);
			this.vertex_degree++;
			this.is_leaf = (this.vertex_degree > 1) ? false : true;		
			return edge;
		}
		return null;
	}
	private boolean hasNeighbor(Node node_) {
		Iterator<Node> itr = this.neighbors.iterator();
		while (itr.hasNext()) {
			if(node_ == itr.next()) {
				return true;
			}	
        }
		return false;
	}
	public boolean hasNeighbor(int index_) {
		Iterator<Node> itr = this.neighbors.iterator();
		while (itr.hasNext()) {
			if(itr.next().index == index_) {
				return true;
			}	
        }
		return false;
	}
	public int leafs_count() {
		int cnt = 0;
		for (Node node : this.neighbors) {
			if (node.is_leaf) {
				cnt++;
			}
		}
		return cnt;
	}
	private String identifyName() {
		String[] names = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", 
				"aa", "ab", "ac", "ad", "ae", "af", "ag", "ah", "ai", "aj", "ak", "al", "am", "an", "ao", "ap", "aq", "ar", "as", "at", "au", "av", "aw", "ax", "ay", "az",
				"ba", "bb", "bc", "bd", "be", "bf", "bg", "bh", "bi", "bj", "bk", "bl", "bm", "bn", "bo", "bp", "bq", "br", "bs", "bt", "bu", "bv", "bw", "bx", "by", "bz",
				"ca", "cb", "cc", "cd", "ce", "cf", "cg", "ch", "ci", "cj", "ck", "cl", "cm", "cn", "co", "cp", "cq", "cr", "cs", "ct", "cu", "cv", "cw", "cx", "cy", "cz",
				"da", "db", "dc", "dd", "de", "df", "dg", "dh", "di", "dj", "dk", "dl", "dm", "dn", "do", "dp", "dq", "dr", "ds", "dt", "du", "dv", "dw", "dx", "dy", "dz",
				"ea", "eb", "ec", "ed", "ee", "ef", "eg", "eh", "ei", "ej", "ek", "el", "em", "en", "eo", "ep", "eq", "er", "es", "et", "eu", "ev", "ew", "ex", "ey", "ez",
				"fa", "fb", "fc", "fd", "fe", "ff", "fg", "fh", "fi", "fj", "fk", "fl", "fm", "fn", "fo", "fp", "fq", "fr", "fs", "ft", "fu", "fv", "fw", "fx", "fy", "fz",
				"ga", "gb", "gc", "gd", "ge", "gf", "gg", "gh", "gi", "gj", "gk", "gl", "gm", "gn", "go", "gp", "gq", "gr", "gs", "gt", "gu", "gv", "gw", "gx", "gy", "gz",
				"ha", "hb", "hc", "hd", "he", "hf", "hg", "hh", "hi", "hj", "hk", "hl", "hm", "hn", "ho", "hp", "hq", "hr", "hs", "ht", "hu", "hv", "hw", "hx", "hy", "hz",
				"ia", "ib", "ic", "id", "ie", "if", "ig", "ih", "ii", "ij", "ik", "il", "im", "in", "io", "ip", "iq", "ir", "is", "it", "iu", "iv", "iw", "ix", "iy", "iz",
				};
		return names[this.index];
	}
	public int[] getNeighborIndexArray() {
		int[] arr = new int[this.neighbors.size()];
		for (int ii = 0; ii < this.neighbors.size(); ii++) {
			arr[ii] = this.neighbors.get(ii).index;
		}
		return arr;
	}	
}
