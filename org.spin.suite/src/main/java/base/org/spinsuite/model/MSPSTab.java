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
 * Contributor(s): Yamel Senih www.erpconsultoresyasociados.com               *
 *****************************************************************************/
package org.spinsuite.model;

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.Query;

/**
 * @author <a href="mailto:dixon.22martinez@gmail.com">Dixon Martinez</a>
 * @author <a href="mailto:yamelsenih@gmail.com">Yamel Senih</a>
 * Add before save method
 */
public class MSPSTab extends X_SPS_Tab
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4624331682737671235L;
	
	/**	The Fields						*/
	private MSPSField []		m_SFAFields 	= null;
	

	/**
	 * *** Constructor ***
	 * @author <a href="mailto:dixon.22martinez@gmail.com">Dixon Martinez</a> 13/02/2014, 12:22:48
	 * @param ctx
	 * @param SPS_Tab_ID
	 * @param trxName
	 */
	public MSPSTab(Properties ctx, int SPS_Tab_ID, String trxName)
	{
		super(ctx, SPS_Tab_ID, trxName);
	}

	/**
	 * *** Constructor ***
	 * @author <a href="mailto:dixon.22martinez@gmail.com">Dixon Martinez</a> 13/02/2014, 12:22:48
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MSPSTab(Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}

	/**
	 * *** Constructor ***
	 * @author <a href="mailto:dixon.22martinez@gmail.com">Dixon Martinez</a> 13/02/2014, 18:20:06
	 * @param m_WindowTo
	 * @param oldTab
	 */
	public MSPSTab(MSPSWindow m_WindowTo, MSPSTab oldTab)
	{
		//	Create new SFA Tab
		this (m_WindowTo.getCtx(), 0, m_WindowTo.get_TrxName());
		
		//	Copy Values
		copyValues(oldTab, this);
		
		//	Set Client and  Organization
		setClientOrg(m_WindowTo);
		
		//	Set SFA Window ID
		setSPS_Window_ID(m_WindowTo.getSPS_Window_ID());
		
		//	Set Entity Type
		setEntityType(m_WindowTo.getEntityType());
	}//	Constructor Copy
	


	
	/**
	 * Get SFA Fields
	 * @author <a href="mailto:dixon.22martinez@gmail.com">Dixon Martinez</a> 13/02/2014, 22:50:49
	 * @param reload
	 * @param get_TrxName
	 * @return
	 * @return MSFAField[]
	 */
	public MSPSField[] getSFAFields(boolean reload, String get_TrxName)
	{
		//	Validate m_SFAFields not instanced or reload is true
		if	(m_SFAFields != null
				&& !reload)
			return m_SFAFields;
	
		//	Where clause
		final String whereClause = I_SPS_Field.COLUMNNAME_SPS_Tab_ID + "=?";
		
		//	Lists of SFA Fields
		List<MSPSField> list = new Query(getCtx(), I_SPS_Field.Table_Name, whereClause, get_TrxName)
			.setParameters(getSPS_Tab_ID())
			.setOrderBy(I_SPS_Field.COLUMNNAME_SeqNo)
			.list();
		
		//	Instanced SFA Field 
		m_SFAFields = new MSPSField[list.size()];
		list.toArray(m_SFAFields);
			
		return m_SFAFields;
	}//	getSFAFields

	
	/**************************************************************************
	 * 	Before Save
	 *	@param newRecord
	 *	@return save
	 */
	protected boolean beforeSave (boolean newRecord)
	{
		super.beforeSave(newRecord);
		//	Verify if exists class path to form
		if(getSPS_Table_ID() == 0){
			if(getClassname() == null
					|| getClassname().trim().length() == 0)
				throw new AdempiereException("@SPS_SyncTable_ID@ @IsMandatory@");
		}
		return true;
	}	//	beforeSave

}
