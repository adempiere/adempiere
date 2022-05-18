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
package org.spin.investment.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Interface for FM_Product
 *  @author Adempiere (generated) 
 *  @version Release 3.9.3
 */
public interface I_FM_Product 
{

    /** TableName=FM_Product */
    public static final String Table_Name = "FM_Product";

    /** AD_Table_ID=54361 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

    /** Load Meta Data */

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

    /** Column name C_Charge_ID */
    public static final String COLUMNNAME_C_Charge_ID = "C_Charge_ID";

	/** Set Charge.
	  * Additional document charges
	  */
	public void setC_Charge_ID (int C_Charge_ID);

	/** Get Charge.
	  * Additional document charges
	  */
	public int getC_Charge_ID();

	public org.compiere.model.I_C_Charge getC_Charge() throws RuntimeException;

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

	public org.compiere.model.I_C_Currency getC_Currency() throws RuntimeException;

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

    /** Column name DunningInterest_ID */
    public static final String COLUMNNAME_DunningInterest_ID = "DunningInterest_ID";

	/** Set Dunning Interest	  */
	public void setDunningInterest_ID (int DunningInterest_ID);

	/** Get Dunning Interest	  */
	public int getDunningInterest_ID();

	public org.spin.investment.model.I_FM_Rate getDunningInterest() throws RuntimeException;

    /** Column name FM_Dunning_ID */
    public static final String COLUMNNAME_FM_Dunning_ID = "FM_Dunning_ID";

	/** Set Financial Dunning	  */
	public void setFM_Dunning_ID (int FM_Dunning_ID);

	/** Get Financial Dunning	  */
	public int getFM_Dunning_ID();

	public org.spin.investment.model.I_FM_Dunning getFM_Dunning() throws RuntimeException;

    /** Column name FM_ProductCategory_ID */
    public static final String COLUMNNAME_FM_ProductCategory_ID = "FM_ProductCategory_ID";

	/** Set Financial Product Category	  */
	public void setFM_ProductCategory_ID (int FM_ProductCategory_ID);

	/** Get Financial Product Category	  */
	public int getFM_ProductCategory_ID();

	public org.spin.investment.model.I_FM_ProductCategory getFM_ProductCategory() throws RuntimeException;

    /** Column name FM_Product_ID */
    public static final String COLUMNNAME_FM_Product_ID = "FM_Product_ID";

	/** Set Financial Product	  */
	public void setFM_Product_ID (int FM_Product_ID);

	/** Get Financial Product	  */
	public int getFM_Product_ID();

    /** Column name FM_Rate_ID */
    public static final String COLUMNNAME_FM_Rate_ID = "FM_Rate_ID";

	/** Set Financial Rate	  */
	public void setFM_Rate_ID (int FM_Rate_ID);

	/** Get Financial Rate	  */
	public int getFM_Rate_ID();

	public org.spin.investment.model.I_FM_Rate getFM_Rate() throws RuntimeException;

    /** Column name GraceDays */
    public static final String COLUMNNAME_GraceDays = "GraceDays";

	/** Set Grace Days.
	  * Days after due date to send first dunning letter
	  */
	public void setGraceDays (int GraceDays);

	/** Get Grace Days.
	  * Days after due date to send first dunning letter
	  */
	public int getGraceDays();

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

    /** Column name IsDueFixed */
    public static final String COLUMNNAME_IsDueFixed = "IsDueFixed";

	/** Set Fixed due date.
	  * Payment is due on a fixed date
	  */
	public void setIsDueFixed (boolean IsDueFixed);

	/** Get Fixed due date.
	  * Payment is due on a fixed date
	  */
	public boolean isDueFixed();

    /** Column name MaxCapitalAmt */
    public static final String COLUMNNAME_MaxCapitalAmt = "MaxCapitalAmt";

	/** Set Max Capital Amount	  */
	public void setMaxCapitalAmt (BigDecimal MaxCapitalAmt);

	/** Get Max Capital Amount	  */
	public BigDecimal getMaxCapitalAmt();

    /** Column name MaxFeesQty */
    public static final String COLUMNNAME_MaxFeesQty = "MaxFeesQty";

	/** Set Max Fees Quantity	  */
	public void setMaxFeesQty (BigDecimal MaxFeesQty);

	/** Get Max Fees Quantity	  */
	public BigDecimal getMaxFeesQty();

    /** Column name MinCapitalAmt */
    public static final String COLUMNNAME_MinCapitalAmt = "MinCapitalAmt";

	/** Set Min Capital Amount	  */
	public void setMinCapitalAmt (BigDecimal MinCapitalAmt);

	/** Get Min Capital Amount	  */
	public BigDecimal getMinCapitalAmt();

    /** Column name MinFeesQty */
    public static final String COLUMNNAME_MinFeesQty = "MinFeesQty";

	/** Set Min Fees Quantity	  */
	public void setMinFeesQty (BigDecimal MinFeesQty);

	/** Get Min Fees Quantity	  */
	public BigDecimal getMinFeesQty();

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

	public org.compiere.model.I_M_Product getM_Product() throws RuntimeException;

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

    /** Column name PaymentFrequency */
    public static final String COLUMNNAME_PaymentFrequency = "PaymentFrequency";

	/** Set Payment Frequency.
	  * Payment Frequency
	  */
	public void setPaymentFrequency (String PaymentFrequency);

	/** Get Payment Frequency.
	  * Payment Frequency
	  */
	public String getPaymentFrequency();

    /** Column name PrepayFeeRate_ID */
    public static final String COLUMNNAME_PrepayFeeRate_ID = "PrepayFeeRate_ID";

	/** Set Prepayment Fee Rate.
	  * % penalty applied to loan interest when it is prepaid
	  */
	public void setPrepayFeeRate_ID (int PrepayFeeRate_ID);

	/** Get Prepayment Fee Rate.
	  * % penalty applied to loan interest when it is prepaid
	  */
	public int getPrepayFeeRate_ID();

	public org.spin.investment.model.I_FM_Rate getPrepayFeeRate() throws RuntimeException;

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
