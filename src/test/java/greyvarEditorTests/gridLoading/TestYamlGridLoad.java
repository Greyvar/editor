package greyvarEditorTests.gridLoading;

import java.io.File;

import greyvarEditor.Tile;
import greyvarEditor.dataModel.EntityInstance;
import greyvarEditor.files.GridFileYaml;
import junit.framework.TestCase;
import jwrCommonsJava.Configuration;

import static org.junit.Assert.*;
 
public class TestYamlGridLoad extends TestCase {
	public void testLoad() { 
		Configuration.set("sysAppName", "greyvarEditor");
		Configuration.parseUserConfiguration();
		
		GridFileYaml gridFile = new GridFileYaml(new File("src/test/testFiles/0.0.grid"));
		
		assertEquals(16, gridFile.getGridWidth());
		assertEquals(16, gridFile.getGridHeight()); 
		
		Tile[][] tileList = gridFile.getTileList();
		
		assertNotNull(tileList);  
		
		EntityInstance[][] entityList = gridFile.getEntityList();
		  
		assertEquals("hill.png", tileList[0][0].getTextureFilenameOnly());
		assertEquals("chest", entityList[13][4].definition);   
	}
} 
