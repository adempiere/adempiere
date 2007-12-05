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

/** Generated Interface for M_EDI_Info
 *  @author Trifon Trifonov (generated) 
 *  @version Release 3.3.1b
 */
public interface I_M_EDI_Info 
{

    /** TableName=M_EDI_Info */
    public static final String Table_Name = "M_EDI_Info";

    /** AD_Table_ID=368 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

    /** Load Meta Data */

    /** Column name Info */
    public static final String COLUMNNAME_Info = "Info";

	/** Set Info.
	  * Information
	  */
	public void setInfo (String Info);

	/** Get Info.
	  * Information
	  */
	public String getInfo();

    /** Column name M_EDI_ID */
    public static final String COLUMNNAME_M_EDI_ID = "M_EDI_ID";

	/** Set EDI Transaction	  */
	public void setM_EDI_ID (int M_EDI_ID);

	/** Get EDI Transaction	  */
	public int getM_EDI_ID();

	public I_M_EDI getM_EDI() throws Exception;

    /** Column name M_EDI_Info_ID */
    public static final String COLUMNNAME_M_EDI_Info_ID = "M_EDI_Info_ID";

	/** Set EDI Log	  */
	public void setM_EDI_Info_ID (int M_EDI_Info_ID);

	/** Get EDI Log	  */
	public int getM_EDI_Info_ID();
}
