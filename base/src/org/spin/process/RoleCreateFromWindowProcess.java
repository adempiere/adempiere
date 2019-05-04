/**
 * Copyright (C) 2012-2018, E.R.P. Consultores y Asociados, S.A, http://www.erpya.com
 * This program is free software, you can redistribute it and/or modify it
 * under the terms version 2 of the GNU General Public License as published
 * or (at your option) any later version.
 * by the Free Software Foundation. This program is distributed in the hope
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along
 * with this program, if not, write to the Free Software Foundation, Inc.,
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.
 * For the text or an alternative of this public license, you may reach us
 * or via info@adempiere.net or http://www.adempiere.net/license.html
 * Author Yamel Senih, ysenih@erpya.com, http://www.erpya.com
 */

package org.spin.process;

import org.adempiere.model.MBrowse;
import org.compiere.model.MProcess;
import org.compiere.model.MProcessAccess;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Generated Process for (Create Process Access from Windows)
 *
 * @author author Yamel Senih, Email: ysenih@erpya.com , http://www.erpya.com, http://github.com/yamelsenih
 * @author Víctor Pérez Juárez Email: victor.perez@e-evolution.com, http://www.e-evolution.com , http://github.com/e-Evolution
 */
public class RoleCreateFromWindowProcess extends RoleCreateFromWindowProcessAbstract {

    @Override
    protected String doIt() throws Exception {
        AtomicInteger processAdded = new AtomicInteger(0);
        getSelectionKeys().stream()
                .filter(processId -> processId > 0)
                .forEach(processId -> {
                    MProcess process = MProcess.get(getCtx(), processId);
                    //	Add to process
                    addProcessAccess(processId, processAdded);
                    //	For Smart Browse
                    if (isDependentEntities()) {
                        if (process.getAD_Browse_ID() > 0) {
                            MBrowse browse = MBrowse.get(getCtx(), process.getAD_Browse_ID());
                            if (browse.getAD_Process_ID() > 0) {
                                addProcessAccess(browse.getAD_Process_ID(), processAdded);
                            }
                        }
                    }
                });

        return "@AD_Process_ID@ @Added@ " + processAdded.get();
    }

    /**
     * Add process Access
     *
     * @param processId
     * @param processAdded
     */
    private void addProcessAccess(Integer processId, AtomicInteger processAdded) {
        Optional.ofNullable(MProcess.get(getCtx(), processId)).ifPresent(process -> {
            MProcessAccess access = new MProcessAccess(process, getRecord_ID());
            access.setIsReadWrite(isReadWrite());
            access.saveEx();
            processAdded.getAndAdd(1);
        });
    }
}