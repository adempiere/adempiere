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
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for AD_PInstance_Para
 *  @author Trifon Trifonov (generated) 
 *  @version Release 3.3.1t
 */
public interface I_AD_PInstance_Para 
{

    /** TableName=AD_PInstance_Para */
    public static final String Table_Name = "AD_PInstance_Para";

    /** AD_Table_ID=283 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 6 - System - Client 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(6);

    /** Load Meta Data */

    /** Column name AD_PInstance_ID */
    public static final String COLUMNNAME_AD_PInstance_ID = "AD_PInstance_ID";

	/** Set Process Instance.
	  * Instance of the process
	  */
	public void setAD_PInstance_ID (int AD_PInstance_ID);

	/** Get Process Instance.
	  * Instance of the process
	  */
	public int getAD_PInstance_ID();

	public I_AD_PInstance getAD_PInstance() throws Exception;

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

    /** Column name Info_To */
    public static final String COLUMNNAME_Info_To = "Info_To";

	/** Set Info To	  */
	public void setInfo_To (String Info_To);

	/** Get Info To	  */
	public String getInfo_To();

    /** Column name P_Date */
    public static final String COLUMNNAME_P_Date = "P_Date";

	/** Set Process Date.
	  * Process Parameter
	  */
	public void setP_Date (Timestamp P_Date);

	/** Get Process Date.
	  * Process Parameter
	  */
	public Timestamp getP_Date();

    /** Column name P_Date_To */
    public static final String COLUMNNAME_P_Date_To = "P_Date_To";

	/** Set Process Date To.
	  * Process Parameter
	  */
	public void setP_Date_To (Timestamp P_Date_To);

	/** Get Process Date To.
	  * Process Parameter
	  */
	public Timestamp getP_Date_To();

    /** Column name P_Number */
    public static final String COLUMNNAME_P_Number = "P_Number";

	/** Set Process Number.
	  * Process Parameter
	  */
	public void setP_Number (BigDecimal P_Number);

	/** Get Process Number.
	  * Process Parameter
	  */
	public BigDecimal getP_Number();

    /** Column name P_Number_To */
    public static final String COLUMNNAME_P_Number_To = "P_Number_To";

	/** Set Process Number To.
	  * Process Parameter
	  */
	public void setP_Number_To (BigDecimal P_Number_To);

	/** Get Process Number To.
	  * Process Parameter
	  */
	public BigDecimal getP_Number_To();

    /** Column name P_String */
    public static final String COLUMNNAME_P_String = "P_String";

	/** Set Process String.
	  * Process Parameter
	  */
	public void setP_String (String P_String);

	/** Get Process String.
	  * Process Parameter
	  */
	public String getP_String();

    /** Column name P_String_To */
    public static final String COLUMNNAME_P_String_To = "P_String_To";

	/** Set Process String To.
	  * Process Parameter
	  */
	public void setP_String_To (String P_String_To);

	/** Get Process String To.
	  * Process Parameter
	  */
	public String getP_String_To();

    /** Column name ParameterName */
    public static final String COLUMNNAME_ParameterName = "ParameterName";

	/** Set Parameter Name	  */
	public void setParameterName (String ParameterName);

	/** Get Parameter Name	  */
	public String getParameterName();

    /** Column name SeqNo */
    public static final String COLUMNNAME_SeqNo = "SeqNo";

	/** Set Sequence.
	  * Method of ordering records;
 lowest number comes first
	  */
	public void setSeqNo (int SeqNo);

	/** Get Sequence.
	  * Method of ordering records;
 lowest number comes first
	  */
	public int getSeqNo();
}
