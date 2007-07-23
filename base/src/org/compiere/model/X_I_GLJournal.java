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
/** Generated Model for I_GLJournal
 *  @author Adempiere (generated) 
 *  @version Release 3.3.0 - $Id$ */
public class X_I_GLJournal extends PO
{
/** Standard Constructor
@param ctx context
@param I_GLJournal_ID id
@param trxName transaction
*/
public X_I_GLJournal (Properties ctx, int I_GLJournal_ID, String trxName)
{
super (ctx, I_GLJournal_ID, trxName);
/** if (I_GLJournal_ID == 0)
{
setI_GLJournal_ID (0);
setI_IsImported (false);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_I_GLJournal (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** TableName=I_GLJournal */
public static final String Table_Name="I_GLJournal";

/** AD_Table_ID=599 */
public static final int Table_ID=MTable.getTable_ID(Table_Name);

protected static KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

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
StringBuffer sb = new StringBuffer ("X_I_GLJournal[").append(get_ID()).append("]");
return sb.toString();
}

/** AD_OrgDoc_ID AD_Reference_ID=130 */
public static final int AD_ORGDOC_ID_AD_Reference_ID=130;
/** Set Document Org.
@param AD_OrgDoc_ID Document Organization (independent from account organization) */
public void setAD_OrgDoc_ID (int AD_OrgDoc_ID)
{
if (AD_OrgDoc_ID <= 0) set_Value ("AD_OrgDoc_ID", null);
 else 
set_Value ("AD_OrgDoc_ID", Integer.valueOf(AD_OrgDoc_ID));
}
/** Get Document Org.
@return Document Organization (independent from account organization) */
public int getAD_OrgDoc_ID() 
{
Integer ii = (Integer)get_Value("AD_OrgDoc_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name AD_OrgDoc_ID */
public static final String COLUMNNAME_AD_OrgDoc_ID = "AD_OrgDoc_ID";

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
/** Set Account Key.
@param AccountValue Key of Account Element */
public void setAccountValue (String AccountValue)
{
if (AccountValue != null && AccountValue.length() > 40)
{
log.warning("Length > 40 - truncated");
AccountValue = AccountValue.substring(0,39);
}
set_Value ("AccountValue", AccountValue);
}
/** Get Account Key.
@return Key of Account Element */
public String getAccountValue() 
{
return (String)get_Value("AccountValue");
}
/** Column name AccountValue */
public static final String COLUMNNAME_AccountValue = "AccountValue";

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
/** Set Account Schema Name.
@param AcctSchemaName Name of the Accounting Schema */
public void setAcctSchemaName (String AcctSchemaName)
{
if (AcctSchemaName != null && AcctSchemaName.length() > 60)
{
log.warning("Length > 60 - truncated");
AcctSchemaName = AcctSchemaName.substring(0,59);
}
set_Value ("AcctSchemaName", AcctSchemaName);
}
/** Get Account Schema Name.
@return Name of the Accounting Schema */
public String getAcctSchemaName() 
{
return (String)get_Value("AcctSchemaName");
}
/** Column name AcctSchemaName */
public static final String COLUMNNAME_AcctSchemaName = "AcctSchemaName";
/** Set Accounted Credit.
@param AmtAcctCr Accounted Credit Amount */
public void setAmtAcctCr (BigDecimal AmtAcctCr)
{
set_Value ("AmtAcctCr", AmtAcctCr);
}
/** Get Accounted Credit.
@return Accounted Credit Amount */
public BigDecimal getAmtAcctCr() 
{
BigDecimal bd = (BigDecimal)get_Value("AmtAcctCr");
if (bd == null) return Env.ZERO;
return bd;
}
/** Column name AmtAcctCr */
public static final String COLUMNNAME_AmtAcctCr = "AmtAcctCr";
/** Set Accounted Debit.
@param AmtAcctDr Accounted Debit Amount */
public void setAmtAcctDr (BigDecimal AmtAcctDr)
{
set_Value ("AmtAcctDr", AmtAcctDr);
}
/** Get Accounted Debit.
@return Accounted Debit Amount */
public BigDecimal getAmtAcctDr() 
{
BigDecimal bd = (BigDecimal)get_Value("AmtAcctDr");
if (bd == null) return Env.ZERO;
return bd;
}
/** Column name AmtAcctDr */
public static final String COLUMNNAME_AmtAcctDr = "AmtAcctDr";
/** Set Source Credit.
@param AmtSourceCr Source Credit Amount */
public void setAmtSourceCr (BigDecimal AmtSourceCr)
{
set_Value ("AmtSourceCr", AmtSourceCr);
}
/** Get Source Credit.
@return Source Credit Amount */
public BigDecimal getAmtSourceCr() 
{
BigDecimal bd = (BigDecimal)get_Value("AmtSourceCr");
if (bd == null) return Env.ZERO;
return bd;
}
/** Column name AmtSourceCr */
public static final String COLUMNNAME_AmtSourceCr = "AmtSourceCr";
/** Set Source Debit.
@param AmtSourceDr Source Debit Amount */
public void setAmtSourceDr (BigDecimal AmtSourceDr)
{
set_Value ("AmtSourceDr", AmtSourceDr);
}
/** Get Source Debit.
@return Source Debit Amount */
public BigDecimal getAmtSourceDr() 
{
BigDecimal bd = (BigDecimal)get_Value("AmtSourceDr");
if (bd == null) return Env.ZERO;
return bd;
}
/** Column name AmtSourceDr */
public static final String COLUMNNAME_AmtSourceDr = "AmtSourceDr";
/** Set Business Partner Key.
@param BPartnerValue Key of the Business Partner */
public void setBPartnerValue (String BPartnerValue)
{
if (BPartnerValue != null && BPartnerValue.length() > 40)
{
log.warning("Length > 40 - truncated");
BPartnerValue = BPartnerValue.substring(0,39);
}
set_Value ("BPartnerValue", BPartnerValue);
}
/** Get Business Partner Key.
@return Key of the Business Partner */
public String getBPartnerValue() 
{
return (String)get_Value("BPartnerValue");
}
/** Column name BPartnerValue */
public static final String COLUMNNAME_BPartnerValue = "BPartnerValue";
/** Set Batch Description.
@param BatchDescription Description of the Batch */
public void setBatchDescription (String BatchDescription)
{
if (BatchDescription != null && BatchDescription.length() > 255)
{
log.warning("Length > 255 - truncated");
BatchDescription = BatchDescription.substring(0,254);
}
set_Value ("BatchDescription", BatchDescription);
}
/** Get Batch Description.
@return Description of the Batch */
public String getBatchDescription() 
{
return (String)get_Value("BatchDescription");
}
/** Column name BatchDescription */
public static final String COLUMNNAME_BatchDescription = "BatchDescription";
/** Set Batch Document No.
@param BatchDocumentNo Document Number of the Batch */
public void setBatchDocumentNo (String BatchDocumentNo)
{
if (BatchDocumentNo != null && BatchDocumentNo.length() > 30)
{
log.warning("Length > 30 - truncated");
BatchDocumentNo = BatchDocumentNo.substring(0,29);
}
set_Value ("BatchDocumentNo", BatchDocumentNo);
}
/** Get Batch Document No.
@return Document Number of the Batch */
public String getBatchDocumentNo() 
{
return (String)get_Value("BatchDocumentNo");
}
/** Column name BatchDocumentNo */
public static final String COLUMNNAME_BatchDocumentNo = "BatchDocumentNo";
/** Set Accounting Schema.
@param C_AcctSchema_ID Rules for accounting */
public void setC_AcctSchema_ID (int C_AcctSchema_ID)
{
if (C_AcctSchema_ID <= 0) set_Value ("C_AcctSchema_ID", null);
 else 
set_Value ("C_AcctSchema_ID", Integer.valueOf(C_AcctSchema_ID));
}
/** Get Accounting Schema.
@return Rules for accounting */
public int getC_AcctSchema_ID() 
{
Integer ii = (Integer)get_Value("C_AcctSchema_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_AcctSchema_ID */
public static final String COLUMNNAME_C_AcctSchema_ID = "C_AcctSchema_ID";
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
/** Set Currency Type.
@param C_ConversionType_ID Currency Conversion Rate Type */
public void setC_ConversionType_ID (int C_ConversionType_ID)
{
if (C_ConversionType_ID <= 0) set_Value ("C_ConversionType_ID", null);
 else 
set_Value ("C_ConversionType_ID", Integer.valueOf(C_ConversionType_ID));
}
/** Get Currency Type.
@return Currency Conversion Rate Type */
public int getC_ConversionType_ID() 
{
Integer ii = (Integer)get_Value("C_ConversionType_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_ConversionType_ID */
public static final String COLUMNNAME_C_ConversionType_ID = "C_ConversionType_ID";
/** Set Currency.
@param C_Currency_ID The Currency for this record */
public void setC_Currency_ID (int C_Currency_ID)
{
if (C_Currency_ID <= 0) set_Value ("C_Currency_ID", null);
 else 
set_Value ("C_Currency_ID", Integer.valueOf(C_Currency_ID));
}
/** Get Currency.
@return The Currency for this record */
public int getC_Currency_ID() 
{
Integer ii = (Integer)get_Value("C_Currency_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_Currency_ID */
public static final String COLUMNNAME_C_Currency_ID = "C_Currency_ID";
/** Set Document Type.
@param C_DocType_ID Document type or rules */
public void setC_DocType_ID (int C_DocType_ID)
{
if (C_DocType_ID <= 0) set_Value ("C_DocType_ID", null);
 else 
set_Value ("C_DocType_ID", Integer.valueOf(C_DocType_ID));
}
/** Get Document Type.
@return Document type or rules */
public int getC_DocType_ID() 
{
Integer ii = (Integer)get_Value("C_DocType_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_DocType_ID */
public static final String COLUMNNAME_C_DocType_ID = "C_DocType_ID";

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
/** Set Period.
@param C_Period_ID Period of the Calendar */
public void setC_Period_ID (int C_Period_ID)
{
if (C_Period_ID <= 0) set_Value ("C_Period_ID", null);
 else 
set_Value ("C_Period_ID", Integer.valueOf(C_Period_ID));
}
/** Get Period.
@return Period of the Calendar */
public int getC_Period_ID() 
{
Integer ii = (Integer)get_Value("C_Period_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_Period_ID */
public static final String COLUMNNAME_C_Period_ID = "C_Period_ID";
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
/** Set UOM.
@param C_UOM_ID Unit of Measure */
public void setC_UOM_ID (int C_UOM_ID)
{
if (C_UOM_ID <= 0) set_Value ("C_UOM_ID", null);
 else 
set_Value ("C_UOM_ID", Integer.valueOf(C_UOM_ID));
}
/** Get UOM.
@return Unit of Measure */
public int getC_UOM_ID() 
{
Integer ii = (Integer)get_Value("C_UOM_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_UOM_ID */
public static final String COLUMNNAME_C_UOM_ID = "C_UOM_ID";
/** Set Combination.
@param C_ValidCombination_ID Valid Account Combination */
public void setC_ValidCombination_ID (int C_ValidCombination_ID)
{
if (C_ValidCombination_ID <= 0) set_Value ("C_ValidCombination_ID", null);
 else 
set_Value ("C_ValidCombination_ID", Integer.valueOf(C_ValidCombination_ID));
}
/** Get Combination.
@return Valid Account Combination */
public int getC_ValidCombination_ID() 
{
Integer ii = (Integer)get_Value("C_ValidCombination_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_ValidCombination_ID */
public static final String COLUMNNAME_C_ValidCombination_ID = "C_ValidCombination_ID";
/** Set Category Name.
@param CategoryName Name of the Category */
public void setCategoryName (String CategoryName)
{
if (CategoryName != null && CategoryName.length() > 60)
{
log.warning("Length > 60 - truncated");
CategoryName = CategoryName.substring(0,59);
}
set_Value ("CategoryName", CategoryName);
}
/** Get Category Name.
@return Name of the Category */
public String getCategoryName() 
{
return (String)get_Value("CategoryName");
}
/** Column name CategoryName */
public static final String COLUMNNAME_CategoryName = "CategoryName";
/** Set Client Key.
@param ClientValue Key of the Client */
public void setClientValue (String ClientValue)
{
if (ClientValue != null && ClientValue.length() > 40)
{
log.warning("Length > 40 - truncated");
ClientValue = ClientValue.substring(0,39);
}
set_Value ("ClientValue", ClientValue);
}
/** Get Client Key.
@return Key of the Client */
public String getClientValue() 
{
return (String)get_Value("ClientValue");
}
/** Column name ClientValue */
public static final String COLUMNNAME_ClientValue = "ClientValue";
/** Set Currency Type Key.
@param ConversionTypeValue Key value for the Currency Conversion Rate Type */
public void setConversionTypeValue (String ConversionTypeValue)
{
if (ConversionTypeValue != null && ConversionTypeValue.length() > 40)
{
log.warning("Length > 40 - truncated");
ConversionTypeValue = ConversionTypeValue.substring(0,39);
}
set_Value ("ConversionTypeValue", ConversionTypeValue);
}
/** Get Currency Type Key.
@return Key value for the Currency Conversion Rate Type */
public String getConversionTypeValue() 
{
return (String)get_Value("ConversionTypeValue");
}
/** Column name ConversionTypeValue */
public static final String COLUMNNAME_ConversionTypeValue = "ConversionTypeValue";
/** Set Rate.
@param CurrencyRate Currency Conversion Rate */
public void setCurrencyRate (BigDecimal CurrencyRate)
{
set_Value ("CurrencyRate", CurrencyRate);
}
/** Get Rate.
@return Currency Conversion Rate */
public BigDecimal getCurrencyRate() 
{
BigDecimal bd = (BigDecimal)get_Value("CurrencyRate");
if (bd == null) return Env.ZERO;
return bd;
}
/** Column name CurrencyRate */
public static final String COLUMNNAME_CurrencyRate = "CurrencyRate";
/** Set Account Date.
@param DateAcct Accounting Date */
public void setDateAcct (Timestamp DateAcct)
{
set_Value ("DateAcct", DateAcct);
}
/** Get Account Date.
@return Accounting Date */
public Timestamp getDateAcct() 
{
return (Timestamp)get_Value("DateAcct");
}
/** Column name DateAcct */
public static final String COLUMNNAME_DateAcct = "DateAcct";
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
/** Set Document Type Name.
@param DocTypeName Name of the Document Type */
public void setDocTypeName (String DocTypeName)
{
if (DocTypeName != null && DocTypeName.length() > 60)
{
log.warning("Length > 60 - truncated");
DocTypeName = DocTypeName.substring(0,59);
}
set_Value ("DocTypeName", DocTypeName);
}
/** Get Document Type Name.
@return Name of the Document Type */
public String getDocTypeName() 
{
return (String)get_Value("DocTypeName");
}
/** Column name DocTypeName */
public static final String COLUMNNAME_DocTypeName = "DocTypeName";
/** Set Budget.
@param GL_Budget_ID General Ledger Budget */
public void setGL_Budget_ID (int GL_Budget_ID)
{
if (GL_Budget_ID <= 0) set_Value ("GL_Budget_ID", null);
 else 
set_Value ("GL_Budget_ID", Integer.valueOf(GL_Budget_ID));
}
/** Get Budget.
@return General Ledger Budget */
public int getGL_Budget_ID() 
{
Integer ii = (Integer)get_Value("GL_Budget_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name GL_Budget_ID */
public static final String COLUMNNAME_GL_Budget_ID = "GL_Budget_ID";
/** Set GL Category.
@param GL_Category_ID General Ledger Category */
public void setGL_Category_ID (int GL_Category_ID)
{
if (GL_Category_ID <= 0) set_Value ("GL_Category_ID", null);
 else 
set_Value ("GL_Category_ID", Integer.valueOf(GL_Category_ID));
}
/** Get GL Category.
@return General Ledger Category */
public int getGL_Category_ID() 
{
Integer ii = (Integer)get_Value("GL_Category_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name GL_Category_ID */
public static final String COLUMNNAME_GL_Category_ID = "GL_Category_ID";
/** Set Journal Batch.
@param GL_JournalBatch_ID General Ledger Journal Batch */
public void setGL_JournalBatch_ID (int GL_JournalBatch_ID)
{
if (GL_JournalBatch_ID <= 0) set_Value ("GL_JournalBatch_ID", null);
 else 
set_Value ("GL_JournalBatch_ID", Integer.valueOf(GL_JournalBatch_ID));
}
/** Get Journal Batch.
@return General Ledger Journal Batch */
public int getGL_JournalBatch_ID() 
{
Integer ii = (Integer)get_Value("GL_JournalBatch_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name GL_JournalBatch_ID */
public static final String COLUMNNAME_GL_JournalBatch_ID = "GL_JournalBatch_ID";
/** Set Journal Line.
@param GL_JournalLine_ID General Ledger Journal Line */
public void setGL_JournalLine_ID (int GL_JournalLine_ID)
{
if (GL_JournalLine_ID <= 0) set_Value ("GL_JournalLine_ID", null);
 else 
set_Value ("GL_JournalLine_ID", Integer.valueOf(GL_JournalLine_ID));
}
/** Get Journal Line.
@return General Ledger Journal Line */
public int getGL_JournalLine_ID() 
{
Integer ii = (Integer)get_Value("GL_JournalLine_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name GL_JournalLine_ID */
public static final String COLUMNNAME_GL_JournalLine_ID = "GL_JournalLine_ID";
/** Set Journal.
@param GL_Journal_ID General Ledger Journal */
public void setGL_Journal_ID (int GL_Journal_ID)
{
if (GL_Journal_ID <= 0) set_Value ("GL_Journal_ID", null);
 else 
set_Value ("GL_Journal_ID", Integer.valueOf(GL_Journal_ID));
}
/** Get Journal.
@return General Ledger Journal */
public int getGL_Journal_ID() 
{
Integer ii = (Integer)get_Value("GL_Journal_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name GL_Journal_ID */
public static final String COLUMNNAME_GL_Journal_ID = "GL_Journal_ID";
/** Set ISO Currency Code.
@param ISO_Code Three letter ISO 4217 Code of the Currency */
public void setISO_Code (String ISO_Code)
{
if (ISO_Code != null && ISO_Code.length() > 3)
{
log.warning("Length > 3 - truncated");
ISO_Code = ISO_Code.substring(0,2);
}
set_Value ("ISO_Code", ISO_Code);
}
/** Get ISO Currency Code.
@return Three letter ISO 4217 Code of the Currency */
public String getISO_Code() 
{
return (String)get_Value("ISO_Code");
}
/** Column name ISO_Code */
public static final String COLUMNNAME_ISO_Code = "ISO_Code";
/** Set Import Error Message.
@param I_ErrorMsg Messages generated from import process */
public void setI_ErrorMsg (String I_ErrorMsg)
{
if (I_ErrorMsg != null && I_ErrorMsg.length() > 2000)
{
log.warning("Length > 2000 - truncated");
I_ErrorMsg = I_ErrorMsg.substring(0,1999);
}
set_Value ("I_ErrorMsg", I_ErrorMsg);
}
/** Get Import Error Message.
@return Messages generated from import process */
public String getI_ErrorMsg() 
{
return (String)get_Value("I_ErrorMsg");
}
/** Column name I_ErrorMsg */
public static final String COLUMNNAME_I_ErrorMsg = "I_ErrorMsg";
/** Set Import GL Journal.
@param I_GLJournal_ID Import General Ledger Journal */
public void setI_GLJournal_ID (int I_GLJournal_ID)
{
if (I_GLJournal_ID < 1) throw new IllegalArgumentException ("I_GLJournal_ID is mandatory.");
set_ValueNoCheck ("I_GLJournal_ID", Integer.valueOf(I_GLJournal_ID));
}
/** Get Import GL Journal.
@return Import General Ledger Journal */
public int getI_GLJournal_ID() 
{
Integer ii = (Integer)get_Value("I_GLJournal_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Get Record ID/ColumnName
@return ID/ColumnName pair
*/public KeyNamePair getKeyNamePair() 
{
return new KeyNamePair(get_ID(), String.valueOf(getI_GLJournal_ID()));
}
/** Column name I_GLJournal_ID */
public static final String COLUMNNAME_I_GLJournal_ID = "I_GLJournal_ID";
/** Set Imported.
@param I_IsImported Has this import been processed */
public void setI_IsImported (boolean I_IsImported)
{
set_Value ("I_IsImported", Boolean.valueOf(I_IsImported));
}
/** Get Imported.
@return Has this import been processed */
public boolean isI_IsImported() 
{
Object oo = get_Value("I_IsImported");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name I_IsImported */
public static final String COLUMNNAME_I_IsImported = "I_IsImported";
/** Set Create New Batch.
@param IsCreateNewBatch If selected a new batch is created */
public void setIsCreateNewBatch (boolean IsCreateNewBatch)
{
set_Value ("IsCreateNewBatch", Boolean.valueOf(IsCreateNewBatch));
}
/** Get Create New Batch.
@return If selected a new batch is created */
public boolean isCreateNewBatch() 
{
Object oo = get_Value("IsCreateNewBatch");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsCreateNewBatch */
public static final String COLUMNNAME_IsCreateNewBatch = "IsCreateNewBatch";
/** Set Create New Journal.
@param IsCreateNewJournal If selected a new journal within the batch is created */
public void setIsCreateNewJournal (boolean IsCreateNewJournal)
{
set_Value ("IsCreateNewJournal", Boolean.valueOf(IsCreateNewJournal));
}
/** Get Create New Journal.
@return If selected a new journal within the batch is created */
public boolean isCreateNewJournal() 
{
Object oo = get_Value("IsCreateNewJournal");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsCreateNewJournal */
public static final String COLUMNNAME_IsCreateNewJournal = "IsCreateNewJournal";
/** Set Journal Document No.
@param JournalDocumentNo Document number of the Journal */
public void setJournalDocumentNo (String JournalDocumentNo)
{
if (JournalDocumentNo != null && JournalDocumentNo.length() > 30)
{
log.warning("Length > 30 - truncated");
JournalDocumentNo = JournalDocumentNo.substring(0,29);
}
set_Value ("JournalDocumentNo", JournalDocumentNo);
}
/** Get Journal Document No.
@return Document number of the Journal */
public String getJournalDocumentNo() 
{
return (String)get_Value("JournalDocumentNo");
}
/** Column name JournalDocumentNo */
public static final String COLUMNNAME_JournalDocumentNo = "JournalDocumentNo";
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
/** Set Trx Org Key.
@param OrgTrxValue Key of the Transaction Organization */
public void setOrgTrxValue (String OrgTrxValue)
{
if (OrgTrxValue != null && OrgTrxValue.length() > 40)
{
log.warning("Length > 40 - truncated");
OrgTrxValue = OrgTrxValue.substring(0,39);
}
set_Value ("OrgTrxValue", OrgTrxValue);
}
/** Get Trx Org Key.
@return Key of the Transaction Organization */
public String getOrgTrxValue() 
{
return (String)get_Value("OrgTrxValue");
}
/** Column name OrgTrxValue */
public static final String COLUMNNAME_OrgTrxValue = "OrgTrxValue";
/** Set Org Key.
@param OrgValue Key of the Organization */
public void setOrgValue (String OrgValue)
{
if (OrgValue != null && OrgValue.length() > 40)
{
log.warning("Length > 40 - truncated");
OrgValue = OrgValue.substring(0,39);
}
set_Value ("OrgValue", OrgValue);
}
/** Get Org Key.
@return Key of the Organization */
public String getOrgValue() 
{
return (String)get_Value("OrgValue");
}
/** Column name OrgValue */
public static final String COLUMNNAME_OrgValue = "OrgValue";

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
/** Column name PostingType */
public static final String COLUMNNAME_PostingType = "PostingType";
/** Set Processed.
@param Processed The document has been processed */
public void setProcessed (boolean Processed)
{
set_Value ("Processed", Boolean.valueOf(Processed));
}
/** Get Processed.
@return The document has been processed */
public boolean isProcessed() 
{
Object oo = get_Value("Processed");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name Processed */
public static final String COLUMNNAME_Processed = "Processed";
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
/** Set Product Key.
@param ProductValue Key of the Product */
public void setProductValue (String ProductValue)
{
if (ProductValue != null && ProductValue.length() > 40)
{
log.warning("Length > 40 - truncated");
ProductValue = ProductValue.substring(0,39);
}
set_Value ("ProductValue", ProductValue);
}
/** Get Product Key.
@return Key of the Product */
public String getProductValue() 
{
return (String)get_Value("ProductValue");
}
/** Column name ProductValue */
public static final String COLUMNNAME_ProductValue = "ProductValue";
/** Set Project Key.
@param ProjectValue Key of the Project */
public void setProjectValue (String ProjectValue)
{
if (ProjectValue != null && ProjectValue.length() > 40)
{
log.warning("Length > 40 - truncated");
ProjectValue = ProjectValue.substring(0,39);
}
set_Value ("ProjectValue", ProjectValue);
}
/** Get Project Key.
@return Key of the Project */
public String getProjectValue() 
{
return (String)get_Value("ProjectValue");
}
/** Column name ProjectValue */
public static final String COLUMNNAME_ProjectValue = "ProjectValue";
/** Set Quantity.
@param Qty Quantity */
public void setQty (BigDecimal Qty)
{
set_Value ("Qty", Qty);
}
/** Get Quantity.
@return Quantity */
public BigDecimal getQty() 
{
BigDecimal bd = (BigDecimal)get_Value("Qty");
if (bd == null) return Env.ZERO;
return bd;
}
/** Column name Qty */
public static final String COLUMNNAME_Qty = "Qty";
/** Set SKU.
@param SKU Stock Keeping Unit */
public void setSKU (String SKU)
{
if (SKU != null && SKU.length() > 30)
{
log.warning("Length > 30 - truncated");
SKU = SKU.substring(0,29);
}
set_Value ("SKU", SKU);
}
/** Get SKU.
@return Stock Keeping Unit */
public String getSKU() 
{
return (String)get_Value("SKU");
}
/** Column name SKU */
public static final String COLUMNNAME_SKU = "SKU";
/** Set UPC/EAN.
@param UPC Bar Code (Universal Product Code or its superset European Article Number) */
public void setUPC (String UPC)
{
if (UPC != null && UPC.length() > 30)
{
log.warning("Length > 30 - truncated");
UPC = UPC.substring(0,29);
}
set_Value ("UPC", UPC);
}
/** Get UPC/EAN.
@return Bar Code (Universal Product Code or its superset European Article Number) */
public String getUPC() 
{
return (String)get_Value("UPC");
}
/** Column name UPC */
public static final String COLUMNNAME_UPC = "UPC";

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
