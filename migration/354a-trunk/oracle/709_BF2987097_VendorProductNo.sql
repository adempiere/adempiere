-- Apr 14, 2010 2:13:17 PM CEST
-- BF [2987097] - Wrong column size: VendorProductNo
-- https://sourceforge.net/tracker/?func=detail&aid=2987097&group_id=176962&atid=879332
UPDATE AD_Column SET FieldLength=40,Updated=TO_DATE('2010-04-14 14:13:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=7818
;

-- Apr 14, 2010 2:13:22 PM CEST
-- BF [2987097] - Wrong column size: VendorProductNo
-- https://sourceforge.net/tracker/?func=detail&aid=2987097&group_id=176962&atid=879332
ALTER TABLE I_Product MODIFY VendorProductNo NVARCHAR2(40) DEFAULT NULL 
;

-- Apr 14, 2010 2:14:09 PM CEST
-- BF [2987097] - Wrong column size: VendorProductNo
-- https://sourceforge.net/tracker/?func=detail&aid=2987097&group_id=176962&atid=879332
UPDATE AD_Column SET FieldLength=40,Updated=TO_DATE('2010-04-14 14:14:09','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=2709
;

-- Apr 14, 2010 2:14:15 PM CEST
-- BF [2987097] - Wrong column size: VendorProductNo
-- https://sourceforge.net/tracker/?func=detail&aid=2987097&group_id=176962&atid=879332
ALTER TABLE M_Product_PO MODIFY VendorProductNo NVARCHAR2(40)
;

