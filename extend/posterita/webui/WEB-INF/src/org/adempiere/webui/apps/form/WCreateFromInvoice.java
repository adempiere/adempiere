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

/**
 * 2007, Modified by Posterita Ltd.
 */

package org.adempiere.webui.apps.form;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;

import org.adempiere.webui.component.ListItem;
import org.adempiere.webui.component.ListModelTable;
import org.adempiere.webui.editor.WEditor;
import org.adempiere.webui.event.ValueChangeEvent;
import org.adempiere.webui.event.ValueChangeListener;
import org.adempiere.webui.event.WTableModelListener;
import org.compiere.model.GridTab;
import org.compiere.model.MInOut;
import org.compiere.model.MInOutLine;
import org.compiere.model.MInvoice;
import org.compiere.model.MInvoiceLine;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.model.MProduct;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Msg;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;

/**
 * Create From Invoice : Based on VCreateFromInvoice
 * 
 * @author  Niraj Sohun
 * @date    Jul 16, 2007
 */

public class WCreateFromInvoice extends WCreateFrom implements EventListener, ValueChangeListener, WTableModelListener
{
	private static final long serialVersionUID = 1L;

	private MInOut m_inout = null;

	private boolean m_actionActive;
	
	/**
	 *  Protected Constructor
	 *  @param mTab MTab
	 */
	
	public WCreateFromInvoice(GridTab mTab)
	{
		super (mTab);
		log.info(mTab.toString());
	}

	@Override
	protected boolean dynInit() throws Exception 
	{
		log.config("");
		
		setTitle(Msg.getElement(Env.getCtx(), "C_Invoice_ID", false) + " .. " + Msg.translate(Env.getCtx(), "CreateFrom"));

		parameterBankPanel.setVisible(false);
		parameterShipmentPanel.setVisible(false);

		initBPartner(true);
		bPartnerField.addValueChangeListner(this);
		
		return true;
	}

	protected void initBPDetails(int C_BPartner_ID)
	{
		log.config("C_BPartner_ID" + C_BPartner_ID);

		// Load Shipments (Receipts) - Completed, Closed
		
		shipmentField.removeEventListener(Events.ON_SELECT, this);
		shipmentField.getChildren().clear();

		// None
		
		KeyNamePair pp = new KeyNamePair(0,"");
		shipmentField.appendItem(pp.getName(), pp);
		
		//	Display
		
		StringBuffer display = new StringBuffer("s.DocumentNo||' - '||")
			.append(DB.TO_CHAR("s.MovementDate", DisplayType.Date, Env.getAD_Language(Env.getCtx())));

		StringBuffer sql = new StringBuffer("SELECT s.M_InOut_ID,").append(display)
			.append(" FROM M_InOut s "
			+ "WHERE s.C_BPartner_ID=? AND s.IsSOTrx='N' AND s.DocStatus IN ('CL','CO')"
			+ " AND s.M_InOut_ID IN "
				+ "(SELECT sl.M_InOut_ID FROM M_InOutLine sl"
				+ " LEFT OUTER JOIN M_MatchInv mi ON (sl.M_InOutLine_ID=mi.M_InOutLine_ID) "
				+ "GROUP BY sl.M_InOut_ID,mi.M_InOutLine_ID,sl.MovementQty "
				+ "HAVING (sl.MovementQty<>SUM(mi.Qty) AND mi.M_InOutLine_ID IS NOT NULL)"
				+ " OR mi.M_InOutLine_ID IS NULL) "
			+ "ORDER BY s.MovementDate");
		
		try
		{
			PreparedStatement pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, C_BPartner_ID);
			ResultSet rs = pstmt.executeQuery();
		
			while (rs.next())
			{
				pp = new KeyNamePair(rs.getInt(1), rs.getString(2));
				shipmentField.appendItem(pp.getName(), pp);
			}
			rs.close();
			pstmt.close();
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql.toString(), e);
		}
		
		shipmentField.setSelectedIndex(0);
		shipmentField.addEventListener(Events.ON_SELECT, this);
	}
	
	public void onEvent(Event e) throws Exception
	{
		super.onEvent(e);
		
		if (m_actionActive)
			return;
		
		m_actionActive = true;
		log.config("Action=" + e.getTarget());

		// Order
		
		if (e.getTarget() == orderField)
		{
			ListItem listitem = orderField.getSelectedItem();
			KeyNamePair pp = (KeyNamePair)listitem.getValue();
			int C_Order_ID = 0;
			
			if (pp != null)
				C_Order_ID = pp.getKey();
			
			// Set Invoice and Shipment to Null
			
			invoiceField.setSelectedIndex(-1);
			shipmentField.setSelectedIndex(-1);
			
			loadOrder(C_Order_ID, true);
		}
		//  Shipment
		else if (e.getTarget() == shipmentField)
		{
			ListItem listitem = shipmentField.getSelectedItem();
			KeyNamePair pp = (KeyNamePair)listitem.getValue();
			int M_InOut_ID = 0;
			
			if (pp != null)
				M_InOut_ID = pp.getKey();
			
			// Set Order and Invoice to Null
			
			orderField.setSelectedIndex(-1);
			invoiceField.setSelectedIndex(-1);
			loadShipment(M_InOut_ID);
		}
		m_actionActive = false;
	}
	
	public void valueChange(ValueChangeEvent evt) 
	{
		log.config(evt.getPropertyName() + "=" + evt.getNewValue());
		
		if (evt == null)
			return;

		if (evt.getSource() instanceof WEditor)
		{
			if (evt.getPropertyName().equals("C_BPartner_ID"))
			{
				int C_BPartner_ID = ((Integer)evt.getNewValue()).intValue();
				initBPartnerOIS (C_BPartner_ID, true);
			}
			tableChanged(null);
		}
	}
	
	private void loadShipment(int M_InOut_ID)
	{
		log.config("M_InOut_ID=" + M_InOut_ID);
		
		m_inout = new MInOut(Env.getCtx(), M_InOut_ID, null);
		p_order = null;
		
		if (m_inout.getC_Order_ID() != 0)
			p_order = new MOrder (Env.getCtx(), m_inout.getC_Order_ID(), null);

		Vector<Vector<Object>> data = new Vector<Vector<Object>>();

		StringBuffer sql = new StringBuffer("SELECT "									// QtyEntered
			+ "l.MovementQty-SUM(NVL(mi.Qty, 0)), l.QtyEntered/l.MovementQty,"
			+ " l.C_UOM_ID, COALESCE(uom.UOMSymbol, uom.Name),"							// 3..4
			+ " l.M_Product_ID, p.Name, po.VendorProductNo, l.M_InOutLine_ID, l.Line,"	// 5..9
			+ " l.C_OrderLine_ID " 														// 10
			+ " FROM M_InOutLine l "
			);   
		
		if (Env.isBaseLanguage(Env.getCtx(), "C_UOM"))
			sql.append(" LEFT OUTER JOIN C_UOM uom ON (l.C_UOM_ID=uom.C_UOM_ID)");
		else
			sql.append(" LEFT OUTER JOIN C_UOM_Trl uom ON (l.C_UOM_ID=uom.C_UOM_ID AND uom.AD_Language='")
				.append(Env.getAD_Language(Env.getCtx())).append("')");
		
		sql.append(" LEFT OUTER JOIN M_Product p ON (l.M_Product_ID=p.M_Product_ID)")
			.append(" INNER JOIN M_InOut io ON (l.M_InOut_ID=io.M_InOut_ID)")
			.append(" LEFT OUTER JOIN M_Product_PO po ON (l.M_Product_ID = po.M_Product_ID AND io.C_BPartner_ID = po.C_BPartner_ID)")
			.append(" LEFT OUTER JOIN M_MatchInv mi ON (l.M_InOutLine_ID=mi.M_InOutLine_ID)")
			
			.append(" WHERE l.M_InOut_ID=? ")	
			.append("GROUP BY l.MovementQty, l.QtyEntered/l.MovementQty, "
				+ "l.C_UOM_ID, COALESCE(uom.UOMSymbol, uom.Name), "
				+ "l.M_Product_ID, p.Name, po.VendorProductNo, l.M_InOutLine_ID, l.Line, l.C_OrderLine_ID ")
			.append("ORDER BY l.Line");

		try
		{
			PreparedStatement pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, M_InOut_ID);
			ResultSet rs = pstmt.executeQuery();
		
			while (rs.next())
			{
				Vector<Object> line = new Vector<Object>(7);
				//line.add(new Boolean(false));           //  0-Selection
			
				BigDecimal qtyMovement = rs.getBigDecimal(1);
				BigDecimal multiplier = rs.getBigDecimal(2);
				BigDecimal qtyEntered = qtyMovement.multiply(multiplier);
				line.add(new Double(qtyEntered.doubleValue()));  //  1-Qty
				
				KeyNamePair pp = new KeyNamePair(rs.getInt(3), rs.getString(4).trim());
				line.add(pp);                           //  2-UOM
				
				pp = new KeyNamePair(rs.getInt(5), rs.getString(6));
				line.add(pp);                           //  3-Product
				line.add(rs.getString(7));				// 4-VendorProductNo
				
				int C_OrderLine_ID = rs.getInt(10);
				
				if (rs.wasNull())
					line.add(null);                     //  5-Order
				else
					line.add(new KeyNamePair(C_OrderLine_ID,"."));
				
				pp = new KeyNamePair(rs.getInt(8), rs.getString(9));
				line.add(pp);                           //  6-Ship
				line.add(null);                     	//  7-Invoice
				
				data.add(line);
			}
			rs.close();
			pstmt.close();
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql.toString(), e);
		}
		loadTableOIS(data);
	}

	@Override
	/**
	 *  List number of rows selected
	 */
	protected void info()
	{
		ListModelTable model = dataTable.getModel();
		int rows = model.size();
		int count = 0;
		
		for (int i = 0; i < rows; i++)
		{
			if (dataTable.getItemAtIndex(i).isSelected())//(((Boolean)model.getDataAt(i, 0)).booleanValue())
				count++;
		}
	}

	/**
	 *  Save - Create Invoice Lines
	 *  @return true if saved
	 */
	protected boolean save()
	{
		log.config("");
		ListModelTable model = dataTable.getModel();
		int rows = model.size();
		
		if (rows == 0)
			return false;

		//  Invoice
		
		Object obj = p_mTab.getValue("C_Invoice_ID");
		
		if (obj == null)
			throw new IllegalStateException("Company Agent or Business Partner has not been selected");
		
		int C_Invoice_ID = ((Integer)p_mTab.getValue("C_Invoice_ID")).intValue();
		MInvoice invoice = new MInvoice (Env.getCtx(), C_Invoice_ID, null);
		log.config(invoice.toString());

		if (p_order != null)
		{
			invoice.setOrder(p_order); // Overwrite header values
			invoice.save();
		}
		
		// Only first time
		if (m_inout != null && m_inout.getM_InOut_ID() != 0 && m_inout.getC_Invoice_ID() == 0)
		{
			m_inout.setC_Invoice_ID(C_Invoice_ID);
			m_inout.save();
		}

		//  Lines
		
		for (int i = 0; i < rows; i++)
		{
			if (dataTable.getItemAtIndex(i).isSelected())//(((Boolean)model.getDataAt(i, 0)).booleanValue())
			{
				// Variable values
				
				Double d = (Double)model.getDataAt(i, 0);              //  1-Qty
				BigDecimal QtyEntered = new BigDecimal(d.doubleValue());
				KeyNamePair pp = (KeyNamePair)model.getDataAt(i, 1);   //  2-UOM
				int C_UOM_ID = pp.getKey();
				pp = (KeyNamePair)model.getDataAt(i, 2);               //  3-Product
				int M_Product_ID = 0;

				if (pp != null)
					M_Product_ID = pp.getKey();
				
				int C_Charge_ID = 0;
				int C_OrderLine_ID = 0;
				pp = (KeyNamePair)model.getDataAt(i, 4);               //  5-OrderLine

				if (pp != null)
					C_OrderLine_ID = pp.getKey();
				
				int M_InOutLine_ID = 0;
				pp = (KeyNamePair)model.getDataAt(i, 5);               //  6-Shipment
				
				if (pp != null)
					M_InOutLine_ID = pp.getKey();
				
				//	Precision of Qty UOM
				int precision = 2;
				
				if (M_Product_ID != 0)
				{
					MProduct product = MProduct.get(Env.getCtx(), M_Product_ID);
					precision = product.getUOMPrecision();
				}

				QtyEntered = QtyEntered.setScale(precision, BigDecimal.ROUND_HALF_DOWN);
				log.fine("Line QtyEntered=" + QtyEntered
					+ ", Product_ID=" + M_Product_ID 
					+ ", OrderLine_ID=" + C_OrderLine_ID + ", InOutLine_ID=" + M_InOutLine_ID);

				// Create new Invoice Line
				
				MInvoiceLine invoiceLine = new MInvoiceLine (invoice);
				invoiceLine.setM_Product_ID(M_Product_ID, C_UOM_ID);	//	Line UOM
				invoiceLine.setQty(QtyEntered);							//	Invoiced/Entered

				// Info
				
				MOrderLine orderLine = null;
				
				if (C_OrderLine_ID != 0)
					orderLine = new MOrderLine (Env.getCtx(), C_OrderLine_ID, null);
				
				MInOutLine inoutLine = null;
				
				if (M_InOutLine_ID != 0)
				{
					inoutLine = new MInOutLine (Env.getCtx(), M_InOutLine_ID, null);
				
					if (orderLine == null && inoutLine.getC_OrderLine_ID() != 0)
					{
						C_OrderLine_ID = inoutLine.getC_OrderLine_ID();
						orderLine = new MOrderLine (Env.getCtx(), C_OrderLine_ID, null);
					}
				}
				else
				{
					MInOutLine[] lines = MInOutLine.getOfOrderLine(Env.getCtx(), 
					C_OrderLine_ID, null, null);
					
					log.fine ("Receipt Lines with OrderLine = #" + lines.length);
					
					if (lines.length > 0)
					{
						for (int j = 0; j < lines.length; j++)
						{
							MInOutLine line = lines[j];
							if (line.getQtyEntered().compareTo(QtyEntered) == 0)
							{
								inoutLine = line;
								M_InOutLine_ID = inoutLine.getM_InOutLine_ID();
								break;
							}
						}
						if (inoutLine == null)
						{
							inoutLine = lines[0]; // First as default
							M_InOutLine_ID = inoutLine.getM_InOutLine_ID();
						}
					}
				} // Get Ship info

				//	Shipment Info
				
				if (inoutLine != null)
				{
					invoiceLine.setShipLine(inoutLine);	// Overwrites
					
					if (inoutLine.getQtyEntered().compareTo(inoutLine.getMovementQty()) != 0)
						invoiceLine.setQtyInvoiced(QtyEntered
								.multiply(inoutLine.getMovementQty())
								.divide(inoutLine.getQtyEntered(), 12, BigDecimal.ROUND_HALF_UP));
				}
				else
					log.fine("No Receipt Line");
					
				//	Order Info
				
				if (orderLine != null)
				{
					invoiceLine.setOrderLine(orderLine); //	Overwrites
					
					if (orderLine.getQtyEntered().compareTo(orderLine.getQtyOrdered()) != 0)
						invoiceLine.setQtyInvoiced(QtyEntered
							.multiply(orderLine.getQtyOrdered())
							.divide(orderLine.getQtyEntered(), 12, BigDecimal.ROUND_HALF_UP));
				}
				else
				{
					log.fine("No Order Line");
					invoiceLine.setPrice();
					invoiceLine.setTax();
				}
				
				if (!invoiceLine.save())
					log.log(Level.SEVERE, "Line NOT created #" + i);
			} // if selected
		} // for all rows

		return true;
	}
}
