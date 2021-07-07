/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2020 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
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
package org.compiere.model;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.adempiere.test.CommonGWSetup;
import org.compiere.util.Env;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("Model")
@Tag("MUser")
class IT_MUser extends CommonGWSetup {

    private MBPartner createBPartner() {
    
        MBPGroup m_group = new MBPGroup(ctx, 0, trxName);
        m_group.setName("Test Group Name");
        m_group.setIsConfidentialInfo(false);
        m_group.setIsDefault(false);
        m_group.setPriorityBase(MBPGroup.PRIORITYBASE_Same);
        m_group.saveEx();
    
        MBPartner partner = new MBPartner(ctx, 0, trxName);
        partner.setValue("");
        partner.setName("Test MBPartner with contact");
        partner.setName2(null);
        partner.setDUNS("");
        partner.setFirstSale(null);
        partner.setSO_CreditLimit(Env.ZERO);
        partner.setSO_CreditUsed(Env.ZERO);
        partner.setTotalOpenBalance(Env.ZERO);
        partner.setActualLifeTimeValue(Env.ZERO);
        partner.setPotentialLifeTimeValue(Env.ZERO);
        partner.setAcqusitionCost(Env.ZERO);
        partner.setShareOfCustomer(0);
        partner.setSalesVolume(0);    
        partner.setBPGroup(m_group);
        partner.save();
    
        return partner;
    }

    @Test
    void addMUserAsContactToBPartner() {

        MBPartner partner = createBPartner();
        MUser contact = new MUser(ctx, 0, trxName);
        contact.setName("Test Contact Name");
        contact.setIsActive(true);
        contact.setC_BPartner_ID(partner.get_ID());
        contact.saveEx();
        assertTrue(contact.getAD_User_ID() > 0,
                "Contact not created as expected");

    }

}
