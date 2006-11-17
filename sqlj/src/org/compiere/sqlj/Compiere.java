/******************************************************************************
 * Product: Compiere ERP & CRM Smart Business Solution                        *
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
package org.compiere.sqlj;

import java.io.*;
import java.math.*;
import java.sql.*;
import java.util.*;


/**
 *	SQLJ Compiere Control and Utility Class
 *	
 *  @author Jorg Janke
 *  @version $Id: Compiere.java,v 1.4 2006/10/02 19:49:59 jjanke Exp $
 */
public class Compiere implements Serializable
{
	/**
	 * 	Get Version
	 *	@return version
	 */
	public static String getVersion()
	{
		return "Compiere SQLJ 1.0";
	}	//	version
	
	/**
	 * 	Get Environment Info
	 *	@return properties
	 */
	public static String getProperties()
	{
		StringBuffer sb = new StringBuffer();
		Enumeration en = System.getProperties().keys();
		while (en.hasMoreElements())
		{
			if (sb.length() != 0)
				sb.append(" - ");
			String key = (String)en.nextElement();
			String value = System.getProperty(key);
			sb.append(key).append("=").append(value);
		}
		return sb.toString();
	}	//	environment

	/**
	 * 	Get Environment Info
	 * 	@param key key
	 *	@return property info
	 *	@throws SQLException
	 */
	public static String getProperty (String key) throws SQLException
	{
		if (key == null || key.length() == 0)
			return "null";
		return System.getProperty(key, "NotFound");
	}	//	environment

	/** Oracle Server					*/
	public static final String TYPE_ORACLE = "oracle";
	/** Derby Server					*/
	public static final String TYPE_DERBY = "derby";
	/** DB2 Server						*/
	public static final String TYPE_DB2 = "db2";
        
        //begin e-evolution vpj-cd 02/02/2005 PostgreSQL
	/** PostgreSQL **/
	public static final String TYPE_POSTGRESQL = "PostgreSQL";
        public static final String TYPE_EDB = "EDB";
	//end e-evolution vpj-cd 02/02/2005 PostgreSQL	
        
	/** Server Type						*/
	public static String 	s_type = null;
	
	/**
	 * 	Get Server Type
	 *	@return server type
	 */
	public static String getServerType()
	{
		if (s_type == null)
		{
			String vendor = System.getProperty("java.vendor");
			if (vendor.startsWith("Oracle"))
				s_type = TYPE_ORACLE;
			else if (vendor.startsWith("Derby"))
				s_type = TYPE_DERBY;
			else
				s_type = "??";
		}
		return s_type;
	}	//	getServerType
	
	/**
	 * 	Is this Oracle ?
	 *	@return true if Oracle
	 */
	static boolean isOracle()
	{
		if (s_type == null)
			getServerType();
		if (s_type != null)
			return TYPE_ORACLE.equals(s_type);
		return false;
	}	//	isOracle
	
	/**
	 * 	Is this Derby ?
	 *	@return true if Derby
	 */
	static boolean isDerby()
	{
		if (s_type == null)
			getServerType();
		if (s_type != null)
			return TYPE_DERBY.equals(s_type);
		return false;
	}	//	isDerby
	
                    //begin vpj-cd e-evolution 02/22/2005 PostgreSQL
	/**
	 * 	Is this PostgreSQL ?
	 *	@return true if Sybase
	 */
	static boolean isPostgreSQL()
	{
		if (s_type == null)
			getServerType();
		if (s_type != null)
			return TYPE_POSTGRESQL.equals(s_type);
		return false;
	}	
        //	isEDB
        	static boolean isEDB()
	{
		if (s_type == null)
			getServerType();
		if (s_type != null)
			return TYPE_EDB.equals(s_type);
		return false;
	}	//	isEDB
	//end vpj-cd e-evolution 02/22/2005 PostgreSQL
	/**
	 * 	Get Connection URL
	 *	@return connection URL
	 */
	static String getConnectionURL()
	{
		if (s_url != null)
			return s_url;
		
		if (isOracle())
			s_url = "jdbc:default:connection:";
		else if (isDerby())
			s_url = "jdbc:default:connection";
                    //begin vpj-cd e-evolution 02/22/2005 PostgreSQL
		else if (isPostgreSQL())
			return "jdbc:default:connection";
                else if (isEDB())
			return "jdbc:default:connection";
		return "jdbc:default:connection";
		//
		//return s_url;
		//end vpj-cd e-evolution 02/22/2005 PostgreSQL
	}	//	getConnectionURL
	
	/**	Connection URL				*/
	protected static String		s_url = null;
	/**	Connection User				*/
	protected static String		s_uid = null;
	/**	Connection Password			*/
	protected static String		s_pwd = null;
	
	/**
	 * 	Get Server side Connection
	 *	@return connection
	 *	@throws SQLException
	 */
	private static Connection getConnection() throws SQLException
	{
		if (s_uid != null && s_pwd != null)
			return DriverManager.getConnection(getConnectionURL(), s_uid, s_pwd);
		return DriverManager.getConnection(getConnectionURL());
	}	//	getConnection

	/**
	 * 	Prepare Statement (Forward, ReadOnly)
	 *	@param sql sql
	 *	@return prepared statement
	 *	@throws SQLException
	 */
	static PreparedStatement prepareStatement (String sql) throws SQLException
	{
		return prepareStatement(sql, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
	}	//	prepareStatement
	
	/**
	 * 	Prepare Statement
	 *	@param sql sql
	 *	@param resultSetType result set type
	 *	@param resultSetCurrency result type currency
	 *	@return prepared statement
	 *	@throws SQLException
	 */
	static PreparedStatement prepareStatement (String sql, int resultSetType, int resultSetCurrency)
		throws SQLException
	{
		if (s_conn == null)
			s_conn = getConnection();
		try
		{
			return s_conn.prepareStatement(sql, resultSetType, resultSetCurrency);
		}
		catch (Exception e)	//	connection not good anymore
		{
		}
		//	get new Connection
		s_conn = getConnection();
		return s_conn.prepareStatement(sql);
	}	//	

	/**
	 * 	Get SQL int Value with param 
	 *	@param sql sql command
	 *	@param param1 parameter
	 *	@return value or -1 if not found
	 *	@throws SQLException
	 */
	static int getSQLValue (String sql, int param1) throws SQLException
	{
		int retValue = -1;
		PreparedStatement pstmt = prepareStatement(sql);
		pstmt.setInt(1, param1);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next())
			retValue = rs.getInt(1);
		rs.close();
		pstmt.close();
		return retValue;
	}	//	getSQLValue
	
	/** Permanently open Connection		*/
	private static Connection s_conn = null;
	
	/** Zero 0				*/
	public static final BigDecimal ZERO = new BigDecimal((double)0.0);
	/** One 1				*/
	public static final BigDecimal ONE = new BigDecimal((double)1.0);
	/** Hundred 100				*/
	public static final BigDecimal HUNDRED = new BigDecimal((double)100.0);
	

	/**
	 * 	Truncate Date
	 *	@param p_dateTime date
	 *	@return day
	 */
	public static Timestamp trunc (Timestamp p_dateTime)
	{
		Timestamp time = p_dateTime;
		if (time == null)
			time = new Timestamp(System.currentTimeMillis());
		//
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(time);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		//
		java.util.Date temp = cal.getTime();
		return new Timestamp (temp.getTime());
	}	//	trunc

	/**
	 * 	Truncate Date
	 *	@param p_dateTime date
	 *	@param XX date part - Supported: DD(default),DY,MM,Q 
	 *	@return day (first)
	 */
	public static Timestamp firstOf (Timestamp p_dateTime, String XX)
	{
		Timestamp time = p_dateTime;
		if (time == null)
			time = new Timestamp(System.currentTimeMillis());
		//
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(time);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		//
		if ("MM".equals(XX))					//	Month
			cal.set(Calendar.DAY_OF_MONTH, 1);
		else if ("DY".equals(XX))				//	Week
			cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		else if ("Q".equals(XX))				//	Quarter
		{
			cal.set(Calendar.DAY_OF_MONTH, 1);
			int mm = cal.get(Calendar.MONTH);	//	January = 0
			if (mm < Calendar.APRIL)
				cal.set(Calendar.MONTH, Calendar.JANUARY);
			else if (mm < Calendar.JULY)
				cal.set(Calendar.MONTH, Calendar.APRIL);
			else if (mm < Calendar.OCTOBER)
				cal.set(Calendar.MONTH, Calendar.JULY);
			else
				cal.set(Calendar.MONTH, Calendar.OCTOBER);
		}
		//
		java.util.Date temp = cal.getTime();
		return new Timestamp (temp.getTime());
	}	//	trunc
	
	/**
	 * 	Calculate the number of days between start and end.
	 * 	@param start start date
	 * 	@param end end date
	 * 	@return number of days (0 = same)
	 */
	static public int getDaysBetween (Timestamp start, Timestamp end)
	{
		boolean negative = false;
		if (end.before(start))
		{
			negative = true;
			Timestamp temp = start;
			start = end;
			end = temp;
		}
		//
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(start);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		GregorianCalendar calEnd = new GregorianCalendar();
		calEnd.setTime(end);
		calEnd.set(Calendar.HOUR_OF_DAY, 0);
		calEnd.set(Calendar.MINUTE, 0);
		calEnd.set(Calendar.SECOND, 0);
		calEnd.set(Calendar.MILLISECOND, 0);

	//	System.out.println("Start=" + start + ", End=" + end + ", dayStart=" + cal.get(Calendar.DAY_OF_YEAR) + ", dayEnd=" + calEnd.get(Calendar.DAY_OF_YEAR));

		//	in same year
		if (cal.get(Calendar.YEAR) == calEnd.get(Calendar.YEAR))
		{
			if (negative)
				return (calEnd.get(Calendar.DAY_OF_YEAR) - cal.get(Calendar.DAY_OF_YEAR)) * -1;
			return calEnd.get(Calendar.DAY_OF_YEAR) - cal.get(Calendar.DAY_OF_YEAR);
		}

		//	not very efficient, but correct
		int counter = 0;
		while (calEnd.after(cal))
		{
			cal.add (Calendar.DAY_OF_YEAR, 1);
			counter++;
		}
		if (negative)
			return counter * -1;
		return counter;
	}	//	getDaysBetween

	/**
	 * 	Return Day + offset (truncates)
	 * 	@param day Day
	 * 	@param offset day offset
	 * 	@return Day + offset at 00:00
	 */
	static public Timestamp addDays (Timestamp day, int offset)
	{
		if (day == null)
			day = new Timestamp(System.currentTimeMillis());
		//
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(day);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		if (offset != 0)
			cal.add(Calendar.DAY_OF_YEAR, offset);	//	may have a problem with negative (before 1/1)
		//
		java.util.Date temp = cal.getTime();
		return new Timestamp (temp.getTime());
	}	//	addDays

	/**
	 * 	Next Business Day.
	 * 	(Only Sa/Su -> Mo)
	 *	@param day day
	 *	@return next business dat if day is "off"
	 */
	static public Timestamp nextBusinessDay (Timestamp day)
	{
		if (day == null)
			day = new Timestamp(System.currentTimeMillis());
		//
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(day);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		//
		int dow = cal.get(Calendar.DAY_OF_WEEK);
		if (dow == Calendar.SATURDAY)
			cal.add(Calendar.DAY_OF_YEAR, 2);
		else if (dow == Calendar.SUNDAY)
			cal.add(Calendar.DAY_OF_YEAR, 1);
		//
		java.util.Date temp = cal.getTime();
		return new Timestamp (temp.getTime());
	}	//	nextBusinessDay	
	
	
	/**
	 * 	Character At Position
	 *	@param source source
	 *	@param posIndex position 1 = first
	 *	@return substring or null
	 */
	public static String charAt (String source, int posIndex)
	{
		if (source == null || source.length() == 0 || posIndex < 0 || posIndex >= source.length())
			return null;
		try
		{
			return (source.substring(posIndex+1, posIndex+2));
		}
		catch (Exception e)
		{}
		return null;
	}	//	charAt
	
	/**
	 * 	Mext ID
	 *	@param AD_Sequence_ID sequence
	 *	@param System system
	 *	@return ID or -1
	 *	@throws SQLException
	 */
	public static int nextID (int AD_Sequence_ID, String System)
		throws SQLException
	{
		boolean isSystem = System != null && "Y".equals(System);
		int retValue = -1;
		StringBuffer sql = new StringBuffer ("SELECT CurrentNext");
		if (isSystem)
			sql.append("Sys");
		sql.append(",IncrementNo FROM AD_Sequence WHERE AD_Sequence_ID=?");
		PreparedStatement pstmt = prepareStatement(sql.toString(),
			ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next())
		{
			retValue = rs.getInt(1);
			int incrementNo = rs.getInt(2);
			rs.updateInt(2, retValue + incrementNo);
			pstmt.getConnection().commit();
		}
		rs.close();
		pstmt.close();
		//
		return retValue;
	}	//	nextID
	
	
	/**
	 * 	get current Date (Timestamp)
	 *	@return Timestamp
	 *	
	 */
	
	//jz not found anywhere, add it
	public static Timestamp getDate()
	{
		return new Timestamp(new java.util.Date().getTime());
	}	//	getDate
	
	/**
	 * 	get chars from a number
	 *  @param n int
	 *	@return String
	 *	
	 */	
	/*jz not found anywhere, add it
	public static String getChars(int n)
	{
		return Integer.toString(n);
	}	//	getDate
	
	/**
	 * 	get chars from a number
	 *  @param d double
	 *	@return String
	 *	
	 */	
	//jz not found anywhere, add it
	public static String getChars(BigDecimal d)
	{
		return d.toString();
	}	//	getDate
	
}	//	Compiere
