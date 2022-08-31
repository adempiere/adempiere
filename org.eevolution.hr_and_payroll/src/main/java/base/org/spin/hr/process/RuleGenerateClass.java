/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 or later of the                                  *
 * GNU General Public License as published                                    *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * Copyright (C) 2003-2019 E.R.P. Consultores y Asociados, C.A.               *
 * All Rights Reserved.                                                       *
 * Contributor(s): Yamel Senih www.erpya.com                                  *
 *****************************************************************************/
package org.spin.hr.process;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MRule;
import org.compiere.model.Query;
import org.spin.hr.util.RuleClassGenerator;

/**
 * 	Generate Rule Classes for rule engine
 *	@author Yamel Senih, ysenih@erpya.com, ERPCyA http://www.erpya.com
 */
public class RuleGenerateClass extends RuleGenerateClassAbstract {
	
	@Override
	protected void prepare() {
		super.prepare();
		//	Validate parameters
		if(getDirectory() == null
				|| getDirectory().length() == 0) {
			throw new AdempiereException("@File_Directory@ @NotFound@");
		}
	}

	@Override
	protected String doIt() throws Exception {
		//	Call generator for parent
		StringBuffer files = new StringBuffer();
		new Query(getCtx(), MRule.Table_Name, null, get_TrxName()).setOnlyActiveRecords(true)
			.setClient_ID()
			.setOrderBy(MRule.COLUMNNAME_Value)
			.<MRule>list()
			.forEach(rule -> {
				RuleClassGenerator parentGenerator = new RuleClassGenerator(rule, getDirectory());
				String msg = parentGenerator.createFile();
				files.append(msg).append(", ");
				//	Set as generated
				rule.setIsRuleClassGenerated(true);
				rule.saveEx();
		});
		//	Return
		return files.toString();
	}
}
