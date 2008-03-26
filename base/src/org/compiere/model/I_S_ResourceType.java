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
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for S_ResourceType
 *  @author Trifon Trifonov (generated) 
 *  @version Release 3.4.0s
 */
public interface I_S_ResourceType 
{

    /** TableName=S_ResourceType */
    public static final String Table_Name = "S_ResourceType";

    /** AD_Table_ID=480 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

    /** Load Meta Data */

    /** Column name AllowUoMFractions */
    public static final String COLUMNNAME_AllowUoMFractions = "AllowUoMFractions";

	/** Set Allow UoM Fractions.
	  * Allow Unit of Measure Fractions
	  */
	public void setAllowUoMFractions (boolean AllowUoMFractions);

	/** Get Allow UoM Fractions.
	  * Allow Unit of Measure Fractions
	  */
	public boolean isAllowUoMFractions();

    /** Column name C_TaxCategory_ID */
    public static final String COLUMNNAME_C_TaxCategory_ID = "C_TaxCategory_ID";

	/** Set Tax Category.
	  * Tax Category
	  */
	public void setC_TaxCategory_ID (int C_TaxCategory_ID);

	/** Get Tax Category.
	  * Tax Category
	  */
	public int getC_TaxCategory_ID();

	public I_C_TaxCategory getC_TaxCategory() throws Exception;

    /** Column name C_UOM_ID */
    public static final String COLUMNNAME_C_UOM_ID = "C_UOM_ID";

	/** Set UOM.
	  * Unit of Measure
	  */
	public void setC_UOM_ID (int C_UOM_ID);

	/** Get UOM.
	  * Unit of Measure
	  */
	public int getC_UOM_ID();

	public I_C_UOM getC_UOM() throws Exception;

    /** Column name ChargeableQty */
    public static final String COLUMNNAME_ChargeableQty = "ChargeableQty";

	/** Set Chargeable Quantity	  */
	public void setChargeableQty (int ChargeableQty);

	/** Get Chargeable Quantity	  */
	public int getChargeableQty();

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

    /** Column name IsDateSlot */
    public static final String COLUMNNAME_IsDateSlot = "IsDateSlot";

	/** Set Day Slot.
	  * Resource has day slot availability
	  */
	public void setIsDateSlot (boolean IsDateSlot);

	/** Get Day Slot.
	  * Resource has day slot availability
	  */
	public boolean isDateSlot();

    /** Column name IsSingleAssignment */
    public static final String COLUMNNAME_IsSingleAssignment = "IsSingleAssignment";

	/** Set Single Assignment only.
	  * Only one assignment at a time (no double-booking or overlapping)
	  */
	public void setIsSingleAssignment (boolean IsSingleAssignment);

	/** Get Single Assignment only.
	  * Only one assignment at a time (no double-booking or overlapping)
	  */
	public boolean isSingleAssignment();

    /** Column name IsTimeSlot */
    public static final String COLUMNNAME_IsTimeSlot = "IsTimeSlot";

	/** Set Time Slot.
	  * Resource has time slot availability
	  */
	public void setIsTimeSlot (boolean IsTimeSlot);

	/** Get Time Slot.
	  * Resource has time slot availability
	  */
	public boolean isTimeSlot();

    /** Column name M_Product_Category_ID */
    public static final String COLUMNNAME_M_Product_Category_ID = "M_Product_Category_ID";

	/** Set Product Category.
	  * Category of a Product
	  */
	public void setM_Product_Category_ID (int M_Product_Category_ID);

	/** Get Product Category.
	  * Category of a Product
	  */
	public int getM_Product_Category_ID();

	public I_M_Product_Category getM_Product_Category() throws Exception;

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

    /** Column name OnFriday */
    public static final String COLUMNNAME_OnFriday = "OnFriday";

	/** Set Friday.
	  * Available on Fridays
	  */
	public void setOnFriday (boolean OnFriday);

	/** Get Friday.
	  * Available on Fridays
	  */
	public boolean isOnFriday();

    /** Column name OnMonday */
    public static final String COLUMNNAME_OnMonday = "OnMonday";

	/** Set Monday.
	  * Available on Mondays
	  */
	public void setOnMonday (boolean OnMonday);

	/** Get Monday.
	  * Available on Mondays
	  */
	public boolean isOnMonday();

    /** Column name OnSaturday */
    public static final String COLUMNNAME_OnSaturday = "OnSaturday";

	/** Set Saturday.
	  * Available on Saturday
	  */
	public void setOnSaturday (boolean OnSaturday);

	/** Get Saturday.
	  * Available on Saturday
	  */
	public boolean isOnSaturday();

    /** Column name OnSunday */
    public static final String COLUMNNAME_OnSunday = "OnSunday";

	/** Set Sunday.
	  * Available on Sundays
	  */
	public void setOnSunday (boolean OnSunday);

	/** Get Sunday.
	  * Available on Sundays
	  */
	public boolean isOnSunday();

    /** Column name OnThursday */
    public static final String COLUMNNAME_OnThursday = "OnThursday";

	/** Set Thursday.
	  * Available on Thursdays
	  */
	public void setOnThursday (boolean OnThursday);

	/** Get Thursday.
	  * Available on Thursdays
	  */
	public boolean isOnThursday();

    /** Column name OnTuesday */
    public static final String COLUMNNAME_OnTuesday = "OnTuesday";

	/** Set Tuesday.
	  * Available on Tuesdays
	  */
	public void setOnTuesday (boolean OnTuesday);

	/** Get Tuesday.
	  * Available on Tuesdays
	  */
	public boolean isOnTuesday();

    /** Column name OnWednesday */
    public static final String COLUMNNAME_OnWednesday = "OnWednesday";

	/** Set Wednesday.
	  * Available on Wednesdays
	  */
	public void setOnWednesday (boolean OnWednesday);

	/** Get Wednesday.
	  * Available on Wednesdays
	  */
	public boolean isOnWednesday();

    /** Column name S_ResourceType_ID */
    public static final String COLUMNNAME_S_ResourceType_ID = "S_ResourceType_ID";

	/** Set Resource Type	  */
	public void setS_ResourceType_ID (int S_ResourceType_ID);

	/** Get Resource Type	  */
	public int getS_ResourceType_ID();

    /** Column name TimeSlotEnd */
    public static final String COLUMNNAME_TimeSlotEnd = "TimeSlotEnd";

	/** Set Slot End.
	  * Time when timeslot ends
	  */
	public void setTimeSlotEnd (Timestamp TimeSlotEnd);

	/** Get Slot End.
	  * Time when timeslot ends
	  */
	public Timestamp getTimeSlotEnd();

    /** Column name TimeSlotStart */
    public static final String COLUMNNAME_TimeSlotStart = "TimeSlotStart";

	/** Set Slot Start.
	  * Time when timeslot starts
	  */
	public void setTimeSlotStart (Timestamp TimeSlotStart);

	/** Get Slot Start.
	  * Time when timeslot starts
	  */
	public Timestamp getTimeSlotStart();

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
