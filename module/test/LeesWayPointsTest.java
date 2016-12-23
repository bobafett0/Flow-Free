package module.test;

import static org.junit.Assert.*;
//import com.jcabi.aspects.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Random;
import module.src.main.java.*;
import module.src.main.java.LeesWayPoints.WayPoints;

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
		cycleThroughStartingPairs(grid);
   	}

    @Test
	public void pathsCompletedWithWayPointsSize3() throws Exception {
        Grid grid = new Grid();
        grid.initialize(3,2);
		cycleThroughStartingPairs(grid);
   	}

    @Test
	public void pathsCompletedWithWayPointsSize4() throws Exception {
        Grid grid = new Grid();
        grid.initialize(4,3);
		cycleThroughStartingPairs(grid);
   	}

    @Test
	public void pathsCompletedWithWayPointsSize5() throws Exception {
        Grid grid = new Grid();
        grid.initialize(5,4);
		cycleThroughStartingPairs(grid);
   	}

    @Test
	public void pathsCompletedWithWayPointsSize6() throws Exception {
        Grid grid = new Grid();
        grid.initialize(6,5);
		cycleThroughStartingPairs(grid);
   	}

    @Test
	public void pathsCompletedWithWayPointsSize7() throws Exception {
        Grid grid = new Grid();
        grid.initialize(7,6);
		cycleThroughStartingPairs(grid);
   	}

    @Test
	public void pathsCompletedWithWayPointsSize8() throws Exception {
        Grid grid = new Grid();
        grid.initialize(8,7);
		cycleThroughStartingPairs(grid);
   	}

    private void cycleThroughStartingPairs(Grid grid) {
        LeesWayPoints leeWayPoints = new LeesWayPoints();
        leeWayPoints.InitializeWayPointDics(grid.GetStartingPairs(),grid.GetGrid());
        leeWayPoints.placeAllWayPoints(grid.GetStartingPairs());

        GridSpot endPoint = null;
        for(int u = 0; u < grid.GetStartingPairs().size(); u++){
            StartingPair curPair = grid.GetStartingPairs().get(u);
            WayPoints wayPoints = leeWayPoints.wayPointDic.get(curPair);
            int initialSize = wayPoints.get(curPair.getR()).size();
            for(int i = 0; i < initialSize; i++) {
                endPoint = findGoal(grid.GetGrid(),wayPoints,curPair.getR());
                Assert.assertTrue(endPoint == curPair.getL());
            }
        }
    }
	
	private GridSpot findGoal(GridSpot[][] grid, WayPoints wayPoints, GridSpot startingPlace) {
        PriorityQueue<PairI<Integer,GridSpot>> curPriorityQueue = wayPoints.get(startingPlace);
        HashSet<GridSpot> neighbors = new HashSet<GridSpot>();
        while( curPriorityQueue.size() > 0) {
            neighbors.add(curPriorityQueue.poll().getR());
        }

        

        PairI<Integer,GridSpot> curPair = null;
        while ( curPriorityQueue.size() > 0) {
        	curPair = curPriorityQueue.poll();
        	curPriorityQueue = wayPoints.get(curPair.getR());
        	
        	if(curPriorityQueue.size() > 0) {
        		curPair = curPriorityQueue.peek();
        	}
        }
        
		return curPair.getR();
	}
}