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

import org.compiere.util.CLogger;

/**
 *  Price List Product
 *
 *  @author Jorg Janke
 *  @version $Id: PriceListProduct.java,v 1.2 2006/07/30 00:53:21 jjanke Exp $
 */
public class PriceListProduct
{
	/**
	 * 	Price List Product.
	 * 	@param M_Product_ID product
	 * 	@param value value
	 * 	@param name name
	 * 	@param description descriprion
	 * 	@param help help
	 * 	@param documentNote document note
	 * 	@param imageURL image
	 * 	@param descriptionURL description
	 * 	@param price price
	 * 	@param uomName uom
	 * 	@param uomSymbol uom
	 */
	public PriceListProduct (int M_Product_ID, String value, String name, String description,
		String help, String documentNote, String imageURL, String descriptionURL,
		BigDecimal price, String uomName, String uomSymbol)
	{
		//
		m_Product_ID = M_Product_ID;
		m_value = value;
		m_name = name;
		m_description = description;
		//	Help, DocumentNote, ImageURL, DescriptionURL,
		m_help = help;
		m_documentNote = documentNote;
		m_imageURL = imageURL;
		m_descriptionURL = descriptionURL;
		//	PriceStd, UOMName, UOMSymbol
		m_price = price;
		m_uomName = uomName;
		m_uomSymbol = uomSymbol;
	}	//	PriceListProduct

	/**	Attribute Name				*/
	public static final String		NAME = "PriceListProduct";
	/**	Logging						*/
	private CLogger			log = CLogger.getCLogger(getClass());

	private int 			m_Product_ID;
	private String 			m_value;
	private String 			m_name;
	private String 			m_description;

	private String 			m_help;
	private String 			m_documentNote;
	private String 			m_imageURL;
	private String 			m_descriptionURL;

	private BigDecimal		m_price;
	private String			m_uomName;
	private String			m_uomSymbol;


	/**
	 * 	String Representation
	 * 	@return info
	 */
	public String toString()
	{
		StringBuffer sb = new StringBuffer("PriceListProduct[");
		sb.append(m_Product_ID).append("-").append(m_name)
			.append("-").append(m_price)
			.append("]");
		return sb.toString();
	}	//	toString

	/*************************************************************************/

	/**
	 * 	Get Product IO
	 * 	@return	M_Product_ID
	 */
	public int getId()
	{
		return m_Product_ID;
	}
	public String getValue()
	{
		return m_value;
	}
	/**
	 * 	Get Name
	 * 	@return name
	 */
	public String getName()
	{
		return m_name;
	}
	public String getDescription()
	{
		return m_description;
	}
	public String getHelp()
	{
		return m_help;
	}

	public String getDocumentNote()
	{
		return m_documentNote;
	}
	public String getImageURL()
	{
		return m_imageURL;
	}
	public String getDescriptionURL()
	{
		return m_descriptionURL;
	}

	public BigDecimal getPrice()
	{
		return m_price;
	}
	public String getUomName()
	{
		return m_uomName;
	}
	public String getUomSymbol()
	{
		return m_uomSymbol;
	}

}	//	PriceListProduct
