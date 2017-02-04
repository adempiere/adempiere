/*
 * Name:		DBObject_Sequence_Counter.java
 * Description:	counter for sequences
 * Created:		Feb 11, 2009
 * Vendor:		K.K. Alice
 * Author:		Stefan Christians
 * 
 * FileTarget:	~/development/sandbox/migrate/src/com/kkalice/adempiere/migrate/DBObject_Sequence_Counter.java
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
 * counter for sequences
 * @author Stefan Christians
 */
public class DBObject_Sequence_Counter extends DBObjectDefinition {

	/** the mimunum value this counter can have */
	private long m_minimum = 0;
	/** the maximum value this counter can have */
	private long m_maximum = 0;
	/** incremental steps for counting */
	private long m_increment = 0;
	/** whether or not this counter is cycled when it reaches the maximum value */
	private boolean m_isCycled = false;
	/** how many values are cached */
	private long m_cached = 0;
	/** the last (uncached) value used */
	private long m_current = 0;

	/**
	 * constructor for counter
	 * @param parent the calling connection
	 * @param name the name of this definition
	 * @param sequence the ordinal sequence of this definition
	 */
	public DBObject_Sequence_Counter(DBConnection parent, String name, int sequence) {
		super(parent, name, sequence);
	}

	/**
	 * initialize this counter
	 * @param minValue the mimunum value this counter can have
	 * @param maxValue the maximum value this counter can have
	 * @param increment incremental steps for counting
	 * @param isCycled whether or not this counter is cycled when it reaches the maximum value
	 * @param cacheSize how many values are cached
	 * @param lastValue the last (uncached) value used
	 */
	public void initializeDefinition (String minValue, String maxValue, String increment, boolean isCycled, String cacheSize, String lastValue) {

		m_minimum = Long.parseLong(minValue);
		m_maximum = Long.parseLong(maxValue);
		m_increment = Long.parseLong(increment);
		m_isCycled = isCycled;
		m_cached = Long.parseLong(cacheSize);
		m_current = Long.parseLong(lastValue);

		s_logger.log(Level.FINEST, toString());
	}
	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBObjectDefinition#toString()
	 */
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer(m_name).append(": ")
			.append(m_minimum).append(" - ").append(m_maximum)
			.append(" incr:").append(m_increment)
			.append(" cycled:").append(m_isCycled)
			.append(" cached:").append(m_cached)
			.append(" current:").append(m_current);
		return sb.toString();
	}

	/**
	 * @return the cached
	 */
	public long getCached() {
		return m_cached;
	}

	/**
	 * @param cached the cached to set
	 */
	public void setCached(long cached) {
		m_cached = cached;
	}

	/**
	 * @return the current
	 */
	public long getCurrent() {
		return m_current;
	}

	/**
	 * @param current the current to set
	 */
	public void setCurrent(long current) {
		m_current = current;
	}

	/**
	 * @return the increment
	 */
	public long getIncrement() {
		return m_increment;
	}

	/**
	 * @param increment the increment to set
	 */
	public void setIncrement(long increment) {
		m_increment = increment;
	}

	/**
	 * @return the isCycled
	 */
	public boolean isCycled() {
		return m_isCycled;
	}

	/**
	 * @param isCycled the isCycled to set
	 */
	public void setCycled(boolean isCycled) {
		m_isCycled = isCycled;
	}

	/**
	 * @return the maximum
	 */
	public long getMaximum() {
		return m_maximum;
	}

	/**
	 * @param maximum the maximum to set
	 */
	public void setMaximum(long maximum) {
		m_maximum = maximum;
	}

	/**
	 * @return the minimum
	 */
	public long getMinimum() {
		return m_minimum;
	}

	/**
	 * @param minimum the minimum to set
	 */
	public void setMinimum(long minimum) {
		m_minimum = minimum;
	}

	
}
