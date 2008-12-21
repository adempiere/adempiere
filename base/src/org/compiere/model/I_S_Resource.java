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

/** Generated Interface for S_Resource
 *  @author Adempiere (generated) 
 *  @version Release 3.5.3a
 */
public interface I_S_Resource 
{

    /** TableName=S_Resource */
    public static final String Table_Name = "S_Resource";

    /** AD_Table_ID=487 */
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

    /** Column name AD_User_ID */
    public static final String COLUMNNAME_AD_User_ID = "AD_User_ID";

	/** Set User/Contact.
	  * User within the system - Internal or Business Partner Contact
	  */
	public void setAD_User_ID (int AD_User_ID);

	/** Get User/Contact.
	  * User within the system - Internal or Business Partner Contact
	  */
	public int getAD_User_ID();

	public I_AD_User getAD_User() throws RuntimeException;

    /** Column name ChargeableQty */
    public static final String COLUMNNAME_ChargeableQty = "ChargeableQty";

	/** Set Chargeable Quantity	  */
	public void setChargeableQty (BigDecimal ChargeableQty);

	/** Get Chargeable Quantity	  */
	public BigDecimal getChargeableQty();

    /** Column name DailyCapacity */
    public static final String COLUMNNAME_DailyCapacity = "DailyCapacity";

	/** Set Daily Capacity	  */
	public void setDailyCapacity (BigDecimal DailyCapacity);

	/** Get Daily Capacity	  */
	public BigDecimal getDailyCapacity();

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

    /** Column name IsAvailable */
    public static final String COLUMNNAME_IsAvailable = "IsAvailable";

	/** Set Available.
	  * Resource is available
	  */
	public void setIsAvailable (boolean IsAvailable);

	/** Get Available.
	  * Resource is available
	  */
	public boolean isAvailable();

    /** Column name IsManufacturingResource */
    public static final String COLUMNNAME_IsManufacturingResource = "IsManufacturingResource";

	/** Set Manufacturing Resource	  */
	public void setIsManufacturingResource (boolean IsManufacturingResource);

	/** Get Manufacturing Resource	  */
	public boolean isManufacturingResource();

    /** Column name ManufacturingResourceType */
    public static final String COLUMNNAME_ManufacturingResourceType = "ManufacturingResourceType";

	/** Set Manufacturing Resource Type	  */
	public void setManufacturingResourceType (String ManufacturingResourceType);

	/** Get Manufacturing Resource Type	  */
	public String getManufacturingResourceType();

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

	public I_M_Warehouse getM_Warehouse() throws RuntimeException;

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

    /** Column name PercentUtilization */
    public static final String COLUMNNAME_PercentUtilization = "PercentUtilization";

	/** Set % Utilization	  */
	public void setPercentUtilization (BigDecimal PercentUtilization);

	/** Get % Utilization	  */
	public BigDecimal getPercentUtilization();

    /** Column name PlanningHorizon */
    public static final String COLUMNNAME_PlanningHorizon = "PlanningHorizon";

	/** Set Planning Horizon.
	  * The planning horizon is the amount of time (Days) an organisation will look into the future when preparing a strategic plan.
	  */
	public void setPlanningHorizon (int PlanningHorizon);

	/** Get Planning Horizon.
	  * The planning horizon is the amount of time (Days) an organisation will look into the future when preparing a strategic plan.
	  */
	public int getPlanningHorizon();

    /** Column name QueuingTime */
    public static final String COLUMNNAME_QueuingTime = "QueuingTime";

	/** Set Queuing Time	  */
	public void setQueuingTime (BigDecimal QueuingTime);

	/** Get Queuing Time	  */
	public BigDecimal getQueuingTime();

    /** Column name S_Resource_ID */
    public static final String COLUMNNAME_S_Resource_ID = "S_Resource_ID";

	/** Set Resource.
	  * Resource
	  */
	public void setS_Resource_ID (int S_Resource_ID);

	/** Get Resource.
	  * Resource
	  */
	public int getS_Resource_ID();

    /** Column name S_ResourceType_ID */
    public static final String COLUMNNAME_S_ResourceType_ID = "S_ResourceType_ID";

	/** Set Resource Type	  */
	public void setS_ResourceType_ID (int S_ResourceType_ID);

	/** Get Resource Type	  */
	public int getS_ResourceType_ID();

	public I_S_ResourceType getS_ResourceType() throws RuntimeException;

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

    /** Column name WaitingTime */
    public static final String COLUMNNAME_WaitingTime = "WaitingTime";

	/** Set Waiting Time.
	  * Workflow Simulation Waiting time
	  */
	public void setWaitingTime (BigDecimal WaitingTime);

	/** Get Waiting Time.
	  * Workflow Simulation Waiting time
	  */
	public BigDecimal getWaitingTime();
}
