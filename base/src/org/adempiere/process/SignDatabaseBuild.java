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
package org.adempiere.process;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.Adempiere;
import org.compiere.model.MClient;
import org.compiere.model.MSequence;
import org.compiere.process.SvrProcess;
import org.compiere.util.CLogMgt;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Trx;

/**
 *	Sign Database Build
 *	[ 1851190 ] Running outdated client can cause data corruption
 *
 *  @author Carlos Ruiz
 */
public class SignDatabaseBuild
{
	/**	Static Logger	*/
	private static CLogger	s_log	= CLogger.getCLogger (SignDatabaseBuild.class);

	public static void main(String[] args) throws Exception 
	{
		Adempiere.startupEnvironment(false);
		CLogMgt.setLevel(Level.FINE);
		s_log.info("Sign Database Build");
		s_log.info("-------------------");
		
		PreparedStatement updateStmt = null;
		try {
			updateStmt = DB.prepareStatement(
							"UPDATE AD_System "
									+ "SET LastBuildInfo = ? "
									+ "WHERE AD_System_ID = 0",
							null);
			updateStmt.setString(1, Adempiere.getImplementationVersion());
			System.out.println(updateStmt);
			updateStmt.executeUpdate();

		} catch (Exception ex) {
			throw ex;
		} finally {
			DB.close(updateStmt);
		}

	}

}	//	SignDatabaseBuild