
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Random;

import junit.framework.TestResult;
import junit.framework.TestSuite;
//import java.org.junit.runner;
import junit.runner.BaseTestRunner;
import junit.textui.TestRunner;
import wheelsunh.users.Frame;

import org.junit.Test;
import org.junit.runners.model.TestClass;
//import org.junit.Test;

public class GridTest {	
		
	@test
	public void GenerateRandomStartingPairsMakesCorrectStartingPairs(){
		System.out.println("Running Test");
		Grid grid = new Grid(); 
		Random rand = new Random();
		int squWidth = 4+rand.nextInt(6);
		Gridspot[][] gridArray = grid.getGrid(squWidth);
		ArrayList<StartingPair> startingPairs = grid.generateRandomStartingPairs(gridArray,squWidth);
		
		for(int i = 0; i < startingPairs.size(); i++){
			
		}
	}
	
	public static void main (String args[]) throws InterruptedException
	{
		TestRunner testRunner = new TestRunner();
		
		TestSuite testSuite = (TestSuite) testRunner.getTest("GridTest");
		Enumeration<junit.framework.Test> tests = testSuite.tests();
		TestResult result = new TestResult();
		
		while(tests.hasMoreElements())
		{
//			System.out.println(tests.nextElement().toString());
			junit.framework.Test test = tests.nextElement();
			test.run(result);
			System.out.println(test);
		}
//		for(int i = 0; i < tests.nextElement(); i++)
//		{
//			System.out.println(tests.get(i));
//		}
		
//		testSuite.addTest(test);
//		TestResult result = new TestResult();
//		testSuite.run(result);
//		System.out.println(result);
//		System.out.println(test.tests());
//		new Grid(4);
	}
	
}