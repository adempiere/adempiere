package org.compiere.apps;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.LayoutManager;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import org.compiere.swing.CPanel;

/**
 * 
 * @author Wilfredo Suarez
 * @version 1.1
 */
public class WJPanel extends CPanel {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4427010953904752785L;
	private Image imagen;

	public WJPanel() {
		super();
		ini();
	}

	public WJPanel(LayoutManager layout) {
		super(layout);
		ini();
	}

	public WJPanel(String nombreImagen) {
		setImagen(nombreImagen);
		ini();
	}

	public WJPanel(String locationURL, String nombreImagen) {
		setImagenUrl(locationURL + nombreImagen);
		ini();
	}

	public WJPanel(Image imagenInicial) {
		imagen = imagenInicial;
		setImagen(imagen);
		ini();
	}

	/**
	 * Metodo que asigna valores iniciales.
	 */
	private void ini() {
		
//		color = Color.white;
//		this.setBackground(color);
		
	}

	public void setImagen(String nombreImagen) {
		imagen = new ImageIcon(nombreImagen).getImage();
		repaint();
	}

	public void setImagen(Image nuevaImagen) {
		imagen = nuevaImagen;
		repaint();
	}

	public void setImagenUrl(String strUrl) {
		try {
			URL url = new URL(strUrl);
			imagen = new ImageIcon(url).getImage();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		this.repaint();
	}

	public Image getImagen() {
		return imagen;
	}

	public void paint(Graphics g) {

		if (imagen != null) {
			g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
			setOpaque(false);
		}
		
		super.paint(g);
	}

}
