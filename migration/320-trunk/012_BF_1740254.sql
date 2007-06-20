--
-- [ 1740254 ] PriceList Version is empty in Product-Price tab
-- http://sourceforge.net/tracker/index.php?func=detail&aid=1740254&group_id=176962&atid=879332
--
-- Column M_ProductPrice.M_PriceList_Version_ID:
UPDATE AD_Column SET IsParent='N' WHERE AD_Column_ID=2760;
--
COMMIT;
