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
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.regex.Pattern;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.ValueNamePair;


/**
 *	User Query Model
 *	
 *  @author Jorg Janke
 *  @version $Id$
 */
public class MUserQuery extends X_AD_UserQuery
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6279689761765405320L;

	public static final String FIELD_SEPARATOR = "<^>";
	public static final String SEGMENT_SEPARATOR = "<~>";

	/**
	 * 	Get all active queries of client for Tab
	 *	@param ctx context
	 *	@param AD_Tab_ID tab
	 *	@return array of queries
	 */
	public static MUserQuery[] get (Properties ctx, int AD_Tab_ID)
	{
		int AD_User_ID = Env.getAD_User_ID(ctx);
		String sql = "SELECT * FROM AD_UserQuery "
			 + "WHERE AD_Client_ID=? AND AD_Tab_ID=? AND IsActive='Y' "
			 + "AND AD_User_ID in (0, " + AD_User_ID + ") "
			 + "ORDER BY Name";
		int AD_Client_ID = Env.getAD_Client_ID (ctx);
		ArrayList<MUserQuery> list = new ArrayList<MUserQuery>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement (sql, null);
			pstmt.setInt (1, AD_Client_ID);
			pstmt.setInt (2, AD_Tab_ID);
			rs = pstmt.executeQuery();
			while (rs.next ())
				list.add(new MUserQuery (ctx, rs, null));
		}
		catch (Exception e)
		{
			s_log.log (Level.SEVERE, sql, e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		MUserQuery[] retValue = new MUserQuery[list.size()];
		list.toArray(retValue);
		return retValue;
	}	//	get
	
	/**
	 * 	Get Specific Tab Query
	 *	@param ctx context
	 *	@param AD_Tab_ID tab
	 *	@param name name
	 *	@return query or null
	 */
	public static MUserQuery get (Properties ctx, int AD_Tab_ID, String name)
	{
		int AD_User_ID = Env.getAD_User_ID(ctx);
		String sql = "SELECT * FROM AD_UserQuery "
			 + "WHERE AD_Client_ID=? AND AD_Tab_ID=? AND UPPER(Name) LIKE ? AND IsActive='Y' "
			 + "AND AD_User_ID in (0, " + AD_User_ID + ") "
			 + "ORDER BY Name";
		int AD_Client_ID = Env.getAD_Client_ID (ctx);
		if (name == null)
			name = "%";
		MUserQuery retValue = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement (sql, null);
			pstmt.setInt (1, AD_Client_ID);
			pstmt.setInt (2, AD_Tab_ID);
			pstmt.setString (3, name.toUpperCase());
			rs = pstmt.executeQuery ();
			if (rs.next ())
				retValue = new MUserQuery (ctx, rs, null);
		}
		catch (Exception e)
		{
			s_log.log (Level.SEVERE, sql, e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		return retValue;
	}	//	get

	/**	Logger	*/
	private static CLogger s_log = CLogger.getCLogger (MUserQuery.class);
	
	/**************************************************************************
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param AD_UserQuery_ID id
	 *	@param trxName trx
	 */
	public MUserQuery(Properties ctx, int AD_UserQuery_ID, String trxName)
	{
		super (ctx, AD_UserQuery_ID, trxName);
	}	//	MUserQuery

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName trx
	 */
	public MUserQuery(Properties ctx, ResultSet rs, String trxName)
	{
		super (ctx, rs, trxName);
	}	//	MUserQuery

	
	/**
	 * 
	 * @return
	 */
	public MQuery getQuery(int AD_Table_ID, int targetWindowNo, GridField[] gridFields) 
	{	
		int openBrackets = 0;
		MQuery m_query = new MQuery(AD_Table_ID);
		m_query.addRestriction(Env.parseContext(Env.getCtx(), targetWindowNo,"", false));
		//m_query.addRestriction(Env.parseContext(Env.getCtx(), m_targetWindowNo, m_whereExtended, false));
		
		String code = this.getCode();
		log.fine("Parse user query: " + code);
		String[] segments = code.split(Pattern.quote(SEGMENT_SEPARATOR),-1);

		
		for (int i = 0; i < segments.length; i++)
		{
			String columnName = null;
			String operator = null;
			Object value = null;
			Object value2 = null;
			String andor = null;
			String left_bracket = null;
			String right_bracket = null;
			String[] fields = segments[i].split(Pattern.quote(FIELD_SEPARATOR));				
			for (int j = 0; j < fields.length; j++)
			{
				// column
				if (j == 0 )  
				{
					//for (ValueNamePair vnp : columnValueNamePairs)
					//{
					//	if (vnp.getValue().equals(fields[j]))
					//	{
							columnName = fields[j];
							continue;
						//}
					//}
				}
				// operator
				else if (j == 1)
				{
					for (ValueNamePair vnp : MQuery.OPERATORS)
					{
						if (vnp.getValue().equals(fields[j]))
						{
							operator = vnp.getValue();
							break;
						}
					}
				}
				// value
				else if ( j == 2  && fields[j].length() > 0 )
				{
					GridField field = getTargetMField(gridFields, columnName);
					value = parseString(field, fields[j]);
				}
				// value 2
				else if ( j == 3 && fields[j].length() > 0 )
				{
						GridField field = getTargetMField(gridFields, columnName);
						value2 = parseString(field, fields[j]);
				}
				// and/or
				else if (j == 4 && fields[j].length() > 0 )
				{
					if ( i != 0 )
						andor = fields[j];
				}
				else if ( j == 5 && fields[j].length() > 0 )
				{
					left_bracket = fields[j];
				}
				else if ( j == 6 && fields[j].length() > 0 )
				{
					right_bracket = fields[j];
				}
			}

			//
			GridField field = getTargetMField(gridFields, columnName);
			if (field == null)
				continue;
			//boolean isProductCategoryField = isProductCategoryField(field.getAD_Column_ID());
			String ColumnSQL = field.getColumnSQL(false);
				
			String lBrackets = left_bracket;
			if ( lBrackets != null )
				openBrackets += lBrackets.length();
			String rBrackets = right_bracket;
			if ( rBrackets != null )
				openBrackets -= rBrackets.length();
				
			boolean and = true;
			if ( i > 0 )
					and = !"OR".equals(andor);
				//	Op
				Object op = operator;
				if (op == null)
					continue;
				
				if (value == null)
				{
					if ( MQuery.OPERATORS[MQuery.EQUAL_INDEX].equals(op) 
							||  MQuery.OPERATORS[MQuery.NOT_EQUAL_INDEX].equals(op) )
					{
						m_query.addRestriction(ColumnSQL, operator, null,
								columnName, null, and, openBrackets);
					}
					else
					{
					continue;
					}
				}
				else 
				{
				Object parsedValue = parseValue(field, value);
				if (parsedValue == null)
					continue;
				String infoDisplay = value.toString();
				if (field.isLookup())
					infoDisplay = field.getLookup().getDisplay(value);
				else if (field.getDisplayType() == DisplayType.YesNo)
					infoDisplay = Msg.getMsg(Env.getCtx(), infoDisplay);
				
				if (MQuery.OPERATORS[MQuery.BETWEEN_INDEX].equals(op))
				{
					if (value2 == null)
						continue;
					Object parsedValue2 = parseValue(field, value2);
					String infoDisplay_to = value2.toString();
					if (parsedValue2 == null)
						continue;
					m_query.addRangeRestriction(ColumnSQL, parsedValue, parsedValue2,
								columnName, infoDisplay, infoDisplay_to, and, openBrackets);
				}
				else
					m_query.addRestriction(ColumnSQL, operator, parsedValue,
								columnName, infoDisplay, and, openBrackets);
				
				
				}
		 }		
		return m_query;
	}

	/**
	 * 	Get Target MField
	 *  @param fields Array of GridField
	 * 	@param columnName column name
	 * 	@return GridField 
	 */
	public GridField getTargetMField (GridField[] fields, String columnName)
	{
		if (columnName == null)
			return null;
		for (int c = 0; c < fields.length; c++)
		{
			GridField field = fields[c];
			if (columnName.equals(field.getColumnName()))
				return field;
		}
		return null;
	}	//	getTargetMField

	/**
	 * 	Parse String
	 * 	@param field column
	 * 	@param in value
	 * 	@return data type corrected value
	 */
	private Object parseString(GridField field, String in)
	{
		log.log(Level.FINE, "Parse: " +field + ":" + in);
		if (in == null)
			return null;
		int dt = field.getDisplayType();
		try
		{
			//	Return Integer
			if (dt == DisplayType.Integer
				|| (DisplayType.isID(dt) && field.getColumnName().endsWith("_ID")))
			{
				int i = Integer.parseInt(in);
				return new Integer(i);
			}
			//	Return BigDecimal
			else if (DisplayType.isNumeric(dt))
			{
				return DisplayType.getNumberFormat(dt).parse(in);
			}
			//	Return Timestamp
			else if (DisplayType.isDate(dt))
			{
				long time = 0;
				try
				{
					time = DisplayType.getDateFormat_JDBC().parse(in).getTime();
					return new Timestamp(time);
				}
				catch (Exception e)
				{
					log.log(Level.SEVERE, in + "(" + in.getClass() + ")" + e);
					time = DisplayType.getDateFormat(dt).parse(in).getTime();
				}
				return new Timestamp(time);
			}
			else if (dt == DisplayType.YesNo)
				return in.equals("Y");
			else
				return in;
		}
		catch (Exception ex)
		{
			log.log(Level.SEVERE, "Object=" + in, ex);
			return null;
		}

	}	//	parseString

	/**
	 * 	Parse Value
	 * 	@param field column
	 * 	@param in value
	 * 	@return data type corrected value
	 */
	private Object parseValue (GridField field, Object in)
	{
		if (in == null)
			return null;
		int dt = field.getDisplayType();
		try
		{
			//	Return Integer
			if (dt == DisplayType.Integer
				|| (DisplayType.isID(dt) && field.getColumnName().endsWith("_ID")))
			{
				if (in instanceof Integer)
					return in;
				int i = Integer.parseInt(in.toString());
				return new Integer(i);
			}
			//	Return BigDecimal
			else if (DisplayType.isNumeric(dt))
			{
				if (in instanceof BigDecimal)
					return in;
				return DisplayType.getNumberFormat(dt).parse(in.toString());
			}
			//	Return Timestamp
			else if (DisplayType.isDate(dt))
			{
				if (in instanceof Timestamp)
					return in;
				long time = 0;
				try
				{
					time = DisplayType.getDateFormat_JDBC().parse(in.toString()).getTime();
					return new Timestamp(time);
				}
				catch (Exception e)
				{
					log.log(Level.SEVERE, in + "(" + in.getClass() + ")" + e);
					time = DisplayType.getDateFormat(dt).parse(in.toString()).getTime();
				}
				return new Timestamp(time);
			}
			//	Return Y/N for Boolean
			else if (in instanceof Boolean)
				return ((Boolean)in).booleanValue() ? "Y" : "N";
		}
		catch (Exception ex)
		{
			log.log(Level.SEVERE, "Object=" + in, ex);
			String error = ex.getLocalizedMessage();
			if (error == null || error.length() == 0)
				error = ex.toString();
			StringBuffer errMsg = new StringBuffer();
			errMsg.append(field.getColumnName()).append(" = ").append(in).append(" - ").append(error);
			//
			throw new AdempiereException(errMsg.toString());
			//return null;
		}

		return in;
	}	//	parseValue

}	//	MUserQuery
