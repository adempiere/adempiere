-- FR [2978440] - Hide non working fields
-- https://sourceforge.net/tracker/index.php?func=detail&aid=2978440&group_id=176962&atid=879332#
-- forum post - https://sourceforge.net/projects/adempiere/forums/forum/610546/topic/3421517/index/page/1
UPDATE AD_Field SET IsActive='N', IsDisplayed='N',Updated=TO_TIMESTAMP('2009-10-06 23:54:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=54055
;

UPDATE AD_Ref_List SET IsActive='N',Updated=TO_TIMESTAMP('2009-10-07 00:29:32','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Ref_List_ID=723
;

