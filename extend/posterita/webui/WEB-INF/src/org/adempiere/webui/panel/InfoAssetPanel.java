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

import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.Textbox;
import org.adempiere.webui.component.VerticalBox;
import org.adempiere.webui.editor.WEditor;
import org.adempiere.webui.editor.WSearchEditor;
import org.adempiere.webui.event.ValueChangeEvent;
import org.adempiere.webui.event.ValueChangeListener;
import org.adempiere.webui.event.WTableModelEvent;
import org.compiere.minigrid.ColumnInfo;
import org.compiere.minigrid.IDColumn;
import org.compiere.model.MLookupFactory;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Separator;

/**
* Based on InfoPayment written by Jorg Janke
*  
* @author 	Niraj Sohun
* 			Aug, 02, 2007
*/

public class InfoAssetPanel extends InfoPanel implements ValueChangeListener, EventListener
{
	private static final long serialVersionUID = 1L;

	/** From Clause             */
	private static String s_assetFROM = "A_ASSET a"
		+ " LEFT OUTER JOIN M_Product p ON (a.M_Product_ID=p.M_Product_ID)"
		+ " LEFT OUTER JOIN C_BPartner bp ON (a.C_BPartner_ID=bp.C_BPartner_ID)"
		+ " LEFT OUTER JOIN AD_User u ON (a.AD_User_ID=u.AD_User_ID)";

	/**  Array of Column Info    */
	private static final ColumnInfo[] s_assetLayout = {
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

	private Textbox fieldValue = new Textbox();
	private Textbox fieldName = new Textbox();
	
	private WEditor fBPartner_ID;
	private WEditor fProduct_ID;

	private Label labelValue = new Label();
	private Label labelName = new Label();
	
	/**
	 *	Standard Constructor

	 * @param WindowNo window no
	 * @param A_Asset_ID asset
	 * @param value    Query Value or Name if enclosed in @
	 * @param multiSelection multiple selections
	 * @param whereClause where clause
	 */
	
	public InfoAssetPanel(	int WindowNo, int A_Asset_ID, String value,
							boolean multiSelection, String whereClause)
	{
		super (WindowNo, "a", "A_Asset_ID", multiSelection, whereClause);
		
		log.info(value + ", ID=" + A_Asset_ID + ", WHERE=" + whereClause);
		setTitle(Msg.getMsg(Env.getCtx(), "InfoAsset"));

		statInit();
		initInfo(value, A_Asset_ID, whereClause);

		int no = contentPanel.getRowCount();
		setStatusLine(Integer.toString(no) + " " + Msg.getMsg(Env.getCtx(), "SearchRows_EnterQuery"), false);
		setStatusDB(Integer.toString(no));
		
		//	AutoQuery
		
		if (value != null && value.length() > 0)
			executeQuery();
		
		p_loadedOK = true;
		
		//	Focus
		//	fieldValue.requestFocus();

		//AEnv.positionCenterWindow(frame, this);
	} // InfoProduct
	
	/**
	 *	Static Setup - add fields to parameterPanel
	 */
	
	private void statInit()
	{
		Hbox boxValue = new Hbox();
				
		labelValue.setValue(Msg.getMsg(Env.getCtx(), "Value"));
		fieldValue.addEventListener(Events.ON_CHANGE, this);
		
		boxValue.setWidth("100%");
		boxValue.setWidths("40%, 60%");
		boxValue.appendChild(labelValue);
		boxValue.appendChild(fieldValue);
		
		Hbox boxName = new Hbox();
				
		labelName.setValue(Msg.getMsg(Env.getCtx(), "Name"));
		fieldName.addEventListener(Events.ON_CANCEL, this);
		
		boxName.setWidth("100%");
		boxName.setWidths("40%, 60%");
		boxName.appendChild(labelName);
		boxName.appendChild(fieldName);
		
		//	From A_Asset.
		
		Hbox boxBPartner = new Hbox();
				
		fBPartner_ID = new WSearchEditor(
				MLookupFactory.get (Env.getCtx(), p_WindowNo, 0, 8065, DisplayType.Search), 
				Msg.translate(Env.getCtx(), "C_BPartner_ID"), "", false, false, true);
		fBPartner_ID.addValueChangeListner(this);
		
		boxBPartner.setWidth("100%");
		boxBPartner.setWidths("40%, ");
		
		boxBPartner.appendChild(fBPartner_ID.getLabel());
		boxBPartner.appendChild(fBPartner_ID.getComponent());
		
		
		Hbox boxProduct = new Hbox();
				
		fProduct_ID = new WSearchEditor(
				MLookupFactory.get (Env.getCtx(), p_WindowNo, 0, 8047, DisplayType.Search), 
				Msg.translate(Env.getCtx(), "M_Product_ID"), "", false, false, true);
		fProduct_ID.addValueChangeListner(this);
		
		boxProduct.appendChild(fProduct_ID.getLabel());
		boxProduct.appendChild(fProduct_ID.getComponent());
		
		VerticalBox boxCol1 = new VerticalBox(); 
		boxCol1.appendChild(boxValue);
		boxCol1.appendChild(new Separator());
		boxCol1.appendChild(boxName);
		
		VerticalBox boxCol2 = new VerticalBox();
		boxCol2.appendChild(boxBPartner);
		boxCol2.appendChild(new Separator());
		boxCol2.appendChild(boxProduct);
		
		Hbox mainBox = new Hbox();
		mainBox.setWidth("100%");
		mainBox.setWidths("30%, 70%");
		mainBox.appendChild(boxCol1);
		mainBox.appendChild(boxCol2);
		
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
	
	private void initInfo (String value, int A_Asset_ID, String whereClause)
	{
		//	Create Grid
		
		StringBuffer where = new StringBuffer();
		where.append("a.IsActive='Y'");
		
		if (whereClause != null && whereClause.length() > 0)
			where.append(" AND ").append(whereClause);
		
		prepareTable(s_assetLayout, s_assetFROM, where.toString(), "a.Value");

		//  Set Value
		
		if (value == null)
			value = "%";
		
		if (!value.endsWith("%"))
			value += "%";
	} // initInfo
	
	/*************************************************************************/
	/**
	 *	Construct SQL Where Clause and define parameters.
	 *  (setParameters needs to set parameters)
	 *  Includes first AND
	 *  @return WHERE clause
	 */
	
	String getSQLWhere()
	{
		StringBuffer sql = new StringBuffer();
	
		//	=> Value
		
		String value = fieldValue.getText().toUpperCase();
		
		if (!(value.equals("") || value.equals("%")))
			sql.append(" AND UPPER(a.Value) LIKE ?");
		
		//	=> Name
		
		String name = fieldName.getText().toUpperCase();
		
		if (!(name.equals("") || name.equals("%")))
			sql.append (" AND UPPER(a.Name) LIKE ?");
		
		//	C_BPartner_ID
		
		Integer C_BPartner_ID = null;
		
		if (fBPartner_ID.getDisplay() != "")
			C_BPartner_ID = (Integer)fBPartner_ID.getValue();
		
		if (C_BPartner_ID != null)
			sql.append (" AND a.C_BPartner_ID=").append(C_BPartner_ID);

		//	M_Product_ID
		
		Integer M_Product_ID = null;
		
		if (fProduct_ID.getDisplay() != "")
			M_Product_ID = (Integer)fProduct_ID.getValue();
		
		if (M_Product_ID != null)
			sql.append (" AND a.M_Product_ID=").append(M_Product_ID);

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
	
	void setParameters(PreparedStatement pstmt, boolean forCount) throws SQLException
	{
		int index = 1;
		
		//	=> Value
		
		String value = fieldValue.getText().toUpperCase();
		
		if (!(value.equals("") || value.equals("%")))
		{
			if (!value.endsWith("%"))
				value += "%";
		
			pstmt.setString(index++, value);
			log.fine("Value: " + value);
		}
		
		//	=> Name
		
		String name = fieldName.getText().toUpperCase();
		
		if (!(name.equals("") || name.equals("%")))
		{
			if (!name.endsWith("%"))
				name += "%";
		
			pstmt.setString(index++, name);
			log.fine("Name: " + name);
		}
	} // setParameters

	/**
	 *  Save Selection Details
	 *  Get Location/Partner Info
	 */
	
	public void saveSelectionDetail()
	{
		int row = contentPanel.getSelectedRow();
		
		if (row == -1)
			return;

		//  publish for Callout to read
		
		Integer ID = getSelectedRowKey();
		Env.setContext(Env.getCtx(), Env.WINDOW_INFO, Env.TAB_INFO, "A_Asset_ID", ID == null ? "0" : ID.toString());
	} // saveSelectionDetail

	/*************************************************************************/
	/**
	 *	Show History
	 */

	void showHistory()
	{
		log.info( "InfoAsset.showHistory");
	}	//	showHistory

	/**
	 *	Has History
	 *  @return true
	 */
	
	boolean hasHistory()
	{
		return false;
	} // hasHistory

	/**
	 *	Zoom
	 */
	
/*	public void zoom()
	{
		log.info( "InfoAsset.zoom");
		Integer A_Asset_ID = getSelectedRowKey();
		
		if (A_Asset_ID == null)
			return;
		
		MQuery query = new MQuery("A_Asset");
		query.addRestriction("A_Asset_ID", MQuery.EQUAL, A_Asset_ID);
		query.setRecordCount(1);
		
		int AD_WindowNo = getAD_Window_ID("A_Asset", true);
		super.zoom (AD_WindowNo, query);
	} // zoom
*/
	/**
	 *	Has Zoom
	 *  @return true
	 */
	
	boolean hasZoom()
	{
		return true;
	} // hasZoom

	/**
	 *	Customize
	 */
	
	void customize()
	{
		log.info( "InfoAsset.customize");
	} // customize

	/**
	 *	Has Customize
	 *  @return false
	 */
	
	boolean hasCustomize()
	{
		return false; // for now
	} // hasCustomize
	
	public void tableChanged(WTableModelEvent event) 
	{
		
	}
	
	public void valueChange(ValueChangeEvent evt)
	{
		if (fBPartner_ID.equals(evt.getSource()))
		{
	    	fBPartner_ID.setValue(evt.getNewValue());
		}
		
		if (fProduct_ID.equals(evt.getSource()))
		{
			fProduct_ID.setValue(evt.getNewValue());
		}
	}

}
