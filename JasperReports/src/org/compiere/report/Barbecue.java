package org.compiere.report;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import net.sf.jasperreports.engine.JRAbstractSvgRenderer;
import net.sourceforge.barbecue.Barcode;

public class Barbecue extends JRAbstractSvgRenderer
{

	private static final long serialVersionUID = 5112469398754718739L;
	
	private Barcode m_barcode = null;
	
	public Barbecue (Barcode barcode) 
	{
		m_barcode = barcode;
	}

    /**
     * Convenience method for creating a barcode renderer with the option of
     *
     *
     * @param barcode           The barcode
     * @param showDrawingText   If the alphanumeric text should be visible below the barcode
     */
    public Barbecue (Barcode barcode, boolean showDrawingText) {
        m_barcode = barcode;
        m_barcode.setDrawingText(showDrawingText);
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

}
