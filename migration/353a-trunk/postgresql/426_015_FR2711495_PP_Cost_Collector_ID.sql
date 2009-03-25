-- 25.03.2009 16:56:40 EET
-- https://sourceforge.net/tracker/?func=detail&aid=2711495&group_id=176962&atid=934929
INSERT INTO AD_Column (Created,CreatedBy,Updated,Version,IsActive,AD_Reference_ID,IsMandatory,IsIdentifier,SeqNo,IsAutocomplete,ColumnName,AD_Column_ID,IsParent,AD_Table_ID,FieldLength,IsKey,IsTranslated,AD_Client_ID,AD_Org_ID,AD_Element_ID,IsSelectionColumn,IsUpdateable,IsSyncDatabase,Name,EntityType,UpdatedBy,IsAlwaysUpdateable,IsEncrypted,IsAllowLogging) VALUES (TO_TIMESTAMP('2009-03-25 16:56:39','YYYY-MM-DD HH24:MI:SS'),0,TO_TIMESTAMP('2009-03-25 16:56:39','YYYY-MM-DD HH24:MI:SS'),0,'Y',30,'N','N',0,'N','PP_Cost_Collector_ID',57008,'N',53102,10,'N','N',0,0,53310,'N','Y','N','Manufacturing Cost Collector','EE02',0,'N','N','Y')
;

-- 25.03.2009 16:56:40 EET
-- https://sourceforge.net/tracker/?func=detail&aid=2711495&group_id=176962&atid=934929
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57008 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 25.03.2009 16:56:41 EET
-- https://sourceforge.net/tracker/?func=detail&aid=2711495&group_id=176962&atid=934929
INSERT INTO AD_Field (UpdatedBy,IsSameLine,IsHeading,CreatedBy,Updated,DisplayLength,IsFieldOnly,AD_Field_ID,AD_Tab_ID,AD_Column_ID,IsActive,Created,IsDisplayed,AD_Client_ID,AD_Org_ID,IsEncrypted,Name,IsReadOnly,IsCentrallyMaintained,EntityType) VALUES (0,'N','N',0,TO_TIMESTAMP('2009-03-25 16:56:41','YYYY-MM-DD HH24:MI:SS'),10,'N',56827,53124,57008,'Y',TO_TIMESTAMP('2009-03-25 16:56:41','YYYY-MM-DD HH24:MI:SS'),'N',0,0,'N','Manufacturing Cost Collector','N','Y','EE02')
;

-- 25.03.2009 16:56:41 EET
-- https://sourceforge.net/tracker/?func=detail&aid=2711495&group_id=176962&atid=934929
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56827 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 25.03.2009 16:56:43 EET
-- https://sourceforge.net/tracker/?func=detail&aid=2711495&group_id=176962&atid=934929
INSERT INTO AD_PrintFormatItem (IsPrinted,IsOrderBy,CreatedBy,AD_Org_ID,XPosition,IsGroupBy,IsHeightOneLine,Updated,SortNo,PrintFormatType,PrintAreaType,AD_PrintFormat_ID,MaxHeight,IsSuppressNull,IsNextPage,FieldAlignmentType,Name,ImageIsAttached,AD_Client_ID,XSpace,PrintName,IsCounted,IsAveraged,YSpace,IsSetNLPosition,IsPageBreak,IsFixedWidth,SeqNo,IsRelativePosition,YPosition,LineAlignmentType,AD_Column_ID,UpdatedBy,IsSummarized,MaxWidth,Created,IsNextLine,IsActive,AD_PrintFormatItem_ID,IsMinCalc,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,IsFilledRectangle,LineWidth,IsCentrallyMaintained,ArcDiameter,ShapeType,IsImageField) VALUES ('N','N',0,0,0,'N','N',TO_TIMESTAMP('2009-03-25 16:56:42','YYYY-MM-DD HH24:MI:SS'),0,'F','C',1500136,0,'N','N','L','Manufacturing Cost Collector','N',0,0,'Manufacturing Cost Collector','N','N',0,'N','N','N',0,'Y',0,'X',57008,0,'N',0,TO_TIMESTAMP('2009-03-25 16:56:42','YYYY-MM-DD HH24:MI:SS'),'N','Y',51072,'N','N','N','N','N','N',1,'Y',0,'N','N')
;

-- 25.03.2009 16:56:43 EET
-- https://sourceforge.net/tracker/?func=detail&aid=2711495&group_id=176962&atid=934929
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51072 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- 25.03.2009 16:56:43 EET
-- https://sourceforge.net/tracker/?func=detail&aid=2711495&group_id=176962&atid=934929
UPDATE AD_PrintFormatItem_Trl SET PrintName = (SELECT e.PrintName FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=57008) WHERE AD_PrintFormatItem_ID = 51072 AND EXISTS (SELECT * FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=57008 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID = 51072) AND EXISTS (SELECT * FROM AD_Client WHERE AD_Client_ID=AD_PrintFormatItem_Trl.AD_Client_ID AND IsMultiLingualDocument='Y')
;

-- 25.03.2009 16:56:43 EET
-- https://sourceforge.net/tracker/?func=detail&aid=2711495&group_id=176962&atid=934929
INSERT INTO AD_PrintFormatItem (IsPrinted,IsOrderBy,CreatedBy,AD_Org_ID,XPosition,IsGroupBy,IsHeightOneLine,Updated,SortNo,PrintFormatType,PrintAreaType,AD_PrintFormat_ID,MaxHeight,IsSuppressNull,IsNextPage,FieldAlignmentType,Name,ImageIsAttached,AD_Client_ID,XSpace,PrintName,IsCounted,IsAveraged,YSpace,IsSetNLPosition,IsPageBreak,IsFixedWidth,SeqNo,IsRelativePosition,YPosition,LineAlignmentType,AD_Column_ID,UpdatedBy,IsSummarized,MaxWidth,Created,IsNextLine,IsActive,AD_PrintFormatItem_ID,IsMinCalc,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,IsFilledRectangle,LineWidth,IsCentrallyMaintained,ArcDiameter,ShapeType,IsImageField) VALUES ('N','N',0,1500001,0,'N','N',TO_TIMESTAMP('2009-03-25 16:56:43','YYYY-MM-DD HH24:MI:SS'),0,'F','C',1500184,0,'N','N','L','Manufacturing Cost Collector','N',1000011,0,'Manufacturing Cost Collector','N','N',0,'N','N','N',0,'Y',0,'X',57008,0,'N',0,TO_TIMESTAMP('2009-03-25 16:56:43','YYYY-MM-DD HH24:MI:SS'),'N','Y',1508873,'N','N','N','N','N','N',1,'Y',0,'N','N')
;

-- 25.03.2009 16:56:43 EET
-- https://sourceforge.net/tracker/?func=detail&aid=2711495&group_id=176962&atid=934929
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=1508873 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- 25.03.2009 16:56:43 EET
-- https://sourceforge.net/tracker/?func=detail&aid=2711495&group_id=176962&atid=934929
UPDATE AD_PrintFormatItem_Trl SET PrintName = (SELECT e.PrintName FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=57008) WHERE AD_PrintFormatItem_ID = 1508873 AND EXISTS (SELECT * FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=57008 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID = 1508873) AND EXISTS (SELECT * FROM AD_Client WHERE AD_Client_ID=AD_PrintFormatItem_Trl.AD_Client_ID AND IsMultiLingualDocument='Y')
;

-- 25.03.2009 16:56:44 EET
-- https://sourceforge.net/tracker/?func=detail&aid=2711495&group_id=176962&atid=934929
INSERT INTO AD_PrintFormatItem (IsPrinted,IsOrderBy,CreatedBy,AD_Org_ID,XPosition,IsGroupBy,IsHeightOneLine,Updated,SortNo,PrintFormatType,PrintAreaType,AD_PrintFormat_ID,MaxHeight,IsSuppressNull,IsNextPage,FieldAlignmentType,Name,ImageIsAttached,AD_Client_ID,XSpace,PrintName,IsCounted,IsAveraged,YSpace,IsSetNLPosition,IsPageBreak,IsFixedWidth,SeqNo,IsRelativePosition,YPosition,LineAlignmentType,AD_Column_ID,UpdatedBy,IsSummarized,MaxWidth,Created,IsNextLine,IsActive,AD_PrintFormatItem_ID,IsMinCalc,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,IsFilledRectangle,LineWidth,IsCentrallyMaintained,ArcDiameter,ShapeType,IsImageField) VALUES ('N','N',0,0,0,'N','N',TO_TIMESTAMP('2009-03-25 16:56:43','YYYY-MM-DD HH24:MI:SS'),0,'F','C',1500139,0,'N','N','L','Manufacturing Cost Collector','N',0,0,'Manufacturing Cost Collector','N','N',0,'N','N','N',0,'Y',0,'X',57008,0,'N',0,TO_TIMESTAMP('2009-03-25 16:56:43','YYYY-MM-DD HH24:MI:SS'),'N','Y',51073,'N','N','N','N','N','N',1,'Y',0,'N','N')
;

-- 25.03.2009 16:56:44 EET
-- https://sourceforge.net/tracker/?func=detail&aid=2711495&group_id=176962&atid=934929
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51073 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- 25.03.2009 16:56:44 EET
-- https://sourceforge.net/tracker/?func=detail&aid=2711495&group_id=176962&atid=934929
UPDATE AD_PrintFormatItem_Trl SET PrintName = (SELECT e.PrintName FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=57008) WHERE AD_PrintFormatItem_ID = 51073 AND EXISTS (SELECT * FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=57008 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID = 51073) AND EXISTS (SELECT * FROM AD_Client WHERE AD_Client_ID=AD_PrintFormatItem_Trl.AD_Client_ID AND IsMultiLingualDocument='Y')
;

-- 25.03.2009 16:56:44 EET
-- https://sourceforge.net/tracker/?func=detail&aid=2711495&group_id=176962&atid=934929
INSERT INTO AD_PrintFormatItem (IsPrinted,IsOrderBy,CreatedBy,AD_Org_ID,XPosition,IsGroupBy,IsHeightOneLine,Updated,SortNo,PrintFormatType,PrintAreaType,AD_PrintFormat_ID,MaxHeight,IsSuppressNull,IsNextPage,FieldAlignmentType,Name,ImageIsAttached,AD_Client_ID,XSpace,PrintName,IsCounted,IsAveraged,YSpace,IsSetNLPosition,IsPageBreak,IsFixedWidth,SeqNo,IsRelativePosition,YPosition,LineAlignmentType,AD_Column_ID,UpdatedBy,IsSummarized,MaxWidth,Created,IsNextLine,IsActive,AD_PrintFormatItem_ID,IsMinCalc,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,IsFilledRectangle,LineWidth,IsCentrallyMaintained,ArcDiameter,ShapeType,IsImageField) VALUES ('N','N',0,0,0,'N','N',TO_TIMESTAMP('2009-03-25 16:56:44','YYYY-MM-DD HH24:MI:SS'),0,'F','C',1500141,0,'N','N','L','Manufacturing Cost Collector','N',1000011,0,'Manufacturing Cost Collector','N','N',0,'N','N','N',0,'Y',0,'X',57008,0,'N',0,TO_TIMESTAMP('2009-03-25 16:56:44','YYYY-MM-DD HH24:MI:SS'),'N','Y',1508874,'N','N','N','N','N','N',1,'Y',0,'N','N')
;

-- 25.03.2009 16:56:44 EET
-- https://sourceforge.net/tracker/?func=detail&aid=2711495&group_id=176962&atid=934929
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=1508874 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- 25.03.2009 16:56:44 EET
-- https://sourceforge.net/tracker/?func=detail&aid=2711495&group_id=176962&atid=934929
UPDATE AD_PrintFormatItem_Trl SET PrintName = (SELECT e.PrintName FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=57008) WHERE AD_PrintFormatItem_ID = 1508874 AND EXISTS (SELECT * FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=57008 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID = 1508874) AND EXISTS (SELECT * FROM AD_Client WHERE AD_Client_ID=AD_PrintFormatItem_Trl.AD_Client_ID AND IsMultiLingualDocument='Y')
;

-- 25.03.2009 16:56:44 EET
-- https://sourceforge.net/tracker/?func=detail&aid=2711495&group_id=176962&atid=934929
INSERT INTO AD_PrintFormatItem (IsPrinted,IsOrderBy,CreatedBy,AD_Org_ID,XPosition,IsGroupBy,IsHeightOneLine,Updated,SortNo,PrintFormatType,PrintAreaType,AD_PrintFormat_ID,MaxHeight,IsSuppressNull,IsNextPage,FieldAlignmentType,Name,ImageIsAttached,AD_Client_ID,XSpace,PrintName,IsCounted,IsAveraged,YSpace,IsSetNLPosition,IsPageBreak,IsFixedWidth,SeqNo,IsRelativePosition,YPosition,LineAlignmentType,AD_Column_ID,UpdatedBy,IsSummarized,MaxWidth,Created,IsNextLine,IsActive,AD_PrintFormatItem_ID,IsMinCalc,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,IsFilledRectangle,LineWidth,IsCentrallyMaintained,ArcDiameter,ShapeType,IsImageField) VALUES ('N','N',0,1500001,0,'N','N',TO_TIMESTAMP('2009-03-25 16:56:44','YYYY-MM-DD HH24:MI:SS'),0,'F','C',1500181,0,'N','N','L','Manufacturing Cost Collector','N',1000011,0,'Manufacturing Cost Collector','N','N',0,'N','N','N',0,'Y',0,'X',57008,0,'N',0,TO_TIMESTAMP('2009-03-25 16:56:44','YYYY-MM-DD HH24:MI:SS'),'N','Y',1508875,'N','N','N','N','N','N',1,'Y',0,'N','N')
;

-- 25.03.2009 16:56:44 EET
-- https://sourceforge.net/tracker/?func=detail&aid=2711495&group_id=176962&atid=934929
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=1508875 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- 25.03.2009 16:56:44 EET
-- https://sourceforge.net/tracker/?func=detail&aid=2711495&group_id=176962&atid=934929
UPDATE AD_PrintFormatItem_Trl SET PrintName = (SELECT e.PrintName FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=57008) WHERE AD_PrintFormatItem_ID = 1508875 AND EXISTS (SELECT * FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=57008 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID = 1508875) AND EXISTS (SELECT * FROM AD_Client WHERE AD_Client_ID=AD_PrintFormatItem_Trl.AD_Client_ID AND IsMultiLingualDocument='Y')
;

-- 25.03.2009 16:56:46 EET
-- https://sourceforge.net/tracker/?func=detail&aid=2711495&group_id=176962&atid=934929
ALTER TABLE HR_Movement ADD COLUMN PP_Cost_Collector_ID NUMERIC(10) DEFAULT NULL 
;

-- 25.03.2009 16:57:01 EET
-- https://sourceforge.net/tracker/?func=detail&aid=2711495&group_id=176962&atid=934929
UPDATE AD_Column SET AD_Reference_ID=19,Updated=TO_TIMESTAMP('2009-03-25 16:57:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=57008
;

-- 25.03.2009 16:57:02 EET
-- https://sourceforge.net/tracker/?func=detail&aid=2711495&group_id=176962&atid=934929
insert into t_alter_column values('hr_movement','PP_Cost_Collector_ID','NUMERIC(10)',null,'NULL')
;

-- 25.03.2009 16:57:37 EET
-- https://sourceforge.net/tracker/?func=detail&aid=2711495&group_id=176962&atid=934929
UPDATE AD_Column_Trl SET Name='Control activitate',IsTranslated='Y',Updated=TO_TIMESTAMP('2009-03-25 16:57:37','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=57008 AND AD_Language='ro_RO'
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

ALTER TABLE HR_Movement ADD CONSTRAINT PPCostCollector_HRMovement FOREIGN KEY (PP_Cost_Collector_ID) REFERENCES PP_Cost_Collector;

-- 25.03.2009 18:11:04 EET
-- https://sourceforge.net/tracker/?func=detail&aid=2711495&group_id=176962&atid=934929
DELETE FROM AD_Column_Trl WHERE AD_Column_ID=56185
;

-- 25.03.2009 18:11:05 EET
-- https://sourceforge.net/tracker/?func=detail&aid=2711495&group_id=176962&atid=934929
DELETE FROM AD_Column WHERE AD_Column_ID=56185
;

alter table HR_MOVEMENT drop column PP_ORDER_NODE_ID;
