package org.compiere.util;

import java.util.Map;

public class CallableResult extends ExecuteResult {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3931232160704716852L;
	private Map<Integer, OutputParameter> m_ordinalOutput = null;	
	private Map<String, OutputParameter> m_namedOutput = null;	
	
	public Map<Integer, OutputParameter> getOrdinalOutput() {
		return m_ordinalOutput;
	}
	public void setOrdinalOutput(Map<Integer, OutputParameter> ordinalOutput) {
		m_ordinalOutput = ordinalOutput;
	}
	
	public Map<String, OutputParameter> getNamedOutput() {
		return m_namedOutput;
	}
	
	public void setNamedOutput(Map<String, OutputParameter> namedOutput) {
		m_namedOutput = namedOutput;
	}
		 
}
