/**
 *  Product: Posterita Web-Based POS and Adempiere Plugin
 *  Copyright (C) 2007  Posterita Ltd
 *  This file is part of POSterita
 *  
 *  POSterita is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License along
 *  with this program; if not, write to the Free Software Foundation, Inc.,
 *  51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 *  
 **/

/**
	@author ashley
 */

package org.posterita.factory;

import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

import org.compiere.model.PO;
import org.compiere.model.X_U_Menu;
import org.compiere.util.Env;
import org.posterita.Constants;
import org.posterita.businesslogic.MenuManager;
import org.posterita.exceptions.OperationException;
import org.posterita.lib.UdiConstants;
import org.posterita.model.UDIPO;
import org.posterita.model.UDIU_Menu;
import org.posterita.order.UDIOrderTypes;

public class POSMenuFactory extends AbstractFactory
{

	public static final String MENU_POSITION_TOP = "TOP";
	public static final String MENU_POSITION_LEFT = "LEFT";
	public static final String MENU_POSITION_RIGHT = "RIGHT";
	public static final String MENU_POSITION_DOWN = "DOWN";
	
	public static final String PMENU_SALES_ID = "pmenu.order.id";
	
	public static final String PMENU_CREDITSALES_ID = "pmenu.creditsales.id";
	public static final String PMENU_PURCHASES_ID = "pmenu.purchases.id";
	public static final String PMENU_STOCK_ID = "pmenu.stock.id";
	
	
	public static final String SMENU_POSORDER_ID = "smenu.posorder.id";
	public static final String SMENU_POSORDERWA_ID="smenu.posorderwa.id";
	public static final String SMENU_POSPARTIALORDER_ID = "smenu.pospartialorder.id";
	public static final String SMENU_INVOKEPOSPARTIALORDER_ID = "smenu.invokepospartialorder.id";
	public static final String SMENU_POSGOODRECNOTE_ID = "smenu.posgoodrecnote.id";
	public static final String SMENU_POSGOODRETNOTE_ID= "smenu.posgoodretnote.id";
	public static final String SMENU_CUSTRETORDER_ID="smenu.custretorder.id";
	public static final String SMENU_POSPARTIALHISTORYORDER_ID="smenu.pospartialposhistory.id";
    public static final String SMENU_CUSTRETOREDRFROMPOS_ID="smenu.custretorderfrompos.id";
    public static final String SMENU_POSORDERQUICK="smenu.posorderquick.id";
    public static final String SMENU_POSORDERCUSTOMERCOMPULSORY="smenu.posordercustomercompulsory.id";
    public static final String SMENU_CREDITORDER_ID = "smenu.creditorder.id";
    public static final String SMENU_SETTLE_PAYMENT_ID = "smenu.settlepayment.id";
    public static final String SMENU_CUSTOMER_RETURN_HISTORY_ID="smenu.customer.return.history.id";
	
	
	public static final String PMENU_REPORTS_ID="pmenu.reports.id";
	public static final String SMENU_POSSTOCKMOV="smenu.posstockmov.id";
	public static final String SMENU_SALESANALREP="smenu.salesanalrep.id";
	public static final String SMENU_FASTMOVITEMS="smenu.fastmovitems.id";
	public static final String SMENU_SLOWMOVITEMS="smenu.slowmovitems.id";
	public static final String SMENU_CUSTSALESREPORT="smenu.custsalesreport.id";
	public static final String SMENU_CASHBOOKREPORT="smenu.cashbookreport.id";
	public static final String SMENU_PERIODICASHBOOKDETAILS="smenu.periodiccashbookdetails.id";
	public static final String SMENU_NOIMAGEREPORT="smenu.noimagereport.id";
	
	public static final String PMENU_ADMINISTRATION_ID="pmenu.administration.id";
	public static final String SMENU_USER_ID="smenu.user.id";

	public static final String SMENU_VIEWROLE_ID="smenu.viewrole.id";
	public static final String SMENU_CREATEVENDOR_ID="smenu.createvendor.id";
	
	public static final String SMENU_CURRENTTILLAMOUNT_ID="smenu.currenttillamout.id";
	public static final String SMENU_CLOSECASHBOOK_ID="smenu.closecashbook.id";
	public static final String SMENU_POSINFO_ID="smenu.posinfo.id";
	public static final String SMENU_MYSTOCK_ID="smenu.mystock.id";

	public static final String SMENU_ORDERHISTORY_ID="smenu.orderhistory.id";
	public static final String SMENU_DOCUMENTHISTORY_ID="smenu.documenthistory.id";

	public static final String SMENU_PRODUCTS="smenu.products.id";
    public static final String SMENU_VIEW_BPINFO="smenu.view.bp.info.id";
    public static final String SMENU_CUSTOMER="smenu.customer.id";
    public static final String SMENU_EDIT_BULK_PRODUCT="smenu.edit.bulk.product.id";
    public static final String SMENU_EDIT_BULK_PRICE="smenu.edit.bulk.price.id";
    public static final String SMENU_EDIT_ATTRIBUTE_VALUE="smenu.edit.attribute.value.id";
    public static final String SMENU_ADJUST_CASH_BOOK="smenu.adjust.cashbook.id";
	public static final String SMENU_SYNCHRONIZE_COLLECTIONS="smenu.synchronize.collections.id";
	public static final String PMENU_LOGOUT_ID="pmenu.logout.id";
    public static final String SMENU_CLOSE_POS_TILL="smenu.close.pos.till.id";
    public static final String SMENU_OPEN_CASH_DRAWER = "smenu.opencashdrawer.id";
    public static final String SMENU_UPDATE_PRODUCT_BY_CSV = "smenu.update.produc.csv.id";
   
    public static final String SMENU_CHECK_SEQUENCE = "smenu.checkSequence.id";
    public static final String SMENU_PRINT_DUNNING_LETTERS_ID = "smenu.printDunningLetters.id";
     public static final String SMENU_VIEW_PAYMENT_ALLOCATION = "smenu.viewpaymentallocation.id";
     public static final String SMENU_VIEW_PAYMENT_TERM = "smenu.viewpaymentterm.id";
     public static final String SMENU_GENERATE_COMMISSION_ID = "smenu.generatecommission.id";
     public static final String SMENU_VIEW_GENERATED_COMMISSION_ID = "smenu.viewgeneratedcommission.id";
     public static final String SMENU_VIEW_TAX="smenu.tax.id";
     public static final String SMENU_CREDITMEMOFROMPOS_ID="smenu.creditmemo.from.creditorder.id";
     public static final String SMENU_CREATE_UNALLOCATED_PAYMENT_ID="smenu.create.unallocated.payment.id";
     public static final String SMENU_CREDIT_MEMO_HISTORY_ID="smenu.credit.memo.history.id";
	
	public static final String MODULE_NAME = "POS";
	
	private static final String SMENU_CASH_SALES_HISTORY_ID = "smenu.cash.sales.history.id";
	private static final String SMENU_CREDIT_SALES_HISTORY_ID = "smenu.credit.sales.history.id";
	private static final String SMENU_GOODSRECNOTEHISTORY_HISTORY_ID = "smenu.goods.received.note.history.id";
	private static final String SMENU_GOODSRETNOTEHISTORY_HISTORY_ID = "smenu.goods.returned.note.history.id";
    
    private static final String SMENU_ADJUST_STOCK_ID = "smenu.adjust.stock.id";
    private static final String SMENU_INVENTORY_HISTORY_ID = "smenu.inventory.history.id";
    private static final String SMENU_ADJUST_INVENTORY_ID = "smenu.adjust.inventory.id";
	private static final String SMENU_VIEW_PREFERENCES = "smenu.viewpreferences.id";
	private static final String SMENU_VIEW_BPARTNERS = "smenu.bpartners.id";
	private static final String SMENU_PRICE_CHECK = "smenu.price.check";

	
	
	private static POSMenuFactory singleton;
	
	protected void loadFactory(Properties ctx) throws OperationException
	{
		loadFactory(ctx, singleton);
	}
	
	@Override
	protected void loadFactory(Properties ctx, AbstractFactory factory) throws OperationException
	{
		loadCashSalesMenu(ctx, factory);
		loadCreditSalesMenu(ctx, factory);
		loadPurchasesMenu(ctx, factory);
		loadPerformanceAnalysisMenu(ctx, factory);
		loadStockMenu(ctx, factory);
		loadAdministrationMenu(ctx, factory);
	
	}


	public static POSMenuFactory getFactoryInstance(Properties ctx) throws OperationException
    {
        if (singleton == null)
            singleton = new POSMenuFactory();
        
        Properties nCtx = (Properties)ctx.clone();
        nCtx.setProperty(UdiConstants.CLIENT_ID_CTX_PARAM, "0");
        nCtx.setProperty(UdiConstants.ORG_ID_CTX_PARAM, "0");
        singleton.loadFactory(nCtx);
        
        return singleton;
    }

	
	private void loadCashSalesMenu(Properties ctx, AbstractFactory factory) throws OperationException
	{
		UDIU_Menu pmenu_sales = MenuManager.createParentMenu(ctx, "pmenu.cash.sales", MODULE_NAME, 1000);
		pmenu_sales.setPosition(MENU_POSITION_TOP);
		pmenu_sales.setImageLink("images/pos/buttons/button_order.gif");
		
		factory.add(ctx, PMENU_SALES_ID, pmenu_sales);
        
		factory.add(ctx, SMENU_POSORDER_ID,
        		MenuManager.createSubMenu(ctx, "smenu.cash.sales.multiple.payments",
        		"CreatePOSOrder.do",
        		MODULE_NAME, pmenu_sales.getID(), 1010, "sales.order"));        
        
		factory.add(ctx, SMENU_POSORDERWA_ID,
        		MenuManager.createSubMenu(ctx, "smenu.cash.sales",
        		"CreatePOSOrderWithoutAdvanced.do",
        		MODULE_NAME, pmenu_sales.getID(), 1020, "sales.order"));
        
		factory.add(ctx, SMENU_POSORDERQUICK,
        		MenuManager.createSubMenu(ctx, "smenu.quick.cash.sales",
        		"CreatePOSOrder3.do",
        		MODULE_NAME, pmenu_sales.getID(), 1030, "sales.order"));
        
        
		factory.add(ctx, SMENU_POSORDERCUSTOMERCOMPULSORY,
        		MenuManager.createSubMenu(ctx, "smenu.cash.sales.customer.complusory",
        		"CreatePOSOrder2.do",
        		MODULE_NAME, pmenu_sales.getID(), 1040, "sales.order"));

		factory.add(ctx, SMENU_CASH_SALES_HISTORY_ID,
                MenuManager.createSubMenu(ctx, "smenu.cash.sales.history",
                "ViewPOSHistoryAction.do?action=getPOSHistory&orderType="+UDIOrderTypes.POS_ORDER.getOrderType(),
                MODULE_NAME, pmenu_sales.getID(), 1050, "sales.order"));	
        
        
		
		factory.add(ctx, SMENU_POSPARTIALORDER_ID,
        		MenuManager.createSubMenu(ctx, "smenu.prepare.order",
        		"CreatePartialPOSOrder.do",
        		MODULE_NAME, pmenu_sales.getID(), 1060, "prepared.order"));

		factory.add(ctx, SMENU_INVOKEPOSPARTIALORDER_ID,
        		MenuManager.createSubMenu(ctx, "smenu.complete.prepared.order",
        		"InvokePartialPOSOrder.do",
        		MODULE_NAME, pmenu_sales.getID(), 1070, "prepared.order"));		
        
		factory.add(ctx, SMENU_POSPARTIALHISTORYORDER_ID,
        		MenuManager.createSubMenu(ctx, "smenu.prepared.order.history",
        		"ViewPartialPOSOrderHistoryAction.do?action=initPartialPOSHistory",
        		MODULE_NAME, pmenu_sales.getID(), 1080, "prepared.order"));			

		factory.add(ctx, SMENU_CUSTRETORDER_ID,
        		MenuManager.createSubMenu(ctx, "smenu.customer.returned.order",
        		"CustomerReturnOrder.do",
        		MODULE_NAME, pmenu_sales.getID(), 1090,"returned.order"));
        
        factory.add(ctx, SMENU_CUSTOMER_RETURN_HISTORY_ID,
                MenuManager.createSubMenu(ctx, "smenu.customer.return.history.id",
                "ViewPOSHistoryAction.do?action=getPOSHistory&orderType="+UDIOrderTypes.CUSTOMER_RETURN_ORDER.getOrderType(),
                MODULE_NAME, pmenu_sales.getID(), 1170, "returned.order"));    
        
		factory.add(ctx, SMENU_CUSTRETOREDRFROMPOS_ID,
                MenuManager.createSubMenu(ctx, "smenu.invoke.customer.returned.order",
                "GetCustomerReturnFromPOS.do",
                MODULE_NAME, pmenu_sales.getID(), 1100,"returned.order"));

		
		factory.add(ctx, SMENU_CURRENTTILLAMOUNT_ID,
        		MenuManager.createSubMenu(ctx, "smenu.current.money.in.terminal",
        		"GetCurrentTillAmount.do?action=getCurrentTillAmount",
        		MODULE_NAME, pmenu_sales.getID(), 1110,"till.management"));

		factory.add(ctx, SMENU_ADJUST_CASH_BOOK,
                MenuManager.createSubMenu(ctx, "smenu.adjust.cashbook",
                "AdjustCashBook.do",
                MODULE_NAME, pmenu_sales.getID(), 1120,"cash.book"));

		factory.add(ctx, SMENU_PERIODICASHBOOKDETAILS,
                MenuManager.createSubMenu(ctx, "smenu.cashbook.report",
                "CashSummaryDate.do",
                MODULE_NAME, pmenu_sales.getID(), 1130,"cash.book"));
		
		factory.add(ctx, SMENU_CASHBOOKREPORT,
                MenuManager.createSubMenu(ctx, "smenu.cashbook.history",
                "CashReportAction.do?action=initGetCashDetailsHistory",
                MODULE_NAME, pmenu_sales.getID(), 1140,"cash.book"));
        
        factory.add(ctx, SMENU_CLOSE_POS_TILL,
                MenuManager.createSubMenu(ctx, "smenu.close.till",
                "InitClosePOSTillAction.do?action=initCloseTill",
                MODULE_NAME, pmenu_sales.getID(), 1150,"till.management"));
    
       factory.add(ctx, SMENU_OPEN_CASH_DRAWER,
                MenuManager.createSubMenu(ctx, "smenu.open.cashdrawer",
                "javascript:openCashDrawer();",
                MODULE_NAME, pmenu_sales.getID(), 1160,"till.management"));
		

	}

	private void loadCreditSalesMenu(Properties ctx, AbstractFactory factory) throws OperationException
	{
		UDIU_Menu pmenu_creditsales = MenuManager.createParentMenu(ctx, "pmenu.credit.sales", MODULE_NAME, 2000);
		pmenu_creditsales.setPosition(MENU_POSITION_TOP);
		pmenu_creditsales.setImageLink("images/pos/buttons/button_order.gif");
		
		factory.add(ctx, PMENU_CREDITSALES_ID, pmenu_creditsales);
        
		factory.add(ctx, SMENU_CREDITORDER_ID,
                MenuManager.createSubMenu(ctx, "smenu.credit.sales",
                "InitCreateCreditOrderAction.do?action=initCreateCreditOrder",
                MODULE_NAME, pmenu_creditsales.getID(), 2010));
        
        factory.add(ctx, SMENU_CREDITMEMOFROMPOS_ID,
                MenuManager.createSubMenu(ctx, "smenu.creditmemo.from.creditorder.id",
                "InvokeCreditOrder.do",
                MODULE_NAME, pmenu_creditsales.getID(), 2020));
        
        factory.add(ctx, SMENU_CREDIT_MEMO_HISTORY_ID,
                MenuManager.createSubMenu(ctx, "smenu.credit.memo.history.id",
                "ViewPOSHistoryAction.do?action=getPOSHistory&orderType="+UDIOrderTypes.CREDIT_MEMO.getOrderType(),
                MODULE_NAME, pmenu_creditsales.getID(), 2080)); 
        
       
        
		factory.add(ctx, SMENU_SETTLE_PAYMENT_ID,
                MenuManager.createSubMenu(ctx, "smenu.settle.payment.credit.sales",
                "InitGetBpartnerPaymentStatus.do?action=initGetBpartnerPaymentStatus",
                MODULE_NAME, pmenu_creditsales.getID(), 2030));
		
        factory.add(ctx, SMENU_PRINT_DUNNING_LETTERS_ID,
                MenuManager.createSubMenu(ctx, "smenu.dunning.letters",
                "InitPrintDunningAction.do?action=initPrintDunning",
                MODULE_NAME, pmenu_creditsales.getID(), 2040));	
        
        
		factory.add(ctx, SMENU_VIEW_PAYMENT_ALLOCATION,
		        MenuManager.createSubMenu(ctx, "smenu.payment.allocation.history",
		        "ViewPaymentAllocation.do",
		        MODULE_NAME, pmenu_creditsales.getID(), 2050));    

		factory.add(ctx, SMENU_CREDIT_SALES_HISTORY_ID,
                MenuManager.createSubMenu(ctx, "smenu.credit.sales.history",
                "ViewPOSHistoryAction.do?action=getPOSHistory&orderType=" + UDIOrderTypes.CREDIT_ORDER.getOrderType(),
                MODULE_NAME, pmenu_creditsales.getID(), 2060));		
        
         factory.add(ctx, SMENU_CREATE_UNALLOCATED_PAYMENT_ID,
                    MenuManager.createSubMenu(ctx, "smenu.create.unallocated.payment.id",
                    "CreateUnallocatedPayment.do",
                    MODULE_NAME, pmenu_creditsales.getID(), 2070));
		
		
	}
	
	
	private void loadPurchasesMenu(Properties ctx, AbstractFactory factory) throws OperationException
	{
		UDIU_Menu pmenu_purchases = MenuManager.createParentMenu(ctx, "pmenu.purchases", MODULE_NAME, 3000);
		pmenu_purchases.setPosition(MENU_POSITION_TOP);
		pmenu_purchases.setImageLink("images/pos/buttons/button_order.gif");
		
		factory.add(ctx, PMENU_PURCHASES_ID, pmenu_purchases);
		
		factory.add(ctx, SMENU_POSGOODRECNOTE_ID,
        		MenuManager.createSubMenu(ctx, "smenu.goods.received.note",
        		"GetAllPOSVendor.do?action=getAllVendors&isSales=true",
        		MODULE_NAME, pmenu_purchases.getID(), 3010));
        
		factory.add(ctx, SMENU_POSGOODRETNOTE_ID,
        		MenuManager.createSubMenu(ctx, "smenu.goods.returned.note",
        		"GetAllPOSVendor.do?action=getAllVendors&isSales=false",
        		MODULE_NAME, pmenu_purchases.getID(), 3020));

		factory.add(ctx, SMENU_GOODSRECNOTEHISTORY_HISTORY_ID,
                MenuManager.createSubMenu(ctx, "smenu.goods.received.note.history",
                "ViewPOSHistoryAction.do?action=getPOSHistory&orderType=" + UDIOrderTypes.POS_GOODS_RECEIVE_NOTE.getOrderType(),
                MODULE_NAME, pmenu_purchases.getID(), 3030));			
		
		factory.add(ctx, SMENU_GOODSRETNOTEHISTORY_HISTORY_ID,
                MenuManager.createSubMenu(ctx, "smenu.goods.returned.note.history",
                "ViewPOSHistoryAction.do?action=getPOSHistory&orderType=" + UDIOrderTypes.POS_GOODS_RETURN_NOTE.getOrderType(),
                MODULE_NAME, pmenu_purchases.getID(), 3040));			
		
	}

	
	
	private void loadPerformanceAnalysisMenu(Properties ctx, AbstractFactory factory) throws OperationException
	{
		UDIU_Menu pmenu_reports = MenuManager.createParentMenu(ctx, "pmenu.performance.analysis", MODULE_NAME, 4000);
		pmenu_reports.setPosition(MENU_POSITION_TOP);
		pmenu_reports.setImageLink("images/pos/buttons/button_reports.gif");
		
		factory.add(ctx, PMENU_REPORTS_ID, pmenu_reports);

		factory.add(ctx, SMENU_CUSTSALESREPORT,
        		MenuManager.createSubMenu(ctx, "smenu.performance.analysis.report",
        		"CustomPOSReportAction.do?action=initCustomReport",
        		MODULE_NAME, pmenu_reports.getID(), 4010));
		
		
		factory.add(ctx, SMENU_POSINFO_ID,
        		MenuManager.createSubMenu(ctx, "smenu.sales.report.per.terminal",
        		"POSInfoReport.do",
        		MODULE_NAME, pmenu_reports.getID(), 4020));

		factory.add(ctx, SMENU_ORDERHISTORY_ID,
        		MenuManager.createSubMenu(ctx, "smenu.order.history",
        		"InitPOSHistoryAction.do?action=initPOSHistory",
        		MODULE_NAME, pmenu_reports.getID(), 4030));

		
		factory.add(ctx, SMENU_DOCUMENTHISTORY_ID,
        		MenuManager.createSubMenu(ctx, "smenu.document.history",
        		"DocumentHistoryAction.do?action=initHistory",
        		MODULE_NAME, pmenu_reports.getID(), 4040));		
        
		factory.add(ctx, SMENU_VIEW_BPINFO,
                MenuManager.createSubMenu(ctx, "smenu.bpartner.sales.details",
                "ViewBPartnerInfoAction.do?action=getBpartnerInfo",
                MODULE_NAME, pmenu_reports.getID(), 4050));

	}

	
	
	private void loadStockMenu(Properties ctx, AbstractFactory factory) throws OperationException
	{
		UDIU_Menu pmenu_stock = MenuManager.createParentMenu(ctx, "pmenu.stock", MODULE_NAME, 5000);
		pmenu_stock.setPosition(MENU_POSITION_TOP);
		pmenu_stock.setImageLink("images/pos/buttons/button_order.gif");
		
		factory.add(ctx, PMENU_STOCK_ID, pmenu_stock);
		

		factory.add(ctx, SMENU_POSSTOCKMOV,
        		MenuManager.createSubMenu(ctx, "smenu.stock.movement",
        		"StockMovementReport.do",
        		MODULE_NAME, pmenu_stock.getID(), 5010));	
		
		
		factory.add(ctx, SMENU_MYSTOCK_ID,
        		MenuManager.createSubMenu(ctx, "smenu.stock",
        		"GetPOSStockAction.do?action=getPOSStock",
        		MODULE_NAME, pmenu_stock.getID(), 5020));		

		
		factory.add(ctx, SMENU_FASTMOVITEMS,
        		MenuManager.createSubMenu(ctx, "smenu.fast.moving.items",
        		"CustomFastMovingItemsReport.do",
        		MODULE_NAME, pmenu_stock.getID(), 5030));
		
		factory.add(ctx, SMENU_SLOWMOVITEMS,
        		MenuManager.createSubMenu(ctx, "smenu.slow.moving.items",
        		"CustomSlowMovingItemsReport.do",
        		MODULE_NAME, pmenu_stock.getID(), 5040));
        
        factory.add(ctx, SMENU_ADJUST_STOCK_ID,
                MenuManager.createSubMenu(ctx, "smenu.adjust.stock.id",
                "ViewInventory.do",
                MODULE_NAME, pmenu_stock.getID(), 5040));
        
        factory.add(ctx, SMENU_INVENTORY_HISTORY_ID,
                MenuManager.createSubMenu(ctx, "smenu.inventory.history.id",
                "ViewInventoryHistoryAction.do?action=viewInventoryHistory",
                MODULE_NAME, pmenu_stock.getID(), 5050));
        
        factory.add(ctx, SMENU_ADJUST_INVENTORY_ID,
                MenuManager.createSubMenu(ctx, "smenu.adjust.inventory.id",
                "AdjustWholeInventoryAction.do?action=createWholeInventory",
                MODULE_NAME, pmenu_stock.getID(), 5060));

	}
	
	
	private void loadAdministrationMenu(Properties ctx, AbstractFactory factory) throws OperationException
	{
		UDIU_Menu pmenu_administration = MenuManager.createParentMenu(ctx, "pmenu.administration", MODULE_NAME, 6000);
		pmenu_administration.setPosition(MENU_POSITION_TOP);
		pmenu_administration.setImageLink("images/pos/buttons/button_administration.gif");
		
		factory.add(ctx, PMENU_ADMINISTRATION_ID, pmenu_administration);
		
		
		
		factory.add(ctx, SMENU_CUSTOMER,
        		MenuManager.createSubMenu(ctx, "smenu.customers",
        		//"ViewAllCustomers.do",
        		"POSCustomerAction.do?action=initSearchPOSCustomer",
        		MODULE_NAME, pmenu_administration.getID(), 6010));
		
		factory.add(ctx, SMENU_CREATEVENDOR_ID,
        		MenuManager.createSubMenu(ctx, "smenu.vendors",
        		"SearchVendor.do?action=initSearchVendors",
        		MODULE_NAME, pmenu_administration.getID(), 6020));
		
		/*factory.add(ctx, SMENU_USER_ID,
        		MenuManager.createSubMenu(ctx, "smenu.users",
        		"POSUserAction.do?action=listUsers&first=1",
        		MODULE_NAME, pmenu_administration.getID(), 6030));
		
		
		factory.add(ctx, SMENU_VIEWROLE_ID,
        		MenuManager.createSubMenu(ctx, "smenu.role",
        		"POSRoleAction.do?action=listRoles&first=1",
        		MODULE_NAME, pmenu_administration.getID(), 6040));*/
		
		  factory.add(ctx, SMENU_USER_ID,
	                MenuManager.createSubMenu(ctx, "smenu.users",
	                "ListPOSUsers.do",
	                MODULE_NAME, pmenu_administration.getID(), 6030));
	        
	        
	        factory.add(ctx, SMENU_VIEWROLE_ID,
	                MenuManager.createSubMenu(ctx, "smenu.role",
	                "ListPOSRoles.do",
	                MODULE_NAME, pmenu_administration.getID(), 6040));
		
		
		factory.add(ctx, SMENU_PRODUCTS,
        		MenuManager.createSubMenu(ctx, "smenu.products",
        		"ViewAllPOSProduct.do",
        		MODULE_NAME, pmenu_administration.getID(), 6050));

        factory.add(ctx, SMENU_EDIT_ATTRIBUTE_VALUE,
                MenuManager.createSubMenu(ctx, "smenu.edit.product.attribute.value",
                "InitViewAttributesAction.do?action=initViewAttributeValues",
                MODULE_NAME, pmenu_administration.getID(), 6060));
         
        
        factory.add(ctx, SMENU_CHECK_SEQUENCE,
                MenuManager.createSubMenu(ctx, "smenu.check.repair.database.integrity",
                "CheckSequenceAction.do?action=checkSequence",
                MODULE_NAME, pmenu_administration.getID(), 6080));

        
        factory.add(ctx, SMENU_GENERATE_COMMISSION_ID,
                MenuManager.createSubMenu(ctx, "smenu.generate.commission",
                "GenerateCommission.do",
                MODULE_NAME, pmenu_administration.getID(), 6090));
        
        factory.add(ctx, SMENU_VIEW_GENERATED_COMMISSION_ID,
                MenuManager.createSubMenu(ctx, "smenu.view.last.generated.commission",
                "ViewCommissionAction.do?action=viewCommission",
                MODULE_NAME, pmenu_administration.getID(), 6100));
        
        factory.add(ctx, SMENU_VIEW_PAYMENT_TERM,
                MenuManager.createSubMenu(ctx, "smenu.payment.term",
                "ViewAllPaymentTermAction.do?action=viewAllPaymentTerms",
                MODULE_NAME, pmenu_administration.getID(), 6110));
        
        factory.add(ctx, SMENU_VIEW_TAX,
                MenuManager.createSubMenu(ctx, "smenu.tax",
                "TaxAction.do?action=viewAllTax",
                MODULE_NAME, pmenu_administration.getID(), 6120));
        
        factory.add(ctx, SMENU_VIEW_PREFERENCES,
                MenuManager.createSubMenu(ctx, "smenu.preferences",
                "ViewPreferences.do",
                MODULE_NAME, pmenu_administration.getID(), 6130));
        
        factory.add(ctx, SMENU_VIEW_BPARTNERS,
                MenuManager.createSubMenu(ctx, "smenu.bpartners",
                "BusinessPartners.do",
                MODULE_NAME, pmenu_administration.getID(), 6140));
        
        factory.add(ctx, SMENU_PRICE_CHECK,
                MenuManager.createSubMenu(ctx, "smenu.price.check",
                "PriceCheck.do",
                MODULE_NAME, pmenu_administration.getID(), 6150));
 	}
	

    public UDIPO get(Properties ctx, String key) throws OperationException
    {
        Properties nCtx = (Properties)ctx.clone();
        Env.setContext(nCtx, UdiConstants.CLIENT_ID_CTX_PARAM, "0");
        Env.setContext(nCtx, UdiConstants.ORG_ID_CTX_PARAM, "0");
        return super.get(nCtx, key);
    }
	
	protected void setFields(Properties ctx, PO fromPO, PO toPO) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException
	{
		X_U_Menu fromUMenu = (X_U_Menu)fromPO;
		X_U_Menu toUMenu = (X_U_Menu)toPO;
		
		toUMenu.setMenuLink(fromUMenu.getMenuLink());
		toUMenu.setName(fromUMenu.getName());
		toUMenu.setHasSubMenu(fromUMenu.isHasSubMenu());
		toUMenu.setModule(fromUMenu.getModule());
		toUMenu.setParentMenu_ID(fromUMenu.getParentMenu_ID());
		toUMenu.setImageLink(fromUMenu.getImageLink());
		toUMenu.setIsActive(fromUMenu.isActive());
		toUMenu.setPosition(fromUMenu.getPosition());
		toUMenu.setCategory(fromUMenu.getCategory());
		toUMenu.setSequence(fromUMenu.getSequence());
	}
}
