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

    /** Generated Interface for AD_Sequence
     *  @author Trifon Trifonov (generated) 
     *  @version Release 3.3.0 - 2007-08-24 11:39:27.984
     */
    public interface I_AD_Sequence 
{

    /** TableName=AD_Sequence */
    public static final String Table_Name = "AD_Sequence";

    /** AD_Table_ID=115 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 6 - System - Client 
     */
    BigDecimal accessLevel = new BigDecimal(6);

    /** Load Meta Data */

    /** Column name AD_Sequence_ID */
    public static final String COLUMNNAME_AD_Sequence_ID = "AD_Sequence_ID";

	/** Set Sequence.
	  * Document Sequence
	  */
	public void setAD_Sequence_ID (int AD_Sequence_ID);

	/** Get Sequence.
	  * Document Sequence
	  */
	public int getAD_Sequence_ID();

    /** Column name CurrentNext */
    public static final String COLUMNNAME_CurrentNext = "CurrentNext";

	/** Set Current Next.
	  * The next number to be used
	  */
	public void setCurrentNext (int CurrentNext);

	/** Get Current Next.
	  * The next number to be used
	  */
	public int getCurrentNext();

    /** Column name CurrentNextSys */
    public static final String COLUMNNAME_CurrentNextSys = "CurrentNextSys";

	/** Set Current Next (System).
	  * Next sequence for system use
	  */
	public void setCurrentNextSys (int CurrentNextSys);

	/** Get Current Next (System).
	  * Next sequence for system use
	  */
	public int getCurrentNextSys();

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

    /** Column name IncrementNo */
    public static final String COLUMNNAME_IncrementNo = "IncrementNo";

	/** Set Increment.
	  * The number to increment the last document number by
	  */
	public void setIncrementNo (int IncrementNo);

	/** Get Increment.
	  * The number to increment the last document number by
	  */
	public int getIncrementNo();

    /** Column name IsAudited */
    public static final String COLUMNNAME_IsAudited = "IsAudited";

	/** Set Activate Audit.
	  * Activate Audit Trail of what numbers are generated
	  */
	public void setIsAudited (boolean IsAudited);

	/** Get Activate Audit.
	  * Activate Audit Trail of what numbers are generated
	  */
	public boolean isAudited();

    /** Column name IsAutoSequence */
    public static final String COLUMNNAME_IsAutoSequence = "IsAutoSequence";

	/** Set Auto numbering.
	  * Automatically assign the next number
	  */
	public void setIsAutoSequence (boolean IsAutoSequence);

	/** Get Auto numbering.
	  * Automatically assign the next number
	  */
	public boolean isAutoSequence();

    /** Column name IsTableID */
    public static final String COLUMNNAME_IsTableID = "IsTableID";

	/** Set Used for Record ID.
	  * The document number  will be used as the record key
	  */
	public void setIsTableID (boolean IsTableID);

	/** Get Used for Record ID.
	  * The document number  will be used as the record key
	  */
	public boolean isTableID();

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

    /** Column name Prefix */
    public static final String COLUMNNAME_Prefix = "Prefix";

	/** Set Prefix.
	  * Prefix before the sequence number
	  */
	public void setPrefix (String Prefix);

	/** Get Prefix.
	  * Prefix before the sequence number
	  */
	public String getPrefix();

    /** Column name StartNewYear */
    public static final String COLUMNNAME_StartNewYear = "StartNewYear";

	/** Set Restart sequence every Year.
	  * Restart the sequence with Start on every 1/1
	  */
	public void setStartNewYear (boolean StartNewYear);

	/** Get Restart sequence every Year.
	  * Restart the sequence with Start on every 1/1
	  */
	public boolean isStartNewYear();

    /** Column name StartNo */
    public static final String COLUMNNAME_StartNo = "StartNo";

	/** Set Start No.
	  * Starting number/position
	  */
	public void setStartNo (int StartNo);

	/** Get Start No.
	  * Starting number/position
	  */
	public int getStartNo();

    /** Column name Suffix */
    public static final String COLUMNNAME_Suffix = "Suffix";

	/** Set Suffix.
	  * Suffix after the number
	  */
	public void setSuffix (String Suffix);

	/** Get Suffix.
	  * Suffix after the number
	  */
	public String getSuffix();

    /** Column name VFormat */
    public static final String COLUMNNAME_VFormat = "VFormat";

	/** Set Value Format.
	  * Format of the value;
 Can contain fixed format elements, Variables: "_lLoOaAcCa09"
	  */
	public void setVFormat (String VFormat);

	/** Get Value Format.
	  * Format of the value;
 Can contain fixed format elements, Variables: "_lLoOaAcCa09"
	  */
	public String getVFormat();
}
