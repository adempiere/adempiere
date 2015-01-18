/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2003-2013 e-Evolution Consultants. All Rights Reserved.      *
 * Copyright (C) 2003-2013 Victor Pérez Juárez 								  * 
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
package org.eevolution.form;

import java.lang.reflect.Method;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.model.CalloutEngine;
import org.compiere.model.GridField;
import org.compiere.util.CLogger;

/**
 * Browser Callout Engine.
 * @author eEvolution author Victor Perez<victor.perez@e-evolution.com>
 */
public class WBrowserCalloutEngine extends CalloutEngine implements WBrowserCallout {

	
	/** Logger */
	protected CLogger log = CLogger.getCLogger(getClass());
	
	private WBrowserRows m_WRow;
	private GridField m_mField;
	

	/**
	 * 
	 * @return Browser Row
	 */
	public WBrowserRows getBrowserRow() {
		return m_WRow;
	}

	/**
	 * 
	 * @return gridField
	 */
	public GridField getGridField() {
		return m_mField;
	}

	/**
	 * 	Get Method
	 *	@param methodName method name
	 *	@return method or null
	 */
	private Method getMethod (String methodName)
	{
		Method[] allMethods = getClass().getMethods();
		for (int i = 0; i < allMethods.length; i++)
		{
			if (methodName.equals(allMethods[i].getName()))
				return allMethods[i];
		}
		return null;
	}	//	getMethod

	@Override
	public String start(Properties ctx, String methodName, int WindowNo,
			WBrowserRows mRow, GridField mField, Object value, Object oldValue,
			int currentRow, int currentColumn) {
		// TODO Auto-generated method stub
		if (methodName == null || methodName.length() == 0)
			throw new IllegalArgumentException("No Method Name");

		m_WRow = mRow;
		m_mField = mField;

		//
		String retValue = "";
		StringBuffer msg = new StringBuffer(methodName).append(" - ")
				.append(mField.getColumnName()).append("=").append(value)
				.append(" (old=").append(oldValue).append(") {active=")
				.append(isCalloutActive()).append("}");
		if (!isCalloutActive())
			log.info(msg.toString());

		// Find Method
		Method method = getMethod(methodName);
		if (method == null)
			throw new IllegalArgumentException("Method not found: "
					+ methodName);
		int argLength = method.getParameterTypes().length;
		if (!(argLength == 8))
			throw new IllegalArgumentException("Method " + methodName
					+ " has invalid no of arguments: " + argLength);

		// Call Method
		try {
			Object[] args = null;
			if (argLength == 8)
				args = new Object[] { ctx, new Integer(WindowNo), mRow, mField,
						value, oldValue, currentRow,  currentColumn};
			//Carlos Parada Comment to Send Current row And column
			/*else
				args = new Object[] { ctx, new Integer(WindowNo), mRow, mField,
						value };*/
			retValue = (String) method.invoke(this, args);
		} catch (Exception e) {
			Throwable ex = e.getCause(); // InvocationTargetException
			if (ex == null)
				ex = e;
			log.log(Level.SEVERE, "start: " + methodName, ex);
			retValue = ex.getLocalizedMessage();
			if (retValue == null) {
				retValue = ex.toString();
			}
		} finally {
			m_WRow = null;
			m_mField = null;
		}
		return retValue;
	}
	
} // BrowserCalloutEngine
