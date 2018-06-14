package greyvarEditor.ui.windows;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import greyvarEditor.triggers.Fragment;
import greyvarEditor.triggers.FragmentArgument;
import greyvarEditor.triggers.Statement;

public class WindowTriggerFragmentEditor extends JFrame {
	private Fragment fragment;
	
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
	
	private void setupComponents() {
		this.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = jwrCommonsJava.Util.getNewGbc();
		gbc.fill = gbc.BOTH; 
		 
		this.add(panStatement, gbc);
		
		panStatement.setBackground(Color.WHITE);
		panStatement.setLayout(new FlowLayout());
		 
		this.add(new JScrollPane(panStatement), gbc); 
		
		this.setTitle("Statement editor");
		this.doLayout();
		this.setVisible(true);
		this.setBounds(300, 300, 640, 480);
		this.setLocationRelativeTo(null); 
	}
}
 