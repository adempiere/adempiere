/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2016 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * or via info@adempiere.net or http://www.adempiere.net/license.html         *
 *****************************************************************************/
package org.compiere.dbPort;

import java.util.TreeMap;

/**
 * convert Map MariaDB Database
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<li> FR [ 391 ] Add connection support to MariaDB
 *		@see https://github.com/adempiere/adempiere/issues/464
 */
public class ConvertMap_MariaDB {
    
    private static TreeMap<String, String>  s_mariaDB = new TreeMap<String,String>();
	
	/**
	 *  Return Map for PostgreSQL
	 *  @return TreeMap with pattern as key and the replacement as value
	 */
	public static TreeMap<String, String> getConvertMap()
	{
		if (s_mariaDB.size() == 0)
			initConvertMap();
		return s_mariaDB;
	}   //  getConvertMap
	/**
	 *  MariaDB Init
	 */
	static private void initConvertMap()
	{
		//      Oracle Pattern                     MariaDB Replacement

		//  Data Types
		s_mariaDB.put("\\bNUMBER[^\\(]\\b",          "DECIMAL(22, 10) ");
		s_mariaDB.put("\\bNUMBER[\\s]?\\(",          "DECIMAL(");
		s_mariaDB.put("\\bVARCHAR2\\b",              "VARCHAR");
		s_mariaDB.put("\\bNVARCHAR2\\b",             "VARCHAR");
		s_mariaDB.put("\\bNCHAR\\b",                 "CHAR");
		s_mariaDB.put("\\bCLOB\\b",                  "TEXT");     // CLOB not directly supported
		
		// Reserved words
		s_mariaDB.put("\\bLIMIT\\b",                 "`limit`");
		s_mariaDB.put("\\bSEPARATOR\\b",             "`separator`");
		s_mariaDB.put("\\bUNDO\\b",                  "`undo`");
		s_mariaDB.put("\\bLINES\\b",                 "`lines`");
		
		//  Storage
		s_mariaDB.put("\\bCACHE\\b",                 "");
		s_mariaDB.put("\\bUSING INDEX\\b",           "");
		s_mariaDB.put("\\bTABLESPACE\\s\\w+\\b",     "");
		s_mariaDB.put("\\bSTORAGE\\([\\w\\s]+\\)",   "");
		//
		s_mariaDB.put("\\bBITMAP INDEX\\b",          "INDEX");

		//  Functions
		s_mariaDB.put("\\bSYSDATE\\b",               "SysDate()");   //  alternative: NOW()
		s_mariaDB.put("\\bUSER\\b",                  "user()");
		s_mariaDB.put("\\bDUMP\\b",                  "MD5"); 	
		s_mariaDB.put("END CASE",                    "END");
		s_mariaDB.put("\\bgetDate\\b\\(\\)",         "CURRENT_TIMESTAMP");   //  alternative: NOW()
		s_mariaDB.put("\\bNVL\\b",                   "COALESCE");
		s_mariaDB.put("\\bTO_DATE\\b",               "TO_DATE"); // POSTGRES has "TO_TIMESTAMP", MariaDB has "STR_TO_DATE". Trifon: Created to_date function for MariaDB!
		//
		s_mariaDB.put("\\bDBMS_OUTPUT.PUT_LINE\\b",  ""); // There is no RAISE NOTICE
		s_mariaDB.put("\\bTO_NCHAR\\b",              "");

		//  Temporary
		s_mariaDB.put("\\bGLOBAL TEMPORARY\\b",        "TEMPORARY");
		s_mariaDB.put("\\bON COMMIT DELETE ROWS\\b",   "");
		s_mariaDB.put("\\bON COMMIT PRESERVE ROWS\\b", "");

        //DDL
                
		s_mariaDB.put("\\bCASCADE CONSTRAINTS\\b",     "");

		//  Select
		s_mariaDB.put("\\sFROM\\s+DUAL\\b",            "");

		//  Statements
		s_mariaDB.put("\\bELSIF\\b",                 "ELSEIF");
		s_mariaDB.put("\\bREC \\b",                  "AS REC ");				

		//  Sequences
		s_mariaDB.put("\\bSTART WITH\\b",            "START");
		s_mariaDB.put("\\bINCREMENT BY\\b",          "INCREMENT");
	}	
}