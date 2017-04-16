package greyvarEditor;

import greyvarEditor.windows.editors.grid.panels.Texture;

import java.awt.Image;
import java.io.File;
import java.util.HashMap;

import javax.imageio.ImageIO;

import jwrCommonsJava.Logger;

public class TextureCache {
	private class MutatedTexture {
		public Texture texOriginal;
		public int rot;
		public boolean flipV;
		public boolean flipH;

		public MutatedTexture(Texture t, int rot, boolean flipV, boolean flipH) {
			this.texOriginal = t;
			this.rot = rot;
			this.flipV = flipV;
			this.flipH = flipH;
		}

		@Override
		public boolean equals(Object comp) {
			if (comp instanceof MutatedTexture) {
				MutatedTexture comp2 = (MutatedTexture) comp;

				if (!(this.texOriginal.equals(comp2.texOriginal))) {
					return false;
				}

				if (this.rot != comp2.rot) {
					return false;
				}

				if (this.flipV != comp2.flipV) {
					return false;
				}

				if (this.flipH != comp2.flipH) {
					return false;
				}

				return true;
			} else {
				return super.equals(comp);
			}
		}
	}

	private final HashMap<String, Texture> textureCacheOriginals = new HashMap<String, Texture>();
	private final HashMap<MutatedTexture, Texture> textureCacheMutated = new HashMap<MutatedTexture, Texture>();

	public static TextureCache instance = new TextureCache();

	private Texture defaultTexture;

	static {
		try {
			File defaultImage = new File(GameResources.dirTextures + "/tiles/construct.png");
			TextureCache.instance.defaultTexture = new Texture(ImageIO.read(defaultImage), defaultImage);
			TextureCache.instance.textureCacheOriginals.put("[default]", TextureCache.instance.defaultTexture);
			TextureCache.instance.loadTextures("tiles/");
		} catch (Exception e) {
			Logger.messageException(e, "Could not load default texture.");
		}
	}

	private TextureCache() {
		Logger.messageDebug("texture cache constructing from dir: " + GameResources.dirTextures);
		this.loadTex(new File(GameResources.dirTextures, "hud/arrow.png").getAbsolutePath());
	}

	public HashMap<String, Texture> getAll() {
		return this.textureCacheOriginals;
	}

	public Texture getDefault() {
		return this.defaultTexture;
	}

	private Texture getTex(String name) {
		if (this.textureCacheOriginals.containsKey(name)) {
			return this.textureCacheOriginals.get(name);
		} else {
			return this.loadTex(name);
		}
	}

	private Texture getTex(String name, int rot, boolean flipV, boolean flipH) {
		Texture t = this.getTex(name);

		if ((rot == 0) && !flipH && !flipV) {
			return t;
		} else {
			MutatedTexture mutatedAttributes = new MutatedTexture(t, rot, flipV, flipH);

			if (this.textureCacheMutated.containsKey(mutatedAttributes)) {
				return this.textureCacheMutated.get(mutatedAttributes);
			} else {
				Texture mutatedTexture = t.clone();
				mutatedTexture.rotate(rot);
				mutatedTexture.flip(mutatedAttributes.flipV, mutatedAttributes.flipH);

				this.textureCacheMutated.put(mutatedAttributes, mutatedTexture);

				return mutatedTexture;
			}
		}
	}

	public Texture getTexHud(String texName, int rotation) {
		return this.getTex(File.separator + "hud" + File.separator + texName, rotation, false, false);
	}

	public Texture getTexTile(String texName, int rotation, boolean flipV, boolean flipH) {
		return this.getTex(File.separator + "tiles" + File.separator + texName, rotation, flipV, flipH);
	}

	private Texture loadTex(String texName) {
		if (texName.isEmpty()) {
			return this.defaultTexture;
		}

		if (!texName.contains(GameResources.dirTextures.getAbsolutePath())) {
			Logger.messageWarning("Not loading a tex outside the normal dir: " + texName);

			return this.defaultTexture;
		} else {
			texName = texName.replace(GameResources.dirTextures.getAbsolutePath(), "");
		}

		if (this.textureCacheOriginals.containsKey(texName)) {
			Logger.messageDebug("Duplicate texture filename, not loading: " + texName);
			return this.textureCacheOriginals.get(texName);
		}

		try {
			File f = new File(GameResources.dirTextures + "/" + texName);
			Image i = ImageIO.read(f);
			Texture tex = new Texture(i, f);

			Logger.messageDebug("Loaded tex: " + texName);
			this.textureCacheOriginals.put(texName, tex);

			return tex;
		} catch (Exception e) {
			Logger.messageWarning("Failed to load: " + texName + " because: " + e.getMessage());
			this.textureCacheOriginals.put(texName, this.defaultTexture);
		}

		return this.defaultTexture;
	}

	public void loadTextures(String textureDir) {
		File textureDirectory = new File(GameResources.dirTextures, textureDir);
		Logger.messageDebug("Loading textures from directory: " + textureDirectory.getAbsolutePath());

		for (File f : textureDirectory.listFiles()) {
			if (f.getName().endsWith(".png")) {
				this.loadTex(f.getAbsolutePath());
			}
		}
	}
}
