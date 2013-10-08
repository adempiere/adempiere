-- Mar 29, 2011 2:31:13 PM COT
-- Fix dictionary error - parent column not defined for AD_TreeBar
UPDATE AD_Column SET IsParent='Y', IsUpdateable='N',Updated=TO_TIMESTAMP('2011-03-29 14:31:13','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=6213
;
