/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2017 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * or (at your option) any later version.									  *
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

package org.spin.process.rpl.imp;

import org.adempiere.process.rpl.ITestImportProcessor;
import org.compiere.model.MIMPProcessor;
import org.compiere.model.MIMPProcessorType;

/** 
 * 	Generated Process for (Test Import Processor)
 *  @author Yamel Senih, ysenih@erpya.com , http://www.erpya.com 
 *  @version Release 3.9.3
 */
public class TestImportProcessor extends TestImportProcessorAbstract {

	@Override
	protected String doIt() throws Exception {
		MIMPProcessor importProcessor = MIMPProcessor.get(getCtx(), getProcessorId(), get_TrxName());
		MIMPProcessorType processorType = (MIMPProcessorType) importProcessor.getIMP_Processor_Type();
		ITestImportProcessor testProcessor = processorType.getProcessorTestInstance();
		if(testProcessor != null) {
			testProcessor.test(getCtx(), importProcessor, get_TrxName());
		}
		return "OK";
	}
}