/** Copyright (C) 2003-2026, e-Evolution Consultants S.A. , http://www.e-evolution.com
  * This program is free software: you can redistribute it and/or modify
  * it under the terms of the GNU Affero General Public License as published by
  * the Free Software Foundation, either version 3 of the License, or
  * (at your option) any later version.
  * This program is distributed in the hope that it will be useful,
  * but WITHOUT ANY WARRANTY; without even the implied warranty of
  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  * GNU Affero General Public License for more details.
  * You should have received a copy of the GNU Affero General Public License
  * along with this program.  If not, see <http://www.gnu.org/licenses/>.
  * Email: victor.perez@e-evolution.com, http://www.e-evolution.com , http://github.com/e-Evolution
  * Created by victor.perez@e-evolution.com , www.e-evolution.com
  */

package org.eevolution.test

import org.compiere.model.{MLookupFactory, MQuery}
import org.compiere.util.{DB, Env}
import org.scalatest.GivenWhenThen
import org.scalatest.featurespec.AnyFeatureSpec

/** The zoom query of a lookup is built by `MLookupFactory` from the where clause of the table reference: one raw
  * restriction, never structured values. The lookup callers (`VLookup`, `WSearchEditor`, `ADLookup`) and the Info
  * windows merge that text with the validation and the context, so they have nothing to bind and keep the literal
  * text. The servlet and mobile `WWindow` append `Value LIKE ?` to it and run `DB.getSQLValueStringQuery`, which
  * binds the values of a structured zoom query before the key.
  *
  * The reference of every check is the statement of before: the same restriction as literal text.
  */
class ValidateLookupBindParameters extends AnyFeatureSpec with AdempiereTestCase with GivenWhenThen {

  private def check(condition: Boolean, message: => String = "assertion failed"): Unit =
    if (!condition) fail(message)

  /** The statement of `WWindow.lookupValue`, with the restriction as the reference of before: literal text */
  private def literalValue(query: MQuery, keyColumn: String, key: String): String =
    DB.getSQLValueString(null, "SELECT " + keyColumn + " FROM " + query.getTableName + " WHERE " + query.getWhereClause + " AND Value LIKE ?", key)

  /** The statement of `WWindow.lookupValue` after: the values of the restriction first, then the key */
  private def boundValue(query: MQuery, keyColumn: String, key: String): String = {
    val bound = query.getBoundWhereClause(false)
    val parameters = new java.util.ArrayList[AnyRef](bound.getParameters)
    parameters.add(key)
    DB.getSQLValueStringQuery(null, "SELECT " + keyColumn + " FROM " + query.getTableName + " WHERE " + bound.getSql + " AND Value LIKE ?", parameters)
  }

  Feature("The zoom query of a lookup is raw text") {

    Scenario("the table references with a where clause produce one raw restriction, without parameters") {
      var compared = 0
      val columnIds = DB.getIDsEx(null,
        "SELECT c.AD_Column_ID FROM AD_Column c INNER JOIN AD_Ref_Table rt ON rt.AD_Reference_ID=c.AD_Reference_Value_ID " +
        "WHERE c.AD_Reference_ID=18 AND rt.WhereClause IS NOT NULL AND POSITION('@' IN rt.WhereClause)=0 ORDER BY c.AD_Column_ID").take(30)
      columnIds.foreach { columnId =>
        val info = MLookupFactory.getLookupInfo(Env.getCtx, 0, columnId, 18)
        if (info != null && info.ZoomQuery != null) {
          val bound = info.ZoomQuery.getBoundWhereClause(false)
          Then(s"column $columnId: no parameter and the literal text")
          check(bound.getParameters.isEmpty, s"column $columnId: ${bound.getParameters}")
          check(bound.getSql == info.ZoomQuery.getWhereClause(false), s"column $columnId: ${bound.getSql} / ${info.ZoomQuery.getWhereClause(false)}")
          compared += 1
        }
      }
      check(compared > 0, "the dictionary has no table reference with a where clause")
    }
  }

  /** The zoom query of a lookup: the table is set, as `MLookupFactory` does */
  private def equalQuery(columnName: String, value: AnyRef): MQuery = {
    val query = new MQuery("C_BPartner")
    query.addRestriction(columnName, MQuery.EQUAL, value)
    query
  }

  Feature("WWindow.lookupValue binds the values of the zoom query before the key") {

    Scenario("a raw restriction gives the row of the literal statement") {
      val query = new MQuery("C_BPartner")
      query.addRestriction("C_BPartner.IsActive='Y'")
      val value = DB.getSQLValueString(null, "SELECT MIN(Value) FROM C_BPartner WHERE IsActive='Y'")
      check(value != null)
      val literal = literalValue(query, "C_BPartner_ID", value)
      check(literal != null, "the literal reference finds nothing")
      check(boundValue(query, "C_BPartner_ID", value) == literal)
      check(boundValue(query, "C_BPartner_ID", "no such value") == null)
    }

    Scenario("a structured restriction keeps its values in front of the key, with an Integer and with a String (R3)") {
      val partnerId = DB.getSQLValue(null, "SELECT MIN(C_BPartner_ID) FROM C_BPartner")
      val value = DB.getSQLValueString(null, "SELECT Value FROM C_BPartner WHERE C_BPartner_ID=?", partnerId)
      Seq(("Integer", equalQuery("C_BPartner_ID", Integer.valueOf(partnerId))),
          ("String", equalQuery("C_BPartner_ID", partnerId.toString))).foreach { case (label, query) =>
        val bound = query.getBoundWhereClause(false)
        check(bound.getParameters.size == 1, s"$label: ${bound.getParameters}")
        val literal = literalValue(query, "C_BPartner_ID", value)
        check(literal == partnerId.toString, s"$label: literal $literal")
        check(boundValue(query, "C_BPartner_ID", value) == literal, s"$label: bound ${boundValue(query, "C_BPartner_ID", value)}")
      }
      And("a key that is not the one of the restriction finds nothing, so the order of the values matters")
      val other = DB.getSQLValueString(null, "SELECT MAX(Value) FROM C_BPartner WHERE C_BPartner_ID<>?", partnerId)
      check(boundValue(equalQuery("C_BPartner_ID", Integer.valueOf(partnerId)), "C_BPartner_ID", other) == null)
    }
  }
}
