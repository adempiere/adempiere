-- 10/08/2009 5:28:26 PM
-- BF2038848 Product Planning window - Order Policy
UPDATE AD_Ref_List SET Name='Lot-for-Lot',Updated=TO_TIMESTAMP('2009-08-10 17:28:26','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Ref_List_ID=53273
;

-- 10/08/2009 5:28:26 PM
-- BF2038848 Product Planning window - Order Policy
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=53273
;

-- 10/08/2009 5:35:01 PM
-- BF2038848 Product Planning window - Order Policy
insert into t_alter_column values('pp_product_planning','Order_Policy','VARCHAR(3)',null,'NULL')
;

