package module.src.main.java;

import java.awt.Color;

public class StartingPair extends Pair<GridSpot,GridSpot>{
	int xIndex, yIndex;
	public Color pairColor;
	public StartingPair(GridSpot l, GridSpot r, Color color) {
		super(l, r);	
		pairColor = color;
		// TODO Auto-generated constructor stub
	}
}
