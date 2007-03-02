package org.compiere.report;
import net.sf.jasperreports.engine.*;
import net.sourceforge.barbecue.*;
import net.sf.jasperreports.engine.util.*;
import java.awt.Graphics2D;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.geom.Dimension2D;

public abstract class Barbecue implements JRRenderable
{

	private static final long serialVersionUID = 5112469398754718739L;
	
	private Barcode m_barcode = null;

	public byte getType()
	{
		return TYPE_SVG;
	}


	public Dimension2D getDimension()
	{
		return m_barcode.getSize();
	}


	public Color getBackcolor()
	{
		return null;
	}

	
	public Barbecue (Barcode barcode) 
	{
		m_barcode = barcode;
	}

	public void render(Graphics2D grx, Rectangle2D rectangle) 
	{
		try
		{
			m_barcode.draw(grx, (int)rectangle.getX(), (int)rectangle.getY());	
		}
		catch (Exception e)
		{
			// TODO implement exception handling
		}
		
	}

	public byte[] getImageData() throws JRException
	{
		try
		{
			BufferedImage bi = BarcodeImageHandler.getImage(m_barcode);
			return JRImageLoader.loadImageDataFromAWTImage(bi);
			
		}
		catch (Exception e)
		{
			return null;
			// TODO implement exception handling
		}		

	}

}
