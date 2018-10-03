/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
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
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.compiere.apps.search;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;

import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import org.adempiere.plaf.AdempierePLAF;
import org.compiere.apps.AEnv;
import org.compiere.apps.ALayout;
import org.compiere.apps.ALayoutConstraint;
import org.compiere.grid.ed.VCheckBox;
import org.compiere.grid.ed.VDate;
import org.compiere.grid.ed.VLookup;
import org.compiere.grid.ed.VNumber;
import org.compiere.minigrid.ColumnInfo;
import org.compiere.minigrid.IDColumn;
import org.compiere.minigrid.MiniTable;
import org.compiere.model.MColumn;
import org.compiere.model.MInvoice;
import org.compiere.model.MLookupFactory;
import org.compiere.model.MQuery;
import org.compiere.swing.CLabel;
import org.compiere.swing.CPanel;
import org.compiere.swing.CTextField;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Trx;
import org.compiere.util.Util;

/**
 *  Info Invoice
 *
 *  @author Jorg Janke
 *  @version  $Id: InfoInvoice.java,v 1.2 2006/07/30 00:51:27 jjanke Exp $
 * 
 * @author Teo Sarca, SC ARHIPAC SERVICE SRL
 * 			FR [ 1926882 ] Info Invoice: display Due Date
 *
 * @author Michael McKay, 
 * 				<li>ADEMPIERE-72 VLookup and Info Window improvements
 * 					https://adempiere.atlassian.net/browse/ADEMPIERE-72
 * 				<li>release/380 Fix listeners on some fields and improve query speed for Payment #
 */
public class InfoInvoice extends Info
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2119484421367033632L;

	/**
	 *  Detail Protected Contructor
	 *
	 *  @param frame parent frame
	 *  @param modal modal
	 *  @param WindowNo window no
	 *  @param value query value
	 *  @param multiSelection multiple selections
	 *  @param whereClause where clause
	 */
	@Deprecated
	protected InfoInvoice(Frame frame, boolean modal, int WindowNo, String value,
		boolean multiSelection, String whereClause)
	{
		this(frame, modal, WindowNo, 0, value,
				multiSelection, true, whereClause);
	}
	
	/**
	 *  Detail Protected Constructor
	 *
	 *  @param frame parent frame
	 *  @param modal modal
	 *  @param WindowNo window no
	 *  @param record_id The record ID to find
	 *  @param value query value to find, exclusive of record_id
	 *  @param multiSelection multiple selections
	 *  @param saveResults  True if results will be saved, false for info only
	 *  @param whereClause where clause
	 */
	protected InfoInvoice(Frame frame, boolean modal, int WindowNo, int record_id, String value,
		boolean multiSelection, boolean saveResults, String whereClause)
	{
		super (frame, modal, WindowNo, "i", "C_Invoice_ID", multiSelection, saveResults, whereClause);
		setTitle(Msg.getMsg(Env.getCtx(), "InfoInvoice"));
		//
		StringBuffer where = new StringBuffer("i.IsActive='Y'");
		if (whereClause.length() > 0)
			where.append(" AND ").append(Util.replace(whereClause, "C_Invoice.", "i."));
		setWhereClause(where.toString());
		setTableLayout(s_Layout);
		setFromClause(s_From);
		setOrderClause(s_Order);
		//
		setShowTotals(true);
		//
		statInit();
		initInfo (record_id, value);

		//  To get the focus after the table update
		m_heldLastFocus = fDocumentNo;
		
		//	AutoQuery
		if(autoQuery() || record_id != 0 || (value != null && value.length() > 0 && value != "%"))
			executeQuery();
		
		p_loadedOK = true;

		AEnv.positionCenterWindow(frame, this);	}   //  InfoInvoice

	//  Static Info
	private int fieldID = 0;
	private CLabel lDocumentNo = new CLabel(Msg.translate(Env.getCtx(), "DocumentNo"));
	private CTextField fDocumentNo = new CTextField(10);
	private CLabel lDescription = new CLabel(Msg.translate(Env.getCtx(), "Description"));
	private CTextField fDescription = new CTextField(10);
	private CLabel lBPartner_ID = new CLabel(Msg.translate(Env.getCtx(), "BPartner"));
	private VLookup fBPartner_ID;
	private CLabel lOrder_ID = new CLabel(Msg.translate(Env.getCtx(), "C_Order_ID"));
	private VLookup fOrder_ID;
	private VCheckBox fIsPaid = new VCheckBox ("IsPaid", false, false, true, Msg.translate(Env.getCtx(), "IsPaid"), "", false);
	private VCheckBox fIsSOTrx = new VCheckBox ("IsSOTrx", false, false, true, Msg.translate(Env.getCtx(), "IsSOTrx"), "", false);
	//
	private CLabel lDateFrom = new CLabel(Msg.translate(Env.getCtx(), "DateInvoiced"));
	private VDate fDateFrom = new VDate("DateFrom", false, false, true, DisplayType.Date, Msg.translate(Env.getCtx(), "DateFrom"));
	private CLabel lDateTo = new CLabel("-  ");
	private VDate fDateTo = new VDate("DateTo", false, false, true, DisplayType.Date, Msg.translate(Env.getCtx(), "DateTo"));
	private CLabel lAmtFrom = new CLabel(Msg.translate(Env.getCtx(), "GrandTotal"));
	private VNumber fAmtFrom = new VNumber("AmtFrom", false, false, true, DisplayType.Amount, Msg.translate(Env.getCtx(), "AmtFrom"));
	private CLabel lAmtTo = new CLabel("-  ");
	private VNumber fAmtTo = new VNumber("AmtTo", false, false, true, DisplayType.Amount, Msg.translate(Env.getCtx(), "AmtTo"));

	private int 				m_C_Invoice_ID = 0;

	private MiniTable scheduleTbl = new MiniTable();
	private String m_sqlSchedule;
	private CPanel tablePanel = new CPanel();

	/** From Clause             */
	private static String s_From = " C_Invoice i";
	/** Order Clause             */
	private static String s_Order = " i.DateInvoiced desc, i.DocumentNo";

	/**  Array of Column Info    */
	private static final Info_Column[] s_Layout = {
		new Info_Column(" ", "i.C_Invoice_ID", IDColumn.class),
		new Info_Column(Msg.translate(Env.getCtx(), "C_BPartner_ID"), "(SELECT Name FROM C_BPartner bp WHERE bp.C_BPartner_ID=i.C_BPartner_ID)", String.class),
		new Info_Column(Msg.translate(Env.getCtx(), "DateInvoiced"), "i.DateInvoiced", Timestamp.class),
		new Info_Column(Msg.translate(Env.getCtx(), "DocumentNo"), "i.DocumentNo", String.class),
		new Info_Column(Msg.translate(Env.getCtx(), "C_Currency_ID"), "(SELECT ISO_Code FROM C_Currency c WHERE c.C_Currency_ID=i.C_Currency_ID)", String.class),
		new Info_Column(Msg.translate(Env.getCtx(), "GrandTotal"), "i.GrandTotal",  BigDecimal.class),
		new Info_Column(Msg.translate(Env.getCtx(), "ConvertedAmount"), "currencyBase(i.GrandTotal, i.C_Currency_ID, i.DateAcct, i.AD_Client_ID, i.AD_Org_ID)", BigDecimal.class),
		new Info_Column(Msg.translate(Env.getCtx(), "OpenAmt"), "invoiceOpen(C_Invoice_ID,0)", BigDecimal.class, true, true, null),
		new Info_Column(Msg.translate(Env.getCtx(), "C_PaymentTerm_ID"), "(SELECT pt.Name FROM C_PaymentTerm pt WHERE pt.C_PaymentTerm_ID = i.C_PaymentTerm_ID)", String.class),		
		new Info_Column(Msg.translate(Env.getCtx(), "IsPaid"), "i.IsPaid", Boolean.class),
		new Info_Column(Msg.translate(Env.getCtx(), "IsSOTrx"), "i.IsSOTrx", Boolean.class),
		new Info_Column(Msg.translate(Env.getCtx(), "Description"), "i.Description", String.class),
		new Info_Column(Msg.translate(Env.getCtx(), "POReference"), "i.POReference", String.class),
		new Info_Column(Msg.translate(Env.getCtx(), "DocStatus"), "i.docstatus", String.class),
	};

	//  Invoice payment schedule info
	/** From Clause             */
	private static String s_subFrom = " C_Invoice_v i";
    /** Where Clause						*/
    private static String s_subWhere = "i.C_Invoice_ID = ?";
	private static String numPayments = 	
			  "COALESCE((SELECT COUNT(ps.C_PaymentTerm_ID)"
			+ " 		FROM"
			+ " 			C_PaySchedule ps, C_InvoicePaySchedule cips"
			+ " 		WHERE"
			+ " 			ps.C_PaySchedule_ID = cips.C_PaySchedule_ID"
			+ " 			AND cips.C_INVOICE_ID = i.C_Invoice_ID"
			+ " 			AND cips.duedate <= i.duedate"
			+ " 		GROUP BY ps.C_PaymentTerm_ID),1)  || ' / ' ||"
		    + " 		COALESCE((SELECT COUNT(ps.C_PaymentTerm_ID) AS maxpayno"
		    + " 		    FROM "
			+ " 			C_PaySchedule ps, C_InvoicePaySchedule cips"
			+ " 		WHERE "
			+ " 			ps.C_PaySchedule_ID = cips.C_PaySchedule_ID"
			+ " 			AND cips.C_INVOICE_ID = i.C_Invoice_ID"
			+ " 		GROUP BY ps.C_PaymentTerm_ID),1)";

	/**  Array of Column Info    */
    private static ColumnInfo[] s_subLayout = new ColumnInfo[] {
		new ColumnInfo(" ", "i.C_InvoicePaySchedule_ID", IDColumn.class),
		new ColumnInfo(Msg.getMsg(Env.getCtx(), "Payment #"), numPayments, String.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "DueDate"), "i.DueDate", Timestamp.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "C_Currency_ID"), "(SELECT ISO_Code FROM C_Currency c WHERE c.C_Currency_ID=i.C_Currency_ID)", String.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "GrandTotal"), "i.GrandTotal",  BigDecimal.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "ConvertedAmount"), "currencyBase(i.GrandTotal, i.C_Currency_ID, i.DateAcct, i.AD_Client_ID, i.AD_Org_ID)", BigDecimal.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "OpenAmt"), "invoiceOpen(C_Invoice_ID,C_InvoicePaySchedule_ID)", BigDecimal.class, true, true, null),
		new ColumnInfo(Msg.translate(Env.getCtx(), "IsPaid"), "CASE WHEN invoiceOpen(C_Invoice_ID,C_InvoicePaySchedule_ID) <= 0 THEN 'Y' ELSE 'N' END", Boolean.class)
	};

	/**
	 *	Static Setup - add fields to parameterPanel
	 */
	private void statInit()
	{
		lDocumentNo.setLabelFor(fDocumentNo);
		fDocumentNo.setBackground(AdempierePLAF.getInfoBackground());
		fDocumentNo.addActionListener(this);
		lDescription.setLabelFor(fDescription);
		fDescription.setBackground(AdempierePLAF.getInfoBackground());
		fDescription.addActionListener(this);
		fIsPaid.setSelected(false);
		fIsPaid.addActionListener(this);
		fIsSOTrx.setSelected(!"N".equals(Env.getContext(Env.getCtx(), p_WindowNo, "IsSOTrx")));
		fIsSOTrx.addActionListener(this);
		//
		//	C_Invoice.C_BPartner_ID
		fBPartner_ID = new VLookup("C_BPartner_ID", false, false, true,
				MLookupFactory.get (Env.getCtx(), p_WindowNo, 0,  
						MColumn.getColumn_ID(MInvoice.Table_Name, MInvoice.COLUMNNAME_C_BPartner_ID),
						DisplayType.Search));
		lBPartner_ID.setLabelFor(fBPartner_ID);
		fBPartner_ID.setBackground(AdempierePLAF.getInfoBackground());
		fBPartner_ID.addActionListener(this);
		//	C_Invoice.C_Order_ID
		fOrder_ID = new VLookup("C_Order_ID", false, false, true, 
	        		MLookupFactory.get(Env.getCtx(), p_WindowNo,0, 
	        				MColumn.getColumn_ID(MInvoice.Table_Name, MInvoice.COLUMNNAME_C_Order_ID), 
	        				DisplayType.Search));
		lOrder_ID.setLabelFor(fOrder_ID);
		fOrder_ID.setBackground(AdempierePLAF.getInfoBackground());
		fOrder_ID.addActionListener(this);
		//
		lDateFrom.setLabelFor(fDateFrom);
		fDateFrom.setBackground(AdempierePLAF.getInfoBackground());
		fDateFrom.setToolTipText(Msg.translate(Env.getCtx(), "DateFrom"));
		lDateTo.setLabelFor(fDateTo);
		fDateTo.setBackground(AdempierePLAF.getInfoBackground());
		fDateTo.setToolTipText(Msg.translate(Env.getCtx(), "DateTo"));
		lAmtFrom.setLabelFor(fAmtFrom);
		fAmtFrom.setBackground(AdempierePLAF.getInfoBackground());
		fAmtFrom.setToolTipText(Msg.translate(Env.getCtx(), "AmtFrom"));
		lAmtTo.setLabelFor(fAmtTo);
		fAmtTo.setBackground(AdempierePLAF.getInfoBackground());
		fAmtTo.setToolTipText(Msg.translate(Env.getCtx(), "AmtTo"));

		//
		CPanel amtPanel = new CPanel();
		CPanel datePanel = new CPanel();
		
		amtPanel.setLayout(new ALayout(0, 0, true));
		amtPanel.add(fAmtFrom, new ALayoutConstraint(0,0));
		amtPanel.add(lAmtTo, null);
		amtPanel.add(fAmtTo, null);

		datePanel.setLayout(new ALayout(0, 0, true));
		datePanel.add(fDateFrom, new ALayoutConstraint(0,0));
		datePanel.add(lDateTo, null);
		datePanel.add(fDateTo, null);

		//  First Row
		p_criteriaGrid.add(lDocumentNo, new ALayoutConstraint(0,0));
		p_criteriaGrid.add(fDocumentNo, null);
		p_criteriaGrid.add(lBPartner_ID, null);
		p_criteriaGrid.add(fBPartner_ID, null);
		p_criteriaGrid.add(fIsSOTrx, new ALayoutConstraint(0,5));
		//  2nd Row
		p_criteriaGrid.add(lDescription, new ALayoutConstraint(1,0));
		p_criteriaGrid.add(fDescription, null);
		p_criteriaGrid.add(lDateFrom, null);
		p_criteriaGrid.add(datePanel, null);
		p_criteriaGrid.add(fIsPaid, new ALayoutConstraint(1,5));
		//  3rd Row
		p_criteriaGrid.add(lOrder_ID, new ALayoutConstraint(2,0));
		p_criteriaGrid.add(fOrder_ID, null);
		p_criteriaGrid.add(lAmtFrom, null);
		p_criteriaGrid.add(amtPanel, null);
		
		m_sqlSchedule = scheduleTbl.prepareTable(s_subLayout, s_subFrom, s_subWhere, false, "i");
		scheduleTbl.setRowSelectionAllowed(true);
		scheduleTbl.setMultiSelection(false);
		scheduleTbl.addMouseListener(this);
		scheduleTbl.setShowTotals(true);
		scheduleTbl.autoSize();

        tablePanel.setPreferredSize(new Dimension(INFO_WIDTH, SCREEN_HEIGHT > 600 ? 255 : 110));
        tablePanel.setLayout(new BorderLayout());
        tablePanel.add(new JScrollPane(scheduleTbl), BorderLayout.CENTER);        

        //  Add the details to the p_detailPanel
		p_detailTaskPane.setTitle(Msg.translate(Env.getCtx(), "C_InvoicePaySchedule_ID"));        
        p_detailTaskPane.add(tablePanel, BorderLayout.CENTER);
        p_detailTaskPane.setVisible(true);
	}	//	statInit

	/**
	 *	General Init
	 */
	protected void initInfo (int record_id, String value)
	{
		//
		if (!(record_id == 0) && value != null && value.length() > 0)
		{
			log.severe("Received both a record_id and a value: " + record_id + " - " + value);
		}

		//  Set values
        if (!(record_id == 0))  // A record is defined
        {
        	fieldID = record_id;
        	
        	// Have to set isPaid and isSOTrx to match or the query will return no results
			String trxName = Trx.createTrxName();
			MInvoice mi = new MInvoice(Env.getCtx(),record_id,trxName);
        	fIsPaid.setSelected(mi.isPaid());
        	fIsSOTrx.setSelected(mi.isSOTrx());
        	mi = null;
        	Trx.get(trxName, false).close();
        }
        else  // Try to find other criteria in the context
        {
			String id;
			
			//  C_BPartner_ID
			id = Env.getContext(Env.getCtx(), p_WindowNo, p_TabNo, "C_BPartner_ID", true);
			if (id != null && id.length() != 0 && (new Integer(id).intValue() > 0))
				fBPartner_ID.setValue(new Integer(id));
			
			//  C_Order_ID
			id = Env.getContext(Env.getCtx(), p_WindowNo, p_TabNo, "C_Order_ID", true);
			if (id != null && id.length() != 0 && (new Integer(id).intValue() > 0))
				fOrder_ID.setValue(new Integer(id));

			//  IsSOTrx - Window context
			id = Env.getContext(Env.getCtx(), p_WindowNo, "IsSOTrx", true);
			if (id != null && id.length() != 0 && (id == "Y" || id == "N"))
			{
				fIsSOTrx.setSelected(id == "Y");
			}

			//  The value passed in from the field
			if (value != null && value.length() > 0)
			{
				fDocumentNo.setValue(value);			
			}
			else
			{
				//  C_Invoice_ID
				id = Env.getContext(Env.getCtx(), p_WindowNo, p_TabNo, "C_Invoice_ID", true);
				if (id != null && id.length() != 0  && (new Integer(id).intValue() > 0))
				{
					fieldID = new Integer(id).intValue();
					
		        	// Have to set isPaid and isSOTrx to match or the query will return no results
					String trxName = Trx.createTrxName();
					MInvoice mi = new MInvoice(Env.getCtx(),record_id,trxName);
		        	fIsPaid.setSelected(mi.isPaid());
		        	fIsSOTrx.setSelected(mi.isSOTrx());
		        	mi = null;
		        	Trx.get(trxName, false).close();
				}
				
			}
        }
        
		return;
	}	//	initInfo

	
	/**************************************************************************
	 *	Construct SQL Where Clause and define parameters.
	 *  (setParameters needs to set parameters)
	 *  Includes first AND
	 *  @return sql
	 */
	protected String getSQLWhere()
	{
		StringBuffer sql = new StringBuffer();
		//  => ID
		if(isResetRecordID())
			fieldID = 0;
		if(!(fieldID == 0))
			sql.append(" AND i.C_Invoice_ID = ?");
		//
		if (isValidSQLText(fDocumentNo))
			sql.append(" AND UPPER(i.DocumentNo) LIKE ?");
		//
		if (isValidSQLText(fDescription))
			sql.append(" AND UPPER(i.Description) LIKE ?");
		//
		if (fBPartner_ID.getValue() != null)
			sql.append(" AND i.C_BPartner_ID=?");
		//
		if (fOrder_ID.getValue() != null)
			sql.append(" AND i.C_Order_ID=?");
		//
		if (fDateFrom.getValue() != null || fDateTo.getValue() != null)
		{
			Timestamp from = (Timestamp)fDateFrom.getValue();
			Timestamp to = (Timestamp)fDateTo.getValue();
			if (from == null && to != null)
				sql.append(" AND TRUNC(i.DateInvoiced, 'DD') <= ?");
			else if (from != null && to == null)
				sql.append(" AND TRUNC(i.DateInvoiced, 'DD') >= ?");
			else if (from != null && to != null)
				sql.append(" AND TRUNC(i.DateInvoiced, 'DD') BETWEEN ? AND ?");
		}
		//
		if (fAmtFrom.getValue() != null || fAmtTo.getValue() != null)
		{
			BigDecimal from = (BigDecimal)fAmtFrom.getValue();
			BigDecimal to = (BigDecimal)fAmtTo.getValue();
			if (from == null && to != null)
				sql.append(" AND i.GrandTotal <= ?");
			else if (from != null && to == null)
				sql.append(" AND i.GrandTotal >= ?");
			else if (from != null && to != null)
				sql.append(" AND i.GrandTotal BETWEEN ? AND ?");
		}
		//
		sql.append(" AND i.IsPaid=? AND i.IsSOTrx=?");

	//	log.fine( "InfoInvoice.setWhereClause", sql.toString());
		return sql.toString();
	}	//	getSQLWhere

	/**
	 *  Set Parameters for Query.
	 *  (as defined in getSQLWhere)
	 *  @param pstmt statement
	 *  @param forCount for counting records
	 *  @throws SQLException
	 */
	protected void setParameters(PreparedStatement pstmt, boolean forCount) throws SQLException
	{
		int index = 1;
		//  => ID
		if (!(fieldID == 0))
			pstmt.setInt(index++, fieldID);
		if (isValidSQLText(fDocumentNo))
			pstmt.setString(index++, getSQLText(fDocumentNo));
		if (isValidSQLText(fDescription))
			pstmt.setString(index++, getSQLText(fDescription));
		//
		if (fBPartner_ID.getValue() != null)
		{
			Integer bp = (Integer)fBPartner_ID.getValue();
			pstmt.setInt(index++, bp.intValue());
			log.fine("BPartner=" + bp);
		}
		//
		if (fOrder_ID.getValue() != null)
		{
			Integer order = (Integer)fOrder_ID.getValue();
			pstmt.setInt(index++, order.intValue());
			log.fine("Order=" + order);
		}
		//
		if (fDateFrom.getValue() != null || fDateTo.getValue() != null)
		{
			Timestamp from = (Timestamp)fDateFrom.getValue();
			Timestamp to = (Timestamp)fDateTo.getValue();
			log.fine("Date From=" + from + ", To=" + to);
			if (from == null && to != null)
				pstmt.setTimestamp(index++, to);
			else if (from != null && to == null)
				pstmt.setTimestamp(index++, from);
			else if (from != null && to != null)
			{
				pstmt.setTimestamp(index++, from);
				pstmt.setTimestamp(index++, to);
			}
		}
		//
		if (fAmtFrom.getValue() != null || fAmtTo.getValue() != null)
		{
			BigDecimal from = (BigDecimal)fAmtFrom.getValue();
			BigDecimal to = (BigDecimal)fAmtTo.getValue();
			log.fine("Amt From=" + from + ", To=" + to);
			if (from == null && to != null)
				pstmt.setBigDecimal(index++, to);
			else if (from != null && to == null)
				pstmt.setBigDecimal(index++, from);
			else if (from != null && to != null)
			{
				pstmt.setBigDecimal(index++, from);
				pstmt.setBigDecimal(index++, to);
			}
		}
			pstmt.setString(index++, fIsPaid.isSelected() ? "Y" : "N");
			pstmt.setString(index++, fIsSOTrx.isSelected() ? "Y" : "N");
	}   //  setParameters

	/**
	 * A record was selected - take action to sync subordinate tables if any
	 */
	protected void recordSelected(int key)
	{
		//  Found and selected the same record or selected the first record
    	if (m_C_Invoice_ID != key)
    	{
    		refresh();
    	}
		p_detailTaskPane.setCollapsed(false);
		return;
	}
	/**
	 * No record was selected - take action to sync subordinate tables if any
	 */
	protected void noRecordSelected()
	{
		//  Nothing was selected, or the query is empty
		//  - close the panel
		m_C_Invoice_ID = 0;
		p_detailTaskPane.setCollapsed(true);
		return;
	}

	/**
	 * 	Refresh Query
	 */
	private void refresh()
	{
		//  Invoke later to not delay events.
		SwingUtilities.invokeLater(new Runnable(){public void run()
		{
	    	String sql;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			int leadRowKey = p_table.getLeadRowKey();
			
	    	if (m_C_Invoice_ID != leadRowKey)
			{
	    		m_C_Invoice_ID = leadRowKey;
	    		
	    		//  Payment Schedule table
				sql = m_sqlSchedule;
		
				log.finest(sql);
				try
				{
					pstmt = DB.prepareStatement(sql, null);
					pstmt.setInt(1, m_C_Invoice_ID);
					rs = pstmt.executeQuery();
					scheduleTbl.loadTable(rs);
					rs.close();
				}
				catch (Exception e)
				{
					log.log(Level.WARNING, sql, e);
				}
				finally
				{
					DB.close(rs, pstmt);
					rs = null; pstmt = null;
				}
			}
			
		}});
	}	//	refresh


	/**
	 *	Zoom
	 */
	protected void zoom(int record_ID)
	{
		log.info( "InfoInvoice.zoom");
		// Integer C_Invoice_ID = getSelectedRowKey();
		Integer C_Invoice_ID = record_ID;
		if (C_Invoice_ID == null)
			return;
		MQuery query = new MQuery("C_Invoice");
		query.addRestriction("C_Invoice_ID", MQuery.EQUAL, C_Invoice_ID);
		query.setRecordCount(1);
		int AD_WindowNo = getAD_Window_ID("C_Invoice", fIsSOTrx.isSelected());
		zoom (AD_WindowNo, query);
	}	//	zoom

	/**
	 *	Has Zoom
	 *  @return true
	 */
	protected boolean hasZoom()
	{
		return true;
	}	//	hasZoom

	/**
	 *	Save Selection Settings
	 */
	protected void saveSelectionDetail()
	{
		//  publish for Callout to read
		Integer ID = getSelectedRowKey();
		Env.setContext(Env.getCtx(), p_WindowNo, Env.TAB_INFO, "C_Invoice_ID", ID == null ? "0" : ID.toString());
		//
		int C_InvoicePaySchedule_ID = scheduleTbl.getSelectedRowKey();
		if (C_InvoicePaySchedule_ID <= 0)	//	not selected
			Env.setContext(Env.getCtx(), p_WindowNo, Env.TAB_INFO, "C_InvoicePaySchedule_ID", "0");
		else
			Env.setContext(Env.getCtx(), p_WindowNo, Env.TAB_INFO, "C_InvoicePaySchedule_ID", String.valueOf(C_InvoicePaySchedule_ID));
	}	//	saveSelectionDetail
	
	/**
	 * Does the parameter panel have outstanding changes that have not been
	 * used in a query?
	 * @return true if there are outstanding changes.
	 */
	protected boolean hasOutstandingChanges()
	{
		//  All the tracked fields
		return(
				fDocumentNo.hasChanged()	||
				fDescription.hasChanged()	||
				fIsPaid.hasChanged()	||
				fIsSOTrx.hasChanged()	||
				fBPartner_ID.hasChanged()	||
				fOrder_ID.hasChanged()	||
				fAmtFrom.hasChanged() ||
				fAmtTo.hasChanged() ||
				fDateFrom.hasChanged()	||
				fDateTo.hasChanged());
	}
	/**
	 * Record outstanding changes by copying the current
	 * value to the oldValue on all fields
	 */
	protected void setFieldOldValues()
	{
		fAmtFrom.set_oldValue();
		fAmtTo.set_oldValue();
		fDocumentNo.set_oldValue();
		fDescription.set_oldValue();
		fIsPaid.set_oldValue();
		fIsSOTrx.set_oldValue();
		fBPartner_ID.set_oldValue();
		fOrder_ID.set_oldValue();
		fDateFrom.set_oldValue();
		fDateTo.set_oldValue();
		return;
	}
    /**
	 *  Clear all fields and set default values in check boxes
	 */
	protected void clearParameters()
	{
		//  Clear fields and set defaults
		Object nullObject = null;
		fDocumentNo.setValue("");
		fDescription.setValue("");
		fIsPaid.setSelected(false);
		fBPartner_ID.setValue(null);
		fOrder_ID.setValue(null);
		fDateFrom.setValue(nullObject);
		fDateTo.setValue(nullObject);
		fIsSOTrx.setSelected(!"N".equals(Env.getContext(Env.getCtx(), p_WindowNo, "IsSOTrx")));
	}
}   //  InfoInvoice
