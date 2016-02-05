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

import java.util.ArrayList;
import java.util.Arrays;

import org.adempiere.pos.service.CPOS;
import org.adempiere.util.StringUtils;
import org.adempiere.webui.component.AutoComplete;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.InputEvent;
import org.zkoss.zul.event.TreeDataEvent;
import org.zkoss.zul.event.TreeDataListener;

/**
 * Component allows to show product lookup search key , name , quantity available , price standard and price list
 * @author Mario Calderon, mario.calderon@westfalia-it.com, Systemhaus Westfalia, http://www.westfalia-it.com
 * @author Raul Muñoz, rmunoz@erpcya.com, ERPCYA http://www.erpcya.com
 */
public class WPOSLookupProduct extends AutoComplete implements EventListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2303830709901143774L;
    private AutoComplete component = null;
    private Integer priceListVersionId = 0;
    private Integer warehouseId = 0;
    private String fill = StringUtils.repeat(" " , 400);
    static private Integer PRODUCT_VALUE_LENGTH = 14;
    static private Integer PRODUCT_NAME_LENGTH = 50;
    static private Integer QUNATITY_LENGTH = 16;

    private String separator = "|";
    private String productValueTitle   = StringUtils.trunc(Msg.parseTranslation(Env.getCtx() , "@ProductValue@") + fill , PRODUCT_VALUE_LENGTH );
    private String productTitle        = StringUtils.trunc(Msg.parseTranslation(Env.getCtx() , "@M_Product_ID@") + fill , PRODUCT_NAME_LENGTH );
    private String availableTitle      = StringUtils.trunc(Msg.parseTranslation(Env.getCtx() , "@QtyAvailable@") + fill , QUNATITY_LENGTH );
    private String priceStdTitle       = StringUtils.trunc(Msg.parseTranslation(Env.getCtx() , "@PriceStd@")     + fill , QUNATITY_LENGTH );
    private String priceListTile       = StringUtils.trunc(Msg.parseTranslation(Env.getCtx() , "@PriceList@")    + fill , QUNATITY_LENGTH );
    private String title = "";
    
    private ArrayList<Integer> recordId;  
    private int index = -1;

    public WPOSLookupProduct (WPOSActionPanel actionPanel, WPOSTextField fieldProductName, long lastKeyboardEvent) {
    	super();
    	component = new AutoComplete();
        this.setClass("input-search");
        this.setButtonVisible(false);
        this.addEventListener(Events.ON_FOCUS, this);
        this.addEventListener(Events.ON_BLUR, this);
        setFillingComponent();
        component.setStyle("Font-size:medium; font-weight:bold");
    }
    
    /**
     * Set Filling Component
     * @param void
     */
    public void setFillingComponent() {
        char[] charArray = new char[200];
        Arrays.fill(charArray,' ');
        this.fill = new String(charArray);
        this.title = new StringBuffer()
                .append(productValueTitle).append(separator)
                .append(productTitle).append(separator)
                .append(availableTitle).append(separator)
                .append(priceStdTitle).append(separator)
                .append(priceListTile).toString();
        this.setText(this.title);
    }
    
    /**
     * Set Price List Version ID
     * @param priceListVersionId
     */
    public void setPriceListVersionId(int priceListVersionId) {
        this.priceListVersionId = priceListVersionId;
    }
    
    /**
     * Set Warehouse ID
     * @param warehouseId
     */
    public void setWarehouseId(int warehouseId) {
        this.warehouseId = warehouseId;
    }
    
    /**
     * Execute Query
     * @param value
     */
    private void executeQuery(String value) {
    	
    	this.setOpen(false);
    	if(value.trim().length() < 3) {
			return;
		}
    	if(value.length() <= 0) {
            this.setText(title);    		
    		this.removeAllItems();
    		return;
    	}

    	component.removeAllItems();

        ArrayList<String> line = new ArrayList<String>();
        recordId = new ArrayList<Integer>();
        for (java.util.Vector<Object> columns : CPOS.getQueryProduct(value, warehouseId, priceListVersionId))
        {
            recordId.add((Integer) columns.elementAt(0));
            String productValue = (String)columns.elementAt(1);
            String productName = (String)columns.elementAt(2);
            String qtyAvailable = (String)columns.elementAt(3);
            String priceStd =  (String)columns.elementAt(4);
            String priceList = (String)columns.elementAt(5);
            line.add(new StringBuilder()
                    .append(StringUtils.trunc(productValue + fill , PRODUCT_VALUE_LENGTH )).append(separator)
                    .append(StringUtils.trunc(productName + fill , PRODUCT_NAME_LENGTH )).append(separator)
                    .append(StringUtils.trunc(qtyAvailable + fill , QUNATITY_LENGTH)).append(separator)
                    .append(StringUtils.trunc(priceStd + fill, QUNATITY_LENGTH )).append(separator)
                    .append(StringUtils.trunc(priceList + fill, QUNATITY_LENGTH )).toString());
        }

    	String[] searchValues = new String[line.size()];
    	String[] searchDescription = new String[line.size()];
    	for(int i = 0; i < line.size(); i++) {
    		searchValues[i] = line.get(i);
    		searchDescription[i] = " ";
    	}
    	this.removeAllItems();
        this.setDict(searchValues);   
        this.setDescription(searchDescription);
        this.setOpen(true);
        
    }

	@Override
	public void onEvent(Event e) throws Exception {
    	
		if(e.getName().equals(Events.ON_FOCUS))
			setText("");
		else if(e.getName().equals(Events.ON_BLUR))
			this.setText(this.title);
	}
	
	/**
	 * Get Selected Record
	 * @return int ID
	 */
	public int getSelectedRecord(){
		if(recordId.size() > 1 ){
			return recordId.get(index);
		}
		else if(recordId.size() == 1){
			return recordId.get(0);
		}
		return -1;
	}
	
	/**
	 * @param event
	 * @see TreeDataListener#onChange(TreeDataEvent)
	 */
	public void onChanging(InputEvent evt) {
		index = this.getSelectedIndex();
        if(!evt.isChangingBySelectBack()){
        	executeQuery(evt.getValue());
        }
        super.onChanging(evt);
	}
	
}