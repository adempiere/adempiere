/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                        *
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
 * Copyright (C) 2003-2007 e-Evolution,SC. All Rights Reserved.               *
 * Contributor(s): Victor Perez www.e-evolution.com                           *
 *****************************************************************************/
package org.eevolution.form;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.VetoableChangeListener;
import java.util.ArrayList;
import java.util.logging.Level;

import org.compiere.apps.ConfirmPanel;
import org.compiere.apps.form.FormFrame;
import org.compiere.apps.form.FormPanel;
import org.compiere.apps.form.VGenPanel;
import org.compiere.grid.ed.VLookup;
import org.compiere.model.MColumn;
import org.compiere.model.MLocator;
import org.compiere.model.MLookup;
import org.compiere.model.MLookupFactory;
import org.compiere.swing.CLabel;
import org.compiere.util.CLogger;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.eevolution.model.MDDOrder;

/**
 *	Create Movement from Distribution Order
 *
 *  @author victor.perez@www.e-evolution.com 
 *  @version $Id: VOrderDistribution,v 1.0 
 */
public class VOrderDistribution extends OrderDistribution implements FormPanel, ActionListener, VetoableChangeListener
{
	private static final long serialVersionUID = 1L;
	
	private VGenPanel panel;

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
		Env.setContext(Env.getCtx(), m_WindowNo, "IsSOTrx", "N");
		panel = new VGenPanel(this, WindowNo, frame);
		try
		{
			super.dynInit();
			dynInit();
			jbInit();
		}
		catch(Exception ex)
		{
			log.log(Level.SEVERE, "init", ex);
		}
	}	//	init
	
	/**
	 * 	Dispose
	 */
	public void dispose()
	{
		if (m_frame != null)
			m_frame.dispose();
		m_frame = null;
	}	//	dispose

	/**	Window No			*/
	private int         	m_WindowNo = 0;
	/**	FormFrame			*/
	private FormFrame 		m_frame;
	
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(VOrderDistribution.class);
	//
	private CLabel lOrder = new CLabel();
	private VLookup fOrder;
	private CLabel lLocator = new CLabel();
	private VLookup fLocator;
	private CLabel lLocatorTo = new CLabel();
	private VLookup fLocatorTo;
	private CLabel lBPartner = new CLabel();
	private VLookup fBPartner;

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
		lOrder.setLabelFor(fOrder);
		lLocator.setLabelFor(fLocator);
		lLocatorTo.setLabelFor(fLocatorTo);
		lBPartner.setLabelFor(fBPartner);
		lBPartner.setText("BPartner");
		panel.getParameterPanel().add(lOrder, null);
		panel.getParameterPanel().add(fOrder, null);
		panel.getParameterPanel().add(lLocator, null);
		panel.getParameterPanel().add(fLocator, null);
		panel.getParameterPanel().add(lLocatorTo, null);
		panel.getParameterPanel().add(fLocatorTo, null);
		panel.getParameterPanel().add(lBPartner, null);
		panel.getParameterPanel().add(fBPartner, null);		
	}	//	jbInit

	/**
	 *	Dynamic Init.
	 *	- Create GridController & Panel
	 *	- AD_Column_ID from C_Order
	 */
	public void dynInit() throws Exception
	{
		// Order Distribution
		MLookup orderL = MLookupFactory.get (Env.getCtx(), m_WindowNo, 0, MColumn.getColumn_ID(MDDOrder.Table_Name, MDDOrder.COLUMNNAME_DD_Order_ID), DisplayType.Search);
		fOrder = new VLookup (MDDOrder.COLUMNNAME_DD_Order_ID, true, false, true, orderL);
		lOrder.setText(Msg.translate(Env.getCtx(), MDDOrder.COLUMNNAME_DD_Order_ID));
		fOrder.addVetoableChangeListener(this);
		lOrder.setVisible(false);
		fOrder.setVisible(false);

		MLookup llocator= MLookupFactory.get (Env.getCtx(), m_WindowNo, 0, 53950, DisplayType.TableDir);
		fLocator = new VLookup (MLocator.COLUMNNAME_M_Locator_ID, true, false, true, llocator);
		lLocator.setText(Msg.translate(Env.getCtx(), "M_Locator_ID"));
		fLocator.addVetoableChangeListener(this);
		m_M_Locator_ID = fLocator.getValue();

		MLookup llocatorto = MLookupFactory.get (Env.getCtx(), m_WindowNo, 0, 53949, DisplayType.TableDir);
		fLocatorTo = new VLookup ("M_LocatorTo_ID", false, false, true, llocatorto);
		lLocatorTo.setText(Msg.translate(Env.getCtx(), "M_LocatorTo_ID"));
		fLocatorTo.addVetoableChangeListener(this);
		m_M_LocatorTo_ID = fLocatorTo.getValue();

		//	C_Order.C_BPartner_ID
		MLookup bpL = MLookupFactory.get (Env.getCtx(), m_WindowNo, 0, 2762, DisplayType.Search);
		fBPartner = new VLookup ("C_BPartner_ID", false, false, true, bpL);
		lBPartner.setText(Msg.translate(Env.getCtx(), "C_BPartner_ID"));
		fBPartner.addVetoableChangeListener(this);		
		panel.getStatusBar().setStatusLine(Msg.getMsg(Env.getCtx(), "InventoryMoveGenerateSel"));
	}	//	dynInit

	public void executeQuery()
	{
		executeQuery(panel.getMiniTable());
	}   //  executeQuery
	
	/**************************************************************************
	 *	Generate Shipments
	 */
	public String generate()
	{

		return generate(panel.getStatusBar(), null);
	}	//	generateShipments

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
		//
		validate();
	}	//	actionPerformed	
	
	public void validate()
	{
		panel.saveSelection();
		
		ArrayList<Integer> selection = getSelection();
		if (selection != null && selection.size() > 0 && isSelectionActive())		
			panel.generate();
		else
			panel.dispose();
	}

	/**
	 *	Vetoable Change Listener - requery
	 *  @param e event
	 */
	public void vetoableChange(PropertyChangeEvent e)
	{
		log.info(e.getPropertyName() + "=" + e.getNewValue());
		if (e.getPropertyName().equals("M_Locator_ID"))
			m_M_Locator_ID = e.getNewValue();
		if (e.getPropertyName().equals("M_LocatorTo_ID"))
			m_M_LocatorTo_ID = e.getNewValue();
		if (e.getPropertyName().equals("C_BPartner_ID"))
		{
			m_C_BPartner_ID = e.getNewValue();
			fBPartner.setValue(m_C_BPartner_ID);	//	display value
		}
		executeQuery();
	}	//	vetoableChange
}	//	VOrderDistribution
