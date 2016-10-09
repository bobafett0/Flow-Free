package module.src.main.java;
import java.awt.Color;
 import java.util.ArrayList;
 import java.util.HashMap;
 import java.util.PriorityQueue;
  
 public class GridSpot {
 	public boolean occupied = false;
 	private int _Xindex, _Yindex;
 	private int _recSize;
 	public PriorityQueue<pairI<Integer,GridSpot>> pQuack;
 	public HashMap<Integer,Path> _map;
 	public HashMap<pair<GridSpot,GridSpot>,PriorityQueue<pairI<Integer,GridSpot>>> _pQuacks;
 	public HashMap<Integer,ArrayList<pair<Integer,Integer>>> tuene;
 	public HashMap<pair<GridSpot,GridSpot>,PriorityQueue<pairI<Integer,pair<Integer,Integer>>>> _tuenes;
 
 	// Constructor with location arguments
 	public GridSpot(int xIndex,int yIndex)
 	{
		 _Xindex = xIndex;
		 _Yindex = yIndex;
 		pQuack = new PriorityQueue<pairI<Integer,GridSpot>>();
 		_pQuacks = new HashMap<pair<GridSpot,GridSpot>,PriorityQueue<pairI<Integer,GridSpot>>>();
 		_map = new HashMap<Integer,Path>(); 		
 	}
 	/*
 	 * pQuack methods
 	 */
 	
 	// Adds elements to the priority queue
 	public boolean insert(pairI<Integer,GridSpot> cur)
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
// 	public pairI<Integer,GridSpot> retrieve()
// 	{
// 		if(pQuack.size() > 0)
// 			return pQuack.peek();
// 		else
// 			return new pairI<Integer,GridSpot>(new Integer(4),new GridSpot());
// 	}
 	
 	public int sizeQuack()
 	{
 		return pQuack.size();
 	}
 	
 	public pairI<Integer,GridSpot> popTop()
 	{
 		return pQuack.poll();
 	}
 	public void assignQuack(pair<GridSpot,GridSpot> a)
 	{
 		pQuack = new PriorityQueue<pairI<Integer,GridSpot>>(_pQuacks.get(a));
 	}
 	
 	public void clearQuack()
 	{
 		pQuack.clear();
 	}
 	
 	public void storeQuack(pair<GridSpot,GridSpot> a)
 	{
 		_pQuacks.put(a, new PriorityQueue<pairI<Integer,GridSpot>>(pQuack));
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
