package greyvarEditor.dataModel;

import java.beans.Transient;
import java.util.HashMap;

public class EntityDefinition {
	public String title; 
	public HashMap<String, EntityState> states = new HashMap<>();
	
	@Transient 
	public EntityState getFirstState() {
		if (states.size() == 0) {
			throw new RuntimeException("Entity definition that has zero states!: " + this.title); 	
		} else { 
			return states.values().iterator().next();
		}
	}
}
