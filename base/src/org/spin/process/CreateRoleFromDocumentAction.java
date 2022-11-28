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

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.adempiere.core.domains.models.I_AD_Document_Action_Access;
import org.adempiere.core.domains.models.I_AD_Ref_List;
import org.adempiere.core.domains.models.X_AD_Document_Action_Access;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MRefList;
import org.compiere.model.Query;
import org.compiere.process.DocAction;

/** 
 * 	Generated Process for (Create from Document Actions)
 *  @author Yamel Senih, ysenih@erpya.com, ERPCyA http://www.erpya.com
 *  @version Release 3.9.3
 */
public class CreateRoleFromDocumentAction extends CreateRoleFromDocumentActionAbstract {
	@Override
	protected void prepare() {
		super.prepare();
		if(getRecord_ID() == 0) {
			throw new AdempiereException("@AD_Role_ID@ @NotFound@");
		}
	}

	@Override
    protected String doIt() throws Exception {
        AtomicInteger documentActionsProcessed = new AtomicInteger(0);
        List<MRefList> actionsList = getActions();
        getSelectionKeys().stream()
                .forEach(documentTypeId -> {
                	//	Process Document Action
                	deletePreviousDocumentActions(documentTypeId);
                	actionsList.forEach(action -> {
                		if(getSelectionAsBoolean(documentTypeId, "IsSelected_" + action.getAD_Ref_List_ID())) {
                			processDocumentActionAccess(documentTypeId, action.getAD_Ref_List_ID(), documentActionsProcessed);
                		}
                	});
                });
        return "@DocAction@ @Processed@ " + documentActionsProcessed.get();
    }

    /**
     * Process Document actions
     * @param documentTypeId
     * @param actionsProcessed
     */
    private void processDocumentActionAccess(int documentTypeId, int actionToAdd, AtomicInteger actionsProcessed) {
    	X_AD_Document_Action_Access access = new X_AD_Document_Action_Access(getCtx(), 0, get_TrxName());
    	access.setAD_Role_ID(getRecord_ID());
    	access.setC_DocType_ID(documentTypeId);
    	access.setAD_Ref_List_ID(actionToAdd);
    	access.saveEx();
    	actionsProcessed.getAndAdd(1);
    }
    
    /**
     * Get Actions to process
     * @return
     */
    private List<MRefList> getActions() {
    	return new Query(getCtx(), I_AD_Ref_List.Table_Name, I_AD_Ref_List.COLUMNNAME_AD_Reference_ID + " = ?", get_TrxName())
    			.setParameters(DocAction.AD_REFERENCE_ID)
    			.<MRefList>list();
    }
    
    /**
     * Delete Previous document actions
     * @param documentTypeId
     */
    private void deletePreviousDocumentActions(int documentTypeId) {
    	new Query(getCtx(), I_AD_Document_Action_Access.Table_Name, "C_DocType_ID = ? AND AD_Role_ID= ?", get_TrxName())
    			.setParameters(documentTypeId, getRecord_ID())
    			.list()
    			.forEach(documentActionAccess -> documentActionAccess.delete(true));
    }
}