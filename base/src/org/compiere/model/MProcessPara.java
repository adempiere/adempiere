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

import java.sql.*;
import java.util.*;
import java.util.logging.*;
import org.compiere.util.*;


/**
 *  Process Parameter Model
 *
 *  @author Jorg Janke
 *  @version $Id: MProcessPara.java,v 1.3 2006/07/30 00:58:37 jjanke Exp $
 */
public class MProcessPara extends X_AD_Process_Para
{
	/**
	 * 	Get MProcessPara from Cache
	 *	@param ctx context
	 *	@param AD_Process_Para_ID id
	 *	@return MProcessPara
	 */
	public static MProcessPara get (Properties ctx, int AD_Process_Para_ID)
	{
		Integer key = new Integer (AD_Process_Para_ID);
		MProcessPara retValue = (MProcessPara)s_cache.get (key);
		if (retValue != null)
			return retValue;
		retValue = new MProcessPara (ctx, AD_Process_Para_ID, null);
		if (retValue.get_ID () != 0)
			s_cache.put (key, retValue);
		return retValue;
	}	//	get

	/**	Cache						*/
	private static CCache<Integer, MProcessPara> s_cache 
		= new CCache<Integer, MProcessPara> ("AD_Process_Para", 20);
	
	
	/**************************************************************************
	 * 	Constructor
	 *	@param ctx context
	 *	@param AD_Process_Para_ID id
	 *	@param trxName transaction
	 */
	public MProcessPara (Properties ctx, int AD_Process_Para_ID, String trxName)
	{
		super (ctx, AD_Process_Para_ID, trxName);
		if (AD_Process_Para_ID == 0)
		{
		//	setAD_Process_ID (0);	Parent
		//	setName (null);
		//	setColumnName (null);
			
			setFieldLength (0);
			setSeqNo (0);
		//	setAD_Reference_ID (0);
			setIsCentrallyMaintained (true);
			setIsRange (false);
			setIsMandatory (false);
			setEntityType (ENTITYTYPE_UserMaintained);
		}
	}	//	MProcessPara

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName transaction
	 */
	public MProcessPara (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MProcessPara

	/** Virtual Window No - 999	*/
	public static int		WINDOW_NO = 999;
	/** Virtual Tab No - 0		*/
	public static int		TAB_NO = 0;
	
	/**	The Lookup				*/
	private Lookup		m_lookup = null;
	

	/**
	 *  Is this field a Lookup?.
	 *  @return true if lookup field
	 */
	public boolean isLookup()
	{
		boolean retValue = false;
		int displayType = getAD_Reference_ID(); 
		if (DisplayType.isLookup(displayType))
			retValue = true;
		else if (displayType == DisplayType.Location
			|| displayType == DisplayType.Locator
			|| displayType == DisplayType.Account
			|| displayType == DisplayType.PAttribute)
			retValue = true;
		return retValue;
	}   //  isLookup

	/**
	 *  Set Lookup for columns with lookup
	 */
	public void loadLookup()
	{
		if (!isLookup())
			return;
		log.fine("(" + getColumnName() + ")");
		int displayType = getAD_Reference_ID();
		if (DisplayType.isLookup(displayType))
		{
			MLookupInfo  lookupInfo = MLookupFactory.getLookupInfo(getCtx(), 0, 
				getAD_Process_Para_ID(), getAD_Reference_ID(), 
				Env.getLanguage(getCtx()), getColumnName(), 
				getAD_Reference_Value_ID(), false, "");
			if (lookupInfo == null)
			{
				log.log(Level.SEVERE, "(" + getColumnName() + ") - No LookupInfo");
				return;
			}
			//	Prevent loading of CreatedBy/UpdatedBy
			if (displayType == DisplayType.Table
				&& (getColumnName().equals("CreatedBy") || getColumnName().equals("UpdatedBy")) )
			{
				lookupInfo.IsCreadedUpdatedBy = true;
				lookupInfo.DisplayType = DisplayType.Search;
			}
			//
			MLookup ml = new MLookup (lookupInfo, TAB_NO);
			m_lookup = ml;
		}
		else if (displayType == DisplayType.Location)   //  not cached
		{
			MLocationLookup ml = new MLocationLookup (getCtx(), WINDOW_NO);
			m_lookup = ml;
		}
		else if (displayType == DisplayType.Locator)
		{
			MLocatorLookup ml = new MLocatorLookup (getCtx(), WINDOW_NO);
			m_lookup = ml;
		}
		else if (displayType == DisplayType.Account)    //  not cached
		{
			MAccountLookup ma = new MAccountLookup (getCtx(), WINDOW_NO);
			m_lookup = ma;
		}
		else if (displayType == DisplayType.PAttribute)    //  not cached
		{
			MPAttributeLookup pa = new MPAttributeLookup (getCtx(), WINDOW_NO);
			m_lookup = pa;
		}
		//
		if (m_lookup != null)
			m_lookup.loadComplete();
	}   //  loadLookup

	/**
	 * 	Get Lookup for Parameter
	 *	@return lookup or null
	 */
	public Lookup getLookup()
	{
		if (m_lookup == null && isLookup())
			loadLookup();
		return m_lookup;
	}	//	getLookup
	
	/**
	 * 	String Representation
	 *	@return info
	 */
	public String toString ()
	{
		StringBuffer sb = new StringBuffer ("MProcessPara[")
			.append (get_ID ())
			.append ("]");
		return sb.toString ();
	}	//	toString

}	//	MProcessPara
