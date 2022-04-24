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
import java.awt.event.ActionEvent;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.compiere.apps.AEnv;
import org.compiere.apps.ALayout;
import org.compiere.apps.ALayoutConstraint;
import org.compiere.grid.ed.VDate;
import org.compiere.grid.ed.VLookup;
import org.compiere.minigrid.IDColumn;
import org.compiere.model.MColumn;
import org.compiere.model.MLookupFactory;
import org.compiere.model.MResource;
import org.compiere.model.MResourceType;
import org.compiere.swing.CLabel;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Msg;


/**
 *  View Assignments and optionally create Resource Assignments
 *
 *  @author     Jorg Janke
 *  @version    $Id: InfoAssignment.java,v 1.2 2006/07/30 00:51:27 jjanke Exp $
 *
 * @author Michael McKay, 
 * 				<li>ADEMPIERE-72 VLookup and Info Window improvements
 * 					https://adempiere.atlassian.net/browse/ADEMPIERE-72
 */
public class InfoAssignment extends Info
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5017170699571459745L;

	/**
	 *  Constructor
	 *  @param frame frame
	 *  @param modal modal
	 *  @param WindowNo WindowNo
	 *  @param  value   Query value Name or Value if contains numbers
	 *  @param multiSelection multiple selection
	 *  @param whereClause where clause
	 */
	@Deprecated
	public InfoAssignment (Frame frame, boolean modal, int WindowNo,
		String value, boolean multiSelection, String whereClause)
	{
		this(frame, modal, WindowNo, 0,
				value, multiSelection, true, whereClause);
	}

	/**
	 *  Constructor
	 *  @param frame frame
	 *  @param modal modal
	 *  @param WindowNo WindowNo
	 *  @param record_id The record ID to find
	 *  @param value query value to find, exclusive of record_id
	 *  @param multiSelection multiple selection
	 *  @param saveResults  True if results will be saved, false for info only
	 *  @param whereClause where clause
	 */
	public InfoAssignment (Frame frame, boolean modal, int WindowNo, int record_id,
		String value, boolean multiSelection, boolean saveResults, String whereClause)
	{
		super (frame, modal, WindowNo, "ra", "S_ResourceAssigment_ID",
			multiSelection, saveResults, whereClause);
		log.info(value);
		setTitle(Msg.getMsg(Env.getCtx(), "InfoAssignment"));
		//
		StringBuffer where = new StringBuffer(s_Where);
		if (whereClause != null && whereClause.length() > 0)
			where.append(" AND ").append(whereClause);
		setWhereClause(where.toString());
		setTableLayout(s_Layout);
		setFromClause(s_From);
		setOrderClause(s_Order);
		//
		statInit();
		initInfo (record_id, value);

		//  To get the focus after the table update
		m_heldLastFocus = fieldResourceType;

		//	AutoQuery
		if(autoQuery() || record_id != 0 || (value != null && value.length() > 0 && value != "%"))
			executeQuery();
		
		p_loadedOK = true;

		AEnv.positionCenterWindow(frame, this);
	}   //  InfoAssignment

	//
	private int fieldID = 0;
	private CLabel	labelResourceType = new CLabel(Msg.translate(Env.getCtx(), "S_ResourceType_ID"));
	private VLookup	fieldResourceType;
	private CLabel	labelResource = new CLabel(Msg.translate(Env.getCtx(), "S_Resource_ID"));
	private VLookup	fieldResource;
	private CLabel	labelFrom = new CLabel(Msg.translate(Env.getCtx(), "DateFrom"));
	private VDate	fieldFrom = new VDate(DisplayType.Date);
	private CLabel	labelTo = new CLabel(Msg.translate(Env.getCtx(), "DateTo"));
	private VDate	fieldTo = new VDate(DisplayType.Date);
	//private CButton bNew = new CButton();


	/**
	 *	Static Setup - add fields to parameterPanel.
	 *  <pre>
	 * 		ResourceType	Resource	DateTimeFrom	DateTimeTo	New
	 *  </pre>
	 */
	private void statInit()
	{
		fieldResourceType = new VLookup ("S_ResourceType_ID", false, false, true, 
				MLookupFactory.get(Env.getCtx(), p_WindowNo, 0, 
						MColumn.getColumn_ID(MResourceType.Table_Name, MResourceType.COLUMNNAME_S_ResourceType_ID), 
						DisplayType.TableDir)); 
		fieldResourceType.addActionListener(this);
		fieldResource = new VLookup ("S_Resource_ID", false, false, true, 				
				MLookupFactory.get (Env.getCtx(), p_WindowNo, 0,
						MColumn.getColumn_ID(MResource.Table_Name, MResource.COLUMNNAME_S_Resource_ID), 
						DisplayType.TableDir));
		fieldResource.addActionListener(this);
		//
		fieldFrom.addActionListener(this);
		fieldTo.addActionListener(this);
		//
		p_criteriaGrid.setLayout(new ALayout());
		p_criteriaGrid.add(labelResourceType, new ALayoutConstraint(0,0));
		p_criteriaGrid.add(fieldResourceType, null);
		p_criteriaGrid.add(labelResource, null);
		p_criteriaGrid.add(fieldResource, null);
		//
		p_criteriaGrid.add(labelFrom, new ALayoutConstraint(1,0));
		p_criteriaGrid.add(fieldFrom, null);
		p_criteriaGrid.add(labelTo, null);
		p_criteriaGrid.add(fieldTo, null);
		//parameterPanel.add(bNew, null);
	}	//	statInit

	/**
SELECT rt.Name, r.Name, ra.AssignDateFrom, ra.AssignDateTo, ra.Qty, uom.UOMSymbol, ra.IsConfirmed
FROM S_ResourceAssignment ra, S_ResourceType rt, S_Resource r, C_UOM uom
WHERE ra.IsActive='Y'
AND ra.S_Resource_ID=r.S_Resource_ID
AND r.S_ResourceType_ID=rt.S_ResourceType_ID
AND rt.C_UOM_ID=uom.C_UOM_ID
	 */

	/** From Clause             */
	private static String s_From =
		"S_ResourceAssignment ra, S_ResourceType rt, S_Resource r, C_UOM uom";
	/** Where Clause             */
	private static String s_Where =
		"ra.IsActive='Y' AND ra.S_Resource_ID=r.S_Resource_ID "
		+ "AND r.S_ResourceType_ID=rt.S_ResourceType_ID AND rt.C_UOM_ID=uom.C_UOM_ID";
	/** Order Clause             */
	private static String s_Order =
		"rt.Name, r.Name";

	/**  Array of Column Info    */
	private static Info_Column[] s_Layout = {
		new Info_Column(" ", "ra.S_ResourceAssignment_ID", IDColumn.class),
		new Info_Column(Msg.translate(Env.getCtx(), "S_ResourceType_ID"), "rt.Name", String.class),
		new Info_Column(Msg.translate(Env.getCtx(), "S_Resource_ID"), "r.Name", String.class),
		new Info_Column(Msg.translate(Env.getCtx(), "AssignDateFrom"), "ra.AssignDateFrom", Timestamp.class),
		new Info_Column(Msg.translate(Env.getCtx(), "Qty"), "ra.Qty", Double.class),
		new Info_Column(Msg.translate(Env.getCtx(), "C_UOM_ID"), "uom.UOMSymbol", String.class),
		new Info_Column(Msg.translate(Env.getCtx(), "AssignDateTo"), "ra.AssignDateTo", Timestamp.class),
		new Info_Column(Msg.translate(Env.getCtx(), "IsConfirmed"), "ra.IsConfirmed", Boolean.class)
	};

	/**
	 *	Dynamic Init
	 *  @param record_id The ID of the record to display or zero
	 *  @param value value 
	 */
	protected void initInfo(int record_id, String value)
	{
		//
		prepareTable(getTableLayout(),
				getFromClause(),
				getWhereClause(),
				getOrderClause());
		//
		if (!(record_id == 0) && value != null && value.length() > 0)
		{
			log.severe("Received both a record_id and a value: " + record_id + " - " + value);
		}

		if (!(record_id == 0))  // A record is defined
        {
        	fieldID = record_id;
        }
        else
        {
			if (value != null && value.length() > 0)
			{
				//	Nowhere to use the value in this info dialog
			}
			else
			{
				//  Try to find the fieldID from the context
	        	String sra = Env.getContext(Env.getCtx(), p_WindowNo, "S_ResourceAssignment_ID");
				if (sra != null && sra.length() != 0)
				{
					fieldID = Integer.valueOf(sra).intValue();
				}
				//  Find the criteria in the context
				//  S_Resource_Type_ID
	        	String srt = Env.getContext(Env.getCtx(), p_WindowNo, "S_ResourceType_ID");
				if (srt != null && srt.length() > 0)
				{
					fieldResourceType.setValue(Integer.valueOf(srt));
				}
				//  S_Resource_ID
	        	String sr = Env.getContext(Env.getCtx(), p_WindowNo, "S_Resource_ID");
				if (sr != null && sr.length() > 0)
				{
					fieldResource.setValue(Integer.valueOf(sr));
				}
			}
        }

		
	}	//	initInfo

	/*************************************************************************/

	/**
	 *  Action Listner
	 *
	 * 	@param e event
	 */
	public void actionPerformed (ActionEvent e)
	{
		//  don't requery if fieldValue and fieldName are empty
		//	return;
		//
		super.actionPerformed(e);
	}   //  actionPerformed

	/*************************************************************************/

	/**
	 *  Get dynamic WHERE part of SQL
	 *	To be overwritten by concrete classes
	 *  @return WHERE clause
	 */
	protected String getSQLWhere()
	{
		StringBuffer sql = new StringBuffer();
		//  => ID
		if(isResetRecordID())
			fieldID = 0;
		if(!(fieldID == 0))
			sql.append(" AND ra.S_ResourceAssignment_ID=").append(fieldID);
		//
		Integer S_ResourceType_ID = (Integer)fieldResourceType.getValue();
		if (S_ResourceType_ID != null)
			sql.append(" AND rt.S_ResourceType_ID=").append(S_ResourceType_ID.intValue());
		//
		Integer S_Resource_ID = (Integer)fieldResource.getValue();
		if (S_Resource_ID != null)
			sql.append(" AND r.S_Resource_ID=").append(S_Resource_ID.intValue());
		//
		Timestamp ts = fieldFrom.getTimestamp();
		if (ts != null)
			sql.append(" AND TRUNC(ra.AssignDateFrom, 'DD')>=").append(DB.TO_DATE(ts,false));
		//
		ts = fieldTo.getTimestamp();
		if (ts != null)
			sql.append(" AND TRUNC(ra.AssignDateTo, 'DD')<=").append(DB.TO_DATE(ts,false));
		return sql.toString();
	}	//	getSQLWhere

	/**
	 *  Set Parameters for Query
	 *	To be overwritten by concrete classes
	 *  @param pstmt pstmt
	 *  @param forCount for counting records
	 *  @throws SQLException
	 */
	protected void setParameters (PreparedStatement pstmt, boolean forCount) throws SQLException
	{
	}

	/**
	 * Does the parameter panel have outstanding changes that have not been
	 * used in a query?
	 * @return true if there are outstanding changes.
	 */
	protected boolean hasOutstandingChanges()
	{
		//  All the tracked fields
		return(
				fieldFrom.hasChanged()	||
				fieldResource.hasChanged()	||
				fieldResourceType.hasChanged()	||
				fieldTo.hasChanged());
	}
	/**
	 * Record outstanding changes by copying the current
	 * value to the oldValue on all fields
	 */
	protected void setFieldOldValues()
	{
		fieldFrom.set_oldValue();
		fieldResource.set_oldValue();
		fieldResourceType.set_oldValue();
		fieldTo.set_oldValue();
		return;
	}
	/**
	 *  Clear all fields and set default values in check boxes
	 */
	protected void clearParameters()
	{
		Object nullObject = null;
		fieldFrom.setValue(nullObject);
		fieldResource.setValue(nullObject);
		fieldResourceType.setValue(nullObject);
		fieldTo.setValue(nullObject);
		return;
	}

}   //  InfoAssignment
