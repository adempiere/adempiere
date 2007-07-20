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
import java.beans.*;
import java.math.*;
import java.sql.*;
import java.util.*;
import java.util.logging.*;
import javax.swing.*;
import javax.swing.event.*;

import org.adempiere.plaf.AdempierePLAF;
import org.compiere.apps.*;
import org.compiere.grid.ed.*;
import org.compiere.minigrid.*;
import org.compiere.model.*;
import org.compiere.plaf.*;
import org.compiere.print.*;
import org.compiere.process.*;
import org.compiere.swing.*;
import org.compiere.util.*;

/**
 *	Manual Invoice Selection
 *
 *  @author Jorg Janke
 *  @version $Id: VInvoiceGen.java,v 1.2 2006/07/30 00:51:28 jjanke Exp $
 */
public class VInvoiceGen extends CPanel
	implements FormPanel, ActionListener, VetoableChangeListener, ChangeListener, TableModelListener, ASyncProcess
{
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
		Env.setContext(Env.getCtx(), m_WindowNo, "IsSOTrx", "Y");
		try
		{
			fillPicks();
			jbInit();
			dynInit();
			frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
			frame.getContentPane().add(statusBar, BorderLayout.SOUTH);
		}
		catch(Exception ex)
		{
			log.log(Level.SEVERE, "init", ex);
		}
	}	//	init

	/**	Window No			*/
	private int         	m_WindowNo = 0;
	/**	FormFrame			*/
	private FormFrame 		m_frame;

	private boolean			m_selectionActive = true;
	private Object 			m_AD_Org_ID = null;
	private Object 			m_C_BPartner_ID = null;
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(VInvoiceGen.class);
	//
	private CTabbedPane tabbedPane = new CTabbedPane();
	private CPanel selPanel = new CPanel();
	private CPanel selNorthPanel = new CPanel();
	private BorderLayout selPanelLayout = new BorderLayout();
	private CLabel lOrg = new CLabel();
	private VLookup fOrg;
	private CLabel lBPartner = new CLabel();
	private VLookup fBPartner;
	private FlowLayout northPanelLayout = new FlowLayout();
	private ConfirmPanel confirmPanelSel = new ConfirmPanel(true);
	private ConfirmPanel confirmPanelGen = new ConfirmPanel(false, true, false, false, false, false, true);
	private StatusBar statusBar = new StatusBar();
	private CPanel genPanel = new CPanel();
	private BorderLayout genLayout = new BorderLayout();
	private CTextPane info = new CTextPane();
	private JScrollPane scrollPane = new JScrollPane();
	private MiniTable miniTable = new MiniTable();
	private ArrayList<Integer> selections = null;
	
	private CLabel     lDocType = new CLabel();
    private VComboBox  cmbDocType = new VComboBox();
	
	/**
	 *	Static Init.
	 *  <pre>
	 *  selPanel (tabbed)
	 *      fOrg, fBPartner
	 *      scrollPane & miniTable
	 *  genPanel
	 *      info
	 *  </pre>
	 *  @throws Exception
	 */
	void jbInit() throws Exception
	{
		CompiereColor.setBackground(this);
		//
		selPanel.setLayout(selPanelLayout);
		lOrg.setLabelFor(fOrg);
		lOrg.setText(Msg.translate(Env.getCtx(), "AD_Org_ID"));
		lBPartner.setLabelFor(fBPartner);
		lBPartner.setText(Msg.translate(Env.getCtx(), "C_BPartner_ID"));
		selNorthPanel.setLayout(northPanelLayout);
		northPanelLayout.setAlignment(FlowLayout.LEFT);
		tabbedPane.add(selPanel, Msg.getMsg(Env.getCtx(), "Select"));
		selPanel.add(selNorthPanel, BorderLayout.NORTH);
		selNorthPanel.add(lOrg, null);
		selNorthPanel.add(fOrg, null);
		selNorthPanel.add(lBPartner, null);
		selNorthPanel.add(fBPartner, null);
		selPanel.setName("selPanel");
		selPanel.add(confirmPanelSel, BorderLayout.SOUTH);
		selPanel.add(scrollPane, BorderLayout.CENTER);
		scrollPane.getViewport().add(miniTable, null);
		confirmPanelSel.addActionListener(this);
		//
		tabbedPane.add(genPanel, Msg.getMsg(Env.getCtx(), "Generate"));
		genPanel.setLayout(genLayout);
		genPanel.add(info, BorderLayout.CENTER);
		genPanel.setEnabled(false);
		info.setBackground(AdempierePLAF.getFieldBackground_Inactive());
		info.setEditable(false);
		genPanel.add(confirmPanelGen, BorderLayout.SOUTH);
		confirmPanelGen.addActionListener(this);
		
		lDocType.setLabelFor(cmbDocType);
        selNorthPanel.add(lDocType, null);
        selNorthPanel.add(cmbDocType, null);
	}	//	jbInit

	/**
	 *	Fill Picks
	 *		Column_ID from C_Order
	 *  @throws Exception if Lookups cannot be initialized
	 */
	private void fillPicks() throws Exception
	{
		MLookup orgL = MLookupFactory.get (Env.getCtx(), m_WindowNo, 0, 2163, DisplayType.TableDir);
		fOrg = new VLookup ("AD_Org_ID", false, false, true, orgL);
	//	lOrg.setText(Msg.translate(Env.getCtx(), "AD_Org_ID"));
		fOrg.addVetoableChangeListener(this);
		//
		MLookup bpL = MLookupFactory.get (Env.getCtx(), m_WindowNo, 0, 2762, DisplayType.Search);
		fBPartner = new VLookup ("C_BPartner_ID", false, false, true, bpL);
	//	lBPartner.setText(Msg.translate(Env.getCtx(), "C_BPartner_ID"));
		fBPartner.addVetoableChangeListener(this);
		
		//Document Type Sales Order/Vendor RMA
        lDocType.setText(Msg.translate(Env.getCtx(), "C_DocType_ID"));
        cmbDocType.addItem(new KeyNamePair(MOrder.Table_ID, Msg.translate(Env.getCtx(), "Order")));
        cmbDocType.addItem(new KeyNamePair(MRMA.Table_ID, Msg.translate(Env.getCtx(), "VendorRMA")));
        cmbDocType.addActionListener(this);
	}	//	fillPicks

	/**
	 *	Dynamic Init.
	 *	- Create GridController & Panel
	 *	- AD_Column_ID from C_Order
	 */
	private void dynInit()
	{
		//  create Columns
		miniTable.addColumn("C_Order_ID");
		miniTable.addColumn("AD_Org_ID");
		miniTable.addColumn("C_DocType_ID");
		miniTable.addColumn("DocumentNo");
		miniTable.addColumn("C_BPartner_ID");
		miniTable.addColumn("DateOrdered");
		miniTable.addColumn("TotalLines");
		//
		miniTable.setMultiSelection(true);
		miniTable.setRowSelectionAllowed(true);
		//  set details
		miniTable.setColumnClass(0, IDColumn.class, false, " ");
		miniTable.setColumnClass(1, String.class, true, Msg.translate(Env.getCtx(), "AD_Org_ID"));
		miniTable.setColumnClass(2, String.class, true, Msg.translate(Env.getCtx(), "C_DocType_ID"));
		miniTable.setColumnClass(3, String.class, true, Msg.translate(Env.getCtx(), "DocumentNo"));
		miniTable.setColumnClass(4, String.class, true, Msg.translate(Env.getCtx(), "C_BPartner_ID"));
		miniTable.setColumnClass(5, Timestamp.class, true, Msg.translate(Env.getCtx(), "DateOrdered"));
		miniTable.setColumnClass(6, BigDecimal.class, true, Msg.translate(Env.getCtx(), "TotalLines"));
		//
		miniTable.autoSize();
		miniTable.getModel().addTableModelListener(this);
		//	Info
		statusBar.setStatusLine(Msg.getMsg(Env.getCtx(), "InvGenerateSel"));
		statusBar.setStatusDB(" ");
		//	Tabbed Pane Listener
		tabbedPane.addChangeListener(this);
	}	//	dynInit
	
	private String getOrderSQL()
	{
	    StringBuffer sql = new StringBuffer(
	            "SELECT C_Order_ID, o.Name, dt.Name, DocumentNo, bp.Name, DateOrdered, TotalLines "
	            + "FROM C_Invoice_Candidate_v ic, AD_Org o, C_BPartner bp, C_DocType dt "
	            + "WHERE ic.AD_Org_ID=o.AD_Org_ID"
	            + " AND ic.C_BPartner_ID=bp.C_BPartner_ID"
	            + " AND ic.C_DocType_ID=dt.C_DocType_ID"
	            + " AND ic.AD_Client_ID=?");

        if (m_AD_Org_ID != null)
            sql.append(" AND ic.AD_Org_ID=").append(m_AD_Org_ID);
        if (m_C_BPartner_ID != null)
            sql.append(" AND ic.C_BPartner_ID=").append(m_C_BPartner_ID);
        
        // bug - [ 1713337 ] "Generate Invoices (manual)" show locked records.
        /* begin - Exclude locked records; @Trifon */
        int AD_User_ID = Env.getContextAsInt(Env.getCtx(), "#AD_User_ID");
        String lockedIDs = MPrivateAccess.getLockedRecordWhere(MOrder.Table_ID, AD_User_ID);
        if (lockedIDs != null)
        {
            if (sql.length() > 0)
                sql.append(" AND ");
            sql.append("C_Order_ID").append(lockedIDs);
        }
        /* eng - Exclude locked records; @Trifon */

        //
        sql.append(" ORDER BY o.Name,bp.Name,DateOrdered");
        
        return sql.toString();
	}
	
	private String getRMASql()
	{
	    StringBuffer sql = new StringBuffer();
	    sql.append("SELECT rma.M_RMA_ID, org.Name, dt.Name, rma.DocumentNo, bp.Name, rma.Created, rma.Amt ");
        sql.append("FROM M_RMA rma INNER JOIN AD_Org org ON rma.AD_Org_ID=org.AD_Org_ID ");
        sql.append("INNER JOIN C_DocType dt ON rma.C_DocType_ID=dt.C_DocType_ID ");
        sql.append("INNER JOIN C_BPartner bp ON rma.C_BPartner_ID=bp.C_BPartner_ID ");
        sql.append("INNER JOIN M_InOut io ON rma.InOut_ID=io.M_InOut_ID ");
        sql.append("WHERE rma.DocStatus='CO' ");
        sql.append("AND dt.DocBaseType = 'POO' ");
        sql.append("AND NOT EXISTS (SELECT * FROM C_Invoice i ");
        sql.append("WHERE i.M_RMA_ID=rma.M_RMA_ID AND i.DocStatus IN ('IP', 'CO', 'CL')) ");
        sql.append("AND EXISTS (SELECT * FROM C_InvoiceLine il INNER JOIN M_InOutLine iol ");
        sql.append("ON il.M_InOutLine_ID=iol.M_InOutLine_ID INNER JOIN C_Invoice i ");
        sql.append("ON i.C_Invoice_ID=il.C_Invoice_ID WHERE i.DocStatus IN ('CO', 'CL') ");
        sql.append("AND iol.M_InOutLine_ID IN ");
        sql.append("(SELECT M_InOutLine_ID FROM M_RMALine rl WHERE rl.M_RMA_ID=rma.M_RMA_ID ");
        sql.append("AND rl.M_InOutLine_ID IS NOT NULL)) ");
        sql.append("AND rma.AD_Client_ID=?");
        
        if (m_AD_Org_ID != null)
            sql.append(" AND rma.AD_Org_ID=").append(m_AD_Org_ID);
        if (m_C_BPartner_ID != null)
            sql.append(" AND bp.C_BPartner_ID=").append(m_C_BPartner_ID);
        
        int AD_User_ID = Env.getContextAsInt(Env.getCtx(), "#AD_User_ID");
        String lockedIDs = MPrivateAccess.getLockedRecordWhere(MRMA.Table_ID, AD_User_ID);
        if (lockedIDs != null)
        {
            sql.append(" AND rma.M_RMA_ID").append(lockedIDs);
        }
        
        sql.append(" ORDER BY org.Name, bp.Name, rma.Created ");
        
        return sql.toString();
	}

	/**
	 *  Query Info
	 */
	private void executeQuery()
	{
		log.info("");
		int AD_Client_ID = Env.getAD_Client_ID(Env.getCtx());
		//  Create SQL
		
		String sql = "";
        
        KeyNamePair docTypeKNPair = (KeyNamePair)cmbDocType.getSelectedItem();
        
        if (docTypeKNPair.getKey() == MOrder.Table_ID)
        {
            sql = getOrderSQL();
        }
        else
        {
            sql = getRMASql();
        }

		//  reset table
		int row = 0;
		miniTable.setRowCount(row);
		//  Execute
		try
		{
			PreparedStatement pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, AD_Client_ID);
			ResultSet rs = pstmt.executeQuery();
			//
			while (rs.next())
			{
				//  extend table
				miniTable.setRowCount(row+1);
				//  set values
				miniTable.setValueAt(new IDColumn(rs.getInt(1)), row, 0);   //  C_Order_ID
				miniTable.setValueAt(rs.getString(2), row, 1);              //  Org
				miniTable.setValueAt(rs.getString(3), row, 2);              //  DocType
				miniTable.setValueAt(rs.getString(4), row, 3);              //  Doc No
				miniTable.setValueAt(rs.getString(5), row, 4);              //  BPartner
				miniTable.setValueAt(rs.getTimestamp(6), row, 5);           //  DateOrdered
				miniTable.setValueAt(rs.getBigDecimal(7), row, 6);          //  TotalLines
				//  prepare next
				row++;
			}
			rs.close();
			pstmt.close();
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql.toString(), e);
		}
		//
		miniTable.autoSize();
	//	statusBar.setStatusDB(String.valueOf(miniTable.getRowCount()));
	}   //  executeQuery

	/**
	 * 	Dispose
	 */
	public void dispose()
	{
		if (m_frame != null)
			m_frame.dispose();
		m_frame = null;
	}	//	dispose

	/**
	 *	Action Listener
	 *  @param e event
	 */
	public void actionPerformed (ActionEvent e)
	{
		log.info("Cmd=" + e.getActionCommand());
		//
		if (e.getActionCommand().equals(ConfirmPanel.A_CANCEL))
		{
			dispose();
			return;
		}
		if (cmbDocType.equals(e.getSource()))
        {
            executeQuery();
            return;
        }
		//
		saveSelection();
		if (selections != null && selections.size() > 0 && m_selectionActive)
			generateInvoices ();
		else
			dispose();
	}	//	actionPerformed

	/**
	 *	Vetoable Change Listener - requery
	 *  @param e event
	 */
	public void vetoableChange(PropertyChangeEvent e)
	{
		log.info(e.getPropertyName() + "=" + e.getNewValue());
		if (e.getPropertyName().equals("AD_Org_ID"))
			m_AD_Org_ID = e.getNewValue();
		if (e.getPropertyName().equals("C_BPartner_ID"))
		{
			m_C_BPartner_ID = e.getNewValue();
			fBPartner.setValue(m_C_BPartner_ID);	//	display value
		}
		executeQuery();
	}	//	vetoableChange

	/**
	 *	Change Listener (Tab changed)
	 *  @param e event
	 */
	public void stateChanged (ChangeEvent e)
	{
		int index = tabbedPane.getSelectedIndex();
		m_selectionActive = (index == 0);
	}	//	stateChanged

	/**
	 *  Table Model Listener
	 *  @param e event
	 */
	public void tableChanged(TableModelEvent e)
	{
		int rowsSelected = 0;
		int rows = miniTable.getRowCount();
		for (int i = 0; i < rows; i++)
		{
			IDColumn id = (IDColumn)miniTable.getValueAt(i, 0);     //  ID in column 0
			if (id != null && id.isSelected())
				rowsSelected++;
		}
		statusBar.setStatusDB(" " + rowsSelected + " ");
	}   //  tableChanged

	/**
	 *	Save Selection
	 */
	private void saveSelection()
	{
		log.info("");
		//  ID selection may be pending
		miniTable.editingStopped(new ChangeEvent(this));
		//  Array of Integers
		ArrayList<Integer> results = new ArrayList<Integer>();
		selections = null;
		
		//	Get selected entries
		int rows = miniTable.getRowCount();
		for (int i = 0; i < rows; i++)
		{
			IDColumn id = (IDColumn)miniTable.getValueAt(i, 0);     //  ID in column 0
		//	log.fine( "Row=" + i + " - " + id);
			if (id != null && id.isSelected())
				results.add(id.getRecord_ID());
		}

		if (results.size() == 0)
			return;

		selections = results;
	}	//	saveSelection

	
	/**************************************************************************
	 *	Generate Invoices
	 */
	private void generateInvoices ()
	{
		String trxName = Trx.createTrxName("IVG");
		Trx trx = Trx.get(trxName, true);	//trx needs to be committed too
		//String trxName = null;
		//Trx trx = null;
		
		m_selectionActive = false;  //  prevents from being called twice
		statusBar.setStatusLine(Msg.getMsg(Env.getCtx(), "InvGenerateGen"));
		statusBar.setStatusDB(String.valueOf(selections.size()));

		//	Prepare Process
		int AD_Process_ID = 0;
		KeyNamePair docTypeKNPair = (KeyNamePair)cmbDocType.getSelectedItem();
        
        if (docTypeKNPair.getKey() == MRMA.Table_ID)
        {
            AD_Process_ID = 52002; // C_Invoice_GenerateRMA - org.adempiere.process.InvoiceGenerateRMA
        }
        else
        {
            AD_Process_ID = 134;  // HARDCODED    C_InvoiceCreate
        }
		MPInstance instance = new MPInstance(Env.getCtx(), AD_Process_ID, 0);
		if (!instance.save())
		{
			info.setText(Msg.getMsg(Env.getCtx(), "ProcessNoInstance"));
			return;
		}
		
		//insert selection
		StringBuffer insert = new StringBuffer();
		insert.append("INSERT INTO T_SELECTION(AD_PINSTANCE_ID, T_SELECTION_ID) ");
		int counter = 0;
		for(Integer selectedId : selections)
		{
			counter++;
			if (counter > 1)
				insert.append(" UNION ");
			insert.append("SELECT ");
			insert.append(instance.getAD_PInstance_ID());
			insert.append(", ");
			insert.append(selectedId);
			insert.append(" FROM DUAL ");
			
			if (counter == 1000) 
			{
				if ( DB.executeUpdate(insert.toString(), trxName) < 0 )
				{
					String msg = "No Shipments";     //  not translated!
					log.config(msg);
					info.setText(msg);
					trx.rollback();
					return;
				}
				insert = new StringBuffer();
				insert.append("INSERT INTO T_SELECTION(AD_PINSTANCE_ID, T_SELECTION_ID) ");
				counter = 0;
			}
		}
		if (counter > 0)
		{
			if ( DB.executeUpdate(insert.toString(), trxName) < 0 )
			{
				String msg = "No Shipments";     //  not translated!
				log.config(msg);
				info.setText(msg);
				trx.rollback();
				return;
			}
		}
		
		ProcessInfo pi = new ProcessInfo ("", AD_Process_ID);
		pi.setAD_PInstance_ID (instance.getAD_PInstance_ID());

		//	Add Parameters
		MPInstancePara para = new MPInstancePara(instance, 10);
		para.setParameter("Selection", "Y");
		if (!para.save())
		{
			String msg = "No Selection Parameter added";  //  not translated
			info.setText(msg);
			log.log(Level.SEVERE, msg);
			return;
		}
		para = new MPInstancePara(instance, 20);
		para.setParameter("DocAction", "CO");
		if (!para.save())
		{
			String msg = "No DocAction Parameter added";  //  not translated
			info.setText(msg);
			log.log(Level.SEVERE, msg);
			return;
		}

		//	Execute Process
		ProcessCtl worker = new ProcessCtl(this, Env.getWindowNo(this), pi, trx);
		worker.start();     //  complete tasks in unlockUI / generateInvoice_complete
	}	//	generateInvoices

	/**
	 *  Complete generating invoices.
	 *  Called from Unlock UI
	 *  @param pi process info
	 */
	private void generateInvoice_complete (ProcessInfo pi)
	{
		//  Switch Tabs
		tabbedPane.setSelectedIndex(1);
		//
		ProcessInfoUtil.setLogFromDB(pi);
		StringBuffer iText = new StringBuffer();
		iText.append("<b>").append(pi.getSummary())
			.append("</b><br>(")
			.append(Msg.getMsg(Env.getCtx(), "InvGenerateInfo"))
			//Invoices are generated depending on the Invoicing Rule selection in the Order
			.append(")<br>")
			.append(pi.getLogInfo(true));
		info.setText(iText.toString());

		//	Reset Selection
		/*
		String sql = "UPDATE C_Order SET IsSelected = 'N' WHERE " + m_whereClause;
		int no = DB.executeUpdate(sql, null);
		log.config("Reset=" + no);*/

		//	Get results
		int[] ids = pi.getIDs();
		if (ids == null || ids.length == 0)
			return;

		confirmPanelGen.getOKButton().setEnabled(false);
		//	OK to print invoices
		if (ADialog.ask(m_WindowNo, this, "PrintInvoices"))
		{
		//	info.append("\n\n" + Msg.getMsg(Env.getCtx(), "PrintInvoices"));
			setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			int retValue = ADialogDialog.A_CANCEL;
			do
			{
				//	Loop through all items
				for (int i = 0; i < ids.length; i++)
				{
					int C_Invoice_ID = ids[i];
					ReportCtl.startDocumentPrint(ReportEngine.INVOICE, C_Invoice_ID, this, Env.getWindowNo(this), true);
				}
				ADialogDialog d = new ADialogDialog (m_frame,
					Env.getHeader(Env.getCtx(), m_WindowNo),
					Msg.getMsg(Env.getCtx(), "PrintoutOK?"),
					JOptionPane.QUESTION_MESSAGE);
				retValue = d.getReturnCode();
			}
			while (retValue == ADialogDialog.A_CANCEL);
			setCursor(Cursor.getDefaultCursor());
		}	//	OK to print invoices

		//
		confirmPanelGen.getOKButton().setEnabled(true);
	}   //  generateInvoices_complete

	
	/**************************************************************************
	 *  Lock User Interface.
	 *  Called from the Worker before processing
	 *  @param pi process info
	 */
	public void lockUI (ProcessInfo pi)
	{
		this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		this.setEnabled(false);
	}   //  lockUI

	/**
	 *  Unlock User Interface.
	 *  Called from the Worker when processing is done
	 *  @param pi result of execute ASync call
	 */
	public void unlockUI (ProcessInfo pi)
	{
		this.setEnabled(true);
		this.setCursor(Cursor.getDefaultCursor());
		//
		generateInvoice_complete(pi);
	}   //  unlockUI

	/**
	 *  Is the UI locked (Internal method)
	 *  @return true, if UI is locked
	 */
	public boolean isUILocked()
	{
		return this.isEnabled();
	}   //  isUILocked

	/**
	 *  Method to be executed async.
	 *  Called from the Worker
	 *  @param pi ProcessInfo
	 */
	public void executeASync (ProcessInfo pi)
	{
	}   //  executeASync

}	//	VInvoiceGen
