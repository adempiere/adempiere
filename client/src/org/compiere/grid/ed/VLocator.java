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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

import javax.swing.JComponent;

import org.compiere.apps.search.Info;
import org.compiere.model.MLocator;
import org.compiere.model.MLocatorLookup;
import org.compiere.model.MRole;
import org.compiere.model.MWarehouse;
import org.compiere.swing.CTextField;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.SwingEnv;

/**
 *	Warehouse Locator Control
 *
 *  @author 	Jorg Janke
 *  @version 	$Id: VLocator.java,v 1.5 2006/07/30 00:51:27 jjanke Exp $
 *  @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<li> FR [ 146 ] Remove unnecessary class, add support for info to specific column
 *		@see https://github.com/adempiere/adempiere/issues/146
 * 	@author Carlos Parada, cparada@erpcya.com, ERPCyA http://www.erpcya.com
 *		<li> BR [ 317 ] Fix bug stack overflow on set value, add condition for launch trigger 
 *		@see https://github.com/adempiere/adempiere/issues/317
 *  @author Michael McKay, mckayERP@gmail.com
 *  	<li><a href="https://github.com/adempiere/adempiere/issues/2908">#2908</a>Updates to ADempiere Look and Feel
 *
 * @version 3.9.4
 */
public class VLocator extends VEditorAbstract
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1953277256988665242L;

	/**
	 * Define the ui class ID. It will be added to the 
	 * look and feel to define the ui class to use.
	 */
	private final static String uiClassID = "LocatorUI";

	private CTextField textField;

	@Override
    public String getUIClassID() {
        return uiClassID ;
    }
	
	/**
	 *  IDE Constructor
	 */
	public VLocator ()
	{
		this("M_Locator_ID", false, false, true, null, 0);
	}   //  VLocator

	/**
	 *	Constructor
	 *
	 * 	@param columnName ColumnName
	 *	@param mandatory mandatory
	 *	@param isReadOnly read only
	 *	@param isUpdateable updateable
	 *	@param mLocator locator (lookup) model
	 * 	@param WindowNo window no
	 */
	public VLocator(String columnName, boolean mandatory, boolean isReadOnly, boolean isUpdateable,
		MLocatorLookup mLocator, int WindowNo)
	{
		this(columnName, mandatory, isReadOnly, isUpdateable, mLocator, WindowNo, false);
	}
	
	/**
	 *	Constructor
	 *
	 * 	@param columnName ColumnName
	 *	@param mandatory mandatory
	 *	@param isReadOnly read only
	 *	@param isUpdateable updateable
	 *	@param mLocator locator (lookup) model
	 * 	@param WindowNo window no
	 *  @param tableCellEditor true if the editor will be used in a table cell
	 */
	public VLocator(String columnName, boolean mandatory, boolean isReadOnly, boolean isUpdateable,
		MLocatorLookup mLocator, int WindowNo, boolean tableCellEditor)
	{
		super(columnName, mandatory, isReadOnly, isUpdateable, tableCellEditor);
		super.setShowInfoInPopupMenu(false);
		super.setLookup(mLocator);
		
		textField = (CTextField) getEditorComponent();
		
		//
//		mZoom = new CMenuItem(Msg.getMsg(Env.getCtx(), "Zoom"), Env.getImageIcon("Zoom16.gif"));
//		mZoom.addActionListener(this);
//		popupMenu.add(mZoom);
//		mRefresh = new CMenuItem(Msg.getMsg(Env.getCtx(), "Refresh"), Env.getImageIcon("Refresh16.gif"));
//		mRefresh.addActionListener(this);
//		popupMenu.add(mRefresh);
		setDefault_Locator_ID(); // set default locator, teo_sarca [ 1661546 ]
	}	//	VLocator


	private Object				currentValue;
	//
	private String				m_columnName;
	private int					m_WindowNo;
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(VLocator.class);

	private String lastDisplay;

	@Override
	protected String setDisplayBasedOnValue (Object value)
	{
		if (lookup == null)
		{
			currentValue = null;
			textField.setText("");
			lastDisplay = "";
			return "";
		}
		
		if (value != null)
		{
			((MLocatorLookup) lookup).setOnly_Warehouse_ID (getOnly_Warehouse_ID ());
			((MLocatorLookup) lookup).setOnly_Product_ID(getOnly_Product_ID());
			if (!((MLocatorLookup) lookup).isValid(value) || ((Integer) value) == 0)
			{
				value = null;
			}
		}
		//
		currentValue = value;
		lastDisplay = lookup.getDisplay(value);
		textField.setText(lastDisplay);	//	loads value
		
		return lastDisplay;
		
	}	//	setDisplayBasedOnValue

	/**
	 *	Return Editor value
	 *  @return value
	 */
	public Object getCurrentValue()
	{
		
		if (currentValue != null 
				&& currentValue instanceof Integer)
				return ((Integer)currentValue).intValue();
			return null;
			
	}	//	getCurrentValue
	
	/**
	 * 	Get M_Locator_ID. Not used, will be deleted.
	 *	@return id
	 */
	@Deprecated
	public int getM_Locator_ID()
	{
		return ((Integer) getCurrentValue()).intValue();
		
	}	//	getM_Locator_ID

	/**
	 *	ActionListener
	 *  @param e ActionEvent
	 */
	public void actionPerformed(ActionEvent e)
	{
		super.actionPerformed(e);

		//	Warehouse/Product
		int only_Warehouse_ID = getOnly_Warehouse_ID();
		int only_Product_ID = getOnly_Product_ID();
		log.config("Only Warehouse_ID=" + only_Warehouse_ID
			+ ", Product_ID=" + only_Product_ID);

		if (e.getSource().equals(getButtonComponent()))
		{	
			actionButton(only_Warehouse_ID, only_Product_ID);	
		}
		
		if (e.getSource().equals(getEditorComponent()))
		{
			actionText(only_Warehouse_ID, only_Product_ID);
		}
		
	}	//	actionPerformed

	
	private void actionButton (int only_Warehouse_ID, int only_Product_ID) {
		//	 Button - Start Dialog
		int M_Locator_ID = 0;
		if (currentValue instanceof Integer)
			M_Locator_ID = ((Integer)currentValue).intValue();
		//
		((MLocatorLookup) lookup).setOnly_Warehouse_ID(only_Warehouse_ID);
		((MLocatorLookup) lookup).setOnly_Product_ID(getOnly_Product_ID());
		VLocatorDialog ld = new VLocatorDialog(SwingEnv.getFrame(this),
			Msg.translate(Env.getCtx(), m_columnName),
			(MLocatorLookup) lookup, M_Locator_ID, isMandatory(), only_Warehouse_ID);
		//	display
		ld.setVisible(true);
		((MLocatorLookup) lookup).setOnly_Warehouse_ID(0);
		//	redisplay
		setDisplayBasedOnValue(ld.getValue());
	}

	/**
	 * 	Hit Enter in Text Field
	 * 	@param only_Warehouse_ID if not 0 restrict warehouse
	 * 	@param only_Product_ID of not 0 restricted product
	 * 	@return true if found
	 */
	private void actionText (int only_Warehouse_ID, int only_Product_ID)
	{
		String text = textField.getText();
		
		// No change, just pressing enter again => ignore - teo_sarca BF [ 1834399 ]
		if (text != null && text.equals(lastDisplay))
		{
			log.finest("Nothing changed [SKIP]");
			return;
		}
		
		//	Nothing entered but there should be or the text is a wild card
		//  then click the button to force a value
		if ((text == null || text.length() == 0) && this.isMandatory() 
			|| (text != null && text.equals("%")))
		{
			actionButton(only_Warehouse_ID, only_Product_ID);
			return;
		}
		
		log.fine(text);
		//	Null
		if (text == null || text.length() == 0)
		{
			setDisplayBasedOnValue(null);
			return;
		}
		
		// Add wildcards and format the text
		text = Info.getSQLText(text);
		
		//	Look up - see MLocatorLookup.run
		StringBuffer sql = new StringBuffer("SELECT M_Locator_ID FROM M_Locator ")
			.append(" WHERE IsActive='Y' AND UPPER(Value) LIKE ")
			.append(DB.TO_STRING(text));
		if (getOnly_Warehouse_ID() != 0)
			sql.append(" AND M_Warehouse_ID=?");
		if (getOnly_Product_ID() != 0)
			sql.append(" AND (IsDefault='Y' ")	//	Default Locator
				.append("OR EXISTS (SELECT * FROM M_Product p ")	//	Product Locator
				.append("WHERE p.M_Locator_ID=M_Locator.M_Locator_ID AND p.M_Product_ID=?)")
				.append("OR EXISTS (SELECT * FROM M_Storage s ")	//	Storage Locator
				.append("WHERE s.M_Locator_ID=M_Locator.M_Locator_ID AND s.M_Product_ID=?))");
		String finalSql = MRole.getDefault(Env.getCtx(), false).addAccessSQL(
			sql.toString(), "M_Locator", MRole.SQL_NOTQUALIFIED, MRole.SQL_RO);
		//
		int M_Locator_ID = 0;
		PreparedStatement pstmt = null;
		try
		{
			pstmt = DB.prepareStatement(finalSql, null);
			int index = 1;
			if (only_Warehouse_ID != 0)
				pstmt.setInt(index++, only_Warehouse_ID);
			if (only_Product_ID != 0)
			{
				pstmt.setInt(index++, only_Product_ID);
				pstmt.setInt(index++, only_Product_ID);
			}
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
			{
				M_Locator_ID = rs.getInt(1);
				if (rs.next())
					M_Locator_ID = 0;	//	more than one
			}
			rs.close();
			pstmt.close();
			pstmt = null;
		}
		catch (SQLException ex)
		{
			log.log(Level.SEVERE, finalSql, ex);
		}
		try
		{
			if (pstmt != null)
				pstmt.close();
		}
		catch (SQLException ex1)
		{
		}
		pstmt = null;

		if (M_Locator_ID != 0)
			setDisplayBasedOnValue (new Integer(M_Locator_ID));
		else
			actionButton(only_Warehouse_ID, only_Product_ID);
		
	}	//	actionText

	/**
	 * 	Get Warehouse restriction if any.
	 *	@return	M_Warehouse_ID or 0
	 */
	private int getOnly_Warehouse_ID()
	{
		String only_Warehouse = Env.getContext(Env.getCtx(), m_WindowNo, "M_Warehouse_ID", true);
		int only_Warehouse_ID = 0;
		try
		{
			if (only_Warehouse != null && only_Warehouse.length () > 0)
				only_Warehouse_ID = Integer.parseInt (only_Warehouse);
		}
		catch (Exception ex)
		{
		}
		return only_Warehouse_ID;
	}	//	getOnly_Warehouse_ID

	/**
	 * 	Get Product restriction if any.
	 *	@return	M_Product_ID or 0
	 */
	private int getOnly_Product_ID()
	{
		if (!Env.isSOTrx(Env.getCtx(), m_WindowNo))
			return 0;	//	No product restrictions for PO
		//
		String only_Product = Env.getContext(Env.getCtx(), m_WindowNo, "M_Product_ID", true);
		int only_Product_ID = 0;
		try
		{
			if (only_Product != null && only_Product.length () > 0)
				only_Product_ID = Integer.parseInt (only_Product);
		}
		catch (Exception ex)
		{
		}
		return only_Product_ID;
	}	//	getOnly_Product_ID
	
	/**
	 * Set the default locator if this field is mandatory 
	 * and we have a warehouse restriction.
	 * 
	 * @since 3.1.4
	 */
	private void setDefault_Locator_ID()
	{
		// teo_sarca, FR [ 1661546 ] Mandatory locator fields should use defaults
		// TODO This is a model issue and should be part of the default logic
		// for the field.  Does this apply to every mandatory editor type? Should 
		// the abstract editor be looking for default values when mandatory? Or 
		// should the user have the control?
		if (!isMandatory() || lookup == null) {
			return;
		}
		int M_Warehouse_ID = getOnly_Warehouse_ID();
		if (M_Warehouse_ID <= 0) {
			return;
		}
		MWarehouse wh = MWarehouse.get(Env.getCtx(), M_Warehouse_ID);
		if (wh == null || wh.get_ID() <= 0) {
			return;
		}
		MLocator loc = wh.getDefaultLocator();
		if (loc == null || loc.get_ID() <= 0) {
			return;
		}
		setValue(Integer.valueOf(loc.get_ID()));
	}

	@Override
	public JComponent getComponent() {
		return textField;
	}

	@Override
	public String getDisplay() {
		return textField.getText();
	}

	@Override
	protected void handleInvalidValue() {
		//	Warehouse/Product
		int only_Warehouse_ID = getOnly_Warehouse_ID();
		int only_Product_ID = getOnly_Product_ID();
		log.config("Only Warehouse_ID=" + only_Warehouse_ID
			+ ", Product_ID=" + only_Product_ID);

		actionButton(only_Warehouse_ID, only_Product_ID);	
		
	}

}	//	VLocator
