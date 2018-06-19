package greyvarEditor.triggers.eventFragments;

import greyvarEditor.triggers.Action;
import greyvarEditor.triggers.Fragment;
import greyvarEditor.triggers.arguments.ArgumentMessage;
 
public class ActionMessage extends Action {
	private ArgumentMessage argMsg = new ArgumentMessage();

	public ActionMessage() {
		this.arguments.put("message", argMsg); 
	}
	
	@Override
	public String getDescription() {
		return "show the message"; 
	} 
	
	public ActionMessage set(String message) {
		this.argMsg.message = message;
		 
		return this; 
	}

}
