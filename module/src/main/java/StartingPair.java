package module.src.main.java;

import java.awt.Color;

public class StartingPair extends Pair<GridSpot,GridSpot>{
	public Color pairColor;
	public StartingPair(GridSpot l, GridSpot r, Color color) {
		super(l, r);	
		pairColor = color;
		// TODO Auto-generated constructor stub
	}
	public StartingPair(GridSpot l, GridSpot r) {
		super(l, r);
		// TODO Auto-generated constructor stub
	}
}
