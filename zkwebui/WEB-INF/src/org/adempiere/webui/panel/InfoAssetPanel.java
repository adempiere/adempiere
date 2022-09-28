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

import org.adempiere.webui.apps.AEnv;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.Row;
import org.adempiere.webui.component.Rows;
import org.adempiere.webui.component.Textbox;
import org.adempiere.webui.editor.WEditor;
import org.adempiere.webui.editor.WSearchEditor;
import org.adempiere.core.domains.models.I_A_Asset;
import org.adempiere.core.domains.models.I_C_BPartner;
import org.adempiere.core.domains.models.I_M_Product;
import org.adempiere.exceptions.ValueChangeListener;
import org.compiere.minigrid.ColumnInfo;
import org.compiere.minigrid.IDColumn;
import org.compiere.model.MColumn;
import org.compiere.model.MLookupFactory;
import org.compiere.model.MQuery;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;

/**
* Based on InfoPayment written by Jorg Janke
*  
* @author 	Niraj Sohun
* 			Aug, 02, 2007
* 
* Zk Port
* @author Elaine
* @version	InfoAsset.java Adempiere Swing UI 3.4.1 
* 
*
 * @author Michael McKay, ADEMPIERE-72 VLookup and Info Window improvements
 * 	<li>https://adempiere.atlassian.net/browse/ADEMPIERE-72
*/

public class InfoAssetPanel extends InfoPanel implements ValueChangeListener, EventListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3324796198694097770L;

	/** From Clause             */
	private static String s_From = "A_ASSET a"
		+ " LEFT OUTER JOIN M_Product p ON (a.M_Product_ID=p.M_Product_ID)"
		+ " LEFT OUTER JOIN C_BPartner bp ON (a.C_BPartner_ID=bp.C_BPartner_ID)"
		+ " LEFT OUTER JOIN AD_User u ON (a.AD_User_ID=u.AD_User_ID)";

	/**  Array of Column Info    */
	private static final ColumnInfo[] s_Layout = {
		new ColumnInfo(" ", "a.A_Asset_ID", IDColumn.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "Value"), "a.Value", String.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "Name"), "a.Name", String.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "M_Product_ID"), "p.Name", String.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "C_BPartner_ID"), "bp.Name",  String.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "AD_User_ID"), "u.Name", String.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "AssetServiceDate"), "a.AssetServiceDate", Timestamp.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "GuaranteeDate"), "a.GuaranteeDate", Timestamp.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "VersionNo"), "a.VersionNo", String.class)
	};

	private int fieldID = 0;
	private Textbox fieldValue = new Textbox();
	private Textbox fieldName = new Textbox();
	
	private WEditor fBPartner_ID;
	private WEditor fProduct_ID;

	private Label labelValue = new Label();
	private Label labelName = new Label();
	
	/**
	 *	Standard Constructor - opens in non-modal mode.
	 * @param WindowNo 			window no
	 * @param record_id			The record_id of the asset
	 * @param value    			Query Value or Name if enclosed in @
	 * @param multiSelection 	multiple selections
	 * @param whereClause 		where clause
	 */
	public InfoAssetPanel(	int WindowNo, int record_id, String value,
							boolean multiSelection, String whereClause)
	{
		this(WindowNo, true, record_id, value, multiSelection, false, whereClause);
	}	
	
	/**
	 *	Standard Constructor
	 * @param WindowNo window no
	 * @param record_id The record ID to find
	 * @param value Query value to find, exclusive of record_id
	 * @param saveResults true if results are saved in context
	 * @param multiSelection multiple selections
	 * @param whereClause where clause
	 * @param modal True if window is opened in modal mode.
	 */
	
	public InfoAssetPanel(	int WindowNo, boolean modal, int record_id, String value,
							boolean multiSelection, boolean saveResults, String whereClause)
	{
		super (WindowNo, modal, "a", "A_Asset_ID", multiSelection, saveResults, whereClause);
		
		log.info(value + ", ID=" + record_id + ", WHERE=" + whereClause);
		setTitle(Msg.getMsg(Env.getCtx(), "InfoAsset"));
		//
		StringBuffer where = new StringBuffer();
		where.append("a.IsActive='Y'");
		if (whereClause != null && whereClause.length() > 0)
			where.append(" AND ").append(whereClause);
		setWhereClause(where.toString());
		setTableLayout(s_Layout);
		setFromClause(s_From);
		setOrderClause("a.Value");
		//
		statInit();
		initInfo(record_id, value, whereClause);
		
		//  Auto query
		if(autoQuery() || record_id != 0 || (value != null && value.length() > 0 && value != "%"))
			prepareAndExecuteQuery();
		//
		p_loadedOK = true;
	} // InfoAssetPanel
	
	/**
	 *	Static Setup - add fields to parameterPanel
	 */
	
	private void statInit()
	{
		fieldValue.setWidth("100%");
		fieldName.setWidth("100%");
		
		labelValue.setValue(Msg.getMsg(Env.getCtx(), "Value"));
		fieldValue.addEventListener(Events.ON_CHANGE, this);
		fieldValue.setAttribute("zk_component_ID", "Lookup_Criteria_fieldValue");
		
		labelName.setValue(Msg.getMsg(Env.getCtx(), "Name"));
		fieldName.addEventListener(Events.ON_CANCEL, this);
		fieldName.setAttribute("zk_component_ID", "Lookup_Criteria_fieldName");
		// From A_Asset.
		fBPartner_ID = new WSearchEditor(
				MLookupFactory.get (Env.getCtx(), p_WindowNo, 0, 
						MColumn.getColumn_ID(I_A_Asset.Table_Name, I_C_BPartner.COLUMNNAME_C_BPartner_ID),
						DisplayType.Search), 
						Msg.translate(Env.getCtx(), "C_BPartner_ID"), "", false, false, true);
		fBPartner_ID.addValueChangeListener(this);
		fBPartner_ID.getComponent().setAttribute("zk_component_ID", "Lookup_Criteria_fBPartner_ID");
		
		fProduct_ID = new WSearchEditor(
				MLookupFactory.get (Env.getCtx(), p_WindowNo, 0,  
						MColumn.getColumn_ID(I_A_Asset.Table_Name, I_M_Product.COLUMNNAME_M_Product_ID),
						DisplayType.Search), 
						Msg.translate(Env.getCtx(), "M_Product_ID"), "", false, false, true);
		fProduct_ID.addValueChangeListener(this);
		fProduct_ID.getComponent().setAttribute("zk_component_ID", "Lookup_Criteria_fProduct_ID");
		
		
		Rows rows = new Rows();
		
		Row row = new Row();
		rows.appendChild(row);
		row.appendChild(labelValue.rightAlign());
		row.appendChild(fieldValue);
		row.appendChild(fBPartner_ID.getLabel().rightAlign());
		row.appendChild(fBPartner_ID.getComponent());
		
		row = new Row();
		rows.appendChild(row);
		row.appendChild(labelName.rightAlign());
		row.appendChild(fieldName);
		row.appendChild(fProduct_ID.getLabel().rightAlign());
		row.appendChild(fProduct_ID.getComponent());
		
		p_criteriaGrid.appendChild(rows);
		super.setSizes();
	}
	
	/**
	 *	Dynamic Init
	 *  @param value value
	 *  @param whereClause where clause
	 */
	
	private void initInfo (int record_id, String value, String whereClause)
	{
		//
		if (!(record_id == 0) && value != null && value.length() > 0)
		{
			log.severe("Received both a record_id and a value: " + record_id + " - " + value);
		}

		//  Set Value and boolean criteria (if any)
		if (!(record_id == 0))
		{
			fieldID = record_id;
		}
		else
		{	
			// Use the value if any
			if (value != null && value.length() > 0)
			{
				fieldValue.setText(value);
			}
			else
			{
				//  Try to find the context - A_Asset_ID
	        	String aid = Env.getContext(Env.getCtx(), p_WindowNo, "A_Asset_ID");
				if (aid != null && aid.length() != 0)
				{
					fieldID = Integer.valueOf(aid).intValue();
				}
				//  C_BPartner_ID
				String bp = Env.getContext(Env.getCtx(), p_WindowNo, "C_BPartner_ID");
				if (bp != null && bp.length() != 0)
				{
					fBPartner_ID.setValue(Integer.valueOf(bp).intValue());
				}
				//  M_Product_ID
				String pid = Env.getContext(Env.getCtx(), p_WindowNo, "M_Product_ID");
				if (pid != null && pid.length() != 0)
				{
					fProduct_ID.setValue(Integer.valueOf(pid).intValue());
				}
			}
		}
	} // initInfo
	
	/*************************************************************************/
	/**
	 *	Construct SQL Where Clause and define parameters.
	 *  (setParameters needs to set parameters)
	 *  Includes first AND
	 *  @return WHERE clause
	 */
	
	protected String getSQLWhere()
	{
		StringBuffer sql = new StringBuffer();
		//  => ID
		if(isResetRecordID())
			fieldID = 0;
		if (!(fieldID == 0))
			sql.append(" AND a.A_Asset_ID = ?");
		//	=> Value
		if (isValidSQLText(fieldValue))
			sql.append(" AND UPPER(a.Value) LIKE ?");
		//	=> Name
		if (isValidSQLText(fieldName))
			sql.append (" AND UPPER(a.Name) LIKE ?");
		//	C_BPartner_ID
		Integer C_BPartner_ID = (Integer)fBPartner_ID.getValue();
		if (C_BPartner_ID != null)
			sql.append (" AND a.C_BPartner_ID=").append(C_BPartner_ID);
		//	M_Product_ID
		Integer M_Product_ID = (Integer)fProduct_ID.getValue();
		if (M_Product_ID != null)
			sql.append (" AND a.M_Product_ID=").append(M_Product_ID);
		//
		return sql.toString();
	} // getSQLWhere

	/**
	 *  Set Parameters for Query
	 *  (as defined in getSQLWhere)
	 *
	 * @param pstmt pstmt
	 * @param forCount for counting records
	 * @throws SQLException
	 */
	
	protected void setParameters(PreparedStatement pstmt, boolean forCount) throws SQLException
	{
		int index = 1;
		//  => ID
		if(!(fieldID ==0))
		{
			pstmt.setInt(index++, fieldID);
			log.fine("Record_ID: " + fieldID);
		}
		//	=> Value
		if (isValidSQLText(fieldValue))
		{
			pstmt.setString(index++, getSQLText(fieldValue));
			log.fine("Value: " + fieldValue.getText());
		}
		//	=> Name
		if (isValidSQLText(fieldName))
		{
			pstmt.setString(index++, getSQLText(fieldName));
			log.fine("Name: " + fieldName.getText());
		}
	} // setParameters

	/**
	 *  Save Selection Details
	 *  Get Location/Partner Info
	 */
	
	public void saveSelectionDetail()
	{
		int row = p_table.getSelectedRow();
		
		if (row == -1)
			return;

		//  publish for Callout to read
		
		Integer ID = getSelectedRowKey();
		Env.setContext(Env.getCtx(), p_WindowNo, Env.TAB_INFO, "A_Asset_ID", ID == null ? "0" : ID.toString());
	} // saveSelectionDetail

	// Elaine 2008/12/16
	/**
	 *	Zoom
	 */
	public void zoom(int record_id)
	{
		log.info( "InfoAsset.zoom");
		Integer A_Asset_ID = record_id;
		
		MQuery query = new MQuery("A_Asset");
		query.addRestriction("A_Asset_ID", MQuery.EQUAL, A_Asset_ID);
		query.setRecordCount(1);
		
		int AD_WindowNo = getAD_Window_ID("A_Asset", true);
		AEnv.zoom(AD_WindowNo, query);
	} // zoom
	//

	/**
	 *	Has Zoom
	 *  @return true
	 */
	
	protected boolean hasZoom()
	{
		return true;
	} // hasZoom

}
