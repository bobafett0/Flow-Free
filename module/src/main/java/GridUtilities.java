package module.src.main.java;


public class GridUtilities {
	
	public static boolean isFilled(Grid grid)
	{		
		GridSpot[][] gridSpotArray = grid.getGrid();
		for(int i = 0; i < gridSpotArray.length; i++ )
		{
			for(int u = 0; u < gridSpotArray[i].length; u++)
			{
				if(!gridSpotArray[i][u].occupied)
					return false;
			}
		}
		return true;
	}
}
