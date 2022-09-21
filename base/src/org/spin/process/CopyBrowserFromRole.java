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

import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import org.adempiere.core.domains.models.I_AD_Browse_Access;
import org.adempiere.core.domains.models.I_AD_Process_Access;
import org.adempiere.core.domains.models.X_AD_Browse_Access;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.model.MBrowse;
import org.compiere.model.MProcess;
import org.compiere.model.MProcessAccess;
import org.compiere.model.Query;

/** 
 * 	Generated Process for (Copy Browser From Role)
 *  @author Yamel Senih, ysenih@erpya.com, ERPCyA http://www.erpya.com
 *  @version Release 3.9.3
 */
public class CopyBrowserFromRole extends CopyBrowserFromRoleAbstract {
	@Override
	protected void prepare() {
		super.prepare();
		if(getRecord_ID() == 0) {
			throw new AdempiereException("@AD_Role_ID@ @NotFound@");
		}
	}
	
    @Override
    protected String doIt() throws Exception {
        AtomicInteger browserAdded = new AtomicInteger(0);
        AtomicInteger processAdded = new AtomicInteger(0);
        getSelectionKeys().stream()
                .filter(browserId -> browserId > 0)
                .filter(browserId -> !isExistsBrowser(browserId))
                .forEach(browserId -> {
                	MBrowse browser = MBrowse.get(getCtx(), browserId);
                    //	Add Window
                    addBrowsserAccess(browser, browserAdded);
                    //	For Smart Browse
                    if (isDependentEntities()) {
                    	MProcess process = MProcess.get(getCtx(), browser.getAD_Process_ID());
                    	if(!isExistsProcess(process.getAD_Process_ID())) {
                    		addProcessAccess(process, processAdded);
                    	}
                    }
                });

        return "@AD_Browse_ID@ @Added@ " + browserAdded.get() + ", @AD_Process_ID@ @Added@ " + processAdded.get();
    }

    /**
     * Add Browser Access
     *
     * @param browser
     * @param browserAdded
     */
    private void addBrowsserAccess(MBrowse browser, AtomicInteger browserAdded) {
        Optional.ofNullable(browser).ifPresent(windowToAdd -> {
            X_AD_Browse_Access access = new X_AD_Browse_Access(getCtx(), 0, get_TrxName());
            access.setAD_Role_ID(getRecord_ID());
            access.setAD_Browse_ID(browser.getAD_Browse_ID());
            access.setIsReadWrite(isReadWrite());
            access.saveEx();
            browserAdded.getAndAdd(1);
        });
    }
    
    /**
     * Add process Access
     *
     * @param process
     * @param processAdded
     */
    private void addProcessAccess(MProcess process, AtomicInteger processAdded) {
        Optional.ofNullable(process).ifPresent(processToAdd -> {
            MProcessAccess access = new MProcessAccess(processToAdd, getRecord_ID());
            access.setIsReadWrite(isReadWrite());
            access.saveEx();
            processAdded.getAndAdd(1);
        });
    }
    
    /**
     * Validate if exists window inside process
     * @param windowId
     * @return
     */
    private boolean isExistsBrowser(int windowId) {
    	return new Query(getCtx(), I_AD_Browse_Access.Table_Name, "AD_Browse_ID = ? AND AD_Role_ID= ?", get_TrxName())
    			.setParameters(windowId, getRecord_ID())
    			.match();
    }
    
    /**
     * Validate if exist process
     * @param windowId
     * @return
     */
    private boolean isExistsProcess(int windowId) {
    	return new Query(getCtx(), I_AD_Process_Access.Table_Name, "AD_Process_ID = ? AND AD_Role_ID= ?", get_TrxName())
    			.setParameters(windowId, getRecord_ID())
    			.match();
    }
}