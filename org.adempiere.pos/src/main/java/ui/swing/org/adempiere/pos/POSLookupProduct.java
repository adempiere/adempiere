/** ****************************************************************************
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
 * Copyright (C) 2003-2016 e-Evolution,SC. All Rights Reserved.               *
 * Contributor(s): Victor Perez www.e-evolution.com                           *
 * ****************************************************************************/

package org.adempiere.pos;

import org.adempiere.pos.service.CPOS;
import org.adempiere.pos.service.POSLookupProductInterface;
import org.adempiere.util.StringUtils;
import org.compiere.apps.ADialog;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Msg;

import javax.swing.JComboBox;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;


/**
 * Component allows to show product lookup search key , name , quantity available , price standard and price list
 * eEvolution author Victor Perez <victor.perez@e-evolution.com>, Created by e-Evolution on 24/01/16.
 */
public class POSLookupProduct implements ActionListener, KeyListener {

    private POSLookupProductInterface lookupProductInterface = null;
    private POSTextField fieldProductName = null;
    private long lastKeyboardEvent = 0;
    private boolean searched = false;
    private boolean selectLock = false;
    private JComboBox<KeyNamePair> productLookupComboBox = null;
    private Integer priceListId = 0;
    private Integer warehouseId = 0;
    private Integer partnerId = 0;
    private String fill = StringUtils.repeat(" " , 400);
    static private Integer PRODUCT_VALUE_LENGTH = 14;
    static private Integer PRODUCT_NAME_LENGTH = 40;
    static private Integer QUANTITY_LENGTH = 15;

    private String separator = "|";
    private String productValueTitle   = StringUtils.trunc(Msg.parseTranslation(Env.getCtx() , "@Value@")        + fill , PRODUCT_VALUE_LENGTH );
    private String productTitle        = StringUtils.trunc(Msg.parseTranslation(Env.getCtx() , "@M_Product_ID@") + fill , PRODUCT_NAME_LENGTH );
    private String availableTitle      = StringUtils.trunc(Msg.parseTranslation(Env.getCtx() , "@QtyAvailable@") + fill , QUANTITY_LENGTH );
    private String priceStdTitle       = StringUtils.trunc(Msg.parseTranslation(Env.getCtx() , "@PriceStd@")     + fill , QUANTITY_LENGTH );
    private String priceListTile       = StringUtils.trunc(Msg.parseTranslation(Env.getCtx() , "@PriceList@")    + fill , QUANTITY_LENGTH );
    private String title = "";


    /**
     * Default constructor
     * *** Constructor ***
     * @param lookupProductInterface
     * @param fieldProductName
     * @param lastKeyboardEvent
     */
    public POSLookupProduct (POSLookupProductInterface lookupProductInterface, 
    		POSTextField fieldProductName, long lastKeyboardEvent) {
        this.lookupProductInterface = lookupProductInterface;
        this.fieldProductName = fieldProductName;
        this.lastKeyboardEvent = lastKeyboardEvent;
    }

    /**
     * Set a last keyboard
     * @param lastKeyboardEvent
     * @return void
     */
    public void setLastKeyboardEvent(long lastKeyboardEvent) {
        this.lastKeyboardEvent = lastKeyboardEvent;
    }

    /**
     * Set Filling Component
     */
    public void setFillingComponent(JComboBox<KeyNamePair> productLookupComboBox)
    {
        this.productLookupComboBox = productLookupComboBox;
        productLookupComboBox.addActionListener(this);
        productLookupComboBox.addKeyListener(this);
        char[] charArray = new char[200];
        Arrays.fill(charArray,' ');
        this.fill = new String(charArray);
        this.title = new StringBuffer()
                .append(productValueTitle).append(separator)
                .append(productTitle).append(separator)
                .append(availableTitle).append(separator)
                .append(priceStdTitle).append(separator)
                .append(priceListTile).toString();
        productLookupComboBox.addItem(new KeyNamePair(0, this.title));
    }

    /**
     * Set Price List Version ID
     * @param priceListId
     */
    public void setPriceListId(int priceListId) {
        this.priceListId = priceListId;
    }

    /**
     * Set Price List Version ID
     * @param partnerId
     */
    public void setPartnerId(int partnerId) {
        this.partnerId = partnerId;
    }

    /**
     * Set Warehouse ID
     * @param warehouseId
     */
    public void setWarehouseId(int warehouseId) {
        this.warehouseId = warehouseId;
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        if (actionEvent.getSource()== productLookupComboBox
        && actionEvent.getModifiers() == 16
        && actionEvent.getSource() != lookupProductInterface.getProductTimer())
            captureProductFromCombo();

        if(actionEvent.getSource()== lookupProductInterface.getProductTimer())
        {
            long now = System.currentTimeMillis();

            if( (now - lastKeyboardEvent) > 500
            && !searched && fieldProductName.getText() != null
            && fieldProductName.getText().length() > 2)
            {
                executeQuery();
            }
            else if(!searched && (fieldProductName.getText()== null ||  fieldProductName.getText().length() == 0))
            {
                productLookupComboBox.hidePopup();
                productLookupComboBox.removeAllItems();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent)  {
    	if(keyEvent.getSource().equals(fieldProductName)) {
    		if(keyEvent.getKeyCode() == KeyEvent.VK_DOWN) {
    			if(productLookupComboBox != null) {
    				productLookupComboBox.requestFocus();
    			}
    		} else if(KeyEvent.VK_TAB == keyEvent.getKeyCode()
    				|| KeyEvent.VK_ENTER == keyEvent.getKeyCode()) {
    			fieldProductName.setPlaceholder(fieldProductName.getText());
                try {

                    lookupProductInterface.findProduct(KeyEvent.VK_TAB == keyEvent.getKeyCode());
                } catch (Exception exception) {
                    ADialog.error(0 , null , exception.getLocalizedMessage());
                }
                //	
//                lookupProductInterface.quantityRequestFocus();
                fieldProductName.setText("");
                return;
    		} else {
    			searched = false;
                this.lastKeyboardEvent = System.currentTimeMillis();
                if (lookupProductInterface.getProductTimer() != null)
                    ((javax.swing.Timer)lookupProductInterface.getProductTimer()).restart();
    		}
    	} else if(keyEvent.getSource().equals(productLookupComboBox)) {
    		if(keyEvent.getKeyCode() == KeyEvent.VK_ENTER) {
    			captureProductFromCombo();
                return;
    		}
    	}
    }

    /**
     * Capture Product from combo box
     * 
     * @return void
     */
    public void captureProductFromCombo() {
        KeyNamePair item = (KeyNamePair) productLookupComboBox.getSelectedItem();
        if(item!=null && !selectLock)
        {
            String productValue = DB.getSQLValueString(null , "SELECT Value FROM M_Product p WHERE M_Product_ID=?", item.getKey());
            fieldProductName.setPlaceholder(productValue);
            try {
                lookupProductInterface.findProduct(true);
            } catch (Exception exception) {
                ADialog.error(0 , null , exception.getLocalizedMessage());
            }
            productLookupComboBox.removeAllItems();
            fieldProductName.setText("");
        }

    }
    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
    }


    private void executeQuery()
    {
            searched = true;
            productLookupComboBox.removeAllItems();
            productLookupComboBox.addItem(new KeyNamePair(0, title));
            selectLock = true;
            for (java.util.Vector<Object> columns : CPOS.getQueryProduct(fieldProductName.getText(), warehouseId, priceListId , partnerId))
            {
                Integer productId = (Integer) columns.elementAt(0);
                String productValue = (String) columns.elementAt(1);
                String productName = (String) columns.elementAt(2);
                String qtyAvailable = (String) columns.elementAt(3);
                String priceStd = (String) columns.elementAt(4);
                String priceList = (String) columns.elementAt(5);
                String  line = new StringBuilder()
                        .append(StringUtils.trunc(productValue  + fill , PRODUCT_VALUE_LENGTH )).append(separator)
                        .append(StringUtils.trunc(productName   + fill , PRODUCT_NAME_LENGTH )).append(separator)
                        .append(StringUtils.trunc(qtyAvailable  + fill , QUANTITY_LENGTH )).append(separator)
                        .append(StringUtils.trunc(priceStd      + fill , QUANTITY_LENGTH )).append(separator)
                        .append(StringUtils.trunc(priceList     + fill , QUANTITY_LENGTH )).toString();
                productLookupComboBox.addItem(new KeyNamePair(productId, line));
            }

        productLookupComboBox.showPopup();
        selectLock = false;
    }
}