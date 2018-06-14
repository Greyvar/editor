package greyvarEditor.triggers.arguments;

import greyvarEditor.triggers.FragmentArgument;

public class ArgumentTileCoordinates extends FragmentArgument {
	public int x = 0;
	public int y = 0;
	
	public ArgumentTileCoordinates(int x, int y) {
		this.x = x;
		this.y = y; 
	}
	
	@Override
	public String toString() { 
		return "Tile: {" + this.x + ", " + this.y + "}";
	}
} 
