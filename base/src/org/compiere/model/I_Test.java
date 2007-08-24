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

import java.util.*;
import java.sql.Timestamp;
import java.math.*;
import org.compiere.util.*;

    /** Generated Interface for Test
     *  @author Trifon Trifonov (generated) 
     *  @version Release 3.3.0 - 2007-08-24 11:39:56.281
     */
    public interface I_Test 
{

    /** TableName=Test */
    public static final String Table_Name = "Test";

    /** AD_Table_ID=135 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 4 - System 
     */
    BigDecimal accessLevel = new BigDecimal(4);

    /** Load Meta Data */

    /** Column name Account_Acct */
    public static final String COLUMNNAME_Account_Acct = "Account_Acct";

	/** Set Account_Acct	  */
	public void setAccount_Acct (int Account_Acct);

	/** Get Account_Acct	  */
	public int getAccount_Acct();

    /** Column name BinaryData */
    public static final String COLUMNNAME_BinaryData = "BinaryData";

	/** Set BinaryData.
	  * Binary Data
	  */
	public void setBinaryData (int BinaryData);

	/** Get BinaryData.
	  * Binary Data
	  */
	public int getBinaryData();

    /** Column name C_BPartner_ID */
    public static final String COLUMNNAME_C_BPartner_ID = "C_BPartner_ID";

	/** Set Business Partner .
	  * Identifies a Business Partner
	  */
	public void setC_BPartner_ID (int C_BPartner_ID);

	/** Get Business Partner .
	  * Identifies a Business Partner
	  */
	public int getC_BPartner_ID();

    /** Column name C_Currency_ID */
    public static final String COLUMNNAME_C_Currency_ID = "C_Currency_ID";

	/** Set Currency.
	  * The Currency for this record
	  */
	public void setC_Currency_ID (int C_Currency_ID);

	/** Get Currency.
	  * The Currency for this record
	  */
	public int getC_Currency_ID();

	public I_C_Currency getI_C_Currency() throws Exception;

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

    /** Column name C_Payment_ID */
    public static final String COLUMNNAME_C_Payment_ID = "C_Payment_ID";

	/** Set Payment.
	  * Payment identifier
	  */
	public void setC_Payment_ID (int C_Payment_ID);

	/** Get Payment.
	  * Payment identifier
	  */
	public int getC_Payment_ID();

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

	public I_C_UOM getI_C_UOM() throws Exception;

    /** Column name CharacterData */
    public static final String COLUMNNAME_CharacterData = "CharacterData";

	/** Set Character Data.
	  * Long Character Field
	  */
	public void setCharacterData (String CharacterData);

	/** Get Character Data.
	  * Long Character Field
	  */
	public String getCharacterData();

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

    /** Column name M_Locator_ID */
    public static final String COLUMNNAME_M_Locator_ID = "M_Locator_ID";

	/** Set Locator.
	  * Warehouse Locator
	  */
	public void setM_Locator_ID (int M_Locator_ID);

	/** Get Locator.
	  * Warehouse Locator
	  */
	public int getM_Locator_ID();

    /** Column name M_Product_ID */
    public static final String COLUMNNAME_M_Product_ID = "M_Product_ID";

	/** Set Product.
	  * Product, Service, Item
	  */
	public void setM_Product_ID (int M_Product_ID);

	/** Get Product.
	  * Product, Service, Item
	  */
	public int getM_Product_ID();

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

    /** Column name T_Amount */
    public static final String COLUMNNAME_T_Amount = "T_Amount";

	/** Set Amount	  */
	public void setT_Amount (BigDecimal T_Amount);

	/** Get Amount	  */
	public BigDecimal getT_Amount();

    /** Column name T_Date */
    public static final String COLUMNNAME_T_Date = "T_Date";

	/** Set Date	  */
	public void setT_Date (Timestamp T_Date);

	/** Get Date	  */
	public Timestamp getT_Date();

    /** Column name T_DateTime */
    public static final String COLUMNNAME_T_DateTime = "T_DateTime";

	/** Set DateTime	  */
	public void setT_DateTime (Timestamp T_DateTime);

	/** Get DateTime	  */
	public Timestamp getT_DateTime();

    /** Column name T_Integer */
    public static final String COLUMNNAME_T_Integer = "T_Integer";

	/** Set Integer	  */
	public void setT_Integer (int T_Integer);

	/** Get Integer	  */
	public int getT_Integer();

    /** Column name T_Number */
    public static final String COLUMNNAME_T_Number = "T_Number";

	/** Set Number	  */
	public void setT_Number (BigDecimal T_Number);

	/** Get Number	  */
	public BigDecimal getT_Number();

    /** Column name T_Qty */
    public static final String COLUMNNAME_T_Qty = "T_Qty";

	/** Set Qty	  */
	public void setT_Qty (BigDecimal T_Qty);

	/** Get Qty	  */
	public BigDecimal getT_Qty();

    /** Column name Test_ID */
    public static final String COLUMNNAME_Test_ID = "Test_ID";

	/** Set Test ID	  */
	public void setTest_ID (int Test_ID);

	/** Get Test ID	  */
	public int getTest_ID();
}
