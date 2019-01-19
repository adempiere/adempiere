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

import java.util.HashMap;

import org.adempiere.webui.apps.AEnv;
import org.adempiere.webui.component.Borderlayout;
import org.adempiere.webui.component.ConfirmPanel;
import org.adempiere.webui.component.Grid;
import org.adempiere.webui.component.GridFactory;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.Panel;
import org.adempiere.webui.component.Row;
import org.adempiere.webui.component.Rows;
import org.adempiere.webui.component.Textbox;
import org.adempiere.webui.component.Window;
import org.compiere.model.MPOSKey;
import org.compiere.model.MPOSKeyLayout;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zkex.zul.Center;
import org.zkoss.zkex.zul.North;
import org.zkoss.zkex.zul.South;
import org.zkoss.zul.Doublebox;

/**
 * On Screen Keyboard
 * @author Mario Calderon, mario.calderon@westfalia-it.com, Systemhaus Westfalia, http://www.westfalia-it.com
 * @author Raul Muñoz, rmunoz@erpcya.com, ERPCYA http://www.erpcya.com
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 */
public class WPOSKeyboard extends Window implements POSKeyListener, EventListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3296839634889851637L;
		
	/**
	 * 	Constructor
	 *	@param posPanel POS Panel
	 */
	public WPOSKeyboard(WPOS posPanel, int keyLayoutId) {
		super();
		keylayout = MPOSKeyLayout.get(Env.getCtx(), keyLayoutId);
		if(keylayout.getPOSKeyLayoutType() != null){
			keyBoardType = keylayout.getPOSKeyLayoutType().equals(MPOSKeyLayout.POSKEYLAYOUTTYPE_Numberpad);
			init( keyLayoutId );
			// add listener on 'ENTER' key for the login window
	        addEventListener(Events.ON_OK,this);
			// add listener on 'ESC' key for the login window
	        addEventListener(Events.ON_CANCEL,this);
		}
	}

	/**
	 * 	Constructor
	 *	@param posPanel POS Panel
	 *	@param int Key Layout ID
	 *  @param Field
	 */
	public WPOSKeyboard(Window parent, WPOS posPanel, int keyLayoutId, WPOSTextField field) {
		super();
		setPosTextField(field);
		setTitle(Msg.translate(Env.getCtx(), "M_Product_ID"));
		keylayout = MPOSKeyLayout.get(posPanel.getCtx(), keyLayoutId);
		if(keylayout.getPOSKeyLayoutType() != null){
			keyBoardType = keylayout.getPOSKeyLayoutType().equals(MPOSKeyLayout.POSKEYLAYOUTTYPE_Numberpad);
			init( keyLayoutId );
			// add listener on 'ENTER' key for the login window
	        addEventListener(Events.ON_OK,this);
			// add listener on 'ESC' key for the login window
	        addEventListener(Events.ON_CANCEL,this);
			AEnv.showCenterWindow(parent, this);
		}
		
	}
		
	/** Fields 								*/
	private WPOSTextField 					field;
	private Textbox 						tfield;
	private Doublebox 						dfield;
	private Textbox 						txtCalc = new Textbox();
	private Label 							lfield;
	/** key Layout							*/
	private MPOSKeyLayout 					keylayout;
	private boolean 						keyBoardType;
	private HashMap<Integer, MPOSKey> 		keys;
	private boolean 						isCancel;

		
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(WPOSKeyboard.class);
	
	/**
	 * 	Initialize
	 * @param startText 
	 * @param POSKeyLayout_ID 
	 */
	public void init(int POSKeyLayout_ID )
	{
		Panel panel = new Panel();
		appendChild(panel);

		//	Content
		Panel mainPanel = new Panel();
		Borderlayout mainLayout = new Borderlayout();
		Grid productLayout = GridFactory.newGridLayout();
		appendChild(panel);
		
		//	North
		Panel northPanel = new Panel();
		mainPanel.appendChild(mainLayout);
		mainPanel.setStyle("width: 100%; height: 100%; padding: 0; margin: 0");
		mainLayout.setHeight("100%");
		mainLayout.setWidth("100%");
		Center center = new Center();
		//
		North north = new North();
		north.setStyle("border: none");
		mainLayout.appendChild(north);
		north.appendChild(northPanel);
		northPanel.appendChild(productLayout);
		productLayout.setWidth("100%");
		appendChild(mainPanel);
		Rows rows = null;
		Row row = null;
		rows = productLayout.newRows();
		row = rows.newRow();

		String txtCalcId = txtCalc.getId();
		row.appendChild(txtCalc);
		txtCalc.setName("number");
		txtCalc.setWidth("92%");
		WPOSKeyPanel keys = new WPOSKeyPanel(POSKeyLayout_ID, this, txtCalcId, keyBoardType);
		center = new Center();
		center.setStyle("border: none");
		keys.setWidth("100%");
		keys.setHeight("99%");

		center.appendChild(keys);
		mainLayout.appendChild(center);
		South south = new South();
		
		ConfirmPanel confirm = new ConfirmPanel(true, false, true, false, false, false, false);
		confirm.addActionListener(this);
		south.appendChild(confirm);
		mainLayout.appendChild(south);
		
		
	}	//	init
	
	/**
	 * 	Dispose - Free Resources
	 */
	public void close()
	{
		if (keys != null)
		{
			keys.clear();
			keys = null;
			field = null;
			txtCalc = null;
			dfield = null;
		}
		onClose();
	}	//	dispose
	
	@Override
	public void keyReturned(MPOSKey key) {
		
	}
	/** 
	 * Set Pos Text Field
	 * @param posTextField
	 * @return void
	 */
	public void setPosTextField(Textbox posTextField) {
		
		tfield = posTextField;
		tfield.setType(posTextField.getType());
		txtCalc.setText(tfield.getText());
		txtCalc.setValue(tfield.getValue());
		txtCalc.setType(posTextField.getType());
		
	}
	
	/**
	 * Set Pos Text Field
	 * @param posTextField
	 * @return void
	 */
	public void setPosTextField(WPOSTextField posTextField) {
		field = posTextField;
		field.setType(posTextField.getType());
		txtCalc.setText(field.getText());
		txtCalc.setValue(field.getValue());
		txtCalc.setType(posTextField.getType());
		
	}
	/**
	 * Set Pos Text Field
	 * @param posTextField
	 * @return void
	 */
	public void setPosTextField(Label posTextField) {
		
		lfield = posTextField;
		txtCalc.setText(lfield.getValue());
		txtCalc.setValue(lfield.getValue());
		
	}
	
	/**
	 * Set Pos Text Field
	 * @param posTextField
	 * @return void
	 */
	public void setPosTextField(Doublebox posTextField) {
		
		dfield = posTextField; 
		txtCalc.setText(dfield.getText());
		txtCalc.setValue(dfield.getValue().toString());
		
	}

	@Override
	public void onEvent(Event e) throws Exception {
		// check that 'ENTER' key is pressed
        if (Events.ON_OK.equals(e.getName())) {
    	   closeWindow();
   		} 
        // check that 'ESC' key is pressed
        else if (Events.ON_CANCEL.equals(e.getName())) {
        	isCancel = true;
			close();
    	}
		String action = e.getTarget().getId();
		if (action == null || action.length() == 0)
			return;
		else if ( action.equals(ConfirmPanel.A_RESET)) {
			if ( keylayout.getPOSKeyLayoutType().equals(MPOSKeyLayout.POSKEYLAYOUTTYPE_Numberpad))
				txtCalc.setText("0");
			else
				txtCalc.setText("");
		}
		else if ( action.equals(ConfirmPanel.A_CANCEL))	{
			isCancel = true;
			close();
		}
		else if (action.equals(ConfirmPanel.A_OK)) {
			closeWindow();
		}
		log.info( "PosSubBasicKeys - actionPerformed: " + action);
	}
	
	/**
	 * Close Window
	 * @return void
	 */
	private void closeWindow() {
		isCancel = false;
		if(txtCalc.getValue().length() > 0) {
			if(dfield != null)
				dfield.setText(txtCalc.getValue());
			else if (field != null)
				field.setText(txtCalc.getValue());
			else if(lfield != null)
				lfield.setText(txtCalc.getValue());
			else 
				tfield.setText(txtCalc.getValue());
		}
		close();
	}
	
	/**
	 * Get Value
	 * @return void
	 */
	public void getValue(){
		if(dfield != null)
			dfield.setText(txtCalc.getValue());
		else if (field != null)
			field.setText(txtCalc.getValue());
		else if (lfield != null)
			lfield.setText(txtCalc.getValue());
		else 
			tfield.setText(txtCalc.getValue());
	}
	
	/** 
	 * Is Cancel
	 * @return
	 * @return boolean
	 */
	public boolean isCancel(){
		return isCancel;
	}

}	
