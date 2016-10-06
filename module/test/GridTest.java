package module.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Random;
import module.src.main.java.*;

import org.junit.Test;

public class GridTestTest {

	@Test
	public void headAndTailStartingPointsAreNotSame() throws Exception {
		System.out.println("Running Test");
		Grid grid = new Grid(); 
		Random rand = new Random();
		int squWidth = 4+rand.nextInt(6);
		
		grid.initialize(squWidth);
		Gridspot[][] gridArray = grid.getGrid(squWidth);
		ArrayList<StartingPair> startingPairs = grid.generateRandomStartingPairs(gridArray,squWidth);
		
		for(int i = 0; i < startingPairs.size(); i++){			
			if(startingPairs.get(i).getL().equals(startingPairs.get(i).getR())) {
				throw new Exception();
			}			
		}
//		fail("Not yet implemented");
	}
	
	@Test
	public void startingPointsDontShareSamePoints() {
		
	}
	
	
//	public 

}