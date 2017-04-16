package greyvarEditor.windows;

import greyvarEditor.GridFile;
import greyvarEditor.Tile;
import greyvarEditor.components.ComponentGridArea;
import greyvarEditor.components.ComponentGridArea.ImageMatrixCellChangeListener;
import greyvarEditor.menubars.MenuBarEditorGrid;
import greyvarEditor.windows.editors.grid.panels.PanAppearance;
import greyvarEditor.windows.editors.grid.panels.PanFocus;
import greyvarEditor.windows.editors.grid.panels.PanPhysics;
import greyvarEditor.windows.editors.grid.panels.PanTransport;
import greyvarEditor.windows.editors.grid.panels.Texture;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.WindowConstants;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import jwrCommonsJava.Configuration;
import jwrCommonsJava.Logger;

public class WindowEditorGrid extends PopableWindow implements ImageMatrixCellChangeListener {

	public final ComponentGridArea im;

	private JSplitPane sp;

	private final PanFocus panFocus = new PanFocus();
	private final PanAppearance panAppearance = new PanAppearance();
	private final PanPhysics panPhysics = new PanPhysics();
	private final PanTransport panTransport = new PanTransport();

	private final GridFile gf;

	public WindowEditorGrid(GridFile gf) {
		this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		this.addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameClosing(InternalFrameEvent e) {
				WindowEditorGrid.this.windowClose();
			}
		});

		this.gf = gf;
		this.im = new ComponentGridArea(this);
		this.im.setGridFile(gf);

		this.setVisible(true);
		this.setTitle("Grid Editor - " + gf.getFilename());
		this.setBounds(Configuration.getBounds("windowEditorGrid", 100, 100, 320, 240));
		this.setResizable(true);
		this.setMaximizable(true);
		this.setClosable(true);
		this.setIconifiable(true);

		this.requestFocus();

		this.setupComponents();
	}

	public void blanketTextureEverything() {
		Texture t = this.panAppearance.getCurrentTexture();

		if (t == null) {
			JOptionPane.showMessageDialog(this, "Select a texture first!");
		} else {
			this.im.blanketTextureEverything(t);
		}
	}

	public GridFile getGridFile() {
		return this.gf;
	}

	public Icon getScreenshot() {
		return new ImageIcon(this.im.getScreenshot());
	}

	@Override
	public void onCellApplyPaint(int x, int y, Tile tile) {
		tile.tex = this.panAppearance.getCurrentTexture();
	}

	@Override
	public void onCellFocus(int x, int y, Tile t) {
		this.panFocus.setFocus(x, y, t);
		this.panPhysics.updateInterface(t);
		this.panTransport.updateSelectedTile(x, y, t);
	}

	@Override
	public void onCellSelected(int currx, int curry, Tile tile) {
		this.panAppearance.setCurrentTexture(tile.tex);
		this.panFocus.setFocus(currx, curry, tile);
	}

	public void reload() {
		this.gf.load();
		this.im.setGridFile(this.gf);
	}

	private void setupComponents() {
		this.setJMenuBar(new MenuBarEditorGrid(this));

		JPanel panControls = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, 1, new Insets(6, 6, 6, 6), 0, 0);

		gbc.weighty = 0;
		panControls.add(this.panFocus, gbc);

		gbc.gridy++;
		panControls.add(this.panAppearance, gbc);

		gbc.weighty = 1;
		gbc.gridy++;
		panControls.add(this.panPhysics, gbc);

		gbc.gridy++;
		panControls.add(this.panTransport, gbc);

		this.sp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, new JScrollPane(this.im), panControls);
		this.sp.setDividerLocation(Configuration.getI("windowEditorGrid.sidebarPositionFromLeft", 300));
		this.add(this.sp, BorderLayout.CENTER);
	}

	public void windowClose() {
		int result = JOptionPane.showOptionDialog(this, "Close?", "Close?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, JOptionPane.NO_OPTION);

		if (result == JOptionPane.YES_OPTION) {
			super.internalFrameClosed(null);
			this.setVisible(false);
			this.dispose();
		}

		Logger.messageDebug("Saving editor bounds");
		Configuration.setBounds("windowEditorGrid", this.getBounds());
		Configuration.set("windowEditorGrid.sidebarPositionFromLeft", this.sp.getDividerLocation());
	}

}
