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
import java.util.Properties;

/**
 * @author <a href="mailto:dixon.22martinez@gmail.com">Dixon Martinez</a>
 *
 */
public class MSPSField extends X_SPS_Field
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;


	/**
	 * *** Constructor ***
	 * SFA Field ID
	 * @author <a href="mailto:dixon.22martinez@gmail.com">Dixon Martinez</a> 13/02/2014, 22:58:37
	 * @param ctx
	 * @param SPS_Field_ID
	 * @param trxName
	 */
	public MSPSField(Properties ctx, int SPS_Field_ID, String trxName)
	{
		super(ctx, SPS_Field_ID, trxName);
	}
	/**
	 * *** Constructor ***
	 * SFA Field Result Set
	 * @author <a href="mailto:dixon.22martinez@gmail.com">Dixon Martinez</a> 13/02/2014, 22:59:50
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MSPSField(Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}


	/**
	 * *** Constructor ***
	 * Copy Constructor
	 * @author <a href="mailto:dixon.22martinez@gmail.com">Dixon Martinez</a> 13/02/2014, 22:58:07
	 * @param newTab
	 * @param oldField
	 */
	public MSPSField(MSPSTab newTab, MSPSField oldField){
		//	Create new SFA Field
		this (newTab.getCtx(), 0, oldField.get_TrxName());
		//	Copy Values
		copyValues(oldField, this);
		//	Set Client and  Organization
		setClientOrg(newTab);
		//	Set SFA Window ID
		setSPS_Tab_ID(newTab.getSPS_Tab_ID());
		//	Set Entity Type
		setEntityType(newTab.getEntityType());
	}//	Copy Constructors MSFAField
	
	/**
	 * *** Parent Constructor ***
	 * @author <a href="mailto:yamelsenih@gmail.com">Yamel Senih</a> 14/02/2014, 15:51:06
	 * @param parent
	 */
	public MSPSField(MSPSTab parent)
	{
		this(parent.getCtx(),0,parent.get_TrxName());
		setClientOrg(parent);
		setSPS_Tab_ID(parent.getSPS_Tab_ID());
	}
	
	/**
	 * Set Column Values
	 * @author <a href="mailto:yamelsenih@gmail.com">Yamel Senih</a> 14/02/2014, 15:56:18
	 * @param m_SFAColumn
	 * @return void
	 */
	public void setColumn(MSPSColumn m_SFAColumn)
	{
		setSPS_Column_ID(m_SFAColumn.getSPS_Column_ID());
		setName(m_SFAColumn.getName());
		setDescription(m_SFAColumn.getDescription());
		setEntityType(m_SFAColumn.getEntityType());
	}//	setColumn
	
	
	
}

