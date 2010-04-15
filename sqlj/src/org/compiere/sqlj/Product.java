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
package org.compiere.sqlj;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;


/**
 *	SQLJ Product related Functions
 *	
 *  @author Jorg Janke
 *  @version $Id: Product.java,v 1.3 2006/07/30 00:59:07 jjanke Exp $
 */
public class Product
{
	/**
	 * 	Get Product Attribute Instance Name.
	 * 	Previously:  M_Attribute_Name - Now: productAttribute
	 * 	Test:
	 	    SELECT M_Attribute_Name (M_AttributeSetInstance_ID) 
		    FROM M_InOutLine WHERE M_AttributeSetInstance_ID > 0
		    --
		    SELECT p.Name
		    FROM C_InvoiceLine il LEFT OUTER JOIN M_Product p ON (il.M_Product_ID=p.M_Product_ID);
		    SELECT p.Name || M_Attribute_Name (il.M_AttributeSetInstance_ID) 
		    FROM C_InvoiceLine il LEFT OUTER JOIN M_Product p ON (il.M_Product_ID=p.M_Product_ID);
	 *	@param p_M_AttributeSetInstance_ID instance
	 *	@return Name or ""
	 *	@throws SQLException
	 */
	public static String attributeName (int p_M_AttributeSetInstance_ID)
		throws SQLException
	{
		if (p_M_AttributeSetInstance_ID == 0)
			return "";
		//
		StringBuffer sb = new StringBuffer();
		//	Get Base Info
		String sql = "SELECT asi.Lot, asi.SerNo, asi.GuaranteeDate "
			+ "FROM M_AttributeSetInstance asi "
			+ "WHERE asi.M_AttributeSetInstance_ID=?";
		PreparedStatement pstmt = Adempiere.prepareStatement(sql);
		pstmt.setInt(1, p_M_AttributeSetInstance_ID);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next())
		{
			String lot = rs.getString(1);
			if (lot != null && lot.length() > 0)
				sb.append(lot).append(" ");
			String serNo = rs.getString(2);
			if (serNo != null && serNo.length() > 0)
				sb.append("#").append(serNo).append(" ");
			Date guaranteeDate = null;
			//either date or timestamp
			Object guarantee = rs.getObject(3);
			if (guarantee != null)
			{
				if (guarantee instanceof Timestamp)
				{
					Timestamp ts = (Timestamp)guarantee;
					guaranteeDate = new Date(ts.getTime());
				}
				else 
				{
					guaranteeDate = (Date)guaranteeDate;
				}
			}
			if (guaranteeDate != null)
				sb.append(guaranteeDate).append(" ");
		}
		rs.close();
		pstmt.close();

		//	Get Instance Info
		sql = "SELECT ai.Value, a.Name "
			+ "FROM M_AttributeInstance ai"
			+ " INNER JOIN M_Attribute a ON (ai.M_Attribute_ID=a.M_Attribute_ID AND a.IsInstanceAttribute='Y') "
			+ "WHERE ai.M_AttributeSetInstance_ID=?";
		pstmt = Adempiere.prepareStatement(sql);
		pstmt.setInt(1, p_M_AttributeSetInstance_ID);
		rs = pstmt.executeQuery();
		while (rs.next())
		{
			sb.append(rs.getString(1))					//	value
				.append(":").append(rs.getString(2))	//	name
				.append(" ");
		}
		rs.close();
		pstmt.close();
		
		if (sb.length() == 0)
			return "";
		sb.insert(0, " (");
		sb.append(")");
		return sb.toString();
	}	//	getAttributeName

	
	/**************************************************************************
	 * 	Get BOM Price Limit
	 * 	Previously:  BOM_PriceLimit - Now: bomPriceLimit
	 *	@param p_M_Product_ID
	 *	@param p_M_PriceList_Version_ID
	 *	@return Price Limit
	 *	@throws SQLException
	 */
	public static BigDecimal bomPriceLimit (int p_M_Product_ID, int p_M_PriceList_Version_ID) 
		throws SQLException
	{
		return bomPrice(p_M_Product_ID, p_M_PriceList_Version_ID, "PriceLimit");
	}	//	bomPriceLimit
	
	/**
	 * 	Get BOM Price List
	 * 	Previously:  BOM_PriceList - Now: bomPriceList
	 *	@param p_M_Product_ID
	 *	@param p_M_PriceList_Version_ID
	 *	@return Price List
	 *	@throws SQLException
	 */
	public static BigDecimal bomPriceList (int p_M_Product_ID, int p_M_PriceList_Version_ID) 
		throws SQLException
	{
		return bomPrice(p_M_Product_ID, p_M_PriceList_Version_ID, "PriceList");
	}	//	bomPriceList
	
	/**
	 * 	Get BOM Price Std
	 * 	Previously:  BOM_PriceStd - Now: bomPriceStd
	 *	@param p_M_Product_ID
	 *	@param p_M_PriceList_Version_ID
	 *	@return Price Std
	 *	@throws SQLException
	 */
	public static BigDecimal bomPriceStd (int p_M_Product_ID, int p_M_PriceList_Version_ID) 
		throws SQLException
	{
		return bomPrice(p_M_Product_ID, p_M_PriceList_Version_ID, "PriceStd");
	}	//	bomPriceStd

	/**
	 * 	Get BOM Price
	 *	@param p_M_Product_ID
	 *	@param p_M_PriceList_Version_ID
	 *	@param p_what variable name
	 *	@return Price
	 *	@throws SQLException
	 */
	static BigDecimal bomPrice (int p_M_Product_ID, int p_M_PriceList_Version_ID, String p_what) 
		throws SQLException
	{
		BigDecimal price = null;
		//	Try to get price from PriceList directly
		String sql = "SELECT " + p_what
			+ " FROM M_ProductPrice "
			+ "WHERE M_PriceList_Version_ID=? AND M_Product_ID=?";
		PreparedStatement pstmt = Adempiere.prepareStatement(sql);
		pstmt.setInt(1, p_M_PriceList_Version_ID);
		pstmt.setInt(2, p_M_Product_ID);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next())
			price = rs.getBigDecimal(1);
		rs.close();
		pstmt.close();
		//	Loop through BOM
		if (price == null || price.signum() == 0)
		{
			price = Adempiere.ZERO;
			/*sql = "SELECT b.M_ProductBOM_ID, b.BOMQty, p.IsBOM "
				+ "FROM M_Product_BOM b, M_Product p "
				+ "WHERE b.M_ProductBOM_ID=p.M_Product_ID"
				+ " AND b.M_Product_ID=?";*/
			sql = "SELECT bl.M_Product_ID , CASE WHEN bl.IsQtyPercentage = 'N' THEN bl.QtyBOM ELSE bl.QtyBatch / 100 END AS Qty , p.IsBOM FROM PP_Product_BOM b "
				+ "INNER JOIN M_Product p ON (p.M_Product_ID=b.M_Product_ID) "
				+ "INNER JOIN PP_Product_BOMLine bl ON (bl.PP_Product_BOM_ID=b.PP_Product_BOM_ID) "
				+ "WHERE b.M_Product_ID = ?";
			pstmt = Adempiere.prepareStatement(sql);
			pstmt.setInt(1, p_M_Product_ID);
			rs = pstmt.executeQuery();
			while (rs.next())
			{
				int M_ProductBOM_ID = rs.getInt(1);
				BigDecimal qty = rs.getBigDecimal(2);
				BigDecimal productPrice = bomPrice(M_ProductBOM_ID, p_M_PriceList_Version_ID, p_what);
				productPrice = productPrice.multiply(qty);
				price = price.add(productPrice);
			}
			rs.close();
			pstmt.close();
		}
		return price;
	}	//	bomPrice

	
	/**************************************************************************
	 * 	Get BOM Quantity Available 
	 * 	Previously:  BOM_Qty_Available - Now: bomQtyAvailable
	 *	@param p_M_Product_ID product
	 *	@param p_M_Warehouse_ID warehouse
	 *	@param p_M_Locator_ID locator
	 *	@return Quantity Available
	 *	@throws SQLException
	 */
	public static BigDecimal bomQtyAvailable (int p_M_Product_ID, 
		int p_M_Warehouse_ID, int p_M_Locator_ID) 
		throws SQLException
	{
		return bomQty(p_M_Product_ID, p_M_Warehouse_ID, p_M_Locator_ID, "QtyOnHand")
			.subtract(bomQty(p_M_Product_ID, p_M_Warehouse_ID, p_M_Locator_ID, "QtyReserved"));
	}	//	bomQtyAvailable
	
	/**
	 * 	Get BOM Quantity OnHand 
	 * 	Previously:  BOM_Qty_OnHand - Now: bomQtyOnHand
	 *	@param p_M_Product_ID product
	 *	@param p_M_Warehouse_ID warehouse
	 *	@param p_M_Locator_ID locator
	 *	@return Quantity Available
	 *	@throws SQLException
	 */
	public static BigDecimal bomQtyOnHand (int p_M_Product_ID, 
		int p_M_Warehouse_ID, int p_M_Locator_ID) 
		throws SQLException
	{
		return bomQty(p_M_Product_ID, p_M_Warehouse_ID, p_M_Locator_ID, "QtyOnHand");
	}	//	bomQtyOnHand
	
	/**
	 * 	Get BOM Quantity Ordered 
	 * 	Previously:  BOM_Qty_Ordered - Now: bomQtyOrdered
	 *	@param p_M_Product_ID product
	 *	@param p_M_Warehouse_ID warehouse
	 *	@param p_M_Locator_ID locator
	 *	@return Quantity Ordered
	 *	@throws SQLException
	 */
	public static BigDecimal bomQtyOrdered (int p_M_Product_ID, 
		int p_M_Warehouse_ID, int p_M_Locator_ID) 
		throws SQLException
	{
		return bomQty(p_M_Product_ID, p_M_Warehouse_ID, p_M_Locator_ID, "QtyOrdered");
	}	//	bomQtyOrdered
	
	/**
	 * 	Get BOM Quantity Reserved 
	 * 	Previously:  BOM_Qty_Reserved - Now: bomQtyReserved
	 *	@param p_M_Product_ID product
	 *	@param p_M_Warehouse_ID warehouse
	 *	@param p_M_Locator_ID locator
	 *	@return Qyantity Reserved
	 *	@throws SQLException
	 */
	public static BigDecimal bomQtyReserved (int p_M_Product_ID, 
		int p_M_Warehouse_ID, int p_M_Locator_ID) 
		throws SQLException
	{
		return bomQty(p_M_Product_ID, p_M_Warehouse_ID, p_M_Locator_ID, "QtyReserved");
	}	//	bomQtyReserved
	
	/**
	 * 	Get BOM Quantity
	 *	@param p_M_Product_ID product
	 *	@param p_M_Warehouse_ID warehouse
	 *	@param p_M_Locator_ID locator
	 *	@param p_what variable name
	 *	@return Quantity
	 *	@throws SQLException
	 */
	static BigDecimal bomQty (int p_M_Product_ID, 
		int p_M_Warehouse_ID, int p_M_Locator_ID, String p_what) 
		throws SQLException
	{
		//	Check Parameters
		int M_Warehouse_ID = p_M_Warehouse_ID;
		if (M_Warehouse_ID == 0)
		{
			if (p_M_Locator_ID == 0)
				return Adempiere.ZERO;
			else
			{
				String sql = "SELECT M_Warehouse_ID "
					+ "FROM M_Locator "
					+ "WHERE M_Locator_ID=?";
				M_Warehouse_ID = Adempiere.getSQLValue(sql, p_M_Locator_ID);
			}
		}
		// begin globalqss 2005-10-11
		// if (M_Warehouse_ID == 0)
		if (M_Warehouse_ID == 0 || M_Warehouse_ID == -1)
	      // end globalqss 2005-10-11
			return Adempiere.ZERO;
		
		//	Check, if product exists and if it is stocked
		boolean isBOM = false;
		String ProductType = null;
		boolean isStocked = false;
		String sql = "SELECT IsBOM, ProductType, IsStocked "
			+ "FROM M_Product "
			+ "WHERE M_Product_ID=?";
		PreparedStatement pstmt = Adempiere.prepareStatement(sql);
		pstmt.setInt(1, p_M_Product_ID);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next())
		{
			isBOM = "Y".equals(rs.getString(1));
			ProductType = rs.getString(2);
			isStocked = "Y".equals(rs.getString(3));
		}
		rs.close();
		pstmt.close();
		//	No Product
		if (ProductType == null)
			return Adempiere.ZERO;
		//	Unlimited capacity if no item
		if (!isBOM && (!ProductType.equals("I") || !isStocked))
			return UNLIMITED;
		//	Get Qty
		if (isStocked)
			return getStorageQty(p_M_Product_ID, M_Warehouse_ID, p_M_Locator_ID, p_what);
		
		//	Go through BOM
		BigDecimal quantity = UNLIMITED;
		BigDecimal productQuantity = null;
		/*sql = "SELECT b.M_ProductBOM_ID, b.BOMQty, p.IsBOM, p.IsStocked, p.ProductType "
			+ "FROM M_Product_BOM b, M_Product p "
			+ "WHERE b.M_ProductBOM_ID=p.M_Product_ID"
			+ " AND b.M_Product_ID=?";*/
		
		sql = "SELECT bl.M_Product_ID , CASE WHEN bl.IsQtyPercentage = 'N' THEN bl.QtyBOM ELSE bl.QtyBatch / 100 END AS Qty , p.IsBOM , p.IsStocked, p.ProductType FROM PP_Product_BOM b "
			+ "INNER JOIN M_Product p ON (p.M_Product_ID=b.M_Product_ID) "
			+ "INNER JOIN PP_Product_BOMLine bl ON (bl.PP_Product_BOM_ID=b.PP_Product_BOM_ID) "
			+ "WHERE b.M_Product_ID = ?";
		
		pstmt = Adempiere.prepareStatement(sql);
		pstmt.setInt(1, p_M_Product_ID);
		rs = pstmt.executeQuery();
		while (rs.next())
		{
			int M_ProductBOM_ID = rs.getInt(1);
			BigDecimal bomQty = rs.getBigDecimal(2);
			isBOM = "Y".equals(rs.getString(3));
			isStocked = "Y".equals(rs.getString(4)); 
			ProductType = rs.getString(5);
			
			//	Stocked Items "leaf node"
			if (ProductType.equals("I") && isStocked)
			{
				//	Get ProductQty
				productQuantity = getStorageQty(M_ProductBOM_ID, M_Warehouse_ID, p_M_Locator_ID, p_what);
				//	Get Rounding Precision
				int uomPrecision = getUOMPrecision(M_ProductBOM_ID);
				//	How much can we make with this product
				//hengsin, [ 1649453 ] bomQtyAvailable sqlj function throw ArithmeticException
				//productQuantity = productQuantity.setScale(uomPrecision)
				//	.divide(bomQty, uomPrecision, BigDecimal.ROUND_HALF_UP);
				productQuantity = productQuantity.divide(bomQty, uomPrecision, BigDecimal.ROUND_HALF_UP);
				//	How much can we make overall
				if (productQuantity.compareTo(quantity) < 0)
					quantity = productQuantity;
			}
			else if (isBOM)	//	Another BOM
			{
				productQuantity = bomQty (M_ProductBOM_ID, M_Warehouse_ID, p_M_Locator_ID, p_what);
				//	How much can we make overall
				if (productQuantity.compareTo(quantity) < 0)
					quantity = productQuantity;
			}
		}
		rs.close();
		pstmt.close();
		
		if (quantity.signum() != 0)
		{
			int uomPrecision = getUOMPrecision(p_M_Product_ID);
			return quantity.setScale(uomPrecision, BigDecimal.ROUND_HALF_UP);
		}
		return Adempiere.ZERO;
	}	//	bomQtyOnHand
	
	/** Unlimited Quantity			*/
	private static final BigDecimal UNLIMITED = new BigDecimal((double)99999.0);
	
	/**
	 * 	Get Storage Qty
	 *	@param p_M_Product_ID product
	 *	@param M_Warehouse_ID warehouse
	 *	@param p_M_Locator_ID locator
	 *	@param p_what variable name
	 *	@return quantity or zero
	 *	@throws SQLException
	 */
	static BigDecimal getStorageQty (int p_M_Product_ID, 
		int M_Warehouse_ID, int p_M_Locator_ID, String p_what)
		throws SQLException
	{
		BigDecimal quantity = null;
		String sql = "SELECT SUM(" + p_what + ") "
			+ "FROM M_Storage s "
			+ "WHERE M_Product_ID=?";
		if (p_M_Locator_ID != 0)
			sql += " AND s.M_Locator_ID=?";
		else
			sql += " AND EXISTS (SELECT * FROM M_Locator l WHERE s.M_Locator_ID=l.M_Locator_ID"
				+ " AND l.M_Warehouse_ID=?)";
		PreparedStatement pstmt = Adempiere.prepareStatement(sql);
		pstmt.setInt(1, p_M_Product_ID);
		if (p_M_Locator_ID != 0)
			pstmt.setInt(2, p_M_Locator_ID);
		else
			pstmt.setInt(2, M_Warehouse_ID);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next())
			quantity = rs.getBigDecimal(1);
		rs.close();
		pstmt.close();
		//	Not found
		if (quantity == null)
			return Adempiere.ZERO;
		return quantity;
	}	//	getStorageQty
	
	/**
	 * 	Get UOM Precision for Product
	 *	@param p_M_Product_ID product
	 *	@return precision or 0
	 *	@throws SQLException
	 */
	static int getUOMPrecision (int p_M_Product_ID) throws SQLException
	{
		int precision = 0;
		String sql = "SELECT u.StdPrecision "
			+ "FROM C_UOM u"
			+ " INNER JOIN M_Product p ON (u.C_UOM_ID=p.C_UOM_ID) "
			+ "WHERE p.M_Product_ID=?";
		PreparedStatement pstmt = Adempiere.prepareStatement(sql);
		pstmt.setInt(1, p_M_Product_ID);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next())
			precision = rs.getInt(1);
		rs.close();
		pstmt.close();
		return precision;
	}	//	getStdPrecision

	/**
	 * 	Test
	 *	@param args
	 *
	public static void main (String[] args)
	{
		
		try
		{
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			Adempiere.s_type = Adempiere.TYPE_ORACLE;
			Adempiere.s_url = "jdbc:oracle:thin:@//dev1:1521/dev1.adempiere.org";
			Adempiere.s_uid = "adempiere";
			Adempiere.s_pwd = "adempiere";
	//		System.out.println(Product.bomQtyOnHand(p_M_Product_ID, 0, p_M_Locator_ID));
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}	//	main	/* */

	
	public static BigDecimal bomQtyAvailableASI (int p_M_Product_ID, int p_M_AttributeSetInstance_ID, 
			int p_M_Warehouse_ID, int p_M_Locator_ID) 
			throws SQLException
		{
			return bomQtyOnHandASI(p_M_Product_ID, p_M_AttributeSetInstance_ID, p_M_Warehouse_ID, p_M_Locator_ID)
			.subtract(bomQtyReservedASI(p_M_Product_ID, p_M_AttributeSetInstance_ID, p_M_Warehouse_ID, p_M_Locator_ID));
		}	//	bomQtyAvailable
	
	public static BigDecimal bomQtyOnHandASI (int p_M_Product_ID, int p_M_AttributeSetInstance_ID, 
			int p_M_Warehouse_ID, int p_M_Locator_ID) 
			throws SQLException
		{
			return bomQtyASI(p_M_Product_ID, p_M_AttributeSetInstance_ID, p_M_Warehouse_ID, p_M_Locator_ID, "QtyOnHand");
		}	//	bomQtyOnHand
	
	public static BigDecimal bomQtyOrderedASI (int p_M_Product_ID, int p_M_AttributeSetInstance_ID,
			int p_M_Warehouse_ID, int p_M_Locator_ID) 
			throws SQLException
		{
			return bomQtyASI(p_M_Product_ID, p_M_AttributeSetInstance_ID, p_M_Warehouse_ID, p_M_Locator_ID, "QtyOrdered");
		}	//	bomQtyOrdered
		
	public static BigDecimal bomQtyReservedASI (int p_M_Product_ID, int p_M_AttributeSetInstance_ID, 
			int p_M_Warehouse_ID, int p_M_Locator_ID) 
			throws SQLException
    {
		return bomQtyASI(p_M_Product_ID, p_M_AttributeSetInstance_ID, p_M_Warehouse_ID, p_M_Locator_ID, "QtyReserved");
	}	//	bomQtyReserved
	
	/**
	 * 	Get BOM Quantity
	 *	@param p_M_Product_ID product
	 *	@param p_M_Warehouse_ID warehouse
	 *	@param p_M_Locator_ID locator
	 *	@param p_what variable name
	 *	@return Quantity
	 */
	static BigDecimal bomQtyASI (int p_M_Product_ID, int p_M_AttributeSetInstance_ID, 
		int p_M_Warehouse_ID, int p_M_Locator_ID, String p_what) 
		throws SQLException
	{
		//	Check Parameters
		/*
		int M_Warehouse_ID = p_M_Warehouse_ID;
		if (M_Warehouse_ID == 0)
		{
			if (p_M_Locator_ID == 0)
				return Compiere.ZERO;
			else
			{
				String sql = "SELECT M_Warehouse_ID "
					+ "FROM M_Locator "
					+ "WHERE M_Locator_ID=" + p_M_Locator_ID;
				M_Warehouse_ID = Compiere.getSQLValue(sql, p_M_Locator_ID);
			}
		}
		if (M_Warehouse_ID == 0)
			return Compiere.ZERO;
		*/
		//	Check, if product exists and if it is stocked
		boolean isBOM = false;
		String ProductType = null;
		boolean isStocked = false;
		String sql = "SELECT IsBOM, ProductType, IsStocked "
			+ "FROM M_Product "
			+ "WHERE M_Product_ID=?";
		PreparedStatement pstmt = Adempiere.prepareStatement(sql);
		pstmt.setInt(1, p_M_Product_ID);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next())
		{
			isBOM = "Y".equals(rs.getString(1));
			ProductType = rs.getString(2);
			isStocked = "Y".equals(rs.getString(3));
		}
		rs.close();
		pstmt.close();
		//	No Product
		if (ProductType == null)
			return Compiere.ZERO;
		//	Unlimited capacity if no item
		if (!isBOM && (!ProductType.equals("I") || !isStocked))
			return UNLIMITED;
		//	Get Qty
		if (isStocked) {
			
			return getStorageQtyASI(p_M_Product_ID, p_M_AttributeSetInstance_ID, p_M_Warehouse_ID, p_M_Locator_ID, p_what);
		}
		//	Go through BOM
		BigDecimal quantity = UNLIMITED;
		BigDecimal productQuantity = null;
		/*sql = "SELECT b.M_ProductBOM_ID, b.BOMQty, p.IsBOM, p.IsStocked, p.ProductType "
			+ "FROM M_Product_BOM b, M_Product p "
			+ "WHERE b.M_ProductBOM_ID=p.M_Product_ID"
			+ " AND b.M_Product_ID=?";*/
		sql = "SELECT bl.M_Product_ID , CASE WHEN bl.IsQtyPercentage = 'N' THEN bl.QtyBOM ELSE bl.QtyBatch / 100 END AS Qty , p.IsBOM , p.IsStocked, p.ProductType FROM PP_Product_BOM b "
			+ "INNER JOIN M_Product p ON (p.M_Product_ID=b.M_Product_ID) "
			+ "INNER JOIN PP_Product_BOMLine bl ON (bl.PP_Product_BOM_ID=b.PP_Product_BOM_ID) "
			+ "WHERE b.M_Product_ID = ?";
		pstmt = Adempiere.prepareStatement(sql);
		pstmt.setInt(1, p_M_Product_ID);
		rs = pstmt.executeQuery();
		while (rs.next())
		{
			int M_ProductBOM_ID = rs.getInt(1);
			BigDecimal bomQty = rs.getBigDecimal(2);
			isBOM = "Y".equals(rs.getString(3));
			isStocked = "Y".equals(rs.getString(4)); 
			ProductType = rs.getString(5);
			
			//	Stocked Items "leaf node"
			if (ProductType.equals("I") && isStocked)
			{
				//	Get ProductQty
				productQuantity = getStorageQtyASI(M_ProductBOM_ID, p_M_AttributeSetInstance_ID, p_M_Warehouse_ID, p_M_Locator_ID, p_what);
				//	Get Rounding Precision
				int StdPrecision = getUOMPrecision(M_ProductBOM_ID);
				//	How much can we make with this product
				productQuantity = productQuantity.setScale(StdPrecision, BigDecimal.ROUND_HALF_UP)
					.divide(bomQty, BigDecimal.ROUND_HALF_UP);
				//	How much can we make overall
				if (productQuantity.compareTo(quantity) < 0)
					quantity = productQuantity;
			}
			else if (isBOM)	//	Another BOM
			{
				productQuantity = bomQtyASI (M_ProductBOM_ID, p_M_AttributeSetInstance_ID, p_M_Warehouse_ID, p_M_Locator_ID, p_what);
				//	How much can we make overall
				if (productQuantity.compareTo(quantity) < 0)
					quantity = productQuantity;
			}
		}
		rs.close();
		pstmt.close();
		
		if (quantity.signum() > 0)
		{
			int StdPrecision = getUOMPrecision(p_M_Product_ID);
			return quantity.setScale(StdPrecision, BigDecimal.ROUND_HALF_UP);
		}
		return Adempiere.ZERO;
	}	//	bomQtyOnHand
	
	/**
	 * 	Get Storage Qty
	 *	@param p_M_Product_ID product
	 *	@param M_Warehouse_ID warehouse
	 *	@param p_M_Locator_ID locator
	 *	@param p_what variable name
	 *	@return quantity or zero
	 *	@throws SQLException
	 */
	static BigDecimal getStorageQtyASI (int p_M_Product_ID, int p_M_AttributeSetInstance_ID,
			int M_Warehouse_ID, int p_M_Locator_ID, String p_what)
			throws SQLException
		{
			BigDecimal quantity = null;

			String sql = "SELECT SUM(" + p_what + ") "
						+ "FROM M_Storage s "
						+ "WHERE M_Product_ID=?";
			if(p_M_AttributeSetInstance_ID != 0) {
				sql +=" AND s.M_AttributeSetInstance_ID = ?";
			}
			if (p_M_Locator_ID != 0) {
				sql += " AND s.M_Locator_ID=?";
			}
			else if(M_Warehouse_ID != 0) {
				sql += " AND EXISTS (SELECT * FROM M_Locator l WHERE s.M_Locator_ID=l.M_Locator_ID"
					+ " AND l.M_Warehouse_ID=?)";
			}
			
			int index=1;
			PreparedStatement pstmt = Adempiere.prepareStatement(sql);
			pstmt.setInt(index++, p_M_Product_ID);
			if(p_M_AttributeSetInstance_ID != 0) {
				pstmt.setInt(index++, p_M_AttributeSetInstance_ID);
			}
			if (p_M_Locator_ID != 0) {
				pstmt.setInt(index++, p_M_Locator_ID);
			}
			else if(M_Warehouse_ID != 0) {
				pstmt.setInt(index++, M_Warehouse_ID);
			}
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
				quantity = rs.getBigDecimal(1);
			rs.close();
			pstmt.close();
			//	Not found
			if (quantity == null)
				return Adempiere.ZERO;
			return quantity;
		}	//	getStorageQty	
	
}	//	Product
