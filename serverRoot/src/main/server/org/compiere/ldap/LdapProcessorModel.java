/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.
 * This program is free software; you can redistribute it and/or modify it
 * under the terms version 2 of the GNU General Public License as published
 * by the Free Software Foundation. This program is distributed in the hope
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. 
 * See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along 
 * with this program; if not, write to the Free Software Foundation, Inc., 
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.
 * You may reach us at: ComPiere, Inc. - http://www.adempiere.org/license.html
 * 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA or info@adempiere.org 
 *****************************************************************************/
package org.compiere.ldap;

import java.sql.*;
import java.util.*;
import org.compiere.model.*;

/**
 * 	Interim LDAP Server Model
 *	
 *  @author Jorg Janke
 *  @version $Id: LdapProcessorModel.java,v 1.1 2006/10/09 00:23:16 jjanke Exp $
 */
public class LdapProcessorModel implements AdempiereProcessor
{
	/**
	 * 	Ldap Processor Model
	 *	@param ctx context
	 */
	public LdapProcessorModel (Properties ctx)
	{
		m_ctx = ctx;
	}
	//	Properties
	private Properties 	m_ctx = null;
	
	private Timestamp m_dateNextRun;
	private Timestamp m_dateLastRun;
	
	
	public int getLdapPort()
	{
		return 389;
	}
	
	
	
	/**
	 * 	String Representation
	 *	@return info
	 */
	public String toString()
	{
		StringBuffer sb = new StringBuffer (getName());
		sb.append (";Port=").append (getLdapPort());
		return sb.toString ();
	}	//	toString
	
	
	
	
	/**************************************************************************
	 * 	getAD_Client_ID
	 *	@see org.compiere.model.AdempiereProcessor#getAD_Client_ID()
	 *	@return 0
	 */
	public int getAD_Client_ID()
	{
		return 0;
	}
	/**
	 * 	getName
	 *	@see org.compiere.model.AdempiereProcessor#getName()
	 *	@return name
	 */
	public String getName()
	{
		return "Adempiere LDAP Server";
	}
	/**
	 * 	getDescription
	 *	@see org.compiere.model.AdempiereProcessor#getDescription()
	 *	@return -
	 */
	public String getDescription()
	{
		return "-";
	}
	/**
	 * 	Get Ctx
	 *	@return context
	 */
	public Properties getCtx()
	{
		return m_ctx;
	}
	/**
	 * 	GetFrequencyType
	 *	@see org.compiere.model.AdempiereProcessor#getFrequencyType()
	 *	@return min
	 */
	public String getFrequencyType()
	{
		return MRequestProcessor.FREQUENCYTYPE_Minute;
	}
	/**
	 * 	getFrequency
	 *	@see org.compiere.model.AdempiereProcessor#getFrequency()
	 *	@return 1
	 */
	public int getFrequency()
	{
		return 1;
	}

	/**
	 * 	Get Unique Server ID
	 *	@return id
	 */
	public String getServerID()
	{
		return "Ldap";
	}

	public Timestamp getDateNextRun(boolean requery)
	{
		return m_dateNextRun;
	}

	public void setDateNextRun(Timestamp dateNextWork)
	{
		m_dateNextRun = dateNextWork;
	}

	public Timestamp getDateLastRun()
	{
		return m_dateLastRun;
	}

	public void setDateLastRun(Timestamp dateLastRun)
	{
		m_dateLastRun = dateLastRun;
	}

	public boolean save()
	{
		return true;
	}

	public AdempiereProcessorLog[] getLogs()
	{
		return new AdempiereProcessorLog[0];
	}
}
