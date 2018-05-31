package greyvarEditor.ui.windows.editors.grid.panels;

import greyvarEditor.TextureCache;
import greyvarEditor.ui.components.ComponentTextureViewer;
import greyvarEditor.ui.windows.WindowTextureChooser;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

import jwrCommonsJava.ui.JButtonWithAl;

public class PanAppearance extends JPanel implements WindowTextureChooser.Listener {
	private final JCheckBox chkPaintTexture = new JCheckBox("Paint");
	private final ComponentTextureViewer texViewer = new ComponentTextureViewer();
	private final JButton btnRotate;
	private final JButton btnFlipH;
	private final JButton btnFlipV;
	private final JPanel panControls = new JPanel();
	private int rot = 0;

	private boolean flipV = false;

	private boolean flipH = false;

	public PanAppearance() {

		this.setBorder(BorderFactory.createTitledBorder("Appearance"));
		this.setLayout(new BorderLayout());
		this.add(this.texViewer, BorderLayout.CENTER);
		JButton btnShowTextureChooser = new JButtonWithAl("Choose tex") {
			@Override
			public void click() {
				new WindowTextureChooser(PanAppearance.this);
			}
		};

		this.add(btnShowTextureChooser, BorderLayout.EAST);
		this.add(this.panControls, BorderLayout.SOUTH);
		this.panControls.setLayout(new FlowLayout());
		this.panControls.add(this.chkPaintTexture);

		this.btnRotate = new JButtonWithAl("Rotate to " + this.rot, false) {
			@Override
			public void click() {
				PanAppearance.this.onBtnRotateClicked();
			}
		};

		this.panControls.add(this.btnRotate);

		this.btnFlipH = new JButtonWithAl("Flip H", false) {
			@Override
			public void click() {
				PanAppearance.this.onFlipHClicked();
			};
		};

		this.panControls.add(this.btnFlipH);

		this.btnFlipV = new JButtonWithAl("Flip V", false) {
			@Override
			public void click() {
				PanAppearance.this.onFlipVClicked();
			};
		};

		this.panControls.add(this.btnFlipV);
	}

	public Texture getCurrentTexture() {
		return this.texViewer.getTexture();
	}

	private void onBtnRotateClicked() {
		this.rot += 90;
		this.rot = (this.rot >= 360) ? this.rot - 360 : this.rot;

		this.updateTexTile();

		this.btnRotate.setText("Rotate to " + this.rot);
	}

	private void onFlipHClicked() {
		this.flipH = !this.flipH;

		this.updateTexTile();
	}

	private void onFlipVClicked() {
		this.flipV = !this.flipV;

		this.updateTexTile();
	}

	@Override
	public void onTexChoose(Texture tex) {
		this.setCurrentTexture(tex);
	}

	public void setCurrentTexture(Texture tex) {
		if (tex == null) {
			return;
		}

		this.texViewer.setTex(tex);

		this.btnRotate.setEnabled(true);
		this.btnFlipH.setEnabled(true);
		this.btnFlipV.setEnabled(true);

	}

	private void updateTexTile() {
		Texture currentTexture = this.texViewer.getTexture();
		Texture newTexture = TextureCache.instance.getTexTile(currentTexture.getFilename().getName(), this.rot, this.flipV, this.flipH);
		this.texViewer.setTex(newTexture);
	}

}
