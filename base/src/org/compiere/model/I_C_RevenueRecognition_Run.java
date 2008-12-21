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

/** Generated Interface for C_RevenueRecognition_Run
 *  @author Adempiere (generated) 
 *  @version Release 3.5.3a
 */
public interface I_C_RevenueRecognition_Run 
{

    /** TableName=C_RevenueRecognition_Run */
    public static final String Table_Name = "C_RevenueRecognition_Run";

    /** AD_Table_ID=444 */
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

    /** Column name C_RevenueRecognition_Run_ID */
    public static final String COLUMNNAME_C_RevenueRecognition_Run_ID = "C_RevenueRecognition_Run_ID";

	/** Set Revenue Recognition Run.
	  * Revenue Recognition Run or Process
	  */
	public void setC_RevenueRecognition_Run_ID (int C_RevenueRecognition_Run_ID);

	/** Get Revenue Recognition Run.
	  * Revenue Recognition Run or Process
	  */
	public int getC_RevenueRecognition_Run_ID();

    /** Column name GL_Journal_ID */
    public static final String COLUMNNAME_GL_Journal_ID = "GL_Journal_ID";

	/** Set Journal.
	  * General Ledger Journal
	  */
	public void setGL_Journal_ID (int GL_Journal_ID);

	/** Get Journal.
	  * General Ledger Journal
	  */
	public int getGL_Journal_ID();

	public I_GL_Journal getGL_Journal() throws RuntimeException;

    /** Column name RecognizedAmt */
    public static final String COLUMNNAME_RecognizedAmt = "RecognizedAmt";

	/** Set Recognized Amount	  */
	public void setRecognizedAmt (BigDecimal RecognizedAmt);

	/** Get Recognized Amount	  */
	public BigDecimal getRecognizedAmt();
}
