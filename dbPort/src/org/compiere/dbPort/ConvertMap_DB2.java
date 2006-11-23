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
public class ConvertMap_DB2
{
	
	/**
	 *  Return Map for DB/2
	 *  @return TreeMap with pattern as key and the replacement as value
	 */
	public static TreeMap getConvertMap()
	{
		if (s_db2.size() == 0)
			initConvertMap();
		return s_db2;
	}   //  getDB2Map
// begin e-evolution PostgreSQL
        
// end e-evolution PostgreSQL
        
	
	/** Tree Map for PostgreSQL     */
	private static TreeMap<String,String>  s_db2 = new TreeMap<String,String>();
        // begin e-evolution PostgreSQL
        /** Tree Map for PostgreSQL     */
	
        // end e-evolution PostgreSQL
	

	/**
	 *  DB/2 Init
	 */
	static private void initConvertMap()
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
	
}   //  ConvertMap
