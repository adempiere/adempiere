/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Contributor: Goodwill Consulting (www.goodwill.co.id)                      *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 *****************************************************************************/
package org.compiere.model;

import java.sql.*;
import java.util.*;
import java.util.logging.*;

import org.compiere.util.*;

/**
 *	System Configuration
 *	
 *  @author Armen Rizal
 *  @version $Id: MSysConfig.java,v 1.5 2005/11/28 11:56:45 armen Exp $
 *  Contributor: Carlos Ruiz - globalqss - [ 1800371 ] System Configurator Enhancements
 */
public class MSysConfig extends X_AD_SysConfig
{
	/**
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param AD_SysConfig_ID id
	 */
	public MSysConfig (Properties ctx, int AD_SysConfig_ID, String trxName)
	{
		super (ctx, AD_SysConfig_ID, trxName);
		if (AD_SysConfig_ID == 0)
		{
		//	setName (null);
		//  setValue (null);
		}	
	}	//	MSysConfig

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 */
	public MSysConfig (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MSysConfig
	
	/**	Static Logger	*/
	private static CLogger	s_log	= CLogger.getCLogger (MSysConfig.class);
	
	/**
	 * Get system configuration property of type string
	 * @param Name
	 * @param defaultValue
	 * @return String
	 */
	public static String getValue(String Name, String defaultValue)
	{
		String str = null;
		String sql = "SELECT Value FROM AD_SysConfig WHERE Name=? AND AD_Client_ID = 0 AND AD_Org_ID = 0 AND IsActive='Y'";
		try
		{
			PreparedStatement pstmt = DB.prepareStatement(sql, null);
			pstmt.setString(1, Name);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
				str = rs.getString(1);
			rs.close();
			pstmt.close();
		}
		catch (SQLException e)
		{
			s_log.log(Level.SEVERE, "getValue", e);
		} 
		if (str == null)
			return defaultValue;
		return (str.trim());
	}
	
	/**
	 * Get system configuration property of type string
	 * @param Name
	 * @return String
	 */
	public static String getValue(String Name)
	{
		return (getValue(Name, null));
	}
	
	/**
	 * Get system configuration property of type int
	 * @param Name
	 * @param defaultValue
	 * @return int
	 */
	public static int getIntValue(String Name, int defaultValue)
	{
		String s = getValue(Name);
		if (s == null)
			return defaultValue; 
		
		if (s.length() == 0)
			return defaultValue;
		//
		try
		{
			return Integer.parseInt(s);
		}
		catch (NumberFormatException e)
		{
			s_log.log(Level.SEVERE, "getIntValue (" + Name + ") = " + s, e);
		}
		return defaultValue;
	}
	
	/**
	 * Get system configuration property of type double
	 * @param Name
	 * @param defaultValue
	 * @return double
	 */
	public static double getDoubleValue(String Name, double defaultValue)
	{
		String s = getValue(Name);
		if (s == null || s.length() == 0)
			return defaultValue;
		//
		try
		{
			return Double.parseDouble(s);
		}
		catch (NumberFormatException e)
		{
			s_log.log(Level.SEVERE, "getDoubleValue (" + Name + ") = " + s, e);
		}
		return defaultValue;
	}
	
	/**
	 * Get system configuration property of type boolean
	 * @param Name
	 * @param defaultValue
	 * @return boolean
	 */
	public static boolean getBooleanValue(String Name, boolean defaultValue)
	{
		String s = getValue(Name);
		if (s == null || s.length() == 0)
			return defaultValue;
		
		if ("Y".equalsIgnoreCase(s))
			return true;
		else if ("N".equalsIgnoreCase(s))
			return false;
		else
			return Boolean.valueOf(s).booleanValue();
	}
	
	/**
	 * Get client configuration property of type string
	 * @param Name
	 * @param defaultValue
	 * @param Client ID
	 * @return String
	 */
	public static String getValue(String Name, String defaultValue, int AD_Client_ID)
	{
		String str = null;
		String sql = "SELECT Value FROM AD_SysConfig WHERE Name=? AND AD_Client_ID = ? AND AD_Org_ID = 0 AND IsActive='Y'";
		try
		{
			PreparedStatement pstmt = DB.prepareStatement(sql, null);
			pstmt.setString(1, Name);
			pstmt.setInt(2, AD_Client_ID);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
				str = rs.getString(1);
			rs.close();
			pstmt.close();
		}
		catch (SQLException e)
		{
			s_log.log(Level.SEVERE, "getValue", e);
		} 
		if (str == null) {
			// if not found by client - get the system parameter
			return getValue(Name, defaultValue);
		}
		return (str.trim());
	}
	
	/**
	 * Get system configuration property of type string
	 * @param Name
	 * @param Client ID
	 * @return String
	 */
	public static String getValue(String Name, int AD_Client_ID)
	{
		return (getValue(Name, null, AD_Client_ID));
	}
	
	/**
	 * Get system configuration property of type int
	 * @param Name
	 * @param defaultValue
	 * @param Client ID
	 * @return int
	 */
	public static int getIntValue(String Name, int defaultValue, int AD_Client_ID)
	{
		String s = getValue(Name, AD_Client_ID);
		if (s == null)
			return defaultValue; 
		
		if (s.length() == 0)
			return defaultValue;
		//
		try
		{
			return Integer.parseInt(s);
		}
		catch (NumberFormatException e)
		{
			s_log.log(Level.SEVERE, "getIntValue (" + Name + ") = " + s, e);
		}
		return defaultValue;
	}
	
	/**
	 * Get system configuration property of type double
	 * @param Name
	 * @param defaultValue
	 * @param Client ID
	 * @return double
	 */
	public static double getDoubleValue(String Name, double defaultValue, int AD_Client_ID)
	{
		String s = getValue(Name, AD_Client_ID);
		if (s == null || s.length() == 0)
			return defaultValue;
		//
		try
		{
			return Double.parseDouble(s);
		}
		catch (NumberFormatException e)
		{
			s_log.log(Level.SEVERE, "getDoubleValue (" + Name + ") = " + s, e);
		}
		return defaultValue;
	}
	
	/**
	 * Get system configuration property of type boolean
	 * @param Name
	 * @param defaultValue
	 * @param Client ID
	 * @return boolean
	 */
	public static boolean getBooleanValue(String Name, boolean defaultValue, int AD_Client_ID)
	{
		String s = getValue(Name, AD_Client_ID);
		if (s == null || s.length() == 0)
			return defaultValue;
		
		if ("Y".equalsIgnoreCase(s))
			return true;
		else if ("N".equalsIgnoreCase(s))
			return false;
		else
			return Boolean.valueOf(s).booleanValue();
	}

	/**
	 * Get client configuration property of type string
	 * @param Name
	 * @param defaultValue
	 * @param Client ID
	 * @param Organization ID
	 * @return String
	 */
	public static String getValue(String Name, String defaultValue, int AD_Client_ID, int AD_Org_ID)
	{
		String str = null;
		String sql = "SELECT Value FROM AD_SysConfig WHERE Name=? AND AD_Client_ID = ? AND AD_Org_ID = ? AND IsActive='Y'";
		try
		{
			PreparedStatement pstmt = DB.prepareStatement(sql, null);
			pstmt.setString(1, Name);
			pstmt.setInt(2, AD_Client_ID);
			pstmt.setInt(3, AD_Org_ID);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
				str = rs.getString(1);
			rs.close();
			pstmt.close();
		}
		catch (SQLException e)
		{
			s_log.log(Level.SEVERE, "getValue", e);
		} 
		if (str == null) {
			// if not found by organization - get the client parameter
			return getValue(Name, defaultValue, AD_Client_ID);
		}
		return (str.trim());
	}
	
	/**
	 * Get system configuration property of type string
	 * @param Name
	 * @param Client ID
	 * @param Organization ID
	 * @return String
	 */
	public static String getValue(String Name, int AD_Client_ID, int AD_Org_ID)
	{
		return (getValue(Name, null, AD_Client_ID, AD_Org_ID));
	}
	
	/**
	 * Get system configuration property of type int
	 * @param Name
	 * @param defaultValue
	 * @param Client ID
	 * @param Organization ID
	 * @return int
	 */
	public static int getIntValue(String Name, int defaultValue, int AD_Client_ID, int AD_Org_ID)
	{
		String s = getValue(Name, AD_Client_ID, AD_Org_ID);
		if (s == null)
			return defaultValue; 
		
		if (s.length() == 0)
			return defaultValue;
		//
		try
		{
			return Integer.parseInt(s);
		}
		catch (NumberFormatException e)
		{
			s_log.log(Level.SEVERE, "getIntValue (" + Name + ") = " + s, e);
		}
		return defaultValue;
	}
	
	/**
	 * Get system configuration property of type double
	 * @param Name
	 * @param defaultValue
	 * @param Client ID
	 * @param Organization ID
	 * @return double
	 */
	public static double getDoubleValue(String Name, double defaultValue, int AD_Client_ID, int AD_Org_ID)
	{
		String s = getValue(Name, AD_Client_ID, AD_Org_ID);
		if (s == null || s.length() == 0)
			return defaultValue;
		//
		try
		{
			return Double.parseDouble(s);
		}
		catch (NumberFormatException e)
		{
			s_log.log(Level.SEVERE, "getDoubleValue (" + Name + ") = " + s, e);
		}
		return defaultValue;
	}
	
	/**
	 * Get system configuration property of type boolean
	 * @param Name
	 * @param defaultValue
	 * @param Client ID
	 * @param Organization ID
	 * @return boolean
	 */
	public static boolean getBooleanValue(String Name, boolean defaultValue, int AD_Client_ID, int AD_Org_ID)
	{
		String s = getValue(Name, AD_Client_ID, AD_Org_ID);
		if (s == null || s.length() == 0)
			return defaultValue;
		
		if ("Y".equalsIgnoreCase(s))
			return true;
		else if ("N".equalsIgnoreCase(s))
			return false;
		else
			return Boolean.valueOf(s).booleanValue();
	}

	/**************************************************************************
	 * 	Before Save
	 *	@param newRecord
	 *	@return true if save
	 */
	protected boolean beforeSave (boolean newRecord)
	{
		log.fine("New=" + newRecord);
		
		if (getAD_Client_ID() != 0 || getAD_Org_ID() != 0) {
			
			// Get the configuration level from the System Record
			String configLevel = null;
			String sql = "SELECT ConfigurationLevel FROM AD_SysConfig WHERE Name=? AND AD_Client_ID = 0 AND AD_Org_ID = 0";
			try
			{
				PreparedStatement pstmt = DB.prepareStatement(sql, null);
				pstmt.setString(1, getName());
				ResultSet rs = pstmt.executeQuery();
				if (rs.next())
					configLevel = rs.getString(1);
				rs.close();
				pstmt.close();
			}
			catch (SQLException e)
			{
				s_log.log(Level.SEVERE, "getValue", e);
			}
			
			if (configLevel == null) {
				// not found for system
				// if saving an org parameter - look config in client
				if (getAD_Org_ID() != 0) {
					// Get the configuration level from the System Record
					sql = "SELECT ConfigurationLevel FROM AD_SysConfig WHERE Name=? AND AD_Client_ID = ? AND AD_Org_ID = 0";
					try
					{
						PreparedStatement pstmt = DB.prepareStatement(sql, null);
						pstmt.setString(1, getName());
						pstmt.setInt(2, getAD_Client_ID());
						ResultSet rs = pstmt.executeQuery();
						if (rs.next())
							configLevel = rs.getString(1);
						rs.close();
						pstmt.close();
					}
					catch (SQLException e)
					{
						s_log.log(Level.SEVERE, "getValue", e);
					}
				}
			}
			
			if (configLevel != null) {
				
				setConfigurationLevel(configLevel);
				
				// Disallow saving org parameter if the system parameter is marked as 'S' or 'C'
				if (getAD_Org_ID() != 0 && 
						(configLevel.equals(MSysConfig.CONFIGURATIONLEVEL_System) || 
						 configLevel.equals(MSysConfig.CONFIGURATIONLEVEL_Client))) {
					log.saveError( "Can't Save Org Level", "This is a system or client parameter, you can't save it as organization parameter" );
					return false;
				}

				// Disallow saving client parameter if the system parameter is marked as 'S'
				if (getAD_Client_ID() != 0 && configLevel.equals(MSysConfig.CONFIGURATIONLEVEL_System)) {
					log.saveError( "Can't Save Client Level", "This is a system parameter, you can't save it as client parameter" );
					return false;
				}

			} else {
				
				// fix possible wrong config level
				if (getAD_Org_ID() != 0)
					setConfigurationLevel(CONFIGURATIONLEVEL_Organization);
				else if (getAD_Client_ID() != 0 && getConfigurationLevel().equals(MSysConfig.CONFIGURATIONLEVEL_System))
					setConfigurationLevel(CONFIGURATIONLEVEL_Client);
				
			}

		}
		
		return true;
	}	//	beforeSave

}	//	MSysConfig