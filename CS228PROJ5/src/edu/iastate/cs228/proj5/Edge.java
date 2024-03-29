package edu.iastate.cs228.proj5;
// This class is complete.

/**
 * An edge class as a pair class with two components of types V and C, where V
 * is a vertex type and C is a cost type.
 */
public class Edge<V, C extends Comparable<? super C>> implements Comparable<Edge<V, C>>
{
  private V node;
  private C cost;

  public Edge(V n, C c)
  {
	node = n;
	cost = c;
  }

  public V getVertex()
  {
	return node;
  }

  public C getCost()
  {
	return cost;
  }

  public int compareTo(Edge<V, C> other)
  {
	return cost.compareTo(other.getCost());
  }

  public String toString()
  {
	return "<" + node.toString() + ", " + cost.toString() + ">";
  }

  public int hashCode()
  {
	return node.hashCode();
  }

  public boolean equals(Object obj)
  {
	if (this == obj)
	  return true;
	if ((obj == null) || (obj.getClass() != this.getClass()))
	  return false;
	Edge<?, ?> test = (Edge<?, ?>) obj;
	return (node == test.node || (node != null && node.equals(test.node)));
	// (node == test.node || (node != null && node.equals(test.node))) &&
	// (cost == test.cost || (cost != null && cost.equals(test.cost)));
  }

}
