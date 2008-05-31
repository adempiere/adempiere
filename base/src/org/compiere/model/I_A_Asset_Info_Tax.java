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

/** Generated Interface for A_Asset_Info_Tax
 *  @author Trifon Trifonov (generated) 
 *  @version Release 3.5.1a
 */
public interface I_A_Asset_Info_Tax 
{

    /** TableName=A_Asset_Info_Tax */
    public static final String Table_Name = "A_Asset_Info_Tax";

    /** AD_Table_ID=53131 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 7 - System - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(7);

    /** Load Meta Data */

    /** Column name A_Asset_ID */
    public static final String COLUMNNAME_A_Asset_ID = "A_Asset_ID";

	/** Set Asset.
	  * Asset used internally or by customers
	  */
	public void setA_Asset_ID (int A_Asset_ID);

	/** Get Asset.
	  * Asset used internally or by customers
	  */
	public int getA_Asset_ID();

    /** Column name A_Asset_Info_Tax_ID */
    public static final String COLUMNNAME_A_Asset_Info_Tax_ID = "A_Asset_Info_Tax_ID";

	/** Set A_Asset_Info_Tax_ID	  */
	public void setA_Asset_Info_Tax_ID (int A_Asset_Info_Tax_ID);

	/** Get A_Asset_Info_Tax_ID	  */
	public int getA_Asset_Info_Tax_ID();

    /** Column name A_Finance_Meth */
    public static final String COLUMNNAME_A_Finance_Meth = "A_Finance_Meth";

	/** Set A_Finance_Meth	  */
	public void setA_Finance_Meth (String A_Finance_Meth);

	/** Get A_Finance_Meth	  */
	public String getA_Finance_Meth();

    /** Column name A_Investment_CR */
    public static final String COLUMNNAME_A_Investment_CR = "A_Investment_CR";

	/** Set A_Investment_CR	  */
	public void setA_Investment_CR (int A_Investment_CR);

	/** Get A_Investment_CR	  */
	public int getA_Investment_CR();

    /** Column name A_New_Used */
    public static final String COLUMNNAME_A_New_Used = "A_New_Used";

	/** Set A_New_Used	  */
	public void setA_New_Used (boolean A_New_Used);

	/** Get A_New_Used	  */
	public boolean isA_New_Used();

    /** Column name A_State */
    public static final String COLUMNNAME_A_State = "A_State";

	/** Set Account State.
	  * State of the Credit Card or Account holder
	  */
	public void setA_State (String A_State);

	/** Get Account State.
	  * State of the Credit Card or Account holder
	  */
	public String getA_State();

    /** Column name A_Tax_Entity */
    public static final String COLUMNNAME_A_Tax_Entity = "A_Tax_Entity";

	/** Set A_Tax_Entity	  */
	public void setA_Tax_Entity (String A_Tax_Entity);

	/** Get A_Tax_Entity	  */
	public String getA_Tax_Entity();

    /** Column name TextMsg */
    public static final String COLUMNNAME_TextMsg = "TextMsg";

	/** Set Text Message.
	  * Text Message
	  */
	public void setTextMsg (String TextMsg);

	/** Get Text Message.
	  * Text Message
	  */
	public String getTextMsg();
}
