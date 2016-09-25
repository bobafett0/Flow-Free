
public static class GridUtilities {
	
	private boolean isFilled(Grid grid)
	{
		for(int i = 0; i < grid.length; i++ )
		{
			for(int u = 0; u < grid[i].length; u++)
			{
				if(!grid[i][u].occupied)
					return false;
			}
		}
		return true;
	}
}
