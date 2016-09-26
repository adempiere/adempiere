/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2003-2011 e-Evolution Consultants. All Rights Reserved.      *
 * Copyright (C) 2003-2011 Victor Pérez Juárez 								  * 
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
 * Contributor(s): Victor Pérez Juárez  (victor.perez@e-evolution.com)		  *
 * Sponsors: e-Evolution Consultants (http://www.e-evolution.com/)            *
 *****************************************************************************/
package org.adempiere.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.model.GridField;
import org.compiere.model.GridFieldVO;
import org.compiere.model.M_Element;
import org.compiere.model.Query;
import org.compiere.util.DB;

/**
 * Class Model for Browse Field
 * 
 * @author victor.perez@e-evoluton.com, www.e-evolution.com
 *  <li>FR [ 3426137 ] Smart Browser
 * 	https://sourceforge.net/tracker/?func=detail&aid=3426137&group_id=176962&atid=879335
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 * 		<li>BR [ 340 ] Smart Browse context is changed from table
 * 		@see https://github.com/adempiere/adempiere/issues/340
 *  	<li><a href="https://github.com/adempiere/adempiere/issues/560">
 * 		FR [ 560 ] SB on ZK have always editable the columns</a>
 */
public class MBrowseField extends X_AD_Browse_Field {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3076943543303710639L;

	private static final String CONTEXT_TABLE_PREFIX = "Table_";
	/**
	 *
	 * @param field
	 * @param windowNo
	 */
	static public GridField createGridFieldVO(MBrowseField field , int windowNo)
	{
		GridFieldVO valueObject = GridFieldVO.createStdField(field.getCtx(), windowNo, 0, 0, 0, false, false, false);
		//	
		String uniqueName =  field.getAD_View_Column().getColumnName();
		valueObject.isProcess = true;
		valueObject.IsDisplayed = field.isDisplayed();
		valueObject.IsReadOnly = field.isReadOnly();
		valueObject.IsUpdateable = true;
		valueObject.WindowNo = windowNo;
		//	BR [ 318 ]
		if(field.getAD_View_Column().getAD_Column_ID() > 0) {
			valueObject.ColumnName = field.getAD_View_Column().getAD_Column().getColumnName();
			valueObject.AD_Column_ID = field.getAD_View_Column().getAD_Column_ID();
			valueObject.AD_Table_ID = field.getAD_View_Column().getAD_Column()
					.getAD_Table_ID();
		} else {
			valueObject.ColumnName = field.getAD_View_Column().getColumnSQL();
		}
		//	Add Alias
		//	BR [ 340 ]
		valueObject.ColumnNameAlias = CONTEXT_TABLE_PREFIX + uniqueName;
		//	
		valueObject.displayType = field.getAD_Reference_ID();
		valueObject.AD_Reference_Value_ID = field.getAD_Reference_Value_ID();
		valueObject.IsMandatory = field.isMandatory();
		valueObject.IsAlwaysUpdateable = false;
		valueObject.IsKey = field.isKey();
		valueObject.DefaultValue = field.getDefaultValue();
		valueObject.DefaultValue2 = field.getDefaultValue2();
		valueObject.InfoFactoryClass = field.getInfoFactoryClass();
		valueObject.FieldLength = field.getFieldLength();
		valueObject.ReadOnlyLogic = field.getReadOnlyLogic();
		valueObject.DisplayLogic =  field.getDisplayLogic();
		valueObject.VFormat = field.getVFormat();
		valueObject.ValueMin = field.getValueMin();
		valueObject.ValueMax = field.getValueMax();
		valueObject.ValidationCode = field.getAD_Val_Rule().getCode();
		valueObject.IsRange = field.isRange();
		try {
			valueObject.Description = field.getDescription();
		}
		catch (IllegalArgumentException e) {
			valueObject.Description = (String) field.get_Value("Description");  
		}
		if (field.getAD_View_Column().getAD_Column_ID() <= 0 && field.isReadOnly())
			valueObject.ColumnSQL = uniqueName;
		valueObject.Help = uniqueName;
		try {
			valueObject.Header = field.getName();
		}
		catch (IllegalArgumentException e) {
			valueObject.Header = (String) field.get_Value("Name");;
		}		
		valueObject.Callout = field.getCallout();
		valueObject.initFinish();

		GridField gridField = new GridField(valueObject);
		if(gridField.isLookup())
			gridField.lookupLoadComplete();

		if (!gridField.isReadOnly()) {
			Object defaultObject = gridField.getDefault();
			gridField.setValue(defaultObject, true);
		}
		return gridField;
	}

	/**
	 * get Browse Field based on View Column
	 * 
	 * @param browse
	 * @param column
	 * @return MBrowseField
	 */
	public static MBrowseField get(MBrowse browse, MViewColumn column) {
		String whereClause = MBrowseField.COLUMNNAME_AD_Browse_ID + "=? AND "
				+ MBrowseField.COLUMNNAME_AD_View_Column_ID + "=?";
		return new Query(column.getCtx(), MBrowseField.Table_Name, whereClause,
				column.get_TrxName())
				.setOnlyActiveRecords(true)
				.setParameters(browse.getAD_Browse_ID(),
						column.getAD_View_Column_ID()).first();
	}
	
	/**
	 * Get ID by Column Name
	 * @param browse
	 * @param columnName
	 * @return
	 */
	public static int getIdByColumnName(MBrowse browse, String columnName) {
		String whereClause = MBrowseField.COLUMNNAME_AD_Browse_ID + "=? AND EXISTS (SELECT 1 FROM AD_View_Column vc WHERE vc.AD_View_Column_ID=AD_Browse_Field.AD_View_Column_ID AND vc.ColumnName=?)";
		return new Query(browse.getCtx(), MBrowseField.Table_Name, whereClause,
				browse.get_TrxName())
				.setParameters(browse.getAD_Browse_ID(),columnName)
				.firstIdOnly();
	}

	/** Element */
	private M_Element m_element = null;
	/** MViewColumn */
	private MViewColumn m_view_column = null;

	/**************************************************************************
	 * Browse Field Constructor
	 * 
	 * @param ctx context
	 * @param AD_SmartBrowseField_ID InOutBound ID
	 * @param trxName transaction name
	 */
	public MBrowseField(Properties ctx, int AD_SmartBrowseField_ID,
			String trxName) {
		super(ctx, AD_SmartBrowseField_ID, trxName);
		if (AD_SmartBrowseField_ID == 0) {
		}
	}

	/**
	 * Browse Field
	 * 
	 * @param ctx context
	 * @param AD_BrowseField_ID    Cahs Flow ID
	 */
	public MBrowseField(Properties ctx, int AD_SmartBrowseField_ID) {
		this(ctx, AD_SmartBrowseField_ID, null);
	}

	/**
	 * Load Constructor
	 * @param ctx context
	 * @param rs result set record
	 * @param trxName transaction
	 */
	public MBrowseField(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	/**
	 * get MViewColumn base on MColumn
	 * @param column
	 * @return
	 */
	public MBrowseField(MBrowse browse, MViewColumn column) {
		super(browse.getCtx(), 0, browse.get_TrxName());
		setAD_Browse_ID(browse.getAD_Browse_ID());
		if (column.get_ID() > 0 )
			setAD_View_Column_ID(column.getAD_View_Column_ID());
		if (column.getAD_Element_ID() > 0 )
			setAD_Element_ID(column.getAD_Element_ID());
		setName(column.getName());
		setDescription(column.getDescription());
		setHelp(column.getHelp());
		setIsActive(true);
		setIsIdentifier(column.isIdentifier());
		setIsRange(false);
		setIsQueryCriteria(false);
		if (column.get_ID() > 0)
			setAD_Reference_ID(column.getAD_Reference_ID());
		if (column.get_ID() > 0)
			setAD_Reference_Value_ID(column.getAD_Column().getAD_Reference_Value_ID());
		setIsKey(false);
		setIsDisplayed(true);
		m_view_column = column;
	}

	/**
	 * Before Save
	 * @param newRecord new
	 * @return true
	 */
	protected boolean beforeSave(boolean newRecord) {
		if (is_ValueChanged(COLUMNNAME_IsKey)) {
			/*
			 * if(getFieldKey() != null) { throw new
			 * AdempiereException("Only can have one field as key"); }
			 */
		}
		//
		return true;
	} // beforeSave

	@Override
	protected boolean afterSave(boolean newRecord, boolean success) {
		if (!success) {
			return false;
		}
		return success;
	}
	
	/**
	 * 	Before Delete
	 *	@return true of it can be deleted
	 */
	protected boolean beforeDelete ()
	{
		DB.executeUpdate("DELETE FROM AD_Browse_Field_Trl WHERE AD_Browse_Field_ID=? ", getAD_Browse_Field_ID(),get_TrxName());
		return true;
	}	//	beforeDelete

	/**
	 * get Element
	 * 
	 * @return M_Element
	 */
	public M_Element getElement() {
		if (m_element == null) {
			m_element = new M_Element(getCtx(), getAD_Element_ID(),
					get_TrxName());
		}
		return m_element;
	}

	/**
	 * get ViewColumn
	 */
	public MViewColumn getAD_View_Column() {
		if (m_view_column == null) {
			m_view_column = new MViewColumn(getCtx(), getAD_View_Column_ID(),
					get_TrxName());
		}
		return m_view_column;
	}

	/**
	 * String representation
	 * 
	 * @return info
	 */
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer("MSmartBrowseField")
				.append(get_ID()).append("-").append(getName()).append("]");
		return sb.toString();
	} // toString
	
	@Override
	public String getName() {
		return get_Translation(COLUMNNAME_Name);
	}
	
	@Override
	public String getDescription() {
		return get_Translation(COLUMNNAME_Description);
	}
	
	@Override
	public String getHelp() {
		return get_Translation(COLUMNNAME_Help);
	}

}