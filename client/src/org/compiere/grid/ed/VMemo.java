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

import java.awt.event.ActionEvent;

import javax.swing.JComponent;
import javax.swing.JFrame;

import org.adempiere.plaf.AdempiereMemoUI;
import org.compiere.apps.ScriptEditor;
import org.compiere.swing.CMenuItem;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.SwingEnv;

/**
 *  Text Control (JTextArea embedded in JScrollPane)
 *
 *  @author 	Jorg Janke
 *  @author Michael McKay, mckayERP@gmail.com
 *  	<li><a href="https://github.com/adempiere/adempiere/issues/2908">#2908</a>Updates to ADempiere Look and Feel
 *  
 *  @version 3.9.4
 */
public class VMemo extends VEditorAbstract
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1589654941310687511L;

	private final static String uiClassID = "MemoUI";

	@Override
    public String getUIClassID() {
        return uiClassID ;
    }

	private AdempiereMemoUI memoUI;
	
	private CMenuItem 	menuEditor;
	
	private String columnName;

	private int fieldLength;

	/**
	 *	IDE Bean Constructor
	 */
	public VMemo()
	{
		this("", false, false, true, 60, 4000);
	}	//	VMemo

	/**
	 *	Standard Constructor
	 *  @param columnName
	 *  @param mandatory
	 *  @param isReadOnly
	 *  @param isUpdateable
	 *  @param displayLength
	 *  @param fieldLength
	 */
	public VMemo (String columnName, boolean mandatory, boolean isReadOnly, boolean isUpdateable,
		int displayLength, int fieldLength)
	{
		this(columnName, mandatory, isReadOnly, isUpdateable, displayLength, fieldLength, false);
	}
	
	/**
	 *	Standard Constructor
	 *  @param columnName
	 *  @param mandatory
	 *  @param isReadOnly
	 *  @param isUpdateable
	 *  @param displayLength
	 *  @param fieldLength
	 *  @param tableCellEditor true if the editor will be used in a table cell
	 */
	public VMemo (String columnName, boolean mandatory, boolean isReadOnly, boolean isUpdateable,
		int displayLength, int fieldLength, boolean tableCellEditor)
	{
		super(columnName, mandatory, isReadOnly, isUpdateable, tableCellEditor);
		this.columnName = columnName;
		this.fieldLength = fieldLength;

		memoUI = (AdempiereMemoUI) getUI();
			
		// Originally, the size was set as fieldLength/80, 50
		memoUI.setFieldLength(fieldLength);
		memoUI.setDisplayLength(displayLength);

		memoUI.addFocusListener(this);
		memoUI.addMouseListener(getMouseAdapter());
		
		if (columnName.equals("Script"))
			menuEditor = new CMenuItem(Msg.getMsg(Env.getCtx(), "Script"), Env.getImageIcon("Script16.gif"));
		else
			menuEditor = new CMenuItem(Msg.getMsg(Env.getCtx(), "Editor"), Env.getImageIcon("Editor16.gif"));
		
		menuEditor.addActionListener(this);
		popupMenu.add(menuEditor);
		
	}	//	VMemo


	/**
	 *	ActionListener
	 *  @param e
	 */
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == menuEditor)
		{
			JFrame parentFrame = SwingEnv.getFrame(this);
			if (parentFrame == null)
				parentFrame = SwingEnv.getWindow(0);

			menuEditor.setEnabled(false);
			String s = null;
			if (columnName.equals("Script") || columnName.endsWith("_Script"))
				s = ScriptEditor.start (parentFrame,
						Msg.translate(Env.getCtx(), columnName), memoUI.getText(), isEditable(), 
						SwingEnv.getWindowNo(parentFrame));
			else
				s = Editor.startEditor (this, Msg.translate(Env.getCtx(), columnName), 
					memoUI.getText(), isEditable(), fieldLength);
			menuEditor.setEnabled(true);
			setDisplayBasedOnValue(s);
		}
		else
		{
			super.actionPerformed(e);
		}
	}	//	actionPerformed
	
	@Override
	public JComponent getComponent() {
		return memoUI.getComponent();
	}

	@Override
	public String getDisplay() {
		return memoUI.getText();
	}
	
	@Override
	protected Object getCurrentValue() {
		return memoUI.getText();
	}
	
	@Override
	protected String setDisplayBasedOnValue(Object value) {
		memoUI.setText(value.toString());
		return memoUI.getText();
	}
	
	@Override
	protected void handleInvalidValue() {
		// Not applicable.  Any string is OK.
	}
	
}	//	VMemo
