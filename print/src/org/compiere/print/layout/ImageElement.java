/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                        *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.compiere.print.layout;

import java.awt.*;
import java.awt.geom.*;
import java.net.*;
import java.util.*;
import java.util.logging.*;
import org.compiere.model.*;
import org.compiere.print.*;
import org.compiere.util.*;

/**
 *	Image Element
 *
 * 	@author 	Jorg Janke
 * 	@version 	$Id: ImageElement.java,v 1.3 2006/07/30 00:53:02 jjanke Exp $
 */
public class ImageElement extends PrintElement
{
	/**
	 *	Create Image from URL
	 *	@param imageURLString image url
	 *	@return image element
	 */
	public static ImageElement get (String imageURLString)
	{
		Object key = imageURLString;
		ImageElement image = (ImageElement)s_cache.get(key);
		if (image == null)
		{
			image = new ImageElement(imageURLString);
			s_cache.put(key, image);
		}
		return new ImageElement(image.getImage());
	}	//	get
	
	/**
	 *	Create Image from URL
	 *  @param imageURL image url
	 *	@return image element
	 */
	public static ImageElement get (URL imageURL)
	{
		Object key = imageURL;
		ImageElement image = (ImageElement)s_cache.get(key);
		if (image == null)
		{
			image = new ImageElement(imageURL);
			s_cache.put(key, image);
		}
		return new ImageElement(image.getImage());
	}	//	get
	
	/**
	 *	Create Image from Attachment
	 * 	@param AD_PrintFormatItem_ID record id
	 *	@return image element
	 */
	public static ImageElement get (int AD_PrintFormatItem_ID)
	{
		Object key = new Integer(AD_PrintFormatItem_ID);
		ImageElement image = (ImageElement)s_cache.get(key);
		if (image == null)
		{
			image = new ImageElement(AD_PrintFormatItem_ID);
			s_cache.put(key, image);
		}
		return new ImageElement(image.getImage());
	}	//	get

	/**	60 minute Cache						*/
	private static CCache<Object,ImageElement>	s_cache 
		= new CCache<Object,ImageElement>("ImageElement", 10, 60);
	
	/**************************************************************************
	 *	Create from existing Image
	 *  @param image image
	 */
	public ImageElement(Image image)
	{
		m_image = image;
		if (m_image != null)
			log.fine("Image=" + image);
		else
			log.log(Level.WARNING, "Image is NULL");
	}	//	ImageElement

	/**
	 *	Create Image from URL
	 *	@param imageURLstring image url
	 */
	private ImageElement(String imageURLstring)
	{
		URL imageURL = getURL(imageURLstring);
		if (imageURL != null)
		{
			m_image = Toolkit.getDefaultToolkit().getImage(imageURL);
			if (m_image != null)
				log.fine("URL=" + imageURL);
			else
				log.log(Level.WARNING, "Not loaded - URL=" + imageURL);
		}
		else
			log.log(Level.WARNING, "Invalid URL=" + imageURLstring);
	}	//	ImageElement

	/**
	 *	Create Image from URL
	 *  @param imageURL image url
	 */
	private ImageElement(URL imageURL)
	{
		if (imageURL != null)
		{
			m_image = Toolkit.getDefaultToolkit().getImage(imageURL);
			if (m_image != null)
				log.fine("URL=" + imageURL);
			else
				log.log(Level.WARNING, "Not loaded - URL=" + imageURL);
		}
		else
			log.severe ("ImageURL is NULL");
	}	//	ImageElement

	/**
	 *	Create Image from Attachment
	 * 	@param AD_PrintFormatItem_ID record id
	 */
	private ImageElement(int AD_PrintFormatItem_ID)
	{
		loadAttachment(AD_PrintFormatItem_ID);
	}	//	ImageElement

	/**	The Image			*/
	private Image	m_image = null;
	/** Scale				*/
	private double	m_scaleFactor = 1;

	/**************************************************************************
	 * 	Get URL from String
	 *  @param urlString url or resource
	 *  @return URL or null
	 */
	private URL getURL (String urlString)
	{
		URL url = null;
		//	not a URL - may be a resource
		if (urlString.indexOf("://") == -1)
		{
			ClassLoader cl = getClass().getClassLoader();
			url = cl.getResource(urlString);
			if (url != null)
				return url;
			log.log(Level.WARNING, "Not found - " + urlString);
			return null;
		}
		//	load URL
		try
		{
			url = new URL (urlString);
		}
		catch (MalformedURLException ex)
		{
			log.log(Level.WARNING, urlString, ex);
		}
		return url;
	}	//	getURL;

	/**
	 * 	Load Attachment
	 * 	@param AD_PrintFormatItem_ID record id
	 */
	private void loadAttachment(int AD_PrintFormatItem_ID)
	{
		MAttachment attachment = MAttachment.get(Env.getCtx(), 
			MPrintFormatItem.Table_ID, AD_PrintFormatItem_ID);
		if (attachment == null)
		{
			log.log(Level.WARNING, "No Attachment - AD_PrintFormatItem_ID=" + AD_PrintFormatItem_ID);
			return;
		}
		if (attachment.getEntryCount() != 1)
		{
			log.log(Level.WARNING, "Need just 1 Attachment Entry = " + attachment.getEntryCount());
			return;
		}
		byte[] imageData = attachment.getEntryData(0);
		if (imageData != null)
			m_image = Toolkit.getDefaultToolkit().createImage(imageData);
		if (m_image != null)
			log.fine(attachment.getEntryName(0) 
				+ " - Size=" + imageData.length);
		else
			log.log(Level.WARNING, attachment.getEntryName(0)
				+ " - not loaded (must be gif or jpg) - AD_PrintFormatItem_ID=" + AD_PrintFormatItem_ID);
	}	//	loadAttachment

	
	/**************************************************************************
	 * 	Calculate Image Size.
	 * 	Set p_width & p_height
	 * 	@return true if calculated
	 */
	protected boolean calculateSize()
	{
		p_width = 0;
		p_height = 0;
		if (m_image == null)
			return true;
		//	we have an image
		waitForLoad(m_image);
		if (m_image != null)
		{
			p_width = m_image.getWidth(this);
			p_height = m_image.getHeight(this);
						
			if (p_width * p_height == 0)
				return true;	//	don't bother scaling and prevent div by 0

			// 0 = unlimited so scale to fit restricted dimension
			if (p_maxWidth * p_maxHeight != 0)	// scale to maintain aspect ratio
			{
				if (p_width/p_height > p_maxWidth/p_maxHeight)
					// image "fatter" than available area
					m_scaleFactor = p_maxWidth/p_width;
				else
					m_scaleFactor = p_maxHeight/p_height;			
			}
			p_width = (float) m_scaleFactor * p_width;
			p_height = (float) m_scaleFactor * p_height;
		}
		return true;
	}	//	calculateSize

	/**
	 * 	Get the Image
	 *	@return image
	 */
	public Image getImage()
	{
		return m_image;
	}	//	getImage
	
	/**
	 * 	Paint Image
	 * 	@param g2D Graphics
	 *  @param pageStart top left Location of page
	 *  @param pageNo page number for multi page support (0 = header/footer) - ignored
	 *  @param ctx print context
	 *  @param isView true if online view (IDs are links)
	 */
	public void paint(Graphics2D g2D, int pageNo, Point2D pageStart, Properties ctx, boolean isView)
	{
		if (m_image == null)
			return;

		//	Position
		Point2D.Double location = getAbsoluteLocation(pageStart);
		int x = (int)location.x;
		if (MPrintFormatItem.FIELDALIGNMENTTYPE_TrailingRight.equals(p_FieldAlignmentType))
			x += p_maxWidth - p_width;
		else if (MPrintFormatItem.FIELDALIGNMENTTYPE_Center.equals(p_FieldAlignmentType))
			x += (p_maxWidth - p_width) / 2;
		int y = (int)location.y;

		// 	map a scaled and shifted version of the image to device space
		AffineTransform transform = new AffineTransform();
		transform.translate(x,y);
		transform.scale(m_scaleFactor, m_scaleFactor); 
		g2D.drawImage(m_image, transform, this);
	}	//	paint
	
}	//	ImageElement
