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

/** Generated Interface for AD_Sequence_No
 *  @author Trifon Trifonov (generated) 
 *  @version Release 3.4.0s
 */
public interface I_AD_Sequence_No 
{

    /** TableName=AD_Sequence_No */
    public static final String Table_Name = "AD_Sequence_No";

    /** AD_Table_ID=122 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 6 - System - Client 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(6);

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

	public I_AD_Sequence getAD_Sequence() throws Exception;

    /** Column name CalendarYear */
    public static final String COLUMNNAME_CalendarYear = "CalendarYear";

	/** Set Year.
	  * Calendar Year
	  */
	public void setCalendarYear (String CalendarYear);

	/** Get Year.
	  * Calendar Year
	  */
	public String getCalendarYear();

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
}
