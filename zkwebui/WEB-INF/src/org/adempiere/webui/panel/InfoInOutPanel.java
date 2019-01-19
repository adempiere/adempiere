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

import org.adempiere.webui.apps.AEnv;
import org.adempiere.webui.component.Checkbox;
import org.adempiere.webui.component.Datebox;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.Row;
import org.adempiere.webui.component.Rows;
import org.adempiere.webui.component.Textbox;
import org.adempiere.webui.editor.WEditor;
import org.adempiere.webui.editor.WSearchEditor;
import org.adempiere.exceptions.ValueChangeListener;
import org.compiere.minigrid.ColumnInfo;
import org.compiere.minigrid.IDColumn;
import org.compiere.model.MColumn;
import org.compiere.model.MInOut;
import org.compiere.model.MLookupFactory;
import org.compiere.model.MQuery;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Util;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Hbox;

/**
* Based on InfoInOut written by Jorg Janke
*  
* @author 	Niraj Sohun
* 			Aug 03, 2007
* 
* Zk Port
* @author Elaine
* @version	InfoInOut.java Adempiere Swing UI 3.4.1
*
 * @author Michael McKay, ADEMPIERE-72 VLookup and Info Window improvements
 * 	<li>https://adempiere.atlassian.net/browse/ADEMPIERE-72
*/

public class InfoInOutPanel extends InfoPanel implements ValueChangeListener, EventListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3927370377224858985L;

	private int fieldID = 0;
	
	private Textbox fDocumentNo = new Textbox();

	private WEditor fBPartner_ID;
	private WEditor fShipper_ID;

	private Textbox fDescription = new Textbox();
	private Textbox fPOReference = new Textbox();

	private Datebox fDateFrom = new Datebox();
	private Datebox fDateTo = new Datebox();

	private Checkbox fIsSOTrx = new Checkbox();

	private Label lDocumentNo = new Label(Msg.translate(Env.getCtx(), "DocumentNo"));
	private Label lDescription = new Label(Msg.translate(Env.getCtx(), "Description"));
	private Label lPOReference = new Label(Msg.translate(Env.getCtx(), "POReference"));

	private Label lDateFrom = new Label(Msg.translate(Env.getCtx(), "MovementDate"));
	private Label lDateTo = new Label("-");

	/** From Clause             */
	private static String s_From = " M_InOut i";
	/** Order Clause             */
	private static String s_Order = " i.MovementDate desc, i.DocumentNo";
	/**  Array of Column Info    */
	private static final ColumnInfo[] s_Layout = {
		new ColumnInfo(" ", "i.M_InOut_ID", IDColumn.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "C_BPartner_ID"), "(SELECT Name FROM C_BPartner bp WHERE bp.C_BPartner_ID=i.C_BPartner_ID)", String.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "MovementDate"), "i.MovementDate", Timestamp.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "DocumentNo"), "i.DocumentNo", String.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "Description"), "i.Description", String.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "POReference"), "i.POReference", String.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "M_Shipper_ID"), "(SELECT Name FROM M_Shipper ms WHERE ms.M_Shipper_ID = i.M_Shipper_ID)", String.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "ShipDate"), "i.ShipDate", Timestamp.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "TrackingNo"), "i.TrackingNo", String.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "IsDropShip"), "i.IsDropShip", Boolean.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "DropShip_BPartner_ID"), "(SELECT Name FROM C_BPartner bp WHERE bp.C_BPartner_ID=i.DropShip_BPartner_ID)", String.class),		
		new ColumnInfo(Msg.translate(Env.getCtx(), "DocStatus"), "i.docstatus", String.class)
	};

	/**
	 *  Detail Protected Constructor
	 *  
	 *  @param WindowNo window no
	 *  @param value query value
	 *  @param multiSelection multiple selections
	 *  @param whereClause where clause
	 */
	protected InfoInOutPanel(	int WindowNo, int record_id, String value,
								boolean multiSelection, String whereClause)
	{
		this(WindowNo, true, record_id, value, multiSelection, true, whereClause);
	}
	
	/**
	 *  Detail Protected Constructor
	 *
	 *  @param WindowNo window no
	 *  @param value query value
	 *  @param multiSelection multiple selections
	 *  @param whereClause where clause
	 */
	protected InfoInOutPanel(	int WindowNo, boolean modal, int record_id, String value,
								boolean multiSelection, boolean saveResults, String whereClause)
	{
		super (WindowNo, modal, "i", "M_InOut_ID", multiSelection, saveResults, whereClause);
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
		//
		if(autoQuery() || record_id != 0 || (value != null && value.length() > 0 && value != "%"))
        {
			prepareAndExecuteQuery();
        }
        //
        p_loadedOK = true;
	} // InfoInOutPanel

	/**
	 *	Static Setup - add fields to parameterPanel
	 */
	
	private void statInit()
	{
		fDocumentNo.setWidth("100%");
		fDocumentNo.addEventListener(Events.ON_CHANGE, this);
        fDocumentNo.setAttribute("zk_component_ID", "Lookup_Criteria_DocumentNo");
		fDescription.setWidth("100%");
		fDescription.addEventListener(Events.ON_CHANGE, this);
        fDescription.setAttribute("zk_component_ID", "Lookup_Criteria_Description");
		fPOReference.setWidth("100%");
		fPOReference.addEventListener(Events.ON_CHANGE, this);
        fPOReference.setAttribute("zk_component_ID", "Lookup_Criteria_POReference");

		// 	Format the dates and number boxes
		fDateFrom = new Datebox();
		fDateFrom.setWidth("97px");
		fDateFrom.setAttribute("zk_component_ID", "Lookup_Criteria_DateFrom");
		fDateFrom.addEventListener(Events.ON_CHANGE, this);
		//
		fDateTo = new Datebox();
		fDateTo.setWidth("97px");
		fDateTo.setAttribute("zk_component_ID", "Lookup_Criteria_DateTo");
		fDateTo.addEventListener(Events.ON_CHANGE, this);
		//
		SimpleDateFormat dateFormat = DisplayType.getDateFormat(DisplayType.Date, AEnv.getLanguage(Env.getCtx()));
		fDateFrom.setFormat(dateFormat.toPattern());
		fDateTo.setFormat(dateFormat.toPattern());
    	

        fIsSOTrx.setLabel(Msg.translate(Env.getCtx(), "IsSOTrx"));
        fIsSOTrx.setName("IsSOTrx");
        fIsSOTrx.setAttribute("zk_component_ID", "Lookup_Criteria_IsSoTrx");
        fIsSOTrx.addActionListener(this);
		fIsSOTrx.setChecked(!"N".equals(Env.getContext(Env.getCtx(), p_WindowNo, "IsSOTrx")));
		
		fBPartner_ID = new WSearchEditor(
				MLookupFactory.get (Env.getCtx(), p_WindowNo, 0,  
						MColumn.getColumn_ID(MInOut.Table_Name, MInOut.COLUMNNAME_C_BPartner_ID),
						DisplayType.Search),  
				Msg.translate(Env.getCtx(), "C_BPartner_ID"), "", false, false, true);
		fBPartner_ID.getComponent().setAttribute("zk_component_ID", "Lookup_Criteria_C_BPartner_ID");
		fBPartner_ID.addValueChangeListener(this);
		//
		fShipper_ID = new WSearchEditor(
				MLookupFactory.get (Env.getCtx(), p_WindowNo, 0, 
						MColumn.getColumn_ID(MInOut.Table_Name, MInOut.COLUMNNAME_M_Shipper_ID),
						DisplayType.TableDir),  
						Msg.translate(Env.getCtx(), "M_Shipper_ID"), "", false, false, true);
		fShipper_ID.getComponent().setAttribute("zk_component_ID", "Lookup_Criteria_M_Shipper_ID");
		fShipper_ID.addValueChangeListener(this);
	
		Rows rows = new Rows();
		
		Row row = new Row();
		rows.appendChild(row);
		row.appendChild(lDocumentNo.rightAlign());
		row.appendChild(fDocumentNo);
		row.appendChild(fBPartner_ID.getLabel().rightAlign());
		row.appendChild(fBPartner_ID.getComponent());
		row.appendChild(fIsSOTrx);
		
		row = new Row();
		row.setSpans("1, 1, 1, 2");
		rows.appendChild(row);
		row.appendChild(lDescription.rightAlign());
		row.appendChild(fDescription);
		row.appendChild(lDateFrom.rightAlign());
		Hbox hbox = new Hbox();
		hbox.appendChild(fDateFrom);
		hbox.appendChild(lDateTo);
		hbox.appendChild(fDateTo);
		row.appendChild(hbox);
		
		row = new Row();
		rows.appendChild(row);
		row.appendChild(lPOReference.rightAlign());
		row.appendChild(fPOReference);		
		row.appendChild(fShipper_ID.getLabel().rightAlign());
		row.appendChild(fShipper_ID.getComponent());
		row.appendChild(new Label());

		p_criteriaGrid.appendChild(rows);
		super.setSizes();
	}
	
	/**
	 *	General Init
	 */
	
	private void initInfo (int record_id, String value)
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
	} // initInfo

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
		if (fBPartner_ID.getDisplay() != "")
			sql.append(" AND i.C_BPartner_ID=?");
		//
		if (fShipper_ID.getValue() != null)
			sql.append(" AND i.M_Shipper_ID=?");
		//
		if (fDateFrom.getValue() != null || fDateTo.getValue() != null)
		{
			Timestamp from = null;
			Timestamp to = null;
			//
			if (fDateFrom.getValue() != null)
				from = new Timestamp(fDateFrom.getValue().getTime());
			if (fDateTo.getValue() != null)
				to = new Timestamp(fDateTo.getValue().getTime());
			//
			log.fine("Date From=" + from + ", To=" + to);
			//
			if (from == null && to != null)
				sql.append(" AND TRUNC(i.MovementDate, 'DD') <= ?");
			else if (from != null && to == null)
				sql.append(" AND TRUNC(i.MovementDate, 'DD') >= ?");
			else if (from != null && to != null)
				sql.append(" AND TRUNC(i.MovementDate, 'DD') BETWEEN ? AND ?");
		}
		sql.append(" AND i.IsSOTrx=?");

		return sql.toString();
	} // getSQLWhere

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
		//
		if (fDescription.getText().length() > 0)
			pstmt.setString(index++, getSQLText(fDescription));
		//
		if (fPOReference.getText().length() > 0)
			pstmt.setString(index++, getSQLText(fPOReference));
		//
		if (fBPartner_ID.getDisplay() != "")
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
			Timestamp from = null;
			Timestamp to = null;
			//
			if (fDateFrom.getValue() != null)
				from = new Timestamp(fDateFrom.getValue().getTime());
			if (fDateTo.getValue() != null)
				to = new Timestamp(fDateTo.getValue().getTime());
			//
			log.fine("Date From=" + from + ", To=" + to);
			//
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
		pstmt.setString(index++, fIsSOTrx .isChecked() ? "Y" : "N");
	} // setParameters

	// Elaine 2008/12/16
	/**
	 *	Zoom
	 */
	public void zoom()
	{
		log.info( "InfoInOut.zoom");
		Integer M_InOut_ID = getSelectedRowKey();
		if (M_InOut_ID == null)
			return;
		MQuery query = new MQuery("M_InOut");
		query.addRestriction("M_InOut_ID", MQuery.EQUAL, M_InOut_ID);
		query.setRecordCount(1);
		int AD_WindowNo = getAD_Window_ID("M_InOut", fIsSOTrx.isSelected());
		AEnv.zoom (AD_WindowNo, query);
	}	//	zoom
	//
	
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
}