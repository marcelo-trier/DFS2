package br.dfs2.test;

import java.io.File;

import br.dfs2.Grafo;
import br.dfs2.scc.SCC;
import br.dfs2.util.GerenteArquivos;

public class Test4 {

	public static void main(String[] args) throws Exception {
		System.out.println( "RUNNING: " + Test4.class.getName() + "\n\n" );

		Grafo g = new Grafo();
		File f = GerenteArquivos.getInstance().getOpenFile();
		g.loadFromFile( f );
		SCC scc = new SCC( g );
		scc.execute();
		System.out.println( scc.toString() );
		
		Grafo g2 = scc.getGrafo();
		System.out.println( g2.toString() );
	}
}
