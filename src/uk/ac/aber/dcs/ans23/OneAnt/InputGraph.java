package uk.ac.aber.dcs.ans23.OneAnt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFileChooser;

/**
 * This class is used to import an input graph from a file in the format that is described in the report.
 * @author Andreas O'Brien Svingeseth (ans23@aber.ac.uk)
 * @version 1.0 final
 */
public class InputGraph {
	private ArrayList<Node> inputGraph = new ArrayList<Node>();
	private int numberOfEdges;
	public InputGraph(){
		numberOfEdges = 0;
		JFileChooser chooser = new JFileChooser();
		chooser.setDialogType(JFileChooser.OPEN_DIALOG);
		chooser.showDialog(null, "Open input graph file");
		File selectedFile = chooser.getSelectedFile();
		try {
			BufferedReader br = new BufferedReader(new FileReader(selectedFile));

			while(br.ready()){
				String s = br.readLine();
				String[] split = s.split(" ");

				Node a = new Node(split[0]);
				Node b = new Node(split[2]);

				int weight = Integer.parseInt(split[1]);
				
				Edge edge = new Edge(a, b, weight);
				
				boolean containsA = false;
				boolean containsB = false;
				
				//TODO lage datastruktur til å holde inputGraph, node plus edges til node
				//Check if graph contains node
				for(Node n : inputGraph)
				{
					if(n.equals(a))
					{
						containsA = true;
						if(!n.hasEdge(edge))
						{
							inputGraph.get(inputGraph.indexOf(n)).addEdge(edge);
							numberOfEdges++;
						}
					}
					if(n.equals(b))
					{
						containsB = true;
						if(!n.hasEdge(edge))
						{
							inputGraph.get(inputGraph.indexOf(n)).addEdge(edge);
							numberOfEdges++;
						}
					}
				}
				
	
				if(!containsA)
				{
					a.addEdge(edge);
					numberOfEdges++;
					inputGraph.add(a);
				}

				if(!containsB)
				{
					b.addEdge(edge);
					numberOfEdges++;
					inputGraph.add(b);
				}
			}

			br.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("Number of nodes: " + inputGraph.size());
		System.out.println("Number of edges: " + numberOfEdges);
	}

	public int getNumberOfEdges(){
		return numberOfEdges;
	}
	
	public ArrayList<Node> getInputGraph(){
		return inputGraph;
	}
}
