package greyvarEditor.ui.components;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;

public class ClickableFragmentLabel extends JLabel implements MouseListener {
	private final JComponent comp;
	
	public interface Listener {
		public void onFragmentClicked(JComponent comp); 
	}
	
	private Listener listener;
	
	public ClickableFragmentLabel(Listener listener, String txt, JComponent comp) {  
		super(txt); 
		
		this.listener = listener; 
		this.comp = comp;
		 
		this.addMouseListener(this);
		this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));  
	}
	
	public void onClicked() {
		this.listener.onFragmentClicked(comp); 
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		this.onClicked();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		Font f = this.getFont();
		Map<TextAttribute, Object> attrs = new HashMap<>(f.getAttributes());  
		attrs.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON); 
		 
		this.setFont(f.deriveFont(attrs));
	} 

	@Override
	public void mouseExited(MouseEvent e) {
		Font f = this.getFont();
		Map<TextAttribute, Object> attrs = new HashMap<>(f.getAttributes());  
		attrs.put(TextAttribute.UNDERLINE, -1);   
		 
		this.setFont(f.deriveFont(attrs));
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
 