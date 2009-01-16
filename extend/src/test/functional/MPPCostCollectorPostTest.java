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
 * Copyright (C) 2003-2007 e-Evolution,SC. All Rights Reserved.               *
 * Contributor(s): Victor Perez www.e-evolution.com                           *
 *****************************************************************************/
package test.functional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;

import org.compiere.acct.Doc;
import org.compiere.model.MAcctSchema;
import org.compiere.model.Query;
import org.compiere.util.CLogMgt;
import org.compiere.util.Env;
import org.eevolution.model.MPPCostCollector;
import org.jfree.util.Log;

import test.AdempiereTestCase;

/**
 * Test MPPCostCollectorPostTest
 * @author victor.perez@e-evolution.com, http://e-evolution.com
 */
public class MPPCostCollectorPostTest extends AdempiereTestCase {
	int PP_Cost_Collector_ID=1000010; 
	
	protected void setUp() throws Exception {
		super.setUp();
		assertEquals("Client is not GardenWorld", 11, Env.getAD_Client_ID(getCtx()));
	}
	
	public MPPCostCollectorPostTest() 
	{		
	}
	
	public void testPosting() throws Exception 
	{
			//CLogMgt.setLevel(LogLevel_Value);
			CLogMgt.setLevel(Level.ALL);
			String whereClause = "";
			ArrayList params = new ArrayList();
			
			if(PP_Cost_Collector_ID  > 0)
			{	
				whereClause = whereClause + MPPCostCollector.COLUMNNAME_PP_Cost_Collector_ID + "=?";
				params.add(PP_Cost_Collector_ID);
			}	
			
			Collection <MPPCostCollector> ccs =new Query(getCtx(), MPPCostCollector.Table_Name, whereClause, null )
			.setParameters(params)
			.list();
			
			for (MPPCostCollector cc : ccs)
			{
				Log.info("Cost Collector" + cc.getDocumentNo());
				MAcctSchema[] m_ass = MAcctSchema.getClientAcctSchema(Env.getCtx(), 11);
				Doc doc = Doc.get (m_ass, MPPCostCollector.Table_ID, cc.get_ID(), null);
				if (doc == null)
				{
					fail("Should show a Exception");
				}
				else
				{
					String error = doc.post(true, true);   //  post no force/repost
					assertNull(null, error);
				}
			}
	}
}
