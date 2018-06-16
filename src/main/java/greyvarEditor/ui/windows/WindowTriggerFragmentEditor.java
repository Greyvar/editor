package greyvarEditor.ui.windows;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import greyvarEditor.triggers.Fragment;
import greyvarEditor.triggers.FragmentArgument;
import greyvarEditor.triggers.LibraryOfFragments;
import greyvarEditor.triggers.Statement;
import jwrCommonsJava.ui.JButtonWithAl;

public class WindowTriggerFragmentEditor extends JFrame {
	private Fragment fragment;
	
	private JButton btnClose;
	
	private JPanel panStatement = new JPanel();
	 
	public WindowTriggerFragmentEditor(Fragment fragment ) {
		this.fragment = fragment;
		
		this.setupComponents();
		
		this.renderStatement();
	}
	
	private void renderStatement() {
		JLabel lblFragment = new JLabel(fragment.toString());
		lblFragment.setForeground(Color.MAGENTA); 
		panStatement.add(lblFragment);  
		
		for (String argName : this.fragment.getArguments().keySet()) {
			FragmentArgument fa = this.fragment.getArgument(argName);
			
			JLabel lbl = new JLabel(fa.toString());
			lbl.setForeground(Color.BLUE); 
			panStatement.add(lbl);
		}
	}
	
	private void closeWindow() {
		this.setVisible(false);
	}
	
	private JComboBox<String> cboConditions = new JComboBox<>();
	
	private void setupComponents() {
		this.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = jwrCommonsJava.Util.getNewGbc();
		 
		for (String cond : LibraryOfFragments.instance.getConditions()) {
			cboConditions.addItem(cond);	
		} 
		
		gbc.weighty = 0;
		this.add(cboConditions, gbc);
		
		gbc.fill = gbc.BOTH;
		gbc.weighty = 1;
		gbc.gridy++;
		this.add(panStatement, gbc);
		
		panStatement.setBackground(Color.WHITE);
		panStatement.setLayout(new FlowLayout());
		 
		this.add(new JScrollPane(panStatement), gbc);
		
		gbc.gridy++;
		gbc.weighty = 0; 
		gbc.anchor = gbc.SOUTHEAST; 
		gbc.fill = gbc.NONE; 
		this.btnClose = new JButtonWithAl("Close") {
			
			@Override
			public void click() {
				closeWindow();
			}
		};
		add(btnClose, gbc); 
		
		this.setTitle("Trigger Fragment editor");
		this.doLayout();
		this.setVisible(true);
		this.setBounds(300, 300, 640, 480);
		this.setLocationRelativeTo(null); 
	}
}
 