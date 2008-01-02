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

/** Generated Interface for I_ElementValue
 *  @author Trifon Trifonov (generated) 
 *  @version Release 3.3.1t
 */
public interface I_I_ElementValue 
{

    /** TableName=I_ElementValue */
    public static final String Table_Name = "I_ElementValue";

    /** AD_Table_ID=534 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 6 - System - Client 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(6);

    /** Load Meta Data */

    /** Column name AD_Column_ID */
    public static final String COLUMNNAME_AD_Column_ID = "AD_Column_ID";

	/** Set Column.
	  * Column in the table
	  */
	public void setAD_Column_ID (int AD_Column_ID);

	/** Get Column.
	  * Column in the table
	  */
	public int getAD_Column_ID();

    /** Column name AccountSign */
    public static final String COLUMNNAME_AccountSign = "AccountSign";

	/** Set Account Sign.
	  * Indicates the Natural Sign of the Account as a Debit or Credit
	  */
	public void setAccountSign (String AccountSign);

	/** Get Account Sign.
	  * Indicates the Natural Sign of the Account as a Debit or Credit
	  */
	public String getAccountSign();

    /** Column name AccountType */
    public static final String COLUMNNAME_AccountType = "AccountType";

	/** Set Account Type.
	  * Indicates the type of account
	  */
	public void setAccountType (String AccountType);

	/** Get Account Type.
	  * Indicates the type of account
	  */
	public String getAccountType();

    /** Column name C_ElementValue_ID */
    public static final String COLUMNNAME_C_ElementValue_ID = "C_ElementValue_ID";

	/** Set Account Element.
	  * Account Element
	  */
	public void setC_ElementValue_ID (int C_ElementValue_ID);

	/** Get Account Element.
	  * Account Element
	  */
	public int getC_ElementValue_ID();

	public I_C_ElementValue getC_ElementValue() throws Exception;

    /** Column name C_Element_ID */
    public static final String COLUMNNAME_C_Element_ID = "C_Element_ID";

	/** Set Element.
	  * Accounting Element
	  */
	public void setC_Element_ID (int C_Element_ID);

	/** Get Element.
	  * Accounting Element
	  */
	public int getC_Element_ID();

	public I_C_Element getC_Element() throws Exception;

    /** Column name Default_Account */
    public static final String COLUMNNAME_Default_Account = "Default_Account";

	/** Set Default Account.
	  * Name of the Default Account Column
	  */
	public void setDefault_Account (String Default_Account);

	/** Get Default Account.
	  * Name of the Default Account Column
	  */
	public String getDefault_Account();

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

    /** Column name ElementName */
    public static final String COLUMNNAME_ElementName = "ElementName";

	/** Set Element Name.
	  * Name of the Element
	  */
	public void setElementName (String ElementName);

	/** Get Element Name.
	  * Name of the Element
	  */
	public String getElementName();

    /** Column name I_ElementValue_ID */
    public static final String COLUMNNAME_I_ElementValue_ID = "I_ElementValue_ID";

	/** Set Import Account.
	  * Import Account Value
	  */
	public void setI_ElementValue_ID (int I_ElementValue_ID);

	/** Get Import Account.
	  * Import Account Value
	  */
	public int getI_ElementValue_ID();

    /** Column name I_ErrorMsg */
    public static final String COLUMNNAME_I_ErrorMsg = "I_ErrorMsg";

	/** Set Import Error Message.
	  * Messages generated from import process
	  */
	public void setI_ErrorMsg (String I_ErrorMsg);

	/** Get Import Error Message.
	  * Messages generated from import process
	  */
	public String getI_ErrorMsg();

    /** Column name I_IsImported */
    public static final String COLUMNNAME_I_IsImported = "I_IsImported";

	/** Set Imported.
	  * Has this import been processed
	  */
	public void setI_IsImported (boolean I_IsImported);

	/** Get Imported.
	  * Has this import been processed
	  */
	public boolean isI_IsImported();

    /** Column name IsDocControlled */
    public static final String COLUMNNAME_IsDocControlled = "IsDocControlled";

	/** Set Document Controlled.
	  * Control account - If an account is controlled by a document, you cannot post manually to it
	  */
	public void setIsDocControlled (boolean IsDocControlled);

	/** Get Document Controlled.
	  * Control account - If an account is controlled by a document, you cannot post manually to it
	  */
	public boolean isDocControlled();

    /** Column name IsSummary */
    public static final String COLUMNNAME_IsSummary = "IsSummary";

	/** Set Summary Level.
	  * This is a summary entity
	  */
	public void setIsSummary (boolean IsSummary);

	/** Get Summary Level.
	  * This is a summary entity
	  */
	public boolean isSummary();

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

    /** Column name ParentElementValue_ID */
    public static final String COLUMNNAME_ParentElementValue_ID = "ParentElementValue_ID";

	/** Set Parent Account.
	  * The parent (summary) account
	  */
	public void setParentElementValue_ID (int ParentElementValue_ID);

	/** Get Parent Account.
	  * The parent (summary) account
	  */
	public int getParentElementValue_ID();

    /** Column name ParentValue */
    public static final String COLUMNNAME_ParentValue = "ParentValue";

	/** Set Parent Key.
	  * Key if the Parent
	  */
	public void setParentValue (String ParentValue);

	/** Get Parent Key.
	  * Key if the Parent
	  */
	public String getParentValue();

    /** Column name PostActual */
    public static final String COLUMNNAME_PostActual = "PostActual";

	/** Set Post Actual.
	  * Actual Values can be posted
	  */
	public void setPostActual (boolean PostActual);

	/** Get Post Actual.
	  * Actual Values can be posted
	  */
	public boolean isPostActual();

    /** Column name PostBudget */
    public static final String COLUMNNAME_PostBudget = "PostBudget";

	/** Set Post Budget.
	  * Budget values can be posted
	  */
	public void setPostBudget (boolean PostBudget);

	/** Get Post Budget.
	  * Budget values can be posted
	  */
	public boolean isPostBudget();

    /** Column name PostEncumbrance */
    public static final String COLUMNNAME_PostEncumbrance = "PostEncumbrance";

	/** Set Post Encumbrance.
	  * Post commitments to this account
	  */
	public void setPostEncumbrance (boolean PostEncumbrance);

	/** Get Post Encumbrance.
	  * Post commitments to this account
	  */
	public boolean isPostEncumbrance();

    /** Column name PostStatistical */
    public static final String COLUMNNAME_PostStatistical = "PostStatistical";

	/** Set Post Statistical.
	  * Post statistical quantities to this account?
	  */
	public void setPostStatistical (boolean PostStatistical);

	/** Get Post Statistical.
	  * Post statistical quantities to this account?
	  */
	public boolean isPostStatistical();

    /** Column name Processed */
    public static final String COLUMNNAME_Processed = "Processed";

	/** Set Processed.
	  * The document has been processed
	  */
	public void setProcessed (boolean Processed);

	/** Get Processed.
	  * The document has been processed
	  */
	public boolean isProcessed();

    /** Column name Processing */
    public static final String COLUMNNAME_Processing = "Processing";

	/** Set Process Now	  */
	public void setProcessing (boolean Processing);

	/** Get Process Now	  */
	public boolean isProcessing();

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
