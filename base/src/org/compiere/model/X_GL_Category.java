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

import java.util.*;
import java.sql.*;
import java.math.*;
import java.lang.reflect.Constructor;
import java.util.logging.Level;
import org.compiere.util.*;

/** Generated Model for GL_Category
 *  @author Adempiere (generated) 
 *  @version Release 3.3.0 - $Id$ */
public class X_GL_Category extends PO implements I_GL_Category, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_GL_Category (Properties ctx, int GL_Category_ID, String trxName)
    {
      super (ctx, GL_Category_ID, trxName);
      /** if (GL_Category_ID == 0)        {			setCategoryType (null);
// M
			setGL_Category_ID (0);
			setIsDefault (false);
			setName (null);
} */
    }

    /** Load Constructor */
    public X_GL_Category (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 2 - Client 
      */
    protected int get_AccessLevel()
    {
      return accessLevel.intValue();
    }

    /** Load Meta Data */
    protected POInfo initPO (Properties ctx)
    {
      POInfo poi = POInfo.getPOInfo (ctx, Table_ID);
      return poi;
    }

    public String toString()
    {
      StringBuffer sb = new StringBuffer ("X_GL_Category[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

/** CategoryType AD_Reference_ID=207 */
public static final int CATEGORYTYPE_AD_Reference_ID=207;/** Document = D */
public static final String CATEGORYTYPE_Document = "D";/** Import = I */
public static final String CATEGORYTYPE_Import = "I";/** Manual = M */
public static final String CATEGORYTYPE_Manual = "M";/** System generated = S */
public static final String CATEGORYTYPE_SystemGenerated = "S";
	/** Set Category Type.
		@param CategoryType 
		Source of the Journal with this category
	  */
	public void setCategoryType (String CategoryType)
	{
if (CategoryType == null) throw new IllegalArgumentException ("CategoryType is mandatory");if (CategoryType.equals("D") || CategoryType.equals("I") || CategoryType.equals("M") || CategoryType.equals("S")); else throw new IllegalArgumentException ("CategoryType Invalid value - " + CategoryType + " - Reference_ID=207 - D - I - M - S");		if (CategoryType.length() > 1)
		{
			log.warning("Length > 1 - truncated");
			CategoryType = CategoryType.substring(0, 0);
		}
		set_Value (COLUMNNAME_CategoryType, CategoryType);
	}

	/** Get Category Type.
		@return Source of the Journal with this category
	  */
	public String getCategoryType () 
	{
		return (String)get_Value(COLUMNNAME_CategoryType);
	}

	/** Set Description.
		@param Description 
		Optional short description of the record
	  */
	public void setDescription (String Description)
	{
		if (Description != null && Description.length() > 255)
		{
			log.warning("Length > 255 - truncated");
			Description = Description.substring(0, 254);
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

/** DocBaseType AD_Reference_ID=183 */
public static final int DOCBASETYPE_AD_Reference_ID=183;/** AP Credit Memo = APC */
public static final String DOCBASETYPE_APCreditMemo = "APC";/** AP Invoice = API */
public static final String DOCBASETYPE_APInvoice = "API";/** AP Payment = APP */
public static final String DOCBASETYPE_APPayment = "APP";/** AR Credit Memo = ARC */
public static final String DOCBASETYPE_ARCreditMemo = "ARC";/** AR Pro Forma Invoice = ARF */
public static final String DOCBASETYPE_ARProFormaInvoice = "ARF";/** AR Invoice = ARI */
public static final String DOCBASETYPE_ARInvoice = "ARI";/** AR Receipt = ARR */
public static final String DOCBASETYPE_ARReceipt = "ARR";/** Payment Allocation = CMA */
public static final String DOCBASETYPE_PaymentAllocation = "CMA";/** Bank Statement = CMB */
public static final String DOCBASETYPE_BankStatement = "CMB";/** Cash Journal = CMC */
public static final String DOCBASETYPE_CashJournal = "CMC";/** GL Document = GLD */
public static final String DOCBASETYPE_GLDocument = "GLD";/** GL Journal = GLJ */
public static final String DOCBASETYPE_GLJournal = "GLJ";/** Material Physical Inventory = MMI */
public static final String DOCBASETYPE_MaterialPhysicalInventory = "MMI";/** Material Movement = MMM */
public static final String DOCBASETYPE_MaterialMovement = "MMM";/** Material Production = MMP */
public static final String DOCBASETYPE_MaterialProduction = "MMP";/** Material Receipt = MMR */
public static final String DOCBASETYPE_MaterialReceipt = "MMR";/** Material Delivery = MMS */
public static final String DOCBASETYPE_MaterialDelivery = "MMS";/** Match Invoice = MXI */
public static final String DOCBASETYPE_MatchInvoice = "MXI";/** Match PO = MXP */
public static final String DOCBASETYPE_MatchPO = "MXP";/** Project Issue = PJI */
public static final String DOCBASETYPE_ProjectIssue = "PJI";/** Purchase Order = POO */
public static final String DOCBASETYPE_PurchaseOrder = "POO";/** Purchase Requisition = POR */
public static final String DOCBASETYPE_PurchaseRequisition = "POR";/** Sales Order = SOO */
public static final String DOCBASETYPE_SalesOrder = "SOO";
	/** Set Document BaseType.
		@param DocBaseType 
		Logical type of document
	  */
	public void setDocBaseType (String DocBaseType)
	{
if (DocBaseType == null || DocBaseType.equals("APC") || DocBaseType.equals("API") || DocBaseType.equals("APP") || DocBaseType.equals("ARC") || DocBaseType.equals("ARF") || DocBaseType.equals("ARI") || DocBaseType.equals("ARR") || DocBaseType.equals("CMA") || DocBaseType.equals("CMB") || DocBaseType.equals("CMC") || DocBaseType.equals("GLD") || DocBaseType.equals("GLJ") || DocBaseType.equals("MMI") || DocBaseType.equals("MMM") || DocBaseType.equals("MMP") || DocBaseType.equals("MMR") || DocBaseType.equals("MMS") || DocBaseType.equals("MXI") || DocBaseType.equals("MXP") || DocBaseType.equals("PJI") || DocBaseType.equals("POO") || DocBaseType.equals("POR") || DocBaseType.equals("SOO")); else throw new IllegalArgumentException ("DocBaseType Invalid value - " + DocBaseType + " - Reference_ID=183 - APC - API - APP - ARC - ARF - ARI - ARR - CMA - CMB - CMC - GLD - GLJ - MMI - MMM - MMP - MMR - MMS - MXI - MXP - PJI - POO - POR - SOO");		if (DocBaseType != null && DocBaseType.length() > 3)
		{
			log.warning("Length > 3 - truncated");
			DocBaseType = DocBaseType.substring(0, 2);
		}
		set_Value (COLUMNNAME_DocBaseType, DocBaseType);
	}

	/** Get Document BaseType.
		@return Logical type of document
	  */
	public String getDocBaseType () 
	{
		return (String)get_Value(COLUMNNAME_DocBaseType);
	}

	/** Set GL Category.
		@param GL_Category_ID 
		General Ledger Category
	  */
	public void setGL_Category_ID (int GL_Category_ID)
	{
		if (GL_Category_ID < 1)
			 throw new IllegalArgumentException ("GL_Category_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_GL_Category_ID, Integer.valueOf(GL_Category_ID));
	}

	/** Get GL Category.
		@return General Ledger Category
	  */
	public int getGL_Category_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_GL_Category_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Default.
		@param IsDefault 
		Default value
	  */
	public void setIsDefault (boolean IsDefault)
	{
		set_Value (COLUMNNAME_IsDefault, Boolean.valueOf(IsDefault));
	}

	/** Get Default.
		@return Default value
	  */
	public boolean isDefault () 
	{
		Object oo = get_Value(COLUMNNAME_IsDefault);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Name.
		@param Name 
		Alphanumeric identifier of the entity
	  */
	public void setName (String Name)
	{
		if (Name == null)
			throw new IllegalArgumentException ("Name is mandatory.");
		if (Name.length() > 60)
		{
			log.warning("Length > 60 - truncated");
			Name = Name.substring(0, 59);
		}
		set_Value (COLUMNNAME_Name, Name);
	}

	/** Get Name.
		@return Alphanumeric identifier of the entity
	  */
	public String getName () 
	{
		return (String)get_Value(COLUMNNAME_Name);
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), getName());
    }
}