/** ****************************************************************************
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
 * Copyright (C) 2003-2016 e-Evolution,SC. All Rights Reserved.               *
 * Contributor(s): Victor Perez www.e-evolution.com                           *
 * *************************************************************************** */

package org.eevolution.services

import java.util.{ArrayList, List}

import org.compiere.model.{I_M_ProductPrice, I_M_PriceList_Version, I_M_PriceList, Query}
import org.eevolution.dsl.*

/**
 * Price List Service
 * eEvolution author Victor Perez <victor.perez@e-evolution.com>, Created by e-Evolution on 27/02/16.
 */
trait PriceListService{

  def getPriceListByName(name: String)(implicit context: Context, transaction: Transaction): PriceList = {
    val whereClause: StringBuilder = new StringBuilder()
    val parameters: List[Object] with Object = new ArrayList[Object]()

    if (name != null) {
      whereClause.append(I_M_PriceList.COLUMNNAME_Name).append("=?")
      parameters.add(name)
    }

    val priceList: PriceList = new Query(context, I_M_PriceList.Table_Name, whereClause.toString(), transaction.getTrxName)
      .setClient_ID()
      .setParameters(parameters).first()
    priceList
  }

  def getListPriceByDefault(isSOTrx: Boolean)(implicit context: Context, transaction: Transaction): PriceList = {
    val whereClause: StringBuilder = new StringBuilder()
    whereClause.append(I_M_PriceList.COLUMNNAME_IsSOPriceList).append("=?")
    whereClause.append(" AND ")
    whereClause.append(I_M_PriceList.COLUMNNAME_IsDefault).append("=?")
    val priceList: PriceList = new Query(context, I_M_PriceList.Table_Name, whereClause.toString(), transaction.getTrxName)
      .setClient_ID()
      .setParameters(isSOTrx.asInstanceOf[java.lang.Boolean], true.asInstanceOf[java.lang.Boolean]).first()
    priceList
  }

  def getProductPrice(priceListVersionId: Integer, productId: Integer)(implicit context: Context, transaction: Transaction): ProductPrice = {

    val whereClause: StringBuilder = new StringBuilder()
    whereClause.append(I_M_ProductPrice.COLUMNNAME_M_PriceList_Version_ID).append("=?")
    whereClause.append(" AND ")
    whereClause.append(I_M_ProductPrice.COLUMNNAME_M_Product_ID).append("=?")
    val productPrice: ProductPrice = new Query(context, I_M_ProductPrice.Table_Name, whereClause.toString(), transaction.getTrxName)
      .setClient_ID()
      .setParameters(priceListVersionId, productId).first()
    productPrice
  }
}

