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

/** Generated Interface for WS_WebService_Para
 *  @author Trifon Trifonov (generated) 
 *  @version Release 3.4.2s
 */
public interface I_WS_WebService_Para 
{

    /** TableName=WS_WebService_Para */
    public static final String Table_Name = "WS_WebService_Para";

    /** AD_Table_ID=53165 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 6 - System - Client 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(6);

    /** Load Meta Data */

    /** Column name ConstantValue */
    public static final String COLUMNNAME_ConstantValue = "ConstantValue";

	/** Set Constant Value.
	  * Constant value
	  */
	public void setConstantValue (String ConstantValue);

	/** Get Constant Value.
	  * Constant value
	  */
	public String getConstantValue();

    /** Column name ParameterName */
    public static final String COLUMNNAME_ParameterName = "ParameterName";

	/** Set Parameter Name	  */
	public void setParameterName (String ParameterName);

	/** Get Parameter Name	  */
	public String getParameterName();

    /** Column name ParameterType */
    public static final String COLUMNNAME_ParameterType = "ParameterType";

	/** Set Parameter Type	  */
	public void setParameterType (String ParameterType);

	/** Get Parameter Type	  */
	public String getParameterType();

    /** Column name WS_WebService_Para_ID */
    public static final String COLUMNNAME_WS_WebService_Para_ID = "WS_WebService_Para_ID";

	/** Set Web Service Parameters	  */
	public void setWS_WebService_Para_ID (int WS_WebService_Para_ID);

	/** Get Web Service Parameters	  */
	public int getWS_WebService_Para_ID();

    /** Column name WS_WebServiceType_ID */
    public static final String COLUMNNAME_WS_WebServiceType_ID = "WS_WebServiceType_ID";

	/** Set Web Service Type	  */
	public void setWS_WebServiceType_ID (int WS_WebServiceType_ID);

	/** Get Web Service Type	  */
	public int getWS_WebServiceType_ID();

	public I_WS_WebServiceType getWS_WebServiceType() throws Exception;
}
