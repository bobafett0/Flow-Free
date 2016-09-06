import java.awt.Color;
 import java.util.ArrayList;
 import java.util.HashMap;
 import java.util.PriorityQueue;
 
 import wheelsunh.users.Ellipse;
 import wheelsunh.users.Rectangle;
 
 public class Gridspot extends Rectangle{
 	public boolean occupied = false;
 	private int _Xindex, _Yindex;
 	private Rectangle reddy;
 	private int _recSize;
 	public PriorityQueue<pairI<Integer,Gridspot>> pQuack;
 	public HashMap<Integer,ArrayList<Gridspot>> _map;
 	public HashMap<pair<Gridspot,Gridspot>,PriorityQueue<pairI<Integer,Gridspot>>> _pQuacks;
 	public HashMap<Integer,ArrayList<pair<Integer,Integer>>> tuene;
 	public HashMap<pair<Gridspot,Gridspot>,PriorityQueue<pairI<Integer,pair<Integer,Integer>>>> _tuenes;
 
 	// Constructor with location arguments
 	public Gridspot(int x , int y, int size)
 	{
 		super(x,y);
 		_recSize = size;
 		pQuack = new PriorityQueue<pairI<Integer,Gridspot>>();
 		_pQuacks = new HashMap<pair<Gridspot,Gridspot>,PriorityQueue<pairI<Integer,Gridspot>>>();
 		_map = new HashMap<Integer,ArrayList<Gridspot>>();
 		
 	}
 	public Gridspot()
 	{
 		super();
 		super.hide();
 		occupied = true;
 	}
 	
 	/*
 	 * pQuack methods
 	 */
 	
 	// Adds elements to the priority queue
 	public boolean insert(pairI<Integer,Gridspot> cur)
 	{
 		if (_map.get(cur.getL()) == null)
 		{
 			_map.put(cur.getL(), new ArrayList<Gridspot>());
 			_map.get(cur.getL()).add(cur.getR());
 		}
 		else
 			_map.get(cur.getL()).add(cur.getR());
 		
 		return pQuack.add(cur);
 	}
 	public pairI<Integer,Gridspot> retrieve()
 	{
 		if(pQuack.size() > 0)
 			return pQuack.peek();
 		else
 			return new pairI<Integer,Gridspot>(new Integer(4),new Gridspot());
 	}
 	
 	public int sizeQuack()
 	{
 		return pQuack.size();
 	}
 	
 	public pairI<Integer,Gridspot> popTop()
 	{
 		return pQuack.poll();
 	}
 	public void assignQuack(pair<Gridspot,Gridspot> a)
 	{
 		pQuack = new PriorityQueue<pairI<Integer,Gridspot>>(_pQuacks.get(a));
 	}
 	
 	public void clearQuack()
 	{
 		pQuack.clear();
 	}
 	
 	public void storeQuack(pair<Gridspot,Gridspot> a)
 	{
 		_pQuacks.put(a, new PriorityQueue<pairI<Integer,Gridspot>>(pQuack));
 		clearQuack();
 	}
 	
 	
 	/*
 	 * reddy methods
 	 */
 	
 	public void setOcc (Color color)
 	{
 		occupied = true;
 		reddy.show();
 		reddy.setColor(color);
 	}
 	public void setUnOcc ()
 	{
 		reddy.hide();
 		occupied = false;
 	}
 	
 	public void setInColor(Color color)
 	{
 		reddy.setColor(color);
 	}
 	public Color getColor()
 	{
 		return reddy.getColor();
 	}
 	
 	public void setIndex(int x, int y)
 	{
 		_Xindex = x; _Yindex = y;
 	}
 	public int getXIndex()
 	{
 		return _Xindex;
 	}
 	public int getYIndex()
 	{
 		return _Yindex;
 	}
 	public void setSize(int x, int y)
 	{
 		super.setSize(x,y);
 		reddy = new Rectangle();
 		reddy.setSize(_recSize,_recSize);
 		reddy.setCenter(super.getCenter());
 		reddy.hide();
 		
 	}
 	
 }
