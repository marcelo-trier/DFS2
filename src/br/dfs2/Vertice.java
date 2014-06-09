package br.dfs2;

public class Vertice {
	int id = 0;
	String label = "";
	
	public Vertice( int i, String l ) {
		id = i;
		label = l;
	}
	
	public String toString() {
		return label;
	}
}
