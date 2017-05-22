/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
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
 * Copyright (C) 2003-2014 E.R.P. Consultores y Asociados, C.A.               *
 * All Rights Reserved.                                                       *
 * Contributor(s): Raul Muñoz www.erpcya.com					              *
 *****************************************************************************/

package org.adempiere.pos;


import java.awt.Color;
import java.util.HashMap;

import org.adempiere.webui.component.Borderlayout;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.Panel;
import org.compiere.model.MImage;
import org.compiere.model.MPOSKey;
import org.compiere.model.MPOSKeyLayout;
import org.compiere.print.MPrintColor;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.zkoss.image.AImage;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zkex.zul.Center;
import org.zkoss.zkex.zul.South;
import org.zkoss.zul.Image;

/**
 * Button panel supporting multiple linked layouts
 * @author Mario Calderon, mario.calderon@westfalia-it.com, Systemhaus Westfalia, http://www.westfalia-it.com
 * @author Raul Muñoz, rmunoz@erpcya.com, ERPCYA http://www.erpcya.com
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 */
public class WPOSKeyPanel extends Panel implements EventListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1773720355288801510L;


	/**
	 * 	Constructor
	 */
	public WPOSKeyPanel (int C_POSKeyLayout_ID, POSKeyListener caller, String m_txtCalc, boolean keyBoardType)
	{
		if (C_POSKeyLayout_ID == 0)
			return;
		setHeight("100%");
		setWidth("100%");
		this.keyBoardType=keyBoardType;
		appendChild(createPanel(C_POSKeyLayout_ID, m_txtCalc));
		currentLayout = C_POSKeyLayout_ID;
		
		this.caller = caller;
	}	//	PosFunctionKeys
	
	/**
	 * 	Constructor
	 */
	public WPOSKeyPanel (int C_POSKeyLayout_ID, POSKeyListener caller)
	{
		if (C_POSKeyLayout_ID == 0)
			return;
		setWidth("100%");
		appendChild(createPanel(C_POSKeyLayout_ID));
		setStyle("overflow:auto");
		currentLayout = C_POSKeyLayout_ID;
		this.caller = caller;
	}	//	PosFunctionKeys
	
	
	/** Currently displayed layout	*/
	private int 				currentLayout;
	/** Caller					*/
	private POSKeyListener 		caller;
	/** Panels					*/
	// Change Panel Disposal key access keys
	private HashMap <Integer, Panel> panelMap = new HashMap<Integer, Panel>();
	/** KeyBoard Type			*/
	private boolean 			keyBoardType; 
	
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(WPOSKeyPanel.class);
	/** Map of map of keys */
	private HashMap<Integer, HashMap<Integer, MPOSKey>> keymap = new HashMap<Integer, HashMap<Integer,MPOSKey>>();
	
	/**
	 * Create Button
	 * @param C_POSKeyLayout_ID
	 * @return
	 * @return Panel
	 */
	public Panel createButton(int C_POSKeyLayout_ID){
		if ( keymap.containsKey(C_POSKeyLayout_ID) ) {
			return null;
		}
		Panel card = new Panel();
		card.setWidth("100%");
		MPOSKeyLayout keyLayout = MPOSKeyLayout.get(Env.getCtx(), C_POSKeyLayout_ID);
		Color stdColor = Color.lightGray;
		if (keyLayout.getAD_PrintColor_ID() != 0)
		{
			MPrintColor color = MPrintColor.get(Env.getCtx(), keyLayout.getAD_PrintColor_ID());
			stdColor = color.getColor();
		}
		if (keyLayout.get_ID() == 0)
			return null;
		MPOSKey[] keys = keyLayout.getKeys(false);
		
		HashMap<Integer, MPOSKey> map = new HashMap<Integer, MPOSKey>(keys.length);

		keymap.put(C_POSKeyLayout_ID, map);
		
		int COLUMNS = 3;	//	Min Columns
		int ROWS = 3;		//	Min Rows
		int noKeys = keys.length;
		int cols = keyLayout.getColumns();
		if ( cols == 0 )
			cols = COLUMNS;
		int buttons = 0;
		log.fine( "PosSubFunctionKeys.init - NoKeys=" + noKeys 
			+ ", Cols=" + cols);
		//	Content
		Panel content = new Panel ();
				
		for (MPOSKey key :  keys)
		{
			if(!key.getName().equals("")){
			map.put(key.getC_POSKey_ID(), key);
			Color keyColor = stdColor;
			
			if (key.getAD_PrintColor_ID() != 0)	{
				MPrintColor color = MPrintColor.get(Env.getCtx(), key.getAD_PrintColor_ID());
				keyColor = color.getColor();
			}
			
			log.fine( "#" + map.size() + " - " + keyColor); 
			Panel button = new Panel();
			Label label = new Label(key.getName());
			
			Center nt = new Center();
			South st = new South();
			Borderlayout mainLayout = new Borderlayout();
			if ( key.getAD_Image_ID() != 0 )
			{
				MImage m_mImage = MImage.get(Env.getCtx(), key.getAD_Image_ID());
				AImage img = null;
				byte[] data = m_mImage.getData();
				if (data != null && data.length > 0) {
					try {
						img = new AImage(null, data);				
					} catch (Exception e) {		
					}
				}
				Image bImg = new Image();
				bImg.setContent(img);
				bImg.setWidth("66%");
				bImg.setHeight("80%");
				nt.appendChild(bImg);
			}
			label.setStyle("word-wrap: break-word; white-space: pre-line;margin: 25px 0px 0px 0px; top:20px; font-size:10pt; font-weight: bold;color: #FFF;");
			label.setHeight("100%");
			button.setHeight("100px");
			st.appendChild(label);
			button.setClass("z-button");
			button.setStyle("float:left; white-space: pre-line;text-align:center; margin:0.4% 1%; Background-color:rgb("+keyColor.getRed()+","+keyColor.getGreen()+","+keyColor.getBlue()+"); border: 2px outset #CCC; "
					+ "background: -moz-linear-gradient(top, rgba(247,247,247,1) 0%, rgba(255,255,255,0.93) 7%, rgba(186,186,186,0.25) 15%, rgba("+keyColor.getRed()+","+keyColor.getGreen()+","+keyColor.getBlue()+",1) 100%);"
					+ "background: -webkit-gradient(left top, left bottom, color-stop(0%, rgba(247,247,247,1)), color-stop(7%, rgba(255,255,255,0.93)), color-stop(15%, rgba(186,186,186,0.25)), color-stop(100%, rgba("+keyColor.getRed()+","+keyColor.getGreen()+","+keyColor.getBlue()+",1)));"
					+ "background: -webkit-linear-gradient(top, rgba(247,247,247,1) 0%, rgba(255,255,255,0.93) 7%, rgba(186,186,186,0.25) 15%, rgba("+keyColor.getRed()+","+keyColor.getGreen()+","+keyColor.getBlue()+",1) 100%);");
			
			mainLayout.appendChild(nt);
			mainLayout.appendChild(st);
			mainLayout.setStyle("background-color: transparent");
			nt.setStyle("background-color: transparent");
			st.setStyle("clear: both; background-color: #333; opacity: 0.6;");
			st.setZindex(99);
			button.appendChild(mainLayout);
			
			button.setId(""+key.getC_POSKey_ID());
			button.addEventListener("onClick", this);
			
			int size = 1;
			if ( key.getSpanX() > 1 )
			{
				size = key.getSpanX();
				button.setWidth("96%");
			}
			else 
				button.setWidth(90/cols+"%");
			if ( key.getSpanY() > 1 )
			{
				size = size*key.getSpanY();
			}
			buttons = buttons + size;
			content.appendChild(button);
		}
		}
		int rows = Math.max ((buttons / cols), ROWS);
		if ( buttons % cols > 0 )
			rows = rows + 1;


		
		card.appendChild(content);
		
		return card;
	}
	
	/** 
	 * Create Panel
	 * @param C_POSKeyLayout_ID
	 * @return
	 * @return Panel
	 */
	public Panel createPanel(int C_POSKeyLayout_ID){
		Panel card = new Panel();
		card.setWidth("100%");
		card.setStyle("overflow:AUTO");
		MPOSKeyLayout keyLayout = MPOSKeyLayout.get(Env.getCtx(), C_POSKeyLayout_ID);
	  
		// Removal panel static
		card = createButton(C_POSKeyLayout_ID);
		panelMap.put(C_POSKeyLayout_ID, card);
		  
		if (keyLayout.get_ID() == 0)
			return null;
		MPOSKey[] keys = keyLayout.getKeys(false);
		
		//	Content
		for (MPOSKey key :  keys)
		{
			if ( key.getSubKeyLayout_ID() > 0 )
			{
			  Panel subcard = new Panel();
			  subcard = createButton(key.getSubKeyLayout_ID());
			  panelMap.put(key.getSubKeyLayout_ID(), subcard);
				appendChild(subcard);
        subcard.setVisible(false);
			}
		}
		return card;
	}
	/**
	 * Create Panel For Keyboard
	 * @param C_POSKeyLayout_ID
	 * @param m_txtCalc
	 * @return
	 * @return Panel
	 */
	public Panel createPanel(int C_POSKeyLayout_ID, String m_txtCalc){
		Panel card = new Panel();
		card.setWidth("100%");
		MPOSKeyLayout keyLayout = MPOSKeyLayout.get(Env.getCtx(), C_POSKeyLayout_ID);
    
		// Removal panel static
    card = createButton(C_POSKeyLayout_ID, m_txtCalc);
    panelMap.put(C_POSKeyLayout_ID, card);

		if (keyLayout.get_ID() == 0)
			return null;
		MPOSKey[] keys = keyLayout.getKeys(false);
		
		//	Content
		for (MPOSKey key :  keys)
		{
			if ( key.getSubKeyLayout_ID() > 0 )
			{
			  //  Add subCard Panel
        Panel subCard = new Panel();
        subCard = createButton(key.getSubKeyLayout_ID(), m_txtCalc);
        panelMap.put(key.getSubKeyLayout_ID(), subCard);
        appendChild(subCard);
        subCard.setVisible(false);
			}
		}
		return card;
	}
	
	/**
	 * Create Buttton For Keyboard
	 * @param C_POSKeyLayout_ID
	 * @param m_txtCalc
	 * @return
	 * @return Panel
	 */
	private Panel createButton(int C_POSKeyLayout_ID, String m_txtCalc) {
		
		// already added
		if ( keymap.containsKey(C_POSKeyLayout_ID) )
		{
			return null;
		}
		
		Panel card = new Panel();
		MPOSKeyLayout keyLayout = MPOSKeyLayout.get(Env.getCtx(), C_POSKeyLayout_ID);

		Color stdColor = Color.lightGray;
		if (keyLayout.getAD_PrintColor_ID() != 0)
		{
			MPrintColor color = MPrintColor.get(Env.getCtx(), keyLayout.getAD_PrintColor_ID());
			stdColor = color.getColor();
		}
		
		if (keyLayout.get_ID() == 0)
			return null;
		MPOSKey[] keys = keyLayout.getKeys(false);
		
		HashMap<Integer, MPOSKey> map = new HashMap<Integer, MPOSKey>(keys.length);

		keymap.put(C_POSKeyLayout_ID, map);
		
		int COLUMNS = 3;	//	Min Columns
		int ROWS = 3;		//	Min Rows
		int noKeys = keys.length;
		int cols = keyLayout.getColumns();
		if ( cols == 0 )
			cols = COLUMNS;
		
		int buttons = 0;
		
		log.fine( "PosSubFunctionKeys.init - NoKeys=" + noKeys 
			+ ", Cols=" + cols);
		//	Content
		Panel content = new Panel ();
		for (MPOSKey key :  keys)
		{
					
			map.put(key.getC_POSKey_ID(), key);
			Color keyColor = stdColor;
			if (key.getAD_PrintColor_ID() != 0)
			{
				MPrintColor color = MPrintColor.get(Env.getCtx(), key.getAD_PrintColor_ID());
				keyColor = color.getColor();
			}
			
			log.fine( "#" + map.size() + " - " + keyColor); 
			Panel button = new Panel();
			Label label = new Label(key.getName());
			label.setStyle("margin: 25px 0px 00px 0px; top:20px; font-size:medium; font-weight: bold;");
			label.setHeight("100%");
			button.appendChild(label);
			button.setClass("z-button");
			button.setStyle("float:left; white-space: pre-line;text-align:center; margin:0.4% 1%; Background-color:rgb("+keyColor.getRed()+","+keyColor.getGreen()+","+keyColor.getBlue()+"); border: 2px outset #CCC; "
					+ "background: -moz-linear-gradient(top, rgba(247,247,247,1) 0%, rgba(255,255,255,0.93) 7%, rgba(186,186,186,0.25) 15%, rgba("+keyColor.getRed()+","+keyColor.getGreen()+","+keyColor.getBlue()+",1) 100%);"
					+ "background: -webkit-gradient(left top, left bottom, color-stop(0%, rgba(247,247,247,1)), color-stop(7%, rgba(255,255,255,0.93)), color-stop(15%, rgba(186,186,186,0.25)), color-stop(100%, rgba("+keyColor.getRed()+","+keyColor.getGreen()+","+keyColor.getBlue()+",1)));"
					+ "background: -webkit-linear-gradient(top, rgba(247,247,247,1) 0%, rgba(255,255,255,0.93) 7%, rgba(186,186,186,0.25) 15%, rgba("+keyColor.getRed()+","+keyColor.getGreen()+","+keyColor.getBlue()+",1) 100%);");
			button.setHeight("55px");
			button.setWidth("55px");

			button.setAction("onClick : text_action.textEvent('" +  m_txtCalc + "', '" + key.getText() + "', '" + this.keyBoardType + "')");
			button.setId(""+key.getC_POSKey_ID());
			button.addEventListener("onClick", this);

			int size = 1;
			if ( key.getSpanX() > 1 )
			{
				size = key.getSpanX();
			}
			if ( key.getSpanY() > 1 )
			{
				size = size*key.getSpanY();
			}
			buttons = buttons + size;
			content.appendChild(button);
		}
		
		int rows = Math.max ((buttons / cols), ROWS);
		if ( buttons % cols > 0 )
			rows = rows + 1;
		
		for (int i = buttons; i < rows*cols; i++)
		{
			Panel button = new Panel();
			button.setStyle("float:left; text-align:center; margin:0.4% 1%;");
			button.setHeight("55px");
			button.setWidth("55px");
			content.appendChild(button);
		}
		card.appendChild(content);

		return card;
	}

	/**
	 * 	Action Listener
	 *	@param e event
	 */
	@Override
	public void onEvent(Event event) throws Exception {
		String action = event.getTarget().getId();

		HashMap<Integer, MPOSKey> currentKeymap = keymap.get(currentLayout);

		try
		{
			int C_POSKey_ID = Integer.parseInt(action);
			MPOSKey key = currentKeymap.get(C_POSKey_ID);
			if ( key.getSubKeyLayout_ID() > 0 )
			{
	      //  Hidden current Panel
			  panelMap.get(currentLayout).setVisible(false);
			  currentLayout = key.getSubKeyLayout_ID();
	      //  Show other Panel
			  panelMap.get(currentLayout).setVisible(true);
			}
			else
			{
				caller.keyReturned(key);
			}
		}
		catch (Exception ex)
		{
		}
	}

	public void showPanel()
	{
		this.setVisible(true);
	}

	public void hidePanel()
	{
		setVisible(false);
	}
	
}
