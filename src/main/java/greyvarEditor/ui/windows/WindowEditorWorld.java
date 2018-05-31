package greyvarEditor.ui.windows;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;

import greyvarEditor.files.GridFile;
import greyvarEditor.files.GridFileLoader;
import greyvarEditor.files.WorldFile;

public class WindowEditorWorld extends JInternalFrame  {

	public WindowEditorWorld(File file) {
		this(WorldFile.load(file));  
	}
	
	public WindowEditorWorld(WorldFile worldFile) {
		this.setVisible(true); 
		this.setTitle("World Editor:" + worldFile.title); 
		this.setBounds(100, 100, 240, 100); 
		this.setResizable(true);
		this.setMaximizable(true); 
		
		this.setLayout(new FlowLayout());
		
		this.add(new JLabel("Author: " + worldFile.author));
		this.add(new JLabel("Spawn: " + worldFile.spawnGrid)); 
			
		for (final File grid : worldFile.gridFiles) {
			JButton btnGrid = new JButton(grid.getName());
			btnGrid.addActionListener(new ActionListener() { 
				
				@Override
				public void actionPerformed(ActionEvent e) { 
					GridFile gf = GridFileLoader.load(grid); 	 	 		
					
					WindowMain.getInstance().addFrame(new WindowEditorGrid(gf));
					
				}
			});
			
			this.add(btnGrid); 
		}
	}

}
