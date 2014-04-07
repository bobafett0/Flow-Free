
import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Stack;

import wheelsunh.users.Ellipse;
import wheelsunh.users.Frame;
import wheelsunh.users.Rectangle;
import wheelsunh.users.ShapeGroup;

public class Line extends ShapeGroup
{
	public static int frameWidth = 800, frameHieght = 800;
	private teller _screen;
	public Stack<Gridspot> _points;
//	private ArrayList<pair<Ellipse,Ellipse>> ;
	private Gridspot[][] _grid;
	private Map<Gridspot, pair<Gridspot, Integer> > _myMap;
	private Stack<Stack<pair<Integer,Gridspot>>> _path;
	
	public Line() throws InterruptedException
	{
		_screen = new teller(150);
		_grid = _screen.getGrid();
		_points = _screen.getpairL();
//		if (!_grid[0][0].occupied)
//		_grid[0][0].setOcc(Color.blue);	
		Stack<pair<Integer,Gridspot>> temp = new Stack<pair<Integer,Gridspot>>();
		temp.push(new pair<Integer,Gridspot>(new Integer(0),_points.peek()));
		_path = new Stack<Stack<pair<Integer,Gridspot>>>();
		_path.push(temp);
		minDis(new pair<Integer,Gridspot>(new Integer(0),_points.pop()),_points.pop());
		System.out.println("Done :)");
		while(!_path.isEmpty())
		{
			while(!_path.peek().isEmpty())
			{
//				System.out.println("WHAT IS GOING ON");
				if (_path.peek().peek().getR() != null)
				{
				System.out.println(_path.peek().peek().getL());
				System.out.println(_path.peek().peek().getR().getXIndex()+" , "+
						_path.peek().pop().getR().getYIndex());
				}
				else
					temp.pop();
			}
			_path.pop();
		}
	}
	
	private void minDis(pair<Integer,Gridspot> cur, Gridspot goal) throws InterruptedException
	{
//		if(cur.getR() == goal)
//		{
//			Stack<pair<Integer,Gridspot>> temp = new Stack<pair<Integer,Gridspot>>();
//			temp.push(new pair<Integer,Gridspot>(null,null));
//			_path.add(temp);
//			System.out.println("Making a new Stack");
//			return;
//		}
		ArrayList<Gridspot> vertices = findVertices(cur.getR(),goal);
		if(vertices.isEmpty()) // The goal was not found, dead end.
		{
			_path.peek().pop();
			System.out.println("Path size is "+_path.size());
//			Stack<pair<Integer,Gridspot>> temp = new Stack<pair<Integer,Gridspot>>();
//			temp.push(new pair<Integer,Gridspot>(null,new Gridspot()));
//			_path.push(temp);
			System.out.println("Dead end");
			return;
		}
		
		for (int i = 0; i < vertices.size(); i++)
		{
			if (vertices.get(i) != goal)
			{
			vertices.get(i).setOcc(goal.getColor());
			_path.peek().push(new pair<Integer,Gridspot>(new Integer(cur.getL().intValue()+1),vertices.get(i)));
			System.out.println("going to "+vertices.get(i).getXIndex()+" , "+vertices.get(i).getYIndex());
//			Thread.sleep(1000);
			minDis(new pair<Integer,Gridspot>(new Integer(cur.getL().intValue()+1),vertices.get(i)),goal);
			
//			Thread.sleep(1000);
			vertices.get(i).setUnOcc();
//			_path.peek().pop();
			}
			else // The goal was found
			{
				System.out.println("We've reached our goal at "+goal.getXIndex()+" , "
						+goal.getYIndex());
				System.out.println("and at "+vertices.get(i).getXIndex()+" , "+vertices.get(i).getYIndex());
				System.out.println("from "+cur.getR().getXIndex()+" , "+cur.getR().getYIndex());
				Stack<pair<Integer,Gridspot>> temp = new Stack<pair<Integer,Gridspot>>();
				temp.push(new pair<Integer,Gridspot>(new Integer(cur.getL().intValue()+1) , goal ) );
				_path.peek().push(new pair<Integer,Gridspot>(new Integer(cur.getL().intValue()+1),vertices.get(i)));
				_path.push(temp);
				System.out.println("Making a new Stack");
				return;
			}
		}
		
//		for(int i = 0; i < vertices.size(); i++)
//		{
//			vertices.get(i).setOcc(cur.getR().getColor());
//			 trav = 1 + minDis(new pair<Gridspot,Gridspot>(vertices.get(i),cur.getR()),trav);
//			 
//		}
//		return 0;
//		ArrayList<pair< Gridspot,Integer > > sam;
//		_myMap = new HashMap<Gridspot, pair<Gridspot, Integer> >();
////		_myMap.put(pair.getL(),new pair<Gridspot, Integer>(pair.getR(),4));
//		
//		for(int i = 0; i < vertices.size(); i++)
//		{
//			_myMap.put(vertices.get(i), new pair<Gridspot,Integer>(null,new Integer(1)));
//		}
//		_myMap.put(pair.getL(),new pair<Gridspot, Integer>(null,new Integer(0)));
////		_myMap.	
	}
	// Checks if the four squares bordering a particular gridspot are occupied or
	// off grid.
	// It returns a collection of all nieghbors that can be traveled to.
	
	private ArrayList<Gridspot> findVertices(Gridspot take, Gridspot goal)
	{
		ArrayList<Gridspot> retur = new ArrayList<Gridspot>();
		int x = take.getXIndex(); int y = take.getYIndex();
		
		if (!getG(x+1,y).occupied || getG(x+1,y) == goal)
		{
//			System.out.println("Adding " + getG(x+1,y).getXIndex() + " , "
//					+getG(x+1,y).getYIndex());
			retur.add(getG(x+1,y));
		}
		if (!getG(x-1,y).occupied || getG(x-1,y) == goal)
		{
//			System.out.println("Adding " + getG(x-1,y).getXIndex() + " , "
//					+getG(x-1,y).getYIndex());
			retur.add(getG(x-1,y));
		}
		if (!getG(x,y+1).occupied || getG(x,y+1) == goal)
		{
//			System.out.println("Adding " + getG(x,y+1).getXIndex() + " , "
//					+getG(x,y+1).getYIndex());
			retur.add(getG(x,y+1));
		}
		if (!getG(x,y-1).occupied || getG(x,y-1) == goal)
		{
//			System.out.println("Adding " + getG(x,y-1).getXIndex() + " , "
//					+getG(x,y-1).getYIndex());
			retur.add(getG(x,y-1));
		}
		return retur;
	}
	

//	The Following method has a purpose to test the find vertices function
	
	private void findDriver (pair<Gridspot,Gridspot> pair)
	{
		ArrayList<Gridspot> collec = findVertices(pair.getL(),pair.getR());
		System.out.println(pair.getL().getXIndex()+" , "+pair.getL().getYIndex());
		System.out.println(pair.getL().getColor());
		System.out.println(collec.size());
		for(int i = 0; i < collec.size(); i++)
		{
			System.out.println(collec.get(i).getXIndex()+" , "+
					collec.get(i).getYIndex());
			
		}
	}
//	private void draw() throws InterruptedException 
//	{
//		_path = new Stack<Gridspot>();
////		Rectangle first = new Rectangle();
////		first.setSize(_screen.cIrcleH, _screen.cIrcleH );
////		first.setCenter(_points.get(0).l.getCenter());
////		first.setColor(_points.get(0).l.getColor());
//		Gridspot temp = getGridSpot(_points.get(0).l.getCenterX(),_points.get(0).l.getCenterY());
//		temp.setInColor(_points.get(0).l.getColor());
//		findPath(temp,temp, getGridSpot(_points.get(0).r.getCenterX(),_points.get(0).r.getCenterY()));
////		for (int i = 1; i < 8; i++)
////		{
////			_path.add(new Rectangle());
////			_path.get(i).setSize(_screen.cIrcleH, _screen.cIrcleH );
////			_path.get(i).setCenter(getC(temp.getXIndex()+i,temp.getYIndex()));
////			System.out.println(i);
////			Thread.sleep(1000);
////		}
////		trail.setCenter(_screen.pairList.get(i).l.getCenter());
////		System.out.println(_screen.pairList.get(i).l.getColor());
////		
////		for (int u = 0; u < 800/_screen._squWidth; u++)
////		trail.setSize(trail.getWidth() + _screen._squWidth ,_screen.cIrcleH/2);
////		System.out.println("u is "+ u);
////		//trail.setCenter(_screen.pairList.get(i).l.getCenter());
////		Thread.sleep(1000);
////		}
//		
//	}
	int xP, yP;
//	private void findPath(Gridspot prev, Gridspot cur, Gridspot finish) throws InterruptedException
//	{
//		_path.push(cur);
//		Random rand = new Random();
//		if(cur == finish)
//		{
//			finish.setOcc(_path.get(0).getColor());
//			return;
//		}
//		else if(!cur.occupied || cur == _path.get(0) )
//		{
////			The following code will determine which Gridspot to go to next at random.
////			if i is 0, it will try going down, 
////			if i is 1 it will try going to the right
////			if i is 2 it will try the left
////			if i is 3 it will try going up
//			System.out.println(cur.occupied);
//			
//			// checks if the next randomly generated index exits on the frame
//			
//			for(int i = 0; i < 4; i++)
//			{
//			if (i == 0)
//			{
//				check(0,1,cur,finish);
//			}
//			else if(i ==1)
//			{
//				check(1,0,cur,finish);
//			}
//			else if (i == 2)
//			{
//				check(-1,0,cur,finish);
//			}
//			else if ( i == 3)
//			{
//				check(0,-1,cur,finish);
//			}
//			
////			Rectangle display = new Rectangle();
////			display.setSize(_screen.cIrcleH, _screen.cIrcleH );
////			display.setCenter(getC(place.getXIndex()+xP,place.getYIndex()+yP ));
//			
////			System.out.println("First index is : "+(cur.getXIndex()+xP)+" Second"
////					+ " index is : "+(cur.getYIndex()+yP));
//			
//			}
//		}		
////		else 
////		{
////			System.out.println("The path size is : "+_path.size());
////			_path.pop();
////			_path.peek().setUnOcc();
////			findPath(prev,_path.peek(),finish);
////		}
//	}
	
//	private void check(int xP, int yP, Gridspot cur, Gridspot finish) throws InterruptedException
//	{
//		if (cur.getXIndex()+xP < _grid.length && cur.getYIndex()+yP < _grid.length 
//				&& cur.getXIndex() + xP > 0 && cur.getYIndex() + yP > 0)
//		{
//		cur.setOcc(_path.get(0).getColor());
//		Thread.sleep(1000);
//		findPath(cur,_grid[cur.getXIndex()+xP][cur.getYIndex()+yP],finish);
//		}
//		else
//		{
//			return;
//		}
//		
//	}
//	// Takes in an x and a y index and returns the point 
//	// representing its center on the frame
//	
	private Point getC(int x, int y)
	{
		return _grid[x][y].getCenter();
	}
	
	// Takes two indexes as arguments and returns the gridspot at those indexes
	
	private Gridspot getG(int x, int y)
	{
		if (x >= _grid.length || x < 0 || y >= _grid.length || y < 0)
		{
			return new Gridspot();
		}
		return _grid[x][y];
	}
	
//	// Takes in an x and a y index and returns the y location on the frame
//	
//	private int getY(int x, int y)
//	{
//		return _grid[x][y].getCenterY();
//	}
	
	// takes in two location arguments and returns the gridspot whose center is at that location
	
	private Gridspot getGridSpot(int x, int y)
	{
//		System.out.println("x is "+(x-_screen._squWidth/2)/_screen._squWidth);
//		System.out.println("y is "+(y-_screen._squWidth/2)/_screen._squWidth);
		return _grid[(x-_screen._squWidth/2)/_screen._squWidth]
				[(y-_screen._squWidth/2)/_screen._squWidth];
	}
//	private void testColor() throws InterruptedException
//	{
//		for (int i = 0; i < _screen.getpairL().size(); i++)
//		{
//			System.out.println("sdfasdfsdf");
//			Color temp1 = _screen.pairList.get(i).l.getColor();
//			Color temp2 = _screen.pairList.get(i).r.getColor();
//			Thread.sleep(1000);
//			_screen.pairList.get(i).l.setColor(Color.magenta);
//			_screen.pairList.get(i).r.setColor(Color.magenta);
//			Thread.sleep(1000);
//			_screen.pairList.get(i).l.setColor(temp1);
//			_screen.pairList.get(i).r.setColor(temp2);
//		}
//	}

	
	public static void main (String args[]) throws InterruptedException
	{
		new Frame(frameWidth,frameHieght);
		new Line();
	}
}
