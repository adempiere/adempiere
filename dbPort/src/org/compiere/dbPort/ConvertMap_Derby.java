package org.compiere.dbPort;

import java.util.TreeMap;

public final class ConvertMap_Derby {
	/** Tree Map for Derby			*/
	private static TreeMap<String,String>  s_derby = new TreeMap<String,String>();
	
	/**
	 *  Return Map for Derby
	 *  @return TreeMap with pattern as key and the replacement as value
	 */
	public static TreeMap getConvertMap()
	{
		if (s_derby.size() == 0)
			initConvertMap();
		return s_derby;
	}   //  getDerbyMap

	
	/**
	 *  Derby Init
	 */
	static private void initConvertMap()
	{	//	C:\Sources\db-derby-10.1.2.1-bin\docs\html\ref\index.html
		
		//      Oracle Pattern                  Replacement

		//  Data Types
		s_derby.put("\\bNUMBER\\b",                "DECIMAL(31,6)"); //jz: changed from decimal to decimal(31,6)
		s_derby.put("\\bDATE\\b",                  "TIMESTAMP");
		s_derby.put("\\bVARCHAR2\\b",              "VARCHAR");
		s_derby.put("\\bNVARCHAR2\\b",             "VARCHAR");
		s_derby.put("\\bNCHAR\\b",                 "CHAR");

		//  Storage
		s_derby.put("\\bCACHE\\b",                 "");
		s_derby.put("\\bUSING INDEX\\b",           "");
		s_derby.put("\\bTABLESPACE\\s\\w+\\b",     "");
		s_derby.put("\\bSTORAGE\\([\\w\\s]+\\)",   "");
		//
		s_derby.put("\\bBITMAP INDEX\\b",          "INDEX");
		
		//	Select
		s_derby.put("\\bFOR UPDATE\\b",				"");
		s_derby.put("\\bTRUNC\\(",					"convert(date,");

		//  Functions
		s_derby.put("\\bSysDate\\b",               "CURRENT_TIMESTAMP");
		s_derby.put("\\bSYSDATE\\b",               "CURRENT_TIMESTAMP");
		s_derby.put("\\bNVL\\b",                   "NULLIF");
		s_derby.put("\\bCOALESCE\\b",              "NULLIF");
		 
		s_derby.put("\\bTO_DATE\\b",               "TO_TIMESTAMP");
		//
	//	s_derby.put("\\bDBMS_OUTPUT.PUT_LINE\\b",  "RAISE NOTICE");

		//  Temporary
		s_derby.put("\\bGLOBAL TEMPORARY\\b",      "TEMPORARY");
		s_derby.put("\\bON COMMIT DELETE ROWS\\b", "");
		s_derby.put("\\bON COMMIT PRESERVE ROWS\\b",   "");


		//  DROP TABLE x CASCADE CONSTRAINTS
	//	s_derby.put("\\bCASCADE CONSTRAINTS\\b",   "");

		//  Select
		s_derby.put("\\sFROM\\s+DUAL\\b",          "");

		//  Statements
		s_derby.put("\\bELSIF\\b",                 "ELSE IF");

		//  Sequences
		s_derby.put("\\bSTART WITH\\b",            "START");
		s_derby.put("\\bINCREMENT BY\\b",          "INCREMENT");

	}   //  initDerby
}
