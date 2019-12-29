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
import javax.swing.JFrame;

import org.adempiere.plaf.AdempiereTextLongUI;
import org.compiere.swing.CMenuItem;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.SwingEnv;

/**
 *	Long Text (CBLOB) Editor 	
 *	
 *  @author Jorg Janke
 *  @version $Id: VTextLong.java,v 1.2 2006/07/30 00:51:28 jjanke Exp $
 *  @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<li> FR [ 146 ] Remove unnecessary class, add support for info to specific column
 *		@see https://github.com/adempiere/adempiere/issues/146
 *  @author Michael McKay, mckayERP@gmail.com
 *  	<li><a href="https://github.com/adempiere/adempiere/issues/2908">#2908</a>Updates to ADempiere Look and Feel
 *
 *  @version 3.9.4
 */
public class VTextLong extends VEditorAbstract
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6484690202241390248L;

	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(VTextLong.class);
	
	/**
	 * Define the ui class ID. It will be added to the 
	 * look and feel to define the ui class to use.
	 */
	private final static String uiClassID = "TextLongUI";

	private AdempiereTextLongUI textLongUI;

	private CMenuItem 			menuEditor;

	@Override
    public String getUIClassID() {
        return uiClassID ;
    }

	/**
	 *	Factory: Start Editor
	 *	@param jc container to get parent frame
	 *	@param header heading
	 *	@param text initial text
	 *	@param editable if false = r/o
	 *	@return edited string
	 */
	public static String startEditor(Container jc, String header, String text, boolean editable)
	{
		//	Find frame
		JFrame frame = SwingEnv.getFrame(jc);
		//	Start it
		HTMLEditor ed = new HTMLEditor (frame, header, text, editable);
		String s = ed.getHtmlText();
		ed = null;
		return s;
	}	//	startEditor

	/**
	 *	Standard Constructor
	 *  @param columnName column name
	 *  @param mandatory mandatory
	 *  @param isReadOnly read only
	 *  @param isUpdateable updateable
	 *  @param displayLength display length
	 *  @param fieldLength field length
	 */
	public VTextLong (String columnName, boolean mandatory, boolean isReadOnly, boolean isUpdateable,
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
	public VTextLong (String columnName, boolean mandatory, boolean isReadOnly, boolean isUpdateable,
		int displayLength, int fieldLength, boolean tableCellEditor)
	{

		super(columnName, mandatory, isReadOnly, isUpdateable, tableCellEditor);
		
		textLongUI = (AdempiereTextLongUI) getUI();
		
		textLongUI.addFocusListener(this);
		textLongUI.addMouseListener(getMouseAdapter());
		
		menuEditor.addActionListener(this);
		popupMenu.add(menuEditor);
		
		//	Popup
		menuEditor = new CMenuItem(Msg.getMsg(Env.getCtx(), "Editor"), Env.getImageIcon("Editor16.gif"));
		menuEditor.addActionListener(this);
		popupMenu.add(menuEditor);
		
	}	//	VText

	/**
	 *	ActionListener
	 *  @param e event
	 */
	public void actionPerformed(ActionEvent e)
	{
		log.finest( "VTestLong.actionPerformed - " + e.getActionCommand());
		if (e.getSource() == menuEditor)
		{
			menuEditor.setEnabled(false);
			String s = VTextLong.startEditor (this, Msg.translate(Env.getCtx(), getColumnName()), (String) getCurrentValue(), isEditable());
			//
			setDisplayBasedOnValue(s);
			menuEditor.setEnabled(true);
		}
		
		super.actionPerformed(e);
		
	}	//	actionPerformed

	@Override
	public JComponent getComponent() {
		return textLongUI.getEditorComponent();
	}

	@Override
	public String getDisplay() {
		return textLongUI.getEditorComponent().getText();
	}

	@Override
	protected Object getCurrentValue() {
		return textLongUI.getEditorComponent();
	}

	@Override
	protected String setDisplayBasedOnValue(Object value) {
		
		textLongUI.getEditorComponent().setText((String) value);
		
		return getDisplay();
	}

	@Override
	protected void handleInvalidValue() {
		// No action required
	}
	
}	//	VTextLong