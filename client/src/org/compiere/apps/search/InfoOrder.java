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

import java.awt.Frame;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.adempiere.plaf.AdempierePLAF;
import org.compiere.apps.ALayout;
import org.compiere.apps.ALayoutConstraint;
import org.compiere.grid.ed.VCheckBox;
import org.compiere.grid.ed.VDate;
import org.compiere.grid.ed.VLookup;
import org.compiere.grid.ed.VNumber;
import org.compiere.minigrid.IDColumn;
import org.compiere.model.MLookupFactory;
import org.compiere.model.MQuery;
import org.compiere.model.MOrder;
import org.compiere.swing.CLabel;
import org.compiere.swing.CPanel;
import org.compiere.swing.CTextField;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Trx;
import org.compiere.util.Util;

/**
 *  Info Order
 *
 *  @author Jorg Janke
 *  @version  $Id: InfoOrder.java,v 1.2 2006/07/30 00:51:27 jjanke Exp $
 *
 * @author Michael McKay, 
 * 				<li>ADEMPIERE-72 VLookup and Info Window improvements
 * 					https://adempiere.atlassian.net/browse/ADEMPIERE-72
 */
public class InfoOrder extends Info
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2246871771555208114L;

	/**
	 *  Detail Protected Constructor
	 *  @param frame parent frame
	 *  @param modal modal
	 *  @param WindowNo window no
	 *  @param value query value
	 *  @param multiSelection multiple selections
	 *  @param whereClause where clause
	 */
	@Deprecated
	protected InfoOrder(Frame frame, boolean modal, int WindowNo, String value,
		boolean multiSelection, String whereClause)
	{
		this(frame, modal, WindowNo, 0, value,
				multiSelection, true, whereClause);
	}
	
	/**
	 *  Detail Protected Constructor
	 *  @param frame parent frame
	 *  @param modal modal
	 *  @param WindowNo window no
	 *  @param record_id The record ID to find
	 *  @param value query value to find, exclusive of record_id
	 *  @param multiSelection multiple selections
	 *  @param saveResults  True if results will be saved, false for info only
	 *  @param whereClause where clause
	 */
	protected InfoOrder(Frame frame, boolean modal, int WindowNo, int record_id, String value,
		boolean multiSelection, boolean saveResults, String whereClause)
	{
		super (frame, modal, WindowNo, "o", "C_Order_ID", multiSelection, saveResults, whereClause);
		log.info( "InfoOrder");
		setTitle(Msg.getMsg(Env.getCtx(), "InfoOrder"));
		//
		try
		{
			statInit();
			p_loadedOK = initInfo (record_id, value);
		}
		catch (Exception e)
		{
			return;
		}
		//
		int no = p_table.getRowCount();
		setStatusLine(Integer.toString(no) + " " + Msg.getMsg(Env.getCtx(), "SearchRows_EnterQuery"), false);
		setStatusDB(Integer.toString(no));
		
		//  Auto query
		if(autoQuery() || record_id != 0 || (value != null && value.length() > 0 && value != "%"))
			executeQuery();
		//
		pack();
		//	Focus
		fDocumentNo.requestFocus();
	}   //  InfoOrder

	//  Static Info
	private int fieldID = 0;
	private CLabel lDocumentNo = new CLabel(Msg.translate(Env.getCtx(), "DocumentNo"));
	private CTextField fDocumentNo = new CTextField(10);
	private CLabel lDescription = new CLabel(Msg.translate(Env.getCtx(), "Description"));
	private CTextField fDescription = new CTextField(10);
	private CLabel lPOReference = new CLabel(Msg.translate(Env.getCtx(), "POReference"));
	private CTextField fPOReference = new CTextField(10);
	//
	private CLabel lBPartner_ID = new CLabel(Msg.translate(Env.getCtx(), "BPartner"));
	private VLookup fBPartner_ID;
	//
	private CLabel lDateFrom = new CLabel(Msg.translate(Env.getCtx(), "DateOrdered"));
	private VDate fDateFrom = new VDate("DateFrom", false, false, true, DisplayType.Date, Msg.translate(Env.getCtx(), "DateFrom"));
	private CLabel lDateTo = new CLabel("- ");
	private VDate fDateTo = new VDate("DateTo", false, false, true, DisplayType.Date, Msg.translate(Env.getCtx(), "DateTo"));
	private CLabel lAmtFrom = new CLabel(Msg.translate(Env.getCtx(), "GrandTotal"));
	private VNumber fAmtFrom = new VNumber("AmtFrom", false, false, true, DisplayType.Amount, Msg.translate(Env.getCtx(), "AmtFrom"));
	private CLabel lAmtTo = new CLabel("- ");
	private VNumber fAmtTo = new VNumber("AmtTo", false, false, true, DisplayType.Amount, Msg.translate(Env.getCtx(), "AmtTo"));
	private VCheckBox fIsSOTrx = new VCheckBox ("IsSOTrx", false, false, true, Msg.translate(Env.getCtx(), "IsSOTrx"), "", false);
	private VCheckBox fIsDelivered = new VCheckBox("IsDelivered", false, false, true, Msg.translate(Env.getCtx(), "IsDelivered"), "", false);

	/**  Array of Column Info    */
	private static final Info_Column[] s_invoiceLayout = {
		new Info_Column(" ", "o.C_Order_ID", IDColumn.class),
		new Info_Column(Msg.translate(Env.getCtx(), "C_BPartner_ID"), "(SELECT Name FROM C_BPartner bp WHERE bp.C_BPartner_ID=o.C_BPartner_ID)", String.class),
		new Info_Column(Msg.translate(Env.getCtx(), "DateOrdered"), "o.DateOrdered", Timestamp.class),
		new Info_Column(Msg.translate(Env.getCtx(), "DocumentNo"), "o.DocumentNo", String.class),
		new Info_Column(Msg.translate(Env.getCtx(), "C_Currency_ID"), "(SELECT ISO_Code FROM C_Currency c WHERE c.C_Currency_ID=o.C_Currency_ID)", String.class),
		new Info_Column(Msg.translate(Env.getCtx(), "GrandTotal"), "o.GrandTotal",  BigDecimal.class),
		new Info_Column(Msg.translate(Env.getCtx(), "ConvertedAmount"), "currencyBase(o.GrandTotal,o.C_Currency_ID,o.DateAcct, o.AD_Client_ID,o.AD_Org_ID)", BigDecimal.class),
		new Info_Column(Msg.translate(Env.getCtx(), "IsSOTrx"), "o.IsSOTrx", Boolean.class),
		new Info_Column(Msg.translate(Env.getCtx(), "Description"), "o.Description", String.class),
		new Info_Column(Msg.translate(Env.getCtx(), "POReference"), "o.POReference", String.class),
		new Info_Column(Msg.translate(Env.getCtx(), "IsDelivered"), "o.IsDelivered", Boolean.class),
	};

	/**
	 *	Static Setup - add fields to parameterPanel
	 *  @throws Exception if Lookups cannot be initialized
	 */
	private void statInit() throws Exception
	{
		lDocumentNo.setLabelFor(fDocumentNo);
		fDocumentNo.setBackground(AdempierePLAF.getInfoBackground());
		fDocumentNo.addActionListener(this);
		lDescription.setLabelFor(fDescription);
		fDescription.setBackground(AdempierePLAF.getInfoBackground());
		fDescription.addActionListener(this);
		lPOReference.setLabelFor(fPOReference);
		fPOReference.setBackground(AdempierePLAF.getInfoBackground());
		fPOReference.addActionListener(this);
		fIsSOTrx.setSelected(!"N".equals(Env.getContext(Env.getCtx(), p_WindowNo, "IsSOTrx")));
		fIsSOTrx.addActionListener(this);
		fIsDelivered.setSelected(false);
		fIsDelivered.addActionListener(this);
		//
		fBPartner_ID = new VLookup("C_BPartner_ID", false, false, true,
			MLookupFactory.get (Env.getCtx(), p_WindowNo, 0, 3499, DisplayType.Search));
		lBPartner_ID.setLabelFor(fBPartner_ID);
		fBPartner_ID.setBackground(AdempierePLAF.getInfoBackground());
		fBPartner_ID.addPropertyChangeListener(this);
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

		parameterPanel.setLayout(new ALayout());
		//  First Row
		parameterPanel.add(lDocumentNo, new ALayoutConstraint(0,0));
		parameterPanel.add(fDocumentNo, null);
		parameterPanel.add(lDescription, null);
		parameterPanel.add(fDescription, null);
		parameterPanel.add(fIsSOTrx, new ALayoutConstraint(0,4));

		//  2nd Row
		parameterPanel.add(lBPartner_ID, new ALayoutConstraint(1,0));
		parameterPanel.add(fBPartner_ID, null);
		parameterPanel.add(lDateFrom, null);
		parameterPanel.add(datePanel, null);
		parameterPanel.add(fIsDelivered, new ALayoutConstraint(1,4));

		//  3rd Row
		parameterPanel.add(lPOReference, new ALayoutConstraint(2,0));
		parameterPanel.add(fPOReference, null);
		parameterPanel.add(lAmtFrom, null);
		parameterPanel.add(amtPanel, null);
	}	//	statInit

	/**
	 *	General Init
	 *	@return true, if success
	 */
	private boolean initInfo (int record_id, String value)
	{

		//  prepare table
		StringBuffer where = new StringBuffer("o.IsActive='Y'");
		if (p_whereClause.length() > 0)
			where.append(" AND ").append(Util.replace(p_whereClause, "C_Order.", "o."));
		prepareTable(s_invoiceLayout,
			" C_Order o",
			where.toString(),
			"2,3,4");
		
		if (!(record_id == 0) && value != null && value.length() > 0)
		{
			log.severe("Received both a record_id and a value: " + record_id + " - " + value);
		}

		if (record_id != 0)
		{
			fieldID = record_id;
			
			// Have to set boolean fields in query
			String trxName = Trx.createTrxName();
			MOrder o = new MOrder(Env.getCtx(),record_id,trxName);
			fIsSOTrx.setValue(o.isSOTrx());
			fIsDelivered.setValue(o.isDelivered());
			o = null;
			Trx.get(trxName, false).close();	
		}
		else  // Try to find other criteria in the context
		{
			String id;
			
			//  C_BPartner_ID - restrict the search to the current BPartner
			id = Env.getContext(Env.getCtx(), p_WindowNo, p_TabNo, "C_BPartner_ID", true);
			if (id != null && id.length() != 0 && (new Integer(id).intValue() > 0))
				fBPartner_ID.setValue(new Integer(id));

			//  The value passed in from the field
			if (value != null && value.length() > 0)
			{
				fDocumentNo.setValue(value);
			}
			else
			{
				//  C_Order_ID
				id = Env.getContext(Env.getCtx(), p_WindowNo, p_TabNo, "C_Order_ID", true);
				if (id != null && id.length() != 0 && (new Integer(id).intValue() > 0))
				{
					fieldID = new Integer(id).intValue();

					// Have to set boolean fields in query
					String trxName = Trx.createTrxName();
					MOrder o = new MOrder(Env.getCtx(),record_id,trxName);
					fIsSOTrx.setValue(o.isSOTrx());
					fIsDelivered.setValue(o.isDelivered());
					o = null;
					Trx.get(trxName, false).close();	
				}
			}
		}
		
		return true;
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
			sql.append(" AND o.C_Order_ID = ?");
		//
		if (isValidSQLText(fDocumentNo))
			sql.append(" AND UPPER(o.DocumentNo) LIKE ?");
		//
		if (isValidSQLText(fDescription))
			sql.append(" AND UPPER(o.Description) LIKE ?");
		//
		if (isValidSQLText(fPOReference))
			sql.append(" AND UPPER(o.POReference) LIKE ?");
		//
		if (fBPartner_ID.getValue() != null)
			sql.append(" AND o.C_BPartner_ID=?");
		//
		if (fDateFrom.getValue() != null || fDateTo.getValue() != null)
		{
			Timestamp from = (Timestamp)fDateFrom.getValue();
			Timestamp to = (Timestamp)fDateTo.getValue();
			if (from == null && to != null)
				sql.append(" AND TRUNC(o.DateOrdered, 'DD') <= ?");
			else if (from != null && to == null)
				sql.append(" AND TRUNC(o.DateOrdered, 'DD') >= ?");
			else if (from != null && to != null)
				sql.append(" AND TRUNC(o.DateOrdered, 'DD') BETWEEN ? AND ?");
		}
		//
		if (fAmtFrom.getValue() != null || fAmtTo.getValue() != null)
		{
			BigDecimal from = (BigDecimal)fAmtFrom.getValue();
			BigDecimal to = (BigDecimal)fAmtTo.getValue();
			if (from == null && to != null)
				sql.append(" AND o.GrandTotal <= ?");
			else if (from != null && to == null)
				sql.append(" AND o.GrandTotal >= ?");
			else if (from != null && to != null)
				sql.append(" AND o.GrandTotal BETWEEN ? AND ?");
		}
		sql.append(" AND o.IsSOTrx=?");
		sql.append(" AND o.IsDelivered=?");

		log.finer(sql.toString());
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
		if (isValidSQLText(fPOReference))
			pstmt.setString(index++, getSQLText(fPOReference));
		//
		if (fBPartner_ID.getValue() != null)
		{
			Integer bp = (Integer)fBPartner_ID.getValue();
			pstmt.setInt(index++, bp.intValue());
			log.fine("BPartner=" + bp);
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
		pstmt.setString(index++, fIsSOTrx.isSelected() ? "Y" : "N");
		pstmt.setString(index++, fIsDelivered.isSelected() ? "Y" : "N");
	}   //  setParameters

	/**
	 *  Get SQL WHERE parameter
	 *  @param f field
	 *  @return sql
	 */


	/**
	 *	Zoom
	 */
	protected void zoom(int record_ID)
	{
		log.info("");
		Integer C_Order_ID = record_ID;
		if (C_Order_ID == null)
			return;
		MQuery query = new MQuery("C_Order");
		query.addRestriction("C_Order_ID", MQuery.EQUAL, C_Order_ID);
		query.setRecordCount(1);
		int AD_WindowNo = getAD_Window_ID("C_Order", fIsSOTrx.isSelected());
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
	
}   //  InfoOrder
