package br.dfs2.scc;

import java.util.ArrayList;
import java.util.Arrays;

import br.dfs2.Aresta;
import br.dfs2.BuscaInfo;
import br.dfs2.DFS;
import br.dfs2.Grafo;
import br.dfs2.Vertice;

public class SCC extends DFS {

	ArrayList< Vertice[] > sccList = new ArrayList< Vertice[] >();
	
	public SCC(Grafo g) throws Exception {
		super(g);
	}

	public void buildConnectedComponent() {
		ArrayList<Vertice> vertexListTmp = new ArrayList<Vertice>();
		Arrays.sort( listaInfo ); // ordena fu em ordem crescente...

		int antecessor = listaInfo[ 0 ].fu - 1; // importante para nao criar o array de primeira
		for( BuscaInfo bi : listaInfo ) {
			if( antecessor != bi.fu - 1 ) {
				Vertice vArr[] = new Vertice[ vertexListTmp.size() ];
				vArr = vertexListTmp.toArray( vArr );
				sccList.add( vArr );
				vertexListTmp.clear();
			}
			vertexListTmp.add( bi.vertex );
			antecessor = bi.fu;
		}
		Vertice vArr[] = new Vertice[ vertexListTmp.size() ];
		vArr = vertexListTmp.toArray( vArr );
		sccList.add( vArr );
	}
	
	public void buildConnectedComponent__OLD() {
		ArrayList<Vertice> vertexListTmp = new ArrayList<Vertice>();
		Arrays.sort( listaInfo ); // ordena fu em ordem crescente...

		int antecessor = listaInfo[ 0 ].fu - 1;
		for( BuscaInfo bi : listaInfo ) {
			if( antecessor != bi.fu - 1 ) {
				Vertice vArr[] = new Vertice[ vertexListTmp.size() ];
				vArr = vertexListTmp.toArray( vArr );
				sccList.add( vArr );
				vertexListTmp.clear();
			}
			vertexListTmp.add( bi.vertex );
			antecessor = bi.fu;
		}
	}
	
	public String toString() {
		String msg = "\nSCC: ";
		for( Vertice[] vList : sccList ) {
			msg += "\nCC[ ";
			for( Vertice v : vList ) {
				msg += v.toString() + " | ";
			}
			msg = msg.substring( 0, msg.length() - 2 );
			msg += " ];";
		}
		return msg;
	}
	
	public void execute() throws Exception {
		System.out.println( "RUNNING: " + this.getClass().getName() + "\n\n" );

		super.execute( 2 );
		System.out.println( super.toString() );
		
		System.out.println( "\n------------[ sorting (fu)... ]------------");
		super.sortFu();
		System.out.println( super.toString() );

		System.out.println( "\n------------[ transpose ... ]------------");
		super.transpose();
		System.out.println( super.toString() );

		System.out.println( "\n------------[ second time ... ]------------");
		super.initInfo(); // refaz as listas de BuildInfo, p/ não misturar com o q jah tem... ;o)
		super.execute();
		System.out.println( super.toString() );
		
//		System.out.println( "\n------------[ SCC ... ]------------");
		buildConnectedComponent();
//		System.out.println( toString() );
		
		// retornar ao estado normaaaaallll
		super.transpose();
	}
	
	protected int getComponentIndex( Vertice findV ) throws Exception {
		for( int i=0; i<sccList.size(); i++ ) {
			Vertice[] listV = sccList.get( i );
			for( Vertice v : listV ) {
				if( v.equals( findV ) )
					return i;
			}
		}
		throw new Exception( "nao encontrado Vertice: "+findV.toString()+"! Olhar código!" );
	}
	
	public Grafo getGrafo() throws Exception {
		
		ArrayList<Vertice> tmpList = new ArrayList<Vertice>();
		Vertice[] verticeList = null;
		for( int i=0; i<sccList.size(); i++ ) {
			String label = "";
			for( Vertice v : sccList.get( i ) ) {
				label += v.toString();
			}
			Vertice novoV = new Vertice( i, label );
			tmpList.add( novoV );
		}
		verticeList = new Vertice[ tmpList.size() ];
		verticeList = tmpList.toArray( verticeList );

		Grafo g = new Grafo();
		g.initVertices( verticeList );

		for( Aresta a : arestas ) {
			Vertice v1=a.vi[0], v2=a.vi[1];
			int i1 = getComponentIndex( v1 );
			int i2 = getComponentIndex( v2 );
			if( i1 != i2 ) {
				g.addAresta( verticeList[i1], verticeList[i2], 1 );
			}
		}
		return g;
	}
}
