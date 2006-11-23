package org.compiere.dbPort;

import java.util.ArrayList;

public class Convert_Oracle extends Convert {

	public Convert_Oracle() {}
	
	@Override
	protected ArrayList<String> convertStatement(String sqlStatement) {
		ArrayList<String> result = new ArrayList<String>();
		result.add(sqlStatement);
		return result;
	}

	@Override
	public boolean isOracle() {
		return true;
	}

}
