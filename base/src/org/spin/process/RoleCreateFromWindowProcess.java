/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2017 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * or (at your option) any later version.										*
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

package org.spin.process;

import org.adempiere.model.MBrowse;
import org.compiere.model.MProcess;
import org.compiere.model.MProcessAccess;

/** Generated Process for (Create Process Access from Windows)
 *  @author ADempiere (generated) 
 *  @version Release 3.9.1
 */
public class RoleCreateFromWindowProcess extends RoleCreateFromWindowProcessAbstract {
	
	/**	Process Added to Access	*/
	private int processAdded = 0;
	
	@Override
	protected String doIt() throws Exception {
		for(int processId : getSelectionKeys()) {
			MProcess process = MProcess.get(getCtx(), processId);
			if(process == null
					|| process.getAD_Process_ID() <= 0) {
				continue;
			}
			//	Add to process
			addProcessAccess(processId);
			//	For Smart Browse
			if(isDependentEntities()) {
				if(process.getAD_Browse_ID() > 0) {
					MBrowse browse = MBrowse.get(getCtx(), process.getAD_Browse_ID());
					if(browse.getAD_Process_ID() > 0) {
						addProcessAccess(browse.getAD_Process_ID());
					}
				}
			}
		}
		return "@AD_Process_ID@ @Added@ " + processAdded;
	}
	
	/**
	 * Add process Access
	 * @param processId
	 */
	private void addProcessAccess(int processId) {
		MProcess process = MProcess.get(getCtx(), processId);
		if(process == null
				|| process.getAD_Process_ID() <= 0) {
			return;
		}
		MProcessAccess access = new MProcessAccess(process, getRecord_ID());
		access.setIsReadWrite(isReadWrite());
		access.saveEx();
		//	Add
		processAdded++;
	}
}