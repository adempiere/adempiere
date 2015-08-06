package org.compiere.apps;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Paint;
import java.awt.RenderingHints;
import javax.swing.ComboBoxModel;
import javax.swing.border.AbstractBorder;
import org.compiere.grid.ed.VComboBox;

public class WVComboBox extends VComboBox {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5031147554521142298L;
	Color BACKGROUND = Color.BLACK; // RED;
	Color FOREGROUND = Color.WHITE; // YELLOW;
	Color SELECTIONFOREGROUND = Color.CYAN;
	private Font font = new Font("Arial", Font.PLAIN, 12);

	public WVComboBox() {
		// TODO Auto-generated constructor stub
		init();
	}

	public WVComboBox(Object[] items) {
		super(items);
		// TODO Auto-generated constructor stub
		init();
	}

	public WVComboBox(ComboBoxModel model) {
		super(model);
		// TODO Auto-generated constructor stub
		init();
	}

	private void init() {

		setOpaque(false);
		setForeground(Color.DARK_GRAY);
		setBorder(new WRoundBorder());
		setBackground(false);
		setFont(font);


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

			g2.setStroke(new BasicStroke(1f));

		}

		g2.drawRoundRect(0, 0, getWidth() - 2, getHeight() - 2, 6, 6);

		g2.setPaint(oldPaint);

		super.paintComponent(g);

	}

	class WRoundBorder extends AbstractBorder {

		public WRoundBorder() {
			// TODO Auto-generated constructor stub

		}

		@Override
		public Insets getBorderInsets(Component c, Insets insets) {
			// TODO Auto-generated method stub
			// return super.getBorderInsets(c, insets);
			insets.left = insets.right = 8;
			insets.top = insets.bottom = 4;
			return insets;
		}

		@Override
		public Insets getBorderInsets(Component c) {
			// TODO Auto-generated method stub
			// return super.getBorderInsets(c);
			return new Insets(4, 8, 4, 8);
		}

		@Override
		public void paintBorder(Component c, Graphics g, int x, int y, int width,
				int height) {
			// TODO Auto-generated method stub

			Graphics2D g2 = (Graphics2D) g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);

			g2.setColor(Color.white);

			Paint oldPaint = g2.getPaint();

			g2.setColor(Color.GRAY);

			g2.setStroke(new BasicStroke(2f));

			g2.drawRoundRect(0, 0, getWidth() - 2, getHeight() - 2, 6, 6);

			g2.setPaint(oldPaint);

		}

	}

}
