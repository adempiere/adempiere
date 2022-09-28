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

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import org.adempiere.core.domains.models.I_AD_Process;
import org.adempiere.core.domains.models.I_AD_Process_Access;
import org.adempiere.core.domains.models.I_AD_Window_Access;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MProcess;
import org.compiere.model.MProcessAccess;
import org.compiere.model.MTab;
import org.compiere.model.MWindow;
import org.compiere.model.MWindowAccess;
import org.compiere.model.Query;

/** 
 * 	Generated Process for (Copy Window From Role)
 *  @author Yamel Senih, ysenih@erpya.com, ERPCyA http://www.erpya.com 
 *  @version Release 3.9.3
 */
public class CopyWindowFromRole extends CopyWindowFromRoleAbstract {
	@Override
	protected void prepare() {
		super.prepare();
		if(getRecord_ID() == 0) {
			throw new AdempiereException("@AD_Role_ID@ @NotFound@");
		}
	}
	
    @Override
    protected String doIt() throws Exception {
        AtomicInteger windowAdded = new AtomicInteger(0);
        AtomicInteger processAdded = new AtomicInteger(0);
        getSelectionKeys().stream()
                .filter(windowId -> windowId > 0)
                .filter(windowId -> !isExistsWindow(windowId))
                .forEach(windowId -> {
                	MWindow window = MWindow.get(getCtx(), windowId);
                    //	Add Window
                    addWindowAccess(window, windowAdded);
                    //	For Smart Browse
                    if (isDependentEntities()) {
                    	Arrays.asList(window.getTabs(false, get_TrxName()))
                    		.forEach(tab -> {
                    			//	From Process for Table
                    			getDependentProcesses(tab)
                    				.stream()
                    				.filter(process -> !isExistsProcess(process.getAD_Process_ID()))
                    				.forEach(process -> addProcessAccess(process, processAdded));
                    		});
                    	
                    }
                });

        return "@AD_Window_ID@ @Added@ " + windowAdded.get() + ", @AD_Process_ID@ @Added@ " + processAdded.get();
    }
    
    /**
     * Get a list of process that exists in source role based on process of tables or columns linked to process
     * @param tab
     * @return
     */
    private List<MProcess> getDependentProcesses(MTab tab) {
    	return new Query(getCtx(), I_AD_Process.Table_Name, "EXISTS(SELECT 1 FROM AD_Table_Process pa "
				+ "				WHERE pa.AD_Process_ID = AD_Process.AD_Process_ID "
				+ "				AND pa.AD_Table_ID = ?) "
				+ "OR EXISTS(SELECT 1 FROM AD_Field f "
				+ "					INNER JOIN AD_Column c ON(c.AD_Column_ID = f.AD_Column_ID) "
				+ "					WHERE c.AD_Process_ID = AD_Process.AD_Process_ID "
				+ "					AND f.AD_Tab_ID = ?)", get_TrxName())
			.setParameters(tab.getAD_Table_ID(), tab.getAD_Tab_ID())
			.<MProcess>list();
    }

    /**
     * Add Window Access
     *
     * @param processId
     * @param processAdded
     */
    private void addWindowAccess(MWindow window, AtomicInteger processAdded) {
        Optional.ofNullable(window).ifPresent(windowToAdd -> {
            MWindowAccess access = new MWindowAccess(windowToAdd, getRecord_ID());
            access.setIsReadWrite(isReadWrite());
            access.saveEx();
            processAdded.getAndAdd(1);
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
    private boolean isExistsWindow(int windowId) {
    	return new Query(getCtx(), I_AD_Window_Access.Table_Name, "AD_Window_ID = ? AND AD_Role_ID= ?", get_TrxName())
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