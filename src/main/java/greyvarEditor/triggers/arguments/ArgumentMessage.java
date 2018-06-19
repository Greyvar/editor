package greyvarEditor.triggers.arguments;

import javax.swing.JComponent;
import javax.swing.JTextField;

import greyvarEditor.triggers.FragmentArgument;
 
public class ArgumentMessage extends FragmentArgument {
	public String message = "hello";
	
	private JTextField txtEditor = new JTextField();

	@Override
	public void setFromEditor() {
		message = txtEditor.getText();   
	}
	
	@Override
	public String toString() {
		return "{" + message + "}";
	} 
	
	@Override
	public JComponent getEditor() {
		txtEditor.setText(message);
		return txtEditor;
	}
} 
