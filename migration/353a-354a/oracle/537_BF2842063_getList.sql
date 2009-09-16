-- Aug 21, 2009 11:39:07 AM COT
-- [ adempiere-Libero-2842063 ] getList not working
UPDATE AD_Column SET IsMandatory='Y',Updated=TO_DATE('2009-08-21 11:39:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=55008
;

-- Aug 21, 2009 11:39:22 AM COT
-- [ adempiere-Libero-2842063 ] getList not working
UPDATE AD_Column SET AD_Reference_ID=15,Updated=TO_DATE('2009-08-21 11:39:22','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=55008
;

-- Aug 21, 2009 11:39:23 AM COT
-- [ adempiere-Libero-2842063 ] getList not working
ALTER TABLE HR_ListVersion MODIFY ValidFrom DATE
;

-- Aug 21, 2009 11:39:24 AM COT
-- [ adempiere-Libero-2842063 ] getList not working
ALTER TABLE HR_ListVersion MODIFY ValidFrom NOT NULL
;

-- Aug 21, 2009 11:39:31 AM COT
-- [ adempiere-Libero-2842063 ] getList not working
UPDATE AD_Column SET AD_Reference_ID=15, IsMandatory='Y',Updated=TO_DATE('2009-08-21 11:39:31','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=55010
;

-- Aug 21, 2009 11:39:32 AM COT
-- [ adempiere-Libero-2842063 ] getList not working
ALTER TABLE HR_ListVersion MODIFY ValidTo DATE
;

-- Aug 21, 2009 11:39:32 AM COT
-- [ adempiere-Libero-2842063 ] getList not working
ALTER TABLE HR_ListVersion MODIFY ValidTo NOT NULL
;

-- Aug 21, 2009 11:40:09 AM COT
-- [ adempiere-Libero-2842063 ] getList not working
UPDATE AD_Column SET IsMandatory='Y',Updated=TO_DATE('2009-08-21 11:40:09','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=55027
;

-- Aug 21, 2009 11:40:10 AM COT
-- [ adempiere-Libero-2842063 ] getList not working
ALTER TABLE HR_ListLine MODIFY MinValue NUMBER
;

-- Aug 21, 2009 11:40:10 AM COT
-- [ adempiere-Libero-2842063 ] getList not working
ALTER TABLE HR_ListLine MODIFY MinValue NOT NULL
;

-- Aug 21, 2009 11:40:18 AM COT
-- [ adempiere-Libero-2842063 ] getList not working
UPDATE AD_Column SET IsMandatory='Y',Updated=TO_DATE('2009-08-21 11:40:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=55026
;

-- Aug 21, 2009 11:40:19 AM COT
-- [ adempiere-Libero-2842063 ] getList not working
ALTER TABLE HR_ListLine MODIFY MaxValue NUMBER
;

-- Aug 21, 2009 11:40:19 AM COT
-- [ adempiere-Libero-2842063 ] getList not working
ALTER TABLE HR_ListLine MODIFY MaxValue NOT NULL
;

-- Aug 21, 2009 11:41:28 AM COT
-- [ adempiere-Libero-2842063 ] getList not working
UPDATE AD_Field SET SeqNo=80,IsDisplayed='Y' WHERE AD_Field_ID=55146
;

-- Aug 21, 2009 11:49:00 AM COT
-- [ adempiere-Libero-2842063 ] getList not working
UPDATE AD_Tab SET OrderByClause='Value',Updated=TO_DATE('2009-08-21 11:49:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Tab_ID=53121
;

-- Aug 21, 2009 11:49:20 AM COT
-- [ adempiere-Libero-2842063 ] getList not working
UPDATE AD_Tab SET OrderByClause='ValidFrom',Updated=TO_DATE('2009-08-21 11:49:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Tab_ID=53122
;

-- Aug 21, 2009 11:49:44 AM COT
-- [ adempiere-Libero-2842063 ] getList not working
UPDATE AD_Tab SET OrderByClause='MinValue',Updated=TO_DATE('2009-08-21 11:49:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Tab_ID=53123
;

