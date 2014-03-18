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
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.AbstractButton;
import javax.swing.Icon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import org.adempiere.plaf.AdempierePLAF;
import org.compiere.minigrid.IDColumn;
import org.compiere.minigrid.MiniTable;
import org.compiere.model.GridField;
import org.compiere.swing.CButton;
import org.compiere.swing.CTable;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;

/**
 *  Table Header Renderer based on Display Type for alignment
 *
 *  @author 	Jorg Janke
 *  
 *  @author Michael McKay, 
 * 		<li><a href="https://adempiere.atlassian.net/browse/ADEMPIERE-241">ADMPIERE-241</a> Adding Select All checkbox to table header.
 * 			Based on work by Michael Dunn as <a hfre="http://www.coderanch.com/t/343795/GUI/java/Check-Box-JTable-header">posted in coderanch.com</a>
 * 		<li>release/380 - fix row selection event handling to fire single event per row selection
 * 
 *  @version 	$Id: VHeaderRenderer.java,v 1.3 2013/11/03 00:51:28
 */
public final class VHeaderRenderer implements TableCellRenderer, MouseListener, ChangeListener 
{
	private Integer	prefWidth;
	
	//  for 3D effect in Windows and single selection or no selection
	private CButton m_button; 
	// The Multi-Selection renderer 
	private JCheckBox   m_check; 

	private int m_alignment;
	private JLabel	m_label;
	private boolean m_multiSelection = false;

	private JTable m_table;
	private int m_column;

	private boolean mousePressed;

	private boolean m_headerOnly;
		
	public VHeaderRenderer() 
	{
		// Assumes no multi-selection
		m_check = null;
		m_button = new CButton();
		m_button.setMargin(new Insets(0,0,0,0));
		m_button.putClientProperty("Plastic.is3D", Boolean.FALSE);
	}
	
	/**
	 * Constructor for multi-selection.  To be used as the header
	 * for the IDColumnRenderer in multi-selection.
	 * 
	 * @param multiSelection True for multi-selection. Else defaults.
	 */	
	public VHeaderRenderer(boolean multiSelection)
	{
		super();
		m_multiSelection = multiSelection;
		if (m_multiSelection)
		{
			m_button = null;
			m_check = new JCheckBox();
			m_check.setMargin(new Insets(0,0,0,0));
			m_check.setHorizontalAlignment(JLabel.CENTER);
			m_check.addItemListener(new MyItemListener());
			m_alignment = JLabel.CENTER;
		}
		else
			m_alignment = JLabel.RIGHT;

	}

	/**
	 * Constructor.
	 * 
	 * @param mField
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
	
	  class MyItemListener implements ItemListener  
	  {  
	    public void itemStateChanged(ItemEvent e) {  
	      Object source = e.getSource();  
	      //  
	      if (source instanceof AbstractButton == false) return;
	      if (m_headerOnly) {
	    	  m_headerOnly = false;
	    	  return;
	      }
	      //  
	      boolean checked = e.getStateChange() == ItemEvent.SELECTED;  
	      for(int x = 0, y = m_table.getRowCount(); x < y; x++)  
	      { 
	    	Object data = m_table.getValueAt(x, m_column);
			if (data instanceof IDColumn)
			{
				IDColumn record = (IDColumn)data;
				record.setSelected(checked);
				m_table.setValueAt(record,x,m_column);
			}
			else if (data instanceof Boolean)
			{
				boolean record = (Boolean) data;
				record = checked;
				m_table.setValueAt(record, x, m_column);
			}
	      }
	      ((MiniTable) m_table).fireRowSelectionEvent();
	    }
	  }
	
	/**
	 * Change listener for the checkboxes in IDColumns 
	 */
    public void stateChanged(ChangeEvent e) 
    {  
      Object source = e.getSource();  
      //
      if (source instanceof AbstractButton == false) return;
      //
      
      boolean checked = ((JCheckBox) source).isSelected();
      if (!checked && m_check.isSelected())
      {
    	  m_headerOnly = true;
    	  m_check.setSelected(false);
    	  m_table.getTableHeader().repaint();
      }
    }
    
    
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
		m_table = table;
		m_column = column;
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
		if (m_label==null && m_button==null && m_check==null) {
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
		if (m_button==null && m_check==null) {
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
		}
		
		/**
		 * VHeaderRenderer has been created with multi-selection true
		 */
		if (m_button==null) {
		    if (table != null) {
		        JTableHeader header = table.getTableHeader();  
		        if (header != null) {  
			        m_check.setForeground(header.getForeground());  
			        m_check.setBackground(header.getBackground());  
			        m_check.setBorder(new MatteBorder(0, 0, 1, 1, AdempierePLAF.getSecondary1()) );
			        m_check.setFont(header.getFont()); 
			        m_check.setBorderPainted(true);
			        m_check.setEnabled(true);
			        header.addMouseListener(this); 
		        }
		    }
			return m_check;
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

	protected void handleClickEvent(MouseEvent e) {  
		JTableHeader header = (JTableHeader)(e.getSource());  
	    JTable tableView = header.getTable();  
	    TableColumnModel columnModel = tableView.getColumnModel();  
	    int viewColumn = columnModel.getColumnIndexAtX(e.getX());  
	    int column = tableView.convertColumnIndexToModel(viewColumn);  
	   
	    if (viewColumn == m_column && e.getClickCount() == 1 && column != -1) {  
	    	m_check.doClick();  
	    }  
	}
	  
	@Override
	public void mouseClicked(MouseEvent e) {
	    if (mousePressed) {  
		      mousePressed=false;  
		      handleClickEvent(e);
		      e.consume();
	    }
    }

	@Override
	public void mouseEntered(MouseEvent e) {
    }

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
	    mousePressed = true;  
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}	//	VHeaderRenderer
