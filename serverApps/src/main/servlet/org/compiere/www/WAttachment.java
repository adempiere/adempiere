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
package org.compiere.www;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ecs.AlignType;
import org.apache.ecs.xhtml.a;
import org.apache.ecs.xhtml.br;
import org.apache.ecs.xhtml.form;
import org.apache.ecs.xhtml.input;
import org.apache.ecs.xhtml.label;
import org.apache.ecs.xhtml.p;
import org.apache.ecs.xhtml.table;
import org.apache.ecs.xhtml.td;
import org.apache.ecs.xhtml.textarea;
import org.apache.ecs.xhtml.tr;
import org.compiere.model.MAttachment;
import org.compiere.model.MAttachmentEntry;
import org.compiere.util.CLogger;
import org.compiere.util.FileUpload;
import org.compiere.util.Msg;
import org.compiere.util.WebDoc;
import org.compiere.util.WebEnv;
import org.compiere.util.WebUtil;


/**
 *	HTML UI Attachment
 *	
 *  @author Jorg Janke
 *  @version $Id: WAttachment.java,v 1.1 2009/04/15 11:27:15 vinhpt Exp $
 */
public class WAttachment extends HttpServlet
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5720154824503276682L;
	
	/**	Logger			*/
	private static CLogger	log = CLogger.getCLogger(WAttachment.class);

	/**
	 * Initialize global variables
	 */
	public void init(ServletConfig config)
		throws ServletException
	{
		super.init(config);
		if (!WebEnv.initWeb(config))
			throw new ServletException("WAttachment.init");
	}   //  init

	public static final String  P_Attachment_ID	= "AD_Attachment_ID";
	public static final String	P_ATTACHMENT_INDEX = "AttachmentIndex";
	public static final String	P_TEXTMSG = "TextMsg";

	private String m_error;
	
	/**
	 * 	Process the HTTP Get request.
	 * 	Initial display and streaming 
	 *  @param request request
	 *  @param response response
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
	{
		log.info("From " + request.getRemoteHost() + " - " + request.getRemoteAddr());
		HttpSession session = request.getSession(false);
		WWindowStatus ws = WWindowStatus.get(request);
		WebDoc doc = null;
		if (session == null || ws == null)
		{
			doc = WebDoc.createPopup ("No Context");
			//Modified by Rob Klein 4/29/07
			doc.addPopupClose(ws.ctx);
		}
		else
		{
			m_error = null;
			int AD_Attachment_ID = WebUtil.getParameterAsInt(request, P_Attachment_ID);
			if (AD_Attachment_ID != 0)
			{
				int attachmentIndex = WebUtil.getParameterAsInt(request, P_ATTACHMENT_INDEX);
				if (attachmentIndex != 0)
				{
					m_error = streamAttachment (AD_Attachment_ID, attachmentIndex, response, ws);
					if (m_error == null)
						return;
				}
			}
			
			MAttachment attachment = null;
			if (AD_Attachment_ID != 0)
				attachment = new MAttachment(ws.ctx, ws.curTab.getAD_AttachmentID(), null);
			else
				attachment = new MAttachment (ws.ctx, ws.curTab.getAD_Table_ID(), ws.curTab.getRecord_ID(), null);
			
			doc = createPage (ws.ctx, attachment, m_error);
		}
		//
		WebUtil.createResponse(request, response, this, null, doc, false);
	}   //  doGet


	/**
	 *  Process the HTTP Post request.
	 *  Update Attachment
	 *  @param request request
	 *  @parem response response
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
	{
		log.info("From " + request.getRemoteHost() + " - " + request.getRemoteAddr());
		HttpSession sess = request.getSession(false);
		WWindowStatus ws = WWindowStatus.get(request);
		//
		WebDoc doc = null;
		if (ws == null)
			doc = WebDoc.create ("Help - No Context");
		else
		{
			MAttachment attachment = processPost (request, response, ws);
			doc = createPage (ws.ctx, attachment, m_error);
		}
		//
		WebUtil.createResponse(request, response, this, null, doc, false);
	}   //  doPost

	/**
	 * 	Create Attachment Page
	 * 	@param ctx context
	 *	@param AD_Attachment_ID id for existing attachment
	 *	@param AD_Table_ID table for new attachment
	 *	@param Record_ID record for new attachment
	 *	@param m_error optional m_error message
	 *	@return WebDoc
	 */
	private WebDoc createPage (Properties ctx, MAttachment attachment, String error)
	{
		WebDoc doc = WebDoc.createPopup (Msg.translate(ctx, "AD_Attachment_ID"));
		table table = doc.getTable();
		//
		if (error != null)
		{
			table.addElement(new tr()
				.addElement(new td ("popupHeader", AlignType.RIGHT, AlignType.TOP, false, null))
				.addElement(new td ("popupHeader", AlignType.LEFT, AlignType.TOP, false, 
					new p(error, AlignType.LEFT).setClass("Cerror"))));	//	window.css
		}

		//
		tr tr = new tr();
		td left = new td("popupCenter", AlignType.LEFT, AlignType.TOP, false,
			new label("TextMsg", "T", Msg.translate(ctx, "TextMsg")));
		//
		td right = new td("popupCenter", AlignType.LEFT, AlignType.TOP, false);

		//	Text Message Update
		form textMsg = new form("WAttachment");
		textMsg.addElement(new input (input.TYPE_HIDDEN, P_Attachment_ID, attachment.getAD_Attachment_ID()));
		textMsg.addElement(new input (input.TYPE_HIDDEN, "AD_Table_ID", attachment.getAD_Table_ID()));
		textMsg.addElement(new input (input.TYPE_HIDDEN, "Record_ID", attachment.getRecord_ID()));
		textarea msg = new textarea(P_TEXTMSG, 5, 40);
		msg.addElement(attachment.getTextMsg());
		textMsg.addElement(msg);
		textMsg.addElement(new br());
		
		//textMsg.addElement(new input (input.TYPE_SUBMIT, "submit", "Submit"));
		//Submit Button
		//String textbtn = "Submit";
		//String text = null;
		//if (ctx != null)
			//text = Msg.getMsg (ctx, "Submit");		
		//input submitbtn = new input(input.TYPE_SUBMIT, textbtn, "  "+text);		
		//submitbtn.setID(text);
		//submitbtn.setClass("submitbtn");
		//textMsg.addElement(submitbtn);
		right.addElement(textMsg);


		//	Existing Links
		p p = new p();
		MAttachmentEntry[] entries = attachment.getEntries();
		for (int i = 0; i < entries.length; i++)
		{
			MAttachmentEntry entry = entries[i];
			if (i > 0)
				p.addElement(" - ");
			String url = "WAttachment?" + P_Attachment_ID + "=" + attachment.getAD_Attachment_ID()
				+ "&" + P_ATTACHMENT_INDEX + "=" + entry.getIndex();
			p.addElement(new a(url, null, a.TARGET_BLANK, entry.getName()));
		}
		right.addElement(p);
		
		//	Upload
		form upload = FileUpload.createForm("WAttachment");
		upload.addElement(new input (input.TYPE_HIDDEN, P_Attachment_ID, attachment.getAD_Attachment_ID()));
		upload.addElement(new input (input.TYPE_HIDDEN, "AD_Table_ID", attachment.getAD_Table_ID()));
		upload.addElement(new input (input.TYPE_HIDDEN, "Record_ID", attachment.getRecord_ID()));
		right.addElement(upload);
		//
		tr.addElement(left);
		tr.addElement(right);		
		table.addElement(tr);
		
		//	Footer
		//Modified by Rob Klein 4/29/07
		doc.addPopupClose(ctx);
		//
	//	System.out.println(doc);
		return doc;
	}	//	createPage

	/**
	 * 	Stream Attachment Entry  
	 *	@param AD_Attachment_ID attachment
	 *	@param attachmentIndex index
	 *	@param response response
	 *	@param ws status
	 *	@return m_error message
	 */
	private String streamAttachment (int AD_Attachment_ID, int attachmentIndex,  
		HttpServletResponse response, WWindowStatus ws)
	{
		log.info("AD_Attachment_ID=" + AD_Attachment_ID 
			+ ", AttachmentIndex=" + attachmentIndex);

		MAttachment attachment = new MAttachment(ws.ctx, AD_Attachment_ID, null);
		if (attachment.get_ID() == 0)
		{
			log.fine("No Attachment AD_Attachment_ID=" + AD_Attachment_ID);
			return "Attachment not found";
		}
		
		//	Make sure it's the right attachment
		if (ws.curTab.getAD_AttachmentID() != AD_Attachment_ID)
		{
			log.warning ("Tab AD_Attachment_ID="
				+ ws.curTab.getAD_AttachmentID() + " <> " + AD_Attachment_ID);
			return "Your Attachment not found";
		}
		//	Stream it
		return WebUtil.streamAttachment (response, attachment, attachmentIndex);
	}	//	streamAttachment

	/**
	 * 	Process Post.
	 * 	Update / Create Attachment
	 * 	Upload Attachment Entry  
	 *	@param request request
	 *	@param response response
	 *	@return m_error message
	 */
	private MAttachment processPost (HttpServletRequest request, 
		HttpServletResponse response, WWindowStatus ws)
	{
		int AD_Attachment_ID = 0;
		int AD_Table_ID = 0;
		int Record_ID = 0;
		String textMsg = null;
		FileUpload upload = null;
		
		//	URL Encrypted
		if (request.getContentType().equals(form.ENC_DEFAULT))
		{
			AD_Attachment_ID = WebUtil.getParameterAsInt(request, P_Attachment_ID);
			AD_Table_ID = WebUtil.getParameterAsInt(request, "AD_Table_ID");
			Record_ID = WebUtil.getParameterAsInt(request, "Record_ID");
			textMsg = WebUtil.getParameter (request, P_TEXTMSG);
		}
		else
		{
			upload = new FileUpload(request);
			m_error = upload.getError();
			if (m_error != null)
			{
				log.warning("pocessPost - " + m_error);
				return null;
			}
			AD_Attachment_ID = upload.getParameterAsInt(P_Attachment_ID);
			AD_Table_ID = upload.getParameterAsInt("AD_Table_ID");
			Record_ID = upload.getParameterAsInt("Record_ID");
		}
	//	WebEnv.dump(request);
		log.info("processPost - AD_Attachment_ID=" + AD_Attachment_ID 
			+ ", AD_Table_ID=" + AD_Table_ID + ", Record_ID=" + Record_ID
			+ " - Upload=" + upload);
		
		//	Check if you own the attachment
		if (ws.curTab.getAD_AttachmentID() != AD_Attachment_ID) {
			m_error = "Your Attachment not found";
			return null;
		}
		//	Check if we can save
		if (AD_Attachment_ID != 0 && Record_ID == 0) {
			m_error = "Need to save record first";
			return null;
		}

		MAttachment attachment = null;
		if (AD_Attachment_ID == 0)
			attachment = new MAttachment (ws.ctx, AD_Table_ID, Record_ID, null);
		else
			attachment = new MAttachment (ws.ctx, AD_Attachment_ID, null);
		
		//	Update Attachment Text
		if (textMsg != null)
			attachment.setTextMsg(textMsg);
		
		//	Create Attachment
		if (upload != null)
			attachment.addEntry(upload.getFileName(), upload.getData());
		
		//	Save and update
		if (attachment.save())
			ws.curTab.loadAttachments();	//	update Tab
		else {
			m_error = "Attachment not saved";
			return null;
		}

		return attachment;
		
	}	//	processPost

	
}	//	WAttachment
