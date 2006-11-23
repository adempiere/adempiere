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
/** Generated Model for PA_ReportSource
 *  @author Jorg Janke (generated) 
 *  @version Release 2.6.0a - $Id$ */
public class X_PA_ReportSource extends PO
{
/** Standard Constructor
@param ctx context
@param PA_ReportSource_ID id
@param trxName transaction
*/
public X_PA_ReportSource (Properties ctx, int PA_ReportSource_ID, String trxName)
{
super (ctx, PA_ReportSource_ID, trxName);
/** if (PA_ReportSource_ID == 0)
{
setElementType (null);
setPA_ReportLine_ID (0);
setPA_ReportSource_ID (0);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_PA_ReportSource (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=450 */
public static final int Table_ID=450;

/** TableName=PA_ReportSource */
public static final String Table_Name="PA_ReportSource";

protected static KeyNamePair Model = new KeyNamePair(450,"PA_ReportSource");

protected BigDecimal accessLevel = new BigDecimal(3);
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
StringBuffer sb = new StringBuffer ("X_PA_ReportSource[").append(get_ID()).append("]");
return sb.toString();
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

/** C_ElementValue_ID AD_Reference_ID=182 */
public static final int C_ELEMENTVALUE_ID_AD_Reference_ID=182;
/** Set Account Element.
@param C_ElementValue_ID Account Element */
public void setC_ElementValue_ID (int C_ElementValue_ID)
{
if (C_ElementValue_ID <= 0) set_Value ("C_ElementValue_ID", null);
 else 
set_Value ("C_ElementValue_ID", new Integer(C_ElementValue_ID));
}
/** Get Account Element.
@return Account Element */
public int getC_ElementValue_ID() 
{
Integer ii = (Integer)get_Value("C_ElementValue_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Address.
@param C_Location_ID Location or Address */
public void setC_Location_ID (int C_Location_ID)
{
if (C_Location_ID <= 0) set_Value ("C_Location_ID", null);
 else 
set_Value ("C_Location_ID", new Integer(C_Location_ID));
}
/** Get Address.
@return Location or Address */
public int getC_Location_ID() 
{
Integer ii = (Integer)get_Value("C_Location_ID");
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

/** ElementType AD_Reference_ID=181 */
public static final int ELEMENTTYPE_AD_Reference_ID=181;
/** Account = AC */
public static final String ELEMENTTYPE_Account = "AC";
/** Activity = AY */
public static final String ELEMENTTYPE_Activity = "AY";
/** BPartner = BP */
public static final String ELEMENTTYPE_BPartner = "BP";
/** Location From = LF */
public static final String ELEMENTTYPE_LocationFrom = "LF";
/** Location To = LT */
public static final String ELEMENTTYPE_LocationTo = "LT";
/** Campaign = MC */
public static final String ELEMENTTYPE_Campaign = "MC";
/** Organization = OO */
public static final String ELEMENTTYPE_Organization = "OO";
/** Org Trx = OT */
public static final String ELEMENTTYPE_OrgTrx = "OT";
/** Project = PJ */
public static final String ELEMENTTYPE_Project = "PJ";
/** Product = PR */
public static final String ELEMENTTYPE_Product = "PR";
/** Sub Account = SA */
public static final String ELEMENTTYPE_SubAccount = "SA";
/** Sales Region = SR */
public static final String ELEMENTTYPE_SalesRegion = "SR";
/** User List 1 = U1 */
public static final String ELEMENTTYPE_UserList1 = "U1";
/** User List 2 = U2 */
public static final String ELEMENTTYPE_UserList2 = "U2";
/** User Element 1 = X1 */
public static final String ELEMENTTYPE_UserElement1 = "X1";
/** User Element 2 = X2 */
public static final String ELEMENTTYPE_UserElement2 = "X2";
/** Set Type.
@param ElementType Element Type (account or user defined) */
public void setElementType (String ElementType)
{
if (ElementType == null) throw new IllegalArgumentException ("ElementType is mandatory");
if (ElementType.equals("AC") || ElementType.equals("AY") || ElementType.equals("BP") || ElementType.equals("LF") || ElementType.equals("LT") || ElementType.equals("MC") || ElementType.equals("OO") || ElementType.equals("OT") || ElementType.equals("PJ") || ElementType.equals("PR") || ElementType.equals("SA") || ElementType.equals("SR") || ElementType.equals("U1") || ElementType.equals("U2") || ElementType.equals("X1") || ElementType.equals("X2"));
 else throw new IllegalArgumentException ("ElementType Invalid value - " + ElementType + " - Reference_ID=181 - AC - AY - BP - LF - LT - MC - OO - OT - PJ - PR - SA - SR - U1 - U2 - X1 - X2");
if (ElementType.length() > 2)
{
log.warning("Length > 2 - truncated");
ElementType = ElementType.substring(0,1);
}
set_Value ("ElementType", ElementType);
}
/** Get Type.
@return Element Type (account or user defined) */
public String getElementType() 
{
return (String)get_Value("ElementType");
}
/** Get Record ID/ColumnName
@return ID/ColumnName pair
*/public KeyNamePair getKeyNamePair() 
{
return new KeyNamePair(get_ID(), String.valueOf(getElementType()));
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

/** Org_ID AD_Reference_ID=322 */
public static final int ORG_ID_AD_Reference_ID=322;
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
/** Set Report Line.
@param PA_ReportLine_ID Report Line */
public void setPA_ReportLine_ID (int PA_ReportLine_ID)
{
if (PA_ReportLine_ID < 1) throw new IllegalArgumentException ("PA_ReportLine_ID is mandatory.");
set_ValueNoCheck ("PA_ReportLine_ID", new Integer(PA_ReportLine_ID));
}
/** Get Report Line.
@return Report Line */
public int getPA_ReportLine_ID() 
{
Integer ii = (Integer)get_Value("PA_ReportLine_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Report Source.
@param PA_ReportSource_ID Restriction of what will be shown in Report Line */
public void setPA_ReportSource_ID (int PA_ReportSource_ID)
{
if (PA_ReportSource_ID < 1) throw new IllegalArgumentException ("PA_ReportSource_ID is mandatory.");
set_ValueNoCheck ("PA_ReportSource_ID", new Integer(PA_ReportSource_ID));
}
/** Get Report Source.
@return Restriction of what will be shown in Report Line */
public int getPA_ReportSource_ID() 
{
Integer ii = (Integer)get_Value("PA_ReportSource_ID");
if (ii == null) return 0;
return ii.intValue();
}
}
