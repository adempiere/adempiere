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
import java.sql.*;
import java.util.*;
import java.util.logging.*;
import javax.swing.*;
import org.compiere.apps.*;
import org.compiere.grid.*;
import org.compiere.grid.ed.*;
import org.compiere.model.*;
import org.compiere.plaf.*;
import org.compiere.swing.*;
import org.compiere.util.*;

/**
 * Material Transaction History
 *
 * @author Jorg Janke
 * @version $Id: VTrxMaterial.java,v 1.3 2006/07/30 00:51:28 jjanke Exp $
 */
public class VTrxMaterial extends CPanel
	implements FormPanel, ActionListener, VetoableChangeListener
{
	/**	Window No			*/
	private int         	m_WindowNo = 0;
	/**	FormFrame			*/
	private FormFrame 		m_frame;

	/** GridController          */
	private GridController  m_gridController = null;
	/** MWindow                 */
	private GridWindow         m_mWindow = null;
	/** MTab pointer            */
	private GridTab            m_mTab = null;

	private MQuery          m_staticQuery = null;
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(VTrxMaterial.class);
	//
	private CPanel mainPanel = new CPanel();
	private BorderLayout mainLayout = new BorderLayout();
	private CPanel parameterPanel = new CPanel();
	private JLabel orgLabel = new JLabel();
	private VLookup orgField;
	private JLabel locatorLabel = new JLabel();
	private VLocator locatorField;
	private JLabel productLabel = new JLabel();
	private VLookup productField;
	private JLabel dateFLabel = new JLabel();
	private VDate dateFField;
	private JLabel dateTLabel = new JLabel();
	private VDate dateTField;
	private JLabel mtypeLabel = new JLabel();
	private VLookup mtypeField;
	private GridBagLayout parameterLayout = new GridBagLayout();
	private CPanel southPanel = new CPanel();
	private BorderLayout southLayout = new BorderLayout();
	private ConfirmPanel confirmPanel = new ConfirmPanel(true, true, false, false, false, true, true);
	private StatusBar statusBar = new StatusBar();


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
			dynParameter();
			jbInit();
			dynInit();
			frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
			frame.getContentPane().add(statusBar, BorderLayout.SOUTH);
		}
		catch(Exception ex)
		{
			log.log(Level.SEVERE, "", ex);
		}
	}	//	init


	/**
	 *  Static Init
	 *  @throws Exception
	 */
	void jbInit() throws Exception
	{
		CompiereColor.setBackground(this);
		mainPanel.setLayout(mainLayout);
		mainLayout.setVgap(10);
		parameterPanel.setLayout(parameterLayout);
		//
		orgLabel.setText(Msg.translate(Env.getCtx(), "AD_Org_ID"));
		locatorLabel.setText(Msg.translate(Env.getCtx(), "M_Locator_ID"));
		productLabel.setText(Msg.translate(Env.getCtx(), "Product"));
		dateFLabel.setText(Msg.translate(Env.getCtx(), "DateFrom"));
		dateTLabel.setText(Msg.translate(Env.getCtx(), "DateTo"));
		mtypeLabel.setText(Msg.translate(Env.getCtx(), "MovementType"));
		//
		mainPanel.add(parameterPanel, BorderLayout.NORTH);
		parameterPanel.add(orgLabel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 0, 5), 0, 0));
		parameterPanel.add(orgField, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 0, 5), 0, 0));
		parameterPanel.add(mtypeLabel, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 0, 5), 0, 0));
		parameterPanel.add(mtypeField, new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 0, 5), 0, 0));
		parameterPanel.add(dateFLabel, new GridBagConstraints(4, 0, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 0, 5), 0, 0));
		parameterPanel.add(dateFField, new GridBagConstraints(5, 0, 1, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 0, 5), 0, 0));

		parameterPanel.add(locatorLabel, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 0, 5), 0, 0));
		parameterPanel.add(locatorField, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 0, 5), 0, 0));
		parameterPanel.add(productLabel, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 0, 5), 0, 0));
		parameterPanel.add(productField, new GridBagConstraints(3, 1, 1, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 0, 5), 0, 0));
		parameterPanel.add(dateTLabel, new GridBagConstraints(4, 1, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 0, 5), 0, 0));
		parameterPanel.add(dateTField, new GridBagConstraints(5, 1, 1, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 0, 5), 0, 0));
		//
		southPanel.setLayout(southLayout);
		southPanel.add(confirmPanel, BorderLayout.NORTH);
		southPanel.add(statusBar, BorderLayout.SOUTH);
		mainPanel.add(southPanel, BorderLayout.SOUTH);
	}   //  jbInit

	/**
	 *  Initialize Parameter fields
	 *  @throws Exception if Lookups cannot be initialized
	 */
	private void dynParameter() throws Exception
	{
		Properties ctx = Env.getCtx();
		//  Organization
		MLookup orgLookup = MLookupFactory.get (ctx, m_WindowNo, 0, 3660, DisplayType.TableDir);
		orgField = new VLookup("AD_Org_ID", false, false, true, orgLookup);
	//	orgField.addVetoableChangeListener(this);
		//  Locator
		MLocatorLookup locatorLookup = new MLocatorLookup(ctx, m_WindowNo);
		locatorField = new VLocator ("M_Locator_ID", false, false, true, locatorLookup, m_WindowNo);
	//	locatorField.addVetoableChangeListener(this);
		//  Product
		MLookup productLookup = MLookupFactory.get (ctx, m_WindowNo, 0, 3668, DisplayType.Search);
		productField = new VLookup("M_Product_ID", false, false, true, productLookup);
		productField.addVetoableChangeListener(this);
		//  Movement Type
		MLookup mtypeLookup = MLookupFactory.get (ctx, m_WindowNo, 0, 3666, DisplayType.List);
		mtypeField = new VLookup("MovementType", false, false, true, mtypeLookup);
		//  Dates
		dateFField = new VDate("DateFrom", false, false, true, DisplayType.Date, Msg.getMsg(Env.getCtx(), "DateFrom"));
		dateTField = new VDate("DateTo", false, false, true, DisplayType.Date, Msg.getMsg(Env.getCtx(), "DateTo"));
		//
		confirmPanel.addActionListener(this);
		statusBar.setStatusLine("");
	}   //  dynParameter

	/**
	 *  Dynamic Layout (Grid).
	 * 	Based on AD_Window: Material Transactions
	 */
	private void dynInit()
	{
		m_staticQuery = new MQuery();
		m_staticQuery.addRestriction("AD_Client_ID", MQuery.EQUAL, Env.getAD_Client_ID(Env.getCtx()));
		int AD_Window_ID = 223;		//	Hardcoded
		GridWindowVO wVO = AEnv.getMWindowVO (m_WindowNo, AD_Window_ID, 0);
		if (wVO == null)
			return;
		m_mWindow = new GridWindow (wVO);
		m_mTab = m_mWindow.getTab(0);
		m_mWindow.initTab(0);
		//
		m_gridController = new GridController();
		m_gridController.initGrid(m_mTab, true, m_WindowNo, null, null);
		mainPanel.add(m_gridController, BorderLayout.CENTER);
		//
		m_mTab.setQuery(MQuery.getEqualQuery("1", "2"));
		m_mTab.query(false);
		statusBar.setStatusLine(" ", false);
		statusBar.setStatusDB(" ");
	}   //  dynInit


	/**
	 * 	Dispose
	 */
	public void dispose()
	{
		if (m_gridController != null)
			m_gridController.dispose();
		m_gridController = null;
		m_mTab = null;
		if (m_mWindow != null)
			m_mWindow.dispose();
		m_mWindow = null;

		orgField = null;
		locatorField = null;
		productField = null;
		mtypeField = null;
		dateFField = null;
		dateTField = null;
		//
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
		if (e.getActionCommand().equals(ConfirmPanel.A_CANCEL))
			dispose();
		else if (e.getActionCommand().equals(ConfirmPanel.A_REFRESH)
				|| e.getActionCommand().equals(ConfirmPanel.A_OK))
			refresh();
		else if (e.getActionCommand().equals(ConfirmPanel.A_ZOOM))
			zoom();
	}   //  actionPerformed

	
	/**************************************************************************
	 *  Property Listener
	 *  @param e event
	 */
	public void vetoableChange (PropertyChangeEvent e)
	{
		if (e.getPropertyName().equals("M_Product_ID"))
			productField.setValue(e.getNewValue());
	}   //  vetoableChange


	
	/**************************************************************************
	 *  Refresh - Create Query and refresh grid
	 */
	private void refresh()
	{
		/**
		 *  Create Where Clause
		 */
		MQuery query = m_staticQuery.deepCopy();
		//  Organization
		Object value = orgField.getValue();
		if (value != null && value.toString().length() > 0)
			query.addRestriction("AD_Org_ID", MQuery.EQUAL, value);
		//  Locator
		value = locatorField.getValue();
		if (value != null && value.toString().length() > 0)
			query.addRestriction("M_Locator_ID", MQuery.EQUAL, value);
		//  Product
		value = productField.getValue();
		if (value != null && value.toString().length() > 0)
			query.addRestriction("M_Product_ID", MQuery.EQUAL, value);
		//  MovementType
		value = mtypeField.getValue();
		if (value != null && value.toString().length() > 0)
			query.addRestriction("MovementType", MQuery.EQUAL, value);
		//  DateFrom
		Timestamp ts = (Timestamp)dateFField.getValue();
		if (ts != null)
			query.addRestriction("TRUNC(MovementDate)", MQuery.GREATER_EQUAL, ts);
		//  DateTO
		ts = (Timestamp)dateTField.getValue();
		if (ts != null)
			query.addRestriction("TRUNC(MovementDate)", MQuery.LESS_EQUAL, ts);
		log.info( "VTrxMaterial.refresh query=" + query.toString());

		/**
		 *  Refresh/Requery
		 */
		setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		statusBar.setStatusLine(Msg.getMsg(Env.getCtx(), "StartSearch"), false);
		//
		m_mTab.setQuery(query);
		m_mTab.query(false);
		//
		setCursor(Cursor.getDefaultCursor());
		int no = m_mTab.getRowCount();
		statusBar.setStatusLine(" ", false);
		statusBar.setStatusDB(Integer.toString(no));
	}   //  refresh

	/**
	 *  Zoom
	 */
	private void zoom()
	{
		log.info("");
		//
		int AD_Window_ID = 0;
		String ColumnName = null;
		String SQL = null;
		//
		int lineID = Env.getContextAsInt(Env.getCtx(), m_WindowNo, "M_InOutLine_ID");
		if (lineID != 0)
		{
			log.fine("M_InOutLine_ID=" + lineID);
			if (Env.getContext(Env.getCtx(), m_WindowNo, "MovementType").startsWith("C"))
				AD_Window_ID = 169;     //  Customer
			else
				AD_Window_ID = 184;     //  Vendor
			ColumnName = "M_InOut_ID";
			SQL = "SELECT M_InOut_ID FROM M_InOutLine WHERE M_InOutLine_ID=?";
		}
		else
		{
			lineID = Env.getContextAsInt(Env.getCtx(), m_WindowNo, "M_InventoryLine_ID");
			if (lineID != 0)
			{
				log.fine("M_InventoryLine_ID=" + lineID);
				AD_Window_ID = 168;
				ColumnName = "M_Inventory_ID";
				SQL = "SELECT M_Inventory_ID FROM M_InventoryLine WHERE M_InventoryLine_ID=?";
			}
			else
			{
				lineID = Env.getContextAsInt(Env.getCtx(), m_WindowNo, "M_MovementLine_ID");
				if (lineID != 0)
				{
					log.fine("M_MovementLine_ID=" + lineID);
					AD_Window_ID = 170;
					ColumnName = "M_Movement_ID";
					SQL = "SELECT M_Movement_ID FROM M_MovementLine WHERE M_MovementLine_ID=?";
				}
				else
				{
					lineID = Env.getContextAsInt(Env.getCtx(), m_WindowNo, "M_ProductionLine_ID");
					if (lineID != 0)
					{
						log.fine("M_ProductionLine_ID=" + lineID);
						AD_Window_ID = 191;
						ColumnName = "M_Production_ID";
						SQL = "SELECT M_Production_ID FROM M_ProductionLine WHERE M_ProductionLine_ID=?";
					}
					else
						log.fine("Not found WindowNo=" + m_WindowNo);
				}
			}
		}
		if (AD_Window_ID == 0)
			return;

		//  Get Parent ID
		int parentID = 0;
		try
		{
			PreparedStatement pstmt = DB.prepareStatement(SQL, null);
			pstmt.setInt(1, lineID);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
				parentID = rs.getInt(1);
			rs.close();
			pstmt.close();
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, SQL, e);
		}
		MQuery query = MQuery.getEqualQuery(ColumnName, parentID);
		log.config("AD_Window_ID=" + AD_Window_ID + " - " + query);
		if (parentID == 0)
			log.log(Level.SEVERE, "No ParentValue - " + SQL + " - " + lineID);

		//  Zoom
		setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		AWindow frame = new AWindow();
		if (!frame.initWindow(AD_Window_ID, query))
		{
			setCursor(Cursor.getDefaultCursor());
			return;
		}
		AEnv.addToWindowManager(frame);
		AEnv.showCenterScreen(frame);
		frame = null;
		setCursor(Cursor.getDefaultCursor());
	}   //  zoom

}   //  VTrxMaterial
