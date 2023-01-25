/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2011-2013 Adaxa Inc., All Rights Reserved.                *
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
 * Contributors: Adaxa                                                        *
 *****************************************************************************/
package org.compiere.apps.form;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import java.util.logging.Level;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import net.miginfocom.swing.MigLayout;

import org.adempiere.core.domains.models.I_Fact_Reconciliation;
import org.adempiere.plaf.AdempierePLAF;
import org.compiere.apps.AEnv;
import org.compiere.apps.ConfirmPanel;
import org.compiere.grid.ed.VCheckBox;
import org.compiere.grid.ed.VDate;
import org.compiere.grid.ed.VLookup;
import org.compiere.minigrid.ColumnInfo;
import org.compiere.minigrid.IDColumn;
import org.compiere.minigrid.MiniTable;
import org.compiere.model.MClient;
import org.compiere.model.MColumn;
import org.compiere.model.MFactAcct;
import org.compiere.model.MFactReconciliation;
import org.compiere.model.MLookup;
import org.compiere.model.MLookupFactory;
import org.compiere.model.Query;
import org.compiere.plaf.CompiereColor;
import org.compiere.process.ProcessInfo;
import org.compiere.swing.CLabel;
import org.compiere.swing.CPanel;
import org.compiere.swing.CTextField;
import org.compiere.util.ASyncProcess;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Msg;

/**
* Original code
* Create manual match of accounting facts
* @author Paul Bowden, Adaxa
* Zk Port - minor changes
* @author Michael McKay, ADEMPIERE-41 GL Reconciliation integration
* @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
*		<a href="https://github.com/adempiere/adempiere/issues/889">
* 		@see FR [ 889 ] Ambidexter General Ledger Reconciliation</a>
*/
public class VFactReconcile extends CPanel
	implements FormPanel, ActionListener, TableModelListener, ASyncProcess
{
	
	private static final long serialVersionUID = 6908391117180005512L;
	
	/**
	 *	Initialize Panel
	 *  @param WindowNo window
	 *  @param frame frame
	 */
	public void init (int WindowNo, FormFrame frame)
	{
		log.info("");
		m_WindowNo = WindowNo;
		m_frame = frame;
		try
		{
			dynInit();
			jbInit();
			
			frame.getContentPane().add(commandPanel, BorderLayout.SOUTH);
			frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
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

	/** Format                  */
	private DecimalFormat   m_format = DisplayType.getNumberFormat(DisplayType.Amount);
	/** SQL for Query           */
	private String          m_sql;
	/** Number of selected rows */
	private int             m_noSelected = 0;
	/** Client ID               */
	private int             m_AD_Client_ID = 0;
	/**/
	private boolean         m_isLocked = false;
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(VFactReconcile.class);

	//
	private CPanel mainPanel = new CPanel();
	private BorderLayout mainLayout = new BorderLayout();
	private CPanel parameterPanel = new CPanel();
	private CLabel labelAcctSchema = new CLabel();
	private VLookup fieldAcctSchema = null;
	private MigLayout parameterLayout = null;
	private CLabel labelOrg = new CLabel();
	private VLookup fieldOrg = null;
	private VCheckBox isReconciled = new VCheckBox();
	private CLabel labelAccount = new CLabel();
	private VLookup fieldAccount = null;
	private CLabel labelBPartner = new CLabel();
	private VLookup fieldBPartner = null;

	private JLabel dataStatus = new JLabel();
	private JScrollPane dataPane = new JScrollPane();
	private MiniTable miniTable = new MiniTable();
	private CPanel commandPanel = new CPanel();
	private JButton bCancel = ConfirmPanel.createCancelButton(true);
	private JButton bGenerate = ConfirmPanel.createProcessButton(true);
	private JButton bReset = ConfirmPanel.createResetButton(true);
	private JButton bZoom = ConfirmPanel.createZoomButton(true);
	private FlowLayout commandLayout = new FlowLayout();
	private JButton bRefresh = ConfirmPanel.createRefreshButton(true);
	private JButton bSelectAll = ConfirmPanel.createCustomizeButton(true);
	private CLabel labelDateAcct = new CLabel();
	private VDate fieldDateAcct = new VDate();
	private CLabel labelDateAcct2 = new CLabel();
	private VDate fieldDateAcct2 = new VDate();
	
	private CLabel labelProduct = new CLabel();
	private VLookup fieldProduct = null;
	private boolean loading = false;
	private int idColIndex = 3;
	private int amtColIndex = 1;
	private CLabel differenceLabel = new CLabel();
	private CTextField differenceField = new CTextField();

	/**
	 *  Static Init
	 *  @throws Exception
	 */
	private void jbInit() throws Exception
	{
		CompiereColor.setBackground(this);
		//
		mainPanel.setLayout(mainLayout);
		parameterLayout = new MigLayout("fillx, wrap 4, hidemode 0", " [150:150][250:250][100:100][200:200]");
		parameterPanel.setLayout(parameterLayout);
		bRefresh.addActionListener(this);
		bReset.addActionListener(this);
		bZoom.addActionListener(this);
		bGenerate.setEnabled(false);
		bReset.setEnabled(false);
		//bRefresh.setText(Msg.getMsg(Env.getCtx(), "Query"));
		bGenerate.setText(Msg.getMsg(Env.getCtx(),"Process"));
		bReset.setText(Msg.getMsg(Env.getCtx(),"Reset"));
		bZoom.setText(Msg.translate(Env.getCtx(), "Fact_Acct_ID"));
		bSelectAll.addActionListener(this);
		bSelectAll.setText(Msg.getMsg(Env.getCtx(),"SelectAll"));
		
		//
		labelAcctSchema.setText(Msg.translate(Env.getCtx(), "C_AcctSchema_ID"));
		labelAccount.setText(Msg.translate(Env.getCtx(), "Account_ID"));
		labelBPartner.setText(Msg.translate(Env.getCtx(), "C_BPartner_ID"));
		labelDateAcct.setText(Msg.translate(Env.getCtx(), "DateAcct"));
		labelDateAcct2.setText("-");
		labelProduct.setText(Msg.translate(Env.getCtx(), "M_Product_ID"));
		//
		labelOrg.setText(Msg.translate(Env.getCtx(), "AD_Org_ID"));
		isReconciled.setText(Msg.translate(Env.getCtx(), "IsReconciled"));
		dataStatus.setText(" ");
		

		differenceLabel.setText(Msg.getMsg(Env.getCtx(), "Difference"));
		differenceField.setBackground(AdempierePLAF.getFieldBackground_Inactive());
		differenceField.setEditable(false);
		differenceField.setText("0");
		differenceField.setColumns(8);
		differenceField.setHorizontalAlignment(SwingConstants.RIGHT);
		//
		bGenerate.addActionListener(this);
		bCancel.addActionListener(this);
		//
		mainPanel.add(parameterPanel, BorderLayout.NORTH);
		parameterPanel.add(labelAcctSchema, "");
		parameterPanel.add(fieldAcctSchema,   "growx");

		parameterPanel.add(labelOrg,  "");
		parameterPanel.add(fieldOrg,   "growx");

		parameterPanel.add(labelAccount,   "");
		parameterPanel.add(fieldAccount,    "wmax 250");
		
		parameterPanel.add(isReconciled,  "skip 1");

		parameterPanel.add(labelBPartner,   "");
		parameterPanel.add(fieldBPartner,   "growx");

		parameterPanel.add(labelProduct,  "");
		parameterPanel.add(fieldProduct, "growx");

		parameterPanel.add(labelDateAcct, "");
		parameterPanel.add(fieldDateAcct,   "growx");
		
		parameterPanel.add(labelDateAcct2,  "");
		parameterPanel.add(fieldDateAcct2,   "growx");
		
		parameterPanel.add(bRefresh,    "growx");


		mainPanel.add(dataStatus, BorderLayout.SOUTH);
		mainPanel.add(dataPane, BorderLayout.CENTER);
		dataPane.getViewport().add(miniTable, null);
		//
		commandPanel.setLayout(commandLayout);
		commandLayout.setAlignment(FlowLayout.RIGHT);
		commandLayout.setHgap(10);
		commandPanel.add(bSelectAll);
		commandPanel.add(bZoom, null);
		commandPanel.add(differenceLabel, null);
		commandPanel.add(differenceField, null);
		commandPanel.add(bGenerate, null);
		commandPanel.add(bReset, null);
		commandPanel.add(bCancel, null);
	}   //  jbInit

	/**
	 *  Dynamic Init.
	 *  - Load Bank Info
	 *  - Load BPartner
	 *  - Load Document Type
	 *  - Init Table
	 */
	private void dynInit()
	{
		Properties ctx = Env.getCtx();
		//
		m_AD_Client_ID = Env.getAD_Client_ID(Env.getCtx());
		

		//  Fact_Acct.C_AcctSchema_ID
		fieldAcctSchema = new VLookup("C_AcctSchema_ID", false, false, true,
				MLookupFactory.get (Env.getCtx(), m_WindowNo, 0, 
				MColumn.getColumn_ID(MFactAcct.Table_Name, MFactAcct.COLUMNNAME_C_AcctSchema_ID),
				DisplayType.TableDir));
		fieldAcctSchema.addActionListener(this);
		fieldAcctSchema.setValue(MClient.get(Env.getCtx()).getAcctSchema().getC_AcctSchema_ID());
		
		Dimension dim = fieldAcctSchema.getPreferredSize();
		dim.width = 300;
		fieldAcctSchema.setPreferredSize(dim);
		
		// Organization filter selection
		fieldOrg = new VLookup("AD_Org_ID", false, false, true, 
				MLookupFactory.get (Env.getCtx(), m_WindowNo, 0, 
						MColumn.getColumn_ID(MFactAcct.Table_Name, MFactAcct.COLUMNNAME_AD_Org_ID),
						DisplayType.TableDir));
        if (fieldOrg.getValue() == null || ((Integer) fieldOrg.getValue()).intValue() != 0)
			fieldOrg.setValue(Env.getAD_Org_ID(Env.getCtx()));
		
		dim = fieldOrg.getPreferredSize();
		dim.width = 300;
		fieldOrg.setPreferredSize(dim);
		
		//  BPartner
		//  C_Invoice.C_BPartner_ID AD_Column_ID = 3499; 
		fieldBPartner = new VLookup("C_BPartner_ID", false, false, true,
				MLookupFactory.get (Env.getCtx(), m_WindowNo, 0, 
						MColumn.getColumn_ID(MFactAcct.Table_Name, MFactAcct.COLUMNNAME_C_BPartner_ID),
						DisplayType.Search));
		
		// Product
		//  Fact_Acct.M_Product_ID AD_Column_ID = 2527;        
		fieldProduct = new VLookup("M_Product_ID", false, false, true,
				MLookupFactory.get (Env.getCtx(), m_WindowNo, 0, 
						MColumn.getColumn_ID(MFactAcct.Table_Name, MFactAcct.COLUMNNAME_M_Product_ID),
						DisplayType.Search));
		
		// The Account combo.  A bit more involved if we try to filter out the summary accounts.
		MLookup lookup;
		try{
			lookup = MLookupFactory.get (Env.getCtx(), m_WindowNo, 
					MColumn.getColumn_ID(MFactAcct.Table_Name, MFactAcct.COLUMNNAME_Account_ID),
					DisplayType.TableDir, Env.getLanguage(Env.getCtx()), MFactAcct.COLUMNNAME_Account_ID, 
					0, false, "C_ElementValue.IsSummary = 'N'");
		}
		catch (Exception e)
		{
			// Jut alors!  Drop the validation and try again.
			lookup = MLookupFactory.get (Env.getCtx(), m_WindowNo, 0, 
					MColumn.getColumn_ID(MFactAcct.Table_Name, MFactAcct.COLUMNNAME_Account_ID), DisplayType.TableDir);
		}
		fieldAccount = new VLookup("Account_ID", true, false, true, lookup);  // Mandatory true will select first entry with no null option.
		dim = fieldAccount.getPreferredSize();
		dim.width = 300;
		fieldAccount.setPreferredSize(dim);
		
		m_sql = miniTable.prepareTable(new ColumnInfo[] {
			new ColumnInfo(Msg.translate(ctx, "Amt"), "abs(fa.amtacctdr-fa.amtacctcr)", BigDecimal.class),
			new ColumnInfo(Msg.translate(ctx, "AmtAcct"), "(fa.amtacctdr-fa.amtacctcr)", BigDecimal.class,true,true,null),
			new ColumnInfo("DR/CR", "(CASE WHEN (fa.amtacctdr-fa.amtacctcr) < 0 THEN 'CR' ELSE 'DR' END)", String.class),
			new ColumnInfo(" ", "fa.Fact_Acct_ID", IDColumn.class, false, false, null),
			new ColumnInfo(Msg.translate(ctx, "C_BPartner_ID"), "bp.Name", String.class),
			new ColumnInfo(Msg.translate(ctx, "DateAcct"), "fa.DateAcct", Timestamp.class),
			new ColumnInfo(Msg.translate(ctx, "GL_Category_ID"), "glc.Name", String.class),
			new ColumnInfo(Msg.translate(ctx, "M_Product_ID"), "p.Value", String.class),
			new ColumnInfo(Msg.translate(ctx, "Qty"), "Qty", BigDecimal.class),
			new ColumnInfo(Msg.translate(ctx, "Description"), "fa.Description", String.class),
			new ColumnInfo(Msg.translate(ctx, "MatchCode"), "r.MatchCode", String.class),
			new ColumnInfo(Msg.translate(ctx, "DateTrx"), "fa.DateTrx", Timestamp.class),
			new ColumnInfo(Msg.translate(ctx, "AD_Org_ID"), "o.Value", String.class)},
			//	FROM
			"Fact_Acct fa"
			+ " LEFT OUTER JOIN Fact_Reconciliation r ON (fa.Fact_Acct_ID=r.Fact_Acct_ID)"
			+ " LEFT OUTER JOIN C_BPartner bp ON (fa.C_BPartner_ID=bp.C_BPartner_ID)"
			+ " LEFT OUTER JOIN AD_Org o ON (o.AD_Org_ID=fa.AD_Org_ID)"
			+ " LEFT OUTER JOIN M_Product p ON (p.M_Product_ID=fa.M_Product_ID)"
			+ " LEFT OUTER JOIN GL_Category glc ON (fa.GL_Category_ID=glc.GL_Category_ID)",
			//	WHERE
			" fa.AD_Client_ID=?",	//	additional where & order in loadTableInfo()
			true, "fa");
		//
		miniTable.getModel().addTableModelListener(this);
		miniTable.setColumnVisibility(miniTable.getColumnModel().getColumn(1), false);

	}   //  dynInit

	/**
	 *  Query and create TableInfo
	 */
	private void loadTableInfo()
	{
		log.config("");
		//  not yet initialized
		if (m_sql == null)
			return;
		loading  = true;

	

		String sql = m_sql;
		int Account_ID = ((Integer) fieldAccount.getValue()).intValue() ;
		if (Account_ID != 0)
			sql += " AND fa.Account_ID=?";
		
		if ( ((Integer) fieldAcctSchema.getValue()) > 0 )
			sql += " AND fa.C_AcctSchema_ID = ?";
		
		sql += " AND ((SELECT SUM(f.amtacctdr-f.amtacctcr) FROM Fact_Reconciliation rec " +
				" INNER JOIN Fact_Acct f ON (f.Fact_Acct_ID = rec.Fact_Acct_ID) " +
				" WHERE r.MatchCode=rec.MatchCode) ";
		if ( isReconciled.isSelected() )
			sql += "= 0) ";
		else
			sql += "<> 0 OR r.MatchCode IS NULL) ";
		
		
		
		if ( fieldBPartner.getValue() != null )
			sql += " AND fa.C_BPartner_ID = ?";
		
		if ( fieldProduct.getValue() != null )
			sql += " AND fa.M_Product_ID = ?";
		
		if ( fieldDateAcct.getValue() != null )
			sql += " AND fa.DateAcct >= ?";
		
		if ( fieldDateAcct2.getValue() != null )
			sql += " AND fa.DateAcct <= ?";
		
		
		sql += " ORDER BY 1,5,3,6";

		log.finest(sql + "Account_ID =" + Account_ID );
		//  Get facts
		try
		{
			int index = 1;
			PreparedStatement pstmt = DB.prepareStatement(sql, null);

			pstmt.setInt(index++, m_AD_Client_ID);		//	Client

			pstmt.setInt(index++, (Integer) fieldAccount.getValue());		//	account
			
			if ( ((Integer) fieldAcctSchema.getValue()) > 0 )
				pstmt.setInt(index++, (Integer) fieldAcctSchema.getValue());
			
			if (  fieldBPartner.getValue() != null )
				pstmt.setInt(index++, (Integer) fieldBPartner.getValue());
			
			if ( fieldProduct.getValue() != null )
				pstmt.setInt(index++, (Integer) fieldProduct.getValue());
			
			if ( fieldDateAcct.getValue() != null )
				pstmt.setTimestamp(index++, (Timestamp) fieldDateAcct.getValue());
			

			if ( fieldDateAcct2.getValue() != null )
				pstmt.setTimestamp(index++, (Timestamp) fieldDateAcct2.getValue());
			
			
			ResultSet rs = pstmt.executeQuery();
			miniTable.loadTable(rs);
			rs.close();
			pstmt.close();
			log.log(Level.FINE, sql);
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		loading = false;
		
		calculateSelection();
	}   //  loadTableInfo

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
	 *  ActionListener
	 *  @param e event
	 */
	public void actionPerformed (ActionEvent e)
	{
		//  Generate Reconciliation
		if (e.getSource() == bGenerate)
		{
			generateReconciliation();
		}
		
		else if (e.getSource() == bReset)
		{
			resetReconciliation();
		}
		else if (e.getSource() == bZoom)
		{
			zoom();
		}

		else if (e.getSource() == bCancel)
			dispose();

		//  Update
		else if (e.getSource() == bRefresh)
			loadTableInfo();
		
		else if (e.getSource() == bSelectAll) {
			int rows = miniTable.getModel().getRowCount();
			for (int i = 0; i < rows; i++) {
				IDColumn id = (IDColumn)miniTable.getModel().getValueAt(i, idColIndex);
				id.setSelected(true);
			}
			miniTable.repaint();
			calculateSelection();
		}

	}   //  actionPerformed
	
	/**
	 *	Zoom to target
	 *  @param AD_Window_ID window id
	 *  @param zoomQuery zoom query
	 */
	protected void zoom ()
	{
		log.info("");
		
		int selected = miniTable.getSelectedRow();
		
		if ( selected == -1 )
			return;
		
		int factId = ((IDColumn) miniTable.getModel().getValueAt(selected, idColIndex)).getRecord_ID();
		
		AEnv.zoom(270, factId);
	}	//	zoom


	private void resetReconciliation() {
		log.info("");
		//
		miniTable.stopEditor(true);
		if (miniTable.getRowCount() == 0)
			return;
		miniTable.setRowSelectionInterval(0,0);
		calculateSelection();
		if (m_noSelected == 0)
			return;

		for ( int r = 0; r < miniTable.getModel().getRowCount(); r++ )
		{
			if ( ((IDColumn) miniTable.getModel().getValueAt(r, idColIndex)).isSelected() )
			{
				int factId = ((IDColumn) miniTable.getModel().getValueAt(r, idColIndex )).getRecord_ID();

				MFactReconciliation rec = new Query(Env.getCtx(), I_Fact_Reconciliation.Table_Name, 
						I_Fact_Reconciliation.COLUMNNAME_Fact_Acct_ID + " = ?", null)
						.setParameters(new Object[] {factId}).first();
				//	
				if (rec == null) {
					continue;
				}

				rec.setMatchCode(null);
				rec.saveEx();

				((DefaultTableModel) miniTable.getModel()).removeRow(r--);
			}
		}	
	}

	/**
	 *  Table Model Listener
	 *  @param e event
	 */
	public void tableChanged(TableModelEvent e)
	{
		if (! loading )
			calculateSelection();
	}   //  valueChanged

	/**
	 *  Calculate selected rows.
	 *  - add up selected rows
	 */
	public void calculateSelection()
	{
		m_noSelected = 0;
		BigDecimal selectedAmt = new BigDecimal(0.0);

		int rows = miniTable.getRowCount();
		for (int i = 0; i < rows; i++)
		{
			IDColumn id = (IDColumn)miniTable.getModel().getValueAt(i, idColIndex);
			if (id.isSelected())
			{
				BigDecimal amt = (BigDecimal)miniTable.getModel().getValueAt(i, amtColIndex);
				if (amt != null)
					selectedAmt = selectedAmt.add(amt);
				m_noSelected++;
			}
		}

		//  Information
		StringBuffer info = new StringBuffer();
		info.append(m_noSelected).append(" ").append(Msg.getMsg(Env.getCtx(), "Selected")).append(" / ").append(miniTable.getRowCount());
		
		differenceField.setText(m_format.format(selectedAmt));
		dataStatus.setText(info.toString());
		//
		bGenerate.setEnabled(m_noSelected != 0 && Env.ZERO.compareTo(selectedAmt) == 0 && !isReconciled.isSelected());
		bReset.setEnabled(m_noSelected > 0 && isReconciled.isSelected());
	}   //  calculateSelection

	/**
	 *  Generate Reconciliation record
	 */
	private void generateReconciliation()
	{
		log.info("");
		//
		miniTable.stopEditor(true);
		if (miniTable.getRowCount() == 0)
			return;
		miniTable.setRowSelectionInterval(0,0);
		calculateSelection();
		if (m_noSelected == 0)
			return;


		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = DisplayType.getDateFormat();
		String time = sdf.format(cal.getTime());

		String matchcode = Msg.parseTranslation(Env.getCtx(), "@IsManual@: " + Env.getContext(Env.getCtx(), "#AD_User_Name") + " " + time);
		
		for ( int r = 0; r < miniTable.getModel().getRowCount(); r++ )
		{
			if ( ((IDColumn) miniTable.getModel().getValueAt(r, idColIndex)).isSelected() )
			{
				int factId = ((IDColumn) miniTable.getModel().getValueAt(r, idColIndex )).getRecord_ID();
				//	
				MFactReconciliation rec = new Query(Env.getCtx(), I_Fact_Reconciliation.Table_Name, 
						I_Fact_Reconciliation.COLUMNNAME_Fact_Acct_ID + " = ?", null)
						.setParameters(new Object[] {factId}).first();
				//	
				if (rec == null) {
					rec = new MFactReconciliation(Env.getCtx(), 0, null);
					rec.setFact_Acct_ID(factId);
				}

				rec.setMatchCode(matchcode);
				rec.saveEx();

				((DefaultTableModel) miniTable.getModel()).removeRow(r--);
			}
		}
	}   

	/**
	 *  Lock User Interface
	 *  Called from the Worker before processing
	 *  @param pi process info
	 */
	public void lockUI (ProcessInfo pi)
	{
		this.setEnabled(false);
		m_isLocked = true;
	}   //  lockUI

	/**
	 *  Unlock User Interface.
	 *  Called from the Worker when processing is done
	 *  @param pi process info
	 */
	public void unlockUI (ProcessInfo pi)
	{
		this.setEnabled(true);
		m_isLocked = false;
	}   //  unlockUI

	/**
	 *  Is the UI locked (Internal method)
	 *  @return true, if UI is locked
	 */
	public boolean isUILocked()
	{
		return m_isLocked;
	}   //  isLoacked

	/**
	 *  Method to be executed async.
	 *  Called from the ASyncProcess worker
	 *  @param pi process info
	 */
	public void executeASync (ProcessInfo pi)
	{
		log.config("-");
	}   //  executeASync

}   //  VPaySelect
