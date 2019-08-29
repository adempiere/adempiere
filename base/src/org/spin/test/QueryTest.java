/*************************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                              *
 * This program is free software; you can redistribute it and/or modify it    		 *
 * under the terms version 2 or later of the GNU General Public License as published *
 * by the Free Software Foundation. This program is distributed in the hope   		 *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied 		 *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           		 *
 * See the GNU General Public License for more details.                       		 *
 * You should have received a copy of the GNU General Public License along    		 *
 * with this program; if not, write to the Free Software Foundation, Inc.,    		 *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     		 *
 * For the text or an alternative of this public license, you may reach us    		 *
 * Copyright (C) 2012-2018 E.R.P. Consultores y Asociados, S.A. All Rights Reserved. *
 * Contributor(s): Yamel Senih www.erpya.com				  		                 *
 *************************************************************************************/
package org.spin.test;

import org.compiere.model.I_AD_Role;
import org.compiere.model.Query;
import org.compiere.util.Env;

import junit.framework.TestCase;

/**
 * @author Yamel Senih, ysenih@erpya.com , http://www.erpya.com
 */
public final class QueryTest extends TestCase {
	
	public QueryTest() {
		org.compiere.Adempiere.startup(true);
		Env.setContext(Env.getCtx(), "#AD_Client_ID", 11);
	}
	
	/**
	 * ADempiereSeed=# SELECT AD_Role_ID, UUID, Name, Description FROM AD_Role ORDER BY Created;
 ad_role_id |                 uuid                 |         name          |                  description                  
------------+--------------------------------------+-----------------------+-----------------------------------------------
          0 | a48d2596-fb40-11e8-a479-7a0060f0aa01 | System Administrator  | System Administrator Role (cannot be changed)
        103 |                                      | GardenWorld User      | GardenWorld User
        102 |                                      | GardenWorld Admin     | GardenWorld Admin
      50001 | a48d3022-fb40-11e8-a479-7a0060f0aa01 | Packaging User        | Packaging User
      50002 | a48d309a-fb40-11e8-a479-7a0060f0aa01 | FA User               | Fixed Asset User
      50004 |                                      | Web Service Execution | 
      50008 | a48d3108-fb40-11e8-a479-7a0060f0aa01 | Role Template         | 
(7 rows)
	 */
	public void testFullResult() {
		int eValue = 7;
		int value = new Query(Env.getCtx(), I_AD_Role.Table_Name, null, null)
			.setOrderBy(I_AD_Role.COLUMNNAME_Created)
			.list()
			.size();
		assertEquals(eValue, value);
	}
	
	/**
	 * ADempiereSeed=# SELECT AD_Role_ID, UUID, Name, Description FROM AD_Role ORDER BY Created LIMIT 5;
 ad_role_id |                 uuid                 |         name         |                  description                  
------------+--------------------------------------+----------------------+-----------------------------------------------
          0 | a48d2596-fb40-11e8-a479-7a0060f0aa01 | System Administrator | System Administrator Role (cannot be changed)
        103 |                                      | GardenWorld User     | GardenWorld User
        102 |                                      | GardenWorld Admin    | GardenWorld Admin
      50001 | a48d3022-fb40-11e8-a479-7a0060f0aa01 | Packaging User       | Packaging User
      50002 | a48d309a-fb40-11e8-a479-7a0060f0aa01 | FA User              | Fixed Asset User
(5 rows)
	 */
	public void testLimit5() {
		int eValue = 5;
		int value = new Query(Env.getCtx(), I_AD_Role.Table_Name, null, null)
			.setOrderBy(I_AD_Role.COLUMNNAME_Created)
			.setLimit(5)
			.list()
			.size();
		assertEquals(eValue, value);
	}
	
	/**
	 * ADempiereSeed=# SELECT AD_Role_ID, UUID, Name, Description FROM AD_Role ORDER BY Created LIMIT 5 OFFSET 4;
 ad_role_id |                 uuid                 |         name          |   description    
------------+--------------------------------------+-----------------------+------------------
      50002 | a48d309a-fb40-11e8-a479-7a0060f0aa01 | FA User               | Fixed Asset User
      50004 |                                      | Web Service Execution | 
      50008 | a48d3108-fb40-11e8-a479-7a0060f0aa01 | Role Template         | 
(3 rows)
	 */
	public void testLimit5Offset4() {
		int eValue = 3;
		int value = new Query(Env.getCtx(), I_AD_Role.Table_Name, null, null)
			.setOrderBy(I_AD_Role.COLUMNNAME_Created)
			.setLimit(5, 4)
			.list()
			.size();
		assertEquals(eValue, value);
	}
	
	/**
	 * ADempiereSeed=# SELECT AD_Role_ID, UUID, Name, Description FROM AD_Role ORDER BY Created OFFSET 4;
 ad_role_id |                 uuid                 |         name          |   description    
------------+--------------------------------------+-----------------------+------------------
      50002 | a48d309a-fb40-11e8-a479-7a0060f0aa01 | FA User               | Fixed Asset User
      50004 |                                      | Web Service Execution | 
      50008 | a48d3108-fb40-11e8-a479-7a0060f0aa01 | Role Template         | 
(3 rows)
	 */
	public void testOffset4() {
		int eValue = 3;
		int value = new Query(Env.getCtx(), I_AD_Role.Table_Name, null, null)
			.setOrderBy(I_AD_Role.COLUMNNAME_Created)
			.setLimit(Query.NO_LIMIT, 4)
			.list()
			.size();
		assertEquals(eValue, value);
	}
	
	/**
	 * ADempiereSeed=# SELECT AD_Role_ID, UUID, Name, Description FROM AD_Role ORDER BY Created OFFSET 2;
 ad_role_id |                 uuid                 |         name          |    description    
------------+--------------------------------------+-----------------------+-------------------
        102 |                                      | GardenWorld Admin     | GardenWorld Admin
      50001 | a48d3022-fb40-11e8-a479-7a0060f0aa01 | Packaging User        | Packaging User
      50002 | a48d309a-fb40-11e8-a479-7a0060f0aa01 | FA User               | Fixed Asset User
      50004 |                                      | Web Service Execution | 
      50008 | a48d3108-fb40-11e8-a479-7a0060f0aa01 | Role Template         | 
(5 rows)
	 */
	public void testOffset2() {
		int eValue = 5;
		int value = new Query(Env.getCtx(), I_AD_Role.Table_Name, null, null)
			.setOrderBy(I_AD_Role.COLUMNNAME_Created)
			.setLimit(Query.NO_LIMIT, 2)
			.list()
			.size();
		assertEquals(eValue, value);
	}
	
	/**
	 * ADempiereSeed=# SELECT AD_Role_ID, UUID, Name, Description FROM AD_Role ORDER BY Created OFFSET 0;
 ad_role_id |                 uuid                 |         name          |                  description                  
------------+--------------------------------------+-----------------------+-----------------------------------------------
          0 | a48d2596-fb40-11e8-a479-7a0060f0aa01 | System Administrator  | System Administrator Role (cannot be changed)
        103 |                                      | GardenWorld User      | GardenWorld User
        102 |                                      | GardenWorld Admin     | GardenWorld Admin
      50001 | a48d3022-fb40-11e8-a479-7a0060f0aa01 | Packaging User        | Packaging User
      50002 | a48d309a-fb40-11e8-a479-7a0060f0aa01 | FA User               | Fixed Asset User
      50004 |                                      | Web Service Execution | 
      50008 | a48d3108-fb40-11e8-a479-7a0060f0aa01 | Role Template         | 
(7 rows)
	 */
	public void testOffset0() {
		int eValue = 7;
		int value = new Query(Env.getCtx(), I_AD_Role.Table_Name, null, null)
			.setOrderBy(I_AD_Role.COLUMNNAME_Created)
			.setLimit(Query.NO_LIMIT, 0)
			.list()
			.size();
		assertEquals(eValue, value);
	}
}
