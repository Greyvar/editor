package greyvarEditor.ui.windows;

import java.awt.FlowLayout;

import javax.swing.JDialog;
import javax.swing.JFrame;

import greyvarEditor.tools.GridResizer;
import jwrCommonsJava.ui.JButtonWithAl;

public class WindowResizeGrid extends JDialog {
	public class ResizeResult {
		int w = 0;
		int h = 0; 
	}
	
	private final WindowEditorGrid windowEditorGrid;
	
	public WindowResizeGrid(WindowEditorGrid windowEditorGrid) {
		this.windowEditorGrid = windowEditorGrid;
		  
		setupComponents();
	}
	
	private ResizeResult rr; 
	 
	private void setupComponents() {
		this.setLayout(new FlowLayout()); 
		this.add(new JButtonWithAl("Done") {
			
			@Override
			public void click() {
				doResize();
				WindowResizeGrid.this.setVisible(false);
			}


		});
		
		this.doLayout(); 
	}
	
	private void doResize() {
		GridResizer resizer = new GridResizer(windowEditorGrid);
		resizer.resize( );
	} 

	public ResizeResult getResult() {
		return this.rr; 
	}
}  
