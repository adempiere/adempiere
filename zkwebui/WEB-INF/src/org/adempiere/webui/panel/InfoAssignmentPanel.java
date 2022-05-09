/******************************************************************************
 * Product: Posterita Ajax UI 												  *
 * Copyright (C) 2007 Posterita Ltd.  All Rights Reserved.                    *
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
 * Posterita Ltd., 3, Draper Avenue, Quatre Bornes, Mauritius                 *
 * or via info@posterita.org or http://www.posterita.org/                     *
 *****************************************************************************/

package org.adempiere.webui.panel;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.adempiere.webui.apps.AEnv;
import org.adempiere.webui.component.Button;
import org.adempiere.webui.component.Datebox;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.Row;
import org.adempiere.webui.component.Rows;
import org.adempiere.webui.editor.WEditor;
import org.adempiere.webui.editor.WTableDirEditor;
import org.adempiere.exceptions.ValueChangeListener;
import org.compiere.minigrid.ColumnInfo;
import org.compiere.minigrid.IDColumn;
import org.compiere.model.MColumn;
import org.compiere.model.MLookupFactory;
import org.compiere.model.MQuery;
import org.compiere.model.MResource;
import org.compiere.model.MResourceType;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Div;

/**
* Based on InfoAssignment written by Jorg Janke
*  
* @author 	Niraj Sohun
* 			Aug 06, 2007
* 
* Zk Port
* @author Elaine
* @version	InfoAssignment.java Adempiere Swing UI 3.4.1
*
 * @author Michael McKay, ADEMPIERE-72 VLookup and Info Window improvements
 * 	<li>https://adempiere.atlassian.net/browse/ADEMPIERE-72
*/

public class InfoAssignmentPanel extends InfoPanel implements EventListener, ValueChangeListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -935642651768066799L;
	
	private int fieldID = 0;
	
	private WEditor fieldResourceType;
	private WEditor fieldResource;
	
	private Button bNew = new Button();
	
	private Datebox fieldFrom = new Datebox();
	private Datebox fieldTo = new Datebox();

	private Label labelFrom = new Label(Msg.translate(Env.getCtx(), "DateFrom"));
	private Label labelTo = new Label(Msg.translate(Env.getCtx(), "DateTo"));

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
	private static ColumnInfo[] s_Layout = {
		new ColumnInfo(" ", "ra.S_ResourceAssignment_ID", IDColumn.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "S_ResourceType_ID"), "rt.Name", String.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "S_Resource_ID"), "r.Name", String.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "AssignDateFrom"), "ra.AssignDateFrom", Timestamp.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "Qty"), "ra.Qty", Double.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "C_UOM_ID"), "uom.UOMSymbol", String.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "AssignDateTo"), "ra.AssignDateTo", Timestamp.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "IsConfirmed"), "ra.IsConfirmed", Boolean.class)
	};

	/**
	 *  Deprecated Constructor
	 *  
	 *  @param WindowNo WindowNo
	 *  @param  value   Query value Name or Value if contains numbers
	 *  @param multiSelection multiple selection
	 *  @param whereClause where clause
	 */
	@Deprecated
	public InfoAssignmentPanel (int WindowNo, 
		String value, boolean multiSelection, String whereClause)
	{
		this(WindowNo, true, 0, value, multiSelection, false, whereClause);
	}

	/**
	 *  Constructor
	 *  
	 *  @param WindowNo WindowNo
	 *  @param record_id The record ID to find
	 *  @param value query value to find, exclusive of record_id
	 *  @param multiSelection multiple selection
	 *  @param saveResults  True if results will be saved, false for info only
	 *  @param whereClause where clause
	 */
	public InfoAssignmentPanel (int WindowNo, int record_id, 
		String value, boolean multiSelection, String whereClause)
	{
		this(WindowNo, true, record_id, value, multiSelection, false, whereClause);
	}
	
	/**
	 *  Constructor
	 *
	 *  @param WindowNo WindowNo
	 *  @param modal True if window is opened in modal mode.
	 *  @param record_id The record ID to find
	 *  @param value query value to find, exclusive of record_id
	 *  @param multiSelection multiple selection
	 *  @param saveResults  True if results will be saved (modal), false for info only (non-modal)
	 *  @param whereClause where clause
	 */
	public InfoAssignmentPanel (int WindowNo, boolean modal, int record_id, 
		String value, boolean multiSelection, boolean saveResults, String whereClause)
	{
		super (WindowNo, modal, "ra", "S_ResourceAssignment_ID",
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
		initInfo (record_id, value, whereClause);

		//  Auto query
		if(autoQuery() || record_id != 0 || (value != null && value.length() > 0 && value != "%"))
			prepareAndExecuteQuery();

		p_loadedOK = true;
	} // InfoAssignmentPanel
	

	/**
	 *	Static Setup - add fields to parameterPanel.
	 *  <pre>
	 * 		ResourceType	Resource	DateTimeFrom	DateTimeTo	New
	 *  </pre>
	 */
	
	private void statInit()
	{
		fieldResourceType = new WTableDirEditor (
				MLookupFactory.get(Env.getCtx(), p_WindowNo, 0, 
						MColumn.getColumn_ID(MResourceType.Table_Name, MResourceType.COLUMNNAME_S_ResourceType_ID), 
						DisplayType.TableDir), 
				Msg.translate(Env.getCtx(), "S_ResourceType_ID"), "", false, false, true);
		fieldResourceType.getComponent().addEventListener(Events.ON_CHANGE, this);;
		fieldResourceType.getComponent().setAttribute("zk_component_ID", "Lookup_Criteria_S_ResourceType_ID");
		
		fieldResource = new WTableDirEditor (
				MLookupFactory.get (Env.getCtx(), p_WindowNo, 0,
						MColumn.getColumn_ID(MResource.Table_Name, MResource.COLUMNNAME_S_Resource_ID), 
						DisplayType.TableDir), 
				Msg.translate(Env.getCtx(), "S_Resource_ID"), "", false, false, true);
		fieldResource.getComponent().addEventListener(Events.ON_CHANGE, this);
		fieldResource.getComponent().setAttribute("zk_component_ID", "Lookup_Criteria_S_Resource_ID");

		bNew.setImage("/images/dark/New16.png");
		fieldFrom.setWidth("180px");
		fieldTo.setWidth("180px");
		
		fieldFrom.setAttribute("zk_component_ID", "Lookup_Criteria_fieldFrom");
		fieldFrom.addEventListener(Events.ON_CHANGE, this);
		fieldTo.setAttribute("zk_component_ID", "Lookup_Criteria_fieldTo");
		fieldTo.addEventListener(Events.ON_CHANGE, this);
		
		SimpleDateFormat dateFormat = DisplayType.getDateFormat(DisplayType.Date, AEnv.getLanguage(Env.getCtx()));
		fieldFrom.setFormat(dateFormat.toPattern());
		fieldTo.setFormat(dateFormat.toPattern());

		bNew.addEventListener(Events.ON_CLICK, this);
		bNew.setAttribute("zk_component_ID", "Lookup_Criteria_bNew");

		Rows rows = new Rows();
		
		Row row = new Row();
		rows.appendChild(row);
		row.appendChild(fieldResourceType.getLabel().rightAlign());
		row.appendChild(fieldResource.getLabel().rightAlign());
		row.appendChild(labelFrom.rightAlign());
		row.appendChild(labelTo.rightAlign());
		row.appendChild(new Label());
		
		row = new Row();
		rows.appendChild(row);
		row.appendChild(fieldResourceType.getComponent());
		row.appendChild(fieldResource.getComponent());
		Div div = new Div();
		div.setAlign("right");
		div.appendChild(fieldFrom);
		row.appendChild(div);
		div = new Div();
		div.setAlign("right");
		div.appendChild(fieldTo);
		row.appendChild(div);
		row.appendChild(bNew);
				
		p_criteriaGrid.appendChild(rows);
		super.setSizes();
	}
	
	/**
	 *	Dynamic Init
	 *  @param value value
	 *  @param whereClause where clause
	 */
	
	private void initInfo(int record_id, String value, String whereClause)
	{
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
	} // initInfo
	
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
		Date f = fieldFrom.getValue();
		Timestamp ts = f != null ? new Timestamp(f.getTime()) : null;
		if (ts != null)
			sql.append(" AND TRUNC(ra.AssignDateFrom, 'DD')>=").append(DB.TO_DATE(ts,false));
		//
		Date t = fieldTo.getValue();
		ts = t != null ? new Timestamp(t.getTime()) : null;
		if (ts != null)
			sql.append(" AND TRUNC(ra.AssignDateTo, 'DD')<=").append(DB.TO_DATE(ts,false));
		
		return sql.toString();
	} // getSQLWhere

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
	 *  Zoom action
	 *	To be overwritten by concrete classes
	 */
	
	public void zoom()
	{
		if (getSelectedRowKey() != null && getSelectedRowKey() > 0)
		{
			MQuery zoomQuery = new MQuery();   //  ColumnName might be changed in MTab.validateQuery
	        String column = getKeyColumn();
	        //strip off table name, fully qualify name doesn't work when zoom into detail tab
	        if (column.indexOf(".") > 0)
	        	column = column.substring(column.indexOf(".")+1);
	        zoomQuery.addRestriction(column, MQuery.EQUAL, getSelectedRowKey());
	        zoomQuery.setRecordCount(1);
	        zoomQuery.setTableName(column.substring(0, column.length() - 3));
	        
	        AEnv.zoom(236, zoomQuery);
		}
	}

	/**
	 *  Has Zoom (false)
	 *	To be overwritten by concrete classes
	 *  @return true if it has zoom (default false)
	 */
	
	protected boolean hasZoom()
	{
		return true;
	}

	/**
	 *  Save Selection Details
	 *	To be overwritten by concrete classes
	 */
	
	protected void saveSelectionDetail()
	{
		// No context to save??
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
			fieldResourceType.hasChanged()	||
			fieldResource.hasChanged() ||
			fieldFrom.hasChanged() ||
			fieldTo.hasChanged()
			);
			
	}
	/**
	 * Record outstanding changes by copying the current
	 * value to the oldValue on all fields
	 */
	protected void setFieldOldValues()
	{
		fieldResourceType.set_oldValue();
		fieldResource.set_oldValue();
		fieldFrom.set_oldValue();
		fieldTo.set_oldValue();
		return;
	}

}
