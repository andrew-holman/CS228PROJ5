package edu.iastate.cs228.proj5;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.EmptyStackException;
import java.util.Stack;

import org.junit.Test;

import edu.iastate.cs228.proj5.DFS;
import edu.iastate.cs228.proj5.DiGraph;
import edu.iastate.cs228.proj5.MaxPath;

/**
 * JUnits for this project. If someone has a test to
 * detect a cycle that'd be a great addition, that 
 * was going to be findMaxPathTest5() before I gave up.
 * 
 * @author Steven Sheets
 */
public class Project5JUnits
{

	/**
	 * "If G is null throw an illegal argument exception.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void findMaxPathTest1()
	{
		MaxPath.findMaxPath(null, new Stack<String>());
	}

	/**
	 * If maxPath is null throw an illegal argument exception.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void findMaxPathTest2()
	{
		
		DiGraph<String> G8 = new DiGraph<String>();
		G8.addEdge("S", "A", 6);
		G8.addEdge("S", "B", 10);
		G8.addEdge("S", "G", -5);
		MaxPath.findMaxPath(G8, null);
		
	}

	/**
	 * If maxPath is not empty throw an illegal argument exception
	 */
	@Test(expected = IllegalArgumentException.class)
	public void findMaxPathTest3()
	{
		DiGraph<String> G8 = new DiGraph<String>();
		G8.addEdge("S", "A", 6);
		G8.addEdge("S", "B", 10);
		G8.addEdge("S", "G", -5);
		G8.addEdge("A", "D", 8);
		Stack<String> path = new Stack<String>();
		System.out.println(path.size());
		MaxPath.findMaxPath(G8, path);
		// Now the path is no longer empty, should throw an error if we do it again.
		MaxPath.findMaxPath(G8, path);
	}

	/**
	 * If topoOrder is empty, throw an illegal state exception.
	 */
	@Test(expected = IllegalStateException.class)
	public void findMaxPathTest4()
	{
		DiGraph<String> G8 = new DiGraph<String>();
		Stack<String> path = new Stack<String>();
		MaxPath.findMaxPath(G8, path);
	}

	/**
	 * Maybe you can write one to determine when the graph has a
	 * cycle. It'd be an illegal argument exception, but I gave up.
	 */
	// @Test(expected = IllegalStateException.class)
	public void findMaxPathTest5()
	{
		DiGraph<String> G8 = new DiGraph<String>();
		G8.addEdge("S", "A", 6);
		Stack<String> path = new Stack<String>();
		MaxPath.findMaxPath(G8, path);

		// YEET. Perhaps you'll have better luck than I.
	}

	/**
	 * From Canvas
	 */
	@Test
	public void findMaxPathTest6()
	{
		DiGraph<String> G8 = new DiGraph<String>();
		G8.addEdge("S", "A", 6);
		G8.addEdge("S", "B", 10);
		G8.addEdge("S", "G", -5);
		G8.addEdge("A", "D", 8);
		G8.addEdge("A", "E", 5);
		G8.addEdge("B", "A", -6);
		G8.addEdge("B", "E", 10);
		G8.addEdge("C", "S", 11);
		G8.addEdge("D", "G", 7);
		G8.addEdge("E", "D", 6);
		G8.addEdge("E", "G", 5);
		G8.addEdge("F", "B", -8);
		G8.addEdge("F", "C", 2);

		Stack<String> path = new Stack<String>();
		assertEquals(46 + "", MaxPath.findMaxPath(G8, path) + "");
	}

	/**
	 * Tis just a line. ______________________
	 */
	@Test
	public void findMaxPathTest7()
	{
		DiGraph<String> G8 = new DiGraph<String>();
		G8.addEdge("S", "A", 0);
		G8.addEdge("S", "B", 1000);
		Stack<String> path = new Stack<String>();
		assertEquals(1000 + "", MaxPath.findMaxPath(G8, path) + "");
	}

	/**
	 * Throw an illegal argument exception if aGraph is null.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void depthFirstSearchTest1()
	{
		DFS.depthFirstSearch(null);
	}

	/**
	 * From Canvas.
	 */
	@Test
	public void depthFirstSearchTest2()
	{
		DiGraph<String> G8 = new DiGraph<String>();
		G8.addEdge("S", "A", 6);
		G8.addEdge("S", "B", 10);
		G8.addEdge("S", "G", -5);
		G8.addEdge("A", "D", 8);
		G8.addEdge("A", "E", 5);
		G8.addEdge("B", "A", -6);
		G8.addEdge("B", "E", 10);
		G8.addEdge("C", "S", 11);
		G8.addEdge("D", "G", 7);
		G8.addEdge("E", "D", 6);
		G8.addEdge("E", "G", 5);
		G8.addEdge("F", "B", -8);
		G8.addEdge("F", "C", 2);

		Stack<String> order = DFS.depthFirstSearch(G8);
		assertTrue(order.pop().equals("F"));
		assertTrue(order.pop().equals("C"));
		assertTrue(order.pop().equals("S"));
		assertTrue(order.pop().equals("B"));
		assertTrue(order.pop().equals("A"));
		assertTrue(order.pop().equals("E"));
		assertTrue(order.pop().equals("D"));
		assertTrue(order.pop().equals("G"));

		// This probably not necessary but w/e.
		boolean caught = false;
		try
		{
			order.pop();
		} catch (EmptyStackException ESE)
		{
			caught = true;
		}
		assertTrue(caught);
	}

	/**
	 * Also from Canvas.
	 */
	@Test
	public void depthFirstSearchTest3()
	{
		DiGraph<String> G8 = new DiGraph<String>();
		G8.addEdge("S", "A", 6);
		G8.addEdge("S", "B", 10);
		G8.addEdge("S", "G", -5);
		G8.addEdge("A", "D", 8);
		G8.addEdge("A", "E", 5);
		G8.addEdge("B", "A", -6);
		G8.addEdge("B", "E", 10);
		G8.addEdge("C", "S", 11);
		G8.addEdge("D", "G", 7);
		G8.addEdge("E", "D", 6);
		G8.addEdge("E", "G", 5);
		G8.addEdge("F", "B", -8);
		G8.addEdge("F", "C", 2);

		Stack<String> path = new Stack<String>();
		MaxPath.findMaxPath(G8, path);
		assertTrue(path.pop().equals("F"));
		assertTrue(path.pop().equals("C"));
		assertTrue(path.pop().equals("S"));
		assertTrue(path.pop().equals("B"));
		assertTrue(path.pop().equals("E"));
		assertTrue(path.pop().equals("D"));
		assertTrue(path.pop().equals("G"));
		// This probably not necessary but w/e.
		boolean caught = false;
		try
		{
			path.pop();
		} catch (EmptyStackException ESE)
		{
			caught = true;
		}
		assertTrue(caught);
	}

}