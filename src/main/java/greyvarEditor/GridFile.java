package greyvarEditor;

import greyvarEditor.Tile.TeleportDirection;
import greyvarEditor.windows.editors.grid.panels.Texture;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Vector;

import jwrCommonsJava.Logger;

public class GridFile {
	private Tile[][] tiles;
	public static final short GRID_SIZE = 16;

	private final File f;

	public GridFile(File selectedFile) {
		this.f = selectedFile;

		this.initMap();
		this.load();
	}

	public GridFile(String text) {
		this(new File(GameResources.dirServerData + "/grids/" + text));
	}

	private String getCsvLine(Object... obs) {
		String ret = "";

		for (Object o : obs) {
			if (o == null) {
				ret += "null,";
			} else {
				ret += o.toString() + ",";
			}
		}

		return ret + "\n";
	}

	private Vector<String> getFileLines(File f) {
		final Vector<String> lines = new Vector<String>();

		try {
			BufferedReader br = new BufferedReader(new FileReader(f));

			while (true) {
				String line = br.readLine();

				if ((line == null) || line.isEmpty()) {
					break;
				} else {
					lines.add(line);
				}
			}

			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return lines;
	}

	public String getFilename() {
		return this.f.getName();
	}

	public Tile[][] getTileList() {
		return this.tiles;
	}

	private void initMap() {
		this.tiles = new Tile[GridFile.GRID_SIZE][GridFile.GRID_SIZE];

		for (int x = 0; x < GridFile.GRID_SIZE; x++) {
			for (int y = 0; y < GridFile.GRID_SIZE; y++) {
				this.tiles[x][y] = new Tile(TextureCache.instance.getDefault());
				Logger.messageDebug("Tile: " + x + ":" + y + "  " + this.tiles[x][y]);
			}
		}
	}

	public void load() {
		for (String line : this.getFileLines(this.f)) {
			if (line.startsWith("#")) {
				continue;
			}

			this.loadTile(line);
		}
	}

	private void loadTile(String line) {
		if ((line == null) || line.isEmpty()) {
			return;
		}

		String[] lineBits = line.split(",");

		if (lineBits.length < 3) {
			Logger.messageWarning("Malformed line, not loading: " + Arrays.toString(lineBits));
		}

		int index = 0;
		int x = Integer.parseInt(lineBits[index++]);
		int y = Integer.parseInt(lineBits[index++]);
		String texName = lineBits[index++];
		int rot = Integer.parseInt(lineBits[index++]);
		boolean flipH = Boolean.parseBoolean(lineBits[index++]);
		boolean flipV = Boolean.parseBoolean(lineBits[index++]);

		Texture tex = TextureCache.instance.getTexTile(texName, rot, flipV, flipH);

		Tile tile = new Tile(tex);
		tile.traversable = Boolean.parseBoolean(lineBits[index++]);
		tile.teleportDestinationGrid = lineBits[index++];
		tile.teleportDestinationX = Integer.parseInt(lineBits[index++]);
		tile.teleportDestinationY = Integer.parseInt(lineBits[index++]);
		tile.teleportDirection = TeleportDirection.fromString(lineBits[index++]);

		try {
			tile.message = lineBits[index++];
		} catch (java.lang.ArrayIndexOutOfBoundsException e) {
		
		}

		Logger.messageDebug(tile.toString());

		this.tiles[x][y] = tile;
	}

	public void save() {
		this.save(this.f);
	}

	public void save(File f) {
		try {
			FileWriter fw = new FileWriter(f);
			fw.append("# Saved at " + Calendar.getInstance().getTimeInMillis() + "\n");

			for (int x = 0; x < GridFile.GRID_SIZE; x++) {
				for (int y = 0; y < GridFile.GRID_SIZE; y++) {
					this.saveTile(fw, x, y, this.tiles[x][y]);
				}
			}

			fw.flush();
			fw.close();

			Logger.messageDebug("Saved file: " + f.getName());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void saveTile(FileWriter fw, int x, int y, Tile tile) {
		try {
			fw.append(this.getCsvLine(x, y, tile.getTextureFilenameOnly(), tile.tex.rot, tile.tex.flipH, tile.tex.flipV, tile.traversable, tile.teleportDestinationGrid, tile.teleportDestinationX, tile.teleportDestinationY, tile.teleportDirection, tile.message));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
