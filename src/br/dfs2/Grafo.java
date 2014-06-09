package br.dfs2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;

public class Grafo {
	Vertice[] vertices;
	protected ArrayList<Aresta> arestas = new ArrayList<Aresta>();

	public String toString() {
		String msg = "GRAFO = { V, E }, show down:\n";
		msg += "V = { ";
		for (Vertice v : vertices) {
			msg += v.label + ", ";
		}
		msg = msg.substring(0, msg.length() - 2);
		msg += " };\n";
		msg += "E = { ";
		for (Aresta a : arestas) {
			Vertice u = a.vi[0], v = a.vi[1];
			msg += "(" + u.label + " -> " + v.label + "), ";
		}
		msg = msg.substring(0, msg.length() - 2);
		msg += " }\n";
		return msg;
	}

	public Vertice[] getVertices() {
		return vertices;
	}

	public void transpose() {
		Vertice tmp;
		for (Aresta a : arestas) {
			tmp = a.vi[0];
			a.vi[0] = a.vi[1];
			a.vi[1] = tmp;
		}
	}

	public ArrayList<Aresta> getArestas() {
		return arestas;
	}

	public Grafo() {
	}

	public Grafo(ArrayList<Aresta> la, Vertice[] v) {
		arestas = (ArrayList<Aresta>) la.clone();
		if (v != null)
			initVertices(v);
		else {
			// TODO: ???? o q fazer se ( v == null ) ??
		}
	}

	public void initVertices(Vertice[] v) {
		if (vertices != null && vertices.length == v.length) {
			System.arraycopy(v, 0, vertices, 0, v.length);
			return;
		}

		vertices = v.clone();
	}

	public Aresta getAresta(Vertice v1, Vertice v2, float w) {
		for (Aresta a : arestas) {
			if (v1 == a.vi[0] && v2 == a.vi[1] && w == a.weight) {
				return a;
			}
		}
		return null;
	}

	public void addAresta(Vertice v1, Vertice v2, float w) {
		if( getAresta( v1, v2, w ) == null ) {
			arestas.add( new Aresta( v1, v2, w ) );
		}
	}

	public void addAresta(Aresta a) {
		arestas.add(a);
	}

	public void criaVertices(int len) {
		vertices = new Vertice[len];
		char tmp = 'a';
		String label;
		for (int i = 0; i < vertices.length; i++) {
			label = "" + tmp++;
			vertices[i] = new Vertice(i, label);
		}
		initVertices(vertices);
	}

	public void loadFromFile(File f) throws Exception {
		FileReader fr = new FileReader(f);
		BufferedReader br = new BufferedReader(fr);
		String linha;
		int numeroLinha = -1;

		while ((linha = br.readLine()) != null) {
			linha = linha.trim();
			if (linha.equals("") || linha.charAt(0) == '#') {
				continue;
			}
			numeroLinha++;
			String[] tokens = linha.split("\\s+"); // pega qualquer coisa:
													// espaço, tab, quebra de
													// linha, etc..
			if (vertices == null) {
				criaVertices(tokens.length);
			}

			for (int numeroColuna = 0; numeroColuna < tokens.length; numeroColuna++) {
				int tmp = Integer.parseInt(tokens[numeroColuna]);
				if (tmp != -1) {
					Vertice v1, v2;
					v1 = vertices[numeroLinha];
					v2 = vertices[numeroColuna];
					Aresta a = new Aresta(v1, v2, tmp);
					addAresta(a);
				}
			}
		}

		br.close();
	}
}
