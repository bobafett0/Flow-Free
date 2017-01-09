package module.src.main.java;


import java.awt.Color;
import java.awt.List;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.Stack;
import wheelsunh.users.Frame;

public class Grid {
	private GridSpot[][] _gridSpotArray;
	private ArrayList<StartingPair> _startingPairs;
	private Color[] colors = { Color.blue, Color.cyan, Color.white,
			Color.darkGray,	Color.green, Color.magenta, Color.orange, 
			Color.gray, Color.red, Color.yellow, Color.black,
			Color.pink, Color.lightGray };
	private Stack<GridSpot> _spaces;
	public int cIrcleH, _squWidth;

	public Grid ()
	{		
//		GridSpotArray = new Gridspot[][];
	}

	public GridSpot[][] GetGrid()
	{
		return _gridSpotArray;
	}

	public ArrayList<StartingPair> GetStartingPairs()
	{
		return _startingPairs;
	}
	
	public void initialize(int squWidth) throws Exception
	{
		GridSpot[][] grid = constructGrid(squWidth);
		ArrayList<GridSpot> gridSpotsToBeStartingPairs = getRandomGridSpotsToBeStartingPairs(grid,squWidth);
		_startingPairs = generateStartingPairs(gridSpotsToBeStartingPairs);
		_gridSpotArray = grid;
	}

	public void initialize(int squWidth,int numStartingPairs) throws Exception
	{
		GridSpot[][] grid = constructGrid(squWidth);
		ArrayList<GridSpot> gridSpotsToBeStartingPairs = getRandomGridSpotsToBeStartingPairs(grid,numStartingPairs);
		_startingPairs = generateStartingPairs(gridSpotsToBeStartingPairs);
		_gridSpotArray = grid;
	}
	
	public void initialize(int squWidth , ArrayList<Pair<Integer,Integer>> intStartingPairs) throws Exception
	{
		GridSpot[][] grid = constructGrid(squWidth);
		_gridSpotArray = grid;
		ArrayList<GridSpot> gridSpotsToBeStartingPairs = generateDefinedStartingPairs(intStartingPairs);
		_startingPairs = generateStartingPairs(gridSpotsToBeStartingPairs);
	}

	private ArrayList<GridSpot> generateDefinedStartingPairs ( ArrayList<Pair<Integer,Integer>> startingPairs) throws Exception
	{
		ArrayList<GridSpot> gridSpotsToBeMadeIntoPairs = new ArrayList<GridSpot>();
		for(int i = 0; i < startingPairs.size(); i++){
			Pair<Integer,Integer> curPair = startingPairs.get(i);
			gridSpotsToBeMadeIntoPairs.add(_gridSpotArray[curPair.getL()][curPair.getR()]);
		}		
		return gridSpotsToBeMadeIntoPairs;
	}
	
//	Creates the two dimentional array of "gridspots," in other words, 
//	the rectangles that fill up the grid.
	private GridSpot[][] constructGrid (int gridWidth)
	{		
		GridSpot[][] grid = new GridSpot[gridWidth][gridWidth];

		for(int u = 0; u < gridWidth; u++)
		{
			for(int i = 0; i < gridWidth; i++)
			{
				GridSpot gridspot = new GridSpot(u,i);
				grid[u][i] = gridspot;
			}
		}
		return grid;
	}
	
	private ArrayList<StartingPair> generateStartingPairs(ArrayList<GridSpot> gridSpotList) throws Exception
	{
		if(gridSpotList.size() % 2 != 0){
			throw new Exception("gridSpotList is not even");
		}
		
		ArrayList<StartingPair> ret = new ArrayList<StartingPair>();
		for(int i = 0; i < gridSpotList.size(); i=i+2)
		{			
			ret.add(new StartingPair(gridSpotList.get(i),gridSpotList.get(i+1),colors[i%2]));
		}
		return ret;		
	}
	
	private ArrayList<GridSpot> getRandomGridSpotsToBeStartingPairs( GridSpot[][] grid, int numStartingPairs )
	{
		int squWidth = grid.length;
		ArrayList<GridSpot> pairs = new ArrayList<GridSpot>();
		Random rand = new Random();
		GridSpot headSpot, tailSpot;
		int xIndexHeadSpot, yIndexHeadSpot, xIndexTailSpot, yIndexTailSpot;
		for(int i = 0; i < numStartingPairs; i++) {
			xIndexHeadSpot = rand.nextInt(squWidth);
			yIndexHeadSpot = rand.nextInt(squWidth);			
			headSpot = grid[xIndexHeadSpot][yIndexHeadSpot];
			
			xIndexTailSpot =  rand.nextInt(squWidth);
			yIndexTailSpot = rand.nextInt(squWidth);
			tailSpot = grid[xIndexTailSpot][yIndexTailSpot];
			
			if(headSpot != tailSpot && !pairs.contains(headSpot) && !pairs.contains(tailSpot)){
				pairs.add(headSpot);
				pairs.add(tailSpot);
			}
			else{
				i--;
			}
		}
		return pairs;		
	}
//	
//	Takes in two gridspots as arguments, and then calculates the number of units they
//	are apart from each other, having a gridspot count as one unit.
}
