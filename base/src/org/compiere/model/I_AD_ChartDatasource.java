/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2017 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * or (at your option) any later version.										*
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * or via info@adempiere.net or http://www.adempiere.net/license.html         *
 *****************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for AD_ChartDatasource
 *  @author Adempiere (generated) 
 *  @version Release 3.9.1
 */
public interface I_AD_ChartDatasource 
{

    /** TableName=AD_ChartDatasource */
    public static final String Table_Name = "AD_ChartDatasource";

    /** AD_Table_ID=53282 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 4 - System 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(4);

    /** Load Meta Data */

    /** Column name AD_ChartDatasource_ID */
    public static final String COLUMNNAME_AD_ChartDatasource_ID = "AD_ChartDatasource_ID";

	/** Set Chart Datasource	  */
	public void setAD_ChartDatasource_ID (int AD_ChartDatasource_ID);

	/** Get Chart Datasource	  */
	public int getAD_ChartDatasource_ID();

    /** Column name AD_Chart_ID */
    public static final String COLUMNNAME_AD_Chart_ID = "AD_Chart_ID";

	/** Set Chart	  */
	public void setAD_Chart_ID (int AD_Chart_ID);

	/** Get Chart	  */
	public int getAD_Chart_ID();

	public org.compiere.model.I_AD_Chart getAD_Chart() throws RuntimeException;

    /** Column name AD_Client_ID */
    public static final String COLUMNNAME_AD_Client_ID = "AD_Client_ID";

	/** Get Client.
	  * Client/Tenant for this installation.
	  */
	public int getAD_Client_ID();

    /** Column name AD_Org_ID */
    public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

	/** Set Organization.
	  * Organizational entity within client
	  */
	public void setAD_Org_ID (int AD_Org_ID);

	/** Get Organization.
	  * Organizational entity within client
	  */
	public int getAD_Org_ID();

    /** Column name AD_Table_ID */
    public static final String COLUMNNAME_AD_Table_ID = "AD_Table_ID";

	/** Set Table.
	  * Database Table information
	  */
	public void setAD_Table_ID (int AD_Table_ID);

	/** Get Table.
	  * Database Table information
	  */
	public int getAD_Table_ID();

	public org.compiere.model.I_AD_Table getAD_Table() throws RuntimeException;

    /** Column name CategoryColumn */
    public static final String COLUMNNAME_CategoryColumn = "CategoryColumn";

	/** Set Category Column.
	  * Fully qualified data category column
	  */
	public void setCategoryColumn (String CategoryColumn);

	/** Get Category Column.
	  * Fully qualified data category column
	  */
	public String getCategoryColumn();

    /** Column name Created */
    public static final String COLUMNNAME_Created = "Created";

	/** Get Created.
	  * Date this record was created
	  */
	public Timestamp getCreated();

    /** Column name CreatedBy */
    public static final String COLUMNNAME_CreatedBy = "CreatedBy";

	/** Get Created By.
	  * User who created this records
	  */
	public int getCreatedBy();

    /** Column name DateColumn */
    public static final String COLUMNNAME_DateColumn = "DateColumn";

	/** Set Date Column.
	  * Fully qualified date column
	  */
	public void setDateColumn (String DateColumn);

	/** Get Date Column.
	  * Fully qualified date column
	  */
	public String getDateColumn();

    /** Column name Description */
    public static final String COLUMNNAME_Description = "Description";

	/** Set Description.
	  * Optional short description of the record
	  */
	public void setDescription (String Description);

	/** Get Description.
	  * Optional short description of the record
	  */
	public String getDescription();

    /** Column name EntityType */
    public static final String COLUMNNAME_EntityType = "EntityType";

	/** Set Entity Type.
	  * Dictionary Entity Type;
 Determines ownership and synchronization
	  */
	public void setEntityType (String EntityType);

	/** Get Entity Type.
	  * Dictionary Entity Type;
 Determines ownership and synchronization
	  */
	public String getEntityType();

    /** Column name FromClause */
    public static final String COLUMNNAME_FromClause = "FromClause";

	/** Set Sql FROM.
	  * SQL FROM clause
	  */
	public void setFromClause (String FromClause);

	/** Get Sql FROM.
	  * SQL FROM clause
	  */
	public String getFromClause();

    /** Column name IsActive */
    public static final String COLUMNNAME_IsActive = "IsActive";

	/** Set Active.
	  * The record is active in the system
	  */
	public void setIsActive (boolean IsActive);

	/** Get Active.
	  * The record is active in the system
	  */
	public boolean isActive();

    /** Column name KeyColumn */
    public static final String COLUMNNAME_KeyColumn = "KeyColumn";

	/** Set Key Column.
	  * Key Column for Table
	  */
	public void setKeyColumn (String KeyColumn);

	/** Get Key Column.
	  * Key Column for Table
	  */
	public String getKeyColumn();

    /** Column name Name */
    public static final String COLUMNNAME_Name = "Name";

	/** Set Name.
	  * Alphanumeric identifier of the entity
	  */
	public void setName (String Name);

	/** Get Name.
	  * Alphanumeric identifier of the entity
	  */
	public String getName();

    /** Column name SeriesColumn */
    public static final String COLUMNNAME_SeriesColumn = "SeriesColumn";

	/** Set Series Column	  */
	public void setSeriesColumn (String SeriesColumn);

	/** Get Series Column	  */
	public String getSeriesColumn();

    /** Column name TimeOffset */
    public static final String COLUMNNAME_TimeOffset = "TimeOffset";

	/** Set Time Offset.
	  * Number of time units to offset displayed chart data from the current date.
	  */
	public void setTimeOffset (int TimeOffset);

	/** Get Time Offset.
	  * Number of time units to offset displayed chart data from the current date.
	  */
	public int getTimeOffset();

    /** Column name UUID */
    public static final String COLUMNNAME_UUID = "UUID";

	/** Set Immutable Universally Unique Identifier.
	  * Immutable Universally Unique Identifier
	  */
	public void setUUID (String UUID);

	/** Get Immutable Universally Unique Identifier.
	  * Immutable Universally Unique Identifier
	  */
	public String getUUID();

    /** Column name Updated */
    public static final String COLUMNNAME_Updated = "Updated";

	/** Get Updated.
	  * Date this record was updated
	  */
	public Timestamp getUpdated();

    /** Column name UpdatedBy */
    public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

	/** Get Updated By.
	  * User who updated this records
	  */
	public int getUpdatedBy();

    /** Column name ValueColumn */
    public static final String COLUMNNAME_ValueColumn = "ValueColumn";

	/** Set Value Column.
	  * Fully qualified data value column
	  */
	public void setValueColumn (String ValueColumn);

	/** Get Value Column.
	  * Fully qualified data value column
	  */
	public String getValueColumn();

    /** Column name WhereClause */
    public static final String COLUMNNAME_WhereClause = "WhereClause";

	/** Set Sql WHERE.
	  * Fully qualified SQL WHERE clause
	  */
	public void setWhereClause (String WhereClause);

	/** Get Sql WHERE.
	  * Fully qualified SQL WHERE clause
	  */
	public String getWhereClause();
}
