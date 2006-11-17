/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                        *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
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
package org.compiere.wf;

import java.sql.*;
import java.util.*;
import java.math.*;

import org.compiere.model.*;

/**
 *	Workflow Transition Condition
 *	
 *  @author Jorg Janke
 *  @version $Id: MWFNextCondition.java,v 1.3 2006/07/30 00:51:05 jjanke Exp $
 */
public class MWFNextCondition extends X_AD_WF_NextCondition
{
	/**
	 * 	Default Constructor
	 *	@param ctx context
	 *	@param id id
	 * 	@param trxName transaction
	 */
	public MWFNextCondition (Properties ctx, int id, String trxName)
	{
		super (ctx, id, trxName);
	}	//	MWFNextCondition

	/**
	 * 	Load Cosntructor
	 *	@param ctx context
	 *	@param rs result set
	 * 	@param trxName transaction
	 */
	public MWFNextCondition (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MWFNextCondition
	
	/**	Numeric evaluation		*/
	private boolean		m_numeric = true;
	
	/**
	 * 	Is Or Condition
	 * 	@return true if OR
	 */
	public boolean isOr()
	{
		return ANDOR_Or.equals(getAndOr());
	}	//	isOr
	
	/**
	 * 	Evaluate Condition
	 * 	@param activity activity
	 *	@return true if true
	 */
	public boolean evaluate (MWFActivity activity)
	{
		if (getAD_Column_ID() == 0)
			throw new IllegalStateException("No Column defined - " + this);
			
		PO po = activity.getPO();
		if (po == null || po.get_ID() == 0)
			throw new IllegalStateException("Could not evaluate " + po + " - " + this);
		//
		Object valueObj = po.get_ValueOfColumn(getAD_Column_ID());
		if (valueObj == null)
			valueObj = "";
		String value1 = getValue();
		if (value1 == null)
			value1 = "";
		String value2 = getValue2();
		if (value2 == null)
			value2 = "";
		
		String resultStr = "PO:{" + valueObj + "} " + getOperation() + " Condition:{" + value1 + "}";
		if (getOperation().equals(OPERATION_Sql))
			throw new IllegalArgumentException("SQL Operator not implemented yet: " + resultStr);
		if (getOperation().equals(OPERATION_X))
			resultStr += "{" + value2 + "}";

		boolean result = false;
		if (valueObj instanceof Number)
			result = compareNumber ((Number)valueObj, value1, value2);
		else
			result = compareString(valueObj, value1, value2);
		//
		log.fine(resultStr + " -> " + result 
			+ (m_numeric ? " (#)" : " ($)"));
		return result;
	}	//	evaluate
	
	/**
	 * 	Compare Number
	 *	@param valueObj comparator
	 *	@param value1 first value
	 *	@param value2 second value
	 *	@return true if operation
	 */
	private boolean compareNumber (Number valueObj, String value1, String value2)
	{
		BigDecimal valueObjB = null;
		BigDecimal value1B = null;
		BigDecimal value2B = null;
		try
		{
			if (valueObj instanceof BigDecimal)
				valueObjB = (BigDecimal)valueObj;
			else if (valueObj instanceof Integer)
				valueObjB = new BigDecimal (((Integer)valueObj).intValue());
			else
				valueObjB = new BigDecimal (String.valueOf(valueObj));
		}
		catch (Exception e)
		{
			log.fine("compareNumber - valueObj=" + valueObj + " - " + e.toString());
			return compareString(valueObj, value1, value2);
		}
		try
		{
			value1B = new BigDecimal (value1);
		}
		catch (Exception e)
		{
			log.fine("compareNumber - value1=" + value1 + " - " + e.toString());
			return compareString(valueObj, value1, value2);
		}
		
		String op = getOperation();
		if (OPERATION_Eq.equals(op))
			return valueObjB.compareTo(value1B) == 0;
		else if (OPERATION_Gt.equals(op))
			return valueObjB.compareTo(value1B) > 0;
		else if (OPERATION_GtEq.equals(op))
			return valueObjB.compareTo(value1B) >= 0;
		else if (OPERATION_Le.equals(op))
			return valueObjB.compareTo(value1B) < 0;
		else if (OPERATION_LeEq.equals(op))
			return valueObjB.compareTo(value1B) <= 0;
		else if (OPERATION_Like.equals(op))
			return valueObjB.compareTo(value1B) == 0;
		else if (OPERATION_NotEq.equals(op))
			return valueObjB.compareTo(value1B) != 0;
		//
		else if (OPERATION_Sql.equals(op))
			throw new IllegalArgumentException("SQL not Implemented");
		//
		else if (OPERATION_X.equals(op))
		{
			if (valueObjB.compareTo(value1B) < 0)
				return false;
			//	To
			try
			{
				value2B = new BigDecimal (String.valueOf(value2));
				return valueObjB.compareTo(value2B) <= 0;
			}
			catch (Exception e)
			{
				log.fine("compareNumber - value2=" + value2 + " - " + e.toString());
				return false;
			}
		}
		//
		throw new IllegalArgumentException("Unknown Operation=" + op);
	}	//	compareNumber
	
	/**
	 * 	Compare String
	 *	@param valueObj comparator
	 *	@param value1S first value
	 *	@param value2S second value
	 *	@return true if operation
	 */
	private boolean compareString (Object valueObj, String value1S, String value2S)
	{
		m_numeric = false;
		String valueObjS = String.valueOf(valueObj);
		//
		String op = getOperation();
		if (OPERATION_Eq.equals(op))
			return valueObjS.compareTo(value1S) == 0;
		else if (OPERATION_Gt.equals(op))
			return valueObjS.compareTo(value1S) > 0;
		else if (OPERATION_GtEq.equals(op))
			return valueObjS.compareTo(value1S) >= 0;
		else if (OPERATION_Le.equals(op))
			return valueObjS.compareTo(value1S) < 0;
		else if (OPERATION_LeEq.equals(op))
			return valueObjS.compareTo(value1S) <= 0;
		else if (OPERATION_Like.equals(op))
			return valueObjS.compareTo(value1S) == 0;
		else if (OPERATION_NotEq.equals(op))
			return valueObjS.compareTo(value1S) != 0;
		//
		else if (OPERATION_Sql.equals(op))
			throw new IllegalArgumentException("SQL not Implemented");
		//
		else if (OPERATION_X.equals(op))
		{
			if (valueObjS.compareTo(value1S) < 0)
				return false;
			//	To
			return valueObjS.compareTo(value2S) <= 0;
		}
		//
		throw new IllegalArgumentException("Unknown Operation=" + op);
	}	//	compareString
	
	/**
	 * 	String Representation
	 *	@return info
	 */
	public String toString ()
	{
		StringBuffer sb = new StringBuffer ("MWFNextCondition[");
		sb.append(get_ID()).append(",SeqNo=").append(getSeqNo())
			.append ("]");
		return sb.toString ();
	} //	toString
	
}	//	MWFNextCondition
