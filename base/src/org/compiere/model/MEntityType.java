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
 * 	Enitity Type Model
 *	
 *  @author Jorg Janke
 *  @version $Id: MEntityType.java,v 1.2 2006/07/30 00:51:02 jjanke Exp $
 */
public class MEntityType extends X_AD_EntityType
{
	/**
	 * 	Get Entity Types
	 * 	@param ctx context
	 *	@return entity type array
	 */
	static public MEntityType[] getEntityTypes(Properties ctx)
	{
		if (s_entityTypes != null)
			return s_entityTypes;
		ArrayList<MEntityType> list = new ArrayList<MEntityType>();
		String sql = "SELECT * FROM AD_EntityType WHERE IsActive='Y' ORDER BY AD_EntityType_ID";
		PreparedStatement pstmt = null;
		try
		{
			pstmt = DB.prepareStatement (sql, null);
			ResultSet rs = pstmt.executeQuery ();
			while (rs.next ())
				list.add (new MEntityType (ctx, rs, null));
			rs.close ();
			pstmt.close ();
			pstmt = null;
		}
		catch (Exception e)
		{
			s_log.log (Level.SEVERE, sql, e);
		}
		try
		{
			if (pstmt != null)
				pstmt.close ();
			pstmt = null;
		}
		catch (Exception e)
		{
			pstmt = null;
		}
		s_entityTypes = new MEntityType[list.size()];
		list.toArray(s_entityTypes);
		s_log.finer("# " + s_entityTypes.length);
		return s_entityTypes;
	}	//	getEntityTypes
	
	/**
	 * 	Get Entity Type as String array
	 *	@param ctx context
	 *	@return entity type array
	 */
	static public String[] getEntityTypeStrings(Properties ctx)
	{
		MEntityType[] entityTypes = getEntityTypes(ctx);
		ArrayList<String> list = new ArrayList<String>();	//	list capabilities
		String[] retValue = new String[entityTypes.length];
		for (int i = 0; i < entityTypes.length; i++)
		{
			String s = entityTypes[i].getEntityType().trim();
			list.add(s);
			retValue[i] = s;
		}
		s_log.finer(list.toString());
		return retValue;
	}	//	getEntityTypeStrings

	/**
	 * 	Get Entity Type Classpath array
	 *	@param ctx context
	 *	@return classpath array
	 */
	static public String[] getClasspaths(Properties ctx)
	{
		MEntityType[] entityTypes = getEntityTypes(ctx);
		ArrayList<String> list = new ArrayList<String>();
		for (int i = 0; i < entityTypes.length; i++)
		{
			String classpath = entityTypes[i].getClasspath();
			if (classpath == null || classpath.length() == 0)
				continue;
			StringTokenizer st = new StringTokenizer(classpath, ";, \t\n\r\f");
			while (st.hasMoreTokens())
			{
				String token = st.nextToken();
				if (token.length() > 0)
				{
					if (!list.contains(token))
						list.add(token);
				}
			}
		}
		String[] retValue = new String[list.size()];
		list.toArray(retValue);
		s_log.finer(list.toString());
		return retValue;
	}	//	getClathpaths

	/**
	 * 	Get Entity Type Model Package array
	 *	@param ctx context
	 *	@return entity type array
	 */
	static public String[] getModelPackages(Properties ctx)
	{
		MEntityType[] entityTypes = getEntityTypes(ctx);
		ArrayList<String> list = new ArrayList<String>();
		list.add("adempiere.model");		//	default
		for (int i = 0; i < entityTypes.length; i++)
		{
			String modelPackage = entityTypes[i].getModelPackage();
			if (modelPackage == null || modelPackage.length() == 0)
				continue;
			StringTokenizer st = new StringTokenizer(modelPackage, ";, \t\n\r\f");
			while (st.hasMoreTokens())
			{
				String token = st.nextToken();
				if (token.length() > 0)
				{
					if (!list.contains(token))
						list.add(token);
				}
			}
		}
		String[] retValue = new String[list.size()];
		list.toArray(retValue);
		s_log.finer(list.toString());
		return retValue;
	}	//	getModelPackages
	
	/** Cached EntityTypes						*/
	private static MEntityType[] s_entityTypes = null;
	/**	Logger	*/
	private static CLogger s_log = CLogger.getCLogger (MEntityType.class);
	
	/**************************************************************************
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param AD_EntityType_ID id
	 *	@param trxName transaction
	 */
	public MEntityType (Properties ctx, int AD_EntityType_ID, String trxName)
	{
		super (ctx, AD_EntityType_ID, trxName);
	}	//	MEntityType

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName transaction
	 */
	public MEntityType (Properties ctx, ResultSet rs, String trxName)
	{
		super (ctx, rs, trxName);
	}	//	MEntityType
	
	/** 10=D, 20=C,  100=U, 110=CUST,  200=A, 210=EXT, 220=XX	*/
	private static final int	s_maxAD_EntityType_ID = 221;
	
	/**
	 * 	Set AD_EntityType_ID
	 */
	private void setAD_EntityType_ID()
	{
		int AD_EntityType_ID = getAD_EntityType_ID();
		if (AD_EntityType_ID == 0)
		{
			String sql = "SELECT NVL(MAX(AD_EntityType_ID), 999999) FROM AD_EntityType WHERE AD_EntityType_ID > 1000";
			AD_EntityType_ID= DB.getSQLValue (get_TrxName(), sql);
			setAD_EntityType_ID(AD_EntityType_ID+1);
		}
	}	//	setAD_EntityType_ID
	
	/**
	 * 	Is System Maintained
	 *	@return true if D/C/U/CUST/A/EXT/XX
	 */
	public boolean isSystemMaintained()
	{
		int id = getAD_EntityType_ID();
		return id < s_maxAD_EntityType_ID;
	}	//	isSystemMaintained
	
	/**
	 * 	Before Save
	 *	@param newRecord new
	 *	@return true if it can be saved
	 */
	protected boolean beforeSave (boolean newRecord)
	{
		if (!newRecord)
		{
			int id = getAD_EntityType_ID();
			boolean systemMaintained = (id == 10 || id == 20);	//	C/D
			if (systemMaintained)
			{
				log.saveError("Error", "You cannot modify a System maintained entity");
				return false;
			}
			systemMaintained = is_ValueChanged("EntityType");
			if (systemMaintained)
			{
				log.saveError("Error", "You cannot modify EntityType");
				return false;
			}
			systemMaintained = id < s_maxAD_EntityType_ID
				&&	(is_ValueChanged("Name") || is_ValueChanged("Description") 
					|| is_ValueChanged("Help") || is_ValueChanged("IsActive"));
			if (systemMaintained)
			{
				log.saveError("Error", "You cannot modify Name,Description,Help");
				return false;
			}
		}
		else	//	new
		{
			/*
			setEntityType(getEntityType().toUpperCase());	//	upper case
			if (getEntityType().trim().length() < 4)
			{
				log.saveError("FillMandatory", Msg.getElement(getCtx(), "EntityType") 
					+ " - 4 Characters");
				return false;
			}
			boolean ok = true;
			char[] cc = getEntityType().toCharArray();
			for (int i = 0; i < cc.length; i++)
			{
				char c = cc[i];
				if (Character.isDigit(c) || (c >= 'A' && c <= 'Z'))
					continue;
				//
				log.saveError("FillMandatory", Msg.getElement(getCtx(), "EntityType") 
					+ " - Must be ASCII Letter or Digit");
				return false;
			}
			*/
			setAD_EntityType_ID();
		}	//	new
		s_entityTypes = null;	//	reset
		return true;
	}	//	beforeSave
	
	/**
	 * 	Before Delete
	 *	@return true if it can be deleted
	 */
	protected boolean beforeDelete ()
	{
		if (isSystemMaintained())	//	all pre-defined
		{
			log.saveError("Error", "You cannot delete a System maintained entity");
			return false;
		}
		s_entityTypes = null;	//	reset
		return true;
	}	//	beforeDelete
	
}	//	MEntityType
