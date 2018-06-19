package greyvarEditor.dataModel;

import greyvarEditor.GameResources;
import greyvarEditor.TextureCache;
import greyvarEditor.ui.windows.editors.grid.panels.Texture;

public class EntityInstance {
	public int id;  
	public String definition;
	
	public transient EntityDefinition definitionLink;
	public transient Texture editorTexture;
	
	public EntityInstance() {
		
	}
	
	public void setDefinition(String def) {
		this.definition = def;
		
		this.definitionLink = GameResources.entityDatabase.entdefs.get(this.definition);
		this.editorTexture = TextureCache.instanceEntities.getTex(this.definitionLink.getFirstState().tex);
	}  
 
	public EntityInstance(String definition, int id) {
		this.id = id; 
		 
		this.setDefinition(definition); 
	}
}
