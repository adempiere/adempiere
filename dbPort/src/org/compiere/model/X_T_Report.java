/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                        *
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
/** Generated Model for T_Report
 *  @author Jorg Janke (generated) 
 *  @version Release 2.5.3d - 2006-10-10 21:56:03.765 */
public class X_T_Report extends PO
{
/** Standard Constructor
@param ctx context
@param T_Report_ID id
@param trxName transaction
*/
public X_T_Report (Properties ctx, int T_Report_ID, String trxName)
{
super (ctx, T_Report_ID, trxName);
/** if (T_Report_ID == 0)
{
setAD_PInstance_ID (0);
setFact_Acct_ID (0);
setPA_ReportLine_ID (0);
setRecord_ID (0);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_T_Report (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=544 */
public static final int Table_ID=544;

/** TableName=T_Report */
public static final String Table_Name="T_Report";

protected static KeyNamePair Model = new KeyNamePair(544,"T_Report");

protected BigDecimal accessLevel = new BigDecimal(4);
/** AccessLevel
@return 4 - System 
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
StringBuffer sb = new StringBuffer ("X_T_Report[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Process Instance.
@param AD_PInstance_ID Instance of the process */
public void setAD_PInstance_ID (int AD_PInstance_ID)
{
if (AD_PInstance_ID < 1) throw new IllegalArgumentException ("AD_PInstance_ID is mandatory.");
set_ValueNoCheck ("AD_PInstance_ID", new Integer(AD_PInstance_ID));
}
/** Get Process Instance.
@return Instance of the process */
public int getAD_PInstance_ID() 
{
Integer ii = (Integer)get_Value("AD_PInstance_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Col_0.
@param Col_0 Col_0 */
public void setCol_0 (BigDecimal Col_0)
{
set_ValueNoCheck ("Col_0", Col_0);
}
/** Get Col_0.
@return Col_0 */
public BigDecimal getCol_0() 
{
BigDecimal bd = (BigDecimal)get_Value("Col_0");
if (bd == null) return Env.ZERO;
return bd;
}
/** Set Col_1.
@param Col_1 Col_1 */
public void setCol_1 (BigDecimal Col_1)
{
set_ValueNoCheck ("Col_1", Col_1);
}
/** Get Col_1.
@return Col_1 */
public BigDecimal getCol_1() 
{
BigDecimal bd = (BigDecimal)get_Value("Col_1");
if (bd == null) return Env.ZERO;
return bd;
}
/** Set Col_10.
@param Col_10 Col_10 */
public void setCol_10 (BigDecimal Col_10)
{
set_ValueNoCheck ("Col_10", Col_10);
}
/** Get Col_10.
@return Col_10 */
public BigDecimal getCol_10() 
{
BigDecimal bd = (BigDecimal)get_Value("Col_10");
if (bd == null) return Env.ZERO;
return bd;
}
/** Set Col_11.
@param Col_11 Col_11 */
public void setCol_11 (BigDecimal Col_11)
{
set_ValueNoCheck ("Col_11", Col_11);
}
/** Get Col_11.
@return Col_11 */
public BigDecimal getCol_11() 
{
BigDecimal bd = (BigDecimal)get_Value("Col_11");
if (bd == null) return Env.ZERO;
return bd;
}
/** Set Col_12.
@param Col_12 Col_12 */
public void setCol_12 (BigDecimal Col_12)
{
set_ValueNoCheck ("Col_12", Col_12);
}
/** Get Col_12.
@return Col_12 */
public BigDecimal getCol_12() 
{
BigDecimal bd = (BigDecimal)get_Value("Col_12");
if (bd == null) return Env.ZERO;
return bd;
}
/** Set Col_13.
@param Col_13 Col_13 */
public void setCol_13 (BigDecimal Col_13)
{
set_ValueNoCheck ("Col_13", Col_13);
}
/** Get Col_13.
@return Col_13 */
public BigDecimal getCol_13() 
{
BigDecimal bd = (BigDecimal)get_Value("Col_13");
if (bd == null) return Env.ZERO;
return bd;
}
/** Set Col_14.
@param Col_14 Col_14 */
public void setCol_14 (BigDecimal Col_14)
{
set_ValueNoCheck ("Col_14", Col_14);
}
/** Get Col_14.
@return Col_14 */
public BigDecimal getCol_14() 
{
BigDecimal bd = (BigDecimal)get_Value("Col_14");
if (bd == null) return Env.ZERO;
return bd;
}
/** Set Col_15.
@param Col_15 Col_15 */
public void setCol_15 (BigDecimal Col_15)
{
set_ValueNoCheck ("Col_15", Col_15);
}
/** Get Col_15.
@return Col_15 */
public BigDecimal getCol_15() 
{
BigDecimal bd = (BigDecimal)get_Value("Col_15");
if (bd == null) return Env.ZERO;
return bd;
}
/** Set Col_16.
@param Col_16 Col_16 */
public void setCol_16 (BigDecimal Col_16)
{
set_ValueNoCheck ("Col_16", Col_16);
}
/** Get Col_16.
@return Col_16 */
public BigDecimal getCol_16() 
{
BigDecimal bd = (BigDecimal)get_Value("Col_16");
if (bd == null) return Env.ZERO;
return bd;
}
/** Set Col_17.
@param Col_17 Col_17 */
public void setCol_17 (BigDecimal Col_17)
{
set_ValueNoCheck ("Col_17", Col_17);
}
/** Get Col_17.
@return Col_17 */
public BigDecimal getCol_17() 
{
BigDecimal bd = (BigDecimal)get_Value("Col_17");
if (bd == null) return Env.ZERO;
return bd;
}
/** Set Col_18.
@param Col_18 Col_18 */
public void setCol_18 (BigDecimal Col_18)
{
set_ValueNoCheck ("Col_18", Col_18);
}
/** Get Col_18.
@return Col_18 */
public BigDecimal getCol_18() 
{
BigDecimal bd = (BigDecimal)get_Value("Col_18");
if (bd == null) return Env.ZERO;
return bd;
}
/** Set Col_19.
@param Col_19 Col_19 */
public void setCol_19 (BigDecimal Col_19)
{
set_ValueNoCheck ("Col_19", Col_19);
}
/** Get Col_19.
@return Col_19 */
public BigDecimal getCol_19() 
{
BigDecimal bd = (BigDecimal)get_Value("Col_19");
if (bd == null) return Env.ZERO;
return bd;
}
/** Set Col_2.
@param Col_2 Col_2 */
public void setCol_2 (BigDecimal Col_2)
{
set_ValueNoCheck ("Col_2", Col_2);
}
/** Get Col_2.
@return Col_2 */
public BigDecimal getCol_2() 
{
BigDecimal bd = (BigDecimal)get_Value("Col_2");
if (bd == null) return Env.ZERO;
return bd;
}
/** Set Col_20.
@param Col_20 Col_20 */
public void setCol_20 (BigDecimal Col_20)
{
set_ValueNoCheck ("Col_20", Col_20);
}
/** Get Col_20.
@return Col_20 */
public BigDecimal getCol_20() 
{
BigDecimal bd = (BigDecimal)get_Value("Col_20");
if (bd == null) return Env.ZERO;
return bd;
}
/** Set Col_3.
@param Col_3 Col_3 */
public void setCol_3 (BigDecimal Col_3)
{
set_ValueNoCheck ("Col_3", Col_3);
}
/** Get Col_3.
@return Col_3 */
public BigDecimal getCol_3() 
{
BigDecimal bd = (BigDecimal)get_Value("Col_3");
if (bd == null) return Env.ZERO;
return bd;
}
/** Set Col_4.
@param Col_4 Col_4 */
public void setCol_4 (BigDecimal Col_4)
{
set_ValueNoCheck ("Col_4", Col_4);
}
/** Get Col_4.
@return Col_4 */
public BigDecimal getCol_4() 
{
BigDecimal bd = (BigDecimal)get_Value("Col_4");
if (bd == null) return Env.ZERO;
return bd;
}
/** Set Col_5.
@param Col_5 Col_5 */
public void setCol_5 (BigDecimal Col_5)
{
set_ValueNoCheck ("Col_5", Col_5);
}
/** Get Col_5.
@return Col_5 */
public BigDecimal getCol_5() 
{
BigDecimal bd = (BigDecimal)get_Value("Col_5");
if (bd == null) return Env.ZERO;
return bd;
}
/** Set Col_6.
@param Col_6 Col_6 */
public void setCol_6 (BigDecimal Col_6)
{
set_ValueNoCheck ("Col_6", Col_6);
}
/** Get Col_6.
@return Col_6 */
public BigDecimal getCol_6() 
{
BigDecimal bd = (BigDecimal)get_Value("Col_6");
if (bd == null) return Env.ZERO;
return bd;
}
/** Set Col_7.
@param Col_7 Col_7 */
public void setCol_7 (BigDecimal Col_7)
{
set_ValueNoCheck ("Col_7", Col_7);
}
/** Get Col_7.
@return Col_7 */
public BigDecimal getCol_7() 
{
BigDecimal bd = (BigDecimal)get_Value("Col_7");
if (bd == null) return Env.ZERO;
return bd;
}
/** Set Col_8.
@param Col_8 Col_8 */
public void setCol_8 (BigDecimal Col_8)
{
set_ValueNoCheck ("Col_8", Col_8);
}
/** Get Col_8.
@return Col_8 */
public BigDecimal getCol_8() 
{
BigDecimal bd = (BigDecimal)get_Value("Col_8");
if (bd == null) return Env.ZERO;
return bd;
}
/** Set Col_9.
@param Col_9 Col_9 */
public void setCol_9 (BigDecimal Col_9)
{
set_ValueNoCheck ("Col_9", Col_9);
}
/** Get Col_9.
@return Col_9 */
public BigDecimal getCol_9() 
{
BigDecimal bd = (BigDecimal)get_Value("Col_9");
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
set_ValueNoCheck ("Description", Description);
}
/** Get Description.
@return Optional short description of the record */
public String getDescription() 
{
return (String)get_Value("Description");
}
/** Set Accounting Fact.
@param Fact_Acct_ID Accounting Fact */
public void setFact_Acct_ID (int Fact_Acct_ID)
{
if (Fact_Acct_ID < 1) throw new IllegalArgumentException ("Fact_Acct_ID is mandatory.");
set_ValueNoCheck ("Fact_Acct_ID", new Integer(Fact_Acct_ID));
}
/** Get Accounting Fact.
@return Accounting Fact */
public int getFact_Acct_ID() 
{
Integer ii = (Integer)get_Value("Fact_Acct_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Level no.
@param LevelNo Level no */
public void setLevelNo (int LevelNo)
{
set_ValueNoCheck ("LevelNo", new Integer(LevelNo));
}
/** Get Level no.
@return Level no */
public int getLevelNo() 
{
Integer ii = (Integer)get_Value("LevelNo");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Name.
@param Name Alphanumeric identifier of the entity */
public void setName (String Name)
{
if (Name != null && Name.length() > 60)
{
log.warning("Length > 60 - truncated");
Name = Name.substring(0,59);
}
set_ValueNoCheck ("Name", Name);
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
/** Set Record ID.
@param Record_ID Direct internal record ID */
public void setRecord_ID (int Record_ID)
{
if (Record_ID < 0) throw new IllegalArgumentException ("Record_ID is mandatory.");
set_ValueNoCheck ("Record_ID", new Integer(Record_ID));
}
/** Get Record ID.
@return Direct internal record ID */
public int getRecord_ID() 
{
Integer ii = (Integer)get_Value("Record_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Sequence.
@param SeqNo Method of ordering records;
 lowest number comes first */
public void setSeqNo (int SeqNo)
{
set_ValueNoCheck ("SeqNo", new Integer(SeqNo));
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
}
