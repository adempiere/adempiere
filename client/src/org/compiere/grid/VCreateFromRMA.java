/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2007 Adempiere, Inc. All Rights Reserved.                    *
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
package org.compiere.grid;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.compiere.model.GridTab;
import org.compiere.model.MRMA;
import org.compiere.model.MRMALine;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Msg;

/**
 * @author ashley
 */
public class VCreateFromRMA extends VCreateFrom
{
	/**
	 * 
	 * @param mTab
	 */
    VCreateFromRMA(GridTab mTab)
    {
        super(mTab);
        log.info(mTab.toString());
    }

    protected boolean dynInit() throws Exception
    {
        log.config("");
        setTitle("Customer RMA - Create Lines From");

        parameterBankPanel.setVisible(false);
        
        invoiceLabel.setVisible(false);
        invoiceField.setVisible(false);
        
        locatorLabel.setVisible(false);
        locatorField.setVisible(false);
        
        orderLabel.setVisible(false);
        orderField.setVisible(false);
        
        shipmentLabel.setVisible(false);
        shipmentField.setVisible(false);
        
        sameWarehouseCb.setVisible(false);
               
        rmaLabel.setVisible(false);
        rmaField.setVisible(false);

        initBPartner(true);
        
        bPartnerField.setEnabled(false);
        
        int inOutId = Env.getContextAsInt(Env.getCtx(), p_mTab.getWindowNo(), "InOut_ID");
        
        loadShipment(inOutId);
        
        return true;
    }
    
    /**
     *  Load Order/Invoice/Shipment data into Table
     *  @param data data
     */
    protected void loadTableOIS (Vector data)
    {
        //  Header Info
        Vector<String> columnNames = new Vector<String>(7);
        columnNames.add(Msg.getMsg(Env.getCtx(), "Select"));
        columnNames.add("Line");
        columnNames.add(Msg.translate(Env.getCtx(), "M_Product_ID"));
        columnNames.add("ASI");
        columnNames.add(Msg.translate(Env.getCtx(), "Quantity"));
        columnNames.add(Msg.getElement(Env.getCtx(), "QtyDelivered", false));
        

        //  Remove previous listeners
        dataTable.getModel().removeTableModelListener(this);
        //  Set Model
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        model.addTableModelListener(this);
        dataTable.setModel(model);
        //
        dataTable.setColumnClass(0, Boolean.class, false);      //  0-Selection
        dataTable.setColumnClass(1, String.class, true);        //  1-Line
        dataTable.setColumnClass(2, String.class, true);        //  2-Product 
        dataTable.setColumnClass(3, String.class, true);        //  3-ASI
        dataTable.setColumnClass(4, Double.class, true);        //  4-Qty
        dataTable.setColumnClass(5, Double.class, true);        //  5-Delivered Qty
        
        //  Table UI
        dataTable.autoSize();
    }   //  loadOrder
    
    /**
     *  Load Data - Shipment not invoiced
     *  @param M_InOut_ID InOut
     */
    private void loadShipment (int M_InOut_ID)
    {
        int m_rma_id = Env.getContextAsInt(Env.getCtx(), p_WindowNo, "M_RMA_ID");
        log.config("M_InOut_ID=" + M_InOut_ID);
        log.config("M_RMA_ID=" + m_rma_id);
        //
        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        
        /**
         * 1 M_InOutLine_ID
         * 2 Line
         * 3 Product Name
         * 4 Qty Entered
         * 5 Movement Qty
         * 6 ASI
         */
        StringBuffer sqlStmt = new StringBuffer();
        
        sqlStmt.append("SELECT iol.M_InOutLine_ID, iol.Line, "); 
        sqlStmt.append("CASE WHEN iol.M_Product_ID IS NOT NULL THEN (Select p.Name from M_Product p where p.M_Product_ID = iol.M_Product_ID) END as ProductName, "); 
        sqlStmt.append("iol.QtyEntered, "); 
        sqlStmt.append("iol.movementQty, "); 
        sqlStmt.append("CASE WHEN iol.M_AttributeSetInstance_ID IS NOT NULL THEN (SELECT SerNo FROM M_AttributeSetInstance asi where asi.M_AttributeSetInstance_ID=iol.M_AttributeSetInstance_ID) END as ASI ");
        sqlStmt.append("from M_InOutLine iol where M_InOut_ID=? ");
        sqlStmt.append("and iol.M_InOutLine_ID not in (select rmal.M_InOutLine_ID from M_RMALine rmal where rmal.M_RMA_ID=?)");
        
        
        try
        {
            PreparedStatement pstmt = DB.prepareStatement(sqlStmt.toString(), null);
            pstmt.setInt(1, M_InOut_ID);
            pstmt.setInt(2, m_rma_id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next())
            {
                Vector<Object> line = new Vector<Object>(7);
                line.add(new Boolean(false));           //  0-Selection
                
                KeyNamePair lineKNPair = new KeyNamePair(rs.getInt(1), rs.getString(2)); // 1-Line
                line.add(lineKNPair);
                line.add(rs.getString(3)); //2-Product
                line.add(rs.getString(6)); //3-ASI
                
                BigDecimal qtyEntered = rs.getBigDecimal(4); 
                BigDecimal movementQty = rs.getBigDecimal(5);
                
                line.add(qtyEntered.doubleValue());  //4-Qty
                line.add(movementQty.doubleValue()); //5-Movement Qty
                
                
                data.add(line);
            }
            rs.close();
            pstmt.close();
        }
        catch (SQLException e)
        {
            log.log(Level.SEVERE, sqlStmt.toString(), e);
        }
        loadTableOIS (data);
    }   //  loadShipment

    protected void initBPDetails(int C_BPartner_ID)
    {
        
    }

    protected void info()
    {
        
    }
    
    protected boolean save()
    {
        log.config("");
        int m_rma_id = Env.getContextAsInt(Env.getCtx(), p_WindowNo, "M_RMA_ID");
        TableModel model = dataTable.getModel();
        int rows = model.getRowCount();
        if (rows == 0)
        {
            return false;
        }
        
//        Integer bpId = (Integer)bPartnerField.getValue();
        MRMA rma = new MRMA(Env.getCtx(), m_rma_id, null);
        //update BP
//        rma.setC_BPartner_ID(bpId);
        
        for (int i = 0; i < rows; i++)
        {
            if (((Boolean)model.getValueAt(i, 0)).booleanValue())
            {
                Double d = (Double)model.getValueAt(i, 5);              //  5-Movement Qty
                KeyNamePair pp = (KeyNamePair)model.getValueAt(i, 1);   //  1-Line
                
                int inOutLineId = pp.getKey();
                
                MRMALine rmaLine = new MRMALine(Env.getCtx(), 0, null);
                rmaLine.setM_RMA_ID(m_rma_id);
                rmaLine.setM_InOutLine_ID(inOutLineId);
                rmaLine.setQty(new BigDecimal(d));
                rmaLine.setAD_Org_ID(rma.getAD_Org_ID());
                if (!rmaLine.save())
                {
                    throw new IllegalStateException("Could not create RMA Line");
                }
            }
        }
        
        if (!rma.save())
        {
            throw new IllegalStateException("Could not update RMA");
        }
        
        return true;
    }

}
