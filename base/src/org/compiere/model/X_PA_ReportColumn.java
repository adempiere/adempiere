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

/** Generated Model for PA_ReportColumn
 *  @author Adempiere (generated) 
 *  @version Release 3.4.0s - $Id$ */
public class X_PA_ReportColumn extends PO implements I_PA_ReportColumn, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_PA_ReportColumn (Properties ctx, int PA_ReportColumn_ID, String trxName)
    {
      super (ctx, PA_ReportColumn_ID, trxName);
      /** if (PA_ReportColumn_ID == 0)
        {
			setColumnType (null);
// R
			setIsPrinted (true);
// Y
			setName (null);
			setPA_ReportColumnSet_ID (0);
			setPA_ReportColumn_ID (0);
			setPostingType (null);
// A
			setSeqNo (0);
// @SQL=SELECT NVL(MAX(SeqNo),0)+10 AS DefaultValue FROM PA_ReportColumn WHERE PA_ReportColumnSet_ID=@PA_ReportColumnSet_ID@
        } */
    }

    /** Load Constructor */
    public X_PA_ReportColumn (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_PA_ReportColumn[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** AmountType AD_Reference_ID=235 */
	public static final int AMOUNTTYPE_AD_Reference_ID=235;
	/** Total Debit Only = DT */
	public static final String AMOUNTTYPE_TotalDebitOnly = "DT";
	/** Total Credit Only = CT */
	public static final String AMOUNTTYPE_TotalCreditOnly = "CT";
	/** Total Balance = BT */
	public static final String AMOUNTTYPE_TotalBalance = "BT";
	/** Period Balance = BP */
	public static final String AMOUNTTYPE_PeriodBalance = "BP";
	/** Period Credit Only = CP */
	public static final String AMOUNTTYPE_PeriodCreditOnly = "CP";
	/** Period Debit Only = DP */
	public static final String AMOUNTTYPE_PeriodDebitOnly = "DP";
	/** Period Quantity = QP */
	public static final String AMOUNTTYPE_PeriodQuantity = "QP";
	/** Total Quantity = QT */
	public static final String AMOUNTTYPE_TotalQuantity = "QT";
	/** Year Balance = BY */
	public static final String AMOUNTTYPE_YearBalance = "BY";
	/** Year Credit Only = CY */
	public static final String AMOUNTTYPE_YearCreditOnly = "CY";
	/** Year Debit Only = DY */
	public static final String AMOUNTTYPE_YearDebitOnly = "DY";
	/** Year Quantity = QY */
	public static final String AMOUNTTYPE_YearQuantity = "QY";
	/** Set Amount Type.
		@param AmountType 
		Type of amount to report
	  */
	public void setAmountType (String AmountType)
	{

		if (AmountType == null || AmountType.equals("DT") || AmountType.equals("CT") || AmountType.equals("BT") || AmountType.equals("BP") || AmountType.equals("CP") || AmountType.equals("DP") || AmountType.equals("QP") || AmountType.equals("QT") || AmountType.equals("BY") || AmountType.equals("CY") || AmountType.equals("DY") || AmountType.equals("QY")); else throw new IllegalArgumentException ("AmountType Invalid value - " + AmountType + " - Reference_ID=235 - DT - CT - BT - BP - CP - DP - QP - QT - BY - CY - DY - QY");
		if (AmountType != null && AmountType.length() > 2)
		{
			log.warning("Length > 2 - truncated");
			AmountType = AmountType.substring(0, 2);
		}
		set_Value (COLUMNNAME_AmountType, AmountType);
	}

	/** Get Amount Type.
		@return Type of amount to report
	  */
	public String getAmountType () 
	{
		return (String)get_Value(COLUMNNAME_AmountType);
	}

	public I_C_Activity getC_Activity() throws Exception 
    {
        Class<?> clazz = MTable.getClass(I_C_Activity.Table_Name);
        I_C_Activity result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_Activity)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_Activity_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw e;
        }
        return result;
    }

	/** Set Activity.
		@param C_Activity_ID 
		Business Activity
	  */
	public void setC_Activity_ID (int C_Activity_ID)
	{
		if (C_Activity_ID < 1) 
			set_Value (COLUMNNAME_C_Activity_ID, null);
		else 
			set_Value (COLUMNNAME_C_Activity_ID, Integer.valueOf(C_Activity_ID));
	}

	/** Get Activity.
		@return Business Activity
	  */
	public int getC_Activity_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Activity_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_C_BPartner getC_BPartner() throws Exception 
    {
        Class<?> clazz = MTable.getClass(I_C_BPartner.Table_Name);
        I_C_BPartner result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_BPartner)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_BPartner_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw e;
        }
        return result;
    }

	/** Set Business Partner .
		@param C_BPartner_ID 
		Identifies a Business Partner
	  */
	public void setC_BPartner_ID (int C_BPartner_ID)
	{
		if (C_BPartner_ID < 1) 
			set_Value (COLUMNNAME_C_BPartner_ID, null);
		else 
			set_Value (COLUMNNAME_C_BPartner_ID, Integer.valueOf(C_BPartner_ID));
	}

	/** Get Business Partner .
		@return Identifies a Business Partner
	  */
	public int getC_BPartner_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BPartner_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_C_Campaign getC_Campaign() throws Exception 
    {
        Class<?> clazz = MTable.getClass(I_C_Campaign.Table_Name);
        I_C_Campaign result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_Campaign)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_Campaign_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw e;
        }
        return result;
    }

	/** Set Campaign.
		@param C_Campaign_ID 
		Marketing Campaign
	  */
	public void setC_Campaign_ID (int C_Campaign_ID)
	{
		if (C_Campaign_ID < 1) 
			set_Value (COLUMNNAME_C_Campaign_ID, null);
		else 
			set_Value (COLUMNNAME_C_Campaign_ID, Integer.valueOf(C_Campaign_ID));
	}

	/** Get Campaign.
		@return Marketing Campaign
	  */
	public int getC_Campaign_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Campaign_ID);
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
			set_Value (COLUMNNAME_C_Currency_ID, null);
		else 
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

	public I_C_ElementValue getC_ElementValue() throws Exception 
    {
        Class<?> clazz = MTable.getClass(I_C_ElementValue.Table_Name);
        I_C_ElementValue result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_ElementValue)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_ElementValue_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw e;
        }
        return result;
    }

	/** Set Account Element.
		@param C_ElementValue_ID 
		Account Element
	  */
	public void setC_ElementValue_ID (int C_ElementValue_ID)
	{
		if (C_ElementValue_ID < 1) 
			set_Value (COLUMNNAME_C_ElementValue_ID, null);
		else 
			set_Value (COLUMNNAME_C_ElementValue_ID, Integer.valueOf(C_ElementValue_ID));
	}

	/** Get Account Element.
		@return Account Element
	  */
	public int getC_ElementValue_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_ElementValue_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Address.
		@param C_Location_ID 
		Location or Address
	  */
	public void setC_Location_ID (int C_Location_ID)
	{
		if (C_Location_ID < 1) 
			set_Value (COLUMNNAME_C_Location_ID, null);
		else 
			set_Value (COLUMNNAME_C_Location_ID, Integer.valueOf(C_Location_ID));
	}

	/** Get Address.
		@return Location or Address
	  */
	public int getC_Location_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Location_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_C_Project getC_Project() throws Exception 
    {
        Class<?> clazz = MTable.getClass(I_C_Project.Table_Name);
        I_C_Project result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_Project)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_Project_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw e;
        }
        return result;
    }

	/** Set Project.
		@param C_Project_ID 
		Financial Project
	  */
	public void setC_Project_ID (int C_Project_ID)
	{
		if (C_Project_ID < 1) 
			set_Value (COLUMNNAME_C_Project_ID, null);
		else 
			set_Value (COLUMNNAME_C_Project_ID, Integer.valueOf(C_Project_ID));
	}

	/** Get Project.
		@return Financial Project
	  */
	public int getC_Project_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Project_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_C_SalesRegion getC_SalesRegion() throws Exception 
    {
        Class<?> clazz = MTable.getClass(I_C_SalesRegion.Table_Name);
        I_C_SalesRegion result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_SalesRegion)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_SalesRegion_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw e;
        }
        return result;
    }

	/** Set Sales Region.
		@param C_SalesRegion_ID 
		Sales coverage region
	  */
	public void setC_SalesRegion_ID (int C_SalesRegion_ID)
	{
		if (C_SalesRegion_ID < 1) 
			set_Value (COLUMNNAME_C_SalesRegion_ID, null);
		else 
			set_Value (COLUMNNAME_C_SalesRegion_ID, Integer.valueOf(C_SalesRegion_ID));
	}

	/** Get Sales Region.
		@return Sales coverage region
	  */
	public int getC_SalesRegion_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_SalesRegion_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** CalculationType AD_Reference_ID=236 */
	public static final int CALCULATIONTYPE_AD_Reference_ID=236;
	/** Add (Op1+Op2) = A */
	public static final String CALCULATIONTYPE_AddOp1PlusOp2 = "A";
	/** Subtract (Op1-Op2) = S */
	public static final String CALCULATIONTYPE_SubtractOp1_Op2 = "S";
	/** Percentage (Op1 of Op2) = P */
	public static final String CALCULATIONTYPE_PercentageOp1OfOp2 = "P";
	/** Add Range (Op1 to Op2) = R */
	public static final String CALCULATIONTYPE_AddRangeOp1ToOp2 = "R";
	/** Set Calculation.
		@param CalculationType Calculation	  */
	public void setCalculationType (String CalculationType)
	{

		if (CalculationType == null || CalculationType.equals("A") || CalculationType.equals("S") || CalculationType.equals("P") || CalculationType.equals("R")); else throw new IllegalArgumentException ("CalculationType Invalid value - " + CalculationType + " - Reference_ID=236 - A - S - P - R");
		if (CalculationType != null && CalculationType.length() > 1)
		{
			log.warning("Length > 1 - truncated");
			CalculationType = CalculationType.substring(0, 1);
		}
		set_Value (COLUMNNAME_CalculationType, CalculationType);
	}

	/** Get Calculation.
		@return Calculation	  */
	public String getCalculationType () 
	{
		return (String)get_Value(COLUMNNAME_CalculationType);
	}

	/** ColumnType AD_Reference_ID=237 */
	public static final int COLUMNTYPE_AD_Reference_ID=237;
	/** Relative Period = R */
	public static final String COLUMNTYPE_RelativePeriod = "R";
	/** Calculation = C */
	public static final String COLUMNTYPE_Calculation = "C";
	/** Segment Value = S */
	public static final String COLUMNTYPE_SegmentValue = "S";
	/** Set Column Type.
		@param ColumnType Column Type	  */
	public void setColumnType (String ColumnType)
	{
		if (ColumnType == null) throw new IllegalArgumentException ("ColumnType is mandatory");
		if (ColumnType.equals("R") || ColumnType.equals("C") || ColumnType.equals("S")); else throw new IllegalArgumentException ("ColumnType Invalid value - " + ColumnType + " - Reference_ID=237 - R - C - S");
		if (ColumnType.length() > 1)
		{
			log.warning("Length > 1 - truncated");
			ColumnType = ColumnType.substring(0, 1);
		}
		set_Value (COLUMNNAME_ColumnType, ColumnType);
	}

	/** Get Column Type.
		@return Column Type	  */
	public String getColumnType () 
	{
		return (String)get_Value(COLUMNNAME_ColumnType);
	}

	/** CurrencyType AD_Reference_ID=238 */
	public static final int CURRENCYTYPE_AD_Reference_ID=238;
	/** Source Currency = S */
	public static final String CURRENCYTYPE_SourceCurrency = "S";
	/** Accounting Currency = A */
	public static final String CURRENCYTYPE_AccountingCurrency = "A";
	/** Set Currency Type.
		@param CurrencyType Currency Type	  */
	public void setCurrencyType (String CurrencyType)
	{

		if (CurrencyType == null || CurrencyType.equals("S") || CurrencyType.equals("A")); else throw new IllegalArgumentException ("CurrencyType Invalid value - " + CurrencyType + " - Reference_ID=238 - S - A");
		if (CurrencyType != null && CurrencyType.length() > 1)
		{
			log.warning("Length > 1 - truncated");
			CurrencyType = CurrencyType.substring(0, 1);
		}
		set_Value (COLUMNNAME_CurrencyType, CurrencyType);
	}

	/** Get Currency Type.
		@return Currency Type	  */
	public String getCurrencyType () 
	{
		return (String)get_Value(COLUMNNAME_CurrencyType);
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

	/** ElementType AD_Reference_ID=181 */
	public static final int ELEMENTTYPE_AD_Reference_ID=181;
	/** Organization = OO */
	public static final String ELEMENTTYPE_Organization = "OO";
	/** Account = AC */
	public static final String ELEMENTTYPE_Account = "AC";
	/** Product = PR */
	public static final String ELEMENTTYPE_Product = "PR";
	/** BPartner = BP */
	public static final String ELEMENTTYPE_BPartner = "BP";
	/** Org Trx = OT */
	public static final String ELEMENTTYPE_OrgTrx = "OT";
	/** Location From = LF */
	public static final String ELEMENTTYPE_LocationFrom = "LF";
	/** Location To = LT */
	public static final String ELEMENTTYPE_LocationTo = "LT";
	/** Sales Region = SR */
	public static final String ELEMENTTYPE_SalesRegion = "SR";
	/** Project = PJ */
	public static final String ELEMENTTYPE_Project = "PJ";
	/** Campaign = MC */
	public static final String ELEMENTTYPE_Campaign = "MC";
	/** User List 1 = U1 */
	public static final String ELEMENTTYPE_UserList1 = "U1";
	/** User List 2 = U2 */
	public static final String ELEMENTTYPE_UserList2 = "U2";
	/** Activity = AY */
	public static final String ELEMENTTYPE_Activity = "AY";
	/** Sub Account = SA */
	public static final String ELEMENTTYPE_SubAccount = "SA";
	/** User Element 1 = X1 */
	public static final String ELEMENTTYPE_UserElement1 = "X1";
	/** User Element 2 = X2 */
	public static final String ELEMENTTYPE_UserElement2 = "X2";
	/** Set Type.
		@param ElementType 
		Element Type (account or user defined)
	  */
	public void setElementType (String ElementType)
	{

		if (ElementType == null || ElementType.equals("OO") || ElementType.equals("AC") || ElementType.equals("PR") || ElementType.equals("BP") || ElementType.equals("OT") || ElementType.equals("LF") || ElementType.equals("LT") || ElementType.equals("SR") || ElementType.equals("PJ") || ElementType.equals("MC") || ElementType.equals("U1") || ElementType.equals("U2") || ElementType.equals("AY") || ElementType.equals("SA") || ElementType.equals("X1") || ElementType.equals("X2")); else throw new IllegalArgumentException ("ElementType Invalid value - " + ElementType + " - Reference_ID=181 - OO - AC - PR - BP - OT - LF - LT - SR - PJ - MC - U1 - U2 - AY - SA - X1 - X2");
		if (ElementType != null && ElementType.length() > 2)
		{
			log.warning("Length > 2 - truncated");
			ElementType = ElementType.substring(0, 2);
		}
		set_Value (COLUMNNAME_ElementType, ElementType);
	}

	/** Get Type.
		@return Element Type (account or user defined)
	  */
	public String getElementType () 
	{
		return (String)get_Value(COLUMNNAME_ElementType);
	}

	public I_GL_Budget getGL_Budget() throws Exception 
    {
        Class<?> clazz = MTable.getClass(I_GL_Budget.Table_Name);
        I_GL_Budget result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_GL_Budget)constructor.newInstance(new Object[] {getCtx(), new Integer(getGL_Budget_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw e;
        }
        return result;
    }

	/** Set Budget.
		@param GL_Budget_ID 
		General Ledger Budget
	  */
	public void setGL_Budget_ID (int GL_Budget_ID)
	{
		if (GL_Budget_ID < 1) 
			set_Value (COLUMNNAME_GL_Budget_ID, null);
		else 
			set_Value (COLUMNNAME_GL_Budget_ID, Integer.valueOf(GL_Budget_ID));
	}

	/** Get Budget.
		@return General Ledger Budget
	  */
	public int getGL_Budget_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_GL_Budget_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Adhoc Conversion.
		@param IsAdhocConversion 
		Perform conversion for all amounts to currency
	  */
	public void setIsAdhocConversion (boolean IsAdhocConversion)
	{
		set_Value (COLUMNNAME_IsAdhocConversion, Boolean.valueOf(IsAdhocConversion));
	}

	/** Get Adhoc Conversion.
		@return Perform conversion for all amounts to currency
	  */
	public boolean isAdhocConversion () 
	{
		Object oo = get_Value(COLUMNNAME_IsAdhocConversion);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Printed.
		@param IsPrinted 
		Indicates if this document / line is printed
	  */
	public void setIsPrinted (boolean IsPrinted)
	{
		set_Value (COLUMNNAME_IsPrinted, Boolean.valueOf(IsPrinted));
	}

	/** Get Printed.
		@return Indicates if this document / line is printed
	  */
	public boolean isPrinted () 
	{
		Object oo = get_Value(COLUMNNAME_IsPrinted);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	public I_M_Product getM_Product() throws Exception 
    {
        Class<?> clazz = MTable.getClass(I_M_Product.Table_Name);
        I_M_Product result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_M_Product)constructor.newInstance(new Object[] {getCtx(), new Integer(getM_Product_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw e;
        }
        return result;
    }

	/** Set Product.
		@param M_Product_ID 
		Product, Service, Item
	  */
	public void setM_Product_ID (int M_Product_ID)
	{
		if (M_Product_ID < 1) 
			set_Value (COLUMNNAME_M_Product_ID, null);
		else 
			set_Value (COLUMNNAME_M_Product_ID, Integer.valueOf(M_Product_ID));
	}

	/** Get Product.
		@return Product, Service, Item
	  */
	public int getM_Product_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Product_ID);
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

	/** Oper_1_ID AD_Reference_ID=239 */
	public static final int OPER_1_ID_AD_Reference_ID=239;
	/** Set Operand 1.
		@param Oper_1_ID 
		First operand for calculation
	  */
	public void setOper_1_ID (int Oper_1_ID)
	{
		if (Oper_1_ID < 1) 
			set_Value (COLUMNNAME_Oper_1_ID, null);
		else 
			set_Value (COLUMNNAME_Oper_1_ID, Integer.valueOf(Oper_1_ID));
	}

	/** Get Operand 1.
		@return First operand for calculation
	  */
	public int getOper_1_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Oper_1_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Oper_2_ID AD_Reference_ID=239 */
	public static final int OPER_2_ID_AD_Reference_ID=239;
	/** Set Operand 2.
		@param Oper_2_ID 
		Second operand for calculation
	  */
	public void setOper_2_ID (int Oper_2_ID)
	{
		if (Oper_2_ID < 1) 
			set_Value (COLUMNNAME_Oper_2_ID, null);
		else 
			set_Value (COLUMNNAME_Oper_2_ID, Integer.valueOf(Oper_2_ID));
	}

	/** Get Operand 2.
		@return Second operand for calculation
	  */
	public int getOper_2_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Oper_2_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Org_ID AD_Reference_ID=130 */
	public static final int ORG_ID_AD_Reference_ID=130;
	/** Set Organization.
		@param Org_ID 
		Organizational entity within client
	  */
	public void setOrg_ID (int Org_ID)
	{
		if (Org_ID < 1) 
			set_Value (COLUMNNAME_Org_ID, null);
		else 
			set_Value (COLUMNNAME_Org_ID, Integer.valueOf(Org_ID));
	}

	/** Get Organization.
		@return Organizational entity within client
	  */
	public int getOrg_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Org_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_PA_ReportColumnSet getPA_ReportColumnSet() throws Exception 
    {
        Class<?> clazz = MTable.getClass(I_PA_ReportColumnSet.Table_Name);
        I_PA_ReportColumnSet result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_PA_ReportColumnSet)constructor.newInstance(new Object[] {getCtx(), new Integer(getPA_ReportColumnSet_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw e;
        }
        return result;
    }

	/** Set Report Column Set.
		@param PA_ReportColumnSet_ID 
		Collection of Columns for Report
	  */
	public void setPA_ReportColumnSet_ID (int PA_ReportColumnSet_ID)
	{
		if (PA_ReportColumnSet_ID < 1)
			 throw new IllegalArgumentException ("PA_ReportColumnSet_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_PA_ReportColumnSet_ID, Integer.valueOf(PA_ReportColumnSet_ID));
	}

	/** Get Report Column Set.
		@return Collection of Columns for Report
	  */
	public int getPA_ReportColumnSet_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PA_ReportColumnSet_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Report Column.
		@param PA_ReportColumn_ID 
		Column in Report
	  */
	public void setPA_ReportColumn_ID (int PA_ReportColumn_ID)
	{
		if (PA_ReportColumn_ID < 1)
			 throw new IllegalArgumentException ("PA_ReportColumn_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_PA_ReportColumn_ID, Integer.valueOf(PA_ReportColumn_ID));
	}

	/** Get Report Column.
		@return Column in Report
	  */
	public int getPA_ReportColumn_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PA_ReportColumn_ID);
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
		if (PostingType == null) throw new IllegalArgumentException ("PostingType is mandatory");
		if (PostingType.equals("A") || PostingType.equals("B") || PostingType.equals("E") || PostingType.equals("S") || PostingType.equals("R")); else throw new IllegalArgumentException ("PostingType Invalid value - " + PostingType + " - Reference_ID=125 - A - B - E - S - R");
		if (PostingType.length() > 1)
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

	/** Set Relative Period.
		@param RelativePeriod 
		Period offset (0 is current)
	  */
	public void setRelativePeriod (BigDecimal RelativePeriod)
	{
		set_Value (COLUMNNAME_RelativePeriod, RelativePeriod);
	}

	/** Get Relative Period.
		@return Period offset (0 is current)
	  */
	public BigDecimal getRelativePeriod () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_RelativePeriod);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Sequence.
		@param SeqNo 
		Method of ordering records; lowest number comes first
	  */
	public void setSeqNo (int SeqNo)
	{
		set_Value (COLUMNNAME_SeqNo, Integer.valueOf(SeqNo));
	}

	/** Get Sequence.
		@return Method of ordering records; lowest number comes first
	  */
	public int getSeqNo () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_SeqNo);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}