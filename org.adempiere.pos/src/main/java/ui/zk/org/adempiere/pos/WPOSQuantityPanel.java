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

import java.math.BigDecimal;
import java.text.DecimalFormat;

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.pos.service.POSPanelInterface;
import org.adempiere.webui.apps.AEnv;
import org.adempiere.webui.component.Button;
import org.adempiere.webui.component.Grid;
import org.adempiere.webui.component.GridFactory;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.Panel;
import org.adempiere.webui.component.Row;
import org.adempiere.webui.component.Rows;
import org.adempiere.webui.window.FDialog;
import org.compiere.model.MOrder;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.zkforge.keylistener.Keylistener;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.KeyEvent;


/**
 * Button panel Up Down
 * @author Mario Calderon, mario.calderon@westfalia-it.com, Systemhaus Westfalia, http://www.westfalia-it.com
 * @author Raul Muñoz, rmunoz@erpcya.com, ERPCYA http://www.erpcya.com
 *  <li><a href="https://github.com/adempiere/adempiere/issues/533">
 *           BF/FR [ 533 ] Update Fields when selected line</a> 
 *  <li><a href="https://github.com/adempiere/adempiere/issues/530">
 *           BF/FR [ 530 ] Added Validation in fields quantity, price and Discount</a>
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 * @author victor.perez@e-evolution.com , http://www.e-evolution.com
 * 
 */
public class WPOSQuantityPanel extends WPOSSubPanel implements POSPanelInterface {

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
	private Button 			buttonPlus;
	private Button 			buttonMinus;
	private Button 			buttonScales;
	private POSNumberBox 	fieldQuantity;
	private POSNumberBox 	fieldPrice;
	private POSNumberBox	fieldDiscountPercentage;

	private final String ACTION_UP       	= "Previous";
	private final String ACTION_DOWN  		= "Next";
	private final BigDecimal CurrentQuantity =  Env.ONE;

	private Panel 		parameterPanel;
	@Override
	protected void init() {

		Grid LayoutButton = GridFactory.newGridLayout();

		Rows rows = null;
		Row row = null;	

		parameterPanel = new Panel();
		parameterPanel.appendChild(LayoutButton);
		LayoutButton.setWidth("100%");
		LayoutButton.setHeight("100%");

		rows = LayoutButton.newRows();
		row = rows.newRow();
		row.setHeight("55px");
		buttonDelete = createButtonAction("Cancel", "Ctrl+F3");
		buttonDelete.setTooltiptext("Ctrl+F3-"+Msg.translate(ctx, "DeleteLine"));
		buttonDelete.addActionListener(this);
		row.appendChild (buttonDelete);
		
		buttonPlus = createButtonAction("Plus", "Ctrl+1");
		buttonPlus.setTooltiptext("Ctrl+0-"+Msg.translate(ctx, "add"));
		row.appendChild(buttonPlus);
		
		buttonMinus = createButtonAction("Minus", "Ctrl+0");
		row.appendChild(buttonMinus);

		buttonUp = createButtonAction(ACTION_UP, "Alt+Up");
		buttonUp.setTooltiptext("Alt+Up-"+Msg.translate(ctx, "Previous"));
		row.appendChild (buttonUp);
		
		buttonDown = createButtonAction(ACTION_DOWN, "Alt+Down");
		buttonDown.setTooltiptext("Alt+Down-"+Msg.translate(ctx, "Next"));
		row.appendChild (buttonDown);

		if (posPanel.isPresentElectronicScales()) {
			buttonScales = createButtonAction("Calculator", "Ctrl+W");
			buttonScales.setTooltiptext("ALT+down-" + Msg.translate(ctx, "Calculator"));
			row.appendChild(buttonScales);
//			buttonScales.addActionListener(posPanel.getScalesListener());
		}
		
		Label qtyLabel = new Label(Msg.translate(Env.getCtx(), "QtyOrdered"));
		row.appendChild(qtyLabel);

		fieldQuantity = new POSNumberBox(false);
		
		row.appendChild(fieldQuantity);
		fieldQuantity.addEventListener(Events.ON_OK, this);
		fieldQuantity.addEventListener(Events.ON_CHANGE, this);
		fieldQuantity.setStyle("display: inline;width:65px;height:30px;Font-size:medium;");

		Label priceLabel = new Label(Msg.translate(Env.getCtx(), "PriceActual"));
		row.appendChild(priceLabel);
		
		fieldPrice = new POSNumberBox(false);
    DecimalFormat format = DisplayType.getNumberFormat(DisplayType.Amount, AEnv.getLanguage(Env.getCtx()));
    fieldPrice.getDecimalbox().setFormat(format.toPattern());
    
		fieldPrice.setTooltiptext(Msg.translate(Env.getCtx(), "PriceActual"));
		row.appendChild(fieldPrice);
		if (!posPanel.isModifyPrice())
			fieldPrice.setEnabled(false);
		else {
			fieldPrice.addEventListener(Events.ON_OK, this);
			fieldPrice.addEventListener(Events.ON_CHANGE, this);
		}
		fieldPrice.setStyle("display: inline;width:70px;height:30px;Font-size:medium;");
		
		Label priceDiscount = new Label(Msg.translate(Env.getCtx(), "Discount"));
		row.appendChild(priceDiscount);

		fieldDiscountPercentage = new POSNumberBox(false);
		row.appendChild(fieldDiscountPercentage);
		fieldDiscountPercentage.setTooltiptext(Msg.translate(Env.getCtx(), "Discount"));
		if (!posPanel.isModifyPrice())
			fieldDiscountPercentage.setEnabled(false);
		else{
			fieldDiscountPercentage.addEventListener(Events.ON_OK, this);
			fieldDiscountPercentage.addEventListener(Events.ON_CHANGE, this);
		}
		fieldDiscountPercentage.setStyle("display: inline;width:70px;height:30px;Font-size:medium;");
		
		Keylistener keyListener = new Keylistener();
		fieldPrice.appendChild(keyListener);
    	keyListener.setCtrlKeys("@#up@#down^#f3^1^0");
    	keyListener.addEventListener(Events.ON_CTRL_KEY, posPanel);
    	keyListener.addEventListener(Events.ON_CTRL_KEY, this);
    	keyListener.setAutoBlur(false);
    	
		changeStatus(false);
	}
	
	@Override
	public void onEvent(Event e) throws Exception {
		try {
			if (Events.ON_CTRL_KEY.equals(e.getName())) {
				KeyEvent keyEvent = (KeyEvent) e;
				//Alt+up == 38
				if (keyEvent.getKeyCode() == 38 ) {
					posPanel.moveUp();
				}
				//Alt+down == 40
				if (keyEvent.getKeyCode() == 40 ) {
					posPanel.moveDown();
				}
				//ctrl+f3 == 114
				if (keyEvent.getKeyCode() == 114 ) {
					posPanel.deleteLine(posPanel.getC_OrderLine_ID());
					fieldQuantity.setValue(0.0);
					fieldPrice.setValue(0.0);
					fieldDiscountPercentage.setValue(0.0);
				}
				//ctrl+1 == 49
				if (keyEvent.getKeyCode() == 49 ) {
					fieldQuantity.setValue(fieldQuantity.getValue().add(CurrentQuantity));
				}
				//ctrl+0 == 48
				if (keyEvent.getKeyCode() == 48 ) {
					fieldQuantity.setValue(fieldQuantity.getValue().subtract(CurrentQuantity));
				}
			}
			if (e.getTarget().equals(buttonUp)){
				posPanel.moveUp();
				return;
			}
			else if (e.getTarget().equals(buttonDown)){
				posPanel.moveDown();
				return;
			}
			if (e.getTarget().equals(buttonMinus)){
				BigDecimal quantity = fieldQuantity.getValue().subtract(CurrentQuantity);
				if(quantity.compareTo(Env.ZERO) == 0) {
					if(posPanel.isUserPinValid()) {
						posPanel.setQty(quantity);
					}
				} else {
					posPanel.setQty(quantity);
				}
			}
			else if (e.getTarget().equals(buttonPlus)){
				posPanel.setQty(fieldQuantity.getValue().add(CurrentQuantity));
			}
			else if (e.getTarget().equals(buttonDelete)){
				if(posPanel.isUserPinValid()) {
					posPanel.deleteLine(posPanel.getC_OrderLine_ID());

					posPanel.updateLineTable();
					posPanel.refreshPanel();
					return;
				}
			}
			BigDecimal value = Env.ZERO;
			if(Events.ON_OK.equals(e.getName()) || Events.ON_CHANGE.equals(e.getName())) {
        
			  value = fieldQuantity.getValue();
        if(value == null)
          return;
        if(e.getTarget().equals(fieldQuantity.getDecimalbox())) {
          if(Events.ON_OK.equals(e.getName())){
            posPanel.setQty(value);
          }
          else if(posPanel.isAddQty() 
              || Events.ON_CHANGE.equals(e.getName())){
            //  Verify if it add or set
            if(posPanel.isAddQty()) {
              posPanel.setQtyAdded(value);
            } else {
              posPanel.setQty(value);
            }
          }
          
        }
      
        if (e.getTarget().equals(fieldPrice.getDecimalbox())) {
          value = fieldPrice.getValue();
          if(value == null)
            return;
          if(posPanel.isUserPinValid()) {
            posPanel.setPrice(value);
          }
        }
        else if ( e.getTarget().equals(fieldDiscountPercentage.getDecimalbox())) {
          if(posPanel.isUserPinValid()) {
            value = fieldDiscountPercentage.getValue();
            if(value == null)
              return;
            posPanel.setDiscountPercentage(value);
          }
        }
			}

			posPanel.updateLineTable();
			posPanel.refreshPanel();
			posPanel.changeViewPanel();
			posPanel.getMainFocus();
		} catch (AdempiereException exception) {
			FDialog.error(posPanel.getWindowNo(), this, exception.getLocalizedMessage());
		}
	}
	
	/**
	 * Change Status 
	 * @param status
	 */
	public void changeStatus(boolean status) {
		fieldQuantity.setEnabled(true);
		fieldPrice.setEnabled(true);
		fieldDiscountPercentage.setEnabled(true);
		buttonDelete.setEnabled(status);
		buttonPlus.setEnabled(status);
		buttonMinus.setEnabled(status);
		buttonDown.setEnabled(status);
		buttonUp.setEnabled(status);
	}
	
	@Override
	public void refreshPanel() {
		if(posPanel.hasLines()){
			buttonDown.setEnabled(true);
			buttonUp.setEnabled(true);
			
			// Only enable buttons if status==(drafted or in progress)
			if(posPanel.getOrder().getDocStatus().compareToIgnoreCase(MOrder.STATUS_Drafted)==0 || 
			   posPanel.getOrder().getDocStatus().compareToIgnoreCase(MOrder.STATUS_InProgress)==0 ){
				buttonDelete.setEnabled(true);
				buttonPlus.setEnabled(true);
				buttonMinus.setEnabled(true);

				if (posPanel.isPresentElectronicScales())
					buttonScales.setEnabled(true);
				else
					buttonScales.setVisible(false);

				fieldQuantity.setEnabled(true);
				if(posPanel.isReturnMaterial() ){
					fieldPrice.setEnabled(false);
					fieldDiscountPercentage.setEnabled(false);					
				} else {
					fieldPrice.setEnabled(true);
					fieldDiscountPercentage.setEnabled(true);					
				}
			}else {
				buttonDelete.setEnabled(false);
				buttonPlus.setEnabled(false);
				buttonMinus.setEnabled(false);

				if (posPanel.isPresentElectronicScales())
					buttonScales.setEnabled(false);
				else
					buttonScales.setVisible(false);

				fieldPrice.setEnabled(false);
				fieldQuantity.setEnabled(false);
				fieldDiscountPercentage.setEnabled(false);
			}
		} else {
			buttonDown.setEnabled(false);
			buttonUp.setEnabled(false);
		}
		changeViewPanel();
	}

	@Override
	public void moveUp() {
		
	}

	@Override
	public void moveDown() {
	}

	@Override
	public String validatePayment() {
		return null;
	}

	@Override
	public void changeViewPanel() {
		if(posPanel.getQty().compareTo(Env.ZERO) == 0)
			changeStatus(false);
		else
			changeStatus(true);
		fieldQuantity.setValue(posPanel.getQty());
		fieldPrice.setValue(posPanel.getPrice());
		fieldDiscountPercentage.setValue(posPanel.getDiscountPercentage());
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
		if (posPanel.isPresentElectronicScales())
			buttonScales.setEnabled(false);
		fieldPrice.setEnabled(false);
		fieldQuantity.setEnabled(false);
		fieldDiscountPercentage.setEnabled(false);
	}

	public void setQuantity(BigDecimal value) {
		fieldQuantity.setValue(value);
		fieldQuantity.focus();
	}

	public void requestFocus() {
		fieldQuantity.focus();
	}

	/**
	 * Get Panel 
	 * @return Panel
	 */
	public Panel getPanel(){
		return parameterPanel;
	}

}