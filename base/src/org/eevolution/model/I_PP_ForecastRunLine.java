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
package org.eevolution.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Interface for PP_ForecastRunLine
 *  @author Adempiere (generated) 
 *  @version Release 3.9.1
 */
public interface I_PP_ForecastRunLine 
{

    /** TableName=PP_ForecastRunLine */
    public static final String Table_Name = "PP_ForecastRunLine";

    /** AD_Table_ID=53394 */
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

    /** Column name C_SalesHistory_ID */
    public static final String COLUMNNAME_C_SalesHistory_ID = "C_SalesHistory_ID";

	/** Set Sales History.
	  * Sales History for statistics and forecast
	  */
	public void setC_SalesHistory_ID (int C_SalesHistory_ID);

	/** Get Sales History.
	  * Sales History for statistics and forecast
	  */
	public int getC_SalesHistory_ID();

	public org.eevolution.model.I_C_SalesHistory getC_SalesHistory() throws RuntimeException;

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

    /** Column name PP_ForecastRunDetail_ID */
    public static final String COLUMNNAME_PP_ForecastRunDetail_ID = "PP_ForecastRunDetail_ID";

	/** Set Forecast Run Detail.
	  * Contains the forecasting calculation results based on forecast definition.
	  */
	public void setPP_ForecastRunDetail_ID (int PP_ForecastRunDetail_ID);

	/** Get Forecast Run Detail.
	  * Contains the forecasting calculation results based on forecast definition.
	  */
	public int getPP_ForecastRunDetail_ID();

	public org.eevolution.model.I_PP_ForecastRunDetail getPP_ForecastRunDetail() throws RuntimeException;

    /** Column name PP_ForecastRunLine_ID */
    public static final String COLUMNNAME_PP_ForecastRunLine_ID = "PP_ForecastRunLine_ID";

	/** Set Forecast Run Line.
	  * Contains the forecasting calculation results based on forecast definition.
	  */
	public void setPP_ForecastRunLine_ID (int PP_ForecastRunLine_ID);

	/** Get Forecast Run Line.
	  * Contains the forecasting calculation results based on forecast definition.
	  */
	public int getPP_ForecastRunLine_ID();

    /** Column name PP_ForecastRun_ID */
    public static final String COLUMNNAME_PP_ForecastRun_ID = "PP_ForecastRun_ID";

	/** Set Forecast Run.
	  * Create the forecast simulation based on the forecast definition
	  */
	public void setPP_ForecastRun_ID (int PP_ForecastRun_ID);

	/** Get Forecast Run.
	  * Create the forecast simulation based on the forecast definition
	  */
	public int getPP_ForecastRun_ID();

	public org.eevolution.model.I_PP_ForecastRun getPP_ForecastRun() throws RuntimeException;

    /** Column name PP_Period_ID */
    public static final String COLUMNNAME_PP_Period_ID = "PP_Period_ID";

	/** Set Operational Period.
	  * Forecast Definition Periods.
	  */
	public void setPP_Period_ID (int PP_Period_ID);

	/** Get Operational Period.
	  * Forecast Definition Periods.
	  */
	public int getPP_Period_ID();

	public org.eevolution.model.I_PP_Period getPP_Period() throws RuntimeException;

    /** Column name QtyInvoiced */
    public static final String COLUMNNAME_QtyInvoiced = "QtyInvoiced";

	/** Set Quantity Invoiced.
	  * Invoiced Quantity
	  */
	public void setQtyInvoiced (BigDecimal QtyInvoiced);

	/** Get Quantity Invoiced.
	  * Invoiced Quantity
	  */
	public BigDecimal getQtyInvoiced();

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
}
