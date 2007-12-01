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
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;
import org.compiere.util.KeyNamePair;

/** Generated Model for C_AcctSchema
 *  @author Adempiere (generated) 
 *  @version Release 3.3.0 - $Id$ */
public class X_C_AcctSchema extends PO implements I_C_AcctSchema, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_C_AcctSchema (Properties ctx, int C_AcctSchema_ID, String trxName)
    {
      super (ctx, C_AcctSchema_ID, trxName);
      /** if (C_AcctSchema_ID == 0)
        {
			setAutoPeriodControl (false);
			setC_AcctSchema_ID (0);
			setC_Currency_ID (0);
			setCommitmentType (null);
// N
			setCostingLevel (null);
// C
			setCostingMethod (null);
// S
			setGAAP (null);
			setHasAlias (false);
			setHasCombination (false);
			setIsAccrual (true);
// Y
			setIsAdjustCOGS (false);
			setIsDiscountCorrectsTax (false);
			setIsExplicitCostAdjustment (false);
// N
			setIsPostServices (false);
// N
			setIsTradeDiscountPosted (false);
			setM_CostType_ID (0);
			setName (null);
			setSeparator (null);
// -
			setTaxCorrectionType (null);
        } */
    }

    /** Load Constructor */
    public X_C_AcctSchema (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_C_AcctSchema[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** AD_OrgOnly_ID AD_Reference_ID=322 */
	public static final int AD_ORGONLY_ID_AD_Reference_ID=322;
	/** Set Only Organization.
		@param AD_OrgOnly_ID 
		Create posting entries only for this organization
	  */
	public void setAD_OrgOnly_ID (int AD_OrgOnly_ID)
	{
		if (AD_OrgOnly_ID <= 0) 
			set_Value (COLUMNNAME_AD_OrgOnly_ID, null);
		else 
			set_Value (COLUMNNAME_AD_OrgOnly_ID, Integer.valueOf(AD_OrgOnly_ID));
	}

	/** Get Only Organization.
		@return Create posting entries only for this organization
	  */
	public int getAD_OrgOnly_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_OrgOnly_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Automatic Period Control.
		@param AutoPeriodControl 
		If selected, the periods are automatically opened and closed
	  */
	public void setAutoPeriodControl (boolean AutoPeriodControl)
	{
		set_Value (COLUMNNAME_AutoPeriodControl, Boolean.valueOf(AutoPeriodControl));
	}

	/** Get Automatic Period Control.
		@return If selected, the periods are automatically opened and closed
	  */
	public boolean isAutoPeriodControl () 
	{
		Object oo = get_Value(COLUMNNAME_AutoPeriodControl);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Accounting Schema.
		@param C_AcctSchema_ID 
		Rules for accounting
	  */
	public void setC_AcctSchema_ID (int C_AcctSchema_ID)
	{
		if (C_AcctSchema_ID < 1)
			 throw new IllegalArgumentException ("C_AcctSchema_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_C_AcctSchema_ID, Integer.valueOf(C_AcctSchema_ID));
	}

	/** Get Accounting Schema.
		@return Rules for accounting
	  */
	public int getC_AcctSchema_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_AcctSchema_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_C_Currency getC_Currency() throws Exception 
    {
        Class<?> clazz = MTable.getClass(I_C_Currency.Table_Name);
        I_C_Currency result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_Currency)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_Currency_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw e;
        }
        return result;
    }

	/** Set Currency.
		@param C_Currency_ID 
		The Currency for this record
	  */
	public void setC_Currency_ID (int C_Currency_ID)
	{
		if (C_Currency_ID < 1)
			 throw new IllegalArgumentException ("C_Currency_ID is mandatory.");
		set_Value (COLUMNNAME_C_Currency_ID, Integer.valueOf(C_Currency_ID));
	}

	/** Get Currency.
		@return The Currency for this record
	  */
	public int getC_Currency_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Currency_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_C_Period getC_Period() throws Exception 
    {
        Class<?> clazz = MTable.getClass(I_C_Period.Table_Name);
        I_C_Period result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_Period)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_Period_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw e;
        }
        return result;
    }

	/** Set Period.
		@param C_Period_ID 
		Period of the Calendar
	  */
	public void setC_Period_ID (int C_Period_ID)
	{
		if (C_Period_ID <= 0) 
			set_ValueNoCheck (COLUMNNAME_C_Period_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_Period_ID, Integer.valueOf(C_Period_ID));
	}

	/** Get Period.
		@return Period of the Calendar
	  */
	public int getC_Period_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Period_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** CommitmentType AD_Reference_ID=359 */
	public static final int COMMITMENTTYPE_AD_Reference_ID=359;
	/** PO Commitment only = C */
	public static final String COMMITMENTTYPE_POCommitmentOnly = "C";
	/** PO Commitment & Reservation = B */
	public static final String COMMITMENTTYPE_POCommitmentReservation = "B";
	/** None = N */
	public static final String COMMITMENTTYPE_None = "N";
	/** PO/SO Commitment & Reservation = A */
	public static final String COMMITMENTTYPE_POSOCommitmentReservation = "A";
	/** SO Commitment only = S */
	public static final String COMMITMENTTYPE_SOCommitmentOnly = "S";
	/** PO/SO Commitment = O */
	public static final String COMMITMENTTYPE_POSOCommitment = "O";
	/** Set Commitment Type.
		@param CommitmentType 
		Create Commitment and/or Reservations for Budget Control
	  */
	public void setCommitmentType (String CommitmentType)
	{
		if (CommitmentType == null) throw new IllegalArgumentException ("CommitmentType is mandatory");
		if (CommitmentType.equals("C") || CommitmentType.equals("B") || CommitmentType.equals("N") || CommitmentType.equals("A") || CommitmentType.equals("S") || CommitmentType.equals("O")); else throw new IllegalArgumentException ("CommitmentType Invalid value - " + CommitmentType + " - Reference_ID=359 - C - B - N - A - S - O");
		if (CommitmentType.length() > 1)
		{
			log.warning("Length > 1 - truncated");
			CommitmentType = CommitmentType.substring(0, 1);
		}
		set_Value (COLUMNNAME_CommitmentType, CommitmentType);
	}

	/** Get Commitment Type.
		@return Create Commitment and/or Reservations for Budget Control
	  */
	public String getCommitmentType () 
	{
		return (String)get_Value(COLUMNNAME_CommitmentType);
	}

	/** CostingLevel AD_Reference_ID=355 */
	public static final int COSTINGLEVEL_AD_Reference_ID=355;
	/** Client = C */
	public static final String COSTINGLEVEL_Client = "C";
	/** Organization = O */
	public static final String COSTINGLEVEL_Organization = "O";
	/** Batch/Lot = B */
	public static final String COSTINGLEVEL_BatchLot = "B";
	/** Set Costing Level.
		@param CostingLevel 
		The lowest level to accumulate Costing Information
	  */
	public void setCostingLevel (String CostingLevel)
	{
		if (CostingLevel == null) throw new IllegalArgumentException ("CostingLevel is mandatory");
		if (CostingLevel.equals("C") || CostingLevel.equals("O") || CostingLevel.equals("B")); else throw new IllegalArgumentException ("CostingLevel Invalid value - " + CostingLevel + " - Reference_ID=355 - C - O - B");
		if (CostingLevel.length() > 1)
		{
			log.warning("Length > 1 - truncated");
			CostingLevel = CostingLevel.substring(0, 1);
		}
		set_Value (COLUMNNAME_CostingLevel, CostingLevel);
	}

	/** Get Costing Level.
		@return The lowest level to accumulate Costing Information
	  */
	public String getCostingLevel () 
	{
		return (String)get_Value(COLUMNNAME_CostingLevel);
	}

	/** CostingMethod AD_Reference_ID=122 */
	public static final int COSTINGMETHOD_AD_Reference_ID=122;
	/** Standard Costing = S */
	public static final String COSTINGMETHOD_StandardCosting = "S";
	/** Average PO = A */
	public static final String COSTINGMETHOD_AveragePO = "A";
	/** Lifo = L */
	public static final String COSTINGMETHOD_Lifo = "L";
	/** Fifo = F */
	public static final String COSTINGMETHOD_Fifo = "F";
	/** Last PO Price = p */
	public static final String COSTINGMETHOD_LastPOPrice = "p";
	/** Average Invoice = I */
	public static final String COSTINGMETHOD_AverageInvoice = "I";
	/** Last Invoice = i */
	public static final String COSTINGMETHOD_LastInvoice = "i";
	/** User Defined = U */
	public static final String COSTINGMETHOD_UserDefined = "U";
	/** _ = x */
	public static final String COSTINGMETHOD__ = "x";
	/** Set Costing Method.
		@param CostingMethod 
		Indicates how Costs will be calculated
	  */
	public void setCostingMethod (String CostingMethod)
	{
		if (CostingMethod == null) throw new IllegalArgumentException ("CostingMethod is mandatory");
		if (CostingMethod.equals("S") || CostingMethod.equals("A") || CostingMethod.equals("L") || CostingMethod.equals("F") || CostingMethod.equals("p") || CostingMethod.equals("I") || CostingMethod.equals("i") || CostingMethod.equals("U") || CostingMethod.equals("x")); else throw new IllegalArgumentException ("CostingMethod Invalid value - " + CostingMethod + " - Reference_ID=122 - S - A - L - F - p - I - i - U - x");
		if (CostingMethod.length() > 1)
		{
			log.warning("Length > 1 - truncated");
			CostingMethod = CostingMethod.substring(0, 1);
		}
		set_Value (COLUMNNAME_CostingMethod, CostingMethod);
	}

	/** Get Costing Method.
		@return Indicates how Costs will be calculated
	  */
	public String getCostingMethod () 
	{
		return (String)get_Value(COLUMNNAME_CostingMethod);
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
			Description = Description.substring(0, 255);
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

	/** GAAP AD_Reference_ID=123 */
	public static final int GAAP_AD_Reference_ID=123;
	/** International GAAP = UN */
	public static final String GAAP_InternationalGAAP = "UN";
	/** US GAAP = US */
	public static final String GAAP_USGAAP = "US";
	/** German HGB = DE */
	public static final String GAAP_GermanHGB = "DE";
	/** French Accounting Standard = FR */
	public static final String GAAP_FrenchAccountingStandard = "FR";
	/** Custom Accounting Rules = XX */
	public static final String GAAP_CustomAccountingRules = "XX";
	/** Set GAAP.
		@param GAAP 
		Generally Accepted Accounting Principles
	  */
	public void setGAAP (String GAAP)
	{
		if (GAAP == null) throw new IllegalArgumentException ("GAAP is mandatory");
		if (GAAP.equals("UN") || GAAP.equals("US") || GAAP.equals("DE") || GAAP.equals("FR") || GAAP.equals("XX")); else throw new IllegalArgumentException ("GAAP Invalid value - " + GAAP + " - Reference_ID=123 - UN - US - DE - FR - XX");
		if (GAAP.length() > 2)
		{
			log.warning("Length > 2 - truncated");
			GAAP = GAAP.substring(0, 2);
		}
		set_Value (COLUMNNAME_GAAP, GAAP);
	}

	/** Get GAAP.
		@return Generally Accepted Accounting Principles
	  */
	public String getGAAP () 
	{
		return (String)get_Value(COLUMNNAME_GAAP);
	}

	/** Set Use Account Alias.
		@param HasAlias 
		Ability to select (partial) account combinations by an Alias
	  */
	public void setHasAlias (boolean HasAlias)
	{
		set_Value (COLUMNNAME_HasAlias, Boolean.valueOf(HasAlias));
	}

	/** Get Use Account Alias.
		@return Ability to select (partial) account combinations by an Alias
	  */
	public boolean isHasAlias () 
	{
		Object oo = get_Value(COLUMNNAME_HasAlias);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Use Account Combination Control.
		@param HasCombination 
		Combination of account elements are checked
	  */
	public void setHasCombination (boolean HasCombination)
	{
		set_Value (COLUMNNAME_HasCombination, Boolean.valueOf(HasCombination));
	}

	/** Get Use Account Combination Control.
		@return Combination of account elements are checked
	  */
	public boolean isHasCombination () 
	{
		Object oo = get_Value(COLUMNNAME_HasCombination);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Accrual.
		@param IsAccrual 
		Indicates if Accrual or Cash Based accounting will be used
	  */
	public void setIsAccrual (boolean IsAccrual)
	{
		set_Value (COLUMNNAME_IsAccrual, Boolean.valueOf(IsAccrual));
	}

	/** Get Accrual.
		@return Indicates if Accrual or Cash Based accounting will be used
	  */
	public boolean isAccrual () 
	{
		Object oo = get_Value(COLUMNNAME_IsAccrual);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Adjust COGS.
		@param IsAdjustCOGS 
		Adjust Cost of Good Sold
	  */
	public void setIsAdjustCOGS (boolean IsAdjustCOGS)
	{
		set_Value (COLUMNNAME_IsAdjustCOGS, Boolean.valueOf(IsAdjustCOGS));
	}

	/** Get Adjust COGS.
		@return Adjust Cost of Good Sold
	  */
	public boolean isAdjustCOGS () 
	{
		Object oo = get_Value(COLUMNNAME_IsAdjustCOGS);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Allow Negative Posting.
		@param IsAllowNegativePosting 
		Allow to post negative accounting values
	  */
	public void setIsAllowNegativePosting (boolean IsAllowNegativePosting)
	{
		set_Value (COLUMNNAME_IsAllowNegativePosting, Boolean.valueOf(IsAllowNegativePosting));
	}

	/** Get Allow Negative Posting.
		@return Allow to post negative accounting values
	  */
	public boolean isAllowNegativePosting () 
	{
		Object oo = get_Value(COLUMNNAME_IsAllowNegativePosting);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Correct tax for Discounts/Charges.
		@param IsDiscountCorrectsTax 
		Correct the tax for payment discount and charges
	  */
	public void setIsDiscountCorrectsTax (boolean IsDiscountCorrectsTax)
	{
		set_Value (COLUMNNAME_IsDiscountCorrectsTax, Boolean.valueOf(IsDiscountCorrectsTax));
	}

	/** Get Correct tax for Discounts/Charges.
		@return Correct the tax for payment discount and charges
	  */
	public boolean isDiscountCorrectsTax () 
	{
		Object oo = get_Value(COLUMNNAME_IsDiscountCorrectsTax);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Explicit Cost Adjustment.
		@param IsExplicitCostAdjustment 
		Post the cost adjustment explicitly
	  */
	public void setIsExplicitCostAdjustment (boolean IsExplicitCostAdjustment)
	{
		set_Value (COLUMNNAME_IsExplicitCostAdjustment, Boolean.valueOf(IsExplicitCostAdjustment));
	}

	/** Get Explicit Cost Adjustment.
		@return Post the cost adjustment explicitly
	  */
	public boolean isExplicitCostAdjustment () 
	{
		Object oo = get_Value(COLUMNNAME_IsExplicitCostAdjustment);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Post if Clearing Equal.
		@param IsPostIfClearingEqual 
		This flag controls if Adempiere must post when clearing (transit) and final accounts are the same
	  */
	public void setIsPostIfClearingEqual (boolean IsPostIfClearingEqual)
	{
		set_Value (COLUMNNAME_IsPostIfClearingEqual, Boolean.valueOf(IsPostIfClearingEqual));
	}

	/** Get Post if Clearing Equal.
		@return This flag controls if Adempiere must post when clearing (transit) and final accounts are the same
	  */
	public boolean isPostIfClearingEqual () 
	{
		Object oo = get_Value(COLUMNNAME_IsPostIfClearingEqual);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Post Services Separately.
		@param IsPostServices 
		Differentiate between Services and Product Receivable/Payables
	  */
	public void setIsPostServices (boolean IsPostServices)
	{
		set_Value (COLUMNNAME_IsPostServices, Boolean.valueOf(IsPostServices));
	}

	/** Get Post Services Separately.
		@return Differentiate between Services and Product Receivable/Payables
	  */
	public boolean isPostServices () 
	{
		Object oo = get_Value(COLUMNNAME_IsPostServices);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Post Trade Discount.
		@param IsTradeDiscountPosted 
		Generate postings for trade discounts
	  */
	public void setIsTradeDiscountPosted (boolean IsTradeDiscountPosted)
	{
		set_Value (COLUMNNAME_IsTradeDiscountPosted, Boolean.valueOf(IsTradeDiscountPosted));
	}

	/** Get Post Trade Discount.
		@return Generate postings for trade discounts
	  */
	public boolean isTradeDiscountPosted () 
	{
		Object oo = get_Value(COLUMNNAME_IsTradeDiscountPosted);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	public I_M_CostType getM_CostType() throws Exception 
    {
        Class<?> clazz = MTable.getClass(I_M_CostType.Table_Name);
        I_M_CostType result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_M_CostType)constructor.newInstance(new Object[] {getCtx(), new Integer(getM_CostType_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw e;
        }
        return result;
    }

	/** Set Cost Type.
		@param M_CostType_ID 
		Type of Cost (e.g. Current, Plan, Future)
	  */
	public void setM_CostType_ID (int M_CostType_ID)
	{
		if (M_CostType_ID < 1)
			 throw new IllegalArgumentException ("M_CostType_ID is mandatory.");
		set_Value (COLUMNNAME_M_CostType_ID, Integer.valueOf(M_CostType_ID));
	}

	/** Get Cost Type.
		@return Type of Cost (e.g. Current, Plan, Future)
	  */
	public int getM_CostType_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_CostType_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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
			Name = Name.substring(0, 60);
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

	/** Set Future Days.
		@param Period_OpenFuture 
		Number of days to be able to post to a future date (based on system date)
	  */
	public void setPeriod_OpenFuture (int Period_OpenFuture)
	{
		set_Value (COLUMNNAME_Period_OpenFuture, Integer.valueOf(Period_OpenFuture));
	}

	/** Get Future Days.
		@return Number of days to be able to post to a future date (based on system date)
	  */
	public int getPeriod_OpenFuture () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Period_OpenFuture);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set History Days.
		@param Period_OpenHistory 
		Number of days to be able to post in the past (based on system date)
	  */
	public void setPeriod_OpenHistory (int Period_OpenHistory)
	{
		set_Value (COLUMNNAME_Period_OpenHistory, Integer.valueOf(Period_OpenHistory));
	}

	/** Get History Days.
		@return Number of days to be able to post in the past (based on system date)
	  */
	public int getPeriod_OpenHistory () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Period_OpenHistory);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Process Now.
		@param Processing Process Now	  */
	public void setProcessing (boolean Processing)
	{
		set_Value (COLUMNNAME_Processing, Boolean.valueOf(Processing));
	}

	/** Get Process Now.
		@return Process Now	  */
	public boolean isProcessing () 
	{
		Object oo = get_Value(COLUMNNAME_Processing);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Element Separator.
		@param Separator 
		Element Separator
	  */
	public void setSeparator (String Separator)
	{
		if (Separator == null)
			throw new IllegalArgumentException ("Separator is mandatory.");

		if (Separator.length() > 1)
		{
			log.warning("Length > 1 - truncated");
			Separator = Separator.substring(0, 1);
		}
		set_Value (COLUMNNAME_Separator, Separator);
	}

	/** Get Element Separator.
		@return Element Separator
	  */
	public String getSeparator () 
	{
		return (String)get_Value(COLUMNNAME_Separator);
	}

	/** TaxCorrectionType AD_Reference_ID=392 */
	public static final int TAXCORRECTIONTYPE_AD_Reference_ID=392;
	/** None = N */
	public static final String TAXCORRECTIONTYPE_None = "N";
	/** Write-off only = W */
	public static final String TAXCORRECTIONTYPE_Write_OffOnly = "W";
	/** Discount only = D */
	public static final String TAXCORRECTIONTYPE_DiscountOnly = "D";
	/** Write-off and Discount = B */
	public static final String TAXCORRECTIONTYPE_Write_OffAndDiscount = "B";
	/** Set Tax Correction.
		@param TaxCorrectionType 
		Type of Tax Correction
	  */
	public void setTaxCorrectionType (String TaxCorrectionType)
	{
		if (TaxCorrectionType == null) throw new IllegalArgumentException ("TaxCorrectionType is mandatory");
		if (TaxCorrectionType.equals("N") || TaxCorrectionType.equals("W") || TaxCorrectionType.equals("D") || TaxCorrectionType.equals("B")); else throw new IllegalArgumentException ("TaxCorrectionType Invalid value - " + TaxCorrectionType + " - Reference_ID=392 - N - W - D - B");
		if (TaxCorrectionType.length() > 1)
		{
			log.warning("Length > 1 - truncated");
			TaxCorrectionType = TaxCorrectionType.substring(0, 1);
		}
		set_Value (COLUMNNAME_TaxCorrectionType, TaxCorrectionType);
	}

	/** Get Tax Correction.
		@return Type of Tax Correction
	  */
	public String getTaxCorrectionType () 
	{
		return (String)get_Value(COLUMNNAME_TaxCorrectionType);
	}
}