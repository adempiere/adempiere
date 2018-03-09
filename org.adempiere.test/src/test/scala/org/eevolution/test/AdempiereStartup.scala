package org.eevolution.test

import java.util.logging.Level

import org.compiere.Adempiere
import org.compiere.util.{CLogMgt, Env, Ini, Login}

/**
  * Trait use to starting Adempiere for test
  * eEvolution author Victor Perez <victor.perez@e-evolution.com>, Created by e-Evolution on 05/01/16.
  */
trait AdempiereStartup {

  def startup
  {
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
    val login = new Login(Env.getCtx())
    if (!login.batchLogin(null))
      System.exit(1)
    CLogMgt.setLoggerLevel(Level.SEVERE, null)
    CLogMgt.setLevel(Level.SEVERE)
    //organization =
  }
}
