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
package org.compiere.apps.form;

import java.awt.*;
import java.awt.event.*;
import java.math.*;
import java.sql.*;
import java.util.*;
import java.util.logging.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import org.compiere.apps.*;
import org.compiere.grid.ed.*;
import org.compiere.minigrid.*;
import org.compiere.model.*;
import org.compiere.plaf.*;
import org.compiere.swing.*;
import org.compiere.util.*;

/**
 *  Manual Matching
 *
 *  @author     Jorg Janke
 *  @version    $Id: VMatch.java,v 1.2 2006/07/30 00:51:28 jjanke Exp $
 */
public class VMatch extends CPanel
	implements FormPanel, ActionListener, TableModelListener, ListSelectionListener
{
	/**
	 *	Initialize Panel
	 *  @param WindowNo window
	 *  @param frame frame
	 */
	public void init (int WindowNo, FormFrame frame)
	{
		m_WindowNo = WindowNo;
		m_frame = frame;
		log.info("WinNo=" + m_WindowNo
			+ " - AD_Client_ID=" + m_AD_Client_ID + ", AD_Org_ID=" + m_AD_Org_ID + ", By=" + m_by);
		Env.setContext(Env.getCtx(), m_WindowNo, "IsSOTrx", "N");

		try
		{
			//	UI
			onlyVendor = VLookup.createBPartner(m_WindowNo); 
			onlyProduct = VLookup.createProduct(m_WindowNo);
			jbInit();
			//
			dynInit();
			frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
			frame.getContentPane().add(statusBar, BorderLayout.SOUTH);
			//
			new Thread()
			{
				public void run()
				{
					log.info("Starting ...");
					MMatchPO.consolidate(Env.getCtx());
					log.info("... Done");
				}
			}.start();
		}
		catch(Exception e)
		{
			log.log(Level.SEVERE, "", e);
		}
	}	//	init

	/**	Window No			*/
	private int         	m_WindowNo = 0;
	/**	FormFrame			*/
	private FormFrame 		m_frame;
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(VMatch.class);

	private int     m_AD_Client_ID = Env.getAD_Client_ID(Env.getCtx());
	private int     m_AD_Org_ID = Env.getAD_Org_ID(Env.getCtx());
	private int     m_by = Env.getAD_User_ID(Env.getCtx());

	/** Match Options           */
	private String[] m_matchOptions = new String[] {
		Msg.getElement(Env.getCtx(), "C_Invoice_ID", false),
		Msg.getElement(Env.getCtx(), "M_InOut_ID", false),
		Msg.getElement(Env.getCtx(), "C_Order_ID", false) };
	private static final int		MATCH_INVOICE = 0;
	private static final int		MATCH_SHIPMENT = 1;
	private static final int		MATCH_ORDER = 2;

	/** Match Mode              	*/
	private String[] m_matchMode = new String[] {
		Msg.translate(Env.getCtx(), "NotMatched"),
		Msg.translate(Env.getCtx(), "Matched")};
	private static final int		MODE_NOTMATCHED = 0;
	private static final int		MODE_MATCHED = 1;

	/**	Indexes in Table			*/
	private static final int		I_BPartner = 3;
	private static final int		I_Line = 4;
	private static final int		I_Product = 5;
	private static final int		I_QTY = 6;
	private static final int		I_MATCHED = 7;


	private StringBuffer    m_sql = null;
	private String          m_dateColumn = "";
	private String          m_qtyColumn = "";
	private String          m_groupBy = "";
	private StringBuffer			m_linetype = null;
	private BigDecimal      m_xMatched = Env.ZERO;
	private BigDecimal      m_xMatchedTo = Env.ZERO;

	//
	private CPanel mainPanel = new CPanel();
	private StatusBar statusBar = new StatusBar();
	private BorderLayout mainLayout = new BorderLayout();
	private CPanel northPanel = new CPanel();
	private GridBagLayout northLayout = new GridBagLayout();
	private CLabel matchFromLabel = new CLabel();
	private CComboBox matchFrom = new CComboBox(m_matchOptions);
	private CLabel matchToLabel = new CLabel();
	private CComboBox matchTo = new CComboBox();
	private CLabel matchModeLabel = new CLabel();
	private CComboBox matchMode = new CComboBox(m_matchMode);
	private VLookup onlyVendor = null; 
	private VLookup onlyProduct = null;
	private CLabel onlyVendorLabel = new CLabel();
	private CLabel onlyProductLabel = new CLabel();
	private CLabel dateFromLabel = new CLabel();
	private CLabel dateToLabel = new CLabel();
	private VDate dateFrom = new VDate("DateFrom", false, false, true, DisplayType.Date, "DateFrom");
	private VDate dateTo = new VDate("DateTo", false, false, true, DisplayType.Date, "DateTo");
	private CButton bSearch = new CButton();
	private CPanel southPanel = new CPanel();
	private GridBagLayout southLayout = new GridBagLayout();
	private CLabel xMatchedLabel = new CLabel();
	private CLabel xMatchedToLabel = new CLabel();
	private CLabel differenceLabel = new CLabel();
	private VNumber xMatched = new VNumber("xMatched", false, true, false, DisplayType.Quantity, "xMatched");
	private VNumber xMatchedTo = new VNumber("xMatchedTo", false, true, false, DisplayType.Quantity, "xMatchedTo");
	private VNumber difference = new VNumber("Difference", false, true, false, DisplayType.Quantity, "Difference");
	private CButton bProcess = new CButton();
	private CPanel centerPanel = new CPanel();
	private BorderLayout centerLayout = new BorderLayout(5,5);
	private JScrollPane xMatchedScrollPane = new JScrollPane();
	private TitledBorder xMatchedBorder = new TitledBorder("xMatched");
	private MiniTable xMatchedTable = new MiniTable();
	private JScrollPane xMatchedToScrollPane = new JScrollPane();
	private TitledBorder xMatchedToBorder = new TitledBorder("xMatchedTo");
	private MiniTable xMatchedToTable = new MiniTable();
	private CPanel xPanel = new CPanel();
	private JCheckBox sameProduct = new JCheckBox();
	private JCheckBox sameBPartner = new JCheckBox();
	private JCheckBox sameQty = new JCheckBox();
	private FlowLayout xLayout = new FlowLayout(FlowLayout.CENTER, 10, 0);

	/**
	 *  Static Init.
	 *  <pre>
	 *  mainPanel
	 *      northPanel
	 *      centerPanel
	 *          xMatched
	 *          xPanel
	 *          xMathedTo
	 *      southPanel
	 *  </pre>
	 *  @throws Exception
	 */
	private void jbInit() throws Exception
	{
		mainPanel.setLayout(mainLayout);
		northPanel.setLayout(northLayout);
		matchFromLabel.setText(Msg.translate(Env.getCtx(), "MatchFrom"));
		matchToLabel.setText(Msg.translate(Env.getCtx(), "MatchTo"));
		matchModeLabel.setText(Msg.translate(Env.getCtx(), "MatchMode"));
		onlyVendorLabel.setText(Msg.translate(Env.getCtx(), "C_BPartner_ID"));
		onlyProductLabel.setText(Msg.translate(Env.getCtx(), "M_Product_ID"));
		dateFromLabel.setText(Msg.translate(Env.getCtx(), "DateFrom"));
		dateToLabel.setText(Msg.translate(Env.getCtx(), "DateTo"));
		bSearch.setText(Msg.translate(Env.getCtx(), "Search"));
		southPanel.setLayout(southLayout);
		xMatchedLabel.setText(Msg.translate(Env.getCtx(), "ToBeMatched"));
		xMatchedToLabel.setText(Msg.translate(Env.getCtx(), "Matching"));
		differenceLabel.setText(Msg.translate(Env.getCtx(), "Difference"));
		bProcess.setText(Msg.translate(Env.getCtx(), "Process"));
		centerPanel.setLayout(centerLayout);
		xMatchedScrollPane.setBorder(xMatchedBorder);
		xMatchedScrollPane.setPreferredSize(new Dimension(450, 200));
		xMatchedToScrollPane.setBorder(xMatchedToBorder);
		xMatchedToScrollPane.setPreferredSize(new Dimension(450, 200));
		sameProduct.setSelected(true);
		sameProduct.setText(Msg.translate(Env.getCtx(), "SameProduct"));
		sameBPartner.setSelected(true);
		sameBPartner.setText(Msg.translate(Env.getCtx(), "SameBPartner"));
		sameQty.setSelected(false);
		sameQty.setText(Msg.translate(Env.getCtx(), "SameQty"));
		xPanel.setLayout(xLayout);
		mainPanel.add(northPanel,  BorderLayout.NORTH);
		northPanel.add(matchFromLabel,    new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(12, 12, 5, 5), 0, 0));
		northPanel.add(matchFrom,        new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(12, 0, 5, 0), 0, 0));
		northPanel.add(matchToLabel,      new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(12, 5, 5, 5), 0, 0));
		northPanel.add(matchTo,        new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(12, 0, 5, 0), 0, 0));
		northPanel.add(matchModeLabel,    new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 12, 5, 5), 0, 0));
		northPanel.add(matchMode,     new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 5, 0), 0, 0));
		northPanel.add(onlyVendor,     new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 0, 5, 0), 0, 0));
		northPanel.add(onlyProduct,       new GridBagConstraints(3, 2, 1, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 0, 5, 0), 0, 0));
		northPanel.add(onlyVendorLabel,     new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 12, 5, 5), 0, 0));
		northPanel.add(onlyProductLabel,      new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		northPanel.add(dateFromLabel,    new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 12, 5, 5), 0, 0));
		northPanel.add(dateToLabel,      new GridBagConstraints(2, 3, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 5, 5), 0, 0));
		northPanel.add(dateFrom,      new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 5, 0), 0, 0));
		northPanel.add(dateTo,       new GridBagConstraints(3, 3, 1, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 5, 0), 0, 0));
		northPanel.add(bSearch,   new GridBagConstraints(4, 3, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 12, 5, 12), 0, 0));
		mainPanel.add(southPanel,  BorderLayout.SOUTH);
		southPanel.add(xMatchedLabel,        new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 12, 5, 5), 0, 0));
		southPanel.add(xMatched,         new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 0, 5, 0), 0, 0));
		southPanel.add(xMatchedToLabel,          new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 10, 5, 5), 0, 0));
		southPanel.add(bProcess,        new GridBagConstraints(6, 0, 1, 1, 0.0, 0.0
			,GridBagConstraints.NORTHEAST, GridBagConstraints.NONE, new Insets(5, 12, 5, 12), 0, 0));
		southPanel.add(differenceLabel,      new GridBagConstraints(4, 0, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 10, 5, 5), 0, 0));
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		centerPanel.add(xMatchedScrollPane,  BorderLayout.NORTH);
		xMatchedScrollPane.getViewport().add(xMatchedTable, null);
		centerPanel.add(xMatchedToScrollPane,  BorderLayout.SOUTH);
		centerPanel.add(xPanel, BorderLayout.CENTER);
		xPanel.add(sameBPartner, null);
		xPanel.add(sameProduct, null);
		xPanel.add(sameQty, null);
		xMatchedToScrollPane.getViewport().add(xMatchedToTable, null);
		southPanel.add(difference,   new GridBagConstraints(5, 0, 1, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 0, 5, 0), 0, 0));
		southPanel.add(xMatchedTo, new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 0, 5, 0), 0, 0));
	}   //  jbInit

	/**
	 *  Dynamic Init.
	 *  Table Layout, Visual, Listener
	 */
	private void dynInit()
	{
		ColumnInfo[] layout = new ColumnInfo[] {
			new ColumnInfo(" ",                                         ".", IDColumn.class, false, false, ""),
			new ColumnInfo(Msg.translate(Env.getCtx(), "DocumentNo"),   ".", String.class),             //  1
			new ColumnInfo(Msg.translate(Env.getCtx(), "Date"),         ".", Timestamp.class),
			new ColumnInfo(Msg.translate(Env.getCtx(), "C_BPartner_ID"),".", KeyNamePair.class, "."),   //  3
			new ColumnInfo(Msg.translate(Env.getCtx(), "Line"),         ".", KeyNamePair.class, "."),
			new ColumnInfo(Msg.translate(Env.getCtx(), "M_Product_ID"), ".", KeyNamePair.class, "."),   //  5
			new ColumnInfo(Msg.translate(Env.getCtx(), "Qty"),          ".", Double.class),
			new ColumnInfo(Msg.translate(Env.getCtx(), "Matched"),      ".", Double.class)
		};

		xMatchedTable.prepareTable(layout, "", "", false, "");
		xMatchedToTable.prepareTable(layout, "", "", true, "");

		//  Visual
		CompiereColor.setBackground (this);

		//  Listener
		matchFrom.addActionListener(this);
		matchTo.addActionListener(this);
		bSearch.addActionListener(this);
		xMatchedTable.getSelectionModel().addListSelectionListener(this);
		xMatchedToTable.getModel().addTableModelListener(this);
		bProcess.addActionListener(this);
		sameBPartner.addActionListener(this);
		sameProduct.addActionListener(this);
		sameQty.addActionListener(this);
		//  Init
		cmd_matchFrom();
		statusBar.setStatusLine("");
		statusBar.setStatusDB(0);
	}   //  dynInit

	/**
	 * 	Dispose
	 */
	public void dispose()
	{
		if (m_frame != null)
			m_frame.dispose();
		m_frame = null;
	}	//	dispose

	
	/**************************************************************************
	 *  Action Listener
	 *  @param e event
	 */
	public void actionPerformed (ActionEvent e)
	{
		setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		if (e.getSource() == matchFrom)
			cmd_matchFrom();
		else if (e.getSource() == matchTo)
			cmd_matchTo();
		else if (e.getSource() == bSearch)
			cmd_search();
		else if (e.getSource() == bProcess)
			cmd_process();
		else if (e.getSource() == sameBPartner
			|| e.getSource() == sameProduct
			|| e.getSource() == sameQty)
			cmd_searchTo();
		setCursor(Cursor.getDefaultCursor());
	}   //  actionPerformed

	/**
	 *  Match From Changed - Fill Match To
	 */
	private void cmd_matchFrom()
	{
	//	log.fine( "VMatch.cmd_matchFrom");
		String selection = (String)matchFrom.getSelectedItem();
		Vector<String> vector = new Vector<String>(2);
		if (selection.equals(m_matchOptions[MATCH_INVOICE]))
			vector.add(m_matchOptions[MATCH_SHIPMENT]);
		else if (selection.equals(m_matchOptions[MATCH_ORDER]))
			vector.add(m_matchOptions[MATCH_SHIPMENT]);
		else    //  shipment
		{
			vector.add(m_matchOptions[MATCH_INVOICE]);
			vector.add(m_matchOptions[MATCH_ORDER]);
		}
		matchTo.setModel(new DefaultComboBoxModel(vector));
		//  Set Title
		xMatchedBorder.setTitle(selection);
		xMatchedScrollPane.repaint();
		//  Reset Table
		xMatchedTable.setRowCount(0);
		//  sync To
		cmd_matchTo();
	}   //  cmd_matchFrom

	/**
	 *  Match To Changed - set Title
	 */
	private void cmd_matchTo()
	{
	//	log.fine( "VMatch.cmd_matchTo");
		String selection = (String)matchTo.getSelectedItem();
		xMatchedToBorder.setTitle(selection);
		xMatchedToScrollPane.repaint();
		//  Reset Table
		xMatchedToTable.setRowCount(0);
	}   //  cmd_matchTo

	/**
	 *  Search Button Pressed - Fill xMatched
	 */
	private void cmd_search()
	{
		//  ** Create SQL **
		int display = matchFrom.getSelectedIndex();
		String matchToString = (String)matchTo.getSelectedItem();
		int matchToType = MATCH_INVOICE;
		if (matchToString.equals(m_matchOptions[MATCH_SHIPMENT]))
			matchToType = MATCH_SHIPMENT;
		else if (matchToString.equals(m_matchOptions[MATCH_ORDER]))
			matchToType = MATCH_ORDER;
		//
		tableInit(display, matchToType);	//	sets m_sql

		//  ** Add Where Clause **
		//  Product
		if (onlyProduct.getValue() != null)
		{
			Integer Product = (Integer)onlyProduct.getValue();
			m_sql.append(" AND lin.M_Product_ID=").append(Product);
		}
		//  BPartner
		if (onlyVendor.getValue() != null)
		{
			Integer Vendor = (Integer)onlyVendor.getValue();
			m_sql.append(" AND hdr.C_BPartner_ID=").append(Vendor);
		}
		//  Date
		Timestamp from = (Timestamp)dateFrom.getValue();
		Timestamp to = (Timestamp)dateTo.getValue();
		if (from != null && to != null)
			m_sql.append(" AND ").append(m_dateColumn).append(" BETWEEN ")
				.append(DB.TO_DATE(from)).append(" AND ").append(DB.TO_DATE(to));
		else if (from != null)
			m_sql.append(" AND ").append(m_dateColumn).append(" >= ").append(DB.TO_DATE(from));
		else if (to != null)
			m_sql.append(" AND ").append(m_dateColumn).append(" <= ").append(DB.TO_DATE(to));

		//  ** Load Table **
		tableLoad (xMatchedTable);
		xMatched.setValue(Env.ZERO);
		//  Status Info
		statusBar.setStatusLine(matchFrom.getSelectedItem().toString()
			+ "# = " + xMatchedTable.getRowCount(),
			xMatchedTable.getRowCount() == 0);
		statusBar.setStatusDB(0);
	}   //  cmd_search

	/**
	 *  Process Button Pressed - Process Matching
	 */
	private void cmd_process()
	{
		log.config("");
		//  Matched From
		int matchedRow = xMatchedTable.getSelectedRow();
		if (matchedRow < 0)
			return;
	//	KeyNamePair BPartner = (KeyNamePair)xMatchedTable.getValueAt(matchedRow, I_BPartner);
		KeyNamePair lineMatched = (KeyNamePair)xMatchedTable.getValueAt(matchedRow, I_Line);
		KeyNamePair Product = (KeyNamePair)xMatchedTable.getValueAt(matchedRow, I_Product);

		int M_Product_ID = Product.getKey();
		double totalQty = m_xMatched.doubleValue();

		//  Matched To
		for (int row = 0; row < xMatchedToTable.getRowCount(); row++)
		{
			IDColumn id = (IDColumn)xMatchedToTable.getValueAt(row, 0);
			if (id != null && id.isSelected())
			{
				//  need to be the same product
				KeyNamePair ProductCompare = (KeyNamePair)xMatchedToTable.getValueAt(row, I_Product);
				if (Product.getKey() != ProductCompare.getKey())
					continue;

				KeyNamePair lineMatchedTo = (KeyNamePair)xMatchedToTable.getValueAt(row, I_Line);

				//	Qty
				double qty = 0.0;
				if (matchMode.getSelectedIndex() == MODE_NOTMATCHED)
					qty = ((Double)xMatchedToTable.getValueAt(row, I_QTY)).doubleValue();	//  doc
				qty -= ((Double)xMatchedToTable.getValueAt(row, I_MATCHED)).doubleValue();  //  matched
				if (qty > totalQty)
					qty = totalQty;
				totalQty -= qty;

				//  Invoice or PO
				boolean invoice = true;
				if (matchFrom.getSelectedIndex() == MATCH_ORDER ||
						matchTo.getSelectedItem().equals(m_matchOptions[MATCH_ORDER]))
					invoice = false;
				//  Get Shipment_ID
				int M_InOutLine_ID = 0;
				int Line_ID = 0;
				if (matchFrom.getSelectedIndex() == MATCH_SHIPMENT)
				{
					M_InOutLine_ID = lineMatched.getKey();      //  upper table
					Line_ID = lineMatchedTo.getKey();
				}
				else
				{
					M_InOutLine_ID = lineMatchedTo.getKey();    //  lower table
					Line_ID = lineMatched.getKey();
				}

				//  Create it
				createMatchRecord(invoice, M_InOutLine_ID, Line_ID, new BigDecimal(qty));
			}
		}
		//  requery
		cmd_search();
	}   //  cmd_process

	
	/**************************************************************************
	 *  List Selection Listener - get Info and fill xMatchedTo
	 *  @param e event
	 */
	public void valueChanged (ListSelectionEvent e)
	{
		if (e.getValueIsAdjusting())
			return;
	//	log.config( "VMatch.valueChanged");
		setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		cmd_searchTo();
		setCursor(Cursor.getDefaultCursor());
	}   //  valueChanged

	/**
	 *  Fill xMatchedTo
	 */
	private void cmd_searchTo()
	{
		int row = xMatchedTable.getSelectedRow();
		log.config("Row=" + row);

		double qty = 0.0;
		if (row < 0)
		{
			xMatchedToTable.setRowCount(0);
		}
		else
		{
			//  ** Create SQL **
			String displayString = (String)matchTo.getSelectedItem();
			int display = MATCH_INVOICE;
			if (displayString.equals(m_matchOptions[MATCH_SHIPMENT]))
				display = MATCH_SHIPMENT;
			else if (displayString.equals(m_matchOptions[MATCH_ORDER]))
				display = MATCH_ORDER;
			int matchToType = matchFrom.getSelectedIndex();
			tableInit (display, matchToType);	//	sets m_sql
			//  ** Add Where Clause **
			KeyNamePair BPartner = (KeyNamePair)xMatchedTable.getValueAt(row, I_BPartner);
			KeyNamePair Product = (KeyNamePair)xMatchedTable.getValueAt(row, I_Product);
			log.fine("BPartner=" + BPartner + " - Product=" + Product);
			//
			if (sameBPartner.isSelected())
				m_sql.append(" AND hdr.C_BPartner_ID=").append(BPartner.getKey());
			if (sameProduct.isSelected())
				m_sql.append(" AND lin.M_Product_ID=").append(Product.getKey());

			//  calculate qty
			double docQty = ((Double)xMatchedTable.getValueAt(row, I_QTY)).doubleValue();
			double matchedQty = ((Double)xMatchedTable.getValueAt(row, I_MATCHED)).doubleValue();
			qty = docQty - matchedQty;
			if (sameQty.isSelected())
				m_sql.append(" AND ").append(m_qtyColumn).append("=").append(docQty);
			//  ** Load Table **
			tableLoad (xMatchedToTable);
		}
		//  Display To be Matched Qty
		m_xMatched = new BigDecimal (qty);
		xMatched.setValue(m_xMatched);
		xMatchedTo.setValue(Env.ZERO);
		difference.setValue(m_xMatched);
		//  Status Info
		statusBar.setStatusLine(matchFrom.getSelectedItem().toString()
			+ "# = " + xMatchedTable.getRowCount() + " - "
			+ matchTo.getSelectedItem().toString()
			+  "# = " + xMatchedToTable.getRowCount(),
			xMatchedToTable.getRowCount() == 0);
		statusBar.setStatusDB(0);
	}   //  cmd_seachTo

	
	/***************************************************************************
	 *  Table Model Listener - calculate matchd Qty
	 *  @param e event
	 */
	public void tableChanged (TableModelEvent e)
	{
		if (e.getColumn() != 0)
			return;
		log.config("Row=" + e.getFirstRow() + "-" + e.getLastRow() + ", Col=" + e.getColumn()
			+ ", Type=" + e.getType());
		setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

		//  Matched From
		int matchedRow = xMatchedTable.getSelectedRow();
		KeyNamePair Product = (KeyNamePair)xMatchedTable.getValueAt(matchedRow, 5);

		//  Matched To
		double qty = 0.0;
		int noRows = 0;
		for (int row = 0; row < xMatchedToTable.getRowCount(); row++)
		{
			IDColumn id = (IDColumn)xMatchedToTable.getValueAt(row, 0);
			if (id != null && id.isSelected())
			{
				KeyNamePair ProductCompare = (KeyNamePair)xMatchedToTable.getValueAt(row, 5);
				if (Product.getKey() != ProductCompare.getKey())
				{
					id.setSelected(false);
				}
				else
				{
					if (matchMode.getSelectedIndex() == MODE_NOTMATCHED)
						qty += ((Double)xMatchedToTable.getValueAt(row, I_QTY)).doubleValue();  //  doc
					qty -= ((Double)xMatchedToTable.getValueAt(row, I_MATCHED)).doubleValue();  //  matched
					noRows++;
				}
			}
		}
		//  update qualtities
		m_xMatchedTo = new BigDecimal(qty);
		xMatchedTo.setValue(m_xMatchedTo);
		difference.setValue(m_xMatched.subtract(m_xMatchedTo));
		bProcess.setEnabled(noRows != 0);
		setCursor(Cursor.getDefaultCursor());
		//  Status
		statusBar.setStatusDB(noRows);
	}   //  tableChanged

	
	/**************************************************************************
	 *  Initialise Table access - create SQL, dateColumn.
	 *  <br>
	 *  The driving table is "hdr", e.g. for hdr.C_BPartner_ID=..
	 *  The line table is "lin", e.g. for lin.M_Product_ID=..
	 *  You use the dateColumn/qtyColumn variable directly as it is table specific.
	 *  <br>
	 *  The sql is dependent on MatchMode:
	 *  - If Matched - all (fully or partially) matched records are listed
	 *  - If Not Matched - all not fully matched records are listed
	 *  @param display (Invoice, Shipment, Order) see MATCH_*
	 *  @param matchToType (Invoice, Shipment, Order) see MATCH_*
	 */
	private void tableInit (int display, int matchToType)
	{
		boolean matched = matchMode.getSelectedIndex() == MODE_MATCHED;
		log.config("Display=" + m_matchOptions[display]
			+ ", MatchTo=" + m_matchOptions[matchToType]
			+ ", Matched=" + matched);

		m_sql = new StringBuffer ();
		if (display == MATCH_INVOICE)
		{
			m_dateColumn = "hdr.DateInvoiced";
			m_qtyColumn = "lin.QtyInvoiced";
			m_sql.append("SELECT hdr.C_Invoice_ID,hdr.DocumentNo, hdr.DateInvoiced, bp.Name,hdr.C_BPartner_ID,"
				+ " lin.Line,lin.C_InvoiceLine_ID, p.Name,lin.M_Product_ID,"
				+ " lin.QtyInvoiced,SUM(NVL(mi.Qty,0)) "
				+ "FROM C_Invoice hdr"
				+ " INNER JOIN C_BPartner bp ON (hdr.C_BPartner_ID=bp.C_BPartner_ID)"
				+ " INNER JOIN C_InvoiceLine lin ON (hdr.C_Invoice_ID=lin.C_Invoice_ID)"
				+ " INNER JOIN M_Product p ON (lin.M_Product_ID=p.M_Product_ID)"
				+ " INNER JOIN C_DocType dt ON (hdr.C_DocType_ID=dt.C_DocType_ID AND dt.DocBaseType IN ('API','APC'))"
				+ " FULL JOIN M_MatchInv mi ON (lin.C_InvoiceLine_ID=mi.C_InvoiceLine_ID) "
				+ "WHERE hdr.DocStatus IN ('CO','CL')");
			m_groupBy = " GROUP BY hdr.C_Invoice_ID,hdr.DocumentNo,hdr.DateInvoiced,bp.Name,hdr.C_BPartner_ID,"
				+ " lin.Line,lin.C_InvoiceLine_ID,p.Name,lin.M_Product_ID,lin.QtyInvoiced "
				+ "HAVING "
				+ (matched ? "0" : "lin.QtyInvoiced")
				+ "<>SUM(NVL(mi.Qty,0))";
		}
		else if (display == MATCH_ORDER)
		{
			m_dateColumn = "hdr.DateOrdered";
			m_qtyColumn = "lin.QtyOrdered";
			m_sql.append("SELECT hdr.C_Order_ID,hdr.DocumentNo, hdr.DateOrdered, bp.Name,hdr.C_BPartner_ID,"
				+ " lin.Line,lin.C_OrderLine_ID, p.Name,lin.M_Product_ID,"
				+ " lin.QtyOrdered,SUM(COALESCE(mo.Qty,0)) "
				+ "FROM C_Order hdr"
				+ " INNER JOIN C_BPartner bp ON (hdr.C_BPartner_ID=bp.C_BPartner_ID)"
				+ " INNER JOIN C_OrderLine lin ON (hdr.C_Order_ID=lin.C_Order_ID)"
				+ " INNER JOIN M_Product p ON (lin.M_Product_ID=p.M_Product_ID)"
				+ " INNER JOIN C_DocType dt ON (hdr.C_DocType_ID=dt.C_DocType_ID AND dt.DocBaseType='POO')"
				+ " FULL JOIN M_MatchPO mo ON (lin.C_OrderLine_ID=mo.C_OrderLine_ID) "
				+ " WHERE " ) ; //[ 1876972 ] Can't match partially matched PO with an unmatched receipt SOLVED BY BOJANA, AGENDA_GM
			m_linetype = new StringBuffer();
			m_linetype.append( matchToType == MATCH_SHIPMENT ? "M_InOutLine_ID" : "C_InvoiceLine_ID") ;
			if ( matched ) {
				m_sql.append( " mo." + m_linetype + " IS NOT NULL " ) ; 
			} else {
 				m_sql.append( " ( mo." + m_linetype + " IS NULL OR "
				+ " (lin.QtyOrdered <>  (SELECT sum(mo1.Qty) AS Qty" 
				+ " FROM m_matchpo mo1 WHERE "
				+ " mo1.C_ORDERLINE_ID=lin.C_ORDERLINE_ID AND "
				+ " hdr.C_ORDER_ID=lin.C_ORDER_ID AND "
				+ " mo1." + m_linetype
				+ " IS NOT NULL group by mo1.C_ORDERLINE_ID))) " );	
			}
			m_sql.append( " AND hdr.DocStatus IN ('CO','CL')" );
			m_groupBy = " GROUP BY hdr.C_Order_ID,hdr.DocumentNo,hdr.DateOrdered,bp.Name,hdr.C_BPartner_ID,"
				+ " lin.Line,lin.C_OrderLine_ID,p.Name,lin.M_Product_ID,lin.QtyOrdered "
				+ "HAVING "
				+ (matched ? "0" : "lin.QtyOrdered")
				+ "<>SUM(COALESCE(mo.Qty,0))";
		}
		else    //  Shipment
		{
			m_dateColumn = "hdr.MovementDate";
			m_qtyColumn = "lin.MovementQty";
			m_sql.append("SELECT hdr.M_InOut_ID,hdr.DocumentNo, hdr.MovementDate, bp.Name,hdr.C_BPartner_ID,"
				+ " lin.Line,lin.M_InOutLine_ID, p.Name,lin.M_Product_ID,"
				+ " lin.MovementQty,SUM(NVL(m.Qty,0)) "
				+ "FROM M_InOut hdr"
				+ " INNER JOIN C_BPartner bp ON (hdr.C_BPartner_ID=bp.C_BPartner_ID)"
				+ " INNER JOIN M_InOutLine lin ON (hdr.M_InOut_ID=lin.M_InOut_ID)"
				+ " INNER JOIN M_Product p ON (lin.M_Product_ID=p.M_Product_ID)"
				+ " INNER JOIN C_DocType dt ON (hdr.C_DocType_ID = dt.C_DocType_ID AND dt.DocBaseType='MMR')"
				+ " FULL JOIN ")
				.append(matchToType == MATCH_ORDER ? "M_MatchPO" : "M_MatchInv")
				.append(" m ON (lin.M_InOutLine_ID=m.M_InOutLine_ID) "
				+ "WHERE hdr.DocStatus IN ('CO','CL')");
			m_groupBy = " GROUP BY hdr.M_InOut_ID,hdr.DocumentNo,hdr.MovementDate,bp.Name,hdr.C_BPartner_ID,"
				+ " lin.Line,lin.M_InOutLine_ID,p.Name,lin.M_Product_ID,lin.MovementQty "
				+ "HAVING "
				+ (matched ? "0" : "lin.MovementQty")
				+ "<>SUM(NVL(m.Qty,0))";
		}
	//	Log.trace(7, "VMatch.tableInit", m_sql + "\n" + m_groupBy);
	}   //  tableInit


	/**
	 *  Fill the table using m_sql
	 *  @param table table
	 */
	private void tableLoad (MiniTable table)
	{
	//	log.finest(m_sql + " - " +  m_groupBy);
		String sql = MRole.getDefault().addAccessSQL(
			m_sql.toString(), "hdr", MRole.SQL_FULLYQUALIFIED, MRole.SQL_RO)
			+ m_groupBy;
		log.finest(sql);
		try
		{
			Statement stmt = DB.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			table.loadTable(rs);
			stmt.close();
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql, e);
		}
	}   //  tableLoad

	/**
	 *  Create Matching Record
	 *  @param invoice true if matching invoice false if matching PO
	 *  @param M_InOutLine_ID shipment line
	 *  @param Line_ID C_InvoiceLine_ID or C_OrderLine_ID
	 *  @param qty quantity
	 *  @return true if created
	 */
	private boolean createMatchRecord (boolean invoice, int M_InOutLine_ID, int Line_ID,
		BigDecimal qty)
	{
		if (qty.compareTo(Env.ZERO) == 0)
			return true;
		log.fine("IsInvoice=" + invoice
			+ ", M_InOutLine_ID=" + M_InOutLine_ID + ", Line_ID=" + Line_ID
			+ ", Qty=" + qty);
		//
		boolean success = false;
		MInOutLine sLine = new MInOutLine (Env.getCtx(), M_InOutLine_ID, null);
		if (invoice)	//	Shipment - Invoice
		{
			//	Update Invoice Line
			MInvoiceLine iLine = new MInvoiceLine (Env.getCtx(), Line_ID, null);
			iLine.setM_InOutLine_ID(M_InOutLine_ID);
			if (sLine.getC_OrderLine_ID() != 0)
				iLine.setC_OrderLine_ID(sLine.getC_OrderLine_ID());
			iLine.save();
			//	Create Shipment - Invoice Link
			if (iLine.getM_Product_ID() != 0)
			{
				MMatchInv match = new MMatchInv (iLine, null, qty);
				match.setM_InOutLine_ID(M_InOutLine_ID);
				if (match.save())
					success = true;
				else
					log.log(Level.SEVERE, "Inv Match not created: " + match);
			}
			else
				success = true;
			//	Create PO - Invoice Link = corrects PO
			if (iLine.getC_OrderLine_ID() != 0 && iLine.getM_Product_ID() != 0)
			{
				MMatchPO matchPO = MMatchPO.create(iLine, sLine, null, qty);
				matchPO.setC_InvoiceLine_ID(iLine);
				matchPO.setM_InOutLine_ID(M_InOutLine_ID);
				if (!matchPO.save())
					log.log(Level.SEVERE, "PO(Inv) Match not created: " + matchPO);
			}
		}
		else	//	Shipment - Order
		{
			//	Update Shipment Line
			sLine.setC_OrderLine_ID(Line_ID);
			sLine.save();
			//	Update Order Line
			MOrderLine oLine = new MOrderLine(Env.getCtx(), Line_ID, null);
			if (oLine.get_ID() != 0)	//	other in MInOut.completeIt
			{
				oLine.setQtyReserved(oLine.getQtyReserved().subtract(qty));
				if(!oLine.save())
					log.severe("QtyReserved not updated - C_OrderLine_ID=" + Line_ID);
			}

			//	Create PO - Shipment Link
			if (sLine.getM_Product_ID() != 0)
			{
				MMatchPO match = new MMatchPO (sLine, null, qty);
				if (!match.save())
					log.log(Level.SEVERE, "PO Match not created: " + match);
				else
				{
					success = true;
					//	Correct Ordered Qty for Stocked Products (see MOrder.reserveStock / MInOut.processIt)
					if (sLine.getProduct() != null && sLine.getProduct().isStocked())
						success = MStorage.add (Env.getCtx(), sLine.getM_Warehouse_ID(), 
							sLine.getM_Locator_ID(), 
							sLine.getM_Product_ID(), 
							sLine.getM_AttributeSetInstance_ID(), oLine.getM_AttributeSetInstance_ID(), 
							null, null, qty.negate(), null);
				}
			}
			else
				success = true;
		}
		return success;
	}   //  createMatchRecord

}   //  VMatch
