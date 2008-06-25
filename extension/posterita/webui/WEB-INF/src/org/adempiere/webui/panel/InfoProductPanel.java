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

/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                        *
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
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;

import org.adempiere.webui.component.Button;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.ListItem;
import org.adempiere.webui.component.Listbox;
import org.adempiere.webui.component.Panel;
import org.adempiere.webui.component.Textbox;
import org.adempiere.webui.component.WListbox;
import org.adempiere.webui.event.WTableModelEvent;
import org.compiere.minigrid.ColumnInfo;
import org.compiere.minigrid.IDColumn;
import org.compiere.model.MClient;
import org.compiere.model.MRole;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Msg;
import org.compiere.util.Util;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Div;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Vbox;

/**
 *	Search Product and return selection
 *  This class is based on org.compiere.apps.search.InfoProduct written by Jorg Janke
 *  @author     Sendy Yagambrum
 *  
 */
public final class InfoProductPanel extends InfoPanel implements EventListener
{
	
	/**
     * 
    **/
    private static final long serialVersionUID = 1L;

    private Label lblValue = new Label();
	private Textbox fieldValue = new Textbox();
	private Label lblName = new Label();
	private Textbox fieldName = new Textbox();
	private Label lblUPC = new Label();
	private Textbox fieldUPC = new Textbox();
	private Label lblSKU = new Label();
	private Textbox fieldSKU = new Textbox();
	private Label lblPriceList = new Label();
	private Listbox pickPriceList = new Listbox();
	private Label lblWarehouse = new Label();
	private Listbox pickWarehouse = new Listbox();
	private Label lblVendor = new Label();
	private Textbox fieldVendor = new Textbox();
	private Button p_attributeInfo;
	/** SQL From				*/
	private static final String s_productFrom =
		"M_Product p"
		+ " LEFT OUTER JOIN M_ProductPrice pr ON (p.M_Product_ID=pr.M_Product_ID AND pr.IsActive='Y')"
		+ " LEFT OUTER JOIN M_AttributeSet pa ON (p.M_AttributeSet_ID=pa.M_AttributeSet_ID)"
		+ " LEFT OUTER JOIN M_Product_PO ppo ON (p.M_Product_ID=ppo.M_Product_ID)"
		+ " LEFT OUTER JOIN C_BPartner bp ON (ppo.C_BPartner_ID=bp.C_BPartner_ID)";

	/**  Array of Column Info    */
	private static ColumnInfo[] s_productLayout = null;
	private static int INDEX_NAME = 0;
	private static int INDEX_PATTRIBUTE = 0;

	
	/** ASI							*/
	private int			m_M_AttributeSetInstance_ID = -1;
	/** Locator						*/
	private int			m_M_Locator_ID = 0;

	private String		m_pAttributeWhere = null;
	private int			m_C_BPartner_ID = 0;

	/**
	 *	Standard Constructor
	 * 	@param WindowNo window no
	 * 	@param M_Warehouse_ID warehouse
	 * 	@param M_PriceList_ID price list
	 * 	@param value    Query Value or Name if enclosed in @
	 * 	@param whereClause where clause
	 */
	public InfoProductPanel(int windowNo,
		int M_Warehouse_ID, int M_PriceList_ID, boolean multipleSelection,String value,
		 String whereClause)
	{
		super (windowNo, "p", "M_Product_ID",multipleSelection, whereClause);
		log.info(value + ", Wh=" + M_Warehouse_ID + ", PL=" + M_PriceList_ID + ", WHERE=" + whereClause);
		setTitle(Msg.getMsg(Env.getCtx(), "InfoProduct"));
		//
		initComponents();
		init();
		initInfo (value, M_Warehouse_ID, M_PriceList_ID);
		m_C_BPartner_ID = Env.getContextAsInt(Env.getCtx(), windowNo, "C_BPartner_ID");
        
        int no = contentPanel.getRowCount();
        setStatusLine(Integer.toString(no) + " " + Msg.getMsg(Env.getCtx(), "SearchRows_EnterQuery"), false);
        setStatusDB(Integer.toString(no));
		//	AutoQuery
		if (value != null && value.length() > 0)
        {    
			executeQuery();
            renderItems();
        }
		p_loadedOK = true;
		
		
	}	//	InfoProductPanel
	
	
	/**
	 *	initialize fields
	 */
	private void initComponents()
	{
		lblValue = new Label();
		lblValue.setValue(Msg.translate(Env.getCtx(), "Value").substring(1));
		lblName = new Label();
		lblName.setValue(Msg.translate(Env.getCtx(), "Name").substring(1));
		lblUPC =  new Label();
		lblUPC.setValue(Msg.translate(Env.getCtx(), "UPC"));
		lblSKU = new Label();
		lblSKU.setValue(Msg.translate(Env.getCtx(), "SKU"));
		lblPriceList = new Label();
		lblPriceList.setValue(Msg.getMsg(Env.getCtx(), "PriceListVersion"));
		lblWarehouse = new Label();
		lblWarehouse.setValue(Msg.getMsg(Env.getCtx(), "Warehouse").substring(1));
		lblVendor = new Label();
		lblVendor.setValue(Msg.translate(Env.getCtx(), "Vendor"));	
		
	/*	p_attributeInfo = new Button();
		p_attributeInfo.setImage("/images/PAttribute16.gif");
		p_attributeInfo.setTooltiptext("Poduct Atribute Info");
		p_attributeInfo.addEventListener(Events.ON_CLICK,this);*/
		
		fieldValue = new Textbox();
		fieldValue.setMaxlength(40);
		fieldName = new Textbox();
		fieldName.setMaxlength(40);
		fieldUPC = new Textbox();
		fieldUPC.setMaxlength(40);
		fieldSKU = new Textbox();
		fieldSKU.setMaxlength(40);
		pickPriceList = new Listbox();
		pickPriceList.setRows(0);
		pickPriceList.setMultiple(false);
		pickPriceList.setMold("select");
		pickPriceList.setWidth("150px");
		pickPriceList.addEventListener(Events.ON_SELECT, this);
		
		pickWarehouse = new Listbox();
		pickWarehouse.setRows(0);
		pickWarehouse.setMultiple(false);
		pickWarehouse.setMold("select");
		pickWarehouse.setWidth("150px");
		pickWarehouse.addEventListener(Events.ON_SELECT, this);
		
		fieldVendor = new Textbox();
		fieldVendor.setMaxlength(40);	
        
        contentPanel = new WListbox();
        contentPanel.setWidth("1500px");
        contentPanel.setHeight("500px");
	}	//	initComponents
	
	private void init()
	{

		Panel pnlValue = new Panel();
		pnlValue.appendChild(lblValue);
		pnlValue.appendChild(fieldValue);		
		pnlValue.setAlign("right");
		
		Panel pnlName = new Panel();
		pnlName.appendChild(lblName);
		pnlName.appendChild(fieldName);
		pnlName.setAlign("right");
		
		Panel pnlUPC = new Panel();
		pnlUPC.appendChild(lblUPC);
		pnlUPC.appendChild(fieldUPC);
		pnlUPC.setAlign("right");
		
		Panel pnlSKU = new Panel();
		pnlSKU.appendChild(lblSKU);
		pnlSKU.appendChild(fieldSKU);
		pnlSKU.setAlign("right");
		
		Panel pnlPriceList = new Panel();
		pnlPriceList.appendChild(lblPriceList);
		pnlPriceList.appendChild(pickPriceList);
		pnlPriceList.setAlign("right");
		
		Panel pnlWarehouse = new Panel();
		pnlWarehouse.appendChild(lblWarehouse);
		pnlWarehouse.appendChild(pickWarehouse);
		pnlWarehouse.setAlign("right");
		
		Panel pnlVendor = new Panel();
		pnlVendor.appendChild(lblVendor);
		pnlVendor.appendChild(fieldVendor);
		pnlVendor.setAlign("right");
		
		/*Panel pnlButton = new Panel();
		pnlButton.appendChild(p_attributeInfo);
		pnlButton.setAlign("left");*/
				
		Vbox vbox1 = new Vbox();
		vbox1.appendChild(pnlValue);
		vbox1.appendChild(pnlName);
		
		Vbox vbox2 = new Vbox();
		vbox2.appendChild(pnlUPC);
		vbox2.appendChild(pnlSKU);
		
		Vbox vbox3 = new Vbox();
		vbox3.appendChild(pnlWarehouse);
		vbox3.appendChild(pnlVendor);
		
		Vbox vbox4 = new Vbox();
		/*vbox4.appendChild(pnlButton);*/
		vbox4.appendChild(pnlPriceList);
		
		
		Hbox parameterPanel = new Hbox();
        parameterPanel.appendChild(vbox1);
        parameterPanel.appendChild(vbox2);
        parameterPanel.appendChild(vbox3);
        parameterPanel.appendChild(vbox4);
		
		Vbox mainPanel = new Vbox();
        mainPanel.setWidth("100%");
        mainPanel.appendChild(parameterPanel);
        Div div = new Div();
        div.setStyle("overflow:auto");
        div.setWidth("100%");
        div.appendChild(contentPanel);
        mainPanel.appendChild(div);
        mainPanel.appendChild(confirmPanel);
		
		this.appendChild(mainPanel);
		this.setBorder("normal");
		this.setWidth("1000px");
	}

	/**
	 *	Dynamic Init
	 *
	 * @param value value
	 * @param M_Warehouse_ID warehouse
	 * @param M_PriceList_ID price list
	 */
	private void initInfo (String value, int M_Warehouse_ID, int M_PriceList_ID)
	{
		//	Pick init
		fillPicks(M_PriceList_ID);
		int M_PriceList_Version_ID = findPLV (M_PriceList_ID);
		//	Set Value or Name
		if (value.startsWith("@") && value.endsWith("@"))
			fieldName.setText(value.substring(1,value.length()-1));
		else
			fieldValue.setText(value);
		//	Set Warehouse
		if (M_Warehouse_ID == 0)
			M_Warehouse_ID = Env.getContextAsInt(Env.getCtx(), "#M_Warehouse_ID");
		if (M_Warehouse_ID != 0)
			setWarehouse (M_Warehouse_ID);
		// 	Set PriceList Version
		if (M_PriceList_Version_ID != 0)
			setPriceListVersion (M_PriceList_Version_ID);

		//	Create Grid
		StringBuffer where = new StringBuffer();
		where.append("p.IsActive='Y'");
		if (M_Warehouse_ID != 0)
			where.append(" AND p.IsSummary='N'");
		//  dynamic Where Clause
		if (p_whereClause != null && p_whereClause.length() > 0)
			where.append(" AND ")   //  replace fully qalified name with alias
				.append(Util.replace(p_whereClause, "M_Product.", "p."));
		//
		prepareTable(getProductLayout(),
			s_productFrom,
			where.toString(),
			"QtyAvailable DESC, Margin DESC");

		//
		pickWarehouse.addEventListener(Events.ON_SELECT,this);
		pickPriceList.addEventListener(Events.ON_SELECT,this);
	}	//	initInfo

	/**
	 *	Fill Picks with values
	 *
	 * @param M_PriceList_ID price list
	 */
	private void fillPicks (int M_PriceList_ID)
	{
		//	Price List
		String SQL = "SELECT M_PriceList_Version.M_PriceList_Version_ID,"
			+ " M_PriceList_Version.Name || ' (' || c.Iso_Code || ')' AS ValueName "
			+ "FROM M_PriceList_Version, M_PriceList pl, C_Currency c "
			+ "WHERE M_PriceList_Version.M_PriceList_ID=pl.M_PriceList_ID"
			+ " AND pl.C_Currency_ID=c.C_Currency_ID"
			+ " AND M_PriceList_Version.IsActive='Y' AND pl.IsActive='Y'";
		//	Same PL currency as original one
		if (M_PriceList_ID != 0)
			SQL += " AND EXISTS (SELECT * FROM M_PriceList xp WHERE xp.M_PriceList_ID=" + M_PriceList_ID
				+ " AND pl.C_Currency_ID=xp.C_Currency_ID)";
		//	Add Access & Order
		SQL = MRole.getDefault().addAccessSQL (SQL, "M_PriceList_Version", true, false)	// fully qualidfied - RO 
			+ " ORDER BY M_PriceList_Version.Name";
		try
		{
			pickPriceList.appendItem("",new Integer(0));
			PreparedStatement pstmt = DB.prepareStatement(SQL, null);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
			{
				KeyNamePair kn = new KeyNamePair (rs.getInt(1), rs.getString(2));
				pickPriceList.appendItem(rs.getString(2),new Integer(rs.getInt(1)));
			}
			rs.close();
			pstmt.close();

			//	Warehouse
			SQL = MRole.getDefault().addAccessSQL (
				"SELECT M_Warehouse_ID, Value || ' - ' || Name AS ValueName "
				+ "FROM M_Warehouse "
				+ "WHERE IsActive='Y'",
					"M_Warehouse", MRole.SQL_NOTQUALIFIED, MRole.SQL_RO)
				+ " ORDER BY Value";
			pickWarehouse.appendItem("", new Integer(0));
			pstmt = DB.prepareStatement(SQL, null);
			rs = pstmt.executeQuery();
			while (rs.next())
			{
				KeyNamePair kn = new KeyNamePair
					(rs.getInt("M_Warehouse_ID"), rs.getString("ValueName"));
				pickWarehouse.appendItem(rs.getString("ValueName"), new Integer(rs.getInt("M_Warehouse_ID")));
			}
			rs.close();
			pstmt.close();
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, SQL, e);
			setStatusLine(e.getLocalizedMessage(), true);
		}
	}	//	fillPicks

	/**
	 *	Set Warehouse
	 *
	 * 	@param M_Warehouse_ID warehouse
	 */
	private void setWarehouse(int M_Warehouse_ID)
	{
		for (int i = 0; i < pickWarehouse.getItemCount(); i++)
		{
			 Integer key = (Integer) pickWarehouse.getItemAtIndex(i).getValue();
			if (key == M_Warehouse_ID)
			{
				pickWarehouse.setSelectedIndex(i);
				return;
			}
		}
	}	//	setWarehouse

	/**
	 *	Set PriceList
	 *
	 * @param M_PriceList_Version_ID price list
	 */
	private void setPriceListVersion(int M_PriceList_Version_ID)
	{
		log.config("M_PriceList_Version_ID=" + M_PriceList_Version_ID);
		for (int i = 0; i < pickPriceList.getItemCount(); i++)
		{
			Integer key = (Integer) pickPriceList.getItemAtIndex(i).getValue();
			if (key == M_PriceList_Version_ID)
			{
				pickPriceList.setSelectedIndex(i);
				return;
			}
		}
		log.fine("NOT found");
	}	//	setPriceList

	/**
	 *	Find Price List Version and update context
	 *
	 * @param M_PriceList_ID price list
	 * @return M_PriceList_Version_ID price list version
	 */
	private int findPLV (int M_PriceList_ID)
	{
		Timestamp priceDate = null;
		//	Sales Order Date
		String dateStr = Env.getContext(Env.getCtx(), p_WindowNo, "DateOrdered"); 
		if (dateStr != null && dateStr.length() > 0)
			priceDate = Env.getContextAsDate(Env.getCtx(), p_WindowNo, "DateOrdered");
		else	//	Invoice Date
		{
			dateStr = Env.getContext(Env.getCtx(), p_WindowNo, "DateInvoiced");
			if (dateStr != null && dateStr.length() > 0)
				priceDate = Env.getContextAsDate(Env.getCtx(), p_WindowNo, "DateInvoiced");
		}
		//	Today
		if (priceDate == null) 
			priceDate = new Timestamp(System.currentTimeMillis());
		//
		log.config("M_PriceList_ID=" + M_PriceList_ID + " - " + priceDate);
		int retValue = 0;
		String sql = "SELECT plv.M_PriceList_Version_ID, plv.ValidFrom "
			+ "FROM M_PriceList pl, M_PriceList_Version plv "
			+ "WHERE pl.M_PriceList_ID=plv.M_PriceList_ID"
			+ " AND plv.IsActive='Y'"
			+ " AND pl.M_PriceList_ID=? "					//	1
			+ "ORDER BY plv.ValidFrom DESC";
		//	find newest one
		try
		{
			PreparedStatement pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, M_PriceList_ID);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next() && retValue == 0)
			{
				Timestamp plDate = rs.getTimestamp(2);
				if (!priceDate.before(plDate))
					retValue = rs.getInt(1);
			}
			rs.close();
			pstmt.close();
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		Env.setContext(Env.getCtx(), p_WindowNo, "M_PriceList_Version_ID", retValue);
		return retValue;
	}	//	findPLV

	
	/**************************************************************************
	 *	Construct SQL Where Clause and define parameters
	 *  (setParameters needs to set parameters)
	 *  Includes first AND
	 *  @return SQL WHERE clause
	 */
	public String getSQLWhere()
	{
		StringBuffer where = new StringBuffer();
		
		//	Optional PLV
		int M_PriceList_Version_ID = 0;
		ListItem listitem = pickPriceList.getSelectedItem();
		if (listitem != null)
			M_PriceList_Version_ID = (Integer)listitem.getValue();
		if (M_PriceList_Version_ID != 0)
			where.append(" AND pr.M_PriceList_Version_ID=?");
		
		//	Product Attribute Search
		if (m_pAttributeWhere != null)
		{
			where.append(m_pAttributeWhere);
			return where.toString();
		}

		//  => Value
		String value = fieldValue.getText().toUpperCase();
		if (!(value.equals("") || value.equals("%")))
			where.append(" AND UPPER(p.Value) LIKE ?");

		//  => Name
		String name = fieldName.getText().toUpperCase();
		if (!(name.equals("") || name.equals("%")))
			where.append(" AND UPPER(p.Name) LIKE ?");

		//  => UPC
		String upc = fieldUPC.getText().toUpperCase();
		if (!(upc.equals("") || upc.equals("%")))
			where.append(" AND UPPER(p.UPC) LIKE ?");

		//  => SKU
		String sku = fieldSKU.getText().toUpperCase();
		if (!(sku.equals("") || sku.equals("%")))
			where.append(" AND UPPER(p.SKU) LIKE ?");
		//	=> Vendor
		String vendor = fieldVendor.getText().toUpperCase();
		if (!(vendor.equals("") || vendor.equals("%")))
			where.append(" AND UPPER(bp.Name) LIKE ?");
		
		return where.toString();
	}	//	getSQLWhere

	/**
	 *  Set Parameters for Query
	 *  (as defined in getSQLWhere)	 *
	 * @param pstmt pstmt
	 *  @param forCount for counting records
	 * @throws SQLException
	 */
	void setParameters(PreparedStatement pstmt, boolean forCount) throws SQLException
	{
		int index = 1;

		//  => Warehouse
		int M_Warehouse_ID = 0;
		ListItem listitem = pickWarehouse.getSelectedItem();
		if (listitem != null)
			M_Warehouse_ID = (Integer)listitem.getValue();
		if (!forCount)	//	parameters in select
		{
			for (int i = 0; i < p_layout.length; i++)
			{
				if (p_layout[i].getColSQL().indexOf('?') != -1)
					pstmt.setInt(index++, M_Warehouse_ID);
			}
		}
		log.fine("M_Warehouse_ID=" + M_Warehouse_ID + " (" + (index-1) + "*)");

		//  => PriceList
		int M_PriceList_Version_ID = 0;
		ListItem lstitem = pickPriceList.getSelectedItem();
		if (lstitem != null)
			M_PriceList_Version_ID = (Integer)lstitem.getValue();
		if (M_PriceList_Version_ID != 0)
		{
			pstmt.setInt(index++, M_PriceList_Version_ID);
			log.fine("M_PriceList_Version_ID=" + M_PriceList_Version_ID);
		}
		//	Rest of Parameter in Query for Attribute Search
		if (m_pAttributeWhere != null)
			return;

		//  => Value
		String value = fieldValue.getText().toUpperCase();
		if (!(value.equals("") || value.equals("%")))
		{
			if (!value.endsWith("%"))
				value += "%";
			pstmt.setString(index++, value);
			log.fine("Value: " + value);
		}

		//  => Name
		String name = fieldName.getText().toUpperCase();
		if (!(name.equals("") || name.equals("%")))
		{
			if (!name.endsWith("%"))
				name += "%";
			pstmt.setString(index++, name);
			log.fine("Name: " + name);
		}

		//  => UPC
		String upc = fieldUPC.getText().toUpperCase();
		if (!(upc.equals("") || upc.equals("%")))
		{
			if (!upc.endsWith("%"))
				upc += "%";
			pstmt.setString(index++, upc);
			log.fine("UPC: " + upc);
		}

		//  => SKU
		String sku = fieldSKU.getText().toUpperCase();
		if (!(sku.equals("") || sku.equals("%")))
		{
			if (!sku.endsWith("%"))
				sku += "%";
			pstmt.setString(index++, sku);
			log.fine("SKU: " + sku);
		}

		//  => Vendor
		String vendor = fieldVendor.getText().toUpperCase();
		if (!(vendor.equals("") || vendor.equals("%")))
		{
			if (!vendor.endsWith("%"))
				vendor += "%";
			pstmt.setString(index++, vendor);
			log.fine("Vendor: " + vendor);
		}

	}   //  setParameters

	
	

	/**
	 * 	Query per Product Attribute.
	 *  <code>
	 * 	Available synonyms:
	 *		M_Product p
	 *		M_ProductPrice pr
	 *		M_AttributeSet pa
	 *	</code>
	 */
	private void cmd_InfoPAttribute()
	{
		/*InfoPAttributePanel ia = new InfoPAttributePanel();
		m_pAttributeWhere = ia.getWhereClause();
		if (m_pAttributeWhere != null)
			executeQuery();*/
	}	//	cmdInfoAttribute

	/**
	 *	Show History
	 */
	void showHistory()
	{
		/*log.info("");
		Integer M_Product_ID = getSelectedRowKey();
		if (M_Product_ID == null)
			return;
		KeyNamePair kn = (KeyNamePair)pickWarehouse.getSelectedItem();
		int M_Warehouse_ID = kn.getKey();
		int M_AttributeSetInstance_ID = m_M_AttributeSetInstance_ID;
		if (m_M_AttributeSetInstance_ID < -1)	//	not selected
			M_AttributeSetInstance_ID = 0;
		//
		InvoiceHistory ih = new InvoiceHistory (this, 0, 
			M_Product_ID.intValue(), M_Warehouse_ID, M_AttributeSetInstance_ID);
		ih.setVisible(true);
		ih = null;*/
	}	//	showHistory

	/**
	 *	Has History
	 *
	 * @return true (has history)
	 */
	boolean hasHistory()
	{
		return true;
	}	//	hasHistory

	/**
	 *	Has Zoom
	 *  @return (has zoom)
	 */
	boolean hasZoom()
	{
		return true;
	}	//	hasZoom

	/**
	 *	Customize
	 */
	void customize()
	{
		log.info("");
	}	//	customize

	/**
	 *	Has Customize
	 *  @return false (no customize)
	 */
	boolean hasCustomize()
	{
		return false;	//	for now
	}	//	hasCustomize

	/**
	 *	Save Selection Settings for PriceList
	 */
	void saveSelectionDetail()
	{
		//  publish for Callout to read
		Integer ID = getSelectedRowKey();
		Env.setContext(Env.getCtx(), p_WindowNo, Env.TAB_INFO, "M_Product_ID", ID == null ? "0" : ID.toString());
		ListItem pickPL = (ListItem)pickPriceList.getSelectedItem();
		if (pickPL!=null)
		{
            Env.setContext(Env.getCtx(), p_WindowNo, Env.TAB_INFO, "M_PriceList_Version_ID",pickPL.getValue().toString());
        }
		ListItem pickWH = (ListItem)pickWarehouse.getSelectedItem();
		if (pickWH != null)
        {
            Env.setContext(Env.getCtx(), p_WindowNo, Env.TAB_INFO, "M_Warehouse_ID",pickWH.getValue().toString());
        }
		//
		if (m_M_AttributeSetInstance_ID == -1)	//	not selected
		{
			Env.setContext(Env.getCtx(), p_WindowNo, Env.TAB_INFO, "M_AttributeSetInstance_ID", "0");
			Env.setContext(Env.getCtx(), p_WindowNo, Env.TAB_INFO, "M_Locator_ID", "0");
		}
		else
		{
			Env.setContext(Env.getCtx(), p_WindowNo, Env.TAB_INFO, "M_AttributeSetInstance_ID", 
				String.valueOf(m_M_AttributeSetInstance_ID));
			Env.setContext(Env.getCtx(), p_WindowNo, Env.TAB_INFO, "M_Locator_ID", 
				String.valueOf(m_M_Locator_ID));
		}
	}	//	saveSelectionDetail

	/**
	 *  Get Product Layout
	 *
	 * @return array of Column_Info
	 */
	private ColumnInfo[] getProductLayout()
	{
		if (s_productLayout != null)
			return s_productLayout;
		//  Euro 13
		MClient client = MClient.get(Env.getCtx());
		if ("FRIE".equals(client.getValue()))
		{
			final ColumnInfo[] frieLayout = {
				new ColumnInfo(" ", "p.M_Product_ID", IDColumn.class),
		//		new Info_Column(Msg.translate(Env.getCtx(), "Value"), "p.Value", String.class),
				new ColumnInfo(Msg.translate(Env.getCtx(), "Name"), "p.Name", String.class),
				new ColumnInfo(Msg.translate(Env.getCtx(), "QtyAvailable"), "bomQtyAvailable(p.M_Product_ID,?,0) AS QtyAvailable", Double.class, true, true, null),
				new ColumnInfo(Msg.translate(Env.getCtx(), "PriceList"), "bomPriceList(p.M_Product_ID, pr.M_PriceList_Version_ID) AS PriceList",  BigDecimal.class),
				new ColumnInfo(Msg.translate(Env.getCtx(), "PriceStd"), "bomPriceStd(p.M_Product_ID, pr.M_PriceList_Version_ID) AS PriceStd", BigDecimal.class),
				new ColumnInfo("Einzel MWSt", "pr.PriceStd * 1.16", BigDecimal.class),
				new ColumnInfo("Einzel kompl", "(pr.PriceStd+13) * 1.16", BigDecimal.class),
				new ColumnInfo("Satz kompl", "((pr.PriceStd+13) * 1.16) * 4", BigDecimal.class),
				new ColumnInfo(Msg.translate(Env.getCtx(), "QtyOnHand"), "bomQtyOnHand(p.M_Product_ID,?,0) AS QtyOnHand", Double.class),
				new ColumnInfo(Msg.translate(Env.getCtx(), "QtyReserved"), "bomQtyReserved(p.M_Product_ID,?,0) AS QtyReserved", Double.class),
				new ColumnInfo(Msg.translate(Env.getCtx(), "QtyOrdered"), "bomQtyOrdered(p.M_Product_ID,?,0) AS QtyOrdered", Double.class),
				new ColumnInfo(Msg.translate(Env.getCtx(), "Discontinued").substring(0, 1), "p.Discontinued", Boolean.class),
				new ColumnInfo(Msg.translate(Env.getCtx(), "Margin"), "bomPriceStd(p.M_Product_ID, pr.M_PriceList_Version_ID)-bomPriceLimit(p.M_Product_ID, pr.M_PriceList_Version_ID) AS Margin", BigDecimal.class),
				new ColumnInfo(Msg.translate(Env.getCtx(), "PriceLimit"), "bomPriceLimit(p.M_Product_ID, pr.M_PriceList_Version_ID) AS PriceLimit", BigDecimal.class),
				new ColumnInfo(Msg.translate(Env.getCtx(), "IsInstanceAttribute"), "pa.IsInstanceAttribute", Boolean.class)
			};
			INDEX_NAME = 2;
			INDEX_PATTRIBUTE = frieLayout.length - 1;	//	last item
			s_productLayout = frieLayout; 
			return s_productLayout;
		}
		//
		if (s_productLayout == null)
		{
			ArrayList<ColumnInfo> list = new ArrayList<ColumnInfo>();
			list.add(new ColumnInfo(" ", "p.M_Product_ID", IDColumn.class));
			list.add(new ColumnInfo(Msg.translate(Env.getCtx(), "Discontinued").substring(0, 1), "p.Discontinued", Boolean.class));
			list.add(new ColumnInfo(Msg.translate(Env.getCtx(), "Value"), "p.Value", String.class));
			list.add(new ColumnInfo(Msg.translate(Env.getCtx(), "Name"), "p.Name", String.class));
			list.add(new ColumnInfo(Msg.translate(Env.getCtx(), "QtyAvailable"), "bomQtyAvailable(p.M_Product_ID,?,0) AS QtyAvailable", Double.class, true, true, null));
			list.add(new ColumnInfo(Msg.translate(Env.getCtx(), "PriceList"), "bomPriceList(p.M_Product_ID, pr.M_PriceList_Version_ID) AS PriceList",  BigDecimal.class));
			list.add(new ColumnInfo(Msg.translate(Env.getCtx(), "PriceStd"), "bomPriceStd(p.M_Product_ID, pr.M_PriceList_Version_ID) AS PriceStd", BigDecimal.class));
			list.add(new ColumnInfo(Msg.translate(Env.getCtx(), "QtyOnHand"), "bomQtyOnHand(p.M_Product_ID,?,0) AS QtyOnHand", Double.class));
			list.add(new ColumnInfo(Msg.translate(Env.getCtx(), "QtyReserved"), "bomQtyReserved(p.M_Product_ID,?,0) AS QtyReserved", Double.class));
			list.add(new ColumnInfo(Msg.translate(Env.getCtx(), "QtyOrdered"), "bomQtyOrdered(p.M_Product_ID,?,0) AS QtyOrdered", Double.class));
			if (isUnconfirmed())
			{
				list.add(new ColumnInfo(Msg.translate(Env.getCtx(), "QtyUnconfirmed"), "(SELECT SUM(c.TargetQty) FROM M_InOutLineConfirm c INNER JOIN M_InOutLine il ON (c.M_InOutLine_ID=il.M_InOutLine_ID) INNER JOIN M_InOut i ON (il.M_InOut_ID=i.M_InOut_ID) WHERE c.Processed='N' AND i.M_Warehouse_ID=? AND il.M_Product_ID=p.M_Product_ID) AS QtyUnconfirmed", Double.class));
				list.add(new ColumnInfo(Msg.translate(Env.getCtx(), "QtyUnconfirmedMove"), "(SELECT SUM(c.TargetQty) FROM M_MovementLineConfirm c INNER JOIN M_MovementLine ml ON (c.M_MovementLine_ID=ml.M_MovementLine_ID) INNER JOIN M_Locator l ON (ml.M_LocatorTo_ID=l.M_Locator_ID) WHERE c.Processed='N' AND l.M_Warehouse_ID=? AND ml.M_Product_ID=p.M_Product_ID) AS QtyUnconfirmedMove", Double.class));
			}
			list.add(new ColumnInfo(Msg.translate(Env.getCtx(), "Margin"), "bomPriceStd(p.M_Product_ID, pr.M_PriceList_Version_ID)-bomPriceLimit(p.M_Product_ID, pr.M_PriceList_Version_ID) AS Margin", BigDecimal.class));
			list.add(new ColumnInfo(Msg.translate(Env.getCtx(), "Vendor"), "bp.Name", String.class));
			list.add(new ColumnInfo(Msg.translate(Env.getCtx(), "PriceLimit"), "bomPriceLimit(p.M_Product_ID, pr.M_PriceList_Version_ID) AS PriceLimit", BigDecimal.class));
			list.add(new ColumnInfo(Msg.translate(Env.getCtx(), "IsInstanceAttribute"), "pa.IsInstanceAttribute", Boolean.class));
			s_productLayout = new ColumnInfo[list.size()];
			list.toArray(s_productLayout);
			INDEX_NAME = 3;
			INDEX_PATTRIBUTE = s_productLayout.length - 1;	//	last item
		}
		return s_productLayout;
	}   //  getProductLayout
	
	/**
	 * 	System has Unforfirmed records
	 *	@return true if unconfirmed
	 */
	private boolean isUnconfirmed()
	{
		int no = DB.getSQLValue(null, 
			"SELECT COUNT(*) FROM M_InOutLineConfirm WHERE AD_Client_ID=?", 
			Env.getAD_Client_ID(Env.getCtx()));
		if (no > 0)
			return true;
		no = DB.getSQLValue(null, 
			"SELECT COUNT(*) FROM M_MovementLineConfirm WHERE AD_Client_ID=?", 
			Env.getAD_Client_ID(Env.getCtx()));
		return no > 0;
	}	//	isUnconfirmed

    public void tableChanged(WTableModelEvent event)
    {
        // TODO Auto-generated method stub
        
    }



}	//	InfoProduct
