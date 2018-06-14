package greyvarEditor.triggers.conditionFragments;

import greyvarEditor.triggers.Condition;
import greyvarEditor.triggers.arguments.ArgumentTileCoordinates;

public class ConditionStepOn extends Condition {
	public ConditionStepOn(int x, int y) {  
		this.arguments.put("pos", new ArgumentTileCoordinates(x, y));
	}
	
	@Override
	public String toString() {
		return "If player steps on tile";   
	}
}
