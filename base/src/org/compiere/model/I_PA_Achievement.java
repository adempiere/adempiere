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

/** Generated Interface for PA_Achievement
 *  @author Trifon Trifonov (generated) 
 *  @version Release 3.3.1t
 */
public interface I_PA_Achievement 
{

    /** TableName=PA_Achievement */
    public static final String Table_Name = "PA_Achievement";

    /** AD_Table_ID=438 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 6 - System - Client 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(6);

    /** Load Meta Data */

    /** Column name DateDoc */
    public static final String COLUMNNAME_DateDoc = "DateDoc";

	/** Set Document Date.
	  * Date of the Document
	  */
	public void setDateDoc (Timestamp DateDoc);

	/** Get Document Date.
	  * Date of the Document
	  */
	public Timestamp getDateDoc();

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

    /** Column name IsAchieved */
    public static final String COLUMNNAME_IsAchieved = "IsAchieved";

	/** Set Achieved.
	  * The goal is achieved
	  */
	public void setIsAchieved (boolean IsAchieved);

	/** Get Achieved.
	  * The goal is achieved
	  */
	public boolean isAchieved();

    /** Column name ManualActual */
    public static final String COLUMNNAME_ManualActual = "ManualActual";

	/** Set Manual Actual.
	  * Manually entered actual value
	  */
	public void setManualActual (BigDecimal ManualActual);

	/** Get Manual Actual.
	  * Manually entered actual value
	  */
	public BigDecimal getManualActual();

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

    /** Column name Note */
    public static final String COLUMNNAME_Note = "Note";

	/** Set Note.
	  * Optional additional user defined information
	  */
	public void setNote (String Note);

	/** Get Note.
	  * Optional additional user defined information
	  */
	public String getNote();

    /** Column name PA_Achievement_ID */
    public static final String COLUMNNAME_PA_Achievement_ID = "PA_Achievement_ID";

	/** Set Achievement.
	  * Performance Achievement
	  */
	public void setPA_Achievement_ID (int PA_Achievement_ID);

	/** Get Achievement.
	  * Performance Achievement
	  */
	public int getPA_Achievement_ID();

    /** Column name PA_Measure_ID */
    public static final String COLUMNNAME_PA_Measure_ID = "PA_Measure_ID";

	/** Set Measure.
	  * Concrete Performance Measurement
	  */
	public void setPA_Measure_ID (int PA_Measure_ID);

	/** Get Measure.
	  * Concrete Performance Measurement
	  */
	public int getPA_Measure_ID();

	public I_PA_Measure getPA_Measure() throws Exception;

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
