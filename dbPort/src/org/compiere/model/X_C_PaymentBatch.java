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
/** Generated Model for C_PaymentBatch
 *  @author Adempiere (generated) 
 *  @version Release 3.2.0 - $Id$ */
public class X_C_PaymentBatch extends PO
{
/** Standard Constructor
@param ctx context
@param C_PaymentBatch_ID id
@param trxName transaction
*/
public X_C_PaymentBatch (Properties ctx, int C_PaymentBatch_ID, String trxName)
{
super (ctx, C_PaymentBatch_ID, trxName);
/** if (C_PaymentBatch_ID == 0)
{
setC_PaymentBatch_ID (0);
setC_PaymentProcessor_ID (0);
setName (null);
setProcessed (false);
setProcessing (false);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_C_PaymentBatch (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=411 */
public static final int Table_ID=MTable.getTable_ID("C_PaymentBatch");

/** TableName=C_PaymentBatch */
public static final String Table_Name="C_PaymentBatch";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"C_PaymentBatch");

protected BigDecimal accessLevel = BigDecimal.valueOf(1);
/** AccessLevel
@return 1 - Org 
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
StringBuffer sb = new StringBuffer ("X_C_PaymentBatch[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Payment Batch.
@param C_PaymentBatch_ID Payment batch for EFT */
public void setC_PaymentBatch_ID (int C_PaymentBatch_ID)
{
if (C_PaymentBatch_ID < 1) throw new IllegalArgumentException ("C_PaymentBatch_ID is mandatory.");
set_ValueNoCheck ("C_PaymentBatch_ID", Integer.valueOf(C_PaymentBatch_ID));
}
/** Get Payment Batch.
@return Payment batch for EFT */
public int getC_PaymentBatch_ID() 
{
Integer ii = (Integer)get_Value("C_PaymentBatch_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_PaymentBatch_ID */
public static final String COLUMNNAME_C_PaymentBatch_ID = "C_PaymentBatch_ID";
/** Set Payment Processor.
@param C_PaymentProcessor_ID Payment processor for electronic payments */
public void setC_PaymentProcessor_ID (int C_PaymentProcessor_ID)
{
if (C_PaymentProcessor_ID < 1) throw new IllegalArgumentException ("C_PaymentProcessor_ID is mandatory.");
set_Value ("C_PaymentProcessor_ID", Integer.valueOf(C_PaymentProcessor_ID));
}
/** Get Payment Processor.
@return Payment processor for electronic payments */
public int getC_PaymentProcessor_ID() 
{
Integer ii = (Integer)get_Value("C_PaymentProcessor_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_PaymentProcessor_ID */
public static final String COLUMNNAME_C_PaymentProcessor_ID = "C_PaymentProcessor_ID";
/** Set Document No.
@param DocumentNo Document sequence number of the document */
public void setDocumentNo (String DocumentNo)
{
if (DocumentNo != null && DocumentNo.length() > 30)
{
log.warning("Length > 30 - truncated");
DocumentNo = DocumentNo.substring(0,29);
}
set_Value ("DocumentNo", DocumentNo);
}
/** Get Document No.
@return Document sequence number of the document */
public String getDocumentNo() 
{
return (String)get_Value("DocumentNo");
}
/** Get Record ID/ColumnName
@return ID/ColumnName pair
*/public KeyNamePair getKeyNamePair() 
{
return new KeyNamePair(get_ID(), getDocumentNo());
}
/** Column name DocumentNo */
public static final String COLUMNNAME_DocumentNo = "DocumentNo";
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
/** Column name Name */
public static final String COLUMNNAME_Name = "Name";
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
/** Set Processing date.
@param ProcessingDate Processing date */
public void setProcessingDate (Timestamp ProcessingDate)
{
set_Value ("ProcessingDate", ProcessingDate);
}
/** Get Processing date.
@return Processing date */
public Timestamp getProcessingDate() 
{
return (Timestamp)get_Value("ProcessingDate");
}
/** Column name ProcessingDate */
public static final String COLUMNNAME_ProcessingDate = "ProcessingDate";
}
