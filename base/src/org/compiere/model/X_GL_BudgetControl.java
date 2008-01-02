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

/** Generated Model for GL_BudgetControl
 *  @author Adempiere (generated) 
 *  @version Release 3.3.1t - $Id$ */
public class X_GL_BudgetControl extends PO implements I_GL_BudgetControl, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_GL_BudgetControl (Properties ctx, int GL_BudgetControl_ID, String trxName)
    {
      super (ctx, GL_BudgetControl_ID, trxName);
      /** if (GL_BudgetControl_ID == 0)
        {
			setBudgetControlScope (null);
			setC_AcctSchema_ID (0);
			setCommitmentType (null);
// C
			setGL_BudgetControl_ID (0);
			setGL_Budget_ID (0);
			setIsBeforeApproval (false);
			setName (null);
        } */
    }

    /** Load Constructor */
    public X_GL_BudgetControl (Properties ctx, ResultSet rs, String trxName)
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
      POInfo poi = POInfo.getPOInfo (ctx, Table_ID, get_TrxName());
      return poi;
    }

    public String toString()
    {
      StringBuffer sb = new StringBuffer ("X_GL_BudgetControl[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** BudgetControlScope AD_Reference_ID=361 */
	public static final int BUDGETCONTROLSCOPE_AD_Reference_ID=361;
	/** Period only = P */
	public static final String BUDGETCONTROLSCOPE_PeriodOnly = "P";
	/** Year To Date = Y */
	public static final String BUDGETCONTROLSCOPE_YearToDate = "Y";
	/** Total = T */
	public static final String BUDGETCONTROLSCOPE_Total = "T";
	/** Set Control Scope.
		@param BudgetControlScope 
		Scope of the Budget Control
	  */
	public void setBudgetControlScope (String BudgetControlScope)
	{
		if (BudgetControlScope == null) throw new IllegalArgumentException ("BudgetControlScope is mandatory");
		if (BudgetControlScope.equals("P") || BudgetControlScope.equals("Y") || BudgetControlScope.equals("T")); else throw new IllegalArgumentException ("BudgetControlScope Invalid value - " + BudgetControlScope + " - Reference_ID=361 - P - Y - T");
		if (BudgetControlScope.length() > 1)
		{
			log.warning("Length > 1 - truncated");
			BudgetControlScope = BudgetControlScope.substring(0, 1);
		}
		set_Value (COLUMNNAME_BudgetControlScope, BudgetControlScope);
	}

	/** Get Control Scope.
		@return Scope of the Budget Control
	  */
	public String getBudgetControlScope () 
	{
		return (String)get_Value(COLUMNNAME_BudgetControlScope);
	}

	public I_C_AcctSchema getC_AcctSchema() throws Exception 
    {
        Class<?> clazz = MTable.getClass(I_C_AcctSchema.Table_Name);
        I_C_AcctSchema result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_AcctSchema)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_AcctSchema_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw e;
        }
        return result;
    }

	/** Set Accounting Schema.
		@param C_AcctSchema_ID 
		Rules for accounting
	  */
	public void setC_AcctSchema_ID (int C_AcctSchema_ID)
	{
		if (C_AcctSchema_ID < 1)
			 throw new IllegalArgumentException ("C_AcctSchema_ID is mandatory.");
		set_Value (COLUMNNAME_C_AcctSchema_ID, Integer.valueOf(C_AcctSchema_ID));
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

	/** Set Budget Control.
		@param GL_BudgetControl_ID 
		Budget Control
	  */
	public void setGL_BudgetControl_ID (int GL_BudgetControl_ID)
	{
		if (GL_BudgetControl_ID < 1)
			 throw new IllegalArgumentException ("GL_BudgetControl_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_GL_BudgetControl_ID, Integer.valueOf(GL_BudgetControl_ID));
	}

	/** Get Budget Control.
		@return Budget Control
	  */
	public int getGL_BudgetControl_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_GL_BudgetControl_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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
			 throw new IllegalArgumentException ("GL_Budget_ID is mandatory.");
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

	/** Set Comment/Help.
		@param Help 
		Comment or Hint
	  */
	public void setHelp (String Help)
	{

		if (Help != null && Help.length() > 2000)
		{
			log.warning("Length > 2000 - truncated");
			Help = Help.substring(0, 2000);
		}
		set_Value (COLUMNNAME_Help, Help);
	}

	/** Get Comment/Help.
		@return Comment or Hint
	  */
	public String getHelp () 
	{
		return (String)get_Value(COLUMNNAME_Help);
	}

	/** Set Before Approval.
		@param IsBeforeApproval 
		The Check is before the (manual) approval
	  */
	public void setIsBeforeApproval (boolean IsBeforeApproval)
	{
		set_Value (COLUMNNAME_IsBeforeApproval, Boolean.valueOf(IsBeforeApproval));
	}

	/** Get Before Approval.
		@return The Check is before the (manual) approval
	  */
	public boolean isBeforeApproval () 
	{
		Object oo = get_Value(COLUMNNAME_IsBeforeApproval);
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

		if (Name.length() > 120)
		{
			log.warning("Length > 120 - truncated");
			Name = Name.substring(0, 120);
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