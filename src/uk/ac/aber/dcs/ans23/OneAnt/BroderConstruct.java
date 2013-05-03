package uk.ac.aber.dcs.ans23.OneAnt;

import java.util.ArrayList;
import java.util.Random;

/**
 * This class is used to utilize the broder construct algorithm.
 * @author Andreas O'Brien Svingeseth (ans23@aber.ac.uk)
 * @version 1.0 final
 */
public class BroderConstruct {
	private Random rand;
	private double alpha, beta, roh;
	
	/**
	 * Constructor to create a new broder construct.
	 * @param alpha
	 * @param beta
	 * @param roh
	 */
	public BroderConstruct(double alpha, double beta, double roh){
		this.alpha = alpha;
		this.beta = beta;
		this.roh = roh;
		rand = new Random();
	}

	/**
	 * 
	 * @param graph
	 * @return a new spanning tree.
	 */
	public ArrayList<Node> constructBroder(InputGraph graph){
		ArrayList<Node> inputGraph = graph.getInputGraph();
		ArrayList<Node> spanningTree = new ArrayList<Node>(); //creates an empty spanning tree 
		Node selectedNode = inputGraph.get(rand.nextInt(inputGraph.size()));
		spanningTree.add(selectedNode);
		
		while(spanningTree.size() < inputGraph.size()){
			System.out.println("Size: " + spanningTree.size());
			double R = 0;
			double neighbourhoodValue = -10;
			Edge chosenEdge = null;

			for(Edge e : selectedNode.getEdges()){
				R += (Math.pow(e.getPheromoneValue(),alpha) * (Math.pow(1/e.getWeight(), beta)));
			}
			
			//System.out.println("Baroo name?? " + selectedNode.getName());
			for(Edge currEdge : selectedNode.getEdges()){
				double val = (Math.pow(currEdge.getPheromoneValue(),alpha) * (Math.pow(1/currEdge.getWeight(), beta)))/R;
				System.out.println("Value: " + val);
				if(val > neighbourhoodValue){
					neighbourhoodValue = val;
					chosenEdge = currEdge;
				}
			}
			
			
			Node neighbour = chosenEdge.getToNode();
			/*if(selectedNode.equals(chosenEdge.getFromNode()))
			{
				neighbour = chosenEdge.getToNode();
			}
			else
			{
				neighbour = chosenEdge.getFromNode();
			}*/
			

			if(!spanningTree.contains(neighbour)){
				spanningTree.add(neighbour);
			}

			selectedNode = neighbour;
		}

	return spanningTree;
}
}
