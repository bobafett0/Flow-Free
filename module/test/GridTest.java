package module.test;

import static org.junit.Assert.*;
//import com.jcabi.aspects.*;
import java.util.ArrayList;
import java.util.Random;

import groovy.util.GroovyTestCase;
import module.src.main.java.*;

import org.junit.Test;

public class GridTest extends GroovyTestCase {

	/*
	 * No two Starting point Gridspots are the same at all 
	 */
	
	@Test
	public void testAllStartingPairsAreUnique4() throws Exception {
		Grid grid = new Grid(); 
		int squWidth = 4;	
		checkStartingPairUniqueness(grid,squWidth);
	}
	
	@Test
	public void testAllStartingPairsAreUnique5() throws Exception {
		Grid grid = new Grid(); 
		int squWidth = 5;	
		checkStartingPairUniqueness(grid,squWidth);
	}
	
	@Test
	public void testAllStartingPairsAreUnique6() throws Exception {
		Grid grid = new Grid(); 
		int squWidth = 6;	
		checkStartingPairUniqueness(grid,squWidth);
	}
	
	@Test
	public void testAllStartingPairsAreUnique7() throws Exception {
		Grid grid = new Grid(); 
		int squWidth = 7;	
		checkStartingPairUniqueness(grid,squWidth);
	}
	
	@Test
	public void testAllStartingPairsAreUnique8() throws Exception {
		Grid grid = new Grid(); 
		int squWidth = 8;	
		checkStartingPairUniqueness(grid,squWidth);
	}
	
	@Test
	public void testAllStartingPairsAreUnique9() throws Exception {
		Grid grid = new Grid(); 
		int squWidth = 9;	
		checkStartingPairUniqueness(grid,squWidth);
	}
	
	@Test
	public void testAllStartingPairsAreUnique10() throws Exception {
		Grid grid = new Grid(); 
		int squWidth = 10;	
		checkStartingPairUniqueness(grid,squWidth);
	}
	
	private void checkStartingPairUniqueness(Grid grid, int squWidth) throws Exception{
		grid.initialize(squWidth);
		ArrayList<StartingPair> startingPairs = grid.GetStartingPairs();
				
		for(int i = 0; i < startingPairs.size(); i++){		
			checkAgainstList(startingPairs,startingPairs.get(i).getL());
			checkAgainstList(startingPairs,startingPairs.get(i).getR());
		}
	}
	
	private void checkAgainstList(ArrayList<StartingPair> startingPairs, GridSpot gridSpot ) throws Exception
	{
		int count = 0;
		for(int i = 0; i < startingPairs.size(); i++){			
			if(startingPairs.get(i).getL().equals(gridSpot)) {
				count++;
			}
			if(startingPairs.get(i).getR().equals(gridSpot)){
				count++;
			}
			if(count > 1){
				throw new Exception("Given GridSpot was present in the starting pairs list more than once.");
			}
		}
	}
}