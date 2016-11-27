package module.test;

import static org.junit.Assert.*;
//import com.jcabi.aspects.*;
import java.util.ArrayList;
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

    private void cycleThroughStartingPairs(Grid grid) {
        LeesWayPoints leeWayPoints = new LeesWayPoints();
        leeWayPoints.InitializeWayPointDics(grid.GetStartingPairs(),grid.GetGrid());
        leeWayPoints.placeAllWayPoints(grid.GetStartingPairs());
        
        WayPoints wayPoints = leeWayPoints.wayPointDic.get(grid.GetStartingPairs().get(0));

        GridSpot endPoint = null;
        for(int u = 0; u < grid.GetStartingPairs().size(); u++){
            int initialSize = wayPoints.get(grid.GetStartingPairs().get(u).getR()).size();
            for(int i = 0; i < initialSize; i++) {
                endPoint = findGoal(grid.GetGrid(),wayPoints,grid.GetStartingPairs().get(0).getR());
                Assert.assertTrue(endPoint == grid.GetStartingPairs().get(0).getL());
            }
        }
    }
	
	private GridSpot findGoal(GridSpot[][] grid, WayPoints wayPoints, GridSpot startingPlace) {
        PriorityQueue<PairI<Integer,GridSpot>> curPriorityQueue = wayPoints.get(startingPlace);
        PairI<Integer,GridSpot> curPair = null;        
        while ( curPriorityQueue.size() > 0) {
            curPair = curPriorityQueue.poll();
        	curPriorityQueue = wayPoints.get(curPair.getR());
        }
        
		return curPair.getR();
	}
}