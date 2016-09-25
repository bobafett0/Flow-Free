import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.PriorityQueue;

import wheelsunh.users.Frame;


public class pathFinder {

	public solver a;
	private static int frameWidth = 800, frameHieght = 800;
	public ArrayList<pair<Gridspot,Gridspot>> _pairs;
	private HashSet<ArrayList<Gridspot>> _paths;	
	private ArrayList<ArrayList<Gridspot>> _reduxed;
	private ArrayList<Gridspot>[] sorted;
	public  ArrayList<ArrayList<Gridspot>>[] _hashSorted;
	public  ArrayList<ArrayList<Gridspot>>[][] _colors;
	public boolean isSolve = true;
//	private HashSet<ArrayList<pair<Integer,Integer> > > _paths;	
	
	public pathFinder() throws InterruptedException
	{
		try {
			a = new solver();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(!a.isSolvable)
		{
			isSolve = false;
			return;
		}
		_pairs = a._pairs;
		_paths = new HashSet<ArrayList<Gridspot>>();
		PriorityQueue<pairI<Integer,Gridspot>>[][] wayPoints = new PriorityQueue[a._grid.length][a._grid.length];

		for(int i = 0; i < _pairs.size(); i++)
		{
		System.out.println(i);
		a.assignQuacks(_pairs.get(i));
		obtainQueues(wayPoints);
		pathCol(wayPoints,_pairs.get(i).getL(),_pairs.get(i).getR(),new ArrayList<Gridspot>());
		
		}
	    reducePaths();
	    double startTime = System.nanoTime();
	    _hashSorted = hashSort();
	    double endTime = System.nanoTime();
	    System.out.println(endTime - startTime);
	    
	    sorted = sortPaths();
	    _colors = ((ArrayList<ArrayList<Gridspot>>[][])new ArrayList[_pairs.size()][]); 
		System.out.println(_hashSorted.length);
		for(int i = 0; i < _pairs.size(); i++)
		  _colors[i] = (ArrayList<ArrayList<Gridspot>>[])new ArrayList[_hashSorted.length]; 
		    
	    organizeHash();	    
	}
	
	private void showOrgan(ArrayList<ArrayList<Gridspot>>[][] a) throws InterruptedException
	{
	  for(int i = 0; i < a.length; i++)
	    for(int u = 0; u < a[i].length; u++)
	      if(a[i][u] != null)
	      for(int y = 0; y < a[i][u].size(); y++)
	      {
	        for(int e = 0; e < a[i][u].get(y).size(); e++)
	        {
	          a[i][u].get(y).get(e).setOcc(a[i][u].get(y).get(0).getColor());
			  Thread.sleep(200);
	        }
	        for(int e = a[i][u].get(y).size()-2; e > 0; e--)
	        {
	          a[i][u].get(y).get(e).setUnOcc();
	        }
	        
	      }
	}
	
	private void organizeHash()
	{
	  for(int i = 0; i < _hashSorted.length ;i++)
	    if( _hashSorted[i] != null)
		  for(int x = 0; x < _hashSorted[i].size();x++)
		  {
		    if (_colors[matchColor(_hashSorted[i].get(x).get(0).getColor())][i] == null)
		      _colors[matchColor(_hashSorted[i].get(x).get(0).getColor())][i] = new ArrayList<ArrayList<Gridspot>>();
		    	  
		      _colors[matchColor(_hashSorted[i].get(x).get(0).getColor())][i].add(_hashSorted[i].get(x));
			}
	}
	
	private int matchColor(Color match)
	{
		for(int i = 0; i < _pairs.size(); i++)
		{
			if(_pairs.get(i).getL().getColor() == match)
			  return i;
		}
		return -1;
	}
	
	
	private void showHashShort( ArrayList<ArrayList<Gridspot>>[] hashSorted) throws InterruptedException
	{
		for(int i = 0; i < hashSorted.length ;i++)
		  if( hashSorted[i] != null)
			for(int x = 0; x < hashSorted[i].size();x++)
			{
				for(int u = 0; u < hashSorted[i].get(x).size(); u++)
				{
					hashSorted[i].get(x).get(u).setOcc(hashSorted[i].get(x).get(0).getColor());
					Thread.sleep(200);
				}
				for(int u = hashSorted[i].get(x).size()-2; u > 0; u--)
				{
					hashSorted[i].get(x).get(u).setUnOcc();
					Thread.sleep(200);
				}
			}
	}
	
	private void showHashLong( ArrayList<ArrayList<Gridspot>>[] hashSorted) throws InterruptedException
	{
		for(int i = hashSorted.length-1; i >= 0 ;i--)
		  if( hashSorted[i] != null)
			for(int x = 0; x < hashSorted[i].size();x++)
			{
				for(int u = 0; u < hashSorted[i].get(x).size(); u++)
				{
					hashSorted[i].get(x).get(u).setOcc(hashSorted[i].get(x).get(0).getColor());
					Thread.sleep(200);
				}
				for(int u = hashSorted[i].get(x).size()-2; u > 0; u--)
				{
					hashSorted[i].get(x).get(u).setUnOcc();
					Thread.sleep(200);
				}
			}
	}
	
	
	private void showSorted() throws InterruptedException
	{
		for(int i = 0; i < sorted.length; i++)
		{
			for(int u = 0; u < sorted[i].size(); u++)
			{
				sorted[i].get(u).setOcc(sorted[i].get(0).getColor());
	    		Thread.sleep(200);
			}
			for(int u = sorted[i].size()-2; u > 0; u--)
			{
				sorted[i].get(u).setUnOcc();
	    		Thread.sleep(200);
			}
		}
	}
	private void showPaths() throws InterruptedException
	{
		Iterator<ArrayList<Gridspot>> it = _paths.iterator();
	    while (it.hasNext()) {
	    	ArrayList<Gridspot> temp = it.next();
	    	for(int i = 0; i < temp.size(); i++)
	    	{
	    		temp.get(i).setOcc(temp.get(0).getColor());
	    		Thread.sleep(200);
	    		
	    	}
	    	for(int i = temp.size()-2; i > 0 ; i--)
	    	{
	    		temp.get(i).setUnOcc();	    	
	    		Thread.sleep(200);
	    	}
	    	
	    }
	}
	
	private ArrayList<ArrayList<Gridspot>>[] hashSort()
	{
		int min = findMin(sorted);
		int max = findMax(sorted);
		ArrayList<ArrayList<Gridspot>>[] hash = (ArrayList<ArrayList<Gridspot>>[])new ArrayList[max-min+1];
		for(int i = 0; i < sorted.length; i++)
		{

			if (hash[sorted[i].size()-min] == null)
			{
				hash[sorted[i].size()-min] = new ArrayList<ArrayList<Gridspot>>();
			}
			hash[sorted[i].size()-min].add(sorted[i]); 
		}
		return hash;
	}
	
	private int findMax (ArrayList<Gridspot>[] search)
	{
		int max = search[0].size();
		for(int i = 1; i < search.length; i++)
		{
			if(max < search[i].size())
				max = search[i].size();
		}
		return max;
	}
	
	private int findMin (ArrayList<Gridspot>[] search)
	{
		int min = search[0].size();
		for(int i = 1; i < search.length; i++)
		{
			if(min > search[i].size())
				min = search[i].size();
		}
		return min;
	}
	
	
	private ArrayList<Gridspot>[] sortPaths()
	{
		for(int i = 0; i < sorted.length; i++)
		{
			for(int u = i; u < sorted.length; u++)
			{
				if(((ArrayList<Gridspot>) sorted[u]).size() > ((ArrayList<Gridspot>) sorted[i]).size())
				{
					ArrayList<Gridspot> tom = (ArrayList<Gridspot>)sorted[u];
					sorted[u] = sorted[i];
					sorted[i] = tom;
				}
			}
		}
		return (ArrayList<Gridspot>[])sorted;
	}
	
	private void reducePaths()
	{
		HashSet<ArrayList<Gridspot>> reduced = new HashSet<ArrayList<Gridspot>>();
		Iterator<ArrayList<Gridspot>> it = _paths.iterator();
	    while (it.hasNext()) {
	    	ArrayList<Gridspot> temp = it.next();
	    	int max = obtainMax(temp.get(temp.size()-1),temp.get(0));
	    	if( temp.size() <= max*max)
	    	{
	    		reduced.add(temp);
	    	}
	    }
	    _paths = reduced;
	    sorted = (ArrayList<Gridspot>[])new ArrayList[_paths.size()];
	    
	    it = _paths.iterator();
	    int index = 0;
	    while (it.hasNext()) {
//	    	System.out.println("dfsf");
	    	ArrayList<Gridspot> temp = it.next();
	    	sorted[index] = temp;
	    	index++;
	    }
	}
	
	private int obtainMax(Gridspot left, Gridspot right)
	{
		for(int i = 0; i < a._pairs.size(); i++)
		{
			if (a._pairs.get(i).l == left && a._pairs.get(i).r == right )
				return a._max.get(a._pairs.get(i));
				
		}
		return -1;
	}
	
	private void obtainQueues(PriorityQueue<pairI<Integer,Gridspot>>[][] wayPoints) 
	{
		for(int i = 0; i < wayPoints.length; i++)
		{
			for(int u = 0; u < wayPoints.length; u++)
			{
				wayPoints[i][u] = new PriorityQueue(a._grid[i][u].pQuack);
				a._grid[i][u].clearQuack();
			}
		}
	}
	
	private PriorityQueue<pairI<Integer,Gridspot>>[][] copy(PriorityQueue<pairI<Integer,Gridspot>>[][] wayPoints)
	{
		PriorityQueue<pairI<Integer,Gridspot>>[][] temp = new PriorityQueue[wayPoints.length][wayPoints.length];
		for(int i = 0; i < wayPoints.length; i++)
			for(int u = 0; u < wayPoints.length; u++)
			{
				temp[i][u] = new PriorityQueue(wayPoints[i][u]);
			}
		return temp;		
	}
// Main logic of class
	
	public void pathCol(PriorityQueue<pairI<Integer,Gridspot>>[][] wayPoints , Gridspot from , Gridspot goal, ArrayList<Gridspot> prevs)
	{
		HashSet<Gridspot> aBop = new HashSet<Gridspot>();
		prevs = new ArrayList<Gridspot>(prevs);
		prevs.add(goal);
		while(!wayPoints[goal.getXIndex()][goal.getYIndex()].isEmpty() )//&& counter < 5)
		{
			aBop.add(wayPoints[goal.getXIndex()][goal.getYIndex()].poll().getR());
		}
		// The goal is the variable parameter, the from is the constant
		Iterator<Gridspot> it = aBop.iterator();
	    while (it.hasNext()) {
	    	Gridspot temp = it.next();
	    	// Adding prevs + optimalPath from last
	    	ArrayList<Gridspot> gotcha = new ArrayList<Gridspot>(prevs);
	    	ArrayList<Gridspot> holder = optimalPath(from,temp, wayPoints);
	    	if(holder != null)
	    	{
	    	for(int i = 0; i < holder.size(); i++)
	    	{
	    		gotcha.add(holder.get(i));
	    	}
	    	_paths.add(gotcha);
	    	}
	    	pathCol(copy(wayPoints),from , temp, new ArrayList<Gridspot>(prevs));
	        it.remove(); // avoids a ConcurrentModificationException
	    }
	}
	
	private ArrayList<Gridspot> optimalPath(Gridspot from, Gridspot goal, PriorityQueue<pairI<Integer,Gridspot>>[][] wayPoints )
	{
		ArrayList<Gridspot> bob = new ArrayList<Gridspot>();
		if(goal == from)
		{
			bob.add(from);
			return bob;
		}
		else
			return null;		
	}
	
	// End of main class logic
	
	private boolean check(Gridspot from, Gridspot to)
	{
		if( a.getGridSpot(from.getXIndex()-1, from.getYIndex()) != null && a.getGridSpot(from.getXIndex()-1, from.getYIndex()) == to)
		{
			return true;
		}
		else if( a.getGridSpot(from.getXIndex()+1, from.getYIndex()) != null && a.getGridSpot(from.getXIndex()+1, from.getYIndex()) == to)
		{
			return true;
		}
		else if( a.getGridSpot(from.getXIndex(), from.getYIndex()+1) != null && a.getGridSpot(from.getXIndex(), from.getYIndex()+1) == to)
		{
			return true;
		}
		else if( a.getGridSpot(from.getXIndex(), from.getYIndex()-1) != null && a.getGridSpot(from.getXIndex(), from.getYIndex()-1) == to)
		{
			return true;
		}
		else
		return false;
	}

	public static void main (String args[]) throws InterruptedException
	{
		new Frame(frameWidth ,frameHieght);
		new pathFinder();
	}
}
