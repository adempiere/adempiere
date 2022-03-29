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

import org.compiere.model.*
import org.eevolution.dsl.*

/**
  * Business Partner Service
 *  eEvolution author Victor Perez <victor.perez@e-evolution.com>, Created by e-Evolution on 02/03/13.
 */
trait PartnerService {

   def getPartnerByValue (value : String) (implicit context : Context, transaction : Transaction) : Partner = {
     val whereClause: StringBuilder = new StringBuilder()
     val parameters:  List[Object] with Object = new ArrayList[Object]()

     if (value != null) {
       whereClause.append(I_C_BPartner.COLUMNNAME_Value).append("=?")
       parameters.add(value)
     }

     val partner : Partner =  new Query(context, I_C_BPartner.Table_Name, whereClause.toString(), transaction.getTrxName)
       .setClient_ID()
       .setParameters(parameters).first()
     partner
   }
}
