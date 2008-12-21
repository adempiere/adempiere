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

/** Generated Interface for M_AttributeInstance
 *  @author Adempiere (generated) 
 *  @version Release 3.5.3a
 */
public interface I_M_AttributeInstance 
{

    /** TableName=M_AttributeInstance */
    public static final String Table_Name = "M_AttributeInstance";

    /** AD_Table_ID=561 */
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

    /** Column name M_Attribute_ID */
    public static final String COLUMNNAME_M_Attribute_ID = "M_Attribute_ID";

	/** Set Attribute.
	  * Product Attribute
	  */
	public void setM_Attribute_ID (int M_Attribute_ID);

	/** Get Attribute.
	  * Product Attribute
	  */
	public int getM_Attribute_ID();

	public I_M_Attribute getM_Attribute() throws RuntimeException;

    /** Column name M_AttributeSetInstance_ID */
    public static final String COLUMNNAME_M_AttributeSetInstance_ID = "M_AttributeSetInstance_ID";

	/** Set Attribute Set Instance.
	  * Product Attribute Set Instance
	  */
	public void setM_AttributeSetInstance_ID (int M_AttributeSetInstance_ID);

	/** Get Attribute Set Instance.
	  * Product Attribute Set Instance
	  */
	public int getM_AttributeSetInstance_ID();

    /** Column name M_AttributeValue_ID */
    public static final String COLUMNNAME_M_AttributeValue_ID = "M_AttributeValue_ID";

	/** Set Attribute Value.
	  * Product Attribute Value
	  */
	public void setM_AttributeValue_ID (int M_AttributeValue_ID);

	/** Get Attribute Value.
	  * Product Attribute Value
	  */
	public int getM_AttributeValue_ID();

	public I_M_AttributeValue getM_AttributeValue() throws RuntimeException;

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

    /** Column name ValueNumber */
    public static final String COLUMNNAME_ValueNumber = "ValueNumber";

	/** Set Value.
	  * Numeric Value
	  */
	public void setValueNumber (BigDecimal ValueNumber);

	/** Get Value.
	  * Numeric Value
	  */
	public BigDecimal getValueNumber();
}
