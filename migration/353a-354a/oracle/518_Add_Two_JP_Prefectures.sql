-- Aug 4, 2009 11:39:02 PM EEST
-- Add missing Janap regions
INSERT INTO C_Region (AD_Client_ID,AD_Org_ID,C_Country_ID,Created,CreatedBy,C_Region_ID,IsActive,IsDefault,Name,Updated,UpdatedBy) VALUES (0,0,216,TO_DATE('2009-08-04 23:39:00','YYYY-MM-DD HH24:MI:SS'),100,50000,'Y','N','栃木県(Tochigi)',TO_DATE('2009-08-04 23:39:00','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 4, 2009 11:39:24 PM EEST
-- Add missing Janap regions
UPDATE C_Region SET Description='栃木県(Tochigi)', Name='栃木県',Updated=TO_DATE('2009-08-04 23:39:24','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE C_Region_ID=50000
;

-- Aug 4, 2009 11:40:01 PM EEST
-- Add missing Janap regions
INSERT INTO C_Region (AD_Client_ID,AD_Org_ID,C_Country_ID,Created,CreatedBy,C_Region_ID,Description,IsActive,IsDefault,Name,Updated,UpdatedBy) VALUES (0,0,216,TO_DATE('2009-08-04 23:40:00','YYYY-MM-DD HH24:MI:SS'),100,50001,'長野県(Nagano)','Y','N','長野県',TO_DATE('2009-08-04 23:40:00','YYYY-MM-DD HH24:MI:SS'),100)
;

