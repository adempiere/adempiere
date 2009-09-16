-- HR - SeqNo in HR_Concept
UPDATE AD_Tab SET AD_ColumnSortYesNo_ID=54825,Updated=TO_TIMESTAMP('2009-03-24 13:00:28','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Tab_ID=53200
;

-- 24.03.2009 13:02:39 EET
-- HR - SeqNo in HR_Concept
UPDATE AD_Tab SET IsInsertRecord='N',Updated=TO_TIMESTAMP('2009-03-24 13:02:39','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Tab_ID=53200
;

-- 24.03.2009 13:26:15 EET
-- HR - SeqNo in HR_Concept
UPDATE AD_Tab SET Description='Concept Ordering', Name='Concept Ordering',Updated=TO_TIMESTAMP('2009-03-24 13:26:15','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Tab_ID=53200
;

-- 24.03.2009 13:26:15 EET
-- HR - SeqNo in HR_Concept
UPDATE AD_Tab_Trl SET IsTranslated='N' WHERE AD_Tab_ID=53200
;


-- 24.03.2009 13:27:44 EET
-- HR - SeqNo in HR_Concept
UPDATE AD_Tab_Trl SET IsTranslated='Y',Name='Ordonare concepte',Updated=TO_TIMESTAMP('2009-03-24 13:27:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Tab_ID=53200 AND AD_Language='ro_RO'
;

-- 24.03.2009 13:28:09 EET
-- HR - SeqNo in HR_Concept
UPDATE AD_Column SET IsParent='Y', IsUpdateable='N',Updated=TO_TIMESTAMP('2009-03-24 13:28:09','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=54820
;

-- 24.03.2009 13:28:12 EET
-- HR - SeqNo in HR_Concept
insert into t_alter_column values('hr_concept','HR_Concept_Category_ID','NUMERIC(10)',null,'NULL')
;

-- 24.03.2009 13:30:19 EET
-- HR - SeqNo in HR_Concept
UPDATE AD_Column SET IsParent='N',Updated=TO_TIMESTAMP('2009-03-24 13:30:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=54820
;

-- 24.03.2009 13:30:21 EET
-- HR - SeqNo in HR_Concept
insert into t_alter_column values('hr_concept','HR_Concept_Category_ID','NUMERIC(10)',null,'NULL')
;

-- 24.03.2009 13:30:28 EET
-- HR - SeqNo in HR_Concept
UPDATE AD_Column SET IsParent='Y', IsUpdateable='N',Updated=TO_TIMESTAMP('2009-03-24 13:30:28','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=54826
;

-- 24.03.2009 13:30:29 EET
-- HR - SeqNo in HR_Concept
insert into t_alter_column values('hr_concept','AD_Client_ID','NUMERIC(10)',null,null)
;

