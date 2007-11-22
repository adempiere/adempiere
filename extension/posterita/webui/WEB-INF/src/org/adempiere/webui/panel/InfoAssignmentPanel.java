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
import java.util.Date;
import java.util.logging.Level;

import org.adempiere.webui.component.Button;
import org.adempiere.webui.component.Datebox;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.VerticalBox;
import org.adempiere.webui.editor.WEditor;
import org.adempiere.webui.editor.WSearchEditor;
import org.adempiere.webui.event.ValueChangeEvent;
import org.adempiere.webui.event.ValueChangeListener;
import org.adempiere.webui.event.WTableModelEvent;
import org.compiere.minigrid.ColumnInfo;
import org.compiere.minigrid.IDColumn;
import org.compiere.model.MLookupFactory;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Separator;

/**
* Based on InfoAssignment written by Jorg Janke
*  
* @author 	Niraj Sohun
* 			Aug 06, 2007
*/

public class InfoAssignmentPanel extends InfoPanel implements EventListener, ValueChangeListener
{
	private static final long serialVersionUID = 1L;
	
	private WEditor fieldResourceType;
	private WEditor fieldResource;
	
	private Button bNew = new Button();
	
	private Datebox fieldFrom = new Datebox();
	private Datebox fieldTo = new Datebox();

	private Label labelFrom = new Label(Msg.translate(Env.getCtx(), "DateFrom"));
	private Label labelTo = new Label(Msg.translate(Env.getCtx(), "DateTo"));

	/** From Clause             */
	private static String s_assignmentFROM =
		"S_ResourceAssignment ra, S_ResourceType rt, S_Resource r, C_UOM uom";

	private static String s_assignmentWHERE =
		"ra.IsActive='Y' AND ra.S_Resource_ID=r.S_Resource_ID "
		+ "AND r.S_ResourceType_ID=rt.S_ResourceType_ID AND rt.C_UOM_ID=uom.C_UOM_ID";

	/**  Array of Column Info    */
	private static ColumnInfo[] s_assignmentLayout = {
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
	 *  Constructor
	 *  
	 *  @param WindowNo WindowNo
	 *  @param  value   Query value Name or Value if contains numbers
	 *  @param multiSelection multiple selection
	 *  @param whereClause where clause
	 */
	
	public InfoAssignmentPanel (int WindowNo,
		String value, boolean multiSelection, String whereClause)
	{
		super (WindowNo, "ra", "S_ResourceAssigment_ID",
			multiSelection, whereClause);
		log.info(value);
		setTitle(Msg.getMsg(Env.getCtx(), "InfoAssignment"));

		if (!initLookups())
			return;
		
		statInit();
		initInfo (value, whereClause);

		int no = contentPanel.getRowCount();
		setStatusLine(Integer.toString(no) + " " + Msg.getMsg(Env.getCtx(), "SearchRows_EnterQuery"), false);
		setStatusDB(Integer.toString(no));

		// AutoQuery
		//	if (value != null && value.length() > 0)
		//		executeQuery();
		
		p_loadedOK = true;

		//AEnv.positionCenterWindow(frame, this);
	} // InfoAssignmentPanel
	
	/**
	 * 	Initialize Lookups
	 * 	@return true if OK
	 */
	
	private boolean initLookups()
	{
		try
		{
			int AD_Column_ID = 6851; //	S_Resource.S_ResourceType_ID

			fieldResourceType = new WSearchEditor (
					MLookupFactory.get(Env.getCtx(), p_WindowNo, 0, AD_Column_ID, DisplayType.TableDir), 
					Msg.translate(Env.getCtx(), "S_ResourceType_ID"), "", false, false, true);
			
			AD_Column_ID = 6826; //	S_ResourceAssignment.S_Resource_ID
			
			fieldResource = new WSearchEditor (
					MLookupFactory.get (Env.getCtx(), p_WindowNo, 0, AD_Column_ID, DisplayType.TableDir), 
					Msg.translate(Env.getCtx(), "S_Resource_ID"), "", false, false, true);
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, "InfoAssignment.initLookup");
			return false;
		}

		bNew.setImage("/images/New16.gif");
		
		return true;
	} // initLookups

	/**
	 *	Static Setup - add fields to parameterPanel.
	 *  <pre>
	 * 		ResourceType	Resource	DateTimeFrom	DateTimeTo	New
	 *  </pre>
	 */
	
	private void statInit()
	{
		VerticalBox boxResourceType = new VerticalBox();
		boxResourceType.appendChild(fieldResourceType.getLabel());
		boxResourceType.appendChild(fieldResourceType.getComponent());
		
		VerticalBox boxResource = new VerticalBox();
		boxResource.appendChild(fieldResource.getLabel());
		boxResource.appendChild(fieldResource.getComponent());
		
		VerticalBox boxFrom = new VerticalBox();
		boxFrom.appendChild(labelFrom);
		boxFrom.appendChild(fieldFrom);
		
		VerticalBox boxTo = new VerticalBox();
		boxTo.appendChild(labelTo);
		boxTo.appendChild(fieldTo);
		
		//	parameterPanel.add(labelPhone, null);
		//	parameterPanel.add(checkFuzzy, null);

		Hbox mainBox = new Hbox();
		
		bNew.addEventListener(Events.ON_CLICK, this);
		
		mainBox.setWidth("100%");
		mainBox.setWidths("30%, 30%, 17%, 17%, 6%");
		mainBox.appendChild(boxResourceType);
		mainBox.appendChild(boxResource);
		mainBox.appendChild(boxFrom);
		mainBox.appendChild(boxTo);
		mainBox.appendChild(bNew);
		
		//	parameterPanel.add(checkCustomer, null);
		
		this.setWidth("850px");
		this.setTitle("Info Asset");
		this.setClosable(true);
		this.setBorder("normal");
		this.appendChild(mainBox);
		this.appendChild(new Separator());
		this.appendChild(contentPanel);
		this.appendChild(new Separator());
		this.appendChild(confirmPanel);
		this.appendChild(new Separator());
		this.appendChild(statusBar);
	}
	
	/**
	 *	Dynamic Init
	 *  @param value value
	 *  @param whereClause where clause
	 */
	
	private void initInfo(String value, String whereClause)
	{
		//  C_BPartner bp, AD_User c, C_BPartner_Location l, C_Location a

		//	Create Grid
		
		StringBuffer where = new StringBuffer(s_assignmentWHERE);
		
		if (whereClause != null && whereClause.length() > 0)
			where.append(" AND ").append(whereClause);
		
		prepareTable(s_assignmentLayout, s_assignmentFROM,
			where.toString(), "rt.Name,r.Name,ra.AssignDateFrom");
	} // initInfo
	
	/*************************************************************************/

	/**
	 *  Event Listener
	 *
	 * 	@param e event
	 */
	public void onEvent (Event e)
	{
		//  don't requery if fieldValue and fieldName are empty
		//	return;

		super.onEvent(e);
	} // onEvent

	/*************************************************************************/

	/**
	 *  Get dynamic WHERE part of SQL
	 *	To be overwritten by concrete classes
	 *  @return WHERE clause
	 */
	
	String getSQLWhere()
	{
		StringBuffer sql = new StringBuffer();

		Integer S_ResourceType_ID = (Integer)fieldResourceType.getValue();
		
		if (S_ResourceType_ID != null)
			sql.append(" AND rt.S_ResourceType_ID=").append(S_ResourceType_ID.intValue());

		Integer S_Resource_ID = (Integer)fieldResource.getValue();
		
		if (S_Resource_ID != null)
			sql.append(" AND r.S_Resource_ID=").append(S_Resource_ID.intValue());

		Date f = fieldFrom.getValue();
		Timestamp ts = new Timestamp(f.getTime());
		
		if (ts != null)
			sql.append(" AND TRUNC(ra.AssignDateFrom)>=").append(DB.TO_DATE(ts,false));

		Date t = fieldTo.getValue();
		ts = new Timestamp(t.getTime());

		if (ts != null)
			sql.append(" AND TRUNC(ra.AssignDateTo)<=").append(DB.TO_DATE(ts,false));
		
		return sql.toString();
	} // getSQLWhere

	/**
	 *  Set Parameters for Query
	 *	To be overwritten by concrete classes
	 *  @param pstmt pstmt
	 *  @param forCount for counting records
	 *  @throws SQLException
	 */
	
	void setParameters (PreparedStatement pstmt, boolean forCount) throws SQLException
	{
	}

	/**
	 *  History dialog
	 *	To be overwritten by concrete classes
	 */
	
	void showHistory()
	{
	}

	/**
	 *  Has History (false)
	 *	To be overwritten by concrete classes
	 *  @return true if it has history (default false)
	 */
	
	boolean hasHistory()
	{
		return false;
	}

	/**
	 *  Customize dialog
	 *	To be overwritten by concrete classes
	 */
	
	void customize()
	{
	}

	/**
	 *  Has Customize (false)
	 *	To be overwritten by concrete classes
	 *  @return true if it has customize (default false)
	 */
	
	boolean hasCustomize()
	{
		return false;
	}

	/**
	 *  Zoom action
	 *	To be overwritten by concrete classes
	 */
	
	public void zoom()
	{
	}

	/**
	 *  Has Zoom (false)
	 *	To be overwritten by concrete classes
	 *  @return true if it has zoom (default false)
	 */
	
	boolean hasZoom()
	{
		return false;
	}

	/**
	 *  Save Selection Details
	 *	To be overwritten by concrete classes
	 */
	
	void saveSelectionDetail()
	{
	}

	public void valueChange(ValueChangeEvent evt) 
	{
		
	}

	public void tableChanged(WTableModelEvent event) 
	{
		
	}

}
