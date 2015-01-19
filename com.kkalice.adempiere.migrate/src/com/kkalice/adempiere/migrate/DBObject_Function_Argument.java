/*
 * Name:		DBObject_Function_Argument.java
 * Description:	arguments for a function
 * Created:		Feb 7, 2009
 * Vendor:		K.K. Alice
 * Author:		Stefan Christians
 * 
 * FileTarget:	~/development/sandbox/migrate/src/com/kkalice/adempiere/migrate/DBObject_Function_Argument.java
 * FileOwner:	spc.dvp
 * FilePerms:	0644
 *
 */

/**
 * This file is part of Adempiere ERP Bazaar
 * http://www.adempiere.org
 * 
 * Copyright (C) Stefan Christians
 * Copyright (C) Contributors
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301, USA.
 * 
 * Contributors:
 * - Stefan Christians
 * 
 * Sponsors:
 * - K.K. Alice
 * 
 */

package com.kkalice.adempiere.migrate;

import java.util.logging.Level;

/**
 * arguments for a function
 * @author Stefan Christians
 */
public class DBObject_Function_Argument extends DBObjectDefinition {

	/** function name */
	private String m_functionName = null;
	/** function language */
	private String m_functionLanguage = null;
	/** function type */
	private String m_functionType = null;
	/** return type */
	private String m_returnType = null;
	/** argument direction */
	private String m_argumentDirection = null;
	/** argument type */
	private String m_argumentType = null;
	
	
	/**
	 * constructor for function arguments
	 * @param parent the calling connection
	 * @param name the name of this definition
	 * @param sequence the ordinal sequence of this definition
	 */
	public DBObject_Function_Argument(DBConnection parent, String name, int sequence) {
		super(parent, name, sequence);
	}

	/**
	 * initializes the arguments of this function
	 * @param functionName
	 * @param functionLanguage
	 * @param functionType
	 * @param returnType
	 * @param argumentDirection
	 * @param argumentType
	 */
	public void initializeDefinition (String functionName, String functionLanguage, String functionType, String returnType, String argumentDirection, String argumentType) {

		setFunctionName(functionName);
		setFunctionLanguage(functionLanguage);
		setFunctionType(functionType);
		setReturnType(returnType);
		setArgumentDirection(argumentDirection);
		setArgumentType(argumentType);
		
		s_logger.log(Level.FINEST, toString());
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBObjectDefinition#toString()
	 */
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer(m_functionLanguage).append(" ")
			.append(m_functionType).append(" ")
			.append(m_returnType).append(" ")
			.append(m_functionName).append(" [")
			.append(m_sequence).append("] (")
			.append(m_argumentDirection).append(" ")
			.append(m_argumentType).append(" ")
			.append(m_name).append(")");
		return sb.toString();
	}

	/**
	 * @return the functionName
	 */
	public String getFunctionName() {
		return m_functionName;
	}

	/**
	 * @param functionName the functionName to set
	 */
	public void setFunctionName(String functionName) {
		m_functionName = functionName;
	}

	/**
	 * @return the functionLanguage
	 */
	public String getFunctionLanguage() {
		return m_functionLanguage;
	}

	/**
	 * @param functionLanguage the functionLanguage to set
	 */
	public void setFunctionLanguage(String functionLanguage) {
		m_functionLanguage = functionLanguage;
	}

	/**
	 * @return the functionType
	 */
	public String getFunctionType() {
		return m_functionType;
	}

	/**
	 * @param functionType the functionType to set
	 */
	public void setFunctionType(String functionType) {
		m_functionType = functionType;
	}

	/**
	 * @return the returnType
	 */
	public String getReturnType() {
		return m_returnType;
	}

	/**
	 * @param returnType the returnType to set
	 */
	public void setReturnType(String returnType) {
		m_returnType = returnType;
	}

	/**
	 * @return the argumentDirection
	 */
	public String getArgumentDirection() {
		return m_argumentDirection;
	}

	/**
	 * @param argumentDirection the argumentDirection to set
	 */
	public void setArgumentDirection(String argumentDirection) {
		m_argumentDirection = argumentDirection;
	}

	/**
	 * @return the argumentType
	 */
	public String getArgumentType() {
		return m_argumentType;
	}

	/**
	 * @param argumentType the argumentType to set
	 */
	public void setArgumentType(String argumentType) {
		m_argumentType = argumentType;
	}


	
}
