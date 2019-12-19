/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2019 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * or via info@adempiere.net or http://www.adempiere.net/license.html         *
 *****************************************************************************/

package org.compiere.grid.ed;

import java.awt.Container;
import java.awt.event.ActionEvent;

import javax.swing.JComponent;

import org.adempiere.plaf.AdempiereTextUI;
import org.compiere.apps.ScriptEditor;
import org.compiere.swing.CMenuItem;
import org.compiere.util.Env;
import org.compiere.util.Msg;

/**
 *  Text Control (JTextArea embedded in JScrollPane)
 *
 *  @author 	Jorg Janke
 *  @version 	$Id: VText.java,v 1.2 2006/07/30 00:51:28 jjanke Exp $
 *  @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<li> FR [ 146 ] Remove unnecessary class, add support for info to specific column
 *		@see https://github.com/adempiere/adempiere/issues/146
 *  @author Michael McKay, mckayERP@gmail.com
 *  	<li><a href="https://github.com/adempiere/adempiere/issues/2908">#2908</a>Updates to ADempiere Look and Feel
 *  
 *  @version 3.9.4
 */
public class VText extends VEditorAbstract
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 437954563775941704L;

	/**
	 * Define the ui class ID. It will be added to the 
	 * look and feel to define the ui class to use.
	 */
	private final static String uiClassID = "TextUI";

	@Override
    public String getUIClassID() {
        return uiClassID ;
    }
	
	private AdempiereTextUI textUI;

	private int fieldLength;

	
	/**
	 *	Standard Constructor
	 *  @param columnName column name
	 *  @param mandatory mandatory
	 *  @param isReadOnly read only
	 *  @param isUpdateable updateable
	 *  @param displayLength display length
	 *  @param fieldLength field length
	 */
	public VText (String columnName, boolean mandatory, boolean isReadOnly, boolean isUpdateable,
		int displayLength, int fieldLength)
	{
		this(columnName, mandatory, isReadOnly, isUpdateable, displayLength, fieldLength, false);
	}
	
	/**
	 *	Standard Constructor
	 *  @param columnName column name
	 *  @param mandatory mandatory
	 *  @param isReadOnly read only
	 *  @param isUpdateable updateable
	 *  @param displayLength display length
	 *  @param fieldLength field length
	 *  @param tableCellEditor true if the editor will be used in a table cell
	 */
	public VText (String columnName, boolean mandatory, boolean isReadOnly, boolean isUpdateable,
		int displayLength, int fieldLength, boolean tableCellEditor)
	{
		super (columnName, mandatory, isReadOnly, isUpdateable, tableCellEditor);
		
		this.fieldLength = fieldLength;
		textUI = (AdempiereTextUI) getUI();

		textUI.setFieldLength(fieldLength);
		textUI.setDisplayLength(displayLength);

		textUI.addFocusListener(this);
		textUI.addMouseListener(getMouseAdapter());

		if (columnName.equals("Script"))
			menuEditor = new CMenuItem(Msg.getMsg(Env.getCtx(), "Script"), Env.getImageIcon("Script16.gif"));
		else
			menuEditor = new CMenuItem(Msg.getMsg(Env.getCtx(), "Editor"), Env.getImageIcon("Editor16.gif"));
		menuEditor.addActionListener(this);
		popupMenu.add(menuEditor);
	}	//	VText

	private CMenuItem 			menuEditor;

	private Object currentValue;

	/**
	 *	Set Editor to value
	 *  @param value value
	 */
	@Override
	public String setDisplayBasedOnValue(Object value)
	{
		currentValue = value;
		textUI.setText((String) value);
		
		return currentValue == null ? "" : currentValue.toString();
	}	//	setValue

	/**
	 *	ActionListener
	 *  @param e event
	 */
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == menuEditor)
		{
			menuEditor.setEnabled(false);
			String s = null;
			if (super.getColumnName().equals("Script") 
					|| super.getColumnName().endsWith("_Script"))
				s = ScriptEditor.start (
						Env.getFrame(this.getParent()),
						Msg.translate(Env.getCtx(), super.getColumnName()), (String) currentValue, isEditable(), 
						findWindowNo());
			else
				s = Editor.startEditor (this, Msg.translate(Env.getCtx(), super.getColumnName()), 
					(String) currentValue, isEditable(), fieldLength);
			setDisplayBasedOnValue(s);
			menuEditor.setEnabled(true);
		}
		
		super.actionPerformed(e);
		
	}	//	actionPerformed

	private int findWindowNo() {
		Container c = this.getParent();		
		return c != null ? Env.getWindowNo(c) : 0;
	}

	public String getText() {
		return textUI.getText();
	}
	
	public void setText(String text) {
		setValue(text);
	}
	
	@Override
	public JComponent getComponent() {
		return textUI.getComponent();
	}

	@Override
	public String getDisplay() {
		return textUI.getText();
	}

	@Override
	protected Object getCurrentValue() {
		return currentValue;
	}

	@Override
	protected void handleInvalidValue() {
		// No action required
	}

}	//	VText
