/******************************************************************************
 * Product: Compiere ERP & CRM Smart Business Solution                        *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
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
/** Generated Model for GL_Distribution
 *  @author Jorg Janke (generated) 
 *  @version Release 2.6.0a - $Id$ */
public class X_GL_Distribution extends PO
{
/** Standard Constructor
@param ctx context
@param GL_Distribution_ID id
@param trxName transaction
*/
public X_GL_Distribution (Properties ctx, int GL_Distribution_ID, String trxName)
{
super (ctx, GL_Distribution_ID, trxName);
/** if (GL_Distribution_ID == 0)
{
setAnyAcct (true);	// Y
setAnyActivity (true);	// Y
setAnyBPartner (true);	// Y
setAnyCampaign (true);	// Y
setAnyLocFrom (true);	// Y
setAnyLocTo (true);	// Y
setAnyOrg (true);	// Y
setAnyOrgTrx (true);	// Y
setAnyProduct (true);	// Y
setAnyProject (true);	// Y
setAnySalesRegion (true);	// Y
setAnyUser1 (true);	// Y
setAnyUser2 (true);	// Y
setC_AcctSchema_ID (0);
setGL_Distribution_ID (0);
setIsValid (false);	// N
setName (null);
setPercentTotal (Env.ZERO);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_GL_Distribution (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=708 */
public static final int Table_ID=708;

/** TableName=GL_Distribution */
public static final String Table_Name="GL_Distribution";

protected static KeyNamePair Model = new KeyNamePair(708,"GL_Distribution");

protected BigDecimal accessLevel = new BigDecimal(2);
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
StringBuffer sb = new StringBuffer ("X_GL_Distribution[").append(get_ID()).append("]");
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
set_Value ("AD_OrgTrx_ID", new Integer(AD_OrgTrx_ID));
}
/** Get Trx Organization.
@return Performing or initiating organization */
public int getAD_OrgTrx_ID() 
{
Integer ii = (Integer)get_Value("AD_OrgTrx_ID");
if (ii == null) return 0;
return ii.intValue();
}

/** Account_ID AD_Reference_ID=132 */
public static final int ACCOUNT_ID_AD_Reference_ID=132;
/** Set Account.
@param Account_ID Account used */
public void setAccount_ID (int Account_ID)
{
if (Account_ID <= 0) set_Value ("Account_ID", null);
 else 
set_Value ("Account_ID", new Integer(Account_ID));
}
/** Get Account.
@return Account used */
public int getAccount_ID() 
{
Integer ii = (Integer)get_Value("Account_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Any Account.
@param AnyAcct Match any value of the Account segment */
public void setAnyAcct (boolean AnyAcct)
{
set_Value ("AnyAcct", new Boolean(AnyAcct));
}
/** Get Any Account.
@return Match any value of the Account segment */
public boolean isAnyAcct() 
{
Object oo = get_Value("AnyAcct");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Any Activity.
@param AnyActivity Match any value of the Activity segment */
public void setAnyActivity (boolean AnyActivity)
{
set_Value ("AnyActivity", new Boolean(AnyActivity));
}
/** Get Any Activity.
@return Match any value of the Activity segment */
public boolean isAnyActivity() 
{
Object oo = get_Value("AnyActivity");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Any Bus.Partner.
@param AnyBPartner Match any value of the Business Partner segment */
public void setAnyBPartner (boolean AnyBPartner)
{
set_Value ("AnyBPartner", new Boolean(AnyBPartner));
}
/** Get Any Bus.Partner.
@return Match any value of the Business Partner segment */
public boolean isAnyBPartner() 
{
Object oo = get_Value("AnyBPartner");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Any Campaign.
@param AnyCampaign Match any value of the Campaign segment */
public void setAnyCampaign (boolean AnyCampaign)
{
set_Value ("AnyCampaign", new Boolean(AnyCampaign));
}
/** Get Any Campaign.
@return Match any value of the Campaign segment */
public boolean isAnyCampaign() 
{
Object oo = get_Value("AnyCampaign");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Any Location From.
@param AnyLocFrom Match any value of the Location From segment */
public void setAnyLocFrom (boolean AnyLocFrom)
{
set_Value ("AnyLocFrom", new Boolean(AnyLocFrom));
}
/** Get Any Location From.
@return Match any value of the Location From segment */
public boolean isAnyLocFrom() 
{
Object oo = get_Value("AnyLocFrom");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Any Location To.
@param AnyLocTo Match any value of the Location To segment */
public void setAnyLocTo (boolean AnyLocTo)
{
set_Value ("AnyLocTo", new Boolean(AnyLocTo));
}
/** Get Any Location To.
@return Match any value of the Location To segment */
public boolean isAnyLocTo() 
{
Object oo = get_Value("AnyLocTo");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Any Organization.
@param AnyOrg Match any value of the Organization segment */
public void setAnyOrg (boolean AnyOrg)
{
set_Value ("AnyOrg", new Boolean(AnyOrg));
}
/** Get Any Organization.
@return Match any value of the Organization segment */
public boolean isAnyOrg() 
{
Object oo = get_Value("AnyOrg");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Any Trx Organization.
@param AnyOrgTrx Match any value of the Transaction Organization segment */
public void setAnyOrgTrx (boolean AnyOrgTrx)
{
set_Value ("AnyOrgTrx", new Boolean(AnyOrgTrx));
}
/** Get Any Trx Organization.
@return Match any value of the Transaction Organization segment */
public boolean isAnyOrgTrx() 
{
Object oo = get_Value("AnyOrgTrx");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Any Product.
@param AnyProduct Match any value of the Product segment */
public void setAnyProduct (boolean AnyProduct)
{
set_Value ("AnyProduct", new Boolean(AnyProduct));
}
/** Get Any Product.
@return Match any value of the Product segment */
public boolean isAnyProduct() 
{
Object oo = get_Value("AnyProduct");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Any Project.
@param AnyProject Match any value of the Project segment */
public void setAnyProject (boolean AnyProject)
{
set_Value ("AnyProject", new Boolean(AnyProject));
}
/** Get Any Project.
@return Match any value of the Project segment */
public boolean isAnyProject() 
{
Object oo = get_Value("AnyProject");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Any Sales Region.
@param AnySalesRegion Match any value of the Sales Region segment */
public void setAnySalesRegion (boolean AnySalesRegion)
{
set_Value ("AnySalesRegion", new Boolean(AnySalesRegion));
}
/** Get Any Sales Region.
@return Match any value of the Sales Region segment */
public boolean isAnySalesRegion() 
{
Object oo = get_Value("AnySalesRegion");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Any User 1.
@param AnyUser1 Match any value of the User 1 segment */
public void setAnyUser1 (boolean AnyUser1)
{
set_Value ("AnyUser1", new Boolean(AnyUser1));
}
/** Get Any User 1.
@return Match any value of the User 1 segment */
public boolean isAnyUser1() 
{
Object oo = get_Value("AnyUser1");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Any User 2.
@param AnyUser2 Match any value of the User 2 segment */
public void setAnyUser2 (boolean AnyUser2)
{
set_Value ("AnyUser2", new Boolean(AnyUser2));
}
/** Get Any User 2.
@return Match any value of the User 2 segment */
public boolean isAnyUser2() 
{
Object oo = get_Value("AnyUser2");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Accounting Schema.
@param C_AcctSchema_ID Rules for accounting */
public void setC_AcctSchema_ID (int C_AcctSchema_ID)
{
if (C_AcctSchema_ID < 1) throw new IllegalArgumentException ("C_AcctSchema_ID is mandatory.");
set_ValueNoCheck ("C_AcctSchema_ID", new Integer(C_AcctSchema_ID));
}
/** Get Accounting Schema.
@return Rules for accounting */
public int getC_AcctSchema_ID() 
{
Integer ii = (Integer)get_Value("C_AcctSchema_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Activity.
@param C_Activity_ID Business Activity */
public void setC_Activity_ID (int C_Activity_ID)
{
if (C_Activity_ID <= 0) set_Value ("C_Activity_ID", null);
 else 
set_Value ("C_Activity_ID", new Integer(C_Activity_ID));
}
/** Get Activity.
@return Business Activity */
public int getC_Activity_ID() 
{
Integer ii = (Integer)get_Value("C_Activity_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Business Partner .
@param C_BPartner_ID Identifies a Business Partner */
public void setC_BPartner_ID (int C_BPartner_ID)
{
if (C_BPartner_ID <= 0) set_Value ("C_BPartner_ID", null);
 else 
set_Value ("C_BPartner_ID", new Integer(C_BPartner_ID));
}
/** Get Business Partner .
@return Identifies a Business Partner */
public int getC_BPartner_ID() 
{
Integer ii = (Integer)get_Value("C_BPartner_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Campaign.
@param C_Campaign_ID Marketing Campaign */
public void setC_Campaign_ID (int C_Campaign_ID)
{
if (C_Campaign_ID <= 0) set_Value ("C_Campaign_ID", null);
 else 
set_Value ("C_Campaign_ID", new Integer(C_Campaign_ID));
}
/** Get Campaign.
@return Marketing Campaign */
public int getC_Campaign_ID() 
{
Integer ii = (Integer)get_Value("C_Campaign_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Document Type.
@param C_DocType_ID Document type or rules */
public void setC_DocType_ID (int C_DocType_ID)
{
if (C_DocType_ID <= 0) set_Value ("C_DocType_ID", null);
 else 
set_Value ("C_DocType_ID", new Integer(C_DocType_ID));
}
/** Get Document Type.
@return Document type or rules */
public int getC_DocType_ID() 
{
Integer ii = (Integer)get_Value("C_DocType_ID");
if (ii == null) return 0;
return ii.intValue();
}

/** C_LocFrom_ID AD_Reference_ID=133 */
public static final int C_LOCFROM_ID_AD_Reference_ID=133;
/** Set Location From.
@param C_LocFrom_ID Location that inventory was moved from */
public void setC_LocFrom_ID (int C_LocFrom_ID)
{
if (C_LocFrom_ID <= 0) set_Value ("C_LocFrom_ID", null);
 else 
set_Value ("C_LocFrom_ID", new Integer(C_LocFrom_ID));
}
/** Get Location From.
@return Location that inventory was moved from */
public int getC_LocFrom_ID() 
{
Integer ii = (Integer)get_Value("C_LocFrom_ID");
if (ii == null) return 0;
return ii.intValue();
}

/** C_LocTo_ID AD_Reference_ID=133 */
public static final int C_LOCTO_ID_AD_Reference_ID=133;
/** Set Location To.
@param C_LocTo_ID Location that inventory was moved to */
public void setC_LocTo_ID (int C_LocTo_ID)
{
if (C_LocTo_ID <= 0) set_Value ("C_LocTo_ID", null);
 else 
set_Value ("C_LocTo_ID", new Integer(C_LocTo_ID));
}
/** Get Location To.
@return Location that inventory was moved to */
public int getC_LocTo_ID() 
{
Integer ii = (Integer)get_Value("C_LocTo_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Project.
@param C_Project_ID Financial Project */
public void setC_Project_ID (int C_Project_ID)
{
if (C_Project_ID <= 0) set_Value ("C_Project_ID", null);
 else 
set_Value ("C_Project_ID", new Integer(C_Project_ID));
}
/** Get Project.
@return Financial Project */
public int getC_Project_ID() 
{
Integer ii = (Integer)get_Value("C_Project_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Sales Region.
@param C_SalesRegion_ID Sales coverage region */
public void setC_SalesRegion_ID (int C_SalesRegion_ID)
{
if (C_SalesRegion_ID <= 0) set_Value ("C_SalesRegion_ID", null);
 else 
set_Value ("C_SalesRegion_ID", new Integer(C_SalesRegion_ID));
}
/** Get Sales Region.
@return Sales coverage region */
public int getC_SalesRegion_ID() 
{
Integer ii = (Integer)get_Value("C_SalesRegion_ID");
if (ii == null) return 0;
return ii.intValue();
}
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
/** Set GL Distribution.
@param GL_Distribution_ID General Ledger Distribution */
public void setGL_Distribution_ID (int GL_Distribution_ID)
{
if (GL_Distribution_ID < 1) throw new IllegalArgumentException ("GL_Distribution_ID is mandatory.");
set_ValueNoCheck ("GL_Distribution_ID", new Integer(GL_Distribution_ID));
}
/** Get GL Distribution.
@return General Ledger Distribution */
public int getGL_Distribution_ID() 
{
Integer ii = (Integer)get_Value("GL_Distribution_ID");
if (ii == null) return 0;
return ii.intValue();
}
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
/** Set Valid.
@param IsValid Element is valid */
public void setIsValid (boolean IsValid)
{
set_Value ("IsValid", new Boolean(IsValid));
}
/** Get Valid.
@return Element is valid */
public boolean isValid() 
{
Object oo = get_Value("IsValid");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Product.
@param M_Product_ID Product, Service, Item */
public void setM_Product_ID (int M_Product_ID)
{
if (M_Product_ID <= 0) set_Value ("M_Product_ID", null);
 else 
set_Value ("M_Product_ID", new Integer(M_Product_ID));
}
/** Get Product.
@return Product, Service, Item */
public int getM_Product_ID() 
{
Integer ii = (Integer)get_Value("M_Product_ID");
if (ii == null) return 0;
return ii.intValue();
}
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

/** Org_ID AD_Reference_ID=130 */
public static final int ORG_ID_AD_Reference_ID=130;
/** Set Organization.
@param Org_ID Organizational entity within client */
public void setOrg_ID (int Org_ID)
{
if (Org_ID <= 0) set_Value ("Org_ID", null);
 else 
set_Value ("Org_ID", new Integer(Org_ID));
}
/** Get Organization.
@return Organizational entity within client */
public int getOrg_ID() 
{
Integer ii = (Integer)get_Value("Org_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Total Percent.
@param PercentTotal Sum of the Percent details  */
public void setPercentTotal (BigDecimal PercentTotal)
{
if (PercentTotal == null) throw new IllegalArgumentException ("PercentTotal is mandatory.");
set_Value ("PercentTotal", PercentTotal);
}
/** Get Total Percent.
@return Sum of the Percent details  */
public BigDecimal getPercentTotal() 
{
BigDecimal bd = (BigDecimal)get_Value("PercentTotal");
if (bd == null) return Env.ZERO;
return bd;
}

/** PostingType AD_Reference_ID=125 */
public static final int POSTINGTYPE_AD_Reference_ID=125;
/** Actual = A */
public static final String POSTINGTYPE_Actual = "A";
/** Budget = B */
public static final String POSTINGTYPE_Budget = "B";
/** Commitment = E */
public static final String POSTINGTYPE_Commitment = "E";
/** Reservation = R */
public static final String POSTINGTYPE_Reservation = "R";
/** Statistical = S */
public static final String POSTINGTYPE_Statistical = "S";
/** Set PostingType.
@param PostingType The type of posted amount for the transaction */
public void setPostingType (String PostingType)
{
if (PostingType == null || PostingType.equals("A") || PostingType.equals("B") || PostingType.equals("E") || PostingType.equals("R") || PostingType.equals("S"));
 else throw new IllegalArgumentException ("PostingType Invalid value - " + PostingType + " - Reference_ID=125 - A - B - E - R - S");
if (PostingType != null && PostingType.length() > 1)
{
log.warning("Length > 1 - truncated");
PostingType = PostingType.substring(0,0);
}
set_Value ("PostingType", PostingType);
}
/** Get PostingType.
@return The type of posted amount for the transaction */
public String getPostingType() 
{
return (String)get_Value("PostingType");
}
/** Set Process Now.
@param Processing Process Now */
public void setProcessing (boolean Processing)
{
set_Value ("Processing", new Boolean(Processing));
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

/** User1_ID AD_Reference_ID=134 */
public static final int USER1_ID_AD_Reference_ID=134;
/** Set User List 1.
@param User1_ID User defined list element #1 */
public void setUser1_ID (int User1_ID)
{
if (User1_ID <= 0) set_Value ("User1_ID", null);
 else 
set_Value ("User1_ID", new Integer(User1_ID));
}
/** Get User List 1.
@return User defined list element #1 */
public int getUser1_ID() 
{
Integer ii = (Integer)get_Value("User1_ID");
if (ii == null) return 0;
return ii.intValue();
}

/** User2_ID AD_Reference_ID=137 */
public static final int USER2_ID_AD_Reference_ID=137;
/** Set User List 2.
@param User2_ID User defined list element #2 */
public void setUser2_ID (int User2_ID)
{
if (User2_ID <= 0) set_Value ("User2_ID", null);
 else 
set_Value ("User2_ID", new Integer(User2_ID));
}
/** Get User List 2.
@return User defined list element #2 */
public int getUser2_ID() 
{
Integer ii = (Integer)get_Value("User2_ID");
if (ii == null) return 0;
return ii.intValue();
}
}
