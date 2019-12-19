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

import java.util.Arrays;

import javax.swing.JComponent;
import javax.swing.UIDefaults;

import org.adempiere.plaf.AdempierePasswordUI;
import org.compiere.swing.CPassword;

/**
 *	Data Binding:
 *		VEditors call fireVetoableChange(m_columnName, null, getText());
 *		GridController (for Single-Row) and VCellExitor (for Multi-Row)
 *      listen to Vetoable Change Listener (vetoableChange)
 *		then set the value for that column in the current row in the table
 *
 *  @author 	Jorg Janke
  *  @author Michael McKay, mckayERP@gmail.com
 *  	<li><a href="https://github.com/adempiere/adempiere/issues/2908">#2908</a>Updates to ADempiere Look and Feel
*  
 *  @version 3.9.4
 */
public final class VPassword extends VEditorAbstract
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1659042515884579907L;

	private final static String uiClassID = "PasswordUI";
	
    /**
     * Gets the class ID for a UI. Should point the
     * default UI to Adempiere + UICLassID = AdempierePasswordUI
     *
     * @return the string "PasswordUI"
     * @see JComponent#getUIClassID
     * @see UIDefaults#getUI
     */
	@Override
    public String getUIClassID() {
        return uiClassID ;
    }
	private AdempierePasswordUI passwordUI;
	
	CPassword passwordEditor;

	/** The value of the password, held as an array of char
	 *  The array must be zeroed on disposal and should be
	 *  zeroed after use to prevent the value appearing in 
	 *  the heap.
	 */
	private char[] insecurePassword;

	/**
	 *	IDE Bean Constructor for 30 character updateable field
	 */
	public VPassword()
	{
		this("Password", false, false, true, 30, 30, "");
	}	//	VPassword

	/**
	 *	Detail Constructor
	 *  @param columnName column name
	 *  @param mandatory mandatory
	 *  @param isReadOnly read only
	 *  @param isUpdateable updateable
	 *  @param displayLength display length
	 *  @param fieldLength field length
	 *  @param VFormat format
	 */
	public VPassword (String columnName, boolean mandatory, boolean isReadOnly, boolean isUpdateable,
		int displayLength, int fieldLength, String VFormat)
	{
		this(columnName, mandatory, isReadOnly, isUpdateable, displayLength, fieldLength, VFormat, false);
	}
	
	/**
	 *	Detail Constructor
	 *  @param columnName column name
	 *  @param mandatory mandatory
	 *  @param isReadOnly read only
	 *  @param isUpdateable updateable
	 *  @param displayLength display length
	 *  @param fieldLength field length
	 *  @param VFormat format
	 *  @param tableCellEditor true if the editor will be used in a table cell
	 */
	public VPassword (String columnName, boolean mandatory, boolean isReadOnly, boolean isUpdateable,
		int displayLength, int fieldLength, String VFormat, boolean tableCellEditor)
	{
		super(columnName, mandatory, isReadOnly, isUpdateable, tableCellEditor);		

		passwordUI = (AdempierePasswordUI) getUI();
		passwordUI.setFormat(VFormat, fieldLength);
		passwordUI.setDisplayLength(displayLength);
		
		if (VFormat != null && VFormat.length() != 0)
			passwordUI.setCaret(new VOvrCaret());

		this.setEditorComponent(passwordEditor);

	}	//	VPassword

	/** 
	 *  Gets the current password value, whether committed or not.
	 *  For increased safety, the characters in the returned string 
	 *  should be overwritten with 0 after use. Use the helper
	 *  function clearPassword()
	 */
	@Override
	public Object getCurrentValue()
	{
		insecurePassword = passwordEditor.getPassword();
		return insecurePassword;
	}	//	getValue

	/**
	 *  Return Display Value - for the password editor, this will 
	 *  be the overlaid value
	 *  @return value
	 */
	@Override
	public String getDisplay()
	{
		return String.valueOf(passwordEditor.getDisplay());
	}   //  getDisplay


	@Override
	public JComponent getComponent() {
		return passwordEditor;
	}

	@Override
	protected String setDisplayBasedOnValue(Object value) {
		
		passwordEditor.setValue(value);
		return passwordEditor.getDisplay();
	}

	@Override
	protected void handleInvalidValue() {
		// This method shouldn't be called
		// but in case;
		cleanPassword();
		
	}

	/**
	 * Clean the password from memory.
	 */
	public void cleanPassword()
	{
		if (insecurePassword != null)
			Arrays.fill(insecurePassword, '0');
	}
	
	@Override
	public void dispose() {
		cleanPassword();
		super.dispose();
	}

}	//	VPassword

