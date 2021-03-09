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
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.adempiere.test.CommonGWSetup;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class IT_MBPartnerLocation extends CommonGWSetup {

    private MBPartner m_partner = null; 
    private MLocation location = null;
    private MBPartnerLocation bpl = null;
    private MBPGroup m_group = null;

    private int getC_Region_ID(String Region) {

        String sql = "select c_region_id from c_region where name = ?";
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        int C_Region_ID = -1;

        try {
            pstmt = DB.prepareStatement(sql, ResultSet.TYPE_FORWARD_ONLY,
                    ResultSet.CONCUR_READ_ONLY, trxName);
            pstmt.setString(1, Region);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                C_Region_ID = rs.getInt(1);
            }
        } catch (SQLException e) {
            fail(e.getLocalizedMessage());
        } finally {
            DB.close(rs, pstmt);
        }
        return C_Region_ID;

    }

    private int getC_Country_ID(String Country) {

        String sql = "select c_country_id from c_country where name = ?";
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        int C_Country_ID = -1;

        try {
            pstmt = DB.prepareStatement(sql, ResultSet.TYPE_FORWARD_ONLY,
                    ResultSet.CONCUR_READ_ONLY, trxName);
            pstmt.setString(1, Country);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                C_Country_ID = rs.getInt(1);
            }
        } catch (SQLException e) {
            fail(e.getLocalizedMessage());
        } finally {
            DB.close(rs, pstmt);
        }

        return C_Country_ID;

    }

    @BeforeEach
    void localSetup() {

        location = new MLocation(ctx, 0, trxName);
        location.setC_Country_ID(getC_Country_ID("United States"));
        location.setC_Region_ID(getC_Region_ID("CA"));
        location.setCity("Windsor");
        location.setAddress1("Happy Lane");
        location.setAddress2("Happy Lane 2");
        String zipcode = ("95492");
        location.setPostal(zipcode);
        location.setPostal_Add(zipcode);
        location.setAD_Org_ID(0);
        location.saveEx();

        m_group = new MBPGroup(ctx, 0, trxName);
        m_group.setName("Test Group Name"); 
        m_group.setIsConfidentialInfo(false);
        m_group.setIsDefault(false);

        m_group.setPriorityBase(MBPGroup.PRIORITYBASE_Same);
        m_group.saveEx();

        m_partner = new MBPartner(ctx, 0, trxName);
        m_partner.setValue("");
        m_partner.setName("Test Business Partner Location");
        m_partner.setName2(null);
        m_partner.setDUNS("");
        m_partner.setFirstSale(null);
        m_partner.setSO_CreditLimit(Env.ZERO);
        m_partner.setSO_CreditUsed(Env.ZERO);
        m_partner.setTotalOpenBalance(Env.ZERO);
        m_partner.setActualLifeTimeValue(Env.ZERO);
        m_partner.setPotentialLifeTimeValue(Env.ZERO);
        m_partner.setAcqusitionCost(Env.ZERO);
        m_partner.setShareOfCustomer(0);
        m_partner.setSalesVolume(0);
        m_partner.setBPGroup(m_group);
        m_partner.saveEx();

    }
    
    @Test
    void testCreatePartnerLocation() {
        
        bpl = new MBPartnerLocation(ctx, 0, trxName);
        bpl.setIsActive(true);
        bpl.setName("Test Business Partner Location");
        bpl.setC_BPartner_ID(m_partner.get_ID());
        bpl.setC_Location_ID(location.get_ID());
        bpl.saveEx();
        
        assertTrue(bpl.get_ID() > 0);

    }

}
