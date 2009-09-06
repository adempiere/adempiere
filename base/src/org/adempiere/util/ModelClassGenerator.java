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
 *                 Teo Sarca - www.arhipac.ro                                 *
 *                 Trifon Trifonov                                            *
 *****************************************************************************/
package org.adempiere.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.TreeSet;
import java.util.logging.Level;

import org.adempiere.exceptions.DBException;
import org.compiere.Adempiere;
import org.compiere.util.CLogMgt;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;

/**
 *  Generate Model Classes extending PO.
 *  Base class for CMP interface - will be extended to create byte code directly
 *
 *  @author Jorg Janke
 *  @version $Id: GenerateModel.java,v 1.42 2005/05/08 15:16:56 jjanke Exp $
 *
 * @author Teo Sarca, SC ARHIPAC SERVICE SRL
 * 				<li>BF [ 1781629 ] Don't use Env.NL in model class/interface generators
 * 				<li>FR [ 1781630 ] Generated class/interfaces have a lot of unused imports
 * 				<li>BF [ 1781632 ] Generated class/interfaces should be UTF-8
 * 				<li>FR [ xxxxxxx ] better formating of generated source
 * 				<li>FR [ 1787876 ] ModelClassGenerator: list constants should be ordered
 * 				<li>FR [ 1803309 ] Model generator: generate get method for Search cols
 * 				<li>FR [ 1990848 ] Generated Models: remove hardcoded field length
 * 				<li>FR [ 2343096 ] Model Generator: Improve Reference Class Detection
 * 				<li>BF [ 2780468 ] ModelClassGenerator: not generating methods for Created*
 * 				<li>--
 * 				<li>FR [ 2848449 ] ModelClassGenerator: Implement model getters
 *					https://sourceforge.net/tracker/?func=detail&atid=879335&aid=2848449&group_id=176962
 * @author Victor Perez, e-Evolution
 * 				<li>FR [ 1785001 ] Using ModelPackage of EntityType to Generate Model Class
 */
public class ModelClassGenerator
{
	/**
	 * 	Generate PO Class
	 * 	@param AD_Table_ID table id
	 * 	@param directory directory
	 * 	@param packageName package name
	 */
	public ModelClassGenerator (int AD_Table_ID, String directory, String packageName)
	{
		this.packageName = packageName;

		//	create column access methods
		StringBuffer mandatory = new StringBuffer();
		StringBuffer sb = createColumns(AD_Table_ID, mandatory);

		// Header
		String tableName = createHeader(AD_Table_ID, sb, mandatory, packageName);

		// Save
		if ( ! directory.endsWith(File.separator) )
			directory += File.separator;

		writeToFile (sb, directory + tableName + ".java");
	}

	public static final String NL = "\n";

	/**	Logger			*/
	private static CLogger	log	= CLogger.getCLogger (ModelClassGenerator.class);

	/** Package Name */
	private String packageName = "";


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
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, AD_Table_ID);
			rs = pstmt.executeQuery();
			if (rs.next())
			{
				tableName = rs.getString(1);
				accessLevel = rs.getInt(2);
			}
		}
		catch (SQLException e)
		{
			throw new DBException(e, sql);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
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
			.append (ModelInterfaceGenerator.COPY)
			.append ("/** Generated Model - DO NOT CHANGE */").append(NL)
			.append("package " + packageName + ";").append(NL)
			.append(NL)
		;

		addImportClass(java.util.Properties.class);
		addImportClass(java.sql.ResultSet.class);
		if (!packageName.equals("org.compiere.model"))
			addImportClass("org.compiere.model.*");
		createImports(start);
		//	Class
		start.append("/** Generated Model for ").append(tableName).append(NL)
			 .append(" *  @author Adempiere (generated) ").append(NL)
			 .append(" *  @version ").append(Adempiere.MAIN_VERSION).append(" - $Id$ */").append(NL)
			 .append("public class ").append(className)
			 	.append(" extends PO")
			 	.append(" implements I_").append(tableName)
			 	.append(", I_Persistent ")
			 	.append(NL)
			 .append("{").append(NL)

			 // serialVersionUID
			 .append(NL)
			 .append("\t/**").append(NL)
			 .append("\t *").append(NL)
			 .append("\t */").append(NL)
			 .append("\tprivate static final long serialVersionUID = ")
			 	.append(Adempiere.DB_VERSION.substring(0,4))
			 	.append(Adempiere.DB_VERSION.substring(5,7))
			 	.append(Adempiere.DB_VERSION.substring(8))
			 	.append("L;").append(NL)
			 //.append("\tprivate static final long serialVersionUID = 1L;").append(NL)

			//	Standard Constructor
			 .append(NL)
			 .append("    /** Standard Constructor */").append(NL)
			 .append("    public ").append(className).append(" (Properties ctx, int ").append(keyColumn).append(", String trxName)").append(NL)
			 .append("    {").append(NL)
			 .append("      super (ctx, ").append(keyColumn).append(", trxName);").append(NL)
			 .append("      /** if (").append(keyColumn).append(" == 0)").append(NL)
			 .append("        {").append(NL)
			 .append(mandatory) //.append(NL)
			 .append("        } */").append(NL)
			 .append("    }").append(NL)
			//	Constructor End

			//	Load Constructor
			 .append(NL)
			 .append("    /** Load Constructor */").append(NL)
			 .append("    public ").append(className).append(" (Properties ctx, ResultSet rs, String trxName)").append(NL)
			 .append("    {").append(NL)
			 .append("      super (ctx, rs, trxName);").append(NL)
			 .append("    }").append(NL)
			//	Load Constructor End

			// TableName
//			 .append(NL)
//			 .append("    /** TableName=").append(tableName).append(" */").append(NL)
//			 .append("    public static final String Table_Name = \"").append(tableName).append("\";").append(NL)

			// AD_Table_ID
//			 .append(NL)
//			 .append("    /** AD_Table_ID=").append(AD_Table_ID).append(" */").append(NL)
//			 .append("    public static final int Table_ID = MTable.getTable_ID(Table_Name);").append(NL)

			// KeyNamePair
//			 .append(NL)
//			 .append("    protected static KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);").append(NL)

			// accessLevel
//			 .append(NL)
//			 .append("    protected BigDecimal accessLevel = BigDecimal.valueOf(").append(accessLevel).append(");").append(NL)
			 .append(NL)
			 .append("    /** AccessLevel").append(NL)
			 .append("      * @return ").append(accessLevelInfo).append(NL)
			 .append("      */").append(NL)
			 .append("    protected int get_AccessLevel()").append(NL)
			 .append("    {").append(NL)
			 .append("      return accessLevel.intValue();").append(NL)
			 .append("    }").append(NL)

			 // initPO
			 .append(NL)
			 .append("    /** Load Meta Data */").append(NL)
			 .append("    protected POInfo initPO (Properties ctx)").append(NL)
			 .append("    {").append(NL)
			 .append("      POInfo poi = POInfo.getPOInfo (ctx, Table_ID, get_TrxName());").append(NL)
			 .append("      return poi;").append(NL)
			 .append("    }").append(NL)
			// initPO

			// toString()
			 .append(NL)
			 .append("    public String toString()").append(NL)
			 .append("    {").append(NL)
			 .append("      StringBuffer sb = new StringBuffer (\"").append(className).append("[\")").append(NL)
			 .append("        .append(get_ID()).append(\"]\");").append(NL)
			 .append("      return sb.toString();").append(NL)
			 .append("    }").append(NL)
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
			+ " c.Name, c.Description, c.ColumnSQL, c.IsEncrypted, c.IsKey, c.IsIdentifier "  // 13..18
			+ "FROM AD_Column c "
			+ "WHERE c.AD_Table_ID=?"
			+ " AND c.ColumnName NOT IN ('AD_Client_ID', 'AD_Org_ID', 'IsActive', 'Created', 'CreatedBy', 'Updated', 'UpdatedBy')"
			+ " AND c.IsActive='Y'"
			+ " ORDER BY c.ColumnName";
		boolean isKeyNamePairCreated = false; // true if the method "getKeyNamePair" is already generated
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, AD_Table_ID);
			rs = pstmt.executeQuery();
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
				boolean IsIdentifier = "Y".equals(rs.getString(18));
				//
				sb.append(
					createColumnMethods (mandatory,
							columnName, isUpdateable, isMandatory,
							displayType, AD_Reference_Value_ID, fieldLength,
							defaultValue, ValueMin, ValueMax, VFormat,
							Callout, Name, Description, virtualColumn, IsEncrypted, IsKey,
							AD_Table_ID)
				);
				//
				if (seqNo == 1 && IsIdentifier) {
					if (!isKeyNamePairCreated) {
						sb.append(createKeyNamePair(columnName, displayType));
						isKeyNamePairCreated = true;
					}
					else {
						throw new RuntimeException("More than one primary identifier found "
								+ " (AD_Table_ID=" + AD_Table_ID + ", ColumnName=" + columnName + ")");
					}
				}
			}
		}
		catch (SQLException e)
		{
			throw new DBException(e, sql);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
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
		boolean virtualColumn, boolean IsEncrypted, boolean IsKey,
		int AD_Table_ID)
	{
		Class<?> clazz = ModelInterfaceGenerator.getClass(columnName, displayType, AD_Reference_ID);
		String dataType = ModelInterfaceGenerator.getDataTypeName(clazz, displayType);
		if (defaultValue == null)
			defaultValue = "";
		if (DisplayType.isLOB(displayType))		//	No length check for LOBs
			fieldLength = 0;

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
		if (DisplayType.isID(displayType) && !IsKey)
		{
			String fieldName = ModelInterfaceGenerator.getFieldName(columnName);
			String referenceClassName = ModelInterfaceGenerator.getReferenceClassName(AD_Table_ID, columnName, displayType, AD_Reference_ID);
			//
			if (fieldName != null && referenceClassName != null)
			{
				sb.append(NL)
				.append("\tpublic "+referenceClassName+" get").append(fieldName).append("() throws RuntimeException").append(NL)
				.append("    {").append(NL)
				.append("\t\treturn ("+referenceClassName+")MTable.get(getCtx(), "+referenceClassName+".Table_Name)").append(NL)
				.append("\t\t\t.getPO(get"+columnName+"(), get_TrxName());")
				/**/
				.append("\t}").append(NL)
				;
				// Add imports:
				addImportClass(clazz);
			}
		}

		// Create Java Comment
		generateJavaSetComment(columnName, Name, Description, sb);

		//	public void setColumn (xxx variable)
		sb.append("\tpublic void set").append(columnName).append(" (").append(dataType).append(" ").append(columnName).append(")").append(NL)
			.append("\t{").append(NL)
		;
		//	List Validation
		if (AD_Reference_ID != 0 && String.class == clazz)
		{
			String staticVar = addListValidation (sb, AD_Reference_ID, columnName);
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
				int firstOK = 1;
				//	check special column
				if (columnName.equals("AD_Client_ID") || columnName.equals("AD_Org_ID")
					|| columnName.equals("Record_ID") || columnName.equals("C_DocType_ID")
					|| columnName.equals("Node_ID") || columnName.equals("AD_Role_ID")
					|| columnName.equals("M_AttributeSet_ID") || columnName.equals("M_AttributeSetInstance_ID"))
					firstOK = 0;
				//	set _ID to null if < 0 for special column or < 1 for others
				sb.append("\t\tif (").append (columnName).append (" < ").append(firstOK).append(") ").append(NL)
					.append("\t").append(setValue).append(" (").append ("COLUMNNAME_").append(columnName).append(", null);").append(NL)
					.append("\t\telse ").append(NL).append("\t");
			}
			sb.append(setValue).append(" (").append ("COLUMNNAME_").append(columnName).append(", Integer.valueOf(").append(columnName).append("));").append(NL);
		}
		//		Boolean
		else if (clazz.equals(Boolean.class))
			sb.append(setValue).append(" (").append ("COLUMNNAME_").append(columnName).append(", Boolean.valueOf(").append(columnName).append("));").append(NL);
		else
		{
			sb.append(setValue).append(" (").append ("COLUMNNAME_").append (columnName).append (", ")
				.append(columnName).append (");").append(NL);
		}
		sb.append("\t}").append(NL);

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
				mandatory.append("new Timestamp( System.currentTimeMillis() )");
			else
				mandatory.append("null");
			mandatory.append(");").append(NL);
			if (defaultValue.length() > 0)
				mandatory.append("// ").append(defaultValue).append(NL);
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
		sb.append(" () ").append(NL)
			.append("\t{").append(NL)
			.append("\t\t");
		if (clazz.equals(Integer.class)) {
			sb.append("Integer ii = (Integer)").append(getValue).append("(").append ("COLUMNNAME_").append(columnName).append(");").append(NL)
				.append("\t\tif (ii == null)").append(NL)
				.append("\t\t\t return 0;").append(NL)
				.append("\t\treturn ii.intValue();").append(NL);
		}
		else if (clazz.equals(BigDecimal.class)) {
			sb.append("BigDecimal bd = (BigDecimal)").append(getValue).append("(").append ("COLUMNNAME_").append(columnName).append(");").append(NL)
				.append("\t\tif (bd == null)").append(NL)
				.append("\t\t\t return Env.ZERO;").append(NL)
				.append("\t\treturn bd;").append(NL);
			addImportClass(java.math.BigDecimal.class);
			addImportClass(org.compiere.util.Env.class);
		}
		else if (clazz.equals(Boolean.class)) {
			sb.append("Object oo = ").append(getValue).append("(").append ("COLUMNNAME_").append(columnName).append(");").append(NL)
				.append("\t\tif (oo != null) ").append(NL)
				.append("\t\t{").append(NL)
				.append("\t\t\t if (oo instanceof Boolean) ").append(NL)
				.append("\t\t\t\t return ((Boolean)oo).booleanValue(); ").append(NL)
				.append("\t\t\treturn \"Y\".equals(oo);").append(NL)
				.append("\t\t}").append(NL)
				.append("\t\treturn false;").append(NL);
		}
		else if (dataType.equals("Object")) {
			sb.append("\t\treturn ").append(getValue)
				.append("(").append ("COLUMNNAME_").append(columnName).append(");").append(NL);
		}
		else {
			sb.append("return (").append(dataType).append(")").append(getValue)
				.append("(").append ("COLUMNNAME_").append(columnName).append(");").append(NL);
			addImportClass(clazz);
		}
		sb.append("\t}").append(NL);
		//
		return sb.toString();
	}	//	createColumnMethods


	//	****** Set Comment ******
	public void generateJavaSetComment(String columnName, String propertyName, String description, StringBuffer result) {

		result.append(NL)
			.append("\t/** Set ").append(propertyName).append(".").append(NL)
			.append("\t\t@param ").append(columnName).append(" ")
		;
		if (description != null && description.length() > 0) {
			result.append(NL)
				.append("\t\t").append(description).append(NL);
		} else {
			result.append(propertyName);
		}
		result.append("\t  */").append(NL);
	}

	//	****** Get Comment ******
	public void generateJavaGetComment(String propertyName, String description, StringBuffer result) {

		result.append(NL)
			.append("\t/** Get ").append(propertyName);
		if (description != null && description.length() > 0) {
			result.append(".").append(NL)
				.append("\t\t@return ").append(description).append(NL);
		} else {
			result.append(".\n\t\t@return ").append(propertyName);
		}
		result.append("\t  */").append(NL);
	}


	/**
	 * 	Add List Validation
	 * 	@param sb buffer - example:
		if (NextAction.equals("N") || NextAction.equals("F"));
		else throw new IllegalArgumentException ("NextAction Invalid value - Reference_ID=219 - N - F");
	 * 	@param AD_Reference_ID reference
	 * 	@param columnName column
	 * 	@return static parameter - Example:
		public static final int NEXTACTION_AD_Reference_ID=219;
		public static final String NEXTACTION_None = "N";
		public static final String NEXTACTION_FollowUp = "F";
	 */
	private String addListValidation (StringBuffer sb, int AD_Reference_ID,
		String columnName)
	{
		StringBuffer retValue = new StringBuffer();
		retValue.append("\n\t/** ").append(columnName).append(" AD_Reference_ID=").append(AD_Reference_ID) .append(" */")
			.append("\n\tpublic static final int ").append(columnName.toUpperCase())
			.append("_AD_Reference_ID=").append(AD_Reference_ID).append(";");
		//
		boolean found = false;
		StringBuffer values = new StringBuffer("Reference_ID=")
			.append(AD_Reference_ID);
		StringBuffer statement = new StringBuffer();
		//
		String sql = "SELECT Value, Name FROM AD_Ref_List WHERE AD_Reference_ID=? ORDER BY AD_Ref_List_ID";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, AD_Reference_ID);
			rs = pstmt.executeQuery();
			while (rs.next())
			{
				String value = rs.getString(1);
				values.append(" - ").append(value);
				if (statement.length() == 0)
					statement.append("\n\t\tif (").append(columnName)
						.append(".equals(\"").append(value).append("\")");
				else
					statement.append(" || ").append(columnName)
						.append(".equals(\"").append(value).append("\")");
				//
				if (!found)
				{
					found = true;
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
				retValue.append("\n\t/** ").append(name).append(" = ").append(value).append(" */");
				retValue.append("\n\tpublic static final String ").append(columnName.toUpperCase())
					.append("_").append(nameClean)
					.append(" = \"").append(value).append("\";");
			}
		}
		catch (SQLException e)
		{
			throw new DBException(e, sql);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		statement.append(")"
			+ "; "
			+ "else "
			+ "throw new IllegalArgumentException (\"").append(columnName)
			.append(" Invalid value - \" + ").append(columnName)
			.append(" + \" - ").append(values).append("\");");
		// [1762461] - Remove hardcoded list items checking in generated models
		// if (found && !columnName.equals("EntityType"))
		//	sb.append (statement);
		sb.append("\n");
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

		StringBuffer sb = new StringBuffer(NL)
			.append("    /** Get Record ID/ColumnName").append(NL)
			.append("        @return ID/ColumnName pair").append(NL)
			.append("      */").append(NL)
			.append("    public KeyNamePair getKeyNamePair() ").append(NL)
			.append("    {").append(NL)
			.append("        return new KeyNamePair(get_ID(), ").append(method).append(");").append(NL)
			.append("    }").append(NL)
		;
		addImportClass(org.compiere.util.KeyNamePair.class);
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
			Writer fw = new OutputStreamWriter(new FileOutputStream(out, false), "UTF-8");
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
						//fw.write(NL);
					}
				}
				//	before & after
				else if (c == '{')
				{
					//fw.write(NL);
					fw.write (c);
					//fw.write(NL);
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
			throw new RuntimeException(ex);
		}
	}

	/** Import classes */
	private Collection<String> s_importClasses = new TreeSet<String>();
	/**
	 * Add class name to class import list
	 * @param className
	 */
	private void addImportClass(String className) {
		if (className == null
				|| (className.startsWith("java.lang.") && !className.startsWith("java.lang.reflect."))
				|| className.startsWith(packageName+"."))
			return;
		for(String name : s_importClasses) {
			if (className.equals(name))
				return;
		}
		s_importClasses.add(className);
	}
	/**
	 * Add class to class import list
	 * @param cl
	 */
	private void addImportClass(Class<?> cl) {
		if (cl.isArray()) {
			cl = cl.getComponentType();
		}
		if (cl.isPrimitive())
			return;
		addImportClass(cl.getCanonicalName());
	}
	/**
	 * Generate java imports
	 * @param sb
	 */
	private void createImports(StringBuffer sb) {
		for (String name : s_importClasses) {
			sb.append("import ").append(name).append(";").append(NL);
		}
		sb.append(NL);
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
		String directory = "C:\\Adempiere\\adempiere-all\\extend\\src\\compiere\\model\\";
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
		//tableLike = "'AD_OrgInfo', 'AD_Role', 'C_CashLine', 'C_Currency', 'C_Invoice', 'C_Order', 'C_Payment', 'M_InventoryLine', 'M_PriceList', 'M_Product', 'U_POSTerminal'";
		if (args.length > 3)
			tableLike = args[3];
		log.info("Table Like: " + tableLike);

		//	complete sql
		sql.insert(0, "SELECT AD_Table_ID "
			+ "FROM AD_Table "
			+ "WHERE (TableName IN ('RV_WarehousePrice','RV_BPartner')"	//	special views
			+ " OR IsView='N')"
			+ " AND IsActive = 'Y' AND TableName NOT LIKE '%_Trl' AND ");
		sql.append(" AND TableName LIKE ").append(tableLike);

		sql.append(" ORDER BY TableName");

		//
		int count = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql.toString(), null);
			rs = pstmt.executeQuery();
			while (rs.next())
			{
				new ModelClassGenerator(rs.getInt(1), directory, packageName);
				count++;
			}
		}
		catch (SQLException e)
		{
			throw new DBException(e, sql.toString());
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		log.info("Generated = " + count);
	}

}
