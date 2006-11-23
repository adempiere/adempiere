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
/** Generated Model for PA_RatioElement
 *  @author Jorg Janke (generated) 
 *  @version Release 2.6.0a - $Id$ */
public class X_PA_RatioElement extends PO
{
/** Standard Constructor
@param ctx context
@param PA_RatioElement_ID id
@param trxName transaction
*/
public X_PA_RatioElement (Properties ctx, int PA_RatioElement_ID, String trxName)
{
super (ctx, PA_RatioElement_ID, trxName);
/** if (PA_RatioElement_ID == 0)
{
setName (null);
setPA_RatioElement_ID (0);
setPA_Ratio_ID (0);
setRatioElementType (null);
setRatioOperand (null);	// P
setSeqNo (0);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_PA_RatioElement (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=836 */
public static final int Table_ID=836;

/** TableName=PA_RatioElement */
public static final String Table_Name="PA_RatioElement";

protected static KeyNamePair Model = new KeyNamePair(836,"PA_RatioElement");

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
StringBuffer sb = new StringBuffer ("X_PA_RatioElement[").append(get_ID()).append("]");
return sb.toString();
}

/** Account_ID AD_Reference_ID=331 */
public static final int ACCOUNT_ID_AD_Reference_ID=331;
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
/** Set Constant Value.
@param ConstantValue Constant value */
public void setConstantValue (BigDecimal ConstantValue)
{
set_Value ("ConstantValue", ConstantValue);
}
/** Get Constant Value.
@return Constant value */
public BigDecimal getConstantValue() 
{
BigDecimal bd = (BigDecimal)get_Value("ConstantValue");
if (bd == null) return Env.ZERO;
return bd;
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
/** Set Name.
@param Name Alphanumeric identifier of the entity */
public void setName (String Name)
{
if (Name == null) throw new IllegalArgumentException ("Name is mandatory.");
if (Name.length() > 120)
{
log.warning("Length > 120 - truncated");
Name = Name.substring(0,119);
}
set_Value ("Name", Name);
}
/** Get Name.
@return Alphanumeric identifier of the entity */
public String getName() 
{
return (String)get_Value("Name");
}
/** Set Measure Calculation.
@param PA_MeasureCalc_ID Calculation method for measuring performance */
public void setPA_MeasureCalc_ID (int PA_MeasureCalc_ID)
{
if (PA_MeasureCalc_ID <= 0) set_Value ("PA_MeasureCalc_ID", null);
 else 
set_Value ("PA_MeasureCalc_ID", new Integer(PA_MeasureCalc_ID));
}
/** Get Measure Calculation.
@return Calculation method for measuring performance */
public int getPA_MeasureCalc_ID() 
{
Integer ii = (Integer)get_Value("PA_MeasureCalc_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Ratio Element.
@param PA_RatioElement_ID Performance Ratio Element */
public void setPA_RatioElement_ID (int PA_RatioElement_ID)
{
if (PA_RatioElement_ID < 1) throw new IllegalArgumentException ("PA_RatioElement_ID is mandatory.");
set_ValueNoCheck ("PA_RatioElement_ID", new Integer(PA_RatioElement_ID));
}
/** Get Ratio Element.
@return Performance Ratio Element */
public int getPA_RatioElement_ID() 
{
Integer ii = (Integer)get_Value("PA_RatioElement_ID");
if (ii == null) return 0;
return ii.intValue();
}

/** PA_RatioUsed_ID AD_Reference_ID=371 */
public static final int PA_RATIOUSED_ID_AD_Reference_ID=371;
/** Set Ratio Used.
@param PA_RatioUsed_ID Performace Ratio Used */
public void setPA_RatioUsed_ID (int PA_RatioUsed_ID)
{
if (PA_RatioUsed_ID <= 0) set_Value ("PA_RatioUsed_ID", null);
 else 
set_Value ("PA_RatioUsed_ID", new Integer(PA_RatioUsed_ID));
}
/** Get Ratio Used.
@return Performace Ratio Used */
public int getPA_RatioUsed_ID() 
{
Integer ii = (Integer)get_Value("PA_RatioUsed_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Ratio.
@param PA_Ratio_ID Performace Ratio */
public void setPA_Ratio_ID (int PA_Ratio_ID)
{
if (PA_Ratio_ID < 1) throw new IllegalArgumentException ("PA_Ratio_ID is mandatory.");
set_ValueNoCheck ("PA_Ratio_ID", new Integer(PA_Ratio_ID));
}
/** Get Ratio.
@return Performace Ratio */
public int getPA_Ratio_ID() 
{
Integer ii = (Integer)get_Value("PA_Ratio_ID");
if (ii == null) return 0;
return ii.intValue();
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

/** RatioElementType AD_Reference_ID=372 */
public static final int RATIOELEMENTTYPE_AD_Reference_ID=372;
/** Account Value = A */
public static final String RATIOELEMENTTYPE_AccountValue = "A";
/** Constant = C */
public static final String RATIOELEMENTTYPE_Constant = "C";
/** Ratio = R */
public static final String RATIOELEMENTTYPE_Ratio = "R";
/** Calculation = X */
public static final String RATIOELEMENTTYPE_Calculation = "X";
/** Set Element Type.
@param RatioElementType Ratio Element Type */
public void setRatioElementType (String RatioElementType)
{
if (RatioElementType == null) throw new IllegalArgumentException ("RatioElementType is mandatory");
if (RatioElementType.equals("A") || RatioElementType.equals("C") || RatioElementType.equals("R") || RatioElementType.equals("X"));
 else throw new IllegalArgumentException ("RatioElementType Invalid value - " + RatioElementType + " - Reference_ID=372 - A - C - R - X");
if (RatioElementType.length() > 1)
{
log.warning("Length > 1 - truncated");
RatioElementType = RatioElementType.substring(0,0);
}
set_Value ("RatioElementType", RatioElementType);
}
/** Get Element Type.
@return Ratio Element Type */
public String getRatioElementType() 
{
return (String)get_Value("RatioElementType");
}

/** RatioOperand AD_Reference_ID=373 */
public static final int RATIOOPERAND_AD_Reference_ID=373;
/** Divide = D */
public static final String RATIOOPERAND_Divide = "D";
/** Multiply = M */
public static final String RATIOOPERAND_Multiply = "M";
/** Minus = N */
public static final String RATIOOPERAND_Minus = "N";
/** Plus = P */
public static final String RATIOOPERAND_Plus = "P";
/** Set Operand.
@param RatioOperand Ratio Operand */
public void setRatioOperand (String RatioOperand)
{
if (RatioOperand == null) throw new IllegalArgumentException ("RatioOperand is mandatory");
if (RatioOperand.equals("D") || RatioOperand.equals("M") || RatioOperand.equals("N") || RatioOperand.equals("P"));
 else throw new IllegalArgumentException ("RatioOperand Invalid value - " + RatioOperand + " - Reference_ID=373 - D - M - N - P");
if (RatioOperand.length() > 1)
{
log.warning("Length > 1 - truncated");
RatioOperand = RatioOperand.substring(0,0);
}
set_Value ("RatioOperand", RatioOperand);
}
/** Get Operand.
@return Ratio Operand */
public String getRatioOperand() 
{
return (String)get_Value("RatioOperand");
}
/** Set Sequence.
@param SeqNo Method of ordering records;
 lowest number comes first */
public void setSeqNo (int SeqNo)
{
set_Value ("SeqNo", new Integer(SeqNo));
}
/** Get Sequence.
@return Method of ordering records;
 lowest number comes first */
public int getSeqNo() 
{
Integer ii = (Integer)get_Value("SeqNo");
if (ii == null) return 0;
return ii.intValue();
}
/** Get Record ID/ColumnName
@return ID/ColumnName pair
*/public KeyNamePair getKeyNamePair() 
{
return new KeyNamePair(get_ID(), String.valueOf(getSeqNo()));
}
}
