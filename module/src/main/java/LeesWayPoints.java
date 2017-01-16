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
		ArrayList<GridSpot> neighbors = GridUtilities.getNeighbors(current,GridSpotArray);

		for(int i = 0; i < neighbors.size(); i++){
			GridSpot curNeighbor = neighbors.get(i);
			if ((curNeighbor != null && !occupiedGridSpots.contains(curNeighbor)) || curNeighbor == goal) {
				GridSpotIdentifier.put(current, currentPair);
				if(!exploreAndPlace(occupiedGridSpots,curNeighbor,startingPairIndex))
					GridSpotIdentifier.remove(current);
				else
					return true;
			}
		}
		occupiedGridSpots.remove(current);
		return false;
	}
}

