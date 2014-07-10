package br.dfs2;

import java.util.ArrayList;
import java.util.Arrays;

public class DFS extends Grafo {

	public BuscaInfo listaInfo[];
	int time = 0;
	
	public DFS( Grafo g ) throws Exception {
		super( g.arestas, g.vertices );
		initInfo();
	}

	public void initInfo() {
		listaInfo = new BuscaInfo[ vertices.length ];
		for( int i=0; i<listaInfo.length; i++ ) {
			listaInfo[i] = new BuscaInfo( vertices[ i ] );
		}
	}
	
	public void sortFu() {
		Arrays.sort( listaInfo ); // ordenar listaInfo em ordem crescente...

		int u = vertices.length - 1;
		for( BuscaInfo bi : listaInfo ) {
			vertices[ u-- ] = bi.vertex; // pegar os vertices em ordem decrescente...
		}
	}
	
	public void buildAdjacentes() throws Exception {
		ArrayList<Aresta> tmp = new ArrayList<Aresta>();
		
		for( Vertice vertex : vertices ) {
			tmp.clear();
			for( Aresta a : arestas ) {
				if( a.vi[0].equals( vertex ) )
					tmp.add( a );
			}
			BuscaInfo bi = BuscaInfo.findBi( vertex, listaInfo );
			bi.setAdjacentes( tmp );
		}
	}
	
	public void execute( int startI ) throws Exception {
		time = 0;
		buildAdjacentes();
		int index = startI;
		for( int i=0; i<listaInfo.length; i++ ) {
			BuscaInfo bi = listaInfo[ index ];
			if( bi.color == COLOR_DFS.WHITE ) {
				visite( bi );
			}
			index = ( index + 1 ) % listaInfo.length;
		}
	}
	
	public void execute() throws Exception{
		execute( 0 );
	}
	
	public void visite( BuscaInfo u ) throws Exception {
		u.color = COLOR_DFS.GRAY;
		u.du = ++time;
		for( Vertice vert : u.listAdj ) {
			BuscaInfo v = BuscaInfo.findBi( vert, listaInfo );
			if( v.color == COLOR_DFS.WHITE ) {
				v.pi = u.vertex;
				visite( v );
			}
		}
		u.color = COLOR_DFS.BLACK;
		u.fu = ++time;
	}
	
	String intFormat = "%2d, ";
	String strFormat = "%2s, ";
	public String toString() {
		String msg;
		String du = "du[] = { ";
		String fu = "fu[] = { ";
		String pi = "pi[] = { ";
		msg = "#DFS :\n";
		for( BuscaInfo bi : listaInfo ) {
			//du += bi.du + ", ";
			//fu += bi.fu + ", ";
			du += String.format( intFormat, bi.du );
			fu += String.format( intFormat, bi.fu );
			//int vertex = ( bi.pi == null ) ? -1 : bi.pi.vertex;
			pi += ( bi.pi == null ) ? "--, " : String.format( strFormat, bi.pi.toString() );
			//pi += ", ";
			//pi += bi.pi + ", ";
		}
		du = du.substring( 0, du.length() - 2 ) + " };";
		fu = fu.substring( 0, fu.length() - 2 ) + " };";
		pi = pi.substring( 0, pi.length() - 2 ) + " };";
		
		msg += du + "\n" + fu + "\n" + pi;

		msg = super.toString() + msg;
		return msg;
	}
}
