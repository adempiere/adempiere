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
package org.compiere.wstore;

import java.math.BigDecimal;

import org.compiere.util.Env;

/**
 *  Web Basket Line
 *
 *  @author Jorg Janke
 *  @version $Id: WebBasketLine.java,v 1.2 2006/07/30 00:53:21 jjanke Exp $
 */
public class WebBasketLine
{
	/**
	 * 	Web Basket Line
	 * 	@param M_Product_ID product
	 * 	@param Name Name
	 *	@param Qty Qty
	 * 	@param Price Price
	 */
	public WebBasketLine (int M_Product_ID, String Name, BigDecimal Qty, BigDecimal Price)
	{
		setM_Product_ID (M_Product_ID);
		setName (Name);
		setQuantity (Qty);
		setPrice (Price);
	}	//	WebBasketLine

	private int			m_line;
	private int 		m_M_Product_ID;
	private String 		m_Name;
	private BigDecimal 	m_Price;
	private BigDecimal 	m_Quantity;
	private BigDecimal 	m_Total;

	/**
	 * 	Extended String Representation
	 * 	@return info
	 */
	public String toStringX()
	{
		StringBuffer sb = new StringBuffer("WebBasketLine[");
		sb.append(m_line).append("-M_Product_ID=") .append(m_M_Product_ID)
			.append(",Qty=").append(m_Quantity).append(",Price=").append(m_Price)
			.append(",Total=").append(getTotal())
			.append("]");
		return sb.toString();
	}	//	toString

	/**
	 * 	Extended String Representation
	 * 	@return info
	 */
	public String toString()
	{
		StringBuffer sb = new StringBuffer();
		sb.append(m_Quantity).append(" * ").append(m_Name)
			.append(" = ").append(getTotal());
		return sb.toString();
	}	//	toString


	/**
	 * 	Get Line number
	 *	@return line no
	 */
	public int getLine()
	{
		return m_line;
	}	//	getLine
	
	/**
	 * 	Set Line number
	 *	@param line no
	 */
	protected void setLine (int line)
	{
		m_line = line;
	}	//	setLine


	/**
	 * 	Get M_Product_ID product
	 *	@return product
	 */
	public int getM_Product_ID()
	{
		return m_M_Product_ID;
	}	//	getM_Product_ID
	
	/**
	 * 	Set M_Product_ID
	 *	@param M_Product_ID id
	 */
	protected void setM_Product_ID (int M_Product_ID)
	{
		m_M_Product_ID = M_Product_ID;
	}	//	setM_Product_ID


	/**
	 * 	Get Name
	 *	@return name or -?-
	 */
	public String getName()
	{
		if (m_Name == null)
			return "-?-";
		return m_Name;
	}	//	getName
	
	/**
	 * 	Set Product Name
	 *	@param name
	 */
	protected void setName (String name)
	{
		m_Name = name;
	}	//	setName


	/**
	 * 	Get Price
	 *	@return price
	 */
	public BigDecimal getPrice()
	{
		if (m_Price == null)
			return Env.ZERO;
		return m_Price;
	}	//	getPrice
	
	/**
	 * 	Set Price
	 *	@param price
	 */
	protected void setPrice (BigDecimal price)
	{
		if (price == null)
			m_Price = Env.ZERO;
		else
			m_Price = price;
		m_Total = null;
	}	//	setPrice


	/**
	 * 	Get Quantity
	 *	@return quantity
	 */
	public BigDecimal getQuantity()
	{
		if (m_Quantity == null)
			return Env.ZERO;
		return m_Quantity;
	}	//	getQuantity
	
	/**
	 * 	Set Quantity
	 *	@param quantity quantity
	 */
	public void setQuantity (BigDecimal quantity)
	{
		if (quantity == null)
			m_Quantity = Env.ZERO;
		else	
			m_Quantity = quantity;
		m_Total = null;
	}	//	setQuantity

	/**
	 * 	Add Quantity
	 *	@param addedQuantity
	 *	@return new quantity
	 */
	public BigDecimal addQuantity (BigDecimal addedQuantity)
	{
		if (addedQuantity == null)
			return getQuantity();
		//
		m_Quantity = getQuantity();
		m_Quantity = m_Quantity.add(addedQuantity);
		m_Total = null;
		return m_Quantity;		
	}	//	addQuantity


	/**
	 * 	Get Total (calculate)
	 *	@return total price
	 */
	public BigDecimal getTotal()
	{
		if (m_Total == null)
			m_Total = getQuantity().multiply(getPrice());
		return m_Total;
	}	//	getTotal

}	//	WebBasketLine
