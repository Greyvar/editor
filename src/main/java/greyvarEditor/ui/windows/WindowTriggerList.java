package greyvarEditor.ui.windows;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListDataListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import greyvarEditor.files.WorldFile;
import greyvarEditor.triggers.Trigger;
import greyvarEditor.ui.components.ComponentCrudList;
import jwrCommonsJava.ui.JButtonWithAl;

public class WindowTriggerList extends JFrame { 
	private ComponentCrudList<Trigger> triggerList;  
	
	private final WorldFile worldFile; 
	
	public WindowTriggerList(JFrame parent, WorldFile worldFile) {
		this.worldFile = worldFile;
		 
		this.triggerList = new ComponentCrudList<Trigger>(worldFile.triggers) {
			
			@Override
			public void onEdit(Trigger t) {
				new WindowTriggerEdit(WindowTriggerList.this, t).setVisible(true);	
			}
			
			@Override
			public void onDelete(Trigger t) {
				WindowTriggerList.this.worldFile.triggers.remove(t);
			} 
			
			@Override
			public void onCreate() {
				Trigger t = new Trigger();  
				WindowTriggerList.this.worldFile.triggers.add(t); 
				  
				new WindowTriggerEdit(WindowTriggerList.this, t).setVisible(true);
			} 
		};
		
		this.setupComponents();
		
		this.setTitle("Triggers for world: " + worldFile.title);
		this.setBounds(100, 100, 640, 480);
		this.setLocationRelativeTo(null); 
		this.setVisible(true); 
	} 
	 
	private void setupComponents() { 
		this.setIconImage(WindowMain.getInstance().getIconImage());
		
		GridBagConstraints gbc = new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(6,6,6,6), 0, 0);  
		this.setLayout(new GridBagLayout());
		
		gbc.gridwidth = gbc.REMAINDER;
		this.add(triggerList, gbc);  
		 
	}
}
