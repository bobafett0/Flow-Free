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
	public static int frameWidth = 800, frameHieght = 800;
	private GridSpot[][] GridSpotArray;
	private ArrayList<StartingPair> startingPairs;
	public int cIrcleH, _squWidth;
	public Color[] colors = { Color.blue, Color.cyan, Color.white,
			Color.darkGray,	Color.green, Color.magenta, Color.orange, 
			Color.gray, Color.red, Color.yellow, Color.black,
			Color.pink, Color.lightGray };
	private Stack<GridSpot> _spaces;
		
	public Grid ()
	{		
//		GridSpotArray = new Gridspot[][];
	}

	public GridSpot[][] getGrid()
	{
		return GridSpotArray;
	}

	public ArrayList<StartingPair> getStartingPairs()
	{
		return startingPairs;
	}
	
	public void initialize(int squWidth)
	{
		GridSpot[][] grid = constructGrid(squWidth);
		startingPairs = generateRandomStartingPairs(grid,squWidth);
	}
	
	public void initialize(int squWidth , ArrayList<StartingPair> startingPairs)
	{
		GridSpot[][] grid = constructGrid(squWidth);
		this.startingPairs = startingPairs;
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

	private ArrayList<StartingPair> generateRandomStartingPairs( GridSpot[][] grid, int numStartingPairs )
	{
		int squWidth = grid.length;
		ArrayList<StartingPair> pairs = new ArrayList<StartingPair>();
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
			
			if(headSpot != tailSpot){
				pairs.add(new StartingPair(headSpot,tailSpot,colors[i]));
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
