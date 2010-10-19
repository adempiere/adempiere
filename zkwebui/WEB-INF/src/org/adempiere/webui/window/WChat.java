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

import java.util.logging.*;

import org.adempiere.webui.apps.AEnv;
import org.adempiere.webui.component.ConfirmPanel;
import org.adempiere.webui.component.Textbox;
import org.adempiere.webui.component.Window;
import org.compiere.model.*;
import org.compiere.util.*;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zkex.zul.Borderlayout;
import org.zkoss.zkex.zul.Center;
import org.zkoss.zkex.zul.North;
import org.zkoss.zkex.zul.South;
import org.zkoss.zul.Div;
import org.zkoss.zul.Html;

/**
 * 	Application Chat
 *
 *  @author Jorg Janke
 *  @version $Id: AChat.java,v 1.3 2006/07/30 00:51:27 jjanke Exp $
 */
public class WChat extends Window implements EventListener
{
	/**
	 * Generated serial version Id
	 */
	private static final long serialVersionUID = 5467212494843748048L;

	/**
	 *	Constructor.
	 *	loads Chat, if ID <> 0
	 *  @param WindowNo window no
	 *  @param CM_Chat_ID chat
	 *  @param AD_Table_ID table
	 *  @param Record_ID record key
	 *  @param Description description
	 *  @param trxName transaction
	 */
	public WChat (int WindowNo, int CM_Chat_ID,
		int AD_Table_ID, int Record_ID, String Description,
		String trxName)
	{
		super();
		setTitle(Msg.getMsg(Env.getCtx(), "Chat") + " " + Description);
		setAttribute(Window.MODE_KEY, Window.MODE_MODAL);
		log.config("ID=" + CM_Chat_ID
			+ ", Table=" + AD_Table_ID + ", Record=" + Record_ID);
		//
		m_WindowNo = WindowNo;
		//
		try
		{
			staticInit();
		}
		catch (Exception ex)
		{
			log.log(Level.SEVERE, "", ex);
		}
		//	Create Model
		if (CM_Chat_ID == 0)
			m_chat = new MChat (Env.getCtx(), AD_Table_ID, Record_ID, Description, trxName);
		else
			m_chat = new MChat (Env.getCtx(), CM_Chat_ID, trxName);
		loadChat();
		//
		try
		{
			newText.focus();
			AEnv.showCenterScreen(this);
		}
		catch (Exception e)
		{
		}		
	}	//	Attachment

	/**	Window No				*/
	@SuppressWarnings("unused")
	private int				m_WindowNo;
	/** Attachment				*/
	private MChat			m_chat;
	/**	Logger					*/
	private static CLogger log = CLogger.getCLogger(WChat.class);

	private Borderlayout 	mainPanel = new Borderlayout();
	private Div historyDiv;
	private Html historyText = new Html();
	private Textbox			newText = new Textbox();
	private ConfirmPanel	confirmPanel = new ConfirmPanel(true);

	/**
	 * 	Static Init.
	 *	@throws Exception
	 */
	private void staticInit () throws Exception
	{
		this.appendChild(mainPanel);
		mainPanel.setStyle("position:absolute; height:90%; width:95%; border: none; background-color: white;");
		//
		North north = new North();
		north.setSplittable(true);
		north.setStyle("border: none");
		mainPanel.appendChild(north);
		historyDiv = new Div();
		historyDiv.setStyle("position:absolute; height:100%; width:100%; background-color: lightgray;");
		historyDiv.appendChild(historyText);
		north.appendChild(historyDiv);
		north.setAutoscroll(true);
		north.setHeight("150px");

		Center center = new Center();
		center.appendChild(newText);
		newText.setStyle("position:absolute; height:100%; width:100%");
		newText.setMultiline(true);
		mainPanel.appendChild(center);
		//
		//	South
		South south = new South();
		south.setHeight("50px");
		south.setStyle("border: none; margin-top: 10px");
		south.appendChild(confirmPanel);
		mainPanel.appendChild(south);
		confirmPanel.addActionListener(this);

		this.setStyle("position: relative; height: 450px; width: 500px;");
		this.setMaximizable(true);
		this.setSizable(true);
		this.setBorder("normal");
	}	//	staticInit

	/**
	 * 	Load Chat
	 */
	private void loadChat()
	{
		String html = m_chat.getHistory(MChat.CONFIDENTIALTYPE_Internal).toString();
		historyText.setContent(html);
	}	//	loadChat


	/**
	 * 	Action Performed
	 *	@param e event
	 */
	public void actionPerformed (Event e)
	{
		if (e.getTarget().getId().equals(ConfirmPanel.A_OK))
		{
			String data = newText.getText();
			if (data != null && data.length() > 0)
			{
				log.config(data);
				if (m_chat.get_ID() == 0)
					m_chat.save();
				MChatEntry entry = new MChatEntry(m_chat, data);
				entry.save();
			}	//	data to be saved
		}
		dispose();
	}	//	actionPerformed

	public void onEvent(Event event) throws Exception {
		actionPerformed(event);
	}

}	//	AChat
