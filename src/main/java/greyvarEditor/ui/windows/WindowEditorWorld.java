package greyvarEditor.ui.windows;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;

import greyvarEditor.files.GridFile;
import greyvarEditor.files.GridFileLoader;
import greyvarEditor.files.WorldFile;
import jwrCommonsJava.ui.JButtonWithAl;

public class WindowEditorWorld extends JInternalFrame  {

	public WindowEditorWorld(File file) {
		this(WorldFile.load(file));  
	} 
	
	public WindowEditorWorld(final WorldFile worldFile) {
		this.setTitle("World Editor:" + worldFile.title); 
		this.setBounds(100, 100, 240, 100); 
		this.setResizable(true);
		this.setMaximizable(true); 
		this.setClosable(true); 
		
		this.setLayout(new GridBagLayout());
		  
		GridBagConstraints gbc = new GridBagConstraints(0,0,1,1,1,1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(6,6,6,6), 0, 0); 
		
		this.add(new JLabel("Author: " + worldFile.author), gbc);
		 
		gbc.gridx++;
		this.add(new JLabel("Spawn: " + worldFile.spawnGrid), gbc);
		
		gbc.gridwidth = gbc.REMAINDER;
		gbc.gridx = 0; 
		gbc.gridy++;
		this.add(new JButtonWithAl("Triggers") {
			@Override
			public void click() { 
				new WindowTriggerList(WindowMain.getInstance(), worldFile).setVisible(true);
			}
		}, gbc);
		 
		gbc.gridy++; 
		this.add(new JLabel("---"), gbc);
		
		for (final File grid : worldFile.gridFiles) {
			if (!grid.getName().endsWith(".grid")) {
				continue;
			} 
			
			JButton btnGrid = new JButton(grid.getName());
			btnGrid.addActionListener(new ActionListener() { 
				
				@Override
				public void actionPerformed(ActionEvent e) { 
					GridFile gf = GridFileLoader.load(grid); 	 	 		
					
					WindowMain.getInstance().addFrame(new WindowEditorGrid(gf));
					
				}
			});
			
			gbc.gridy++;  
			this.add(btnGrid, gbc); 
		}
		
		this.pack();
		this.setVisible(true);
	}

}
