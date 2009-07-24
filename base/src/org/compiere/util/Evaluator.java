/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
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
package org.compiere.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.logging.Level;


/**
 *	Expression Evaluator	
 *	
 *  @author Jorg Janke
 *  @version $Id: Evaluator.java,v 1.3 2006/07/30 00:54:36 jjanke Exp $
 */
public class Evaluator
{
	/**	Static Logger	*/
	private static CLogger	s_log	= CLogger.getCLogger (Evaluator.class);
	
	/**
	 * 	Check if All Variables are Defined
	 *	@param source source
	 *	@param logic logic info
	 *	@return true if fully defined
	 */
	public static boolean isAllVariablesDefined (Evaluatee source, String logic)
	{
		if (logic == null || logic.length() == 0)
			return true;
		//
		int pos = 0;
		while (pos < logic.length())
		{
			int first = logic.indexOf('@', pos);
			if (first == -1)
				return true;
			int second = logic.indexOf('@', first+1);
			if (second == -1)
			{
				s_log.severe("No second @ in Logic: " + logic);
				return false;
			}
			String variable = logic.substring(first+1, second-1);
			String eval = source.get_ValueAsString (variable);
			s_log.finest(variable + "=" + eval);
			if (eval == null || eval.length() == 0)
				return false;
			//	
			pos = second + 1;
		}
		return true;
	}	//	isAllVariablesDefined
	
	/**
	 *	Evaluate Logic.
	 *  <code>
	 *	format		:= <expression> [<logic> <expression>]
	 *	expression	:= @<context>@<exLogic><value>
	 *	logic		:= <|> | <&>
	 *  exLogic		:= <=> | <!> | <^> | <<> | <>>
	 *
	 *	context		:= any global or window context
	 *	value		:= strings can be with ' or "
	 *	logic operators	:= AND or OR with the prevoius result from left to right
	 *
	 *	Example	'@AD_Table@=Test | @Language@=GERGER
	 *  </code>
	 *  @param source class implementing get_ValueAsString(variable)
	 *  @param logic logic string
	 *  @return locic result
	 */
	public static boolean evaluateLogic (Evaluatee source, String logic)
	{
		//	Conditional
		StringTokenizer st = new StringTokenizer(logic.trim(), "&|", true);
		int it = st.countTokens();
		if (((it/2) - ((it+1)/2)) == 0)		//	only uneven arguments
		{
			s_log.severe ("Logic does not comply with format "
				+ "'<expression> [<logic> <expression>]' => " + logic);
			return false;
		}

		boolean retValue = evaluateLogicTuple(source, st.nextToken());
		while (st.hasMoreTokens())
		{
			String logOp = st.nextToken().trim();
			boolean temp = evaluateLogicTuple(source, st.nextToken());
			if (logOp.equals("&"))
				retValue = retValue & temp;
			else if (logOp.equals("|"))
				retValue = retValue | temp;
			else
			{
				s_log.log(Level.SEVERE, "Logic operant '|' or '&' expected => " + logic);
				return false;
			}
		}	// hasMoreTokens
		return retValue;
	}   //  evaluateLogic

	/**
	 *	Evaluate	@context@=value or @context@!value or @context@^value.
	 *  <pre>
	 *	value: strips ' and " always (no escape or mid stream)
	 *  value: can also be a context variable
	 *  </pre>
	 *  @param source class implementing get_ValueAsString(variable)
	 *  @param logic logic tuple
	 *	@return	true or false
	 */
	private static boolean evaluateLogicTuple (Evaluatee source, String logic)
	{
		StringTokenizer st = new StringTokenizer (logic.trim(), "!=^><", true);
		if (st.countTokens() != 3)
		{
			s_log.log(Level.SEVERE, "Logic tuple does not comply with format "
				+ "'@context@=value' where operand could be one of '=!^><' => " + logic);
			return false;
		}
		//	First Part
		String first = st.nextToken().trim();					//	get '@tag@'
		String firstEval = first.trim();
		if (first.indexOf('@') != -1)		//	variable
		{
			first = first.replace ('@', ' ').trim (); 			//	strip 'tag'
			firstEval = source.get_ValueAsString (first);		//	replace with it's value
		}
		firstEval = firstEval.replace('\'', ' ').replace('"', ' ').trim();	//	strip ' and "

		//	Comperator
		String operand = st.nextToken();
		
		//	Second Part
		String second = st.nextToken();							//	get value
		String secondEval = second.trim();
		if (second.indexOf('@') != -1)		//	variable
		{
			second = second.replace('@', ' ').trim();			// strip tag
			secondEval = source.get_ValueAsString (second);		//	replace with it's value
		}
		secondEval = secondEval.replace('\'', ' ').replace('"', ' ').trim();	//	strip ' and "

		//	Handling of ID compare (null => 0)
		if (first.indexOf("_ID") != -1 && firstEval.length() == 0)
			firstEval = "0";
		if (second.indexOf("_ID") != -1 && secondEval.length() == 0)
			secondEval = "0";

		//	Logical Comparison
		boolean result = evaluateLogicTuple (firstEval, operand, secondEval);
		//
		if (CLogMgt.isLevelFinest())
			s_log.finest(logic 
				+ " => \"" + firstEval + "\" " + operand + " \"" + secondEval + "\" => " + result);
		//
		return result;
	}	//	evaluateLogicTuple
	
	/**
	 * 	Evaluate Logic Tuple
	 *	@param value1 value
	 *	@param operand operand = ~ ^ ! > <
	 *	@param value2
	 *	@return evaluation
	 */
	private static boolean evaluateLogicTuple (String value1, String operand, String value2)
	{
		if (value1 == null || operand == null || value2 == null)
			return false;
		
		BigDecimal value1bd = null;
		BigDecimal value2bd = null;
		try
		{
			if (!value1.startsWith("'"))
				value1bd = new BigDecimal (value1);
			if (!value2.startsWith("'"))
				value2bd = new BigDecimal (value2);
		}
		catch (Exception e)
		{
			value1bd = null;
			value2bd = null;
		}
		//
		if (operand.equals("="))
		{
			if (value1bd != null && value2bd != null)
				return value1bd.compareTo(value2bd) == 0;
			return value1.compareTo(value2) == 0;
		}
		else if (operand.equals("<"))
		{
			if (value1bd != null && value2bd != null)
				return value1bd.compareTo(value2bd) < 0;
			return value1.compareTo(value2) < 0;
		}
		else if (operand.equals(">"))
		{
			if (value1bd != null && value2bd != null)
				return value1bd.compareTo(value2bd) > 0;
			return value1.compareTo(value2) > 0;
		}
		else //	interpreted as not
		{
			if (value1bd != null && value2bd != null)
				return value1bd.compareTo(value2bd) != 0;
			return value1.compareTo(value2) != 0;
		}
	}	//	evaluateLogicTuple

	
	/**
	 *  Parse String and add variables with @ to the list.
	 *  @param list list to be added to
	 *  @param parseString string to parse for variables
	 */
	public static void parseDepends (ArrayList<String> list, String parseString)
	{
		if (parseString == null || parseString.length() == 0)
			return;
	//	log.fine( "MField.parseDepends", parseString);
		String s = parseString;
		//  while we have variables
		while (s.indexOf('@') != -1)
		{
			int pos = s.indexOf('@');
			s = s.substring(pos+1);
			pos = s.indexOf('@');
			if (pos == -1)
				continue;	//	error number of @@ not correct
			String variable = s.substring(0, pos);
			s = s.substring(pos+1);
		//	log.fine( variable);
			list.add(variable);
		}
	}   //  parseDepends

}	//	Evaluator
