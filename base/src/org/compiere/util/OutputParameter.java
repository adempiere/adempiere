package org.compiere.util;

import java.io.Serializable;

public class OutputParameter implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5269261802164086098L;

	public OutputParameter(int sqlType, int scale, String typeName) {
		this.sqlType = sqlType;
		this.scale = scale;
		this.typeName = typeName;
	}
	
	private int sqlType = -1;
	private int scale = -1;
	private String typeName = null;
	
	private Serializable value = null;
	
	public Serializable getValue() {
		return value;
	}
	
	public void setValue(Serializable value) {
		this.value = value;
	}

	public int getSqlType() {
		return sqlType;
	}

	public int getScale() {
		return scale;
	}

	public String getTypeName() {
		return typeName;
	}
}
