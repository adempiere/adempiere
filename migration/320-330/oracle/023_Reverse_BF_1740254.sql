--
-- [ 1740254 ] PriceList Version is empty in Product-Price tab
-- http://sourceforge.net/tracker/index.php?func=detail&aid=1740254&group_id=176962&atid=879332
--
-- Column M_ProductPrice.M_PriceList_Version_ID:

-- Original File 012_BF_1740254.sql was deleted - the original file put the IsParent='N'
-- restoring past value from IsParent because somebody could apply this patch into their system

UPDATE AD_COLUMN SET IsParent='Y' WHERE AD_Column_ID=2760;

COMMIT;
