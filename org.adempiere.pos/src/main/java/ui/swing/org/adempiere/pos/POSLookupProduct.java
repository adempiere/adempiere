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

import org.adempiere.util.StringUtils;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Msg;

import javax.swing.JComboBox;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;

/**
 * Component allows to show product lookup search key , name , quantity available , price standard and price list
 * eEvolution author Victor Perez <victor.perez@e-evolution.com>, Created by e-Evolution on 24/01/16.
 */
public class POSLookupProduct implements ActionListener, KeyListener {

    private POSActionPanel actionPanel = null;
    private POSTextField fieldProductName = null;
    private long lastKeyboardEvent = 0;
    private boolean searched = false;
    private boolean selectLock = false;
    private javax.swing.Timer timer = null;
    private JComboBox<KeyNamePair> component = null;
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



    public POSLookupProduct (POSActionPanel actionPanel, POSTextField fieldProductName, long lastKeyboardEvent)
    {
        this.actionPanel = actionPanel;
        this.fieldProductName = fieldProductName;
        this.lastKeyboardEvent = lastKeyboardEvent;
    }

    public void setLastKeyboardEvent(long lastKeyboardEvent)
    {
        this.lastKeyboardEvent = lastKeyboardEvent;
    }

    public void setTimer(javax.swing.Timer timer)
    {
        this.timer = timer;
    }

    public void setFillingComponent(JComboBox<KeyNamePair> component)
    {
        this.component = component;
        component.addActionListener(this);
        component.addKeyListener(this);
        char[] charArray = new char[200];
        Arrays.fill(charArray,' ');
        this.fill = new String(charArray);
        this.title = new StringBuffer()
                .append(productValueTitle).append(separator)
                .append(productTitle).append(separator)
                .append(availableTitle).append(separator)
                .append(priceStdTitle).append(separator)
                .append(priceListTile).toString();
        component.addItem(new KeyNamePair(0, this.title));
    }

    public void setPriceListVersionId(int priceListVersionId)
    {
        this.priceListVersionId = priceListVersionId;
    }

    public void setWarehouseId(int warehouseId)
    {
        this.warehouseId = warehouseId;
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==timer)
        {
            long now = System.currentTimeMillis();

            if( (now - lastKeyboardEvent) > 500 && !searched && fieldProductName.getText()!= null && fieldProductName.getText().length()>2)
            {
                searched = true;
                executeQuery();
            }
            else if(!searched && (fieldProductName.getText()== null ||  fieldProductName.getText().length() == 0))
            {
                component.hidePopup();
                component.removeAllItems();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode()==40 && e.getSource()== fieldProductName) // Key down on product text field
        {
            component.requestFocus();
        }
        else if (e.getSource()== fieldProductName) //writing product name or value
        {
            searched = false;
            this.lastKeyboardEvent = System.currentTimeMillis();
            timer.restart();
        }
        else if(e.getKeyCode()==10 && e.getSource()==component) //Enter on component field
        {
            KeyNamePair item = (KeyNamePair) component.getSelectedItem();
            if(item!=null && !selectLock)
            {
                String productValue = DB.getSQLValueString(null , "SELECT Value FROM M_Product p WHERE M_Product_ID=?", item.getKey());
                fieldProductName.setText(productValue);
                actionPanel.findProduct();
                //form.updateInfo();
                component.removeAllItems();
                fieldProductName.requestFocus();
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }


    private void executeQuery()
    {
        component.hidePopup();

        String sql = "SELECT p.M_Product_ID, p.Value, p.Name  , BomQtyAvailable(p.M_Product_ID, ? , 0 ) AS QtyAvailable , pp.pricestd , pp.pricelist "
                + " FROM M_Product p "
                + " INNER JOIN M_ProductPrice pp ON (p.M_Product_ID=pp.M_Product_ID)"
                + " WHERE pp.M_Product_ID = p.M_Product_ID "
                + " AND pp.M_PriceList_Version_ID = ? "
                + " AND pp.IsActive='Y' "
                + " AND (UPPER(p.Name) like UPPER('"+ "%" + fieldProductName.getText().replace(" ", "%") + "%" +"')"
                + " OR UPPER(p.Value) like UPPER('" + "%" + fieldProductName.getText().replace(" ", "%") + "%" + "')) "
                + " ORDER By 3";

        PreparedStatement pstmt = null;
        try{
            pstmt = DB.prepareStatement(sql, null);
            pstmt.setInt(1, warehouseId);
            pstmt.setInt(2, priceListVersionId);

            ResultSet rs = pstmt.executeQuery();

            component.removeAllItems();
            component.addItem(new KeyNamePair(0, title));

            selectLock = true;

            while (rs.next()) {
                Integer productId = rs.getInt(1);
                String productValue = rs.getString(2).trim();
                String productName = rs.getString(3).trim();
                String qtyAvailable = rs.getBigDecimal(4).toString().trim();
                String priceStd = rs.getBigDecimal(5).toString().trim();
                String priceList = rs.getBigDecimal(6).toString().trim();
                String line = new StringBuilder()
                        .append(StringUtils.trunc(productValue + fill , PRODUCT_VALUE_LENGTH )).append(separator)
                        .append(StringUtils.trunc(productName + fill , PRODUCT_NAME_LENGTH )).append(separator)
                        .append(StringUtils.trunc(qtyAvailable + fill , QUNATITY_LENGTH)).append(separator)
                        .append(StringUtils.trunc(priceStd + fill, QUNATITY_LENGTH )).append(separator)
                        .append(StringUtils.trunc(priceList + fill, QUNATITY_LENGTH )).toString();
                component.addItem(new KeyNamePair(productId, line));
            }

            DB.close(rs,pstmt);
            pstmt = null;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        component.showPopup();
        selectLock = false;
    }
}