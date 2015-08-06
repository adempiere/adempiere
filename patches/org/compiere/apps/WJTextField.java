package org.compiere.apps;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.RenderingHints;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.Document;

/**
 * Clase que dibuja el componente JTextField personalizado
 *  
 * @author Wilfredo Suarez
 * @version 1.1
 */
public class WJTextField extends JTextField {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4007542336385488480L;

	public WJTextField() {
		// TODO Auto-generated constructor stub
		init();
	}

	public WJTextField(String text) {
		super(text);
		// TODO Auto-generated constructor stub
		init();
	}

	public WJTextField(int columns) {
		super(columns);
		// TODO Auto-generated constructor stub
		init();
		
	}

	public WJTextField(String text, int columns) {
		super(text, columns);
		// TODO Auto-generated constructor stub
		init();
	}

	public WJTextField(Document doc, String text, int columns) {
		super(doc, text, columns);
		// TODO Auto-generated constructor stub
		init();
	}

	private void init() {
		
		setOpaque(false);
		setForeground(Color.DARK_GRAY);
		setBorder(new EmptyBorder(0, 5, 0, 5));
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		// super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		g2.setColor(Color.white);
		
		Paint oldPaint = g2.getPaint();

		g2.fillRoundRect(1, 1, getWidth() - 2, getHeight() - 2, 6, 6);
		
		g2.setColor(Color.GRAY);
		
		if (isFocusOwner()) {
			
			g2.setStroke(new BasicStroke(3f));
			
		} else {
			
			g2.setStroke(new BasicStroke(2f));
			
		}
		
		g2.drawRoundRect(0, 0, getWidth() - 2, getHeight() - 2, 6, 6);

		g2.setPaint(oldPaint);

		super.paintComponent(g);

	}

}
