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

import org.adempiere.core.domains.models.I_A_Asset;
import org.adempiere.core.domains.models.I_C_BPartner;
import org.adempiere.core.domains.models.I_M_Product;
import org.adempiere.plaf.AdempierePLAF;
import org.compiere.apps.AEnv;
import org.compiere.apps.ALayoutConstraint;
import org.compiere.grid.ed.VLookup;
import org.compiere.minigrid.IDColumn;
import org.compiere.model.MColumn;
import org.compiere.model.MLookupFactory;
import org.compiere.model.MQuery;
import org.compiere.swing.CLabel;
import org.compiere.swing.CTextField;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Msg;

/**
 *	Asset Information
 *	
 *  @author Jorg Janke
 *  @version $Id: InfoAsset.java,v 1.2 2006/07/30 00:51:27 jjanke Exp $
 *
 * @author Michael McKay, 
 * 				<li>ADEMPIERE-72 VLookup and Info Window improvements
 * 					https://adempiere.atlassian.net/browse/ADEMPIERE-72
 * 				<li>release/380 fix listeners on some fields
 */
public class InfoAsset extends Info
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6014685562933753813L;

	/**
	 *	Standard Constructor

	 * @param frame frame
	 * @param modal modal
	 * @param WindowNo window no
	 * @param A_Asset_ID asset
	 * @param value    Query Value or Name if enclosed in @
	 * @param multiSelection multiple selections
	 * @param whereClause where clause
	 */
	@Deprecated
	public InfoAsset (Frame frame, boolean modal, int WindowNo,
		int A_Asset_ID, String value,
		boolean multiSelection, String whereClause)
	{
		this(frame, modal, WindowNo,
				0, value,
				multiSelection, true, whereClause);
	}
	
	/**
	 *	Standard Constructor

	 * @param frame frame
	 * @param modal modal
	 * @param WindowNo window no
	 * @param record_id The record ID to find
	 * @param value query value to find, exclusive of record_id
	 * @param multiSelection multiple selections
	 * @param saveResults  True if results will be saved, false for info only
	 * @param whereClause where clause
	 */
	public InfoAsset (Frame frame, boolean modal, int WindowNo,
		int record_id, String value,
		boolean multiSelection, boolean saveResults, String whereClause)
	{
		super (frame, modal, WindowNo, "a", "A_Asset_ID", multiSelection, saveResults, whereClause);
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
		initInfo (record_id, value);

		//  To get the focus after the table update
		m_heldLastFocus = fieldValue;
		
		//	AutoQuery
		if(autoQuery() || record_id != 0 || (value != null && value.length() > 0 && value != "%"))
			executeQuery();
		
		p_loadedOK = true;

		AEnv.positionCenterWindow(frame, this);
	}	//	InfoProduct

	/** From Clause             */
	private static String s_From = "A_ASSET a"
		+ " LEFT OUTER JOIN M_Product p ON (a.M_Product_ID=p.M_Product_ID)"
		+ " LEFT OUTER JOIN C_BPartner bp ON (a.C_BPartner_ID=bp.C_BPartner_ID)"
		+ " LEFT OUTER JOIN AD_User u ON (a.AD_User_ID=u.AD_User_ID)";

	/**  Array of Column Info    */
	private static final Info_Column[] s_Layout = {
		new Info_Column(" ", "a.A_Asset_ID", IDColumn.class),
		new Info_Column(Msg.translate(Env.getCtx(), "Value"), "a.Value", String.class),
		new Info_Column(Msg.translate(Env.getCtx(), "Name"), "a.Name", String.class),
		new Info_Column(Msg.translate(Env.getCtx(), "M_Product_ID"), "p.Name", String.class),
		new Info_Column(Msg.translate(Env.getCtx(), "C_BPartner_ID"), "bp.Name",  String.class),
		new Info_Column(Msg.translate(Env.getCtx(), "AD_User_ID"), "u.Name", String.class),
		new Info_Column(Msg.translate(Env.getCtx(), "AssetServiceDate"), "a.AssetServiceDate", Timestamp.class),
		new Info_Column(Msg.translate(Env.getCtx(), "GuaranteeDate"), "a.GuaranteeDate", Timestamp.class),
		new Info_Column(Msg.translate(Env.getCtx(), "VersionNo"), "a.VersionNo", String.class)
	};
	
	//
	private int fieldID = 0;
	private CLabel labelValue = new CLabel();
	private CTextField fieldValue = new CTextField(10);
	private CLabel labelName = new CLabel();
	private CTextField fieldName = new CTextField(10);
	//
	private CLabel lBPartner_ID = new CLabel(Msg.translate(Env.getCtx(), "BPartner"));
	private VLookup fBPartner_ID;
	private CLabel lProduct_ID = new CLabel(Msg.translate(Env.getCtx(), "Product"));
	private VLookup fProduct_ID;
	
	/**
	 *	Static Setup - add fields to parameterPanel
	 */
	private void statInit()
	{
		labelValue.setText(Msg.getMsg(Env.getCtx(), "Value"));
		fieldValue.setBackground(AdempierePLAF.getInfoBackground());
		fieldValue.addActionListener(this);
		labelName.setText(Msg.getMsg(Env.getCtx(), "Name"));
		fieldName.setBackground(AdempierePLAF.getInfoBackground());
		fieldName.addActionListener(this);
		//	From A_Asset.
		fBPartner_ID = new VLookup("C_BPartner_ID", false, false, true,
			MLookupFactory.get (Env.getCtx(), p_WindowNo, 0, 
					MColumn.getColumn_ID(I_A_Asset.Table_Name, I_C_BPartner.COLUMNNAME_C_BPartner_ID),
					DisplayType.Search));
		lBPartner_ID.setLabelFor(fBPartner_ID);
		fBPartner_ID.setBackground(AdempierePLAF.getInfoBackground());
		fBPartner_ID.addActionListener(this);
		
		fProduct_ID = new VLookup("M_Product_ID", false, false, true,
			MLookupFactory.get (Env.getCtx(), p_WindowNo, 0, 
					MColumn.getColumn_ID(I_A_Asset.Table_Name, I_M_Product.COLUMNNAME_M_Product_ID),
					DisplayType.Search));
		lProduct_ID.setLabelFor(fProduct_ID);
		fProduct_ID.setBackground(AdempierePLAF.getInfoBackground());
		fProduct_ID.addActionListener(this);
		//
		p_criteriaGrid.add(labelValue, new ALayoutConstraint(0,0));
		p_criteriaGrid.add(fieldValue, null);
		p_criteriaGrid.add(lBPartner_ID, null);
		p_criteriaGrid.add(fBPartner_ID, null);
		//		
		p_criteriaGrid.add(labelName, new ALayoutConstraint(1,0));
		p_criteriaGrid.add(fieldName, null);
		p_criteriaGrid.add(lProduct_ID, null);
		p_criteriaGrid.add(fProduct_ID, null);
	}	//	statInit

	/**
	 *	Dynamic Init
	 */
	protected void initInfo (int record_id, String value)
	{
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
	}	//	initInfo

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
	}	//	getSQLWhere

	/**
	 *  Set Parameters for Query
	 *  (as defined in getSQLWhere)
	 *
	 * @param pstmt pstmt
	 *  @param forCount for counting records
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
	}	//	setParameters

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
	}   //  saveSelectionDetail


	/*************************************************************************/

	/**
	 *	Show History
	 */
	protected void showHistory()
	{
		log.info( "InfoAsset.showHistory");
	}	//	showHistory

	/**
	 *	Has History
	 *  @return true
	 */
	protected boolean hasHistory()
	{
		return false;
	}	//	hasHistory

	/**
	 *	Zoom
	 */
	protected void zoom(int record_ID)
	{
		log.info( "InfoAsset.zoom");
		Integer A_Asset_ID = record_ID;
		MQuery query = new MQuery("A_Asset");
		query.addRestriction("A_Asset_ID", MQuery.EQUAL, A_Asset_ID);
		query.setRecordCount(1);
		int AD_WindowNo = getAD_Window_ID("A_Asset", true);
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
	 *	Customize
	 */
	protected void customize()
	{
		log.info( "InfoAsset.customize");
	}	//	customize

	/**
	 *	Has Customize
	 *  @return false
	 */
	protected boolean hasCustomize()
	{
		return false;	//	for now
	}	//	hasCustomize

	/**
	 * Does the parameter panel have outstanding changes that have not been
	 * used in a query?
	 * @return true if there are outstanding changes.
	 */
	protected boolean hasOutstandingChanges()
	{
		//  All the tracked fields
		return(
				fieldValue.hasChanged()	||
				fieldName.hasChanged()	||
				fProduct_ID.hasChanged()	||
				fBPartner_ID.hasChanged());
	}
	/**
	 * Record outstanding changes by copying the current
	 * value to the oldValue on all fields
	 */
	protected void setFieldOldValues()
	{
		fieldValue.set_oldValue();
		fieldName.set_oldValue();
		fProduct_ID.set_oldValue();
		fBPartner_ID.set_oldValue();
		return;
	}
	/**
	 *  Clear all fields and set default values in check boxes
	 */
	protected void clearParameters()
	{
		//  Clear fields and set defaults
		fieldValue.setText("");
		fieldName.setText("");
    	fProduct_ID.setValue(null);
    	fBPartner_ID.setValue(null);
	}

}	//	InfoAsset
