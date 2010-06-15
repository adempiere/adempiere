-- Apr 15, 2010 6:49:16 PM CEST
-- BF [2863381] - absolute paths in webshop
-- https://sourceforge.net/tracker/?func=detail&aid=2863381&group_id=176962&atid=879332
UPDATE AD_Table SET AccessLevel='6',Updated=TO_TIMESTAMP('2010-04-15 18:49:15','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Table_ID=461
;

-- Apr 15, 2010 6:52:03 PM CEST
-- BF [2863381] - absolute paths in webshop
-- https://sourceforge.net/tracker/?func=detail&aid=2863381&group_id=176962&atid=879332
UPDATE AD_Image SET ImageURL=NULL, Name='gwr_footertile.jpg',Updated=TO_TIMESTAMP('2010-04-15 18:52:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Image_ID=110
;

-- Apr 15, 2010 6:58:10 PM CEST
-- BF [2863381] - absolute paths in webshop
-- https://sourceforge.net/tracker/?func=detail&aid=2863381&group_id=176962&atid=879332
UPDATE AD_Image SET UpdatedBy=100, ImageURL=NULL, Name='gwr_header.jpg',Updated=TO_TIMESTAMP('2010-04-15 18:58:10','YYYY-MM-DD HH24:MI:SS') WHERE AD_Image_ID=109
;


