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
 * Copyright (C) 2003-2013 E.R.P. Consultores y Asociados.                    *
 * All Rights Reserved.                                                       *
 * Contributor(s): Dixon Martinez www.erpconsultoresyasociados.com            *
 *****************************************************************************/
package org.spinsuite.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.model.MSequence;
import org.compiere.model.M_Element;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;

/**
 * @author Dixon Martinez
 * @author <a href="mailto:yamelsenih@gmail.com">Yamel Senih</a>
 * <li>Add method for generate Script Create Table
 * <li>Add method for get Columns
 */
public class MSPSTable extends X_SPS_Table {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4584267653241186524L;

	/**
	 * *** Class Constructor ***
	 * @author Dixon Martinez 08/02/2013, 17:22:40
	 * @param ctx
	 * @param SPS_Table_ID
	 * @param trxName
	 */
	public MSPSTable(Properties ctx, int SPS_Table_ID, String trxName) {
		super(ctx, SPS_Table_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	/**
	 * *** Class Constructor ***
	 * @author Dixon Martinez 08/02/2013, 17:22:40
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MSPSTable(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 	Get SQL Create
	 *	@return create table DDL
	 */
	public String getSQLCreate()
	{
		StringBuffer sb = new StringBuffer("CREATE TABLE ")
			.append(getTableName()).append(" (");
		//
		boolean hasPK = false;
		boolean hasParents = false;
		StringBuffer constraints = new StringBuffer();
		MSPSColumn[] m_columns = getColumns();
		for (int i = 0; i < m_columns.length; i++)
		{
			MSPSColumn column = m_columns[i];
			String colSQL = column.getSQLDDL();
			if ( colSQL != null )
			{
				if (i > 0)
					sb.append(", ");
					sb.append(colSQL);
			}
			else // virtual column
				continue;
			//
			if (column.isKey())
				hasPK = true;
			if (column.isParent())
				hasParents = true;
			String constraint = column.getConstraint(getTableName());
			if (constraint != null && constraint.length() > 0)
				constraints.append(", ").append(constraint);
		}
		//	Multi Column PK 
		if (!hasPK && hasParents)
		{
			StringBuffer cols = new StringBuffer();
			for (int i = 0; i < m_columns.length; i++)
			{
				MSPSColumn column = m_columns[i];
				if (!column.isParent())
					continue;
				if (cols.length() > 0)
					cols.append(", ");
				cols.append(column.getColumnName());
			}
			sb.append(", CONSTRAINT ")
				.append(getTableName()).append("_Key PRIMARY KEY (")
				.append(cols).append(")");
		}

		sb.append(constraints)
			.append(")");
		return sb.toString();
	}	//	getSQLCreate
	
	
	/**
	 * 	Get Columns
	 *	@return array of columns
	 */
	public MSPSColumn[] getColumns ()
	{
		MSPSColumn[] m_columns;
		
		String sql = "SELECT * FROM SPS_Column WHERE SPS_Table_ID=? ORDER BY ColumnName";
		ArrayList<MSPSColumn> list = new ArrayList<MSPSColumn>();
		PreparedStatement pstmt = null;
		try
		{
			pstmt = DB.prepareStatement (sql, get_TrxName());
			pstmt.setInt (1, getSPS_Table_ID());
			ResultSet rs = pstmt.executeQuery ();
			while (rs.next ())
				list.add (new MSPSColumn (getCtx(), rs, get_TrxName()));
			rs.close ();
			pstmt.close ();
			pstmt = null;
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, sql, e);
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
		//
		m_columns = new MSPSColumn[list.size ()];
		list.toArray (m_columns);
		return m_columns;
	}	//	getColumns

	/**
	 * 	Before Save
	 *	@param newRecord new
	 *	@return true
	 */
	protected boolean beforeSave (boolean newRecord)
	{
		if (isView() && isDeleteable())
			setIsDeleteable(false);
		//
		return true;
	}	//	beforeSave
	
	/**
	 * 	After Save
	 *	@param newRecord new
	 *	@param success success
	 *	@return success
	 */
	protected boolean afterSave (boolean newRecord, boolean success)
	{
		//	Sync Table ID
		if (newRecord) {
			
			//	Create columns if not exists table reference 
			if(getAD_Table_ID() == 0)
				createMandatoryColumns();
			
			MSequence seq = MSequence.get(getCtx(), getTableName(), get_TrxName());
			if (seq == null || seq.get_ID() == 0)
				MSequence.createTableSequence(getCtx(), getTableName(), get_TrxName());
		}
		else
		{
			MSequence seq = MSequence.get(getCtx(), getTableName(), get_TrxName());
			if (seq == null || seq.get_ID() == 0)
				MSequence.createTableSequence(getCtx(), getTableName(), get_TrxName());
			else if (!seq.getName().equals(getTableName()))
			{
				seq.setName(getTableName());
				seq.save();
			}
		}	
		
		return success;
	}	//	afterSave
	
	/*
	 * Create Mandatory Fields
	 */
	public void createMandatoryColumns(){		
		
		MSPSColumn m_SPS_Column = null;
		//MColumn column = null;
		//M_Element.get(getCtx(),COLUMNNAME_AD_Client_ID);
		
		m_SPS_Column = new MSPSColumn(this, COLUMNNAME_AD_Client_ID	, 22 , DisplayType.TableDir , "@#AD_Client_ID@");
		m_SPS_Column.setIsUpdateable(false);
		m_SPS_Column.setAD_Val_Rule_ID(129);
		m_SPS_Column.saveEx();
		m_SPS_Column = new MSPSColumn(this, COLUMNNAME_AD_Org_ID	, 22 , DisplayType.TableDir , "@#AD_Org_ID@");
		m_SPS_Column.setIsUpdateable(false);
		m_SPS_Column.setAD_Val_Rule_ID(104);
		m_SPS_Column.saveEx();
		m_SPS_Column = new MSPSColumn(this, COLUMNNAME_IsActive	, 1 , DisplayType.YesNo , "Y");
		m_SPS_Column.setIsUpdateable(false);
		m_SPS_Column.saveEx();
		m_SPS_Column = new MSPSColumn(this, COLUMNNAME_Created	, 7 , DisplayType.DateTime , "");
		m_SPS_Column.saveEx();		
		m_SPS_Column = new MSPSColumn(this, COLUMNNAME_Updated	, 7 , DisplayType.DateTime , "");
		m_SPS_Column.saveEx();
		m_SPS_Column = new MSPSColumn(this, COLUMNNAME_CreatedBy	, 22 , DisplayType.TableDir, "");
		m_SPS_Column.setAD_Reference_Value_ID(110);
		m_SPS_Column.saveEx();
		m_SPS_Column = new MSPSColumn(this, COLUMNNAME_UpdatedBy	, 22 , DisplayType.TableDir, "");
		m_SPS_Column.setAD_Reference_Value_ID(110);
		m_SPS_Column.saveEx();
		if(!isView())
		{	
			if(getTableName().endsWith("_Trl") || getTableName().endsWith("_Access"))
				return;
			
			M_Element element = M_Element.get(getCtx(), getTableName()+"_ID", get_TrxName());
			if(element == null) {
				element = new M_Element(getCtx(), 0 , get_TrxName());
				element.setColumnName(getTableName()+"_ID");
				element.setName(getName() + " ID");
				element.setPrintName(getName() + " ID");
				element.setEntityType(getEntityType());
				element.saveEx();
			}
			
			m_SPS_Column = new MSPSColumn(this, element.getColumnName(), 22 , DisplayType.ID, "");
			m_SPS_Column.setAD_Element_ID(element.get_ID());
			m_SPS_Column.setIsKey(true);
			m_SPS_Column.setIsMandatory(true);
			m_SPS_Column.saveEx();
		}	
	}
}
