-- Dec 8, 2010 12:41:03 AM COT
-- FR3132075-Over/Under payment must be enabled by default
UPDATE AD_Column SET DefaultValue='Y',Updated=TO_DATE('2010-12-08 00:41:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=7035
;

-- Dec 8, 2010 12:41:07 AM COT
ALTER TABLE C_Payment MODIFY IsOverUnderPayment CHAR(1) DEFAULT 'Y'
;

