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

import org.adempiere.core.domains.models.I_AD_Form_Access;
import org.adempiere.core.domains.models.X_AD_Form_Access;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MForm;
import org.compiere.model.Query;

/** 
 * 	Generated Process for (Copy Forms From Role)
 *  @author Yamel Senih, ysenih@erpya.com, ERPCyA http://www.erpya.com
 *  @version Release 3.9.3
 */
public class CopyFormFromRole extends CopyFormFromRoleAbstract {
	@Override
	protected void prepare() {
		super.prepare();
		if(getRecord_ID() == 0) {
			throw new AdempiereException("@AD_Role_ID@ @NotFound@");
		}
	}

	@Override
    protected String doIt() throws Exception {
        AtomicInteger formAdded = new AtomicInteger(0);
        getSelectionKeys().stream()
                .filter(formId -> formId > 0)
                .filter(formId -> !isExistsform(formId))
                .forEach(formId -> {
                	MForm form = new MForm(getCtx(), formId, get_TrxName());
                    //	Add Window
                    addFormAccess(form, formAdded);
                });

        return "@AD_Form_ID@ @Added@ " + formAdded.get();
    }

    /**
     * Add Form Access
     *
     * @param form
     * @param formAdded
     */
    private void addFormAccess(MForm form, AtomicInteger formAdded) {
        Optional.ofNullable(form).ifPresent(formToAdd -> {
            X_AD_Form_Access access = new X_AD_Form_Access(getCtx(), 0, get_TrxName());
            access.setAD_Role_ID(getRecord_ID());
            access.setAD_Form_ID(formToAdd.getAD_Form_ID());
            access.setIsReadWrite(isReadWrite());
            access.saveEx();
            formAdded.getAndAdd(1);
        });
    }
    
    /**
     * Validate if exists form
     * @param formId
     * @return
     */
    private boolean isExistsform(int formId) {
    	return new Query(getCtx(), I_AD_Form_Access.Table_Name, "AD_Form_ID = ? AND AD_Role_ID= ?", get_TrxName())
    			.setParameters(formId, getRecord_ID())
    			.match();
    }
}