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

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.event.CaretListener;

import org.adempiere.plaf.AdempierePLAF;
import org.adempiere.plaf.AdempiereStringUI;
import org.compiere.model.Obscure;
import org.compiere.swing.CMenuItem;
import org.compiere.swing.CTextField;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Msg;

/**
 *	Data Binding:
 *		VEditors call fireVetoableChange(m_columnName, null, getText());
 *		GridController (for Single-Row) and VCellExitor (for Multi-Row)
 *      listen to Vetoable Change Listener (vetoableChange)
 *		then set the value for that column in the current row in the table
 *
 *  @author 	Jorg Janke
 *  @version 	$Id: VString.java,v 1.2 2006/07/30 00:51:27 jjanke Exp $
 *  @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<li> FR [ 146 ] Remove unnecessary class, add support for info to specific column
 *		@see https://github.com/adempiere/adempiere/issues/146
 *  @author Michael McKay, mckayERP@gmail.com
 *  	<li><a href="https://github.com/adempiere/adempiere/issues/2908">#2908</a>Updates to ADempiere Look and Feel
 *  
 *  @version 3.9.4
 */
public final class VString extends VEditorAbstract
	implements VEditor
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8487860095322876086L;
	
	/**
	 * Define the ui class ID. It will be added to the 
	 * look and feel to define the ui class to use.
	 */
	private final static String uiClassID = "StringUI";

	@Override
    public String getUIClassID() {
        return uiClassID ;
    }

	/** Max Display Length - 60 */
	public static final int MAXDISPLAY_LENGTH = org.compiere.model.GridField.MAXDISPLAY_LENGTH;


	private CTextField editor;
	
	/**
	 *	IDE Bean Constructor for 30 character updateable field
	 */
	public VString()
	{
		this("String", false, false, true, 30, 30, "", null);
	}	//	VString

	/**
	 *	Detail Constructor
	 *  @param columnName column name
	 *  @param mandatory mandatory
	 *  @param isReadOnly read only
	 *  @param isUpdateable updateable
	 *  @param displayLength display length
	 *  @param fieldLength field length
	 *  @param VFormat format
	 *  @param ObscureType obscure type
	 */
	public VString (String columnName, boolean mandatory, boolean isReadOnly, boolean isUpdateable,
		int displayLength, int fieldLength, String VFormat, String ObscureType)
	{
		this(columnName, mandatory, isReadOnly, isUpdateable, 
				displayLength, fieldLength, VFormat, ObscureType, false);
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
	 *  @param ObscureType obscure type
	 *  @param tableCellEditor true if the editor is used in a table cell
	 */
	public VString (String columnName, boolean mandatory, boolean isReadOnly, boolean isUpdateable,
		int displayLength, int fieldLength, String VFormat, String ObscureType, boolean tableCellEditor)
	{
		super(columnName, mandatory, isReadOnly, isUpdateable, tableCellEditor);
		
		editor = (CTextField) getEditorComponent();
		
		//  String specific setup
		if (VFormat == null)
			VFormat = "";
		vFormat = VFormat;
		this.fieldLength = fieldLength;
		if (vFormat.length() != 0 || fieldLength != 0)
			editor.setDocument(new MDocString(vFormat, fieldLength, editor));
		if (vFormat.length() != 0)
			editor.setCaret(new VOvrCaret());
		
		((AdempiereStringUI) getUI()).setDisplayLength(displayLength);

		//
		if (ObscureType != null && ObscureType.length() > 0)
		{
			m_obscure = new Obscure ("", ObscureType);
			m_stdFont = editor.getFont();
			m_obscureFont = new Font("SansSerif", Font.ITALIC, m_stdFont.getSize());
		}

		//	Popup for Editor
		if (fieldLength > displayLength)
		{			
			openEditorDialog = new CMenuItem (Msg.getMsg(Env.getCtx(), "Editor"), Env.getImageIcon("Editor16.gif"));
			openEditorDialog.addActionListener(this);
			popupMenu.add(openEditorDialog);
		}

		
	}	//	VString


	/** Editor Menu Item		*/
	private CMenuItem 			openEditorDialog;
	
	private String				m_oldText = "";
	private String				vFormat;
	/** Field Length				*/
	private int					fieldLength;
	/**	Obcure Setting				*/
	private Obscure				m_obscure = null;
	private Font				m_stdFont = null;
	private Font				m_obscureFont = null;

	/**	Logger	*/
	private static CLogger log = CLogger.getCLogger (VString.class);


	/**
	 *  Return Display Value. Note the display value may exceed
	 *  the field length.
	 *  @return value
	 */
	public String getDisplay()
	{
		return getText();
	}   //  getDisplay


	/**
	 *	Respond to popup menu actions
	 *  @param e event
	 */
	public void actionPerformed(ActionEvent e)
	{
		//  Invoke Editor
		if (e.getSource() == openEditorDialog)
		{
			String s = Editor.startEditor(this, Msg.translate(Env.getCtx(), getColumnName()), 
				getText(), isEditable(), fieldLength);
			setText(s);
		}
		else
			super.actionPerformed(e);
		
	}	//	actionPerformed

	/**
	 *  Feature Request [1707462]
	 *  Set VFormat
	 *  @param strMask mask
	 *  @author fer_luck
	 */
	public void setVFormat (String strMask)
	{
		vFormat = strMask;
		//Get the actual caret from the field, if there's no
		//caret then just catch the exception and continue
		//creating the new caret.
		try{
			CaretListener [] cl = editor.getCaretListeners();
			editor.removeCaretListener(cl[0]);
		} catch(ClassCastException ex ){
			log.fine("VString.setVFormat - No caret Listeners");
		}
		
		//hengsin: [ adempiere-Bugs-1891037 ], preserve current data before change of format		
		String s = getText();
		editor.setDocument(new MDocString(vFormat, fieldLength, editor));
		setText(s);
	}   //  setVFormat
	
	/**
	 * 	Set Text (optionally obscured)
	 *	@param text text
	 */
	public void setText (String text)
	{
		
		if (m_obscure != null && !isInFocus())
		{
			editor.setFont(m_obscureFont);
			editor.setText (m_obscure.getObscuredValue(text));
			editor.setForeground(Color.gray);
		}
		else
		{
			if (m_stdFont != null)
			{
				editor.setFont(m_stdFont);
				editor.setForeground(AdempierePLAF.getTextColor_Normal());
			}
			editor.setText (text);
		}
	}	//	setText

	
	/**
	 * 	Get Text (clear)
	 *	@return text
	 */
	public String getText ()
	{
		String text = editor.getText();
		if (m_obscure != null && text != null && text.length() > 0)
		{
			if (text.equals(m_obscure.getObscuredValue()))
				text = m_obscure.getClearValue();
		}
		return text;
	}	//	getText

	/**
	 *  Feature Request [1707462]
	 *  Get VFormat
	 *  @return strMask mask
	 *  @author fer_luck
	 */
	public String getVFormat ()
	{
		return this.vFormat;
	}	//	getVFormat
	
	@Override
	public void setFont(Font f) {
		super.setFont(f);
		m_stdFont = f;
		m_obscureFont = new Font("SansSerif", Font.ITALIC, m_stdFont.getSize());
	}

	@Override
	protected Object getCurrentValue() {
		String clear = getText();
		if (clear.length() > fieldLength)
			clear = clear.substring(0, fieldLength);
		return clear.isEmpty() ? null : clear;
	}

	@Override
	protected String setDisplayBasedOnValue(Object value) {
		
		// For a the string editor, the value is the string.
		if (value == null)
			m_oldText = "";
		else
			m_oldText = value.toString();
		
		// Obscure the text as required. Focus dependent.
		setText (m_oldText);
		
		if (!editor.isEditable() || !editor.isEnabled())
			editor.setCaretPosition(0);
		else
			editor.selectAll();  // User input will overwrite the current text

		return editor.getText();
	}

	@Override
	protected void handleInvalidValue() {
		// It is what it is. Can't do anything to fix it here.
	}

}	//	VString