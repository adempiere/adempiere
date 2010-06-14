-- 06-08-2010 12:47:16 PM CST
-- IsCreatePlan default value=Y
UPDATE AD_Column SET DefaultValue='Y',Updated=TO_DATE('2010-06-08 12:47:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=53382
;

-- Jun 14, 2010 1:50:44 PM COT
-- FR3013401_IsCreatePlan default value=Y
ALTER TABLE PP_Product_Planning MODIFY IsCreatePlan CHAR(1) DEFAULT 'Y'
;

-- Jun 14, 2010 1:50:45 PM COT
-- FR3013401_IsCreatePlan default value=Y
UPDATE PP_Product_Planning SET IsCreatePlan='Y' WHERE IsCreatePlan IS NULL
;

