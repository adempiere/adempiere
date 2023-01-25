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

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Vector;
import java.util.logging.Level;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.WindowConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import org.compiere.apps.AEnv;
import org.compiere.apps.ConfirmPanel;
import org.compiere.minigrid.MiniTable;
import org.compiere.model.MPriceList;
import org.compiere.swing.CDialog;
import org.compiere.swing.CPanel;
import org.compiere.util.CLogMgt;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;

/**
 *	Price History for BPartner/Product
 *
 *  @author Jorg Janke
 *  @version  $Id: InvoiceHistory.java,v 1.2 2006/07/30 00:51:27 jjanke Exp $
 */
public class InvoiceHistory extends CDialog
	implements ActionListener, ChangeListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7886949815469558804L;

	/**
	 *	Show History
	 *	@param C_BPartner_ID partner
	 *	@param M_Product_ID product
	 *	@param M_Warehouse_ID warehouse
	 *	@param M_AttributeSetInstance_ID ASI
	 */
	public InvoiceHistory (Dialog frame, 
		int C_BPartner_ID, int M_Product_ID, int M_Warehouse_ID, int M_AttributeSetInstance_ID)
	{
		super(frame, Msg.getMsg(Env.getCtx(), "PriceHistory"), true);
		log.config("C_BPartner_ID=" + C_BPartner_ID
			+ ", M_Product_ID=" + M_Product_ID
			+ ", M_Warehouse_ID=" + M_Warehouse_ID
			+ ", M_AttributeSetInstance_ID=" + M_AttributeSetInstance_ID);
		m_C_BPartner_ID = C_BPartner_ID;
		m_M_Product_ID = M_Product_ID;
		m_M_Warehouse_ID = M_Warehouse_ID;
		m_M_AttributeSetInstance_ID = M_AttributeSetInstance_ID;
		try
		{
			jbInit();
			dynInit();
		}
		catch(Exception ex)
		{
			log.log(Level.SEVERE, "", ex);
		}
		mainPanel.setPreferredSize(new Dimension(700,400));
		AEnv.positionCenterWindow(frame, this);
	}	//	InvoiceHistory

	private int		m_C_BPartner_ID;
	private int		m_M_Product_ID;
	private int		m_M_Warehouse_ID;
	private int		m_M_AttributeSetInstance_ID;
	
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(InvoiceHistory.class);

	private CPanel 			mainPanel = new CPanel();
	private BorderLayout 	mainLayout = new BorderLayout();
	private CPanel 			northPanel = new CPanel();
	private JLabel 			label = new JLabel();
	private FlowLayout 		northLayout = new FlowLayout();
	//
	private ConfirmPanel 	confirmPanel = new ConfirmPanel();
	private JTabbedPane 	centerTabbedPane = new JTabbedPane();
	//
	private JScrollPane 		pricePane = new JScrollPane();
	private MiniTable 			m_tablePrice = new MiniTable();
	private DefaultTableModel 	m_modelPrice = null;
	
	private JScrollPane			orderPricePane		= new JScrollPane();
	private MiniTable			m_tableOrderPrice	= new MiniTable();
	private DefaultTableModel	m_modelOrderPrice	= null;
	
	private JScrollPane 		reservedPane = new JScrollPane();
	private MiniTable 			m_tableReserved = new MiniTable();
	private DefaultTableModel 	m_modelReserved = null;
	
	private JScrollPane 		orderedPane = new JScrollPane();
	private MiniTable 			m_tableOrdered = new MiniTable();
	private DefaultTableModel 	m_modelOrdered = null;
	
	private JScrollPane 		unconfirmedPane = new JScrollPane();
	private MiniTable 			m_tableUnconfirmed = new MiniTable();
	private DefaultTableModel 	m_modelUnconfirmed = null;

	private JScrollPane 		atpPane = new JScrollPane();
	private MiniTable 			m_tableAtp = new MiniTable();
	private DefaultTableModel 	m_modelAtp = null;
	
	/**
	 *	Ststic Init
	 */
	void jbInit() throws Exception
	{
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		mainPanel.setLayout(mainLayout);
		label.setText("Label");
		northPanel.setLayout(northLayout);
		northLayout.setAlignment(FlowLayout.LEFT);
		getContentPane().add(mainPanel);
		mainPanel.add(northPanel, BorderLayout.NORTH);
		northPanel.add(label, null);
		mainPanel.add(confirmPanel, BorderLayout.SOUTH);
		mainPanel.add(centerTabbedPane, BorderLayout.CENTER);
		centerTabbedPane.addChangeListener(this);
		centerTabbedPane.add(pricePane,   Msg.getMsg(Env.getCtx(), "PriceHistory"));
		centerTabbedPane.add(orderPricePane, Msg.translate(Env.getCtx(), "OrderPriceHistory"));
		centerTabbedPane.add(reservedPane, Msg.translate(Env.getCtx(), "QtyReserved"));
		centerTabbedPane.add(orderedPane, Msg.translate(Env.getCtx(), "QtyOrdered"));
		centerTabbedPane.add(unconfirmedPane, Msg.getMsg(Env.getCtx(), "QtyUnconfirmed"));
		if (m_M_Product_ID != 0)
			centerTabbedPane.add(atpPane, Msg.getMsg(Env.getCtx(), "ATP"));
		//
		pricePane.getViewport().add(m_tablePrice, null);
		orderPricePane.getViewport().add(m_tableOrderPrice, null);
		reservedPane.getViewport().add(m_tableReserved, null);
		orderedPane.getViewport().add(m_tableOrdered, null);
		unconfirmedPane.getViewport().add(m_tableUnconfirmed, null);
		if (m_M_Product_ID != 0)
			atpPane.getViewport().add(m_tableAtp, null);
		//
		confirmPanel.addActionListener(this);
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
		m_modelPrice = new DefaultTableModel(data, columnNames);
		m_tablePrice.setModel(m_modelPrice);
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
				+ "WHERE i.C_BPartner_ID="+ m_M_Product_ID
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
					+ "WHERE o.C_BPartner_ID="+m_C_BPartner_ID
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
			 sql = "SELECT bp.Name,l.PriceActual,l.PriceList,l.QtyInvoiced,"		//	1,2,3,4
				+ "i.DateInvoiced,dt.PrintName || ' ' || i.DocumentNo As DocumentNo,"	//	5,6
				+ "o.Name,"																//  7
				+ "NULL, i.M_PriceList_ID"												//  8,9
				+ " FROM C_Invoice i"
				+ " INNER JOIN C_InvoiceLine l ON (i.C_Invoice_ID=l.C_Invoice_ID)"
				+ " INNER JOIN C_DocType dt ON (i.C_DocType_ID=dt.C_DocType_ID)"
				+ " INNER JOIN AD_Org o ON (i.AD_Org_ID=o.AD_Org_ID)"
				+ " INNER JOIN C_BPartner bp ON (i.C_BPartner_ID=bp.C_BPartner_ID) "
				+ "WHERE l.M_Product_ID="+m_M_Product_ID
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
					+ " WHERE ol.M_Product_ID=" + m_M_Product_ID
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


	/**
	 *	Action Listener
	 */
	public void actionPerformed(ActionEvent e)
	{
		if (e.getActionCommand().equals(ConfirmPanel.A_OK))
			dispose();
	}	//	actionPerformed


	/**
	 * 	Tab Changed
	 * 	@param e event
	 */
	public void stateChanged(ChangeEvent e)
	{
		if (centerTabbedPane.getSelectedIndex() == 1)
			initOrderPriceHistoryTab();
		else if (centerTabbedPane.getSelectedIndex() == 2)
			initReservedOrderedTab(true);
		else if (centerTabbedPane.getSelectedIndex() == 3)
			initReservedOrderedTab(false);
		else if (centerTabbedPane.getSelectedIndex() == 4)
			initUnconfirmedTab();
		else if (centerTabbedPane.getSelectedIndex() == 5)
			initAtpTab();
	}	//	stateChanged

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
			sql = "SELECT bp.Name AS BPName, ol.PriceActual AS PriceActual,ol.PriceList AS PriceList ,ol.QtyReserved AS QtyReserved,"
				+ "o.DateOrdered AS DateOrdered,dt.PrintName || ' ' || o.DocumentNo As DocumentNo, "
				+ "w.Name AS WarehouseName,"
				+ "ol.Discount AS Discount "															// 8,9=M_PriceList_ID
				+ "FROM C_Order o"
				+ " INNER JOIN C_OrderLine ol ON (o.C_Order_ID=ol.C_Order_ID)"
				+ " INNER JOIN C_DocType dt ON (o.C_DocType_ID=dt.C_DocType_ID)"
				+ " INNER JOIN M_Warehouse w ON (ol.M_Warehouse_ID=w.M_Warehouse_ID)"
				+ " INNER JOIN C_BPartner bp  ON (o.C_BPartner_ID=bp.C_BPartner_ID) "
				+ "WHERE ol.QtyReserved<>0"
				+ " AND ol.M_Product_ID="+m_M_Product_ID
				+ " AND o.IsSOTrx=" + (reserved ? "'Y'" : "'N'");

		}
		else
		{
			sql = "SELECT p.Name AS BPName , ol.PriceActual AS PriceActual ,ol.PriceList AS PriceList ,ol.QtyReserved AS QtyReserved,"
				+ "o.DateOrdered AS DateOrdered ,dt.PrintName || ' ' || o.DocumentNo As DocumentNo, "
				+ "w.Name AS WarehouseName,"
				+ "ol.Discount AS Discount "													// 8,9=M_PriceList_ID
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
		MiniTable table = null;
		if (reserved)
		{
			m_modelReserved = new DefaultTableModel(data, columnNames); 
			m_tableReserved.setModel(m_modelReserved);
			table = m_tableReserved;
		}
		else
		{
			m_modelOrdered = new DefaultTableModel(data, columnNames); 
			m_tableOrdered.setModel(m_modelOrdered);
			table = m_tableOrdered;
		}
		//
		table.setColumnClass(0, String.class, true);      //  Product/Partner
		table.setColumnClass(1, BigDecimal.class, true);  //  Price
		table.setColumnClass(2, Double.class, true);      //  Quantity
		table.setColumnClass(3, BigDecimal.class, true);  //  Discount (%)
		table.setColumnClass(4, String.class, true);      //  DocNo
		table.setColumnClass(5, Timestamp.class, true);   //  Date
		table.setColumnClass(6, String.class, true);   	  //  Warehouse
		//
		table.autoSize();
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
		m_modelUnconfirmed = new DefaultTableModel(data, columnNames); 
		m_tableUnconfirmed.setModel(m_modelUnconfirmed);
		MiniTable table = m_tableUnconfirmed;
		//
		table.setColumnClass(0, String.class, true);      //  Product/Partner
		table.setColumnClass(1, Double.class, true);  	  //  MovementQty
		table.setColumnClass(2, Timestamp.class, true);   //  MovementDate
		table.setColumnClass(3, Boolean.class, true);  	  //  IsSOTrx
		table.setColumnClass(4, String.class, true);      //  DocNo
		table.setColumnClass(5, String.class, true);	  //  Warehouse
		//
		table.autoSize();
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
		columnNames.add(Msg.translate(Env.getCtx(), "DocumentNo"));
		columnNames.add(Msg.translate(Env.getCtx(), "C_BPartner_ID"));
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
		sql += " w.Name, l.Value , 0 AS QtyDelivered "
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
				line.add(qtyOnHand);  							//  qty On Hand
				line.add(qtyReserved);  						//  QtyReserved
				line.add(qtyOrdered);  						//  Qty To Delivery
				line.add(qtyExpected);  						//  ATP
				line.add(null);									//  DocumentNo
				line.add(null);									//  BPartner
				line.add(rs.getString(6));  			//	Warehouse
				line.add(rs.getString(7));      		//  Locator
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
		sql = "SELECT o.DatePromised,CASE WHEN o.IsSOTrx = 'Y' THEN ol.QtyReserved ELSE 0  END AS QtyReserved,"
			+ " productAttribute(ol.M_AttributeSetInstance_ID), ol.M_AttributeSetInstance_ID,"
			+ " dt.DocBaseType, bp.Name,"
			+ " dt.PrintName || ' ' || o.DocumentNo As DocumentNo, w.Name ,"
			+ " CASE WHEN o.IsSOTrx = 'N' THEN ol.QtyReserved ELSE 0 END AS QtyOrdered ,"
			+ " ol.QtyDelivered AS QtyDelivered "
			+ "FROM C_Order o"
			+ " INNER JOIN C_OrderLine ol ON (o.C_Order_ID=ol.C_Order_ID)"
			+ " INNER JOIN C_DocType dt ON (o.C_DocType_ID=dt.C_DocType_ID)"
			+ " INNER JOIN M_Warehouse w ON (ol.M_Warehouse_ID=w.M_Warehouse_ID)"
			+ " INNER JOIN C_BPartner bp  ON (o.C_BPartner_ID=bp.C_BPartner_ID) "
			+ "WHERE ol.QtyReserved<>0"
			+ " AND ol.M_Product_ID="+ m_M_Product_ID;
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

		//	Manufacturing Orders Ordered
		sql += " UNION ALL ";
		sql += "SELECT o.DatePromised, 0 AS QtyReserved,"
				+ " productAttribute(o.M_AttributeSetInstance_ID), o.M_AttributeSetInstance_ID,"
				+ " dt.DocBaseType, null AS Name,"
				+ " dt.PrintName || ' ' || o.DocumentNo As DocumentNo, w.Name , o.QtyOrdered AS QtyOrdered , o.QtyDelivered AS QtyDelivered "
				+ "FROM PP_Order o"
				+ " INNER JOIN C_DocType dt ON (o.C_DocType_ID=dt.C_DocType_ID)"
				+ " INNER JOIN M_Warehouse w ON (o.M_Warehouse_ID=w.M_Warehouse_ID)"
				+ " WHERE o.M_Product_ID="+ m_M_Product_ID;
		if (m_M_Warehouse_ID != 0)
			sql += " AND o.M_Warehouse_ID=" + m_M_Warehouse_ID;
		if (m_M_AttributeSetInstance_ID > 0)
			sql += " AND o.M_AttributeSetInstance_ID="+ m_M_AttributeSetInstance_ID;

		//	Manufacturing Orders Reserved
		sql += " UNION ALL ";
		sql += "SELECT o.DatePromised, ol.QtyReserved,"
				+ " productAttribute(ol.M_AttributeSetInstance_ID), ol.M_AttributeSetInstance_ID,"
				+ " dt.DocBaseType, null AS Name,"
				+ " dt.PrintName || ' ' || o.DocumentNo As DocumentNo, w.Name , 0 AS QtyOrdered , ol.QtyDelivered AS QtyDelivered "
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
				line.add(rs.getTimestamp(1));			//  Date
				line.add(0); 		 					//  Qty On Hand
				line.add(qtyReserved);					//  QtyReserved
				line.add(qtyOrdered);					//  QtyOrdered
				line.add(qtyExpected); 		 			//  ATP
				line.add(rs.getString(7));				//  DocumentNo
				line.add(rs.getString(6));				//  BPartner
				line.add(rs.getString(8));  			//	Warehouse
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
		MiniTable table = null;
		m_modelAtp = new DefaultTableModel(data, columnNames); 
		m_tableAtp.setModel(m_modelAtp);
		table = m_tableAtp;
		//
		table.setColumnClass(0, Timestamp.class, true);   //  Date
		table.setColumnClass(1, Double.class, true);      //  On Hand Quantity
		table.setColumnClass(2, Double.class, true);      //  Reserved Quantity
		table.setColumnClass(3, Double.class, true);      //  Ordered Quantity
		table.setColumnClass(4, Double.class, true);      //  ATP
		table.setColumnClass(5, String.class, true);      //  DocNo
		table.setColumnClass(6, String.class, true);      //  Partner
		table.setColumnClass(7, String.class, true);   	 //  Warehouse
		table.setColumnClass(8, String.class, true);   	 //  Locator
		table.setColumnClass(9, String.class, true);   	 //  ASI
		table.autoSize();
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
		m_modelOrderPrice = new DefaultTableModel(data, columnNames);
		m_tableOrderPrice.setModel(m_modelOrderPrice);
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
