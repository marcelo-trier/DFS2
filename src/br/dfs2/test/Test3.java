package br.dfs2.test;

import java.io.File;

import br.dfs2.DFS;
import br.dfs2.Grafo;
//import br.dfs2.scc.SCC_OLD;
import br.dfs2.util.GerenteArquivos;

public class Test3 {

	public static void main(String[] args) throws Exception {
		System.out.println( "RUNNING: " + Test3.class.getName() + "\n\n" );

		Grafo g = new Grafo();
		File f = GerenteArquivos.getInstance().getOpenFile();
		g.loadFromFile( f );
		DFS dfs = new DFS( g );
		dfs.execute( 2 );
		System.out.println( dfs.toString() );

		System.out.println( "\n------------[ sorting (fu)... ]------------");
		dfs.sortFu();
		System.out.println( dfs.toString() );

		System.out.println( "\n------------[ transpose ... ]------------");
		dfs.transpose();
		System.out.println( dfs.toString() );
		
		System.out.println( "\n------------[ second time ... ]------------");
		DFS dfs2 = new DFS( dfs );
		dfs2.execute();
		System.out.println( dfs2.toString() );
		
//		System.out.println( "\n------------[ SCC ... ]------------");
//		SCC_OLD scc = new SCC_OLD( dfs2 );
//		scc.execute();
//		scc.buildConnectedComponent();
//		System.out.println( scc.toString() );
	}
}
