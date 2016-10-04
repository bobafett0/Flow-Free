
public class GridUtilities {
	
	public static boolean isFilled(Grid grid)
	{		
		Gridspot[][] gridSpotArray = grid.GridSpotArray;
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
