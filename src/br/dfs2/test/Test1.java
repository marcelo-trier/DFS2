package br.dfs2.test;

import java.io.File;

import br.dfs2.DFS;
import br.dfs2.Grafo;
import br.dfs2.util.GerenteArquivos;

public class Test1 {

	public static void main(String[] args) throws Exception {
		Grafo g = new Grafo();
		File f = GerenteArquivos.getInstance().getOpenFile();
		g.loadFromFile( f );
		DFS dfs = new DFS( g );
		dfs.execute();
		System.out.println( dfs.toString() );
		//File save = GerenteArquivos.getInstance().getSaveFile();
		//GraphUtils.writeGMLFile( g );
	}

}
