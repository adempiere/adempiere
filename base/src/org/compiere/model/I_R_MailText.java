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

/** Generated Interface for R_MailText
 *  @author Trifon Trifonov (generated) 
 *  @version Release 3.5.1a
 */
public interface I_R_MailText 
{

    /** TableName=R_MailText */
    public static final String Table_Name = "R_MailText";

    /** AD_Table_ID=416 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 7 - System - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(7);

    /** Load Meta Data */

    /** Column name IsHtml */
    public static final String COLUMNNAME_IsHtml = "IsHtml";

	/** Set HTML.
	  * Text has HTML tags
	  */
	public void setIsHtml (boolean IsHtml);

	/** Get HTML.
	  * Text has HTML tags
	  */
	public boolean isHtml();

    /** Column name MailHeader */
    public static final String COLUMNNAME_MailHeader = "MailHeader";

	/** Set Subject.
	  * Mail Header (Subject)
	  */
	public void setMailHeader (String MailHeader);

	/** Get Subject.
	  * Mail Header (Subject)
	  */
	public String getMailHeader();

    /** Column name MailText */
    public static final String COLUMNNAME_MailText = "MailText";

	/** Set Mail Text.
	  * Text used for Mail message
	  */
	public void setMailText (String MailText);

	/** Get Mail Text.
	  * Text used for Mail message
	  */
	public String getMailText();

    /** Column name MailText2 */
    public static final String COLUMNNAME_MailText2 = "MailText2";

	/** Set Mail Text 2.
	  * Optional second text part used for Mail message
	  */
	public void setMailText2 (String MailText2);

	/** Get Mail Text 2.
	  * Optional second text part used for Mail message
	  */
	public String getMailText2();

    /** Column name MailText3 */
    public static final String COLUMNNAME_MailText3 = "MailText3";

	/** Set Mail Text 3.
	  * Optional third text part used for Mail message
	  */
	public void setMailText3 (String MailText3);

	/** Get Mail Text 3.
	  * Optional third text part used for Mail message
	  */
	public String getMailText3();

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

    /** Column name R_MailText_ID */
    public static final String COLUMNNAME_R_MailText_ID = "R_MailText_ID";

	/** Set Mail Template.
	  * Text templates for mailings
	  */
	public void setR_MailText_ID (int R_MailText_ID);

	/** Get Mail Template.
	  * Text templates for mailings
	  */
	public int getR_MailText_ID();
}
