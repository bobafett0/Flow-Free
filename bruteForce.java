import java.util.ArrayList;
import java.util.HashMap;

import wheelsunh.users.Frame;



public class bruteForce {
	private static int frameWidth = 800, frameHieght = 800;
	public pathFinder _find;
	public ArrayList<ArrayList<Gridspot>>[] _buckets;
	public int[] _indexes;
	public ArrayList<ArrayList<Gridspot>>[][] buck;
	public ArrayList<Gridspot>[] _pathsPlaced;
	public HashMap <ArrayList<Gridspot>,Integer> _visited; 
	// keeps track of the number of times a path has been considered 

	public bruteForce() throws InterruptedException
	{
		
		_find = new pathFinder();	
		while(!_find.isSolve)
			_find = new pathFinder();	

		 buck = _find._colors.clone();
		_indexes = new int[buck.length*2];
		for(int i = 0; i < _indexes.length; i++)
		{
			_indexes[i] = 0;
		}
		_pathsPlaced = new ArrayList[buck.length];
		
		_visited = new HashMap <ArrayList<Gridspot>,Integer>();
				
		System.out.println(isSolvable(0));
	}
	
	// Notes:
	/*
	 * Right now, if it cannot find a suitable path, it will simply recurse to the previous, 
	 * and force it to use a different path than before
	 * 
	 * points:
	 * 
	 *  It should only recurse forward, with a modulus
	 *  Meaning, if it is on the last color, it should go back to the first
	 *  
	 *  If a suitable path is not found, it should clear the previous path
	 *  and then retry (recurse with the same value).
	 *  
	 *  isSolvable is a boolean, so it should be treated as such even within its own method
	 *  
	 */
	
	private boolean isSolvable(int i) throws InterruptedException
	{
		
  	  if(_pathsPlaced[i] != null)
		  clearPath(_pathsPlaced[i]);
	  
	  for(int x = 0; x < buck[i].length; x++)
	   if(buck[i][x] != null) 
		for(int u = 0; u < buck[i][x].size(); u++)
		{
		  if(i < buck.length - 1)
		  {
			  if(canGo( buck[i][x].get(u)) )
			  {
				  if(isSolvable(i+1))
					  return true;
				  clearPath(buck[i][x].get(u));
			  }
			  else
				  clearPath(buck[i][x].get(u));
		  }
		  else
		  {
			  if(canGo( buck[i][x].get(u)))
			  {
				  if(isSolved())
					  return true;
			  }
			  else
				  clearPath(buck[i][x].get(u));
		  }
			  
		}
	  if(_pathsPlaced[i] != null)
		  clearPath(_pathsPlaced[i]);
	  return false;
	}
	
	private void clearGrid()
	{
		for(int i = 0; i < _pathsPlaced.length; i++)
		{
			if(_pathsPlaced[i] != null)
			clearPath(_pathsPlaced[i]);
		}
	}
	
	private boolean isCleared(ArrayList<Gridspot> path)
	{
		for(int i = 1; i < path.size()-1; i++)
		{
			if( path.get(i).occupied )
				return false;
		}
		return true;
	}
	

	
	private boolean isSolved() throws InterruptedException
	{
		if(!isFilled())
		  return false;
		
//		  _find._pairs.get(0);
		for(int i = 0; i < _find._pairs.size(); i++)
		{
			Gridspot prev = _find._pairs.get(i).l ;
			Gridspot curr = findAdj(prev,null);
			if(curr == null)
			{
				return false;
			}
			while(curr != _find._pairs.get(i).r)
			{
				Gridspot temp = curr;
				curr = findAdj(curr,prev);	
				prev = temp;

				if(curr == null)
					return false;
			}			
		}		
		return true;		
	}
	
	private Gridspot findAdj(Gridspot a, Gridspot from)
	{
		if ( getG(a.getXIndex()-1,a.getYIndex()).getColor() == a.getColor() && getG(a.getXIndex()-1,a.getYIndex()) != from )
		{
			return getG(a.getXIndex()-1,a.getYIndex());
		}
		else if ( getG(a.getXIndex()+1,a.getYIndex()).getColor() == a.getColor() && getG(a.getXIndex()+1,a.getYIndex()) != from )
		{
			return getG(a.getXIndex()+1,a.getYIndex());
		}
		else if ( getG(a.getXIndex(),a.getYIndex()-1).getColor() == a.getColor() && getG(a.getXIndex(),a.getYIndex()-1) != from )
		{
			return getG(a.getXIndex(),a.getYIndex()-1);
		}
		else if ( getG(a.getXIndex(),a.getYIndex()+1).getColor() == a.getColor() && getG(a.getXIndex(),a.getYIndex()+1) != from )
		{
			return getG(a.getXIndex(),a.getYIndex()+1);
		}
		return null;
	}
	private Gridspot getG(int x, int y)
	{
		if (x >= _find.a._grid.length || x < 0 || y >= _find.a._grid.length || y < 0)
		{
			return new Gridspot();
		}
		return _find.a._grid[x][y];
	}	
	
	private void showSolution(ArrayList<ArrayList<Gridspot>>[][] buck)
	{
		for(int i = 0; i < _indexes.length;i++)
		{
			int fIndx = _indexes[i];
			int sIndx = _indexes[i+1];
			System.out.println("The index is "+i+" The bucket is "+fIndx+ " The path is "+ sIndx);
			showPath(buck[i][fIndx].get(sIndx));
		}
	}
	
	private void showPath(ArrayList<Gridspot> a)
	{
		for(int i = 1; i < a.size()-1; i++)
		{
			a.get(i).setOcc(a.get(0).getColor());
		}
	}
	
	private boolean notStart(Gridspot gridspot) {
		
		for(int i = 0; i < _find.a._pairs.size(); i++)
		{
			if(gridspot == _find.a._pairs.get(i).l || gridspot == _find.a._pairs.get(i).r)
				return false;
		}
		
		return true;
	}

	private void clearPath (ArrayList<Gridspot> path)
	{
		for(int u = 1; u < path.size()-1; u++)
		{
		   if(path.get(u).getColor() == path.get(0).getColor())
		      path.get(u).setUnOcc();
		}
	}
	
	private boolean canGo(ArrayList<Gridspot> path)
	{
		for(int u = 1; u < path.size()-1; u++)
		{
			if(path.get(u).occupied)
				return false;
			else
				path.get(u).setOcc(path.get(0).getColor());
		}
		return true;
	}
	
	
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		new Frame(frameWidth ,frameHieght);
		new bruteForce();
	}

}
