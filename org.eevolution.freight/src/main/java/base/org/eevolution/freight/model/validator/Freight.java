/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
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
 * Copyright (C) 2003-2016 e-Evolution,SC. All Rights Reserved.               *
 * Contributor(s): Victor Perez www.e-evolution.com                           *
 *****************************************************************************/

package org.eevolution.freight.model.validator;

import org.compiere.model.MClient;
import org.compiere.model.ModelValidationEngine;
import org.compiere.model.PO;
import org.eevolution.engine.freight.FreightEngine;
import org.eevolution.model.FreightModelValidator;

/**
 * Model Validator to Calculate Freight
 * Created by eEvolution author Victor Perez <victor.perez@e-evolution.com> 21/08/16.
 * @author Yamel Senih, ysenih@erpya.com , http://www.erpya.com
 * Freight support for package
 */
public class Freight extends FreightModelValidator {

    FreightEngine freightEngine;

    @Override
    public void initialize(ModelValidationEngine engine, MClient client) {
        super.initialize(engine, client);
    }

    @Override
    public int getAD_Client_ID() {
        return super.getAD_Client_ID();
    }

    @Override
    public String login(int orgId, int roleId, int userId) {
        return super.login(orgId, roleId, userId);
    }

    /**
     * @param po     persistent object
     * @param timing see TIMING_ constants
     * @return
     */
    @Override
    public String docValidate(PO po, int timing) {
    	return super.docValidate(po, timing);
    }

    /**
     * @param po   persistent object
     * @param type TYPE_
     * @return
     * @throws Exception
     */
    @Override
    public String modelChange(PO po, int type) throws Exception {
    	return super.modelChange(po, type);
    }
}
