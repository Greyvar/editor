package greyvarEditor.triggers.eventFragments;

import greyvarEditor.triggers.Action;
import greyvarEditor.triggers.arguments.ArgumentEntityId;
import greyvarEditor.triggers.arguments.ArgumentTileCoordinates;

public class ActionEntityMove extends Action {
	public ActionEntityMove(int entityId, int x, int y) { 
		this.arguments.put("entity", new ArgumentEntityId(entityId));
		this.arguments.put("pos", new ArgumentTileCoordinates(x, y));
	}
} 
