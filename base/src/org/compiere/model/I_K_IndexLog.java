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

/** Generated Interface for K_IndexLog
 *  @author Trifon Trifonov (generated) 
 *  @version Release 3.3.1t
 */
public interface I_K_IndexLog 
{

    /** TableName=K_IndexLog */
    public static final String Table_Name = "K_IndexLog";

    /** AD_Table_ID=899 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 6 - System - Client 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(6);

    /** Load Meta Data */

    /** Column name IndexQuery */
    public static final String COLUMNNAME_IndexQuery = "IndexQuery";

	/** Set Index Query.
	  * Text Search Query 
	  */
	public void setIndexQuery (String IndexQuery);

	/** Get Index Query.
	  * Text Search Query 
	  */
	public String getIndexQuery();

    /** Column name IndexQueryResult */
    public static final String COLUMNNAME_IndexQueryResult = "IndexQueryResult";

	/** Set Query Result.
	  * Result of the text query
	  */
	public void setIndexQueryResult (int IndexQueryResult);

	/** Get Query Result.
	  * Result of the text query
	  */
	public int getIndexQueryResult();

    /** Column name K_IndexLog_ID */
    public static final String COLUMNNAME_K_IndexLog_ID = "K_IndexLog_ID";

	/** Set Index Log.
	  * Text search log
	  */
	public void setK_IndexLog_ID (int K_IndexLog_ID);

	/** Get Index Log.
	  * Text search log
	  */
	public int getK_IndexLog_ID();

    /** Column name QuerySource */
    public static final String COLUMNNAME_QuerySource = "QuerySource";

	/** Set Query Source.
	  * Source of the Query
	  */
	public void setQuerySource (String QuerySource);

	/** Get Query Source.
	  * Source of the Query
	  */
	public String getQuerySource();
}
