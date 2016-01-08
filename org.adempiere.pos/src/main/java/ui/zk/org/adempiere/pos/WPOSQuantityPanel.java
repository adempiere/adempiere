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
 * 
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
import org.compiere.model.MOrder;
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
 * @author victor.perez@e-evolution.com , http://www.e-evolution.com
 */
public class WPOSQuantityPanel extends WPOSSubPanel implements I_POSPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -21082207371857594L;

	public WPOSQuantityPanel(WPOS posPanel) {
		super(posPanel);
	}

	/** Buttons Controls  		*/
	private Button 			buttonUp;
	private Button 			buttonDown;
	private Button 			buttonDelete;
	private Button 			buttonMinus;
	private Button 			buttonPlus;
	private POSNumberBox 	fieldQuantity;
	private POSNumberBox 	fieldPrice;
	private POSNumberBox	fieldDiscountPercentage;

	private final String ACTION_UP       	= "Previous";
	private final String ACTION_DOWN  		= "Next";
	private final BigDecimal CurrentQuantity =  Env.ONE;

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
		
		buttonDelete = createButtonAction("Cancel", null);
		row.appendChild (buttonDelete);
		
		buttonPlus = createButtonAction("Plus", null);
		row.appendChild(buttonPlus);
		
		buttonMinus = createButtonAction("Minus", null);
		row.appendChild(buttonMinus);

		buttonUp = createButtonAction(ACTION_UP, KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0));
		buttonUp.setTooltiptext(Msg.translate(ctx, "Previous"));
		row.appendChild (buttonUp);
		
		buttonDown = createButtonAction(ACTION_DOWN, KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0));
		buttonDown.setTooltiptext(Msg.translate(ctx, "Next"));
		row.appendChild (buttonDown);
		
		Label qtyLabel = new Label(Msg.translate(Env.getCtx(), "QtyOrdered"));
		row.appendChild(qtyLabel);

		fieldQuantity = new POSNumberBox(false);
		row.appendChild(fieldQuantity);
		fieldQuantity.addEventListener(Events.ON_CHANGE, this);
		fieldQuantity.setStyle("display: inline;width:100px;height:30px;Font-size:medium;");
		
		Label priceLabel = new Label(Msg.translate(Env.getCtx(), "PriceActual"));
		row.appendChild(priceLabel);
		
		fieldPrice = new POSNumberBox(false);
		row.appendChild(fieldPrice);
		fieldPrice.addEventListener(Events.ON_CHANGE, this);
		fieldPrice.setStyle("display: inline;width:100px;height:30px;Font-size:medium;");
		
		Label priceDiscount = new Label(Msg.translate(Env.getCtx(), "Discount"));
		row.appendChild(priceDiscount);
		
		fieldDiscountPercentage = new POSNumberBox(false);
		row.appendChild(fieldDiscountPercentage);
		fieldDiscountPercentage.addEventListener(Events.ON_CHANGE, this);
		fieldDiscountPercentage.setStyle("display: inline;width:100px;height:30px;Font-size:medium;");
		
		changeStatus(false);
	}
	
	@Override
	public void onEvent(Event e) throws Exception {
		if (e.getTarget().equals(buttonUp)){
			posPanel.moveUp();
			return;
		}
		else if (e.getTarget().equals(buttonDown)){
			posPanel.moveDown();
			return;
		}
		if (e.getTarget().equals(buttonMinus)){
			fieldQuantity.setValue(fieldQuantity.getValue().subtract(CurrentQuantity));
		}
		else if (e.getTarget().equals(buttonPlus)){
			fieldQuantity.setValue(fieldQuantity.getValue().add(CurrentQuantity));
		}
		else if (e.getTarget().equals(buttonDelete)){
			posPanel.deleteLine(posPanel.getC_OrderLine_ID());
			fieldQuantity.setValue(0.0);
			fieldPrice.setValue(0.0);
			fieldDiscountPercentage.setValue(0.0);
		}

		BigDecimal quantity           = (BigDecimal) fieldQuantity.getValue();
		BigDecimal price              = (BigDecimal) fieldPrice.getValue();
		BigDecimal discountPercentage =  (BigDecimal) fieldDiscountPercentage.getValue();
		
		if ((posPanel.getQty().compareTo(quantity) != 0 && fieldQuantity.hasChanged() 
				&& (e.getTarget().equals(fieldQuantity) || e.getTarget().equals(buttonDelete) || e.getTarget().equals(buttonPlus) || e.getTarget().equals(buttonMinus)))
		|| 	(posPanel.getPrice().compareTo(price) != 0 && fieldPrice.hasChanged() && e.getTarget().equals(fieldPrice))
		|| 	(posPanel.getDiscountPercentage().compareTo(discountPercentage) != 0 && fieldDiscountPercentage.hasChanged() && e.getTarget().equals(fieldDiscountPercentage))) {
			posPanel.setQuantity((BigDecimal) fieldQuantity.getValue());
			posPanel.setPrice((BigDecimal) fieldPrice.getValue());
			posPanel.setDiscountPercentage((BigDecimal) fieldDiscountPercentage.getValue());
			posPanel.changeViewQuantityPanel();
			posPanel.updateLineTable();
		}
	}

	/**
	 * Change Status 
	 * @param p_Status
	 */
	public void changeStatus(boolean p_Status) {
		fieldQuantity.setEnabled(p_Status);
		fieldPrice.setEnabled(p_Status);
		fieldDiscountPercentage.setEnabled(p_Status);
		buttonDelete.setEnabled(p_Status);
		buttonPlus.setEnabled(p_Status);
		buttonMinus.setEnabled(p_Status);
	}
	
	@Override
	public void refreshPanel() {
		if(posPanel.hasLines()){
			buttonDown.setEnabled(true);
			buttonUp.setEnabled(true);
			
			// Only enable buttons if status==(drafted or in progress)
			if(posPanel.getM_Order().getDocStatus().compareToIgnoreCase(MOrder.STATUS_Drafted)==0 || 
			   posPanel.getM_Order().getDocStatus().compareToIgnoreCase(MOrder.STATUS_InProgress)==0 ){
				buttonDelete.setEnabled(true);
				buttonPlus.setEnabled(true);
				buttonMinus.setEnabled(true);
				fieldPrice.setEnabled(true);
				fieldQuantity.setEnabled(true);
				fieldDiscountPercentage.setEnabled(true);
			}else {
				buttonDelete.setEnabled(false);
				buttonPlus.setEnabled(false);
				buttonMinus.setEnabled(false);
				fieldPrice.setEnabled(false);
				fieldQuantity.setEnabled(false);
				fieldDiscountPercentage.setEnabled(false);
			}
		} else {
			buttonDown.setEnabled(false);
			buttonUp.setEnabled(false);
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
		if(posPanel.getQty().compareTo(Env.ZERO) == 0)
			changeStatus(false);
		else
			changeStatus(true);
		fieldQuantity.setValue(posPanel.getQty().doubleValue());
		fieldPrice.setValue(posPanel.getPrice().doubleValue());
		fieldDiscountPercentage.setValue(posPanel.getDiscountPercentage().doubleValue());
	}

	public void resetPanel() {
		fieldQuantity.setValue(0);
		fieldPrice.setValue(0);
		fieldDiscountPercentage.setValue(0);
		buttonDown.setEnabled(false);
		buttonUp.setEnabled(false);
		buttonDelete.setEnabled(false);
		buttonPlus.setEnabled(false);
		buttonMinus.setEnabled(false);
		fieldPrice.setEnabled(false);
		fieldQuantity.setEnabled(false);
		fieldDiscountPercentage.setEnabled(false);
	}

}
