package greyvarEditor.ui.windows.editors.grid.panels;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import greyvarEditor.Entity;
import greyvarEditor.TextureCache;
import greyvarEditor.ui.components.ComponentTextureViewer;
import greyvarEditor.utils.EditLayerMode;
import jwrCommonsJava.ui.JButtonWithAl;

public class PanEntity extends JPanel {
	public PanEntity() {
		this.setBorder(BorderFactory.createTitledBorder("Entity")); 
		this.setupComponents();
	}

	private ComponentTextureViewer viewer;
	
	private void setupComponents() {
		this.viewer = new ComponentTextureViewer(true, null, EditLayerMode.ENTITIES);
		 
		this.setLayout(new BorderLayout());
		this.add(viewer, BorderLayout.CENTER); 
		this.doLayout();	
	}
	
	public void setCurrentEntity(Entity entity) {
		if (entity != null) {
			this.viewer.setTex(entity.tex);
		} else {
			this.viewer.setTex(TextureCache.instanceEntities.getDefault()); 
		}
	
	}

	public Texture getTexture() { 
		return this.viewer.getTexture();
	}

	public Entity getNewSelected(int id) { 
		return new Entity(this.viewer.getTexture(), id);  
	}
}
