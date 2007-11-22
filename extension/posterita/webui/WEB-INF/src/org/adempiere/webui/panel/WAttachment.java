/******************************************************************************
 * Product: Posterita Ajax UI 												  *
 * Copyright (C) 2007 Posterita Ltd.  All Rights Reserved.                    *
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
import java.io.File;
import java.util.logging.Level;

import org.adempiere.webui.apps.AEnv;
import org.adempiere.webui.component.Button;
import org.adempiere.webui.component.ListItem;
import org.adempiere.webui.component.Listbox;
import org.adempiere.webui.component.Panel;
import org.adempiere.webui.component.Textbox;
import org.adempiere.webui.component.VerticalBox;
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
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Fileupload;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Iframe;
import org.zkoss.zul.Image;
import org.zkoss.zul.Separator;
import org.zkoss.zul.Splitter;

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

	private Iframe pdfViewer = new Iframe();

	private Textbox text = new Textbox();
	private Textbox info = new Textbox();

	private Listbox cbContent = new Listbox();

	private Image gifPanel = new Image();

	private Button bDelete = new Button();
	private Button bOpen = new Button();
	private Button bSave = new Button();
	private Button bDeleteAll = new Button();
	private Button bLoad = new Button();
	private Button bCancel = new Button();
	private Button bOk = new Button();
	
	private Panel graphPanel = new Panel();

	private VerticalBox mainPanel = new VerticalBox();

	private Panel northPanel = new Panel();

	private Hbox toolBar = new Hbox();
	private Hbox centerPane = new Hbox();
	
	private Hbox confirmPanel = new Hbox();

	/**
	 *	Constructor.
	 *	loads Attachment, if ID <> 0
	 *  @param frame frame
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
		
		//	needs to be modal otherwise APanel does not recognize change.
		
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
		
		//text.requestFocus();
	} // WAttachment

	/**
	 *	Static setup.
	 *  <pre>
	 *  - northPanel
	 *      - toolBar
	 *      - title
	 *  - centerPane [split]
	 * 		- graphPanel (left)
	 *		  	- gifScroll - gifPanel
	 *			- pdfViewer
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
		
		northPanel.appendChild(toolBar);
		
		cbContent.setMold("select");
		cbContent.setRows(0);
		cbContent.addEventListener(Events.ON_SELECT, this);
		
		toolBar.appendChild(bLoad);
		toolBar.appendChild(bDelete);
		toolBar.appendChild(bSave);
		toolBar.appendChild(bOpen);
		toolBar.appendChild(cbContent);
		
		mainPanel.appendChild(northPanel);
		mainPanel.appendChild(new Separator());

		bOpen.setEnabled(false);
		bOpen.setSrc("/images/Editor24.gif");
		bOpen.setTooltiptext(Msg.getMsg(Env.getCtx(), "Open"));
		bOpen.addEventListener(Events.ON_CLICK, this);

		bSave.setEnabled(false);
		bSave.setSrc("/images/Export24.gif");
		bSave.setTooltiptext(Msg.getMsg(Env.getCtx(), "AttachmentSave"));
		bSave.addEventListener(Events.ON_CLICK, this);

		bLoad.setSrc("/images/Import24.gif");
		bLoad.setTooltiptext(Msg.getMsg(Env.getCtx(), "Load"));
		bLoad.addEventListener(Events.ON_CLICK, this);

		bDelete.setSrc("/images/Delete24.gif");
		bDelete.setTooltiptext(Msg.getMsg(Env.getCtx(), "Delete"));
		bDelete.addEventListener(Events.ON_CLICK, this);

		//Dimension size = cbContent.getPreferredSize();
		//size.width = 200;
		//cbContent.setPreferredSize(size);
		//	cbContent.setToolTipText(text);
		//cbContent.addEventListener(Events.ON_SELECT,this);
		//cbContent.setLightWeightPopupEnabled(false);	//	Acrobat Panel is heavy

		//text.setBackground(AdempierePLAF.getInfoBackground());
		//text.setPreferredSize(new Dimension(200, 200));
		
		info.setText("-");
		info.setEnabled(false);
		graphPanel.appendChild(info);
			
		mainPanel.appendChild(centerPane);
		mainPanel.appendChild(new Separator());
		
		centerPane.appendChild(graphPanel);
		centerPane.appendChild(new Splitter());
		centerPane.appendChild(text);
		//centerPane.setResizeWeight(.75);	//	more to graph
		
		mainPanel.appendChild(confirmPanel);
		
		bCancel.setImage("/images/Cancel24.gif");
		bCancel.addEventListener(Events.ON_CLICK, this);

		bOk.setImage("/images/Ok24.gif");
		bOk.addEventListener(Events.ON_CLICK, this);
		
		bDeleteAll.setImage("/images/Delete24.gif");
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
		pdfViewer = null;
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
			cbContent.setSelectedIndex(0);
		else
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
		
		gifPanel.setSrc(null);
		graphPanel.getChildren().clear();

		bDelete.setEnabled(false);
		bOpen.setEnabled(false);
		bSave.setEnabled(false);

		Dimension size = null;
		
		//	no attachment
	
		if (entry == null || entry.getData() == null)
		{
			info.setText("-");
		}
		else
		{
			bOpen.setEnabled(true);
			bSave.setEnabled(true);
			bDelete.setEnabled(true);
			
			log.config(entry.toStringX());

			info.setText(entry.toStringX());
			
			if (entry.isPDF() && pdfViewer != null)
			{
				try
				{
					AMedia media = new AMedia(entry.getName(), "pdf", "application/pdf", entry.getData());
					pdfViewer.setContent(media);
/*					pdfViewer.loadPDF(entry.getInputStream());
					pdfViewer.setScale(50);
					size = pdfViewer.getPreferredSize();*/
					
					//	size.width = Math.min(size.width, 400);
					//	size.height = Math.min(size.height, 400);

					info.setVisible(false);
					pdfViewer.setVisible(true);
					graphPanel.appendChild(pdfViewer);
				}
				catch (Exception e)
				{
					log.log(Level.SEVERE, "(pdf)", e);
				}
			}
			else if (entry.isGraphic())
			{
				//  Can we display it
				
				
				
/*				Image image = Toolkit.getDefaultToolkit().createImage(entry.getData());
				
				if (image != null)
				{
					gifPanel.setImage(image);
					size = gifPanel.getPreferredSize();
					
					if (size.width == -1 && size.height == -1)
					{
						log.log(Level.SEVERE, "Invalid Image");
					}
					else
					{
						//	size.width += 40;
						//	size.height += 40;
						graphPanel.add(gifScroll, BorderLayout.CENTER);
					}
				}
				else
					log.log(Level.SEVERE, "Could not create image");*/
			}
		}
		
		if (graphPanel.getChildren().size() == 0)
		{
			graphPanel.appendChild(info);
		}

		log.config("Size=" + size);
		
		//	graphPanel.setPreferredSize(size);
		//	centerPane.setDividerLocation(size.width+30);
		//	size.width += 100;
		//	size.height += 100;
		//	centerPane.setPreferredSize(size);
		//	pack();
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
		//	log.config(e.getActionCommand());
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
		
		//	Open Attachment
		
		else if (e.getTarget() == bOpen)
		{
			if (!openAttachment())
				saveAttachmentToFile();
		}
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
				pdfViewer.setContent(media);
			}
			else
				return;
		}
		catch (InterruptedException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		//JFileChooser chooser = new JFileChooser();
		//chooser.setDialogType(JFileChooser.OPEN_DIALOG);
		//chooser.setDialogTitle(Msg.getMsg(Env.getCtx(), "AttachmentNew"));
		//int returnVal = chooser.showOpenDialog(this);
		//if (returnVal != JFileChooser.APPROVE_OPTION)
		//	return;
		
		String fileName = media.getName(); 
		log.config(fileName);
		File file = new File(fileName);
		int cnt = m_attachment.getEntryCount();
		
		//update
		
		for (int i = 0; i < cnt; i++) 
		{
			if (m_attachment.getEntryName(i).equals(fileName))
			{
				//if (m_attachment.) 
				{
					cbContent.setSelectedIndex(i);
					m_change = true;
				}
				return;
			}
		}
		
		//new
		
		if (m_attachment.addEntry(file))
		{
			//MAttachmentEntry attachmentEntry = new MAttachmentEntry(media.getName(), media.getByteData());

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

/*		String fileName = getFileName(index);
		String ext = fileName.substring (fileName.lastIndexOf('.'));
		log.config( "Ext=" + ext);*/

		ListItem listitem = cbContent.getSelectedItem();
		Media media = (Media)listitem.getValue();
		
		Filedownload.save(media);
		
/*		JFileChooser chooser = new JFileChooser();
		chooser.setDialogType(JFileChooser.SAVE_DIALOG);
		chooser.setDialogTitle(Msg.getMsg(Env.getCtx(), "AttachmentSave"));
		File f = new File(fileName);
		chooser.setSelectedFile(f);
		//	Show dialog
		int returnVal = chooser.showSaveDialog(this);
		if (returnVal != JFileChooser.APPROVE_OPTION)
			return;
		File saveFile = chooser.getSelectedFile();
		if (saveFile == null)
			return;*/

		/*log.config("Save to " + saveFile.getAbsolutePath());
		m_attachment.getEntryFile(index, saveFile);*/
	}	//	saveAttachmentToFile

	/**
	 *	Open the temporary file with the application associated with the extension in the file name
	 *	@return true if file was opened with third party application
	 */
	
	private boolean openAttachment ()
    {
        int index = cbContent.getSelectedIndex();
        byte[] data = m_attachment.getEntryData(index);
        
        if (data == null)
            return false;
        
        try 
        {
            String fileName = System.getProperty("java.io.tmpdir") + m_attachment.getEntryName(index);
            File tempFile = new File(fileName);
            m_attachment.getEntryFile(index, tempFile);
        
            if (Env.isWindows())
            {
            //	Runtime.getRuntime().exec ("rundll32 url.dll,FileProtocolHandler " + url);
                Process p = Runtime.getRuntime().exec("rundll32 SHELL32.DLL,ShellExec_RunDLL \"" + tempFile + "\"");
            //	p.waitFor();
                return true;
            }
            else if (Env.isMac())
            {
            	String [] cmdArray = new String [] {"open", tempFile.getAbsolutePath()};
            	Process p = Runtime.getRuntime ().exec (cmdArray);
            //	p.waitFor();
                return true;
            }
            else	//	other OS
            {
            }
        } 
        catch (Exception e) 
        {
        	log.log(Level.SEVERE, "", e);
        }
        return false;
    }    //    openFile

	
	/**************************************************************************
	 *  Graphic Image Panel
	 */
	
	class GImage extends Panel
	{
		private static final long serialVersionUID = 1L;

		/** The Image           */
		private Image m_image = null;

		/**
		 *  Graphic Image
		 */
		
		public GImage()
		{
			super();
		}   //  GImage

		/**
		 *  Set Image
		 *  @param image image
		 */
		
		public void setImage (Image image)
		{
			m_image = image;
			
			if (m_image == null)
				return;

			//MediaTracker mt = new MediaTracker(this);
			this.appendChild(m_image);
		
/*			try 
			{
				mt.waitForID(0);
			} 
			catch (Exception e) 
			{}
			
			Dimension dim = new Dimension(m_image.getWidth(this), m_image.getHeight(this));
			this.setPreferredSize(dim);*/
		}   //  setImage

		/**
		 *  Paint
		 *  @param g graphics
		 */
		
/*		public void paint (Graphics g)
		{
			Insets in = getInsets();
			
			if (m_image != null)
				g.drawImage(m_image, in.left, in.top, this);
		}   //  paint
*/
		/**
		 *  Update
		 *  @param g graphics
		 */
		
/*		public void update (Graphics g)
		{
			paint(g);
		}   //  update
*/	}	//	GImage
}
