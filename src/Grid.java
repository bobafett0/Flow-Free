
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
	public Gridspot[][] GridSpotArray;
	public int cIrcleH, _squWidth;
	public ArrayList<pair<Gridspot,Gridspot>> pairList;
	public Color[] colors = { Color.blue, Color.cyan, Color.DARK_GRAY, 
			Color.green, Color.magenta, Color.orange, Color.pink,
			Color.red, Color.yellow, Color.black,Color.gray };
	private Stack<Gridspot> _spaces;
	
	public Grid ()
	{
//		GridSpotArray = new Gridspot[][];
	}
//	public Grid(ArrayList<StartingPair> startingPairs )
//	{
//		game(squWidth);
//		for(int i = 0; i < list.size(); i++)
//		{
//			System.out.println(i);
//			System.out.println(list.get(i).getL().getL().intValue());
//			System.out.println(list.get(i).getL().getR().intValue());
//		Gridspot temp1 = bob[list.get(i).getL().getL().intValue()]
//				[list.get(i).getL().getR().intValue()];
//		Gridspot temp2 = bob[list.get(i).getR().getL().intValue()]
//				[list.get(i).getR().getR().intValue()];
//		temp1.setOcc(colors[_spaces.size()/2]);
//		temp2.setOcc(colors[_spaces.size()/2]);
//		_spaces.push(temp1);_spaces.push(temp2);
//		}
//		
//	}
	
	public void initialize(int squWidth)
	{
		Gridspot[][] grid = getGrid(squWidth);
		ArrayList<StartingPair> startingPairs = generateRandomStartingPairs(grid,squWidth);
		
		
	}
	
	public void initialize(ArrayList<StartingPair> startingPairs, int squWidth)
	{
		Gridspot[][] grid = getGrid(squWidth);
		
		
	}
	
//	Creates the two dimentional array of "gridspots," in other words, 
//	the rectangles that fill up the grid.
	public Gridspot[][] getGrid (int gridWidth)
	{		
		Gridspot[][] grid = new Gridspot[gridWidth][gridWidth];

		for(int u = 0; u < gridWidth; u++)
		{
			for(int i = 0; i < gridWidth; i++)
			{
				Gridspot gridspot = new Gridspot(u,i);
				grid[u][i] = gridspot;
			}
		}
		return grid;
	}
	
	public ArrayList<StartingPair> generateRandomStartingPairs( Gridspot[][] grid, int numStartingPairs )
	{
		int squWidth = grid.length;
		ArrayList<StartingPair> startingPairs = new ArrayList<StartingPair>();
		Random rand = new Random();
		Gridspot headSpot, tailSpot;
		int xIndexHeadSpot, yIndexHeadSpot, xIndexTailSpot, yIndexTailSpot;
		for(int i = 0; i < numStartingPairs; i++) {
			xIndexHeadSpot = rand.nextInt(squWidth);
			yIndexHeadSpot = rand.nextInt(squWidth);			
			headSpot = grid[xIndexHeadSpot][yIndexHeadSpot];
			
			xIndexTailSpot =  rand.nextInt(squWidth);
			yIndexTailSpot = rand.nextInt(squWidth);
			tailSpot = grid[xIndexTailSpot][yIndexTailSpot];
			
			if(headSpot != tailSpot){
				startingPairs.add(new StartingPair(headSpot,tailSpot));
			}
			else{
				i--;
			}
		}
		return startingPairs;		
	}
//	Loops through grid points and makes sure they are not already occupied
//	calls the setCirle method through each iteration
//	private void placeStartingPairs(Gridspot[][] grid, int numStartingPairs) throws InterruptedException
//	{
//		Random indexGenerator = new Random();
//		
//		for(int i = 0; i < numStartingPairs; i++)
//		{
//			Gridspot head = grid[][];
//		}
//		
//		int cap = bob.length*2;
//		if (cap % 2 != 0)
//			cap++;
//		
//		for(int i = 0; i < cap-2;)
//		{
//			Gridspot temp1 = bob[Math.abs(rope.nextInt()%bob.length)]
//					[Math.abs(rope.nextInt()%bob.length)];
//			
//			if (!temp1.occupied && (_spaces.isEmpty() || 
//					( _spaces.size() % 2 == 0 ) || (calSquDis(temp1,_spaces.peek())) > 1 ))
//			{
//			temp1.setOcc(colors[_spaces.size()/2]);
//			_spaces.push(temp1);
//			i++;
//			}
////			An Else statement to confirm the above conditional is working 
//			else
//			{
////				System.out.println("Was going to do "+temp1.getXIndex()+" "
////						+temp1.getYIndex());
//			}
//		}
//	}
//	
//	Takes in two gridspots as arguments, and then calculates the number of units they
//	are apart from each other, having a gridspot count as one unit.
	public int calSquDis(Gridspot x, Gridspot y)
	{
		return (int) (Math.pow(x.getXIndex()-y.getXIndex(),2) + Math.pow(x.getYIndex()-y.getYIndex(),2));
	}
	
	public Gridspot[][] getGrid()
	{
		return GridSpotArray;
	}

	public Stack<Gridspot> getpairL() {
		return _spaces;
	}
	
	public static void main (String args[]) throws InterruptedException
	{
		new Frame(frameWidth,frameHieght);
//		new Grid(4);
	}

}
