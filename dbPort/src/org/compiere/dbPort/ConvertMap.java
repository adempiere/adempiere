/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                        *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.compiere.dbPort;

import java.util.*;

/**
 *  Database Syntax Conversion Map.
 *
 *
 *  @author     Jorg Janke & Victor Perez
 *  @version    $Id: ConvertMap.java,v 1.6 2006/09/22 23:35:19 jjanke Exp $
 */
public class ConvertMap
{
	/**
	 *  Return Map for Derby
	 *  @return TreeMap with pattern as key and the replacement as value
	 */
	public static TreeMap getDerbyMap()
	{
		if (s_derby.size() == 0)
			initDerby();
		return s_derby;
	}   //  getDerbyMap

	/**
	 *  Return Map for DB/2
	 *  @return TreeMap with pattern as key and the replacement as value
	 */
	public static TreeMap getDB2Map()
	{
		if (s_db2.size() == 0)
			initDB2();
		return s_db2;
	}   //  getDB2Map
// begin e-evolution PostgreSQL
        /**
	 *  Return Map for PostgreSQL
	 *  @return TreeMap with pattern as key and the replacement as value
	 */
	public static TreeMap getPostgeSQLMap()
	{
		if (s_pg.size() == 0)
			initPostgreSQL();
		return s_pg;
	}   //  getPostgreSQLMap
// end e-evolution PostgreSQL
        
	/** Tree Map for Derby			*/
	private static TreeMap<String,String>  s_derby = new TreeMap<String,String>();
	/** Tree Map for PostgreSQL     */
	private static TreeMap<String,String>  s_db2 = new TreeMap<String,String>();
        // begin e-evolution PostgreSQL
        /** Tree Map for PostgreSQL     */
	private static TreeMap<String,String>  s_pg = new TreeMap<String,String>();
        // end e-evolution PostgreSQL
	/**
	 *  Derby Init
	 */
	static private void initDerby()
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

	/**
	 *  DB/2 Init
	 */
	static private void initDB2()
	{
		//      Oracle Pattern                  Replacement

		//  Data Types
		s_db2.put("\\bNUMBER\\b",                "NUMERIC");
		s_db2.put("\\bDATE\\b",                  "TIMESTAMP");
		s_db2.put("\\bVARCHAR2\\b",              "VARCHAR");
		s_db2.put("\\bNVARCHAR2\\b",             "VARCHAR");
		s_db2.put("\\bNCHAR\\b",                 "CHAR");
		s_db2.put("\\bBLOB\\b",                  "OID");                 //  BLOB not directly supported
		s_db2.put("\\bCLOB\\b",                  "TEXT");                //  CLOB not directly supported

		//  Storage
		s_db2.put("\\bCACHE\\b",                 "");
		s_db2.put("\\bUSING INDEX\\b",           "");
		s_db2.put("\\bTABLESPACE\\s\\w+\\b",     "");
		s_db2.put("\\bSTORAGE\\([\\w\\s]+\\)",   "");
		//
		s_db2.put("\\bBITMAP INDEX\\b",          "INDEX");

		//  Functions
		s_db2.put("\\bSYSDATE\\b",               "CURRENT_TIMESTAMP");   //  alternative: NOW()
		s_db2.put("\\bNVL\\b",                   "COALESCE");
		s_db2.put("\\bTO_DATE\\b",               "TO_TIMESTAMP");
		//
		s_db2.put("\\bDBMS_OUTPUT.PUT_LINE\\b",  "RAISE NOTICE");

		//  Temporary
		s_db2.put("\\bGLOBAL TEMPORARY\\b",      "TEMPORARY");
		s_db2.put("\\bON COMMIT DELETE ROWS\\b", "");
		s_db2.put("\\bON COMMIT PRESERVE ROWS\\b",   "");


		//  DROP TABLE x CASCADE CONSTRAINTS
		s_db2.put("\\bCASCADE CONSTRAINTS\\b",   "");

		//  Select
		s_db2.put("\\sFROM\\s+DUAL\\b",          "");

		//  Statements
		s_db2.put("\\bELSIF\\b",                 "ELSE IF");

		//  Sequences
		s_db2.put("\\bSTART WITH\\b",            "START");
		s_db2.put("\\bINCREMENT BY\\b",          "INCREMENT");

	}   //  initPostgreSQL
	
        	/**
	 *  PostgreSQL Init
	 */
	static private void initPostgreSQL()
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
		s_pg.put("\\bCLOB\\b",                  "BYTEA");                //  CLOB not directly supported
                s_pg.put("\\bLIMIT\\b","\"limit\""); 
                s_pg.put("\\bACTION\\b","\"action\""); 
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
		//Bug fix, Gunther Hoppe 08.07.2005 e-evolution
        //Begin ----------------------------------------------------------------------------------------			
		s_pg.put("\\bSysDate\\b",               "CURRENT_TIMESTAMP");
		s_pg.put("SysDate",               "CURRENT_TIMESTAMP");
		//end ----------------------------------------------------------------------------------------	
        //begin vpj-cd e-evolution 03/11/2005 PostgreSQL		                                     
		s_pg.put("\\bDUMP\\b",               "MD5"); 	
		s_pg.put("END CASE",               "END");		
		s_pg.put("\\bgetDate()\\b",               "CURRENT_TIMESTAMP");   //  alternative: NOW()
		//end vpj-cd e-evolution 03/11/2005 PostgreSQL
		s_pg.put("\\bNVL\\b",                   "COALESCE");
		s_pg.put("\\bTO_DATE\\b",               "TO_TIMESTAMP");
		//
		s_pg.put("\\bDBMS_OUTPUT.PUT_LINE\\b",  "RAISE NOTICE");

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
		//s_pg.put("\\bAND\\sROWNUM=\\b",                 "LIMIT ");
		// end vpj-cd e-evolution 03/11/2005 PostgreSQL

		//  Sequences
		s_pg.put("\\bSTART WITH\\b",            "START");
		s_pg.put("\\bINCREMENT BY\\b",          "INCREMENT");

	}   //  initPostgreSQL
	
}   //  ConvertMap
