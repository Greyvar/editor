package greyvarEditor;

import greyvarEditor.windows.editors.grid.panels.Texture;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class JTextureViewer extends JLabel {
	private Texture tex = null;

	public JTextureViewer() {
		super("No texture");
		this.setPreferredSize(new Dimension(200, 200));
		this.setBackground(Color.GRAY);
		this.setBorder(BorderFactory.createLoweredBevelBorder());
	}

	public JTextureViewer(Texture t) {
		this();
		this.setTex(t);
	}

	public Texture getTexture() {
		return this.tex;
	}

	public void setTex(Texture tex) {
		this.tex = tex;
		this.setIcon(new ImageIcon(tex.image));
		this.setText(tex.getId());
		this.repaint();
	};
}
