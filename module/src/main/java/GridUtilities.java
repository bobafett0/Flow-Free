package module.src.main.java;

import java.util.ArrayList;
import java.util.Hashtable;




public class GridUtilities {
	
	public static boolean IsGridFilled(Hashtable<GridSpot,StartingPair> solution, GridSpot[][] startingBoard ){
		for(int i = 0; i < startingBoard.length; i++){
			for(int u = 0; u < startingBoard[i].length; u++){
				if(solution.get(startingBoard[i][u]) == null){
					return false;
				}					
			}
		}
		return true;		
	}

	public static ArrayList<GridSpot> convertStartingPairsToGridSpots(ArrayList<StartingPair> startingPairs) {
		ArrayList<GridSpot> gridSpots = new ArrayList<GridSpot>();
		for(int i = 0; i < startingPairs.size(); i++){
			StartingPair curPair = startingPairs.get(i);
			gridSpots.add(curPair.getL());
			gridSpots.add(curPair.getR());
		}
		return gridSpots;
	}

	public static ArrayList<GridSpot> getNeighbors(GridSpot current, GridSpot[][] gridSpotArray) {
		ArrayList<GridSpot> ret = new ArrayList<GridSpot>();
		if(current.XIndex + 1 < gridSpotArray.length) {
			ret.add(gridSpotArray[current.XIndex+1][current.YIndex]);
		}
		if(current.XIndex - 1 >= 0) {
			ret.add(gridSpotArray[current.XIndex-1][current.YIndex]);
		}
		if(current.YIndex - 1 >= 0) {
			ret.add(gridSpotArray[current.XIndex][current.YIndex-1]);
		}
		if(current.YIndex + 1 < gridSpotArray[current.XIndex].length) {
			ret.add(gridSpotArray[current.XIndex][current.YIndex+1]);
		}
		return ret;
	}	
}
