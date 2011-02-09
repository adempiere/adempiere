/*******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution * Copyright (C)
 * 1999-2009 Adempiere, Inc. All Rights Reserved. * This program is free
 * software; you can redistribute it and/or modify it * under the terms version
 * 2 of the GNU General Public License as published * by the Free Software
 * Foundation. This program is distributed in the hope * that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied * warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. * See the GNU General
 * Public License for more details. * You should have received a copy of the GNU
 * General Public License along * with this program; if not, write to the Free
 * Software Foundation, Inc., * 59 Temple Place, Suite 330, Boston, MA
 * 02111-1307 USA. *
 * 
 ******************************************************************************/

package org.compiere.process;

import org.compiere.model.MMigrationStep;
import org.compiere.process.SvrProcess;

/**
 * 
 * Process to rollback a single migration step
 * 
 * @author Paul Bowden, Adaxa Pty Ltd
 *
 */
public class MigrationStepRollback extends SvrProcess {

	private MMigrationStep migrationstep;

	@Override
	protected String doIt() throws Exception {

		if ( migrationstep == null || migrationstep.is_new() )
			return "No migration step";
		else
			return migrationstep + migrationstep.rollback();
	}

	@Override
	protected void prepare() {
		
		migrationstep = new MMigrationStep(getCtx(), getRecord_ID(), get_TrxName());

	}

}
