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
 * Contributor(s): Carlos Ruiz - globalqss                                    *
 *                 Teo Sarca                                                  *
 *                 Trifon Trifonov                                            *
 *****************************************************************************/
package org.adempiere.util;

import java.io.File;
import java.io.FileWriter;
import java.math.BigDecimal;
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
 *  Generate Model Classes extending PO.
 *  Base class for CMP interface - will be extended to create byte code directly
 *
 *  @author Jorg Janke
 *  @version $Id: GenerateModel.java,v 1.42 2005/05/08 15:16:56 jjanke Exp $
 */
public class ModelClassGenerator
{
	/**
	 * 	Generate PO Class
	 * 	@param AD_Table_ID table id
	 * 	@param directory directory with \ or / at the end.
	 * 	@param packageName package name
	 */
	public ModelClassGenerator (int AD_Table_ID, String directory, String packageName)
	{
		//	create column access methods
		StringBuffer mandatory = new StringBuffer();
		StringBuffer sb = createColumns(AD_Table_ID, mandatory);
		
		// Header
		String tableName = createHeader(AD_Table_ID, sb, mandatory, packageName);
		
		// Save
		writeToFile (sb, directory + tableName + ".java");
	}
	
	/** File Header			*/
	public static final String COPY = 
		 "/******************************************************************************\n"
		+" * Product: Adempiere ERP & CRM Smart Business Solution                       *\n"
		+" * Copyright (C) 1999-2007 ComPiere, Inc. All Rights Reserved.                *\n"
		+" * This program is free software; you can redistribute it and/or modify it    *\n"
		+" * under the terms version 2 of the GNU General Public License as published   *\n"
		+" * by the Free Software Foundation. This program is distributed in the hope   *\n"
		+" * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *\n"
		+" * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *\n"
		+" * See the GNU General Public License for more details.                       *\n"
		+" * You should have received a copy of the GNU General Public License along    *\n"
		+" * with this program; if not, write to the Free Software Foundation, Inc.,    *\n"
		+" * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *\n"
		+" * For the text or an alternative of this public license, you may reach us    *\n"
		+" * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *\n"
		+" * or via info@compiere.org or http://www.compiere.org/license.html           *\n"
		+" *****************************************************************************/\n";
	
	/**	Generated on					*/
//	private Timestamp 		s_run = new Timestamp(System.currentTimeMillis());
	
	/**	Logger			*/
	private static CLogger	log	= CLogger.getCLogger (ModelClassGenerator.class);
	
	/**
	 * 	Add Header info to buffer
	 * 	@param AD_Table_ID table
	 * 	@param sb buffer
	 * 	@param mandatory init call for mandatory columns
	 * 	@param packageName package name
	 * 	@return class name
	 */
	private String createHeader (int AD_Table_ID, StringBuffer sb, StringBuffer mandatory, String packageName)
	{
		String tableName = "";
		int accessLevel = 0;
		String sql = "SELECT TableName, AccessLevel FROM AD_Table WHERE AD_Table_ID=?";
		PreparedStatement pstmt = null;
		try
		{
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, AD_Table_ID);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
			{
				tableName = rs.getString(1);
				accessLevel = rs.getInt(2);
			}
			rs.close();
			pstmt.close();
			pstmt = null;
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		finally
		{
			try
			{
				if (pstmt != null)
					pstmt.close ();
			}
			catch (Exception e)
			{}
			pstmt = null;
		}
		if (tableName == null)
			throw new RuntimeException ("TableName not found for ID=" + AD_Table_ID);
		//
		String accessLevelInfo = accessLevel + " ";
		if (accessLevel >= 4 )
			accessLevelInfo += "- System ";
		if (accessLevel == 2 || accessLevel == 3 || accessLevel == 6 || accessLevel == 7)
			accessLevelInfo += "- Client ";
		if (accessLevel == 1 || accessLevel == 3 || accessLevel == 5 || accessLevel == 7)
			accessLevelInfo += "- Org ";
		
		//
		String keyColumn = tableName + "_ID";
		String className = "X_" + tableName;
		//
		StringBuffer start = new StringBuffer ()
			.append (COPY)
			.append ("/** Generated Model - DO NOT CHANGE */").append(Env.NL)
			.append("package " + packageName + ";").append(Env.NL)
			.append(Env.NL)
		;
		if (!packageName.equals("org.compiere.model"))
			start.append("import org.compiere.model.*;").append(Env.NL);
		
		start.append("import java.util.*;").append(Env.NL)
			 .append("import java.sql.*;").append(Env.NL)
			 .append("import java.math.*;").append(Env.NL)
			 .append("import java.lang.reflect.Constructor;").append(Env.NL)
			 .append("import java.util.logging.Level;").append(Env.NL)
			 .append("import org.compiere.util.*;").append(Env.NL)
			 .append(Env.NL)
			//	Class
			 .append("/** Generated Model for ").append(tableName).append(Env.NL)
			 .append(" *  @author Adempiere (generated) ").append(Env.NL)
			 .append(" *  @version ").append(Adempiere.MAIN_VERSION).append(" - $Id$ */").append(Env.NL)
			 .append("public class ").append(className)
			 	.append(" extends PO")
			 	.append(" implements I_").append(tableName)
			 	.append(", I_Persistent ")
			 	.append(Env.NL)
			 .append("{").append(Env.NL)
			
			 // serialVersionUID
			 .append(Env.NL)
			 .append("\t/**").append(Env.NL)
			 .append("\t *").append(Env.NL)
			 .append("\t */").append(Env.NL)
			 //.append("\tprivate static final long serialVersionUID = ").append(Adempiere.MAIN_VERSION.substring(8)).append(";").append(Env.NL)
			 .append("\tprivate static final long serialVersionUID = 1L;").append(Env.NL)
			
			//	Standard Constructor
			 .append(Env.NL)
			 .append("    /** Standard Constructor */").append(Env.NL)
			 .append("    public ").append(className).append(" (Properties ctx, int ").append(keyColumn).append(", String trxName)").append(Env.NL)
			 .append("    {").append(Env.NL)
			 .append("      super (ctx, ").append(keyColumn).append(", trxName);").append(Env.NL)
			 .append("      /** if (").append(keyColumn).append(" == 0)")
			 .append("        {").append(mandatory).append("} */").append(Env.NL)
			 .append("    }").append(Env.NL)
			//	Constructor End
			
			//	Load Constructor
			 .append(Env.NL)
			 .append("    /** Load Constructor */").append(Env.NL)
			 .append("    public ").append(className).append(" (Properties ctx, ResultSet rs, String trxName)").append(Env.NL)
			 .append("    {").append(Env.NL)
			 .append("      super (ctx, rs, trxName);").append(Env.NL)
			 .append("    }").append(Env.NL)
			//	Load Constructor End
			
			// TableName
//			 .append(Env.NL)
//			 .append("    /** TableName=").append(tableName).append(" */").append(Env.NL)
//			 .append("    public static final String Table_Name = \"").append(tableName).append("\";").append(Env.NL)
			 
			// AD_Table_ID
//			 .append(Env.NL)
//			 .append("    /** AD_Table_ID=").append(AD_Table_ID).append(" */").append(Env.NL)
//			 .append("    public static final int Table_ID = MTable.getTable_ID(Table_Name);").append(Env.NL)
			 
			// KeyNamePair
//			 .append(Env.NL)
//			 .append("    protected static KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);").append(Env.NL)
			 
			// accessLevel
//			 .append(Env.NL)
//			 .append("    protected BigDecimal accessLevel = BigDecimal.valueOf(").append(accessLevel).append(");").append(Env.NL)
			 .append(Env.NL)
			 .append("    /** AccessLevel").append(Env.NL)
			 .append("      * @return ").append(accessLevelInfo).append(Env.NL)
			 .append("      */").append(Env.NL)
			 .append("    protected int get_AccessLevel()").append(Env.NL)
			 .append("    {").append(Env.NL)
			 .append("      return accessLevel.intValue();").append(Env.NL)
			 .append("    }").append(Env.NL)

			 // initPO
			 .append(Env.NL)
			 .append("    /** Load Meta Data */").append(Env.NL)
			 .append("    protected POInfo initPO (Properties ctx)").append(Env.NL)
			 .append("    {").append(Env.NL)
			 .append("      POInfo poi = POInfo.getPOInfo (ctx, Table_ID);").append(Env.NL)
			 .append("      return poi;").append(Env.NL)
			 .append("    }").append(Env.NL)
			// initPO
			
			// toString()
			 .append(Env.NL)
			 .append("    public String toString()").append(Env.NL)
			 .append("    {").append(Env.NL)
			 .append("      StringBuffer sb = new StringBuffer (\"").append(className).append("[\")").append(Env.NL)
			 .append("        .append(get_ID()).append(\"]\");").append(Env.NL)
			 .append("      return sb.toString();").append(Env.NL)
			 .append("    }").append(Env.NL)
		;

		StringBuffer end = new StringBuffer ("}");
		//
		sb.insert(0, start);
		sb.append(end);

		return className;
	}

	/**
	 * 	Create Column access methods
	 * 	@param AD_Table_ID table
	 * 	@param mandatory init call for mandatory columns
	 * 	@return set/get method
	 */
	private StringBuffer createColumns (int AD_Table_ID, StringBuffer mandatory)
	{
		StringBuffer sb = new StringBuffer();
		String sql = "SELECT c.ColumnName, c.IsUpdateable, c.IsMandatory,"		//	1..3
			+ " c.AD_Reference_ID, c.AD_Reference_Value_ID, DefaultValue, SeqNo, "	//	4..7
			+ " c.FieldLength, c.ValueMin, c.ValueMax, c.VFormat, c.Callout, "	//	8..12
			+ " c.Name, c.Description, c.ColumnSQL, c.IsEncrypted, c.IsKey "
			+ "FROM AD_Column c "
			+ "WHERE c.AD_Table_ID=?"
			+ " AND c.ColumnName <> 'AD_Client_ID'"
			+ " AND c.ColumnName <> 'AD_Org_ID'"
			+ " AND c.ColumnName <> 'IsActive'"
			+ " AND c.ColumnName NOT LIKE 'Created%'"
			+ " AND c.ColumnName NOT LIKE 'Updated%' "
			+ "ORDER BY c.ColumnName";
		PreparedStatement pstmt = null;
		try
		{
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, AD_Table_ID);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
			{
				String columnName = rs.getString(1);
				boolean isUpdateable = "Y".equals(rs.getString(2));
				boolean isMandatory = "Y".equals(rs.getString(3));
				int displayType = rs.getInt(4);
				int AD_Reference_Value_ID = rs.getInt(5);
				String defaultValue = rs.getString(6);
				int seqNo = rs.getInt(7);
				int fieldLength = rs.getInt(8);
				String ValueMin = rs.getString(9);
				String ValueMax = rs.getString(10);
				String VFormat = rs.getString(11);
				String Callout = rs.getString(12);
				String Name = rs.getString(13);
				String Description = rs.getString(14);
				String ColumnSQL = rs.getString(15); 
				boolean virtualColumn = ColumnSQL != null && ColumnSQL.length() > 0;
				boolean IsEncrypted = "Y".equals(rs.getString(16));
				boolean IsKey = "Y".equals(rs.getString(17));
				//
				sb.append(
					createColumnMethods (mandatory,
							columnName, isUpdateable, isMandatory, 
							displayType, AD_Reference_Value_ID, fieldLength, 
							defaultValue, ValueMin, ValueMax, VFormat,
							Callout, Name, Description, virtualColumn, IsEncrypted, IsKey)
				);
				//	
				if (seqNo == 1)
					sb.append(createKeyNamePair(columnName, displayType));
			}
			rs.close();
			pstmt.close();
			pstmt = null;
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		finally
		{
			try
			{
				if (pstmt != null)
					pstmt.close ();
			}
			catch (Exception e)
			{}
			pstmt = null;
		}
		return sb;
	}	//	createColumns

	/**
	 *	Create set/get methods for column
	 * 	@param mandatory init call for mandatory columns
	 * 	@param columnName column name
	 * 	@param isUpdateable updateable
	 * 	@param isMandatory mandatory
	 * 	@param displayType display type
	 * 	@param AD_Reference_ID validation reference
	 * 	@param fieldLength int
	 *	@param defaultValue default value
	 * 	@param ValueMin String
	 *	@param ValueMax String
	 *	@param VFormat String
	 *	@param Callout String
	 *	@param Name String
	 *	@param Description String
	 * 	@param virtualColumn virtual column
	 * 	@param IsEncrypted stored encrypted
	@return set/get method
	 */
	private String createColumnMethods (StringBuffer mandatory,
		String columnName, boolean isUpdateable, boolean isMandatory,
		int displayType, int AD_Reference_ID, int fieldLength, 
		String defaultValue, String ValueMin, String ValueMax, String VFormat,
		String Callout, String Name, String Description, 
		boolean virtualColumn, boolean IsEncrypted, boolean IsKey)
	{
		Class<?> clazz = DisplayType.getClass(displayType, true);
		if (defaultValue == null)
			defaultValue = "";
		if (DisplayType.isLOB(displayType))		//	No length check for LOBs
			fieldLength = 0;

		//	Handle Posted
		if (columnName.equalsIgnoreCase("Posted") 
			|| columnName.equalsIgnoreCase("Processed")
			|| columnName.equalsIgnoreCase("Processing"))
		{
			clazz = Boolean.class;
			AD_Reference_ID = 0;
		}
		//	Record_ID
		else if (columnName.equalsIgnoreCase("Record_ID"))
		{
			clazz = Integer.class;
			AD_Reference_ID = 0;
		}
		//	String Key
		else if (columnName.equalsIgnoreCase("AD_Language")
			|| columnName.equalsIgnoreCase("EntityType"))
		{
			clazz = String.class;
		}	
		//	Data Type
		String dataType = clazz.getName();
		dataType = dataType.substring(dataType.lastIndexOf('.')+1);
		if (dataType.equals("Boolean")) {
			dataType = "boolean";
		} else if (dataType.equals("Integer")) {
			dataType = "int";
		} else if (displayType == DisplayType.Binary || displayType == DisplayType.Image) {
			dataType = "byte[]";
		}

		//	Set	********
		String setValue = "\t\tset_Value";
		if (IsEncrypted)
			setValue = "\t\tset_ValueE";
		// Handle isUpdateable
		if (!isUpdateable)
		{
			setValue = "\t\tset_ValueNoCheck";
			if (IsEncrypted)
				setValue = "\t\tset_ValueNoCheckE";
		}

		StringBuffer sb = new StringBuffer();
		
		// TODO - New functionality
		// 1) Must understand which class to reference
		if (DisplayType.isID(displayType) && !IsKey) {
			if (displayType == DisplayType.TableDir) {
				String referenceClassName = "I_"+columnName.substring(0, columnName.length()-3);
				
				sb.append(Env.NL)
					.append("\tpublic "+referenceClassName+" get").append(referenceClassName).append("() throws Exception ").append(Env.NL)
					.append("    {").append(Env.NL)
				// TODO - here we can implement Lazy loading or Cache of class
					.append("        Class<?> clazz = MTable.getClass("+referenceClassName+".Table_Name);").append(Env.NL)
					.append("        ").append(referenceClassName).append(" result = null;").append(Env.NL)
					.append("        try	{").append(Env.NL)
					.append("	        Constructor<?> constructor = null;").append(Env.NL)
//					.append("    	    try	{").append(Env.NL)
					.append("	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});").append(Env.NL)
//					.append("	        } catch (NoSuchMethodException e) {").append(Env.NL)
//					.append("		        log.warning(\"No transaction Constructor for \" + clazz + \" Exception[\" + e.toString() + \"]\");").append(Env.NL)
//					.append("        	}").append(Env.NL)
					// TODO - here we can implement Lazy loading or Cache of record. Like in Hibernate, objects can be loaded on demand or when master object is loaded.
					.append("    	    result = ("+referenceClassName+")constructor.newInstance(new Object[] {getCtx(), new Integer(get"+columnName+"()), get_TrxName()});").append(Env.NL)
					.append("        } catch (Exception e) {").append(Env.NL)
					.append("	        log.log(Level.SEVERE, \"(id) - Table=\" + Table_Name + \",Class=\" + clazz, e);").append(Env.NL)
					.append("	        log.saveError(\"Error\", \"Table=\" + Table_Name + \",Class=\" + clazz);").append(Env.NL)
					.append("           throw e;").append(Env.NL)					
					.append("        }").append(Env.NL)
					.append("        return result;").append(Env.NL)
					.append("    }").append(Env.NL)
				;
			} else {
				// TODO - Handle other types
				//sb.append("\tpublic I_"+columnName+" getI_").append(columnName).append("(){return null; };");
			}
		}

		// Create Java Comment
		generateJavaSetComment(columnName, Name, Description, sb);

		//	public void setColumn (xxx variable)
		sb.append("\tpublic void set").append(columnName).append(" (").append(dataType).append(" ").append(columnName).append(")").append(Env.NL)
			.append("\t{").append(Env.NL)
		;
		//	List Validation
		if (AD_Reference_ID != 0)
		{
			String staticVar = addListValidation (sb, AD_Reference_ID, columnName, !isMandatory);
			sb.insert(0, staticVar);
		}
		//	setValue ("ColumnName", xx);
		if (virtualColumn)
		{
			sb.append ("\t\tthrow new IllegalArgumentException (\"").append(columnName).append(" is virtual column\");");
		}
		//	Integer
		else if (clazz.equals(Integer.class))
		{
			if (columnName.endsWith("_ID"))
			{
				if (isMandatory)	//	check mandatory ID
				{
					int firstOK = 1;	//	Valid ID 0
					if (columnName.equals("AD_Client_ID") || columnName.equals("AD_Org_ID") 
						|| columnName.equals("Record_ID") || columnName.equals("C_DocType_ID")
						|| columnName.equals("Node_ID") || columnName.equals("AD_Role_ID")
						|| columnName.equals("M_AttributeSet_ID") || columnName.equals("M_AttributeSetInstance_ID"))
						firstOK = 0;
					sb.append("\t\tif (").append (columnName).append (" < ").append(firstOK).append(")").append(Env.NL)
						.append("\t\t\t throw new IllegalArgumentException (\"").append(columnName).append(" is mandatory.\");").append(Env.NL);
				}
				else				//	set optional _ID to null if 0
					sb.append("\t\tif (").append (columnName).append (" <= 0) ")
						.append(setValue).append(" (").append ("COLUMNNAME_").append(columnName).append(", null);").append(Env.NL)
						.append(" else ").append(Env.NL);
			}
			sb.append(setValue).append(" (").append ("COLUMNNAME_").append(columnName).append(", Integer.valueOf(").append(columnName).append("));").append(Env.NL);
		}
//		Boolean
		else if (clazz.equals(Boolean.class))
			sb.append(setValue).append(" (").append ("COLUMNNAME_").append(columnName).append(", Boolean.valueOf(").append(columnName).append("));").append(Env.NL);
		else
		{
			if (isMandatory && AD_Reference_ID == 0)	//	does not apply to int/boolean
			{
				sb.append("\t\tif (").append(columnName).append (" == null)").append(Env.NL)
					.append("\t\t\tthrow new IllegalArgumentException (\"")
				  	.append(columnName).append(" is mandatory.\");").append(Env.NL);
			}
			// String length check
			if (clazz.equals(String.class) && fieldLength > 0)
			{
				sb.append ("\t\tif (");
				if (!isMandatory)
					sb.append(columnName).append(" != null && ");
				sb.append(columnName).append(".length() > ").append(fieldLength).append(")").append(Env.NL)
					.append("\t\t{").append(Env.NL)
					.append("\t\t\tlog.warning(\"Length > ").append(fieldLength).append(" - truncated\");").append(Env.NL)
					.append("\t\t\t").append(columnName).append(" = ").append(columnName).append(".substring(0, ").append(fieldLength-1).append(");").append(Env.NL)
					.append("\t\t}").append(Env.NL)
				;
			}
					  
			//
			sb.append(setValue).append(" (").append ("COLUMNNAME_").append (columnName).append (", ")
				.append(columnName).append (");").append(Env.NL);
		}
		sb.append("\t}").append(Env.NL);
		
		//	Mandatory call in constructor
		if (isMandatory)
		{
			mandatory.append("\t\t\tset").append(columnName).append(" (");
			if (clazz.equals(Integer.class))
				mandatory.append("0");
			else if (clazz.equals(Boolean.class))
			{
				if (defaultValue.indexOf('Y') != -1)
					mandatory.append(true);
				else
					mandatory.append("false");
			}
			else if (clazz.equals(BigDecimal.class))
				mandatory.append("Env.ZERO");
			else if (clazz.equals(Timestamp.class))
				mandatory.append("new Timestamp(System.currentTimeMillis())");
			else
				mandatory.append("null");
			mandatory.append(");").append(Env.NL);
			if (defaultValue.length() > 0)
				mandatory.append("// ").append(defaultValue).append(Env.NL);
		}


		//	****** Get Comment ****** 
		generateJavaGetComment(Name, Description, sb);
		
		//	Get	********
		String getValue = "get_Value";
		if (IsEncrypted)
			getValue = "get_ValueE";
		
		sb.append("\tpublic ").append(dataType);
		if (clazz.equals(Boolean.class))
		{
			sb.append(" is");
			if (columnName.toLowerCase().startsWith("is"))
				sb.append(columnName.substring(2));
			else
				sb.append(columnName);
		} else {
			sb.append(" get").append(columnName);
		}
		sb.append(" () ").append(Env.NL)
			.append("\t{").append(Env.NL)
			.append("\t\t");
		if (clazz.equals(Integer.class))
			sb.append("Integer ii = (Integer)").append(getValue).append("(").append ("COLUMNNAME_").append(columnName).append(");").append(Env.NL)
				.append("\t\tif (ii == null)").append(Env.NL)
				.append("\t\t\t return 0;").append(Env.NL)
				.append("\t\treturn ii.intValue();").append(Env.NL);
		else if (clazz.equals(BigDecimal.class))
			sb.append("BigDecimal bd = (BigDecimal)").append(getValue).append("(").append ("COLUMNNAME_").append(columnName).append(");").append(Env.NL)
				.append("\t\tif (bd == null)").append(Env.NL)
				.append("\t\t\t return Env.ZERO;").append(Env.NL)
				.append("\t\treturn bd;").append(Env.NL);
		else if (clazz.equals(Boolean.class))
			sb.append("Object oo = ").append(getValue).append("(").append ("COLUMNNAME_").append(columnName).append(");").append(Env.NL)
				.append("\t\tif (oo != null) ").append(Env.NL)
				.append("\t\t{").append(Env.NL)
				.append("\t\t\t if (oo instanceof Boolean) ").append(Env.NL)
				.append("\t\t\t\t return ((Boolean)oo).booleanValue(); ").append(Env.NL)
				.append("\t\t\treturn \"Y\".equals(oo);").append(Env.NL)
				.append("\t\t}").append(Env.NL)
				.append("\t\treturn false;").append(Env.NL);
		else if (dataType.equals("Object"))
			sb.append("\t\treturn ").append(getValue)
				.append("(").append ("COLUMNNAME_").append(columnName).append(");").append(Env.NL);
		else
			sb.append("return (").append(dataType).append(")").append(getValue)
				.append("(").append ("COLUMNNAME_").append(columnName).append(");").append(Env.NL);
		sb.append("\t}").append(Env.NL);
		//
		return sb.toString();
	}	//	createColumnMethods


	//	****** Set Comment ******
	public void generateJavaSetComment(String columnName, String propertyName, String description, StringBuffer result) {
		
		result.append(Env.NL)
			.append("\t/** Set ").append(propertyName).append(".").append(Env.NL)
			.append("\t\t@param ").append(columnName).append(" ")
		;
		if (description != null && description.length() > 0) {
			result.append(Env.NL)
				.append("\t\t").append(description).append(Env.NL);
		} else {
			result.append(propertyName);
		}
		result.append("\t  */").append(Env.NL);
	}

	//	****** Get Comment ******
	public void generateJavaGetComment(String propertyName, String description, StringBuffer result) {
		
		result.append(Env.NL)
			.append("\t/** Get ").append(propertyName);
		if (description != null && description.length() > 0) {
			result.append(".").append(Env.NL)
				.append("\t\t@return ").append(description).append(Env.NL);
		} else {
			result.append(".\n@return ").append(propertyName);
		}
		result.append("\t  */").append(Env.NL);
	}


	/**
	 * 	Add List Validation
	 * 	@param sb buffer - example:
		if (NextAction.equals("N") || NextAction.equals("F"));
		else throw new IllegalArgumentException ("NextAction Invalid value - Reference_ID=219 - N - F");
	 * 	@param AD_Reference_ID reference
	 * 	@param columnName column
	 * 	@param nullable the validation must allow null values
	 * 	@return static parameter - Example:
		public static final int NEXTACTION_AD_Reference_ID=219;
		public static final String NEXTACTION_None = "N";
		public static final String NEXTACTION_FollowUp = "F";
	 */
	private String addListValidation (StringBuffer sb, int AD_Reference_ID, 
		String columnName, boolean nullable)
	{
		StringBuffer retValue = new StringBuffer();
		retValue.append("\n/** ").append(columnName).append(" AD_Reference_ID=").append(AD_Reference_ID) .append(" */\n")
			.append("public static final int ").append(columnName.toUpperCase())
			.append("_AD_Reference_ID=").append(AD_Reference_ID).append(";");
		//
		boolean found = false;
		StringBuffer values = new StringBuffer("Reference_ID=")
			.append(AD_Reference_ID);
		StringBuffer statement = new StringBuffer();
		if (nullable)
			statement.append("if (").append(columnName).append(" == null");
		//
		String sql = "SELECT Value, Name FROM AD_Ref_List WHERE AD_Reference_ID=?";
		PreparedStatement pstmt = null;
		try
		{
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, AD_Reference_ID);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
			{
				String value = rs.getString(1);
				values.append(" - ").append(value);
				if (statement.length() == 0)
					statement.append("if (").append(columnName)
						.append(".equals(\"").append(value).append("\")");
				else
					statement.append(" || ").append(columnName)
						.append(".equals(\"").append(value).append("\")");
				//
				if (!found)
				{
					found = true;
					if (!nullable)
						sb.append("if (")
							.append (columnName).append (" == null)"
							+ " throw new IllegalArgumentException (\"")
							.append(columnName).append(" is mandatory\");");
				}
				
				
				//	Name (SmallTalkNotation)
				String name = rs.getString(2);
				char[] nameArray = name.toCharArray();
				StringBuffer nameClean = new StringBuffer();
				boolean initCap = true;
				for (int i = 0; i < nameArray.length; i++)
				{
					char c = nameArray[i];
					if (Character.isJavaIdentifierPart(c))
					{
						if (initCap)
							nameClean.append(Character.toUpperCase(c));
						else
							nameClean.append(c);
						initCap = false;
					}
					else
					{
						if (c == '+')
							nameClean.append("Plus");
						else if (c == '-')
							nameClean.append("_");
						else if (c == '>')
						{
							if (name.indexOf('<') == -1)	//	ignore <xx>
								nameClean.append("Gt");
						}
						else if (c == '<')
						{
							if (name.indexOf('>') == -1)	//	ignore <xx>
								nameClean.append("Le");
						}
						else if (c == '!')
							nameClean.append("Not");
						else if (c == '=')
							nameClean.append("Eq");
						else if (c == '~')
							nameClean.append("Like");
						initCap = true;
					}
				}
				retValue.append("/** ").append(name).append(" = ").append(value).append(" */\n");
				retValue.append("public static final String ").append(columnName.toUpperCase())
					.append("_").append(nameClean)
					.append(" = \"").append(value).append("\";");
			}
			rs.close();
			pstmt.close();
			pstmt = null;
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, sql, e);
			found = false;
		}
		finally
		{
			try
			{
				if (pstmt != null)
					pstmt.close ();
			}
			catch (Exception e)
			{}
			pstmt = null;
		}
		statement.append(")"
			+ "; "
			+ "else "
			+ "throw new IllegalArgumentException (\"").append(columnName)
			.append(" Invalid value - \" + ").append(columnName)
			.append(" + \" - ").append(values).append("\");");
		//
		if (found && !columnName.equals("EntityType"))
			sb.append (statement);
		return retValue.toString();
	}	//	addListValidation

	/**
	 * 	Create getKeyNamePair() method with first identifier
	 *	@param columnName name
	 *	 * @param displayType int
	@return method code
	 */
	private StringBuffer createKeyNamePair (String columnName, int displayType)
	{
		String method = "get" + columnName + "()";
		if (displayType != DisplayType.String)
			method = "String.valueOf(" + method + ")";
		
		StringBuffer sb = new StringBuffer(Env.NL)
			.append("    /** Get Record ID/ColumnName").append(Env.NL)
			.append("        @return ID/ColumnName pair").append(Env.NL)
			.append("      */").append(Env.NL)
			.append("    public KeyNamePair getKeyNamePair() ").append(Env.NL)
			.append("    {").append(Env.NL)
			.append("        return new KeyNamePair(get_ID(), ").append(method).append(");").append(Env.NL)
			.append("    }").append(Env.NL)
		;
		return sb;
	}	//	createKeyNamePair


	/**************************************************************************
	 * 	Write to file
	 * 	@param sb string buffer
	 * 	@param fileName file name
	 */
	private void writeToFile (StringBuffer sb, String fileName)
	{
		try
		{
			File out = new File (fileName);
			FileWriter fw = new FileWriter (out);
			for (int i = 0; i < sb.length(); i++)
			{
				char c = sb.charAt(i);
				//	after
				if (c == ';' || c == '}')
				{
					fw.write (c);
					if (sb.substring(i+1).startsWith("//")) {
						//fw.write('\t');
					} else {
						//fw.write(Env.NL);
					}
				}
				//	before & after
				else if (c == '{')
				{
					//fw.write(Env.NL);
					fw.write (c);
					//fw.write(Env.NL);
				}
				else
					fw.write (c);
			}
			fw.flush ();
			fw.close ();
			float size = out.length();
			size /= 1024;
			log.info(out.getAbsolutePath() + " - " + size + " kB");
		}
		catch (Exception ex)
		{
			log.log(Level.SEVERE, fileName, ex);
		}
	}

	/**
	 * 	String representation
	 * 	@return string representation
	 */
	public String toString()
	{
		StringBuffer sb = new StringBuffer ("GenerateModel[").append("]");
		return sb.toString();
	}



	/**************************************************************************
	 * 	Generate PO Model Class.
	 * 	<pre>
	 * 	Example: java GenerateModel.class mydirectory myPackage 'U','A'
	 * 	would generate entity type User and Application classes into mydirectory.
	 * 	Without parameters, the default is used:
	 * 	C:\Compiere\compiere-all\extend\src\compiere\model\ compiere.model 'U','A'
	 * 	</pre>
	 * 	@param args directory package entityType 
	 * 	- directory where to save the generated file
	 * 	- package of the classes to be generated
	 * 	- entityType to be generated
	 */
	public static void main (String[] args)
	{
		Adempiere.startupEnvironment(true);
		CLogMgt.setLevel(Level.FINE);
		log.info("Generate Model   $Revision: 1.42 $");
		log.info("----------------------------------");
		//	first parameter
		String directory = "C:\\Compiere\\compiere-all\\extend\\src\\compiere\\model\\";
		if (args.length > 0)
			directory = args[0];
		if (directory == null || directory.length() == 0)
		{
			System.err.println("No Directory");
			System.exit(1);
		}
		log.info("Directory: " + directory);
		
		//	second parameter
		String packageName = "compiere.model";
		if (args.length > 1)
			packageName = args[1]; 
		if (packageName == null || packageName.length() == 0)
		{
			System.err.println("No package");
			System.exit(1);
		}
		log.info("Package:   " + packageName);
		
		//	third parameter
		String entityType = "'U','A'";	//	User, Application
		if (args.length > 2)
			entityType = args[2]; 
		if (entityType == null || entityType.length() == 0)
		{
			System.err.println("No EntityType");
			System.exit(1);
		}
		StringBuffer sql = new StringBuffer("EntityType IN (")
			.append(entityType).append(")");
		log.info(sql.toString());
		log.info("----------------------------------");
		
		String tableLike = "'%'";	//	All tables
		if (args.length > 3)
			tableLike = args[3];
		log.info("Table Like: " + tableLike);

		//	complete sql
		sql.insert(0, "SELECT AD_Table_ID "
			+ "FROM AD_Table "
			+ "WHERE (TableName IN ('RV_WarehousePrice','RV_BPartner')"	//	special views
			+ " OR IsView='N')"
			+ " AND TableName NOT LIKE '%_Trl' AND ");
		sql.append(" AND TableName LIKE ").append(tableLike);

		sql.append(" ORDER BY TableName");
		
		//
		int count = 0;
		PreparedStatement pstmt = null;
		try
		{
			pstmt = DB.prepareStatement(sql.toString(), null);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
			{
				new ModelClassGenerator(rs.getInt(1), directory, packageName);
				count++;
			}
			rs.close();
			pstmt.close();
			pstmt = null;
		}
		catch (Exception e)
		{
			log.severe("main - " + e);
		}
		finally
		{
			try	{
				if (pstmt != null)
					pstmt.close ();
			} catch (Exception e) { /* ignored */ }
			pstmt = null;
		}
		log.info("Generated = " + count);
	}

}
