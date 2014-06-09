package br.dfs2.util;

import java.io.FileWriter;
import java.io.IOException;

import br.dfs2.Grafo;
import br.dfs2.Aresta;

public class GraphUtils {
// TODO:: VERIFICAR codigo abaixo para construir um leitor GML
// https://code.google.com/p/flexigraph/source/browse/trunk/src/gr/forth/ics/graph/io/gml/GmlReader.java?r=4

	public static <T> void writeGMLFile(Grafo g) throws IOException {
		GerenteArquivos ga = GerenteArquivos.getInstance();
		//GerenteArquivos ga = new GerenteArquivos(null);
		FileWriter salvar = new FileWriter(ga.getSaveFile());
		String msg = "\n\n";
		msg += "graph [\n\n";
		msg += "  directed 0\n\n";
		salvar.write(msg);

		for (int i = 0; i < g.getVertices().length; i++) {
			msg = "  node [\n";
			msg += "	id " + i + "\n";
			msg += "	label \"nodo-"+i+"\" \n";
			msg += "  ]\n\n";
			salvar.write( msg );
		}

		salvar.write( "\n\n" );
		for (Aresta a : g.getArestas()) {
			msg = "  edge [\n";
			msg += " 	source " + ( a.vi[0] ) + "\n";
			msg += " 	target " + ( a.vi[1] ) + "\n";
			msg += " 	label \"" + ((int) a.weight ) + "\" \n";
			msg += "  ]\n\n";
			salvar.write( msg );
		}
		salvar.write("]\n");
		salvar.close();
	}
}
