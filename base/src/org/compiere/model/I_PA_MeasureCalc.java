/**********************************************************************
 * This file is part of Adempiere ERP Bazaar                          *
 * http://www.adempiere.org                                           *
 *                                                                    *
 * Copyright (C) Trifon Trifonov.                                     *
 * Copyright (C) Contributors                                         *
 *                                                                    *
 * This program is free software;
 you can redistribute it and/or      *
 * modify it under the terms of the GNU General Public License        *
 * as published by the Free Software Foundation;
 either version 2     *
 * of the License, or (at your option) any later version.             *
 *                                                                    *
 * This program is distributed in the hope that it will be useful,    *
 * but WITHOUT ANY WARRANTY;
 without even the implied warranty of     *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the       *
 * GNU General Public License for more details.                       *
 *                                                                    *
 * You should have received a copy of the GNU General Public License  *
 * along with this program;
 if not, write to the Free Software        *
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,         *
 * MA 02110-1301, USA.                                                *
 *                                                                    *
 * Contributors:                                                      *
 * - Trifon Trifonov (trifonnt@users.sourceforge.net)                 *
 *                                                                    *
 * Sponsors:                                                          *
 * - Company (http://www.site.com)                                    *
 **********************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for PA_MeasureCalc
 *  @author Trifon Trifonov (generated) 
 *  @version Release 3.4.0s
 */
public interface I_PA_MeasureCalc 
{

    /** TableName=PA_MeasureCalc */
    public static final String Table_Name = "PA_MeasureCalc";

    /** AD_Table_ID=442 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 6 - System - Client 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(6);

    /** Load Meta Data */

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

	public I_AD_Table getAD_Table() throws Exception;

    /** Column name BPartnerColumn */
    public static final String COLUMNNAME_BPartnerColumn = "BPartnerColumn";

	/** Set B.Partner Column.
	  * Fully qualified Business Partner key column (C_BPartner_ID)
	  */
	public void setBPartnerColumn (String BPartnerColumn);

	/** Get B.Partner Column.
	  * Fully qualified Business Partner key column (C_BPartner_ID)
	  */
	public String getBPartnerColumn();

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

    /** Column name OrgColumn */
    public static final String COLUMNNAME_OrgColumn = "OrgColumn";

	/** Set Org Column.
	  * Fully qualified Organization column (AD_Org_ID)
	  */
	public void setOrgColumn (String OrgColumn);

	/** Get Org Column.
	  * Fully qualified Organization column (AD_Org_ID)
	  */
	public String getOrgColumn();

    /** Column name PA_MeasureCalc_ID */
    public static final String COLUMNNAME_PA_MeasureCalc_ID = "PA_MeasureCalc_ID";

	/** Set Measure Calculation.
	  * Calculation method for measuring performance
	  */
	public void setPA_MeasureCalc_ID (int PA_MeasureCalc_ID);

	/** Get Measure Calculation.
	  * Calculation method for measuring performance
	  */
	public int getPA_MeasureCalc_ID();

    /** Column name ProductColumn */
    public static final String COLUMNNAME_ProductColumn = "ProductColumn";

	/** Set Product Column.
	  * Fully qualified Product column (M_Product_ID)
	  */
	public void setProductColumn (String ProductColumn);

	/** Get Product Column.
	  * Fully qualified Product column (M_Product_ID)
	  */
	public String getProductColumn();

    /** Column name SelectClause */
    public static final String COLUMNNAME_SelectClause = "SelectClause";

	/** Set Sql SELECT.
	  * SQL SELECT clause
	  */
	public void setSelectClause (String SelectClause);

	/** Get Sql SELECT.
	  * SQL SELECT clause
	  */
	public String getSelectClause();

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
