//MProductTest.java
package test.functional;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.logging.Level;

import junit.framework.TestCase;

import org.compiere.model.*;
import org.compiere.util.CLogMgt;
import org.compiere.util.DB;
import org.compiere.util.Ini;
import org.compiere.utils.DBUtils;
import org.compiere.util.*;
import java.sql.*;

public class MProductTest extends TestCase {
	
	// Test: General
	private Properties testProperties = null;

	private Properties m_Ctx = null;
	String trxName = "test";
	
	private String fileName_DefaultValue = "J:/Trifon-CD-0.3/workspace/adempiere-trunk/adempiere/Adempiere/Adempiere.properties";
	private String fileName_Key = "AdempiereProperties";
	private String fileName_Value = "";
	
	private String isClient_DefaultValue = "Y";
	private String isClient_Key = "isClient";
	private boolean isClient_Value = true;

	private String AD_User_ID_DefaultValue = "0";
	private String AD_User_ID_Key = "AD_User_ID";
	private int AD_User_ID_Value = 0;

	private String AD_Client_ID_DefaultValue = "11";
	private String AD_Client_ID_Key = "AD_Client_ID";
	private int AD_Client_ID_Value = 11;

	// Test: Specific variables
	private MProduct location = null;
	

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		
		testProperties = new Properties();
		testProperties.load(new FileInputStream("test.properties"));
		fileName_Value = testProperties.getProperty(fileName_Key, fileName_DefaultValue);
		isClient_Value = "Y".equals( testProperties.getProperty(isClient_Key, isClient_DefaultValue) );
		AD_User_ID_Value = Integer.parseInt(testProperties.getProperty(AD_User_ID_Key, AD_User_ID_DefaultValue) );
		AD_Client_ID_Value = Integer.parseInt(testProperties.getProperty(AD_Client_ID_Key, AD_Client_ID_DefaultValue) );
		
		
		m_Ctx = new Properties();
		m_Ctx.setProperty("#AD_User_ID", new Integer(AD_User_ID_Value).toString());
		m_Ctx.setProperty("#AD_Client_ID", new Integer(AD_Client_ID_Value).toString());
		System.out.println("m_Ctx: " + m_Ctx);
		
		if (fileName_Value.length() < 1) {
		    assertEquals("Please specify path to Adempiere.properties file!", true, false);
		}
		
		System.setProperty("PropertyFile", fileName_Value);
		Ini.setClient (isClient_Value);
		org.compiere.Adempiere.startup(isClient_Value);

		// Force connection if there are enough parameters. Else we work with Adempiere.properties
//		if (args.length >= 6) {
//		    CConnection cc = CConnection.get(Database.DB_ORACLE, args[1], Integer.valueOf(args[2]).intValue(), args[3], args[4], args[5]);
//		    System.out.println("DB UserID:"+cc.getDbUid());
//		    DB.setDBTarget(cc);
//		}
	
		CLogMgt.setLevel(Level.FINEST);
/*		Available levels: 
		Level.OFF, Level.SEVERE, Level.WARNING, Level.INFO,
		Level.CONFIG, Level.FINE, Level.FINER, Level.FINEST, Level.ALL
*/
	}
	
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		
		testProperties = null;
		m_Ctx = null;
	}

 	public int getProduct_Category_ID(String productCategory) {
        	System.out.println("In getProduct_Category_ID");

        String sql = "select m_product_category_id from m_product_category where name = ?";
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        int M_ProductCategory_ID = -1;

         try {
                pstmt = DB.prepareStatement(sql, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY, trxName);
                pstmt.setString(1, productCategory.trim());
                rs = pstmt.executeQuery();


                while (rs.next()) {
                        M_ProductCategory_ID = rs.getInt(1);
                }
        } catch (SQLException e) {
                System.out.println("Execption; sql = "+sql+"; e.getMessage() = " +e.getMessage());
 } finally {
                DBUtils.close( rs);
                DBUtils.close( pstmt);
        }
        System.out.println("productCategory: " + productCategory);
        System.out.println("M_ProductCategory_ID: " + M_ProductCategory_ID);

    return M_ProductCategory_ID;

    }

 	public int getUOM_ID(String UOM) {
        	System.out.println("In getUOM_ID");

        String sql = "select c_uom_id from c_uom where name = ?";
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        int C_Uom_ID = -1;

         try {
                pstmt = DB.prepareStatement(sql, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY, trxName);
                pstmt.setString(1, UOM.trim());
                rs = pstmt.executeQuery();


                while (rs.next()) {
                        C_Uom_ID = rs.getInt(1);
                }
        } catch (SQLException e) {
                System.out.println("Execption; sql = "+sql+"; e.getMessage() = " +e.getMessage());
 } finally {
                DBUtils.close( rs);
                DBUtils.close( pstmt);
        }
        System.out.println("Uom: " + UOM);
        System.out.println("C_Uom_ID: " + C_Uom_ID);

    return C_Uom_ID;

    }



	public void testMProductCreation() {
		MProduct m_product = new MProduct(m_Ctx, 0, trxName);
		m_product.setAD_Org_ID(0);
		m_product.setProductType (X_I_Product.PRODUCTTYPE_Item);      // I
                m_product.setIsBOM (false);       // N
                m_product.setIsInvoicePrintDetails (false);
                m_product.setIsPickListPrintDetails (false);
                m_product.setIsPurchased (true);  // Y
                m_product.setIsSold (true);       // Y
                m_product.setIsStocked (true);    // Y
                m_product.setIsSummary (false);
                m_product.setIsVerified (false);  // N
                m_product.setIsWebStoreFeatured (false);
                m_product.setIsSelfService(true);
                m_product.setIsExcludeAutoDelivery(false);
                m_product.setProcessing (false);  // N
                m_product.setName("Test Product");  // N
		m_product.setC_UOM_ID(getUOM_ID("Each"));
		//m_product.setM_Product_ID(getProduct_Category_ID("Documentation"));
		m_product.setM_Product_ID(111);


		boolean saveResult = m_product.save();
		if (!saveResult) {
			assertEquals("Product not updated!", true, saveResult);
		} else {
			System.out.println("m_product.get_ID: " + m_product.get_ID());
			/* try {
				DB.commit(true, trxName);
			} catch (Exception e) {
				assertEquals("Location not updated!", true, false);
			} */
		}
		assertTrue("MProductTest", true);
	}
}
