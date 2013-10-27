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
 * Contributors:                                                              *
 * Colin Rooney (croo) Patch 1605368 Fixed Payment Terms & Only due           *
 *****************************************************************************/
package org.adempiere.webui.apps.form;

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

import org.adempiere.plaf.AdempierePLAF;
import org.adempiere.webui.component.Window;
import org.compiere.apps.AEnv;
import org.compiere.apps.ConfirmPanel;
import org.compiere.grid.ed.AutoCompletion;
import org.compiere.grid.ed.VCheckBox;
import org.compiere.grid.ed.VComboBox;
import org.compiere.grid.ed.VDate;
import org.compiere.grid.ed.VLookup;
import org.compiere.minigrid.ColumnInfo;
import org.compiere.minigrid.IDColumn;
import org.compiere.minigrid.MiniTable;
import org.compiere.model.MClient;
import org.compiere.model.MFactReconciliation;
import org.compiere.model.MLookup;
import org.compiere.model.MLookupFactory;
import org.compiere.model.MRole;
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
import org.compiere.util.KeyNamePair;
import org.compiere.util.Msg;

/**
 *  Create manual match of accounting facts
 */
public class WFactReconcile extends Window
	implements ActionListener, TableModelListener, ASyncProcess
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5188910630217870819L;

	/**
	 *	Initialize Panel
	 */
	public void init ()
	{
		log.info("");
		try
		{
			dynInit();
			jbInit();
		}
		catch(Exception e)
		{
			log.log(Level.SEVERE, "", e);
		}
	}	//	init

	/**	Window No			*/
	private int         	m_WindowNo = 0;

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
	private static CLogger log = CLogger.getCLogger(WFactReconcile.class);

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
	private VComboBox fieldAccount = new VComboBox();
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

	}   //  dynInit

	/**
	 *  Query and create TableInfo
	 */
	private void loadTableInfo()
	{
	}   //  loadTableInfo

	/**
	 * 	Dispose
	 */
	public void dispose()
	{
	}	//	dispose


	/**************************************************************************
	 *  ActionListener
	 *  @param e event
	 */
	public void actionPerformed (ActionEvent e)
	{
	}   //  actionPerformed
	
	/**
	 *	Zoom to target
	 *  @param AD_Window_ID window id
	 *  @param zoomQuery zoom query
	 */
	protected void zoom ()
	{
	}	//	zoom


	private void resetReconciliation() {
	}

	/**
	 *  Table Model Listener
	 *  @param e event
	 */
	public void tableChanged(TableModelEvent e)
	{
	}   //  valueChanged

	/**
	 *  Calculate selected rows.
	 *  - add up selected rows
	 */
	public void calculateSelection()
	{
	}   //  calculateSelection

	/**
	 *  Generate Reconciliation record
	 */
	private void generateReconciliation()
	{
	}   

	/**
	 *  Lock User Interface
	 *  Called from the Worker before processing
	 *  @param pi process info
	 */
	public void lockUI (ProcessInfo pi)
	{
	}   //  lockUI

	/**
	 *  Unlock User Interface.
	 *  Called from the Worker when processing is done
	 *  @param pi process info
	 */
	public void unlockUI (ProcessInfo pi)
	{
	}   //  unlockUI

	/**
	 *  Is the UI locked (Internal method)
	 *  @return true, if UI is locked
	 */
	public boolean isUILocked()
	{
		return true;
	}   //  isLoacked

	/**
	 *  Method to be executed async.
	 *  Called from the ASyncProcess worker
	 *  @param pi process info
	 */
	public void executeASync (ProcessInfo pi)
	{
	}   //  executeASync

} 
