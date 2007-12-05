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
import java.sql.Timestamp;
import java.util.Properties;
import java.util.logging.Level;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;

/** Generated Model for C_Project
 *  @author Adempiere (generated) 
 *  @version Release 3.3.1b - $Id$ */
public class X_C_Project extends PO implements I_C_Project, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_C_Project (Properties ctx, int C_Project_ID, String trxName)
    {
      super (ctx, C_Project_ID, trxName);
      /** if (C_Project_ID == 0)
        {
			setC_Currency_ID (0);
			setC_Project_ID (0);
			setCommittedAmt (Env.ZERO);
			setCommittedQty (Env.ZERO);
			setInvoicedAmt (Env.ZERO);
			setInvoicedQty (Env.ZERO);
			setIsCommitCeiling (false);
			setIsCommitment (false);
			setIsSummary (false);
			setName (null);
			setPlannedAmt (Env.ZERO);
			setPlannedMarginAmt (Env.ZERO);
			setPlannedQty (Env.ZERO);
			setProcessed (false);
			setProjInvoiceRule (null);
// -
			setProjectBalanceAmt (Env.ZERO);
			setProjectLineLevel (null);
// P
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_C_Project (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 3 - Client - Org 
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
      StringBuffer sb = new StringBuffer ("X_C_Project[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_AD_User getAD_User() throws Exception 
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
		if (AD_User_ID <= 0) 
			set_Value (COLUMNNAME_AD_User_ID, null);
		else 
			set_Value (COLUMNNAME_AD_User_ID, Integer.valueOf(AD_User_ID));
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

	/** C_BPartnerSR_ID AD_Reference_ID=353 */
	public static final int C_BPARTNERSR_ID_AD_Reference_ID=353;
	/** Set BPartner (Agent).
		@param C_BPartnerSR_ID 
		Business Partner (Agent or Sales Rep)
	  */
	public void setC_BPartnerSR_ID (int C_BPartnerSR_ID)
	{
		if (C_BPartnerSR_ID <= 0) 
			set_Value (COLUMNNAME_C_BPartnerSR_ID, null);
		else 
			set_Value (COLUMNNAME_C_BPartnerSR_ID, Integer.valueOf(C_BPartnerSR_ID));
	}

	/** Get BPartner (Agent).
		@return Business Partner (Agent or Sales Rep)
	  */
	public int getC_BPartnerSR_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BPartnerSR_ID);
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
		if (C_BPartner_ID <= 0) 
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

	public I_C_BPartner_Location getC_BPartner_Location() throws Exception 
    {
        Class<?> clazz = MTable.getClass(I_C_BPartner_Location.Table_Name);
        I_C_BPartner_Location result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_BPartner_Location)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_BPartner_Location_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw e;
        }
        return result;
    }

	/** Set Partner Location.
		@param C_BPartner_Location_ID 
		Identifies the (ship to) address for this Business Partner
	  */
	public void setC_BPartner_Location_ID (int C_BPartner_Location_ID)
	{
		if (C_BPartner_Location_ID <= 0) 
			set_Value (COLUMNNAME_C_BPartner_Location_ID, null);
		else 
			set_Value (COLUMNNAME_C_BPartner_Location_ID, Integer.valueOf(C_BPartner_Location_ID));
	}

	/** Get Partner Location.
		@return Identifies the (ship to) address for this Business Partner
	  */
	public int getC_BPartner_Location_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BPartner_Location_ID);
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
		if (C_Campaign_ID <= 0) 
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

	public I_C_PaymentTerm getC_PaymentTerm() throws Exception 
    {
        Class<?> clazz = MTable.getClass(I_C_PaymentTerm.Table_Name);
        I_C_PaymentTerm result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_PaymentTerm)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_PaymentTerm_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw e;
        }
        return result;
    }

	/** Set Payment Term.
		@param C_PaymentTerm_ID 
		The terms of Payment (timing, discount)
	  */
	public void setC_PaymentTerm_ID (int C_PaymentTerm_ID)
	{
		if (C_PaymentTerm_ID <= 0) 
			set_Value (COLUMNNAME_C_PaymentTerm_ID, null);
		else 
			set_Value (COLUMNNAME_C_PaymentTerm_ID, Integer.valueOf(C_PaymentTerm_ID));
	}

	/** Get Payment Term.
		@return The terms of Payment (timing, discount)
	  */
	public int getC_PaymentTerm_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_PaymentTerm_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_C_Phase getC_Phase() throws Exception 
    {
        Class<?> clazz = MTable.getClass(I_C_Phase.Table_Name);
        I_C_Phase result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_Phase)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_Phase_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw e;
        }
        return result;
    }

	/** Set Standard Phase.
		@param C_Phase_ID 
		Standard Phase of the Project Type
	  */
	public void setC_Phase_ID (int C_Phase_ID)
	{
		if (C_Phase_ID <= 0) 
			set_Value (COLUMNNAME_C_Phase_ID, null);
		else 
			set_Value (COLUMNNAME_C_Phase_ID, Integer.valueOf(C_Phase_ID));
	}

	/** Get Standard Phase.
		@return Standard Phase of the Project Type
	  */
	public int getC_Phase_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Phase_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Project Type.
		@param C_ProjectType_ID 
		Type of the project
	  */
	public void setC_ProjectType_ID (String C_ProjectType_ID)
	{

		if (C_ProjectType_ID != null && C_ProjectType_ID.length() > 22)
		{
			log.warning("Length > 22 - truncated");
			C_ProjectType_ID = C_ProjectType_ID.substring(0, 22);
		}
		set_Value (COLUMNNAME_C_ProjectType_ID, C_ProjectType_ID);
	}

	/** Get Project Type.
		@return Type of the project
	  */
	public String getC_ProjectType_ID () 
	{
		return (String)get_Value(COLUMNNAME_C_ProjectType_ID);
	}

	/** Set Project.
		@param C_Project_ID 
		Financial Project
	  */
	public void setC_Project_ID (int C_Project_ID)
	{
		if (C_Project_ID < 1)
			 throw new IllegalArgumentException ("C_Project_ID is mandatory.");
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

	/** Set Committed Amount.
		@param CommittedAmt 
		The (legal) commitment amount
	  */
	public void setCommittedAmt (BigDecimal CommittedAmt)
	{
		if (CommittedAmt == null)
			throw new IllegalArgumentException ("CommittedAmt is mandatory.");
		set_Value (COLUMNNAME_CommittedAmt, CommittedAmt);
	}

	/** Get Committed Amount.
		@return The (legal) commitment amount
	  */
	public BigDecimal getCommittedAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_CommittedAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Committed Quantity.
		@param CommittedQty 
		The (legal) commitment Quantity
	  */
	public void setCommittedQty (BigDecimal CommittedQty)
	{
		if (CommittedQty == null)
			throw new IllegalArgumentException ("CommittedQty is mandatory.");
		set_Value (COLUMNNAME_CommittedQty, CommittedQty);
	}

	/** Get Committed Quantity.
		@return The (legal) commitment Quantity
	  */
	public BigDecimal getCommittedQty () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_CommittedQty);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Copy From.
		@param CopyFrom 
		Copy From Record
	  */
	public void setCopyFrom (String CopyFrom)
	{

		if (CopyFrom != null && CopyFrom.length() > 1)
		{
			log.warning("Length > 1 - truncated");
			CopyFrom = CopyFrom.substring(0, 1);
		}
		set_Value (COLUMNNAME_CopyFrom, CopyFrom);
	}

	/** Get Copy From.
		@return Copy From Record
	  */
	public String getCopyFrom () 
	{
		return (String)get_Value(COLUMNNAME_CopyFrom);
	}

	/** Set Contract Date.
		@param DateContract 
		The (planned) effective date of this document.
	  */
	public void setDateContract (Timestamp DateContract)
	{
		set_Value (COLUMNNAME_DateContract, DateContract);
	}

	/** Get Contract Date.
		@return The (planned) effective date of this document.
	  */
	public Timestamp getDateContract () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateContract);
	}

	/** Set Finish Date.
		@param DateFinish 
		Finish or (planned) completion date
	  */
	public void setDateFinish (Timestamp DateFinish)
	{
		set_Value (COLUMNNAME_DateFinish, DateFinish);
	}

	/** Get Finish Date.
		@return Finish or (planned) completion date
	  */
	public Timestamp getDateFinish () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateFinish);
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

	/** Set Generate To.
		@param GenerateTo 
		Generate To
	  */
	public void setGenerateTo (String GenerateTo)
	{

		if (GenerateTo != null && GenerateTo.length() > 1)
		{
			log.warning("Length > 1 - truncated");
			GenerateTo = GenerateTo.substring(0, 1);
		}
		set_Value (COLUMNNAME_GenerateTo, GenerateTo);
	}

	/** Get Generate To.
		@return Generate To
	  */
	public String getGenerateTo () 
	{
		return (String)get_Value(COLUMNNAME_GenerateTo);
	}

	/** Set Invoiced Amount.
		@param InvoicedAmt 
		The amount invoiced
	  */
	public void setInvoicedAmt (BigDecimal InvoicedAmt)
	{
		if (InvoicedAmt == null)
			throw new IllegalArgumentException ("InvoicedAmt is mandatory.");
		set_ValueNoCheck (COLUMNNAME_InvoicedAmt, InvoicedAmt);
	}

	/** Get Invoiced Amount.
		@return The amount invoiced
	  */
	public BigDecimal getInvoicedAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_InvoicedAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Quantity Invoiced .
		@param InvoicedQty 
		The quantity invoiced
	  */
	public void setInvoicedQty (BigDecimal InvoicedQty)
	{
		if (InvoicedQty == null)
			throw new IllegalArgumentException ("InvoicedQty is mandatory.");
		set_ValueNoCheck (COLUMNNAME_InvoicedQty, InvoicedQty);
	}

	/** Get Quantity Invoiced .
		@return The quantity invoiced
	  */
	public BigDecimal getInvoicedQty () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_InvoicedQty);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Commitment is Ceiling.
		@param IsCommitCeiling 
		The commitment amount/quantity is the chargeable ceiling 
	  */
	public void setIsCommitCeiling (boolean IsCommitCeiling)
	{
		set_Value (COLUMNNAME_IsCommitCeiling, Boolean.valueOf(IsCommitCeiling));
	}

	/** Get Commitment is Ceiling.
		@return The commitment amount/quantity is the chargeable ceiling 
	  */
	public boolean isCommitCeiling () 
	{
		Object oo = get_Value(COLUMNNAME_IsCommitCeiling);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Commitment.
		@param IsCommitment 
		Is this document a (legal) commitment?
	  */
	public void setIsCommitment (boolean IsCommitment)
	{
		set_Value (COLUMNNAME_IsCommitment, Boolean.valueOf(IsCommitment));
	}

	/** Get Commitment.
		@return Is this document a (legal) commitment?
	  */
	public boolean isCommitment () 
	{
		Object oo = get_Value(COLUMNNAME_IsCommitment);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Summary Level.
		@param IsSummary 
		This is a summary entity
	  */
	public void setIsSummary (boolean IsSummary)
	{
		set_Value (COLUMNNAME_IsSummary, Boolean.valueOf(IsSummary));
	}

	/** Get Summary Level.
		@return This is a summary entity
	  */
	public boolean isSummary () 
	{
		Object oo = get_Value(COLUMNNAME_IsSummary);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	public I_M_PriceList_Version getM_PriceList_Version() throws Exception 
    {
        Class<?> clazz = MTable.getClass(I_M_PriceList_Version.Table_Name);
        I_M_PriceList_Version result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_M_PriceList_Version)constructor.newInstance(new Object[] {getCtx(), new Integer(getM_PriceList_Version_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw e;
        }
        return result;
    }

	/** Set Price List Version.
		@param M_PriceList_Version_ID 
		Identifies a unique instance of a Price List
	  */
	public void setM_PriceList_Version_ID (int M_PriceList_Version_ID)
	{
		if (M_PriceList_Version_ID <= 0) 
			set_Value (COLUMNNAME_M_PriceList_Version_ID, null);
		else 
			set_Value (COLUMNNAME_M_PriceList_Version_ID, Integer.valueOf(M_PriceList_Version_ID));
	}

	/** Get Price List Version.
		@return Identifies a unique instance of a Price List
	  */
	public int getM_PriceList_Version_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_PriceList_Version_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_M_Warehouse getM_Warehouse() throws Exception 
    {
        Class<?> clazz = MTable.getClass(I_M_Warehouse.Table_Name);
        I_M_Warehouse result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_M_Warehouse)constructor.newInstance(new Object[] {getCtx(), new Integer(getM_Warehouse_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw e;
        }
        return result;
    }

	/** Set Warehouse.
		@param M_Warehouse_ID 
		Storage Warehouse and Service Point
	  */
	public void setM_Warehouse_ID (int M_Warehouse_ID)
	{
		if (M_Warehouse_ID <= 0) 
			set_Value (COLUMNNAME_M_Warehouse_ID, null);
		else 
			set_Value (COLUMNNAME_M_Warehouse_ID, Integer.valueOf(M_Warehouse_ID));
	}

	/** Get Warehouse.
		@return Storage Warehouse and Service Point
	  */
	public int getM_Warehouse_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Warehouse_ID);
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

	/** Set Note.
		@param Note 
		Optional additional user defined information
	  */
	public void setNote (String Note)
	{

		if (Note != null && Note.length() > 2000)
		{
			log.warning("Length > 2000 - truncated");
			Note = Note.substring(0, 2000);
		}
		set_Value (COLUMNNAME_Note, Note);
	}

	/** Get Note.
		@return Optional additional user defined information
	  */
	public String getNote () 
	{
		return (String)get_Value(COLUMNNAME_Note);
	}

	/** Set Order Reference.
		@param POReference 
		Transaction Reference Number (Sales Order, Purchase Order) of your Business Partner
	  */
	public void setPOReference (String POReference)
	{

		if (POReference != null && POReference.length() > 20)
		{
			log.warning("Length > 20 - truncated");
			POReference = POReference.substring(0, 20);
		}
		set_Value (COLUMNNAME_POReference, POReference);
	}

	/** Get Order Reference.
		@return Transaction Reference Number (Sales Order, Purchase Order) of your Business Partner
	  */
	public String getPOReference () 
	{
		return (String)get_Value(COLUMNNAME_POReference);
	}

	/** Set Planned Amount.
		@param PlannedAmt 
		Planned amount for this project
	  */
	public void setPlannedAmt (BigDecimal PlannedAmt)
	{
		if (PlannedAmt == null)
			throw new IllegalArgumentException ("PlannedAmt is mandatory.");
		set_Value (COLUMNNAME_PlannedAmt, PlannedAmt);
	}

	/** Get Planned Amount.
		@return Planned amount for this project
	  */
	public BigDecimal getPlannedAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_PlannedAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Planned Margin.
		@param PlannedMarginAmt 
		Project's planned margin amount
	  */
	public void setPlannedMarginAmt (BigDecimal PlannedMarginAmt)
	{
		if (PlannedMarginAmt == null)
			throw new IllegalArgumentException ("PlannedMarginAmt is mandatory.");
		set_Value (COLUMNNAME_PlannedMarginAmt, PlannedMarginAmt);
	}

	/** Get Planned Margin.
		@return Project's planned margin amount
	  */
	public BigDecimal getPlannedMarginAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_PlannedMarginAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Planned Quantity.
		@param PlannedQty 
		Planned quantity for this project
	  */
	public void setPlannedQty (BigDecimal PlannedQty)
	{
		if (PlannedQty == null)
			throw new IllegalArgumentException ("PlannedQty is mandatory.");
		set_Value (COLUMNNAME_PlannedQty, PlannedQty);
	}

	/** Get Planned Quantity.
		@return Planned quantity for this project
	  */
	public BigDecimal getPlannedQty () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_PlannedQty);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Processed.
		@param Processed 
		The document has been processed
	  */
	public void setProcessed (boolean Processed)
	{
		set_Value (COLUMNNAME_Processed, Boolean.valueOf(Processed));
	}

	/** Get Processed.
		@return The document has been processed
	  */
	public boolean isProcessed () 
	{
		Object oo = get_Value(COLUMNNAME_Processed);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
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

	/** ProjInvoiceRule AD_Reference_ID=383 */
	public static final int PROJINVOICERULE_AD_Reference_ID=383;
	/** None = - */
	public static final String PROJINVOICERULE_None = "-";
	/** Committed Amount = C */
	public static final String PROJINVOICERULE_CommittedAmount = "C";
	/** Time&Material max Comitted = c */
	public static final String PROJINVOICERULE_TimeMaterialMaxComitted = "c";
	/** Time&Material = T */
	public static final String PROJINVOICERULE_TimeMaterial = "T";
	/** Product  Quantity = P */
	public static final String PROJINVOICERULE_ProductQuantity = "P";
	/** Set Invoice Rule.
		@param ProjInvoiceRule 
		Invoice Rule for the project
	  */
	public void setProjInvoiceRule (String ProjInvoiceRule)
	{
		if (ProjInvoiceRule == null) throw new IllegalArgumentException ("ProjInvoiceRule is mandatory");
		if (ProjInvoiceRule.equals("-") || ProjInvoiceRule.equals("C") || ProjInvoiceRule.equals("c") || ProjInvoiceRule.equals("T") || ProjInvoiceRule.equals("P")); else throw new IllegalArgumentException ("ProjInvoiceRule Invalid value - " + ProjInvoiceRule + " - Reference_ID=383 - - - C - c - T - P");
		if (ProjInvoiceRule.length() > 1)
		{
			log.warning("Length > 1 - truncated");
			ProjInvoiceRule = ProjInvoiceRule.substring(0, 1);
		}
		set_Value (COLUMNNAME_ProjInvoiceRule, ProjInvoiceRule);
	}

	/** Get Invoice Rule.
		@return Invoice Rule for the project
	  */
	public String getProjInvoiceRule () 
	{
		return (String)get_Value(COLUMNNAME_ProjInvoiceRule);
	}

	/** Set Project Balance.
		@param ProjectBalanceAmt 
		Total Project Balance
	  */
	public void setProjectBalanceAmt (BigDecimal ProjectBalanceAmt)
	{
		if (ProjectBalanceAmt == null)
			throw new IllegalArgumentException ("ProjectBalanceAmt is mandatory.");
		set_ValueNoCheck (COLUMNNAME_ProjectBalanceAmt, ProjectBalanceAmt);
	}

	/** Get Project Balance.
		@return Total Project Balance
	  */
	public BigDecimal getProjectBalanceAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_ProjectBalanceAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** ProjectCategory AD_Reference_ID=288 */
	public static final int PROJECTCATEGORY_AD_Reference_ID=288;
	/** General = N */
	public static final String PROJECTCATEGORY_General = "N";
	/** Asset Project = A */
	public static final String PROJECTCATEGORY_AssetProject = "A";
	/** Work Order (Job) = W */
	public static final String PROJECTCATEGORY_WorkOrderJob = "W";
	/** Service (Charge) Project = S */
	public static final String PROJECTCATEGORY_ServiceChargeProject = "S";
	/** Set Project Category.
		@param ProjectCategory 
		Project Category
	  */
	public void setProjectCategory (String ProjectCategory)
	{

		if (ProjectCategory == null || ProjectCategory.equals("N") || ProjectCategory.equals("A") || ProjectCategory.equals("W") || ProjectCategory.equals("S")); else throw new IllegalArgumentException ("ProjectCategory Invalid value - " + ProjectCategory + " - Reference_ID=288 - N - A - W - S");
		if (ProjectCategory != null && ProjectCategory.length() > 1)
		{
			log.warning("Length > 1 - truncated");
			ProjectCategory = ProjectCategory.substring(0, 1);
		}
		set_Value (COLUMNNAME_ProjectCategory, ProjectCategory);
	}

	/** Get Project Category.
		@return Project Category
	  */
	public String getProjectCategory () 
	{
		return (String)get_Value(COLUMNNAME_ProjectCategory);
	}

	/** ProjectLineLevel AD_Reference_ID=384 */
	public static final int PROJECTLINELEVEL_AD_Reference_ID=384;
	/** Project = P */
	public static final String PROJECTLINELEVEL_Project = "P";
	/** Phase = A */
	public static final String PROJECTLINELEVEL_Phase = "A";
	/** Task = T */
	public static final String PROJECTLINELEVEL_Task = "T";
	/** Set Line Level.
		@param ProjectLineLevel 
		Project Line Level
	  */
	public void setProjectLineLevel (String ProjectLineLevel)
	{
		if (ProjectLineLevel == null) throw new IllegalArgumentException ("ProjectLineLevel is mandatory");
		if (ProjectLineLevel.equals("P") || ProjectLineLevel.equals("A") || ProjectLineLevel.equals("T")); else throw new IllegalArgumentException ("ProjectLineLevel Invalid value - " + ProjectLineLevel + " - Reference_ID=384 - P - A - T");
		if (ProjectLineLevel.length() > 1)
		{
			log.warning("Length > 1 - truncated");
			ProjectLineLevel = ProjectLineLevel.substring(0, 1);
		}
		set_Value (COLUMNNAME_ProjectLineLevel, ProjectLineLevel);
	}

	/** Get Line Level.
		@return Project Line Level
	  */
	public String getProjectLineLevel () 
	{
		return (String)get_Value(COLUMNNAME_ProjectLineLevel);
	}

	/** SalesRep_ID AD_Reference_ID=190 */
	public static final int SALESREP_ID_AD_Reference_ID=190;
	/** Set Sales Representative.
		@param SalesRep_ID 
		Sales Representative or Company Agent
	  */
	public void setSalesRep_ID (int SalesRep_ID)
	{
		if (SalesRep_ID <= 0) 
			set_Value (COLUMNNAME_SalesRep_ID, null);
		else 
			set_Value (COLUMNNAME_SalesRep_ID, Integer.valueOf(SalesRep_ID));
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

	/** Set Search Key.
		@param Value 
		Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value)
	{
		if (Value == null)
			throw new IllegalArgumentException ("Value is mandatory.");

		if (Value.length() > 40)
		{
			log.warning("Length > 40 - truncated");
			Value = Value.substring(0, 40);
		}
		set_Value (COLUMNNAME_Value, Value);
	}

	/** Get Search Key.
		@return Search key for the record in the format required - must be unique
	  */
	public String getValue () 
	{
		return (String)get_Value(COLUMNNAME_Value);
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), getValue());
    }
}