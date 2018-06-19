package greyvarEditor;

import greyvarEditor.ui.windows.editors.grid.panels.Texture;

public class Entity {
	public int id; 
	public Texture tex;
 
	public Entity(Texture texTile, int id) {
		this.tex = texTile; 
		this.id = id; 
	}
}
