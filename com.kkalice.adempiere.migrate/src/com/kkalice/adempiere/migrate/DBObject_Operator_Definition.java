/*
 * Name:		DBObject_Operator_Definition.java
 * Description:	operator definition
 * Created:		May 8, 2009
 * Vendor:		K.K. Alice
 * Author:		Stefan Christians
 * 
 * FileTarget:	~/development/sandbox/migrate/src/com/kkalice/adempiere/migrate/DBObject_Operator_Definition.java
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
 * operator definition
 * @author Stefan Christians
 */
public class DBObject_Operator_Definition extends DBObjectDefinition {

	/** name of function to call */
	private String m_functionName = null;
	/** the commutator of this operator */
	private String m_commutator = null;
	/** the negator of this operator */
	private String m_negator = null;
	/** the restriction selectivity estimator function for this operator */
	private String m_restrictor = null;
	/** the join selectivity estimator function for this operator */
	private String m_joiner = null;
	/** indicates this operator can support a hash join */
	private boolean m_isHashable = false;
	/** indicates this operator can support a merge join */
	private boolean m_isMergeable = false;
	
	/**
	 * constructor for operator definition
	 * @param parent
	 * @param name
	 * @param sequence
	 */
	public DBObject_Operator_Definition(DBConnection parent, String name, int sequence) {
		super(parent, name, sequence);
	}

	/**
	 * initializes the definition for this operator
	 * @param functionName name of function to call 
	 * @param commutator the commutator of this operator
	 * @param negator the negator of this operator
	 * @param restrictor the restriction selectivity estimator function for this operator
	 * @param joiner the join selectivity estimator function for this operator
	 * @param isHashable indicates this operator can support a hash join
	 * @param isMergeable indicates this operator can support a merge join
	 */
	public void initializeDefinition (String functionName, 
			String commutator, String negator, String restrictor, String joiner,
			boolean isHashable, boolean isMergeable) {

		m_functionName = functionName;
		m_commutator = commutator;
		m_negator = negator;
		m_restrictor = restrictor;
		m_joiner = joiner;
		m_isHashable = isHashable;
		m_isMergeable = isMergeable;

		s_logger.log(Level.FINEST, toString());
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBObjectDefinition#toString()
	 */
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer(m_name).append(": function=").append(m_functionName)
			.append(" com=").append(m_commutator)
			.append(" neg=").append(m_negator)
			.append(" rest=").append(m_restrictor)
			.append(" join=").append(m_joiner)
			.append(" hashable=").append(m_isHashable)
			.append(" mergeable=").append(m_isMergeable);
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
	 * @return the commutator
	 */
	public String getCommutator() {
		return m_commutator;
	}

	/**
	 * @param commutator the commutator to set
	 */
	public void setCommutator(String commutator) {
		m_commutator = commutator;
	}

	/**
	 * @return the negator
	 */
	public String getNegator() {
		return m_negator;
	}

	/**
	 * @param negator the negator to set
	 */
	public void setNegator(String negator) {
		m_negator = negator;
	}

	/**
	 * @return the restrictor
	 */
	public String getRestrictor() {
		return m_restrictor;
	}

	/**
	 * @param restrictor the restrictor to set
	 */
	public void setRestrictor(String restrictor) {
		m_restrictor = restrictor;
	}

	/**
	 * @return the joiner
	 */
	public String getJoiner() {
		return m_joiner;
	}

	/**
	 * @param joiner the joiner to set
	 */
	public void setJoiner(String joiner) {
		m_joiner = joiner;
	}

	/**
	 * @return the isHashable
	 */
	public boolean isHashable() {
		return m_isHashable;
	}

	/**
	 * @param isHashable the isHashable to set
	 */
	public void setHashable(boolean isHashable) {
		m_isHashable = isHashable;
	}

	/**
	 * @return the isMergeable
	 */
	public boolean isMergeable() {
		return m_isMergeable;
	}

	/**
	 * @param isMergeable the isMergeable to set
	 */
	public void setMergeable(boolean isMergeable) {
		m_isMergeable = isMergeable;
	}

}
