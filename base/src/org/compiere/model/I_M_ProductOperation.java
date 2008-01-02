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

/** Generated Interface for M_ProductOperation
 *  @author Trifon Trifonov (generated) 
 *  @version Release 3.3.1t
 */
public interface I_M_ProductOperation 
{

    /** TableName=M_ProductOperation */
    public static final String Table_Name = "M_ProductOperation";

    /** AD_Table_ID=796 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

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

	public I_M_Product getM_Product() throws Exception;

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
