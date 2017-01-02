package module.src.main.java;
import java.awt.Color;
 import java.util.ArrayList;
 import java.util.HashMap;
 import java.util.PriorityQueue;
  
 public class GridSpot {
 	public boolean occupied = false;
 	public int XIndex, YIndex;
 	private int _recSize;
 	public PriorityQueue<PairI<Integer,GridSpot>> pQuack;
 	public HashMap<Integer,Path> _map;
 	public HashMap<Pair<GridSpot,GridSpot>,PriorityQueue<PairI<Integer,GridSpot>>> _pQuacks;
 	public HashMap<Integer,ArrayList<Pair<Integer,Integer>>> tuene;
 	public HashMap<Pair<GridSpot,GridSpot>,PriorityQueue<PairI<Integer,Pair<Integer,Integer>>>> _tuenes;

 	// Constructor with location arguments
 	public GridSpot(int xIndex,int yIndex)
 	{
		XIndex = xIndex;
		YIndex = yIndex;
 		pQuack = new PriorityQueue<PairI<Integer,GridSpot>>();
 		_pQuacks = new HashMap<Pair<GridSpot,GridSpot>,PriorityQueue<PairI<Integer,GridSpot>>>();
 		_map = new HashMap<Integer,Path>(); 		
 	}
 	/*
 	 * pQuack methods
 	 */
 	
 	// Adds elements to the priority queue
 	public boolean insert(PairI<Integer,GridSpot> cur)
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
 	
 	public int sizeQuack()
 	{
 		return pQuack.size();
 	}
 	
 	public PairI<Integer,GridSpot> popTop()
 	{
 		return pQuack.poll();
 	}
 	public void assignQuack(Pair<GridSpot,GridSpot> a)
 	{
 		pQuack = new PriorityQueue<PairI<Integer,GridSpot>>(_pQuacks.get(a));
 	}
 	
 	public void clearQuack()
 	{
 		pQuack.clear();
 	}
 	
 	public void storeQuack(Pair<GridSpot,GridSpot> a)
 	{
 		_pQuacks.put(a, new PriorityQueue<PairI<Integer,GridSpot>>(pQuack));
 		clearQuack();
 	}
 	
 	
 	/*
 	 * reddy methods
 	 */ 	
 	 	
 	public void setIndex(int x, int y)
 	{
 		XIndex = x; YIndex = y;
 	}
 	public int getXIndex()
 	{
 		return XIndex;
 	}
 	public int getYIndex()
 	{
 		return YIndex;
 	}

 }
