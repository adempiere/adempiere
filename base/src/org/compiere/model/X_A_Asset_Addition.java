/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2007 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;

/** Generated Model for A_Asset_Addition
 *  @author Adempiere (generated) 
 *  @version Release 3.5.1a - $Id$ */
public class X_A_Asset_Addition extends PO implements I_A_Asset_Addition, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_A_Asset_Addition (Properties ctx, int A_Asset_Addition_ID, String trxName)
    {
      super (ctx, A_Asset_Addition_ID, trxName);
      /** if (A_Asset_Addition_ID == 0)
        {
			setA_Asset_Addition_ID (0);
			setA_Asset_ID (0);
			setAssetValueAmt (Env.ZERO);
        } */
    }

    /** Load Constructor */
    public X_A_Asset_Addition (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 7 - System - Client - Org 
      */
    protected int get_AccessLevel()
    {
      return accessLevel.intValue();
    }

    /** Load Meta Data */
    protected POInfo initPO (Properties ctx)
    {
      POInfo poi = POInfo.getPOInfo (ctx, Table_ID, get_TrxName());
      return poi;
    }

    public String toString()
    {
      StringBuffer sb = new StringBuffer ("X_A_Asset_Addition[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set A_Asset_Addition_ID.
		@param A_Asset_Addition_ID A_Asset_Addition_ID	  */
	public void setA_Asset_Addition_ID (int A_Asset_Addition_ID)
	{
		if (A_Asset_Addition_ID < 1)
			 throw new IllegalArgumentException ("A_Asset_Addition_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_A_Asset_Addition_ID, Integer.valueOf(A_Asset_Addition_ID));
	}

	/** Get A_Asset_Addition_ID.
		@return A_Asset_Addition_ID	  */
	public int getA_Asset_Addition_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Asset_Addition_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getA_Asset_Addition_ID()));
    }

	/** Set Asset.
		@param A_Asset_ID 
		Asset used internally or by customers
	  */
	public void setA_Asset_ID (int A_Asset_ID)
	{
		if (A_Asset_ID < 1)
			 throw new IllegalArgumentException ("A_Asset_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_A_Asset_ID, Integer.valueOf(A_Asset_ID));
	}

	/** Get Asset.
		@return Asset used internally or by customers
	  */
	public int getA_Asset_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Asset_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** A_CapvsExp AD_Reference_ID=53277 */
	public static final int A_CAPVSEXP_AD_Reference_ID=53277;
	/** Capital = Cap */
	public static final String A_CAPVSEXP_Capital = "Cap";
	/** Expense = Exp */
	public static final String A_CAPVSEXP_Expense = "Exp";
	/** Set A_CapvsExp.
		@param A_CapvsExp A_CapvsExp	  */
	public void setA_CapvsExp (String A_CapvsExp)
	{

		if (A_CapvsExp == null || A_CapvsExp.equals("Cap") || A_CapvsExp.equals("Exp")); else throw new IllegalArgumentException ("A_CapvsExp Invalid value - " + A_CapvsExp + " - Reference_ID=53277 - Cap - Exp");
		if (A_CapvsExp != null && A_CapvsExp.length() > 3)
		{
			log.warning("Length > 3 - truncated");
			A_CapvsExp = A_CapvsExp.substring(0, 3);
		}
		set_Value (COLUMNNAME_A_CapvsExp, A_CapvsExp);
	}

	/** Get A_CapvsExp.
		@return A_CapvsExp	  */
	public String getA_CapvsExp () 
	{
		return (String)get_Value(COLUMNNAME_A_CapvsExp);
	}

	/** Set A_QTY_Current.
		@param A_QTY_Current A_QTY_Current	  */
	public void setA_QTY_Current (BigDecimal A_QTY_Current)
	{
		set_Value (COLUMNNAME_A_QTY_Current, A_QTY_Current);
	}

	/** Get A_QTY_Current.
		@return A_QTY_Current	  */
	public BigDecimal getA_QTY_Current () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_A_QTY_Current);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** A_SourceType AD_Reference_ID=53276 */
	public static final int A_SOURCETYPE_AD_Reference_ID=53276;
	/** Imported = IMP */
	public static final String A_SOURCETYPE_Imported = "IMP";
	/** Invoice = INV */
	public static final String A_SOURCETYPE_Invoice = "INV";
	/** Journal Entry = JRN */
	public static final String A_SOURCETYPE_JournalEntry = "JRN";
	/** Manual = MAN */
	public static final String A_SOURCETYPE_Manual = "MAN";
	/** Set A_SourceType.
		@param A_SourceType A_SourceType	  */
	public void setA_SourceType (String A_SourceType)
	{

		if (A_SourceType == null || A_SourceType.equals("IMP") || A_SourceType.equals("INV") || A_SourceType.equals("JRN") || A_SourceType.equals("MAN")); else throw new IllegalArgumentException ("A_SourceType Invalid value - " + A_SourceType + " - Reference_ID=53276 - IMP - INV - JRN - MAN");
		if (A_SourceType != null && A_SourceType.length() > 3)
		{
			log.warning("Length > 3 - truncated");
			A_SourceType = A_SourceType.substring(0, 3);
		}
		set_Value (COLUMNNAME_A_SourceType, A_SourceType);
	}

	/** Get A_SourceType.
		@return A_SourceType	  */
	public String getA_SourceType () 
	{
		return (String)get_Value(COLUMNNAME_A_SourceType);
	}

	/** Set Asset value.
		@param AssetValueAmt 
		Book Value of the asset
	  */
	public void setAssetValueAmt (BigDecimal AssetValueAmt)
	{
		if (AssetValueAmt == null)
			throw new IllegalArgumentException ("AssetValueAmt is mandatory.");
		set_Value (COLUMNNAME_AssetValueAmt, AssetValueAmt);
	}

	/** Get Asset value.
		@return Book Value of the asset
	  */
	public BigDecimal getAssetValueAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_AssetValueAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** C_Invoice_ID AD_Reference_ID=53275 */
	public static final int C_INVOICE_ID_AD_Reference_ID=53275;
	public I_C_Invoice getC_Invoice() throws Exception 
    {
        Class<?> clazz = MTable.getClass(I_C_Invoice.Table_Name);
        I_C_Invoice result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_Invoice)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_Invoice_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw e;
        }
        return result;
    }

	/** Set Invoice.
		@param C_Invoice_ID 
		Invoice Identifier
	  */
	public void setC_Invoice_ID (int C_Invoice_ID)
	{
		if (C_Invoice_ID < 1) 
			set_Value (COLUMNNAME_C_Invoice_ID, null);
		else 
			set_Value (COLUMNNAME_C_Invoice_ID, Integer.valueOf(C_Invoice_ID));
	}

	/** Get Invoice.
		@return Invoice Identifier
	  */
	public int getC_Invoice_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Invoice_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Description.
		@param Description 
		Optional short description of the record
	  */
	public void setDescription (String Description)
	{

		if (Description != null && Description.length() > 510)
		{
			log.warning("Length > 510 - truncated");
			Description = Description.substring(0, 510);
		}
		set_Value (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	  */
	public String getDescription () 
	{
		return (String)get_Value(COLUMNNAME_Description);
	}

	/** DocumentNo AD_Reference_ID=53274 */
	public static final int DOCUMENTNO_AD_Reference_ID=53274;
	/** Set Document No.
		@param DocumentNo 
		Document sequence number of the document
	  */
	public void setDocumentNo (String DocumentNo)
	{

		if (DocumentNo != null && DocumentNo.length() > 30)
		{
			log.warning("Length > 30 - truncated");
			DocumentNo = DocumentNo.substring(0, 30);
		}
		set_Value (COLUMNNAME_DocumentNo, DocumentNo);
	}

	/** Get Document No.
		@return Document sequence number of the document
	  */
	public String getDocumentNo () 
	{
		return (String)get_Value(COLUMNNAME_DocumentNo);
	}

	public I_GL_JournalBatch getGL_JournalBatch() throws Exception 
    {
        Class<?> clazz = MTable.getClass(I_GL_JournalBatch.Table_Name);
        I_GL_JournalBatch result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_GL_JournalBatch)constructor.newInstance(new Object[] {getCtx(), new Integer(getGL_JournalBatch_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw e;
        }
        return result;
    }

	/** Set Journal Batch.
		@param GL_JournalBatch_ID 
		General Ledger Journal Batch
	  */
	public void setGL_JournalBatch_ID (int GL_JournalBatch_ID)
	{
		if (GL_JournalBatch_ID < 1) 
			set_Value (COLUMNNAME_GL_JournalBatch_ID, null);
		else 
			set_Value (COLUMNNAME_GL_JournalBatch_ID, Integer.valueOf(GL_JournalBatch_ID));
	}

	/** Get Journal Batch.
		@return General Ledger Journal Batch
	  */
	public int getGL_JournalBatch_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_GL_JournalBatch_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Line No.
		@param Line 
		Unique line for this document
	  */
	public void setLine (int Line)
	{
		set_Value (COLUMNNAME_Line, Integer.valueOf(Line));
	}

	/** Get Line No.
		@return Unique line for this document
	  */
	public int getLine () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Line);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Shipment/Receipt Line.
		@param M_InOutLine_ID 
		Line on Shipment or Receipt document
	  */
	public void setM_InOutLine_ID (int M_InOutLine_ID)
	{
		if (M_InOutLine_ID < 1) 
			set_Value (COLUMNNAME_M_InOutLine_ID, null);
		else 
			set_Value (COLUMNNAME_M_InOutLine_ID, Integer.valueOf(M_InOutLine_ID));
	}

	/** Get Shipment/Receipt Line.
		@return Line on Shipment or Receipt document
	  */
	public int getM_InOutLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_InOutLine_ID);
		if (ii == null)
			 return 0;
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
	/** Statistical = S */
	public static final String POSTINGTYPE_Statistical = "S";
	/** Reservation = R */
	public static final String POSTINGTYPE_Reservation = "R";
	/** Set PostingType.
		@param PostingType 
		The type of posted amount for the transaction
	  */
	public void setPostingType (String PostingType)
	{

		if (PostingType == null || PostingType.equals("A") || PostingType.equals("B") || PostingType.equals("E") || PostingType.equals("S") || PostingType.equals("R")); else throw new IllegalArgumentException ("PostingType Invalid value - " + PostingType + " - Reference_ID=125 - A - B - E - S - R");
		if (PostingType != null && PostingType.length() > 1)
		{
			log.warning("Length > 1 - truncated");
			PostingType = PostingType.substring(0, 1);
		}
		set_Value (COLUMNNAME_PostingType, PostingType);
	}

	/** Get PostingType.
		@return The type of posted amount for the transaction
	  */
	public String getPostingType () 
	{
		return (String)get_Value(COLUMNNAME_PostingType);
	}
}