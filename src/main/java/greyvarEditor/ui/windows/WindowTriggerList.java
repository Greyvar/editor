package greyvarEditor.ui.windows;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

import greyvarEditor.files.WorldFile;
import greyvarEditor.triggers.Trigger;
import jwrCommonsJava.ui.JButtonWithAl;

public class WindowTriggerList extends JFrame { 
	private JList<TriggerListModel> triggerList;  
	private JButton btnDelete;
	private JButton btnCreate;
	private JButton btnEdit; 
	
	private WorldFile worldFile; 
	
	private class TriggerListModel implements ListModel<Trigger> {

		@Override 
		public int getSize() {
			return 0;
		}

		@Override
		public Trigger getElementAt(int index) {
			return null;
		} 

		@Override
		public void addListDataListener(ListDataListener l) {
			
		}

		@Override
		public void removeListDataListener(ListDataListener l) {
			
		}
	} 
	
	public WindowTriggerList(WorldFile worldFile) {
		this.worldFile = worldFile;
		 
		this.triggerList = new JList(new TriggerListModel());  
		
		this.setTitle("Triggers for world: " + worldFile.title); 
		  
		this.setupComponents(); 
		this.setBounds(100, 100, 640, 480);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	} 
	 
	private void setupComponents() { 
		GridBagConstraints gbc = new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(6,6,6,6), 0, 0);  
		this.setLayout(new GridBagLayout());
		
		gbc.gridwidth = gbc.REMAINDER;
		this.add(new JScrollPane(triggerList), gbc); 
		 
		gbc.gridy++; 
		gbc.gridwidth = 1;
		gbc.anchor = gbc.CENTER;
		gbc.fill = gbc.NONE;
		this.btnCreate = new JButtonWithAl("Create") {
			@Override
			public void click() { 
				onClickCreateTrigger();
			}
		};
		
		gbc.gridwidth = 1; 
		gbc.anchor = gbc.SOUTHEAST; 
		this.add(btnCreate, gbc);
		
		gbc.gridx++;
		this.btnEdit = new JButtonWithAl("Edit") {
			
			@Override
			public void click() {
				onClickEditTrigger();
			}
		};
		
		this.add(btnEdit, gbc);
		
		gbc.gridx++;
		this.btnDelete = new JButtonWithAl("Delete") {
			
			@Override
			public void click() {
				onClickDeleteTrigger();
			}
		};
		 
		this.add(btnDelete, gbc);
	}
	
	private void onClickCreateTrigger() {
	
	}
	
	private void onClickEditTrigger() {
		
	}
	
	private void onClickDeleteTrigger() {
		
	}	
}
