/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
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
 * Copyright (C) 2003-2014 E.R.P. Consultores y Asociados, C.A.               *
 * All Rights Reserved.                                                       *
 * Contributor(s): Raul Muñoz www.erpcya.com					              *
 *****************************************************************************/

package org.compiere.minigrid;

import java.awt.Color;
import java.awt.Component;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import org.adempiere.plaf.AdempierePLAF;
import org.compiere.apps.AppsAction;
import org.compiere.swing.CButton;

/**
 *  Check Box Renderer based on Boolean values
 *
 *  @author     Jorg Janke
 *  @author Michael McKay, 
 * 		<li><a href="https://adempiere.atlassian.net/browse/ADEMPIERE-241">ADMPIERE-241</a> Adding Select All checkbox to table header.
 * 		<li>release/380 - fix row selection event handling to fire single event per row selection
 *  
 *  @version    $Id: CheckRenderer.java,v 1.3 2013/11/03 $
 * @author Mario Calderon, mario.calderon@westfalia-it.com, Systemhaus Westfalia, http://www.westfalia-it.com
 * @author Raúl Muñoz, rmunoz@erpcya.com, ERPCyA http://www.erpcya.com
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 */
public final class ButtonRenderer extends DefaultTableCellRenderer
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7687503957742100068L;

	/**
	 *  Constructor
	 */
	public ButtonRenderer()
	{
		super();
		AppsAction act = new AppsAction("Delete", null, false);
		
		m_check = (CButton)act.getButton();
		m_check.setMargin(new Insets(0,0,0,0));
		m_check.setHorizontalAlignment(JLabel.CENTER);
		m_check.setOpaque(true);
		
	}   //  CheckRenderer

	private CButton   m_check = new CButton();

	/**
	 *  Return centered, white Check Box
	 *  @param table
	 *  @param value
	 *  @param isSelected
	 *  @param hasFocus
	 *  @param row
	 *  @param col
	 *  @return Component
	 */
	public Component getTableCellRendererComponent(JTable table, Object value,
		boolean isSelected, boolean hasFocus, int row, int col)
	{

		//  Background & Foreground
		Color bg = AdempierePLAF.getFieldBackground_Normal();
		//  Selected is white on blue in Windows
		if (isSelected && !hasFocus)
			bg = table.getSelectionBackground();
		//  row not selected or field has focus
		
		//  Set Color
		m_check.setBackground(bg);

		//  Value
//		setValue(value.toString());
		return m_check;
	}	//	getTableCellRendererComponent

	/**
	 *  Set Value
	 *  @param value
	 */
	public void setValue(Object value)
	{
		if (value != null)
			m_check.setText(value.toString());
		else
			m_check.setText("");
	}   //  setValue

	public void addActionListener(ActionListener listener) {
		m_check.addActionListener(listener);
	}

}   //  CheckRenderer

