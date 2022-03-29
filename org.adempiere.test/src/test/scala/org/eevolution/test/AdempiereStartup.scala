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

package org.eevolution.test

import java.util.logging.Level

import org.compiere.Adempiere
import org.compiere.util.*
/**
  * Trait use to starting Adempiere for test
  * eEvolution author Victor Perez <victor.perez@e-evolution.com>, Created by e-Evolution on 05/01/16.
  */
trait AdempiereStartup {

  def startup(): Unit = {
    Adempiere.startup(false)
    CLogMgt.setLevel(Level.OFF)
    CLogMgt.setLoggerLevel(Level.OFF,null)

    //Ini.setProperty(Ini.P_PWD,"System")
    //Ini.setProperty(Ini.P_CLIENT, "System")
    //Ini.setProperty(Ini.P_ORG,"*");
    //Ini.setProperty(Ini.P_WAREHOUSE," ")
    //Ini.setProperty(Ini.P_ORG, "HQ")
    //Ini.setProperty(Ini.P_WAREHOUSE, "0")

    Ini.setProperty(Ini.P_UID,"SuperUser")
    Ini.setProperty(Ini.P_PWD,"System")
    Ini.setProperty(Ini.P_ROLE,"GardenWorld Admin")
    Ini.setProperty(Ini.P_CLIENT, "GardenWorld")
    Ini.setProperty(Ini.P_ORG,"HQ");
    Ini.setProperty(Ini.P_WAREHOUSE,"HQ Warehouse")
    Ini.setProperty(Ini.P_LANGUAGE,"English")
    val login = new Login(Env.getCtx)
    if (!login.batchLogin(null))
      System.exit(1)
    CLogMgt.setLoggerLevel(Level.SEVERE, null)
    CLogMgt.setLevel(Level.SEVERE)
    //organization =
  }
}
