package br.dfs2;

public class Aresta implements Comparable<Aresta> {
	public Vertice vi[] = { null, null }; // somente um indice para o vetor de vertice
	public float weight; // peso
	public String label;
	
	public Aresta( Vertice v1, Vertice v2, float w ) {
		setInfo( v1, v2, w );
	}

	public void setInfo( Vertice v1, Vertice v2, float w ) {
		vi[0] = v1;
		vi[1] = v2;
		weight = w;
	}
	
	public int compareTo(Aresta o) {
		if( this.weight < o.weight )
			return -1;

		if( this.weight == o.weight )
			return 0;

		return 1;
	}
}
