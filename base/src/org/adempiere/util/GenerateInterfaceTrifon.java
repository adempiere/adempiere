/**********************************************************************
* This file is part of Adempiere ERP Bazaar                           *
* http://www.adempiere.org                                            *
*                                                                     *
* Copyright (C) Trifon Trifonov.                                      *
* Copyright (C) Contributors                                          *
*                                                                     *
* This program is free software; you can redistribute it and/or       *
* modify it under the terms of the GNU General Public License         *
* as published by the Free Software Foundation; either version 2      *
* of the License, or (at your option) any later version.              *
*                                                                     *
* This program is distributed in the hope that it will be useful,     *
* but WITHOUT ANY WARRANTY; without even the implied warranty of      *
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the        *
* GNU General Public License for more details.                        *
*                                                                     *
* You should have received a copy of the GNU General Public License   *
* along with this program; if not, write to the Free Software         *
* Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,          *
* MA 02110-1301, USA.                                                 *
*                                                                     *
* Contributors:                                                       *
* - Trifon Trifonov (trifonnt@users.sourceforge.net)                  *
*                                                                     *
* Sponsors:                                                           *
* - Company (http://www.d3-soft.com)                                  *
***********************************************************************/

package org.adempiere.util;

import java.io.File;
import java.io.FileWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.logging.Level;

import org.compiere.Adempiere;
import org.compiere.util.CLogMgt;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;

/**
 *	@author Trifon Trifonov
 *	@version $Id$
 */
public class GenerateInterfaceTrifon {
	
	private String packageName = "";
	
	public GenerateInterfaceTrifon(int AD_Table_ID, String directory, String packageName) {
		this.packageName = packageName;
		// create column access methods
		StringBuffer mandatory = new StringBuffer();
		StringBuffer sb = createColumns(AD_Table_ID, mandatory);
		
		// Header
		String tableName = createHeader(AD_Table_ID, sb, mandatory);
		
		// Save
		if (directory.endsWith("/") || directory.endsWith("\\")) 
		{
			
		} else {
			directory = directory + "/"; 
		}
		writeToFile(sb, directory + tableName + ".java");
	}

	/** Generated on */
	private Timestamp s_run = new Timestamp(System.currentTimeMillis());

	/** Logger */
	private static CLogger log = CLogger.getCLogger(GenerateInterfaceTrifon.class);

	/**
	 * Add Header info to buffer
	 * 
	 * @param AD_Table_ID	table
	 * @param sb			buffer
	 * @param mandatory		init call for mandatory columns
	 * @param packageName	package name
	 * @return class name
	 */
	private String createHeader(int AD_Table_ID, StringBuffer sb, StringBuffer mandatory) {
		String tableName = "";
		int accessLevel = 0;
		String sql = "SELECT TableName, AccessLevel FROM AD_Table WHERE AD_Table_ID=?";
		PreparedStatement pstmt = null;
		try {
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, AD_Table_ID);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				tableName = rs.getString(1);
				accessLevel = rs.getInt(2);
			}
			rs.close();
			pstmt.close();
			pstmt = null;
		} catch (Exception e) {
			log.log(Level.SEVERE, sql, e);
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (Exception e) {
			}
			pstmt = null;
		}
		if (tableName == null)
			throw new RuntimeException("TableName not found for ID=" + AD_Table_ID);
		//
		String className = "I_" + tableName;
		//
		StringBuffer start = new StringBuffer()
			.append("/** Generated Model - DO NOT CHANGE - Copyright (C) 2006-2007 Adempiere */\n")
			.append("package ").append(packageName).append(";").append(Env.NL)
		;
		
		if (!packageName.equals("org.compiere.model")) {
			start.append("import org.compiere.model.*;");
		}
		
		start.append("import java.util.*;")
			 .append("import java.sql.*;")
			 .append("import java.math.*;")
			 .append("import org.compiere.util.*;")
			 .append("\n")
				// Class
			 .append("/** Generated Model for ").append(tableName).append("\n")
			 .append(" *  @author Trifon Trifonov (generated) \n")
			 .append(" *  @version ").append(Adempiere.MAIN_VERSION).append(" - ").append(s_run).append(" */\n")
			 .append("public interface ").append(className).append(" {").append("\n")
			 
			 .append("/** AD_Table_ID=").append(AD_Table_ID).append(" */\n")
			 .append("public static final int Table_ID=").append(AD_Table_ID).append(";\n")
			 
			 .append("/** TableName=") .append(tableName).append(" */\n")
			 .append("public static final String Table_Name=\"").append(tableName).append("\";\n")
			 
			 .append("public KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);\n")
			 
			 .append("public BigDecimal AccessLevel = new BigDecimal(").append(accessLevel).append(");\n")
			 
			 .append("/** Load Meta Data */\n")
			 .append(" POInfo initPO (Properties ctx);")
		;

		StringBuffer end = new StringBuffer("}");
		//
		sb.insert(0, start);
		sb.append(end);

		return className;
	}

	/**
	 * Create Column access methods
	 * 
	 * @param AD_Table_ID table
	 * @param mandatory   init call for mandatory columns
	 * @return set/get method
	 */
	private StringBuffer createColumns(int AD_Table_ID, StringBuffer mandatory) {
		StringBuffer sb = new StringBuffer();
		String sql = "SELECT c.ColumnName, c.IsUpdateable, c.IsMandatory," // 1..3
				+ " c.AD_Reference_ID, c.AD_Reference_Value_ID, DefaultValue, SeqNo, " // 4..7
				+ " c.FieldLength, c.ValueMin, c.ValueMax, c.VFormat, c.Callout, " // 8..12
				+ " c.Name, c.Description, c.ColumnSQL, c.IsEncrypted "
				+ "FROM AD_Column c "
				+ "WHERE c.AD_Table_ID=?"
				+ " AND c.ColumnName <> 'AD_Client_ID'"
				+ " AND c.ColumnName <> 'AD_Org_ID'"
				+ " AND c.ColumnName <> 'IsActive'"
				+ " AND c.ColumnName NOT LIKE 'Created%'"
				+ " AND c.ColumnName NOT LIKE 'Updated%' "
				+ "ORDER BY c.ColumnName";
		PreparedStatement pstmt = null;
		try {
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, AD_Table_ID);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String columnName = rs.getString(1);
				boolean isUpdateable = "Y".equals(rs.getString(2));
				boolean isMandatory = "Y".equals(rs.getString(3));
				int displayType = rs.getInt(4);
				int AD_Reference_Value_ID = rs.getInt(5);
				String defaultValue = rs.getString(6);
				//int seqNo = rs.getInt(7);
				int fieldLength = rs.getInt(8);
				String ValueMin = rs.getString(9);
				String ValueMax = rs.getString(10);
				String VFormat = rs.getString(11);
				String Callout = rs.getString(12);
				String Name = rs.getString(13);
				String Description = rs.getString(14);
				String ColumnSQL = rs.getString(15);
				boolean virtualColumn = ColumnSQL != null
						&& ColumnSQL.length() > 0;
				boolean IsEncrypted = "Y".equals(rs.getString(16));
				//
				sb.append(createColumnMethods(mandatory, columnName,
						isUpdateable, isMandatory, displayType,
						AD_Reference_Value_ID, fieldLength, defaultValue,
						ValueMin, ValueMax, VFormat, Callout, Name,
						Description, virtualColumn, IsEncrypted));
			}
			rs.close();
			pstmt.close();
			pstmt = null;
		} catch (Exception e) {
			log.log(Level.SEVERE, sql, e);
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (Exception e) {
			}
			pstmt = null;
		}
		return sb;
	}

	/**
	 * Create set/get methods for column
	 * 
	 * @param mandatory        init call for mandatory columns
	 * @param columnName       column name
	 * @param isUpdateable     updateable
	 * @param isMandatory      mandatory
	 * @param displayType      display type
	 * @param AD_Reference_ID  validation reference
	 * @param fieldLength    int
	 * @param defaultValue   default value
	 * @param ValueMin       String
	 * @param ValueMax       String
	 * @param VFormat        String
	 * @param Callout        String
	 * @param Name           String
	 * @param Description    String
	 * @param virtualColumn  virtual column
	 * @param IsEncrypted    stored encrypted
	 * @return set/get method
	 */
	private String createColumnMethods(StringBuffer mandatory,
			String columnName, boolean isUpdateable, boolean isMandatory,
			int displayType, int AD_Reference_ID, int fieldLength,
			String defaultValue, String ValueMin, String ValueMax,
			String VFormat, String Callout, String Name, String Description,
			boolean virtualColumn, boolean IsEncrypted) {
		Class clazz = DisplayType.getClass(displayType, true);
		if (defaultValue == null)
			defaultValue = "";
		// Handle Posted
		if (columnName.equalsIgnoreCase("Posted")
				|| columnName.equalsIgnoreCase("Processed")
				|| columnName.equalsIgnoreCase("Processing")) {
			clazz = Boolean.class;
			AD_Reference_ID = 0;
		}
		// Record_ID
		else if (columnName.equalsIgnoreCase("Record_ID")) {
			clazz = Integer.class;
			AD_Reference_ID = 0;
		}
		// String Key
		else if (columnName.equalsIgnoreCase("AD_Language")) {
			clazz = String.class;
		}
		// Data Type
		String dataType = clazz.getName();
		dataType = dataType.substring(dataType.lastIndexOf('.') + 1);
		if (dataType.equals("Boolean"))
			dataType = "boolean";
		else if (DisplayType.isID(displayType)) { // Added by @Trifon
			dataType = "int";
		} else if (dataType.equals("Integer"))
			dataType = "int";
		else if (displayType == DisplayType.Binary
				|| displayType == DisplayType.Image)
			dataType = "byte[]";

		StringBuffer sb = new StringBuffer();

		// Create Java Comment
		generateJavaComment("Set", Name, Description, sb);

		// public void setColumn (xxx variable)
		sb.append("\tpublic void set").append(columnName).append(" (")
			.append(dataType).append(" ").append(columnName).append(");");

		// ****** Get Comment ******
		generateJavaComment("Get", Name, Description, sb);

		sb.append("\tpublic ").append(dataType);
		if (clazz.equals(Boolean.class)) {
			sb.append(" is");
			if (columnName.toLowerCase().startsWith("is"))
				sb.append(columnName.substring(2));
			else
				sb.append(columnName);
		} else
			sb.append(" get").append(columnName);
		sb.append("();");
		//
		return sb.toString();
	} // createColumnMethods

	// ****** Set/Get Comment ******
	public void generateJavaComment(String startOfComment, String propertyName,
			String description, StringBuffer result) {
		result.append("\t/** ").append(startOfComment).append(" ").append(
				propertyName);
		if (description != null && description.length() > 0)
			result.append(".\n\t\t").append(description).append(Env.NL);
		result.append("\t  */\n");
	}

	/*
	 * Write to file
	 * 
	 * @param sb string buffer
	 * @param fileName file name
	 */
	private void writeToFile(StringBuffer sb, String fileName) {
		try {
			File out = new File(fileName);
			FileWriter fw = new FileWriter(out);
			for (int i = 0; i < sb.length(); i++) {
				char c = sb.charAt(i);
				// after
				if (c == ';' || c == '}') {
					fw.write(c);
					if (sb.substring(i + 1).startsWith("//"))
						fw.write('\t');
					else
						fw.write(Env.NL);
				}
				// before & after
				else if (c == '{') {
					fw.write(Env.NL);
					fw.write(c);
					fw.write(Env.NL);
				} else
					fw.write(c);
			}
			fw.flush();
			fw.close();
			float size = out.length();
			size /= 1024;
			log.info(out.getAbsolutePath() + " - " + size + " kB");
		} catch (Exception ex) {
			log.log(Level.SEVERE, fileName, ex);
		}
	}

	/**
	 * String representation
	 * 
	 * @return string representation
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer("GenerateModel[").append("]");
		return sb.toString();
	}

	/***************************************************************************
	 * Generate Interface.
	 * 
	 * <pre>
	 *  	Example: java GenerateInterafce.class mydirectory myPackage 'U','A'
	 *  	would generate entity type User and Application classes into mydirectory.
	 *  	Without parameters, the default is used:
	 *  	C:\extend\src\compiere\model\ compiere.model 'U','A'
	 *  	
	 * </pre>
	 * 
	 * @param args
	 *            directory package entityType - directory where to save the
	 *            generated file - package of the classes to be generated -
	 *            entityType to be generated
	 */
	public static void main(String[] args) {
		Adempiere.startupEnvironment(true);
		CLogMgt.setLevel(Level.FINE);
		log.info("Generate Interface   $Revision: 1.0 $");
		log.info("----------------------------------");
		// first parameter
		String directory = "C:\\extend\\src\\compiere\\model\\";
		if (args.length > 0)
			directory = args[0];
		if (directory == null || directory.length() == 0) {
			System.err.println("No Directory");
			System.exit(1);
		}
		log.info("Directory: " + directory);

		// second parameter
		String packageName = "compiere.model";
		if (args.length > 1)
			packageName = args[1];
		if (packageName == null || packageName.length() == 0) {
			System.err.println("No package");
			System.exit(1);
		}
		log.info("Package:   " + packageName);

		// third parameter
		String entityType = "'U','A'"; // User, Application
		if (args.length > 2)
			entityType = args[2];
		if (entityType == null || entityType.length() == 0) {
			System.err.println("No EntityType");
			System.exit(1);
		}
		StringBuffer sql = new StringBuffer("EntityType IN (").append(
				entityType).append(")");
		log.info(sql.toString());
		log.info("----------------------------------");

		// complete sql
		sql.insert(0, "SELECT AD_Table_ID " + "FROM AD_Table "
				+ "WHERE (TableName IN ('RV_WarehousePrice','RV_BPartner')" // special views
				+ " OR IsView='N')" + " AND TableName NOT LIKE '%_Trl' AND ");
		sql.append(" ORDER BY TableName");

		//
		int count = 0;
		PreparedStatement pstmt = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				new GenerateInterfaceTrifon(rs.getInt(1), directory, packageName);
				count++;
			}
			rs.close();
			pstmt.close();
			pstmt = null;
		} catch (Exception e) {
			log.severe("main - " + e);
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (Exception e) {
			}
			pstmt = null;
		}
		log.info("Generated = " + count);

	}

}