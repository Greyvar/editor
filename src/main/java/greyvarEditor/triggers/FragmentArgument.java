package greyvarEditor.triggers;

import javax.swing.JComponent;
import javax.swing.JLabel;

public class FragmentArgument {
	@Override
	public String toString() {
		return this.getClass().getSimpleName(); 
	}

	public JComponent getEditor() {
		return new JLabel("(no editor available");
	}
}
