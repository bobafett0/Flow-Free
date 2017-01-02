package module.test;

import static org.junit.Assert.*;
//import com.jcabi.aspects.*;
import java.util.ArrayList;
import java.util.Hashtable;
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

//    private void cycleThroughStartingPairs(Grid grid) {
//        LeesWayPoints leeWayPoints = new LeesWayPoints();
//        leeWayPoints.InitializeWayPointDics(grid.GetStartingPairs(),grid.GetGrid());
//        leeWayPoints.placeAllWayPoints(grid.GetStartingPairs());
//        
//        for(int i = 0; i < grid.GetStartingPairs().size(); i++){
//            WayPoints wayPoints = leeWayPoints.wayPointDic.get(grid.GetStartingPairs().get(i));
//            
//        }
//
//        GridSpot endPoint = null;
//        for(int u = 0; u < grid.GetStartingPairs().size(); u++){
//            int initialSize = wayPoints.get(grid.GetStartingPairs().get(u).getR()).size();
//            for(int i = 0; i < initialSize; i++) {
//                endPoint = findGoal(grid.GetGrid(),wayPoints,grid.GetStartingPairs().get(0).getR());
//                Assert.assertTrue(endPoint == grid.GetStartingPairs().get(0).getL());
//            }
//        }
//    }
//	
//	private GridSpot findGoal(GridSpot[][] grid, WayPoints wayPoints, GridSpot startingPlace) {
//        PriorityQueue<PairI<Integer,GridSpot>> curPriorityQueue = wayPoints.get(startingPlace);
//        PairI<Integer,GridSpot> curPair = null;        
//        while ( curPriorityQueue.size() > 0) {
//            curPair = curPriorityQueue.poll();
//        	curPriorityQueue = wayPoints.get(curPair.getR());
//        }
//        
//		return curPair.getR();
//	}
}