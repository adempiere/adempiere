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

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

import org.compiere.print.MPrintFormatItem;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Msg;


/**
 *	System Element Model
 *	
 *  @author Jorg Janke
 *  @version $Id: M_Element.java,v 1.3 2006/07/30 00:58:37 jjanke Exp $
 *  FR: [ 2214883 ] Remove SQL code and Replace for Query - red1, teo_sarca
 *  @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<li> FR [ 9223372036854775807 ] Add default values for Name, Description, Entity Type...
 *		@see https://adempiere.atlassian.net/browse/ADEMPIERE-449
 *		<li> Lookup for search view not show button
 *  	<li> Add default Tables lookup
 *  	@see https://adempiere.atlassian.net/browse/ADEMPIERE-447
 *   	<a href="https://github.com/adempiere/adempiere/issues/998">
 * 		@see FR [ 998 ] Possible NPE on isLookupColumnName method of M_Element class</a>
 * eEvolution @author Victor Perez <victor.perez@e-evolution.com>, Created by e-Evolution on 26/02/16.
 * 		<li>The current isLookupColumnName logic not should be hard code should be solve using column name and name convention #328
 * 		@see http://github.com/adempiere/adempiere/issues/328
 */
public class M_Element extends X_AD_Element
{

    /**
     * 
     */
    private static final long serialVersionUID = -6644398794862560030L;

	/**
	 * 	Get case sensitive Column Name
	 *	@param columnName case insensitive column name
	 *	@return case sensitive column name
	 */
	public static String getColumnName (String columnName)
	{
		return getColumnName(columnName, null);
	}
	
	/**
	 * Get case sensitive Column Name
	 * @param columnName case insensitive column name
	 * @param trxName optional transaction name
	 * @return case sensitive column name
	 */
	public static String getColumnName (String columnName, String trxName)
	{
		if (columnName == null || columnName.length() == 0)
			return columnName;
	 	M_Element element = get(Env.getCtx(), columnName, trxName);
	 	if (element == null)
	 		return columnName;
		return element.getColumnName();

	}	//	getColumnName

	/**
	 * 	Get Element
	 * 	@param ctx context
	 *	@param columnName case insensitive column name
	 *	@return case sensitive column name
	 */
	public static M_Element get (Properties ctx, String columnName) 
	{
		return get(ctx, columnName, null);
	}
	
	/**
	 * 	Get Element
	 * 	@param ctx context
	 *	@param columnName case insensitive column name
	 *  @param trxName optional transaction name
	 *	@return case sensitive column name
	 */
	public static M_Element get (Properties ctx, String columnName, String trxName)
	{
		if (columnName == null || columnName.length() == 0)
			return null;
		//
		// TODO: caching if trxName == null
 	 	final String whereClause = "UPPER(ColumnName)=?";
	 	M_Element retValue = new Query(ctx, I_AD_Element.Table_Name, whereClause, trxName)
			.setParameters(columnName.toUpperCase())
			.firstOnly();
		return retValue;
	}	//	get

	/**
	 * 	Get Element
	 * 	@param ctx context
	 *	@param columnId case insensitive column name
 	 *	@param trxName trx
	 *	@return case sensitive column name
	 */
	public static M_Element getOfColumn (Properties ctx, int columnId, String trxName)
	{
		if (columnId ==0)
			return null;
		final String whereClause = "EXISTS (SELECT 1 FROM AD_Column c "
				+ "WHERE c.AD_Element_ID=AD_Element.AD_Element_ID AND c.AD_Column_ID=?)";
		M_Element retValue = new Query(ctx, Table_Name, whereClause, trxName)
		.setParameters(columnId)
		.firstOnly();
		return retValue;
	}	//	get

	/**
	 * 	Get Element
	 * 	@param ctx context
	 *	@param columnId case sensitive column name
	 *	@return case sensitive column name
	 */
	public static M_Element getOfColumn (Properties ctx, int columnId)
	{
		return getOfColumn(ctx, columnId, null);
	}	//	get
	
	/**************************************************************************
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param AD_Element_ID element
	 *	@param trxName transaction
	 */
	public M_Element (Properties ctx, int AD_Element_ID, String trxName)
	{
		super (ctx, AD_Element_ID, trxName);	
	}	//	M_Element

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName transaction
	 */
	public M_Element (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	M_Element

	/**
	 * 	Minimum Constructor
	 *	@param ctx context
	 *	@param columnName column
	 *	@param EntityType entity type
	 *	@param trxName trx
	 */
	public M_Element (Properties ctx, String columnName, String EntityType,
		String trxName)
	{
		super(ctx, 0, trxName);
		setColumnName (columnName);
		setName (columnName);
		setPrintName (columnName);
		//
		setEntityType (EntityType);	// U
	}	//	M_Element

	
	/* (non-Javadoc)
	 * @see org.compiere.model.PO#beforeSave(boolean)
	 */
	@Override
	protected boolean beforeSave(boolean newRecord) {
		// Column AD_Element.ColumnName should be unique - teo_sarca [ 1613107 ]
		if (newRecord || is_ValueChanged(COLUMNNAME_ColumnName)) {
			String columnName = getColumnName().trim();
			if (getColumnName().length() != columnName.length())
				setColumnName(columnName);
			
			String sql = "select count(*) from AD_Element where UPPER(ColumnName)=?";
			if (!newRecord)
				sql += " AND AD_Element_ID<>" + get_ID(); 
			int no = DB.getSQLValue(null, sql, columnName.toUpperCase());
			if (no > 0) {
				log.saveError("@SaveErrorNotUnique@", Msg.getElement(getCtx(), COLUMNNAME_ColumnName));
				return false;
			}
		}
		
		return true;
	}

	/**
	 * 	After Save
	 *	@param newRecord new
	 *	@param success success
	 *	@return success
	 */
	protected boolean afterSave (boolean newRecord, boolean success)
	{		
		//	Update Columns, Fields, Parameters, Print Info
		if (!newRecord)
		{
			StringBuffer whereClause = new StringBuffer();
			int no = 0;
			
			if (   is_ValueChanged(M_Element.COLUMNNAME_Name)
				|| is_ValueChanged(M_Element.COLUMNNAME_Description)
				|| is_ValueChanged(M_Element.COLUMNNAME_Help)
				|| is_ValueChanged(M_Element.COLUMNNAME_ColumnName)
				) {
				// Use the Column model to trigger the after save and sync of the database.
				whereClause = new StringBuffer(" AD_Element_ID=?");
				List<MColumn> columns = new Query(getCtx(), MColumn.Table_Name, whereClause.toString(), get_TrxName())
						.setParameters(get_ID())
						.list();
				
				for (MColumn column: columns)
				{
					column.setColumnName(getColumnName());
					column.setName(getName());
					column.setDescription(getDescription());
					column.setHelp(getHelp());
					column.saveEx();
					no++;
				}
				log.fine("afterSave - Columns updated #" + no);
				//	Parameter 
				whereClause = new StringBuffer("UPPER(ColumnName)=?")
									.append(" AND IsCentrallyMaintained=? AND AD_Element_ID IS NULL");
				List<MProcessPara> processParas = new Query(getCtx(), MProcessPara.Table_Name, whereClause.toString(), get_TrxName())
						.setParameters(DB.TO_STRING(getColumnName().toUpperCase()), true)
						.list();
				no = 0;
				for (MProcessPara para: processParas)
				{
					para.setColumnName(getColumnName());
					para.setName(getName());
					para.setDescription(getDescription());
					para.setHelp(getHelp());
					para.setAD_Element_ID(get_ID());
					para.saveEx();
					no++;
				}

				whereClause = new StringBuffer("AD_Element_ID=?")
						.append(" AND IsCentrallyMaintained=?");
				processParas = new Query(getCtx(), MProcessPara.Table_Name, whereClause.toString(), get_TrxName())
						.setParameters(get_ID(), true)
						.list();
				for (MProcessPara para: processParas)
				{
					para.setColumnName(getColumnName());
					para.setName(getName());
					para.setDescription(getDescription());
					para.setHelp(getHelp());
					para.saveEx();
					no++;
				}
				log.fine("Parameters updated #" + no);
			}
			
			if (   is_ValueChanged(M_Element.COLUMNNAME_Name)
				|| is_ValueChanged(M_Element.COLUMNNAME_Description)
				|| is_ValueChanged(M_Element.COLUMNNAME_Help)
				) {
				//	Field
				whereClause = new StringBuffer("AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=?")
						.append(") AND IsCentrallyMaintained=?");
				List<MField> fields = new Query(getCtx(), MField.Table_Name, whereClause.toString(), get_TrxName())
						.setParameters(get_ID(), true)
								.list();
				no = 0;
				for (MField field: fields)
				{
					field.setName(getName());
					field.setDescription(getDescription());
					field.setHelp(getHelp());
					field.saveEx();
					no++;
				}
				log.fine("Fields updated #" + no);
			}
			
			if (   is_ValueChanged(M_Element.COLUMNNAME_PrintName)
				|| is_ValueChanged(M_Element.COLUMNNAME_Name)
				) {
				//	Print Info
				whereClause = new StringBuffer("AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=?")
					.append(") AND IsCentrallyMaintained=?");
				List<MPrintFormatItem> items = new Query(getCtx(), MPrintFormatItem.Table_Name, whereClause.toString(), get_TrxName())
						.setParameters(get_ID(), true)
								.list();
				no=0;
				for (MPrintFormatItem item: items)
				{
					item.setPrintName(getPrintName());
					item.setName(getName());
					item.saveEx();
					no++;
				}
				log.fine("PrintFormatItem updated #" + no);
			}			
		}
		return success;
	}	//	afterSave
	
	/**
	 * Validate if column name is reserved
	 * FR [ 9223372036854775807 ]
	 * @return
	 */
	public static boolean isReservedColumnName(String columnName) {
		//	Validate Null
		if(columnName == null)
			return false;
		//	Validation hard code, for support to old implementations
		//	its must be change for dynamic dictionary query
		return columnName.equals("AD_Client_ID")
			//
			|| columnName.startsWith("Created") || columnName.startsWith("Updated")
			|| columnName.equals("EntityType") || columnName.equals("DocumentNo")
			|| columnName.equals("Processed") || columnName.equals("IsSelfService")
			|| columnName.equals("DocAction") || columnName.equals("DocStatus")
			|| columnName.equals("Posted") || columnName.equals("IsReconciled")
			|| columnName.equals("IsApproved") // BF [ 1943682 ]
			|| columnName.equals("IsGenerated") // BF [ 1943682 ]
			|| columnName.startsWith("Ref_")
			//	Order/Invoice
			|| columnName.equals("GrandTotal") || columnName.equals("TotalLines")
			|| columnName.equals("C_CashLine_ID") || columnName.equals("C_Payment_ID")
			|| columnName.equals("IsPaid") || columnName.equals("IsAllocated")
			// Bug [ 1807947 ] 
			|| columnName.equals("C_DocType_ID")
			|| (columnName.equals("Line"));
	}
	
	/**
	 * Verify if is default table info for column name
	 * @param columnName
	 * @return
	 */
	public static boolean isLookupColumnName(String columnName, int referenceKeyId) {
		//	Valid Null
		if(columnName == null
				|| columnName.trim().length() == 0)
			return false;
		if (!columnName.endsWith("_ID") && referenceKeyId <= 0)
			return false;

		String tableName =  columnName.substring(0, columnName.length() -3);
		MTable table = MTable.get(Env.getCtx(), tableName);
		if (table == null)
			return false;
		else if (DisplayType.isLookup(referenceKeyId))
			return true;
		else
			return false;
	}
	
	/**
	 * 	String Representation
	 *	@return info
	 */
	public String toString()
	{
		StringBuffer sb = new StringBuffer ("M_Element[");
		sb.append (get_ID()).append ("-").append (getColumnName()).append ("]");
		return sb.toString ();
	}	//	toString
	
}	//	M_Element
