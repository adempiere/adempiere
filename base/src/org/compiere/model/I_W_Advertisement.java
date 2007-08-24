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

    /** Generated Interface for W_Advertisement
     *  @author Trifon Trifonov (generated) 
     *  @version Release 3.3.0 - 2007-08-24 11:39:56.328
     */
    public interface I_W_Advertisement 
{

    /** TableName=W_Advertisement */
    public static final String Table_Name = "W_Advertisement";

    /** AD_Table_ID=579 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = new BigDecimal(3);

    /** Load Meta Data */

    /** Column name AD_User_ID */
    public static final String COLUMNNAME_AD_User_ID = "AD_User_ID";

	/** Set User/Contact.
	  * User within the system - Internal or Business Partner Contact
	  */
	public void setAD_User_ID (int AD_User_ID);

	/** Get User/Contact.
	  * User within the system - Internal or Business Partner Contact
	  */
	public int getAD_User_ID();

	public I_AD_User getI_AD_User() throws Exception;

    /** Column name AdText */
    public static final String COLUMNNAME_AdText = "AdText";

	/** Set Advertisement Text.
	  * Text of the Advertisement
	  */
	public void setAdText (String AdText);

	/** Get Advertisement Text.
	  * Text of the Advertisement
	  */
	public String getAdText();

    /** Column name C_BPartner_ID */
    public static final String COLUMNNAME_C_BPartner_ID = "C_BPartner_ID";

	/** Set Business Partner .
	  * Identifies a Business Partner
	  */
	public void setC_BPartner_ID (int C_BPartner_ID);

	/** Get Business Partner .
	  * Identifies a Business Partner
	  */
	public int getC_BPartner_ID();

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

    /** Column name Help */
    public static final String COLUMNNAME_Help = "Help";

	/** Set Comment/Help.
	  * Comment or Hint
	  */
	public void setHelp (String Help);

	/** Get Comment/Help.
	  * Comment or Hint
	  */
	public String getHelp();

    /** Column name ImageURL */
    public static final String COLUMNNAME_ImageURL = "ImageURL";

	/** Set Image URL.
	  * URL of  image
	  */
	public void setImageURL (String ImageURL);

	/** Get Image URL.
	  * URL of  image
	  */
	public String getImageURL();

    /** Column name IsSelfService */
    public static final String COLUMNNAME_IsSelfService = "IsSelfService";

	/** Set Self-Service.
	  * This is a Self-Service entry or this entry can be changed via Self-Service
	  */
	public void setIsSelfService (boolean IsSelfService);

	/** Get Self-Service.
	  * This is a Self-Service entry or this entry can be changed via Self-Service
	  */
	public boolean isSelfService();

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

    /** Column name Processing */
    public static final String COLUMNNAME_Processing = "Processing";

	/** Set Process Now	  */
	public void setProcessing (boolean Processing);

	/** Get Process Now	  */
	public boolean isProcessing();

    /** Column name PublishStatus */
    public static final String COLUMNNAME_PublishStatus = "PublishStatus";

	/** Set Publication Status.
	  * Status of Publication
	  */
	public void setPublishStatus (String PublishStatus);

	/** Get Publication Status.
	  * Status of Publication
	  */
	public String getPublishStatus();

    /** Column name ValidFrom */
    public static final String COLUMNNAME_ValidFrom = "ValidFrom";

	/** Set Valid from.
	  * Valid from including this date (first day)
	  */
	public void setValidFrom (Timestamp ValidFrom);

	/** Get Valid from.
	  * Valid from including this date (first day)
	  */
	public Timestamp getValidFrom();

    /** Column name ValidTo */
    public static final String COLUMNNAME_ValidTo = "ValidTo";

	/** Set Valid to.
	  * Valid to including this date (last day)
	  */
	public void setValidTo (Timestamp ValidTo);

	/** Get Valid to.
	  * Valid to including this date (last day)
	  */
	public Timestamp getValidTo();

    /** Column name Version */
    public static final String COLUMNNAME_Version = "Version";

	/** Set Version.
	  * Version of the table definition
	  */
	public void setVersion (int Version);

	/** Get Version.
	  * Version of the table definition
	  */
	public int getVersion();

    /** Column name W_Advertisement_ID */
    public static final String COLUMNNAME_W_Advertisement_ID = "W_Advertisement_ID";

	/** Set Advertisement.
	  * Web Advertisement
	  */
	public void setW_Advertisement_ID (int W_Advertisement_ID);

	/** Get Advertisement.
	  * Web Advertisement
	  */
	public int getW_Advertisement_ID();

    /** Column name W_ClickCount_ID */
    public static final String COLUMNNAME_W_ClickCount_ID = "W_ClickCount_ID";

	/** Set Click Count.
	  * Web Click Management
	  */
	public void setW_ClickCount_ID (int W_ClickCount_ID);

	/** Get Click Count.
	  * Web Click Management
	  */
	public int getW_ClickCount_ID();

	public I_W_ClickCount getI_W_ClickCount() throws Exception;

    /** Column name W_CounterCount_ID */
    public static final String COLUMNNAME_W_CounterCount_ID = "W_CounterCount_ID";

	/** Set Counter Count.
	  * Web Counter Count Management
	  */
	public void setW_CounterCount_ID (int W_CounterCount_ID);

	/** Get Counter Count.
	  * Web Counter Count Management
	  */
	public int getW_CounterCount_ID();

	public I_W_CounterCount getI_W_CounterCount() throws Exception;

    /** Column name WebParam1 */
    public static final String COLUMNNAME_WebParam1 = "WebParam1";

	/** Set Web Parameter 1.
	  * Web Site Parameter 1 (default: header image)
	  */
	public void setWebParam1 (String WebParam1);

	/** Get Web Parameter 1.
	  * Web Site Parameter 1 (default: header image)
	  */
	public String getWebParam1();

    /** Column name WebParam2 */
    public static final String COLUMNNAME_WebParam2 = "WebParam2";

	/** Set Web Parameter 2.
	  * Web Site Parameter 2 (default index page)
	  */
	public void setWebParam2 (String WebParam2);

	/** Get Web Parameter 2.
	  * Web Site Parameter 2 (default index page)
	  */
	public String getWebParam2();

    /** Column name WebParam3 */
    public static final String COLUMNNAME_WebParam3 = "WebParam3";

	/** Set Web Parameter 3.
	  * Web Site Parameter 3 (default left - menu)
	  */
	public void setWebParam3 (String WebParam3);

	/** Get Web Parameter 3.
	  * Web Site Parameter 3 (default left - menu)
	  */
	public String getWebParam3();

    /** Column name WebParam4 */
    public static final String COLUMNNAME_WebParam4 = "WebParam4";

	/** Set Web Parameter 4.
	  * Web Site Parameter 4 (default footer left)
	  */
	public void setWebParam4 (String WebParam4);

	/** Get Web Parameter 4.
	  * Web Site Parameter 4 (default footer left)
	  */
	public String getWebParam4();
}
