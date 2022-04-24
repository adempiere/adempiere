/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
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
 * Copyright (C) 2003-2010 e-Evolution,SC. All Rights Reserved.               *
 * Contributor(s): Victor Perez www.e-evolution.com                           *
 *****************************************************************************/
package org.eevolution.form;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import org.adempiere.exceptions.DBException;
import org.adempiere.plaf.AdempierePLAF;
import org.compiere.apps.AEnv;
import org.compiere.apps.ALayout;
import org.compiere.apps.ALayoutConstraint;
import org.compiere.apps.AWindow;
import org.compiere.apps.ConfirmPanel;
import org.compiere.apps.StatusBar;
import org.compiere.apps.form.FormFrame;
import org.compiere.apps.form.FormPanel;
import org.compiere.apps.search.PAttributeInstance;
import org.compiere.grid.ed.VCheckBox;
import org.compiere.grid.ed.VDate;
import org.compiere.grid.ed.VLookup;
import org.compiere.grid.ed.VNumber;
import org.compiere.minigrid.ColumnInfo;
import org.compiere.minigrid.IDColumn;
import org.compiere.minigrid.MiniTable;
import org.compiere.model.I_C_Order;
import org.compiere.model.MColumn;
import org.compiere.model.MLookup;
import org.compiere.model.MLookupFactory;
import org.compiere.model.MLot;
import org.compiere.model.MProduct;
import org.compiere.model.MQuery;
import org.compiere.model.MRefList;
import org.compiere.model.MResource;
import org.compiere.model.MRole;
import org.compiere.model.MStorage;
import org.compiere.model.MUOM;
import org.compiere.model.MWarehouse;
import org.compiere.swing.CButton;
import org.compiere.swing.CLabel;
import org.compiere.swing.CPanel;
import org.compiere.swing.CTextField;
import org.compiere.util.ASyncProcess;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Language;
import org.compiere.util.Msg;
import org.eevolution.model.MPPMRP;
import org.eevolution.model.MPPOrder;
import org.eevolution.model.MPPProductPlanning;
import org.eevolution.model.X_PP_MRP;
import org.eevolution.model.X_PP_Product_Planning;

/**
 * VMRPDetailed
 * 
 * @author victor.perez@e-evolution.com, www.e-evolution.com
 * @author Teo Sarca, SC ARHIPAC SERVICE SRL
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<li> FR [ 114 ] Change "Create From" UI for Form like Dialog in window without "hardcode"
 *		@see https://github.com/adempiere/adempiere/issues/114
 */
public class VMRPDetailed extends MRPDetailed implements FormPanel,
		ActionListener, VetoableChangeListener, ChangeListener,
		ListSelectionListener, TableModelListener, ASyncProcess {
	/**
	 * Worker
	 */
	class Worker extends Thread {

		@Override
		public void run() {
			log.fine("Info.Worker.run");
			panel.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			// Clear Table
			p_table.setRowCount(0);
			//
			StringBuffer sql = new StringBuffer(m_sqlMain);
			String dynWhere = getWhereClause(getSQLWhere());
			if (dynWhere.length() > 0) {
				sql.append(dynWhere); // includes first AND
			}
			sql.append(m_sqlAdd);
			StringBuilder sqlFinal = new StringBuilder (MRole.getDefault().addAccessSQL(Msg.parseTranslation(getCtx(), sql.toString()), getTableName(),
					MRole.SQL_FULLYQUALIFIED, MRole.SQL_RO));
			try {
				PreparedStatement pstmt = DB.prepareStatement(sqlFinal.toString(), null);
				log.fine("SQL=" + sqlFinal);
				setParameters(pstmt, false);
				// Log.trace(Log.l6_Database, "Info.Worker.run - start query");
				ResultSet rs = pstmt.executeQuery();
				// Log.trace(Log.l6_Database, "Info.Worker.run - end query");
				while (!isInterrupted() & rs.next()) {
					int row = p_table.getRowCount();
					p_table.setRowCount(row + 1);
					int colOffset = 1; // columns start with 1
					for (int col = 0; col < p_layout.length; col++) {
						Object data = null;
						Class<?> c = p_layout[col].getColClass();
						int colIndex = col + colOffset;
						if (c == IDColumn.class) {

							IDColumn id = new IDColumn(rs.getInt(colIndex));
							id.setSelected(true);
							data = id;
							p_table.setColumnReadOnly(0, false);
						} else if (c == Boolean.class)
							data = Boolean.valueOf("Y".equals(rs.getString(colIndex)));
						else if (c == Timestamp.class)
							data = rs.getTimestamp(colIndex);
						else if (c == BigDecimal.class)
							data = rs.getBigDecimal(colIndex);
						else if (c == Double.class)
							data = new Double(rs.getDouble(colIndex));
						else if (c == Integer.class)
							data = Integer.valueOf(rs.getInt(colIndex));
						else if (c == KeyNamePair.class) {
							String display = rs.getString(colIndex);
							int key = rs.getInt(colIndex + 1);
							data = new KeyNamePair(key, display);
							colOffset++;
						} else
							data = rs.getString(colIndex);
						// store
						p_table.setValueAt(data, row, col);
					}
				}
				log.fine("Info.Worker.run - interrupted=" + isInterrupted());
				rs.close();
				pstmt.close();
			} catch (SQLException e) {
				log.log(Level.SEVERE, "Info.Worker.run - " + sqlFinal, e);
			}

			p_table.autoSize();
			panel.setCursor(Cursor.getDefaultCursor());

			// 00 "+getTableName()+".PP_MRP_ID"
			// 01 Value,
			// "(Select Value from M_Product p where p.M_Product_ID="+getTableName()+".M_Product_ID)",
			// String.class)
			// 02 Name,
			// "(Select Name from M_Product p where p.M_Product_ID="+getTableName()+".M_Product_ID)",
			// String.class),
			// 03 Resource", "(Select Name from S_Resource sr where
			// sr.S_Resource_ID="+getTableName()+".S_Resource_ID)",
			// String.class),
			// 04 Warehouse", "(Select Name from M_Warehouse wh where
			// wh.M_Warehouse_ID="+getTableName()+".M_Warehouse_ID)",
			// String.class),
			// 05 DatePromised, ""+getTableName()+".DatePromised",
			// Timestamp.class),
			// 06 Gross Reqs."), "(SELECT m.Qty FROM PP_MRP m WHERE
			// m.TypeMRP='D' AND m.PP_MRP_ID="+getTableName()+".PP_MRP_ID)",
			// BigDecimal.class),
			// 07 Schedule Reciept."), "(SELECT m.Qty FROM PP_MRP m WHERE
			// m.TypeMRP='S' AND m.DocStatus IN ('IP', 'CO') AND
			// m.PP_MRP_ID="+getTableName()+".PP_MRP_ID)", BigDecimal.class),
			// 08 Plan Orders"), "(SELECT m.Qty FROM PP_MRP m WHERE
			// m.TypeMRP='S' AND m.DocStatus = 'DR' AND
			// m.PP_MRP_ID="+getTableName()+".PP_MRP_ID)", BigDecimal.class),
			// 09 Proj QOH"), "bomQtyOnHand( "+getTableName()+".M_Product_ID ,
			// "+getTableName()+".M_Warehouse_ID, 0)", BigDecimal.class),
			// 10 Details"), ""+getTableName()+".Type", String.class),
			// 11 Type"), ""+getTableName()+".TypeMRP", String.class),
			// 12 DocumentNo"), "documentNo("+getTableName()+".PP_MRP_ID)",
			// String.class),
			// 13 DocStatus"), ""+getTableName()+".DocStatus", String.class),
			// 14 DateStartSchedule"), ""+getTableName()+".DateStartSchedule",
			// Timestamp.class),
			// 15 C_BPartner_ID"), "(SELECT cb.Name FROM C_BPartner cb WHERE
			// cb.C_BPartner_ID="+getTableName()+".C_BPartner_ID)",
			// String.class)
			if (getM_Product_ID() > 0) {
				BigDecimal OnHand = getQtyOnHand();
				for (int row = 0; row < p_table.getRowCount(); row++) {
					Timestamp datepromised = (Timestamp) p_table.getValueAt(
							row, 5);
					Timestamp today = new Timestamp(System.currentTimeMillis());
					IDColumn id = (IDColumn) p_table.getValueAt(row, 0);
					String TypeMRP = DB.getSQLValueString(null,
							"SELECT TypeMRP FROM " + getTableName()
									+ " WHERE PP_MRP_ID=?", id.getRecord_ID());
					String OrderType = (String) p_table.getValueAt(row, 11);
					if (X_PP_MRP.TYPEMRP_Demand.equals(TypeMRP)
							|| (X_PP_MRP.ORDERTYPE_Forecast.equals(OrderType) // TODO:
																			// arhipac:
																			// teo_sarca:
																			// is
																			// this
																			// ok,
																			// since
																			// gross
																			// req
																			// =
																			// sum
																			// of
																			// all
																			// demands
																			// ???
							&& datepromised.after(today))) {
						BigDecimal QtyGrossReqs = (BigDecimal) p_table
								.getValueAt(row, 6);
						OnHand = OnHand.subtract(QtyGrossReqs);
						p_table.setValueAt(OnHand, row, 9);
					}
					if (X_PP_MRP.TYPEMRP_Supply.equals(TypeMRP)) {
						BigDecimal QtyScheduledReceipts = (BigDecimal) p_table
								.getValueAt(row, 7);
						BigDecimal QtyPlan = (BigDecimal) p_table.getValueAt(
								row, 8);
						if (QtyPlan == null)
							QtyPlan = Env.ZERO;
						if (QtyScheduledReceipts == null)
							QtyScheduledReceipts = Env.ZERO;
						OnHand = OnHand.add(QtyScheduledReceipts.add(QtyPlan));
						p_table.setValueAt(OnHand, row, 9);
					}
				}
			}
		} // run
	} // Worker
	private static final long serialVersionUID = 1L;

	private CPanel panel = new CPanel();

	/** Window No */
	private int m_WindowNo = 0;

	/** FormFrame */
	private FormFrame m_frame;
	private StatusBar statusBar = new StatusBar();
	private int AD_Client_ID = Env.getAD_Client_ID(getCtx());
	/** Master (owning) Window */
	protected int p_WindowNo;

	/** Key Column Name */
	protected String p_keyColumn;
	/** Enable more than one selection */
	protected boolean p_multiSelection = true;
	/** Initial WHERE Clause */
	protected String p_whereClause = "";
	/** Table */
	protected MiniTable p_table = new MiniTable();

	/** Model Index of Key Column */
	private int m_keyColumnIndex = -1;
	/** OK pressed */
	// private boolean m_ok = false;
	/** Cancel pressed - need to differentiate between OK - Cancel - Exit */
	private boolean m_cancel = false;
	/** Result IDs */
	/** Layout of Grid */
	protected ColumnInfo[] p_layout;

	/** Main SQL Statement */
	private String m_sqlMain;
	/** Order By Clause */
	private String m_sqlAdd;
	/** Worker */
	private Worker m_worker = null;

	/** Static Layout */
	private CPanel southPanel = new CPanel();

	private BorderLayout southLayout = new BorderLayout();
	ConfirmPanel confirmPanel = new ConfirmPanel(true, true, true, true, true,
			true, true);
	protected CPanel parameterPanel = new CPanel();
	private JScrollPane scrollPane = new JScrollPane();
	//
	private JPopupMenu popup = new JPopupMenu();
	private JMenuItem calcMenu = new JMenuItem();
	/** Window Width */
	static final int INFO_WIDTH = 800;

	private CLabel lProduct_ID = new CLabel(Msg.translate(getCtx(),
			MPPMRP.COLUMNNAME_M_Product_ID));

	private VLookup fProduct_ID;
	private CLabel lAttrSetInstance_ID = new CLabel(Msg.translate(getCtx(),
			MPPOrder.COLUMNNAME_M_AttributeSetInstance_ID));
	private CButton fAttrSetInstance_ID;
	private CLabel lResource_ID = new CLabel(Msg.translate(getCtx(),
			MPPMRP.COLUMNNAME_S_Resource_ID));
	private VLookup fResource_ID;
	private CLabel lWarehouse_ID = new CLabel(Msg.translate(getCtx(),
			MPPMRP.COLUMNNAME_M_Warehouse_ID));
	private VLookup fWarehouse_ID;
	private CLabel lPlanner_ID = new CLabel(Msg.translate(getCtx(),
			MPPMRP.COLUMNNAME_Planner_ID));
	private VLookup fPlanner_ID;
	//
	private CLabel lDateFrom = new CLabel(Msg.translate(getCtx(),
			MLot.COLUMNNAME_DateFrom));

	// DueStart Field
	private VDate fDateFrom = new VDate(MLot.COLUMNNAME_DateFrom, false, false,
			true, DisplayType.Date, Msg.translate(getCtx(),
					MLot.COLUMNNAME_DateFrom)) {
		private static final long serialVersionUID = 1L;

		@Override
		public void setValue(Object arg0) {
			super.setValue(arg0);
		};
	};

	private CLabel lDateTo = new CLabel(Msg.translate(getCtx(),
			MLot.COLUMNNAME_DateTo));

	// DueEnd Field
	private VDate fDateTo = new VDate(MLot.COLUMNNAME_DateTo, false, false,
			true, DisplayType.Date, Msg.translate(getCtx(),
					MLot.COLUMNNAME_DateTo)) {
		private static final long serialVersionUID = 1L;

		@Override
		public void setValue(Object arg0) {
			super.setValue(arg0);
		};
	};
	private CLabel lType = new CLabel();

	private CTextField fType = new CTextField(6);
	private CLabel lUOM = new CLabel();
	private CTextField fUOM = new CTextField(5);
	private CLabel lOrderPeriod = new CLabel();
	private VNumber fOrderPeriod = new VNumber();
	private CLabel lTimefence = new CLabel();
	private VNumber fTimefence = new VNumber();
	private CLabel lLeadtime = new CLabel();
	private VNumber fLeadtime = new VNumber();
	private CLabel lReplenishMin = new CLabel();
	private VNumber fReplenishMin = new VNumber();
	private CLabel lMinOrd = new CLabel();
	private VNumber fMinOrd = new VNumber();
	private CLabel lMaxOrd = new CLabel();
	private VNumber fMaxOrd = new VNumber();
	private CLabel lOrdMult = new CLabel();
	private VNumber fOrdMult = new VNumber();
	private CLabel lOrderQty = new CLabel();
	private VNumber fOrderQty = new VNumber();
	private CLabel lYield = new CLabel();
	private VNumber fYield = new VNumber();
	private CLabel lOnhand = new CLabel();
	private VNumber fOnhand = new VNumber();
	private CLabel lSafetyStock = new CLabel();
	private VNumber fSafetyStock = new VNumber();
	private CLabel lOrdered = new CLabel();
	private VNumber fOrdered = new VNumber();
	private CLabel lReserved = new CLabel();
	private VNumber fReserved = new VNumber();
	private CLabel lAvailable = new CLabel();
	private VNumber fAvailable = new VNumber();
	private CLabel lSupplyType = new CLabel(Msg.translate(getCtx(),
			MPPMRP.COLUMNNAME_TypeMRP));

	private VLookup fSupplyType = null;
	private VCheckBox fMaster = new VCheckBox(
			MPPProductPlanning.COLUMNNAME_IsMPS, false, false, true,
			Msg.translate(getCtx(), MPPProductPlanning.COLUMNNAME_IsMPS), "",
			false);
	private VCheckBox fMRPReq = new VCheckBox(
			MPPProductPlanning.COLUMNNAME_IsRequiredMRP,
			false,
			false,
			true,
			Msg.translate(getCtx(), MPPProductPlanning.COLUMNNAME_IsRequiredMRP),
			"", false);
	private VCheckBox fCreatePlan = new VCheckBox(
			MPPProductPlanning.COLUMNNAME_IsCreatePlan,
			false,
			false,
			true,
			Msg.translate(getCtx(), MPPProductPlanning.COLUMNNAME_IsCreatePlan),
			"", false);
	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JTabbedPane OrderPlanning;
	private javax.swing.JPanel PanelBottom;
	private javax.swing.JPanel PanelCenter;
	private javax.swing.JPanel PanelFind;
	private javax.swing.JPanel PanelOrder;
	private javax.swing.JPanel Results;
	private javax.swing.JPanel mainPanel;

	/** Creates new form VMRPDetailed */
	public VMRPDetailed() {
		initComponents();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if (cmd.equals(ConfirmPanel.A_OK)) {
			m_frame.dispose();
		} else if (cmd.equals(ConfirmPanel.A_CANCEL)) {
			m_cancel = true;
			m_frame.dispose();
		} else if (cmd.equals(ConfirmPanel.A_ZOOM)) {
			zoom();
		} else if (cmd.equals(ConfirmPanel.A_REFRESH)) {
			executeQuery();
		}
		m_frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}

	@Override
	public void dispose() {

		if (m_frame != null)
			m_frame.dispose();
		m_frame = null;
	}

	/**
	 * Enable OK, History, Zoom if row selected
	 */
	void enableButtons() {
		boolean enable = true;// p_table.getSelectedRow() != -1;

		confirmPanel.getOKButton().setEnabled(true);
		if (hasHistory())
			confirmPanel.getHistoryButton().setEnabled(enable);
		if (hasZoom())
			confirmPanel.getZoomButton().setEnabled(enable);
	} // enableButtons

	@Override
	public void executeASync(org.compiere.process.ProcessInfo processInfo) {
	}

	/**************************************************************************
	 * Execute Query
	 */
	void executeQuery() {
		// ignore when running
		if (m_worker != null && m_worker.isAlive())
			return;
		m_worker = new Worker();
		m_worker.start();
	} // executeQuery

	/**
	 * Fill the head value
	 */
	private void fillHead() {
		MPPProductPlanning pp = MPPProductPlanning.find(getCtx(),
				getAD_Org_ID(), getM_Warehouse_ID(), getS_Resource_ID(),
				getM_Product_ID(), null);
		if (pp == null)
			pp = new MPPProductPlanning(getCtx(), 0, null);
		fMaster.setSelected(pp.isMPS());
		fMRPReq.setSelected(pp.isRequiredMRP());
		fCreatePlan.setSelected(pp.isCreatePlan());
		fOrderPeriod.setValue(pp.getOrder_Period());
		fLeadtime.setValue(pp.getDeliveryTime_Promised());
		fTimefence.setValue(pp.getTimeFence());
		fMinOrd.setValue(pp.getOrder_Min());
		fMaxOrd.setValue(pp.getOrder_Max());
		fOrdMult.setValue(pp.getOrder_Pack());
		fOrderQty.setValue(pp.getOrder_Qty());
		fYield.setValue(pp.getYield());
		fType.setText(MRefList.getListName(getCtx(),
				X_PP_Product_Planning.ORDER_POLICY_AD_Reference_ID,
				pp.getOrder_Policy()));
		fSafetyStock.setValue(pp.getSafetyStock());
	}

	/**
	 * Fill Picks Column_ID from C_Order
	 * 
	 * @throws Exception
	 *             if Lookups cannot be initialized
	 */
	private void fillPicks() throws Exception {
		prepareTable(m_layout, getTableName(), getWhereClause(getSQLWhere()) ,"DatePromised,ProductValue"
				);
	}

	protected int getAD_Org_ID() {
		int warehouse_id = getM_Warehouse_ID();
		if (warehouse_id <= 0)
			return 0;
		return MWarehouse.get(getCtx(), warehouse_id).getAD_Org_ID();
	}

	protected Timestamp getDueEnd() {
		return fDateTo.getTimestamp();
	}

	protected Timestamp getDueStart() {
		return fDateFrom.getTimestamp();
	}

	protected int getM_AttributeSetInstance_ID() {
		Object o = fAttrSetInstance_ID.getValue();
		return o != null && (o instanceof Integer) ? (Integer) o : Integer
				.valueOf(0);
	}

	protected int getM_Product_ID() {
		Object o = fProduct_ID.getValue();
		return o != null && (o instanceof Integer) ? (Integer) o : Integer
				.valueOf(0);
	}

	protected int getM_Warehouse_ID() {
		Object o = fWarehouse_ID.getValue();
		return o != null && (o instanceof Integer) ? (Integer) o : Integer
				.valueOf(0);
	}

	protected int getPlanner_ID() {
		Object o = fPlanner_ID.getValue();
		return o != null && (o instanceof Integer) ? (Integer) o : Integer
				.valueOf(0);
	}

	protected BigDecimal getQtyOnHand() {
		BigDecimal bd = (BigDecimal) fOnhand.getValue();
		return bd != null ? bd : Env.ZERO;
	}

	protected int getS_Resource_ID() {
		Object o = fResource_ID.getValue();
		return o != null && (o instanceof Integer) ? (Integer) o : Integer
				.valueOf(0);
	}

	/**
	 * Get the key of currently selected row
	 * 
	 * @return selected key
	 */
	@Override
	public Integer getSelectedRowKey() {
		int row = p_table.getSelectedRow();
		if (row != -1 && m_keyColumnIndex != -1) {
			Object data = p_table.getModel().getValueAt(row, m_keyColumnIndex);
			if (data instanceof IDColumn)
				data = ((IDColumn) data).getRecord_ID();
			if (data instanceof Integer)
				return (Integer) data;
		}
		return null;
	} // getSelectedRowKey

	/**
	 * Set sql to get the head values
	 * 
	 * @return sql
	 */
	private String getSQLWhere() {

		StringBuffer sql = new StringBuffer();

		if (fProduct_ID.getValue() != null) {
			sql.append(" AND " + getTableName() + ".M_Product_ID=?");
			sql.append(" AND (("
					+ getTableName()
					+ ".OrderType IN ('SOO','MOP','POO','POR','STK','DOO')) OR ("
					+ getTableName() + ".OrderType='FCT' AND " + getTableName()
					+ ".DatePromised >= SYSDATE))");
			fillHead();
			setMRP();
		}

		if (isAttributeSetInstance()) {

			sql.append(" AND " + getTableName()
					+ ".M_AttributeSetInstance_ID=?");
			fillHead();
			setMRP();
		}

		if (fResource_ID.getValue() != null)
			sql.append(" AND " + getTableName() + ".S_Resource_ID=?");
		if (fPlanner_ID.getValue() != null)
			sql.append(" AND " + getTableName() + ".Planner_ID=?");
		if (fWarehouse_ID.getValue() != null)
			sql.append(" AND " + getTableName() + ".M_Warehouse_ID=?");
		if (fDateFrom.getValue() != null || fDateFrom.getValue() != null) {
			Timestamp from = (Timestamp) fDateFrom.getValue();
			Timestamp to = (Timestamp) fDateTo.getValue();
			if (from == null && to != null)
				sql.append(" AND TRUNC(" + getTableName()
						+ ".DatePromised) <= ?");
			else if (from != null && to == null)
				sql.append(" AND TRUNC(" + getTableName()
						+ ".DatePromised) >= ?");
			else if (from != null && to != null)
				sql.append(" AND TRUNC(" + getTableName()
						+ ".DatePromised) BETWEEN ? AND ?");
		}

		log.fine("MRP Info.setWhereClause=" + sql.toString());
		return sql.toString();
	}

	/**
	 * Initialize Panel
	 * 
	 * @param WindowNo
	 *            window
	 * @param frame
	 *            frame
	 */
	@Override
	public void init(int WindowNo, FormFrame frame) {
		m_WindowNo = WindowNo;
		m_frame = frame;
		Env.setContext(getCtx(), m_WindowNo, I_C_Order.COLUMNNAME_IsSOTrx, "N");

		try {
			// UI
			statInit();
			fillPicks();
			jbInit();
			//
			m_frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
			m_frame.getContentPane().add(statusBar, BorderLayout.SOUTH);
		} catch (Exception e) {
			log.log(Level.SEVERE, "VMRPDetailed.init", e);
		}
	} // init

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	private void initComponents() {// GEN-BEGIN:initComponents
		mainPanel = new javax.swing.JPanel();
		OrderPlanning = new javax.swing.JTabbedPane();
		PanelOrder = new javax.swing.JPanel();
		PanelFind = new javax.swing.JPanel();
		PanelCenter = new javax.swing.JPanel();
		PanelBottom = new javax.swing.JPanel();
		Results = new javax.swing.JPanel();

		panel.setLayout(new java.awt.BorderLayout());
		mainPanel.setLayout(new java.awt.BorderLayout());
		PanelOrder.setLayout(new java.awt.BorderLayout());
		PanelOrder.add(PanelFind, java.awt.BorderLayout.NORTH);
		PanelOrder.add(PanelCenter, java.awt.BorderLayout.CENTER);
		PanelOrder.add(PanelBottom, java.awt.BorderLayout.SOUTH);
		OrderPlanning.addTab("Order", PanelOrder);
		OrderPlanning.addTab("Results", Results);
		mainPanel.add(OrderPlanning, java.awt.BorderLayout.CENTER);
		panel.add(mainPanel, java.awt.BorderLayout.CENTER);

	}// GEN-END:initComponents

	/*
	 * return true if have integer vlaue
	 */
	private boolean isAttributeSetInstance() {
		return getM_AttributeSetInstance_ID() > 0;
	}

	public boolean isUILocked() {
		return false;
	}

	/**
	 * Static Init
	 * 
	 * @throws Exception
	 */
	protected void jbInit() throws Exception {

		mainPanel.setLayout(new java.awt.BorderLayout());
		panel.setLayout(new java.awt.BorderLayout());
		southPanel.setLayout(southLayout);
		southPanel.add(confirmPanel, BorderLayout.CENTER);
		southPanel.add(statusBar, BorderLayout.SOUTH);

		mainPanel.add(southPanel, BorderLayout.SOUTH);
		mainPanel.add(parameterPanel, BorderLayout.NORTH);
		mainPanel.add(scrollPane, BorderLayout.CENTER);

		scrollPane.getViewport().add(p_table, null);
		//
		confirmPanel.addActionListener(this);
		confirmPanel.getResetButton().setVisible(hasReset());
		confirmPanel.getCustomizeButton().setVisible(hasCustomize());
		confirmPanel.getHistoryButton().setVisible(hasHistory());
		confirmPanel.getZoomButton().setVisible(hasZoom());
		//
		//JButton print = ConfirmPanel.createPrintButton(true);
		//print.addActionListener(this);
		//confirmPanel.addButton(print);
		//
		popup.add(calcMenu);
		calcMenu.setText(Msg.getMsg(getCtx(), "Calculator"));
		calcMenu.setIcon(new ImageIcon(org.compiere.Adempiere.class
				.getResource("images/Calculator16.gif")));
		calcMenu.addActionListener(this);
		//
		p_table.getSelectionModel().addListSelectionListener(this);
		enableButtons();

	} // jbInit

	public void lockUI(org.compiere.process.ProcessInfo processInfo) {
	}

	/**************************************************************************
	 * Prepare Table, Construct SQL (m_m_sqlMain, m_sqlAdd) and size Window
	 * 
	 * @param layout
	 *            layout array
	 * @param from
	 *            from clause
	 * @param staticWhere
	 *            where clause
	 * @param orderBy
	 *            order by clause
	 */
	protected void prepareTable(ColumnInfo[] layout, String from,
			String staticWhere, String orderBy) {
		p_layout = layout;
		StringBuffer sql = new StringBuffer("SELECT ");
		// add columns & sql
		for (int i = 0; i < layout.length; i++) {
			if (i > 0)
				sql.append(", ");
			sql.append(layout[i].getColSQL());
			// adding ID column
			if (layout[i].isKeyPairCol())
				sql.append(",").append(layout[i].getKeyPairColSQL());
			// add to model
			p_table.addColumn(layout[i].getColHeader());
			if (layout[i].isColorColumn())
				p_table.setColorColumn(i);
			if (layout[i].getColClass() == IDColumn.class)
				m_keyColumnIndex = i;
		}
		// set editors (two steps)
		for (int i = 0; i < layout.length; i++)
			p_table.setColumnClass(i, layout[i].getColClass(),
					layout[i].isReadOnly(), layout[i].getColHeader());

		sql.append(" FROM ").append(from);
		sql.append(" WHERE ");

		m_sqlMain = sql.toString();

		m_sqlAdd = "";
		if (orderBy != null && orderBy.length() > 0)
			m_sqlAdd = " ORDER BY " + orderBy;

		if (m_keyColumnIndex == -1)
			log.log(Level.SEVERE, "No KeyColumn - " + sql);

		// Table Selection
		p_table.setRowSelectionAllowed(true);
		// p_table.addMouseListener(this);
		p_table.setMultiSelection(false);
		p_table.setEditingColumn(0);
		p_table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

		// Window Sizing
		parameterPanel.setPreferredSize(new Dimension(INFO_WIDTH,
				parameterPanel.getPreferredSize().height));
		scrollPane.setPreferredSize(new Dimension(INFO_WIDTH, 400));
	} // prepareTable

	/**
	 * filter by Attribute Set Instance
	 */
	private void selectAttributeSetInstance() {
		int m_warehouse_id = getM_Warehouse_ID();
		int m_product_id = getM_Product_ID();

		if (m_product_id <= 0)
			return;
		MProduct product = MProduct.get(getCtx(), m_product_id);
		MWarehouse wh = MWarehouse.get(getCtx(), m_warehouse_id);
		String title = product.get_Translation(MProduct.COLUMNNAME_Name)
				+ " - " + wh.get_Translation(MWarehouse.COLUMNNAME_Name);
		//	Yamel Senih FR [ 114 ] 2015-11-23
		PAttributeInstance pai = new PAttributeInstance(m_frame.getCFrame(), title,
				m_warehouse_id, 0, m_product_id, 0);
		if (pai.getM_AttributeSetInstance_ID() != -1) {
			fAttrSetInstance_ID.setText(pai.getM_AttributeSetInstanceName());
			fAttrSetInstance_ID.setValue(Integer.valueOf(pai.getM_AttributeSetInstance_ID()));
		} else {
			fAttrSetInstance_ID.setValue(Integer.valueOf(0));
		}
	}

	/**
	 * Fill header MRP information
	 */
	private void setMRP() {
		int M_Product_ID = getM_Product_ID();
		int M_AttributeSetInstance_ID = getM_AttributeSetInstance_ID();
		int M_Warehouse_ID = getM_Warehouse_ID();
		// Check Product (mandatory):
		if (M_Product_ID <= 0)
			return;
		// Set Quantities
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			StringBuffer sql = new StringBuffer("SELECT ")
					.append("BOMQtyOnHandASI(M_Product_ID,?,?,?) as qtyonhand, ")
					.append("BOMQtyReservedASI(M_Product_ID,?,?,?) as qtyreserved, ")
					.append("BOMQtyAvailableASI(M_Product_ID,?,?,?) as qtyavailable, ")
					.append("BOMQtyOrderedASI(M_Product_ID,?,?,?) as qtyordered")
					.append(" FROM M_Product WHERE M_Product_ID=?");
			pstmt = DB.prepareStatement(sql.toString(), null);
			DB.setParameters(pstmt, new Object[] {
					getM_AttributeSetInstance_ID(), getM_Warehouse_ID(), 0,
					getM_AttributeSetInstance_ID(), getM_Warehouse_ID(), 0,
					getM_AttributeSetInstance_ID(), getM_Warehouse_ID(), 0,
					getM_AttributeSetInstance_ID(), getM_Warehouse_ID(), 0,
					getM_Product_ID() });
			rs = pstmt.executeQuery();
			while (rs.next()) {
				fOnhand.setValue(rs.getBigDecimal(1));
				fReserved.setValue(rs.getBigDecimal(2));
				fAvailable.setValue(rs.getBigDecimal(3));
				fOrdered.setValue(rs.getBigDecimal(4));
			}
		} catch (SQLException ex) {
			throw new DBException(ex);
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		//
		// Set UOM:
		int uom_id = MProduct.get(getCtx(), M_Product_ID).getC_UOM_ID();
		MUOM um = MUOM.get(getCtx(), uom_id);
		KeyNamePair kum = new KeyNamePair(um.getC_UOM_ID(),
				um.get_Translation(MUOM.COLUMNNAME_Name));
		fUOM.setText(kum.toString());
		//
		// Set Replenish Min Level:
		BigDecimal replenishLevelMin = Env.ZERO;
		if (getM_Warehouse_ID() > 0) {
			String sql = "SELECT Level_Min FROM M_Replenish"
					+ " WHERE AD_Client_ID=? AND M_Product_ID=? AND M_Warehouse_ID=?";
			replenishLevelMin = DB.getSQLValueBD(null, sql, AD_Client_ID,
					M_Product_ID, M_Warehouse_ID);
		}
		fReplenishMin.setValue(replenishLevelMin);
	}

	/**
	 * Set Parameters for Query. (as defined in getSQLWhere)
	 * 
	 * @param pstmt
	 *            statement
	 * @throws SQLException
	 */
	protected void setParameters(PreparedStatement pstmt, boolean forCount)
			throws SQLException {
		int index = 1;
		if (getM_Product_ID() > 0) {
			int product_id = getM_Product_ID();
			pstmt.setInt(index++, product_id);
			log.fine("Product=" + product_id);
		}

		if (isAttributeSetInstance()) {
			int asi = getM_AttributeSetInstance_ID();
			pstmt.setInt(index++, asi);
			log.fine("AttributeSetInstance=" + asi);
		}
		if (getS_Resource_ID() > 0) {
			int resource_id = getS_Resource_ID();
			pstmt.setInt(index++, resource_id);
			log.fine("Resource=" + resource_id);
		}
		if (getM_Warehouse_ID() > 0) {
			int warehouse_id = getM_Warehouse_ID();
			pstmt.setInt(index++, getM_Warehouse_ID());
			log.fine("Warehouse=" + warehouse_id);
		}
		if (getPlanner_ID() > 0) {
			int planner_id = getPlanner_ID();
			pstmt.setInt(index++, planner_id);
			log.fine("Planner=" + planner_id);
		}
		if (getDueStart() != null || getDueEnd() != null) {
			Timestamp from = getDueStart();
			Timestamp to = getDueEnd();
			log.fine("Date From=" + from + ", Date To=" + to);
			if (from == null && to != null)
				pstmt.setTimestamp(index++, to);
			else if (from != null && to == null)
				pstmt.setTimestamp(index++, from);
			else if (from != null && to != null) {
				pstmt.setTimestamp(index++, from);
				pstmt.setTimestamp(index++, to);
			}
		}
	} // setParameters
	public void stateChanged(ChangeEvent e) {
	}
	/**
	 * Static Setup - add fields to parameterPanel
	 * @throws Exception if Lookups cannot be initialized
	 */
	private void statInit() throws Exception {
		// Resource Lookup
		Language language = Language.getLoginLanguage(); // Base Language
		MLookup resourceL = MLookupFactory.get(getCtx(), p_WindowNo, MColumn
				.getColumn_ID(MResource.Table_Name,
						MResource.COLUMNNAME_S_Resource_ID),
				DisplayType.TableDir, language,
				MResource.COLUMNNAME_S_Resource_ID, 0, false,
				MResource.Table_Name + "."
						+ MResource.COLUMNNAME_ManufacturingResourceType
						+ "= '" + MResource.MANUFACTURINGRESOURCETYPE_Plant
						+ "'");
		fResource_ID = new VLookup(MPPMRP.COLUMNNAME_S_Resource_ID, false,
				false, true, resourceL) {
			private static final long serialVersionUID = 1L;

			public void setValue(Object arg0) {
				super.setValue(arg0);
			};
		};
		lResource_ID.setLabelFor(fResource_ID);
		fResource_ID.setBackground(AdempierePLAF.getInfoBackground());
		// Planner Lookup
		fPlanner_ID = new VLookup(MPPMRP.COLUMNNAME_Planner_ID, false, false,
				true, MLookupFactory.get(getCtx(), p_WindowNo, 0, MColumn
						.getColumn_ID(MPPProductPlanning.Table_Name,
								MPPMRP.COLUMNNAME_Planner_ID),
						DisplayType.Table)) {
			private static final long serialVersionUID = 1L;

			public void setValue(Object arg0) {
				super.setValue(arg0);
			};
		};
		lPlanner_ID.setLabelFor(fPlanner_ID);
		fPlanner_ID.setBackground(AdempierePLAF.getInfoBackground());
		// Wahrehouse Lookup
		fWarehouse_ID = new VLookup(MPPMRP.COLUMNNAME_M_Warehouse_ID, true,
				false, true, MLookupFactory.get(getCtx(), p_WindowNo, 0,
						MColumn.getColumn_ID(MWarehouse.Table_Name,
								MPPMRP.COLUMNNAME_M_Warehouse_ID),
						DisplayType.TableDir)) {
			private static final long serialVersionUID = 1L;

			public void setValue(Object arg0) {
				super.setValue(arg0);
			};
		};
		lWarehouse_ID.setLabelFor(fWarehouse_ID);
		fWarehouse_ID.setBackground(AdempierePLAF.getInfoBackground());

		fMaster.setSelected(false);
		fMaster.setReadWrite(false);
		fMRPReq.setSelected(false);
		fMRPReq.setReadWrite(false);
		fCreatePlan.setSelected(false);
		fCreatePlan.setReadWrite(false);

		lUOM.setText(Msg.translate(getCtx(), MUOM.COLUMNNAME_C_UOM_ID));
		fUOM.setBackground(AdempierePLAF.getInfoBackground());
		fUOM.setReadWrite(false);

		lType.setText(Msg.translate(getCtx(),
				MPPProductPlanning.COLUMNNAME_Order_Policy));
		fType.setBackground(AdempierePLAF.getInfoBackground());
		fType.setReadWrite(false);

		lOrderPeriod.setText(Msg.translate(getCtx(),
				MPPProductPlanning.COLUMNNAME_Order_Period));
		fOrderPeriod.setBackground(AdempierePLAF.getInfoBackground());
		fOrderPeriod.setReadWrite(false);

		lTimefence.setText(Msg.translate(getCtx(),
				MPPProductPlanning.COLUMNNAME_TimeFence));
		fTimefence.setBackground(AdempierePLAF.getInfoBackground());
		fTimefence.setReadWrite(false);

		lLeadtime.setText(Msg.translate(getCtx(),
				MPPProductPlanning.COLUMNNAME_DeliveryTime_Promised));
		fLeadtime.setBackground(AdempierePLAF.getInfoBackground());
		fLeadtime.setReadWrite(false);

		lMinOrd.setText(Msg.translate(getCtx(),
				MPPProductPlanning.COLUMNNAME_Order_Min));
		fMinOrd.setBackground(AdempierePLAF.getInfoBackground());
		fMinOrd.setReadWrite(false);

		lMaxOrd.setText(Msg.translate(getCtx(),
				MPPProductPlanning.COLUMNNAME_Order_Max));
		fMaxOrd.setBackground(AdempierePLAF.getInfoBackground());
		fMaxOrd.setReadWrite(false);

		lOrdMult.setText(Msg.translate(getCtx(),
				MPPProductPlanning.COLUMNNAME_Order_Pack));
		fOrdMult.setBackground(AdempierePLAF.getInfoBackground());
		fOrdMult.setReadWrite(false);

		lOrderQty.setText(Msg.translate(getCtx(),
				MPPProductPlanning.COLUMNNAME_Order_Qty));
		fOrderQty.setBackground(AdempierePLAF.getInfoBackground());
		fOrderQty.setReadWrite(false);

		lYield.setText(Msg.translate(getCtx(),
				MPPProductPlanning.COLUMNNAME_Yield));
		fYield.setBackground(AdempierePLAF.getInfoBackground());
		fYield.setReadWrite(false);

		lOnhand.setText(Msg.translate(getCtx(), MStorage.COLUMNNAME_QtyOnHand));
		fOnhand.setBackground(AdempierePLAF.getInfoBackground());
		fOnhand.setReadWrite(false);

		lSafetyStock.setText(Msg.translate(getCtx(),
				MPPProductPlanning.COLUMNNAME_SafetyStock));
		fSafetyStock.setBackground(AdempierePLAF.getInfoBackground());
		fSafetyStock.setReadWrite(false);

		lReserved.setText(Msg.translate(getCtx(),
				MStorage.COLUMNNAME_QtyReserved));
		fReserved.setBackground(AdempierePLAF.getInfoBackground());
		fReserved.setReadWrite(false);

		lAvailable.setText(Msg.translate(getCtx(), "QtyAvailable"));
		fAvailable.setBackground(AdempierePLAF.getInfoBackground());
		fAvailable.setReadWrite(false);

		lOrdered.setText(Msg
				.translate(getCtx(), MPPOrder.COLUMNNAME_QtyOrdered));
		fOrdered.setBackground(AdempierePLAF.getInfoBackground());
		fOrdered.setReadWrite(false);
		// Product Lookup
		fProduct_ID = new VLookup(MPPMRP.COLUMNNAME_M_Product_ID, true, false,
				true, MLookupFactory.get(getCtx(), p_WindowNo, 0, MColumn
						.getColumn_ID(MProduct.Table_Name,
								MPPMRP.COLUMNNAME_M_Product_ID),
						DisplayType.Search)) {
			private static final long serialVersionUID = 1L;

			public void setValue(Object arg0) {
				super.setValue(arg0);
				fAttrSetInstance_ID.setValue(Integer.valueOf(0));
			};
		};

		fAttrSetInstance_ID = new CButton() {
			private static final long serialVersionUID = 1L;
			private Object value;

			public Object getValue() {
				return value;
			};

			public void setText(String text) {
				if (text == null) {
					text = "---";
				}
				if (text.length() > 23) {
					text = text.substring(0, 20) + "...";
				}
				super.setText(text);
			};

			public void setValue(Object arg0) {
				value = arg0;
				int i = (arg0 instanceof Integer) ? ((Integer) arg0).intValue()
						: 0;
				if (i == 0) {
					setText(null);
				}
			};
		};

		fAttrSetInstance_ID.setValue(Integer.valueOf(0));
		fAttrSetInstance_ID.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectAttributeSetInstance();
			}
		});

		lProduct_ID.setLabelFor(fProduct_ID);
		fProduct_ID.setBackground(AdempierePLAF.getInfoBackground());
		//
		lDateFrom.setLabelFor(fDateFrom);
		fDateFrom.setBackground(AdempierePLAF.getInfoBackground());
		fDateFrom.setToolTipText(Msg.translate(getCtx(),
				MLot.COLUMNNAME_DateFrom));
		lDateTo.setLabelFor(fDateTo);
		fDateTo.setBackground(AdempierePLAF.getInfoBackground());
		fDateTo.setToolTipText(Msg.translate(getCtx(), MLot.COLUMNNAME_DateTo));
		fSupplyType = new VLookup(MPPMRP.COLUMNNAME_TypeMRP, false, false,
				true, MLookupFactory.get(getCtx(), p_WindowNo, 0, MColumn
						.getColumn_ID(MPPMRP.Table_Name,
								MPPMRP.COLUMNNAME_TypeMRP), DisplayType.List));
		lSupplyType.setLabelFor(fSupplyType);
		fSupplyType.setBackground(AdempierePLAF.getInfoBackground());
		//
		parameterPanel.setLayout(new ALayout());
		// 1st Row
		parameterPanel.add(lProduct_ID, new ALayoutConstraint(0, 0));
		parameterPanel.add(fProduct_ID, new ALayoutConstraint(0, 1));
		parameterPanel.add(lUOM, new ALayoutConstraint(0, 2));
		parameterPanel.add(fUOM, new ALayoutConstraint(0, 3));
		parameterPanel.add(lType, new ALayoutConstraint(0, 4));
		parameterPanel.add(fType, new ALayoutConstraint(0, 5));

		// 2nd Row
		parameterPanel.add(lAttrSetInstance_ID, new ALayoutConstraint(1, 0));
		parameterPanel.add(fAttrSetInstance_ID, new ALayoutConstraint(1, 1));
		parameterPanel.add(lOnhand, new ALayoutConstraint(1, 2));
		parameterPanel.add(fOnhand, new ALayoutConstraint(1, 3));
		parameterPanel.add(lOrderPeriod, new ALayoutConstraint(1, 4));
		parameterPanel.add(fOrderPeriod, new ALayoutConstraint(1, 5));

		// 3rd Row
		parameterPanel.add(lPlanner_ID, new ALayoutConstraint(2, 0));
		parameterPanel.add(fPlanner_ID, new ALayoutConstraint(2, 1));
		parameterPanel.add(lSafetyStock, new ALayoutConstraint(2, 2));
		parameterPanel.add(fSafetyStock, new ALayoutConstraint(2, 3));
		parameterPanel.add(lMinOrd, new ALayoutConstraint(2, 4));
		parameterPanel.add(fMinOrd, new ALayoutConstraint(2, 5));

		// 4th Row
		parameterPanel.add(lWarehouse_ID, new ALayoutConstraint(3, 0));
		parameterPanel.add(fWarehouse_ID, new ALayoutConstraint(3, 1));
		parameterPanel.add(lReserved, new ALayoutConstraint(3, 2));
		parameterPanel.add(fReserved, new ALayoutConstraint(3, 3));
		parameterPanel.add(lMaxOrd, new ALayoutConstraint(3, 4));
		parameterPanel.add(fMaxOrd, new ALayoutConstraint(3, 5));

		// 5th Row
		parameterPanel.add(lResource_ID, new ALayoutConstraint(4, 0));
		parameterPanel.add(fResource_ID, new ALayoutConstraint(4, 1));
		parameterPanel.add(lAvailable, new ALayoutConstraint(4, 2));
		parameterPanel.add(fAvailable, new ALayoutConstraint(4, 3));
		parameterPanel.add(lOrdMult, new ALayoutConstraint(4, 4));
		parameterPanel.add(fOrdMult, new ALayoutConstraint(4, 5));

		// 6th Row
		parameterPanel.add(lDateFrom, new ALayoutConstraint(5, 0));
		parameterPanel.add(fDateFrom, new ALayoutConstraint(5, 1));
		parameterPanel.add(lOrdered, new ALayoutConstraint(5, 2));
		parameterPanel.add(fOrdered, new ALayoutConstraint(5, 3));
		parameterPanel.add(lOrderQty, new ALayoutConstraint(5, 4));
		parameterPanel.add(fOrderQty, new ALayoutConstraint(5, 5));

		// 7th Row
		parameterPanel.add(lDateTo, new ALayoutConstraint(6, 0));
		parameterPanel.add(fDateTo, new ALayoutConstraint(6, 1));
		parameterPanel.add(lTimefence, new ALayoutConstraint(6, 4));
		parameterPanel.add(fTimefence, new ALayoutConstraint(6, 5));

		// 8th Row
		parameterPanel.add(fMaster, new ALayoutConstraint(7, 1));
		parameterPanel.add(fCreatePlan, new ALayoutConstraint(7, 3));
		parameterPanel.add(lLeadtime, new ALayoutConstraint(7, 4));
		parameterPanel.add(fLeadtime, new ALayoutConstraint(7, 5));

		// 9th Row
		parameterPanel.add(fMRPReq, new ALayoutConstraint(8, 3));
		parameterPanel.add(lYield, new ALayoutConstraint(8, 4));
		parameterPanel.add(fYield, new ALayoutConstraint(8, 5));
	}
	public void tableChanged(TableModelEvent e) {
	}
	public void unlockUI(org.compiere.process.ProcessInfo processInfo) {
	}
	public void valueChanged(ListSelectionEvent e) {
	}
	public void vetoableChange(PropertyChangeEvent evt)
			throws PropertyVetoException {
	}

	// End of variables declaration//GEN-END:variables

	/**
	 * Zoom to target
	 * 
	 * @param AD_Window_ID
	 *            window id
	 * @param zoomQuery
	 *            zoom query
	 * @throws InterruptedException
	 */
	public void zoom(int AD_Window_ID, MQuery zoomQuery) {
		panel.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

		final AWindow frame = new AWindow();
		if (!frame.initWindow(AD_Window_ID, zoomQuery))
			return;

		new Thread() {
			public void run() {
				try {
					sleep(50);
				} catch (Exception e) {
				}
				AEnv.showCenterScreen(frame);
			}
		}.start();
	} // zoom

}
