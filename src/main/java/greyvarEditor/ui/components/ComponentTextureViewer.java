package greyvarEditor.ui.components;

import greyvarEditor.ui.windows.WindowTextureChooser;
import greyvarEditor.ui.windows.editors.grid.panels.Texture;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import jwrCommonsJava.ui.JButtonWithAl;

public class ComponentTextureViewer extends JPanel implements WindowTextureChooser.Listener {
	private Texture tex = null;
	private final JLabel lblTex = new JLabel(); 

	private final JButton btnChoose = new JButtonWithAl("Choose") {
 
		@Override
		public void click() {
			new WindowTextureChooser(ComponentTextureViewer.this);
		}
	};

	public ComponentTextureViewer() {
		this(false);
	}

	public ComponentTextureViewer(boolean chooserButton) {
		this.lblTex.setText("No texture");
		this.lblTex.setPreferredSize(new Dimension(200, 200));
		this.lblTex.setBackground(Color.GRAY);
		this.lblTex.setBorder(BorderFactory.createLoweredBevelBorder());

		this.setLayout(new BorderLayout());
		this.add(this.lblTex, BorderLayout.CENTER);

		if (chooserButton) {
			this.add(this.btnChoose, BorderLayout.EAST);
		}
	}

	public ComponentTextureViewer(Texture t) {
		this();
		this.setTex(t);
	}

	public Texture getTexture() {
		return this.tex;
	}

	@Override
	public void onTexChoose(Texture tex) {
		this.setTex(tex);
	}

	public void setTex(Texture tex) {
		this.tex = tex;
		this.lblTex.setIcon(new ImageIcon(tex.image));
		this.lblTex.setText(tex.getId());
		this.lblTex.repaint();
	};
}
