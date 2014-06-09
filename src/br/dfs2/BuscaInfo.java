package br.dfs2;

import java.util.ArrayList;

enum COLOR_DFS {
	WHITE, GRAY, BLACK;
}

public class BuscaInfo implements Comparable<BuscaInfo> {
	
	COLOR_DFS color = COLOR_DFS.WHITE;
	public Vertice vertex = null;
	int du = 0;
	public int fu = 0;
	Vertice pi = null; // indice do vertice anterior
	Vertice listAdj[] = null; // indice dos verticesss

	public BuscaInfo( Vertice vi ) {
		vertex = vi;
	}

	public void setAdjacentes( ArrayList<Aresta> l ) throws Exception {
		listAdj = new Vertice[ l.size() ];
		int i = 0;
		for (Aresta a : l) {
			listAdj[i++] = a.vi[1];
		}
	}
	
	public static BuscaInfo findBi( Vertice vertex, BuscaInfo[] lInfo ) throws Exception {
		for( BuscaInfo bi : lInfo ) {
			if( bi.vertex.equals( vertex ) )
				return bi;
		}
		throw new Exception( "nao encontrado ID... verificar!" );
	}

	@Override
	public int compareTo(BuscaInfo o) {
		if( this.fu < o.fu )
			return -1;
		else if( this.fu > o.fu )
			return 1;
		else
			return 0;
	}
}
