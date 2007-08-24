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

    /** Generated Interface for M_DistributionListLine
     *  @author Trifon Trifonov (generated) 
     *  @version Release 3.3.0 - 2007-08-24 11:39:49.828
     */
    public interface I_M_DistributionListLine 
{

    /** TableName=M_DistributionListLine */
    public static final String Table_Name = "M_DistributionListLine";

    /** AD_Table_ID=665 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = new BigDecimal(3);

    /** Load Meta Data */

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

    /** Column name C_BPartner_Location_ID */
    public static final String COLUMNNAME_C_BPartner_Location_ID = "C_BPartner_Location_ID";

	/** Set Partner Location.
	  * Identifies the (ship to) address for this Business Partner
	  */
	public void setC_BPartner_Location_ID (int C_BPartner_Location_ID);

	/** Get Partner Location.
	  * Identifies the (ship to) address for this Business Partner
	  */
	public int getC_BPartner_Location_ID();

	public I_C_BPartner_Location getI_C_BPartner_Location() throws Exception;

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

    /** Column name M_DistributionListLine_ID */
    public static final String COLUMNNAME_M_DistributionListLine_ID = "M_DistributionListLine_ID";

	/** Set Distribution List Line.
	  * Distribution List Line with Business Partner and Quantity/Percentage
	  */
	public void setM_DistributionListLine_ID (int M_DistributionListLine_ID);

	/** Get Distribution List Line.
	  * Distribution List Line with Business Partner and Quantity/Percentage
	  */
	public int getM_DistributionListLine_ID();

    /** Column name M_DistributionList_ID */
    public static final String COLUMNNAME_M_DistributionList_ID = "M_DistributionList_ID";

	/** Set Distribution List.
	  * Distribution Lists allow to distribute products to a selected list of partners
	  */
	public void setM_DistributionList_ID (int M_DistributionList_ID);

	/** Get Distribution List.
	  * Distribution Lists allow to distribute products to a selected list of partners
	  */
	public int getM_DistributionList_ID();

	public I_M_DistributionList getI_M_DistributionList() throws Exception;

    /** Column name MinQty */
    public static final String COLUMNNAME_MinQty = "MinQty";

	/** Set Minimum Quantity.
	  * Minimum quantity for the business partner
	  */
	public void setMinQty (BigDecimal MinQty);

	/** Get Minimum Quantity.
	  * Minimum quantity for the business partner
	  */
	public BigDecimal getMinQty();

    /** Column name Ratio */
    public static final String COLUMNNAME_Ratio = "Ratio";

	/** Set Ratio.
	  * Relative Ratio for Distributions
	  */
	public void setRatio (BigDecimal Ratio);

	/** Get Ratio.
	  * Relative Ratio for Distributions
	  */
	public BigDecimal getRatio();
}
