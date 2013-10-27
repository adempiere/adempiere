/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2003-2011 e-Evolution Consultants. All Rights Reserved.      *
 * Copyright (C) 2003-2011 Victor Pérez Juárez 								  * 
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
 * Contributor(s): Victor Pérez Juárez  (victor.perez@e-evolution.com)		  *
 * Sponsors: e-Evolution Consultants (http://www.e-evolution.com/)            *
 *****************************************************************************/
package org.compiere.apps;

import java.awt.Cursor;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.regex.Pattern;

import javax.swing.JComponent;
import javax.swing.JPopupMenu;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.apps.search.Find;
import org.compiere.grid.GridController;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.MQuery;
import org.compiere.model.MUserQuery;
import org.compiere.print.ReportEngine;
import org.compiere.print.Viewer;
import org.compiere.util.CLogger;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Msg;
import org.compiere.util.ValueNamePair;

/**
 *	Application Search.
 *  Called from APanel; Queries Search Table.
 *	
 * @author victor.perez@e-evolution.com, www.e-evolution.com
 * 	<li>RF [ 2853359 ] Popup Menu for Lookup Record
 * 	<li>http://sourceforge.net/tracker/?func=detail&aid=2853359&group_id=176962&atid=879335
 */
public class ASearch implements ActionListener
{
	
	private static final String FIELD_SEPARATOR = "<^>";
	private static final String SEGMENT_SEPARATOR = "<~>";
	private GridField[]		m_findFields;
	
	/**
	 *	Constructor
	 *  @param invoker component to display popup (optional)
	 *  @param tableName table name
	 *  @param query query
	 */
	public ASearch (AppsAction appsAction, Frame owner, int targetWindowNo, GridController gc ,GridTab gridTab, int m_onlyCurrentDays)
	{
		m_onlycurrentdays = m_onlyCurrentDays;
		m_appsaction = appsAction;
		m_invoker =  appsAction.getButton();
		m_owner = owner;
		m_viewer = null;
		m_targetWindowNo = targetWindowNo;
		m_AD_Tab_ID = gridTab.getAD_Tab_ID();
		m_AD_Table_ID = gridTab.getAD_Table_ID();
		m_tableName = gridTab.getTableName();
		m_title = gridTab.getName();
		m_where = gridTab.getWhereExtended();
		m_gt = gridTab;
		m_gc = gc;		
		m_findFields = GridField.createFields(Env.getCtx(), m_targetWindowNo, 0, m_gt.getAD_Tab_ID());
		//	See What is there
		getSearchTargets ();
	}	//	ASearch
	
	/**
	 *	Constructor
	 *  @param invoker component to display popup (optional)
	 *  @param tableName table name
	 *  @param query query
	 */
	public ASearch (JComponent invoker,Viewer viewer,String  title ,int AD_Tab_ID,int  AD_Table_ID,String tableName,ReportEngine reportEngine, GridField[] findFields ,int m_onlyCurrentDays)
	{
		
		m_onlycurrentdays = m_onlyCurrentDays;
		m_owner = (Frame)viewer;
		m_viewer = viewer;
		m_targetWindowNo = reportEngine.getWindowNo();
		m_AD_Tab_ID = AD_Tab_ID;
		m_AD_Table_ID = AD_Table_ID;
		m_tableName = tableName;
		m_where = reportEngine.getWhereExtended();
		m_gt = null;
		m_gc = null;
		m_findFields = findFields;
		m_invoker = invoker;
		m_reportEngine = reportEngine;
		//	See What is there
		getSearchTargets ();
	}	//	AReport
	
	
	
	/**	The Popup						*/
	private JPopupMenu 	m_popup = new JPopupMenu("SearchMenu");
	/**	The Option List					*/
	private ArrayList<KeyNamePair>	m_list = new ArrayList<KeyNamePair>();
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(ASearch.class);
	
	private int m_onlycurrentdays;
	private AppsAction m_appsaction;
	private JComponent m_invoker;
	private Frame m_owner;
	private int m_targetWindowNo;
	private GridTab m_gt;
	private GridController m_gc;
	private int m_AD_Tab_ID;
	private int m_AD_Table_ID;
	private String m_tableName;
	private String m_title;
	private String m_where;
	private MQuery m_query;
	private Viewer m_viewer;
	/**	Report Engine				*/
	private ReportEngine 	m_reportEngine;
	
	/**
	 * 	Get the UserQuery for the table.
	 *  Fill the list and the popup menu
	 *  @param invoker component to display popup (optional)
	 * 	@param AD_Tab_ID Tab ID
	 *  @param AD_Table_ID Table ID
	 *  @param AD_User_ID User ID
	 */
	private void getSearchTargets ()
	{
		boolean baseLanguage = Env.isBaseLanguage(Env.getCtx(), "AD_Window"); 
		MUserQuery[] search = MUserQuery.get(Env.getCtx(), m_AD_Tab_ID);
		KeyNamePair pp = null;
		
		if(search.length == 0)
		{
			find();
			return;
		}		
		else
		{	
			pp = pp = new KeyNamePair (0, Msg.getMsg(Env.getCtx(), "Search"));
			m_list.add(pp);
			m_popup.add(pp.toString()).addActionListener(this);
		}
		
		for (MUserQuery query: search)
		{
			pp = new KeyNamePair (query.getAD_UserQuery_ID(), query.getName());
			m_list.add(pp);
			m_popup.add(pp.toString()).addActionListener(this);
		}

		if (m_invoker.isShowing() && m_list.size() > 1)
		{			
			m_popup.show(m_invoker, 0, m_invoker.getHeight());
		}	
		else
		{
			launchSearch(pp);
		}
	}	//	getZoomTargets

	
	/**
	 * 	Action Listener
	 * 	@param e event
	 */
	public void actionPerformed(ActionEvent e)
	{
		m_popup.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		String cmd = e.getActionCommand();
		for (int i = 0; i < m_list.size(); i++)
		{
			KeyNamePair pp = (KeyNamePair)m_list.get(i);
			if (cmd.equals(pp.getName()))
			{
				launchSearch (pp);
				return;
			}
		}		
	}	//	actionPerformed
	
	/**
	 * 	Launch Search
	 *	@param pp KeyPair
	 */
	private void launchSearch (KeyNamePair pp)
	{
		if(pp.getName().equals(Msg.getMsg(Env.getCtx(), "Search")))
		{
			find();
		}
		else
		{	
			filter(pp.getKey());
		}	
	}	
	
	/**
	 * 
	 * Open Find window
	 */
	private void find ()
	{
		GridField[] findFields = GridField.createFields(Env.getCtx(), m_targetWindowNo, 0, m_AD_Tab_ID);
		Find find = new Find (Env.getFrame(m_owner), m_targetWindowNo, m_title,
				m_AD_Tab_ID, m_AD_Table_ID, m_tableName, 
				m_where, findFields, 1);
		
		m_query = find.getQuery();
		find.dispose();
		find = null;

		if(m_gt != null && m_gc!= null)
		{
			//	Confirmed query
			if (m_query != null)
			{
				//m_onlyCurrentRows = false;      	//  search history too
				m_gt.setQuery(m_query);
				m_gc.query(false, m_onlycurrentdays, 0);   //  autoSize
			}
			m_appsaction.setPressed(m_gt.isQueryActive());	
		}
		if(m_reportEngine != null)
		{
			m_reportEngine.setQuery(m_query);
			m_viewer.revalidate();
		}
	}
	
	/**
	 * Set Query for this window based on User Query
	 * @param AD_UserQuery_ID
	 */
	private void filter(int AD_UserQuery_ID)
	{
		MUserQuery userQuery = null;
		if(AD_UserQuery_ID  > 0)
		{
			userQuery = new MUserQuery(Env.getCtx(),AD_UserQuery_ID, null);
			m_query = getQuery(userQuery);
			
			//			Confirmed query
			if(m_gt != null && m_gc!= null)
			{
				if (m_query != null)
				{
					//m_onlyCurrentRows = false;      	//  search history too
					m_gt.setQuery(m_query);
					m_gc.query(false, m_onlycurrentdays, 0);   //  autoSize
				}
				m_appsaction.setPressed(m_gt.isQueryActive());	
			}	
			if(m_reportEngine != null && m_viewer != null )
			{
				m_reportEngine.setQuery(m_query);
				m_viewer.revalidate();
				//m_viewer.
			}
		}		
	}
	
	private MQuery getQuery(MUserQuery userQuery) 
	{	
		int openBrackets = 0;
		m_query = new MQuery(userQuery.getAD_Table_ID());
		m_query.addRestriction(Env.parseContext(Env.getCtx(), m_targetWindowNo,"", false));
		//m_query.addRestriction(Env.parseContext(Env.getCtx(), m_targetWindowNo, m_whereExtended, false));
		
		String code = userQuery.getCode();
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
							continue;
						}
					}
				}
				// value
				else if ( j == 2  && fields[j].length() > 0 )
				{
					GridField field = getTargetMField(columnName);
					value = parseString(field, fields[j]);
				}
				// value 2
				else if ( j == 3 && fields[j].length() > 0 )
				{
						GridField field = getTargetMField(columnName);
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
			GridField field = getTargetMField(columnName);
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
	 * get Query
	 * @return MQuery
	 */
	public MQuery getQuery()
	{
		return m_query;
	}
	
	/**
	 * 	Get Target MField
	 * 	@param columnName column name
	 * 	@return MField
	 */
	public GridField getTargetMField (String columnName)
	{
		if (columnName == null)
			return null;
		for (int c = 0; c < m_findFields.length; c++)
		{
			GridField field = m_findFields[c];
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
				return Boolean.valueOf(in);
			else
				return in;
		}
		catch (Exception ex)
		{
			log.log(Level.SEVERE, "Object=" + in, ex);
			return null;
		}

	}	//	parseValue
	
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
}	//	ASearch
