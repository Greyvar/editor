package greyvarEditor;


import javax.swing.UIManager;

import greyvarEditor.ui.windows.WindowMain;
import jwrCommonsJava.Configuration;
import jwrCommonsJava.Logger;

public class Main {
	public static void main(String[] args) throws Exception {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {

			e.printStackTrace();
		}

		Configuration.set("sysAppName", "greyvarEditor");
		Configuration.parseInternalConfiguration();

		if (!Configuration.isUserConfigExistering()) {
			Configuration.createUserConfig();
		}

		Configuration.parseUserConfiguration();
		
		Logger.messageDebug("Textures (paths.textureDirectory): " + GameResources.dirTextures);

		TextureCache.instance.getDefault();

		WindowMain.getInstance().setVisible(true);
	}
}
