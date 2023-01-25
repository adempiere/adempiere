/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
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
package org.compiere.model;

import java.awt.Container;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import org.adempiere.core.domains.models.X_AD_Image;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.util.CCache;
import org.compiere.util.Ini;
import org.spin.util.AttachmentUtil;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 *  Image Model
 *  (DisplayType = 32)
 *
 *  @author Jorg Janke
 *  @version $Id: MImage.java,v 1.5 2006/07/30 00:51:02 jjanke Exp $
 */
public class MImage extends X_AD_Image
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7361463683427300715L;

	/**
	 * 	Get MImage from Cache
	 *	@param ctx context
	 *	@param AD_Image_ID id
	 *	@return MImage
	 */
	public static MImage get (Properties ctx, int AD_Image_ID)
	{
	    return get(ctx, AD_Image_ID, null);
	}
	
    /**
     *  Get MImage from Cache
     *  @param ctx context
     *  @param ad_image_id id
     *  @param trxName The Transaction name
     *  @return MImage
     */
    public static MImage get (Properties ctx, int ad_image_id, String trxName)
    {
		if (ad_image_id == 0)
			return new MImage (ctx, ad_image_id, trxName);
		//
		Integer key = Integer.valueOf(ad_image_id);
		MImage retValue = cache.get (key);
		if (retValue != null)
			return retValue;
		retValue = new MImage (ctx, ad_image_id, trxName);
		if (retValue.get_ID () != 0 && Ini.isClient())
			cache.put (key, retValue);
		return retValue;
	} //	get

	private static CCache<Integer,MImage> cache = new CCache<>("AD_Image", 20);
	
	/**
	 *  Constructor
	 *  @param ctx context
	 *  @param AD_Image_ID image
	 *  @param trxName transaction
	 */
	public MImage(Properties ctx, int AD_Image_ID, String trxName)
	{
		super (ctx, AD_Image_ID, trxName);
		if (AD_Image_ID < 1)
			setName("-");
	}   //  MImage

	/**
	 * 	Load Constructor
	 *	@param ctx
	 *	@param rs
	 *  @param trxName transaction
	 */
	public MImage (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MImage

	
	/** The Image                   */
	private Image			m_image = null;
	/** The Icon                   */
	private Icon			m_icon = null;
	/**	Binary Data					*/
	private byte[]			data = null;

	/**
	 * 	Get Image
	 *	@return image or null
	 */
	public Image getImage ()
	{
		if (m_image != null)
			return m_image;
		//	Via byte array
		byte[] data = getBinaryData();
		if (data != null && data.length > 0)
		{
			try
			{
			BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(data));
	        m_image = bufferedImage;
	        return m_image;
			}
			catch (IOException e) {
				log.log(Level.WARNING, "(byteArray)", e);
				return null;
            }
			/*try
			{
				Toolkit tk = Toolkit.getDefaultToolkit();
				m_image = tk.createImage(data);
				MediaTracker mediaTracker = new MediaTracker(new Container());
                mediaTracker.addImage(m_image, 0);
                mediaTracker.waitForID(0);
				return m_image;
			}
			catch (Exception e)
			{
				log.log(Level.WARNING, "(byteArray)", e);
				return null;
			}*/
		}
		//	Via URL
		URL url = getURL();
		if (url == null)
			return null;
		try
		{
			Toolkit tk = Toolkit.getDefaultToolkit();
			m_image = tk.getImage(url);
			MediaTracker mediaTracker = new MediaTracker(new Container());
            mediaTracker.addImage(m_image, 0);
            mediaTracker.waitForID(0);
			return m_image;
		}
		catch (Exception e)
		{
			log.log(Level.WARNING, "(URL)", e);
		}
		return null;
	}   //  getImage

	/**
	 * 	Get Icon
	 *	@return icon or null
	 */
	public Icon getIcon ()
	{
		if (m_icon != null)
			return m_icon;
		//	Via byte array
		byte[] data = getBinaryData();
		if (data != null && data.length > 0)
		{
			try
			{
				BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(data));
				m_icon = new ImageIcon(bufferedImage, getName());
				return m_icon;
			}
			catch (Exception e)
			{
				log.log(Level.WARNING, "(byteArray)", e);
				return null;
			}
		}
		//	Via URL
		URL url = getURL();
		if (url == null)
			return null;
		try
		{
			m_icon = new ImageIcon(url, getName());
			return m_icon;
		}
		catch (Exception e)
		{
			log.log(Level.WARNING, "(URL)", e);
		}
		return null;
	}   //  getIcon

	/**
	 * 	Get URL
	 *	@return url or null
	 */
	private URL getURL()
	{
		String str = getImageURL();
		if (str == null || str.length() == 0)
			return null;
		
		URL url = null;
		try
		{
			//	Try URL directly
			if (str.indexOf("://") != -1)
				url = new URL(str);
			else	//	Try Resource
				url = getClass().getResource(str);
			//
			if (url == null)
				log.warning("Not found: " + str);
		}
		catch (Exception e)
		{
			log.warning("Not found: " + str + " - " + e.getMessage());
		}
		return url;
	}	//	getURL
	
	/**
	 * 	Set Image URL
	 *	@param ImageURL url
	 */
	public void setImageURL (String ImageURL)
	{
		m_image = null;
		m_icon = null;
		super.setImageURL (ImageURL);
	}	//	setImageURL
	
	/**
	 * 	Set Binary Data
	 *	@param BinaryData data
	 */
	public void setBinaryData (byte[] BinaryData)
	{
		m_image = null;
		m_icon = null;
		super.setBinaryData (BinaryData);
	}	//	setBinaryData
	
	@Override
	public byte[] getBinaryData() {
		byte[] data = null;
		if(getAD_Image_ID() != 0) {
			if(AttachmentUtil.getInstance().isValidForClient(getAD_Client_ID())) {
				try {
					data = AttachmentUtil.getInstance()
							.clear()
							.withImageId(getAD_Image_ID())
							.withClientId(getAD_Client_ID())
							.getAttachment();
				} catch (Exception e) {
					log.warning("Error loading image: " + e.getLocalizedMessage());
				}
				return data;
			} else {	//	Get from DB
				data = super.getBinaryData();
			}
		}
		//	
		return data;
	}
	
	/**
	 * 	Get Data 
	 *	@return data
	 */
	public byte[] getData()
	{
		byte[] data = getBinaryData ();
		if (data != null)
			return data;
		//	From URL
		String str = getImageURL();
		if (str == null || str.length() == 0)
		{
			log.config("No Image URL");
			return null;
		}
		//	Get from URL
		URL url = getURL();
		if (url == null)
		{
			log.config("No URL");
			return null;
		}
		try
		{
			URLConnection conn = url.openConnection();
		    conn.setUseCaches(false);
		    InputStream is = conn.getInputStream();
			byte[] buffer = new byte[1024*8];   //  8kB
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			int length = -1;
			while ((length = is.read(buffer)) != -1)
				os.write(buffer, 0, length);
			is.close();
			data = os.toByteArray();
			os.close();
		    
		}
		catch (Exception e)
		{
			log.config (e.toString());
		}
		return data;
	}	//	getData
	
	/**
	 *  String Representation
	 *  @return String
	 */
	public String toString()
	{
		return "MImage[ID=" + get_ID() + ",Name=" + getName() + "]";
	}   //  toString

	
	/**
	 * 	Before Save
	 *	@param newRecord new
	 *	@return true
	 */
	protected boolean beforeSave (boolean newRecord)
	{
		if (getAD_Org_ID() != 0)
			setAD_Org_ID(0);
		if(AttachmentUtil.getInstance().isValidForClient(getAD_Client_ID())) {
			data = super.getBinaryData();
			super.setBinaryData(null);
		}
		return true;
	}	//	beforeSave
	
	@Override
	protected boolean afterSave(boolean newRecord, boolean success) {
		//	Save from external
		if(AttachmentUtil.getInstance().isValidForClient(getAD_Client_ID())) {
			try {
				AttachmentUtil.getInstance()
					.clear()
					.withImageId(getAD_Image_ID())
					.withFileName(getName())
					.withDescription(getDescription())
					.withData(data)
					.withTansactionName(get_TrxName())
					.saveAttachment();
			} catch (Exception e) {
				throw new AdempiereException(e);
			}
		}
		data = null;
		return super.afterSave(newRecord, success);
	}
	
}   //  MImage
