/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
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
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.compiere.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.adempiere.test.CommonGWSetup;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("IntegrationTest")
class IT_MRole extends CommonGWSetup
{

	private MRole m_role = null;
	
	@BeforeEach
	public void localSetUp()
	{
		m_role = MRole.getDefault(ctx, false);
	}

	@Test
	void testAddAccessSQL()
	{
		String sql = m_role.addAccessSQL(
			"SELECT r.a,r.b,r.c FROM AD_Role r WHERE EXISTS "
			+ "(SELECT AD_Column c WHERE c.a=c.b) ORDER BY r.a",
			"r", 
			MRole.SQL_FULLYQUALIFIED, MRole.SQL_RO);
		assertEquals("SELECT r.a,r.b,r.c FROM AD_Role r WHERE EXISTS "
				+ "(SELECT AD_Column c WHERE c.a=c.b) "
				+ "AND r.AD_Client_ID=0 AND r.AD_Org_ID=0 "
				+ "AND (r.AD_Role_ID IS NULL OR r.AD_Role_ID NOT IN "
				+ "( SELECT Record_ID FROM AD_Private_Access WHERE AD_Table_ID = 156 "
				+ "AND AD_User_ID <> 100 AND IsActive = 'Y' )) ORDER BY r.a",
				sql, "Role access not added as expected");
	}

}	//	MRoleTest
