package greyvarEditor.files;

import java.io.File;

import greyvarEditor.Entity;
import greyvarEditor.Tile;

public interface GridFile { 
	public Tile[][] getTileList();

	public String getFilename(); 
	
	public void load();

	public int getGridHeight();
 
	public int getGridWidth();

	public void save();

	public File getFile(); 

	public Entity[][] getEntityList();   
	
	public int nextEntityId();

	public void grow(int i, int j);  
}
 