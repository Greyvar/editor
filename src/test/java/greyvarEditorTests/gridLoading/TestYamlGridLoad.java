package greyvarEditorTests.gridLoading;

import java.io.File;

import greyvarEditor.Tile;
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
		 
		assertEquals("water.png", tileList[0][0].getTextureFilenameOnly());
	}
}
