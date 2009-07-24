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
package org.compiere.grid.ed;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Insets;

import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.TableCellRenderer;

import org.compiere.swing.CButton;
import org.compiere.swing.CTable;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;

/**
 *  Table Header Renderer based on Display Type for aligmnet
 *
 *  @author 	Jorg Janke
 *  @version 	$Id: VHeaderRenderer.java,v 1.2 2006/07/30 00:51:28 jjanke Exp $
 */
public final class VHeaderRenderer implements TableCellRenderer
{
	public VHeaderRenderer() 
	{
		m_button = new CButton();
		m_button.setMargin(new Insets(0,0,0,0));
		m_button.putClientProperty("Plastic.is3D", Boolean.FALSE);
	}
	
	/**
	 *	Constructor
	 *  @param displayType
	 */
	public VHeaderRenderer(int displayType)
	{
		super();
		//	Alignment
		if (DisplayType.isNumeric(displayType))
			m_alignment = JLabel.RIGHT;
		else if (displayType == DisplayType.YesNo)
			m_alignment = JLabel.CENTER;
		else
			m_alignment = JLabel.LEFT;
	}	//	VHeaderRenderer

	//  for 3D effect in Windows
	private CButton m_button; 
	
	private int m_alignment;

	/**
	 *	Get TableCell RendererComponent
	 *  @param table
	 *  @param value
	 *  @param isSelected
	 *  @param hasFocus
	 *  @param row
	 *  @param column
	 *  @return Button
	 */
	public Component getTableCellRendererComponent(JTable table, Object value,
		boolean isSelected, boolean hasFocus, int row, int column)
	{
	//	Log.trace(this,10, "VHeaderRenderer.getTableCellRendererComponent", value==null ? "null" : value.toString());
		//  indicator for invisible column
		Icon icon = null;
		if (table instanceof CTable)
		{
			CTable cTable = (CTable)table;
			if (cTable.getSortColumn() == table.convertColumnIndexToModel(column))
			{
				icon = cTable.isSortAscending() 
					? Env.getImageIcon2("uparrow")
					: Env.getImageIcon2("downarrow");
			}
		}
		
		TableCellRenderer headerRenderer = table.getTableHeader().getDefaultRenderer();
		Component headerComponent = headerRenderer == null ? null :
			headerRenderer.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		if (headerComponent != null && headerComponent instanceof JComponent) {
			if (headerComponent instanceof JLabel ) {
				JLabel label = (JLabel)headerComponent;
				label.setHorizontalAlignment(m_alignment);
				if (value == null) 
					label.setPreferredSize(new Dimension(0,0));
				else
					label.setText(value.toString());
				label.setIcon(icon);
				label.setHorizontalTextPosition(SwingConstants.LEADING);
				return label;
			}
			m_button.setBorder(((JComponent)headerComponent).getBorder());
		} else {
			m_button.setBorder(UIManager.getBorder("TableHeader.cellBorder"));
		}
		
		if (value == null)
		{
			m_button.setPreferredSize(new Dimension(0,0));
			return m_button;
		}
		m_button.setText(value.toString());
		m_button.setIcon(icon);
		m_button.setHorizontalTextPosition(SwingConstants.LEADING);
		return m_button;
	}	//	getTableCellRendererComponent

}	//	VHeaderRenderer
