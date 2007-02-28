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
/** Generated Model for GL_DistributionLine
 *  @author Adempiere (generated) 
 *  @version Release 3.1.5 - $Id$ */
public class X_GL_DistributionLine extends PO
{
/** Standard Constructor
@param ctx context
@param GL_DistributionLine_ID id
@param trxName transaction
*/
public X_GL_DistributionLine (Properties ctx, int GL_DistributionLine_ID, String trxName)
{
super (ctx, GL_DistributionLine_ID, trxName);
/** if (GL_DistributionLine_ID == 0)
{
setGL_DistributionLine_ID (0);
setGL_Distribution_ID (0);
setLine (0);	// @SQL=SELECT NVL(MAX(Line),0)+10 AS DefaultValue FROM GL_DistributionLine WHERE GL_Distribution_ID=@GL_Distribution_ID@
setOverwriteAcct (false);
setOverwriteActivity (false);
setOverwriteBPartner (false);
setOverwriteCampaign (false);
setOverwriteLocFrom (false);
setOverwriteLocTo (false);
setOverwriteOrg (false);
setOverwriteOrgTrx (false);
setOverwriteProduct (false);
setOverwriteProject (false);
setOverwriteSalesRegion (false);
setOverwriteUser1 (false);
setOverwriteUser2 (false);
setPercent (Env.ZERO);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_GL_DistributionLine (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=707 */
public static final int Table_ID=MTable.getTable_ID("GL_DistributionLine");

/** TableName=GL_DistributionLine */
public static final String Table_Name="GL_DistributionLine";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"GL_DistributionLine");

protected BigDecimal accessLevel = BigDecimal.valueOf(2);
/** AccessLevel
@return 2 - Client 
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
StringBuffer sb = new StringBuffer ("X_GL_DistributionLine[").append(get_ID()).append("]");
return sb.toString();
}

/** AD_OrgTrx_ID AD_Reference_ID=130 */
public static final int AD_ORGTRX_ID_AD_Reference_ID=130;
/** Set Trx Organization.
@param AD_OrgTrx_ID Performing or initiating organization */
public void setAD_OrgTrx_ID (int AD_OrgTrx_ID)
{
if (AD_OrgTrx_ID <= 0) set_Value ("AD_OrgTrx_ID", null);
 else 
set_Value ("AD_OrgTrx_ID", Integer.valueOf(AD_OrgTrx_ID));
}
/** Get Trx Organization.
@return Performing or initiating organization */
public int getAD_OrgTrx_ID() 
{
Integer ii = (Integer)get_Value("AD_OrgTrx_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name AD_OrgTrx_ID */
public static final String COLUMNNAME_AD_OrgTrx_ID = "AD_OrgTrx_ID";

/** Account_ID AD_Reference_ID=132 */
public static final int ACCOUNT_ID_AD_Reference_ID=132;
/** Set Account.
@param Account_ID Account used */
public void setAccount_ID (int Account_ID)
{
if (Account_ID <= 0) set_Value ("Account_ID", null);
 else 
set_Value ("Account_ID", Integer.valueOf(Account_ID));
}
/** Get Account.
@return Account used */
public int getAccount_ID() 
{
Integer ii = (Integer)get_Value("Account_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name Account_ID */
public static final String COLUMNNAME_Account_ID = "Account_ID";
/** Set Activity.
@param C_Activity_ID Business Activity */
public void setC_Activity_ID (int C_Activity_ID)
{
if (C_Activity_ID <= 0) set_Value ("C_Activity_ID", null);
 else 
set_Value ("C_Activity_ID", Integer.valueOf(C_Activity_ID));
}
/** Get Activity.
@return Business Activity */
public int getC_Activity_ID() 
{
Integer ii = (Integer)get_Value("C_Activity_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_Activity_ID */
public static final String COLUMNNAME_C_Activity_ID = "C_Activity_ID";
/** Set Business Partner .
@param C_BPartner_ID Identifies a Business Partner */
public void setC_BPartner_ID (int C_BPartner_ID)
{
if (C_BPartner_ID <= 0) set_Value ("C_BPartner_ID", null);
 else 
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
/** Set Campaign.
@param C_Campaign_ID Marketing Campaign */
public void setC_Campaign_ID (int C_Campaign_ID)
{
if (C_Campaign_ID <= 0) set_Value ("C_Campaign_ID", null);
 else 
set_Value ("C_Campaign_ID", Integer.valueOf(C_Campaign_ID));
}
/** Get Campaign.
@return Marketing Campaign */
public int getC_Campaign_ID() 
{
Integer ii = (Integer)get_Value("C_Campaign_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_Campaign_ID */
public static final String COLUMNNAME_C_Campaign_ID = "C_Campaign_ID";

/** C_LocFrom_ID AD_Reference_ID=133 */
public static final int C_LOCFROM_ID_AD_Reference_ID=133;
/** Set Location From.
@param C_LocFrom_ID Location that inventory was moved from */
public void setC_LocFrom_ID (int C_LocFrom_ID)
{
if (C_LocFrom_ID <= 0) set_Value ("C_LocFrom_ID", null);
 else 
set_Value ("C_LocFrom_ID", Integer.valueOf(C_LocFrom_ID));
}
/** Get Location From.
@return Location that inventory was moved from */
public int getC_LocFrom_ID() 
{
Integer ii = (Integer)get_Value("C_LocFrom_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_LocFrom_ID */
public static final String COLUMNNAME_C_LocFrom_ID = "C_LocFrom_ID";

/** C_LocTo_ID AD_Reference_ID=133 */
public static final int C_LOCTO_ID_AD_Reference_ID=133;
/** Set Location To.
@param C_LocTo_ID Location that inventory was moved to */
public void setC_LocTo_ID (int C_LocTo_ID)
{
if (C_LocTo_ID <= 0) set_Value ("C_LocTo_ID", null);
 else 
set_Value ("C_LocTo_ID", Integer.valueOf(C_LocTo_ID));
}
/** Get Location To.
@return Location that inventory was moved to */
public int getC_LocTo_ID() 
{
Integer ii = (Integer)get_Value("C_LocTo_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_LocTo_ID */
public static final String COLUMNNAME_C_LocTo_ID = "C_LocTo_ID";
/** Set Project.
@param C_Project_ID Financial Project */
public void setC_Project_ID (int C_Project_ID)
{
if (C_Project_ID <= 0) set_Value ("C_Project_ID", null);
 else 
set_Value ("C_Project_ID", Integer.valueOf(C_Project_ID));
}
/** Get Project.
@return Financial Project */
public int getC_Project_ID() 
{
Integer ii = (Integer)get_Value("C_Project_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_Project_ID */
public static final String COLUMNNAME_C_Project_ID = "C_Project_ID";
/** Set Sales Region.
@param C_SalesRegion_ID Sales coverage region */
public void setC_SalesRegion_ID (int C_SalesRegion_ID)
{
if (C_SalesRegion_ID <= 0) set_Value ("C_SalesRegion_ID", null);
 else 
set_Value ("C_SalesRegion_ID", Integer.valueOf(C_SalesRegion_ID));
}
/** Get Sales Region.
@return Sales coverage region */
public int getC_SalesRegion_ID() 
{
Integer ii = (Integer)get_Value("C_SalesRegion_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_SalesRegion_ID */
public static final String COLUMNNAME_C_SalesRegion_ID = "C_SalesRegion_ID";
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
/** Set GL Distribution Line.
@param GL_DistributionLine_ID General Ledger Distribution Line */
public void setGL_DistributionLine_ID (int GL_DistributionLine_ID)
{
if (GL_DistributionLine_ID < 1) throw new IllegalArgumentException ("GL_DistributionLine_ID is mandatory.");
set_ValueNoCheck ("GL_DistributionLine_ID", Integer.valueOf(GL_DistributionLine_ID));
}
/** Get GL Distribution Line.
@return General Ledger Distribution Line */
public int getGL_DistributionLine_ID() 
{
Integer ii = (Integer)get_Value("GL_DistributionLine_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name GL_DistributionLine_ID */
public static final String COLUMNNAME_GL_DistributionLine_ID = "GL_DistributionLine_ID";
/** Set GL Distribution.
@param GL_Distribution_ID General Ledger Distribution */
public void setGL_Distribution_ID (int GL_Distribution_ID)
{
if (GL_Distribution_ID < 1) throw new IllegalArgumentException ("GL_Distribution_ID is mandatory.");
set_ValueNoCheck ("GL_Distribution_ID", Integer.valueOf(GL_Distribution_ID));
}
/** Get GL Distribution.
@return General Ledger Distribution */
public int getGL_Distribution_ID() 
{
Integer ii = (Integer)get_Value("GL_Distribution_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name GL_Distribution_ID */
public static final String COLUMNNAME_GL_Distribution_ID = "GL_Distribution_ID";
/** Set Line No.
@param Line Unique line for this document */
public void setLine (int Line)
{
set_Value ("Line", Integer.valueOf(Line));
}
/** Get Line No.
@return Unique line for this document */
public int getLine() 
{
Integer ii = (Integer)get_Value("Line");
if (ii == null) return 0;
return ii.intValue();
}
/** Get Record ID/ColumnName
@return ID/ColumnName pair
*/public KeyNamePair getKeyNamePair() 
{
return new KeyNamePair(get_ID(), String.valueOf(getLine()));
}
/** Column name Line */
public static final String COLUMNNAME_Line = "Line";
/** Set Product.
@param M_Product_ID Product, Service, Item */
public void setM_Product_ID (int M_Product_ID)
{
if (M_Product_ID <= 0) set_Value ("M_Product_ID", null);
 else 
set_Value ("M_Product_ID", Integer.valueOf(M_Product_ID));
}
/** Get Product.
@return Product, Service, Item */
public int getM_Product_ID() 
{
Integer ii = (Integer)get_Value("M_Product_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name M_Product_ID */
public static final String COLUMNNAME_M_Product_ID = "M_Product_ID";

/** Org_ID AD_Reference_ID=130 */
public static final int ORG_ID_AD_Reference_ID=130;
/** Set Organization.
@param Org_ID Organizational entity within client */
public void setOrg_ID (int Org_ID)
{
if (Org_ID <= 0) set_Value ("Org_ID", null);
 else 
set_Value ("Org_ID", Integer.valueOf(Org_ID));
}
/** Get Organization.
@return Organizational entity within client */
public int getOrg_ID() 
{
Integer ii = (Integer)get_Value("Org_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name Org_ID */
public static final String COLUMNNAME_Org_ID = "Org_ID";
/** Set Overwrite Account.
@param OverwriteAcct Overwrite the account segment Account with the value specified */
public void setOverwriteAcct (boolean OverwriteAcct)
{
set_Value ("OverwriteAcct", Boolean.valueOf(OverwriteAcct));
}
/** Get Overwrite Account.
@return Overwrite the account segment Account with the value specified */
public boolean isOverwriteAcct() 
{
Object oo = get_Value("OverwriteAcct");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name OverwriteAcct */
public static final String COLUMNNAME_OverwriteAcct = "OverwriteAcct";
/** Set Overwrite Activity.
@param OverwriteActivity Overwrite the account segment Activity with the value specified */
public void setOverwriteActivity (boolean OverwriteActivity)
{
set_Value ("OverwriteActivity", Boolean.valueOf(OverwriteActivity));
}
/** Get Overwrite Activity.
@return Overwrite the account segment Activity with the value specified */
public boolean isOverwriteActivity() 
{
Object oo = get_Value("OverwriteActivity");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name OverwriteActivity */
public static final String COLUMNNAME_OverwriteActivity = "OverwriteActivity";
/** Set Overwrite Bus.Partner.
@param OverwriteBPartner Overwrite the account segment Business Partner with the value specified */
public void setOverwriteBPartner (boolean OverwriteBPartner)
{
set_Value ("OverwriteBPartner", Boolean.valueOf(OverwriteBPartner));
}
/** Get Overwrite Bus.Partner.
@return Overwrite the account segment Business Partner with the value specified */
public boolean isOverwriteBPartner() 
{
Object oo = get_Value("OverwriteBPartner");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name OverwriteBPartner */
public static final String COLUMNNAME_OverwriteBPartner = "OverwriteBPartner";
/** Set Overwrite Campaign.
@param OverwriteCampaign Overwrite the account segment Campaign with the value specified */
public void setOverwriteCampaign (boolean OverwriteCampaign)
{
set_Value ("OverwriteCampaign", Boolean.valueOf(OverwriteCampaign));
}
/** Get Overwrite Campaign.
@return Overwrite the account segment Campaign with the value specified */
public boolean isOverwriteCampaign() 
{
Object oo = get_Value("OverwriteCampaign");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name OverwriteCampaign */
public static final String COLUMNNAME_OverwriteCampaign = "OverwriteCampaign";
/** Set Overwrite Location From.
@param OverwriteLocFrom Overwrite the account segment Location From with the value specified */
public void setOverwriteLocFrom (boolean OverwriteLocFrom)
{
set_Value ("OverwriteLocFrom", Boolean.valueOf(OverwriteLocFrom));
}
/** Get Overwrite Location From.
@return Overwrite the account segment Location From with the value specified */
public boolean isOverwriteLocFrom() 
{
Object oo = get_Value("OverwriteLocFrom");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name OverwriteLocFrom */
public static final String COLUMNNAME_OverwriteLocFrom = "OverwriteLocFrom";
/** Set Overwrite Location To.
@param OverwriteLocTo Overwrite the account segment Location From with the value specified */
public void setOverwriteLocTo (boolean OverwriteLocTo)
{
set_Value ("OverwriteLocTo", Boolean.valueOf(OverwriteLocTo));
}
/** Get Overwrite Location To.
@return Overwrite the account segment Location From with the value specified */
public boolean isOverwriteLocTo() 
{
Object oo = get_Value("OverwriteLocTo");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name OverwriteLocTo */
public static final String COLUMNNAME_OverwriteLocTo = "OverwriteLocTo";
/** Set Overwrite Organization.
@param OverwriteOrg Overwrite the account segment Organization with the value specified */
public void setOverwriteOrg (boolean OverwriteOrg)
{
set_Value ("OverwriteOrg", Boolean.valueOf(OverwriteOrg));
}
/** Get Overwrite Organization.
@return Overwrite the account segment Organization with the value specified */
public boolean isOverwriteOrg() 
{
Object oo = get_Value("OverwriteOrg");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name OverwriteOrg */
public static final String COLUMNNAME_OverwriteOrg = "OverwriteOrg";
/** Set Overwrite Trx Organuzation.
@param OverwriteOrgTrx Overwrite the account segment Transaction Organization with the value specified */
public void setOverwriteOrgTrx (boolean OverwriteOrgTrx)
{
set_Value ("OverwriteOrgTrx", Boolean.valueOf(OverwriteOrgTrx));
}
/** Get Overwrite Trx Organuzation.
@return Overwrite the account segment Transaction Organization with the value specified */
public boolean isOverwriteOrgTrx() 
{
Object oo = get_Value("OverwriteOrgTrx");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name OverwriteOrgTrx */
public static final String COLUMNNAME_OverwriteOrgTrx = "OverwriteOrgTrx";
/** Set Overwrite Product.
@param OverwriteProduct Overwrite the account segment Product with the value specified */
public void setOverwriteProduct (boolean OverwriteProduct)
{
set_Value ("OverwriteProduct", Boolean.valueOf(OverwriteProduct));
}
/** Get Overwrite Product.
@return Overwrite the account segment Product with the value specified */
public boolean isOverwriteProduct() 
{
Object oo = get_Value("OverwriteProduct");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name OverwriteProduct */
public static final String COLUMNNAME_OverwriteProduct = "OverwriteProduct";
/** Set Overwrite Project.
@param OverwriteProject Overwrite the account segment Project with the value specified */
public void setOverwriteProject (boolean OverwriteProject)
{
set_Value ("OverwriteProject", Boolean.valueOf(OverwriteProject));
}
/** Get Overwrite Project.
@return Overwrite the account segment Project with the value specified */
public boolean isOverwriteProject() 
{
Object oo = get_Value("OverwriteProject");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name OverwriteProject */
public static final String COLUMNNAME_OverwriteProject = "OverwriteProject";
/** Set Overwrite Sales Region.
@param OverwriteSalesRegion Overwrite the account segment Sales Region with the value specified */
public void setOverwriteSalesRegion (boolean OverwriteSalesRegion)
{
set_Value ("OverwriteSalesRegion", Boolean.valueOf(OverwriteSalesRegion));
}
/** Get Overwrite Sales Region.
@return Overwrite the account segment Sales Region with the value specified */
public boolean isOverwriteSalesRegion() 
{
Object oo = get_Value("OverwriteSalesRegion");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name OverwriteSalesRegion */
public static final String COLUMNNAME_OverwriteSalesRegion = "OverwriteSalesRegion";
/** Set Overwrite User1.
@param OverwriteUser1 Overwrite the account segment User 1 with the value specified */
public void setOverwriteUser1 (boolean OverwriteUser1)
{
set_Value ("OverwriteUser1", Boolean.valueOf(OverwriteUser1));
}
/** Get Overwrite User1.
@return Overwrite the account segment User 1 with the value specified */
public boolean isOverwriteUser1() 
{
Object oo = get_Value("OverwriteUser1");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name OverwriteUser1 */
public static final String COLUMNNAME_OverwriteUser1 = "OverwriteUser1";
/** Set Overwrite User2.
@param OverwriteUser2 Overwrite the account segment User 2 with the value specified */
public void setOverwriteUser2 (boolean OverwriteUser2)
{
set_Value ("OverwriteUser2", Boolean.valueOf(OverwriteUser2));
}
/** Get Overwrite User2.
@return Overwrite the account segment User 2 with the value specified */
public boolean isOverwriteUser2() 
{
Object oo = get_Value("OverwriteUser2");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name OverwriteUser2 */
public static final String COLUMNNAME_OverwriteUser2 = "OverwriteUser2";
/** Set Percent.
@param Percent Percentage */
public void setPercent (BigDecimal Percent)
{
if (Percent == null) throw new IllegalArgumentException ("Percent is mandatory.");
set_Value ("Percent", Percent);
}
/** Get Percent.
@return Percentage */
public BigDecimal getPercent() 
{
BigDecimal bd = (BigDecimal)get_Value("Percent");
if (bd == null) return Env.ZERO;
return bd;
}
/** Column name Percent */
public static final String COLUMNNAME_Percent = "Percent";

/** User1_ID AD_Reference_ID=134 */
public static final int USER1_ID_AD_Reference_ID=134;
/** Set User List 1.
@param User1_ID User defined list element #1 */
public void setUser1_ID (int User1_ID)
{
if (User1_ID <= 0) set_Value ("User1_ID", null);
 else 
set_Value ("User1_ID", Integer.valueOf(User1_ID));
}
/** Get User List 1.
@return User defined list element #1 */
public int getUser1_ID() 
{
Integer ii = (Integer)get_Value("User1_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name User1_ID */
public static final String COLUMNNAME_User1_ID = "User1_ID";

/** User2_ID AD_Reference_ID=137 */
public static final int USER2_ID_AD_Reference_ID=137;
/** Set User List 2.
@param User2_ID User defined list element #2 */
public void setUser2_ID (int User2_ID)
{
if (User2_ID <= 0) set_Value ("User2_ID", null);
 else 
set_Value ("User2_ID", Integer.valueOf(User2_ID));
}
/** Get User List 2.
@return User defined list element #2 */
public int getUser2_ID() 
{
Integer ii = (Integer)get_Value("User2_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name User2_ID */
public static final String COLUMNNAME_User2_ID = "User2_ID";
}
