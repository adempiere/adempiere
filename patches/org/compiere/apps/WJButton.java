package org.compiere.apps;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

import javax.swing.ButtonModel;
import javax.swing.SwingConstants;

import org.compiere.swing.CButton;

/**
 * Clase que dibuja botones con estilos predefinidos.
 * 
 * @author Wilfredo Suarez
 * @version 1.1
 */
public class WJButton extends CButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6520867977977209420L;
	public static final int TYPE_NONE = 0;
	public static final int TYPE_DESKTOP = 1;
	public static final int TYPE_CANCEL = 2;
	public static final int TYPE_OK = 3;
	public static final int TYPE_CONFIG = 4;
	public static final int TYPE_HELP = 5;
	public static final int TYPE_EXIT = 6;
	public static final int TYPE_EMPTY = 7;

	private int type = 0;
	private Color color1 = new Color(0x666f7f);
	private Color color2 = new Color(0x262d3d);
	private Color color3 = new Color(0x262d3d);
	private String numberTitle = "0";
	private String text = null;

	/**
	 * Constructor por defecto
	 */
	public WJButton() {

		super();

	}

	/**
	 * Constructor
	 */
	public WJButton(String tittle) {

		super(tittle);
		this.setText(tittle);

	}

	/**
	 * Constructor para dibujar el boton con un estilo predefinido valores.
	 * 
	 * @param type
	 *            El tipo de boton a dibujar NONE, DESKTOP, CANCEL, OK,
	 *            TYPE_CONFIG, TYPE_HELP, TYPE_EMPTY
	 */
	public WJButton(int type) {

		super();
		this.type = type;

		if (type != TYPE_NONE) {

			setOpaque(false);
			setContentAreaFilled(false);
			setForeground(Color.WHITE);
			setFocusPainted(false);
			setBorderPainted(false);

		}
	}

	/**
	 * Constructor para dibujar el boton con un estilo predefinido y titulo.
	 * 
	 * @param type
	 *            El tipo de boton a dibujar NONE, DESKTOP, CANCEL, OK,
	 *            TYPE_CONFIG, TYPE_HELP, TYPE_EMPTY
	 */
	public WJButton(String tittle, int type) {

		super(tittle);

		this.setText(tittle);
		this.type = type;

		if (type != TYPE_NONE) {

			setOpaque(false);
			setContentAreaFilled(false);
			setForeground(Color.WHITE);
			setFocusPainted(false);
			setBorderPainted(false);

		}
	}

	@Override
	protected void paintComponent(Graphics g) {

		if (type != TYPE_NONE) {

			switch (type) {

			case TYPE_DESKTOP:

				drawButtonDesktop(g);

				break;

			case TYPE_CANCEL:

				drawButtonCancel(g);

				break;

			case TYPE_OK:

				drawButtonOk(g);

				break;

			case TYPE_CONFIG:

				drawButtonConfig(g);

				break;

			case TYPE_HELP:

				drawButtonHelp(g);

				break;

			case TYPE_EXIT:

				drawButtonExit(g);

				break;

			case TYPE_EMPTY:

				drawButtonEmpty(g);

				break;

			}

		}

		super.paintComponent(g);

	}

	/**
	 * Dibuja el boton Vacio
	 * 
	 * @param g
	 */
	private void drawButtonEmpty(Graphics g) {

	}

	/**
	 * Dibuja el boton Exit
	 * 
	 * @param g
	 */
	private void drawButtonExit(Graphics g) {

		Font font = new Font("Arial", Font.BOLD, 12);
		
		Color color1 = Color.LIGHT_GRAY;
		Color color2 = Color.LIGHT_GRAY;
		
		Color c1, c2;
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		setFont(font);
		setHorizontalAlignment(SwingConstants.LEFT);

		ButtonModel model = getModel();

		Paint oldPaint = g2.getPaint();

		if (model.isArmed()) {

			c2 = color1.darker();
			c1 = color2.brighter();

		} else {

			c1 = color1.darker();
			c2 = color2.darker();

		}

		if (!model.isEnabled()) {

			c2 = color1.brighter();
			c1 = color2.brighter();

		}

		g2.setPaint(new GradientPaint(0.0f, 0.0f, c1, 0.0f, getHeight(), c2));
		g2.fillRoundRect(1, 1, getWidth() - 2, getHeight() - 2, 6, 6);

		g2.setColor(Color.white);
		RoundRectangle2D.Float r2d = new RoundRectangle2D.Float(-2, 0,
				getWidth() + 2, getHeight() + 2, 6, 6);
		g2.clip(r2d);

		g2.setStroke(new BasicStroke(1f));
		g2.drawRoundRect(0, 0, getWidth() - 2, getHeight() - 2, 6, 6);

		g2.setPaint(oldPaint);

	}

	/**
	 * Dibuja el boton Help
	 * 
	 * @param g
	 */
	private void drawButtonHelp(Graphics g) {

		Color color1 = Color.LIGHT_GRAY;
		Color color2 = Color.LIGHT_GRAY;
		
		Color c1, c2;
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		ButtonModel model = getModel();

		Paint oldPaint = g2.getPaint();

		if (model.isArmed()) {

			c2 = color1.darker();
			c1 = color2.brighter();

		} else {

			c1 = color1.darker();
			c2 = color2.darker();

		}

		if (!model.isEnabled()) {

			c2 = color1.brighter();
			c1 = color2.brighter();

		}

		g2.setPaint(new GradientPaint(0.0f, 0.0f, c1, 0.0f, getHeight(), c2));
		g2.fillOval(1, 1, getWidth() - 2, getHeight() - 2);

		g2.setColor(Color.white);

		g2.setStroke(new BasicStroke(2f));

		g2.drawOval(0, 0, getWidth() - 2, getHeight() - 2);

		g2.setPaint(oldPaint);
		
		g2.drawString("?", ((getWidth() - 2) / 2) - 3, ((getWidth() - 2)/2) + 5);
		

	}

	/**
	 * Dibuja el boton Cancel
	 * 
	 * @param g
	 */
	private void drawButtonCancel(Graphics g) {
		
	}

	/**
	 * Dibuja el boton Ok
	 * 
	 * @param g
	 */
	private void drawButtonOk(Graphics g) {

		Color color1 = Color.DARK_GRAY;
		Color color2 = Color.DARK_GRAY;

		Color c1, c2;
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		ButtonModel model = getModel();

		Paint oldPaint = g2.getPaint();

		if (model.isArmed()) {

			c2 = color1.darker();
			c1 = color2.brighter();

		} else {

			c1 = color1.darker();
			c2 = color2.darker();

		}

		if (!model.isEnabled()) {

			c2 = color1.brighter();
			c1 = color2.brighter();

		}

		g2.setPaint(new GradientPaint(0.0f, 0.0f, c1, 0.0f, getHeight(), c2));
		g2.fillRoundRect(1, 1, getWidth() - 2, getHeight() - 2, 6, 6);

		g2.setColor(Color.white);
		RoundRectangle2D.Float r2d = new RoundRectangle2D.Float(-2, 0,
				getWidth() + 2, getHeight() + 2, 6, 6);
		g2.clip(r2d);

		g2.setStroke(new BasicStroke(1f));
		g2.drawRoundRect(0, 0, getWidth() - 2, getHeight() - 2, 6, 6);

		g2.setPaint(oldPaint);

	}

	/**
	 * Dibuja el boton Config
	 * 
	 * @param g
	 */
	private void drawButtonConfig(Graphics g) {

		
		Color color1 = Color.LIGHT_GRAY;
		Color color2 = Color.LIGHT_GRAY;
		
		Color c1, c2;
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		ButtonModel model = getModel();

		Paint oldPaint = g2.getPaint();

		if (model.isArmed()) {

			c2 = color1.darker();
			c1 = color2.brighter();

		} else {

			c1 = color1.darker();
			c2 = color2.darker();

		}

		if (!model.isEnabled()) {

			c2 = color1.brighter();
			c1 = color2.brighter();

		}

		g2.setPaint(new GradientPaint(0.0f, 0.0f, c1, 0.0f, getHeight(), c2));
		g2.fillRoundRect(1, 1, getWidth() - 2, getHeight() - 2, 6, 6);

		g2.setColor(Color.white);
		RoundRectangle2D.Float r2d = new RoundRectangle2D.Float(-2, 0,
				getWidth() + 2, getHeight() + 2, 6, 6);
		g2.clip(r2d);

		g2.setStroke(new BasicStroke(1f));
		g2.drawRoundRect(0, 0, getWidth() - 2, getHeight() - 2, 6, 6);

		g2.setPaint(oldPaint);

	}

	/**
	 * Dibuja el boton Desktop
	 * 
	 * @param g
	 */
	private void drawButtonDesktop(Graphics g) {

		Color c1, c2;
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		ButtonModel model = getModel();

		Paint oldPaint = g2.getPaint();

		if (model.isArmed()) {

			c2 = color1.darker();
			c1 = color2.darker();

		} else {

			c1 = color1.darker();
			c2 = color2.darker();

		}

		if (!model.isEnabled()) {

			c2 = color1.brighter();
			c1 = color2.brighter();

		}

		g2.setColor(Color.white);
		RoundRectangle2D.Float r2d = new RoundRectangle2D.Float(-20, -20,
				getWidth() + 20, getHeight() + 20, 20, 20);
		g2.clip(r2d);

		g2.setStroke(new BasicStroke(2f));

		g2.drawRoundRect(5, 5, getWidth() - 7, getHeight() - 7, 18, 18);

		g2.setPaint(new GradientPaint(0.0f, 0.0f, c1, 0.0f, getHeight(), c2));
		g2.fillRoundRect(7, 7, getWidth() - 10, getHeight() - 10, 18, 18);

		g2.setPaint(oldPaint);

		g2.setStroke(new BasicStroke(4f));
		g2.setColor(Color.red);
		g2.fillOval(2, 2, 30, 30);

		g2.setColor(Color.white);
		g2.drawOval(2, 2, 30, 30);
		g2.drawString(getNumberTitle(), 9, 21);

	}

	@Override
	public String getText() {
		// TODO Auto-generated method stub
		return this.text;
	}

	@Override
	public void setText(String text) {
		// TODO Auto-generated method stub
		this.text = "<html><center><p>" + text + "</p></center></html>";
		super.setText(text);

	}

	public Color getColor1() {
		return color1;
	}

	public void setColor1(Color color1) {
		this.color1 = color1;
	}

	public Color getColor2() {
		return color2;
	}

	public void setColor2(Color color2) {
		this.color2 = color2;
	}

	public Color getColor3() {
		return color3;
	}

	public void setColor3(Color color3) {
		this.color3 = color3;
	}

	public String getNumberTitle() {
		return numberTitle;
	}

	public void setNumberTitle(String numberTitle) {
		this.numberTitle = numberTitle;
	}

}