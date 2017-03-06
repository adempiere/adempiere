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
package org.compiere.apps.form;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.VetoableChangeListener;
import java.util.Properties;
import java.util.logging.Level;

import javax.swing.JLabel;

import org.compiere.apps.ConfirmPanel;
import org.compiere.apps.StatusBar;
import org.compiere.grid.GridController;
import org.compiere.grid.ed.VLocator;
import org.compiere.grid.ed.VLookup;
import org.compiere.grid.ed.VNumber;
import org.compiere.model.MLocatorLookup;
import org.compiere.model.MLookup;
import org.compiere.model.MLookupFactory;
import org.compiere.plaf.CompiereColor;
import org.compiere.swing.CCheckBox;
import org.compiere.swing.CPanel;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Msg;

/**
 * Stocktake - Stocktake data entry form
 * 
 * @author Jorg Janke
 * @version $Id: VTrxMaterial.java,v 1.3 2006/07/30 00:51:28 jjanke Exp $
 * 
 * @author Tony Snook - Physical Inventory Stocktake Enhancement
 */
public class VStocktake extends Stocktake
	implements FormPanel, ActionListener, VetoableChangeListener
{
	private CPanel panel = new CPanel();

	/**	FormFrame			*/
	private FormFrame 		m_frame;
	/** Grid Controller		*/
	private GridController m_gridController;

	//
	private CPanel mainPanel = new CPanel();
	private BorderLayout mainLayout = new BorderLayout();
	private CPanel parameterPanel = new CPanel();
	private JLabel inventoryLabel = new JLabel();
	private VLookup inventoryField;
	private JLabel locatorLabel = new JLabel();
	private VLocator locatorField;
	private JLabel productLabel = new JLabel();
	private VLookup productField;
	private JLabel lineFLabel = new JLabel();
	private VNumber lineFField;
	private JLabel lineTLabel = new JLabel();
	private VNumber lineTField;
	private JLabel aislexLabel = new JLabel();
	private VLookup aislexField;
	private CCheckBox secondCount = new CCheckBox();
	private GridBagLayout parameterLayout = new GridBagLayout();
	private CPanel southPanel = new CPanel();
	private BorderLayout southLayout = new BorderLayout();
	private ConfirmPanel confirmPanel = new ConfirmPanel(true, true, false, true, false, false, true);
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
		CompiereColor.setBackground(panel);
		mainPanel.setLayout(mainLayout);
		mainLayout.setVgap(10);
		parameterPanel.setLayout(parameterLayout);
		//
		inventoryLabel.setText(Msg.translate(Env.getCtx(), "M_Inventory_ID"));
		locatorLabel.setText(Msg.translate(Env.getCtx(), "M_Locator_ID"));
		productLabel.setText(Msg.translate(Env.getCtx(), "M_Product_ID"));
		lineFLabel.setText(Msg.translate(Env.getCtx(), "Line")+" "+Msg.translate(Env.getCtx(), "From"));
		lineTLabel.setText(Msg.translate(Env.getCtx(), "Line")+" "+Msg.translate(Env.getCtx(), "To"));
		aislexLabel.setText(Msg.translate(Env.getCtx(), "X"));
		//
		mainPanel.add(parameterPanel, BorderLayout.NORTH);
		parameterPanel.add(inventoryLabel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 0, 5), 0, 0));
		parameterPanel.add(inventoryField, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 0, 5), 0, 0));
		parameterPanel.add(lineFLabel, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 0, 5), 0, 0));
		parameterPanel.add(lineFField, new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 0, 5), 0, 0));
		parameterPanel.add(lineTLabel, new GridBagConstraints(4, 0, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 0, 5), 0, 0));
		parameterPanel.add(lineTField, new GridBagConstraints(5, 0, 1, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 0, 5), 0, 0));
		parameterPanel.add(secondCount, new GridBagConstraints(6, 0, 1, 1, 0.0, 0.0
				,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 0, 5), 0, 0));

		parameterPanel.add(aislexLabel, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 0, 5), 0, 0));
		parameterPanel.add(aislexField, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 0, 5), 0, 0));
		parameterPanel.add(productLabel, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 0, 5), 0, 0));
		parameterPanel.add(productField, new GridBagConstraints(3, 1, 1, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 0, 5), 0, 0));
		parameterPanel.add(locatorLabel, new GridBagConstraints(4, 1, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 0, 5), 0, 0));
		parameterPanel.add(locatorField, new GridBagConstraints(5, 1, 1, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 0, 5), 0, 0));
		//
		secondCount.setText(Msg.getMsg(Env.getCtx(), "Second Count"));
		secondCount.addActionListener(this);

		confirmPanel.getCustomizeButton().setText(Msg.translate(Env.getCtx(), "CountEntered"));
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
		//  Physical Inventory Document
		String vcode = "M_Inventory.IsStocktake='Y' ";
		MLookup inventoryLookup = MLookupFactory.get(ctx, m_WindowNo, 3563, DisplayType.TableDir, Env.getLanguage(Env.getCtx()), "M_Inventory_ID", 53249, true, vcode);
		inventoryField = new VLookup("M_Inventory_ID", false, false, true, inventoryLookup);
		//  Locator
		MLocatorLookup locatorLookup = new MLocatorLookup(ctx, m_WindowNo);
		locatorField = new VLocator ("M_Locator_ID", false, false, true, locatorLookup, m_WindowNo);
		//  Product
		MLookup productLookup = MLookupFactory.get (ctx, m_WindowNo, 0, 3668, DisplayType.Search);
		productField = new VLookup("M_Product_ID", false, false, true, productLookup);
		//  Aisle(X)
		MLookup aislexLookup = MLookupFactory.get (ctx, m_WindowNo, 1399, DisplayType.Table, Env.getLanguage(Env.getCtx()), "X", 53562, false, null);
		aislexField = new VLookup("X", false, false, true, aislexLookup);
		//  lines
		lineFField = new VNumber();
		lineTField = new VNumber();
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
		super.dynInit(statusBar);
		m_gridController = new GridController();
		m_gridController.initGrid(m_mTab, true, m_WindowNo, null, null);	
		mainPanel.add(m_gridController, BorderLayout.CENTER);
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

		inventoryField = null;
		locatorField = null;
		productField = null;
		aislexField = null;
		lineFField = null;
		lineTField = null;
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
		else if (e.getActionCommand().equals(ConfirmPanel.A_CUSTOMIZE))
			set_counted();
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
		Object inventory = inventoryField.getValue();
		Object locator = locatorField.getValue();
		Object product = productField.getValue();
		Object movementType = aislexField.getValue();
		Object lineFrom = lineFField.getValue();
		Object lineTo = lineTField.getValue();
		Boolean isSecondCount = secondCount.isSelected();
		
		panel.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		refresh(inventory, locator, product, movementType, lineFrom, lineTo, statusBar, isSecondCount);
		Env.sleep(1);
		m_gridController.getTable().autoSize(true);
		panel.setCursor(Cursor.getDefaultCursor());
	}   //  refresh

}   //  VTrxMaterial
