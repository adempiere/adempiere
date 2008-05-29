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
package org.eevolution.model;

import java.math.BigDecimal;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXP_ProcessorParameter
 *  @author Trifon Trifonov (generated) 
 *  @version Release 3.5.1a
 */
public interface I_EXP_ProcessorParameter 
{

    /** TableName=EXP_ProcessorParameter */
    public static final String Table_Name = "EXP_ProcessorParameter";

    /** AD_Table_ID=53075 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 7 - System - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(7);

    /** Load Meta Data */

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

    /** Column name EXP_ProcessorParameter_ID */
    public static final String COLUMNNAME_EXP_ProcessorParameter_ID = "EXP_ProcessorParameter_ID";

	/** Set EXP_ProcessorParameter_ID	  */
	public void setEXP_ProcessorParameter_ID (int EXP_ProcessorParameter_ID);

	/** Get EXP_ProcessorParameter_ID	  */
	public int getEXP_ProcessorParameter_ID();

    /** Column name EXP_Processor_ID */
    public static final String COLUMNNAME_EXP_Processor_ID = "EXP_Processor_ID";

	/** Set EXP_Processor_ID	  */
	public void setEXP_Processor_ID (int EXP_Processor_ID);

	/** Get EXP_Processor_ID	  */
	public int getEXP_Processor_ID();

	public org.eevolution.model.I_EXP_Processor getEXP_Processor() throws Exception;

    /** Column name Help */
    public static final String COLUMNNAME_Help = "Help";

	/** Set Comment/Help.
	  * Comment or Hint
	  */
	public void setHelp (String Help);

	/** Get Comment/Help.
	  * Comment or Hint
	  */
	public String getHelp();

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

    /** Column name ParameterValue */
    public static final String COLUMNNAME_ParameterValue = "ParameterValue";

	/** Set ParameterValue	  */
	public void setParameterValue (String ParameterValue);

	/** Get ParameterValue	  */
	public String getParameterValue();

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
