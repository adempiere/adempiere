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
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.adempiere.plaf.AdempierePLAF;
import org.compiere.apps.AEnv;
import org.compiere.apps.ALayout;
import org.compiere.apps.ALayoutConstraint;
import org.compiere.grid.ed.VCheckBox;
import org.compiere.grid.ed.VDate;
import org.compiere.grid.ed.VLookup;
import org.compiere.minigrid.IDColumn;
import org.compiere.model.MColumn;
import org.compiere.model.MInOut;
import org.compiere.model.MLookupFactory;
import org.compiere.model.MQuery;
import org.compiere.swing.CLabel;
import org.compiere.swing.CPanel;
import org.compiere.swing.CTextField;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Util;


/**
 *  Info InOut
 *
 *  @author Jorg Janke
 *  @version  $Id: InfoInOut.java,v 1.2 2006/07/30 00:51:27 jjanke Exp $
 *
 * @author Michael McKay, 
 * 				<li>ADEMPIERE-72 VLookup and Info Window improvements
 * 					https://adempiere.atlassian.net/browse/ADEMPIERE-72
 */
public class InfoInOut extends Info
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2066307179999903184L;

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
	protected InfoInOut(Frame frame, boolean modal, int WindowNo, String value,
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
	protected InfoInOut(Frame frame, boolean modal, int WindowNo, int record_id, String value,
		boolean multiSelection, boolean saveResults, String whereClause)
	{
		super (frame, modal, WindowNo, "i", "M_InOut_ID", multiSelection, saveResults, whereClause);
		log.info( "InfoInOut");
		setTitle(Msg.getMsg(Env.getCtx(), "InfoInOut"));
		//
		StringBuffer where = new StringBuffer("i.IsActive='Y'");
		if (whereClause.length() > 0)
			where.append(" AND ").append(Util.replace(whereClause, "M_InOut.", "i."));
		setWhereClause(where.toString());
		setTableLayout(s_Layout);
		setFromClause(s_From);
		setOrderClause(s_Order);
		//
		statInit();
		initInfo (record_id, value);

		//  To get the focus after the table update
		m_heldLastFocus = fDocumentNo;
		
		//	AutoQuery
		if(autoQuery() || record_id != 0 || (value != null && value.length() > 0 && value != "%"))
			executeQuery();
		
		p_loadedOK = true;

		AEnv.positionCenterWindow(frame, this);	
		
	}   //  InfoInOut


	//  Static Info
	int fieldID = 0;
	private CLabel lDocumentNo = new CLabel(Msg.translate(Env.getCtx(), "DocumentNo"));
	private CTextField fDocumentNo = new CTextField(10);
	private CLabel lDescription = new CLabel(Msg.translate(Env.getCtx(), "Description"));
	private CTextField fDescription = new CTextField(10);
	private CLabel lPOReference = new CLabel(Msg.translate(Env.getCtx(), "POReference"));
	private CTextField fPOReference = new CTextField(10);
	//
	private CLabel lBPartner_ID = new CLabel(Msg.translate(Env.getCtx(), "BPartner"));
	private VLookup fBPartner_ID;
	private CLabel lShipper_ID = new CLabel(Msg.translate(Env.getCtx(), "Shipper"));
	private VLookup fShipper_ID;
	//
	private CLabel lDateFrom = new CLabel(Msg.translate(Env.getCtx(), "MovementDate"));
	private VDate fDateFrom = new VDate("DateFrom", false, false, true, DisplayType.Date, Msg.translate(Env.getCtx(), "DateFrom"));
	private CLabel lDateTo = new CLabel("- ");
	private VDate fDateTo = new VDate("DateTo", false, false, true, DisplayType.Date, Msg.translate(Env.getCtx(), "DateTo"));
	private VCheckBox fIsSOTrx = new VCheckBox ("IsSOTrx", false, false, true, Msg.translate(Env.getCtx(), "IsSOTrx"), "", false);

	/** From Clause             */
	private static String s_From = " M_InOut i";
	/** Order Clause             */
	private static String s_Order = " i.MovementDate desc, i.DocumentNo";

	/**  Array of Column Info    */
	private static final Info_Column[] s_Layout = {
		new Info_Column(" ", "i.M_InOut_ID", IDColumn.class),
		new Info_Column(Msg.translate(Env.getCtx(), "C_BPartner_ID"), "(SELECT Name FROM C_BPartner bp WHERE bp.C_BPartner_ID=i.C_BPartner_ID)", String.class),
		new Info_Column(Msg.translate(Env.getCtx(), "MovementDate"), "i.MovementDate", Timestamp.class),
		new Info_Column(Msg.translate(Env.getCtx(), "DocumentNo"), "i.DocumentNo", String.class),
		new Info_Column(Msg.translate(Env.getCtx(), "Description"), "i.Description", String.class),
		new Info_Column(Msg.translate(Env.getCtx(), "POReference"), "i.POReference", String.class),
		new Info_Column(Msg.translate(Env.getCtx(), "M_Shipper_ID"), "(SELECT Name FROM M_Shipper ms WHERE ms.M_Shipper_ID = i.M_Shipper_ID)", String.class),
		new Info_Column(Msg.translate(Env.getCtx(), "ShipDate"), "i.ShipDate", Timestamp.class),
		new Info_Column(Msg.translate(Env.getCtx(), "TrackingNo"), "i.TrackingNo", String.class),
		new Info_Column(Msg.translate(Env.getCtx(), "IsDropShip"), "i.IsDropShip", Boolean.class),
		new Info_Column(Msg.translate(Env.getCtx(), "DropShip_BPartner_ID"), "(SELECT Name FROM C_BPartner bp WHERE bp.C_BPartner_ID=i.DropShip_BPartner_ID)", String.class),
		new Info_Column(Msg.translate(Env.getCtx(), "DocStatus"), "i.docstatus", String.class),

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
		lPOReference.setLabelFor(fPOReference);
		fPOReference.setBackground(AdempierePLAF.getInfoBackground());
		fPOReference.addActionListener(this);
		fIsSOTrx.setSelected(!"N".equals(Env.getContext(Env.getCtx(), p_WindowNo, "IsSOTrx")));
		fIsSOTrx.addActionListener(this);
		//
		fBPartner_ID = new VLookup("C_BPartner_ID", false, false, true,
			MLookupFactory.get (Env.getCtx(), p_WindowNo, 0, 
					MColumn.getColumn_ID(MInOut.Table_Name, MInOut.COLUMNNAME_C_BPartner_ID),
					DisplayType.Search));
		lBPartner_ID.setLabelFor(fBPartner_ID);
		fBPartner_ID.setBackground(AdempierePLAF.getInfoBackground());
		fBPartner_ID.addActionListener(this);
		//
		fShipper_ID = new VLookup("M_Shipper_ID", false, false, true,
				MLookupFactory.get (Env.getCtx(), p_WindowNo, 0, 
						MColumn.getColumn_ID(MInOut.Table_Name, MInOut.COLUMNNAME_M_Shipper_ID),
						DisplayType.TableDir));
		lShipper_ID.setLabelFor(fShipper_ID);
		fShipper_ID.setBackground(AdempierePLAF.getInfoBackground());
		fShipper_ID.addActionListener(this);
		//
		lDateFrom.setLabelFor(fDateFrom);
		fDateFrom.setBackground(AdempierePLAF.getInfoBackground());
		fDateFrom.setToolTipText(Msg.translate(Env.getCtx(), "DateFrom"));
		fDateFrom.addActionListener(this);
		lDateTo.setLabelFor(fDateTo);
		fDateTo.setBackground(AdempierePLAF.getInfoBackground());
		fDateTo.setToolTipText(Msg.translate(Env.getCtx(), "DateTo"));
		fDateTo.addActionListener(this);
		//
		CPanel datePanel = new CPanel();
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
		//  3rd Row
		p_criteriaGrid.add(lPOReference, new ALayoutConstraint(2,0));
		p_criteriaGrid.add(fPOReference, null);
		p_criteriaGrid.add(lShipper_ID, null);
		p_criteriaGrid.add(fShipper_ID, null);
	}	//	statInit

	/**
	 *	General Init
	 *	@return true, if success
	 */
	protected void initInfo (int record_id, String value)
	{
		if (!(record_id == 0) && value != null && value.length() > 0)
		{
			log.severe("Received both a record_id and a value: " + record_id + " - " + value);
		}
		//  Set values
        if (!(record_id == 0))  // A record is defined
        {
        	fieldID = record_id;
        } 
        else
        {
			if (value != null && value.length() > 0)
			{
				fDocumentNo.setValue(value);
			}
			else
			{
				// Try to find other criteria in the context
				String id;
				//  M_InOut_ID
				id = Env.getContext(Env.getCtx(), p_WindowNo, p_TabNo, "M_InOut_ID", true);
				if (id != null && id.length() != 0 && (new Integer(id).intValue() > 0))
				{
					fieldID = new Integer(id).intValue();
				}

				//  C_BPartner_ID
				id = Env.getContext(Env.getCtx(), p_WindowNo, p_TabNo, "C_BPartner_ID", true);
				if (id != null && id.length() != 0 && (new Integer(id).intValue() > 0))
					fBPartner_ID.setValue(new Integer(id));

				//  M_Shipper_ID
				id = Env.getContext(Env.getCtx(), p_WindowNo, p_TabNo, "M_Shipper_ID", true);
				if (id != null && id.length() != 0 && (new Integer(id).intValue() > 0))
				{
					fShipper_ID.setValue(new Integer(id).intValue());
				}

			}
        }

        return;
	}	//	initInfo

	/*************************************************************************/

	/**
	 *	Construct SQL Where Clause and define parameters.
	 *  (setParameters needs to set parameters)
	 *  Includes first AND
	 * 	@return where clause
	 */
	protected String getSQLWhere()
	{
		StringBuffer sql = new StringBuffer();
		//  => ID
		if(isResetRecordID())
			fieldID = 0;
		if(!(fieldID == 0))
			sql.append(" AND i.M_InOut_ID = ?");
		//
		if (fDocumentNo.getText().length() > 0)
			sql.append(" AND UPPER(i.DocumentNo) LIKE ?");
		//
		if (fDescription.getText().length() > 0)
			sql.append(" AND UPPER(i.Description) LIKE ?");
		//
		if (fPOReference.getText().length() > 0)
			sql.append(" AND UPPER(i.POReference) LIKE ?");
		//
		if (fBPartner_ID.getValue() != null)
			sql.append(" AND i.C_BPartner_ID=?");
		//
		if (fShipper_ID.getValue() != null)
			sql.append(" AND i.M_Shipper_ID=?");
		//
		if (fDateFrom.getValue() != null || fDateTo.getValue() != null)
		{
			Timestamp from = (Timestamp)fDateFrom.getValue();
			Timestamp to = (Timestamp)fDateTo.getValue();
			if (from == null && to != null)
				sql.append(" AND TRUNC(i.MovementDate, 'DD') <= ?");
			else if (from != null && to == null)
				sql.append(" AND TRUNC(i.MovementDate, 'DD') >= ?");
			else if (from != null && to != null)
				sql.append(" AND TRUNC(i.MovementDate, 'DD') BETWEEN ? AND ?");
		}
		sql.append(" AND i.IsSOTrx=?");

	//	log.fine( "InfoInOut.setWhereClause", sql.toString());
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
		//
		if (fDocumentNo.getText().length() > 0)
			pstmt.setString(index++, getSQLText(fDocumentNo));
		if (fDescription.getText().length() > 0)
			pstmt.setString(index++, getSQLText(fDescription));
		if (fPOReference.getText().length() > 0)
			pstmt.setString(index++, getSQLText(fPOReference));
		//
		if (fBPartner_ID.getValue() != null)
		{
			Integer bp = (Integer)fBPartner_ID.getValue();
			pstmt.setInt(index++, bp.intValue());
			log.fine("BPartner=" + bp);
		}
		//
		if (fShipper_ID.getValue() != null)
		{
			Integer bp = (Integer)fShipper_ID.getValue();
			pstmt.setInt(index++, bp.intValue());
			log.fine("Shipper=" + bp);
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
		pstmt.setString(index++, fIsSOTrx.isSelected() ? "Y" : "N");
	}   //  setParameters


	/**
	 *	Zoom
	 */
	protected void zoom(int record_ID)
	{
		log.info( "InfoInOut.zoom");
		Integer M_InOut_ID = record_ID;
		if (M_InOut_ID == null)
			return;
		MQuery query = new MQuery("M_InOut");
		query.addRestriction("M_InOut_ID", MQuery.EQUAL, M_InOut_ID);
		query.setRecordCount(1);
		int AD_WindowNo = getAD_Window_ID("M_InOut", fIsSOTrx.isSelected());
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
				fPOReference.hasChanged()	||
				fIsSOTrx.hasChanged()	||
				fBPartner_ID.hasChanged()	||
				fShipper_ID.hasChanged()	||
				fDateFrom.hasChanged()	||
				fDateTo.hasChanged());
	}
	/**
	 * Record outstanding changes by copying the current
	 * value to the oldValue on all fields
	 */
	protected void setFieldOldValues()
	{
		fDocumentNo.set_oldValue();
		fDescription.set_oldValue();
		fPOReference.set_oldValue();
		fIsSOTrx.set_oldValue();
		fBPartner_ID.set_oldValue();
		fShipper_ID.set_oldValue();
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
		fPOReference.setValue("");
		fBPartner_ID.setValue(null);
		fShipper_ID.setValue(null);
		fDateFrom.setValue(nullObject);
		fDateTo.setValue(nullObject);
		fIsSOTrx.setSelected(!"N".equals(Env.getContext(Env.getCtx(), p_WindowNo, "IsSOTrx")));
	}
	
}   //  InfoInOut
