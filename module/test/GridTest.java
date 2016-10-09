package module.test;

import static org.junit.Assert.*;
//import com.jcabi.aspects.*;
import java.util.ArrayList;
import java.util.Random;
import module.src.main.java.*;

import org.junit.Test;

public class GridTest {

	/*
	 * No two Starting point Gridspots are the same at all 
	 */
	
	@Test
	public void headAndTailStartingPointsAreNotSame() throws Exception {
		System.out.println("Running Test");
		
		Grid grid = new Grid(); 
		Random rand = new Random();
		int squWidth = 4+rand.nextInt(6);
		
		grid.initialize(squWidth);
		ArrayList<StartingPair> startingPairs = grid.getStartingPairs();
				
		for(int i = 0; i < startingPairs.size(); i++){		
			for(int j = 0; j < startingPairs.size(); j++){
				
				if(startingPairs.get(i).getL().equals(startingPairs.get(i).getR())) {
					throw new Exception();
				}
			}
		}
//		fail("Not yet implemented");
	}
	
	public void checkAgainstList(ArrayList<StartingPair> startingPairs, GridSpot gridSpot )
	{
		
	}
	
	@Test
	public void startingPointsDontShareSamePoints() {
		
	}
	
	
//	public 

}