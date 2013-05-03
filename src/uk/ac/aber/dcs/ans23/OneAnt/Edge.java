package uk.ac.aber.dcs.ans23.OneAnt;

public class Edge{
	private final Node from;
	private final Node to;
	private final double weight;
	private double pheromoneValue;

	public Edge(Node from, Node to, int weight){
		this.from = from;
		this.to = to;
		this.weight = weight;
		pheromoneValue = 0;
		//System.out.println(from.getName() + " " + weight + " " + to.getName());
	}

	public double getWeight(){
		return this.weight;
	}

	public Node getToNode()
	{
		return to;
	}

	public Node getFromNode()
	{
		return from;
	}

	public boolean contains(Node b){
		if(from.equals(b) || to.equals(b)){
			return true;
		}
		else{
			return false;
		}

	}

	public void setPheromoneValue(double value){
		pheromoneValue = value;
	}
	
	public double getPheromoneValue(){
		return pheromoneValue;
	}
	
	public boolean equals(Edge oEdge){		
		boolean isSame = false;
		if(from.equals(oEdge.getFromNode()) && to.equals(oEdge.getToNode()))
		{
			isSame = true;
		}
		else if(from.equals(oEdge.getToNode()) && to.equals(oEdge.getFromNode()))
		{
			isSame = true;
		}
		if(weight != oEdge.getWeight())
		{
			isSame = false;
		}

		return isSame;
	}
}