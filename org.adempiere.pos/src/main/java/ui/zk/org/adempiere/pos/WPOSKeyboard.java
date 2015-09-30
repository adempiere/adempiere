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

import java.math.BigDecimal;

import org.adempiere.webui.component.Textbox;

import java.util.HashMap;

import org.adempiere.webui.apps.AEnv;
import org.adempiere.webui.component.Borderlayout;
import org.adempiere.webui.component.Button;
import org.adempiere.webui.component.ConfirmPanel;
import org.adempiere.webui.component.Grid;
import org.adempiere.webui.component.GridFactory;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.Panel;
import org.adempiere.webui.component.Row;
import org.adempiere.webui.component.Rows;
import org.adempiere.webui.component.Window;
import org.compiere.model.MPOSKey;
import org.compiere.model.MPOSKeyLayout;
import org.compiere.pos.POSKeyboard;
import org.compiere.pos.PosKeyListener;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.InputEvent;
import org.zkoss.zkex.zul.Center;
import org.zkoss.zkex.zul.North;
import org.zkoss.zkex.zul.South;
import org.zkoss.zul.Doublebox;


/**
 *	On Screen Keyboard
 * @author Raul Muñoz 19/03/2015, 12:50
 *	
 */
public class WPOSKeyboard extends Window implements PosKeyListener, EventListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3296839634889851637L;
		

		public static final int KEYBOARD_NUMERIC = 1;
		public static final int KEYBOARD_NUMERIC_CASHOUT = 2;
		private WPosTextField field;
		private Textbox tfield;
		private Doublebox dfield;
		private Label lfield;
		private MPOSKeyLayout keylayout;
		private boolean keyBoardType;
		private Textbox txtCalc = new Textbox();
		private HashMap<Integer, MPOSKey> keys;
		private int typeKeyboard = KEYBOARD_NUMERIC;
		private BigDecimal cashOut;
		private Button bCashOut;
		private boolean isCancel;
		
	/**
	 * 	Constructor
	 *	@param posPanel POS Panel
	 */
	public WPOSKeyboard (WPOS posPanel, int C_POSKeyLayout_ID, WPosTextField field, String title)
	{
		this(posPanel, C_POSKeyLayout_ID);
		setTitle(title);
		setPosTextField(field);
	}
	
	public WPOSKeyboard(WPOS posPanel, int keyLayoutId) {
		super();
		keylayout = MPOSKeyLayout.get(posPanel.getCtx(), keyLayoutId);
		keyBoardType = keylayout.getPOSKeyLayoutType().equals(MPOSKeyLayout.POSKEYLAYOUTTYPE_Numberpad);
		init( keyLayoutId );
	}
	
	public WPOSKeyboard(Window parent, WPOS posPanel, int keyLayoutId, WPosTextField field) {
		super();
		setPosTextField(field);
		setTitle(Msg.translate(Env.getCtx(), "M_Product_ID"));
		keylayout = MPOSKeyLayout.get(posPanel.getCtx(), keyLayoutId);
		keyBoardType = keylayout.getPOSKeyLayoutType().equals(MPOSKeyLayout.POSKEYLAYOUTTYPE_Numberpad);
		init( keyLayoutId );
		AEnv.showCenterWindow(parent, this);
	}
	public WPOSKeyboard(Panel parent, int keyLayoutId, WPosTextField field) {
		super();
		setWidth("280px");
		setHeight("320px");
		setPosTextField(field);
		keylayout = MPOSKeyLayout.get(Env.getCtx(), keyLayoutId);
		keyBoardType = keylayout.getPOSKeyLayoutType().equals(MPOSKeyLayout.POSKEYLAYOUTTYPE_Numberpad);
		init( keyLayoutId );
		
	}
	public WPOSKeyboard(int keyLayoutId, Label field, int typeKeyboard, Label balance) {
		super();
		setWidth("280px");
		setHeight("320px");
		setPosTextField(field);
		setTypeKeyboard(typeKeyboard);
		cashOut = new BigDecimal(balance.getValue());
		keylayout = MPOSKeyLayout.get(Env.getCtx(), keyLayoutId);
		keyBoardType = keylayout.getPOSKeyLayoutType().equals(MPOSKeyLayout.POSKEYLAYOUTTYPE_Numberpad);
		
		init( keyLayoutId );
		
	}
		
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
		WPosKeyPanel keys = new WPosKeyPanel(POSKeyLayout_ID, this, txtCalcId, keyBoardType);
		center = new Center();
		center.setStyle("border: none");
		keys.setWidth("100%");
		keys.setHeight("99%");

		center.appendChild(keys);
		mainLayout.appendChild(center);
		South south = new South();
		if(typeKeyboard == KEYBOARD_NUMERIC){
			ConfirmPanel confirm = new ConfirmPanel(true, false, true, false, false, false, false);
			confirm.addActionListener(this);
			south.appendChild(confirm);
		}
		else if(typeKeyboard == KEYBOARD_NUMERIC_CASHOUT){
			bCashOut = new Button(Msg.translate(Env.getCtx(), "CashOut"));
			bCashOut.addActionListener(this);
			bCashOut.setWidth("255px");
			south.appendChild(bCashOut);
		}
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

	public void onChanging(InputEvent e){
	}
	
	@Override
	public void keyReturned(MPOSKey key) {
		
	}
	public void setPosTextField(Textbox posTextField) {
		
		tfield = posTextField;
		txtCalc.setText(tfield.getText());
		txtCalc.setValue(tfield.getValue());
		
	}
	public void setPosTextField(WPosTextField posTextField) {
		
		field = posTextField;
		txtCalc.setText(field.getText());
		txtCalc.setValue(field.getValue());
		
	}
	public void setPosTextField(Label posTextField) {
		
		lfield = posTextField;
		txtCalc.setText(lfield.getValue());
		txtCalc.setValue(lfield.getValue());
		
	}
	public void setPosTextField(Doublebox posTextField) {
		
		dfield = posTextField; 
		txtCalc.setText(dfield.getText());
		txtCalc.setValue(dfield.getValue().toString());
		
	}

	

	@Override
	public void onEvent(Event e) throws Exception {
	
		String action = e.getTarget().getId();
		if(e.getTarget().equals(bCashOut)){
				(txtCalc).setText("" + getCashOut());
		}
		if (action == null || action.length() == 0)
			return;
		else if ( action.equals(ConfirmPanel.A_RESET))
		{
			if ( keylayout.getPOSKeyLayoutType().equals(MPOSKeyLayout.POSKEYLAYOUTTYPE_Numberpad))
				txtCalc.setText("0");
			else
				txtCalc.setText("");
		}
		else if ( action.equals(ConfirmPanel.A_CANCEL))
		{
			isCancel = true;
			close();
		}
		else if (action.equals(ConfirmPanel.A_OK))
		{
			isCancel = false;
			if(dfield != null)
				dfield.setText(txtCalc.getValue());
			else if (field != null)
				field.setText(txtCalc.getValue());
			else if(lfield != null)
				lfield.setText(txtCalc.getValue());
			else 
				tfield.setText(txtCalc.getValue());
			close();
		}
		log.info( "PosSubBasicKeys - actionPerformed: " + action);
	}
	public void getMount(){
		if(dfield != null)
			dfield.setText(txtCalc.getValue());
		else if (field != null)
			field.setText(txtCalc.getValue());
		else if (lfield != null)
			lfield.setText(txtCalc.getValue());
		else 
			tfield.setText(txtCalc.getValue());
	}
	public int getTypeKeyboard() {
		return typeKeyboard;
	}

	public void setTypeKeyboard(int typeKeyboard) {
		this.typeKeyboard = typeKeyboard;
	}
	public void setCashOut(BigDecimal cashOut) {
		this.cashOut = cashOut;
	}

	public boolean isCancel(){
		return isCancel;
	}
	public BigDecimal getCashOut() {
		return cashOut;
	}
}	
