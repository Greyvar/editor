package greyvarEditor;

import greyvarEditor.ui.windows.editors.grid.panels.Texture;

public class EntityInstance {
	public int id;  
	public String definition; 
	public Texture editorTexture;
 
	public EntityInstance(String definition, int id) {
		this.definition = definition; 
		this.id = id; 
		
		this.editorTexture = GameResources.entityDatabase.getEntityTexture(definition); 
	}
}
