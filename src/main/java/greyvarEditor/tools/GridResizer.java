package greyvarEditor.tools;

import java.io.File;
import java.io.IOException;

import greyvarEditor.files.GridFileYaml;
import greyvarEditor.ui.windows.WindowEditorGrid;

public class GridResizer {
	private final WindowEditorGrid windowEditorGrid;

	public GridResizer(WindowEditorGrid windowEditorGrid) {
		this.windowEditorGrid = windowEditorGrid;
	}
	
	public void resize() { 
		windowEditorGrid.getGridFile().grow(2, 2); 
	}
}
 