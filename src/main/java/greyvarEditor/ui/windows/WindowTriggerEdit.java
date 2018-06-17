package greyvarEditor.ui.windows;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import greyvarEditor.triggers.Action;
import greyvarEditor.triggers.Condition;
import greyvarEditor.triggers.Trigger;
import greyvarEditor.ui.components.ComponentCrudList;
 
public class WindowTriggerEdit extends JFrame {
	private final Trigger trigger;
	
	private JTextField txtTitle = new JTextField();
	private ComponentCrudList<Condition> lstConditions;
	private ComponentCrudList<Action> lstActions;
	
	public WindowTriggerEdit(WindowTriggerList wnd, Trigger trigger) {
		this.trigger = trigger;
		
		setupComponents();
		
		this.pack(); 
		this.setLocationRelativeTo(null); 
		this.requestFocus(); 
		this.setVisible(true);
	}
	
	public void setupComponents() {
		this.setIconImage(WindowMain.getInstance().getIconImage()); 
		
		this.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.NORTHEAST, GridBagConstraints.BOTH, new Insets(6,6,6,6), 0, 0);
		
		gbc.anchor = gbc.NORTHEAST;
		gbc.weightx = 0;
		gbc.weighty = 0; 
		this.add(new JLabel("Title:"), gbc); 
		 
		gbc.gridx++; 
		gbc.weightx = 1; 
		gbc.anchor = gbc.NORTHWEST; 
		txtTitle.setText(trigger.title); 
		this.add(txtTitle, gbc);
		
		gbc.gridy++;
		gbc.weightx = 0;
		gbc.gridx = 0;
		gbc.anchor = gbc.NORTHEAST; 
		gbc.fill = gbc.NONE;
		this.add(new JLabel("Conditions:"), gbc);
		
		
		gbc.gridx++; 
		gbc.weightx = 1;
		gbc.weighty = 1; 
		gbc.anchor = gbc.NORTHWEST;
		gbc.fill = gbc.BOTH; 
		lstConditions = new ComponentCrudList<Condition>(trigger.conditions) {
			@Override
			public void onCreate() {
				Condition c = new Condition();
				trigger.conditions.add(c);
				
				new WindowTriggerFragmentEditor(c);
			}

			@Override
			public void onDelete(Condition c) {
				trigger.conditions.remove(c);
			}

			@Override
			public void onEdit(Condition c) {
				new WindowTriggerFragmentEditor(c);
			}
		};
		this.add(lstConditions, gbc);
		  
		gbc.gridy++;
		gbc.gridx = 0;
		gbc.weightx = 0;
		gbc.anchor = gbc.NORTHEAST;
		gbc.fill = gbc.NONE;
		this.add(new JLabel("Actions:"), gbc);
		
		gbc.gridx++;
		gbc.weightx = 1;
		gbc.anchor = gbc.NORTHWEST;
		gbc.fill = gbc.BOTH;
		lstActions = new ComponentCrudList<Action>(trigger.actions) {

			@Override
			public void onCreate() {
				Action a = new Action();
				trigger.actions.add(a);
				
				new WindowTriggerFragmentEditor(a);
			}

			@Override
			public void onDelete(Action a) {
				trigger.actions.remove(a);
			}

			@Override
			public void onEdit(Action a) {
				new WindowTriggerFragmentEditor(a);
			}
		};  
		this.add(lstActions, gbc);
		
		this.setTitle("Edit Trigger");
		this.setBounds(100, 100, 640, 480);
		this.setVisible(true); 
		this.doLayout();
	}
	   
	@Override
	public void setVisible(boolean b) {
		super.setVisible(b);
		
		if (!b) {
			this.onClose();
		}
	}
	
	private void onClose() {
		trigger.title = txtTitle.getText();  
	}
}
