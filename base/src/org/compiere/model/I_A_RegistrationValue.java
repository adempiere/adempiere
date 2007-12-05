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

/** Generated Interface for A_RegistrationValue
 *  @author Trifon Trifonov (generated) 
 *  @version Release 3.3.1b
 */
public interface I_A_RegistrationValue 
{

    /** TableName=A_RegistrationValue */
    public static final String Table_Name = "A_RegistrationValue";

    /** AD_Table_ID=653 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

    /** Load Meta Data */

    /** Column name A_RegistrationAttribute_ID */
    public static final String COLUMNNAME_A_RegistrationAttribute_ID = "A_RegistrationAttribute_ID";

	/** Set Registration Attribute.
	  * Asset Registration Attribute
	  */
	public void setA_RegistrationAttribute_ID (int A_RegistrationAttribute_ID);

	/** Get Registration Attribute.
	  * Asset Registration Attribute
	  */
	public int getA_RegistrationAttribute_ID();

	public I_A_RegistrationAttribute getA_RegistrationAttribute() throws Exception;

    /** Column name A_Registration_ID */
    public static final String COLUMNNAME_A_Registration_ID = "A_Registration_ID";

	/** Set Registration.
	  * User Asset Registration
	  */
	public void setA_Registration_ID (int A_Registration_ID);

	/** Get Registration.
	  * User Asset Registration
	  */
	public int getA_Registration_ID();

	public I_A_Registration getA_Registration() throws Exception;

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
}
