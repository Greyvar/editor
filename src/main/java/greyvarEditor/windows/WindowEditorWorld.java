package greyvarEditor.windows;

import javax.swing.JInternalFrame;

public class WindowEditorWorld extends JInternalFrame {
	public WindowEditorWorld() {
		System.out.println(" new world editor");
		this.setVisible(true);
		this.setTitle("World Editor");
		this.setBounds(100, 100, 100, 100);
		this.setResizable(true);
		this.setMaximizable(true); 
	}

}
