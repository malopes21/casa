package org;

import java.util.ArrayList;
import java.util.List;

public class Geometria {

	private String id;
	private List<Point2D> pontos = new ArrayList<Point2D>();
	private List<Aresta> arestas = new ArrayList<Aresta>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Point2D> getPontos() {
		return pontos;
	}

	public void setPontos(List<Point2D> pontos) {
		this.pontos = pontos;
	}

	public List<Aresta> getArestas() {
		return arestas;
	}

	public void setArestas(List<Aresta> vertices) {
		this.arestas = vertices;
	}

}
