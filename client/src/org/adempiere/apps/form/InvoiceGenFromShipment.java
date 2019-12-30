/******************************************************************************
 * Copyright (C) 2009 Low Heng Sin                                            *
 * Copyright (C) 2009 Idalica Corporation                                     *
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
package org.adempiere.apps.form;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;

import org.adempiere.process.InvoiceGenerateFromShipmentAbstract;
import org.compiere.apps.IStatusBar;
import org.compiere.apps.form.GenForm;
import org.compiere.minigrid.IDColumn;
import org.compiere.minigrid.IMiniTable;
import org.compiere.model.MInOut;
import org.compiere.model.MPInstance;
import org.compiere.model.MPInstancePara;
import org.compiere.model.MPrivateAccess;
import org.compiere.print.ReportEngine;
import org.compiere.process.ProcessInfo;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Msg;
import org.compiere.util.Trx;

/**
 * "Generate Invoices from Shipments (manual)" - controller class
 *
 *	@see <a href="https://github.com/adempiere/adempiere/issues/1657">
 * 		  bug in swing client "Generate Invoices from Shipments (manual)"</a>
 */
public class InvoiceGenFromShipment extends GenForm {
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(InvoiceGenFromShipment.class);

	public Object 			m_AD_Org_ID = null;
	public Object 			m_C_BPartner_ID = null;

	public void dynInit() throws Exception {
		setTitle("InvGenerateInfo");
		setReportEngineType( ReportEngine.INVOICE );
		setAskPrintMsg("PrintInvoices");
	}

	public void configureMiniTable(IMiniTable miniTable) {
		//  create Columns
		miniTable.addColumn("M_InOut_ID");
		miniTable.addColumn("DocumentNo");
		miniTable.addColumn("C_BPartner_ID");
		miniTable.addColumn("DateDelivered"); // @Trifon old:DateOrdered
		miniTable.addColumn("Description");   // @Trifon
		//
		miniTable.setMultiSelection(true);
		//  set details
		int i = 0;
		miniTable.setColumnClass(i++, IDColumn.class, false, " ");
		miniTable.setColumnClass(i++, String.class, true, Msg.translate(Env.getCtx(), "DocumentNo"));
		miniTable.setColumnClass(i++, String.class, true, Msg.translate(Env.getCtx(), "C_BPartner_ID"));
		miniTable.setColumnClass(i++, Timestamp.class, true, Msg.translate(Env.getCtx(), "DateDelivered")); // @trifon
		miniTable.setColumnClass(i++, String.class, true, Msg.translate(Env.getCtx(), "Description"));     // @Trifon
		//
		miniTable.autoSize();
	}

	/**
	 * Get SQL for Shipments that needs to be invoiced
	 * @return sql
	 */
	private String getInOutSQL() {
	    StringBuffer sql = new StringBuffer(
	    		//                  1,      2,       3,            4,        5,               6,               7,              8
	            "SELECT ic.M_InOut_ID, o.Name, dt.Name, ic.DocumentNo, bp.Name, ic.MovementDate, ic.QtyToInvoice, ic.Description "
	            + "FROM C_Invoice_Candidate_Ship_v ic, AD_Org o, C_BPartner bp, C_DocType dt "
	            + "WHERE ic.AD_Org_ID=o.AD_Org_ID"
	            + " AND ic.C_BPartner_ID=bp.C_BPartner_ID"
	            + " AND ic.C_DocType_ID=dt.C_DocType_ID"
	            + " AND ic.AD_Client_ID=?");

        if (m_AD_Org_ID != null)
            sql.append(" AND ic.AD_Org_ID=").append(m_AD_Org_ID);
        if (m_C_BPartner_ID != null)
            sql.append(" AND ic.C_BPartner_ID=").append(m_C_BPartner_ID);

        // bug - [ 1713337 ] "Generate Invoices (manual)" show locked records.
        /* begin - Exclude locked records; @Trifon */
        int AD_User_ID = Env.getContextAsInt(Env.getCtx(), "#AD_User_ID");
        String lockedIDs = MPrivateAccess.getLockedRecordWhere(MInOut.Table_ID, AD_User_ID);
        if (lockedIDs != null) {
            if (sql.length() > 0)
                sql.append(" AND ");
            sql.append("M_InOut_ID").append( lockedIDs );//@Trifon - old: C_Order_ID
        }
        /* end - Exclude locked records; @Trifon */

        sql.append(" ORDER BY o.Name, bp.Name, MovementDate");
        return sql.toString();
	}

	/**
	 *  Query Info
	 */
	public void executeQuery(KeyNamePair docTypeKNPair, IMiniTable miniTable) {
		log.info("");
		int AD_Client_ID = Env.getAD_Client_ID(Env.getCtx());
		String sql = "";

        if (docTypeKNPair.getKey() == MInOut.Table_ID) { //@Trifon old: MOrder.Table_ID
            sql = getInOutSQL();
        }

		//  reset table
		int row = 0;
		miniTable.setRowCount(row);
		//  Execute
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, AD_Client_ID);
			rs = pstmt.executeQuery();
			//
			while (rs.next()) {
				//  extend table
				miniTable.setRowCount(row+1);
				int i = 0;
				//  set values
				miniTable.setValueAt(new IDColumn(rs.getInt(1)), row, i++);   //  M_InOut_ID @Trifon
				miniTable.setValueAt(rs.getString(4), row, i++);              //  Doc No
				miniTable.setValueAt(rs.getString(5), row, i++);              //  BPartner
				miniTable.setValueAt(rs.getTimestamp(6), row, i++);           //  MovementDate @Trifon
				miniTable.setValueAt(rs.getString(8), row, i++);              //  Description
				//  prepare next
				row++;
			}
		} catch (SQLException e) {
			log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}

		miniTable.autoSize();
	}

	/**
	 *	Save Selection & return selection Query or ""
	 *  @return where clause like M_InOut_ID IN (...)
	 */
	public void saveSelection(IMiniTable miniTable) {
		log.info("");
		//  Array of Integers
		ArrayList<Integer> results = new ArrayList<Integer>();
		setSelection(null);

		//	Get selected entries
		int rows = miniTable.getRowCount();
		for (int i = 0; i < rows; i++) {
			IDColumn id = (IDColumn)miniTable.getValueAt(i, 0);     //  ID in column 0
			//log.fine( "Row=" + i + " - " + id);
			if (id != null && id.isSelected())
				results.add(id.getRecord_ID());
		}

		if (results.size() == 0)
			return;
		log.config("Selected #" + results.size());
		setSelection(results);
	}


	/**************************************************************************
	 *	Generate Invoices
	 */
	public String generate(IStatusBar statusBar, KeyNamePair docTypeKNPair, String docActionSelected) {
		log.info("docTypeKNPair "+docTypeKNPair.toStringX()+" , docActionSelected="+docActionSelected);
		String info = "";
		String trxName = Trx.createTrxName("IVG");
		Trx trx = Trx.get(trxName, true);	//trx needs to be committed too

		setSelectionActive(false);  //  prevents from being called twice
		statusBar.setStatusLine(Msg.getMsg(Env.getCtx(), "InvGenerateGen"));
		statusBar.setStatusDB(String.valueOf(getSelection().size()));

		//	Prepare Process
		int AD_Process_ID = 0;

        if (docTypeKNPair.getKey() == MInOut.Table_ID) {
        	AD_Process_ID = InvoiceGenerateFromShipmentAbstract.getProcessId();
        }
		MPInstance instance = new MPInstance(Env.getCtx(), AD_Process_ID, 0);
		if (!instance.save()) {
			info = Msg.getMsg(Env.getCtx(), "ProcessNoInstance");
			return info;
		}

		//insert selection
		StringBuffer insert = new StringBuffer();
		insert.append("INSERT INTO T_SELECTION(AD_PINSTANCE_ID, T_SELECTION_ID) ");
		int counter = 0;
		for (Integer selectedId : getSelection()) {
			counter++;
			if (counter > 1)
				insert.append(" UNION ");
			insert.append("SELECT ");
			insert.append(instance.getAD_PInstance_ID());
			insert.append(", ");
			insert.append(selectedId);
			insert.append(" FROM DUAL ");

			if (counter == 1000) {
				if ( DB.executeUpdate(insert.toString(), trxName) < 0 ) {
					String msg = "No Invoices";     //  not translated!
					info = msg;
					log.config(msg);
					trx.rollback();
					return info;
				}
				insert = new StringBuffer();
				insert.append("INSERT INTO T_SELECTION(AD_PINSTANCE_ID, T_SELECTION_ID) ");
				counter = 0;
			}
		}
		if (counter > 0) {
			if ( DB.executeUpdate(insert.toString(), trxName) < 0 ) {
				String msg = "No Invoices";     //  not translated!
				info = msg;
				log.config(msg);
				trx.rollback();
				return info;
			}
		}

		ProcessInfo pi = new ProcessInfo ("", AD_Process_ID);
		pi.setAD_PInstance_ID (instance.getAD_PInstance_ID());

		//	Add Parameters
		MPInstancePara para = new MPInstancePara(instance, 10);
		pi.setIsSelection(true);

		para = new MPInstancePara(instance, 20);
		para.setParameter("DocAction", docActionSelected);
		if (!para.save()) {
			String msg = Msg.getMsg(Env.getCtx(), "ParameterMissing") + " DocAction";
			info = msg;
			log.log(Level.SEVERE, msg);
			return info;
		}

		if(m_AD_Org_ID==null) {
			String msg = Msg.getMsg(Env.getCtx(), "ParameterMissing") + " AD_Org_ID";
			info = msg;
			log.log(Level.SEVERE, msg);
			return info;
		} else {
			para = new MPInstancePara(instance, 30);
			para.setParameter("AD_Org_ID", (Integer)m_AD_Org_ID);
			if (!para.save()) {
				String msg = Msg.getMsg(Env.getCtx(), "ParameterMissing") + " AD_Org_ID";
				info = msg;
				log.log(Level.SEVERE, msg);
				return info;
			}
		}

		if(m_C_BPartner_ID!=null) {
			para = new MPInstancePara(instance, 40);
			para.setParameter("C_BPartner_ID", (Integer)m_C_BPartner_ID);
			if (!para.save()) {
				String msg = Msg.getMsg(Env.getCtx(), "ParameterMissing") + " C_BPartner_ID";
				info = msg;
				log.log(Level.SEVERE, msg);
				return info;
			}
		}

		setTrx(trx);
		setProcessInfo(pi);

		return info;
	}

}