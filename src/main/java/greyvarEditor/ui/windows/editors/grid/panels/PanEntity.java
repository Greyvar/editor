package greyvarEditor.ui.windows.editors.grid.panels;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import greyvarEditor.GameResources;
import greyvarEditor.TextureCache;
import greyvarEditor.dataModel.EntityDefinition;
import greyvarEditor.dataModel.EntityInstance;
import greyvarEditor.ui.components.ComponentTextureViewer;
import greyvarEditor.utils.EditLayerMode;
import jwrCommonsJava.ui.JButtonWithAl;

public class PanEntity extends JPanel implements ActionListener {
	public PanEntity() {
		this.setBorder(BorderFactory.createTitledBorder("Entity")); 
		this.setupComponents();
	}

	private JComboBox<String> cboEntities = new JComboBox<>(); 
	private ComponentTextureViewer viewer = new ComponentTextureViewer();
	
	private void setupComponents() {
		for (EntityDefinition ed : GameResources.entityDatabase.entdefs.values()) {
			cboEntities.addItem(ed.title);
		}  
		
		cboEntities.addActionListener(this);
		
		this.setLayout(new BorderLayout());   
	
		this.add(cboEntities, BorderLayout.SOUTH); 
		this.add(viewer, BorderLayout.CENTER); 
		this.doLayout();	 
	}
	
	public void setCurrentEntity(EntityInstance entity) {
		if (entity != null) {
			cboEntities.getModel().setSelectedItem(entity.definition);
		} else {
			//this.viewer.setTex(TextureCache.instanceEntities.getDefault()); 
		}
	}

	public EntityInstance getNewSelected(int id) {   
		return new EntityInstance(cboEntities.getSelectedItem().toString(), id);
	}
 
	@Override
	public void actionPerformed(ActionEvent e) {
		Texture tex = GameResources.entityDatabase.getEntityTexture(cboEntities.getSelectedItem().toString());
		 
		this.viewer.setTex(tex);
	}
}
