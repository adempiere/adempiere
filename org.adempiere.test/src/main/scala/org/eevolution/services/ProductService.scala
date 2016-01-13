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

package org.eevolution.service

import java.util.{ArrayList, List}

import org.compiere.model.{I_M_Product, Query}
import org.compiere.util.Env

/**
 *  eEvolution author Victor Perez <victor.perez@e-evolution.com>, Created by e-Evolution on 14/12/13.
 */
trait ProductService {

   def getProduct (value : String, trxName : String) : Product = {
     val whereClause: StringBuilder = new StringBuilder("IsStocked='Y' AND ProductType='I'")
     val parameters:  List[Object] with Object = new ArrayList[Object]()

     if (value != null) {
       whereClause.append(" AND ").append(I_M_Product.COLUMNNAME_Value).append("=?")
       parameters.add(value)
     }

     val product : Product =  new Query(Env.getCtx(), I_M_Product.Table_Name, whereClause.toString(), trxName)
       .setClient_ID()
       .setParameters(parameters).first()
     product
   }
}
