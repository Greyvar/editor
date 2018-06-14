package greyvarEditor.ui.windows;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;

import greyvarEditor.triggers.Action;
import greyvarEditor.triggers.Condition;
import greyvarEditor.triggers.Trigger;

public class WindowTriggerEdit extends JFrame {
	private final Trigger trigger;
	
	private JList lstConditions = new JList<Condition>(); 
	private JList lstActions = new JList<Action>(); 
	
	public WindowTriggerEdit(Trigger trigger) {
		this.trigger = trigger;
		
	}
	
	public void setupComponents() {
		this.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(6,6,6,6), 0, 0);
		
		this.add(new JLabel("Grid"), gbc);
		 
		gbc.gridy++; 
		this.add(new JLabel("Grid: ???"), gbc);
		
		gbc.gridy++;
		gbc.fill = gbc.NONE;
		this.add(new JLabel("Conditions"), gbc);
		
		gbc.gridy++; 
		gbc.fill = gbc.BOTH;
		this.add(new JScrollPane(lstConditions), gbc);
		  
		gbc.gridy++; 
		gbc.fill = gbc.NONE;
		this.add(new JLabel("Actions"), gbc);
		
		gbc.gridy++;
		gbc.fill = gbc.BOTH;
		this.add(new JScrollPane(lstActions), gbc);
		
		this.setTitle("Edit Trigger");
		this.setBounds(100, 100, 640, 480);
		this.setVisible(true); 
		this.doLayout();
	}
}
