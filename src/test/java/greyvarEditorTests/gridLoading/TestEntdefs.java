package greyvarEditorTests.gridLoading;

import java.io.File;

import greyvarEditor.EntityDatabase;

public class TestEntdefs {
	public void testEntdefs() {
		EntityDatabase entdb = new EntityDatabase(new File("../greyvar-server/dat/entdefs/"));
		
		entdb.reloadEntdefs(); 
	}
}
 