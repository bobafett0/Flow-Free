
public static class GridUtilities {
	
	private boolean isFilled()
	{
		Gridspot[][] grid = _find.a._screen.getGrid();
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
