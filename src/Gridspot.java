import java.awt.Color;
 import java.util.ArrayList;
 import java.util.HashMap;
 import java.util.PriorityQueue;
  
 public class Gridspot {
 	public boolean occupied = false;
 	private int _Xindex, _Yindex;
 	private int _recSize;
 	public PriorityQueue<pairI<Integer,Gridspot>> pQuack;
 	public HashMap<Integer,Path> _map;
 	public HashMap<pair<Gridspot,Gridspot>,PriorityQueue<pairI<Integer,Gridspot>>> _pQuacks;
 	public HashMap<Integer,ArrayList<pair<Integer,Integer>>> tuene;
 	public HashMap<pair<Gridspot,Gridspot>,PriorityQueue<pairI<Integer,pair<Integer,Integer>>>> _tuenes;
 
 	// Constructor with location arguments
 	public Gridspot(int xIndex,int yIndex)
 	{
		 _Xindex = xIndex;
		 _Yindex = yIndex;
 		pQuack = new PriorityQueue<pairI<Integer,Gridspot>>();
 		_pQuacks = new HashMap<pair<Gridspot,Gridspot>,PriorityQueue<pairI<Integer,Gridspot>>>();
 		_map = new HashMap<Integer,Path>(); 		
 	}
 	/*
 	 * pQuack methods
 	 */
 	
 	// Adds elements to the priority queue
 	public boolean insert(pairI<Integer,Gridspot> cur)
 	{
 		if (_map.get(cur.getL()) == null)
 		{
 			_map.put(cur.getL(), new Path());
 			_map.get(cur.getL()).add(cur.getR());
 		}
 		else
 			_map.get(cur.getL()).add(cur.getR());
 		
 		return pQuack.add(cur);
 	}
// 	public pairI<Integer,Gridspot> retrieve()
// 	{
// 		if(pQuack.size() > 0)
// 			return pQuack.peek();
// 		else
// 			return new pairI<Integer,Gridspot>(new Integer(4),new Gridspot());
// 	}
 	
 	public int sizeQuack()
 	{
 		return pQuack.size();
 	}
 	
 	public pairI<Integer,Gridspot> popTop()
 	{
 		return pQuack.poll();
 	}
 	public void assignQuack(pair<Gridspot,Gridspot> a)
 	{
 		pQuack = new PriorityQueue<pairI<Integer,Gridspot>>(_pQuacks.get(a));
 	}
 	
 	public void clearQuack()
 	{
 		pQuack.clear();
 	}
 	
 	public void storeQuack(pair<Gridspot,Gridspot> a)
 	{
 		_pQuacks.put(a, new PriorityQueue<pairI<Integer,Gridspot>>(pQuack));
 		clearQuack();
 	}
 	
 	
 	/*
 	 * reddy methods
 	 */ 	
 	 	
 	public void setIndex(int x, int y)
 	{
 		_Xindex = x; _Yindex = y;
 	}
 	public int getXIndex()
 	{
 		return _Xindex;
 	}
 	public int getYIndex()
 	{
 		return _Yindex;
 	}

 }
