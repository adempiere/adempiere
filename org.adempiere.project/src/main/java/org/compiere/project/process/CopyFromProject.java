/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
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
package org.compiere.project.process;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MProject;
import org.compiere.util.Util;

/**
 *  Copy Project Details
 *
 *	@author Jorg Janke
 *	@version $Id: CopyFromProject.java,v 1.2 2006/07/30 00:51:01 jjanke Exp $
 */
public class CopyFromProject extends CopyFromProjectAbstract {
	
	@Override
	protected void prepare() {
		super.prepare();
		// Valid Record Identifier
		if (getRecord_ID() <= 0 && Util.isEmptyCollection(getSelectionKeys())) {
			throw new AdempiereException("@FillMandatory@ @C_Project_ID@ (@Record_ID@)");
		}
		if (getProjectId() == 0)
			throw new IllegalArgumentException("@C_Project_ID@ @IsMandatory@");
	}
	
	/**
	 *  Perform process.
	 *  @return Message (clear text)
	 *  @throws Exception if not successful
	 */
	protected String doIt() throws Exception {
		log.info("doIt - From C_Project_ID=" + getProjectId() + " to " + getRecord_ID());
		MProject from = new MProject (getCtx(), getProjectId(), get_TrxName());
		MProject to = new MProject (getCtx(), getRecord_ID(), get_TrxName());
		//
		int no = to.copyDetailsFrom (from);
		return "@Copied@=" + no;
	}	//	doIt

}	//	CopyFromProject
