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
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;

import org.compiere.model.GridField;
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
	private Integer	prefWidth;
	//  for 3D effect in Windows
	private CButton m_button; 
	private int m_alignment;
	private JLabel	m_label;
	
	public VHeaderRenderer() 
	{
		m_button = new CButton();
		m_button.setMargin(new Insets(0,0,0,0));
		m_button.putClientProperty("Plastic.is3D", Boolean.FALSE);
	}

	/**
	 * Constructor.
	 * 
	 * @param mField
	 * @param col		The position of this column in the table.
	 */
	public VHeaderRenderer(GridField mField) {
		super();
		//	Alignment
		if (DisplayType.isNumeric(mField.getDisplayType()))
			m_alignment = JLabel.RIGHT;
		else if (mField.getDisplayType() == DisplayType.YesNo)
			m_alignment = JLabel.CENTER;
		else
			m_alignment = JLabel.LEFT;
		
		m_label = new JLabel();
		if (mField.getPreferredWidthInListView()!=0) {
			prefWidth = mField.getPreferredWidthInListView();
		}
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

		/**
		 * If VHeaderRenderer is just created with display type as argument
		 */
		if (m_label==null && m_button==null) {
			TableCellRenderer headerRenderer = table.getTableHeader().getDefaultRenderer();
			Component headerComponent = headerRenderer == null ? null :
				headerRenderer.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
			if (value == null)
				headerComponent.setPreferredSize(new Dimension(0,0));
			if (headerComponent instanceof JLabel) {
				((JLabel)headerComponent).setIcon(icon);
				((JLabel)headerComponent).setHorizontalTextPosition(SwingConstants.LEADING);
			}
			return headerComponent;
		}

		/**
		 * If VHeaderRenderer has been created with GridField as argument
		 */
		if (m_button==null) {
			m_label.setHorizontalAlignment(m_alignment);
			
			if (value == null) 
				m_label.setPreferredSize(new Dimension(0,0));
			else {
				m_label.setText(value.toString());
				if (prefWidth!=null && prefWidth>0) {
					m_label.setPreferredSize(new Dimension(prefWidth, m_label.getHeight()));
					m_label.setToolTipText(value.toString());
				}
			}
			m_label.setIcon(icon);
			m_label.setHorizontalTextPosition(SwingConstants.LEADING);
			return m_label;
		} else {
			/**
			 * VHeaderRenderer has been created with no argument
			 */
			if (value == null)
			{
				m_button.setPreferredSize(new Dimension(0,0));
				return m_button;
			}
			m_button.setText(value.toString());
			m_button.setIcon(icon);
			m_button.setHorizontalTextPosition(SwingConstants.LEADING);
			return m_button;
		}
	}	//	getTableCellRendererComponent

}	//	VHeaderRenderer
