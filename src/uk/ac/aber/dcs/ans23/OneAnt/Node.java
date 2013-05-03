package uk.ac.aber.dcs.ans23.OneAnt;

import java.util.ArrayList;

public class Node{
	private final String name;
	private ArrayList<Edge> edges;

	public Node(String name){
		this.name = name;
		edges = new ArrayList<Edge>();
	}

	public Node addEdge(Edge e){
		edges.add(e);
		return this;
	}

	public String getName(){
		return this.name;
	}
	
	public boolean equals(Node b)
	{
		return name.equals(b.getName());
	}
	
	public boolean hasEdge(Edge e)
	{
		for(Edge edge : edges)
		{
			if(edge.equals(e))
			{
				return true;
			}
		}
		return false;
	}
	
	public Edge getEdge(Node b){
		for(Edge e : edges){
			if(e.contains(b)){
				return e;
			}
		}
		return null;
	}
	
	public ArrayList<Edge> getEdges(){
		return edges;
	}
	
	@Override
	public String toString(){
		return name;
	}
}