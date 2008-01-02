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

/** Generated Interface for M_OperationResource
 *  @author Trifon Trifonov (generated) 
 *  @version Release 3.3.1t
 */
public interface I_M_OperationResource 
{

    /** TableName=M_OperationResource */
    public static final String Table_Name = "M_OperationResource";

    /** AD_Table_ID=797 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

    /** Load Meta Data */

    /** Column name A_Asset_ID */
    public static final String COLUMNNAME_A_Asset_ID = "A_Asset_ID";

	/** Set Asset.
	  * Asset used internally or by customers
	  */
	public void setA_Asset_ID (int A_Asset_ID);

	/** Get Asset.
	  * Asset used internally or by customers
	  */
	public int getA_Asset_ID();

	public I_A_Asset getA_Asset() throws Exception;

    /** Column name C_Job_ID */
    public static final String COLUMNNAME_C_Job_ID = "C_Job_ID";

	/** Set Position.
	  * Job Position
	  */
	public void setC_Job_ID (int C_Job_ID);

	/** Get Position.
	  * Job Position
	  */
	public int getC_Job_ID();

	public I_C_Job getC_Job() throws Exception;

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

    /** Column name M_OperationResource_ID */
    public static final String COLUMNNAME_M_OperationResource_ID = "M_OperationResource_ID";

	/** Set Operation Resource.
	  * Product Operation Resource
	  */
	public void setM_OperationResource_ID (int M_OperationResource_ID);

	/** Get Operation Resource.
	  * Product Operation Resource
	  */
	public int getM_OperationResource_ID();

    /** Column name M_ProductOperation_ID */
    public static final String COLUMNNAME_M_ProductOperation_ID = "M_ProductOperation_ID";

	/** Set Product Operation.
	  * Product Manufacturing Operation
	  */
	public void setM_ProductOperation_ID (int M_ProductOperation_ID);

	/** Get Product Operation.
	  * Product Manufacturing Operation
	  */
	public int getM_ProductOperation_ID();

	public I_M_ProductOperation getM_ProductOperation() throws Exception;

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

    /** Column name SetupTime */
    public static final String COLUMNNAME_SetupTime = "SetupTime";

	/** Set Setup Time.
	  * Setup time before starting Production
	  */
	public void setSetupTime (BigDecimal SetupTime);

	/** Get Setup Time.
	  * Setup time before starting Production
	  */
	public BigDecimal getSetupTime();

    /** Column name TeardownTime */
    public static final String COLUMNNAME_TeardownTime = "TeardownTime";

	/** Set Teardown Time.
	  * Time at the end of the operation
	  */
	public void setTeardownTime (BigDecimal TeardownTime);

	/** Get Teardown Time.
	  * Time at the end of the operation
	  */
	public BigDecimal getTeardownTime();

    /** Column name UnitRuntime */
    public static final String COLUMNNAME_UnitRuntime = "UnitRuntime";

	/** Set Runtime per Unit.
	  * Time to produce one unit
	  */
	public void setUnitRuntime (BigDecimal UnitRuntime);

	/** Get Runtime per Unit.
	  * Time to produce one unit
	  */
	public BigDecimal getUnitRuntime();
}
