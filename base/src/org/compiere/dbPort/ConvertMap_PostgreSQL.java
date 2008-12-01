/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 Adempiere, Inc. All Rights Reserved.               *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 *****************************************************************************/

package org.compiere.dbPort;

import java.util.TreeMap;

public final class ConvertMap_PostgreSQL {
	private static TreeMap<String,String>  s_pg = new TreeMap<String,String>();
	
	/**
	 *  Return Map for PostgreSQL
	 *  @return TreeMap with pattern as key and the replacement as value
	 */
	public static TreeMap getConvertMap()
	{
		if (s_pg.size() == 0)
			initConvertMap();
		return s_pg;
	}   //  getConvertMap
	
	/**
	 *  PostgreSQL Init
	 */
	static private void initConvertMap()
	{
		//      Oracle Pattern                  Replacement

		//  Data Types
		s_pg.put("\\bNUMBER\\b",                "NUMERIC");
		s_pg.put("\\bDATE\\b",                  "TIMESTAMP");
		s_pg.put("\\bVARCHAR2\\b",              "VARCHAR");
		s_pg.put("\\bNVARCHAR2\\b",             "VARCHAR");
		s_pg.put("\\bNCHAR\\b",                 "CHAR");
        //begin vpj-cd e-evolution 03/11/2005 PostgreSQL
		s_pg.put("\\bBLOB\\b",                  "BYTEA");                 //  BLOB not directly supported
		s_pg.put("\\bCLOB\\b",                  "TEXT");                //  CLOB not directly supported
		s_pg.put("\\bLIMIT\\b","\"limit\""); 
		s_pg.put("\\bACTION\\b","\"action\""); 
		s_pg.put("\\bold\\b","\"old\""); 
		s_pg.put("\\bnew\\b","\"new\""); 
		//s_pg.put("\\bBLOB\\b",                  "OID");                 //  BLOB not directly supported
		//s_pg.put("\\bCLOB\\b",                  "OID");                //  CLOB not directly supported
        //end vpj-cd e-evolution 03/11/2005 PostgreSQL
		
		//  Storage
		s_pg.put("\\bCACHE\\b",                 "");
		s_pg.put("\\bUSING INDEX\\b",           "");
		s_pg.put("\\bTABLESPACE\\s\\w+\\b",     "");
		s_pg.put("\\bSTORAGE\\([\\w\\s]+\\)",   "");
		//
		s_pg.put("\\bBITMAP INDEX\\b",          "INDEX");

		//  Functions
		s_pg.put("\\bSYSDATE\\b",               "CURRENT_TIMESTAMP");   //  alternative: NOW()
        //begin vpj-cd e-evolution 03/11/2005 PostgreSQL		                                     
		s_pg.put("\\bDUMP\\b",               "MD5"); 	
		s_pg.put("END CASE",               "END");
		s_pg.put("\\bgetDate\\b\\(\\)",               "CURRENT_TIMESTAMP");   //  alternative: NOW()
		//end vpj-cd e-evolution 03/11/2005 PostgreSQL
		s_pg.put("\\bNVL\\b",                   "COALESCE");
		s_pg.put("\\bTO_DATE\\b",               "TO_TIMESTAMP");
		//
		s_pg.put("\\bDBMS_OUTPUT.PUT_LINE\\b",  "RAISE NOTICE");
		s_pg.put("\\bTO_NCHAR\\b",              "");

		//  Temporary
		s_pg.put("\\bGLOBAL TEMPORARY\\b",      "TEMPORARY");
		s_pg.put("\\bON COMMIT DELETE ROWS\\b", "");
		s_pg.put("\\bON COMMIT PRESERVE ROWS\\b",   "");

        //DDL
                
        // begin vpj-cd e-evolution 08/02/2005 PostgreSQL
		//s_pg.put("\\bMODIFY\\b","ALTER COLUMN");						
        //s_pg.put("\\bDEFAULT\\b","SET DEFAULT");
		// end vpj-cd e-evolution 08/02/2005 PostgreSQL
                
		//  DROP TABLE x CASCADE CONSTRAINTS
		s_pg.put("\\bCASCADE CONSTRAINTS\\b",   "");

		//  Select
		s_pg.put("\\sFROM\\s+DUAL\\b",          "");

		//  Statements
		s_pg.put("\\bELSIF\\b",                 "ELSE IF");
		// begin vpj-cd e-evolution 03/11/2005 PostgreSQL
		s_pg.put("\\bREC \\b",                 "AS REC ");				
		// end vpj-cd e-evolution 03/11/2005 PostgreSQL

		//  Sequences
		s_pg.put("\\bSTART WITH\\b",            "START");
		s_pg.put("\\bINCREMENT BY\\b",          "INCREMENT");

	}   //  initPostgreSQL
	
}
