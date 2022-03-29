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

import org.compiere.model.*

import org.eevolution.dsl
import org.eevolution.dsl.*

/***
 * Tax Service
 *  eEvolution author Victor Perez <victor.perez@e-evolution.com>, Created by e-Evolution on 01/03/16
 */
trait TaxService {
  def getTaxByName (name : String) (implicit context : Context, transaction : Transaction) : dsl.Tax = {
    val whereClause = new StringBuilder()
    whereClause.append(I_C_Tax.COLUMNNAME_Name).append("=?")
    val tax : dsl.Tax= new Query(context, I_C_Tax.Table_Name, whereClause.toString(), transaction.getTrxName)
        .setClient_ID()
      .setParameters(name)
      .first()
    tax
  }

  def getTaxCategoryByName (name : String) (implicit context : Context, transaction : Transaction) : dsl.TaxCategory = {
    val whereClause = new StringBuilder()
    whereClause.append(I_C_TaxCategory.COLUMNNAME_Name).append("=?")
    val taxCategory : dsl.TaxCategory = new Query(context, I_C_TaxCategory.Table_Name, whereClause.toString(), transaction.getTrxName)
        .setClient_ID()
      .setParameters(name)
      .first()
    taxCategory
  }
}
