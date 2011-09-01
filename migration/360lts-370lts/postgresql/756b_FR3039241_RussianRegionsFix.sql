-- 18-dic-2010 12:46:11 COT
-- Russian Country regions
UPDATE C_Country SET RegionName='Federal Subject',Updated=TO_TIMESTAMP('2010-12-18 12:46:11','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE C_Country_ID=286
;

-- 18-dic-2010 12:46:30 COT
UPDATE C_Country_Trl SET IsTranslated='Y',RegionName='Sujeto Federal',Updated=TO_TIMESTAMP('2010-12-18 12:46:30','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE C_Country_ID=286 AND AD_Language LIKE 'es_%'
;

