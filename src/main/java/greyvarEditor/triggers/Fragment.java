package greyvarEditor.triggers;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class Fragment {
	protected Map<String, FragmentArgument> arguments = new HashMap<>(); 
	
	public String toString() {
		String name = this.getClass().getSimpleName();
		
		return name; 
	} 
	 
	public Map<String, FragmentArgument> getArguments() {
		return this.arguments;
	}

	public FragmentArgument getArgument(String argName) {
		return this.arguments.get(argName); 
	}
}  