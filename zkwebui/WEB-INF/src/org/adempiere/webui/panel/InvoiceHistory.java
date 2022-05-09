/******************************************************************************
 * Copyright (C) 2008 Elaine Tan
 * Copyright (C) 2008 Idalica                                              *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 *****************************************************************************/
package org.adempiere.webui.panel;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Vector;
import java.util.logging.Level;

import org.adempiere.webui.apps.AEnv;
import org.adempiere.webui.component.ConfirmPanel;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.ListModelTable;
import org.adempiere.webui.component.ListboxFactory;
import org.adempiere.webui.component.Tab;
import org.adempiere.webui.component.Tabbox;
import org.adempiere.webui.component.Tabpanel;
import org.adempiere.webui.component.Tabpanels;
import org.adempiere.webui.component.Tabs;
import org.adempiere.webui.component.WListbox;
import org.adempiere.webui.component.Window;
import org.compiere.model.MPriceList;
import org.compiere.util.CLogMgt;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zkex.zul.Borderlayout;
import org.zkoss.zkex.zul.Center;
import org.zkoss.zkex.zul.North;
import org.zkoss.zkex.zul.South;

/**
 * Price History for BPartner/Product
 * This class is based on org.compiere.apps.search.InvoiceHistory written by Jorg Janke
 * @author <a href="mailto:elaine.tan@idalica.com">Elaine</a>
 */
public class InvoiceHistory extends Window implements EventListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7426909865199278220L;

	/**
	 *	Show History
	 *	@param C_BPartner_ID partner
	 *	@param M_Product_ID product
	 *	@param M_Warehouse_ID warehouse
	 *	@param M_AttributeSetInstance_ID ASI
	 */
	public InvoiceHistory (Window parent, 
		int C_BPartner_ID, int M_Product_ID, int M_Warehouse_ID, int M_AttributeSetInstance_ID)
	{
		super();
		setTitle(Msg.getMsg(Env.getCtx(), "PriceHistory"));
		log.config("C_BPartner_ID=" + C_BPartner_ID
			+ ", M_Product_ID=" + M_Product_ID
			+ ", M_Warehouse_ID=" + M_Warehouse_ID
			+ ", M_AttributeSetInstance_ID=" + M_AttributeSetInstance_ID);
		m_C_BPartner_ID = C_BPartner_ID;
		m_M_Product_ID = M_Product_ID;
		m_M_Warehouse_ID = M_Warehouse_ID;
		m_M_AttributeSetInstance_ID = M_AttributeSetInstance_ID;
		setHeight("600px");
		setWidth("900px");
		setSizable(true);
		try
		{
			jbInit();
			dynInit();
		}
		catch(Exception ex)
		{
			log.log(Level.SEVERE, "", ex);
		}
		
		AEnv.showCenterWindow(parent, this);
	}	//	InvoiceHistory

	private int		m_C_BPartner_ID;
	private int		m_M_Product_ID;
	private int		m_M_Warehouse_ID;
	private int		m_M_AttributeSetInstance_ID;
	
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(InvoiceHistory.class);

	private Label 			label = new Label();
	//
	private ConfirmPanel 	confirmPanel = new ConfirmPanel();
	private Tabbox 			tabbox = new Tabbox();
	//
	private Tabpanel 		pricePane = new Tabpanel();
	private WListbox 		m_tablePrice = ListboxFactory.newDataTable();
	private ListModelTable 	m_modelPrice = null;
	
	private Tabpanel		orderPricePane		= new Tabpanel();
	private WListbox		m_tableOrderPrice	= ListboxFactory.newDataTable();
	private ListModelTable	m_modelOrderPrice	= null;
	
	private Tabpanel 		reservedPane = new Tabpanel();
	private WListbox 		m_tableReserved = ListboxFactory.newDataTable();
	private ListModelTable 	m_modelReserved = null;
	
	private Tabpanel 		orderedPane = new Tabpanel();
	private WListbox 		m_tableOrdered = ListboxFactory.newDataTable();
	private ListModelTable 	m_modelOrdered = null;
	
	private Tabpanel 		unconfirmedPane = new Tabpanel();
	private WListbox 		m_tableUnconfirmed = ListboxFactory.newDataTable();
	private ListModelTable 	m_modelUnconfirmed = null;

	private Tabpanel 		atpPane = new Tabpanel();
	private WListbox 		m_tableAtp = ListboxFactory.newDataTable();
	private ListModelTable 	m_modelAtp = null;
	
	/**
	 *	Ststic Init
	 */
	void jbInit() throws Exception
	{
		label.setText("Label");
		
		Tabs tabs = new Tabs();
		tabbox.appendChild(tabs);

		Tabpanels tabpanels = new Tabpanels();
		tabbox.appendChild(tabpanels);
		
		tabs.appendChild(new Tab(Msg.getMsg(Env.getCtx(), "PriceHistory")));
		tabs.appendChild(new Tab(Msg.translate(Env.getCtx(), "OrderPriceHistory")));
		tabs.appendChild(new Tab(Msg.translate(Env.getCtx(), "QtyReserved")));
		tabs.appendChild(new Tab(Msg.translate(Env.getCtx(), "QtyOrdered")));
		tabs.appendChild(new Tab(Msg.getMsg(Env.getCtx(), "QtyUnconfirmed")));
		
		if (m_M_Product_ID != 0)
			tabs.appendChild(new Tab(Msg.getMsg(Env.getCtx(), "ATP")));
		
		pricePane.setHeight("100%");
		pricePane.appendChild(m_tablePrice);
		tabpanels.appendChild(pricePane);
		
		orderPricePane.setHeight("100%");
		orderPricePane.appendChild(m_tableOrderPrice);
		tabpanels.appendChild(orderPricePane);
		
		reservedPane.setHeight("100%");
		reservedPane.appendChild(m_tableReserved);
		tabpanels.appendChild(reservedPane);
		
		orderedPane.setHeight("100%");
		orderedPane.appendChild(m_tableOrdered);
		tabpanels.appendChild(orderedPane);
		
		unconfirmedPane.setHeight("100%");
		unconfirmedPane.appendChild(m_tableUnconfirmed);
		tabpanels.appendChild(unconfirmedPane);
		
		if (m_M_Product_ID != 0)
		{
			atpPane.setHeight("100%");
			atpPane.appendChild(m_tableAtp);
			tabpanels.appendChild(atpPane);
		}
		
		tabbox.setSelectedIndex(0);
		tabbox.addEventListener(Events.ON_SELECT, this);
		confirmPanel.addActionListener(this);
        
		Borderlayout borderlayout = new Borderlayout();
        borderlayout.setWidth("100%");
        borderlayout.setHeight("100%");
        borderlayout.setStyle("border: none; position: relative");
		this.appendChild(borderlayout);
		
		North north = new North();
		north.setStyle("border: none");
		borderlayout.appendChild(north);
		north.appendChild(label);
		
		Center center = new Center();
		center.setStyle("border: none");
		center.setAutoscroll(true);
        center.setFlex(true);
		borderlayout.appendChild(center);
		center.appendChild(tabbox);
		
		South south = new South();
		south.setStyle("border: none");
		borderlayout.appendChild(south);
		south.appendChild(confirmPanel);
	}	//	jbInit

	/**
	 *	Dynamic Init for Price Tab
	 */
	private boolean dynInit()
	{
		//	Header
		Vector<String> columnNames = new Vector<String>();
		columnNames.add(Msg.translate(Env.getCtx(), m_C_BPartner_ID == 0 ? "C_BPartner_ID" : "M_Product_ID"));
		columnNames.add(Msg.translate(Env.getCtx(), "PriceActual"));
		columnNames.add(Msg.translate(Env.getCtx(), "QtyInvoiced"));
		columnNames.add(Msg.translate(Env.getCtx(), "Discount"));
		columnNames.add(Msg.translate(Env.getCtx(), "DocumentNo"));
		columnNames.add(Msg.translate(Env.getCtx(), "DateInvoiced"));
		columnNames.add(Msg.translate(Env.getCtx(), "AD_Org_ID"));

		//	Fill Data
		Vector<Vector<Object>> data = null;
		if (m_C_BPartner_ID == 0)
			data = queryBPartner(true);		//	BPartner of Product
		else
			data = queryProduct(true);		//	Product of BPartner

		//  Table
		m_modelPrice = new ListModelTable(data);
		m_tablePrice.setData(m_modelPrice, columnNames);		
		//
		m_tablePrice.setColumnClass(0, String.class, true);      //  Product/Partner
		m_tablePrice.setColumnClass(1, Double.class, true);  	 //  Price
		m_tablePrice.setColumnClass(2, Double.class, true);      //  Quantity
		m_tablePrice.setColumnClass(3, BigDecimal.class, true);  //  Discount (%) to limit precision
		m_tablePrice.setColumnClass(4, String.class, true);      //  DocNo
		m_tablePrice.setColumnClass(5, Timestamp.class, true);   //  Date
		m_tablePrice.setColumnClass(6, String.class, true);   	 //  Org
		//
		m_tablePrice.autoSize();
		//
		
		return data.size() != 0;
	}	//	dynInit


	/**
	 *	Get Info for Product for given Business Partner
	 */
	private Vector<Vector<Object>> queryProduct (boolean isInvoicePrice)
	{
		String sql;
		if (isInvoicePrice)
		{
			// for Invoice Price
			sql = "SELECT p.Name,l.PriceActual,l.PriceList,l.QtyInvoiced,"		//  1,2,3,4
				+ "i.DateInvoiced,dt.PrintName || ' ' || i.DocumentNo As DocumentNo,"	//  5,6
				+ "o.Name, "															//  7
				+ "NULL, i.M_PriceList_ID "												//  8,9
				+ "FROM C_Invoice i"
				+ " INNER JOIN C_InvoiceLine l ON (i.C_Invoice_ID=l.C_Invoice_ID)"
				+ " INNER JOIN C_DocType dt ON (i.C_DocType_ID=dt.C_DocType_ID)"
				+ " INNER JOIN AD_Org o ON (i.AD_Org_ID=o.AD_Org_ID)"
				+ " INNER JOIN M_Product p  ON (l.M_Product_ID=p.M_Product_ID) "
				+ "WHERE i.C_BPartner_ID=? "
				+ "ORDER BY i.DateInvoiced DESC";
		}
		else
		{
			// for Order Price
			sql = "SELECT p.Name, ol.PriceActual, ol.PriceList, ol.QtyOrdered, o.DateOrdered, " // 1,2,3,4,5
					+ " dt.PrintName || ' ' || o.DocumentNo As DocumentNo, org.Name, NULL, o.M_PriceList_ID " // 6,7,8,9
					+ "FROM C_Order o " 
					+ "INNER JOIN C_OrderLine ol ON (o.C_Order_ID=ol.C_Order_ID) "
					+ "INNER JOIN C_DocType dt ON (o.C_DocType_ID=dt.C_DocType_ID) "
					+ "INNER JOIN AD_Org org ON (o.AD_Org_ID=o.AD_Org_ID) "
					+ "INNER JOIN M_Product p ON (ol.M_Product_ID=p.M_Product_ID) " 
					+ "WHERE o.C_BPartner_ID=? "
					+ "ORDER BY o.DateOrdered DESC";
		}
		
		Vector<Vector<Object>> data = fillTable (sql, m_C_BPartner_ID);

		sql = "SELECT Name from C_BPartner WHERE C_BPartner_ID=?";
		fillLabel (sql, m_C_BPartner_ID);
		return data;
	}   //  queryProduct

	/**
	 *	Get Info for Business Partners for given Product
	 */
	private Vector<Vector<Object>> queryBPartner (boolean isInvoicePrice)
	{
		String sql;
		if (isInvoicePrice)
		{
			// for Invoice Price
			sql = "SELECT bp.Name,l.PriceActual,l.PriceList,l.QtyInvoiced," // 1,2,3,4
					+ "i.DateInvoiced,dt.PrintName || ' ' || i.DocumentNo As DocumentNo," // 5,6
					+ "o.Name," // 7
					+ "NULL, i.M_PriceList_ID" // 8,9
					+ " FROM C_Invoice i" 
					+ " INNER JOIN C_InvoiceLine l ON (i.C_Invoice_ID=l.C_Invoice_ID)"
					+ " INNER JOIN C_DocType dt ON (i.C_DocType_ID=dt.C_DocType_ID)"
					+ " INNER JOIN AD_Org o ON (i.AD_Org_ID=o.AD_Org_ID)"
					+ " INNER JOIN C_BPartner bp ON (i.C_BPartner_ID=bp.C_BPartner_ID) " 
					+ "WHERE l.M_Product_ID="+ m_M_Product_ID
					+ "ORDER BY i.DateInvoiced DESC";
		}
		else
		{
			// for Order Price
			sql = "SELECT bp.Name, ol.PriceActual, ol.PriceList, ol.QtyOrdered, o.DateOrdered, " // 1,2,3,4,5
					+ " dt.PrintName || ' ' || o.DocumentNo As DocumentNo, org.Name, NULL, o.M_PriceList_ID " // 6,7,8,9
					+ "	FROM C_Order o " 
					+ "	INNER JOIN C_OrderLine ol	ON (o.C_Order_ID = ol.C_Order_ID) "
					+ " INNER JOIN C_DocType dt		ON (o.C_DocType_ID = dt.C_DocType_ID) "
					+ " INNER JOIN AD_Org org		ON (o.AD_Org_ID = org.AD_Org_ID) "
					+ " INNER JOIN C_BPartner bp	ON (o.C_BPartner_ID = bp.C_BPartner_ID) "
					+ " WHERE ol.M_Product_ID="+ m_M_Product_ID
					+ " ORDER BY o.DateOrdered DESC";
		}
		
		Vector<Vector<Object>> data = fillTable (sql, m_M_Product_ID);

		sql = "SELECT Name from M_Product WHERE M_Product_ID=?";
		fillLabel (sql, m_M_Product_ID);
		return data;
	}	//	qyeryBPartner

	/**
	 *	Fill Table
	 */
	private Vector<Vector<Object>> fillTable (String sql, int parameter)
	{
		log.fine(sql + "; Parameter=" + parameter);
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, null);
			rs = pstmt.executeQuery();
			while (rs.next())
			{
				Vector<Object> line = new Vector<Object>(6);
				//	0-Name, 1-PriceActual, 2-QtyInvoiced, 3-Discount, 4-DocumentNo, 5-DateInvoiced
				line.add(rs.getString(1));      //  Name
				line.add(rs.getBigDecimal(2));  //	Price
				line.add(new Double(rs.getDouble(4)));      //  Qty
				BigDecimal discountBD = rs.getBigDecimal(8);
				if (discountBD == null) {
					double priceList = rs.getDouble(3);
					double priceActual = rs.getDouble(2);
					if (priceList != 0) {
						discountBD = new BigDecimal((priceList - priceActual)/priceList * 100);
						// Rounding:
						int precision = MPriceList.getStandardPrecision(Env.getCtx(), rs.getInt(9));
						if (discountBD.scale() > precision)
							discountBD = discountBD.setScale(precision, RoundingMode.HALF_UP);
					}
					else
						discountBD = Env.ZERO;
				}
				line.add(discountBD);  //  Discount
				line.add(rs.getString(6));      //  DocNo
				line.add(rs.getTimestamp(5));   //  Date
				line.add(rs.getString(7));		//	Org/Warehouse
				data.add(line);
			}
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		finally {
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		log.fine("#" + data.size());
		return data;
	}	//	fillTable

	/**
	 *	Set Label
	 *  to product or bp name
	 */
	private void fillLabel (String sql, int parameter)
	{
		log.fine(sql + "; Parameter=" + parameter);
		String retValue = DB.getSQLValueString(null, sql, parameter);
		if (retValue != null)
			label.setText(retValue);
	}	//	fillLabel


	public void onEvent(Event e) throws Exception {
		Component component = e.getTarget();
		
		if (component.equals(confirmPanel.getButton(ConfirmPanel.A_OK)))
			dispose();
		else if(component instanceof Tab)
		{
			if (tabbox.getSelectedIndex() == 1)
				initOrderPriceHistoryTab();
			else if (tabbox.getSelectedIndex() == 2)
				initReservedOrderedTab(true);
			else if (tabbox.getSelectedIndex() == 3)
				initReservedOrderedTab(false);
			else if (tabbox.getSelectedIndex() == 4)
				initUnconfirmedTab();
			else if (tabbox.getSelectedIndex() == 5)
				initAtpTab();
		}
	}

	/**
	 *	Query Reserved/Ordered
	 *	@param reserved po/so
	 */
	private void initReservedOrderedTab (boolean reserved)
	{
		//	Done already
		if (reserved && m_modelReserved != null)
			return;
		if (!reserved && m_modelOrdered != null)
			return;
			
		//	Header
		Vector<String> columnNames = new Vector<String>();
		columnNames.add(Msg.translate(Env.getCtx(), m_C_BPartner_ID == 0 ? "C_BPartner_ID" : "M_Product_ID"));
		columnNames.add(Msg.translate(Env.getCtx(), "PriceActual"));
		columnNames.add(Msg.translate(Env.getCtx(), reserved ? "QtyReserved" : "QtyOrdered"));
		columnNames.add(Msg.translate(Env.getCtx(), "Discount"));
		columnNames.add(Msg.translate(Env.getCtx(), "DocumentNo"));
		columnNames.add(Msg.translate(Env.getCtx(), "DateOrdered"));
		columnNames.add(Msg.translate(Env.getCtx(), "M_Warehouse_ID"));

		//	Fill Data
		Vector<Vector<Object>> data = null;
		String sql;
		if (m_C_BPartner_ID == 0)
		{
			sql = "SELECT bp.Name AS BPName, ol.PriceActual AS PriceActual ,ol.PriceList AS PriceList,ol.QtyReserved AS QtyReserved,"
				+ "o.DateOrdered AS DateOrdered,dt.PrintName || ' ' || o.DocumentNo As DocumentNo, "
				+ "w.Name AS WarehouseName,"
				+ "ol.Discount AS Discount "															// 8,9=M_PriceList_ID
				+ "FROM C_Order o"
				+ " INNER JOIN C_OrderLine ol ON (o.C_Order_ID=ol.C_Order_ID)"
				+ " INNER JOIN C_DocType dt ON (o.C_DocType_ID=dt.C_DocType_ID)"
				+ " INNER JOIN M_Warehouse w ON (ol.M_Warehouse_ID=w.M_Warehouse_ID)"
				+ " INNER JOIN C_BPartner bp  ON (o.C_BPartner_ID=bp.C_BPartner_ID) "
				+ "WHERE ol.QtyReserved<>0"
				+ " AND ol.M_Product_ID=" + m_M_Product_ID
				+ " AND o.IsSOTrx=" + (reserved ? "'Y'" : "'N'");
		}
		else
		{
			sql = "SELECT p.Name AS BPName, ol.PriceActual AS PriceActual,ol.PriceList AS PriceList,ol.QtyReserved AS QtyReserved,"
				+ "o.DateOrdered AS DateOrdered,dt.PrintName || ' ' || o.DocumentNo As DocumentNo, "
				+ "w.Name AS WarehouseName,"
				+ "ol.Discount AS Discount "															// 8,9=M_PriceList_ID
				+ "FROM C_Order o"
				+ " INNER JOIN C_OrderLine ol ON (o.C_Order_ID=ol.C_Order_ID)"
				+ " INNER JOIN C_DocType dt ON (o.C_DocType_ID=dt.C_DocType_ID)"
				+ " INNER JOIN M_Warehouse w ON (ol.M_Warehouse_ID=w.M_Warehouse_ID)"
				+ " INNER JOIN M_Product p  ON (ol.M_Product_ID=p.M_Product_ID) "
				+ "WHERE ol.QtyReserved<>0"
				+ " AND o.C_BPartner_ID="+m_C_BPartner_ID
				+ " AND o.IsSOTrx=" + (reserved ? "'Y'" : "'N'");
		}

		if (reserved) {
			sql += " UNION ALL ";
			sql += "SELECT null AS BPName, 0 AS PriceActual,0 AS PriceList,ol.QtyReserved AS QtyReserved,"
					+ "o.DateOrdered AS DateOrdered,dt.PrintName || ' ' || o.DocumentNo As DocumentNo, "
					+ "w.Name AS WarehouseName,"
					+ "0 AS Discount "                                                            // 8,9=M_PriceList_ID
					+ "FROM PP_Order o"
					+ " INNER JOIN PP_Order_BOMLine ol ON (o.PP_Order_ID=ol.PP_Order_ID)"
					+ " INNER JOIN C_DocType dt ON (o.C_DocType_ID=dt.C_DocType_ID)"
					+ " INNER JOIN M_Warehouse w ON (ol.M_Warehouse_ID=w.M_Warehouse_ID)"
					+ " INNER JOIN M_Product p  ON (ol.M_Product_ID=p.M_Product_ID) "
					+ "WHERE ol.QtyReserved<>0"
					+ " AND ol.M_Product_ID=" + m_M_Product_ID;

			//Distribution Order Reserved
			sql += " UNION ALL ";
			sql += "SELECT null AS BPName , 0 AS PriceActual ,0 AS PriceList , ol.QtyReserved AS QtyReserved,"
					+ "o.DateOrdered AS DateOrdered ,dt.PrintName || ' ' || o.DocumentNo As DocumentNo, "
					+ "w.Name AS WarehouseName,"
					+ "0 AS Discount "													// 8,9=M_PriceList_ID
					+ "FROM DD_Order o"
					+ " INNER JOIN DD_OrderLine ol ON (o.DD_Order_ID=ol.DD_Order_ID)"
					+ " INNER JOIN C_DocType dt ON (o.C_DocType_ID=dt.C_DocType_ID)"
					+ " INNER JOIN M_Locator l ON (l.M_Locator_ID = ol.M_LocatorTo_ID)"
					+ " INNER JOIN M_Warehouse w ON (l.M_Warehouse_ID=w.M_Warehouse_ID)"
					+ " INNER JOIN M_Product p  ON (ol.M_Product_ID=p.M_Product_ID) "
					+ "WHERE ol.QtyReserved<>0"
					+ " AND ol.M_Product_ID="+m_M_Product_ID;
		} else {
			// Manufacturing Order Ordered
			sql += " UNION ALL ";
			sql += "SELECT null AS BPName, 0 AS PriceActual,0 AS PriceList, o.QtyOrdered AS QtyReserved,"
					+ "o.DateOrdered AS DateOrdered,dt.PrintName || ' ' || o.DocumentNo As DocumentNo, "
					+ "w.Name AS WarehouseName,"
					+ "0 AS Discount "															// 8,9=M_PriceList_ID
					+ "FROM PP_Order o"
					+ " INNER JOIN C_DocType dt ON (o.C_DocType_ID=dt.C_DocType_ID)"
					+ " INNER JOIN M_Warehouse w ON (o.M_Warehouse_ID=w.M_Warehouse_ID)"
					+ " INNER JOIN M_Product p  ON (o.M_Product_ID=p.M_Product_ID) "
					+ "WHERE o.QtyOrdered<>0"
					+ " AND o.M_Product_ID=" + m_M_Product_ID;

			//Distribution Order Ordered
			sql += " UNION ALL ";
			sql += "SELECT null AS BPName , 0 AS PriceActual ,0 AS PriceList ,ol.QtyOrdered - ol.QtyDelivered AS QtyReserved,"
					+ "o.DateOrdered AS DateOrdered ,dt.PrintName || ' ' || o.DocumentNo As DocumentNo, "
					+ "wf.Name AS WarehouseName,"
					+ "0 AS Discount "													// 8,9=M_PriceList_ID
					+ "FROM DD_Order o"
					+ " INNER JOIN DD_OrderLine ol ON (o.DD_Order_ID=ol.DD_Order_ID)"
					+ " INNER JOIN C_DocType dt ON (o.C_DocType_ID=dt.C_DocType_ID)"
					+ " INNER JOIN M_Locator lf on (lf.M_Locator_ID = ol.M_Locator_ID)"
					+ " INNER JOIN M_Warehouse wf ON (lf.M_Warehouse_ID=wf.M_Warehouse_ID)"
					+ " INNER JOIN M_Product p  ON (ol.M_Product_ID=p.M_Product_ID) "
					+ "WHERE (ol.QtyOrdered - ol.QtyDelivered) <>0"
					+ " AND ol.M_Product_ID="+m_M_Product_ID;

		}
		sql += " ORDER BY DateOrdered";
		data = fillTable (sql, m_C_BPartner_ID);//	Product of BP

		//  Table
		if (reserved)
		{
			m_modelReserved = new ListModelTable(data);
			m_tableReserved.setData(m_modelReserved, columnNames);
			//
			m_tableReserved.setColumnClass(0, String.class, true);      //  Product/Partner
			m_tableReserved.setColumnClass(1, BigDecimal.class, true);  //  Price
			m_tableReserved.setColumnClass(2, Double.class, true);      //  Quantity
			m_tableReserved.setColumnClass(3, BigDecimal.class, true);  //  Discount (%)
			m_tableReserved.setColumnClass(4, String.class, true);      //  DocNo
			m_tableReserved.setColumnClass(5, Timestamp.class, true);   //  Date
			m_tableReserved.setColumnClass(6, String.class, true);   	  //  Warehouse
			//
			m_tableReserved.autoSize();
		}
		else
		{
			m_modelOrdered = new ListModelTable(data);
			m_tableOrdered.setData(m_modelOrdered, columnNames);
			//
			m_tableOrdered.setColumnClass(0, String.class, true);      //  Product/Partner
			m_tableOrdered.setColumnClass(1, BigDecimal.class, true);  //  Price
			m_tableOrdered.setColumnClass(2, Double.class, true);      //  Quantity
			m_tableOrdered.setColumnClass(3, BigDecimal.class, true);  //  Discount (%)
			m_tableOrdered.setColumnClass(4, String.class, true);      //  DocNo
			m_tableOrdered.setColumnClass(5, Timestamp.class, true);   //  Date
			m_tableOrdered.setColumnClass(6, String.class, true);   	  //  Warehouse
			//
			m_tableOrdered.autoSize();
		}

	}	//	initReservedOrderedTab

	
	/**
	 *	Query Unconfirmed
	 */
	private void initUnconfirmedTab ()
	{
		//	Done already
		if (m_modelUnconfirmed != null)
			return;
			
		//	Header
		Vector<String> columnNames = new Vector<String>();
		columnNames.add(Msg.translate(Env.getCtx(), m_C_BPartner_ID == 0 ? "C_BPartner_ID" : "M_Product_ID"));
		columnNames.add(Msg.translate(Env.getCtx(), "MovementQty"));
		columnNames.add(Msg.translate(Env.getCtx(), "MovementDate"));
		columnNames.add(Msg.translate(Env.getCtx(), "IsSOTrx"));
		columnNames.add(Msg.translate(Env.getCtx(), "DocumentNo"));
		columnNames.add(Msg.translate(Env.getCtx(), "M_Warehouse_ID"));

		//	Fill Data
		String sql = null;
		int parameter = 0;
		if (m_C_BPartner_ID == 0)	
		{
			sql = "SELECT bp.Name,"
				+ " CASE WHEN io.IsSOTrx='Y' THEN iol.MovementQty*-1 ELSE iol.MovementQty END AS MovementQty,"
				+ " io.MovementDate,io.IsSOTrx,"
				+ " dt.PrintName || ' ' || io.DocumentNo As DocumentNo,"
				+ " w.Name "
				+ "FROM M_InOutLine iol"
				+ " INNER JOIN M_InOut io ON (iol.M_InOut_ID=io.M_InOut_ID)"
				+ " INNER JOIN C_BPartner bp  ON (io.C_BPartner_ID=bp.C_BPartner_ID)"
				+ " INNER JOIN C_DocType dt ON (io.C_DocType_ID=dt.C_DocType_ID)"
				+ " INNER JOIN M_Warehouse w ON (io.M_Warehouse_ID=w.M_Warehouse_ID)"
				+ " INNER JOIN M_InOutLineConfirm lc ON (iol.M_InOutLine_ID=lc.M_InOutLine_ID) "
				+ "WHERE iol.M_Product_ID=?"
				+ " AND lc.Processed='N' "
				+ "ORDER BY io.MovementDate,io.IsSOTrx";
			parameter = m_M_Product_ID;
		}
		else
		{
			sql = "SELECT p.Name,"
				+ " CASE WHEN io.IsSOTrx='Y' THEN iol.MovementQty*-1 ELSE iol.MovementQty END AS MovementQty,"
				+ " io.MovementDate,io.IsSOTrx,"
				+ " dt.PrintName || ' ' || io.DocumentNo As DocumentNo,"
				+ " w.Name "
				+ "FROM M_InOutLine iol"
				+ " INNER JOIN M_InOut io ON (iol.M_InOut_ID=io.M_InOut_ID)"
				+ " INNER JOIN M_Product p  ON (iol.M_Product_ID=p.M_Product_ID)"
				+ " INNER JOIN C_DocType dt ON (io.C_DocType_ID=dt.C_DocType_ID)"
				+ " INNER JOIN M_Warehouse w ON (io.M_Warehouse_ID=w.M_Warehouse_ID)"
				+ " INNER JOIN M_InOutLineConfirm lc ON (iol.M_InOutLine_ID=lc.M_InOutLine_ID) "
				+ "WHERE io.C_BPartner_ID=?"
				+ " AND lc.Processed='N' "
				+ "ORDER BY io.MovementDate,io.IsSOTrx";
			parameter = m_C_BPartner_ID;
		}
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, parameter);
			rs = pstmt.executeQuery();
			while (rs.next())
			{
				Vector<Object> line = new Vector<Object>(6);
				//	1-Name, 2-MovementQty, 3-MovementDate, 4-IsSOTrx, 5-DocumentNo
				line.add(rs.getString(1));      		//  Name
				line.add(new Double(rs.getDouble(2)));  //  Qty
				line.add(rs.getTimestamp(3));   		//  Date
				line.add(Boolean.valueOf("Y".equals(rs.getString(4))));	//  IsSOTrx
				line.add(rs.getString(5));				//  DocNo
				line.add(rs.getString(6));				//  Warehouse
				data.add(line);
			}
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		finally {
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		log.fine("#" + data.size());

		//  Table
		m_modelUnconfirmed = new ListModelTable(data);
		m_tableUnconfirmed.setData(m_modelUnconfirmed, columnNames);
		//
		m_tableUnconfirmed.setColumnClass(0, String.class, true);      //  Product/Partner
		m_tableUnconfirmed.setColumnClass(1, Double.class, true);  	  //  MovementQty
		m_tableUnconfirmed.setColumnClass(2, Timestamp.class, true);   //  MovementDate
		m_tableUnconfirmed.setColumnClass(3, Boolean.class, true);  	  //  IsSOTrx
		m_tableUnconfirmed.setColumnClass(4, String.class, true);      //  DocNo
		//
		m_tableUnconfirmed.autoSize();
	}	//	initUnconfirmedTab

	/**
	 *	Query ATP
	 */
	private void initAtpTab ()
	{
		//	Done already
		if (m_modelAtp != null)
			return;
			
		//	Header
		Vector<String> columnNames = new Vector<String>();
		columnNames.add(Msg.translate(Env.getCtx(), "Date"));
		columnNames.add(Msg.translate(Env.getCtx(), "QtyOnHand"));
		columnNames.add(Msg.translate(Env.getCtx(), "QtyReserved"));
		columnNames.add(Msg.translate(Env.getCtx(), "QtyOrdered"));
		columnNames.add(Msg.translate(Env.getCtx(), "ATP"));
		columnNames.add(Msg.translate(Env.getCtx(), "C_BPartner_ID"));
		columnNames.add(Msg.translate(Env.getCtx(), "DocumentNo"));
		columnNames.add(Msg.translate(Env.getCtx(), "M_Warehouse_ID"));
		columnNames.add(Msg.translate(Env.getCtx(), "M_Locator_ID"));
		columnNames.add(Msg.translate(Env.getCtx(), "M_AttributeSetInstance_ID"));
		//	Fill Storage Data
		boolean showDetail = CLogMgt.isLevelFine();
		String sql = "SELECT s.QtyOnHand, 0 AS QtyReserved, 0 AS QtyOrdered,"
			+ " productAttribute(s.M_AttributeSetInstance_ID), s.M_AttributeSetInstance_ID,";
		if (!showDetail)
			sql = "SELECT SUM(s.QtyOnHand), 0 AS QtyReserved, 0 AS QtyOrdered,"
				+ " productAttribute(s.M_AttributeSetInstance_ID), 0,";
		sql += " w.Name, l.Value  , 0 AS QtyDelivered "
			+ "FROM M_Storage s"
			+ " INNER JOIN M_Locator l ON (s.M_Locator_ID=l.M_Locator_ID)"
			+ " INNER JOIN M_Warehouse w ON (l.M_Warehouse_ID=w.M_Warehouse_ID) "
			+ "WHERE M_Product_ID=?";
		if (m_M_Warehouse_ID != 0)
			sql += " AND l.M_Warehouse_ID=?";
		if (m_M_AttributeSetInstance_ID > 0)
			sql += " AND s.M_AttributeSetInstance_ID=?";
		sql += " AND (s.QtyOnHand<>0 OR s.QtyReserved<>0 OR s.QtyOrdered<>0)";
		if (!showDetail)
			sql += " GROUP BY productAttribute(s.M_AttributeSetInstance_ID), w.Name, l.Value";
		sql += " ORDER BY l.Value";
		
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		double qtyExpected = 0;
		double qtyOnHand = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, m_M_Product_ID);
			if (m_M_Warehouse_ID != 0)
				pstmt.setInt(2, m_M_Warehouse_ID);
			if (m_M_AttributeSetInstance_ID > 0)
				pstmt.setInt(3, m_M_AttributeSetInstance_ID);
			rs = pstmt.executeQuery();
			while (rs.next())
			{
				Vector<Object> line = new Vector<Object>(9);
				line.add(null);							//  Date
				qtyOnHand = rs.getDouble(1);
				double qtyDelivered  = rs.getDouble(8);
				double qtyOrdered = rs.getDouble(3);
				double qtyReserved = rs.getDouble(2);
				qtyExpected += qtyOnHand;
				qtyExpected += qtyOrdered;
				qtyExpected -= qtyReserved;
				line.add(qtyOnHand);  					//  QtyOnHand
				line.add(qtyReserved);  				//  QtyReserved
				line.add(qtyOrdered);  					//  QtyOrdered
				line.add(qtyExpected);  				//  Qty
				line.add(null);							//  DocumentNo
				line.add(null);							//  BPartner
				line.add(rs.getString(6));  	//	Warehouse
				line.add(rs.getString(7));  //  Locator
				String asi = rs.getString(4);
				if (showDetail && (asi == null || asi.length() == 0))
					asi = "{" + rs.getInt(5) + "}";
				line.add(asi);							//  ASI
				data.add(line);
			}
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		finally {
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}

		//	Orders
		sql = "SELECT o.DatePromised, CASE WHEN o.IsSOTrx = 'Y' THEN ol.QtyReserved ELSE 0  END AS QtyReserved,"
			+ " productAttribute(ol.M_AttributeSetInstance_ID), ol.M_AttributeSetInstance_ID,"
			+ " dt.DocBaseType, bp.Name,"
			+ " dt.PrintName || ' ' || o.DocumentNo As DocumentNo, w.Name, CASE WHEN o.IsSOTrx = 'N' THEN ol.QtyReserved ELSE 0 END AS QtyOrdered,"
			+ " ol.QtyDelivered AS QtyDelivered "
			+ "FROM C_Order o"
			+ " INNER JOIN C_OrderLine ol ON (o.C_Order_ID=ol.C_Order_ID)"
			+ " INNER JOIN C_DocType dt ON (o.C_DocType_ID=dt.C_DocType_ID)"
			+ " INNER JOIN M_Warehouse w ON (ol.M_Warehouse_ID=w.M_Warehouse_ID)"
			+ " INNER JOIN C_BPartner bp  ON (o.C_BPartner_ID=bp.C_BPartner_ID) "
			+ " WHERE ol.QtyReserved<>0"
			+ " AND ol.M_Product_ID=" + m_M_Product_ID;
		if (m_M_Warehouse_ID != 0)
			sql += " AND ol.M_Warehouse_ID=" + m_M_Warehouse_ID;
		if (m_M_AttributeSetInstance_ID > 0)
			sql += " AND ol.M_AttributeSetInstance_ID="+ m_M_AttributeSetInstance_ID;

		//	Distribution Orders Reserved
		sql += " UNION ALL ";
		sql += "SELECT o.DatePromised, ol.QtyReserved AS QtyReserved,"
				+ " productAttribute(ol.M_AttributeSetInstance_ID), ol.M_AttributeSetInstance_ID,"
				+ " dt.DocBaseType, bp.Name,"
				+ " dt.PrintName || ' ' || o.DocumentNo As DocumentNo, wf.Name , 0 AS QtyOrdered , ol.QtyInTransit AS QtyDelivered "
				+ "FROM DD_Order o"
				+ " INNER JOIN DD_OrderLine ol ON (o.DD_Order_ID=ol.DD_Order_ID)"
				+ " INNER JOIN C_DocType dt ON (o.C_DocType_ID=dt.C_DocType_ID)"
				+ " INNER JOIN M_Locator lf on (lf.M_Locator_ID = ol.M_Locator_ID)"
				+ " INNER JOIN M_Warehouse wf ON (lf.M_Warehouse_ID=wf.M_Warehouse_ID)"
				+ " INNER JOIN C_BPartner bp  ON (o.C_BPartner_ID=bp.C_BPartner_ID) "
				+ "WHERE ol.QtyReserved <>0 AND o.DocStatus in ('IP','CO') AND o.IsDelivered = 'N' "
				+ " AND ol.M_Product_ID="+ m_M_Product_ID;
		if (m_M_Warehouse_ID != 0)
			sql += " AND wf.M_Warehouse_ID=" + m_M_Warehouse_ID;
		if (m_M_AttributeSetInstance_ID > 0)
			sql += " AND ol.M_AttributeSetInstance_ID="+ m_M_AttributeSetInstance_ID;


		//	Distribution Orders Ordered
		sql += " UNION ALL ";
		sql += "SELECT o.DatePromised, 0 AS QtyReserved,"
				+ " productAttribute(ol.M_AttributeSetInstanceTo_ID), ol.M_AttributeSetInstanceTo_ID,"
				+ " dt.DocBaseType, bp.Name,"
				+ " dt.PrintName || ' ' || o.DocumentNo As DocumentNo, w.Name , ol.QtyOrdered, ol.QtyDelivered AS QtyDelivered "
				+ "FROM DD_Order o"
				+ " INNER JOIN DD_OrderLine ol ON (o.DD_Order_ID=ol.DD_Order_ID)"
				+ " INNER JOIN C_DocType dt ON (o.C_DocType_ID=dt.C_DocType_ID)"
				+ " INNER JOIN M_Locator l ON (l.M_Locator_ID = ol.M_LocatorTo_ID)"
				+ " INNER JOIN M_Warehouse w ON (l.M_Warehouse_ID=w.M_Warehouse_ID)"
				+ " INNER JOIN C_BPartner bp  ON (o.C_BPartner_ID=bp.C_BPartner_ID) "
				+ "WHERE ol.QtyOrdered<>0 AND o.DocStatus in ('IP','CO') AND o.IsDelivered = 'N' "
				+ " AND ol.M_Product_ID="+ m_M_Product_ID;
		if (m_M_Warehouse_ID != 0)
			sql += " AND w.M_Warehouse_ID=" + m_M_Warehouse_ID;
		if (m_M_AttributeSetInstance_ID > 0)
			sql += " AND ol.M_AttributeSetInstanceTo_ID="+ m_M_AttributeSetInstance_ID;

		//	Manufacturing Orders Reserved
		sql += " UNION ALL ";
		sql += "SELECT o.DatePromised, ol.QtyReserved,"
				+ " productAttribute(ol.M_AttributeSetInstance_ID), ol.M_AttributeSetInstance_ID,"
				+ " dt.DocBaseType, null AS Name,"
				+ " dt.PrintName || ' ' || o.DocumentNo As DocumentNo, w.Name , 0 AS QtyOrdered, ol.QtyDelivered AS QtyDelivered "
				+ "FROM PP_Order o"
				+ " INNER JOIN PP_Order_BOMLine ol ON (o.PP_Order_ID=ol.PP_Order_ID)"
				+ " INNER JOIN C_DocType dt ON (o.C_DocType_ID=dt.C_DocType_ID)"
				+ " INNER JOIN M_Warehouse w ON (ol.M_Warehouse_ID=w.M_Warehouse_ID)"
				+ " WHERE ol.QtyReserved<>0"
				+ " AND ol.M_Product_ID="+ m_M_Product_ID;
		if (m_M_Warehouse_ID != 0)
			sql += " AND ol.M_Warehouse_ID=" + m_M_Warehouse_ID;
		if (m_M_AttributeSetInstance_ID > 0)
			sql += " AND ol.M_AttributeSetInstance_ID="+ m_M_AttributeSetInstance_ID;

		sql += " ORDER BY DatePromised";
		try
		{
			pstmt = DB.prepareStatement(sql, null);
			rs = pstmt.executeQuery();
			while (rs.next())
			{
				Vector<Object> line = new Vector<Object>(9);
				double qtyDelivered  = rs.getDouble(10);
				double qtyOrdered = rs.getDouble(9);
				double qtyReserved = rs.getDouble(2);
				qtyExpected += qtyOrdered;
				qtyExpected -= qtyReserved;
				line.add(rs.getTimestamp(1));//  Date
				line.add(0); 		 					//  Qty On Hand
				line.add(qtyReserved);					//  Qty Reserved
				line.add(qtyOrdered);				//  Qty to Delivery
				line.add(qtyExpected); 		 			//  ATP
				line.add(rs.getString(6));	//  BPartner
				line.add(rs.getString(7));	//  DocumentNo
				line.add(rs.getString(8));  	//	Warehouse
				line.add(null);				      		//  Locator
				String asi = rs.getString(3);
				if (showDetail && (asi == null || asi.length() == 0))
					asi = "{" + rs.getInt(4) + "}";
				line.add(asi);							//  ASI
				data.add(line);
			}
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		finally {
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}

		//  Table
		m_modelAtp = new ListModelTable(data);
		m_tableAtp.setData(m_modelAtp, columnNames);
		//
		m_tableAtp.setColumnClass(0, Timestamp.class, true);   //  Date
		m_tableAtp.setColumnClass(1, Double.class, true);      //  Quantity On Hand
		m_tableAtp.setColumnClass(2, Double.class, true);       //  Quantity Reserved
		m_tableAtp.setColumnClass(3, Double.class, true);       //  Quantity Ordered
		m_tableAtp.setColumnClass(4, Double.class, true);       //  ATP
		m_tableAtp.setColumnClass(5, String.class, true);       //  DocNo
		m_tableAtp.setColumnClass(6, String.class, true);      //  Partner
		m_tableAtp.setColumnClass(7, String.class, true);       //  Warehouse
		m_tableAtp.setColumnClass(8, String.class, true);      //  Locator
		m_tableAtp.setColumnClass(9, String.class, true);      //  ASI
		//
		m_tableAtp.autoSize();
	}	//	initAtpTab
	
	/**
	 * Query Order Price History [Sales/Purchase]
	 */
	private void initOrderPriceHistoryTab()
	{
		// Done already
		if (m_modelOrderPrice != null)
			return;

		Vector<String> columnNames = new Vector<String>();
		columnNames.add(Msg.translate(Env.getCtx(), m_C_BPartner_ID == 0 ? "C_BPartner_ID" : "M_Product_ID"));
		columnNames.add(Msg.translate(Env.getCtx(), "PriceActual"));
		columnNames.add(Msg.translate(Env.getCtx(), "QtyOrdered"));
		columnNames.add(Msg.translate(Env.getCtx(), "Discount"));
		columnNames.add(Msg.translate(Env.getCtx(), "DocumentNo"));
		columnNames.add(Msg.translate(Env.getCtx(), "DateOrdered"));
		columnNames.add(Msg.translate(Env.getCtx(), "AD_Org_ID"));

		// Fill Data
		Vector<Vector<Object>> data = null;
		if (m_C_BPartner_ID == 0)
			data = queryBPartner(false); // BPartner of Product
		else
			data = queryProduct(false); // Product of BPartner

		// Table
		m_modelOrderPrice = new ListModelTable(data);
		m_tableOrderPrice.setData(m_modelOrderPrice, columnNames);
		//
		m_tableOrderPrice.setColumnClass(0, String.class, true); // Product/Partner
		m_tableOrderPrice.setColumnClass(1, Double.class, true); // Price
		m_tableOrderPrice.setColumnClass(2, Double.class, true); // Quantity
		m_tableOrderPrice.setColumnClass(3, BigDecimal.class, true); //  Discount (%) to limit precision
		m_tableOrderPrice.setColumnClass(4, String.class, true); // DocNo
		m_tableOrderPrice.setColumnClass(5, Timestamp.class, true); // Date
		m_tableOrderPrice.setColumnClass(6, String.class, true); // Org
		//
		m_tableOrderPrice.autoSize();

	} // initOrderPriceHistoryTab
	
}	//	InvoiceHistory
