package module.src.main.java;

import java.util.Hashtable;

import module.src.main.java.LeesWayPoints;

public class SolutionVerifier {
	
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

}
