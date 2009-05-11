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
package org.adempiere.webui.apps.form;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;

import org.adempiere.webui.apps.AEnv;
import org.adempiere.webui.component.Button;
import org.adempiere.webui.component.Checkbox;
import org.adempiere.webui.component.Combobox;
import org.adempiere.webui.component.ConfirmPanel;
import org.adempiere.webui.component.Grid;
import org.adempiere.webui.component.GridFactory;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.ListModelTable;
import org.adempiere.webui.component.Listbox;
import org.adempiere.webui.component.ListboxFactory;
import org.adempiere.webui.component.Panel;
import org.adempiere.webui.component.Row;
import org.adempiere.webui.component.Rows;
import org.adempiere.webui.component.WAppsAction;
import org.adempiere.webui.component.WListbox;
import org.adempiere.webui.component.Window;
import org.adempiere.webui.editor.WEditor;
import org.adempiere.webui.editor.WLocatorEditor;
import org.adempiere.webui.editor.WSearchEditor;
import org.adempiere.webui.editor.WStringEditor;
import org.adempiere.webui.event.WTableModelEvent;
import org.adempiere.webui.event.WTableModelListener;
import org.adempiere.webui.panel.StatusBarPanel;
import org.compiere.grid.ICreateFrom;
import org.compiere.model.GridTab;
import org.compiere.model.MLookup;
import org.compiere.model.MLookupFactory;
import org.compiere.model.MOrder;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Msg;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zkex.zul.Borderlayout;
import org.zkoss.zkex.zul.Center;
import org.zkoss.zkex.zul.North;
import org.zkoss.zkex.zul.South;
import org.zkoss.zul.Separator;
import org.zkoss.zul.Space;

/**
 *  CreateFrom (Called from GridController.startProcess)
 *
 *  @author  Jorg Janke
 *  @version $Id: VCreateFrom.java,v 1.4 2006/10/11 09:52:23 comdivision Exp $
 *
 * @author Teo Sarca, SC ARHIPAC SERVICE SRL
 * 			<li>FR [ 1794050 ] Usability: VCreateFrom OK button always enabled
 * @author Victor Perez, e-Evolucion 
 *          <li> RF [1811114] http://sourceforge.net/tracker/index.php?func=detail&aid=1811114&group_id=176962&atid=879335
 * @author Karsten Thiemann, Schaeffer AG
 * 			<li>Bug [ 1759431 ] Problems with VCreateFrom
 */
public abstract class WCreateFrom extends Window
	implements ICreateFrom, EventListener, WTableModelListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 15158515174358003L;
	/**
	 *  Factory - called from APanel
	 *  @param  mTab        Model Tab for the trx
	 *  @return JDialog
	 */
	public static WCreateFrom create (GridTab mTab)
	{
		//	dynamic init preparation
		int AD_Table_ID = Env.getContextAsInt(Env.getCtx(), mTab.getWindowNo(), "BaseTable_ID");

		WCreateFrom retValue = null;
		if (AD_Table_ID == 392)             //  C_BankStatement
			retValue = new WCreateFromStatement (mTab);
		else if (AD_Table_ID == 319)        //  M_InOut
			retValue = new WCreateFromShipment (mTab);
		else if (AD_Table_ID == 426)		//	C_PaySelection
			return null;	//	ignore - will call process C_PaySelection_CreateFrom
		/**
		 * Modification to support create Lines from for RMA
		 * @author ashley
		 */
		else if (AD_Table_ID == 661)
			retValue = new WCreateFromRMA(mTab); // RMA
		else    //  Not supported CreateFrom
		{
			s_log.info("Unsupported AD_Table_ID=" + AD_Table_ID);
			return null;
		}
		return retValue;
	}   //  create

	
	/**************************************************************************
	 *  Protected super class Constructor
	 *  @param mTab MTab
	 */
	WCreateFrom (GridTab mTab)
	{
		super();
		this.setAttribute("mode", "modal");
		log.info(mTab.toString());
		p_WindowNo = mTab.getWindowNo();
		p_mTab = mTab;

		try
		{
			if (!dynInit())
				return;
			zkInit();
			confirmPanel.addActionListener(this);
			//  Set status
			statusBar.setStatusDB("");
			tableChanged(null);
			p_initOK = true;
		}
		catch(Exception e)
		{
			log.log(Level.SEVERE, "", e);
			p_initOK = false;
		}
		AEnv.showWindow(this);
	}   //  VCreateFrom

	/** Window No               */
	protected int               p_WindowNo;
	/** Model Tab               */
	protected GridTab         		p_mTab;

	private boolean             p_initOK = false;

	/** Loaded Order            */
	protected MOrder 			p_order = null;
	/**	Logger			*/
	protected CLogger 		log = CLogger.getCLogger(getClass());
	/**	Static Logger	*/
	private static CLogger 	s_log = CLogger.getCLogger (WCreateFrom.class);
	
	//
	protected Borderlayout contentPane = new Borderlayout();
	protected Panel parameterPanel = new Panel();
	protected Panel parameterBankPanel = new Panel();
	protected Borderlayout parameterLayout = new Borderlayout();
	protected Label bankAccountLabel = new Label();
	protected Panel parameterStdPanel = new Panel();
	protected Label bPartnerLabel = new Label();
	protected Combobox bankAccountField;
	//RF [1811114]
	protected Label authorizationLabel = new Label();
	protected WStringEditor authorizationField = new WStringEditor();
	protected Grid parameterStdLayout = GridFactory.newGridLayout();
	protected Grid parameterBankLayout = GridFactory.newGridLayout();
	// Bug [1759431]
	protected Checkbox sameWarehouseCb = new Checkbox();
	protected WEditor bPartnerField;
	protected Label orderLabel = new Label();
	protected Listbox orderField = ListboxFactory.newDropdownListbox();
	protected Label invoiceLabel = new Label();
	protected Listbox invoiceField = ListboxFactory.newDropdownListbox();
	protected Label shipmentLabel = new Label();
	protected Listbox shipmentField = ListboxFactory.newDropdownListbox();
//	private JScrollPane dataPane = new JScrollPane();
	protected Panel southPanel = new Panel();
//	private Borderlayout southLayout = new Borderlayout();
	protected ConfirmPanel confirmPanel = new ConfirmPanel(true);
	protected StatusBarPanel statusBar = new StatusBarPanel();
	protected WListbox dataTable = ListboxFactory.newDataTable();
	protected Label locatorLabel = new Label();
	protected WLocatorEditor locatorField = new WLocatorEditor();
	public static final String SELECT_ALL = "SelectAll";
//	public static final String SELECT_ALL_TOOLTIP = "Select all records";	
    
    /** Label for the rma selection */
    protected Label rmaLabel = new Label();
    /** Combo box for selecting RMA document */
    protected Listbox rmaField = ListboxFactory.newDropdownListbox();
	/**
	 *  Static Init.
	 *  <pre>
	 *  parameterPanel
	 *      parameterBankPanel
	 *      parameterStdPanel
	 *          bPartner/order/invoice/shopment/licator Label/Field
	 *  dataPane
	 *  southPanel
	 *      confirmPanel
	 *      statusBar
	 *  </pre>
	 *  @throws Exception
	 */
	protected void zkInit() throws Exception
	{
		parameterPanel.appendChild(parameterLayout);
		parameterStdPanel.appendChild(parameterStdLayout);
		parameterBankPanel.appendChild(parameterBankLayout);
		//
		bankAccountLabel.setText(Msg.translate(Env.getCtx(), "C_BankAccount_ID"));
	    //RF [1811114]
		authorizationLabel.setText(Msg.translate(Env.getCtx(), "R_AuthCode"));
		bPartnerLabel.setText(Msg.getElement(Env.getCtx(), "C_BPartner_ID"));
		orderLabel.setText(Msg.getElement(Env.getCtx(), "C_Order_ID", false));
		invoiceLabel.setText(Msg.getElement(Env.getCtx(), "C_Invoice_ID", false));
		shipmentLabel.setText(Msg.getElement(Env.getCtx(), "M_InOut_ID", false));
		locatorLabel.setText(Msg.translate(Env.getCtx(), "M_Locator_ID"));
        rmaLabel.setText(Msg.translate(Env.getCtx(), "M_RMA_ID"));
        sameWarehouseCb.setText(Msg.getMsg(Env.getCtx(), "FromSameWarehouseOnly", true));
        sameWarehouseCb.setTooltiptext(Msg.getMsg(Env.getCtx(), "FromSameWarehouseOnly", false));
        
		//
        North north = new North();
        this.appendChild(contentPane);
        contentPane.appendChild(north);
        north.appendChild(parameterPanel);
        parameterPanel.appendChild(parameterLayout);
        north = new North();
        parameterLayout.appendChild(north);
		north.appendChild(parameterBankPanel);
		
		parameterLayout.setHeight("110px");
		parameterLayout.setWidth("100%");
		
		parameterBankPanel.appendChild(parameterBankLayout);
	    //RF [1811114]
		Rows rows = (Rows) parameterBankLayout.newRows();		
		Row row = rows.newRow();
		row.appendChild(bankAccountLabel.rightAlign());
		if (bankAccountField != null)
			row.appendChild(bankAccountField);
		row.appendChild(authorizationLabel.rightAlign());
		row.appendChild(authorizationField.getComponent());
		
		Center center = new Center();
		parameterLayout.appendChild(center);
		center.appendChild(parameterStdPanel);
		
		parameterStdPanel.appendChild(parameterStdLayout);
		rows = (Rows) parameterStdLayout.newRows();
		row = rows.newRow();
		row.appendChild(bPartnerLabel.rightAlign());
		if (bPartnerField != null)
			row.appendChild(bPartnerField.getComponent());
		row.appendChild(orderLabel.rightAlign());
		row.appendChild(orderField);
		
		row = rows.newRow();
		row.appendChild(locatorLabel.rightAlign());
		row.appendChild(locatorField.getComponent());
		row.appendChild(invoiceLabel.rightAlign());
		row.appendChild(invoiceField);
		
		row = rows.newRow();
		row.appendChild(new Space());
		row.appendChild(sameWarehouseCb);
		row.appendChild(shipmentLabel.rightAlign());
		row.appendChild(shipmentField);				
        
        // Add RMA document selection to panel
		row = rows.newRow();
		row.appendChild(new Space());
		row.appendChild(new Space());
        row.appendChild(rmaLabel.rightAlign());
        row.appendChild(rmaField);
        
        center = new Center();
        contentPane.appendChild(center);
		center.appendChild(dataTable);
		//
 		//
		// @Trifon
		WAppsAction selectAllAction = new WAppsAction (SELECT_ALL, null, null);
		
		Button selectAllButton = selectAllAction.getButton();
		confirmPanel.addComponentsLeft(selectAllButton);
		selectAllButton.addActionListener(this);
		//
		South south = new South();
		contentPane.appendChild(south);
		south.appendChild(southPanel);
		southPanel.appendChild(new Separator());
		southPanel.appendChild(confirmPanel);
		// Trifon End
		southPanel.appendChild(new Separator());
		southPanel.appendChild(statusBar);
		
		this.setWidth("750px");
		this.setHeight("550px");
		this.setSizable(true);
		this.setBorder("normal");
		contentPane.setWidth("100%");
		contentPane.setHeight("100%");		
	}   //  jbInit

	/**
	 *	Init OK to be able to make changes?
	 *  @return on if initialized
	 */
	public boolean isInitOK()
	{
		return p_initOK;
	}	//	isInitOK

	/**
	 *  Dynamic Init
	 *  @throws Exception if Lookups cannot be initialized
	 *  @return true if initialized
	 */
	abstract boolean dynInit() throws Exception;

	/**
	 *  Init Business Partner Details
	 *  @param C_BPartner_ID BPartner
	 */
	abstract void initBPDetails(int C_BPartner_ID);

	/**
	 *  Add Info
	 */
	abstract void info();

	/**
	 *  Save & Insert Data
	 *  @return true if saved
	 */
	abstract boolean save();

	/*************************************************************************/

	/**
	 *  Action Listener
	 *  @param e event
	 * @throws Exception 
	 */
	public void onEvent(Event e) throws Exception
	{
		log.config("Action=" + e.getTarget().getId());
	//	if (m_action)
	//		return;
	//	m_action = true;

		//  OK - Save
		if (e.getTarget().getId().equals(ConfirmPanel.A_OK))
		{
			if (save())
				dispose();
		}
		//  Cancel
		else if (e.getTarget().getId().equals(ConfirmPanel.A_CANCEL))
		{
			dispose();
		}
		// Select All
		// Trifon
		else if (e.getTarget().getId().equals(SELECT_ALL)) {
			ListModelTable model = dataTable.getModel();
			int rows = model.getSize();
			for (int i = 0; i < rows; i++)
			{
				model.setValueAt(new Boolean(true), i, 0);
			}
			//refresh
			dataTable.setModel(model);
			info();
		}
	//	m_action = false;
	}   //  actionPerformed

	/**
	 *  Table Model Listener
	 *  @param e event
	 */
	public void tableChanged (WTableModelEvent e)
	{
		int type = -1;
		if (e != null)
		{
			type = e.getType();
			if (type != WTableModelEvent.CONTENTS_CHANGED)
				return;
		}
		log.config("Type=" + type);
		info();
	}   //  tableChanged

	
	/**************************************************************************
	 *  Load BPartner Field
	 *  @param forInvoice true if Invoices are to be created, false receipts
	 *  @throws Exception if Lookups cannot be initialized
	 */
	protected void initBPartner (boolean forInvoice) throws Exception
	{
		//  load BPartner
		int AD_Column_ID = 3499;        //  C_Invoice.C_BPartner_ID
		MLookup lookup = MLookupFactory.get (Env.getCtx(), p_WindowNo, 0, AD_Column_ID, DisplayType.Search);
		bPartnerField = new WSearchEditor ("C_BPartner_ID", true, false, true, lookup);
		//
		int C_BPartner_ID = Env.getContextAsInt(Env.getCtx(), p_WindowNo, "C_BPartner_ID");
		bPartnerField.setValue(new Integer(C_BPartner_ID));

		//  initial loading
		initBPartnerOIS(C_BPartner_ID, forInvoice);
	}   //  initBPartner

	/**
	 *  Load PBartner dependent Order/Invoice/Shipment Field.
	 *  @param C_BPartner_ID BPartner
	 *  @param forInvoice for invoice
	 */
	protected void initBPartnerOIS (int C_BPartner_ID, boolean forInvoice)
	{
		log.config("C_BPartner_ID=" + C_BPartner_ID);
		KeyNamePair pp = new KeyNamePair(0,"");
		boolean sameWarehouseOnly = sameWarehouseCb.isVisible() && sameWarehouseCb.isSelected();
		//  load PO Orders - Closed, Completed
		orderField.removeActionListener(this);
		orderField.removeAllItems();
		orderField.addItem(pp);
		//	Display
		StringBuffer display = new StringBuffer("o.DocumentNo||' - ' ||")
			.append(DB.TO_CHAR("o.DateOrdered", DisplayType.Date, Env.getAD_Language(Env.getCtx())))
			.append("||' - '||")
			.append(DB.TO_CHAR("o.GrandTotal", DisplayType.Amount, Env.getAD_Language(Env.getCtx())));
		//
		String column = "ol.QtyDelivered";
		if (forInvoice)
			column = "ol.QtyInvoiced";
		StringBuffer sql = new StringBuffer("SELECT o.C_Order_ID,").append(display)
			.append(" FROM C_Order o "
			+ "WHERE o.C_BPartner_ID=? AND o.IsSOTrx='N' AND o.DocStatus IN ('CL','CO')"
			+ " AND o.C_Order_ID IN "
				  + "(SELECT ol.C_Order_ID FROM C_OrderLine ol"
				  + " WHERE ol.QtyOrdered - ").append(column).append(" != 0) ");
		if(sameWarehouseOnly) {
			sql = sql.append(" AND o.M_Warehouse_ID=? ");
		}
		sql = sql.append("ORDER BY o.DateOrdered");
		try
		{
			PreparedStatement pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, C_BPartner_ID);
			if(sameWarehouseOnly) {
				//only active for material receipts
				pstmt.setInt(2, Env.getContextAsInt(Env.getCtx(), p_WindowNo, "M_Warehouse_ID"));
			}
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
			{
				pp = new KeyNamePair(rs.getInt(1), rs.getString(2));
				orderField.addItem(pp);
			}
			rs.close();
			pstmt.close();
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql.toString(), e);
		}
		orderField.setSelectedIndex(0);
		orderField.addActionListener(this);

		initBPDetails(C_BPartner_ID);
	}   //  initBPartnerOIS

	/**
	 *  Load Data - Order
	 *  @param C_Order_ID Order
	 *  @param forInvoice true if for invoice vs. delivery qty
	 */
	protected void loadOrder (int C_Order_ID, boolean forInvoice)
	{
		/**
		 *  Selected        - 0
		 *  Qty             - 1
		 *  C_UOM_ID        - 2
		 *  M_Product_ID    - 3
		 *  VendorProductNo - 4
		 *  OrderLine       - 5
		 *  ShipmentLine    - 6
		 *  InvoiceLine     - 7
		 */
		log.config("C_Order_ID=" + C_Order_ID);
		p_order = new MOrder (Env.getCtx(), C_Order_ID, null);      //  save

		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		StringBuffer sql = new StringBuffer("SELECT "
			+ "l.QtyOrdered-SUM(COALESCE(m.Qty,0)),"					//	1
			+ "CASE WHEN l.QtyOrdered=0 THEN 0 ELSE l.QtyEntered/l.QtyOrdered END,"	//	2
			+ " l.C_UOM_ID,COALESCE(uom.UOMSymbol,uom.Name),"			//	3..4
			+ " COALESCE(l.M_Product_ID,0),COALESCE(p.Name,c.Name),po.VendorProductNo,"	//	5..7
			+ " l.C_OrderLine_ID,l.Line "								//	8..9
			+ "FROM C_OrderLine l"
			+ " LEFT OUTER JOIN M_Product_PO po ON (l.M_Product_ID = po.M_Product_ID AND l.C_BPartner_ID = po.C_BPartner_ID) "
			+ " LEFT OUTER JOIN M_MatchPO m ON (l.C_OrderLine_ID=m.C_OrderLine_ID AND ");
		sql.append(forInvoice ? "m.C_InvoiceLine_ID" : "m.M_InOutLine_ID");
		sql.append(" IS NOT NULL)")
			.append(" LEFT OUTER JOIN M_Product p ON (l.M_Product_ID=p.M_Product_ID)"
			+ " LEFT OUTER JOIN C_Charge c ON (l.C_Charge_ID=c.C_Charge_ID)");
		if (Env.isBaseLanguage(Env.getCtx(), "C_UOM"))
			sql.append(" LEFT OUTER JOIN C_UOM uom ON (l.C_UOM_ID=uom.C_UOM_ID)");
		else
			sql.append(" LEFT OUTER JOIN C_UOM_Trl uom ON (l.C_UOM_ID=uom.C_UOM_ID AND uom.AD_Language='")
				.append(Env.getAD_Language(Env.getCtx())).append("')");
		//
		sql.append(" WHERE l.C_Order_ID=? "			//	#1
			+ "GROUP BY l.QtyOrdered,CASE WHEN l.QtyOrdered=0 THEN 0 ELSE l.QtyEntered/l.QtyOrdered END, "
			+ "l.C_UOM_ID,COALESCE(uom.UOMSymbol,uom.Name),po.VendorProductNo, "
				+ "l.M_Product_ID,COALESCE(p.Name,c.Name), l.Line,l.C_OrderLine_ID "
			+ "ORDER BY l.Line");
		//
		log.finer(sql.toString());
		try
		{
			PreparedStatement pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, C_Order_ID);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
			{
				Vector<Object> line = new Vector<Object>();
				line.add(new Boolean(false));           //  0-Selection
				BigDecimal qtyOrdered = rs.getBigDecimal(1);
				BigDecimal multiplier = rs.getBigDecimal(2);
				BigDecimal qtyEntered = qtyOrdered.multiply(multiplier);
				line.add(new Double(qtyEntered.doubleValue()));  //  1-Qty
				KeyNamePair pp = new KeyNamePair(rs.getInt(3), rs.getString(4).trim());
				line.add(pp);                           //  2-UOM
				pp = new KeyNamePair(rs.getInt(5), rs.getString(6));
				line.add(pp);                           //  3-Product
				line.add(rs.getString(7));				// 4-VendorProductNo
				pp = new KeyNamePair(rs.getInt(8), rs.getString(9));
				line.add(pp);                           //  5-OrderLine
				line.add(null);                         //  6-Ship
				line.add(null);                         //  7-Invoice
				data.add(line);
			}
			rs.close();
			pstmt.close();
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, sql.toString(), e);
		}
		loadTableOIS (data);
	}   //  LoadOrder


	/**
	 *  Load Order/Invoice/Shipment data into Table
	 *  @param data data
	 */
	protected void loadTableOIS (Vector<Vector<Object>> data)
	{
		dataTable.clear();
		//  Header Info
		Vector<String> columnNames = new Vector<String>(7);
		columnNames.add(Msg.getMsg(Env.getCtx(), "Select"));
		columnNames.add(Msg.translate(Env.getCtx(), "Quantity"));
		columnNames.add(Msg.translate(Env.getCtx(), "C_UOM_ID"));
		columnNames.add(Msg.translate(Env.getCtx(), "M_Product_ID"));
		columnNames.add(Msg.getElement(Env.getCtx(), "VendorProductNo", false));
		columnNames.add(Msg.getElement(Env.getCtx(), "C_Order_ID", false));
		columnNames.add(Msg.getElement(Env.getCtx(), "M_InOut_ID", false));
		columnNames.add(Msg.getElement(Env.getCtx(), "C_Invoice_ID", false));

		//  Remove previous listeners
		dataTable.getModel().removeTableModelListener(this);
		//  Set Model
		ListModelTable model = new ListModelTable(data);
		model.addTableModelListener(this);
		dataTable.setData(model, columnNames);
		//
		dataTable.setColumnClass(0, Boolean.class, false);      //  0-Selection
		dataTable.setColumnClass(1, Double.class, true);        //  1-Qty
		dataTable.setColumnClass(2, String.class, true);        //  2-UOM
		dataTable.setColumnClass(3, String.class, true);        //  3-Product
		dataTable.setColumnClass(4, String.class, true);        //  4-VendorProductNo
		dataTable.setColumnClass(5, String.class, true);        //  5-Order
		dataTable.setColumnClass(6, String.class, true);        //  6-Ship
		dataTable.setColumnClass(7, String.class, true);        //  7-Invoice
		//  Table UI
		dataTable.autoSize();
	}   //  loadOrder

	/**
	 * Set form status line.
	 * Please note, will enable/disable the OK button if the selectedRowCount > 0.
	 * @param selectedRowCount number of selected lines
	 * @param text additional text
	 */
	protected void setStatusLine(int selectedRowCount, String text) {
		StringBuffer sb = new StringBuffer(String.valueOf(selectedRowCount));
		if (text != null && text.trim().length() > 0) {
			sb.append(" - ").append(text);
		}
		statusBar.setStatusLine(sb.toString());
		//
		confirmPanel.getOKButton().setEnabled(selectedRowCount > 0);
	}

	public void showWindow()
	{
		setVisible(true);
	}
	
	public void closeWindow()
	{
		dispose();
	}
}   //  VCreateFrom
