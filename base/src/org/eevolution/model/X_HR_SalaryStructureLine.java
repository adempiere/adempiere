/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2017 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * or (at your option) any later version.										*
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * or via info@adempiere.net or http://www.adempiere.net/license.html         *
 *****************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.eevolution.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.Env;

/** Generated Model for HR_SalaryStructureLine
 *  @author Adempiere (generated) 
 *  @version Release 3.9.1 - $Id$ */
public class X_HR_SalaryStructureLine extends PO implements I_HR_SalaryStructureLine, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20181220L;

    /** Standard Constructor */
    public X_HR_SalaryStructureLine (Properties ctx, int HR_SalaryStructureLine_ID, String trxName)
    {
      super (ctx, HR_SalaryStructureLine_ID, trxName);
      /** if (HR_SalaryStructureLine_ID == 0)
        {
			setAmount (Env.ZERO);
			setHR_Concept_ID (0);
			setHR_SalaryStructureLine_ID (0);
			setHR_SalaryStructure_ID (0);
			setPercentage (Env.ZERO);
        } */
    }

    /** Load Constructor */
    public X_HR_SalaryStructureLine (Properties ctx, ResultSet rs, String trxName)
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
      POInfo poi = POInfo.getPOInfo (ctx, Table_ID, get_TrxName());
      return poi;
    }

    public String toString()
    {
      StringBuffer sb = new StringBuffer ("X_HR_SalaryStructureLine[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Amount.
		@param Amount 
		Amount in a defined currency
	  */
	public void setAmount (BigDecimal Amount)
	{
		set_Value (COLUMNNAME_Amount, Amount);
	}

	/** Get Amount.
		@return Amount in a defined currency
	  */
	public BigDecimal getAmount () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Amount);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	public org.eevolution.model.I_HR_Concept getHR_Concept() throws RuntimeException
    {
		return (org.eevolution.model.I_HR_Concept)MTable.get(getCtx(), org.eevolution.model.I_HR_Concept.Table_Name)
			.getPO(getHR_Concept_ID(), get_TrxName());	}

	/** Set Global Payroll Concept.
		@param HR_Concept_ID 
		The Payroll Concept allows to define all the perception and deductions elements needed to define a payroll.
	  */
	public void setHR_Concept_ID (int HR_Concept_ID)
	{
		if (HR_Concept_ID < 1) 
			set_Value (COLUMNNAME_HR_Concept_ID, null);
		else 
			set_Value (COLUMNNAME_HR_Concept_ID, Integer.valueOf(HR_Concept_ID));
	}

	/** Get Global Payroll Concept.
		@return The Payroll Concept allows to define all the perception and deductions elements needed to define a payroll.
	  */
	public int getHR_Concept_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Concept_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Salary Structure Line.
		@param HR_SalaryStructureLine_ID 
		Salary Structure Line
	  */
	public void setHR_SalaryStructureLine_ID (int HR_SalaryStructureLine_ID)
	{
		if (HR_SalaryStructureLine_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HR_SalaryStructureLine_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HR_SalaryStructureLine_ID, Integer.valueOf(HR_SalaryStructureLine_ID));
	}

	/** Get Salary Structure Line.
		@return Salary Structure Line
	  */
	public int getHR_SalaryStructureLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_SalaryStructureLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.eevolution.model.I_HR_SalaryStructure getHR_SalaryStructure() throws RuntimeException
    {
		return (org.eevolution.model.I_HR_SalaryStructure)MTable.get(getCtx(), org.eevolution.model.I_HR_SalaryStructure.Table_Name)
			.getPO(getHR_SalaryStructure_ID(), get_TrxName());	}

	/** Set Salary Structure.
		@param HR_SalaryStructure_ID 
		Salary Structure of an Employee
	  */
	public void setHR_SalaryStructure_ID (int HR_SalaryStructure_ID)
	{
		if (HR_SalaryStructure_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HR_SalaryStructure_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HR_SalaryStructure_ID, Integer.valueOf(HR_SalaryStructure_ID));
	}

	/** Get Salary Structure.
		@return Salary Structure of an Employee
	  */
	public int getHR_SalaryStructure_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_SalaryStructure_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Percentage.
		@param Percentage 
		Percent of the entire amount
	  */
	public void setPercentage (BigDecimal Percentage)
	{
		set_Value (COLUMNNAME_Percentage, Percentage);
	}

	/** Get Percentage.
		@return Percent of the entire amount
	  */
	public BigDecimal getPercentage () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Percentage);
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

	/** Set Immutable Universally Unique Identifier.
		@param UUID 
		Immutable Universally Unique Identifier
	  */
	public void setUUID (String UUID)
	{
		set_Value (COLUMNNAME_UUID, UUID);
	}

	/** Get Immutable Universally Unique Identifier.
		@return Immutable Universally Unique Identifier
	  */
	public String getUUID () 
	{
		return (String)get_Value(COLUMNNAME_UUID);
	}
}