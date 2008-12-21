/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2007 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software;
 you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY;
 without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program;
 if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for M_Warehouse
 *  @author Adempiere (generated) 
 *  @version Release 3.5.3a
 */
public interface I_M_Warehouse 
{

    /** TableName=M_Warehouse */
    public static final String Table_Name = "M_Warehouse";

    /** AD_Table_ID=190 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

    /** Load Meta Data */

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

    /** Column name C_Location_ID */
    public static final String COLUMNNAME_C_Location_ID = "C_Location_ID";

	/** Set Address.
	  * Location or Address
	  */
	public void setC_Location_ID (int C_Location_ID);

	/** Get Address.
	  * Location or Address
	  */
	public int getC_Location_ID();

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

    /** Column name IsInTransit */
    public static final String COLUMNNAME_IsInTransit = "IsInTransit";

	/** Set In Transit.
	  * Movement is in transit
	  */
	public void setIsInTransit (boolean IsInTransit);

	/** Get In Transit.
	  * Movement is in transit
	  */
	public boolean isInTransit();

    /** Column name M_Warehouse_ID */
    public static final String COLUMNNAME_M_Warehouse_ID = "M_Warehouse_ID";

	/** Set Warehouse.
	  * Storage Warehouse and Service Point
	  */
	public void setM_Warehouse_ID (int M_Warehouse_ID);

	/** Get Warehouse.
	  * Storage Warehouse and Service Point
	  */
	public int getM_Warehouse_ID();

    /** Column name M_WarehouseSource_ID */
    public static final String COLUMNNAME_M_WarehouseSource_ID = "M_WarehouseSource_ID";

	/** Set Source Warehouse.
	  * Optional Warehouse to replenish from
	  */
	public void setM_WarehouseSource_ID (int M_WarehouseSource_ID);

	/** Get Source Warehouse.
	  * Optional Warehouse to replenish from
	  */
	public int getM_WarehouseSource_ID();

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

    /** Column name ReplenishmentClass */
    public static final String COLUMNNAME_ReplenishmentClass = "ReplenishmentClass";

	/** Set Replenishment Class.
	  * Custom class to calculate Quantity to Order
	  */
	public void setReplenishmentClass (String ReplenishmentClass);

	/** Get Replenishment Class.
	  * Custom class to calculate Quantity to Order
	  */
	public String getReplenishmentClass();

    /** Column name Separator */
    public static final String COLUMNNAME_Separator = "Separator";

	/** Set Element Separator.
	  * Element Separator
	  */
	public void setSeparator (String Separator);

	/** Get Element Separator.
	  * Element Separator
	  */
	public String getSeparator();

    /** Column name Value */
    public static final String COLUMNNAME_Value = "Value";

	/** Set Search Key.
	  * Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value);

	/** Get Search Key.
	  * Search key for the record in the format required - must be unique
	  */
	public String getValue();
}
