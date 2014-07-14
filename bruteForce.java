
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
	public boolean canSolve;
	public int deleteIndex = 0; // The index that represents the only color that paths may be deleted from at one time.

	// keeps track of the number of times a path has been considered 

	public bruteForce() throws InterruptedException
	{
		new Frame(frameWidth ,frameHieght);
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
//		for(int i = 0; i < 10; i++)
//		{
		swwitch();
		canSolve = isSolvable(0);
//		  if(canSolve)
//		    break;
//		  
//		  for(int x = 0; x < buck[0].length; x++)
//		    if(buck[i][x] != null) 
//			  for(int u = 0; u < buck[0][x].size(); u++)
//			    {
//				  buck[0][x].remove(u);
//				  break;
//				}
//		}
		System.out.println(canSolve);
		
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
	  
//	  if(isEmpty(i))
//	    return false;

	  if(_pathsPlaced[i] != null)
		  clearPath(_pathsPlaced[i]);

	  for(int x = 0; x < buck[i].length; x++)
	   if(buck[i][x] != null) 
		for(int u = 0; u < buck[i][x].size(); u++)
		{
//		  if(isEmpty(i))
//		    return false;
		  if(!canGo( buck[i][x].get(u)) ) // if the current path didn't work out
		  {
		    clearPath(buck[i][x].get(u)); // Clear the path
		  }
		  else 
		  {
			  if(isSolved())
				  return true;

			  _pathsPlaced[i] = buck[i][x].get(u);

			  if(!isSolvable((i+1)%buck.length)); // move on to the next path and try it
				  // continue
			  else
				  return true;
			  
 			  if(isLast(i,x,u))
			  {
				clearGrid();
				return false;
			  }
		  }
		}
	  // If none of the paths worked
	    //clear the previous one
	    //then attempt again
	  if(isSolved())
		  return true;

	  if(isLast(_pathsPlaced[i],i))
	  {			
		clearGrid();
		return false;
	  }

	   	return isSolvable((i+1)%buck.length);		
	}
	
	private boolean isLast(ArrayList<Gridspot> a, int i)
	{
		int outerI = i; int midI; int innerI;
		for(int u = 0; u < buck[i].length; u++)
		{
		  if(buck[i][u] != null)
		  {
		    for(int y = 0; y < buck[i][u].size(); y++)
			{
			  if(buck[i][u].get(y) == a)
			    return isLast(i,u,y);
			}
		  }
		}
		return true;		
	}

	private boolean isLast(int i, int c, int colI)
	{
	  ArrayList<ArrayList<Gridspot>>[] left = buck[i];
	  
	  if( colI != left[c].size() - 1 )// If current path is not the last in its bucket
          return false;
	  
	  for(int u = c; u < left.length; u++)
	  {
         if(left[u] != null)
           return false;         
	  }
	  return true;
	}
	
	private void swwitch()
	{
	  ArrayList<ArrayList<Gridspot>> foundFirst = buck[0][0];
      for(int x = 0; x < buck[0].length; x++)
	  {
		if(buck[0][x] != null)
		{
		  foundFirst = buck[0][x];
		  break;
		}
	  }
      
      for(int i = 0; i < foundFirst.size(); i++)
      {
    	  if (hasTwo(foundFirst.get(i)))
    	  {
    		  ArrayList<Gridspot> temp = foundFirst.get(i);
    		  foundFirst.set(i, foundFirst.get(0));
    		  foundFirst.set(0, temp);    		  
    	  }
      }
      
      // Find the first bucket that is not null. 
      // i.e. the bucket with the shortest length paths
      
      ArrayList<ArrayList<Gridspot>> foundSeco = buck[1][0];
      for(int i = 0; i < buck[1].length; i++)
      {
  		if(buck[1][i] != null)
  		{
  		  foundSeco = buck[1][i];
  		  break;
  		} 
      }
      
      for(int i = 0; i < foundSeco.size(); i++)
      {
    	  if (hasOne(foundSeco.get(i)))
    	  {
    		  ArrayList<Gridspot> temp = foundSeco.get(i);
    		  foundSeco.set(i, foundSeco.get(0));
    		  foundSeco.set(0, temp);    		  
    	  }
      }
      
	}
	
	private boolean hasOne(ArrayList<Gridspot> a)
	{
		for(int i = 0; i < a.size(); i++)
		{
			if(a.get(i).getXIndex() == 2 && a.get(i).getYIndex() == 1)
				return true;
		}
		return false;
	}
	
	private boolean hasTwo (ArrayList<Gridspot> a)
	{
		boolean hasSb = false;
		boolean hasFb = false;
		
		for(int i = 0; i < a.size(); i++)
		{
			if(a.get(i).getXIndex() == 3 && a.get(i).getYIndex() == 4)
				hasSb = true;
			if(a.get(i).getXIndex() == 4 && a.get(i).getYIndex() == 4)
				hasFb = true;
		}
		return hasFb && hasSb;
	}
	
	private int bucketIndex(int colorI, ArrayList<Gridspot> chck)
	{
		for(int i = 0; i < buck[colorI].length; i++)
		  if(buck[colorI][i] != null) 
		    for(int u = 0; u < buck[colorI][i].size(); u++)
				if(buck[colorI][i].get(u) == chck)
					return i;
		
//		returns the index of the particular bucket of the color
		return -1;
	}
	
	private boolean isEmpty(int u)
	{
//		checks if all the paths of a certain color have been deleted or not 
		for(int i = 0; i < buck[u].length; i++)
		  if(buck[u][i] != null) 
		    for(int t = 0; t < buck[u][i].size(); t++)
		    {
		      if( buck[u][i].size() > 0 )
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
				System.out.println("returning false");
				Thread.sleep(200);
				return false;
			}
			while(curr != _find._pairs.get(i).r)
			{
				Gridspot temp = curr;
				curr = findAdj(curr,prev);	
				prev = temp;
//				System.out.println("curr is "+curr.getXIndex()+" , "+curr.getYIndex());
//				System.out.println("end is "+_find._pairs.get(i).r.getXIndex()+" , "+_find._pairs.get(i).r.getYIndex());

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
	
	private boolean isFilled()
	{
		Gridspot[][] grid = _find.a._screen.getGrid();
		for(int i = 0; i < grid.length; i++ )
		{
			for(int u = 0; u < grid[i].length; u++)
			{
				if(!grid[i][u].occupied)
					return false;
			}
		}
		return true;
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
//		new Frame(frameWidth ,frameHieght);
		new bruteForce();
	}

}

/*
 * Tasks :
 *    1. Write a more comprehensive verification that the puzzle is solved
 *       a. Options :
 *            - keep a data structure to store a boolean saying whether or not the path is cleared
 *            - write a check to see if every original can be traced back to its other pair
 *    2. Investigate why the puzzle selectively reports the solution 
 *       a. Explore guesses
 *            -   The paths are shuffled differently every time
 *            -   MAybe the first one must be the absolute first/ shortest
 * 
 *   3. Implement configuration task
 */



