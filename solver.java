import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Stack;

import wheelsunh.users.Frame;

public class solver {

	public Stack<Gridspot> _points, _saved;
	private static int frameWidth = 800, frameHieght = 800;
	public teller _screen;
	public Gridspot[][] _grid;
	private HashMap<Gridspot, Integer> _map;
	public ArrayList<pair<Gridspot,Gridspot>> _pairs;
	private HashMap<pair<Gridspot,Gridspot>,Integer> _min;
	public HashMap<pair<Gridspot,Gridspot>,Integer> _max;
	private HashSet<ArrayList<Gridspot>> _paths;
	public boolean isSolvable = true;

	// What I want - pair<Gridspot,Gridspot> -> max #distance to include
	// Need - set Quacks.
	// Pop elements off the queue and save them into a new priority queue, then
	// stop once the distances are greater than the max
	// assign pQueue pointer to the new pQueue
	// repeat for all Gridspots3
	// Assign the quack
	
	public solver ()
	{
		_screen = new teller(maze3(5), 150);
//		_screen = new teller( maze(5),150);
//		_screen = new teller(150);
		_grid = _screen.getGrid();
		_points = _screen.getpairL();_saved = new Stack<Gridspot>();
		_min = new HashMap<pair<Gridspot,Gridspot>,Integer>(); 
		_pairs = new ArrayList<pair<Gridspot,Gridspot>>();
		_max = new HashMap<pair<Gridspot,Gridspot>,Integer>();
		// Sum is the sum of all the minimum distances 
		int sum = 0;
//		Thread.sleep(1000);
		// These points are the points on the grid, hgence they are Gridspots
		while(!_points.empty())
		{
		_map = new HashMap<Gridspot, Integer>();
		Gridspot temp1 = _points.pop();_saved.push(temp1);
		Gridspot temp2 = _points.pop();_saved.push(temp2);
		
//		temp2.setUnOcc();
//		solve(new pairI<Integer,Gridspot>(new Integer(0),temp1),temp2);
		temp2.setOcc(temp2.getColor());
		if (!_map.containsKey(temp2))
		{
//			System.out.println("The puzzle is unsolvable.");
			isSolvable = false;
			return;
		}
//		ArrayList<Gridspot> temp = optimalPath(temp1,temp2);
//		for(int i = 0; i < temp.size(); i++)
//		{
//			System.out.println(temp.get(i).getXIndex()+ " , "+temp.get(i).getYIndex());
//		}
//		System.out.println("putting "+(temp2.retrieve().getL().intValue()-1)
//				+" into "+temp1.getXIndex()+" , "+temp1.getYIndex());
		_pairs.add(new pair<Gridspot,Gridspot>(temp1,temp2));
		_min.put(_pairs.get(_pairs.size()-1),new Integer(temp2.retrieve().getL().intValue()-1));
		sum = sum+temp2.retrieve().getL().intValue()-1;
//		System.out.println(temp2.retrieve().getL().intValue()-1);
		
//		ArrayList<Gridspot> temp = optimalPath(a.getL(),a.getR());
		
		storeQuacks(_pairs.get(_pairs.size()-1));
		}
		
		_points = _screen.getpairL();
		sum = _grid.length*_grid.length - sum - _saved.size();
		if(sum < 0)
		{
			System.out.println("The puzzle is unsolvable.");
			isSolvable = false;
			return;
		}
//		System.out.println("Sum is :"+sum);
		for(int i = 0; i < _pairs.size(); i++)
		{
//			System.out.print("The maximum Distance for "+_saved.get(i).getXIndex());
//			System.out.print(" , "+_saved.get(i).getYIndex()+" to " );
//			System.out.print(_saved.get(i+1).getXIndex()+" , "+_saved.get(i+1).getYIndex()+" is " );
			_max.put(_pairs.get(i), _min.get(_pairs.get(i)).intValue()+sum);
//			System.out.println(_min.get(_pairs.get(i)).intValue()+sum);
		}
//		reduceQuacks();
		
	}
	
	private void reduceQuacks()
	{
		Object maxs[] = _max.values().toArray();
		Object mins[] = _min.values().toArray();
		for(int x = 0; x < _pairs.size(); x++)
		{
			assignQuacks(_pairs.get(x));
		for(int i = 0; i < _grid.length; i++)
		{
			for(int u = 0; u < _grid.length; u++)
			{
				PriorityQueue<pairI<Integer,Gridspot>> temp  = new PriorityQueue<pairI<Integer,Gridspot>>();
				
//				if (_grid[i][u].pQuack.size() > 0 && _grid[i][u].retrieve().l <= _max.get(_pairs.get(x)))
//				temp.add(_grid[i][u].popTop());
				while( _grid[i][u].pQuack.size() > 0 && _grid[i][u].retrieve().l <= _max.get(_pairs.get(x)))
				{
					temp.add(_grid[i][u].popTop());
				}
				_grid[i][u].pQuack = temp;
				_grid[i][u].storeQuack(_pairs.get(x));
			}
		}
		}
	}
	
	// Error in Store Quacks! the 0,0 only has one in the queue, when it should have MANY!
	
	private void solve(pair<Gridspot,Gridspot> a, int max)
	{
		assignQuacks(a);
		ArrayList<Gridspot> temp = optimalPath(a.getL(),a.getR());
		assignQuacks(a);
		_paths.add(temp);
	}
	
	private ArrayList<Gridspot> optimalPath(Gridspot from, Gridspot goal)
	{
		Gridspot temp = goal;
		ArrayList<Gridspot> bob = new ArrayList<Gridspot>();
		while(temp != from)
		{
			if (temp.sizeQuack() > 0)
			{
				temp = temp.popTop().getR();
				bob.add(temp);
			}
			else 
				break;
		}
		return bob;
	}
	
	private void altPaths (Gridspot cur, Gridspot from, int u,
			ArrayList<Gridspot> path, Gridspot start) throws InterruptedException // from is the goal
	{
		if (cur == from)
		{
			ArrayList<Gridspot> temp = new ArrayList<Gridspot> (path);
			if(path.contains(start))
			_paths.add(temp);
			path.clear();
			return;
		}

		HashSet<Integer> keys = new HashSet(cur._map.keySet());
			for(int i = 0; i < cur._map.get(u).size(); i++)
			{
				path.add(cur);
				altPaths(cur._map.get(u).get(i),from,u-1,path,start);
			}
	}
	
	private ArrayList<pair<pair<Integer,Integer>,pair<Integer,Integer>>> maze(int dem)
	{
		ArrayList<pair<pair<Integer,Integer>,pair<Integer,Integer>>> sam = 
				new ArrayList<pair<pair<Integer,Integer>,pair<Integer,Integer>>>();
//		for(int i = 0; i < dem-1; i++)
//		{
////			System.out.println(i);
//			sam.add(new pair<pair<Integer,Integer>,pair<Integer,Integer>>
//			( new pair<Integer,Integer>(new Integer(i),new Integer(0)),
//			  new pair<Integer,Integer>(new Integer(i),new Integer(dem-1))));
//		}
		sam.add(new pair<pair<Integer,Integer>,pair<Integer,Integer>>
		( new pair<Integer,Integer>(new Integer(0),new Integer(0)),
		  new pair<Integer,Integer>(new Integer(4),new Integer(3))));
		
		sam.add(new pair<pair<Integer,Integer>,pair<Integer,Integer>>
		( new pair<Integer,Integer>(new Integer(0),new Integer(3)),
		  new pair<Integer,Integer>(new Integer(4),new Integer(4))));
		
		sam.add(new pair<pair<Integer,Integer>,pair<Integer,Integer>>
		( new pair<Integer,Integer>(new Integer(2),new Integer(2)),
		  new pair<Integer,Integer>(new Integer(1),new Integer(3))));
		
		sam.add(new pair<pair<Integer,Integer>,pair<Integer,Integer>>
		( new pair<Integer,Integer>(new Integer(4),new Integer(4)),
		  new pair<Integer,Integer>(new Integer(2),new Integer(3))));
		
		return sam;
	}
	
	private ArrayList<pair<pair<Integer,Integer>,pair<Integer,Integer>>> maze2(int dem)
	{
		ArrayList<pair<pair<Integer,Integer>,pair<Integer,Integer>>> sam = 
				new ArrayList<pair<pair<Integer,Integer>,pair<Integer,Integer>>>();
		
		sam.add(new pair<pair<Integer,Integer>,pair<Integer,Integer>>
		( new pair<Integer,Integer>(new Integer(0),new Integer(0)),
		  new pair<Integer,Integer>(new Integer(2),new Integer(3))));
		
		sam.add(new pair<pair<Integer,Integer>,pair<Integer,Integer>>
		( new pair<Integer,Integer>(new Integer(dem-1),new Integer(0)),
		  new pair<Integer,Integer>(new Integer(2),new Integer(dem-1))));
		
		sam.add(new pair<pair<Integer,Integer>,pair<Integer,Integer>>
		( new pair<Integer,Integer>(new Integer(0),new Integer(dem-1)),
		  new pair<Integer,Integer>(new Integer(2),new Integer(2))));
		
		sam.add(new pair<pair<Integer,Integer>,pair<Integer,Integer>>
		( new pair<Integer,Integer>(new Integer(1),new Integer(2)),
		  new pair<Integer,Integer>(new Integer(1),new Integer(dem-1))));
		
		return sam;
	}
	
	private ArrayList<pair<pair<Integer,Integer>,pair<Integer,Integer>>> maze3(int dem)
	{
		ArrayList<pair<pair<Integer,Integer>,pair<Integer,Integer>>> sam = 
				new ArrayList<pair<pair<Integer,Integer>,pair<Integer,Integer>>>();
		
		sam.add(new pair<pair<Integer,Integer>,pair<Integer,Integer>>
		( new pair<Integer,Integer>(new Integer(0),new Integer(4)),
		  new pair<Integer,Integer>(new Integer(2),new Integer(3))));
		
		sam.add(new pair<pair<Integer,Integer>,pair<Integer,Integer>>
		( new pair<Integer,Integer>(new Integer(1),new Integer(1)),
		  new pair<Integer,Integer>(new Integer(1),new Integer(4))));
		
		sam.add(new pair<pair<Integer,Integer>,pair<Integer,Integer>>
		( new pair<Integer,Integer>(new Integer(2),new Integer(2)),
		  new pair<Integer,Integer>(new Integer(3),new Integer(1))));
		
		sam.add(new pair<pair<Integer,Integer>,pair<Integer,Integer>>
		( new pair<Integer,Integer>(new Integer(2),new Integer(4)),
		  new pair<Integer,Integer>(new Integer(4),new Integer(3))));
		
		return sam;
	}
	
	
	
	private void solverDriver()
	{
		for(int i = 0; i < _grid.length; i++)
		{
			for(int u = 0; u < _grid.length; u++)
			{
				if(_map.containsKey(_grid[u][i]))
				{
					_map.put(_grid[u][i], _grid[u][i].retrieve().getL());
				}
			}
		}
	}
	
	private void solve(pairI<Integer,Gridspot> cur, Gridspot goal ) throws InterruptedException
	{
		goal.setUnOcc();
		ArrayList<Gridspot> vertices = findVertices(cur.getR(),goal);
		for(int i = 0; i < vertices.size(); i++)
		{
			if (vertices.get(i) == goal)
			{
//				System.out.println("We have reached out goal at "+goal.getXIndex()+" , "+goal.getYIndex());
			}
			_map.put(vertices.get(i), new Integer(0));
			vertices.get(i).setOcc(goal.getColor());
			vertices.get(i).insert(new pairI<Integer,Gridspot>(new Integer(cur.getL().intValue()+1),cur.getR()));
			solve(new pairI<Integer,Gridspot>(new Integer(cur.getL().intValue()+1),vertices.get(i)),goal);
			vertices.get(i).setUnOcc();
		}
	}
	
	public ArrayList<Gridspot> findVertices(Gridspot take, Gridspot goal)
	{
		ArrayList<Gridspot> retur = new ArrayList<Gridspot>();
		int x = take.getXIndex(); int y = take.getYIndex();
		
		if (!getG(x+1,y).occupied || getG(x+1,y) == goal )
		{
			retur.add(getG(x+1,y));
		}
		if (!getG(x-1,y).occupied || getG(x-1,y) == goal)
		{
			retur.add(getG(x-1,y));
		}
		if (!getG(x,y+1).occupied || getG(x,y+1) == goal)
		{
			retur.add(getG(x,y+1));
		}
		if (!getG(x,y-1).occupied || getG(x,y-1) == goal)
		{
			retur.add(getG(x,y-1));
		}
		return retur;
	}
	
	public void assignQuacks(pair<Gridspot,Gridspot> a)
	{
		for(int i = 0; i < _grid.length; i++)
		{
			for(int u = 0; u < _grid.length; u++)
			{
				_grid[i][u].assignQuack(a);
			}
		}
	}
	
	private void storeQuacks(pair<Gridspot,Gridspot> a)
	{
		for(int i = 0; i < _grid.length; i++)
		{
			for(int u = 0; u < _grid.length; u++)
			{
				_grid[i][u].storeQuack(a);
			}
		}
	}
	
	private Point getC(int x, int y)
	{
		return _grid[x][y].getCenter();
	}
	
	private Gridspot getG(int x, int y)
	{
		if (x >= _grid.length || x < 0 || y >= _grid.length || y < 0)
		{
			return new Gridspot();
		}
		return _grid[x][y];
	}
	
	public Gridspot getGridSpot(int x, int y)
	{
		return _grid[(x-_screen._squWidth/2)/_screen._squWidth]
				[(y-_screen._squWidth/2)/_screen._squWidth];
	}
	
	public static void main (String args[])
	{
		new Frame(frameWidth ,frameHieght);
		new solver();
	}

	//
}
