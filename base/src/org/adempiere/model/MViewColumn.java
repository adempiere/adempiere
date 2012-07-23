/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2003-2011 e-Evolution Consultants. All Rights Reserved.      *
 * Copyright (C) 2003-2011 Victor Pérez Juárez 								  * 
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
 * Contributor(s): Victor Pérez Juárez  (victor.perez@e-evolution.com)		  *
 * Sponsors: e-Evolution Consultants (http://www.e-evolution.com/)            *
 *****************************************************************************/
package org.adempiere.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.model.MColumn;
import org.compiere.util.CLogger;
import org.compiere.util.DB;

/**
 * Class Model Smart View Column
 * 
 * @author victor.perez@e-evoluton.com, www.e-evolution.com
 *  <li>FR [ 3426137 ] Smart Browser
 * 	https://sourceforge.net/tracker/?func=detail&aid=3426137&group_id=176962&atid=879335
 */
public class MViewColumn extends X_AD_View_Column {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1829379740863651306L;
	/** Logger **/
	private static CLogger s_log = CLogger.getCLogger(MViewColumn.class);
	/** MColumn **/
	private MColumn m_column = null;

	/**************************************************************************
	 * MViewColumn Constructor
	 * 
	 * @param ctx
	 *            context
	 * @param AD_ViewColumn_ID
	 * @param trxName
	 *            transaction name
	 */
	public MViewColumn(Properties ctx, int AD_ViewColumn_ID, String trxName) {
		super(ctx, AD_ViewColumn_ID, trxName);
		if (AD_ViewColumn_ID == 0) {
		}
	}

	/**
	 * MViewColumn Constructor
	 * @param ctx context
	 * @param AD_ViewColumn_ID View Column ID
	 */
	public MViewColumn(Properties ctx, int AD_ViewColumn_ID) {
		this(ctx, AD_ViewColumn_ID, null);
	}

	/**
	 * get MViewColumn base on MColumn
	 * @param column
	 */
	public MViewColumn(MColumn column) {
		super(column.getCtx(), 0, column.get_TrxName());
		setAD_Column_ID(column.getAD_Column_ID());
		setName(column.getName());
		setDescription(column.getDescription());
		setHelp(column.getHelp());
		setIsActive(true);
		setEntityType(column.getEntityType());
	}

	/**
	 * Load Constructor
	 * @param ctx context
	 * @param rs result set record
	 * @param trxName transaction
	 */
	public MViewColumn(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	} // MViewColumn

	/**
	 * String representation
	 * @return info
	 */
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer("MViewColumn[").append(get_ID())
				.append("-").append(getName()).append("]");
		return sb.toString();
	} // toString

	/**
	 * get MColumn
	 */
	public MColumn getAD_Column() {
		if (m_column == null) {
			m_column = new MColumn(getCtx(), getAD_Column_ID(), get_TrxName());
		}
		return m_column;
	}

	/**
	 * Is Key from MColumn 
	 * @return
	 */
	public boolean isKey() {
		return getAD_Column().isKey();
	}

	/**
	 * Is Identifier
	 * @return boolean
	 */
	public boolean isIdentifier() {
		return getAD_Column().isIdentifier();
	}

	/**
	 * get AD Element ID
	 * 
	 * @return int AD Element ID
	 */
	public int getAD_Element_ID() {
		return getAD_Column().getAD_Element_ID();
	}

	/**
	 * get AD Reference 
	 * @return AD_Reference_ID
	 */
	public int getAD_Reference_ID() {
		return getAD_Column().getAD_Reference_ID();
	}
	
	/**
	 * 	Before Delete
	 *	@return true of it can be deleted
	 */
	protected boolean beforeDelete ()
	{
		DB.executeUpdate("DELETE FROM AD_View_Column_Trl WHERE AD_View_Column_ID=? ", getAD_View_Column_ID(),get_TrxName());
		return true;
	}	//	beforeDelete
}
