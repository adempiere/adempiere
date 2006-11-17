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
package org.compiere.db;

/**
 *  General Database Constants and Utilities
 *
 *  @author     Jorg Janke
 *  @version    $Id: Database.java,v 1.3 2006/07/30 00:55:13 jjanke Exp $
 */
public class Database
{
	/** Oracle ID       */
	public static String        DB_ORACLE   = "Oracle";               	
	/** IBM DB/2 ID		*/
	public static String        DB_DB2 = "DB2";
	/** Derby ID		*/
	public static String        DB_DERBY = "Derby";    
        /** Microsoft ID	*/
	public static String        DB_MSSQLServer = "SQLServer";
                /** PostgreSQL ID   */
	public static String        DB_POSTGRESQL = "PostgreSQL";
        // begin vpj-c e-evolution 11/30/2005 EDB
        /** Enterprise DB   */
	//public static String        DB_EDB = "EnterpriseDB";
        // end vpj-c e-evolution 11/30/2005 EDB
        

	/** Supported Databases     */
	public static String[]      DB_NAMES = new String[] {
		 DB_ORACLE
		,DB_DB2
	//	,DB_DERBY
	//	,DB_MSSQLServer
        // begin vpj-c e-evolution 02/08/205 PostgreSQL
		,DB_POSTGRESQL 
        //        ,DB_EDB
        // end e-evolution 02/08/2005 PostgreSQL
	};

	/** Database Classes        */
	protected static Class[]    DB_CLASSES = new Class[] {
		DB_Oracle.class
		,DB_DB2.class
	//	,DB_Derby.class
	//	,DB_MSSQLServer.class
        //begin vpj-c e-evolution 02/08/2005 PostgreSQL        
		,DB_PostgreSQL.class
        //        ,DB_EDB.class        
        //end e-evolution 02/08/205	PostgreSQL
	};

	/** Connection Timeout in seconds   */
	public static int           CONNECTION_TIMEOUT = 10;

}   //  Database
