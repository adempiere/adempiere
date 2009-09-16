-- 25.03.2009 16:56:40 EET
-- https://sourceforge.net/tracker/?func=detail&aid=2711495&group_id=176962&atid=934929
INSERT INTO AD_Column (Created,CreatedBy,Updated,Version,IsActive,AD_Reference_ID,IsMandatory,IsIdentifier,SeqNo,IsAutocomplete,ColumnName,AD_Column_ID,IsParent,AD_Table_ID,FieldLength,IsKey,IsTranslated,AD_Client_ID,AD_Org_ID,AD_Element_ID,IsSelectionColumn,IsUpdateable,IsSyncDatabase,Name,EntityType,UpdatedBy,IsAlwaysUpdateable,IsEncrypted,IsAllowLogging) VALUES (TO_DATE('2009-03-25 16:56:39','YYYY-MM-DD HH24:MI:SS'),0,TO_DATE('2009-03-25 16:56:39','YYYY-MM-DD HH24:MI:SS'),0,'Y',30,'N','N',0,'N','PP_Cost_Collector_ID',57008,'N',53102,10,'N','N',0,0,53310,'N','Y','N','Manufacturing Cost Collector','EE02',0,'N','N','Y')
;

-- 25.03.2009 16:56:40 EET
-- https://sourceforge.net/tracker/?func=detail&aid=2711495&group_id=176962&atid=934929
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57008 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 25.03.2009 16:56:41 EET
-- https://sourceforge.net/tracker/?func=detail&aid=2711495&group_id=176962&atid=934929
INSERT INTO AD_Field (UpdatedBy,IsSameLine,IsHeading,CreatedBy,Updated,DisplayLength,IsFieldOnly,AD_Field_ID,AD_Tab_ID,AD_Column_ID,IsActive,Created,IsDisplayed,AD_Client_ID,AD_Org_ID,IsEncrypted,Name,IsReadOnly,IsCentrallyMaintained,EntityType) VALUES (0,'N','N',0,TO_DATE('2009-03-25 16:56:41','YYYY-MM-DD HH24:MI:SS'),10,'N',56827,53124,57008,'Y',TO_DATE('2009-03-25 16:56:41','YYYY-MM-DD HH24:MI:SS'),'N',0,0,'N','Manufacturing Cost Collector','N','Y','EE02')
;

-- 25.03.2009 16:56:41 EET
-- https://sourceforge.net/tracker/?func=detail&aid=2711495&group_id=176962&atid=934929
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56827 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;


-- 25.03.2009 16:56:46 EET
-- https://sourceforge.net/tracker/?func=detail&aid=2711495&group_id=176962&atid=934929
ALTER TABLE HR_Movement ADD PP_Cost_Collector_ID NUMBER(10) DEFAULT NULL 
;

-- 25.03.2009 16:57:01 EET
-- https://sourceforge.net/tracker/?func=detail&aid=2711495&group_id=176962&atid=934929
UPDATE AD_Column SET AD_Reference_ID=19,Updated=TO_DATE('2009-03-25 16:57:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=57008
;

-- 25.03.2009 16:57:02 EET
-- https://sourceforge.net/tracker/?func=detail&aid=2711495&group_id=176962&atid=934929
ALTER TABLE HR_Movement MODIFY PP_Cost_Collector_ID NUMBER(10) DEFAULT NULL 
;

-- 25.03.2009 16:57:37 EET
-- https://sourceforge.net/tracker/?func=detail&aid=2711495&group_id=176962&atid=934929
UPDATE AD_Column_Trl SET Name='Control activitate',IsTranslated='Y',Updated=TO_DATE('2009-03-25 16:57:37','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=57008 AND AD_Language='ro_RO'
;

-- 25.03.2009 16:58:16 EET
-- https://sourceforge.net/tracker/?func=detail&aid=2711495&group_id=176962&atid=934929
UPDATE AD_Field SET SeqNo=200,IsDisplayed='Y' WHERE AD_Field_ID=56827
;

-- 25.03.2009 16:58:16 EET
-- https://sourceforge.net/tracker/?func=detail&aid=2711495&group_id=176962&atid=934929
UPDATE AD_Field SET SeqNo=210,IsDisplayed='Y' WHERE AD_Field_ID=55184
;

-- 25.03.2009 16:58:16 EET
-- https://sourceforge.net/tracker/?func=detail&aid=2711495&group_id=176962&atid=934929
UPDATE AD_Field SET SeqNo=220,IsDisplayed='Y' WHERE AD_Field_ID=55185
;

-- 25.03.2009 16:58:16 EET
-- https://sourceforge.net/tracker/?func=detail&aid=2711495&group_id=176962&atid=934929
UPDATE AD_Field SET SeqNo=230,IsDisplayed='Y' WHERE AD_Field_ID=55186
;

-- 25.03.2009 16:58:16 EET
-- https://sourceforge.net/tracker/?func=detail&aid=2711495&group_id=176962&atid=934929
UPDATE AD_Field SET SeqNo=240,IsDisplayed='Y' WHERE AD_Field_ID=56313
;

-- 25.03.2009 16:58:16 EET
-- https://sourceforge.net/tracker/?func=detail&aid=2711495&group_id=176962&atid=934929
UPDATE AD_Field SET SeqNo=250,IsDisplayed='Y' WHERE AD_Field_ID=56308
;

-- 25.03.2009 16:58:16 EET
-- https://sourceforge.net/tracker/?func=detail&aid=2711495&group_id=176962&atid=934929
UPDATE AD_Field SET SeqNo=260,IsDisplayed='Y' WHERE AD_Field_ID=56314
;

-- 25.03.2009 16:58:16 EET
-- https://sourceforge.net/tracker/?func=detail&aid=2711495&group_id=176962&atid=934929
UPDATE AD_Field SET SeqNo=270,IsDisplayed='Y' WHERE AD_Field_ID=56315
;

-- 25.03.2009 16:58:16 EET
-- https://sourceforge.net/tracker/?func=detail&aid=2711495&group_id=176962&atid=934929
UPDATE AD_Field SET SeqNo=280,IsDisplayed='Y' WHERE AD_Field_ID=56311
;

-- 25.03.2009 16:58:16 EET
-- https://sourceforge.net/tracker/?func=detail&aid=2711495&group_id=176962&atid=934929
UPDATE AD_Field SET SeqNo=290,IsDisplayed='Y' WHERE AD_Field_ID=56312
;

-- 25.03.2009 16:58:16 EET
-- https://sourceforge.net/tracker/?func=detail&aid=2711495&group_id=176962&atid=934929
UPDATE AD_Field SET SeqNo=300,IsDisplayed='Y' WHERE AD_Field_ID=56310
;

-- 25.03.2009 16:58:16 EET
-- https://sourceforge.net/tracker/?func=detail&aid=2711495&group_id=176962&atid=934929
UPDATE AD_Field SET SeqNo=310,IsDisplayed='Y' WHERE AD_Field_ID=56309
;

ALTER TABLE HR_Movement ADD (CONSTRAINT PPCostCollector_HRMovement FOREIGN KEY (PP_Cost_Collector_ID) REFERENCES PP_Cost_Collector);

delete from AD_Field where AD_Column_ID=56185;

-- 25.03.2009 18:11:04 EET
-- https://sourceforge.net/tracker/?func=detail&aid=2711495&group_id=176962&atid=934929
DELETE  FROM  AD_Column_Trl WHERE AD_Column_ID=56185
;

-- 25.03.2009 18:11:05 EET
-- https://sourceforge.net/tracker/?func=detail&aid=2711495&group_id=176962&atid=934929
DELETE FROM AD_Column WHERE AD_Column_ID=56185
;

alter table "HR_MOVEMENT" drop column "PP_ORDER_NODE_ID";
