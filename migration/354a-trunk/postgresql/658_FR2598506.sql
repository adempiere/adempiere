-- Jan 15, 2010 8:58:38 AM COT
-- Fix city dynamic validation on Initial Client Setup Process
UPDATE AD_Val_Rule SET Code='((@C_Region_ID@>0 AND C_City.C_Region_ID=@C_Region_ID@) OR (@C_Region_ID@=0 AND C_City.C_Country_ID=@C_Country_ID@ AND C_City.C_Region_ID IS NULL))', Description='Cities of region if C_Region_ID is set, otherwise cities without region',Updated=TO_TIMESTAMP('2010-01-15 08:58:38','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Val_Rule_ID=52045
;

