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
 * Copyright (C) 2003-2007 e-Evolution,SC. All Rights Reserved.               *
 * Contributor(s): victor.perez@e-evolution.com			                          *
 *****************************************************************************/
package org.eevolution.form;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.apps.IStatusBar;
import org.compiere.apps.form.FormFrame;
import org.compiere.apps.form.GenForm;
import org.compiere.minigrid.IDColumn;
import org.compiere.minigrid.IMiniTable;
import org.compiere.minigrid.MiniTable;
import org.compiere.model.MMovement;
import org.compiere.model.MMovementLine;
import org.compiere.model.MPInstance;
import org.compiere.model.MProcess;
import org.compiere.print.MPrintFormat;
import org.compiere.process.ProcessInfo;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Trx;
import org.eevolution.model.MDDOrder;
import org.eevolution.model.MDDOrderLine;

/**
 *	Create Movement for Material Receipt from Distribution Order
 *
 *  @author victor.perez@www.e-evolution.com 
 *  @version $Id: VOrderDistributionReceipt,v 1.0 
 */
public class OrderDistributionReceipt extends GenForm
{
	/**	FormFrame			*/
	public FormFrame 		m_frame;
	public Object 			m_DD_Order_ID = null;
	public Object 			m_MovementDate = null;

	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(OrderDistributionReceipt.class);
	
	public void dynInit() throws Exception
	{
		setTitle("MovementGenerateInfo");
		MPrintFormat format = MPrintFormat.get(Env.getCtx(), MPrintFormat.getPrintFormat_ID("Inventory Move Hdr (Example)", MMovement.Table_ID,  0), false);
		this.setPrintFormat(format);
		setAskPrintMsg("PrintMovements");
	}
	
	public void configureMiniTable(IMiniTable miniTable)
	{
		//  create Columns
		miniTable.addColumn("DD_Order_ID");
		miniTable.addColumn("QtyInTransit");
		miniTable.addColumn("C_UOM_ID");
		miniTable.addColumn("Value");
		miniTable.addColumn("M_Product_ID");
		miniTable.addColumn("M_WarehouseSource_ID");
		miniTable.setMultiSelection(true);
		//  set details
		miniTable.setColumnClass(0, IDColumn.class, false, " ");
		miniTable.setColumnClass(1, BigDecimal.class, false, Msg.translate(Env.getCtx(), "QtyInTransit")); //Qty
		miniTable.setColumnClass(2, String.class, true, Msg.translate(Env.getCtx(), "C_UOM_ID")); //UM 
		miniTable.setColumnClass(3, String.class, true, Msg.translate(Env.getCtx(), "M_Product_ID")); // Product 
		miniTable.setColumnClass(4, String.class, true, Msg.translate(Env.getCtx(), "Value")); // Line
		miniTable.setColumnClass(5, String.class, true, Msg.translate(Env.getCtx(), "WarehouseSource"));
		miniTable.autoSize();
	}	//	dynInit

	/**
	 * Get SQL for Orders that needs to be shipped
	 * @return sql
	 */
	private String getOrderSQL()
	{
		// Create SQL
        StringBuffer sql = new StringBuffer(
            "SELECT ol.DD_OrderLine_ID, ol.QtyInTransit , uom.Name , p.Value ,p.Name  , w.Name "
            + "FROM DD_OrderLine ol INNER JOIN DD_Order o ON (o.DD_Order_ID=ol.DD_Order_ID) INNER JOIN M_Product p ON (p.M_Product_ID=ol.M_Product_ID) "
            + " INNER JOIN C_UOM uom  ON (uom.C_UOM_ID=ol.C_UOM_ID)"
            + " INNER JOIN M_Locator  l ON (l.M_Locator_ID = ol.M_Locator_ID)"
            + " INNER JOIN M_Warehouse  w ON (w.M_Warehouse_ID = l.M_Warehouse_ID)"  
            + " WHERE o.DocStatus= 'CO' AND  ol.QtyInTransit > 0  AND  o.DD_Order_ID = ? ");
        
        return sql.toString();
	}

	/**
	 *  Query Info
	 */
	public void executeQuery(IMiniTable miniTable)
	{
		log.info("");	
		String sql = "";
		
		if (m_DD_Order_ID == null)
			return;
		
		    sql = getOrderSQL();

		log.fine(sql);
		//  reset table
		int row = 0;
		miniTable.setRowCount(row);
			
		//  Execute
		try
		{
			PreparedStatement pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, Integer.parseInt(m_DD_Order_ID.toString()));
			ResultSet rs = pstmt.executeQuery();
			//
			while (rs.next())
			{
				//  extend table
				miniTable.setRowCount(row+1);
				//  set values
				miniTable.setValueAt(new IDColumn(rs.getInt(1)), row, 0);   //  DD_Order_ID
				miniTable.setValueAt(rs.getBigDecimal(2), row, 1);          //  QtyInTransit
				miniTable.setValueAt(rs.getString(3), row, 2);              //  C_UOM_ID
				miniTable.setValueAt(rs.getString(4), row, 4);              //  Value
				miniTable.setValueAt(rs.getString(5), row, 3);              //  M_Product_ID
				miniTable.setValueAt(rs.getString(6), row, 5);           	//  WarehouseSource
				//  prepare next
				row++;
			}
			rs.close();
			pstmt.close();
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql.toString(), e);
		}
		//
		miniTable.autoSize();
	}   //  executeQuery

	/**
	 * 	Dispose
	 */
	public void dispose()
	{
		if (m_frame != null)
			m_frame.dispose();
		m_frame = null;
	}	//	dispose

	/**
	 *	Save Selection & return selecion Query or ""
	 *  @return where clause like C_Order_ID IN (...)
	 */
	public void saveSelection(IMiniTable miniTable)
	{
		log.info("");
		//  Array of Integers
		ArrayList<Integer> results = new ArrayList<Integer>();
		setSelection(null);

		//	Get selected entries
		int rows = miniTable.getRowCount();
		for (int i = 0; i < rows; i++)
		{
			IDColumn id = (IDColumn)miniTable.getValueAt(i, 0);     //  ID in column 0
		//	log.fine( "Row=" + i + " - " + id);
			if (id != null && id.isSelected())
				results.add(id.getRecord_ID());
		}

		if (results.size() == 0)
			return;
		log.config("Selected #" + results.size());
		setSelection(results);
	}	//	saveSelection
	
	/**************************************************************************
	 *	Generate Movements
	 */
	public String generate(MiniTable miniTable,IStatusBar statusBar , String docActionSelected)
	{
		String info = "";
		log.info("DD_Order_ID=" + m_DD_Order_ID);
		log.info("MovementDate" + m_MovementDate);
		String trxName = Trx.createTrxName("MVG");	
		Trx trx = Trx.get(trxName, true);
		
		setSelectionActive(false);  //  prevents from being called twice
		statusBar.setStatusLine(Msg.translate(Env.getCtx(), "M_Movement_ID"));
		statusBar.setStatusDB(String.valueOf(getSelection().size()));

		Properties m_ctx = Env.getCtx();
		
		Timestamp movementDate = (Timestamp) m_MovementDate;
		MDDOrder order = new MDDOrder(m_ctx , Integer.parseInt(m_DD_Order_ID.toString()), trxName);
		MMovement movement = new MMovement(order, movementDate);
		movement.saveEx();
		
		ArrayList<Integer> ids = getSelection();
		int i = 0;
		for (int DD_OrderLine_ID : ids)
		{
			MDDOrderLine oline = new MDDOrderLine(m_ctx, DD_OrderLine_ID, trxName);
			MMovementLine line = new MMovementLine(movement);
			line.setM_Product_ID(oline.getM_Product_ID());
			BigDecimal QtyDeliver = (BigDecimal) miniTable.getValueAt(i, 1);
			if(QtyDeliver == null | QtyDeliver.compareTo(oline.getQtyInTransit()) > 0)
				 throw new AdempiereException("Error in Qty");
			
			line.setOrderLine(oline, QtyDeliver, true);
			line.saveEx();
			i ++;
		}
		//	Fails if there is a confirmation
		if (!movement.processIt(MMovement.ACTION_Complete))
			log.warning("Failed: " + movement);
		movement.setDocStatus(MMovement.DOCACTION_Complete);
		movement.setDocAction(MMovement.ACTION_Close);
		movement.saveEx();
		return info;
	}	//	generate
}	//	VOrderDistributionReceipt
