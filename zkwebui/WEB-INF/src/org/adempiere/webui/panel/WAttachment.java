/******************************************************************************
 * Copyright (C) 2008 Low Heng Sin  All Rights Reserved.                      *
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
 * Posterita Ltd., 3, Draper Avenue, Quatre Bornes, Mauritius                 *
 * or via info@posterita.org or http://www.posterita.org/                     *
 *****************************************************************************/

package org.adempiere.webui.panel;

import java.awt.Dimension;
import java.util.logging.Level;

import org.adempiere.webui.apps.AEnv;
import org.adempiere.webui.component.Button;
import org.adempiere.webui.component.ListItem;
import org.adempiere.webui.component.Listbox;
import org.adempiere.webui.component.Panel;
import org.adempiere.webui.component.Textbox;
import org.adempiere.webui.component.Window;
import org.adempiere.webui.window.FDialog;
import org.compiere.model.MAttachment;
import org.compiere.model.MAttachmentEntry;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.zkoss.util.media.AMedia;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zkex.zul.Borderlayout;
import org.zkoss.zkex.zul.Center;
import org.zkoss.zkex.zul.North;
import org.zkoss.zkex.zul.South;
import org.zkoss.zkex.zul.West;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Fileupload;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Iframe;

/**
 * 
 * @author Low Heng Sin
 *
 */
public class WAttachment extends Window implements EventListener
{
	private static final long serialVersionUID = 1L;
	
	private static CLogger log = CLogger.getCLogger(WAttachment.class);

	/**	Window No				*/
	private int	m_WindowNo;
	
	/** Attachment				*/
	private MAttachment	m_attachment;
	
	/** Change					*/
	private boolean m_change = false;

	private Iframe preview = new Iframe();

	private Textbox text = new Textbox();

	private Listbox cbContent = new Listbox();

	private Button bDelete = new Button();
	private Button bSave = new Button();
	private Button bDeleteAll = new Button();
	private Button bLoad = new Button();
	private Button bCancel = new Button();
	private Button bOk = new Button();
	
	private Panel previewPanel = new Panel();

	private Borderlayout mainPanel = new Borderlayout();

	private Hbox toolBar = new Hbox();	
	
	private Hbox confirmPanel = new Hbox();

	/**
	 *	Constructor.
	 *	loads Attachment, if ID <> 0
	 *  @param WindowNo window no
	 *  @param AD_Attachment_ID attachment
	 *  @param AD_Table_ID table
	 *  @param Record_ID record key
	 *  @param trxName transaction
	 */
	
	public WAttachment(	int WindowNo, int AD_Attachment_ID,
						int AD_Table_ID, int Record_ID, String trxName)
	{
		super();
		
		log.config("ID=" + AD_Attachment_ID + ", Table=" + AD_Table_ID + ", Record=" + Record_ID);

		m_WindowNo = WindowNo;

		try
		{
			staticInit();
		}
		catch (Exception ex)
		{
			log.log(Level.SEVERE, "", ex);
		}
		
		//	Create Model
		
		if (AD_Attachment_ID == 0)
			m_attachment = new MAttachment (Env.getCtx(), AD_Table_ID, Record_ID, trxName);
		else
			m_attachment = new MAttachment (Env.getCtx(), AD_Attachment_ID, trxName);
		
		loadAttachments();

		try
		{
			AEnv.showWindow(this);
		}
		catch (Exception e)
		{
		}
		
	} // WAttachment

	/**
	 *	Static setup.
	 *  <pre>
	 *  - northPanel
	 *      - toolBar
	 *      - title
	 *  - centerPane [split]
	 * 		- previewPanel (left)
	 *  	- text (right)
	 *  - confirmPanel
	 *  </pre>
	 *  @throws Exception
	 */
	
	void staticInit() throws Exception
	{
		this.setWidth("500px");
		this.setHeight("600px");
		this.setTitle("Attachment");
		this.setClosable(true);
		this.setBorder("normal");
		this.appendChild(mainPanel);
		mainPanel.setHeight("100%");
		mainPanel.setWidth("100%");
		
		
		North northPanel = new North();
		northPanel.setCollapsible(false);
		northPanel.setSplittable(false);
		
		cbContent.setMold("select");
		cbContent.setRows(0);
		cbContent.addEventListener(Events.ON_SELECT, this);
		
		toolBar.appendChild(bLoad);
		toolBar.appendChild(bDelete);
		toolBar.appendChild(bSave);
		toolBar.appendChild(cbContent);
		
		mainPanel.appendChild(northPanel);
		northPanel.appendChild(toolBar);
		
		bSave.setEnabled(false);
		bSave.setSrc("/images/Export24.png");
		bSave.setTooltiptext(Msg.getMsg(Env.getCtx(), "AttachmentSave"));
		bSave.addEventListener(Events.ON_CLICK, this);

		bLoad.setSrc("/images/Import24.png");
		bLoad.setTooltiptext(Msg.getMsg(Env.getCtx(), "Load"));
		bLoad.addEventListener(Events.ON_CLICK, this);

		bDelete.setSrc("/images/Delete24.png");
		bDelete.setTooltiptext(Msg.getMsg(Env.getCtx(), "Delete"));
		bDelete.addEventListener(Events.ON_CLICK, this);

		previewPanel.appendChild(preview);
		preview.setHeight("100%");
		preview.setWidth("100%");
			
		Center centerPane = new Center();
		centerPane.setAutoscroll(true);
		centerPane.setFlex(true);
		mainPanel.appendChild(centerPane);
		centerPane.appendChild(previewPanel);
		
		West westPane = new West();
		westPane.setWidth("20%");
		westPane.setSplittable(true);
		westPane.setCollapsible(true);
		mainPanel.appendChild(westPane);
		westPane.appendChild(text);

		South southPane = new South();
		mainPanel.appendChild(southPane);
		southPane.appendChild(confirmPanel);
		southPane.setHeight("30px");
		
		bCancel.setImage("/images/Cancel24.png");
		bCancel.addEventListener(Events.ON_CLICK, this);

		bOk.setImage("/images/Ok24.png");
		bOk.addEventListener(Events.ON_CLICK, this);
		
		bDeleteAll.setImage("/images/Delete24.png");
		bDeleteAll.addEventListener(Events.ON_CLICK, this);
		
		confirmPanel.appendChild(bDeleteAll);
		confirmPanel.appendChild(bCancel);
		confirmPanel.appendChild(bOk);
	}
	
	/**
	 * 	Dispose
	 */
	
	public void dispose ()
	{
		preview = null;
		this.detach();
	} // dispose
	
	/**
	 *	Load Attachments
	 */
	
	private void loadAttachments()
	{
		log.config("");
		
		//	Set Text/Description
		
		String sText = m_attachment.getTextMsg();
		
		if (sText == null)
			text .setText("");
		else
			text.setText(sText);

		//	Set Combo
		
		int size = m_attachment.getEntryCount();
		
		for (int i = 0; i < size; i++)
			cbContent.appendItem(m_attachment.getEntryName(i), m_attachment.getEntryName(i));
		
		if (size > 0)
		{
			cbContent.setSelectedIndex(0);					
		}
		displayData(0);
		
	} // loadAttachment
	
	/**
	 *  Display gif or jpg in gifPanel
	 * 	@param index index
	 */
	
	private void displayData (int index)
	{
		MAttachmentEntry entry = m_attachment.getEntry(index); 
		log.config("Index=" + index + " - " + entry);
		
		//	Reset UI		
		preview.setVisible(false);

		bDelete.setEnabled(false);
		bSave.setEnabled(false);

		Dimension size = null;
		
		if (entry != null && entry.getData() != null)
		{
			bSave.setEnabled(true);
			bDelete.setEnabled(true);
			
			log.config(entry.toStringX());

			try
			{
				AMedia media = new AMedia(entry.getName(), null, entry.getContentType(), entry.getData());
				preview.setContent(media);
				preview.setVisible(true);
			}
			catch (Exception e)
			{
				log.log(Level.SEVERE, "attachment", e);
			}
		}		
	}   //  displayData
	
	/**
	 * 	Get File Name with index
	 *	@param index index
	 *	@return file name or null
	 */
	
	private String getFileName (int index)
	{
		String fileName = null;
	
		if (cbContent.getItemCount() > index)
		{
			ListItem listitem = cbContent.getItemAtIndex(index);
			fileName = (String)listitem.getValue();
		}
		
		return fileName;
	}	//	getFileName

	/**
	 *	Action Listener
	 *  @param e event
	 */
	
	public void onEvent(Event e)
	{
		//	Save and Close
		
		if (e.getTarget() == bOk)
		{
			String newText = text.getText();
			
			if (newText == null)
				newText = "";
			
			String oldText = m_attachment.getTextMsg();
			
			if (oldText == null)
				oldText = "";
			
			if (!m_change)
				m_change = !newText.equals(oldText);
			
			if (newText.length() > 0 || m_attachment.getEntryCount() > 0)
			{
				if (m_change)
				{
					m_attachment.setTextMsg(text.getText());
					m_attachment.save();
				}
			}
			else
				m_attachment.delete(true);
			
			dispose();
		}
	
		//	Cancel
		
		else if (e.getTarget() == bCancel)
		{
			dispose();
		}
		
		//	Delete Attachment
		
		else if (e.getTarget() == bDeleteAll)
		{
			deleteAttachment();
			dispose();
		}
		
		//	Delete individual entry and Return
		
		else if (e.getTarget() == bDelete)
			deleteAttachmentEntry();
		
		//	Show Data
		
		else if (e.getTarget() == cbContent)
			displayData (cbContent.getSelectedIndex());
		
		//	Load Attachment
		
		else if (e.getTarget() == bLoad)
			loadFile();
		
		//	Open Attachment
		
		else if (e.getTarget() == bSave)
			saveAttachmentToFile();
		
	}	//	onEvent
	
	/**************************************************************************
	 *	Load file for attachment
	 */
	
	private void loadFile()
	{
		log.info("");
		
		Media media = null;
		
		try 
		{
			media = Fileupload.get(); 
			
			if (media != null)
			{
//				pdfViewer.setContent(media);
				;
			}
			else
				return;
		}
		catch (InterruptedException e) 
		{
			log.log(Level.WARNING, e.getLocalizedMessage(), e);
		}
	
		String fileName = media.getName(); 
		log.config(fileName);
		int cnt = m_attachment.getEntryCount();
		
		//update
		
		for (int i = 0; i < cnt; i++) 
		{
			if (m_attachment.getEntryName(i).equals(fileName))
			{
				m_attachment.updateEntry(i, media.getByteData());
				cbContent.setSelectedIndex(i);
				m_change = true;
				return;
			}
		}
		
		//new
		
		if (m_attachment.addEntry(fileName, media.getByteData()))
		{
			cbContent.appendItem(media.getName(), media.getName());
			cbContent.setSelectedIndex(cbContent.getItemCount()-1);
			m_change = true;
		}
	}	//	getFileName

	/**
	 *	Delete entire Attachment
	 */
	private void deleteAttachment()
	{
		log.info("");
		
		if (FDialog.ask(m_WindowNo, this, "AttachmentDelete?"))
			m_attachment.delete(true);
	}	//	deleteAttachment

	/**
	 *	Delete Attachment Entry
	 */
	
	private void deleteAttachmentEntry()
	{
		log.info("");
		
		int index = cbContent.getSelectedIndex();
		String fileName = getFileName(index);
		
		if (fileName == null)
			return;

		if (FDialog.ask(m_WindowNo, this, "AttachmentDeleteEntry?"))
		{
			if (m_attachment.deleteEntry(index))
				cbContent.removeItemAt(index);
			
			m_change = true;
		}
	}	//	deleteAttachment

	/**
	 *	Save Attachment to File
	 */
	
	private void saveAttachmentToFile()
	{
		int index = cbContent.getSelectedIndex();
		log.info("index=" + index);
	
		if (m_attachment.getEntryCount() < index)
			return;

		MAttachmentEntry entry = m_attachment.getEntry(index);
		if (entry != null && entry.getData() != null)
		{
			try
			{
				AMedia media = new AMedia(entry.getName(), null, entry.getContentType(), entry.getData());
				Filedownload.save(media);
			}
			catch (Exception e)
			{
				log.log(Level.SEVERE, "attachment", e);
			}
		}
	}	//	saveAttachmentToFile

}
