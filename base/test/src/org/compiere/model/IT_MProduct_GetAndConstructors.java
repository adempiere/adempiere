//MProductTest.java
package test.functional;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.compiere.model.MProduct;
import org.compiere.model.MProductPrice;
import org.compiere.model.MProductPricing;
import org.compiere.model.X_I_Product;
import org.compiere.util.DB;
import org.compiere.util.Env;

import test.AdempiereTestCase;

public class MProductTest extends AdempiereTestCase {
/*
	public int getProduct_Category_ID(String productCategory) {
		String sql = "select m_product_category_id from m_product_category where name = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int M_ProductCategory_ID = -1;

		try {
			pstmt = DB.prepareStatement(sql, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY, getTrxName());
			pstmt.setString(1, productCategory.trim());
			rs = pstmt.executeQuery();


			while (rs.next()) {
				M_ProductCategory_ID = rs.getInt(1);
			}
		} catch (SQLException e) {
			fail(e.getLocalizedMessage());
		} finally {
			DB.close( rs, pstmt );
		}

		return M_ProductCategory_ID;

	}

	public int getUOM_ID(String UOM) {
		System.out.println("In getUOM_ID");

		String sql = "select c_uom_id from c_uom where name = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int C_Uom_ID = -1;

		try {
			pstmt = DB.prepareStatement(sql, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY, getTrxName());
			pstmt.setString(1, UOM.trim());
			rs = pstmt.executeQuery();


			while (rs.next()) {
				C_Uom_ID = rs.getInt(1);
			}
		} catch (SQLException e) {
			fail(e.getLocalizedMessage());
		} finally {
			DB.close( rs, pstmt );
		}
		System.out.println("Uom: " + UOM);
		System.out.println("C_Uom_ID: " + C_Uom_ID);

		return C_Uom_ID;

	}
 
	public void testCreateMProduct() {
		MProduct m_product = new MProduct(getCtx(), 0, getTrxName());
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

		boolean saveResult = m_product.saveEx();
		assertEquals("Create new product.", true, saveResult);
	}  
	
	public void testSetBaseInfo() {
		MProductPricing prodprice = new MProductPricing(122,100, new BigDecimal (100),true);
		int uom = 0;
		uom = prodprice.getC_UOM_ID();
		assertTrue("UOM must be correct", uom == 100);
	} */
	
	public void testPrice() {
		MProductPrice test = MProductPrice.get(getCtx(), 105, 124, getTrxName());
		assertTrue("Confirming Prod ID to be true", test.getM_Product_ID() == 124);
	}
}
