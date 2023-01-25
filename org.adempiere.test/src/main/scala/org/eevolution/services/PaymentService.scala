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
  * ***************************************************************************
  */

package org.eevolution.services

import org.adempiere.core.domains.models.*
import org.compiere.model.*
import org.eevolution.dsl.*
import scala.jdk.CollectionConverters.*

/**  Payment Service
  *  eEvolution author Victor Perez <victor.perez@e-evolution.com>, Created by e-Evolution on 15/01/16.
  */
trait PaymentService {

  def getOrderPayments(order: Order): List[Payment] = {
    val whereClause = new StringBuilder()
    whereClause.append(I_C_Payment.COLUMNNAME_C_Order_ID).append("=?")
    val payments = new Query(
      order.getCtx(),
      I_C_Payment.Table_Name,
      whereClause.toString(),
      order.get_TrxName()
    )
      .setClient_ID()
      .setParameters(order.get_ID().asInstanceOf[Object])
      .list()
      .asScala
      .toList
    payments
  }
}
