package module.test;

import static org.junit.Assert.*;
//import com.jcabi.aspects.*;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Random;

import groovy.util.GroovyTestCase;
import module.src.main.java.*;

import org.junit.Test;

import junit.framework.Assert;

public class LeesWayPointsTest extends GroovyTestCase {

	/*
	 * No two Starting point Gridspots are the same at all 
	 */ 
	
	@Test
	public void testPathsCompletedWithWayPointsSize2() throws Exception {
          Grid grid = new Grid();
          ArrayList<Pair<Integer,Integer>> pairList = new ArrayList<Pair<Integer,Integer>>();
          pairList.add(new Pair<Integer,Integer>(0,0));
          pairList.add(new Pair<Integer,Integer>(0,1));
          grid.initialize(2,pairList);
          LeesWayPoints leeWayPoints = new LeesWayPoints();
          leeWayPoints.InitializeWayPointDics(grid.GetStartingPairs(),grid.GetGrid());
          Hashtable<GridSpot,StartingPair> solution = leeWayPoints.placeAllWayPoints(grid.GetStartingPairs());
          Assert.assertTrue(GridUtilities.IsGridFilled(solution,grid.GetGrid()));
   	}
	
	@Test
	public void testPathsNotCompletedWithWayPointsSize2Insolvable() throws Exception {
          Grid grid = new Grid();
          ArrayList<Pair<Integer,Integer>> pairList = new ArrayList<Pair<Integer,Integer>>();
          pairList.add(new Pair<Integer,Integer>(0,0));
          pairList.add(new Pair<Integer,Integer>(1,1));
          grid.initialize(2,pairList);
          LeesWayPoints leeWayPoints = new LeesWayPoints();
          leeWayPoints.InitializeWayPointDics(grid.GetStartingPairs(),grid.GetGrid());
          Hashtable<GridSpot,StartingPair> solution = leeWayPoints.placeAllWayPoints(grid.GetStartingPairs());
          Assert.assertFalse(GridUtilities.IsGridFilled(solution,grid.GetGrid()));
   	}
           
	@Test
	public void testPathsCompletedWithWayPointsSize3() throws Exception {
          Grid grid = new Grid();
          ArrayList<Pair<Integer,Integer>> pairList = new ArrayList<Pair<Integer,Integer>>();
          pairList.add(new Pair<Integer,Integer>(0,0));
          pairList.add(new Pair<Integer,Integer>(0,2));
          pairList.add(new Pair<Integer,Integer>(1,0));
          pairList.add(new Pair<Integer,Integer>(1,2));
          pairList.add(new Pair<Integer,Integer>(2,0));
          pairList.add(new Pair<Integer,Integer>(2,2));
          grid.initialize(3,pairList);
          LeesWayPoints leeWayPoints = new LeesWayPoints();
          leeWayPoints.InitializeWayPointDics(grid.GetStartingPairs(),grid.GetGrid());
          Hashtable<GridSpot,StartingPair> solution = leeWayPoints.placeAllWayPoints(grid.GetStartingPairs());
          Assert.assertTrue(GridUtilities.IsGridFilled(solution,grid.GetGrid()));
   	}
	
	@Test
	public void testPathsCompletedWithWayPointsSize3Insolvable() throws Exception {
          Grid grid = new Grid();
          ArrayList<Pair<Integer,Integer>> pairList = new ArrayList<Pair<Integer,Integer>>();
          pairList.add(new Pair<Integer,Integer>(0,0));
          pairList.add(new Pair<Integer,Integer>(0,2));
          pairList.add(new Pair<Integer,Integer>(1,1));
          pairList.add(new Pair<Integer,Integer>(1,2));
          pairList.add(new Pair<Integer,Integer>(2,0));
          pairList.add(new Pair<Integer,Integer>(2,2));
          grid.initialize(3,pairList);
          LeesWayPoints leeWayPoints = new LeesWayPoints();
          leeWayPoints.InitializeWayPointDics(grid.GetStartingPairs(),grid.GetGrid());
          Hashtable<GridSpot,StartingPair> solution = leeWayPoints.placeAllWayPoints(grid.GetStartingPairs());
          Assert.assertFalse(GridUtilities.IsGridFilled(solution,grid.GetGrid()));
   	}
	
	@Test
	public void testPathsCompletedWithWayPointsSize4() throws Exception {
          Grid grid = new Grid();
          ArrayList<Pair<Integer,Integer>> pairList = new ArrayList<Pair<Integer,Integer>>();
          pairList.add(new Pair<Integer,Integer>(0,0));
          pairList.add(new Pair<Integer,Integer>(0,3));
          pairList.add(new Pair<Integer,Integer>(1,0));
          pairList.add(new Pair<Integer,Integer>(1,3));
          pairList.add(new Pair<Integer,Integer>(2,0));
          pairList.add(new Pair<Integer,Integer>(2,3));
          pairList.add(new Pair<Integer,Integer>(3,0));
          pairList.add(new Pair<Integer,Integer>(3,3));
          grid.initialize(4,pairList);
          LeesWayPoints leeWayPoints = new LeesWayPoints();
          leeWayPoints.InitializeWayPointDics(grid.GetStartingPairs(),grid.GetGrid());
          Hashtable<GridSpot,StartingPair> solution = leeWayPoints.placeAllWayPoints(grid.GetStartingPairs());
          Assert.assertTrue(GridUtilities.IsGridFilled(solution,grid.GetGrid()));
   	}
	
	@Test
	public void testPathsCompletedWithWayPointsSize7() throws Exception {
          Grid grid = new Grid();
          ArrayList<Pair<Integer,Integer>> pairList = new ArrayList<Pair<Integer,Integer>>();
          pairList.add(new Pair<Integer,Integer>(0,0));
          pairList.add(new Pair<Integer,Integer>(0,6));
          pairList.add(new Pair<Integer,Integer>(1,0));
          pairList.add(new Pair<Integer,Integer>(1,6));
          pairList.add(new Pair<Integer,Integer>(2,0));
          pairList.add(new Pair<Integer,Integer>(2,6));
          pairList.add(new Pair<Integer,Integer>(3,0));
          pairList.add(new Pair<Integer,Integer>(3,6));
          pairList.add(new Pair<Integer,Integer>(4,0));
          pairList.add(new Pair<Integer,Integer>(4,6));
          pairList.add(new Pair<Integer,Integer>(5,0));
          pairList.add(new Pair<Integer,Integer>(5,6));
          pairList.add(new Pair<Integer,Integer>(6,0));
          pairList.add(new Pair<Integer,Integer>(6,6));
          grid.initialize(7,pairList);
          LeesWayPoints leeWayPoints = new LeesWayPoints();
          leeWayPoints.InitializeWayPointDics(grid.GetStartingPairs(),grid.GetGrid());
          Hashtable<GridSpot,StartingPair> solution = leeWayPoints.placeAllWayPoints(grid.GetStartingPairs());
          Assert.assertTrue(GridUtilities.IsGridFilled(solution,grid.GetGrid()));
   	}
}