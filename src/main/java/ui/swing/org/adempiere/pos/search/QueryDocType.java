/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 Adempiere, Inc. All Rights Reserved.               *
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

package org.adempiere.pos.search;

import java.awt.Cursor;
import java.awt.Frame;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import net.miginfocom.swing.MigLayout;

import org.adempiere.pos.POSTextField;
import org.adempiere.pos.VPOS;
import org.adempiere.pos.grid.VPOSBPartner;
import org.compiere.minigrid.ColumnInfo;
import org.compiere.minigrid.IDColumn;
import org.compiere.model.MBPartnerInfo;
import org.compiere.model.MDocType;
import org.compiere.model.MOrder;
import org.compiere.model.PO;
import org.compiere.swing.CLabel;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;

/**
 * @author Mario Calderon, mario.calderon@westfalia-it.com, Systemhaus Westfalia, http://www.westfalia-it.com
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 * @author victor.perez@e-evolution.com , http://www.e-evolution.com
 */
public class QueryDocType extends POSQuery {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7109518709654253628L;
	
	/**
	 * 	Constructor
	 */
	public QueryDocType (VPOS posPanel) {
		super(posPanel);
	}	//	PosQueryBPartner

	/**	Search Fields		*/
	private POSTextField	f_Name;
	private POSTextField	f_Description;
	/**	Internal Variables	*/
	private int				m_C_DocType_ID;
	private String 			m_DocTypeName;
	/**	Logger				*/
	private static CLogger log = CLogger.getCLogger(QueryDocType.class);
	
	
	/**	Table Column Layout Info			*/
	private static ColumnInfo[] s_layout = new ColumnInfo[] {
		new ColumnInfo(" ", "C_DocType_ID", IDColumn.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "Name"), "Name", String.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "Description"), "Description", String.class), 
		new ColumnInfo(Msg.translate(Env.getCtx(), "SeqNo"), "SeqNo", String.class)
	};
	/**	From Clause							*/
	private static String s_sqlFrom = "C_DocType";
	/** Where Clause						*/
	private static String s_sqlWhere = "IsActive='Y'"; 

	/**
	 * 	Set up Panel
	 */
	protected void init() {
		setTitle(Msg.translate(Env.getCtx(), "C_DocType_ID"));
		//	North
		parameterPanel.setLayout(new MigLayout("fill","", "[50][50][]"));
		parameterPanel.setBorder(new TitledBorder(Msg.getMsg(ctx, "Query")));
		//
		CLabel lname = new CLabel(Msg.translate(ctx, "Name"));
		parameterPanel.add (lname, " growy");
		f_Name = new POSTextField("", posPanel.getKeyboard());
		lname.setLabelFor(f_Name);
		parameterPanel.add(f_Name, "h 30, w 200");
		f_Name.addActionListener(this);
		
		CLabel ldescription = new CLabel(Msg.translate(ctx, "Description"));
		parameterPanel.add (ldescription, " growy");
		f_Description = new POSTextField("", posPanel.getKeyboard());
		lname.setLabelFor(f_Description);
		parameterPanel.add(f_Description, "h 30, w 200");
		f_Description.addActionListener(this);
		//	Center
		posTable.prepareTable (s_layout, s_sqlFrom,
			s_sqlWhere, false, "C_DocType");
		//	
		posTable.growScrollbars();
		SwingUtilities.invokeLater(new Runnable()
		{
			public void run() {
				f_Name.requestFocus();
			}
		});
	}	//	init
	
	
	@Override
	protected void newAction() {
		super.newAction();
		VPOSBPartner t = new VPOSBPartner(new Frame(), 0, posPanel);
		t.setVisible(true);
		m_C_DocType_ID = t.getC_BPartner_ID();
		//	Close
		close();
	}
	
	@Override
	public void editAction() {
		super.editAction();
		VPOSBPartner t = new VPOSBPartner(new Frame(), 1, posPanel);
		select();
		t.loadBPartner(m_C_DocType_ID);
		t.setVisible(true);
		//	Close
		close();
	}
	
	/**
	 * 	Set/display Results
	 *	@param results results
	 */
	private void setResultsFromArray(MDocType[] results) {
		posTable.loadTable(results);
		int rowCount = posTable.getRowCount();
		if (rowCount > 0) {
			posTable.setRowSelectionInterval(0, 0);
			if(rowCount == 1) {
				select();
			}
		}
	}	//	setResults
	
	@Override
	public void setResults(PO[] results) {
		//	Valid Result
		if(results == null
				|| !(results instanceof MDocType[]))
			return;
		//	
		setResultsFromArray((MDocType[]) results);
	}
	
	/**
	 * Load Data BPartner
	 * 
	 * @return void
	 */
	public void loadData() {
		StringBuffer sql = new StringBuffer();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try  {
			this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			sql.append(" SELECT dt.C_DocType_ID, dt.Name, sq.Name, (COALESCE(sq.Prefix, '') || sq.CurrentNext || COALESCE(sq.Suffix, '')) SeqNo")
				.append(" FROM C_DocType dt")
				.append(" LEFT JOIN AD_Sequence sq ON (sq.AD_Sequence_ID = dt.DocNoSequence_ID)")
				.append(" WHERE dt.AD_Client_ID = ? AND dt.AD_Org_ID IN (0, ?)")
				.append(" AND dt.isActive='Y'")
				.append(" AND dt.DocBaseType='SOO'")
				.append(" AND dt.DocSubTypeSO IN(?, ?, ?, ?, ?)")
			    .append(" ORDER BY dt.Name");
			int i = 1;			
			pstm = DB.prepareStatement(sql.toString(), null);
			//	POS
			pstm.setInt(i++, Env.getAD_Client_ID(ctx));
			pstm.setInt(i++, posPanel.getAD_Org_ID());
			pstm.setString(i++, MOrder.DocSubTypeSO_POS);
			pstm.setString(i++, MOrder.DocSubTypeSO_OnCredit);
			pstm.setString(i++, MOrder.DocSubTypeSO_Standard);
			pstm.setString(i++, MOrder.DocSubTypeSO_Prepay);
			pstm.setString(i++, MOrder.DocSubTypeSO_Warehouse);
			//	
			rs = pstm.executeQuery();
			posTable.loadTable(rs);
			int rowNo = posTable.getRowCount();
			if (rowNo > 0) {
				posTable.setRowSelectionInterval(0, 0);
				if(rowNo == 1) {
					select();
				}
			}
		} catch(Exception e) {
			log.severe("QueryTicket.setResults: " + e + " -> " + sql);
		} finally {
			DB.close(rs);
			DB.close(pstm);
			this.setCursor(Cursor.getDefaultCursor());
		}

	}
	
	/**
	 * 	Enable/Set Buttons and set ID
	 */
	protected void select() {
		cleanValues();
		int row = posTable.getSelectedRow();
		boolean enabled = row != -1;
		if (enabled) {
			Integer ID = posTable.getSelectedRowKey();
			if (ID != null) {
				m_C_DocType_ID = ID.intValue();
				m_DocTypeName = (String) posTable.getValueAt(row, 2);
			}
		}
		log.fine("C_BPartner_ID=" + m_C_DocType_ID); 
	}	//	enableButtons

	/**
	 * 	Close.
	 * 	Set Values on other panels and close
	 */
	protected void close() {
		dispose();
	}	//	close
	
	@Override
	public void reset() {
		f_Name.setText(null);
		setResults(new MBPartnerInfo[0]);
		cleanValues();
	}

	@Override
	public void refresh() {
		cleanValues();
		setResults(null);
	}
	
	/**
	 * Clean Values
	 * @return void
	 */
	private void cleanValues() {
		m_C_DocType_ID = -1;
		m_DocTypeName = null;
	}

	@Override
	protected void cancel() {
		cleanValues();
		dispose();
	}

	@Override
	public int getRecord_ID() {
		return m_C_DocType_ID;
	}

	@Override
	public String getValue() {
		return m_DocTypeName;
	}
}	//	PosQueryBPartner