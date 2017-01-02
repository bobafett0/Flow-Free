package module.test;

import static org.junit.Assert.*;
//import com.jcabi.aspects.*;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Random;
import module.src.main.java.*;

import org.junit.Test;

import junit.framework.Assert;

public class LeesWayPointsTest {

	/*
	 * No two Starting point Gridspots are the same at all 
	 */ 
	
	@Test
	public void pathsCompletedWithWayPointsSize2() throws Exception {
        Grid grid = new Grid();
        grid.initialize(2,1);
        LeesWayPoints leeWayPoints = new LeesWayPoints();
        leeWayPoints.InitializeWayPointDics(grid.GetStartingPairs(),grid.GetGrid());
        Hashtable<GridSpot,StartingPair> solution = leeWayPoints.placeAllWayPoints(grid.GetStartingPairs());
        Assert.assertTrue(SolutionVerifier.IsGridFilled(solution,grid.GetGrid()));
   	}	
	@Test
	public void pathsCompletedWithWayPointsSize3() throws Exception {
        Grid grid = new Grid();
        grid.initialize(3,2);
        LeesWayPoints leeWayPoints = new LeesWayPoints();
        leeWayPoints.InitializeWayPointDics(grid.GetStartingPairs(),grid.GetGrid());
        Hashtable<GridSpot,StartingPair> solution = leeWayPoints.placeAllWayPoints(grid.GetStartingPairs());
        Assert.assertTrue(SolutionVerifier.IsGridFilled(solution,grid.GetGrid()));
   	}
}