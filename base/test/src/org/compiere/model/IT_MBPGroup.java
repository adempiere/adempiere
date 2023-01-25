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
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("Model")
@Tag("MBPGroup")
class IT_MBPGroup extends CommonGWSetup {

    private MBPGroup m_group = null; // business partner

    @Test
    void testCreateMBPGroup() {

        m_group = new MBPGroup(ctx, 0, trxName);
        m_group.setName("Test Group Name"); 
        m_group.setIsConfidentialInfo(false);
        m_group.setIsDefault(false);
        m_group.setPriorityBase(MBPGroup.PRIORITYBASE_Same);
        m_group.saveEx();
        
        assertTrue(m_group.get_ID() > 0, "ID not set");

    }

}
