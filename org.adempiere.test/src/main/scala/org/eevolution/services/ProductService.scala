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
  * ****************************************************************************/

package org.eevolution.services

import java.util.{ArrayList, List}
import org.compiere.model.{I_C_UOM, I_M_Product, I_M_Product_Category, Query}
import org.eevolution.dsl.*

import java.util

/**
 * Product Service
 *  eEvolution author Victor Perez <victor.perez@e-evolution.com>, Created by e-Evolution on 14/12/13.
 */
trait ProductService {

   def getProductByValue (value : String) (implicit context : Context, transaction : Transaction) : Product = {
     val whereClause: StringBuilder = new StringBuilder("IsStocked='Y' AND ProductType='I'")
     val parameters:  util.List[Object] with Object = new util.ArrayList[Object]()

     if (value != null) {
       whereClause.append(" AND ").append(I_M_Product.COLUMNNAME_Value).append("=?")
       parameters.add(value)
     }

     val product : Product =  new Query(context, I_M_Product.Table_Name, whereClause.toString(), transaction.getTrxName)
       .setClient_ID()
       .setParameters(parameters).first()
     product
   }

    def getProductCategoryByValue (value : String) (implicit context : Context, transaction : Transaction) : ProductCategory = {
      val whereClause: StringBuilder = new StringBuilder()
      val parameters:  util.List[Object] with Object = new util.ArrayList[Object]()

      if (value != null) {
        whereClause.append(I_M_Product_Category.COLUMNNAME_Value).append("=?")
        parameters.add(value)
      }

      val product : ProductCategory =  new Query(context, I_M_Product_Category.Table_Name, whereClause.toString(), transaction.getTrxName)
        .setClient_ID()
        .setParameters(parameters).first()
      product
    }

    def getUOMByCode(symbol : String)(implicit context : Context, transaction : Transaction) : UOM = {
      val whereClause: StringBuilder = new StringBuilder()
        whereClause.append(I_C_UOM.COLUMNNAME_UOMSymbol).append("=?")
      val uom : UOM =  new Query(context, I_C_UOM.Table_Name, whereClause.toString(), transaction.getTrxName)
        .setParameters(symbol).first()
      uom
    }
}
