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

/** Generated Interface for M_AttributeUse
 *  @author Adempiere (generated) 
 *  @version Release 3.5.3a
 */
public interface I_M_AttributeUse 
{

    /** TableName=M_AttributeUse */
    public static final String Table_Name = "M_AttributeUse";

    /** AD_Table_ID=563 */
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

    /** Column name M_Attribute_ID */
    public static final String COLUMNNAME_M_Attribute_ID = "M_Attribute_ID";

	/** Set Attribute.
	  * Product Attribute
	  */
	public void setM_Attribute_ID (int M_Attribute_ID);

	/** Get Attribute.
	  * Product Attribute
	  */
	public int getM_Attribute_ID();

	public I_M_Attribute getM_Attribute() throws RuntimeException;

    /** Column name M_AttributeSet_ID */
    public static final String COLUMNNAME_M_AttributeSet_ID = "M_AttributeSet_ID";

	/** Set Attribute Set.
	  * Product Attribute Set
	  */
	public void setM_AttributeSet_ID (int M_AttributeSet_ID);

	/** Get Attribute Set.
	  * Product Attribute Set
	  */
	public int getM_AttributeSet_ID();

	public I_M_AttributeSet getM_AttributeSet() throws RuntimeException;

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
