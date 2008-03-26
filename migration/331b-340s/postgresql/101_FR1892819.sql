-- Review optional scripts at the end

-- Feb 13, 2008 10:31:57 AM COT
-- 1892819 - Field Phone Format
UPDATE AD_Column SET Callout='org.adempiere.model.CalloutBPartnerLocation.formatPhone',Updated=TO_TIMESTAMP('2008-02-13 10:31:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=2959
;

-- Please uncomment following lines if you prefer to preserve old functionality - this is: not formatting phones

/*

UPDATE C_Country SET ExpressionPhone=NULL,Updated=TO_TIMESTAMP('2008-02-13 10:32:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE C_Country_ID=100
;

UPDATE C_Country SET ExpressionPhone=NULL,Updated=TO_TIMESTAMP('2008-02-13 10:33:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE C_Country_ID=122
;

*/