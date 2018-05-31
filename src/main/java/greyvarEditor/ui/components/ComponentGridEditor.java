package greyvarEditor.ui.components;

import greyvarEditor.TextureCache;
import greyvarEditor.Tile;
import greyvarEditor.files.GridFile;
import greyvarEditor.ui.windows.editors.grid.panels.Texture;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints; 
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.Vector;

import javax.swing.JComponent;

public class ComponentGridEditor extends JComponent implements MouseListener, MouseMotionListener {
	public interface ImageMatrixCellChangeListener {
		void onCellApplyPaint(int x, int y, Tile tile);

		void onCellFocus(int x, int y, Tile tile);

		void onCellSelected(int currx, int curry, Tile tile);
	}

	private int currx;
	private int curry;

	private Tile[][] tileList;

	private final ImageMatrixCellChangeListener listener;

	private GridFile gf;
	private Tile lastHoveredTile;
	private int selectionRectSize = 1;
	public boolean highlightTeleports = false;
	public boolean highlightTraversable = false;

	public ComponentGridEditor(ImageMatrixCellChangeListener l) {
		this.listener = l;

		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.setPreferredSize(new Dimension(50, 50));
	}

	public void blanketTextureEverything(Texture t) {
		for (int i = 0; i < this.gf.getGridWidth(); i++) {
			for (int j = 0; j < this.gf.getGridHeight(); j++) {
				this.tileList[i][j].tex = t;
			}
		}
 
		this.repaint();
	}

	public GridFile getGridFile() {
		return this.gf;
	}

	public Image getScreenshot() {
		BufferedImage bi = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_4BYTE_ABGR);

		Graphics2D g = (Graphics2D) bi.getGraphics();

		this.paint(g);

		return this.scaleImage(bi, 64, 64, Color.WHITE);
	}

	public Tile getTileAt(int x, int y) {
		return this.tileList[x][y];
	}

	public Point getTileAt(Point p) {
		int w = this.getWidth() / gf.getGridWidth();
		int h = this.getHeight() / gf.getGridHeight();

		int x = (int) p.getX() / w;
		int y = (int) p.getY() / h;

		return new Point(x, y);
	}

	public Vector<Tile> getTiles() {
		Vector<Tile> tiles = new Vector<Tile>();

		for (int x = 0; x < gf.getGridWidth(); x++) {
			for (int y = 0; y < gf.getGridHeight(); y++) { 
				tiles.add(this.tileList[x][y]);
			}
		}

		return tiles;
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		Point p = this.getTileAt(e.getPoint());

		if ((p.x < gf.getGridWidth()) && (p.y < gf.getGridHeight())) {
			Tile t = this.tileList[p.x][p.y];

			if (this.lastHoveredTile != null) {
				this.lastHoveredTile.hover = false;
			}

			if (this.lastHoveredTile != t) {
				t.hover = true;
				this.lastHoveredTile = t;
				this.repaint();
			}
		}
	}

	@Override 
	public void mousePressed(MouseEvent e) {
		Point p = this.getTileAt(e.getPoint());

		this.currx = p.x;
		this.curry = p.y;

		if (e.getButton() == MouseEvent.BUTTON1) {
			if (e.getClickCount() > 1) {
				this.listener.onCellSelected(this.currx, this.curry, this.tileList[p.x][p.y]);
			} else {
				this.listener.onCellFocus(this.currx, this.curry, this.tileList[p.x][p.y]);
			}
		} else if (e.getButton() == MouseEvent.BUTTON3) {
			this.listener.onCellApplyPaint(this.currx, this.curry, this.tileList[p.x][p.y]);
		}

		this.repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void paint(Graphics g) {
		int w = this.getWidth() / gf.getGridWidth();
		int h = this.getHeight() / gf.getGridHeight(); 

		for (int x = 0; x < gf.getGridWidth(); x++) {
			for (int y = 0; y < gf.getGridHeight(); y++) {
				this.paintTile(g, x, y, this.tileList[x][y], w, h);
			}
		} 
	}

	public void paintTile(Graphics g, int x, int y, Tile t, int w, int h) {
		if (t == null) {
			g.setColor(Color.PINK);
			g.fillRect(x * w, y * h, w, h);
		} else {
			g.drawImage(t.tex.image, x * w, y * h, w, h, null);
		}

		if ((x == this.currx) && (y == this.curry)) {
			g.setColor(Color.RED);
			g.drawRect(x * w, y * h, w - 1, h - 1);
		} else if (t.hover) {
			g.setColor(Color.BLUE);
			g.drawRect(x * w, y * h, (w * this.selectionRectSize) - 1, (h * this.selectionRectSize) - 1);
		}

		if (this.highlightTeleports && !t.teleportDestinationGrid.isEmpty()) {
			g.setColor(Color.ORANGE);
			g.fillOval((x * w) + (w / 4), (y * h) + (h / 4), w / 2, h / 2);
			Texture i = TextureCache.instance.getTexHud("arrow.png", t.teleportDirection.getDegrees());
			g.drawImage(i.image, (x * w) + (w / 4), (y * h) + (h / 4), w / 2, h / 2, null);
		}

		if (this.highlightTraversable) {
			if (t.traversable) {
				g.setColor(Color.GREEN);
			} else {
				g.setColor(Color.RED);
			}

			g.fillRect((x * w) + (w / 4), (y * h) + (h / 4), w - ((w / 4) * 2), h - ((h / 4) * 2));
		}
	}

	public BufferedImage scaleImage(BufferedImage img, int width, int height, Color background) {
		int imgWidth = img.getWidth();
		int imgHeight = img.getHeight();
		if ((imgWidth * height) < (imgHeight * width)) {
			width = (imgWidth * height) / imgHeight;
		} else {
			height = (imgHeight * width) / imgWidth;
		}
		BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = newImage.createGraphics();
		try {
			g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
			g.setBackground(background);
			g.clearRect(0, 0, width, height);
			g.drawImage(img, 0, 0, width, height, null);
		} finally {
			g.dispose();
		}
		return newImage;
	}

	public void setGridFile(GridFile gf) {
		this.gf = gf;
		this.tileList = gf.getTileList();
		this.repaint(); 
	}

	public void setSelectionRectSize(int size) {
		this.selectionRectSize = size;
	}

	public void setTileAt(int x, int y, Tile tile) {
		this.tileList[x][y] = tile;
	}
}
