package org.adempiere.pos;

import org.compiere.util.DB;
import org.compiere.util.KeyNamePair;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by e-Evolution on 24/01/16.
 */
public class POSLookupProduct implements ActionListener, KeyListener {

    private POSActionPanel actionPanel = null;
    private POSTextField fieldName = null;
    private long lastKeyboardEvent = 0;
    private boolean searched = false;
    private boolean selectLock = false;
    private javax.swing.Timer timer = null;
    private JComboBox<KeyNamePair> component = null;
    private int priceListVersionId = 0;

    public POSLookupProduct (POSActionPanel actionPanel, POSTextField fieldName, long lastKeyboardEvent)
    {
        this.actionPanel = actionPanel;
        this.fieldName = fieldName;
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
    }

    public void setPriceList_ID(int pricelist_id)
    {
        this.priceListVersionId = pricelist_id;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==timer)
        {
            long now = System.currentTimeMillis();

            if( (now - lastKeyboardEvent) > 500 && !searched && fieldName.getText()!= null && fieldName.getText().length()>2)
            {
                searched = true;
                executeQuery();
            }
            else if(!searched && (fieldName.getText()== null ||  fieldName.getText().length() == 0))
            {
                component.hidePopup();
                component.removeAllItems();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode()==40 && e.getSource()== fieldName) // Key down on product text field
        {
            component.requestFocus();
        }
        else if (e.getSource()== fieldName) //writing product name or value
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
                fieldName.setText(item.getName().substring(0, item.getName().indexOf("_")));
                actionPanel.findProduct();
                //form.updateInfo();
                component.removeAllItems();
                fieldName.requestFocus();
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

        String sql = "SELECT M_Product.M_Product_ID, M_Product.Value, M_Product.Name "
                + " FROM M_Product M_Product "
                + " WHERE EXISTS (SELECT 1 FROM M_ProductPrice pp "
                + " WHERE pp.M_Product_ID = M_Product.M_Product_ID "
                + " AND pp.M_PriceList_Version_ID = ? "
                + " AND pp.IsActive='Y') "
                + " AND (UPPER(M_Product.Name) like UPPER('"+ "%" + fieldName.getText().replace(" ", "%") + "%" +"')"
                + " OR UPPER(M_Product.Value) like UPPER('" + "%" + fieldName.getText().replace(" ", "%") + "%" + "')) "
                + " ORDER By 3";

        PreparedStatement pstmt = null;
        try{
            pstmt = DB.prepareStatement(sql, null);
            pstmt.setInt(1, priceListVersionId);

            ResultSet rs = pstmt.executeQuery();

            component.removeAllItems();

            selectLock = true;

            while (rs.next())
                 component.addItem(new KeyNamePair(rs.getInt(1), rs.getString(2) + "_" + rs.getString(3)));


            rs.close();
            pstmt.close();
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