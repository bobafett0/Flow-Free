package module.src.main.java;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.PriorityQueue;

public class LeesWayPoints {
	
//	public Hashtable<StartingPair,WayPoints> WayPointDic;
	public GridSpot[][] GridSpotArray;
	public ArrayList<StartingPair> StartingPairs;
	public Hashtable<GridSpot,StartingPair> GridSpotIdentifier;

	public void InitializeWayPointDics (ArrayList<StartingPair> startingPairs, GridSpot[][] gridSpotArray)
	{
//		WayPointDic = new Hashtable<StartingPair,WayPoints>();
//		for (int i = 0; i < startingPairs.size(); i++) {
//			StartingPair curPair = startingPairs.get(i);
//			WayPointDic.put(curPair, new WayPoints());
//			InitializeWayPoints(WayPointDic.get(curPair),gridSpotArray);
//		}
		GridSpotIdentifier = new Hashtable<GridSpot,StartingPair>();
		StartingPairs = startingPairs;
		GridSpotArray = gridSpotArray;
	}

//	private void InitializeWayPoints(WayPoints wayPointCol, GridSpot[][] gridSpotArray)
//	{
//		for(int i = 0; i < gridSpotArray.length; i++) {
//			for(int u = 0; u < gridSpotArray[i].length; u++){
//				wayPointCol.put(gridSpotArray[i][u],new HashSet<GridSpot>());
//			}
//		}
//	}

	public Hashtable<GridSpot,StartingPair> placeAllWayPoints (ArrayList<StartingPair> startingPairs) {
//		for (int i = 0; i < startingPairs.size(); i++) {
//			StartingPair currentPair = startingPairs.get(i);
		exploreAndPlace(new ArrayList<GridSpot>(),startingPairs.get(0).getL(),0);
//		}
		return GridSpotIdentifier;
	}
         
	
	private boolean exploreAndPlace (ArrayList<GridSpot> occupiedGridSpots, GridSpot current,int startingPairIndex) {
		if(startingPairIndex > StartingPairs.size() - 1){
			return SolutionVerifier.IsGridFilled(GridSpotIdentifier, GridSpotArray);
		}
		StartingPair currentPair = StartingPairs.get(startingPairIndex);
		GridSpot goal = currentPair.getR();
		if(current == goal){
//			StartingPair nextPair = StartingPairs.get(startingPairIndex+1);
//			wayPoints = WayPointDic.get(nextPair);
			GridSpotIdentifier.put(current, currentPair);
			return exploreAndPlace(occupiedGridSpots,currentPair.getL(),startingPairIndex+1);
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
			GridSpotIdentifier.put(current, currentPair);
			if(!exploreAndPlace(occupiedGridSpots,rightOfCur,startingPairIndex))
				GridSpotIdentifier.remove(current);
			else
				return true;
		}
		if (leftOfCur != null && !occupiedGridSpots.contains(leftOfCur)) {
			GridSpotIdentifier.put(current, currentPair);
			if(!exploreAndPlace(occupiedGridSpots,leftOfCur,startingPairIndex))
				GridSpotIdentifier.remove(current);
			else
				return true;
		}
		if (aboveCur != null && !occupiedGridSpots.contains(aboveCur)) {
			GridSpotIdentifier.put(current, currentPair);
			if(!exploreAndPlace(occupiedGridSpots,aboveCur,startingPairIndex))
				GridSpotIdentifier.remove(current);
			else
				return true;
		}
		if (belowCur != null && !occupiedGridSpots.contains(belowCur)) {
			GridSpotIdentifier.put(current, currentPair);
			if(!exploreAndPlace(occupiedGridSpots,belowCur,startingPairIndex))
				GridSpotIdentifier.remove(current);
			else
				return true;
		}
		
		return false;
	}
}

