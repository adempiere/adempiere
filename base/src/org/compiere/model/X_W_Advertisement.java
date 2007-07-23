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

/** Generated Model - DO NOT CHANGE */
import java.util.*;
import java.sql.*;
import java.math.*;
import org.compiere.util.*;
/** Generated Model for W_Advertisement
 *  @author Adempiere (generated) 
 *  @version Release 3.3.0 - $Id$ */
public class X_W_Advertisement extends PO
{
/** Standard Constructor
@param ctx context
@param W_Advertisement_ID id
@param trxName transaction
*/
public X_W_Advertisement (Properties ctx, int W_Advertisement_ID, String trxName)
{
super (ctx, W_Advertisement_ID, trxName);
/** if (W_Advertisement_ID == 0)
{
setAD_User_ID (0);	// -1
setC_BPartner_ID (0);
setIsSelfService (true);	// Y
setName (null);
setPublishStatus (null);	// U
setW_Advertisement_ID (0);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_W_Advertisement (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** TableName=W_Advertisement */
public static final String Table_Name="W_Advertisement";

/** AD_Table_ID=579 */
public static final int Table_ID=MTable.getTable_ID(Table_Name);

protected static KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

protected BigDecimal accessLevel = BigDecimal.valueOf(3);
/** AccessLevel
@return 3 - Client - Org 
*/
protected int get_AccessLevel()
{
return accessLevel.intValue();
}
/** Load Meta Data
@param ctx context
@return PO Info
*/
protected POInfo initPO (Properties ctx)
{
POInfo poi = POInfo.getPOInfo (ctx, Table_ID);
return poi;
}
/** Info
@return info
*/
public String toString()
{
StringBuffer sb = new StringBuffer ("X_W_Advertisement[").append(get_ID()).append("]");
return sb.toString();
}
/** Set User/Contact.
@param AD_User_ID User within the system - Internal or Business Partner Contact */
public void setAD_User_ID (int AD_User_ID)
{
if (AD_User_ID < 1) throw new IllegalArgumentException ("AD_User_ID is mandatory.");
set_Value ("AD_User_ID", Integer.valueOf(AD_User_ID));
}
/** Get User/Contact.
@return User within the system - Internal or Business Partner Contact */
public int getAD_User_ID() 
{
Integer ii = (Integer)get_Value("AD_User_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name AD_User_ID */
public static final String COLUMNNAME_AD_User_ID = "AD_User_ID";
/** Set Advertisement Text.
@param AdText Text of the Advertisement */
public void setAdText (String AdText)
{
if (AdText != null && AdText.length() > 2000)
{
log.warning("Length > 2000 - truncated");
AdText = AdText.substring(0,1999);
}
set_Value ("AdText", AdText);
}
/** Get Advertisement Text.
@return Text of the Advertisement */
public String getAdText() 
{
return (String)get_Value("AdText");
}
/** Column name AdText */
public static final String COLUMNNAME_AdText = "AdText";

/** C_BPartner_ID AD_Reference_ID=232 */
public static final int C_BPARTNER_ID_AD_Reference_ID=232;
/** Set Business Partner .
@param C_BPartner_ID Identifies a Business Partner */
public void setC_BPartner_ID (int C_BPartner_ID)
{
if (C_BPartner_ID < 1) throw new IllegalArgumentException ("C_BPartner_ID is mandatory.");
set_Value ("C_BPartner_ID", Integer.valueOf(C_BPartner_ID));
}
/** Get Business Partner .
@return Identifies a Business Partner */
public int getC_BPartner_ID() 
{
Integer ii = (Integer)get_Value("C_BPartner_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_BPartner_ID */
public static final String COLUMNNAME_C_BPartner_ID = "C_BPartner_ID";
/** Set Description.
@param Description Optional short description of the record */
public void setDescription (String Description)
{
if (Description != null && Description.length() > 255)
{
log.warning("Length > 255 - truncated");
Description = Description.substring(0,254);
}
set_Value ("Description", Description);
}
/** Get Description.
@return Optional short description of the record */
public String getDescription() 
{
return (String)get_Value("Description");
}
/** Column name Description */
public static final String COLUMNNAME_Description = "Description";
/** Set Comment/Help.
@param Help Comment or Hint */
public void setHelp (String Help)
{
if (Help != null && Help.length() > 2000)
{
log.warning("Length > 2000 - truncated");
Help = Help.substring(0,1999);
}
set_Value ("Help", Help);
}
/** Get Comment/Help.
@return Comment or Hint */
public String getHelp() 
{
return (String)get_Value("Help");
}
/** Column name Help */
public static final String COLUMNNAME_Help = "Help";
/** Set Image URL.
@param ImageURL URL of  image */
public void setImageURL (String ImageURL)
{
if (ImageURL != null && ImageURL.length() > 120)
{
log.warning("Length > 120 - truncated");
ImageURL = ImageURL.substring(0,119);
}
set_Value ("ImageURL", ImageURL);
}
/** Get Image URL.
@return URL of  image */
public String getImageURL() 
{
return (String)get_Value("ImageURL");
}
/** Column name ImageURL */
public static final String COLUMNNAME_ImageURL = "ImageURL";
/** Set Self-Service.
@param IsSelfService This is a Self-Service entry or this entry can be changed via Self-Service */
public void setIsSelfService (boolean IsSelfService)
{
set_Value ("IsSelfService", Boolean.valueOf(IsSelfService));
}
/** Get Self-Service.
@return This is a Self-Service entry or this entry can be changed via Self-Service */
public boolean isSelfService() 
{
Object oo = get_Value("IsSelfService");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsSelfService */
public static final String COLUMNNAME_IsSelfService = "IsSelfService";
/** Set Name.
@param Name Alphanumeric identifier of the entity */
public void setName (String Name)
{
if (Name == null) throw new IllegalArgumentException ("Name is mandatory.");
if (Name.length() > 60)
{
log.warning("Length > 60 - truncated");
Name = Name.substring(0,59);
}
set_Value ("Name", Name);
}
/** Get Name.
@return Alphanumeric identifier of the entity */
public String getName() 
{
return (String)get_Value("Name");
}
/** Get Record ID/ColumnName
@return ID/ColumnName pair
*/public KeyNamePair getKeyNamePair() 
{
return new KeyNamePair(get_ID(), getName());
}
/** Column name Name */
public static final String COLUMNNAME_Name = "Name";
/** Set Process Now.
@param Processing Process Now */
public void setProcessing (boolean Processing)
{
set_Value ("Processing", Boolean.valueOf(Processing));
}
/** Get Process Now.
@return Process Now */
public boolean isProcessing() 
{
Object oo = get_Value("Processing");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name Processing */
public static final String COLUMNNAME_Processing = "Processing";

/** PublishStatus AD_Reference_ID=310 */
public static final int PUBLISHSTATUS_AD_Reference_ID=310;
/** Released = R */
public static final String PUBLISHSTATUS_Released = "R";
/** Test = T */
public static final String PUBLISHSTATUS_Test = "T";
/** Under Revision = U */
public static final String PUBLISHSTATUS_UnderRevision = "U";
/** Void = V */
public static final String PUBLISHSTATUS_Void = "V";
/** Set Publication Status.
@param PublishStatus Status of Publication */
public void setPublishStatus (String PublishStatus)
{
if (PublishStatus == null) throw new IllegalArgumentException ("PublishStatus is mandatory");
if (PublishStatus.equals("R") || PublishStatus.equals("T") || PublishStatus.equals("U") || PublishStatus.equals("V"));
 else throw new IllegalArgumentException ("PublishStatus Invalid value - " + PublishStatus + " - Reference_ID=310 - R - T - U - V");
if (PublishStatus.length() > 1)
{
log.warning("Length > 1 - truncated");
PublishStatus = PublishStatus.substring(0,0);
}
set_Value ("PublishStatus", PublishStatus);
}
/** Get Publication Status.
@return Status of Publication */
public String getPublishStatus() 
{
return (String)get_Value("PublishStatus");
}
/** Column name PublishStatus */
public static final String COLUMNNAME_PublishStatus = "PublishStatus";
/** Set Valid from.
@param ValidFrom Valid from including this date (first day) */
public void setValidFrom (Timestamp ValidFrom)
{
set_Value ("ValidFrom", ValidFrom);
}
/** Get Valid from.
@return Valid from including this date (first day) */
public Timestamp getValidFrom() 
{
return (Timestamp)get_Value("ValidFrom");
}
/** Column name ValidFrom */
public static final String COLUMNNAME_ValidFrom = "ValidFrom";
/** Set Valid to.
@param ValidTo Valid to including this date (last day) */
public void setValidTo (Timestamp ValidTo)
{
set_Value ("ValidTo", ValidTo);
}
/** Get Valid to.
@return Valid to including this date (last day) */
public Timestamp getValidTo() 
{
return (Timestamp)get_Value("ValidTo");
}
/** Column name ValidTo */
public static final String COLUMNNAME_ValidTo = "ValidTo";
/** Set Version.
@param Version Version of the table definition */
public void setVersion (int Version)
{
set_Value ("Version", Integer.valueOf(Version));
}
/** Get Version.
@return Version of the table definition */
public int getVersion() 
{
Integer ii = (Integer)get_Value("Version");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name Version */
public static final String COLUMNNAME_Version = "Version";
/** Set Advertisement.
@param W_Advertisement_ID Web Advertisement */
public void setW_Advertisement_ID (int W_Advertisement_ID)
{
if (W_Advertisement_ID < 1) throw new IllegalArgumentException ("W_Advertisement_ID is mandatory.");
set_ValueNoCheck ("W_Advertisement_ID", Integer.valueOf(W_Advertisement_ID));
}
/** Get Advertisement.
@return Web Advertisement */
public int getW_Advertisement_ID() 
{
Integer ii = (Integer)get_Value("W_Advertisement_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name W_Advertisement_ID */
public static final String COLUMNNAME_W_Advertisement_ID = "W_Advertisement_ID";
/** Set Click Count.
@param W_ClickCount_ID Web Click Management */
public void setW_ClickCount_ID (int W_ClickCount_ID)
{
if (W_ClickCount_ID <= 0) set_Value ("W_ClickCount_ID", null);
 else 
set_Value ("W_ClickCount_ID", Integer.valueOf(W_ClickCount_ID));
}
/** Get Click Count.
@return Web Click Management */
public int getW_ClickCount_ID() 
{
Integer ii = (Integer)get_Value("W_ClickCount_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name W_ClickCount_ID */
public static final String COLUMNNAME_W_ClickCount_ID = "W_ClickCount_ID";
/** Set Counter Count.
@param W_CounterCount_ID Web Counter Count Management */
public void setW_CounterCount_ID (int W_CounterCount_ID)
{
if (W_CounterCount_ID <= 0) set_Value ("W_CounterCount_ID", null);
 else 
set_Value ("W_CounterCount_ID", Integer.valueOf(W_CounterCount_ID));
}
/** Get Counter Count.
@return Web Counter Count Management */
public int getW_CounterCount_ID() 
{
Integer ii = (Integer)get_Value("W_CounterCount_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name W_CounterCount_ID */
public static final String COLUMNNAME_W_CounterCount_ID = "W_CounterCount_ID";
/** Set Web Parameter 1.
@param WebParam1 Web Site Parameter 1 (default: header image) */
public void setWebParam1 (String WebParam1)
{
if (WebParam1 != null && WebParam1.length() > 2000)
{
log.warning("Length > 2000 - truncated");
WebParam1 = WebParam1.substring(0,1999);
}
set_Value ("WebParam1", WebParam1);
}
/** Get Web Parameter 1.
@return Web Site Parameter 1 (default: header image) */
public String getWebParam1() 
{
return (String)get_Value("WebParam1");
}
/** Column name WebParam1 */
public static final String COLUMNNAME_WebParam1 = "WebParam1";
/** Set Web Parameter 2.
@param WebParam2 Web Site Parameter 2 (default index page) */
public void setWebParam2 (String WebParam2)
{
if (WebParam2 != null && WebParam2.length() > 2000)
{
log.warning("Length > 2000 - truncated");
WebParam2 = WebParam2.substring(0,1999);
}
set_Value ("WebParam2", WebParam2);
}
/** Get Web Parameter 2.
@return Web Site Parameter 2 (default index page) */
public String getWebParam2() 
{
return (String)get_Value("WebParam2");
}
/** Column name WebParam2 */
public static final String COLUMNNAME_WebParam2 = "WebParam2";
/** Set Web Parameter 3.
@param WebParam3 Web Site Parameter 3 (default left - menu) */
public void setWebParam3 (String WebParam3)
{
if (WebParam3 != null && WebParam3.length() > 2000)
{
log.warning("Length > 2000 - truncated");
WebParam3 = WebParam3.substring(0,1999);
}
set_Value ("WebParam3", WebParam3);
}
/** Get Web Parameter 3.
@return Web Site Parameter 3 (default left - menu) */
public String getWebParam3() 
{
return (String)get_Value("WebParam3");
}
/** Column name WebParam3 */
public static final String COLUMNNAME_WebParam3 = "WebParam3";
/** Set Web Parameter 4.
@param WebParam4 Web Site Parameter 4 (default footer left) */
public void setWebParam4 (String WebParam4)
{
if (WebParam4 != null && WebParam4.length() > 2000)
{
log.warning("Length > 2000 - truncated");
WebParam4 = WebParam4.substring(0,1999);
}
set_Value ("WebParam4", WebParam4);
}
/** Get Web Parameter 4.
@return Web Site Parameter 4 (default footer left) */
public String getWebParam4() 
{
return (String)get_Value("WebParam4");
}
/** Column name WebParam4 */
public static final String COLUMNNAME_WebParam4 = "WebParam4";
}
