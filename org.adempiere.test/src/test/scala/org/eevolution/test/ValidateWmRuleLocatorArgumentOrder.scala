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

import org.adempiere.core.domains.models.X_WM_Rule
import org.compiere.model.{MLocator, MWarehouse}
import org.eevolution.services.ProductService
import org.eevolution.wms.engine.WMRuleEngine
import org.eevolution.wms.model.{MWMArea, MWMRule, MWMSection, MWMSectionDetail, MWMStrategy, MWMStrategyDetail}
import org.scalatest.GivenWhenThen
import org.scalatest.featurespec.AnyFeatureSpec

/**
  * Regression test for issue #4436: WMRuleEngine.getLocator() must pass the
  * strategy warehouse ID as warehouseId and the selected product ID as
  * productId to the WM rule implementation. With the swapped arguments the
  * rule searched areas by the product ID and returned no locators.
  */
class ValidateWmRuleLocatorArgumentOrder extends AnyFeatureSpec
  with AdempiereTestCase
  with ProductService
  with GivenWhenThen {

  Feature("WMS rule locator argument order") {
    info("Issue #4436: the rule must receive the strategy warehouse ID, not the product ID")

    Scenario("The locator rule searches the strategy warehouse, not the product") {
      Given("a warehouse with an area, a section and a locator wired through a section detail")
      val warehouse = new MWarehouse(Context, 0, trxName)
      warehouse.setName("WMS Locator Rule Test Warehouse")
      warehouse.save()

      val locator = new MLocator(Context, 0, trxName)
      locator.setValue("WMS Locator Rule Test")
      locator.setM_Warehouse_ID(warehouse.getM_Warehouse_ID)
      locator.save()

      val area = new MWMArea(Context, 0, trxName)
      area.setName("WMS Locator Rule Test Area")
      area.setM_Warehouse_ID(warehouse.getM_Warehouse_ID)
      area.save()

      val section = new MWMSection(Context, 0, trxName)
      section.setName("WMS Locator Rule Test Section")
      section.setWM_Area_ID(area.getWM_Area_ID)
      section.save()

      val sectionDetail = new MWMSectionDetail(Context, 0, trxName)
      sectionDetail.setWM_Section_ID(section.getWM_Section_ID)
      sectionDetail.setM_Locator_ID(locator.getM_Locator_ID)
      sectionDetail.save()

      Given("a strategy bound to the warehouse with the FIFO rule")
      val rule = new MWMRule(Context, 0, trxName)
      rule.setName("WMS Locator Rule Test FIFO")
      rule.setInOutBoundRule(X_WM_Rule.INOUTBOUNDRULE_ForTheMaterialOldestUsingFIFO)
      rule.save()

      val strategy = new MWMStrategy(Context, 0, trxName)
      strategy.setName("WMS Locator Rule Test Strategy")
      strategy.setM_Warehouse_ID(warehouse.getM_Warehouse_ID)
      strategy.save()

      val strategyDetail = new MWMStrategyDetail(Context, 0, trxName)
      strategyDetail.setWM_Strategy_ID(strategy.getWM_Strategy_ID)
      strategyDetail.setWM_Rule_ID(rule.getWM_Rule_ID)
      strategyDetail.save()

      When("getLocator is called for a product different from the warehouse")
      val product = getProductByValue("Oak")
      assert(product != null)
      assert(product.getM_Product_ID != warehouse.getM_Warehouse_ID)

      val locators = WMRuleEngine.getLocator(strategy, product.getM_Product_ID, 0, 0)

      Then("the rule finds the locators of the strategy warehouse")
      assert(!locators.isEmpty)
      var found = false
      locators.forEach(l => if (l.getM_Locator_ID == locator.getM_Locator_ID) found = true)
      assert(found)

      And("no locator from another warehouse is returned")
      locators.forEach(l => assert(l.getM_Warehouse_ID == warehouse.getM_Warehouse_ID))
    }
  }
}
