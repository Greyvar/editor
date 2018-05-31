package greyvarEditor.files;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import greyvarEditor.utils.YamlFile;
import jwrCommonsJava.Logger;

public class WorldFile {
	public String author = "unknown";
	public String title = "unknown"; 
	public String spawnGrid = "unknown"; 
	  
	public transient Vector<File> gridFiles = new Vector<File>(); 

	public static WorldFile load(File f) { 		
		try {
			WorldFile wf = YamlFile.read(f, WorldFile.class);
			wf.loadGrids(new File(f.getParentFile(), "grids"));
			 
			return wf;
		} catch (Exception e) { 
			Logger.messageException(e, "Could not load world file"); 
		}
		 
		return null;
	}
	 
	private void loadGrids(File worldDirectory) {
		for (File f : worldDirectory.listFiles()) {
			this.gridFiles.add(f);  
		}
	}
}
