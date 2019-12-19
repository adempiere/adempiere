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

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;

import org.compiere.apps.search.Info;
import org.compiere.model.MAccountLookup;
import org.compiere.model.MRole;
import org.compiere.swing.CButton;
import org.compiere.swing.CTextField;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 *	Account Control - Displays ValidCombination and launches Dialog
 *
 *  @author Jorg Janke
 *  @version  $Id: VAccount.java,v 1.2 2006/07/30 00:51:28 jjanke Exp $
 * 
 * @author Teo Sarca, SC ARHIPAC SERVICE SRL
 * 			<li>BF [ 1830531 ] Process parameter with type Account not working
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<li> FR [ 146 ] Remove unnecessary class, add support for info to specific column
 *		@see https://github.com/adempiere/adempiere/issues/146
 *  @author Michael McKay, mckayERP@gmail.com
 *  	<li><a href="https://github.com/adempiere/adempiere/issues/2908">#2908</a>Updates to ADempiere Look and Feel
 *  
 *  @version 3.9.4
 */
public final class VAccount extends VEditorAbstract
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4177614835777620089L;

	/**
	 * Define the ui class ID. It will be added to the 
	 * look and feel to define the ui class to use.
	 */
	private final static String uiClassID = "AccountUI";

	@Override
    public String getUIClassID() {
        return uiClassID ;
    }
	
	/**
	 *	Constructor
	 *  @param columnName
	 *  @param mandatory
	 *  @param isReadOnly
	 *  @param isUpdateable
	 *  @param mAccount
	 *  @param title
	 */
	public VAccount(String columnName, boolean mandatory, boolean isReadOnly, boolean isUpdateable,
		MAccountLookup mAccount, String title)
	{
		this(columnName, mandatory, isReadOnly, isUpdateable, mAccount, title, false);
	}		
	
	
	/**
	 *	Constructor
	 *  @param columnName
	 *  @param mandatory
	 *  @param isReadOnly
	 *  @param isUpdateable
	 *  @param mAccount
	 *  @param title
	 *  @param tableCellEditor true if the editor will be used in a table cell
	 */
	public VAccount(String columnName, boolean mandatory, boolean isReadOnly, boolean isUpdateable,
		MAccountLookup mAccount, String title, boolean tableCellEditor)
	{

		super(columnName, mandatory, isReadOnly, isUpdateable, tableCellEditor);
		setLookup(mAccount);
		this.title = title;
		textField = (CTextField) getEditorComponent();
		button = getButtonComponent();

	}	//	VAccount


	private CTextField			textField;
	private CButton				button;
	private String				title;
	
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(VAccount.class);

	/**
	 *	Set Editor to value
	 *  @param value
	 * @return 
	 */
	@Override
	public String setDisplayBasedOnValue(Object value)
	{
		log.finest("Value=" + value);
		String display = ((MAccountLookup) getLookup()).getDisplay(value);
		textField.setText(display);	//	loads lookup
		textField.setToolTipText(((MAccountLookup) getLookup()).getDescription());
		return display;
	}	//	setValue

//	/**
//	 *  Return Display Value
//	 *  @return String representation
//	 */
//	public String getDisplay()
//	{
//		return textField.getText();
//	}   //  getDisplay
//
	/**
	 *	ActionListener - Button - Start Dialog
	 *  @param e
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		super.actionPerformed(e);
		
		if (e.getSource().equals(textField))
			cmd_text();
		else if (e.getSource().equals(button))
			cmd_button();		
		
	}	//	actionPerformed

	/**
	 *	Button - Start Dialog
	 */
	public void cmd_button()
	{
		setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		int C_AcctSchema_ID = Env.getContextAsInt(Env.getCtx(), getWindowNo(), "C_AcctSchema_ID", false);
		// Try to get C_AcctSchema_ID from global context - teo_sarca BF [ 1830531 ]
		if (C_AcctSchema_ID <= 0)
		{
			C_AcctSchema_ID = Env.getContextAsInt(Env.getCtx(), "$C_AcctSchema_ID");
		}
		VAccountDialog ad = new VAccountDialog (Env.getFrame(this), title, 
			(MAccountLookup) getLookup(), C_AcctSchema_ID);
		setCursor(Cursor.getDefaultCursor());
		//
		Integer newValue = ad.getValue();

		//	set & redisplay
		textField.setText(((MAccountLookup) getLookup()).getDisplay(newValue));
		textField.setToolTipText(((MAccountLookup) getLookup()).getDescription());
		
	}	//	cmd_button

	private boolean m_cmdTextRunning = false;
	
	/**
	 *	Text - try to find Alias or start Dialog
	 */
	public void cmd_text()
	{
		if (m_cmdTextRunning)
			return;
	
		m_cmdTextRunning = true;

		// Try to find the value based on the text
		Integer newValue = (Integer) getCurrentValue();
		
		// If that doesn't work, open the dialog.
		if (newValue == null || newValue.compareTo(Integer.valueOf(0)) <= 0)
			cmd_button();  
		
		m_cmdTextRunning = false;
	}	//	actionPerformed


	protected Object getCurrentValue() {
		
		String text = textField.getText();
		log.info("Text=" + text);
		if (text == null || text.length() == 0 || text.equals("%"))
		{
			return null;
		}
		//
		String sql = "SELECT C_ValidCombination_ID FROM C_ValidCombination "
			+ "WHERE C_AcctSchema_ID=?" 
			+ " AND (UPPER(Alias) LIKE ? OR UPPER(Combination) LIKE ?)";
		sql = MRole.getDefault().addAccessSQL(sql, 
			"C_ValidCombination", MRole.SQL_NOTQUALIFIED, MRole.SQL_RO);
		
		int C_AcctSchema_ID = Env.getContextAsInt(Env.getCtx(), getWindowNo(), "C_AcctSchema_ID");
		//
		int C_ValidCombination_ID = 0;
		PreparedStatement pstmt = null;
		try
		{
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, C_AcctSchema_ID);
			pstmt.setString(2, Info.getSQLText(text));
			pstmt.setString(3, Info.getSQLText(text));
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
			{
				C_ValidCombination_ID = rs.getInt(1);
				if (rs.next())		//	only one
					C_ValidCombination_ID = 0;
			}
			rs.close();
			pstmt.close();
			pstmt = null;
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		try
		{
			if (pstmt != null)
				pstmt.close();
			pstmt = null;
		}
		catch (Exception e)
		{
			pstmt = null;
		}
		
		this.setCurrentValueValid(C_ValidCombination_ID != 0);
		
		Object retValue = new Integer(C_ValidCombination_ID);
		
		setDisplayBasedOnValue(retValue);
		
		return retValue;

	}
		
	/**
	 * 	String Representation
	 *	@return info
	 */
	public String toString()
	{
		StringBuffer sb = new StringBuffer ("VAccount[");
		sb.append (getValue()).append ("]");
		return sb.toString ();
	}	//	toString

	@Override
	public String getDisplay() {
		
		return textField.getDisplay();
		
	}

	@Override
	protected void handleInvalidValue() {
		cmd_button();
	}
	
}	//	VAccount