package org.compiere.util;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ExecuteResult implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1121381937893681417L;
	private int m_updateCount = 0;
	private ArrayList<ResultSet> m_resultSets = new ArrayList<ResultSet>();
	private int resultSetPointer = 0;
	private boolean firstResult = false;
	
	public int getUpdateCount() {
		if (resultSetPointer >= m_resultSets.size()) {
			int updateCount = m_updateCount;
			m_updateCount = -1;
			return updateCount;
		}
		return -1;
	}
	
	public void setUpdateCount(int updateCount) {
		m_updateCount = updateCount;
	}
	
	public void addResultSet(ResultSet rs) {
		m_resultSets.add(rs);
	}
	
	public boolean getMoreResults() {
		if (resultSetPointer >= m_resultSets.size())
			return false;
		
		//implicitly close the current resultset
		try {
			m_resultSets.get(resultSetPointer).close();
		} catch (SQLException e) {}
		resultSetPointer ++;
		return (resultSetPointer < m_resultSets.size());
	}
	
	public ResultSet getResultSet() {
		if (resultSetPointer >= m_resultSets.size())
			return null;
		
		return m_resultSets.get(resultSetPointer);
	}

	public boolean isFirstResult() {
		return firstResult;
	}

	public void setFirstResult(boolean firstResult) {
		this.firstResult = firstResult;
	}
}
