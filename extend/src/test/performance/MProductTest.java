package test.performance;

/**********************************************************************
* This file is part of ADempiere Business Suite                       *
* http://www.adempiere.org                                            *
*                                                                     *
* Copyright (C) Trifon Trifonov.                                      *
* Copyright (C) Contributors                                          *
*                                                                     *
* This program is free software; you can redistribute it and/or       *
* modify it under the terms of the GNU General Public License         *
* as published by the Free Software Foundation; either version 2      *
* of the License, or (at your option) any later version.              *
*                                                                     *
* This program is distributed in the hope that it will be useful,     *
* but WITHOUT ANY WARRANTY; without even the implied warranty of      *
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the        *
* GNU General Public License for more details.                        *
*                                                                     *
* You should have received a copy of the GNU General Public License   *
* along with this program; if not, write to the Free Software         *
* Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,          *
* MA 02110-1301, USA.                                                 *
*                                                                     *
* Contributors:                                                       *
* - Trifon Trifonov (trifonnt@users.sourceforge.net)                  *
*                                                                     *
* Sponsors:                                                           *
* - Catura AG (http://www.catura.de)                                  *
***********************************************************************/

import java.text.DecimalFormat;
import java.text.NumberFormat;
import org.compiere.model.MProduct;
import test.AdempiereTestCase;

/**
 *	@author Trifon Trifonov
 */
public class MProductTest extends AdempiereTestCase {
	
	private MProduct product = null;
	private int AD_Org_ID = 0;
	private int M_Product_Category_ID = 0;
	private int C_TaxCategory_ID = 0;
	private int C_UOM_ID = 0;
	private String ProductType = null;
	
	
	protected void setUp() throws Exception
	{
		super.setUp();
		// How to setup language
		//Env.setContext(getCtx(), Env.LANGUAGE, "en_US");
		
		// Organization
		AD_Org_ID = Integer.parseInt( testProperties.getProperty("AD_Org_ID", "0") ); // 0 in Any Tenant
		// M_Product_Category
		M_Product_Category_ID = Integer.parseInt( testProperties.getProperty("M_Product_Category_ID", "105") ); // 105 in GardenWorld
		// C_TaxCategory
		C_TaxCategory_ID = Integer.parseInt( testProperties.getProperty("C_TaxCategory_ID", "107") ); // 107 in GardenWorld
		// C_UOM
		C_UOM_ID = Integer.parseInt( testProperties.getProperty("C_UOM_ID", "100") ); // 100 in GardenWorld
		// C_UOM
		ProductType = testProperties.getProperty("ProductType", "I"); // 'I' in GardenWorld
		
		// How to setup Log Level. Not necessary as AdempiereTestCase is taking care of it!
//		CLogMgt.setLevel(Level.OFF);
/*		Available levels: 
		Level.OFF, Level.SEVERE, Level.WARNING, Level.INFO,
		Level.CONFIG, Level.FINE, Level.FINER, Level.FINEST, Level.ALL
*/
		
//		assertEquals("Client is NOT GardenWorld", 11, Env.getAD_Client_ID(getCtx()));
		
	}
	
	public void testMProductCreation() {
		boolean singleCommit = true;
		
		// Start time - 20:16
		long startTime = System.currentTimeMillis();
		System.out.println("Start Time(ms) = " + startTime);
		System.out.println("Start Time     = " + new java.util.Date(startTime));
		int startCount = 43000;
		int count = 500;
		// The 0 symbol shows a digit or 0 if no digit present 
		// The # symbol shows a digit or nothing if no digit present 
		NumberFormat formatter = new DecimalFormat("00000");  // -001235
		
		for (int idx= startCount; idx < (startCount + count); idx++) {
			System.out.println("idx = " + idx);
			product = new MProduct(getCtx(), 0, getTrxName());
			
			String formattedIdx = formatter.format( idx );
			product.setValue("test-product-" + formattedIdx);
			product.setName("Test Product " + formattedIdx);
			
			product.setAD_Org_ID(AD_Org_ID);
			product.setM_Product_Category_ID(M_Product_Category_ID);
			product.setC_TaxCategory_ID(C_TaxCategory_ID);
			product.setC_UOM_ID(C_UOM_ID);
			product.setProductType(ProductType);
			
			boolean saveResult = product.save();
			assertTrue("MProduct.save()", saveResult);
			//System.out.println("product.getM_Product_ID: " + product.getM_Product_ID());
			if (singleCommit) {
				try {
					commit();
				} catch (Exception e) {
					fail(e.getLocalizedMessage());
				}
			}	
		} // end 'for' loop
		
		if (!singleCommit) {
			try {
				commit();
			} catch (Exception e) {
				fail(e.getLocalizedMessage());
			}
		}
		long endTime = System.currentTimeMillis();
		System.out.println("End Time(ms) = " + endTime);
		System.out.println("End Time     = " + new java.util.Date(endTime));
		long time = endTime - startTime;
		System.out.println("Duration(ms) = " + time);
		
		time = time / 1000;
		System.out.println("Duration(sec.) = " + time);
		if (time > 0) {
			System.out.println("Duration(min.) = " + time / 60);	
		}
		
		System.out.println(  
			  "Count = " + count 
			+ "; Time(seconds) = " + time + "; Produsts/Second = " + ((float)count/time) + "; ");
		
		assertTrue(this.getClass().getName(), true);
	}
}
