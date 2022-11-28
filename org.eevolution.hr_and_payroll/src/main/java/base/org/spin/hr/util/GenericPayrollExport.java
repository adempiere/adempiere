/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
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
 * Copyright (C) 2003-2014 E.R.P. Consultores y Asociados, C.A.               *
 * All Rights Reserved.                                                       *
 * Contributor(s): Yamel Senih www.erpya.com                                  *
 *****************************************************************************/
package org.spin.hr.util;

import java.io.File;
import java.io.FileWriter;
import java.util.Properties;

import org.compiere.util.CLogger;
import org.compiere.util.Env;

/**
 * Generic Payroll Report Export
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<a href="https://github.com/adempiere/adempiere/issues/1539">
 * 		@see FR [ 1539 ] Add Process for Reporting</a>
 */
public class GenericPayrollExport extends AbstractPayrollReportExport {

	public GenericPayrollExport(Properties ctx) {
		super(ctx);
	}
	
	/**	Static Logger	*/
	private static CLogger	log	= CLogger.getCLogger (GenericPayrollExport.class);
	
	@Override
	public String getFileName() {
		log.fine("getFileName");
		return "TestPayrollReportExport";
	}

	@Override
	public boolean exportToFile(File file) throws Exception {
		if(file == null) {
			file = createFile();
		}
		//	Write it
		FileWriter writer = new FileWriter(file);
		writer.write("Test of File");
		writer.write(Env.NL);
		writer.write("Test of File");
		writer.flush();
		writer.close();
		return true;
	}
}
