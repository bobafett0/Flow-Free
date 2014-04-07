
import java.awt.Color;
import java.awt.List;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.Stack;

import wheelsunh.users.Ellipse;
import wheelsunh.users.Frame;
import wheelsunh.users.Rectangle;

public class teller {
	public static int frameWidth = 800, frameHieght = 800;
	private Gridspot[][] bob;
	public int cIrcleH, _squWidth;
	public ArrayList<pair<Gridspot,Gridspot>> pairList;
	public Color[] colors = { Color.blue, Color.cyan, Color.DARK_GRAY, 
			Color.green, Color.magenta, Color.orange, Color.pink,
			Color.red, Color.yellow, Color.black,Color.gray };
	private Stack<Gridspot> _spaces;
//	private Set<pair<Integer,Integer>> collec;
	//public int 
	public teller (int squWidth) throws InterruptedException
	{
		game(squWidth);
		makeMaze(squWidth);
		
	}
	public teller(ArrayList<pair<pair<Integer,Integer>,pair<Integer,Integer>>> list, int squWidth )
	{
		game(squWidth);
		for(int i = 0; i < list.size(); i++)
		{
			System.out.println(i);
			System.out.println(list.get(i).getL().getL().intValue());
			System.out.println(list.get(i).getL().getR().intValue());
		Gridspot temp1 = bob[list.get(i).getL().getL().intValue()]
				[list.get(i).getL().getR().intValue()];
		Gridspot temp2 = bob[list.get(i).getR().getL().intValue()]
				[list.get(i).getR().getR().intValue()];
		temp1.setOcc(colors[_spaces.size()/2]);
		temp2.setOcc(colors[_spaces.size()/2]);
		_spaces.push(temp1);_spaces.push(temp2);
		}
		
	}
	
	private void game(int squWidth)
	{
		_squWidth = squWidth;
		if (squWidth > 400)
		{
			System.out.println("Square Width argument too small. It "
					+ "Must be equal to or less than 400");
			return;
		}
		cIrcleH = 3*squWidth/5;
		makeGrd(squWidth);
		_spaces = new Stack<Gridspot>();
	}
//	Creates the two dimentional array of "gridspots," in other words, 
//	the rectangles that fill up the grid.
	private void makeGrd (int squWidth)
	{
		bob = new Gridspot[(frameWidth/squWidth)][(frameWidth/squWidth)];
		System.out.println(bob.length);
		System.out.println(bob[1].length);

		for(int u = 0; u < (frameHieght)/squWidth; u++)
		{
			for(int i = 0; i < (frameWidth)/squWidth; i++)
			{
				Gridspot tom = new Gridspot(i*squWidth,u*squWidth,squWidth/2);
				tom.setIndex(i,u);
				bob[i][u] = tom;
				bob[i][u].setSize(squWidth,squWidth);
				bob[i][u].setFillColor(Color.white);
				bob[i][u].setFrameColor(Color.black);
			}
		}	
	}
//	Loops through grid points and makes sure they are not already occupied
//	calls the setCirle method through each iteration
	private void makeMaze(int squWidth) throws InterruptedException
	{
//		ArrayList<Ellipse> points = new ArrayList<Ellipse>();
		
		Random rope = new Random();
		int cap = bob.length*2;
//		makes the number of balls larger by one if it is an odd number
		if (cap % 2 != 0)
			cap++;
		
		for(int i = 0; i < cap-2;)
		{
//			Obtaining gridspots of which to place circles
			Gridspot temp1 = bob[Math.abs(rope.nextInt()%bob.length)]
					[Math.abs(rope.nextInt()%bob.length)];
			
			if (!temp1.occupied && (_spaces.isEmpty() || 
					( _spaces.size() % 2 == 0 ) || (calSquDis(temp1,_spaces.peek())) > 1 ))
			{
			temp1.setOcc(colors[_spaces.size()/2]);
			_spaces.push(temp1);
//			points.add(setCircle(temp1,squWidth*3/5,i/2));
			i++;
//			Thread.sleep(1000);
			}
//			An Else statement to confirm the above conditional is working 
			else
			{
//				System.out.println("Was going to do "+temp1.getXIndex()+" "
//						+temp1.getYIndex());
			}
		}
//		pairList = pairUp(points);
	}
//	Creates the Circle,places it at the appropriate place on the grid, 
//	sets the circle to the appropriate color, then returns the ellipse
	
	private Ellipse setCircle(Gridspot joe,int crClWidth,int index) 
	{
		Ellipse var = new Ellipse();
		var.setColor(colors[index]);
		var.setSize(crClWidth, crClWidth);
		Point p = new Point(joe.getCenterX(), joe.getCenterY()); 
//		System.out.print(joe.getCenterX()); 
//		System.out.println(" "+joe.getCenterY()); 
		var.setCenter(p);
		return var;
	}
	
	private ArrayList<pair<Ellipse,Ellipse>> retur;
	
	private ArrayList<pair<Ellipse,Ellipse>> pairUp( ArrayList<Ellipse> points)
	{
		retur = new ArrayList<pair<Ellipse,Ellipse>>();
		for(int i = 0; i < points.size(); i=i+2)
		{
//			ArrayList<pair<Gridspot,Gridspot>> retur;
//			System.out.println("The array size is"+points.size());
//			System.out.println("Current Indexes are : "+i+"and "+(i+1));
//			System.out.println(points.get(i).getCenter());
			Ellipse a = points.get(i);
			Ellipse b = points.get(i+1);
			pair<Ellipse,Ellipse> c = new pair<Ellipse,Ellipse>(a,b); 
			retur.add(c);
		}
		
		return retur;
	}
	
//	Takes in two gridspots as arguments, and then calculates the number of units they
//	are apart from each other, having a gridspot count as one unit.
	public int calSquDis(Gridspot x, Gridspot y)
	{
		return (int) (Math.pow(x.getXIndex()-y.getXIndex(),2) + Math.pow(x.getYIndex()-y.getYIndex(),2));
	}
	
	public Gridspot[][] getGrid()
	{
		return bob;
	}

	public Stack<Gridspot> getpairL() {
		return _spaces;
	}
	
	public static void main (String args[]) throws InterruptedException
	{
		new Frame(frameWidth,frameHieght);
		new teller(200);
	}

}
