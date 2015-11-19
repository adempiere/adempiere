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

import java.awt.event.KeyEvent;
import java.math.BigDecimal;

import javax.swing.KeyStroke;

import org.adempiere.pos.service.I_POSPanel;
import org.adempiere.webui.component.Button;
import org.adempiere.webui.component.Grid;
import org.adempiere.webui.component.GridFactory;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.Row;
import org.adempiere.webui.component.Rows;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zkex.zul.North;


/**
 * Button panel Up Down
 * @author Mario Calderon, mario.calderon@westfalia-it.com, Systemhaus Westfalia, http://www.westfalia-it.com
 * @author Raul Muñoz, rmunoz@erpcya.com, ERPCYA http://www.erpcya.com
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 */
public class WPOSUpDownPanel extends WPOSSubPanel implements I_POSPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -21082207371857594L;

	public WPOSUpDownPanel(WPOS posPanel) {
		super(posPanel);
	}

	/** Buttons Controls  		*/
	private Button 			f_bUp;
	private Button 			f_bDown;
	private Button 			f_bDelete;
	private Button 			f_bMinus;
	private Button 			f_bPlus;
	private POSNumberBox	f_Quantity;
	private POSNumberBox	f_Price;		 			

	private final String ACTION_UP       	= "Previous";
	private final String ACTION_DOWN  		= "Next";
	private final BigDecimal ACTION_QUANTITY    =  Env.ONE;

	@Override
	protected void init() {

		Grid LayoutButton = GridFactory.newGridLayout();
		Rows rows = null;
		Row row = null;	
		North north = new North();
		appendChild(north);
		
		north.appendChild(LayoutButton);
		LayoutButton.setWidth("100%");
		LayoutButton.setHeight("100%");
		rows = LayoutButton.newRows();
		LayoutButton.setStyle("border:none");
		row = rows.newRow();
		row.setHeight("55px");

		f_bUp = createButtonAction(ACTION_UP, KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0));
		f_bUp.setTooltiptext(Msg.translate(m_ctx, "Previous"));
		row.appendChild (f_bUp);
		f_bDown = createButtonAction(ACTION_DOWN, KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0));
		f_bDown.setTooltiptext(Msg.translate(m_ctx, "Next"));
		row.appendChild (f_bDown);
		
		f_bDelete = createButtonAction("Cancel", null);
		row.appendChild (f_bDelete);
		
		//
		f_bMinus = createButtonAction("Minus", null);
		row.appendChild(f_bMinus);
		
		Label qtyLabel = new Label(Msg.translate(Env.getCtx(), "QtyOrdered"));
		row.appendChild(qtyLabel);

		f_Quantity = new POSNumberBox(false);
		row.appendChild(f_Quantity);
		f_Quantity.addEventListener(Events.ON_CHANGE, this);
		f_Quantity.setStyle("display: inline;width:100px;height:30px;Font-size:medium;");
		//
		f_bPlus = createButtonAction("Plus", null);
		row.appendChild(f_bPlus);
		
		Label priceLabel = new Label(Msg.translate(Env.getCtx(), "PriceActual"));
		row.appendChild(priceLabel);
		
		f_Price = new POSNumberBox(false);
		row.appendChild(f_Price);
		f_Price.addEventListener(Events.ON_CHANGE, this);
		f_Price.setStyle("display: inline;width:100px;height:30px;Font-size:medium;");
		
		changeStatus(false);
	}
	
	@Override
	public void onEvent(Event e) throws Exception {
		if (e.getTarget().equals(f_bUp)){
			v_POSPanel.moveUp();
			return;
		}
		else if (e.getTarget().equals(f_bDown)){
			v_POSPanel.moveDown();
			return;
		}
		if (e.getTarget().equals(f_bMinus)){
			f_Quantity.setValue(f_Quantity.getValue().subtract(ACTION_QUANTITY));
		}
		else if (e.getTarget().equals(f_bPlus)){
			f_Quantity.setValue(f_Quantity.getValue().add(ACTION_QUANTITY));
		}
		else if (e.getTarget().equals(f_bDelete)){
			f_Quantity.setValue(0.0);
		}

		v_POSPanel.setQty(f_Quantity.getValue());
		v_POSPanel.setPrice(f_Price.getValue());
		v_POSPanel.updateLineTable();
	}

	/**
	 * Change Status 
	 * @param p_Status
	 */
	public void changeStatus(boolean p_Status) {
		f_Quantity.setEnabled(p_Status);
		f_Price.setEnabled(p_Status);
		f_bDelete.setEnabled(p_Status);
		f_bPlus.setEnabled(p_Status);
		f_bMinus.setEnabled(p_Status);
	}
	
	@Override
	public void refreshPanel() {
		if(v_POSPanel.hasLines()){
			f_bDown.setEnabled(true);
			f_bUp.setEnabled(true);
		} else {
			f_bDown.setEnabled(false);
			f_bUp.setEnabled(false);
		}
	}

	@Override
	public void moveUp() {
		
	}

	@Override
	public void moveDown() {
		
	}

	@Override
	public String validatePanel() {
		return null;
	}

	@Override
	public void changeViewPanel() {
		if(v_POSPanel.getQty().compareTo(Env.ZERO) == 0)
			changeStatus(false);
		else
			changeStatus(true);
		f_Quantity.setValue(v_POSPanel.getQty().doubleValue());
		f_Price.setValue(v_POSPanel.getPrice().doubleValue());
	}

}
