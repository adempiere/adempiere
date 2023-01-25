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

/** Currency Service
  *  eEvolution author Victor Perez <victor.perez@e-evolution.com>, Created by e-Evolution on 29/02/16
  */
trait CurrencyService {
  def getCurrencyByCode(
      code: String
  )(implicit context: Context, transaction: Transaction): Currency = {
    val whereClause = new StringBuilder()
    whereClause.append(I_C_Currency.COLUMNNAME_ISO_Code).append("=?")
    val currency: Currency = new Query(
      context,
      I_C_Currency.Table_Name,
      whereClause.toString(),
      transaction.getTrxName
    )
      .setParameters(code)
      .first()
    currency
  }
}
