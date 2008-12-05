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
package org.adempiere.webui.window;

import java.io.*;
import java.util.logging.*;

import org.adempiere.webui.apps.AEnv;
import org.adempiere.webui.component.Button;
import org.adempiere.webui.component.ConfirmPanel;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.Panel;
import org.adempiere.webui.component.Window;
import org.compiere.model.*;
import org.compiere.util.*;
import org.zkoss.image.AImage;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zkex.zul.Borderlayout;
import org.zkoss.zkex.zul.Center;
import org.zkoss.zkex.zul.North;
import org.zkoss.zkex.zul.South;
import org.zkoss.zul.Fileupload;
import org.zkoss.zul.Image;
import org.zkoss.zul.Separator;

/**
 *  Base on the original Swing Image Dialog.
 *  @author   Jorg Janke
 *  
 *  Zk Port
 *  @author Low Heng Sin 
 *  
 */
public class WImageDialog extends Window implements EventListener
{
	/**
	 *  Constructor
	 *  @param mImage
	 */
	public WImageDialog (MImage mImage)
	{
		super ();
		this.setTitle(Msg.translate(Env.getCtx(), "AD_Image_ID"));
		log.info("MImage=" + mImage);
		m_mImage = mImage;
		try
		{
			init();
		}
		catch(Exception ex)
		{
			log.log(Level.SEVERE, "", ex);
		}
		//  load data
		if (m_mImage == null)
			m_mImage = MImage.get (Env.getCtx(), 0);
		else
		{
			try {
				AImage aImage = new AImage(m_mImage.getName(), m_mImage.getData());
				
				image.setContent(aImage);
			} catch (Exception e) {
				log.log(Level.WARNING, "load image", e);
			}
		}
		
		fileButton.setLabel(m_mImage.getName());
//		imageLabel.setIcon(m_mImage.getIcon());
		AEnv.showCenterScreen(this);
	}   //  WImageDialog

	/**  Image Model            */
	private MImage      m_mImage = null;
	/**	Logger					*/
	private static CLogger log = CLogger.getCLogger(WImageDialog.class);

	/** */
	private Borderlayout mainLayout = new Borderlayout();
	private Panel parameterPanel = new Panel();
	private Label fileLabel = new Label();
	private Button fileButton = new Button();
	private Image image = new Image();
	private ConfirmPanel confirmPanel = new ConfirmPanel(true);

	/**
	 *  Static Init
	 *  @throws Exception
	 */
	void init() throws Exception
	{
		this.setWidth("450px");
		this.setHeight("550px");
		this.setSizable(true);
		
		mainLayout.setParent(this);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("100%");
		mainLayout.setStyle("background-color: transparent");
		
		fileLabel.setValue(Msg.getMsg(Env.getCtx(), "SelectFile"));
		fileButton.setLabel("-");
		
		North north = new North();
		north.setParent(mainLayout);
		north.setStyle("background-color: transparent");
		north.appendChild(parameterPanel);
		
		parameterPanel.appendChild(fileLabel);
		parameterPanel.appendChild(new Separator());
		parameterPanel.appendChild((fileButton));
		
		Center center = new Center();
		center.setFlex(true);
		center.setParent(mainLayout);
		center.appendChild(image);
		center.setStyle("background-color: transparent");
		
		South south = new South();
		south.setStyle("background-color: transparent; border: none;");
		south.setParent(mainLayout);
		south.appendChild(confirmPanel);
		
		//
		fileButton.addEventListener(Events.ON_CLICK, this);
		confirmPanel.addActionListener(Events.ON_CLICK, this);
	}   //  init

	public void onEvent(Event e) throws Exception {
		if (e.getTarget() == fileButton)
			cmd_file();

		else if (e.getTarget().getId().equals("Ok"))
		{
			if (m_mImage.save())
				detach();
			else
				FDialog.error(-1, "Failed to save image");
		}
		else if (e.getTarget().getId().equals("Cancel"))
		{
			m_mImage = null;	//	reset
			detach();
		}
	}
	
	/**
	 *  Load file & display
	 */
	private void cmd_file()
	{
		//  Show File Open Dialog
		Media imageFile = null;
		
		try 
		{
			imageFile = Fileupload.get(); 
			
			if (imageFile == null)
				return;
		}
		catch (InterruptedException e) 
		{
			log.warning(e.getLocalizedMessage());
			return;
		}

		String fileName = imageFile.getName();
		
		//  See if we can load & display it
		try
		{
			InputStream is = imageFile.getStreamData();
			AImage aImage = new AImage(fileName, is);
			
			image.setContent(aImage);
			
			is.close();
		}
		catch (Exception e)
		{
			log.log(Level.WARNING, "load image", e);
			return;
		}

		//  OK
		fileButton.setLabel(imageFile.getName());
		invalidate();

		//  Save info
		m_mImage.setName(fileName);
		m_mImage.setImageURL(fileName);
		if (image.getContent() != null)
			m_mImage.setBinaryData(image.getContent().getByteData());
		else
			m_mImage.setBinaryData(null);
	}   //  cmd_file

	/**
	 * 	Get Image ID
	 *	@return ID or 0
	 */
	public int getAD_Image_ID()
	{
		if (m_mImage != null)
			return m_mImage.getAD_Image_ID();
		return 0;
	}	//	getAD_Image_ID	
}   //  WImageDialog
