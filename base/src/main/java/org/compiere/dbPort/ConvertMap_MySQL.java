 /**********************************************************************
 * This file is part of ADempiere Business Suite                       *
 * http://www.adempiere.org                                            *
 *                                                                     *
 * Copyright (C) Trifon Trifonov.                                      *
 * Copyright (C) Contributors                                          *
 *                                                                     *
 * This program is free software; you can redistribute it and/or       *
 * modify it under the terms of the GNU General Public License         *
 * as published by the Free Software Foundation; either version 2      *
 * of the License, or (at your option) any later version.              *
 *                                                                     *
 * This program is distributed in the hope that it will be useful,     *
 * but WITHOUT ANY WARRANTY; without even the implied warranty of      *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the        *
 * GNU General Public License for more details.                        *
 *                                                                     *
 * You should have received a copy of the GNU General Public License   *
 * along with this program; if not, write to the Free Software         *
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,          *
 * MA 02110-1301, USA.                                                 *
 *                                                                     *
 * Contributors:                                                       *
 * - Trifon Trifonov (trifonnt@users.sourceforge.net)                  *
 *                                                                     *
 ***********************************************************************/

package org.compiere.dbPort;

import java.util.TreeMap;


/**
 *
 * @author praneet tiwari
 * @author Trifon Trifonov
 */
public class ConvertMap_MySQL {
    
    private static TreeMap<String, String>  s_mysql = new TreeMap<String,String>();
	
	/**
	 *  Return Map for PostgreSQL
	 *  @return TreeMap with pattern as key and the replacement as value
	 */
	public static TreeMap<String, String> getConvertMap()
	{
		if (s_mysql.size() == 0)
			initConvertMap();
		return s_mysql;
	}   //  getConvertMap
	/**
	 *  MySQL Init
	 */
	static private void initConvertMap()
	{
		//      Oracle Pattern                     MySQL Replacement

		//  Data Types
		s_mysql.put("\\bNUMBER[^\\(]\\b",          "DECIMAL(22, 10) ");
		s_mysql.put("\\bNUMBER[\\s]?\\(",          "DECIMAL(");
		s_mysql.put("\\bVARCHAR2\\b",              "VARCHAR");
		s_mysql.put("\\bNVARCHAR2\\b",             "VARCHAR");
		s_mysql.put("\\bNCHAR\\b",                 "CHAR");
		s_mysql.put("\\bCLOB\\b",                  "TEXT");     // CLOB not directly supported
		
		// Reserved words
		s_mysql.put("\\bLIMIT\\b",                 "`limit`");
		s_mysql.put("\\bSEPARATOR\\b",             "`separator`");
		s_mysql.put("\\bUNDO\\b",                  "`undo`");
		s_mysql.put("\\bLINES\\b",                 "`lines`");
		//s_mysql.put("\\bACTION\\b",                "`action`");
		
		//  Storage
		s_mysql.put("\\bCACHE\\b",                 "");
		s_mysql.put("\\bUSING INDEX\\b",           "");
		s_mysql.put("\\bTABLESPACE\\s\\w+\\b",     "");
		s_mysql.put("\\bSTORAGE\\([\\w\\s]+\\)",   "");
		//
		s_mysql.put("\\bBITMAP INDEX\\b",          "INDEX");

		//  Functions
		s_mysql.put("\\bSYSDATE\\b",               "SysDate()");   //  alternative: NOW()
		s_mysql.put("\\bUSER\\b",                  "user()");
		s_mysql.put("\\bDUMP\\b",                  "MD5"); 	
		s_mysql.put("END CASE",                    "END");
		s_mysql.put("\\bgetDate\\b\\(\\)",         "CURRENT_TIMESTAMP");   //  alternative: NOW()
		s_mysql.put("\\bNVL\\b",                   "COALESCE");
		s_mysql.put("\\bTO_DATE\\b",               "TO_DATE"); // POSTGRES has "TO_TIMESTAMP", MYSQL has "STR_TO_DATE". Trifon: Created to_date function for MySQL!
		//
		s_mysql.put("\\bDBMS_OUTPUT.PUT_LINE\\b",  ""); // There is no RAISE NOTICE
		s_mysql.put("\\bTO_NCHAR\\b",              "");

		//  Temporary
		s_mysql.put("\\bGLOBAL TEMPORARY\\b",        "TEMPORARY");
		s_mysql.put("\\bON COMMIT DELETE ROWS\\b",   "");
		s_mysql.put("\\bON COMMIT PRESERVE ROWS\\b", "");

        //DDL
                
		s_mysql.put("\\bCASCADE CONSTRAINTS\\b",     "");

		//  Select
		s_mysql.put("\\sFROM\\s+DUAL\\b",            "");

		//  Statements
		s_mysql.put("\\bELSIF\\b",                 "ELSEIF");
		s_mysql.put("\\bREC \\b",                  "AS REC ");				

		//  Sequences
		s_mysql.put("\\bSTART WITH\\b",            "START");
		s_mysql.put("\\bINCREMENT BY\\b",          "INCREMENT");
	}	
}