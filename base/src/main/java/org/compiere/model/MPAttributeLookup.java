/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
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

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogMgt;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.KeyNamePair;
import org.compiere.util.NamePair;

/**
 *	Product Attribute Lookup Model (not Cached)
 *	
 *  @author Jorg Janke
 *  @version $Id: MPAttributeLookup.java,v 1.2 2006/07/30 00:58:38 jjanke Exp $
 *  
 * @author Teo Sarca, SC ARHIPAC SERVICE SRL
 * 			<li>BF [ 1885260 ] MPAttributeLookup: throws SQLException sometimes
 */
public class MPAttributeLookup extends Lookup
	implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 70273805106666111L;

	/**
	 * 	Constructor
	 * 	@param ctx context
	 *	@param WindowNo window no
	 */
	public MPAttributeLookup(Properties ctx, int WindowNo)
	{
		super(DisplayType.TableDir, WindowNo);
//		m_ctx = ctx;
	}	//	MPAttribute

//	/**	Properties					*/
//	private Properties 			m_ctx;
	/**	No Instance Value			*/
	private static KeyNamePair	NO_INSTANCE = new KeyNamePair (0,"");

	/**
	 *	Get Display for Value (not cached)
	 *  @param value Location_ID
	 *  @return String Value
	 */
	public String getDisplay (Object value)
	{
		if (value == null)
			return "";
		NamePair pp = get (value);
		if (pp == null)
			return "<" + value.toString() + ">";
		return pp.getName();
	}	//	getDisplay

	/**
	 *  The Lookup contains the key (not cached)
	 *  @param key Location_ID
	 *  @return true if key known
	 */
	public boolean containsKey (Object key)
	{
		return get(key) != null;
	}   //  containsKey

	/**
	 *	Get Object of Key Value
	 *  @param value value
	 *  @return Object or null
	 */
	public NamePair get (Object value)
	{
		if (value == null)
			return null;
		int M_AttributeSetInstance_ID = 0;
		if (value instanceof Integer)
			M_AttributeSetInstance_ID = ((Integer)value).intValue();
		else
		{
			try
			{
				M_AttributeSetInstance_ID = Integer.parseInt(value.toString());
			}
			catch (Exception e)
			{
				log.log(Level.SEVERE, "Value=" + value, e);
			}
		}
		if (M_AttributeSetInstance_ID == 0)
			return NO_INSTANCE;
		//
		String Description = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement("SELECT Description "
					+ "FROM M_AttributeSetInstance "
					+ "WHERE M_AttributeSetInstance_ID=?", null);
			pstmt.setInt(1, M_AttributeSetInstance_ID);
			rs = pstmt.executeQuery();
			if (rs.next())
			{
				Description = rs.getString(1);			//	Description
				if (Description == null || Description.length() == 0)
				{
					if (CLogMgt.isLevelFine())
						Description = "{" + M_AttributeSetInstance_ID + "}";
					else
						Description = "";
				}
			}
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, "get", e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		if (Description == null)
			return null;
		return new KeyNamePair (M_AttributeSetInstance_ID, Description);
	}	//	get

	/**
	 * 	Dispose
	 *	@see org.compiere.model.Lookup#dispose()
	 */
	public void dispose()
	{
		log.fine("");
		super.dispose();
	}	//	dispose

	/**
	 *	Return data as sorted Array - not implemented
	 *  @param mandatory mandatory
	 *  @param onlyValidated only validated
	 *  @param onlyActive only active
	 * 	@param temporary force load for temporary display
	 *  @return null
	 */
	public ArrayList<Object> getData (boolean mandatory, boolean onlyValidated, boolean onlyActive, boolean temporary)
	{
		log.log(Level.SEVERE, "Not implemented");
		return null;
	}   //  getArray

	/**
	 *	Get underlying fully qualified Table.Column Name.
	 *	Used for VLookup.actionButton (Zoom)
	 *  @return column name
	 */
	public String getColumnName()
	{
		return "M_AttributeSetInstance_ID";
	}	//	getColumnName

}	//	MPAttribute
