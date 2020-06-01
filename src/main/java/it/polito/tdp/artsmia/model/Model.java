package it.polito.tdp.artsmia.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.artsmia.db.ArtsmiaDAO;

public class Model {
	
	private Graph<Integer, DefaultWeightedEdge> grafo;
	private List<Adiacenza> adiacenze;
	private ArtsmiaDAO dao;
	private List<Integer> best;
	
	
	public Model() {
		this.dao = new ArtsmiaDAO();
	}
	
	public List<String> getRuoli(){
		return this.dao.getRuoli();
	}
	
	public int vertici() {
		return this.grafo.vertexSet().size();
	}
	
	public int archi() {
		return this.grafo.edgeSet().size();
	}
	
	public List<Adiacenza> getAdiacenze(){
		return this.adiacenze;
	}
	
	public void creaGrafo(String ruolo) {
		this.grafo = new SimpleWeightedGraph<Integer,DefaultWeightedEdge>(DefaultWeightedEdge.class);
		
		Graphs.addAllVertices(this.grafo, this.dao.getArtisti(ruolo));
		
		adiacenze = this.dao.getAdiacenze(ruolo);
		
		for(Adiacenza a : adiacenze) {
			/*if(!this.grafo.containsVertex(a.getA1()))
				this.grafo.addVertex(a.getA1());
			if(!this.grafo.containsVertex(a.getA2()))
				this.grafo.addVertex(a.getA2());*/
			if(this.grafo.getEdge(a.getA1(), a.getA2()) == null) {
				Graphs.addEdgeWithVertices(this.grafo, a.getA1(), a.getA2(), a.getPeso());
			}
		}
		
		System.out.println("Grafo creato!");
		System.out.println("# VERTICI: " + this.grafo.vertexSet().size());
		System.out.println("# ARCHI: " + this.grafo.edgeSet().size());
	}
	

}
