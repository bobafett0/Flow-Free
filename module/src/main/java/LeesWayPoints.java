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
		GridSpotIdentifier = new Hashtable<GridSpot,StartingPair>();
		StartingPairs = startingPairs;
		GridSpotArray = gridSpotArray;
	}

	public Hashtable<GridSpot,StartingPair> placeAllWayPoints (ArrayList<StartingPair> startingPairs) {
		exploreAndPlace(GridUtilities.convertStartingPairsToGridSpots(StartingPairs),startingPairs.get(0).getL(),0);
		return GridSpotIdentifier;
	}
         
	
	private boolean exploreAndPlace (ArrayList<GridSpot> occupiedGridSpots, GridSpot current,int startingPairIndex) {
		StartingPair currentPair = StartingPairs.get(startingPairIndex);
		GridSpot goal = currentPair.getR();		
		if(current == goal){
			GridSpotIdentifier.put(current, currentPair);
			if(startingPairIndex + 1 >= StartingPairs.size())
				return GridUtilities.IsGridFilled(GridSpotIdentifier, GridSpotArray);
			else
				return exploreAndPlace(occupiedGridSpots,StartingPairs.get(startingPairIndex+1).getL() ,startingPairIndex+1);
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

		if ((rightOfCur != null && !occupiedGridSpots.contains(rightOfCur)) || rightOfCur == goal) {
			GridSpotIdentifier.put(current, currentPair);
			if(!exploreAndPlace(occupiedGridSpots,rightOfCur,startingPairIndex))
				GridSpotIdentifier.remove(current);
			else
				return true;
		}
		if ((leftOfCur != null && !occupiedGridSpots.contains(leftOfCur)) || leftOfCur == goal) {
			GridSpotIdentifier.put(current, currentPair);
			if(!exploreAndPlace(occupiedGridSpots,leftOfCur,startingPairIndex))
				GridSpotIdentifier.remove(current);
			else
				return true;
		}
		if ((aboveCur != null && !occupiedGridSpots.contains(aboveCur)) || aboveCur == goal) {
			GridSpotIdentifier.put(current, currentPair);
			if(!exploreAndPlace(occupiedGridSpots,aboveCur,startingPairIndex))
				GridSpotIdentifier.remove(current);
			else
				return true;
		}
		if ((belowCur != null && !occupiedGridSpots.contains(belowCur)) || belowCur == goal) {
			GridSpotIdentifier.put(current, currentPair);
			if(!exploreAndPlace(occupiedGridSpots,belowCur,startingPairIndex))
				GridSpotIdentifier.remove(current);
			else
				return true;
		}
		occupiedGridSpots.remove(current);
		return false;
	}
}

