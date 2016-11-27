package module.src.main.java;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.PriorityQueue;

public class LeesWayPoints {
	
	public Hashtable<StartingPair,WayPoints> wayPointDic;
	public GridSpot[][] GridSpotArray;

	public void InitializeWayPointDics (ArrayList<StartingPair> startingPairs, GridSpot[][] gridSpotArray)
	{
		wayPointDic = new Hashtable<StartingPair,WayPoints>();
		for (int i = 0; i < startingPairs.size(); i++) {
			StartingPair curPair = startingPairs.get(i);
			wayPointDic.put(curPair, new WayPoints());
			InitializeWayPoints(wayPointDic.get(curPair),gridSpotArray);
		}
		GridSpotArray = gridSpotArray;
	}

	private void InitializeWayPoints(WayPoints wayPointCol, GridSpot[][] gridSpotArray)
	{
		for(int i = 0; i < gridSpotArray.length; i++) {
			for(int u = 0; u < gridSpotArray[i].length; u++){
				wayPointCol.put(gridSpotArray[i][u],new PriorityQueue<PairI<Integer,GridSpot>>());
			}
		}
	}

	public void placeAllWayPoints (ArrayList<StartingPair> startingPairs) {
		for (int i = 0; i < startingPairs.size(); i++) {
			StartingPair currentPair = startingPairs.get(i);
			WayPoints wayPointGroup = wayPointDic.get(currentPair);
			exploreAndPlace(new ArrayList<GridSpot>(),currentPair.getL(),0,currentPair.getR(),wayPointGroup);
		}
	}

	private void exploreAndPlace (ArrayList<GridSpot> occupiedGridSpots, GridSpot current,int distance,GridSpot goal,WayPoints wayPoints) {
		if(current == goal){
			return;
		}
		occupiedGridSpots.add(current);
		GridSpot rightOfCur = null;
		GridSpot leftOfCur = null;
		GridSpot aboveCur = null;
		GridSpot belowCur = null;

		if(current.XIndex + 1 < GridSpotArray.length) {
			rightOfCur = GridSpotArray[current.XIndex+1][current.YIndex];
		}
		if(current.XIndex - 1 >= 0) {
			leftOfCur = GridSpotArray[current.XIndex-1][current.YIndex];
		}
		if(current.YIndex - 1 >= 0) {
			aboveCur = GridSpotArray[current.XIndex][current.YIndex-1];
		}
		if(current.YIndex + 1 < GridSpotArray.length) {
			belowCur = GridSpotArray[current.XIndex][current.YIndex+1];
		}
		if (rightOfCur != null && !occupiedGridSpots.contains(rightOfCur)) {
			wayPoints.get(rightOfCur).add(new PairI<Integer,GridSpot>(new Integer(distance+1),current));
			exploreAndPlace(occupiedGridSpots,rightOfCur,distance+1,goal,wayPoints);
		}
		if (leftOfCur != null && !occupiedGridSpots.contains(leftOfCur)) {
			wayPoints.get(leftOfCur).add(new PairI<Integer,GridSpot>(new Integer(distance+1),current));
			exploreAndPlace(occupiedGridSpots,leftOfCur,distance+1,goal,wayPoints);
		}
		if (aboveCur != null && !occupiedGridSpots.contains(aboveCur)) {
			wayPoints.get(aboveCur).add(new PairI<Integer,GridSpot>(new Integer(distance+1),current));
			exploreAndPlace(occupiedGridSpots,aboveCur,distance+1,goal,wayPoints);
		}
		if (belowCur != null && !occupiedGridSpots.contains(belowCur)) {
			wayPoints.get(belowCur).add(new PairI<Integer,GridSpot>(new Integer(distance+1),current));
			exploreAndPlace(occupiedGridSpots,belowCur,distance+1,goal,wayPoints);
		}
	}
	
	public class WayPoints extends Hashtable<GridSpot,PriorityQueue<PairI<Integer,GridSpot>>> {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

	}
}

