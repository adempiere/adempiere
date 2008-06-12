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
 * Copyright (C) 2003-2007 e-Evolution,SC. All Rights Reserved.               *
 * Contributor(s): Victor Perez www.e-evolution.com                           *
 *****************************************************************************/

package org.eevolution.model;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;


/**
 *
 * @author  Victor Perez www.e-evolution.com 
 */
public class QueryDB {

	private String classname;
	private static CLogger	log	= CLogger.getCLogger (QueryDB.class);
	/** Creates a new instance of POQuery */

	public QueryDB(String classname)
	{
		this.classname = classname;
	}



	public static Object newInstance(String classname, int id, String trxName ) 
	{
		Object result = null;
		Class args;
		int begin = classname.indexOf("X_") + 2 ;
		String table = classname.substring(begin);
		Class[] intArgsClass = new Class[] {Properties.class , int.class, String.class};
		//Integer height = new Integer(12);
		Integer ID = new Integer(id);
		Object[] intArgs = new Object[] {Env.getCtx(), ID,table};
		Constructor intArgsConstructor;

		try 
		{

			args = Class.forName(classname);
			intArgsConstructor = 
				args.getConstructor(intArgsClass);
			result = createObject(intArgsConstructor, intArgs);
			return result;
		}
		catch (ClassNotFoundException e) 
		{
			System.out.println(e);
			return result;
		} 
		catch (NoSuchMethodException e) 
		{
			System.out.println(e);
			return result;
		}
	}

	public static Object createObject(Constructor constructor, 
			Object[] arguments) {

		//System.out.println ("Constructor: " + constructor.toString());
		Object object = null;

		try {
			object = constructor.newInstance(arguments);
			//System.out.println ("Object: " + object.toString());
			return object;
		} catch (InstantiationException e) {
			log.log(Level.SEVERE,"InstantiationException:" + e);
		} catch (IllegalAccessException e) {
			log.log(Level.SEVERE,"IllegalAccessException:" + e);
		} catch (IllegalArgumentException e) {
			log.log(Level.SEVERE,"IllegalArgumentExceptio:" + e);
		} catch (InvocationTargetException e) {
			log.log(Level.SEVERE,"InvocationTargetException:" + e);
		}
		return object;
	}

	public List<Object> execute(String filter) {

		//String tablename = POClass.getName();     
		//System.out.print(classname.indexOf("X_"));   
		int begin = classname.indexOf("X_") + 2 ;
		String table = classname.substring(begin);
		StringBuffer sql = new StringBuffer("SELECT ").append(table).append("_ID FROM " + table);
		if (filter.equals(""))
			System.out.println("not exist filter");
		else
			sql.append(" WHERE ").append(filter);

		//System.out.println("Query " + sql.toString());

		List<Object> results = new ArrayList<Object>();

		try
		{
			PreparedStatement pstmt = DB.prepareStatement(sql.toString(), null);
			//pstmt.setInt(1, C_BPartner_ID);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
			{
				int id = rs.getInt(1);
				Object element =  newInstance(classname , id, table);
				results.add(element);				
			}
			rs.close();
			pstmt.close();
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE,"VCreateFrom.initIOS - Order\nSQL=" + sql.toString(), e);
		}

		return results;
	}   


	public List<Object> execute() {

		//String tablename = POClass.getName();     
		//System.out.print(classname.indexOf("X_"));   
		int begin = classname.indexOf("X_") + 2 ;
		String table = classname.substring(begin);
		StringBuffer sql = new StringBuffer("SELECT ").append(table).append("_ID FROM " + table);
		//if (filter.equals(""))
		//System.out.println("not exist filter");
		//else
		//sql.append(" WHERE ").append(filter);

		//System.out.println("Query " + sql.toString());

		List<Object> results = new ArrayList<Object>();

		try
		{
			PreparedStatement pstmt = DB.prepareStatement(sql.toString(), null);
			//pstmt.setInt(1, C_BPartner_ID);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
			{
				int id = rs.getInt(1);
				Object element =  newInstance(classname , id, table);
				results.add(element);				
			}
			rs.close();
			pstmt.close();
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE,"VCreateFrom.initIOS - Order\nSQL=" + sql.toString(), e);
		}

		return results;
	}   

}