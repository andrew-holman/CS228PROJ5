package edu.iastate.cs228.proj5;
/*
 *  @author Andrew Holman
 *
 *
 */

import java.util.HashMap;
import java.util.Iterator;
import java.util.Stack;



public class DFS
{
  /**
   * This method creates a color map and a pred map to do a depth first search on the given graph.
   * @param aGraph Is the graph that is being searched.
   * @return Returns a stack containing the list of all vertices in a topological order.
   */
  public static <V> Stack<V> depthFirstSearch(DiGraph<V> aGraph)
  {
	  if(aGraph == null)
		  throw new IllegalArgumentException();
	  Stack<V> stack = new Stack<V>();
	  HashMap<V, String> color = new HashMap<V, String>();
	  HashMap<V, V> pred = new HashMap<V, V>();
	  
	  for(V w: aGraph.vertices())
	  {
		  pred.put(w, null);
		  color.put(w, "white");
	  }
	  for(V w: aGraph.vertices())
	  {
		  if(color.get(w).equals("white"))
			  if(!visitDFS(aGraph, w, color, pred, stack))
				  return null;
	  }
		  
	  return stack;
  }

  /**
   * Implements an iterative depth-first search algorithm to check if the given graph is acyclical.
   * If the vertex w is gray, then the graph has a cycle. This means that the function would
   * return false. However if a vertex is black then it is pushed onto the stack topoOrder.
   * @param aGraph The graph that is being searched.
   * @param s The individual vertex that is being checked for a cycle.
   * @param color The color of the vertex.
   * @param pred The map that is being used to hold the various vertices.
   * @param topoOrder The stack that holds all the black colored vertices.
   * @return true if the graph has no cycles.
   */
  protected static <V> boolean visitDFS(DiGraph<V> aGraph, V s, HashMap<V, String> color, HashMap<V, V> pred,
	  Stack<V> topoOrder)
  {
	color.put(s, "grey");
	Stack<V> nodeStack = new Stack<V>();
	Stack<Iterator<Edge<V, Integer>>> edgestack = new Stack<Iterator<Edge<V,Integer>>>();
	Iterator<Edge<V,Integer>> siter = aGraph.adjacentTo(s).iterator();
	nodeStack.push(s);
	edgestack.push(siter);
	while(!nodeStack.isEmpty())
	{
		V  c = nodeStack.peek();
		Iterator<Edge<V,Integer>> citer = edgestack.peek();
		if(citer.hasNext())
		{
			V w= citer.next().getVertex();
			if(color.get(w).contentEquals("white"))
			{
				color.put(w, "grey");
				pred.put(w, c);
				Iterator<Edge<V, Integer>> witer = aGraph.adjacentTo(w).iterator();
				nodeStack.push(w);
				edgestack.push(witer);
			}
			else if(color.get(w).equals("grey"))
			{
				while(!topoOrder.isEmpty())
					topoOrder.pop();
				return false;
			}
		}
		else 
		{
			color.put(c, "black");
			nodeStack.pop();
			edgestack.pop();
			topoOrder.push(c);
		}
	}
	return true;
  }
}
