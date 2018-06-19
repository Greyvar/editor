package greyvarEditor.triggers;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.Vector;

public class Fragment { 
	protected Map<String, FragmentArgument> arguments = new TreeMap<>(); 
	
	public String getDescription() {
		return "<generic fragment>"; 
	}
	 
	public String getArgumentDescriptionPrefix(String argumentName) {
		return "";
	}
	 
	public Map<String, FragmentArgument> getArguments() {
		return this.arguments; 
	}

	public FragmentArgument getArgument(String argName) {
		return this.arguments.get(argName); 
	}
	
	@Override
	public String toString() {
		String ret = "";
		
		ret += this.getDescription();
		   
		for (FragmentArgument fa : this.arguments.values()) {
			ret += " "; 
			ret += fa.toString();
		}  
				
		return ret;
	}
}  