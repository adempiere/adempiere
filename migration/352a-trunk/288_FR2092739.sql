-- Aug 27, 2008 1:30:17 PM EEST
-- Update PP_Order.M_Product_ID column: set callout and reference value
UPDATE AD_Column SET Callout='org.eevolution.model.CalloutOrder.product',Updated=TO_DATE('2008-08-27 13:30:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=53623
;
UPDATE AD_Column SET AD_Reference_Value_ID=211,Updated=TO_DATE('2008-08-27 13:31:38','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=53623
;
