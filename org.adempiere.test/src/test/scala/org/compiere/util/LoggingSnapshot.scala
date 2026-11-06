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

package org.compiere.util

import java.util.logging.{Handler, Level, LogManager, Logger}
import scala.collection.mutable

/**
  * Snapshot of the logging state a test suite is about to silence.
  *
  * Adempiere.startup applies the configured Ini.P_TRACELEVEL, so the level a
  * suite finds on entry is not a fixed SEVERE. A suite that silences logging
  * must give back the exact levels it found, handler by handler and logger by
  * logger; a guessed level would follow every later suite in the same JVM.
  *
  * Lives in this package because CLogMgt.getHandlers is protected.
  */
object LoggingSnapshot {

  /** Captured levels of the CLogMgt handlers and of every registered logger. */
  final class Snapshot private[util] (
      globalLevel: Level,
      handlerLevels: Map[Handler, Level],
      loggerLevels: Map[String, Level]) {

    /** Restore each handler and logger to exactly the captured level. */
    def restore(): Unit = {
      //	The aggregate first, so CLogMgt is not left silenced; then the exact
      //	per-handler and per-logger values, which may differ from it.
      CLogMgt.setLevel(globalLevel)
      handlerLevels.foreach { case (handler, level) => handler.setLevel(level) }
      loggerLevels.foreach { case (name, level) => Logger.getLogger(name).setLevel(level) }
    }
  }

  /** The CLogMgt handlers, so a spec can assert on them (getHandlers is protected). */
  def handlers: Array[Handler] = CLogMgt.getHandlers

  /** Capture the current level of every CLogMgt handler and registered logger. */
  def capture(): Snapshot = {
    val handlerLevels = CLogMgt.getHandlers
      .map(handler => handler -> handler.getLevel).toMap
    val loggerLevels = mutable.LinkedHashMap[String, Level]()
    //	A captured null means "inherit from the parent logger", and has to come
    //	back as null rather than as some concrete level.
    val names = LogManager.getLogManager.getLoggerNames
    while (names.hasMoreElements) {
      val name = names.nextElement()
      val logger = Logger.getLogger(name)
      if (logger != null)
        loggerLevels.put(name, logger.getLevel)
    }
    new Snapshot(CLogMgt.getLevel, handlerLevels, loggerLevels.toMap)
  }

  /** Silence every CLogMgt handler and every registered logger. */
  def silence(): Unit = {
    CLogMgt.setLevel(Level.OFF)
    CLogMgt.setLoggerLevel(Level.OFF, null)
  }
}
