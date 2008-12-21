/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2007 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software;
 you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY;
 without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program;
 if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for K_IndexLog
 *  @author Adempiere (generated) 
 *  @version Release 3.5.3a
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

    /** Column name AD_Org_ID */
    public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

	/** Set Organization.
	  * Organizational entity within client
	  */
	public void setAD_Org_ID (int AD_Org_ID);

	/** Get Organization.
	  * Organizational entity within client
	  */
	public int getAD_Org_ID();

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
