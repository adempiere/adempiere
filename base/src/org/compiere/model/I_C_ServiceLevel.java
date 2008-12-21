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

/** Generated Interface for C_ServiceLevel
 *  @author Adempiere (generated) 
 *  @version Release 3.5.3a
 */
public interface I_C_ServiceLevel 
{

    /** TableName=C_ServiceLevel */
    public static final String Table_Name = "C_ServiceLevel";

    /** AD_Table_ID=337 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 1 - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(1);

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

    /** Column name C_RevenueRecognition_Plan_ID */
    public static final String COLUMNNAME_C_RevenueRecognition_Plan_ID = "C_RevenueRecognition_Plan_ID";

	/** Set Revenue Recognition Plan.
	  * Plan for recognizing or recording revenue
	  */
	public void setC_RevenueRecognition_Plan_ID (int C_RevenueRecognition_Plan_ID);

	/** Get Revenue Recognition Plan.
	  * Plan for recognizing or recording revenue
	  */
	public int getC_RevenueRecognition_Plan_ID();

	public I_C_RevenueRecognition_Plan getC_RevenueRecognition_Plan() throws RuntimeException;

    /** Column name C_ServiceLevel_ID */
    public static final String COLUMNNAME_C_ServiceLevel_ID = "C_ServiceLevel_ID";

	/** Set Service Level.
	  * Product Revenue Recognition Service Level 
	  */
	public void setC_ServiceLevel_ID (int C_ServiceLevel_ID);

	/** Get Service Level.
	  * Product Revenue Recognition Service Level 
	  */
	public int getC_ServiceLevel_ID();

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

	public I_M_Product getM_Product() throws RuntimeException;

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

    /** Column name ServiceLevelInvoiced */
    public static final String COLUMNNAME_ServiceLevelInvoiced = "ServiceLevelInvoiced";

	/** Set Quantity Invoiced.
	  * Quantity of product or service invoiced
	  */
	public void setServiceLevelInvoiced (BigDecimal ServiceLevelInvoiced);

	/** Get Quantity Invoiced.
	  * Quantity of product or service invoiced
	  */
	public BigDecimal getServiceLevelInvoiced();

    /** Column name ServiceLevelProvided */
    public static final String COLUMNNAME_ServiceLevelProvided = "ServiceLevelProvided";

	/** Set Quantity Provided.
	  * Quantity of service or product provided
	  */
	public void setServiceLevelProvided (BigDecimal ServiceLevelProvided);

	/** Get Quantity Provided.
	  * Quantity of service or product provided
	  */
	public BigDecimal getServiceLevelProvided();
}
