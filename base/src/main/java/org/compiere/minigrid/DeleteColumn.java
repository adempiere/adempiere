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
 * Copyright (C) 2003-2014 E.R.P. Consultores y Asociados, C.A.               *
 * All Rights Reserved.                                                       *
 * Contributor(s): Raul Muñoz www.erpcya.com					              *
 *****************************************************************************/

package org.compiere.minigrid;

/**
 * @author Mario Calderon, mario.calderon@westfalia-it.com, Systemhaus Westfalia, http://www.westfalia-it.com
 * @author Raúl Muñoz, rmunoz@erpcya.com, ERPCyA http://www.erpcya.com
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 */
public class DeleteColumn implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4752314456318509488L;


	public DeleteColumn(int record_ID) {
		this(new Integer(record_ID));
	}
	
	public DeleteColumn(Integer record_ID) {
		super();
		
		setRecord_ID(record_ID);
		setSelected(false);
	}

	/** Is the row selected         */
	private boolean     m_selected = false;
	/** The Record_ID               */
	private int     m_record_ID;


	/**
	 *  Set Selection
	 *  @param selected
	 */
	public void setSelected(boolean selected)
	{
		m_selected = selected;
	}
	/**
	 *  Is Selected
	 *  @return true if selected
	 */
	public boolean isSelected()
	{
		return m_selected;
	}

	/**
	 *  Set Record_ID
	 *  @param record_ID
	 */
	public void setRecord_ID(int record_ID)
	{
		m_record_ID = record_ID;
	}
	/**
	 * Get Record ID
	 * @return ID
	 */
	public int getRecord_ID()
	{
		return m_record_ID;
	}

	/**
	 *  To String
	 *  @return String representation
	 */
	public String toString()
	{
		return "DeleteColumn - ID=" + m_record_ID + ", Selected=" + m_selected;
	}   //  toString

}
