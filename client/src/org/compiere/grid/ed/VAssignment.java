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
import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.logging.Level;

import org.compiere.apps.search.InfoSchedule;
import org.compiere.model.MResourceAssignment;
import org.compiere.swing.CButton;
import org.compiere.swing.CMenuItem;
import org.compiere.swing.CTextField;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Msg;

/**
 *	Resource Assignment Entry
 *
 * 	@author 	Jorg Janke
 * 	@version 	$Id: VAssignment.java,v 1.2 2006/07/30 00:51:28 jjanke Exp $
 * 	@author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<li> FR [ 146 ] Remove unnecessary class, add support for info to specific column
 *		@see https://github.com/adempiere/adempiere/issues/146
 *  @author Michael McKay, mckayERP@gmail.com
 *  	<li><a href="https://github.com/adempiere/adempiere/issues/2908">#2908</a>Updates to ADempiere Look and Feel
 *
 * @version 3.9.4
 */
public class VAssignment extends VEditorAbstract
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1362298262975012883L;
	
	/**
	 * Define the ui class ID. It will be added to the 
	 * look and feel to define the ui class to use.
	 */
	private final static String uiClassID = "AssignmentUI";
	
	@Override
    public String getUIClassID() {
        return uiClassID ;
    }

	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(VAssignment.class);

	/**
	 *	IDE Constructor
	 */
	public VAssignment()
	{
		this (false, false, true);
	}	//	VAssigment

	/**
	 *	Create Resource Assigment.
	 *  <pre>
	 * 		Resource DateTimeFrom Qty UOM Button
	 *  </pre>
	 *  @param mandatory mandatory
	 *  @param isReadOnly read only
	 *  @param isUpdateable updateable
	 */
	public VAssignment (boolean mandatory, boolean isReadOnly, boolean isUpdateable)
	{
		this(mandatory, isReadOnly, isUpdateable, false);
	}

	/**
	 *	Create Resource Assigment.
	 *  <pre>
	 * 		Resource DateTimeFrom Qty UOM Button
	 *  </pre>
	 *  @param mandatory mandatory
	 *  @param isReadOnly read only
	 *  @param isUpdateable updateable
	 */
	public VAssignment (boolean mandatory, boolean isReadOnly, boolean isUpdateable, boolean tableCellEditor)
	{
		super("", mandatory, isReadOnly, isUpdateable, tableCellEditor);
		textField = (CTextField) getEditorComponent();
		button = getButtonComponent();

		//	ReadWrite
		if (isReadOnly || !isUpdateable)
			setReadWrite(false);
		else
			setReadWrite(true);

		//	Popup
		menuEditor = new CMenuItem(Msg.getMsg(Env.getCtx(), "InfoResource"), Env.getImageIcon("Zoom16.gif"));
		menuEditor.addActionListener(this);
		popupMenu.add(menuEditor);
		
	}	//	VAssignment

	/** Get Info				*/
	private PreparedStatement	m_pstmt = null;

	/** The Text Field          */
	private CTextField			textField;
	/** The Button              */
	private CButton				button;

	private CMenuItem 			menuEditor;

	/**	The Format				*/
	private DateFormat			m_dateFormat = DisplayType.getDateFormat(DisplayType.DateTime);
	private NumberFormat		m_qtyFormat = DisplayType.getNumberFormat(DisplayType.Quantity);

	private int S_ResourceAssignment_ID;
	

	/**
	 * 	Get Display Value
	 *	@return info
	 */
	public String getDisplay()
	{
		return textField.getText();
	}	//	getDisplay

	/*************************************************************************/
	

	/**
	 * 	Action Listener - start dialog
	 * 	@param e Event
	 */
	public void actionPerformed(ActionEvent e)
	{
		super.actionPerformed(e);
		
		if (e.getSource().equals(button))
			cmd_button();		
	}
	
	private void cmd_button()
	{
		button.setEnabled(false);
		//
		Integer oldValue = (Integer)getValue();
		S_ResourceAssignment_ID = oldValue == null ? 0 : oldValue.intValue();
		MResourceAssignment ma = new MResourceAssignment(Env.getCtx(), S_ResourceAssignment_ID, null);

		//	Start VAssignment Dialog
		if (S_ResourceAssignment_ID != 0)
		{
			VAssignmentDialog vad = new VAssignmentDialog (Env.getFrame(this), ma, true, true);
			ma = vad.getMResourceAssignment();
		}
		//	Start InfoSchedule directly
		else
		{
			InfoSchedule is = new InfoSchedule(Env.getFrame(this), ma, true);
			ma = is.getMResourceAssignment();
		}

		//	Set Value
		if (ma != null && ma.getS_ResourceAssignment_ID() != 0)
		{
			S_ResourceAssignment_ID = ma.getS_ResourceAssignment_ID();
		}
		
		setDisplayBasedOnValue(S_ResourceAssignment_ID);
		
		button.setEnabled(true);
		
	}	//	actionPerformed


	@Override
	protected Object getCurrentValue() {
		
		return new Integer(S_ResourceAssignment_ID);
		
	}

	@Override
	protected String setDisplayBasedOnValue(Object value) {
		
		log.finest("Value=" + value);
		String display = "";
		int id = 0;
		if (value != null && value instanceof Integer)
			id = ((Integer) value).intValue();
		//	Set Empty
		if (id == 0)
		{
			textField.setText("");
			return "";
		}

		// TODO replace this with a proper lookup
		//	Statement
		if (m_pstmt == null)
			m_pstmt = DB.prepareStatement("SELECT r.Name,ra.AssignDateFrom,ra.Qty,uom.UOMSymbol "
				+ "FROM S_ResourceAssignment ra, S_Resource r, S_ResourceType rt, C_UOM uom "
				+ "WHERE ra.S_ResourceAssignment_ID=?"
				+ " AND ra.S_Resource_ID=r.S_Resource_ID"
				+ " AND r.S_ResourceType_ID=rt.S_ResourceType_ID"
				+ " and rt.C_UOM_ID=uom.C_UOM_ID", null);
		//
		try
		{
			m_pstmt.setInt(1, id);
			ResultSet rs = m_pstmt.executeQuery();
			if (rs.next())
			{
				StringBuffer sb = new StringBuffer(rs.getString(1));
				sb.append(" ").append(m_dateFormat.format(rs.getTimestamp(2)))
					.append(" ").append(m_qtyFormat.format(rs.getBigDecimal(3)))
					.append(" ").append(rs.getString(4).trim());
				display = sb.toString();
			}
			else
				display = "<" + id + ">";
			rs.close();
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, "", e);
		}
		
		textField.setText(display);
		return display;
	}

	@Override
	protected void handleInvalidValue() {
		// Open the dialog to sort it out
		cmd_button();
	}

}	//	VAssignment
