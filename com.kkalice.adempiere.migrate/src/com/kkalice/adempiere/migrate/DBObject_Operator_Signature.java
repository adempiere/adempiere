/*
 * Name:		DBObject_Operator_Signature.java
 * Description:	operator signature
 * Created:		May 9, 2009
 * Vendor:		K.K. Alice
 * Author:		Stefan Christians
 * 
 * FileTarget:	~/development/sandbox/migrate/src/com/kkalice/adempiere/migrate/DBObject_Operator_Signature.java
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
 * operator signature
 * @author Stefan Christians
 */
public class DBObject_Operator_Signature extends DBObjectDefinition {

	/** type of left argument */
	private String m_leftArg = null;
	/** type of right argument */
	private String m_rightArg = null;
	/** type of return value */
	private String m_returnType = null;

	/**
	 * constructor for operator signature
	 * @param parent
	 * @param name
	 * @param sequence
	 */
	public DBObject_Operator_Signature(DBConnection parent, String name, int sequence) {
		super(parent, name, sequence);
	}

	/**
	 * initializes the signature for this operator
	 * @param leftArg type of left argument
	 * @param rightArg type of right argument
	 * @param returnType type of return value
	 */
	public void initializeDefinition (String leftArg, String rightArg, String returnType) { 

		m_leftArg = leftArg;
		m_rightArg = rightArg;
		m_returnType = returnType;

		s_logger.log(Level.FINEST, toString());
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBObjectDefinition#toString()
	 */
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer(m_name).append(" ").append(getSignature());
		return sb.toString();
	}

	/**
	 * @return the leftArg
	 */
	public String getLeftArg() {
		return m_leftArg;
	}

	/**
	 * @param leftArg the leftArg to set
	 */
	public void setLeftArg(String leftArg) {
		m_leftArg = leftArg;
	}

	/**
	 * @return the rightArg
	 */
	public String getRightArg() {
		return m_rightArg;
	}

	/**
	 * @param rightArg the rightArg to set
	 */
	public void setRightArg(String rightArg) {
		m_rightArg = rightArg;
	}

	/**
	 * @return the unique operator signature
	 */
	public String getSignature() {
		StringBuffer args = new StringBuffer();
		if (m_leftArg!=null)
			args.append(m_leftArg);
		if (m_rightArg!=null) {
			if (args.length()>0)
				args.append(", ");
			args.append(m_rightArg);
		}
		StringBuffer sb = new StringBuffer("(").append(args).append(")");
		return sb.toString();
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
	
}
