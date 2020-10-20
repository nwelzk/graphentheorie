import java.lang.Math;
import java.util.Arrays;
import java.util.ArrayList;

public class Graph {
	public boolean prefix = true;
	private int nodes_count;
	private int[][] nodes;
	private ArrayList<String> node_names = new ArrayList<String>();
	private ArrayList<int[]> relations = new ArrayList<int[]>();
	private ArrayList<int[]> related_nodes_list = new ArrayList<int[]>();
	private int[][] focused_nodes;
	
	
	public Graph(int nodes_count) {
		// Constructs a graph with 50% probability of relations between nodes.
		double probability = 50;
		this.nodes_count = nodes_count;
		this.nodes = new int[nodes_count][nodes_count];
		this.createRandomGraph(probability);
		this.setNodeList();
	}
	
	public Graph(int nodes_count, double probability) {
		// Constructs a random graph with a given amount of nodes.
		this.nodes_count = nodes_count;
		this.nodes = new int[nodes_count][nodes_count];
		this.createRandomGraph(probability);
		this.setNodeList();
	}
	
	public Graph(int[][] nodes) {
		// Constructs a given graph and counts its nodes.
		this.nodes = nodes;
		this.nodes_count = nodes.length;
		this.setNodeList();
	}
	
	private void setNodeList() {
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
		
		this.node_names = new ArrayList<String>();
				
		for (int ii = 0; ii < this.nodes_count; ii++) {
			this.node_names.add(names[ii]);
		}
	}
	
	public void printNodeNames() {
		if(this.prefix) {
			System.out.print("   ");
		}
		System.out.println(this.node_names);
	}
	
	public void printNodeList() {
		String list = "[";
		for (int dd = 0; dd < this.relations.size(); dd++) {
			list += "[" + this.relations.get(dd)[0] + "," + this.relations.get(dd)[1] + "]";
			
			if(dd <= this.relations.size()-2) {
				list += ", ";
			}
		}
		list += "]";
		System.out.println(list);
	}
	
	public void printRelatedNodeList() {
		String list = "[";
		for (int dd = 0; dd < this.related_nodes_list.size(); dd++) {
			int[] arr = this.related_nodes_list.get(dd);
			list += "[";
			if(arr.length == 0) {
				list += "]";
			}
			
			for (int ee = 0; ee < arr.length; ee++) {
				list += arr[ee];
				
				
				if(ee <= arr.length - 2) {
					list += ",";
				}else{
					list += "]";
				}
			}
			if(dd <= this.related_nodes_list.size() - 2) {
				list += ", ";
			}
		}
		list += "]";
		System.out.println(list);
	}

	private void createRandomGraph(double probability) {
		this.relations = new ArrayList<int[]>();
		
		// Creates a two-dimensional array of random the relations for the graph.
		for (int ii = 0; ii < this.nodes_count; ii++) {	
			for (int jj = 0; jj < this.nodes_count; jj++){	
				int edge = 0;
				if(jj == ii) {
					this.nodes[ii][jj] = 0;
				}else if(ii <= jj){
					edge = getRandomRealtion(probability);
			
					// Eintragung in die Adjazenzliste
					this.nodes[ii][jj] = edge;
					this.nodes[jj][ii] = edge;					
			
					//Eintragung in Eckenliste
					if(edge == 1) {
						int[] f = new int[2];
						if(ii < jj) {
							f[0] = ii;
							f[1] = jj;
						}else {
							f[0] = jj;
							f[1] = ii;
						}						
						this.relations.add(f);
					}
				}
			}
		}
		this.createRelatedNodesListFromEdges();
		
	}
	
	private void createRelatedNodesListFromEdges() {
		this.related_nodes_list = new ArrayList<int[]>();
		
		for (int[] node : this.nodes) {
			int sum = Arrays.stream(node).sum();
			int[] arr = new int[sum];
			int cc = 0;	
			for (int ii = 0; ii < node.length; ii++) {
				if(node[ii] == 1) {
					arr[cc] = ii;
					cc++;
				}
			}
			this.related_nodes_list.add(arr);
		}
	}
	
	public int getNodeCount() {
		// Returns the amount of nodes.
		return this.nodes_count;
	}
	
	private int getRandomRealtion(double probability) {
		int newRandom = (int) (Math.random() * 100);
		if(newRandom < probability) {
		    return 1;
		}
		return 0;
	}
	
	public void print() {
		// Prints a graph into console.
		for (int aa = 0; aa < this.nodes.length; aa++) {
			//for (int bb = 0; bb < this.nodes.length; bb++){if(aa < bb){System.out.print(this.nodes[aa][bb]);}else{System.err.print(this.nodes[aa][bb]);}}System.out.println();
			if(this.prefix) {
				System.out.print("[" + this.node_names.get(aa) + "]");
			}
			System.out.print(Arrays.toString(this.nodes[aa]));
			System.out.println();
		}
	}
	
	public void printAsArray() {
		// Prints a graph as array into console.
		
		String str = "{";
		for (int aa = 0; aa < this.nodes.length; aa++) {
			
			str += Arrays.toString(this.nodes[aa]);
			if(aa != this.nodes.length - 1) {
				str += ", ";
			}
		}
		str = str.replace("]","}");
		str = str.replace("[","{");
		str += "}";
		System.out.println(str);
	}

	public boolean check() {
		// Graph has an odd amount of nodes.
		if (this.nodes_count % 2 == 1) {	
			return true;
		}
		
		this.focused_nodes = this.nodes;
	
		for (int ii = 0; ii < this.focused_nodes.length; ii++) {
			if(Arrays.stream(this.nodes[ii]).sum() == 1) {
				// Ermittle, an welcher position die 1 ist.
				// Lösche die Zeilen und Spalten der aktuellen und der 1er Position.
				
				// Wiederhole die Prüfung.
			}	
		}
		return true;
	}
}
