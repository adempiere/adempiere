/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 Adempiere, Inc. All Rights Reserved.               *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 *****************************************************************************/

package org.compiere.pos;


import java.awt.Color;
import java.util.HashMap;

import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.Panel;
import org.adempiere.webui.event.ActionEvent;
import org.adempiere.webui.event.ActionListener;
import org.compiere.model.MPOSKey;
import org.compiere.model.MPOSKeyLayout;
import org.compiere.print.MPrintColor;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
/**
 * Button panel supporting multiple linked layouts
 * @author Paul Bowden
 * Adaxa Pty Ltd
 *
 */
public class WPosKeyPanel extends Panel implements ActionListener, EventListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1773720355288801510L;


	/**
	 * 	Constructor
	 */
	public WPosKeyPanel (int C_POSKeyLayout_ID, PosKeyListener caller, String m_txtCalc, boolean keyBoardType)
	{
		if (C_POSKeyLayout_ID == 0)
			return;
		setHeight("100%");
		setWidth("100%");
		status = false;
		this.keyBoardType=keyBoardType;
		appendChild(createPanel(C_POSKeyLayout_ID, m_txtCalc));
		currentLayout = C_POSKeyLayout_ID;
		
		this.caller = caller;
	}	//	PosSubFunctionKeys
	
	/** Map of map of keys */
	private HashMap<Integer, HashMap<Integer, MPOSKey>> keymap = new HashMap<Integer, HashMap<Integer,MPOSKey>>();
	/** Currently displayed layout	*/
	int currentLayout;
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(WPosKeyPanel.class);
	/** Caller			*/
	private PosKeyListener caller;
	/** Status Panel */
	private boolean status;
	private Panel ABC_SubCard;
	private Panel abc_SubCard;
	boolean keyBoardType; 
	public Panel createPanel(int C_POSKeyLayout_ID, String m_txtCalc){
		Panel card = new Panel();
		card.setWidth("100%");
		MPOSKeyLayout keyLayout = MPOSKeyLayout.get(Env.getCtx(), C_POSKeyLayout_ID);
		
		if(abc_SubCard==null) {
			abc_SubCard = createButton(C_POSKeyLayout_ID, m_txtCalc);
			card.appendChild(abc_SubCard);
		}
		if (keyLayout.get_ID() == 0)
			return null;
		MPOSKey[] keys = keyLayout.getKeys(false);
		
		//	Content
		for (MPOSKey key :  keys)
		{
			if ( key.getSubKeyLayout_ID() > 0 )
			{
				if(ABC_SubCard == null){
					ABC_SubCard = createButton(key.getSubKeyLayout_ID(), m_txtCalc);
				}
				if ( ABC_SubCard != null  ){
					if(status==false) {
						card.appendChild(ABC_SubCard);
						ABC_SubCard.setVisible(status);
						ABC_SubCard.setContext(""+key.getC_POSKey_ID());
						status=true;
					}
				}
					card.appendChild(ABC_SubCard);
			}
		}
		return card;
	}
	
	/**
	 * @return
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
			button.setStyle("float:left; text-align:center; margin:1% 1%; Background-color:rgb("+keyColor.getRed()+","+keyColor.getGreen()+","+keyColor.getBlue()+"); border: 2px outset #CCC;");
			button.setHeight("55px");
			button.setWidth("55px");

			button.setAction("onClick : text_action.clearAlll('" +  m_txtCalc + "', '" + key.getText() + "', '" + this.keyBoardType + "')");
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
			button.setStyle("float:left; text-align:center; margin:1% 1%;  border: 2px outset #CCC;");
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
		// TODO Auto-generated method stub
		String action = event.getTarget().getId();

		HashMap<Integer, MPOSKey> currentKeymap = keymap.get(currentLayout);

		try
		{
			int C_POSKey_ID = Integer.parseInt(action);
			MPOSKey key = currentKeymap.get(C_POSKey_ID);
			if ( key.getSubKeyLayout_ID() > 0 )
			{
				currentLayout = key.getSubKeyLayout_ID();
				if(ABC_SubCard.getContext().equals(event.getTarget().getId())){
					ABC_SubCard.setVisible(true);
					abc_SubCard.setVisible(false);
				}
				else {
					ABC_SubCard.setVisible(false);
					abc_SubCard.setVisible(true);
				}
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

	@Override
	public void actionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub
		
	}

}
