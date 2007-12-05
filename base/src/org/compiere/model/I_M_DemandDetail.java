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

/** Generated Interface for M_DemandDetail
 *  @author Trifon Trifonov (generated) 
 *  @version Release 3.3.1b
 */
public interface I_M_DemandDetail 
{

    /** TableName=M_DemandDetail */
    public static final String Table_Name = "M_DemandDetail";

    /** AD_Table_ID=721 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 2 - Client 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(2);

    /** Load Meta Data */

    /** Column name C_OrderLine_ID */
    public static final String COLUMNNAME_C_OrderLine_ID = "C_OrderLine_ID";

	/** Set Sales Order Line.
	  * Sales Order Line
	  */
	public void setC_OrderLine_ID (int C_OrderLine_ID);

	/** Get Sales Order Line.
	  * Sales Order Line
	  */
	public int getC_OrderLine_ID();

	public I_C_OrderLine getC_OrderLine() throws Exception;

    /** Column name M_DemandDetail_ID */
    public static final String COLUMNNAME_M_DemandDetail_ID = "M_DemandDetail_ID";

	/** Set Demand Detail.
	  * Material Demand Line Source Detail
	  */
	public void setM_DemandDetail_ID (int M_DemandDetail_ID);

	/** Get Demand Detail.
	  * Material Demand Line Source Detail
	  */
	public int getM_DemandDetail_ID();

    /** Column name M_DemandLine_ID */
    public static final String COLUMNNAME_M_DemandLine_ID = "M_DemandLine_ID";

	/** Set Demand Line.
	  * Material Demand Line
	  */
	public void setM_DemandLine_ID (int M_DemandLine_ID);

	/** Get Demand Line.
	  * Material Demand Line
	  */
	public int getM_DemandLine_ID();

	public I_M_DemandLine getM_DemandLine() throws Exception;

    /** Column name M_ForecastLine_ID */
    public static final String COLUMNNAME_M_ForecastLine_ID = "M_ForecastLine_ID";

	/** Set Forecast Line.
	  * Forecast Line
	  */
	public void setM_ForecastLine_ID (int M_ForecastLine_ID);

	/** Get Forecast Line.
	  * Forecast Line
	  */
	public int getM_ForecastLine_ID();

	public I_M_ForecastLine getM_ForecastLine() throws Exception;

    /** Column name M_RequisitionLine_ID */
    public static final String COLUMNNAME_M_RequisitionLine_ID = "M_RequisitionLine_ID";

	/** Set Requisition Line.
	  * Material Requisition Line
	  */
	public void setM_RequisitionLine_ID (int M_RequisitionLine_ID);

	/** Get Requisition Line.
	  * Material Requisition Line
	  */
	public int getM_RequisitionLine_ID();

	public I_M_RequisitionLine getM_RequisitionLine() throws Exception;
}
