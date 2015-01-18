/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                        *
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
 * Contributor(s): Victor Perez www.e-evolution.com                           *
 *****************************************************************************/
package org.eevolution.form;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;

import org.compiere.apps.IStatusBar;
import org.compiere.apps.form.GenForm;
import org.compiere.minigrid.IDColumn;
import org.compiere.minigrid.IMiniTable;
import org.compiere.model.MLocator;
import org.compiere.model.MMovement;
import org.compiere.model.MPInstance;
import org.compiere.model.MPInstancePara;
import org.compiere.model.MPrivateAccess;
import org.compiere.model.MProcess;
import org.compiere.print.MPrintFormat;
import org.compiere.process.ProcessInfo;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Trx;
import org.eevolution.model.MDDOrder;

/**
 *	Generate Invoice (manual) controller class
 *
 *  @author victor.perez@www.e-evolution.com, e-Evolution
 */
public class OrderDistribution extends GenForm
{
	private static final long serialVersionUID = 1L;
	
	private static CLogger log = CLogger.getCLogger(OrderDistribution.class);
	
	public Object 			m_M_Locator_ID = null;
	public Object 			m_M_LocatorTo_ID = null;
	public Object 			m_C_BPartner_ID = null;

	public void dynInit() throws Exception
	{
		setTitle("MovementGenerateInfo");
		MPrintFormat format = MPrintFormat.get(Env.getCtx(), MPrintFormat.getPrintFormat_ID("Inventory Move Hdr (Example)", MMovement.Table_ID,  0), false);
		setPrintFormat(format);
		setAskPrintMsg("PrintMovements");
	}
	
	public void configureMiniTable(IMiniTable miniTable)
	{
		miniTable.addColumn("C_Order_ID");
		miniTable.addColumn("AD_Org_ID");
		miniTable.addColumn("C_DocType_ID");
		miniTable.addColumn("DocumentNo");
		miniTable.addColumn("C_BPartner_ID");
		miniTable.addColumn("DateOrdered");
		miniTable.addColumn("TotalLines");
		miniTable.setMultiSelection(true);
		
		miniTable.setColumnClass(0, IDColumn.class, false, " ");
		miniTable.setColumnClass(1, String.class, true, Msg.translate(Env.getCtx(), "AD_Org_ID"));
		miniTable.setColumnClass(2, String.class, true, Msg.translate(Env.getCtx(), "C_DocType_ID"));
		miniTable.setColumnClass(3, String.class, true, Msg.translate(Env.getCtx(), "DocumentNo"));
		miniTable.setColumnClass(4, String.class, true, Msg.translate(Env.getCtx(), "C_BPartner_ID"));
		miniTable.setColumnClass(5, Timestamp.class, true, Msg.translate(Env.getCtx(), "DateOrdered"));
		miniTable.setColumnClass(6, BigDecimal.class, true, Msg.translate(Env.getCtx(), "TotalLines"));
		miniTable.autoSize();
	}
	
	/**
	 * Get SQL for Orders that needs to be shipped
	 * @return sql
	 */
	private String getOrderSQL()
	{
		//  Create SQL
		StringBuffer sql = new StringBuffer(
				"SELECT DD_Order_ID, o.Name, dt.Name, DocumentNo, bp.Name, DateOrdered "
				+ "FROM M_Movement_Candidate_v ic, AD_Org o, C_BPartner bp, C_DocType dt "
				+ "WHERE ic.AD_Org_ID=o.AD_Org_ID"
				+ " AND ic.C_BPartner_ID=bp.C_BPartner_ID"
				+ " AND ic.C_DocType_ID=dt.C_DocType_ID"
				+ " AND ic.AD_Client_ID=?");

		if(m_M_Locator_ID != null)
			sql.append(" AND ic.M_Locator_ID=").append(m_M_Locator_ID);
		if(m_M_LocatorTo_ID != null)
			sql.append(" AND ic.M_LocatorTo_ID=").append(m_M_LocatorTo_ID);
		if (m_C_BPartner_ID != null)
			sql.append(" AND ic.C_BPartner_ID=").append(m_C_BPartner_ID);

		int AD_User_ID = Env.getContextAsInt(Env.getCtx(), "#AD_User_ID");
		String lockedIDs = MPrivateAccess.getLockedRecordWhere(MDDOrder.Table_ID, AD_User_ID);
		if (lockedIDs != null)
		{
			if (sql.length() > 0)
				sql.append(" AND ");
			sql.append("DD_Order_ID").append(lockedIDs);
		}

		//
		sql.append(" ORDER BY o.Name,bp.Name,DateOrdered");

		return sql.toString();
	}
	
	/**
	 *  Query Info
	 */
	public void executeQuery(IMiniTable miniTable)
	{
		int AD_Client_ID = Env.getAD_Client_ID(Env.getCtx());

		String sql = "";
		
		sql = getOrderSQL();

		log.fine(sql);
		//  reset table
		int row = 0;
		miniTable.setRowCount(row);
		//  Execute
		try
		{
			PreparedStatement pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, AD_Client_ID);
			ResultSet rs = pstmt.executeQuery();
			//
			while (rs.next())
			{
				//  extend table
				miniTable.setRowCount(row+1);
				//  set values
				miniTable.setValueAt(new IDColumn(rs.getInt(1)), row, 0);   //  C_Order_ID
				miniTable.setValueAt(rs.getString(2), row, 1);              //  Org
				miniTable.setValueAt(rs.getString(3), row, 2);              //  DocType
				miniTable.setValueAt(rs.getString(4), row, 3);              //  Doc No
				miniTable.setValueAt(rs.getString(5), row, 4);              //  BPartner
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

	public String generate(IStatusBar statusBar , String docActionSelected)
	{
		String info = "";
		log.info("M_Locator_ID=" + m_M_Locator_ID);
		String trxName = Trx.createTrxName("IMG");	
		Trx trx = Trx.get(trxName, true);

		setSelectionActive(false);  //  prevents from being called twice
		statusBar.setStatusLine(Msg.translate(Env.getCtx(), "M_Movement_ID"));
		statusBar.setStatusDB(String.valueOf(getSelection().size()));

		//	Prepare Process
		int AD_Process_ID = MProcess.getProcess_ID("M_Generate Movement", trxName);	  
		
		MPInstance instance = new MPInstance(Env.getCtx(), AD_Process_ID, 0);
		if (!instance.save())
		{
			info = Msg.getMsg(Env.getCtx(), "ProcessNoInstance");
			return info;
		}		
		
		DB.createT_Selection(instance.getAD_PInstance_ID(), getSelection(), null);

		//call process
		ProcessInfo pi = new ProcessInfo ("VOrderDistribution", AD_Process_ID);
		pi.setAD_PInstance_ID (instance.getAD_PInstance_ID());

		//	Add Parameter - Selection=Y
		MPInstancePara ip = new MPInstancePara(instance, 10);
		ip.setParameter("Selection","Y");
		ip.saveEx();
		MLocator locator = MLocator.get(Env.getCtx(), Integer.parseInt(m_M_Locator_ID.toString()));
		//	Add Parameter - M_Warehouse_ID=x
		ip = new MPInstancePara(instance, 20);
		ip.setParameter("M_Warehouse_ID", locator.getM_Warehouse_ID());
		ip.saveEx();
		
		setTrx(trx);
		setProcessInfo(pi);	
		return info;
	}	//	generateShipments
}	//	OrderDistribution
