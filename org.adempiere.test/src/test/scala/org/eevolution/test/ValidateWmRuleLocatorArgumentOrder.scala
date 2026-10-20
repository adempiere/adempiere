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

import org.adempiere.core.domains.models.X_WM_Area_Type
import org.adempiere.core.domains.models.X_WM_Rule
import org.adempiere.core.domains.models.X_WM_Section_Type
import org.adempiere.core.domains.models.X_WM_Strategy
import org.eevolution.dsl.*
import org.eevolution.services.ProductService
import org.eevolution.wms.engine.WMRuleEngine
import org.scalatest.GivenWhenThen
import org.scalatest.featurespec.AnyFeatureSpec

/**
  * Regression test for issue #4436: WMRuleEngine.getLocator() must pass the
  * strategy warehouse ID as warehouseId and the selected product ID as
  * productId to the WM rule implementation. With the swapped arguments the
  * rule searched areas by the product ID and returned no locators.
  *
  * Written against the DSL types (org.eevolution.dsl) rather than the
  * underlying model implementations, so a model class swap only touches
  * the DSL, not the tests.
  *
  * Every save() is asserted: InOutBoundType is mandatory in AD_Column for both
  * WM_Rule and WM_Strategy, and the generated constructors leave it null, so an
  * unasserted save fails silently and the engine later reports the confusing
  * "null is not implement" instead of the real cause.
  */
class ValidateWmRuleLocatorArgumentOrder extends AnyFeatureSpec
  with AdempiereTestCase
  with ProductService
  with GivenWhenThen {

  Feature("WMS rule locator argument order") {
    info("Issue #4436: the rule must receive the strategy warehouse ID, not the product ID")

    Scenario("The locator rule searches the strategy warehouse, not the product") {
      Given("a warehouse with an area, a section and a locator wired through a section detail")
      // M_Warehouse.C_Location_ID is NOT NULL without a default, and the generated
      // constructor leaves it null: reuse the location of the login warehouse.
      val contextWarehouse = Warehouse
      val warehouse = new Warehouse(Context, 0, trxName)
      warehouse.setName("WMS Locator Rule Test Warehouse")
      warehouse.setC_Location_ID(contextWarehouse.getC_Location_ID)
      assert(warehouse.save(), "unable to save the test warehouse")

      val locator = new Locator(Context, 0, trxName)
      locator.setValue("WMS Locator Rule Test")
      locator.setM_Warehouse_ID(warehouse.getM_Warehouse_ID)
      assert(locator.save(), "unable to save the test locator")

      val area = new WarehouseArea(Context, 0, trxName)
      area.setName("WMS Locator Rule Test Area")
      area.setM_Warehouse_ID(warehouse.getM_Warehouse_ID)
      // WM_Area.WM_Area_Type_ID and WM_Section.WM_Section_Type_ID are NOT NULL
      // without a default; the area/section type tables are empty in a fresh
      // installation, so the spec creates its own types.
      val areaType = new X_WM_Area_Type(Context, 0, trxName)
      areaType.setName("WMS Locator Rule Test Area Type")
      assert(areaType.save(), "unable to save the test area type")
      area.setWM_Area_Type_ID(areaType.getWM_Area_Type_ID)
      assert(area.save(), "unable to save the test area")

      val sectionType = new X_WM_Section_Type(Context, 0, trxName)
      sectionType.setName("WMS Locator Rule Test Section Type")
      sectionType.setInOutBoundType(X_WM_Section_Type.INOUTBOUNDTYPE_OutboundOperation)
      assert(sectionType.save(), "unable to save the test section type")

      val section = new WarehouseSection(Context, 0, trxName)
      section.setName("WMS Locator Rule Test Section")
      section.setWM_Area_ID(area.getWM_Area_ID)
      section.setWM_Section_Type_ID(sectionType.getWM_Section_Type_ID)
      assert(section.save(), "unable to save the test section")

      val sectionDetail = new WarehouseSectionDetail(Context, 0, trxName)
      sectionDetail.setWM_Section_ID(section.getWM_Section_ID)
      sectionDetail.setM_Locator_ID(locator.getM_Locator_ID)
      assert(sectionDetail.save(), "unable to save the test section detail")

      Given("a strategy bound to the warehouse with the FIFO rule")
      val rule = new WarehouseRule(Context, 0, trxName)
      rule.setName("WMS Locator Rule Test FIFO")
      rule.setInOutBoundRule(X_WM_Rule.INOUTBOUNDRULE_ForTheMaterialOldestUsingFIFO)
      rule.setInOutBoundType(X_WM_Rule.INOUTBOUNDTYPE_OutboundOperation)
      assert(rule.save(), "unable to save the test rule")

      val strategy = new WarehouseStrategy(Context, 0, trxName)
      strategy.setName("WMS Locator Rule Test Strategy")
      strategy.setM_Warehouse_ID(warehouse.getM_Warehouse_ID)
      strategy.setInOutBoundType(X_WM_Strategy.INOUTBOUNDTYPE_OutboundOperation)
      assert(strategy.save(), "unable to save the test strategy")

      val strategyDetail = new WarehouseStrategyDetail(Context, 0, trxName)
      strategyDetail.setWM_Strategy_ID(strategy.getWM_Strategy_ID)
      strategyDetail.setWM_Rule_ID(rule.getWM_Rule_ID)
      assert(strategyDetail.save(), "unable to save the test strategy detail")

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
