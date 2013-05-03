package uk.ac.aber.dcs.ans23.OneAnt;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class will contain the one ant, whose destiny is to walk alone, all the
 * edges, all the time, until darkness finds him (or the number of epochs are
 * achieved).
 * 
 * @author aXeroN
 * 
 */
public class TheOneAnt {
	private InputGraph inputGraph;
	private BroderConstruct broderConstruct;
	private ArrayList<Node> broderGraph;
	private double alpha, beta, rho;
	private int epochs;
	private double averageLength;
	private ArrayList<Node> bestPath;
	private int bestTime;
	/**
	 * Constructor for the one ant, used to create the one ant who will walk
	 * them all.
	 */
	public TheOneAnt() {
		inputGraph = new InputGraph();
		bestTime = 1000;
		Scanner in = new Scanner(System.in);
		System.out.println("Please input alpha: ");
		alpha = 1;//Double.parseDouble(in.next());
		System.out.println("Please input beta: ");
		beta = 0;//Double.parseDouble(in.next());
		System.out.println("Please input rho: ");
		rho = 1;//Double.parseDouble(in.next());
		System.out.println("How many epochs do you want the simulation to last?");
		epochs = 1;//in.nextInt();
		in.close();


		broderConstruct = new BroderConstruct(alpha, beta, rho);
		inputGraph = calculateInitialPheromone(inputGraph); 
		
		/*for(Node n : inputGraph.getInputGraph())
		{
			for(Edge e : n.getEdges())
			{
				System.out.println(e.getFromNode().getName() + " " + e.getWeight() + " " + e.getToNode().getName() + " Pvalue: " + e.getPheromoneValue());
			}
		}*/
		
		broderGraph = broderConstruct.constructBroder(inputGraph);
		
		
		
		//oneAntIsEnough();
		//printSolution();
	}

	private void oneAntIsEnough() {
		int epochsPassed = 0;
		while(epochsPassed < epochs){
			inputGraph = calculateInitialPheromone(inputGraph);
			ArrayList<Node> currentSolution = construction(broderGraph, inputGraph);
			int newTime = calculateTime(currentSolution);

			if(newTime < bestTime){
				bestTime = newTime;
				bestPath = currentSolution;
				averageLength += newTime;
			}

			for(int i = 1; i < currentSolution.size(); i++)
			{
				Node v0 = currentSolution.get(i-1);
				Node v1 = currentSolution.get(i);

				Edge edge = v0.getEdge(v1);

				Node o0 = broderGraph.get(broderGraph.indexOf(v0));
				Node o1 = broderGraph.get(broderGraph.indexOf(v1));

				Edge oEdge = o0.getEdge(o1);

				if(edge.getPheromoneValue() < oEdge.getPheromoneValue())
				{
					oEdge.setPheromoneValue(edge.getPheromoneValue());
				}
			}
			epochsPassed++;
		}
	}

	private void printSolution(){
		System.out.println("Best solution: Average time traversing spanning tree (" + averageLength/epochs + ")");
		for(int i = 1; i < bestPath.size(); i++){
			Node n0 = bestPath.get(i-1);
			Node n1 = bestPath.get(i);

			Edge edge = n0.getEdge(n1);

			System.out.println(n0 + " " + edge.getWeight() + " " + n1);
		}
	}

	private ArrayList<Node> construction(ArrayList<Node> constGraph, InputGraph inputGraph){
		ArrayList<Node> path = new ArrayList<Node>();
		int k = 0;
		path.add(constGraph.get(k));

		while(k < constGraph.size()){
			//TODO bruke k+1 også
			double R = 0;
			double neighbourhoodValue = 0;
			Edge chosenEdge = null;

			for(Edge e : constGraph.get(k).getEdges()){
				R += (Math.pow(e.getPheromoneValue(),alpha) * (Math.pow(1/e.getWeight(), beta)));
			}

			for(Edge currEdge : constGraph.get(k).getEdges()){
				if((Math.pow(currEdge.getPheromoneValue(),alpha) * (Math.pow(1/currEdge.getWeight(), beta)))/R > neighbourhoodValue){
					neighbourhoodValue = (Math.pow(currEdge.getPheromoneValue(),alpha) * (Math.pow(1/currEdge.getWeight(), beta)))/R;
					chosenEdge = currEdge;
				}
			}
			Node nextNode = chosenEdge.getToNode();
			path.add(nextNode);
			k++;
		}
		return path;
	}

	private InputGraph calculateInitialPheromone(InputGraph inputGraph){
		double pheromoneValue = 1/(double)inputGraph.getNumberOfEdges();
		for(Node n : inputGraph.getInputGraph()){
			for(Edge e : n.getEdges())
			{
				e.setPheromoneValue(pheromoneValue);
			}
		}
		
		return inputGraph;
	}

	private int calculateTime(ArrayList<Node> path){
		int totalTime = 0;
		for(int i = 1; i < path.size(); i++){
			Node n0 = path.get(i-1);
			Node n1 = path.get(i);

			totalTime += n0.getEdge(n1).getWeight();
		}
		return totalTime;
	}
}
