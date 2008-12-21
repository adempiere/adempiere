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

/** Generated Interface for K_EntryCategory
 *  @author Adempiere (generated) 
 *  @version Release 3.5.3a
 */
public interface I_K_EntryCategory 
{

    /** TableName=K_EntryCategory */
    public static final String Table_Name = "K_EntryCategory";

    /** AD_Table_ID=611 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

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

    /** Column name K_Category_ID */
    public static final String COLUMNNAME_K_Category_ID = "K_Category_ID";

	/** Set Knowledge Category.
	  * Knowledge Category
	  */
	public void setK_Category_ID (int K_Category_ID);

	/** Get Knowledge Category.
	  * Knowledge Category
	  */
	public int getK_Category_ID();

	public I_K_Category getK_Category() throws RuntimeException;

    /** Column name K_CategoryValue_ID */
    public static final String COLUMNNAME_K_CategoryValue_ID = "K_CategoryValue_ID";

	/** Set Category Value.
	  * The value of the category
	  */
	public void setK_CategoryValue_ID (int K_CategoryValue_ID);

	/** Get Category Value.
	  * The value of the category
	  */
	public int getK_CategoryValue_ID();

	public I_K_CategoryValue getK_CategoryValue() throws RuntimeException;

    /** Column name K_Entry_ID */
    public static final String COLUMNNAME_K_Entry_ID = "K_Entry_ID";

	/** Set Entry.
	  * Knowledge Entry
	  */
	public void setK_Entry_ID (int K_Entry_ID);

	/** Get Entry.
	  * Knowledge Entry
	  */
	public int getK_Entry_ID();

	public I_K_Entry getK_Entry() throws RuntimeException;
}
