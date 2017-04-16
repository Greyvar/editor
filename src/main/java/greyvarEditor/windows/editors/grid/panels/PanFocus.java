package greyvarEditor.windows.editors.grid.panels;

import greyvarEditor.Tile;
import greyvarEditor.components.ComponentTextureViewer;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanFocus extends JPanel {
	public PanFocus() {
		this.setBorder(BorderFactory.createTitledBorder("Focus (red box)"));
	}

	public void setFocus(int x, int y, Tile t) {
		this.removeAll();
		this.setLayout(new GridLayout(1, 0));
		this.add(new JLabel("Cell: " + x + ":" + y));
		this.add(new ComponentTextureViewer(t.tex));
		this.doLayout();
		this.repaint();
	}

}
