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
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for C_ServiceLevelLine
 *  @author Adempiere (generated) 
 *  @version Release 3.5.3a
 */
public interface I_C_ServiceLevelLine 
{

    /** TableName=C_ServiceLevelLine */
    public static final String Table_Name = "C_ServiceLevelLine";

    /** AD_Table_ID=338 */
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

	public I_C_ServiceLevel getC_ServiceLevel() throws RuntimeException;

    /** Column name C_ServiceLevelLine_ID */
    public static final String COLUMNNAME_C_ServiceLevelLine_ID = "C_ServiceLevelLine_ID";

	/** Set Service Level Line.
	  * Product Revenue Recognition Service Level Line
	  */
	public void setC_ServiceLevelLine_ID (int C_ServiceLevelLine_ID);

	/** Get Service Level Line.
	  * Product Revenue Recognition Service Level Line
	  */
	public int getC_ServiceLevelLine_ID();

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

    /** Column name ServiceDate */
    public static final String COLUMNNAME_ServiceDate = "ServiceDate";

	/** Set Service date.
	  * Date service was provided
	  */
	public void setServiceDate (Timestamp ServiceDate);

	/** Get Service date.
	  * Date service was provided
	  */
	public Timestamp getServiceDate();

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
