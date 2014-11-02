-- Sep 11, 2009 2:06:24 PM ECT
-- Warehouse Management
UPDATE AD_Table SET AD_Window_ID=53089,Updated=TO_TIMESTAMP('2009-09-11 14:06:24','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Table_ID=53230
;
-- Sep 11, 2009 2:05:33 PM ECT
-- Warehouse Management
UPDATE AD_Table SET AD_Window_ID=53088,Updated=TO_TIMESTAMP('2009-09-11 14:05:33','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Table_ID=53224
;
-- Sep 29, 2009 12:28:25 AM ECT
-- Warehouse Management
UPDATE AD_Tab SET AD_Column_ID=58087,Updated=TO_TIMESTAMP('2009-09-29 00:28:25','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Tab_ID=53256
;
-- Sep 29, 2009 12:34:03 AM ECT
-- Warehouse Management
UPDATE AD_Tab SET TabLevel=2,Updated=TO_TIMESTAMP('2009-09-29 00:34:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Tab_ID=53257
;
-- Sep 29, 2009 12:34:12 AM ECT
-- Warehouse Management
UPDATE AD_Tab SET TabLevel=3,Updated=TO_TIMESTAMP('2009-09-29 00:34:12','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Tab_ID=53253
;
-- Sep 11, 2009 1:26:11 PM ECT
-- Warehouse Management
insert into t_alter_column values('ad_browse','AccessLevel',null,'NOT NULL',null)
;
-- Sep 29, 2009 12:10:23 AM ECT
-- Warehouse Management
insert into t_alter_column values('ad_view_column','ColumnName','VARCHAR(255)',null,'NULL')
;

-- Sep 29, 2009 12:34:52 AM ECT
-- Warehouse Management
UPDATE AD_Tab SET TabLevel=1,Updated=TO_TIMESTAMP('2009-09-29 00:34:52','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Tab_ID=53250
;

-- Sep 11, 2009 2:33:14 PM ECT
-- Warehouse Management
UPDATE AD_Tab SET WhereClause='(IsDisplayed=''Y'' OR IsQueryCriteria=''Y'')',Updated=TO_TIMESTAMP('2009-09-11 14:33:14','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Tab_ID=53247
;
-- Sep 11, 2009 3:11:58 PM ECT
-- Warehouse Management
UPDATE AD_Tab SET OrderByClause='SeqNo',Updated=TO_TIMESTAMP('2009-09-11 15:11:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Tab_ID=53247
;

-- Sep 29, 2009 12:37:22 AM ECT
-- Warehouse Management
UPDATE AD_Tab SET SeqNo=40,Updated=TO_TIMESTAMP('2009-09-29 00:37:22','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Tab_ID=53247
;


-- Sep 29, 2009 12:37:31 AM ECT
-- Warehouse Management
UPDATE AD_Tab SET SeqNo=50,Updated=TO_TIMESTAMP('2009-09-29 00:37:31','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Tab_ID=53251
;

-- Sep 29, 2009 12:37:15 AM ECT
-- Warehouse Management
UPDATE AD_Tab SET SeqNo=60,Updated=TO_TIMESTAMP('2009-09-29 00:37:15','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Tab_ID=53252
;