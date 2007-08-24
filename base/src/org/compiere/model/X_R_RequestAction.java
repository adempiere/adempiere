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

/** Generated Model for R_RequestAction
 *  @author Adempiere (generated) 
 *  @version Release 3.3.0 - $Id$ */
public class X_R_RequestAction extends PO implements I_R_RequestAction, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_R_RequestAction (Properties ctx, int R_RequestAction_ID, String trxName)
    {
      super (ctx, R_RequestAction_ID, trxName);
      /** if (R_RequestAction_ID == 0)        {			setR_RequestAction_ID (0);
			setR_Request_ID (0);
} */
    }

    /** Load Constructor */
    public X_R_RequestAction (Properties ctx, ResultSet rs, String trxName)
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
      POInfo poi = POInfo.getPOInfo (ctx, Table_ID);
      return poi;
    }

    public String toString()
    {
      StringBuffer sb = new StringBuffer ("X_R_RequestAction[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_AD_Role getI_AD_Role() throws Exception 
    {
        Class<?> clazz = MTable.getClass(I_AD_Role.Table_Name);
        I_AD_Role result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_AD_Role)constructor.newInstance(new Object[] {getCtx(), new Integer(getAD_Role_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw e;
        }
        return result;
    }

	/** Set Role.
		@param AD_Role_ID 
		Responsibility Role
	  */
	public void setAD_Role_ID (int AD_Role_ID)
	{
		if (AD_Role_ID <= 0) 		set_ValueNoCheck (COLUMNNAME_AD_Role_ID, null);
 else 
		set_ValueNoCheck (COLUMNNAME_AD_Role_ID, Integer.valueOf(AD_Role_ID));
	}

	/** Get Role.
		@return Responsibility Role
	  */
	public int getAD_Role_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Role_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_AD_User getI_AD_User() throws Exception 
    {
        Class<?> clazz = MTable.getClass(I_AD_User.Table_Name);
        I_AD_User result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_AD_User)constructor.newInstance(new Object[] {getCtx(), new Integer(getAD_User_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw e;
        }
        return result;
    }

	/** Set User/Contact.
		@param AD_User_ID 
		User within the system - Internal or Business Partner Contact
	  */
	public void setAD_User_ID (int AD_User_ID)
	{
		if (AD_User_ID <= 0) 		set_ValueNoCheck (COLUMNNAME_AD_User_ID, null);
 else 
		set_ValueNoCheck (COLUMNNAME_AD_User_ID, Integer.valueOf(AD_User_ID));
	}

	/** Get User/Contact.
		@return User within the system - Internal or Business Partner Contact
	  */
	public int getAD_User_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_User_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Asset.
		@param A_Asset_ID 
		Asset used internally or by customers
	  */
	public void setA_Asset_ID (int A_Asset_ID)
	{
		if (A_Asset_ID <= 0) 		set_ValueNoCheck (COLUMNNAME_A_Asset_ID, null);
 else 
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

	public I_C_Activity getI_C_Activity() throws Exception 
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
		if (C_Activity_ID <= 0) 		set_ValueNoCheck (COLUMNNAME_C_Activity_ID, null);
 else 
		set_ValueNoCheck (COLUMNNAME_C_Activity_ID, Integer.valueOf(C_Activity_ID));
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

	/** Set Business Partner .
		@param C_BPartner_ID 
		Identifies a Business Partner
	  */
	public void setC_BPartner_ID (int C_BPartner_ID)
	{
		if (C_BPartner_ID <= 0) 		set_ValueNoCheck (COLUMNNAME_C_BPartner_ID, null);
 else 
		set_ValueNoCheck (COLUMNNAME_C_BPartner_ID, Integer.valueOf(C_BPartner_ID));
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

	/** Set Invoice.
		@param C_Invoice_ID 
		Invoice Identifier
	  */
	public void setC_Invoice_ID (int C_Invoice_ID)
	{
		if (C_Invoice_ID <= 0) 		set_ValueNoCheck (COLUMNNAME_C_Invoice_ID, null);
 else 
		set_ValueNoCheck (COLUMNNAME_C_Invoice_ID, Integer.valueOf(C_Invoice_ID));
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

	/** Set Order.
		@param C_Order_ID 
		Order
	  */
	public void setC_Order_ID (int C_Order_ID)
	{
		if (C_Order_ID <= 0) 		set_ValueNoCheck (COLUMNNAME_C_Order_ID, null);
 else 
		set_ValueNoCheck (COLUMNNAME_C_Order_ID, Integer.valueOf(C_Order_ID));
	}

	/** Get Order.
		@return Order
	  */
	public int getC_Order_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Order_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Payment.
		@param C_Payment_ID 
		Payment identifier
	  */
	public void setC_Payment_ID (int C_Payment_ID)
	{
		if (C_Payment_ID <= 0) 		set_ValueNoCheck (COLUMNNAME_C_Payment_ID, null);
 else 
		set_ValueNoCheck (COLUMNNAME_C_Payment_ID, Integer.valueOf(C_Payment_ID));
	}

	/** Get Payment.
		@return Payment identifier
	  */
	public int getC_Payment_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Payment_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_C_Project getI_C_Project() throws Exception 
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
		if (C_Project_ID <= 0) 		set_ValueNoCheck (COLUMNNAME_C_Project_ID, null);
 else 
		set_ValueNoCheck (COLUMNNAME_C_Project_ID, Integer.valueOf(C_Project_ID));
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

/** ConfidentialType AD_Reference_ID=340 */
public static final int CONFIDENTIALTYPE_AD_Reference_ID=340;/** Public Information = A */
public static final String CONFIDENTIALTYPE_PublicInformation = "A";/** Partner Confidential = C */
public static final String CONFIDENTIALTYPE_PartnerConfidential = "C";/** Internal = I */
public static final String CONFIDENTIALTYPE_Internal = "I";/** Private Information = P */
public static final String CONFIDENTIALTYPE_PrivateInformation = "P";
	/** Set Confidentiality.
		@param ConfidentialType 
		Type of Confidentiality
	  */
	public void setConfidentialType (String ConfidentialType)
	{
if (ConfidentialType == null || ConfidentialType.equals("A") || ConfidentialType.equals("C") || ConfidentialType.equals("I") || ConfidentialType.equals("P")); else throw new IllegalArgumentException ("ConfidentialType Invalid value - " + ConfidentialType + " - Reference_ID=340 - A - C - I - P");		if (ConfidentialType != null && ConfidentialType.length() > 1)
		{
			log.warning("Length > 1 - truncated");
			ConfidentialType = ConfidentialType.substring(0, 0);
		}
		set_ValueNoCheck (COLUMNNAME_ConfidentialType, ConfidentialType);
	}

	/** Get Confidentiality.
		@return Type of Confidentiality
	  */
	public String getConfidentialType () 
	{
		return (String)get_Value(COLUMNNAME_ConfidentialType);
	}

	/** Set Complete Plan.
		@param DateCompletePlan 
		Planned Completion Date
	  */
	public void setDateCompletePlan (Timestamp DateCompletePlan)
	{
		set_Value (COLUMNNAME_DateCompletePlan, DateCompletePlan);
	}

	/** Get Complete Plan.
		@return Planned Completion Date
	  */
	public Timestamp getDateCompletePlan () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateCompletePlan);
	}

	/** Set Date next action.
		@param DateNextAction 
		Date that this request should be acted on
	  */
	public void setDateNextAction (Timestamp DateNextAction)
	{
		set_ValueNoCheck (COLUMNNAME_DateNextAction, DateNextAction);
	}

	/** Get Date next action.
		@return Date that this request should be acted on
	  */
	public Timestamp getDateNextAction () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateNextAction);
	}

	/** Set Start Plan.
		@param DateStartPlan 
		Planned Start Date
	  */
	public void setDateStartPlan (Timestamp DateStartPlan)
	{
		set_Value (COLUMNNAME_DateStartPlan, DateStartPlan);
	}

	/** Get Start Plan.
		@return Planned Start Date
	  */
	public Timestamp getDateStartPlan () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateStartPlan);
	}

	/** Set End Date.
		@param EndDate 
		Last effective date (inclusive)
	  */
	public void setEndDate (Timestamp EndDate)
	{
		set_Value (COLUMNNAME_EndDate, EndDate);
	}

	/** Get End Date.
		@return Last effective date (inclusive)
	  */
	public Timestamp getEndDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_EndDate);
	}

/** IsEscalated AD_Reference_ID=319 */
public static final int ISESCALATED_AD_Reference_ID=319;/** No = N */
public static final String ISESCALATED_No = "N";/** Yes = Y */
public static final String ISESCALATED_Yes = "Y";
	/** Set Escalated.
		@param IsEscalated 
		This request has been escalated
	  */
	public void setIsEscalated (String IsEscalated)
	{
if (IsEscalated == null || IsEscalated.equals("N") || IsEscalated.equals("Y")); else throw new IllegalArgumentException ("IsEscalated Invalid value - " + IsEscalated + " - Reference_ID=319 - N - Y");		if (IsEscalated != null && IsEscalated.length() > 1)
		{
			log.warning("Length > 1 - truncated");
			IsEscalated = IsEscalated.substring(0, 0);
		}
		set_ValueNoCheck (COLUMNNAME_IsEscalated, IsEscalated);
	}

	/** Get Escalated.
		@return This request has been escalated
	  */
	public String getIsEscalated () 
	{
		return (String)get_Value(COLUMNNAME_IsEscalated);
	}

/** IsInvoiced AD_Reference_ID=319 */
public static final int ISINVOICED_AD_Reference_ID=319;/** No = N */
public static final String ISINVOICED_No = "N";/** Yes = Y */
public static final String ISINVOICED_Yes = "Y";
	/** Set Invoiced.
		@param IsInvoiced 
		Is this invoiced?
	  */
	public void setIsInvoiced (String IsInvoiced)
	{
if (IsInvoiced == null || IsInvoiced.equals("N") || IsInvoiced.equals("Y")); else throw new IllegalArgumentException ("IsInvoiced Invalid value - " + IsInvoiced + " - Reference_ID=319 - N - Y");		if (IsInvoiced != null && IsInvoiced.length() > 1)
		{
			log.warning("Length > 1 - truncated");
			IsInvoiced = IsInvoiced.substring(0, 0);
		}
		set_ValueNoCheck (COLUMNNAME_IsInvoiced, IsInvoiced);
	}

	/** Get Invoiced.
		@return Is this invoiced?
	  */
	public String getIsInvoiced () 
	{
		return (String)get_Value(COLUMNNAME_IsInvoiced);
	}

/** IsSelfService AD_Reference_ID=319 */
public static final int ISSELFSERVICE_AD_Reference_ID=319;/** No = N */
public static final String ISSELFSERVICE_No = "N";/** Yes = Y */
public static final String ISSELFSERVICE_Yes = "Y";
	/** Set Self-Service.
		@param IsSelfService 
		This is a Self-Service entry or this entry can be changed via Self-Service
	  */
	public void setIsSelfService (String IsSelfService)
	{
if (IsSelfService == null || IsSelfService.equals("N") || IsSelfService.equals("Y")); else throw new IllegalArgumentException ("IsSelfService Invalid value - " + IsSelfService + " - Reference_ID=319 - N - Y");		if (IsSelfService != null && IsSelfService.length() > 1)
		{
			log.warning("Length > 1 - truncated");
			IsSelfService = IsSelfService.substring(0, 0);
		}
		set_ValueNoCheck (COLUMNNAME_IsSelfService, IsSelfService);
	}

	/** Get Self-Service.
		@return This is a Self-Service entry or this entry can be changed via Self-Service
	  */
	public String getIsSelfService () 
	{
		return (String)get_Value(COLUMNNAME_IsSelfService);
	}

	/** Set Shipment/Receipt.
		@param M_InOut_ID 
		Material Shipment Document
	  */
	public void setM_InOut_ID (int M_InOut_ID)
	{
		if (M_InOut_ID <= 0) 		set_ValueNoCheck (COLUMNNAME_M_InOut_ID, null);
 else 
		set_ValueNoCheck (COLUMNNAME_M_InOut_ID, Integer.valueOf(M_InOut_ID));
	}

	/** Get Shipment/Receipt.
		@return Material Shipment Document
	  */
	public int getM_InOut_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_InOut_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

/** M_ProductSpent_ID AD_Reference_ID=162 */
public static final int M_PRODUCTSPENT_ID_AD_Reference_ID=162;
	/** Set Product Used.
		@param M_ProductSpent_ID 
		Product/Resource/Service used in Request
	  */
	public void setM_ProductSpent_ID (int M_ProductSpent_ID)
	{
		if (M_ProductSpent_ID <= 0) 		set_Value (COLUMNNAME_M_ProductSpent_ID, null);
 else 
		set_Value (COLUMNNAME_M_ProductSpent_ID, Integer.valueOf(M_ProductSpent_ID));
	}

	/** Get Product Used.
		@return Product/Resource/Service used in Request
	  */
	public int getM_ProductSpent_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_ProductSpent_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Product.
		@param M_Product_ID 
		Product, Service, Item
	  */
	public void setM_Product_ID (int M_Product_ID)
	{
		if (M_Product_ID <= 0) 		set_ValueNoCheck (COLUMNNAME_M_Product_ID, null);
 else 
		set_ValueNoCheck (COLUMNNAME_M_Product_ID, Integer.valueOf(M_Product_ID));
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

	public I_M_RMA getI_M_RMA() throws Exception 
    {
        Class<?> clazz = MTable.getClass(I_M_RMA.Table_Name);
        I_M_RMA result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_M_RMA)constructor.newInstance(new Object[] {getCtx(), new Integer(getM_RMA_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw e;
        }
        return result;
    }

	/** Set RMA.
		@param M_RMA_ID 
		Return Material Authorization
	  */
	public void setM_RMA_ID (int M_RMA_ID)
	{
		if (M_RMA_ID <= 0) 		set_ValueNoCheck (COLUMNNAME_M_RMA_ID, null);
 else 
		set_ValueNoCheck (COLUMNNAME_M_RMA_ID, Integer.valueOf(M_RMA_ID));
	}

	/** Get RMA.
		@return Return Material Authorization
	  */
	public int getM_RMA_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_RMA_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Null Columns.
		@param NullColumns 
		Columns with NULL value
	  */
	public void setNullColumns (String NullColumns)
	{
		if (NullColumns != null && NullColumns.length() > 255)
		{
			log.warning("Length > 255 - truncated");
			NullColumns = NullColumns.substring(0, 254);
		}
		set_ValueNoCheck (COLUMNNAME_NullColumns, NullColumns);
	}

	/** Get Null Columns.
		@return Columns with NULL value
	  */
	public String getNullColumns () 
	{
		return (String)get_Value(COLUMNNAME_NullColumns);
	}

/** Priority AD_Reference_ID=154 */
public static final int PRIORITY_AD_Reference_ID=154;/** Urgent = 1 */
public static final String PRIORITY_Urgent = "1";/** High = 3 */
public static final String PRIORITY_High = "3";/** Medium = 5 */
public static final String PRIORITY_Medium = "5";/** Low = 7 */
public static final String PRIORITY_Low = "7";/** Minor = 9 */
public static final String PRIORITY_Minor = "9";
	/** Set Priority.
		@param Priority 
		Indicates if this request is of a high, medium or low priority.
	  */
	public void setPriority (String Priority)
	{
if (Priority == null || Priority.equals("1") || Priority.equals("3") || Priority.equals("5") || Priority.equals("7") || Priority.equals("9")); else throw new IllegalArgumentException ("Priority Invalid value - " + Priority + " - Reference_ID=154 - 1 - 3 - 5 - 7 - 9");		if (Priority != null && Priority.length() > 1)
		{
			log.warning("Length > 1 - truncated");
			Priority = Priority.substring(0, 0);
		}
		set_ValueNoCheck (COLUMNNAME_Priority, Priority);
	}

	/** Get Priority.
		@return Indicates if this request is of a high, medium or low priority.
	  */
	public String getPriority () 
	{
		return (String)get_Value(COLUMNNAME_Priority);
	}

/** PriorityUser AD_Reference_ID=154 */
public static final int PRIORITYUSER_AD_Reference_ID=154;/** Urgent = 1 */
public static final String PRIORITYUSER_Urgent = "1";/** High = 3 */
public static final String PRIORITYUSER_High = "3";/** Medium = 5 */
public static final String PRIORITYUSER_Medium = "5";/** Low = 7 */
public static final String PRIORITYUSER_Low = "7";/** Minor = 9 */
public static final String PRIORITYUSER_Minor = "9";
	/** Set User Importance.
		@param PriorityUser 
		Priority of the issue for the User
	  */
	public void setPriorityUser (String PriorityUser)
	{
if (PriorityUser == null || PriorityUser.equals("1") || PriorityUser.equals("3") || PriorityUser.equals("5") || PriorityUser.equals("7") || PriorityUser.equals("9")); else throw new IllegalArgumentException ("PriorityUser Invalid value - " + PriorityUser + " - Reference_ID=154 - 1 - 3 - 5 - 7 - 9");		if (PriorityUser != null && PriorityUser.length() > 1)
		{
			log.warning("Length > 1 - truncated");
			PriorityUser = PriorityUser.substring(0, 0);
		}
		set_ValueNoCheck (COLUMNNAME_PriorityUser, PriorityUser);
	}

	/** Get User Importance.
		@return Priority of the issue for the User
	  */
	public String getPriorityUser () 
	{
		return (String)get_Value(COLUMNNAME_PriorityUser);
	}

	/** Set Quantity Invoiced.
		@param QtyInvoiced 
		Invoiced Quantity
	  */
	public void setQtyInvoiced (BigDecimal QtyInvoiced)
	{
		set_Value (COLUMNNAME_QtyInvoiced, QtyInvoiced);
	}

	/** Get Quantity Invoiced.
		@return Invoiced Quantity
	  */
	public BigDecimal getQtyInvoiced () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_QtyInvoiced);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Quantity Plan.
		@param QtyPlan 
		Planned Quantity
	  */
	public void setQtyPlan (BigDecimal QtyPlan)
	{
		set_Value (COLUMNNAME_QtyPlan, QtyPlan);
	}

	/** Get Quantity Plan.
		@return Planned Quantity
	  */
	public BigDecimal getQtyPlan () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_QtyPlan);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Quantity Used.
		@param QtySpent 
		Quantity used for this event
	  */
	public void setQtySpent (BigDecimal QtySpent)
	{
		set_Value (COLUMNNAME_QtySpent, QtySpent);
	}

	/** Get Quantity Used.
		@return Quantity used for this event
	  */
	public BigDecimal getQtySpent () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_QtySpent);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	public I_R_Category getI_R_Category() throws Exception 
    {
        Class<?> clazz = MTable.getClass(I_R_Category.Table_Name);
        I_R_Category result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_R_Category)constructor.newInstance(new Object[] {getCtx(), new Integer(getR_Category_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw e;
        }
        return result;
    }

	/** Set Category.
		@param R_Category_ID 
		Request Category
	  */
	public void setR_Category_ID (int R_Category_ID)
	{
		if (R_Category_ID <= 0) 		set_ValueNoCheck (COLUMNNAME_R_Category_ID, null);
 else 
		set_ValueNoCheck (COLUMNNAME_R_Category_ID, Integer.valueOf(R_Category_ID));
	}

	/** Get Category.
		@return Request Category
	  */
	public int getR_Category_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_R_Category_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_R_Group getI_R_Group() throws Exception 
    {
        Class<?> clazz = MTable.getClass(I_R_Group.Table_Name);
        I_R_Group result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_R_Group)constructor.newInstance(new Object[] {getCtx(), new Integer(getR_Group_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw e;
        }
        return result;
    }

	/** Set Group.
		@param R_Group_ID 
		Request Group
	  */
	public void setR_Group_ID (int R_Group_ID)
	{
		if (R_Group_ID <= 0) 		set_ValueNoCheck (COLUMNNAME_R_Group_ID, null);
 else 
		set_ValueNoCheck (COLUMNNAME_R_Group_ID, Integer.valueOf(R_Group_ID));
	}

	/** Get Group.
		@return Request Group
	  */
	public int getR_Group_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_R_Group_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Request History.
		@param R_RequestAction_ID 
		Request has been changed
	  */
	public void setR_RequestAction_ID (int R_RequestAction_ID)
	{
		if (R_RequestAction_ID < 1)
			 throw new IllegalArgumentException ("R_RequestAction_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_R_RequestAction_ID, Integer.valueOf(R_RequestAction_ID));
	}

	/** Get Request History.
		@return Request has been changed
	  */
	public int getR_RequestAction_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_R_RequestAction_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_R_RequestType getI_R_RequestType() throws Exception 
    {
        Class<?> clazz = MTable.getClass(I_R_RequestType.Table_Name);
        I_R_RequestType result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_R_RequestType)constructor.newInstance(new Object[] {getCtx(), new Integer(getR_RequestType_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw e;
        }
        return result;
    }

	/** Set Request Type.
		@param R_RequestType_ID 
		Type of request (e.g. Inquiry, Complaint, ..)
	  */
	public void setR_RequestType_ID (int R_RequestType_ID)
	{
		if (R_RequestType_ID <= 0) 		set_ValueNoCheck (COLUMNNAME_R_RequestType_ID, null);
 else 
		set_ValueNoCheck (COLUMNNAME_R_RequestType_ID, Integer.valueOf(R_RequestType_ID));
	}

	/** Get Request Type.
		@return Type of request (e.g. Inquiry, Complaint, ..)
	  */
	public int getR_RequestType_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_R_RequestType_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_R_Request getI_R_Request() throws Exception 
    {
        Class<?> clazz = MTable.getClass(I_R_Request.Table_Name);
        I_R_Request result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_R_Request)constructor.newInstance(new Object[] {getCtx(), new Integer(getR_Request_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw e;
        }
        return result;
    }

	/** Set Request.
		@param R_Request_ID 
		Request from a Business Partner or Prospect
	  */
	public void setR_Request_ID (int R_Request_ID)
	{
		if (R_Request_ID < 1)
			 throw new IllegalArgumentException ("R_Request_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_R_Request_ID, Integer.valueOf(R_Request_ID));
	}

	/** Get Request.
		@return Request from a Business Partner or Prospect
	  */
	public int getR_Request_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_R_Request_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_R_Resolution getI_R_Resolution() throws Exception 
    {
        Class<?> clazz = MTable.getClass(I_R_Resolution.Table_Name);
        I_R_Resolution result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_R_Resolution)constructor.newInstance(new Object[] {getCtx(), new Integer(getR_Resolution_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw e;
        }
        return result;
    }

	/** Set Resolution.
		@param R_Resolution_ID 
		Request Resolution
	  */
	public void setR_Resolution_ID (int R_Resolution_ID)
	{
		if (R_Resolution_ID <= 0) 		set_ValueNoCheck (COLUMNNAME_R_Resolution_ID, null);
 else 
		set_ValueNoCheck (COLUMNNAME_R_Resolution_ID, Integer.valueOf(R_Resolution_ID));
	}

	/** Get Resolution.
		@return Request Resolution
	  */
	public int getR_Resolution_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_R_Resolution_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_R_Status getI_R_Status() throws Exception 
    {
        Class<?> clazz = MTable.getClass(I_R_Status.Table_Name);
        I_R_Status result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_R_Status)constructor.newInstance(new Object[] {getCtx(), new Integer(getR_Status_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw e;
        }
        return result;
    }

	/** Set Status.
		@param R_Status_ID 
		Request Status
	  */
	public void setR_Status_ID (int R_Status_ID)
	{
		if (R_Status_ID <= 0) 		set_ValueNoCheck (COLUMNNAME_R_Status_ID, null);
 else 
		set_ValueNoCheck (COLUMNNAME_R_Status_ID, Integer.valueOf(R_Status_ID));
	}

	/** Get Status.
		@return Request Status
	  */
	public int getR_Status_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_R_Status_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

/** SalesRep_ID AD_Reference_ID=110 */
public static final int SALESREP_ID_AD_Reference_ID=110;
	/** Set Sales Representative.
		@param SalesRep_ID 
		Sales Representative or Company Agent
	  */
	public void setSalesRep_ID (int SalesRep_ID)
	{
		if (SalesRep_ID <= 0) 		set_ValueNoCheck (COLUMNNAME_SalesRep_ID, null);
 else 
		set_ValueNoCheck (COLUMNNAME_SalesRep_ID, Integer.valueOf(SalesRep_ID));
	}

	/** Get Sales Representative.
		@return Sales Representative or Company Agent
	  */
	public int getSalesRep_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_SalesRep_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Start Date.
		@param StartDate 
		First effective day (inclusive)
	  */
	public void setStartDate (Timestamp StartDate)
	{
		set_Value (COLUMNNAME_StartDate, StartDate);
	}

	/** Get Start Date.
		@return First effective day (inclusive)
	  */
	public Timestamp getStartDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_StartDate);
	}

	/** Set Summary.
		@param Summary 
		Textual summary of this request
	  */
	public void setSummary (String Summary)
	{
		if (Summary != null && Summary.length() > 2000)
		{
			log.warning("Length > 2000 - truncated");
			Summary = Summary.substring(0, 1999);
		}
		set_ValueNoCheck (COLUMNNAME_Summary, Summary);
	}

	/** Get Summary.
		@return Textual summary of this request
	  */
	public String getSummary () 
	{
		return (String)get_Value(COLUMNNAME_Summary);
	}

/** TaskStatus AD_Reference_ID=366 */
public static final int TASKSTATUS_AD_Reference_ID=366;/**  0% Not Started = 0 */
public static final String TASKSTATUS_0NotStarted = "0";/**  20% Started = 2 */
public static final String TASKSTATUS_20Started = "2";/**  40% Busy = 4 */
public static final String TASKSTATUS_40Busy = "4";/**  60% Good Progress = 6 */
public static final String TASKSTATUS_60GoodProgress = "6";/**  80% Nearly Done = 8 */
public static final String TASKSTATUS_80NearlyDone = "8";/**  90% Finishing = 9 */
public static final String TASKSTATUS_90Finishing = "9";/**  95% Almost Done = A */
public static final String TASKSTATUS_95AlmostDone = "A";/**  99% Cleaning up = C */
public static final String TASKSTATUS_99CleaningUp = "C";/** 100% Complete = D */
public static final String TASKSTATUS_100Complete = "D";
	/** Set Task Status.
		@param TaskStatus 
		Status of the Task
	  */
	public void setTaskStatus (String TaskStatus)
	{
if (TaskStatus == null || TaskStatus.equals("0") || TaskStatus.equals("2") || TaskStatus.equals("4") || TaskStatus.equals("6") || TaskStatus.equals("8") || TaskStatus.equals("9") || TaskStatus.equals("A") || TaskStatus.equals("C") || TaskStatus.equals("D")); else throw new IllegalArgumentException ("TaskStatus Invalid value - " + TaskStatus + " - Reference_ID=366 - 0 - 2 - 4 - 6 - 8 - 9 - A - C - D");		if (TaskStatus != null && TaskStatus.length() > 1)
		{
			log.warning("Length > 1 - truncated");
			TaskStatus = TaskStatus.substring(0, 0);
		}
		set_Value (COLUMNNAME_TaskStatus, TaskStatus);
	}

	/** Get Task Status.
		@return Status of the Task
	  */
	public String getTaskStatus () 
	{
		return (String)get_Value(COLUMNNAME_TaskStatus);
	}
}