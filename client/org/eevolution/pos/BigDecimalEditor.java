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
 * Copyright (C) 2003-2009 e-Evolution,SC. All Rights Reserved.               *
 * Contributor(s): Antonio Ca\x96averal http://www.e-evolution.com                      *
 * Contributor(s): Victor P\x8Erez http://www.e-evolution.com                           *
 *****************************************************************************/
package org.eevolution.pos;

import java.awt.Component;
import java.math.BigDecimal;

import javax.swing.DefaultCellEditor;
import javax.swing.JTable;
import javax.swing.JTextField;

/* 
 * @author antonio.canaveral@..., http://www.e-evolution.com
 * @author victor.perez@..., http://www.e-evolution.com
 * Table cell editor for BigDecimal
 */
public class BigDecimalEditor extends DefaultCellEditor
{
	JTextField textField;

	public BigDecimalEditor()
	{
		super(new JTextField());

		// editorComponent is a protected member of DefaultCellEditor
		textField = (JTextField)editorComponent;

		textField.setHorizontalAlignment(JTextField.RIGHT);
	}


	// the editor should return a BigDecimal
	public Object getCellEditorValue()
	{
		try
		{
			if (textField.getText().trim().length() == 0)
				return BigDecimal.ZERO; 

			return new BigDecimal(textField.getText().trim());
		}
		catch (NumberFormatException e)
		{

			// this line will automatically generate an exception in CustomTableModel
			//return textField.getText();
			return BigDecimal.ONE;
		}
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		this.textField = (JTextField) super.getTableCellEditorComponent(
				table, value, isSelected, row, column);

		this.textField.selectAll();
		return this.textField;
	}

}