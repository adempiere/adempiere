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
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JComponent;

import org.adempiere.plaf.AdempiereURLUI;
import org.compiere.apps.ADialog;
import org.compiere.swing.CMenuItem;
import org.compiere.swing.CTextField;
import org.compiere.util.Env;
import org.compiere.util.Msg;

/**
 * 	URL Editor
 *	
 *  @author Jorg Janke
 *  @version $Id: VURL.java,v 1.2 2006/07/30 00:51:28 jjanke Exp $
 *  @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<li> FR [ 146 ] Remove unnecessary class, add support for info to specific column
 *		@see https://github.com/adempiere/adempiere/issues/146
 *  @author Michael McKay, mckayERP@gmail.com
 *  	<li><a href="https://github.com/adempiere/adempiere/issues/2908">#2908</a>Updates to ADempiere Look and Feel
 *
 *  @version 3.9.4
 */
public class VURL extends VEditorAbstract
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3023749380845372419L;

	private final static String uiClassID = "URLUI";

	private int fieldLength;
	
	private CMenuItem mEditor;

	@Override
    public String getUIClassID() {
        return uiClassID ;
    }

	/**
	 * 	IDE Constructor
	 */
	public VURL ()
	{
		this ("URL", false, false, true, 20, 60);
	}	//	VURL
	
	/**
	 *	Detail Constructor
	 *  @param columnName column name
	 *  @param mandatory mandatory
	 *  @param isReadOnly read only
	 *  @param isUpdateable updateable
	 *  @param displayLength display length
	 *  @param fieldLength field length
	 */
	public VURL (String columnName, boolean mandatory, boolean isReadOnly, boolean isUpdateable,
		int displayLength, int fieldLength)
	{
		this(columnName, mandatory, isReadOnly, isUpdateable, displayLength, fieldLength, false);
	}
	
	
	/**
	 *	Detail Constructor
	 *  @param columnName column name
	 *  @param mandatory mandatory
	 *  @param isReadOnly read only
	 *  @param isUpdateable updateable
	 *  @param displayLength display length
	 *  @param fieldLength field length
	 *  @param tableCellEditor true if the editor will be used in a table cell
	 */
	public VURL (String columnName, boolean mandatory, boolean isReadOnly, boolean isUpdateable,
		int displayLength, int fieldLength, boolean tableCellEditor)
	{

		super(columnName, mandatory, isReadOnly, isUpdateable, tableCellEditor);
		this.fieldLength = fieldLength;
		
		((AdempiereURLUI) getUI()).setDisplayLength(displayLength);
		
		//	Popup for Editor
		if (fieldLength > displayLength)
		{			
			mEditor = new CMenuItem (Msg.getMsg(Env.getCtx(), "Editor"), Env.getImageIcon("Editor16.gif"));
			mEditor.addActionListener(this);
			popupMenu.add(mEditor);
		}
	}	//	VURL

	/**
	 *  Return Display Value
	 *  @return value
	 */
	public String getDisplay()
	{
		return ((CTextField) getEditorComponent()).getText();
		
	}   //  getDisplay
	
	/**
	 *	Data Binding to MTable (via GridController)	-	Enter pressed
	 *  @param e event
	 */
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == mEditor)
		{
			String s = Editor.startEditor(this, Msg.translate(Env.getCtx(), getColumnName()), 
				getText(), isEditable(), fieldLength);
			setDisplayBasedOnValue(s);
		}
		else if (e.getSource() == getButtonComponent())
		{
			action_button();
			return;
		}
		
		super.actionPerformed(e);
		
	}	//	actionPerformed

	/**
	 * 	Action button pressed - show URL
	 */
	private void action_button()
	{
		String urlString = getText();
		String message = null;
		if (urlString != null && urlString.length() > 0)
		{
			try
			{
				// Test it and if its ok, open the browser.x 
				new URL(urlString);
				Env.startBrowser(urlString);
				return;
			}
			catch (Exception e)
			{
				message = e.getMessage();
			}
		}
		ADialog.warn(0, this, "URLnotValid", message);
	}	//	action button
	

	/**
	 * 	Set Text
	 *	@param text text
	 */
	public void setText (String text)
	{
		setDisplayBasedOnValue(text);
	}	//	setText

	
	/**
	 * 	Get Text (clear)
	 *	@return text
	 */
	public String getText ()
	{
		
		return ((CTextField) getEditorComponent()).getText();
		
	}	//	getText

	@Override
	public JComponent getComponent() {
		return (CTextField) getEditorComponent();
	}

	@Override
	protected Object getCurrentValue() {
		return ((CTextField) getEditorComponent()).getText();
	}

	@Override
	protected String setDisplayBasedOnValue(Object value) {
		
		if (value == null)
		{
			((CTextField) getEditorComponent()).setText("");
		}
		else
		{
			try {
				// Test the URL
				new URL((String) value);
			} catch (MalformedURLException e) {
				this.setCurrentValueValid(false);
			}
	
			((CTextField) getEditorComponent()).setText((String) value);
		}
		
		return ((CTextField) getEditorComponent()).getText();
		
	}

	@Override
	protected void handleInvalidValue() {
		// No action possible
	}

}	//	VURL
