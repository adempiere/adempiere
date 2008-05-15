/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                        *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
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
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;


/**
 *	Alert Rule Model
 *	
 *  @author Jorg Janke
 *  @version $Id: MAlertRule.java,v 1.3 2006/07/30 00:51:03 jjanke Exp $
 */
public class MAlertRule extends X_AD_AlertRule
{
	/**
	 * 	Standatd Constructor
	 *	@param ctx context
	 *	@param AD_AlertRule_ID id
	 *	@param trxName transaction
	 */
	public MAlertRule (Properties ctx, int AD_AlertRule_ID, String trxName)
	{
		super (ctx, AD_AlertRule_ID, trxName);
	}	//	MAlertRule

	/**
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName transaction
	 */
	public MAlertRule (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MAlertRule
	
	/** Alert */
	private MAlert m_parent = null;
	
	/**
	 * Get parent
	 * @return parent alert
	 */
	public MAlert getParent() {
		if (m_parent == null || m_parent.get_ID() != getAD_Alert_ID())
			m_parent = new MAlert(getCtx(), getAD_Alert_ID(), get_TrxName());
		return m_parent;
	}
	
	/**
	 * Set parent alert.
	 * NOTE: is not setting AD_Alert_ID
	 * @param alert
	 */
	public void setParent(MAlert alert) {
		m_parent = alert;
	}
	
	/**
	 *	Get Sql
	 *	@return sql
	 * @deprecated Use {@link #getSql(boolean)} instead
	 */
	public String getSql()
	{
		return getSql(false);
	}
	
	/**
	 * Get Sql
	 * @param applySecurity apply role/client security
	 * @return sql
	 */
	public String getSql(boolean applySecurity)
	{
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ").append(getSelectClause())
			.append(" FROM ").append(getFromClause());
		if (getWhereClause() != null && getWhereClause().length() > 0)
			sql.append(" WHERE ").append(getWhereClause());
		String finalSQL = sql.toString();
		//
		// Apply Security:
		if (applySecurity) {
			MAlert alert = getParent();
			if (alert.isEnforceRoleSecurity()
					|| alert.isEnforceClientSecurity())
			{
				int AD_Role_ID = alert.getFirstAD_Role_ID();
				if (AD_Role_ID == -1)
					AD_Role_ID = alert.getFirstUserAD_Role_ID();
				if (AD_Role_ID != -1)
				{
					MRole role = MRole.get(getCtx(), AD_Role_ID);
					finalSQL = role.addAccessSQL(finalSQL, null, true, false);
				}
			}
		}
		//
		if (getOtherClause() != null && getOtherClause().length() > 0)
			finalSQL += " " + getOtherClause();
		return finalSQL;
	}	//	getSql
	
	
	/**
	 * 	Before Save
	 *	@param newRecord new
	 *	@return true
	 */
	protected boolean beforeSave (boolean newRecord)
	{
		if (newRecord)
			setIsValid(true);
		if (isValid())
			setErrorMsg(null);
		return true;
	}	//	beforeSave

	/**
	 * 	String Representation
	 *	@return info
	 */
	public String toString ()
	{
		StringBuffer sb = new StringBuffer ("MAlertRule[");
		sb.append(get_ID())
			.append("-").append(getName())
			.append(",Valid=").append(isValid())
			.append(",").append(getSql(false));
		sb.append ("]");
		return sb.toString ();
	}	//	toString
	
}	//	MAlertRule
