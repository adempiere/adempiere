/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2008 SC ARHIPAC SERVICE SRL. All Rights Reserved.            *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 *****************************************************************************/
package test.functional;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MTest;
import org.compiere.model.Query;
import org.compiere.util.Trx;
import org.compiere.util.TrxRunnable;

import test.AdempiereTestCase;

/**
 * Test {@link Trx} class
 * @author Teo Sarca, http://www.arhipac.ro
 */
public class TrxTest extends AdempiereTestCase {
	private int m_id2 = -1;
	
	/**
	 * Test {@link Trx#run(TrxRunnable)} and {@link Trx#run(String, TrxRunnable)} methods
	 */
	public void testRunTrxRunnable() throws Exception
	{
		//
		// Create test outside trx - success
		m_id2 = -1;
		Trx.run(new TrxRunnable() {
			public void run(String trxName) {
				m_id2 = createTest(trxName).get_ID();
			}
		});
		assertTestExists(m_id2, true, null);
		new MTest(getCtx(), m_id2, null).deleteEx(true);

		//
		// Create test outside trx - fail
		m_id2 = -1;
		try {
			Trx.run(new TrxRunnable() {
				public void run(String trxName) {
					m_id2 = createTest(trxName).get_ID();
					throw new AdempiereException("FORCE");
				}
			});
			//
			assertTrue("Should not happen because previous code is throwing exception", false);
		}
		catch (AdempiereException e) {
		}
		assertTestExists(m_id2, false, null);
		
		//
		// Create test1
		String trxName = getTrxName();
		MTest test1 = createTest(trxName);
		
		//
		// Fail creating test2
		m_id2 = -1;
		try {
			Trx.run(trxName, new TrxRunnable() {
				public void run(String trxName) {
					m_id2 = createTest(trxName).get_ID();
					throw new AdempiereException("FORCE");
				}
			});
			//
			assertTrue("Should not happen because previous code is throwing exception", false);
		}
		catch (AdempiereException e) {
		}
		assertTestExists(m_id2, false, trxName);
		assertTestExists(test1.get_ID(), true, trxName);
		
		//
		// Success creating test2
		m_id2 = -1;
		Trx.run(trxName, new TrxRunnable() {
			public void run(String trxName) {
				m_id2 = createTest(trxName).get_ID();
			}
		});
		assertTestExists(m_id2, true, trxName);
		assertTestExists(test1.get_ID(), true, trxName);
	}

	private final MTest createTest(String trxName) {
		MTest test = new MTest (getCtx(), "test-"+getClass(), 10);
		test.set_TrxName(trxName);
		test.saveEx();
		return test;
	}
	
	private final void assertTestExists(int test_id, boolean existsTarget, String trxName)
	{
		String whereClause = MTest.COLUMNNAME_Test_ID+"=?";
		boolean exists =  new Query(getCtx(), MTest.Table_Name, whereClause, trxName)
								.setParameters(new Object[]{test_id})
								.match();
		assertEquals("Test "+test_id+" [trxName="+trxName+"] - existance issue", existsTarget, exists);
	}
}
