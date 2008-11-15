--------------------------------
-- Nov 15, 2008 2:08:42 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,ImageLink,IsActive,MenuLink,Module,Name,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:08:36','YYYY-MM-DD HH24:MI:SS'),100,'N','images/pos/buttons/button_order.gif','Y','GetMenuItemsAction.do?action=getMenuItems&menuId=','POS','pmenu.cash.sales','TOP',1000,50282,TO_TIMESTAMP('2008-11-15 14:08:36','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:08:44 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Category,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,'sales.order',TO_TIMESTAMP('2008-11-15 14:08:42','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','LoadOrderScreen.do?action=loadOrderScreen&isSOTrx=true&orderType=POS%20Order&menuId=','POS','smenu.cash.sales',50282,'LEFT',1010,50283,TO_TIMESTAMP('2008-11-15 14:08:42','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:08:45 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Category,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,'sales.order',TO_TIMESTAMP('2008-11-15 14:08:44','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','ViewPOSHistoryAction.do?action=getPOSHistory&orderType=POS Order&menuId=','POS','smenu.cash.sales.history',50282,'LEFT',1050,50284,TO_TIMESTAMP('2008-11-15 14:08:44','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:08:47 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Category,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,'returned.order',TO_TIMESTAMP('2008-11-15 14:08:45','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','LoadOrderScreen.do?action=loadOrderScreen&isSOTrx=true&orderType=Customer%20Returned%20Order&menuId=','POS','smenu.customer.returned.order',50282,'LEFT',1090,50285,TO_TIMESTAMP('2008-11-15 14:08:45','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:08:48 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Category,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,'returned.order',TO_TIMESTAMP('2008-11-15 14:08:47','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','ViewPOSHistoryAction.do?action=getPOSHistory&orderType=Customer%20Returned%20Order&menuId=','POS','smenu.customer.return.history.id',50282,'LEFT',1170,50286,TO_TIMESTAMP('2008-11-15 14:08:47','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:08:49 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Category,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,'returned.order',TO_TIMESTAMP('2008-11-15 14:08:48','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','GetCustomerReturnFromPOS.do?menuId=','POS','smenu.invoke.customer.returned.order',50282,'LEFT',1100,50287,TO_TIMESTAMP('2008-11-15 14:08:48','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:08:51 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Category,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,'till.management',TO_TIMESTAMP('2008-11-15 14:08:50','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','GetCurrentTillAmount.do?action=getCurrentTillAmount&menuId=','POS','smenu.current.money.in.terminal',50282,'LEFT',1110,50288,TO_TIMESTAMP('2008-11-15 14:08:50','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:08:53 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Category,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,'cash.book',TO_TIMESTAMP('2008-11-15 14:08:52','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','AdjustCashBook.do?menuId=','POS','smenu.adjust.cashbook',50282,'LEFT',1120,50289,TO_TIMESTAMP('2008-11-15 14:08:52','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:08:55 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Category,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,'cash.book',TO_TIMESTAMP('2008-11-15 14:08:53','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','CashSummaryDate.do?menuId=','POS','smenu.cashbook.report',50282,'LEFT',1130,50290,TO_TIMESTAMP('2008-11-15 14:08:53','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:08:56 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Category,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,'cash.book',TO_TIMESTAMP('2008-11-15 14:08:55','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','CashReportAction.do?action=initGetCashDetailsHistory&menuId=','POS','smenu.cashbook.history',50282,'LEFT',1140,50291,TO_TIMESTAMP('2008-11-15 14:08:55','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:08:57 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Category,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,'till.management',TO_TIMESTAMP('2008-11-15 14:08:56','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','InitClosePOSTillAction.do?action=initCloseTill&menuId=','POS','smenu.close.till',50282,'LEFT',1150,50292,TO_TIMESTAMP('2008-11-15 14:08:56','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:08:59 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Category,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,'till.management',TO_TIMESTAMP('2008-11-15 14:08:57','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','javascript:openCashDrawer();','POS','smenu.open.cashdrawer',50282,'LEFT',1160,50293,TO_TIMESTAMP('2008-11-15 14:08:57','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:09:00 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,ImageLink,IsActive,MenuLink,Module,Name,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:08:59','YYYY-MM-DD HH24:MI:SS'),100,'N','images/pos/buttons/button_order.gif','Y','GetMenuItemsAction.do?action=getMenuItems&menuId=','POS','pmenu.credit.sales','TOP',2000,50294,TO_TIMESTAMP('2008-11-15 14:08:59','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:09:02 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:09:00','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','CreditSales.do?menuId=','POS','smenu.credit.sales',50294,'LEFT',2010,50295,TO_TIMESTAMP('2008-11-15 14:09:00','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:09:05 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:09:02','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','InvokeCreditOrder.do?menuId=','POS','smenu.creditmemo.from.creditorder.id',50294,'LEFT',2010,50296,TO_TIMESTAMP('2008-11-15 14:09:02','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:09:06 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:09:05','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','ViewPOSHistoryAction.do?action=getPOSHistory&orderType=Credit Memo&menuId=','POS','smenu.credit.memo.history.id',50294,'LEFT',2020,50297,TO_TIMESTAMP('2008-11-15 14:09:05','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:09:08 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:09:06','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','InitGetBpartnerPaymentStatus.do?action=initGetBpartnerPaymentStatus&menuId=','POS','smenu.settle.payment.credit.sales',50294,'LEFT',2030,50298,TO_TIMESTAMP('2008-11-15 14:09:06','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:09:09 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:09:08','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','InitPrintDunningAction.do?action=initPrintDunning&menuId=','POS','smenu.dunning.letters',50294,'LEFT',2040,50299,TO_TIMESTAMP('2008-11-15 14:09:08','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:09:10 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:09:09','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','ViewPaymentAllocation.do?menuId=','POS','smenu.payment.allocation.history',50294,'LEFT',2050,50300,TO_TIMESTAMP('2008-11-15 14:09:09','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:09:11 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:09:10','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','ViewPOSHistoryAction.do?action=getPOSHistory&orderType=Credit Order&menuId=','POS','smenu.credit.sales.history',50294,'LEFT',2060,50301,TO_TIMESTAMP('2008-11-15 14:09:10','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:09:13 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:09:12','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','CreateUnallocatedPayment.do?menuId=','POS','smenu.create.unallocated.payment.id',50294,'LEFT',2070,50302,TO_TIMESTAMP('2008-11-15 14:09:12','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:09:15 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,ImageLink,IsActive,MenuLink,Module,Name,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:09:14','YYYY-MM-DD HH24:MI:SS'),100,'N','images/pos/buttons/button_order.gif','Y','GetMenuItemsAction.do?action=getMenuItems&menuId=','POS','pmenu.purchases','TOP',3000,50303,TO_TIMESTAMP('2008-11-15 14:09:14','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:09:16 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:09:15','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','LoadOrderScreen.do?action=loadOrderScreen&isSOTrx=false&orderType=POS%20Goods%20Receive%20Note&menuId=','POS','smenu.goods.received.note',50303,'LEFT',3010,50304,TO_TIMESTAMP('2008-11-15 14:09:15','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:09:19 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:09:17','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','LoadOrderScreen.do?action=loadOrderScreen&isSOTrx=false&orderType=POS%20Goods%20Returned%20Note&menuId=','POS','smenu.goods.returned.note',50303,'LEFT',3020,50305,TO_TIMESTAMP('2008-11-15 14:09:17','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:09:21 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:09:19','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','ViewPOSHistoryAction.do?action=getPOSHistory&orderType=POS%20Goods%20Receive%20Note&menuId=','POS','smenu.goods.received.note.history',50303,'LEFT',3030,50306,TO_TIMESTAMP('2008-11-15 14:09:19','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:09:22 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:09:21','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','ViewPOSHistoryAction.do?action=getPOSHistory&orderType=POS%20Goods%20Returned%20Note&menuId=','POS','smenu.goods.returned.note.history',50303,'LEFT',3040,50307,TO_TIMESTAMP('2008-11-15 14:09:21','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:09:25 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,ImageLink,IsActive,MenuLink,Module,Name,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:09:22','YYYY-MM-DD HH24:MI:SS'),100,'N','images/pos/buttons/button_reports.gif','Y','GetMenuItemsAction.do?action=getMenuItems&menuId=','POS','pmenu.performance.analysis','TOP',4000,50308,TO_TIMESTAMP('2008-11-15 14:09:22','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:09:27 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:09:25','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','CustomPOSReportAction.do?action=initCustomReport&menuId=','POS','smenu.performance.analysis.report',50308,'LEFT',4010,50309,TO_TIMESTAMP('2008-11-15 14:09:25','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:09:28 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:09:27','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','POSInfoReport.do?menuId=','POS','smenu.sales.report.per.terminal',50308,'LEFT',4020,50310,TO_TIMESTAMP('2008-11-15 14:09:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:09:29 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:09:28','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','InitPOSHistoryAction.do?action=initPOSHistory&menuId=','POS','smenu.order.history',50308,'LEFT',4030,50311,TO_TIMESTAMP('2008-11-15 14:09:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:09:32 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:09:29','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','DocumentHistoryAction.do?action=initHistory&menuId=','POS','smenu.document.history',50308,'LEFT',4040,50312,TO_TIMESTAMP('2008-11-15 14:09:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:09:34 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:09:32','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','ViewBPartnerInfoAction.do?action=getBpartnerInfo&menuId=','POS','smenu.bpartner.sales.details',50308,'LEFT',4050,50313,TO_TIMESTAMP('2008-11-15 14:09:32','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:09:37 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:09:34','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','SalesReport.do?action=getSalesReport&isSalesReport=true&menuId=','POS','smenu.sales.report',50308,'LEFT',4060,50314,TO_TIMESTAMP('2008-11-15 14:09:34','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:09:40 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:09:37','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','POSReportAction.do?action=initBestSellingReport&menuId=','POS','smenu.best.selling.items',50308,'LEFT',4070,50315,TO_TIMESTAMP('2008-11-15 14:09:37','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:09:41 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:09:40','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','ViewStockSales.do?menuId=','POS','smenu.stock.sales.report',50308,'LEFT',4080,50316,TO_TIMESTAMP('2008-11-15 14:09:40','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:09:43 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:09:41','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','SalesReport.do?action=getSalesReport&isSalesReport=false&menuId=','POS','smenu.pos.purchase.report',50308,'LEFT',4090,50317,TO_TIMESTAMP('2008-11-15 14:09:41','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:09:44 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,ImageLink,IsActive,MenuLink,Module,Name,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:09:43','YYYY-MM-DD HH24:MI:SS'),100,'N','images/pos/buttons/button_order.gif','Y','GetMenuItemsAction.do?action=getMenuItems&menuId=','POS','pmenu.stock','TOP',5000,50318,TO_TIMESTAMP('2008-11-15 14:09:43','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:09:47 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:09:44','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','StockMovementReport.do?menuId=','POS','smenu.stock.movement',50318,'LEFT',5010,50319,TO_TIMESTAMP('2008-11-15 14:09:44','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:09:49 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:09:47','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','GetPOSStockAction.do?action=initPOSStock&menuId=','POS','smenu.stock',50318,'LEFT',5020,50320,TO_TIMESTAMP('2008-11-15 14:09:47','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:09:51 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:09:49','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','CustomFastMovingItemsReport.do?menuId=','POS','smenu.fast.moving.items',50318,'LEFT',5030,50321,TO_TIMESTAMP('2008-11-15 14:09:49','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:09:53 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:09:52','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','CustomSlowMovingItemsReport.do?menuId=','POS','smenu.slow.moving.items',50318,'LEFT',5040,50322,TO_TIMESTAMP('2008-11-15 14:09:52','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:09:57 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:09:53','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','InventoryCartAction.do?action=newInventoryCart&menuId=','POS','smenu.adjust.stock.id',50318,'LEFT',5040,50323,TO_TIMESTAMP('2008-11-15 14:09:53','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:09:59 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:09:58','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','ViewInventoryHistoryAction.do?action=viewInventoryHistory&menuId=','POS','smenu.inventory.history.id',50318,'LEFT',5050,50324,TO_TIMESTAMP('2008-11-15 14:09:58','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:10:00 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:09:59','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','AdjustWholeInventoryAction.do?action=createWholeInventory&menuId=','POS','smenu.adjust.inventory.id',50318,'LEFT',5060,50325,TO_TIMESTAMP('2008-11-15 14:09:59','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:10:01 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:10:00','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','ViewStock.do?action=viewStock&menuId=','POS','smenu.transfer.stock',50318,'LEFT',5060,50326,TO_TIMESTAMP('2008-11-15 14:10:00','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:10:02 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:10:01','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','StockMovementAction.do?action=viewMMovementHistory&menuId=','POS','smenu.inventory.move',50318,'LEFT',5070,50327,TO_TIMESTAMP('2008-11-15 14:10:01','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:10:03 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:10:02','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','StockMovementAction.do?action=viewMoveConfirm&menuId=','POS','smenu.move.confirmation',50318,'LEFT',5080,50328,TO_TIMESTAMP('2008-11-15 14:10:02','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:10:05 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,ImageLink,IsActive,MenuLink,Module,Name,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:10:03','YYYY-MM-DD HH24:MI:SS'),100,'N','images/pos/buttons/button_administration.gif','Y','GetMenuItemsAction.do?action=getMenuItems&menuId=','POS','pmenu.administration','TOP',6000,50329,TO_TIMESTAMP('2008-11-15 14:10:03','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:10:06 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:10:05','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','POSCustomerAction.do?action=initSearchPOSCustomer&menuId=','POS','smenu.customers',50329,'LEFT',6010,50330,TO_TIMESTAMP('2008-11-15 14:10:05','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:10:06 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:10:06','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','SearchVendor.do?action=initSearchVendors&menuId=','POS','smenu.vendors',50329,'LEFT',6020,50331,TO_TIMESTAMP('2008-11-15 14:10:06','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:10:08 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:10:07','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','ListPOSUsers.do?menuId=','POS','smenu.users',50329,'LEFT',6030,50332,TO_TIMESTAMP('2008-11-15 14:10:07','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:10:09 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:10:08','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','ListPOSRoles.do?menuId=','POS','smenu.role',50329,'LEFT',6040,50333,TO_TIMESTAMP('2008-11-15 14:10:08','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:10:10 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:10:09','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','ViewAllPOSProduct.do?menuId=','POS','smenu.products',50329,'LEFT',6050,50334,TO_TIMESTAMP('2008-11-15 14:10:09','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:10:11 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:10:10','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','initProductBarcodeCart.do?menuId=','POS','smenu.barcode.printing',50329,'LEFT',6060,50335,TO_TIMESTAMP('2008-11-15 14:10:10','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:10:12 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:10:11','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','InitViewAttributesAction.do?action=initViewAttributeValues&menuId=','POS','smenu.edit.product.attribute.value',50329,'LEFT',6070,50336,TO_TIMESTAMP('2008-11-15 14:10:11','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:10:13 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:10:12','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','CheckSequenceAction.do?action=checkSequence&menuId=','POS','smenu.check.repair.database.integrity',50329,'LEFT',6080,50337,TO_TIMESTAMP('2008-11-15 14:10:12','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:10:14 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:10:13','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','GenerateCommission.do?menuId=','POS','smenu.generate.commission',50329,'LEFT',6090,50338,TO_TIMESTAMP('2008-11-15 14:10:13','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:10:15 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:10:14','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','ViewCommissionAction.do?action=viewCommission&menuId=','POS','smenu.view.last.generated.commission',50329,'LEFT',6100,50339,TO_TIMESTAMP('2008-11-15 14:10:14','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:10:16 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:10:15','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','ViewAllPaymentTermAction.do?action=viewAllPaymentTerms&menuId=','POS','smenu.payment.term',50329,'LEFT',6110,50340,TO_TIMESTAMP('2008-11-15 14:10:15','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:10:18 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:10:16','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','TaxAction.do?action=viewAllTax&menuId=','POS','smenu.tax',50329,'LEFT',6120,50341,TO_TIMESTAMP('2008-11-15 14:10:16','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:10:19 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:10:18','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','ViewPreferences.do?menuId=','POS','smenu.preferences',50329,'LEFT',6130,50342,TO_TIMESTAMP('2008-11-15 14:10:18','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:10:20 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:10:19','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','BusinessPartners.do?menuId=','POS','smenu.bpartners',50329,'LEFT',6140,50343,TO_TIMESTAMP('2008-11-15 14:10:19','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:10:21 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:10:20','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','PriceCheck.do?menuId=','POS','smenu.price.check',50329,'LEFT',6150,50344,TO_TIMESTAMP('2008-11-15 14:10:20','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:10:22 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:10:21','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','ListOrgs.do?menuId=','POS','smenu.organisation',50329,'LEFT',6160,50345,TO_TIMESTAMP('2008-11-15 14:10:21','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:10:23 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:10:22','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','ListPriceLists.do?menuId=','POS','smenu.price.list',50329,'LEFT',6170,50346,TO_TIMESTAMP('2008-11-15 14:10:22','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:10:24 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:10:23','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','ListCurrencies.do?menuId=','POS','smenu.currency',50329,'LEFT',6180,50347,TO_TIMESTAMP('2008-11-15 14:10:23','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:10:25 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:10:24','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','PriceListAction.do?action=fromDeletePriceOnPriceList&menuId=','POS','smenu.delete.price.on.pricelist',50329,'LEFT',6190,50348,TO_TIMESTAMP('2008-11-15 14:10:24','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:10:26 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:10:25','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','SearchCashBookAction.do?action=initSearchCashBook&menuId=','POS','smenu.cashbook',50329,'LEFT',6200,50349,TO_TIMESTAMP('2008-11-15 14:10:25','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:10:28 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:10:26','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','SearchTerminalAction.do?action=initSearchTerminal&menuId=','POS','smenu.terminal',50329,'LEFT',6210,50350,TO_TIMESTAMP('2008-11-15 14:10:26','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:10:28 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,ImageLink,IsActive,MenuLink,Module,Name,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:10:28','YYYY-MM-DD HH24:MI:SS'),100,'N','images/pos/buttons/button_order.gif','Y','GetMenuItemsAction.do?action=getMenuItems&menuId=','POS','pmenu.help','TOP',7000,50351,TO_TIMESTAMP('2008-11-15 14:10:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:10:30 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:10:29','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','javascript:void window.open("http://www.posterita.org/mediawiki/index.php/User%60s_Manual")','POS','smenu.user.manual',50351,'LEFT',7010,50352,TO_TIMESTAMP('2008-11-15 14:10:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:10:31 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:10:30','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','POSHelpAction.do?action=initContactUs&menuId=','POS','smenu.contactus',50351,'LEFT',7020,50353,TO_TIMESTAMP('2008-11-15 14:10:30','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:10:32 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,ImageLink,IsActive,MenuLink,Module,Name,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:10:31','YYYY-MM-DD HH24:MI:SS'),100,'N','images/pos/buttons/button_order.gif','Y','GetMenuItemsAction.do?action=getMenuItems&menuId=','POS','pmenu.new.reports','TOP',8000,50354,TO_TIMESTAMP('2008-11-15 14:10:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:10:34 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:10:32','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','CreditorDebtorAction.do?action=getDebtors&menuId=','POS','smenu.debtors.id',50294,'LEFT',2080,50355,TO_TIMESTAMP('2008-11-15 14:10:32','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:10:35 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:10:34','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','InitGetBpartnerPaymentStatus.do?action=initGetCreditorPaymentStatus&menuId=','POS','smenu.settle.payment.credit.purchase',50303,'LEFT',3050,50356,TO_TIMESTAMP('2008-11-15 14:10:34','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:10:36 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:10:35','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','CreditorDebtorAction.do?action=getCreditors&menuId=','POS','smenu.creditors.id',50303,'LEFT',3060,50357,TO_TIMESTAMP('2008-11-15 14:10:35','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:10:39 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:10:36','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','CreateUnallocatedAPPayment.do?menuId=','POS','smenu.create.unallocated.ap.payment.id',50303,'LEFT',3070,50358,TO_TIMESTAMP('2008-11-15 14:10:36','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:10:40 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:10:39','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','ViewAPPaymentAllocation.do?menuId=','POS','smenu.ap.payment.allocation.history',50303,'LEFT',3080,50359,TO_TIMESTAMP('2008-11-15 14:10:39','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:10:41 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:10:40','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','GenerateReportAction.do?action=generateReportInput&processId=320&menuId=','POS','Commission Run Detail',50354,'LEFT',8130,50360,TO_TIMESTAMP('2008-11-15 14:10:40','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:10:42 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:10:41','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','GenerateReportAction.do?action=generateReportInput&processId=264&menuId=','POS','RfQ Response',50354,'LEFT',8540,50361,TO_TIMESTAMP('2008-11-15 14:10:41','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:10:43 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:10:42','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','GenerateReportAction.do?action=generateReportInput&processId=130&menuId=','POS','Weekly Invoice',50354,'LEFT',8250,50362,TO_TIMESTAMP('2008-11-15 14:10:42','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:10:44 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:10:43','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','GenerateReportAction.do?action=generateReportInput&processId=252&menuId=','POS','Accounting Fact Details',50354,'LEFT',8360,50363,TO_TIMESTAMP('2008-11-15 14:10:43','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:10:46 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:10:44','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','GenerateReportAction.do?action=generateReportInput&processId=284&menuId=','POS','Open Confirmation Details',50354,'LEFT',8430,50364,TO_TIMESTAMP('2008-11-15 14:10:44','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:10:47 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:10:46','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','GenerateReportAction.do?action=generateReportInput&processId=274&menuId=','POS','Asset Delivery Month',50354,'LEFT',8070,50365,TO_TIMESTAMP('2008-11-15 14:10:46','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:10:48 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:10:47','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','GenerateReportAction.do?action=generateReportInput&processId=293&menuId=','POS','Material Receipt Details',50354,'LEFT',8150,50366,TO_TIMESTAMP('2008-11-15 14:10:47','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:10:49 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:10:48','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','GenerateReportAction.do?action=generateReportInput&processId=234&menuId=','POS','Project Margin (Work Order)',50354,'LEFT',8240,50367,TO_TIMESTAMP('2008-11-15 14:10:48','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:10:51 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:10:50','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','GenerateReportAction.do?action=generateReportInput&processId=237&menuId=','POS','Transaction Detail',50354,'LEFT',8320,50368,TO_TIMESTAMP('2008-11-15 14:10:50','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:10:52 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:10:51','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','GenerateReportAction.do?action=generateReportInput&processId=239&menuId=','POS','Clicks Monthy',50354,'LEFT',8330,50369,TO_TIMESTAMP('2008-11-15 14:10:51','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:10:53 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:10:52','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','GenerateReportAction.do?action=generateReportInput&processId=226&menuId=','POS','Project Detail Accounting Report',50354,'LEFT',8020,50370,TO_TIMESTAMP('2008-11-15 14:10:52','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:10:54 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:10:53','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','GenerateReportAction.do?action=generateReportInput&processId=115&menuId=','POS','Vendor Selection',50354,'LEFT',8180,50371,TO_TIMESTAMP('2008-11-15 14:10:53','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:10:56 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:10:55','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','GenerateReportAction.do?action=generateReportInput&processId=124&menuId=','POS','Product Transaction Summary',50354,'LEFT',8620,50372,TO_TIMESTAMP('2008-11-15 14:10:55','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:10:57 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:10:56','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','GenerateReportAction.do?action=generateReportInput&processId=129&menuId=','POS','Monthly Invoice',50354,'LEFT',8390,50373,TO_TIMESTAMP('2008-11-15 14:10:56','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:10:58 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:10:57','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','GenerateReportAction.do?action=generateReportInput&processId=254&menuId=','POS','Accounting Fact Period',50354,'LEFT',8370,50374,TO_TIMESTAMP('2008-11-15 14:10:57','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:10:59 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:10:58','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','GenerateReportAction.do?action=generateReportInput&processId=322&menuId=','POS','Material Reference',50354,'LEFT',8480,50375,TO_TIMESTAMP('2008-11-15 14:10:58','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:11:00 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:10:59','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','GenerateReportAction.do?action=generateReportInput&processId=203&menuId=','POS','Update Accounting Balance',50354,'LEFT',8490,50376,TO_TIMESTAMP('2008-11-15 14:10:59','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:11:02 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:11:00','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','GenerateReportAction.do?action=generateReportInput&processId=127&menuId=','POS','Invoice Transactions (Acct)',50354,'LEFT',8640,50377,TO_TIMESTAMP('2008-11-15 14:11:00','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:11:03 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:11:02','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','GenerateReportAction.do?action=generateReportInput&processId=251&menuId=','POS','Invoice Tax',50354,'LEFT',8310,50378,TO_TIMESTAMP('2008-11-15 14:11:02','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:11:04 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:11:03','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','GenerateReportAction.do?action=generateReportInput&processId=240&menuId=','POS','Clicks Unprocessed',50354,'LEFT',8340,50379,TO_TIMESTAMP('2008-11-15 14:11:03','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:11:06 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:11:04','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','GenerateReportAction.do?action=generateReportInput&processId=222&menuId=','POS','Customer Assets',50354,'LEFT',8230,50380,TO_TIMESTAMP('2008-11-15 14:11:04','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:11:07 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:11:06','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','GenerateReportAction.do?action=generateReportInput&processId=121&menuId=','POS','Open Orders',50354,'LEFT',8610,50381,TO_TIMESTAMP('2008-11-15 14:11:06','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:11:08 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:11:07','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','GenerateReportAction.do?action=generateReportInput&processId=152&menuId=','POS','Invoice Detail & Margin',50354,'LEFT',8510,50382,TO_TIMESTAMP('2008-11-15 14:11:07','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:11:09 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:11:08','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','GenerateReportAction.do?action=generateReportInput&processId=161&menuId=','POS','Product Transaction Value',50354,'LEFT',8190,50383,TO_TIMESTAMP('2008-11-15 14:11:08','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:11:12 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:11:10','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','GenerateReportAction.do?action=generateReportInput&processId=183&menuId=','POS','Product Costing Report (old)',50354,'LEFT',8060,50384,TO_TIMESTAMP('2008-11-15 14:11:10','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:11:12 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:11:12','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','GenerateReportAction.do?action=generateReportInput&processId=253&menuId=','POS','Accounting Fact Daily',50354,'LEFT',8040,50385,TO_TIMESTAMP('2008-11-15 14:11:12','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:11:14 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:11:13','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','GenerateReportAction.do?action=generateReportInput&processId=228&menuId=','POS','Project Lines not Issued',50354,'LEFT',8100,50386,TO_TIMESTAMP('2008-11-15 14:11:13','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:11:15 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:11:14','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','GenerateReportAction.do?action=generateReportInput&processId=263&menuId=','POS','RfQ Unanswered',50354,'LEFT',8080,50387,TO_TIMESTAMP('2008-11-15 14:11:14','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:11:17 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:11:15','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','GenerateReportAction.do?action=generateReportInput&processId=331&menuId=','POS','Product Cost Detail',50354,'LEFT',8580,50388,TO_TIMESTAMP('2008-11-15 14:11:15','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:11:19 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:11:17','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','GenerateReportAction.do?action=generateReportInput&processId=218&menuId=','POS','Project Cycle Report',50354,'LEFT',8220,50389,TO_TIMESTAMP('2008-11-15 14:11:17','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:11:22 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:11:19','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','GenerateReportAction.do?action=generateReportInput&processId=316&menuId=','POS','UnAllocated Invoices',50354,'LEFT',8110,50390,TO_TIMESTAMP('2008-11-15 14:11:19','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:11:23 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:11:22','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','GenerateReportAction.do?action=generateReportInput&processId=285&menuId=','POS','Open Confirmations',50354,'LEFT',8440,50391,TO_TIMESTAMP('2008-11-15 14:11:22','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:11:24 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:11:23','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','GenerateReportAction.do?action=generateReportInput&processId=326&menuId=','POS','Invoice Not Realized Gain/Loss',50354,'LEFT',8630,50392,TO_TIMESTAMP('2008-11-15 14:11:23','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:11:25 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:11:24','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','GenerateReportAction.do?action=generateReportInput&processId=236&menuId=','POS','Storage Detail',50354,'LEFT',8500,50393,TO_TIMESTAMP('2008-11-15 14:11:24','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:11:27 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:11:25','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','GenerateReportAction.do?action=generateReportInput&processId=170&menuId=','POS','Cash Journal Detail',50354,'LEFT',8520,50394,TO_TIMESTAMP('2008-11-15 14:11:25','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:11:28 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:11:27','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','GenerateReportAction.do?action=generateReportInput&processId=131&menuId=','POS','Weekly Invoice Prod Cat',50354,'LEFT',8380,50395,TO_TIMESTAMP('2008-11-15 14:11:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:11:29 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:11:28','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','GenerateReportAction.do?action=generateReportInput&processId=317&menuId=','POS','UnAllocated Payments',50354,'LEFT',8050,50396,TO_TIMESTAMP('2008-11-15 14:11:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:11:30 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:11:29','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','GenerateReportAction.do?action=generateReportInput&processId=341&menuId=','POS','Quarter Invoice Product',50354,'LEFT',8010,50397,TO_TIMESTAMP('2008-11-15 14:11:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:11:32 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:11:31','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','GenerateReportAction.do?action=generateReportInput&processId=238&menuId=','POS','Aging',50354,'LEFT',8600,50398,TO_TIMESTAMP('2008-11-15 14:11:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:11:34 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:11:32','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','GenerateReportAction.do?action=generateReportInput&processId=270&menuId=','POS','Open Requisitions',50354,'LEFT',8550,50399,TO_TIMESTAMP('2008-11-15 14:11:32','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:11:38 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:11:34','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','GenerateReportAction.do?action=generateReportInput&processId=146&menuId=','POS','UnReconciled Payments',50354,'LEFT',8350,50400,TO_TIMESTAMP('2008-11-15 14:11:34','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:11:41 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:11:38','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','GenerateReportAction.do?action=generateReportInput&processId=133&menuId=','POS','Monthly Invoice Vendor',50354,'LEFT',8470,50401,TO_TIMESTAMP('2008-11-15 14:11:38','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:11:43 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:11:41','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','GenerateReportAction.do?action=generateReportInput&processId=148&menuId=','POS','Allocation',50354,'LEFT',8450,50402,TO_TIMESTAMP('2008-11-15 14:11:41','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:11:44 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:11:43','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','GenerateReportAction.do?action=generateReportInput&processId=125&menuId=','POS','Replenish Report',50354,'LEFT',8570,50403,TO_TIMESTAMP('2008-11-15 14:11:43','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:11:48 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:11:44','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','GenerateReportAction.do?action=generateReportInput&processId=180&menuId=','POS','Inventory Valuation Report',50354,'LEFT',8530,50404,TO_TIMESTAMP('2008-11-15 14:11:44','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:11:50 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:11:48','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','GenerateReportAction.do?action=generateReportInput&processId=128&menuId=','POS','Daily Invoice',50354,'LEFT',8200,50405,TO_TIMESTAMP('2008-11-15 14:11:48','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:11:53 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:11:51','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','GenerateReportAction.do?action=generateReportInput&processId=334&menuId=','POS','Business Partner Detail',50354,'LEFT',8140,50406,TO_TIMESTAMP('2008-11-15 14:11:51','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:11:54 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:11:53','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','GenerateReportAction.do?action=generateReportInput&processId=294&menuId=','POS','Shipment Details',50354,'LEFT',8300,50407,TO_TIMESTAMP('2008-11-15 14:11:53','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:11:55 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:11:55','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','GenerateReportAction.do?action=generateReportInput&processId=333&menuId=','POS','Order Detail',50354,'LEFT',8030,50408,TO_TIMESTAMP('2008-11-15 14:11:55','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:11:57 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:11:56','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','GenerateReportAction.do?action=generateReportInput&processId=138&menuId=','POS','Quarterly Invoice Customer by Product',50354,'LEFT',8590,50409,TO_TIMESTAMP('2008-11-15 14:11:56','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:11:59 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:11:58','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','GenerateReportAction.do?action=generateReportInput&processId=204&menuId=','POS','Statement of Accounts',50354,'LEFT',8210,50410,TO_TIMESTAMP('2008-11-15 14:11:58','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:12:02 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:11:59','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','GenerateReportAction.do?action=generateReportInput&processId=223&menuId=','POS','Asset Delivery Details',50354,'LEFT',8160,50411,TO_TIMESTAMP('2008-11-15 14:11:59','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:12:04 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:12:02','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','GenerateReportAction.do?action=generateReportInput&processId=340&menuId=','POS','Monthly Invoice Product',50354,'LEFT',8170,50412,TO_TIMESTAMP('2008-11-15 14:12:02','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:12:05 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:12:04','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','GenerateReportAction.do?action=generateReportInput&processId=120&menuId=','POS','Order Transactions',50354,'LEFT',8460,50413,TO_TIMESTAMP('2008-11-15 14:12:04','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:12:07 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:12:05','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','GenerateReportAction.do?action=generateReportInput&processId=330&menuId=','POS','Product Cost Summary',50354,'LEFT',8270,50414,TO_TIMESTAMP('2008-11-15 14:12:05','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:12:10 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:12:07','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','GenerateReportAction.do?action=generateReportInput&processId=310&menuId=','POS','Trial Balance',50354,'LEFT',8090,50415,TO_TIMESTAMP('2008-11-15 14:12:07','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:12:12 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:12:10','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','GenerateReportAction.do?action=generateReportInput&processId=229&menuId=','POS','Project POs not Issued',50354,'LEFT',8280,50416,TO_TIMESTAMP('2008-11-15 14:12:10','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:12:15 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:12:13','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','GenerateReportAction.do?action=generateReportInput&processId=202&menuId=','POS','Create Report',50354,'LEFT',8560,50417,TO_TIMESTAMP('2008-11-15 14:12:13','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:12:16 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:12:15','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','GenerateReportAction.do?action=generateReportInput&processId=318&menuId=','POS','Payment Details',50354,'LEFT',8410,50418,TO_TIMESTAMP('2008-11-15 14:12:15','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:12:18 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:12:16','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','GenerateReportAction.do?action=generateReportInput&processId=139&menuId=','POS','Quarterly Invoice Customer by Vendor',50354,'LEFT',8260,50419,TO_TIMESTAMP('2008-11-15 14:12:16','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:12:19 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:12:18','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','GenerateReportAction.do?action=generateReportInput&processId=151&menuId=','POS','Invoice Transactions (Doc)',50354,'LEFT',8420,50420,TO_TIMESTAMP('2008-11-15 14:12:18','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:12:22 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:12:19','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','GenerateReportAction.do?action=generateReportInput&processId=329&menuId=','POS','Product Cost',50354,'LEFT',8290,50421,TO_TIMESTAMP('2008-11-15 14:12:19','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:12:27 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:12:22','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','GenerateReportAction.do?action=generateReportInput&processId=132&menuId=','POS','Monthly Invoice Prod Cat',50354,'LEFT',8120,50422,TO_TIMESTAMP('2008-11-15 14:12:22','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:12:28 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:12:27','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','GenerateReportAction.do?action=generateReportInput&processId=145&menuId=','POS','Open Items',50354,'LEFT',8400,50423,TO_TIMESTAMP('2008-11-15 14:12:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 2:12:30 PM EET
-- Posterita Sample Data
INSERT INTO U_WebMenu (AD_Client_ID,AD_Org_ID,Created,CreatedBy,HasSubMenu,IsActive,MenuLink,Module,Name,ParentMenu_ID,Position,Sequence,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 14:12:29','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','GenerateReportAction.do?action=generateReportInput&processId=319&menuId=','POS','Business Partner Open',50354,'LEFT',8650,50424,TO_TIMESTAMP('2008-11-15 14:12:29','YYYY-MM-DD HH24:MI:SS'),100)
;



--------------------------------
-- Nov 15, 2008 5:48:51 PM EET
-- Posterita Sample Data
INSERT INTO U_RoleMenu (AD_Client_ID,AD_Org_ID,AD_Role_ID,Created,CreatedBy,IsActive,U_RoleMenu_ID,U_WebMenu_ID,Updated,UpdatedBy) VALUES (11,0,102,TO_TIMESTAMP('2008-11-15 17:48:49','YYYY-MM-DD HH24:MI:SS'),100,'Y',50073,50303,TO_TIMESTAMP('2008-11-15 17:48:49','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 5:48:53 PM EET
-- Posterita Sample Data
INSERT INTO U_RoleMenu (AD_Client_ID,AD_Org_ID,AD_Role_ID,Created,CreatedBy,IsActive,U_RoleMenu_ID,U_WebMenu_ID,Updated,UpdatedBy) VALUES (11,0,102,TO_TIMESTAMP('2008-11-15 17:48:52','YYYY-MM-DD HH24:MI:SS'),100,'Y',50074,50288,TO_TIMESTAMP('2008-11-15 17:48:52','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 5:48:54 PM EET
-- Posterita Sample Data
INSERT INTO U_RoleMenu (AD_Client_ID,AD_Org_ID,AD_Role_ID,Created,CreatedBy,IsActive,U_RoleMenu_ID,U_WebMenu_ID,Updated,UpdatedBy) VALUES (11,0,102,TO_TIMESTAMP('2008-11-15 17:48:53','YYYY-MM-DD HH24:MI:SS'),100,'Y',50075,50331,TO_TIMESTAMP('2008-11-15 17:48:53','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 5:48:55 PM EET
-- Posterita Sample Data
INSERT INTO U_RoleMenu (AD_Client_ID,AD_Org_ID,AD_Role_ID,Created,CreatedBy,IsActive,U_RoleMenu_ID,U_WebMenu_ID,Updated,UpdatedBy) VALUES (11,0,102,TO_TIMESTAMP('2008-11-15 17:48:54','YYYY-MM-DD HH24:MI:SS'),100,'Y',50076,50348,TO_TIMESTAMP('2008-11-15 17:48:54','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 5:48:55 PM EET
-- Posterita Sample Data
INSERT INTO U_RoleMenu (AD_Client_ID,AD_Org_ID,AD_Role_ID,Created,CreatedBy,IsActive,U_RoleMenu_ID,U_WebMenu_ID,Updated,UpdatedBy) VALUES (11,0,102,TO_TIMESTAMP('2008-11-15 17:48:55','YYYY-MM-DD HH24:MI:SS'),100,'Y',50077,50322,TO_TIMESTAMP('2008-11-15 17:48:55','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 5:48:56 PM EET
-- Posterita Sample Data
INSERT INTO U_RoleMenu (AD_Client_ID,AD_Org_ID,AD_Role_ID,Created,CreatedBy,IsActive,U_RoleMenu_ID,U_WebMenu_ID,Updated,UpdatedBy) VALUES (11,0,102,TO_TIMESTAMP('2008-11-15 17:48:55','YYYY-MM-DD HH24:MI:SS'),100,'Y',50078,50290,TO_TIMESTAMP('2008-11-15 17:48:55','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 5:48:57 PM EET
-- Posterita Sample Data
INSERT INTO U_RoleMenu (AD_Client_ID,AD_Org_ID,AD_Role_ID,Created,CreatedBy,IsActive,U_RoleMenu_ID,U_WebMenu_ID,Updated,UpdatedBy) VALUES (11,0,102,TO_TIMESTAMP('2008-11-15 17:48:56','YYYY-MM-DD HH24:MI:SS'),100,'Y',50079,50293,TO_TIMESTAMP('2008-11-15 17:48:56','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 5:48:57 PM EET
-- Posterita Sample Data
INSERT INTO U_RoleMenu (AD_Client_ID,AD_Org_ID,AD_Role_ID,Created,CreatedBy,IsActive,U_RoleMenu_ID,U_WebMenu_ID,Updated,UpdatedBy) VALUES (11,0,102,TO_TIMESTAMP('2008-11-15 17:48:57','YYYY-MM-DD HH24:MI:SS'),100,'Y',50080,50330,TO_TIMESTAMP('2008-11-15 17:48:57','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 5:48:58 PM EET
-- Posterita Sample Data
INSERT INTO U_RoleMenu (AD_Client_ID,AD_Org_ID,AD_Role_ID,Created,CreatedBy,IsActive,U_RoleMenu_ID,U_WebMenu_ID,Updated,UpdatedBy) VALUES (11,0,102,TO_TIMESTAMP('2008-11-15 17:48:57','YYYY-MM-DD HH24:MI:SS'),100,'Y',50081,50334,TO_TIMESTAMP('2008-11-15 17:48:57','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 5:48:59 PM EET
-- Posterita Sample Data
INSERT INTO U_RoleMenu (AD_Client_ID,AD_Org_ID,AD_Role_ID,Created,CreatedBy,IsActive,U_RoleMenu_ID,U_WebMenu_ID,Updated,UpdatedBy) VALUES (11,0,102,TO_TIMESTAMP('2008-11-15 17:48:58','YYYY-MM-DD HH24:MI:SS'),100,'Y',50082,50325,TO_TIMESTAMP('2008-11-15 17:48:58','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 5:48:59 PM EET
-- Posterita Sample Data
INSERT INTO U_RoleMenu (AD_Client_ID,AD_Org_ID,AD_Role_ID,Created,CreatedBy,IsActive,U_RoleMenu_ID,U_WebMenu_ID,Updated,UpdatedBy) VALUES (11,0,102,TO_TIMESTAMP('2008-11-15 17:48:59','YYYY-MM-DD HH24:MI:SS'),100,'Y',50083,50354,TO_TIMESTAMP('2008-11-15 17:48:59','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 5:49:00 PM EET
-- Posterita Sample Data
INSERT INTO U_RoleMenu (AD_Client_ID,AD_Org_ID,AD_Role_ID,Created,CreatedBy,IsActive,U_RoleMenu_ID,U_WebMenu_ID,Updated,UpdatedBy) VALUES (11,0,102,TO_TIMESTAMP('2008-11-15 17:48:59','YYYY-MM-DD HH24:MI:SS'),100,'Y',50084,50351,TO_TIMESTAMP('2008-11-15 17:48:59','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 5:49:01 PM EET
-- Posterita Sample Data
INSERT INTO U_RoleMenu (AD_Client_ID,AD_Org_ID,AD_Role_ID,Created,CreatedBy,IsActive,U_RoleMenu_ID,U_WebMenu_ID,Updated,UpdatedBy) VALUES (11,0,102,TO_TIMESTAMP('2008-11-15 17:49:00','YYYY-MM-DD HH24:MI:SS'),100,'Y',50085,50296,TO_TIMESTAMP('2008-11-15 17:49:00','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 5:49:01 PM EET
-- Posterita Sample Data
INSERT INTO U_RoleMenu (AD_Client_ID,AD_Org_ID,AD_Role_ID,Created,CreatedBy,IsActive,U_RoleMenu_ID,U_WebMenu_ID,Updated,UpdatedBy) VALUES (11,0,102,TO_TIMESTAMP('2008-11-15 17:49:01','YYYY-MM-DD HH24:MI:SS'),100,'Y',50086,50291,TO_TIMESTAMP('2008-11-15 17:49:01','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 5:49:02 PM EET
-- Posterita Sample Data
INSERT INTO U_RoleMenu (AD_Client_ID,AD_Org_ID,AD_Role_ID,Created,CreatedBy,IsActive,U_RoleMenu_ID,U_WebMenu_ID,Updated,UpdatedBy) VALUES (11,0,102,TO_TIMESTAMP('2008-11-15 17:49:01','YYYY-MM-DD HH24:MI:SS'),100,'Y',50087,50324,TO_TIMESTAMP('2008-11-15 17:49:01','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 5:49:02 PM EET
-- Posterita Sample Data
INSERT INTO U_RoleMenu (AD_Client_ID,AD_Org_ID,AD_Role_ID,Created,CreatedBy,IsActive,U_RoleMenu_ID,U_WebMenu_ID,Updated,UpdatedBy) VALUES (11,0,102,TO_TIMESTAMP('2008-11-15 17:49:02','YYYY-MM-DD HH24:MI:SS'),100,'Y',50088,50349,TO_TIMESTAMP('2008-11-15 17:49:02','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 5:49:03 PM EET
-- Posterita Sample Data
INSERT INTO U_RoleMenu (AD_Client_ID,AD_Org_ID,AD_Role_ID,Created,CreatedBy,IsActive,U_RoleMenu_ID,U_WebMenu_ID,Updated,UpdatedBy) VALUES (11,0,102,TO_TIMESTAMP('2008-11-15 17:49:03','YYYY-MM-DD HH24:MI:SS'),100,'Y',50089,50328,TO_TIMESTAMP('2008-11-15 17:49:03','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 5:49:04 PM EET
-- Posterita Sample Data
INSERT INTO U_RoleMenu (AD_Client_ID,AD_Org_ID,AD_Role_ID,Created,CreatedBy,IsActive,U_RoleMenu_ID,U_WebMenu_ID,Updated,UpdatedBy) VALUES (11,0,102,TO_TIMESTAMP('2008-11-15 17:49:03','YYYY-MM-DD HH24:MI:SS'),100,'Y',50090,50284,TO_TIMESTAMP('2008-11-15 17:49:03','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 5:49:04 PM EET
-- Posterita Sample Data
INSERT INTO U_RoleMenu (AD_Client_ID,AD_Org_ID,AD_Role_ID,Created,CreatedBy,IsActive,U_RoleMenu_ID,U_WebMenu_ID,Updated,UpdatedBy) VALUES (11,0,102,TO_TIMESTAMP('2008-11-15 17:49:04','YYYY-MM-DD HH24:MI:SS'),100,'Y',50091,50336,TO_TIMESTAMP('2008-11-15 17:49:04','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 5:49:05 PM EET
-- Posterita Sample Data
INSERT INTO U_RoleMenu (AD_Client_ID,AD_Org_ID,AD_Role_ID,Created,CreatedBy,IsActive,U_RoleMenu_ID,U_WebMenu_ID,Updated,UpdatedBy) VALUES (11,0,102,TO_TIMESTAMP('2008-11-15 17:49:05','YYYY-MM-DD HH24:MI:SS'),100,'Y',50092,50309,TO_TIMESTAMP('2008-11-15 17:49:05','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 5:49:06 PM EET
-- Posterita Sample Data
INSERT INTO U_RoleMenu (AD_Client_ID,AD_Org_ID,AD_Role_ID,Created,CreatedBy,IsActive,U_RoleMenu_ID,U_WebMenu_ID,Updated,UpdatedBy) VALUES (11,0,102,TO_TIMESTAMP('2008-11-15 17:49:05','YYYY-MM-DD HH24:MI:SS'),100,'Y',50093,50319,TO_TIMESTAMP('2008-11-15 17:49:05','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 5:49:06 PM EET
-- Posterita Sample Data
INSERT INTO U_RoleMenu (AD_Client_ID,AD_Org_ID,AD_Role_ID,Created,CreatedBy,IsActive,U_RoleMenu_ID,U_WebMenu_ID,Updated,UpdatedBy) VALUES (11,0,102,TO_TIMESTAMP('2008-11-15 17:49:06','YYYY-MM-DD HH24:MI:SS'),100,'Y',50094,50347,TO_TIMESTAMP('2008-11-15 17:49:06','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 5:49:07 PM EET
-- Posterita Sample Data
INSERT INTO U_RoleMenu (AD_Client_ID,AD_Org_ID,AD_Role_ID,Created,CreatedBy,IsActive,U_RoleMenu_ID,U_WebMenu_ID,Updated,UpdatedBy) VALUES (11,0,102,TO_TIMESTAMP('2008-11-15 17:49:07','YYYY-MM-DD HH24:MI:SS'),100,'Y',50095,50350,TO_TIMESTAMP('2008-11-15 17:49:07','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 5:49:09 PM EET
-- Posterita Sample Data
INSERT INTO U_RoleMenu (AD_Client_ID,AD_Org_ID,AD_Role_ID,Created,CreatedBy,IsActive,U_RoleMenu_ID,U_WebMenu_ID,Updated,UpdatedBy) VALUES (11,0,102,TO_TIMESTAMP('2008-11-15 17:49:07','YYYY-MM-DD HH24:MI:SS'),100,'Y',50096,50343,TO_TIMESTAMP('2008-11-15 17:49:07','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 5:49:09 PM EET
-- Posterita Sample Data
INSERT INTO U_RoleMenu (AD_Client_ID,AD_Org_ID,AD_Role_ID,Created,CreatedBy,IsActive,U_RoleMenu_ID,U_WebMenu_ID,Updated,UpdatedBy) VALUES (11,0,102,TO_TIMESTAMP('2008-11-15 17:49:09','YYYY-MM-DD HH24:MI:SS'),100,'Y',50097,50344,TO_TIMESTAMP('2008-11-15 17:49:09','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 5:49:10 PM EET
-- Posterita Sample Data
INSERT INTO U_RoleMenu (AD_Client_ID,AD_Org_ID,AD_Role_ID,Created,CreatedBy,IsActive,U_RoleMenu_ID,U_WebMenu_ID,Updated,UpdatedBy) VALUES (11,0,102,TO_TIMESTAMP('2008-11-15 17:49:10','YYYY-MM-DD HH24:MI:SS'),100,'Y',50098,50333,TO_TIMESTAMP('2008-11-15 17:49:10','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 5:49:11 PM EET
-- Posterita Sample Data
INSERT INTO U_RoleMenu (AD_Client_ID,AD_Org_ID,AD_Role_ID,Created,CreatedBy,IsActive,U_RoleMenu_ID,U_WebMenu_ID,Updated,UpdatedBy) VALUES (11,0,102,TO_TIMESTAMP('2008-11-15 17:49:10','YYYY-MM-DD HH24:MI:SS'),100,'Y',50099,50317,TO_TIMESTAMP('2008-11-15 17:49:10','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 5:49:12 PM EET
-- Posterita Sample Data
INSERT INTO U_RoleMenu (AD_Client_ID,AD_Org_ID,AD_Role_ID,Created,CreatedBy,IsActive,U_RoleMenu_ID,U_WebMenu_ID,Updated,UpdatedBy) VALUES (11,0,102,TO_TIMESTAMP('2008-11-15 17:49:11','YYYY-MM-DD HH24:MI:SS'),100,'Y',50100,50289,TO_TIMESTAMP('2008-11-15 17:49:11','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 6:12:44 PM EET
-- Posterita Sample Data
INSERT INTO U_RoleMenu (AD_Client_ID,AD_Org_ID,AD_Role_ID,Created,CreatedBy,IsActive,U_RoleMenu_ID,U_WebMenu_ID,Updated,UpdatedBy) VALUES (11,0,102,TO_TIMESTAMP('2008-11-15 18:12:38','YYYY-MM-DD HH24:MI:SS'),100,'Y',50101,50346,TO_TIMESTAMP('2008-11-15 18:12:38','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 6:12:46 PM EET
-- Posterita Sample Data
INSERT INTO U_RoleMenu (AD_Client_ID,AD_Org_ID,AD_Role_ID,Created,CreatedBy,IsActive,U_RoleMenu_ID,U_WebMenu_ID,Updated,UpdatedBy) VALUES (11,0,102,TO_TIMESTAMP('2008-11-15 18:12:45','YYYY-MM-DD HH24:MI:SS'),100,'Y',50102,50297,TO_TIMESTAMP('2008-11-15 18:12:45','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 6:12:46 PM EET
-- Posterita Sample Data
INSERT INTO U_RoleMenu (AD_Client_ID,AD_Org_ID,AD_Role_ID,Created,CreatedBy,IsActive,U_RoleMenu_ID,U_WebMenu_ID,Updated,UpdatedBy) VALUES (11,0,102,TO_TIMESTAMP('2008-11-15 18:12:46','YYYY-MM-DD HH24:MI:SS'),100,'Y',50103,50292,TO_TIMESTAMP('2008-11-15 18:12:46','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 6:12:47 PM EET
-- Posterita Sample Data
INSERT INTO U_RoleMenu (AD_Client_ID,AD_Org_ID,AD_Role_ID,Created,CreatedBy,IsActive,U_RoleMenu_ID,U_WebMenu_ID,Updated,UpdatedBy) VALUES (11,0,102,TO_TIMESTAMP('2008-11-15 18:12:47','YYYY-MM-DD HH24:MI:SS'),100,'Y',50104,50321,TO_TIMESTAMP('2008-11-15 18:12:47','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 6:12:48 PM EET
-- Posterita Sample Data
INSERT INTO U_RoleMenu (AD_Client_ID,AD_Org_ID,AD_Role_ID,Created,CreatedBy,IsActive,U_RoleMenu_ID,U_WebMenu_ID,Updated,UpdatedBy) VALUES (11,0,102,TO_TIMESTAMP('2008-11-15 18:12:48','YYYY-MM-DD HH24:MI:SS'),100,'Y',50105,50327,TO_TIMESTAMP('2008-11-15 18:12:48','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 6:12:49 PM EET
-- Posterita Sample Data
INSERT INTO U_RoleMenu (AD_Client_ID,AD_Org_ID,AD_Role_ID,Created,CreatedBy,IsActive,U_RoleMenu_ID,U_WebMenu_ID,Updated,UpdatedBy) VALUES (11,0,102,TO_TIMESTAMP('2008-11-15 18:12:49','YYYY-MM-DD HH24:MI:SS'),100,'Y',50106,50301,TO_TIMESTAMP('2008-11-15 18:12:49','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 6:12:50 PM EET
-- Posterita Sample Data
INSERT INTO U_RoleMenu (AD_Client_ID,AD_Org_ID,AD_Role_ID,Created,CreatedBy,IsActive,U_RoleMenu_ID,U_WebMenu_ID,Updated,UpdatedBy) VALUES (11,0,102,TO_TIMESTAMP('2008-11-15 18:12:49','YYYY-MM-DD HH24:MI:SS'),100,'Y',50107,50308,TO_TIMESTAMP('2008-11-15 18:12:49','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 6:12:51 PM EET
-- Posterita Sample Data
INSERT INTO U_RoleMenu (AD_Client_ID,AD_Org_ID,AD_Role_ID,Created,CreatedBy,IsActive,U_RoleMenu_ID,U_WebMenu_ID,Updated,UpdatedBy) VALUES (11,0,102,TO_TIMESTAMP('2008-11-15 18:12:50','YYYY-MM-DD HH24:MI:SS'),100,'Y',50108,50315,TO_TIMESTAMP('2008-11-15 18:12:50','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 6:12:51 PM EET
-- Posterita Sample Data
INSERT INTO U_RoleMenu (AD_Client_ID,AD_Org_ID,AD_Role_ID,Created,CreatedBy,IsActive,U_RoleMenu_ID,U_WebMenu_ID,Updated,UpdatedBy) VALUES (11,0,102,TO_TIMESTAMP('2008-11-15 18:12:51','YYYY-MM-DD HH24:MI:SS'),100,'Y',50109,50352,TO_TIMESTAMP('2008-11-15 18:12:51','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 6:12:52 PM EET
-- Posterita Sample Data
INSERT INTO U_RoleMenu (AD_Client_ID,AD_Org_ID,AD_Role_ID,Created,CreatedBy,IsActive,U_RoleMenu_ID,U_WebMenu_ID,Updated,UpdatedBy) VALUES (11,0,102,TO_TIMESTAMP('2008-11-15 18:12:52','YYYY-MM-DD HH24:MI:SS'),100,'Y',50110,50326,TO_TIMESTAMP('2008-11-15 18:12:52','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 6:12:53 PM EET
-- Posterita Sample Data
INSERT INTO U_RoleMenu (AD_Client_ID,AD_Org_ID,AD_Role_ID,Created,CreatedBy,IsActive,U_RoleMenu_ID,U_WebMenu_ID,Updated,UpdatedBy) VALUES (11,0,102,TO_TIMESTAMP('2008-11-15 18:12:52','YYYY-MM-DD HH24:MI:SS'),100,'Y',50111,50314,TO_TIMESTAMP('2008-11-15 18:12:52','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 6:12:54 PM EET
-- Posterita Sample Data
INSERT INTO U_RoleMenu (AD_Client_ID,AD_Org_ID,AD_Role_ID,Created,CreatedBy,IsActive,U_RoleMenu_ID,U_WebMenu_ID,Updated,UpdatedBy) VALUES (11,0,102,TO_TIMESTAMP('2008-11-15 18:12:53','YYYY-MM-DD HH24:MI:SS'),100,'Y',50112,50287,TO_TIMESTAMP('2008-11-15 18:12:53','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 6:12:54 PM EET
-- Posterita Sample Data
INSERT INTO U_RoleMenu (AD_Client_ID,AD_Org_ID,AD_Role_ID,Created,CreatedBy,IsActive,U_RoleMenu_ID,U_WebMenu_ID,Updated,UpdatedBy) VALUES (11,0,102,TO_TIMESTAMP('2008-11-15 18:12:54','YYYY-MM-DD HH24:MI:SS'),100,'Y',50113,50340,TO_TIMESTAMP('2008-11-15 18:12:54','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 6:12:55 PM EET
-- Posterita Sample Data
INSERT INTO U_RoleMenu (AD_Client_ID,AD_Org_ID,AD_Role_ID,Created,CreatedBy,IsActive,U_RoleMenu_ID,U_WebMenu_ID,Updated,UpdatedBy) VALUES (11,0,102,TO_TIMESTAMP('2008-11-15 18:12:54','YYYY-MM-DD HH24:MI:SS'),100,'Y',50114,50306,TO_TIMESTAMP('2008-11-15 18:12:54','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 6:12:56 PM EET
-- Posterita Sample Data
INSERT INTO U_RoleMenu (AD_Client_ID,AD_Org_ID,AD_Role_ID,Created,CreatedBy,IsActive,U_RoleMenu_ID,U_WebMenu_ID,Updated,UpdatedBy) VALUES (11,0,102,TO_TIMESTAMP('2008-11-15 18:12:55','YYYY-MM-DD HH24:MI:SS'),100,'Y',50115,50342,TO_TIMESTAMP('2008-11-15 18:12:55','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 6:12:57 PM EET
-- Posterita Sample Data
INSERT INTO U_RoleMenu (AD_Client_ID,AD_Org_ID,AD_Role_ID,Created,CreatedBy,IsActive,U_RoleMenu_ID,U_WebMenu_ID,Updated,UpdatedBy) VALUES (11,0,102,TO_TIMESTAMP('2008-11-15 18:12:56','YYYY-MM-DD HH24:MI:SS'),100,'Y',50116,50323,TO_TIMESTAMP('2008-11-15 18:12:56','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 6:12:57 PM EET
-- Posterita Sample Data
INSERT INTO U_RoleMenu (AD_Client_ID,AD_Org_ID,AD_Role_ID,Created,CreatedBy,IsActive,U_RoleMenu_ID,U_WebMenu_ID,Updated,UpdatedBy) VALUES (11,0,102,TO_TIMESTAMP('2008-11-15 18:12:57','YYYY-MM-DD HH24:MI:SS'),100,'Y',50117,50341,TO_TIMESTAMP('2008-11-15 18:12:57','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 6:12:58 PM EET
-- Posterita Sample Data
INSERT INTO U_RoleMenu (AD_Client_ID,AD_Org_ID,AD_Role_ID,Created,CreatedBy,IsActive,U_RoleMenu_ID,U_WebMenu_ID,Updated,UpdatedBy) VALUES (11,0,102,TO_TIMESTAMP('2008-11-15 18:12:57','YYYY-MM-DD HH24:MI:SS'),100,'Y',50118,50299,TO_TIMESTAMP('2008-11-15 18:12:57','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 6:12:59 PM EET
-- Posterita Sample Data
INSERT INTO U_RoleMenu (AD_Client_ID,AD_Org_ID,AD_Role_ID,Created,CreatedBy,IsActive,U_RoleMenu_ID,U_WebMenu_ID,Updated,UpdatedBy) VALUES (11,0,102,TO_TIMESTAMP('2008-11-15 18:12:58','YYYY-MM-DD HH24:MI:SS'),100,'Y',50119,50295,TO_TIMESTAMP('2008-11-15 18:12:58','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 6:12:59 PM EET
-- Posterita Sample Data
INSERT INTO U_RoleMenu (AD_Client_ID,AD_Org_ID,AD_Role_ID,Created,CreatedBy,IsActive,U_RoleMenu_ID,U_WebMenu_ID,Updated,UpdatedBy) VALUES (11,0,102,TO_TIMESTAMP('2008-11-15 18:12:59','YYYY-MM-DD HH24:MI:SS'),100,'Y',50120,50345,TO_TIMESTAMP('2008-11-15 18:12:59','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 6:13:00 PM EET
-- Posterita Sample Data
INSERT INTO U_RoleMenu (AD_Client_ID,AD_Org_ID,AD_Role_ID,Created,CreatedBy,IsActive,U_RoleMenu_ID,U_WebMenu_ID,Updated,UpdatedBy) VALUES (11,0,102,TO_TIMESTAMP('2008-11-15 18:12:59','YYYY-MM-DD HH24:MI:SS'),100,'Y',50121,50282,TO_TIMESTAMP('2008-11-15 18:12:59','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 6:13:00 PM EET
-- Posterita Sample Data
INSERT INTO U_RoleMenu (AD_Client_ID,AD_Org_ID,AD_Role_ID,Created,CreatedBy,IsActive,U_RoleMenu_ID,U_WebMenu_ID,Updated,UpdatedBy) VALUES (11,0,102,TO_TIMESTAMP('2008-11-15 18:13:00','YYYY-MM-DD HH24:MI:SS'),100,'Y',50122,50300,TO_TIMESTAMP('2008-11-15 18:13:00','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 6:13:01 PM EET
-- Posterita Sample Data
INSERT INTO U_RoleMenu (AD_Client_ID,AD_Org_ID,AD_Role_ID,Created,CreatedBy,IsActive,U_RoleMenu_ID,U_WebMenu_ID,Updated,UpdatedBy) VALUES (11,0,102,TO_TIMESTAMP('2008-11-15 18:13:01','YYYY-MM-DD HH24:MI:SS'),100,'Y',50123,50286,TO_TIMESTAMP('2008-11-15 18:13:01','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 6:13:02 PM EET
-- Posterita Sample Data
INSERT INTO U_RoleMenu (AD_Client_ID,AD_Org_ID,AD_Role_ID,Created,CreatedBy,IsActive,U_RoleMenu_ID,U_WebMenu_ID,Updated,UpdatedBy) VALUES (11,0,102,TO_TIMESTAMP('2008-11-15 18:13:01','YYYY-MM-DD HH24:MI:SS'),100,'Y',50124,50307,TO_TIMESTAMP('2008-11-15 18:13:01','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 6:13:02 PM EET
-- Posterita Sample Data
INSERT INTO U_RoleMenu (AD_Client_ID,AD_Org_ID,AD_Role_ID,Created,CreatedBy,IsActive,U_RoleMenu_ID,U_WebMenu_ID,Updated,UpdatedBy) VALUES (11,0,102,TO_TIMESTAMP('2008-11-15 18:13:02','YYYY-MM-DD HH24:MI:SS'),100,'Y',50125,50310,TO_TIMESTAMP('2008-11-15 18:13:02','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 6:13:03 PM EET
-- Posterita Sample Data
INSERT INTO U_RoleMenu (AD_Client_ID,AD_Org_ID,AD_Role_ID,Created,CreatedBy,IsActive,U_RoleMenu_ID,U_WebMenu_ID,Updated,UpdatedBy) VALUES (11,0,102,TO_TIMESTAMP('2008-11-15 18:13:02','YYYY-MM-DD HH24:MI:SS'),100,'Y',50126,50353,TO_TIMESTAMP('2008-11-15 18:13:02','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 6:13:04 PM EET
-- Posterita Sample Data
INSERT INTO U_RoleMenu (AD_Client_ID,AD_Org_ID,AD_Role_ID,Created,CreatedBy,IsActive,U_RoleMenu_ID,U_WebMenu_ID,Updated,UpdatedBy) VALUES (11,0,102,TO_TIMESTAMP('2008-11-15 18:13:03','YYYY-MM-DD HH24:MI:SS'),100,'Y',50127,50338,TO_TIMESTAMP('2008-11-15 18:13:03','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 6:13:05 PM EET
-- Posterita Sample Data
INSERT INTO U_RoleMenu (AD_Client_ID,AD_Org_ID,AD_Role_ID,Created,CreatedBy,IsActive,U_RoleMenu_ID,U_WebMenu_ID,Updated,UpdatedBy) VALUES (11,0,102,TO_TIMESTAMP('2008-11-15 18:13:04','YYYY-MM-DD HH24:MI:SS'),100,'Y',50128,50329,TO_TIMESTAMP('2008-11-15 18:13:04','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 6:13:06 PM EET
-- Posterita Sample Data
INSERT INTO U_RoleMenu (AD_Client_ID,AD_Org_ID,AD_Role_ID,Created,CreatedBy,IsActive,U_RoleMenu_ID,U_WebMenu_ID,Updated,UpdatedBy) VALUES (11,0,102,TO_TIMESTAMP('2008-11-15 18:13:05','YYYY-MM-DD HH24:MI:SS'),100,'Y',50129,50294,TO_TIMESTAMP('2008-11-15 18:13:05','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 6:13:06 PM EET
-- Posterita Sample Data
INSERT INTO U_RoleMenu (AD_Client_ID,AD_Org_ID,AD_Role_ID,Created,CreatedBy,IsActive,U_RoleMenu_ID,U_WebMenu_ID,Updated,UpdatedBy) VALUES (11,0,102,TO_TIMESTAMP('2008-11-15 18:13:06','YYYY-MM-DD HH24:MI:SS'),100,'Y',50130,50335,TO_TIMESTAMP('2008-11-15 18:13:06','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 6:13:07 PM EET
-- Posterita Sample Data
INSERT INTO U_RoleMenu (AD_Client_ID,AD_Org_ID,AD_Role_ID,Created,CreatedBy,IsActive,U_RoleMenu_ID,U_WebMenu_ID,Updated,UpdatedBy) VALUES (11,0,102,TO_TIMESTAMP('2008-11-15 18:13:06','YYYY-MM-DD HH24:MI:SS'),100,'Y',50131,50304,TO_TIMESTAMP('2008-11-15 18:13:06','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 6:13:08 PM EET
-- Posterita Sample Data
INSERT INTO U_RoleMenu (AD_Client_ID,AD_Org_ID,AD_Role_ID,Created,CreatedBy,IsActive,U_RoleMenu_ID,U_WebMenu_ID,Updated,UpdatedBy) VALUES (11,0,102,TO_TIMESTAMP('2008-11-15 18:13:07','YYYY-MM-DD HH24:MI:SS'),100,'Y',50132,50316,TO_TIMESTAMP('2008-11-15 18:13:07','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 6:13:09 PM EET
-- Posterita Sample Data
INSERT INTO U_RoleMenu (AD_Client_ID,AD_Org_ID,AD_Role_ID,Created,CreatedBy,IsActive,U_RoleMenu_ID,U_WebMenu_ID,Updated,UpdatedBy) VALUES (11,0,102,TO_TIMESTAMP('2008-11-15 18:13:08','YYYY-MM-DD HH24:MI:SS'),100,'Y',50133,50318,TO_TIMESTAMP('2008-11-15 18:13:08','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 6:13:10 PM EET
-- Posterita Sample Data
INSERT INTO U_RoleMenu (AD_Client_ID,AD_Org_ID,AD_Role_ID,Created,CreatedBy,IsActive,U_RoleMenu_ID,U_WebMenu_ID,Updated,UpdatedBy) VALUES (11,0,102,TO_TIMESTAMP('2008-11-15 18:13:09','YYYY-MM-DD HH24:MI:SS'),100,'Y',50134,50285,TO_TIMESTAMP('2008-11-15 18:13:09','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 6:13:11 PM EET
-- Posterita Sample Data
INSERT INTO U_RoleMenu (AD_Client_ID,AD_Org_ID,AD_Role_ID,Created,CreatedBy,IsActive,U_RoleMenu_ID,U_WebMenu_ID,Updated,UpdatedBy) VALUES (11,0,102,TO_TIMESTAMP('2008-11-15 18:13:10','YYYY-MM-DD HH24:MI:SS'),100,'Y',50135,50312,TO_TIMESTAMP('2008-11-15 18:13:10','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 6:13:12 PM EET
-- Posterita Sample Data
INSERT INTO U_RoleMenu (AD_Client_ID,AD_Org_ID,AD_Role_ID,Created,CreatedBy,IsActive,U_RoleMenu_ID,U_WebMenu_ID,Updated,UpdatedBy) VALUES (11,0,102,TO_TIMESTAMP('2008-11-15 18:13:11','YYYY-MM-DD HH24:MI:SS'),100,'Y',50136,50320,TO_TIMESTAMP('2008-11-15 18:13:11','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 6:35:03 PM EET
-- Posterita Sample Data
INSERT INTO U_RoleMenu (AD_Client_ID,AD_Org_ID,AD_Role_ID,Created,CreatedBy,IsActive,U_RoleMenu_ID,U_WebMenu_ID,Updated,UpdatedBy) VALUES (0,0,102,TO_TIMESTAMP('2008-11-15 18:34:41','YYYY-MM-DD HH24:MI:SS'),100,'Y',50137,50303,TO_TIMESTAMP('2008-11-15 18:34:41','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 6:35:12 PM EET
-- Posterita Sample Data
INSERT INTO U_RoleMenu (AD_Client_ID,AD_Org_ID,AD_Role_ID,Created,CreatedBy,IsActive,U_RoleMenu_ID,U_WebMenu_ID,Updated,UpdatedBy) VALUES (11,0,102,TO_TIMESTAMP('2008-11-15 18:35:11','YYYY-MM-DD HH24:MI:SS'),100,'Y',50138,50332,TO_TIMESTAMP('2008-11-15 18:35:11','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 6:35:13 PM EET
-- Posterita Sample Data
INSERT INTO U_RoleMenu (AD_Client_ID,AD_Org_ID,AD_Role_ID,Created,CreatedBy,IsActive,U_RoleMenu_ID,U_WebMenu_ID,Updated,UpdatedBy) VALUES (11,0,102,TO_TIMESTAMP('2008-11-15 18:35:12','YYYY-MM-DD HH24:MI:SS'),100,'Y',50139,50339,TO_TIMESTAMP('2008-11-15 18:35:12','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 6:35:15 PM EET
-- Posterita Sample Data
INSERT INTO U_RoleMenu (AD_Client_ID,AD_Org_ID,AD_Role_ID,Created,CreatedBy,IsActive,U_RoleMenu_ID,U_WebMenu_ID,Updated,UpdatedBy) VALUES (11,0,102,TO_TIMESTAMP('2008-11-15 18:35:13','YYYY-MM-DD HH24:MI:SS'),100,'Y',50140,50337,TO_TIMESTAMP('2008-11-15 18:35:13','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 6:35:16 PM EET
-- Posterita Sample Data
INSERT INTO U_RoleMenu (AD_Client_ID,AD_Org_ID,AD_Role_ID,Created,CreatedBy,IsActive,U_RoleMenu_ID,U_WebMenu_ID,Updated,UpdatedBy) VALUES (11,0,102,TO_TIMESTAMP('2008-11-15 18:35:15','YYYY-MM-DD HH24:MI:SS'),100,'Y',50141,50298,TO_TIMESTAMP('2008-11-15 18:35:15','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 6:35:16 PM EET
-- Posterita Sample Data
INSERT INTO U_RoleMenu (AD_Client_ID,AD_Org_ID,AD_Role_ID,Created,CreatedBy,IsActive,U_RoleMenu_ID,U_WebMenu_ID,Updated,UpdatedBy) VALUES (11,0,102,TO_TIMESTAMP('2008-11-15 18:35:16','YYYY-MM-DD HH24:MI:SS'),100,'Y',50142,50305,TO_TIMESTAMP('2008-11-15 18:35:16','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 6:35:17 PM EET
-- Posterita Sample Data
INSERT INTO U_RoleMenu (AD_Client_ID,AD_Org_ID,AD_Role_ID,Created,CreatedBy,IsActive,U_RoleMenu_ID,U_WebMenu_ID,Updated,UpdatedBy) VALUES (11,0,102,TO_TIMESTAMP('2008-11-15 18:35:16','YYYY-MM-DD HH24:MI:SS'),100,'Y',50143,50302,TO_TIMESTAMP('2008-11-15 18:35:16','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 6:35:18 PM EET
-- Posterita Sample Data
INSERT INTO U_RoleMenu (AD_Client_ID,AD_Org_ID,AD_Role_ID,Created,CreatedBy,IsActive,U_RoleMenu_ID,U_WebMenu_ID,Updated,UpdatedBy) VALUES (11,0,102,TO_TIMESTAMP('2008-11-15 18:35:17','YYYY-MM-DD HH24:MI:SS'),100,'Y',50144,50313,TO_TIMESTAMP('2008-11-15 18:35:17','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 6:35:19 PM EET
-- Posterita Sample Data
INSERT INTO U_RoleMenu (AD_Client_ID,AD_Org_ID,AD_Role_ID,Created,CreatedBy,IsActive,U_RoleMenu_ID,U_WebMenu_ID,Updated,UpdatedBy) VALUES (11,0,102,TO_TIMESTAMP('2008-11-15 18:35:18','YYYY-MM-DD HH24:MI:SS'),100,'Y',50145,50283,TO_TIMESTAMP('2008-11-15 18:35:18','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:13:57 PM EET
-- Posterita Sample Data
INSERT INTO U_RoleMenu (AD_Client_ID,AD_Org_ID,AD_Role_ID,Created,CreatedBy,IsActive,U_RoleMenu_ID,U_WebMenu_ID,Updated,UpdatedBy) VALUES (11,0,102,TO_TIMESTAMP('2008-11-15 19:13:54','YYYY-MM-DD HH24:MI:SS'),100,'Y',50146,50311,TO_TIMESTAMP('2008-11-15 19:13:54','YYYY-MM-DD HH24:MI:SS'),100)
;


--------------------------------
-- Nov 15, 2008 7:24:33 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:24:30','YYYY-MM-DD HH24:MI:SS'),100,'Y','pmenu.new.reports.id','1000072',50143,TO_TIMESTAMP('2008-11-15 19:24:30','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:24:34 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:24:34','YYYY-MM-DD HH24:MI:SS'),100,'Y','smenu.debtors.id','1000073',50144,TO_TIMESTAMP('2008-11-15 19:24:34','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:24:35 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:24:35','YYYY-MM-DD HH24:MI:SS'),100,'Y','smenu.settle.grn.payment','1000074',50145,TO_TIMESTAMP('2008-11-15 19:24:35','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:24:36 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:24:35','YYYY-MM-DD HH24:MI:SS'),100,'Y','smenu.creditors.id','1000075',50146,TO_TIMESTAMP('2008-11-15 19:24:35','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:24:37 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:24:36','YYYY-MM-DD HH24:MI:SS'),100,'Y','smenu.create.unallocated.ap.payment.id','1000076',50147,TO_TIMESTAMP('2008-11-15 19:24:36','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:24:37 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:24:37','YYYY-MM-DD HH24:MI:SS'),100,'Y','smenu.view.ap.payment.allocation.id','1000077',50148,TO_TIMESTAMP('2008-11-15 19:24:37','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:24:38 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:24:37','YYYY-MM-DD HH24:MI:SS'),100,'Y','Accounting Fact Details','1000081',50149,TO_TIMESTAMP('2008-11-15 19:24:37','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:24:39 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:24:38','YYYY-MM-DD HH24:MI:SS'),100,'Y','Asset Delivery Month','1000083',50150,TO_TIMESTAMP('2008-11-15 19:24:38','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:24:39 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:24:39','YYYY-MM-DD HH24:MI:SS'),100,'Y','Project Margin (Work Order)','1000085',50151,TO_TIMESTAMP('2008-11-15 19:24:39','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:24:40 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:24:39','YYYY-MM-DD HH24:MI:SS'),100,'Y','Transaction Detail','1000086',50152,TO_TIMESTAMP('2008-11-15 19:24:39','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:24:40 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:24:40','YYYY-MM-DD HH24:MI:SS'),100,'Y','Clicks Monthy','1000087',50153,TO_TIMESTAMP('2008-11-15 19:24:40','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:24:41 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:24:41','YYYY-MM-DD HH24:MI:SS'),100,'Y','Project Detail Accounting Report','1000088',50154,TO_TIMESTAMP('2008-11-15 19:24:41','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:24:42 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:24:41','YYYY-MM-DD HH24:MI:SS'),100,'Y','Product Transaction Summary','1000090',50155,TO_TIMESTAMP('2008-11-15 19:24:41','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:24:42 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:24:42','YYYY-MM-DD HH24:MI:SS'),100,'Y','Monthly Invoice','1000091',50156,TO_TIMESTAMP('2008-11-15 19:24:42','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:24:43 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:24:43','YYYY-MM-DD HH24:MI:SS'),100,'Y','Accounting Fact Period','1000092',50157,TO_TIMESTAMP('2008-11-15 19:24:43','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:24:44 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:24:43','YYYY-MM-DD HH24:MI:SS'),100,'Y','Invoice Transactions (Acct)','1000095',50158,TO_TIMESTAMP('2008-11-15 19:24:43','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:24:45 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:24:44','YYYY-MM-DD HH24:MI:SS'),100,'Y','Clicks Unprocessed','1000097',50159,TO_TIMESTAMP('2008-11-15 19:24:44','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:24:46 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:24:45','YYYY-MM-DD HH24:MI:SS'),100,'Y','Product Transaction Value','1000101',50160,TO_TIMESTAMP('2008-11-15 19:24:45','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:24:46 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:24:46','YYYY-MM-DD HH24:MI:SS'),100,'Y','Product Costing Report (old)','1000102',50161,TO_TIMESTAMP('2008-11-15 19:24:46','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:24:47 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:24:46','YYYY-MM-DD HH24:MI:SS'),100,'Y','Accounting Fact Daily','1000103',50162,TO_TIMESTAMP('2008-11-15 19:24:46','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:24:48 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:24:47','YYYY-MM-DD HH24:MI:SS'),100,'Y','Product Cost Detail','1000106',50163,TO_TIMESTAMP('2008-11-15 19:24:47','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:24:48 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:24:48','YYYY-MM-DD HH24:MI:SS'),100,'Y','Invoice Not Realized Gain/Loss','1000110',50164,TO_TIMESTAMP('2008-11-15 19:24:48','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:24:49 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:24:48','YYYY-MM-DD HH24:MI:SS'),100,'Y','Storage Detail','1000111',50165,TO_TIMESTAMP('2008-11-15 19:24:48','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:24:50 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:24:49','YYYY-MM-DD HH24:MI:SS'),100,'Y','UnAllocated Payments','1000114',50166,TO_TIMESTAMP('2008-11-15 19:24:49','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:24:50 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:24:50','YYYY-MM-DD HH24:MI:SS'),100,'Y','UnReconciled Payments','1000118',50167,TO_TIMESTAMP('2008-11-15 19:24:50','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:24:51 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:24:50','YYYY-MM-DD HH24:MI:SS'),100,'Y','Monthly Invoice Vendor','1000119',50168,TO_TIMESTAMP('2008-11-15 19:24:50','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:24:52 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:24:51','YYYY-MM-DD HH24:MI:SS'),100,'Y','Inventory Valuation Report','1000122',50169,TO_TIMESTAMP('2008-11-15 19:24:51','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:24:52 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:24:52','YYYY-MM-DD HH24:MI:SS'),100,'Y','Daily Invoice','1000123',50170,TO_TIMESTAMP('2008-11-15 19:24:52','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:24:53 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:24:52','YYYY-MM-DD HH24:MI:SS'),100,'Y','Shipment Details','1000125',50171,TO_TIMESTAMP('2008-11-15 19:24:52','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:24:54 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:24:53','YYYY-MM-DD HH24:MI:SS'),100,'Y','Order Detail','1000126',50172,TO_TIMESTAMP('2008-11-15 19:24:53','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:24:54 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:24:54','YYYY-MM-DD HH24:MI:SS'),100,'Y','Asset Delivery Details','1000129',50173,TO_TIMESTAMP('2008-11-15 19:24:54','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:24:55 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:24:54','YYYY-MM-DD HH24:MI:SS'),100,'Y','Monthly Invoice Product','1000130',50174,TO_TIMESTAMP('2008-11-15 19:24:54','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:24:56 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:24:55','YYYY-MM-DD HH24:MI:SS'),100,'Y','Order Transactions','1000131',50175,TO_TIMESTAMP('2008-11-15 19:24:55','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:24:57 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:24:56','YYYY-MM-DD HH24:MI:SS'),100,'Y','Trial Balance','1000133',50176,TO_TIMESTAMP('2008-11-15 19:24:56','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:24:57 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:24:57','YYYY-MM-DD HH24:MI:SS'),100,'Y','Create Report','1000135',50177,TO_TIMESTAMP('2008-11-15 19:24:57','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:24:58 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:24:57','YYYY-MM-DD HH24:MI:SS'),100,'Y','Monthly Invoice Prod Cat','1000140',50178,TO_TIMESTAMP('2008-11-15 19:24:57','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:24:59 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:24:58','YYYY-MM-DD HH24:MI:SS'),100,'Y','Open Items','1000141',50179,TO_TIMESTAMP('2008-11-15 19:24:58','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:24:59 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:24:59','YYYY-MM-DD HH24:MI:SS'),100,'Y','Business Partner Open','1000142',50180,TO_TIMESTAMP('2008-11-15 19:24:59','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:25:01 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:24:59','YYYY-MM-DD HH24:MI:SS'),100,'Y','Commission Run Detail','1000078',50181,TO_TIMESTAMP('2008-11-15 19:24:59','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:25:01 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:25:01','YYYY-MM-DD HH24:MI:SS'),100,'Y','RfQ Response','1000079',50182,TO_TIMESTAMP('2008-11-15 19:25:01','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:25:02 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:25:01','YYYY-MM-DD HH24:MI:SS'),100,'Y','Weekly Invoice','1000080',50183,TO_TIMESTAMP('2008-11-15 19:25:01','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:25:03 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:25:02','YYYY-MM-DD HH24:MI:SS'),100,'Y','Open Confirmation Details','1000082',50184,TO_TIMESTAMP('2008-11-15 19:25:02','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:25:04 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:25:03','YYYY-MM-DD HH24:MI:SS'),100,'Y','Material Receipt Details','1000084',50185,TO_TIMESTAMP('2008-11-15 19:25:03','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:25:05 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:25:04','YYYY-MM-DD HH24:MI:SS'),100,'Y','Vendor Selection','1000089',50186,TO_TIMESTAMP('2008-11-15 19:25:04','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:25:06 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:25:05','YYYY-MM-DD HH24:MI:SS'),100,'Y','Material Reference','1000093',50187,TO_TIMESTAMP('2008-11-15 19:25:05','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:25:06 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:25:06','YYYY-MM-DD HH24:MI:SS'),100,'Y','Update Accounting Balance','1000094',50188,TO_TIMESTAMP('2008-11-15 19:25:06','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:25:07 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:25:06','YYYY-MM-DD HH24:MI:SS'),100,'Y','Invoice Tax','1000096',50189,TO_TIMESTAMP('2008-11-15 19:25:06','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:25:07 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:25:07','YYYY-MM-DD HH24:MI:SS'),100,'Y','Customer Assets','1000098',50190,TO_TIMESTAMP('2008-11-15 19:25:07','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:25:08 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:25:08','YYYY-MM-DD HH24:MI:SS'),100,'Y','Open Orders','1000099',50191,TO_TIMESTAMP('2008-11-15 19:25:08','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:25:09 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:25:08','YYYY-MM-DD HH24:MI:SS'),100,'Y','Invoice Detail & Margin','1000100',50192,TO_TIMESTAMP('2008-11-15 19:25:08','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:25:10 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:25:09','YYYY-MM-DD HH24:MI:SS'),100,'Y','Project Lines not Issued','1000104',50193,TO_TIMESTAMP('2008-11-15 19:25:09','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:25:10 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:25:10','YYYY-MM-DD HH24:MI:SS'),100,'Y','RfQ Unanswered','1000105',50194,TO_TIMESTAMP('2008-11-15 19:25:10','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:25:11 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:25:10','YYYY-MM-DD HH24:MI:SS'),100,'Y','Project Cycle Report','1000107',50195,TO_TIMESTAMP('2008-11-15 19:25:10','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:25:12 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:25:11','YYYY-MM-DD HH24:MI:SS'),100,'Y','UnAllocated Invoices','1000108',50196,TO_TIMESTAMP('2008-11-15 19:25:11','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:25:12 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:25:12','YYYY-MM-DD HH24:MI:SS'),100,'Y','Open Confirmations','1000109',50197,TO_TIMESTAMP('2008-11-15 19:25:12','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:25:13 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:25:12','YYYY-MM-DD HH24:MI:SS'),100,'Y','Cash Journal Detail','1000112',50198,TO_TIMESTAMP('2008-11-15 19:25:12','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:25:13 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:25:13','YYYY-MM-DD HH24:MI:SS'),100,'Y','Weekly Invoice Prod Cat','1000113',50199,TO_TIMESTAMP('2008-11-15 19:25:13','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:25:14 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:25:14','YYYY-MM-DD HH24:MI:SS'),100,'Y','Quarter Invoice Product','1000115',50200,TO_TIMESTAMP('2008-11-15 19:25:14','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:25:15 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:25:14','YYYY-MM-DD HH24:MI:SS'),100,'Y','Aging','1000116',50201,TO_TIMESTAMP('2008-11-15 19:25:14','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:25:15 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:25:15','YYYY-MM-DD HH24:MI:SS'),100,'Y','Open Requisitions','1000117',50202,TO_TIMESTAMP('2008-11-15 19:25:15','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:25:16 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:25:16','YYYY-MM-DD HH24:MI:SS'),100,'Y','Allocation','1000120',50203,TO_TIMESTAMP('2008-11-15 19:25:16','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:25:17 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:25:16','YYYY-MM-DD HH24:MI:SS'),100,'Y','Replenish Report','1000121',50204,TO_TIMESTAMP('2008-11-15 19:25:16','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:25:17 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:25:17','YYYY-MM-DD HH24:MI:SS'),100,'Y','Business Partner Detail','1000124',50205,TO_TIMESTAMP('2008-11-15 19:25:17','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:25:18 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:25:17','YYYY-MM-DD HH24:MI:SS'),100,'Y','Quarterly Invoice Customer by Product','1000127',50206,TO_TIMESTAMP('2008-11-15 19:25:17','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:25:19 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:25:18','YYYY-MM-DD HH24:MI:SS'),100,'Y','Statement of Accounts','1000128',50207,TO_TIMESTAMP('2008-11-15 19:25:18','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:25:19 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:25:19','YYYY-MM-DD HH24:MI:SS'),100,'Y','Product Cost Summary','1000132',50208,TO_TIMESTAMP('2008-11-15 19:25:19','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:25:20 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:25:19','YYYY-MM-DD HH24:MI:SS'),100,'Y','Project POs not Issued','1000134',50209,TO_TIMESTAMP('2008-11-15 19:25:19','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:25:21 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:25:20','YYYY-MM-DD HH24:MI:SS'),100,'Y','Payment Details','1000136',50210,TO_TIMESTAMP('2008-11-15 19:25:20','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:25:21 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:25:21','YYYY-MM-DD HH24:MI:SS'),100,'Y','Quarterly Invoice Customer by Vendor','1000137',50211,TO_TIMESTAMP('2008-11-15 19:25:21','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:25:22 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:25:21','YYYY-MM-DD HH24:MI:SS'),100,'Y','Invoice Transactions (Doc)','1000138',50212,TO_TIMESTAMP('2008-11-15 19:25:21','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:25:22 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:25:22','YYYY-MM-DD HH24:MI:SS'),100,'Y','Product Cost','1000139',50213,TO_TIMESTAMP('2008-11-15 19:25:22','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:25:23 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:25:23','YYYY-MM-DD HH24:MI:SS'),100,'Y','pmenu.order.id','1000000',50214,TO_TIMESTAMP('2008-11-15 19:25:23','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:25:24 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:25:23','YYYY-MM-DD HH24:MI:SS'),100,'Y','smenu.posorderwa.id','1000001',50215,TO_TIMESTAMP('2008-11-15 19:25:23','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:25:24 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:25:24','YYYY-MM-DD HH24:MI:SS'),100,'Y','smenu.cash.sales.history.id','1000002',50216,TO_TIMESTAMP('2008-11-15 19:25:24','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:25:25 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:25:24','YYYY-MM-DD HH24:MI:SS'),100,'Y','smenu.custretorder.id','1000003',50217,TO_TIMESTAMP('2008-11-15 19:25:24','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:25:26 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:25:25','YYYY-MM-DD HH24:MI:SS'),100,'Y','smenu.customer.return.history.id','1000004',50218,TO_TIMESTAMP('2008-11-15 19:25:25','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:25:26 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:25:26','YYYY-MM-DD HH24:MI:SS'),100,'Y','smenu.custretorderfrompos.id','1000005',50219,TO_TIMESTAMP('2008-11-15 19:25:26','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:25:27 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:25:27','YYYY-MM-DD HH24:MI:SS'),100,'Y','smenu.currenttillamout.id','1000006',50220,TO_TIMESTAMP('2008-11-15 19:25:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:25:28 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:25:27','YYYY-MM-DD HH24:MI:SS'),100,'Y','smenu.adjust.cashbook.id','1000007',50221,TO_TIMESTAMP('2008-11-15 19:25:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:25:28 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:25:28','YYYY-MM-DD HH24:MI:SS'),100,'Y','smenu.periodiccashbookdetails.id','1000008',50222,TO_TIMESTAMP('2008-11-15 19:25:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:25:29 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:25:28','YYYY-MM-DD HH24:MI:SS'),100,'Y','smenu.cashbookreport.id','1000009',50223,TO_TIMESTAMP('2008-11-15 19:25:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:25:30 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:25:29','YYYY-MM-DD HH24:MI:SS'),100,'Y','smenu.close.pos.till.id','1000010',50224,TO_TIMESTAMP('2008-11-15 19:25:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:25:30 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:25:30','YYYY-MM-DD HH24:MI:SS'),100,'Y','smenu.opencashdrawer.id','1000011',50225,TO_TIMESTAMP('2008-11-15 19:25:30','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:25:31 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:25:30','YYYY-MM-DD HH24:MI:SS'),100,'Y','pmenu.creditsales.id','1000012',50226,TO_TIMESTAMP('2008-11-15 19:25:30','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:25:32 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:25:31','YYYY-MM-DD HH24:MI:SS'),100,'Y','smenu.creditorder.id','1000013',50227,TO_TIMESTAMP('2008-11-15 19:25:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:25:32 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:25:32','YYYY-MM-DD HH24:MI:SS'),100,'Y','smenu.creditmemo.from.creditorder.id','1000014',50228,TO_TIMESTAMP('2008-11-15 19:25:32','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:25:33 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:25:32','YYYY-MM-DD HH24:MI:SS'),100,'Y','smenu.credit.memo.history.id','1000015',50229,TO_TIMESTAMP('2008-11-15 19:25:32','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:25:33 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:25:33','YYYY-MM-DD HH24:MI:SS'),100,'Y','smenu.settlepayment.id','1000016',50230,TO_TIMESTAMP('2008-11-15 19:25:33','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:25:34 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:25:33','YYYY-MM-DD HH24:MI:SS'),100,'Y','smenu.printDunningLetters.id','1000017',50231,TO_TIMESTAMP('2008-11-15 19:25:33','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:25:35 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:25:34','YYYY-MM-DD HH24:MI:SS'),100,'Y','smenu.viewpaymentallocation.id','1000018',50232,TO_TIMESTAMP('2008-11-15 19:25:34','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:25:35 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:25:35','YYYY-MM-DD HH24:MI:SS'),100,'Y','smenu.credit.sales.history.id','1000019',50233,TO_TIMESTAMP('2008-11-15 19:25:35','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:25:36 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:25:35','YYYY-MM-DD HH24:MI:SS'),100,'Y','smenu.create.unallocated.payment.id','1000020',50234,TO_TIMESTAMP('2008-11-15 19:25:35','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:25:37 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:25:36','YYYY-MM-DD HH24:MI:SS'),100,'Y','pmenu.purchases.id','1000021',50235,TO_TIMESTAMP('2008-11-15 19:25:36','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:25:38 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:25:37','YYYY-MM-DD HH24:MI:SS'),100,'Y','smenu.posgoodrecnote.id','1000022',50236,TO_TIMESTAMP('2008-11-15 19:25:37','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:25:38 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:25:38','YYYY-MM-DD HH24:MI:SS'),100,'Y','smenu.posgoodretnote.id','1000023',50237,TO_TIMESTAMP('2008-11-15 19:25:38','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:25:39 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:25:38','YYYY-MM-DD HH24:MI:SS'),100,'Y','smenu.goods.received.note.history.id','1000024',50238,TO_TIMESTAMP('2008-11-15 19:25:38','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:25:39 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:25:39','YYYY-MM-DD HH24:MI:SS'),100,'Y','smenu.goods.returned.note.history.id','1000025',50239,TO_TIMESTAMP('2008-11-15 19:25:39','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:25:40 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:25:40','YYYY-MM-DD HH24:MI:SS'),100,'Y','pmenu.reports.id','1000026',50240,TO_TIMESTAMP('2008-11-15 19:25:40','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:25:41 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:25:40','YYYY-MM-DD HH24:MI:SS'),100,'Y','smenu.custsalesreport.id','1000027',50241,TO_TIMESTAMP('2008-11-15 19:25:40','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:25:41 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:25:41','YYYY-MM-DD HH24:MI:SS'),100,'Y','smenu.posinfo.id','1000028',50242,TO_TIMESTAMP('2008-11-15 19:25:41','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:25:42 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:25:41','YYYY-MM-DD HH24:MI:SS'),100,'Y','smenu.orderhistory.id','1000029',50243,TO_TIMESTAMP('2008-11-15 19:25:41','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:25:43 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:25:42','YYYY-MM-DD HH24:MI:SS'),100,'Y','smenu.documenthistory.id','1000030',50244,TO_TIMESTAMP('2008-11-15 19:25:42','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:25:44 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:25:43','YYYY-MM-DD HH24:MI:SS'),100,'Y','smenu.view.bp.info.id','1000031',50245,TO_TIMESTAMP('2008-11-15 19:25:43','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:25:44 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:25:44','YYYY-MM-DD HH24:MI:SS'),100,'Y','smenu.pos.sales.report.id','1000032',50246,TO_TIMESTAMP('2008-11-15 19:25:44','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:25:45 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:25:44','YYYY-MM-DD HH24:MI:SS'),100,'Y','smenu.best.selling.items','1000033',50247,TO_TIMESTAMP('2008-11-15 19:25:44','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:25:46 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:25:45','YYYY-MM-DD HH24:MI:SS'),100,'Y','smenu.stock.sales.report','1000034',50248,TO_TIMESTAMP('2008-11-15 19:25:45','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:25:46 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:25:46','YYYY-MM-DD HH24:MI:SS'),100,'Y','smenu.pos.purchase.report','1000035',50249,TO_TIMESTAMP('2008-11-15 19:25:46','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:25:47 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:25:46','YYYY-MM-DD HH24:MI:SS'),100,'Y','pmenu.stock.id','1000036',50250,TO_TIMESTAMP('2008-11-15 19:25:46','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:25:48 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:25:47','YYYY-MM-DD HH24:MI:SS'),100,'Y','smenu.posstockmov.id','1000037',50251,TO_TIMESTAMP('2008-11-15 19:25:47','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:25:48 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:25:48','YYYY-MM-DD HH24:MI:SS'),100,'Y','smenu.mystock.id','1000038',50252,TO_TIMESTAMP('2008-11-15 19:25:48','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:25:49 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:25:48','YYYY-MM-DD HH24:MI:SS'),100,'Y','smenu.fastmovitems.id','1000039',50253,TO_TIMESTAMP('2008-11-15 19:25:48','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:25:50 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:25:49','YYYY-MM-DD HH24:MI:SS'),100,'Y','smenu.slowmovitems.id','1000040',50254,TO_TIMESTAMP('2008-11-15 19:25:49','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:25:51 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:25:50','YYYY-MM-DD HH24:MI:SS'),100,'Y','smenu.adjust.stock.id','1000041',50255,TO_TIMESTAMP('2008-11-15 19:25:50','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:25:51 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:25:51','YYYY-MM-DD HH24:MI:SS'),100,'Y','smenu.inventory.history.id','1000042',50256,TO_TIMESTAMP('2008-11-15 19:25:51','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:25:52 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:25:51','YYYY-MM-DD HH24:MI:SS'),100,'Y','smenu.adjust.inventory.id','1000043',50257,TO_TIMESTAMP('2008-11-15 19:25:51','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:25:53 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:25:52','YYYY-MM-DD HH24:MI:SS'),100,'Y','smenu.transfer.stock','1000044',50258,TO_TIMESTAMP('2008-11-15 19:25:52','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:25:54 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:25:53','YYYY-MM-DD HH24:MI:SS'),100,'Y','smenu.inventory.move','1000045',50259,TO_TIMESTAMP('2008-11-15 19:25:53','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:25:55 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:25:54','YYYY-MM-DD HH24:MI:SS'),100,'Y','smenu.move.confirmation','1000046',50260,TO_TIMESTAMP('2008-11-15 19:25:54','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:25:55 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:25:55','YYYY-MM-DD HH24:MI:SS'),100,'Y','pmenu.administration.id','1000047',50261,TO_TIMESTAMP('2008-11-15 19:25:55','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:25:56 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:25:55','YYYY-MM-DD HH24:MI:SS'),100,'Y','smenu.customer.id','1000048',50262,TO_TIMESTAMP('2008-11-15 19:25:55','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:25:57 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:25:56','YYYY-MM-DD HH24:MI:SS'),100,'Y','smenu.createvendor.id','1000049',50263,TO_TIMESTAMP('2008-11-15 19:25:56','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:25:57 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:25:57','YYYY-MM-DD HH24:MI:SS'),100,'Y','smenu.user.id','1000050',50264,TO_TIMESTAMP('2008-11-15 19:25:57','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:25:58 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:25:57','YYYY-MM-DD HH24:MI:SS'),100,'Y','smenu.viewrole.id','1000051',50265,TO_TIMESTAMP('2008-11-15 19:25:57','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:25:59 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:25:58','YYYY-MM-DD HH24:MI:SS'),100,'Y','smenu.products.id','1000052',50266,TO_TIMESTAMP('2008-11-15 19:25:58','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:25:59 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:25:59','YYYY-MM-DD HH24:MI:SS'),100,'Y','smenu.barcode.printing','1000053',50267,TO_TIMESTAMP('2008-11-15 19:25:59','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:26:00 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:25:59','YYYY-MM-DD HH24:MI:SS'),100,'Y','smenu.edit.attribute.value.id','1000054',50268,TO_TIMESTAMP('2008-11-15 19:25:59','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:26:00 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:26:00','YYYY-MM-DD HH24:MI:SS'),100,'Y','smenu.checkSequence.id','1000055',50269,TO_TIMESTAMP('2008-11-15 19:26:00','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:26:01 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:26:01','YYYY-MM-DD HH24:MI:SS'),100,'Y','smenu.generatecommission.id','1000056',50270,TO_TIMESTAMP('2008-11-15 19:26:01','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:26:02 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:26:01','YYYY-MM-DD HH24:MI:SS'),100,'Y','smenu.viewgeneratedcommission.id','1000057',50271,TO_TIMESTAMP('2008-11-15 19:26:01','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:26:03 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:26:02','YYYY-MM-DD HH24:MI:SS'),100,'Y','smenu.viewpaymentterm.id','1000058',50272,TO_TIMESTAMP('2008-11-15 19:26:02','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:26:03 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:26:03','YYYY-MM-DD HH24:MI:SS'),100,'Y','smenu.tax.id','1000059',50273,TO_TIMESTAMP('2008-11-15 19:26:03','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:26:04 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:26:03','YYYY-MM-DD HH24:MI:SS'),100,'Y','smenu.viewpreferences.id','1000060',50274,TO_TIMESTAMP('2008-11-15 19:26:03','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:26:05 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:26:04','YYYY-MM-DD HH24:MI:SS'),100,'Y','smenu.bpartners.id','1000061',50275,TO_TIMESTAMP('2008-11-15 19:26:04','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:26:06 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:26:05','YYYY-MM-DD HH24:MI:SS'),100,'Y','smenu.price.check','1000062',50276,TO_TIMESTAMP('2008-11-15 19:26:05','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:26:06 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:26:06','YYYY-MM-DD HH24:MI:SS'),100,'Y','smenu.organisation','1000063',50277,TO_TIMESTAMP('2008-11-15 19:26:06','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:26:07 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:26:06','YYYY-MM-DD HH24:MI:SS'),100,'Y','smenu.price.list','1000064',50278,TO_TIMESTAMP('2008-11-15 19:26:06','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:26:08 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:26:07','YYYY-MM-DD HH24:MI:SS'),100,'Y','smenu.currency','1000065',50279,TO_TIMESTAMP('2008-11-15 19:26:07','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:26:08 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:26:08','YYYY-MM-DD HH24:MI:SS'),100,'Y','smenu.delete.price.on.pricelist','1000066',50280,TO_TIMESTAMP('2008-11-15 19:26:08','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:26:09 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:26:08','YYYY-MM-DD HH24:MI:SS'),100,'Y','smenu.cashbook','1000067',50281,TO_TIMESTAMP('2008-11-15 19:26:08','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:26:10 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:26:09','YYYY-MM-DD HH24:MI:SS'),100,'Y','smenu.terminal','1000068',50282,TO_TIMESTAMP('2008-11-15 19:26:09','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:26:10 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:26:10','YYYY-MM-DD HH24:MI:SS'),100,'Y','pmenu.help.id','1000069',50283,TO_TIMESTAMP('2008-11-15 19:26:10','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:26:11 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:26:11','YYYY-MM-DD HH24:MI:SS'),100,'Y','smenu.user.manual','1000070',50284,TO_TIMESTAMP('2008-11-15 19:26:11','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 15, 2008 7:26:12 PM EET
-- Posterita Sample Data
INSERT INTO U_Web_Properties (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,U_Key,U_Value,U_Web_Properties_ID,Updated,UpdatedBy) VALUES (0,0,TO_TIMESTAMP('2008-11-15 19:26:11','YYYY-MM-DD HH24:MI:SS'),100,'Y','smenu.contactus','1000071',50285,TO_TIMESTAMP('2008-11-15 19:26:11','YYYY-MM-DD HH24:MI:SS'),100)
;


--------------------------------
