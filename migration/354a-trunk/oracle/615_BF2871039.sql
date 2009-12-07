-- Dec 3, 2009 12:09:29 AM CST
-- Fix BOM and Parent Tax Rate
UPDATE AD_Tab SET TabLevel=1,Updated=TO_DATE('2009-12-03 00:09:29','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Tab_ID=53078
;

-- Dec 3, 2009 12:10:10 AM CST
-- Fix BOM and Parent Tax Rate
UPDATE AD_Field SET DefaultValue='@C_Tax_ID@',Updated=TO_DATE('2009-12-03 00:10:10','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=54489
;

-- Dec 3, 2009 12:12:51 AM CST
-- Fix BOM and Parent Tax Rate
UPDATE AD_Tab SET Parent_Column_ID=2240,Updated=TO_DATE('2009-12-03 00:12:51','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Tab_ID=53078
;

-- Dec 3, 2009 12:14:48 AM CST
-- Fix BOM and Parent Tax Rate
UPDATE AD_Field SET DefaultValue='@Parent_Tax_ID@',Updated=TO_DATE('2009-12-03 00:14:48','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=54489
;

-- Dec 3, 2009 12:19:07 AM CST
-- Fix BOM and Parent Tax Rate
UPDATE AD_Field SET DefaultValue='@0|C_Tax_ID@',Updated=TO_DATE('2009-12-03 00:19:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=54489
;