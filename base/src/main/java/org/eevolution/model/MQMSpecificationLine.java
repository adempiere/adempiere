/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
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
 * Copyright (C) 2003-2007 e-Evolution,SC. All Rights Reserved.               *
 * Contributor(s): Victor Perez www.e-evolution.com                           *
 *****************************************************************************/
package org.eevolution.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;

/**
 *	Forcast Line Model
 *	
 *  @author Victor Perez www.e-evolution.com  
 *  @version $Id: MQMSpecificationLine.java,v 1.11 2005/05/17 05:29:52 vpj-cd Exp $
 */
public class MQMSpecificationLine extends  X_QM_SpecificationLine
{
	/**
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param M_ForecastLine_ID id
	 */
	public MQMSpecificationLine (Properties ctx, int QM_SpecificationLine_ID, String trxName)
	{
		super (ctx, QM_SpecificationLine_ID, trxName);
		if (QM_SpecificationLine_ID == 0)
		{		
		}
		
	}	//	MQMSpecification

	
	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 */
	public MQMSpecificationLine(Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MQMSpecification
	
		
	/**
	 * 	Evaluate Condition
	 *	@return true if true
	 */
	public boolean evaluate (Object valueObj,String value1)
	{
		boolean result = false;
		if (valueObj instanceof Number)
			result = compareNumber ((Number)valueObj, value1, getValue());
		else
			result = compareString(valueObj, value1, getValue());
		//
		//log.fine(resultStr + " -> " + result 
		//	+ (m_numeric ? " (#)" : " ($)"));
		return result;
	}	//	evaluate
	
	/**
	 * 	Compare Number
	 *	@param valueObj comparator
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
			value2B = new BigDecimal (value2);
		}
		catch (Exception e)
		{
			log.fine("compareNumber - value1=" + value1 + " - " + e.toString());
			return compareString(valueObj, value1, value2);
		}
		
		String op = getOperation();

		if (OPERATION_Eq.equals(op))
			return value1B.compareTo(value2B) == 0;
		else if (OPERATION_Gt.equals(op))
			return value1B.compareTo(value2B) > 0;
		else if (OPERATION_GtEq.equals(op))
			return value1B.compareTo(value2B) >= 0;
		else if (OPERATION_Le.equals(op))
			return value1B.compareTo(value2B) < 0;
		else if (OPERATION_LeEq.equals(op))
			return value1B.compareTo(value2B) <= 0;			
		else if (OPERATION_Like.equals(op))
			return value1B.compareTo(value2B) == 0;
		else if (OPERATION_NotEq.equals(op))
			return value1B.compareTo(value2B) != 0;
		//
		else if (OPERATION_Sql.equals(op))
			throw new IllegalArgumentException("SQL not Implemented");
		//
		else if (OPERATION_X.equals(op))
		{
			if (value1B.compareTo(value2B) < 0)
				return false;
			//	To
			try
			{
				value2B = new BigDecimal (String.valueOf(value2));
				return value1B.compareTo(value2B) <= 0;
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
	 *	@return true if operation
	 */
	private boolean compareString (Object valueObj, String value1S, String value2S)
	{
		//m_numeric = false;
		String valueObjS = String.valueOf(valueObj);
		//		
		String op = getOperation();
		if (OPERATION_Eq.equals(op))
			return valueObjS.compareTo(value2S) == 0;
		else if (OPERATION_Gt.equals(op))
			return valueObjS.compareTo(value2S) > 0;
		else if (OPERATION_GtEq.equals(op))
			return valueObjS.compareTo(value2S) >= 0;
		else if (OPERATION_Le.equals(op))
			return valueObjS.compareTo(value2S) < 0;
		else if (OPERATION_LeEq.equals(op))
			return valueObjS.compareTo(value2S) <= 0;
		else if (OPERATION_Like.equals(op))
			return valueObjS.compareTo(value2S) == 0;
		else if (OPERATION_NotEq.equals(op))
			return valueObjS.compareTo(value2S) != 0;
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

}	//	MForcastLine
