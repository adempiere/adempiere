-- May 20, 2009 6:50:30 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormat (AD_Client_ID,AD_Org_ID,AD_PrintColor_ID,AD_PrintFont_ID,AD_PrintFormat_ID,AD_PrintPaper_ID,AD_PrintTableFormat_ID,AD_Table_ID,CreateCopy,Created,CreatedBy,Description,FooterMargin,HeaderMargin,IsActive,IsDefault,IsForm,IsStandardHeaderFooter,IsTableBased,Name,Updated,UpdatedBy) VALUES (0,0,100,119,50040,101,100,53198,'N',TO_TIMESTAMP('2009-05-20 18:50:28','YYYY-MM-DD HH24:MI:SS'),0,'Manufacturing Order BOM Line',0,0,'Y','N','N','N','Y','Manufacturing_Order_BOMLine_Component ** TEMPLATE **',TO_TIMESTAMP('2009-05-20 18:50:28','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 20, 2009 6:50:31 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:50:30','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50040,'N',0,'F','N',0,57415,'X',0,0,0,'L','Y','Manufacturing Order',0,0,'N','N','N','N','N','N','Manufacturing Order','N',0,TO_TIMESTAMP('2009-05-20 18:50:30','YYYY-MM-DD HH24:MI:SS'),0,'N',51077,'Y',0,'N',0,'N','N','N','N',0,0)
;

-- May 20, 2009 6:50:31 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51077 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:50:32 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:50:32','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50040,'N',0,'F','N',0,57393,'X',0,0,0,'L','Y','Updated',0,0,'N','N','N','N','N','N','Updated','N',0,TO_TIMESTAMP('2009-05-20 18:50:32','YYYY-MM-DD HH24:MI:SS'),0,'N',51078,'Y',0,'N',0,'N','N','N','N',0,0)
;

-- May 20, 2009 6:50:32 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51078 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:50:33 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:50:32','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50040,'N',0,'F','N',0,57394,'X',0,0,0,'L','Y','Updated By',0,0,'N','N','N','N','N','N','Updated By','N',0,TO_TIMESTAMP('2009-05-20 18:50:32','YYYY-MM-DD HH24:MI:SS'),0,'N',51079,'Y',0,'N',0,'N','N','N','N',0,0)
;

-- May 20, 2009 6:50:33 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51079 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:50:34 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:50:33','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50040,'N',0,'F','N',0,57416,'X',0,0,0,'T','Y','Quantity',0,0,'Y','N','N','N','N','N','Quantity','N',0,TO_TIMESTAMP('2009-05-20 18:50:33','YYYY-MM-DD HH24:MI:SS'),0,'N',51080,'Y',0,'N',0,'N','N','N','N',0,0)
;

-- May 20, 2009 6:50:34 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51080 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:50:34 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:50:34','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50040,'N',0,'F','N',0,57404,'X',0,0,0,'L','Y','Is Critical Component',0,0,'N','N','N','N','N','N','Is Critical Component','N',0,TO_TIMESTAMP('2009-05-20 18:50:34','YYYY-MM-DD HH24:MI:SS'),0,'N',51081,'Y',0,'N',0,'N','N','N','N',0,0)
;

-- May 20, 2009 6:50:34 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51081 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:50:35 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:50:34','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50040,'N',0,'F','N',0,57414,'X',0,0,0,'L','Y','Manufacturing Order BOM Line',0,0,'N','N','N','N','N','N','Manufacturing Order BOM Line','N',0,TO_TIMESTAMP('2009-05-20 18:50:34','YYYY-MM-DD HH24:MI:SS'),0,'N',51082,'Y',0,'N',0,'N','N','N','N',0,0)
;

-- May 20, 2009 6:50:35 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51082 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:50:36 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:50:35','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50040,'N',0,'F','N',0,57413,'X',0,0,0,'L','Y','Manufacturing Order BOM',0,0,'N','N','N','N','N','N','Manufacturing Order BOM','N',0,TO_TIMESTAMP('2009-05-20 18:50:35','YYYY-MM-DD HH24:MI:SS'),0,'N',51083,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:50:36 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51083 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:50:37 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:50:36','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50040,'N',0,'F','N',0,57417,'X',0,0,0,'T','Y','Quantity in %',0,0,'N','N','N','N','N','N','Quantity in %','N',0,TO_TIMESTAMP('2009-05-20 18:50:36','YYYY-MM-DD HH24:MI:SS'),0,'N',51084,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:50:37 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51084 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:50:38 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:50:37','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50040,'N',0,'F','N',0,57405,'X',0,0,0,'L','Y','Is Qty Percentage',0,0,'N','N','N','N','N','N','Is Qty Percentage','N',0,TO_TIMESTAMP('2009-05-20 18:50:37','YYYY-MM-DD HH24:MI:SS'),0,'N',51085,'Y',0,'N',0,'N','N','N','N',0,0)
;

-- May 20, 2009 6:50:38 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51085 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:50:38 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:50:38','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50040,'N',0,'F','N',0,57392,'X',0,0,0,'L','Y','Created By',0,0,'N','N','N','N','N','N','Created By','N',0,TO_TIMESTAMP('2009-05-20 18:50:38','YYYY-MM-DD HH24:MI:SS'),0,'N',51086,'Y',0,'N',0,'N','N','N','N',0,0)
;

-- May 20, 2009 6:50:38 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51086 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:50:39 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:50:38','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50040,'N',0,'F','N',0,57389,'X',0,0,0,'L','Y','Organization',0,0,'N','N','N','N','N','N','Organization','N',0,TO_TIMESTAMP('2009-05-20 18:50:38','YYYY-MM-DD HH24:MI:SS'),0,'N',51087,'Y',0,'N',0,'N','N','N','N',0,0)
;

-- May 20, 2009 6:50:39 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51087 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:50:40 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:50:39','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50040,'N',0,'F','N',0,57390,'X',0,0,0,'L','Y','Active',0,0,'N','N','N','N','N','N','Active','N',0,TO_TIMESTAMP('2009-05-20 18:50:39','YYYY-MM-DD HH24:MI:SS'),0,'N',51088,'Y',0,'N',0,'N','N','N','N',0,0)
;

-- May 20, 2009 6:50:40 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51088 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:50:41 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:50:40','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50040,'N',0,'F','N',0,57388,'X',0,0,0,'L','Y','Client',0,0,'N','N','N','N','N','N','Client','N',0,TO_TIMESTAMP('2009-05-20 18:50:40','YYYY-MM-DD HH24:MI:SS'),0,'N',51089,'Y',0,'N',0,'N','N','N','N',0,0)
;

-- May 20, 2009 6:50:41 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51089 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:50:42 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:50:41','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50040,'N',0,'F','N',0,57400,'X',0,0,0,'L','Y','Component Type',0,0,'N','N','N','N','N','N','Component Type','N',0,TO_TIMESTAMP('2009-05-20 18:50:41','YYYY-MM-DD HH24:MI:SS'),0,'N',51090,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:50:42 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51090 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:50:43 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:50:42','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50040,'N',0,'F','N',0,57391,'X',0,0,0,'L','Y','Created',0,0,'N','N','N','N','N','N','Created','N',0,TO_TIMESTAMP('2009-05-20 18:50:42','YYYY-MM-DD HH24:MI:SS'),0,'N',51091,'Y',0,'N',0,'N','N','N','N',0,0)
;

-- May 20, 2009 6:50:43 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51091 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:50:43 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:50:43','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50040,'N',0,'F','N',0,57552,'X',0,0,0,'T','Y','Qty Available',0,0,'N','N','N','N','N','N','Available Quantity','N',0,TO_TIMESTAMP('2009-05-20 18:50:43','YYYY-MM-DD HH24:MI:SS'),0,'N',51092,'Y',0,'N',0,'N','N','N','N',0,0)
;

-- May 20, 2009 6:50:43 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51092 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:50:45 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:50:43','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50040,'N',0,'F','N',0,57553,'X',0,0,0,'T','Y','On Hand Qty',0,0,'N','N','N','N','N','N','On Hand Quantity','N',0,TO_TIMESTAMP('2009-05-20 18:50:43','YYYY-MM-DD HH24:MI:SS'),0,'N',51093,'Y',0,'N',0,'N','N','N','N',0,0)
;

-- May 20, 2009 6:50:45 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51093 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:50:45 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:50:45','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50040,'N',0,'F','N',0,57422,'X',0,0,0,'T','Y','Reserved Qty',0,0,'N','N','N','N','N','N','Reserved Quantity','N',0,TO_TIMESTAMP('2009-05-20 18:50:45','YYYY-MM-DD HH24:MI:SS'),0,'N',51094,'Y',0,'N',0,'N','N','N','N',0,0)
;

-- May 20, 2009 6:50:45 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51094 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:50:46 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:50:45','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50040,'N',0,'F','N',0,57412,'X',0,0,0,'L','Y','Warehouse',0,0,'N','N','N','N','N','N','Warehouse','N',0,TO_TIMESTAMP('2009-05-20 18:50:45','YYYY-MM-DD HH24:MI:SS'),0,'N',51095,'Y',0,'N',0,'N','N','N','N',0,0)
;

-- May 20, 2009 6:50:46 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51095 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:50:47 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:50:46','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','Y','N','C',50040,'N',0,'F','N',10,57397,'X',0,0,0,'L','Y','Product',0,0,'N','N','N','N','N','N','Product','N',0,TO_TIMESTAMP('2009-05-20 18:50:46','YYYY-MM-DD HH24:MI:SS'),0,'N',51096,'Y',0,'N',0,'N','N','N','N',0,0)
;

-- May 20, 2009 6:50:47 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51096 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:50:47 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,AD_PrintColor_ID,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,AD_PrintFont_ID,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:50:47','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','Y','Y','C',50040,'N',0,100,'F','N',20,57395,'X',0,0,0,'D','Y','Description',0,130,0,'N','N','N','N','Y','N','Description','N',0,TO_TIMESTAMP('2009-05-20 18:50:47','YYYY-MM-DD HH24:MI:SS'),0,'N',51097,'Y',0,'N',20,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:50:47 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51097 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:50:48 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,AD_PrintColor_ID,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,AD_PrintFont_ID,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:50:47','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','Y','Y','C',50040,'N',0,100,'F','N',30,57403,'X',0,0,0,'D','Y','Help',0,130,0,'N','N','N','N','Y','N','Help','N',0,TO_TIMESTAMP('2009-05-20 18:50:47','YYYY-MM-DD HH24:MI:SS'),0,'N',51098,'Y',0,'N',20,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:50:48 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51098 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:50:49 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:50:48','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','Y','N','C',50040,'N',0,'F','N',40,57399,'X',0,0,0,'L','Y','UOM',0,0,'N','N','N','N','N','N','UOM','N',0,TO_TIMESTAMP('2009-05-20 18:50:48','YYYY-MM-DD HH24:MI:SS'),0,'N',51099,'Y',0,'N',0,'N','N','N','N',0,0)
;

-- May 20, 2009 6:50:49 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51099 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:50:49 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:50:49','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','Y','N','C',50040,'N',0,'F','N',50,57554,'X',0,0,0,'T','Y','Qty Batch Size',0,0,'Y','N','N','N','N','N','Qty Batch Size','N',0,TO_TIMESTAMP('2009-05-20 18:50:49','YYYY-MM-DD HH24:MI:SS'),0,'N',51100,'Y',0,'N',0,'N','N','N','N',0,0)
;

-- May 20, 2009 6:50:49 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51100 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:50:50 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:50:49','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','Y','N','C',50040,'N',0,'F','N',60,57421,'X',0,0,0,'T','Y','Qty Required',0,0,'Y','N','N','N','N','N','Qty Requiered','N',0,TO_TIMESTAMP('2009-05-20 18:50:49','YYYY-MM-DD HH24:MI:SS'),0,'N',51101,'Y',0,'N',0,'N','N','N','N',0,0)
;

-- May 20, 2009 6:50:50 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51101 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:50:51 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormat (AD_Client_ID,AD_Org_ID,AD_PrintColor_ID,AD_PrintFont_ID,AD_PrintFormat_ID,AD_PrintPaper_ID,AD_PrintTableFormat_ID,AD_Table_ID,CreateCopy,Created,CreatedBy,FooterMargin,HeaderMargin,IsActive,IsDefault,IsForm,IsStandardHeaderFooter,IsTableBased,Name,Updated,UpdatedBy) VALUES (0,0,100,119,50041,101,100,53197,'N',TO_TIMESTAMP('2009-05-20 18:50:50','YYYY-MM-DD HH24:MI:SS'),0,0,0,'Y','N','Y','N','Y','Manufacturing_Order_BOM_Header ** TEMPLATE **',TO_TIMESTAMP('2009-05-20 18:50:50','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 20, 2009 6:50:52 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:50:51','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50041,'N',0,'F','N',0,57364,'X',0,0,0,'T','Y','Ordered Qty',0,0,'N','N','N','N','N','N','Ordered Quantity','N',0,TO_TIMESTAMP('2009-05-20 18:50:51','YYYY-MM-DD HH24:MI:SS'),0,'N',51102,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:50:52 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51102 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:50:52 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:50:52','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50041,'N',0,'F','N',0,57328,'X',0,0,0,'L','Y','Active',0,0,'N','N','N','N','N','N','Active','N',0,TO_TIMESTAMP('2009-05-20 18:50:52','YYYY-MM-DD HH24:MI:SS'),0,'N',51103,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:50:52 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51103 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:50:53 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:50:52','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50041,'N',0,'F','N',0,57382,'X',0,0,0,'L','Y','Attribute Set Instance',0,0,'N','N','N','N','N','N','Attribute Set Instance','N',0,TO_TIMESTAMP('2009-05-20 18:50:52','YYYY-MM-DD HH24:MI:SS'),0,'N',51104,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:50:53 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51104 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:50:54 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:50:53','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50041,'N',0,'F','N',0,57355,'X',0,0,0,'L','Y','BOM & Formula',0,0,'N','N','N','N','N','N','BOM & Formula','N',0,TO_TIMESTAMP('2009-05-20 18:50:53','YYYY-MM-DD HH24:MI:SS'),0,'N',51105,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:50:54 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51105 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:50:54 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:50:54','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50041,'N',0,'F','N',0,57378,'X',0,0,0,'L','Y','BOM Type',0,0,'N','N','N','N','N','N','BOM Type','N',0,TO_TIMESTAMP('2009-05-20 18:50:54','YYYY-MM-DD HH24:MI:SS'),0,'N',51106,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:50:54 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51106 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:50:55 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:50:55','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50041,'N',0,'F','N',0,57379,'X',0,0,0,'L','Y','BOM Use',0,0,'N','N','N','N','N','N','BOM Use','N',0,TO_TIMESTAMP('2009-05-20 18:50:55','YYYY-MM-DD HH24:MI:SS'),0,'N',51107,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:50:55 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51107 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:50:56 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:50:55','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50041,'N',0,'F','N',0,57384,'X',0,0,0,'L','Y','Name',0,0,'N','N','N','N','N','N','Name','N',0,TO_TIMESTAMP('2009-05-20 18:50:55','YYYY-MM-DD HH24:MI:SS'),0,'N',51108,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:50:56 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51108 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:50:57 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:50:56','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50041,'N',0,'F','N',0,57326,'X',0,0,0,'L','Y','Client',0,0,'N','N','N','N','N','N','Client','N',0,TO_TIMESTAMP('2009-05-20 18:50:56','YYYY-MM-DD HH24:MI:SS'),0,'N',51109,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:50:57 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51109 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:50:57 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:50:57','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50041,'N',0,'F','N',0,57329,'X',0,0,0,'L','Y','Created',0,0,'N','N','N','N','N','N','Created','N',0,TO_TIMESTAMP('2009-05-20 18:50:57','YYYY-MM-DD HH24:MI:SS'),0,'N',51110,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:50:57 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51110 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:50:58 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:50:57','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50041,'N',0,'F','N',0,57330,'X',0,0,0,'L','Y','Created By',0,0,'N','N','N','N','N','N','Created By','N',0,TO_TIMESTAMP('2009-05-20 18:50:57','YYYY-MM-DD HH24:MI:SS'),0,'N',51111,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:50:58 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51111 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:50:59 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:50:58','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50041,'N',0,'F','N',0,57327,'X',0,0,0,'L','Y','Organization',0,0,'N','N','N','N','N','N','Organization','N',0,TO_TIMESTAMP('2009-05-20 18:50:58','YYYY-MM-DD HH24:MI:SS'),0,'N',51112,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:50:59 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51112 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:50:59 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:50:59','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50041,'N',0,'F','N',0,57331,'X',0,0,0,'L','Y','Updated',0,0,'N','N','N','N','N','N','Updated','N',0,TO_TIMESTAMP('2009-05-20 18:50:59','YYYY-MM-DD HH24:MI:SS'),0,'N',51113,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:50:59 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51113 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:51:00 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:50:59','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50041,'N',0,'F','N',0,57377,'X',0,0,0,'L','Y','Activity',0,0,'N','N','N','N','N','N','Activity','N',0,TO_TIMESTAMP('2009-05-20 18:50:59','YYYY-MM-DD HH24:MI:SS'),0,'N',51114,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:51:00 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51114 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:51:01 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:51:00','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50041,'N',0,'F','N',0,57375,'X',0,0,0,'L','Y','Campaign',0,0,'N','N','N','N','N','N','Campaign','N',0,TO_TIMESTAMP('2009-05-20 18:51:00','YYYY-MM-DD HH24:MI:SS'),0,'N',51115,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:51:01 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51115 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:51:01 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:51:01','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50041,'N',0,'F','N',0,57381,'X',0,0,0,'B','Y','Comment',0,0,'N','N','N','N','N','N','Comment/Help','N',0,TO_TIMESTAMP('2009-05-20 18:51:01','YYYY-MM-DD HH24:MI:SS'),0,'N',51116,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:51:01 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51116 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:51:02 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:51:01','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50041,'N',0,'F','N',0,57362,'X',0,0,0,'T','Y','Delivered Qty',0,0,'N','N','N','N','N','N','Delivered Quantity','N',0,TO_TIMESTAMP('2009-05-20 18:51:01','YYYY-MM-DD HH24:MI:SS'),0,'N',51117,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:51:02 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51117 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:51:03 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:51:02','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50041,'N',0,'F','N',0,57358,'X',0,0,0,'L','Y','Order Line',0,0,'N','N','N','N','N','N','Sales Order Line','N',0,TO_TIMESTAMP('2009-05-20 18:51:02','YYYY-MM-DD HH24:MI:SS'),0,'N',51118,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:51:03 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51118 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:51:03 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:51:03','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50041,'N',0,'F','N',0,57345,'X',0,0,0,'L','Y','Sales Rep',0,0,'N','N','N','N','N','N','Sales Representative','N',0,TO_TIMESTAMP('2009-05-20 18:51:03','YYYY-MM-DD HH24:MI:SS'),0,'N',51119,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:51:03 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51119 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:51:04 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:51:03','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50041,'N',0,'F','N',0,57352,'X',0,0,0,'L','Y','Serial No',0,0,'N','N','N','N','N','N','Serial No','N',0,TO_TIMESTAMP('2009-05-20 18:51:03','YYYY-MM-DD HH24:MI:SS'),0,'N',51120,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:51:04 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51120 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:51:05 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:51:04','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50041,'N',0,'F','N',0,57339,'X',0,0,0,'L','Y','Tax ID',0,0,'N','N','N','N','N','N','Tax ID','N',0,TO_TIMESTAMP('2009-05-20 18:51:04','YYYY-MM-DD HH24:MI:SS'),0,'N',51121,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:51:05 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51121 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:51:05 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:51:05','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50041,'N',0,'F','N',0,57353,'X',0,0,0,'L','Y','UOM',0,0,'N','N','N','N','N','N','UOM','N',0,TO_TIMESTAMP('2009-05-20 18:51:05','YYYY-MM-DD HH24:MI:SS'),0,'N',51122,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:51:05 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51122 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:51:06 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:51:05','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50041,'N',0,'F','N',0,57356,'X',0,0,0,'L','Y','Workflow',0,0,'N','N','N','N','N','N','Workflow','N',0,TO_TIMESTAMP('2009-05-20 18:51:05','YYYY-MM-DD HH24:MI:SS'),0,'N',51123,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:51:06 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51123 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:51:07 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:51:06','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50041,'N',0,'F','N',0,57374,'X',0,0,0,'T','Y','Yield %',0,0,'N','N','N','N','N','N','Yield %','N',0,TO_TIMESTAMP('2009-05-20 18:51:06','YYYY-MM-DD HH24:MI:SS'),0,'N',51124,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:51:07 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51124 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:51:08 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:51:07','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50041,'N',0,'F','N',0,57332,'X',0,0,0,'L','Y','Updated By',0,0,'N','N','N','N','N','N','Updated By','N',0,TO_TIMESTAMP('2009-05-20 18:51:07','YYYY-MM-DD HH24:MI:SS'),0,'N',51125,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:51:08 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51125 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:51:08 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:51:08','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50041,'N',0,'F','N',0,57380,'X',0,0,0,'L','Y','Description',0,0,'N','N','N','N','N','N','Description','N',0,TO_TIMESTAMP('2009-05-20 18:51:08','YYYY-MM-DD HH24:MI:SS'),0,'N',51126,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:51:08 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51126 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:51:09 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:51:08','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50041,'N',0,'F','N',0,57337,'X',0,0,0,'L','Y','Doc Type',0,0,'N','N','N','N','N','N','Document Type','N',0,TO_TIMESTAMP('2009-05-20 18:51:08','YYYY-MM-DD HH24:MI:SS'),0,'N',51127,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:51:09 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51127 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:51:10 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='N', IsHeightOneLine='N', PrintAreaType='C', AD_PrintFormat_ID=50041, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=0, AD_Column_ID=57342, LineAlignmentType='X', XSpace=0, FieldAlignmentType='L', IsRelativePosition='Y', PrintName='Document Type', YPosition=0, IsSummarized='N', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='N', IsMinCalc='N', Name='Document Type', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=0, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType='N', LineWidth=1, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:51:10','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=51127
;

-- May 20, 2009 6:51:10 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=51127
;

-- May 20, 2009 6:51:11 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:51:10','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50041,'N',0,'F','N',0,57343,'X',0,0,0,'B','Y','Document Type Note',0,0,'N','N','N','N','N','N','Document Type Note','N',0,TO_TIMESTAMP('2009-05-20 18:51:10','YYYY-MM-DD HH24:MI:SS'),0,'N',51128,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:51:11 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51128 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:51:11 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:51:11','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50041,'N',0,'F','N',0,57348,'X',0,0,0,'T','Y','Float After',0,0,'N','N','N','N','N','N','Float After','N',0,TO_TIMESTAMP('2009-05-20 18:51:11','YYYY-MM-DD HH24:MI:SS'),0,'N',51129,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:51:11 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51129 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:51:12 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:51:11','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50041,'N',0,'F','N',0,57349,'X',0,0,0,'T','Y','Float Befored',0,0,'N','N','N','N','N','N','Float Befored','N',0,TO_TIMESTAMP('2009-05-20 18:51:11','YYYY-MM-DD HH24:MI:SS'),0,'N',51130,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:51:12 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51130 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:51:13 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:51:12','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50041,'N',0,'F','N',0,57333,'X',0,0,0,'B','Y','Language',0,0,'N','N','N','N','N','N','Language','N',0,TO_TIMESTAMP('2009-05-20 18:51:12','YYYY-MM-DD HH24:MI:SS'),0,'N',51131,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:51:13 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51131 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:51:14 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:51:13','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50041,'N',0,'F','N',0,57350,'X',0,0,0,'T','Y','Line No',0,0,'N','N','N','N','N','N','Line No','N',0,TO_TIMESTAMP('2009-05-20 18:51:13','YYYY-MM-DD HH24:MI:SS'),0,'N',51132,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:51:14 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51132 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:51:15 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:51:14','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50041,'N',0,'F','N',0,57334,'X',0,0,0,'L','Y','Manufacturing Order',0,0,'N','N','N','N','N','N','Manufacturing Order','N',0,TO_TIMESTAMP('2009-05-20 18:51:14','YYYY-MM-DD HH24:MI:SS'),0,'N',51133,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:51:15 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51133 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:51:15 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:51:15','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50041,'N',0,'F','N',0,57359,'X',0,0,0,'L','Y','Priority',0,0,'N','N','N','N','N','N','Priority','N',0,TO_TIMESTAMP('2009-05-20 18:51:15','YYYY-MM-DD HH24:MI:SS'),0,'N',51134,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:51:16 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51134 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:51:16 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:51:16','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50041,'N',0,'F','N',0,57376,'X',0,0,0,'L','Y','Project',0,0,'N','N','N','N','N','N','Project','N',0,TO_TIMESTAMP('2009-05-20 18:51:16','YYYY-MM-DD HH24:MI:SS'),0,'N',51135,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:51:16 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51135 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:51:17 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:51:16','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50041,'N',0,'F','N',0,57386,'X',0,0,0,'L','Y','Valid from',0,0,'N','N','N','N','N','N','Valid from','N',0,TO_TIMESTAMP('2009-05-20 18:51:16','YYYY-MM-DD HH24:MI:SS'),0,'N',51136,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:51:17 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51136 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:51:18 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:51:17','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50041,'N',0,'F','N',0,57387,'X',0,0,0,'L','Y','Valid to',0,0,'N','N','N','N','N','N','Valid to','N',0,TO_TIMESTAMP('2009-05-20 18:51:17','YYYY-MM-DD HH24:MI:SS'),0,'N',51137,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:51:18 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51137 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:51:19 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:51:18','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50041,'N',0,'F','N',0,57341,'X',0,0,0,'L','Y','Warehouse Address',0,0,'N','N','N','N','N','N','Warehouse Address','N',0,TO_TIMESTAMP('2009-05-20 18:51:18','YYYY-MM-DD HH24:MI:SS'),0,'N',51138,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:51:19 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51138 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:51:20 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:51:19','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50041,'N',0,'F','N',0,57338,'X',0,0,0,'L','Y','Org Address',0,0,'N','N','N','N','N','N','Org Address','N',0,TO_TIMESTAMP('2009-05-20 18:51:19','YYYY-MM-DD HH24:MI:SS'),0,'N',51139,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:51:20 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51139 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:51:21 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:51:20','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50041,'N',0,'F','N',0,57357,'X',0,0,0,'T','Y','Quantity Assay',0,0,'N','N','N','N','N','N','Quantity Assay','N',0,TO_TIMESTAMP('2009-05-20 18:51:20','YYYY-MM-DD HH24:MI:SS'),0,'N',51140,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:51:21 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51140 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:51:26 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:51:21','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50041,'N',0,'F','N',0,57354,'X',0,0,0,'L','Y','Resource',0,0,'N','N','N','N','N','N','Resource','N',0,TO_TIMESTAMP('2009-05-20 18:51:21','YYYY-MM-DD HH24:MI:SS'),0,'N',51141,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:51:26 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51141 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:51:26 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:51:26','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50041,'N',0,'F','N',0,57385,'X',0,0,0,'L','Y','Revision',0,0,'N','N','N','N','N','N','Revision','N',0,TO_TIMESTAMP('2009-05-20 18:51:26','YYYY-MM-DD HH24:MI:SS'),0,'N',51142,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:51:26 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51142 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:51:27 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,AD_PrintColor_ID,PrintFormatType,ImageIsAttached,SeqNo,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,AD_PrintFont_ID,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:51:26','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','Y','Y','H',50041,'N',0,100,'T','N',10,'C',0,0,0,'C','Y','G A R D E N  W O R L D',0,117,0,'N','N','N','N','Y','N','G A R D E N  W O R L D','N',0,TO_TIMESTAMP('2009-05-20 18:51:26','YYYY-MM-DD HH24:MI:SS'),20,'Y',51143,'Y',0,'N',20,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:51:27 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51143 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:51:28 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:51:27','YYYY-MM-DD HH24:MI:SS'),0,10,5,'Y','N','Y','N','H',50041,'N',0,'F','N',20,57335,'X',0,0,0,'L','N','M Order No:',40,0,'N','N','N','N','N','N','Document No','N',0,TO_TIMESTAMP('2009-05-20 18:51:27','YYYY-MM-DD HH24:MI:SS'),0,'N',51144,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:51:28 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51144 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:51:29 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:51:28','YYYY-MM-DD HH24:MI:SS'),0,0,200,'N','N','Y','N','H',50041,'N',0,'F','N',30,57344,'X',0,0,0,'L','N','Planner:',40,0,'N','N','N','N','N','N','Planner','N',0,TO_TIMESTAMP('2009-05-20 18:51:28','YYYY-MM-DD HH24:MI:SS'),0,'N',51145,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:51:29 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51145 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:51:30 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:51:29','YYYY-MM-DD HH24:MI:SS'),0,0,5,'N','N','Y','N','H',50041,'N',0,'F','N',40,57336,'X',0,0,0,'L','N','Doc Status:',50,0,'N','N','N','N','Y','N','Document Status','N',0,TO_TIMESTAMP('2009-05-20 18:51:29','YYYY-MM-DD HH24:MI:SS'),0,'N',51146,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:51:30 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51146 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:51:31 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:51:30','YYYY-MM-DD HH24:MI:SS'),0,0,200,'N','N','Y','N','H',50041,'N',0,'F','N',50,57340,'X',0,0,0,'L','N','Warehouse:',50,0,'N','N','N','N','N','N','Warehouse','N',0,TO_TIMESTAMP('2009-05-20 18:51:30','YYYY-MM-DD HH24:MI:SS'),0,'N',51147,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:51:31 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51147 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:51:31 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:51:31','YYYY-MM-DD HH24:MI:SS'),0,0,5,'N','N','Y','N','H',50041,'N',0,'F','N',60,57383,'X',0,0,0,'L','N','Product:',70,0,'N','N','N','N','N','N','Product','N',0,TO_TIMESTAMP('2009-05-20 18:51:31','YYYY-MM-DD HH24:MI:SS'),0,'N',51148,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:51:31 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51148 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:51:32 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:51:32','YYYY-MM-DD HH24:MI:SS'),0,0,350,'N','N','Y','N','H',50041,'N',0,'F','N',70,57372,'X',0,0,0,'L','N','Reserved:',70,0,'N','N','N','N','N','N','Reserved Quantity','N',0,TO_TIMESTAMP('2009-05-20 18:51:32','YYYY-MM-DD HH24:MI:SS'),0,'N',51149,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:51:32 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51149 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:51:34 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:51:32','YYYY-MM-DD HH24:MI:SS'),0,0,450,'N','N','Y','N','H',50041,'N',0,'F','N',80,57351,'X',0,0,0,'L','N','Lot No:',70,0,'N','N','N','N','N','N','Lot No','N',0,TO_TIMESTAMP('2009-05-20 18:51:32','YYYY-MM-DD HH24:MI:SS'),0,'N',51150,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:51:34 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51150 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:51:34 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:51:34','YYYY-MM-DD HH24:MI:SS'),0,0,5,'N','N','Y','N','H',50041,'N',0,'F','N',90,57363,'X',0,0,0,'T','N','Quantity:',80,0,'N','N','N','N','N','N','Quantity','N',0,TO_TIMESTAMP('2009-05-20 18:51:34','YYYY-MM-DD HH24:MI:SS'),0,'N',51151,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:51:34 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51151 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:51:35 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:51:34','YYYY-MM-DD HH24:MI:SS'),0,0,150,'N','N','Y','N','H',50041,'N',0,'F','N',100,57371,'X',0,0,0,'T','N','Qty Reject:',80,0,'N','N','N','N','N','N','Qty Reject','N',0,TO_TIMESTAMP('2009-05-20 18:51:34','YYYY-MM-DD HH24:MI:SS'),0,'N',51152,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:51:35 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51152 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:51:36 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:51:35','YYYY-MM-DD HH24:MI:SS'),0,0,250,'N','N','Y','N','H',50041,'N',0,'F','N',110,57373,'X',0,0,0,'T','N','% Scrap:',80,0,'N','N','N','N','N','N','QtyScrap','N',0,TO_TIMESTAMP('2009-05-20 18:51:35','YYYY-MM-DD HH24:MI:SS'),0,'N',51153,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:51:36 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51153 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:51:37 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:51:36','YYYY-MM-DD HH24:MI:SS'),0,0,350,'N','N','Y','N','H',50041,'N',0,'F','N',120,57361,'X',0,0,0,'T','N','# Batch:',80,0,'N','N','N','N','N','N','Qty Batchs','N',0,TO_TIMESTAMP('2009-05-20 18:51:36','YYYY-MM-DD HH24:MI:SS'),0,'N',51154,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:51:37 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51154 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:51:38 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:51:37','YYYY-MM-DD HH24:MI:SS'),0,0,450,'N','N','Y','N','H',50041,'N',0,'F','N',130,57360,'X',0,0,0,'D','N','Qty Batch Size:',80,0,'N','N','N','N','N','N','Qty Batch Size','N',0,TO_TIMESTAMP('2009-05-20 18:51:37','YYYY-MM-DD HH24:MI:SS'),0,'N',51155,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:51:38 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51155 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:51:39 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:51:38','YYYY-MM-DD HH24:MI:SS'),0,0,5,'N','N','Y','N','H',50041,'N',0,'F','N',140,57369,'X',0,0,0,'L','N','Date:',100,0,'N','N','N','N','N','N','Date Ordered','N',0,TO_TIMESTAMP('2009-05-20 18:51:38','YYYY-MM-DD HH24:MI:SS'),0,'N',51156,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:51:39 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51156 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:51:39 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:51:39','YYYY-MM-DD HH24:MI:SS'),0,0,330,'N','N','Y','N','H',50041,'N',0,'F','N',150,57370,'X',0,0,0,'L','N','Promised:',100,0,'N','N','N','N','N','N','Date Promised','N',0,TO_TIMESTAMP('2009-05-20 18:51:39','YYYY-MM-DD HH24:MI:SS'),0,'N',51157,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:51:39 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51157 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:51:40 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:51:39','YYYY-MM-DD HH24:MI:SS'),0,0,5,'N','N','Y','N','H',50041,'N',0,'F','N',160,57346,'X',0,0,0,'L','N','Date Start:',110,0,'N','N','N','N','N','N','DateStart','N',0,TO_TIMESTAMP('2009-05-20 18:51:39','YYYY-MM-DD HH24:MI:SS'),0,'N',51158,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:51:40 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51158 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:51:41 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:51:40','YYYY-MM-DD HH24:MI:SS'),0,0,330,'N','N','Y','N','H',50041,'N',0,'F','N',170,57347,'X',0,0,0,'L','N','DateStartSchedule:',110,0,'N','N','N','N','N','N','DateStartSchedule','N',0,TO_TIMESTAMP('2009-05-20 18:51:40','YYYY-MM-DD HH24:MI:SS'),0,'N',51159,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:51:41 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51159 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:51:42 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:51:41','YYYY-MM-DD HH24:MI:SS'),0,0,5,'N','N','Y','N','H',50041,'N',0,'F','N',180,57367,'X',0,0,0,'L','N','Finish Date:',120,0,'N','N','N','N','N','N','Finish Date','N',0,TO_TIMESTAMP('2009-05-20 18:51:41','YYYY-MM-DD HH24:MI:SS'),0,'N',51160,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:51:42 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51160 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:51:43 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:51:42','YYYY-MM-DD HH24:MI:SS'),0,0,330,'N','N','Y','N','H',50041,'N',0,'F','N',190,57368,'X',0,0,0,'L','N','Finish D. Schedule:',120,0,'N','N','N','N','N','N','DateFinishSchedule','N',0,TO_TIMESTAMP('2009-05-20 18:51:42','YYYY-MM-DD HH24:MI:SS'),0,'N',51161,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:51:43 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51161 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:51:44 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:51:43','YYYY-MM-DD HH24:MI:SS'),0,0,5,'N','N','Y','N','H',50041,'N',0,'F','N',200,57365,'X',0,0,0,'L','N','DateConfirm:',130,0,'N','N','N','N','N','N','DateConfirm','N',0,TO_TIMESTAMP('2009-05-20 18:51:43','YYYY-MM-DD HH24:MI:SS'),0,'N',51162,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:51:44 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51162 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:51:44 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:51:44','YYYY-MM-DD HH24:MI:SS'),0,0,330,'N','N','Y','N','H',50041,'N',0,'F','N',210,57366,'X',0,0,0,'L','N','Date Delivered:',130,0,'N','N','N','N','N','N','Date Delivered','N',0,TO_TIMESTAMP('2009-05-20 18:51:44','YYYY-MM-DD HH24:MI:SS'),0,'N',51163,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:51:44 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51163 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:51:45 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,AD_PrintFormatChild_ID,IsGroupBy,MaxHeight,AD_PrintColor_ID,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,YPosition,AD_PrintFont_ID,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:51:44','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','Y','Y','C',50041,50040,'N',0,100,'P','N',220,57334,'X',0,0,0,'D','Y',0,130,0,'N','N','N','N','Y','N','Manufacturing Order BOM Line','N',0,TO_TIMESTAMP('2009-05-20 18:51:44','YYYY-MM-DD HH24:MI:SS'),160,'Y',51164,'Y',0,'N',20,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:51:45 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51164 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:52:48 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormat SET AD_PrintColor_ID=100, AD_PrintFont_ID=119, AD_PrintPaper_ID=100, AD_PrintTableFormat_ID=100, AD_Table_ID=53200, CreateCopy='N', Description='Manufacturing Order Node', FooterMargin=0, HeaderMargin=0, IsActive='Y', IsDefault='N', IsForm='N', IsStandardHeaderFooter='N', IsTableBased='Y', Name='Manufacturing_Order_Node ** TEMPLATE **', PrinterName=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:52:48','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormat_ID=50037
;

-- May 20, 2009 6:52:49 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='N', IsHeightOneLine='N', PrintAreaType='C', AD_PrintFormat_ID=50037, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=0, AD_Column_ID=57500, LineAlignmentType='X', XSpace=0, FieldAlignmentType='L', IsRelativePosition='Y', PrintName='Client', YPosition=0, IsSummarized='N', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='N', IsMinCalc='N', Name='Client', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=0, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType='N', LineWidth=1, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:52:49','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50965
;

-- May 20, 2009 6:52:49 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50965
;

-- May 20, 2009 6:52:49 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='N', IsHeightOneLine='N', PrintAreaType='C', AD_PrintFormat_ID=50037, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=0, AD_Column_ID=57503, LineAlignmentType='X', XSpace=0, FieldAlignmentType='L', IsRelativePosition='Y', PrintName='Created', YPosition=0, IsSummarized='N', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='N', IsMinCalc='N', Name='Created', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=0, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType='N', LineWidth=1, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:52:49','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50966
;

-- May 20, 2009 6:52:49 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50966
;

-- May 20, 2009 6:52:50 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='N', IsHeightOneLine='N', PrintAreaType='C', AD_PrintFormat_ID=50037, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=0, AD_Column_ID=57506, LineAlignmentType='X', XSpace=0, FieldAlignmentType='L', IsRelativePosition='Y', PrintName='Updated By', YPosition=0, IsSummarized='N', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='N', IsMinCalc='N', Name='Updated By', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=0, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType='N', LineWidth=1, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:52:50','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50983
;

-- May 20, 2009 6:52:50 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50983
;

-- May 20, 2009 6:52:50 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='N', IsHeightOneLine='N', PrintAreaType='C', AD_PrintFormat_ID=50037, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=0, AD_Column_ID=57505, LineAlignmentType='X', XSpace=0, FieldAlignmentType='L', IsRelativePosition='Y', PrintName='Updated', YPosition=0, IsSummarized='N', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='N', IsMinCalc='N', Name='Updated', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=0, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType='N', LineWidth=1, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:52:50','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50982
;

-- May 20, 2009 6:52:50 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50982
;

-- May 20, 2009 6:52:51 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='N', IsHeightOneLine='N', PrintAreaType='C', AD_PrintFormat_ID=50037, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=0, AD_Column_ID=57525, LineAlignmentType='X', XSpace=0, FieldAlignmentType='L', IsRelativePosition='Y', PrintName='Manufacturing Order Workflow', YPosition=0, IsSummarized='N', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='N', IsMinCalc='N', Name='Manufacturing Order Workflow', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=0, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType='N', LineWidth=1, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:52:51','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50976
;

-- May 20, 2009 6:52:51 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50976
;

-- May 20, 2009 6:52:51 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='N', IsHeightOneLine='N', PrintAreaType='C', AD_PrintFormat_ID=50037, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=0, AD_Column_ID=57527, LineAlignmentType='X', XSpace=0, FieldAlignmentType='T', IsRelativePosition='Y', PrintName='Delivered Qty', YPosition=0, IsSummarized='N', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='N', IsMinCalc='N', Name='Delivered Quantity', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=0, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType='N', LineWidth=1, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:52:51','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50970
;

-- May 20, 2009 6:52:51 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50970
;

-- May 20, 2009 6:52:52 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='N', IsHeightOneLine='N', PrintAreaType='C', AD_PrintFormat_ID=50037, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=0, AD_Column_ID=57517, LineAlignmentType='X', XSpace=0, FieldAlignmentType='T', IsRelativePosition='Y', PrintName='Duration Real', YPosition=0, IsSummarized='N', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='N', IsMinCalc='N', Name='Duration Real', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=0, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType='N', LineWidth=1, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:52:52','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50973
;

-- May 20, 2009 6:52:52 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50973
;

-- May 20, 2009 6:52:52 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='N', IsHeightOneLine='N', PrintAreaType='C', AD_PrintFormat_ID=50037, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=0, AD_Column_ID=57501, LineAlignmentType='X', XSpace=0, FieldAlignmentType='L', IsRelativePosition='Y', PrintName='Organization', YPosition=0, IsSummarized='N', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='N', IsMinCalc='N', Name='Organization', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=0, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType='N', LineWidth=1, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:52:52','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50977
;

-- May 20, 2009 6:52:52 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50977
;

-- May 20, 2009 6:52:53 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='N', IsHeightOneLine='N', PrintAreaType='C', AD_PrintFormat_ID=50037, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=0, AD_Column_ID=57529, LineAlignmentType='X', XSpace=0, FieldAlignmentType='T', IsRelativePosition='Y', PrintName='% Scrap', YPosition=0, IsSummarized='N', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='N', IsMinCalc='N', Name='QtyScrap', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=0, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType='N', LineWidth=1, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:52:53','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50979
;

-- May 20, 2009 6:52:53 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50979
;

-- May 20, 2009 6:52:53 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='N', IsHeightOneLine='N', PrintAreaType='C', AD_PrintFormat_ID=50037, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=0, AD_Column_ID=57502, LineAlignmentType='X', XSpace=0, FieldAlignmentType='L', IsRelativePosition='Y', PrintName='Active', YPosition=0, IsSummarized='N', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='N', IsMinCalc='N', Name='Active', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=0, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType='N', LineWidth=1, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:52:53','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50964
;

-- May 20, 2009 6:52:53 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50964
;

-- May 20, 2009 6:52:54 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='N', IsHeightOneLine='N', PrintAreaType='C', AD_PrintFormat_ID=50037, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=0, AD_Column_ID=57504, LineAlignmentType='X', XSpace=0, FieldAlignmentType='L', IsRelativePosition='Y', PrintName='Created By', YPosition=0, IsSummarized='N', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='N', IsMinCalc='N', Name='Created By', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=0, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType='N', LineWidth=1, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:52:54','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50967
;

-- May 20, 2009 6:52:54 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50967
;

-- May 20, 2009 6:52:54 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='N', IsHeightOneLine='N', PrintAreaType='C', AD_PrintFormat_ID=50037, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=0, AD_Column_ID=57524, LineAlignmentType='X', XSpace=0, FieldAlignmentType='L', IsRelativePosition='Y', PrintName='Manufacturing Order', YPosition=0, IsSummarized='N', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='N', IsMinCalc='N', Name='Manufacturing Order', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=0, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType='N', LineWidth=1, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:52:54','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50975
;

-- May 20, 2009 6:52:54 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50975
;

-- May 20, 2009 6:52:55 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='Y', IsHeightOneLine='Y', PrintAreaType='C', AD_PrintFormat_ID=50037, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=10, AD_Column_ID=57531, LineAlignmentType='X', XSpace=0, FieldAlignmentType='L', IsRelativePosition='Y', PrintName='Resource', YPosition=0, IsSummarized='N', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='Y', IsNextLine='N', IsMinCalc='N', Name='Resource', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=40, IsNextPage='N', RunningTotalLines=0, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType='N', LineWidth=1, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:52:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50980
;

-- May 20, 2009 6:52:55 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50980
;

-- May 20, 2009 6:52:55 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='Y', IsHeightOneLine='N', PrintAreaType='C', AD_PrintFormat_ID=50037, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=20, AD_Column_ID=57537, LineAlignmentType='X', XSpace=0, FieldAlignmentType='L', IsRelativePosition='Y', PrintName='Search Key', YPosition=0, IsSummarized='N', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='N', IsMinCalc='N', Name='Search Key', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=0, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType='N', LineWidth=1, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:52:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50981
;

-- May 20, 2009 6:52:55 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50981
;

-- May 20, 2009 6:52:56 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='Y', IsHeightOneLine='N', PrintAreaType='C', AD_PrintFormat_ID=50037, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=30, AD_Column_ID=57507, LineAlignmentType='X', XSpace=0, FieldAlignmentType='L', IsRelativePosition='Y', PrintName='Name', YPosition=0, IsSummarized='N', IsSuppressNull='Y', IsPageBreak='N', IsFixedWidth='N', IsNextLine='N', IsMinCalc='N', Name='Name', IsAveraged='N', BelowColumn=2, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=0, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType='N', LineWidth=1, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:52:56','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50984
;

-- May 20, 2009 6:52:56 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50984
;

-- May 20, 2009 6:52:56 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='Y', IsHeightOneLine='N', PrintAreaType='C', AD_PrintFormat_ID=50037, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=40, AD_Column_ID=57514, LineAlignmentType='X', XSpace=0, FieldAlignmentType='B', IsRelativePosition='Y', PrintName='Description', YPosition=0, IsSummarized='N', IsSuppressNull='Y', IsPageBreak='N', IsFixedWidth='N', IsNextLine='Y', IsMinCalc='N', Name='Description', IsAveraged='N', BelowColumn=3, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=20, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType='N', LineWidth=1, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:52:56','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50985
;

-- May 20, 2009 6:52:56 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50985
;

-- May 20, 2009 6:52:57 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='Y', IsHeightOneLine='Y', PrintAreaType='C', AD_PrintFormat_ID=50037, IsGroupBy='N', MaxHeight=0, AD_PrintColor_ID=100, PrintFormatType='F', ImageIsAttached='N', SeqNo=50, AD_Column_ID=57519, LineAlignmentType='X', XSpace=0, FieldAlignmentType='D', IsRelativePosition='Y', PrintName='Help', YPosition=0, AD_PrintFont_ID=130, IsSummarized='N', IsSuppressNull='Y', IsPageBreak='N', IsFixedWidth='N', IsNextLine='Y', IsMinCalc='N', Name='Help', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=20, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType='N', LineWidth=1, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:52:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=51076
;

-- May 20, 2009 6:52:57 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=51076
;

-- May 20, 2009 6:52:57 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='Y', IsHeightOneLine='N', PrintAreaType='C', AD_PrintFormat_ID=50037, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=60, AD_Column_ID=57511, LineAlignmentType='X', XSpace=0, FieldAlignmentType='L', IsRelativePosition='Y', PrintName='DateFinishSchedule', YPosition=0, IsSummarized='N', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='N', IsMinCalc='N', Name='DateFinishSchedule', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=0, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType='N', LineWidth=1, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:52:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50968
;

-- May 20, 2009 6:52:57 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50968
;

-- May 20, 2009 6:52:58 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='Y', IsHeightOneLine='N', PrintAreaType='C', AD_PrintFormat_ID=50037, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=70, AD_Column_ID=57511, LineAlignmentType='X', XSpace=0, FieldAlignmentType='L', IsRelativePosition='Y', PrintName='DateFinishSchedule', YPosition=0, IsSummarized='N', IsSuppressNull='Y', IsPageBreak='N', IsFixedWidth='N', IsNextLine='Y', IsMinCalc='N', Name='DateFinishSchedule', IsAveraged='N', BelowColumn=5, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=0, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType='N', LineWidth=1, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:52:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50968
;

-- May 20, 2009 6:52:58 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50968
;

-- May 20, 2009 6:52:58 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='Y', IsHeightOneLine='N', PrintAreaType='C', AD_PrintFormat_ID=50037, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=80, AD_Column_ID=57538, LineAlignmentType='X', XSpace=0, FieldAlignmentType='L', IsRelativePosition='Y', PrintName='Waiting Time', YPosition=0, IsSummarized='Y', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='Y', IsMinCalc='N', Name='Waiting Time', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=20, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType='N', LineWidth=1, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:52:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50991
;

-- May 20, 2009 6:52:58 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50991
;

-- May 20, 2009 6:52:59 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='Y', IsHeightOneLine='N', PrintAreaType='C', AD_PrintFormat_ID=50037, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=90, AD_Column_ID=57532, LineAlignmentType='X', XSpace=0, FieldAlignmentType='L', IsRelativePosition='Y', PrintName='Setup', YPosition=0, IsSummarized='Y', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='N', IsMinCalc='N', Name='Setup T', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=20, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType='N', LineWidth=1, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:52:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50992
;

-- May 20, 2009 6:52:59 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50992
;

-- May 20, 2009 6:52:59 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='Y', IsHeightOneLine='N', PrintAreaType='C', AD_PrintFormat_ID=50037, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=100, AD_Column_ID=57516, LineAlignmentType='X', XSpace=0, FieldAlignmentType='L', IsRelativePosition='Y', PrintName='Duration', YPosition=0, IsSummarized='Y', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='N', IsMinCalc='N', Name='Duration', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=0, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType='N', LineWidth=1, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:52:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50972
;

-- May 20, 2009 6:52:59 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50972
;

-- May 20, 2009 6:53:00 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='Y', IsHeightOneLine='N', PrintAreaType='C', AD_PrintFormat_ID=50037, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=110, AD_Column_ID=57518, LineAlignmentType='X', XSpace=0, FieldAlignmentType='L', IsRelativePosition='Y', PrintName='Duration Requiered', YPosition=0, IsSummarized='Y', IsSuppressNull='Y', IsPageBreak='N', IsFixedWidth='N', IsNextLine='Y', IsMinCalc='N', Name='Duration Requiered', IsAveraged='N', BelowColumn=9, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=0, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType='N', LineWidth=1, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:53:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50974
;

-- May 20, 2009 6:53:00 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50974
;

-- May 20, 2009 6:53:00 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='Y', IsHeightOneLine='N', PrintAreaType='C', AD_PrintFormat_ID=50037, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=120, AD_Column_ID=57522, LineAlignmentType='X', XSpace=0, FieldAlignmentType='L', IsRelativePosition='Y', PrintName='Moving Time', YPosition=0, IsSummarized='Y', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='Y', IsMinCalc='N', Name='Moving Time', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=20, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType='N', LineWidth=1, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:53:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50989
;

-- May 20, 2009 6:53:00 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50989
;

-- May 20, 2009 6:53:01 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='Y', IsHeightOneLine='N', PrintAreaType='C', AD_PrintFormat_ID=50037, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=130, AD_Column_ID=57530, LineAlignmentType='X', XSpace=0, FieldAlignmentType='L', IsRelativePosition='Y', PrintName='Queuing Time', YPosition=0, IsSummarized='Y', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='Y', IsMinCalc='N', Name='Queuing Time', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=20, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType='N', LineWidth=1, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:53:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50990
;

-- May 20, 2009 6:53:01 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50990
;

-- May 20, 2009 6:53:02 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='Y', IsHeightOneLine='N', PrintAreaType='C', AD_PrintFormat_ID=50037, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=140, AD_Column_ID=57551, LineAlignmentType='X', XSpace=0, FieldAlignmentType='L', IsRelativePosition='Y', PrintName='Doc Status', YPosition=0, IsSummarized='Y', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='N', IsMinCalc='N', Name='Document Status', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=0, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType='N', LineWidth=1, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:53:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50971
;

-- May 20, 2009 6:53:02 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50971
;

-- May 20, 2009 6:53:02 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='Y', IsHeightOneLine='N', PrintAreaType='C', AD_PrintFormat_ID=50037, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=150, AD_Column_ID=57520, LineAlignmentType='X', XSpace=0, FieldAlignmentType='L', IsRelativePosition='Y', PrintName='Is Milestone', YPosition=0, IsSummarized='N', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='Y', IsMinCalc='N', Name='Is Milestone', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=20, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType='N', LineWidth=1, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:53:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50987
;

-- May 20, 2009 6:53:02 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50987
;

-- May 20, 2009 6:53:03 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='Y', IsHeightOneLine='N', PrintAreaType='C', AD_PrintFormat_ID=50037, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=160, AD_Column_ID=57521, LineAlignmentType='X', XSpace=0, FieldAlignmentType='L', IsRelativePosition='Y', PrintName='Is Subcontracting', YPosition=0, IsSummarized='N', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='N', IsMinCalc='N', Name='Is Subcontracting', IsAveraged='N', BelowColumn=14, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=20, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType='N', LineWidth=1, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:53:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50988
;

-- May 20, 2009 6:53:03 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50988
;

-- May 20, 2009 6:53:03 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='Y', IsHeightOneLine='N', PrintAreaType='C', AD_PrintFormat_ID=50037, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=170, AD_Column_ID=57508, LineAlignmentType='X', XSpace=0, FieldAlignmentType='L', IsRelativePosition='Y', PrintName='Business Partner ', YPosition=0, IsSummarized='N', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='Y', IsMinCalc='N', Name='Business Partner ', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=20, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType='N', LineWidth=1, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:53:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50986
;

-- May 20, 2009 6:53:03 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50986
;

-- May 20, 2009 6:53:04 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormat (AD_Client_ID,AD_Org_ID,AD_PrintColor_ID,AD_PrintFont_ID,AD_PrintFormat_ID,AD_PrintPaper_ID,AD_PrintTableFormat_ID,AD_Table_ID,CreateCopy,Created,CreatedBy,FooterMargin,HeaderMargin,IsActive,IsDefault,IsForm,IsStandardHeaderFooter,IsTableBased,Name,Updated,UpdatedBy) VALUES (0,0,100,119,50042,101,100,53199,'N',TO_TIMESTAMP('2009-05-20 18:53:03','YYYY-MM-DD HH24:MI:SS'),0,0,0,'Y','N','Y','N','Y','Manufacturing_Order_Workflow_Header ** TEMPLATE **',TO_TIMESTAMP('2009-05-20 18:53:03','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 20, 2009 6:53:05 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:53:04','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50042,'N',0,'F','N',0,57454,'X',0,0,0,'L','Y','Serial No',0,0,'N','N','N','N','N','N','Serial No','N',0,TO_TIMESTAMP('2009-05-20 18:53:04','YYYY-MM-DD HH24:MI:SS'),0,'N',51165,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:53:05 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51165 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:53:06 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:53:05','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50042,'N',0,'F','N',0,57431,'X',0,0,0,'L','Y','Active',0,0,'N','N','N','N','N','N','Active','N',0,TO_TIMESTAMP('2009-05-20 18:53:05','YYYY-MM-DD HH24:MI:SS'),0,'N',51166,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:53:06 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51166 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:53:07 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:53:06','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50042,'N',0,'F','N',0,57429,'X',0,0,0,'L','Y','Client',0,0,'N','N','N','N','N','N','Client','N',0,TO_TIMESTAMP('2009-05-20 18:53:06','YYYY-MM-DD HH24:MI:SS'),0,'N',51167,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:53:07 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51167 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:53:09 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:53:07','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50042,'N',0,'F','N',0,57432,'X',0,0,0,'L','Y','Created',0,0,'N','N','N','N','N','N','Created','N',0,TO_TIMESTAMP('2009-05-20 18:53:07','YYYY-MM-DD HH24:MI:SS'),0,'N',51168,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:53:09 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51168 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:53:10 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:53:09','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50042,'N',0,'F','N',0,57433,'X',0,0,0,'L','Y','Created By',0,0,'N','N','N','N','N','N','Created By','N',0,TO_TIMESTAMP('2009-05-20 18:53:09','YYYY-MM-DD HH24:MI:SS'),0,'N',51169,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:53:10 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51169 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:53:11 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:53:10','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50042,'N',0,'F','N',0,57481,'X',0,0,0,'L','Y','Author',0,0,'N','N','N','N','N','N','Author','N',0,TO_TIMESTAMP('2009-05-20 18:53:10','YYYY-MM-DD HH24:MI:SS'),0,'N',51170,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:53:11 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51170 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:53:12 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:53:11','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50042,'N',0,'F','N',0,57491,'X',0,0,0,'L','Y','Publication Status',0,0,'N','N','N','N','N','N','Publication Status','N',0,TO_TIMESTAMP('2009-05-20 18:53:11','YYYY-MM-DD HH24:MI:SS'),0,'N',51171,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:53:12 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51171 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:53:13 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:53:12','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50042,'N',0,'F','N',0,57483,'X',0,0,0,'L','Y','Document No',0,0,'N','N','N','N','N','N','Document No','N',0,TO_TIMESTAMP('2009-05-20 18:53:12','YYYY-MM-DD HH24:MI:SS'),0,'N',51172,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:53:13 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51172 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:53:14 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:53:13','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50042,'N',0,'F','N',0,57465,'X',0,0,0,'T','Y','Ordered Qty',0,0,'N','N','N','N','N','N','Ordered Quantity','N',0,TO_TIMESTAMP('2009-05-20 18:53:13','YYYY-MM-DD HH24:MI:SS'),0,'N',51173,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:53:14 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51173 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:53:14 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:53:14','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50042,'N',0,'F','N',0,57434,'X',0,0,0,'L','Y','Updated',0,0,'N','N','N','N','N','N','Updated','N',0,TO_TIMESTAMP('2009-05-20 18:53:14','YYYY-MM-DD HH24:MI:SS'),0,'N',51174,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:53:14 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51174 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:53:16 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:53:14','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50042,'N',0,'F','N',0,57435,'X',0,0,0,'L','Y','Updated By',0,0,'N','N','N','N','N','N','Updated By','N',0,TO_TIMESTAMP('2009-05-20 18:53:14','YYYY-MM-DD HH24:MI:SS'),0,'N',51175,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:53:16 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51175 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:53:16 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:53:16','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50042,'N',0,'F','N',0,57477,'X',0,0,0,'L','Y','Activity',0,0,'N','N','N','N','N','N','Activity','N',0,TO_TIMESTAMP('2009-05-20 18:53:16','YYYY-MM-DD HH24:MI:SS'),0,'N',51176,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:53:16 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51176 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:53:17 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:53:16','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50042,'N',0,'F','N',0,57474,'X',0,0,0,'T','Y','% Scrap',0,0,'N','N','N','N','N','N','QtyScrap','N',0,TO_TIMESTAMP('2009-05-20 18:53:16','YYYY-MM-DD HH24:MI:SS'),0,'N',51177,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:53:17 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51177 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:53:18 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:53:17','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50042,'N',0,'F','N',0,57462,'X',0,0,0,'T','Y','Qty Batchs',0,0,'N','N','N','N','N','N','Qty Batchs','N',0,TO_TIMESTAMP('2009-05-20 18:53:17','YYYY-MM-DD HH24:MI:SS'),0,'N',51178,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:53:18 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51178 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:53:19 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:53:18','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50042,'N',0,'F','N',0,57459,'X',0,0,0,'T','Y','Quantity Assay',0,0,'N','N','N','N','N','N','Quantity Assay','N',0,TO_TIMESTAMP('2009-05-20 18:53:18','YYYY-MM-DD HH24:MI:SS'),0,'N',51179,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:53:19 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51179 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:53:20 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:53:19','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50042,'N',0,'F','N',0,57473,'X',0,0,0,'T','Y','Reserved Qty',0,0,'N','N','N','N','N','N','Reserved Quantity','N',0,TO_TIMESTAMP('2009-05-20 18:53:19','YYYY-MM-DD HH24:MI:SS'),0,'N',51180,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:53:20 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51180 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:53:21 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:53:20','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50042,'N',0,'F','N',0,57456,'X',0,0,0,'L','Y','Resource',0,0,'N','N','N','N','N','N','Resource','N',0,TO_TIMESTAMP('2009-05-20 18:53:20','YYYY-MM-DD HH24:MI:SS'),0,'N',51181,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:53:21 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51181 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:53:22 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:53:21','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50042,'N',0,'F','N',0,57460,'X',0,0,0,'L','Y','Order Line',0,0,'N','N','N','N','N','N','Sales Order Line','N',0,TO_TIMESTAMP('2009-05-20 18:53:21','YYYY-MM-DD HH24:MI:SS'),0,'N',51182,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:53:22 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51182 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:53:23 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:53:22','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50042,'N',0,'F','N',0,57447,'X',0,0,0,'L','Y','Sales Rep',0,0,'N','N','N','N','N','N','Sales Representative','N',0,TO_TIMESTAMP('2009-05-20 18:53:22','YYYY-MM-DD HH24:MI:SS'),0,'N',51183,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:53:23 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51183 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:53:24 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:53:23','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50042,'N',0,'F','N',0,57441,'X',0,0,0,'L','Y','Tax ID',0,0,'N','N','N','N','N','N','Tax ID','N',0,TO_TIMESTAMP('2009-05-20 18:53:23','YYYY-MM-DD HH24:MI:SS'),0,'N',51184,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:53:24 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51184 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:53:24 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:53:24','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50042,'N',0,'F','N',0,57455,'X',0,0,0,'L','Y','UOM',0,0,'N','N','N','N','N','N','UOM','N',0,TO_TIMESTAMP('2009-05-20 18:53:24','YYYY-MM-DD HH24:MI:SS'),0,'N',51185,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:53:24 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51185 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:53:25 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:53:24','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50042,'N',0,'F','N',0,57495,'X',0,0,0,'T','Y','Units by Cycles',0,0,'N','N','N','N','N','N','Units by Cycles','N',0,TO_TIMESTAMP('2009-05-20 18:53:24','YYYY-MM-DD HH24:MI:SS'),0,'N',51186,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:53:25 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51186 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:53:26 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:53:25','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50042,'N',0,'F','N',0,57487,'X',0,0,0,'L','Y','Valid from',0,0,'N','N','N','N','N','N','Valid from','N',0,TO_TIMESTAMP('2009-05-20 18:53:25','YYYY-MM-DD HH24:MI:SS'),0,'N',51187,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:53:26 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51187 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:53:27 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:53:26','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50042,'N',0,'F','N',0,57488,'X',0,0,0,'L','Y','Valid to',0,0,'N','N','N','N','N','N','Valid to','N',0,TO_TIMESTAMP('2009-05-20 18:53:26','YYYY-MM-DD HH24:MI:SS'),0,'N',51188,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:53:27 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51188 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:53:27 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:53:27','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50042,'N',0,'F','N',0,57486,'X',0,0,0,'T','Y','Version',0,0,'N','N','N','N','N','N','Version','N',0,TO_TIMESTAMP('2009-05-20 18:53:27','YYYY-MM-DD HH24:MI:SS'),0,'N',51189,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:53:28 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51189 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:53:28 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:53:28','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50042,'N',0,'F','N',0,57442,'X',0,0,0,'L','Y','Warehouse',0,0,'N','N','N','N','N','N','Warehouse','N',0,TO_TIMESTAMP('2009-05-20 18:53:28','YYYY-MM-DD HH24:MI:SS'),0,'N',51190,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:53:28 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51190 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:53:29 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:53:28','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50042,'N',0,'F','N',0,57443,'X',0,0,0,'L','Y','Warehouse Address',0,0,'N','N','N','N','N','N','Warehouse Address','N',0,TO_TIMESTAMP('2009-05-20 18:53:28','YYYY-MM-DD HH24:MI:SS'),0,'N',51191,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:53:29 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51191 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:53:30 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:53:29','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50042,'N',0,'F','N',0,57497,'X',0,0,0,'L','Y','Workflow Type',0,0,'N','N','N','N','N','N','Workflow Type','N',0,TO_TIMESTAMP('2009-05-20 18:53:29','YYYY-MM-DD HH24:MI:SS'),0,'N',51192,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:53:30 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51192 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:53:30 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:53:30','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50042,'N',0,'F','N',0,57498,'X',0,0,0,'T','Y','Working Time',0,0,'N','N','N','N','N','N','Working Time','N',0,TO_TIMESTAMP('2009-05-20 18:53:30','YYYY-MM-DD HH24:MI:SS'),0,'N',51193,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:53:30 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51193 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:53:31 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:53:30','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50042,'N',0,'F','N',0,57499,'X',0,0,0,'T','Y','Yield %',0,0,'N','N','N','N','N','N','Yield %','N',0,TO_TIMESTAMP('2009-05-20 18:53:30','YYYY-MM-DD HH24:MI:SS'),0,'N',51194,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:53:31 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51194 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:53:32 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:53:31','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50042,'N',0,'F','N',0,57472,'X',0,0,0,'T','Y','Qty Reject',0,0,'N','N','N','N','N','N','Qty Reject','N',0,TO_TIMESTAMP('2009-05-20 18:53:31','YYYY-MM-DD HH24:MI:SS'),0,'N',51195,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:53:32 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51195 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:53:32 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:53:32','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50042,'N',0,'F','N',0,57475,'X',0,0,0,'L','Y','Campaign',0,0,'N','N','N','N','N','N','Campaign','N',0,TO_TIMESTAMP('2009-05-20 18:53:32','YYYY-MM-DD HH24:MI:SS'),0,'N',51196,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:53:32 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51196 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:53:33 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:53:32','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50042,'N',0,'F','N',0,57480,'X',0,0,0,'B','Y','Comment',0,0,'N','N','N','N','N','N','Comment/Help','N',0,TO_TIMESTAMP('2009-05-20 18:53:32','YYYY-MM-DD HH24:MI:SS'),0,'N',51197,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:53:33 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51197 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:53:34 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:53:33','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50042,'N',0,'F','N',0,57482,'X',0,0,0,'T','Y','Cost',0,0,'N','N','N','N','N','N','Cost','N',0,TO_TIMESTAMP('2009-05-20 18:53:33','YYYY-MM-DD HH24:MI:SS'),0,'N',51198,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:53:34 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51198 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:53:35 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:53:34','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50042,'N',0,'F','N',0,57430,'X',0,0,0,'L','Y','Organization',0,0,'N','N','N','N','N','N','Organization','N',0,TO_TIMESTAMP('2009-05-20 18:53:34','YYYY-MM-DD HH24:MI:SS'),0,'N',51199,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:53:35 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51199 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:53:35 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:53:35','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50042,'N',0,'F','N',0,57479,'X',0,0,0,'L','Y','Description',0,0,'N','N','N','N','N','N','Description','N',0,TO_TIMESTAMP('2009-05-20 18:53:35','YYYY-MM-DD HH24:MI:SS'),0,'N',51200,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:53:35 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51200 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:53:36 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:53:35','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50042,'N',0,'F','N',0,57438,'X',0,0,0,'L','Y','Doc Status',0,0,'N','N','N','N','N','N','Document Status','N',0,TO_TIMESTAMP('2009-05-20 18:53:35','YYYY-MM-DD HH24:MI:SS'),0,'N',51201,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:53:36 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51201 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:53:37 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:53:36','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50042,'N',0,'F','N',0,57439,'X',0,0,0,'L','Y','Doc Type',0,0,'N','N','N','N','N','N','Document Type','N',0,TO_TIMESTAMP('2009-05-20 18:53:36','YYYY-MM-DD HH24:MI:SS'),0,'N',51202,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:53:37 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51202 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:53:38 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='N', IsHeightOneLine='N', PrintAreaType='C', AD_PrintFormat_ID=50042, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=0, AD_Column_ID=57444, LineAlignmentType='X', XSpace=0, FieldAlignmentType='L', IsRelativePosition='Y', PrintName='Document Type', YPosition=0, IsSummarized='N', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='N', IsMinCalc='N', Name='Document Type', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=0, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType='N', LineWidth=1, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:53:38','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=51202
;

-- May 20, 2009 6:53:38 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=51202
;

-- May 20, 2009 6:53:38 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:53:38','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50042,'N',0,'F','N',0,57445,'X',0,0,0,'B','Y','Document Type Note',0,0,'N','N','N','N','N','N','Document Type Note','N',0,TO_TIMESTAMP('2009-05-20 18:53:38','YYYY-MM-DD HH24:MI:SS'),0,'N',51203,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:53:38 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51203 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:53:39 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:53:38','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50042,'N',0,'F','N',0,57450,'X',0,0,0,'T','Y','Float After',0,0,'N','N','N','N','N','N','Float After','N',0,TO_TIMESTAMP('2009-05-20 18:53:38','YYYY-MM-DD HH24:MI:SS'),0,'N',51204,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:53:39 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51204 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:53:40 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:53:39','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50042,'N',0,'F','N',0,57451,'X',0,0,0,'T','Y','Float Befored',0,0,'N','N','N','N','N','N','Float Befored','N',0,TO_TIMESTAMP('2009-05-20 18:53:39','YYYY-MM-DD HH24:MI:SS'),0,'N',51205,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:53:40 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51205 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:53:40 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:53:40','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50042,'N',0,'F','N',0,57436,'X',0,0,0,'B','Y','Language',0,0,'N','N','N','N','N','N','Language','N',0,TO_TIMESTAMP('2009-05-20 18:53:40','YYYY-MM-DD HH24:MI:SS'),0,'N',51206,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:53:40 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51206 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:53:42 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:53:40','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50042,'N',0,'F','N',0,57452,'X',0,0,0,'T','Y','Line No',0,0,'N','N','N','N','N','N','Line No','N',0,TO_TIMESTAMP('2009-05-20 18:53:40','YYYY-MM-DD HH24:MI:SS'),0,'N',51207,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:53:42 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51207 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:53:43 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:53:42','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50042,'N',0,'F','N',0,57453,'X',0,0,0,'L','Y','Lot No',0,0,'N','N','N','N','N','N','Lot No','N',0,TO_TIMESTAMP('2009-05-20 18:53:42','YYYY-MM-DD HH24:MI:SS'),0,'N',51208,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:53:43 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51208 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:53:44 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:53:43','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50042,'N',0,'F','N',0,57440,'X',0,0,0,'L','Y','Org Address',0,0,'N','N','N','N','N','N','Org Address','N',0,TO_TIMESTAMP('2009-05-20 18:53:43','YYYY-MM-DD HH24:MI:SS'),0,'N',51209,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:53:44 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51209 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:53:44 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:53:44','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50042,'N',0,'F','N',0,57461,'X',0,0,0,'L','Y','Priority',0,0,'N','N','N','N','N','N','Priority','N',0,TO_TIMESTAMP('2009-05-20 18:53:44','YYYY-MM-DD HH24:MI:SS'),0,'N',51210,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:53:44 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51210 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:53:45 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:53:44','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50042,'N',0,'F','N',0,57476,'X',0,0,0,'L','Y','Project',0,0,'N','N','N','N','N','N','Project','N',0,TO_TIMESTAMP('2009-05-20 18:53:44','YYYY-MM-DD HH24:MI:SS'),0,'N',51211,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:53:45 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51211 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:53:46 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,AD_PrintColor_ID,PrintFormatType,ImageIsAttached,SeqNo,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,AD_PrintFont_ID,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:53:45','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','Y','Y','C',50042,'N',0,100,'T','N',10,'C',0,0,0,'C','Y','G A R D E N  W O R L D',0,130,0,'N','N','N','N','N','N','G A R D E N  W O R L D','N',0,TO_TIMESTAMP('2009-05-20 18:53:45','YYYY-MM-DD HH24:MI:SS'),5,'Y',51212,'Y',0,'N',20,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:53:46 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51212 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:53:46 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:53:46','YYYY-MM-DD HH24:MI:SS'),0,0,5,'N','N','Y','N','H',50042,'N',0,'F','N',20,57458,'X',0,0,0,'L','N','Workflow:',20,0,'N','N','N','N','N','N','Workflow','N',0,TO_TIMESTAMP('2009-05-20 18:53:46','YYYY-MM-DD HH24:MI:SS'),0,'N',51213,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:53:46 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51213 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:53:47 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:53:46','YYYY-MM-DD HH24:MI:SS'),0,0,5,'N','N','Y','N','H',50042,'N',0,'F','N',30,57437,'X',0,0,0,'L','N','M. Order No:',30,0,'N','N','N','N','N','N','Manufacturing Order','N',0,TO_TIMESTAMP('2009-05-20 18:53:46','YYYY-MM-DD HH24:MI:SS'),0,'N',51214,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:53:47 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51214 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:53:48 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:53:47','YYYY-MM-DD HH24:MI:SS'),0,0,5,'N','N','Y','N','H',50042,'N',0,'F','N',40,57457,'X',0,0,0,'L','N','BOM & Formula:',40,0,'N','N','N','N','N','N','BOM & Formula','N',0,TO_TIMESTAMP('2009-05-20 18:53:47','YYYY-MM-DD HH24:MI:SS'),0,'N',51215,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:53:48 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51215 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:53:49 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:53:48','YYYY-MM-DD HH24:MI:SS'),0,0,350,'N','N','Y','N','H',50042,'N',0,'F','N',50,57446,'X',0,0,0,'L','N','Planner:',40,0,'N','N','N','N','N','N','Planner','N',0,TO_TIMESTAMP('2009-05-20 18:53:48','YYYY-MM-DD HH24:MI:SS'),0,'N',51216,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:53:49 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51216 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:53:50 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:53:49','YYYY-MM-DD HH24:MI:SS'),0,0,5,'N','N','Y','N','H',50042,'N',0,'F','N',60,57470,'X',0,0,0,'L','N','Date Ordered:',60,0,'N','N','N','N','N','N','Date Ordered','N',0,TO_TIMESTAMP('2009-05-20 18:53:49','YYYY-MM-DD HH24:MI:SS'),0,'N',51217,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:53:50 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51217 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:53:50 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:53:50','YYYY-MM-DD HH24:MI:SS'),0,0,300,'N','N','Y','N','H',50042,'N',0,'F','N',70,57471,'X',0,0,0,'L','N','Date Promised:',60,0,'N','N','N','N','N','N','Date Promised','N',0,TO_TIMESTAMP('2009-05-20 18:53:50','YYYY-MM-DD HH24:MI:SS'),0,'N',51218,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:53:50 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51218 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:53:51 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:53:50','YYYY-MM-DD HH24:MI:SS'),0,0,5,'N','N','Y','N','H',50042,'N',0,'F','N',80,57448,'X',0,0,0,'L','N','Date Start:',70,0,'N','N','N','N','N','N','DateStart','N',0,TO_TIMESTAMP('2009-05-20 18:53:50','YYYY-MM-DD HH24:MI:SS'),0,'N',51219,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:53:51 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51219 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:53:51 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:53:51','YYYY-MM-DD HH24:MI:SS'),0,0,300,'N','N','Y','N','H',50042,'N',0,'F','N',90,57449,'X',0,0,0,'L','N','DateStartSchedule:',70,0,'N','N','N','N','N','N','DateStartSchedule','N',0,TO_TIMESTAMP('2009-05-20 18:53:51','YYYY-MM-DD HH24:MI:SS'),0,'N',51220,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:53:51 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51220 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:53:52 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:53:51','YYYY-MM-DD HH24:MI:SS'),0,0,5,'N','N','Y','N','H',50042,'N',0,'F','N',100,57468,'X',0,0,0,'L','N','Finish Date:',80,0,'N','N','N','N','N','N','Finish Date','N',0,TO_TIMESTAMP('2009-05-20 18:53:51','YYYY-MM-DD HH24:MI:SS'),0,'N',51221,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:53:52 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51221 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:53:52 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:53:52','YYYY-MM-DD HH24:MI:SS'),0,0,300,'N','N','Y','N','H',50042,'N',0,'F','N',110,57469,'X',0,0,0,'L','N','DateFinishSchedule:',80,0,'N','N','N','N','N','N','DateFinishSchedule','N',0,TO_TIMESTAMP('2009-05-20 18:53:52','YYYY-MM-DD HH24:MI:SS'),0,'N',51222,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:53:52 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51222 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:53:53 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:53:52','YYYY-MM-DD HH24:MI:SS'),0,0,5,'N','N','Y','N','H',50042,'N',0,'F','N',120,57467,'X',0,0,0,'L','N','Date Delivered:',90,0,'N','N','N','N','N','N','Date Delivered','N',0,TO_TIMESTAMP('2009-05-20 18:53:52','YYYY-MM-DD HH24:MI:SS'),0,'N',51223,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:53:53 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51223 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:53:54 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:53:53','YYYY-MM-DD HH24:MI:SS'),0,0,300,'N','N','Y','N','H',50042,'N',0,'F','N',130,57466,'X',0,0,0,'L','N','Date Confirm:',90,0,'N','N','N','N','N','N','DateConfirm','N',0,TO_TIMESTAMP('2009-05-20 18:53:53','YYYY-MM-DD HH24:MI:SS'),0,'N',51224,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:53:54 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51224 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:53:55 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:53:54','YYYY-MM-DD HH24:MI:SS'),0,0,5,'N','N','Y','N','H',50042,'N',0,'F','N',140,57492,'X',0,0,0,'T','N','Qty Batch Size:',110,0,'N','N','N','N','N','N','Qty Batch Size','N',0,TO_TIMESTAMP('2009-05-20 18:53:54','YYYY-MM-DD HH24:MI:SS'),0,'N',51225,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:53:55 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51225 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:53:55 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:53:55','YYYY-MM-DD HH24:MI:SS'),0,0,150,'N','N','Y','N','H',50042,'N',0,'F','N',150,57464,'X',0,0,0,'T','N','Quantity:',110,0,'N','N','N','N','N','N','Quantity','N',0,TO_TIMESTAMP('2009-05-20 18:53:55','YYYY-MM-DD HH24:MI:SS'),0,'N',51226,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:53:55 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51226 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:53:56 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:53:55','YYYY-MM-DD HH24:MI:SS'),0,0,350,'N','N','Y','N','H',50042,'N',0,'F','N',160,57463,'X',0,0,0,'T','N','Delivered Qty:',110,0,'N','N','N','N','N','N','Delivered Quantity','N',0,TO_TIMESTAMP('2009-05-20 18:53:55','YYYY-MM-DD HH24:MI:SS'),0,'N',51227,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:53:56 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51227 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:53:57 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:53:56','YYYY-MM-DD HH24:MI:SS'),0,0,450,'N','N','Y','N','H',50042,'N',0,'F','N',170,57490,'X',0,0,0,'T','N','Overlap Units:',110,0,'N','N','N','N','N','N','Overlap Units','N',0,TO_TIMESTAMP('2009-05-20 18:53:56','YYYY-MM-DD HH24:MI:SS'),0,'N',51228,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:53:57 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51228 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:53:57 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:53:57','YYYY-MM-DD HH24:MI:SS'),0,0,5,'N','N','Y','N','H',50042,'N',0,'F','N',180,57494,'X',0,0,0,'T','N','Setup Time:',130,0,'N','N','N','N','N','N','Setup Time','N',0,TO_TIMESTAMP('2009-05-20 18:53:57','YYYY-MM-DD HH24:MI:SS'),0,'N',51229,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:53:57 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51229 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:53:58 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:53:57','YYYY-MM-DD HH24:MI:SS'),0,0,350,'N','N','Y','N','H',50042,'N',0,'F','N',190,57493,'X',0,0,0,'T','N','Queuing Time:',130,0,'N','N','N','N','N','N','Queuing Time','N',0,TO_TIMESTAMP('2009-05-20 18:53:57','YYYY-MM-DD HH24:MI:SS'),0,'N',51230,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:53:58 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51230 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:53:58 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:53:58','YYYY-MM-DD HH24:MI:SS'),0,0,5,'N','N','Y','N','H',50042,'N',0,'F','N',200,57496,'X',0,0,0,'T','N','Waiting Time:',140,0,'N','N','N','N','N','N','Waiting Time','N',0,TO_TIMESTAMP('2009-05-20 18:53:58','YYYY-MM-DD HH24:MI:SS'),0,'N',51231,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:53:58 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51231 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:53:59 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:53:58','YYYY-MM-DD HH24:MI:SS'),0,0,350,'N','N','Y','N','H',50042,'N',0,'F','N',210,57489,'X',0,0,0,'T','N','Moving Time:',140,0,'N','N','N','N','N','N','Moving Time','N',0,TO_TIMESTAMP('2009-05-20 18:53:58','YYYY-MM-DD HH24:MI:SS'),0,'N',51232,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:53:59 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51232 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:54:00 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:53:59','YYYY-MM-DD HH24:MI:SS'),0,0,5,'N','N','Y','N','H',50042,'N',0,'F','N',220,57484,'X',0,0,0,'T','N','Duration:',150,0,'N','N','N','N','N','N','Duration','N',0,TO_TIMESTAMP('2009-05-20 18:53:59','YYYY-MM-DD HH24:MI:SS'),0,'N',51233,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:54:00 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51233 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:54:00 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:54:00','YYYY-MM-DD HH24:MI:SS'),0,0,350,'N','N','Y','N','H',50042,'N',0,'F','N',230,57485,'X',0,0,0,'L','N','Duration Unit:',150,0,'N','N','N','N','N','N','Duration Unit','N',0,TO_TIMESTAMP('2009-05-20 18:54:00','YYYY-MM-DD HH24:MI:SS'),0,'N',51234,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:54:00 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51234 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:54:01 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,AD_PrintFormatChild_ID,IsGroupBy,MaxHeight,AD_PrintColor_ID,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,YPosition,AD_PrintFont_ID,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:54:00','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','Y','Y','C',50042,50037,'N',0,100,'P','N',240,57437,'X',0,0,0,'D','Y',0,130,0,'N','N','N','N','N','N','Manufacturing Workflow Node','N',0,TO_TIMESTAMP('2009-05-20 18:54:00','YYYY-MM-DD HH24:MI:SS'),170,'N',51235,'Y',0,'N',20,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:54:01 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51235 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:54:02 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormat (AD_Client_ID,AD_Org_ID,AD_PrintColor_ID,AD_PrintFont_ID,AD_PrintFormat_ID,AD_PrintPaper_ID,AD_PrintTableFormat_ID,AD_Table_ID,CreateCopy,Created,CreatedBy,FooterMargin,HeaderMargin,IsActive,IsDefault,IsForm,IsStandardHeaderFooter,IsTableBased,Name,Updated,UpdatedBy) VALUES (0,0,100,119,50043,101,100,53030,'N',TO_TIMESTAMP('2009-05-20 18:54:01','YYYY-MM-DD HH24:MI:SS'),0,0,0,'Y','N','N','Y','Y','Manufacturing Order Activity Product',TO_TIMESTAMP('2009-05-20 18:54:01','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 20, 2009 6:54:02 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:54:02','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50043,'N',0,'F','N',0,53728,'X',0,0,0,'L','Y','Client',0,0,'N','N','N','N','N','N','Client','N',0,TO_TIMESTAMP('2009-05-20 18:54:02','YYYY-MM-DD HH24:MI:SS'),0,'N',51236,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:54:02 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51236 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:54:03 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:54:02','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50043,'N',0,'F','N',0,53729,'X',0,0,0,'L','Y','Organization',0,0,'N','N','N','N','N','N','Organization','N',0,TO_TIMESTAMP('2009-05-20 18:54:02','YYYY-MM-DD HH24:MI:SS'),0,'N',51237,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:54:03 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51237 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:54:04 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:54:03','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50043,'N',0,'F','N',0,53737,'X',0,0,0,'L','Y','Manufacturing Order Workflow',0,0,'N','N','N','N','N','N','Manufacturing Order Workflow','N',0,TO_TIMESTAMP('2009-05-20 18:54:03','YYYY-MM-DD HH24:MI:SS'),0,'N',51238,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:54:04 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51238 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:54:05 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:54:04','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50043,'N',0,'F','N',0,53732,'X',0,0,0,'L','Y','Active',0,0,'N','N','N','N','N','N','Active','N',0,TO_TIMESTAMP('2009-05-20 18:54:04','YYYY-MM-DD HH24:MI:SS'),0,'N',51239,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:54:05 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51239 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:54:06 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:54:05','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50043,'N',0,'F','N',0,53736,'X',0,0,0,'L','Y','Manufacturing Order Activity Product',0,0,'N','N','N','N','N','N','Manufacturing Order Activity Product','N',0,TO_TIMESTAMP('2009-05-20 18:54:05','YYYY-MM-DD HH24:MI:SS'),0,'N',51240,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:54:06 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51240 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:54:06 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:54:06','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50043,'N',0,'F','N',0,53734,'X',0,0,0,'L','Y','Manufacturing Order',0,0,'N','N','N','N','N','N','Manufacturing Order','N',0,TO_TIMESTAMP('2009-05-20 18:54:06','YYYY-MM-DD HH24:MI:SS'),0,'N',51241,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:54:06 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51241 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:54:07 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:54:06','YYYY-MM-DD HH24:MI:SS'),0,10,0,'Y','N','Y','N','C',50043,'N',0,'F','N',10,53735,'X',0,0,0,'L','Y','Manufacturing Order Activity',0,0,'N','N','N','N','N','N','Manufacturing Order Activity','N',0,TO_TIMESTAMP('2009-05-20 18:54:06','YYYY-MM-DD HH24:MI:SS'),0,'N',51242,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:54:07 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51242 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:54:08 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:54:07','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','Y','N','C',50043,'N',0,'F','N',20,56528,'X',0,0,0,'T','Y','Sequence',0,0,'N','N','N','N','N','N','Sequence','N',0,TO_TIMESTAMP('2009-05-20 18:54:07','YYYY-MM-DD HH24:MI:SS'),0,'N',51243,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:54:08 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51243 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:54:08 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:54:08','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','Y','N','C',50043,'N',0,'F','N',30,53733,'X',0,0,0,'L','Y','Product',0,0,'N','N','N','N','N','N','Product','N',0,TO_TIMESTAMP('2009-05-20 18:54:08','YYYY-MM-DD HH24:MI:SS'),0,'N',51244,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:54:08 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51244 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:54:09 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:54:08','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','Y','N','C',50043,'N',0,'F','N',40,56529,'X',0,0,0,'T','Y','Qty',0,0,'N','N','N','N','N','N','Quantity','N',0,TO_TIMESTAMP('2009-05-20 18:54:08','YYYY-MM-DD HH24:MI:SS'),0,'N',51245,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:54:09 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51245 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:54:10 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:54:09','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','Y','N','C',50043,'N',0,'F','N',50,56525,'X',0,0,0,'L','Y','Is Subcontracting',0,0,'N','N','N','N','N','N','Is Subcontracting','N',0,TO_TIMESTAMP('2009-05-20 18:54:09','YYYY-MM-DD HH24:MI:SS'),0,'N',51246,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:54:10 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51246 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:54:10 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,AD_PrintFormatChild_ID,IsGroupBy,MaxHeight,AD_PrintColor_ID,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,YPosition,AD_PrintFont_ID,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:54:10','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','Y','Y','C',50042,50043,'N',0,100,'P','N',250,57437,'X',0,0,0,'D','Y',0,130,0,'N','N','N','N','Y','N','Manufacturin Node Activity Product','N',0,TO_TIMESTAMP('2009-05-20 18:54:10','YYYY-MM-DD HH24:MI:SS'),0,'N',51247,'Y',0,'N',20,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:54:10 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51247 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:57:19 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_Table (AD_Client_ID,AD_Org_ID,AD_Table_ID,AccessLevel,Created,CreatedBy,EntityType,IsActive,IsChangeLog,IsDeleteable,IsHighVolume,IsSecurityEnabled,IsView,Name,ReplicationType,TableName,Updated,UpdatedBy) VALUES (0,0,53205,'3',TO_TIMESTAMP('2009-05-20 18:57:18','YYYY-MM-DD HH24:MI:SS'),0,'U','Y','N','N','N','N','N','PP_Order_Header_v','L','PP_Order_Header_v',TO_TIMESTAMP('2009-05-20 18:57:18','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 20, 2009 6:57:19 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_Table_Trl (AD_Language,AD_Table_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Table_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Table t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Table_ID=53205 AND EXISTS (SELECT * FROM AD_Table_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Table_ID!=t.AD_Table_ID)
;

-- May 20, 2009 6:57:19 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormat SET AD_PrintColor_ID=100, AD_PrintFont_ID=119, AD_PrintPaper_ID=101, AD_PrintTableFormat_ID=100, AD_Table_ID=53205, CreateCopy='N', Description='Manufacturing Order', FooterMargin=0, HeaderMargin=230, IsActive='Y', IsDefault='N', IsForm='Y', IsStandardHeaderFooter='N', IsTableBased='Y', Name='Manufacturing_Order_Header ** TEMPLATE **', PrinterName=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:57:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormat_ID=50001
;

-- May 20, 2009 6:57:20 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='N', IsHeightOneLine='N', PrintAreaType='C', AD_PrintFormat_ID=50001, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=0, LineAlignmentType='X', XSpace=0, FieldAlignmentType='L', IsRelativePosition='Y', PrintName='Order Line', YPosition=0, IsSummarized='N', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='N', IsMinCalc='N', Name='Sales Order Line', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=0, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType='N', LineWidth=1, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:57:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50032
;

-- May 20, 2009 6:57:20 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50032
;

-- May 20, 2009 6:57:20 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='N', IsHeightOneLine='N', PrintAreaType='C', AD_PrintFormat_ID=50001, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=0, LineAlignmentType='X', XSpace=0, FieldAlignmentType='T', IsRelativePosition='Y', PrintName='Line No', YPosition=0, IsSummarized='N', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='N', IsMinCalc='N', Name='Line No', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=0, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType=NULL, LineWidth=0, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:57:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50039
;

-- May 20, 2009 6:57:20 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50039
;

-- May 20, 2009 6:57:21 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='N', IsHeightOneLine='N', PrintAreaType='C', AD_PrintFormat_ID=50001, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=0, LineAlignmentType='X', XSpace=0, FieldAlignmentType='L', IsRelativePosition='Y', PrintName='Created By', YPosition=0, IsSummarized='N', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='N', IsMinCalc='N', Name='Created By', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=0, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType='N', LineWidth=1, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:57:21','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50052
;

-- May 20, 2009 6:57:21 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50052
;

-- May 20, 2009 6:57:21 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='N', IsHeightOneLine='N', PrintAreaType='C', AD_PrintFormat_ID=50001, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=0, LineAlignmentType='X', XSpace=0, FieldAlignmentType='T', IsRelativePosition='Y', PrintName='Delivered Qty', YPosition=0, IsSummarized='N', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='N', IsMinCalc='N', Name='Delivered Quantity', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=0, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType=NULL, LineWidth=0, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:57:21','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50068
;

-- May 20, 2009 6:57:21 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50068
;

-- May 20, 2009 6:57:22 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='N', IsHeightOneLine='N', PrintAreaType='C', AD_PrintFormat_ID=50001, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=0, LineAlignmentType='X', XSpace=0, FieldAlignmentType='L', IsRelativePosition='Y', PrintName='Campaign', YPosition=0, IsSummarized='N', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='N', IsMinCalc='N', Name='Campaign', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=0, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType=NULL, LineWidth=0, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:57:22','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50070
;

-- May 20, 2009 6:57:22 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50070
;

-- May 20, 2009 6:57:22 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='N', IsHeightOneLine='N', PrintAreaType='C', AD_PrintFormat_ID=50001, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=0, LineAlignmentType='X', XSpace=0, FieldAlignmentType='B', IsRelativePosition='Y', PrintName='Description', YPosition=0, IsSummarized='N', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='N', IsMinCalc='N', Name='Description', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=0, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType=NULL, LineWidth=0, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:57:22','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50042
;

-- May 20, 2009 6:57:22 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50042
;

-- May 20, 2009 6:57:23 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='N', IsHeightOneLine='N', PrintAreaType='C', AD_PrintFormat_ID=50001, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=0, LineAlignmentType='X', XSpace=0, FieldAlignmentType='L', IsRelativePosition='Y', PrintName='Doc Action', YPosition=0, IsSummarized='N', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='N', IsMinCalc='N', Name='Document Action', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=0, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType=NULL, LineWidth=0, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:57:23','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50051
;

-- May 20, 2009 6:57:23 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50051
;

-- May 20, 2009 6:57:23 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='N', IsHeightOneLine='N', PrintAreaType='C', AD_PrintFormat_ID=50001, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=0, LineAlignmentType='X', XSpace=0, FieldAlignmentType='T', IsRelativePosition='Y', PrintName='Ordered Qty', YPosition=0, IsSummarized='N', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='N', IsMinCalc='N', Name='Ordered Quantity', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=0, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType=NULL, LineWidth=0, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:57:23','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50067
;

-- May 20, 2009 6:57:23 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50067
;

-- May 20, 2009 6:57:23 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='N', IsHeightOneLine='N', PrintAreaType='C', AD_PrintFormat_ID=50001, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=0, LineAlignmentType='X', XSpace=0, FieldAlignmentType='L', IsRelativePosition='Y', PrintName='Posted', YPosition=0, IsSummarized='N', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='N', IsMinCalc='N', Name='Posted', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=0, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType=NULL, LineWidth=0, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:57:23','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50065
;

-- May 20, 2009 6:57:23 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50065
;

-- May 20, 2009 6:57:24 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='N', IsHeightOneLine='N', PrintAreaType='C', AD_PrintFormat_ID=50001, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=0, LineAlignmentType='X', XSpace=0, FieldAlignmentType='L', IsRelativePosition='Y', PrintName='Project', YPosition=0, IsSummarized='N', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='N', IsMinCalc='N', Name='Project', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=0, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType=NULL, LineWidth=0, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:57:24','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50069
;

-- May 20, 2009 6:57:24 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50069
;

-- May 20, 2009 6:57:24 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='N', IsHeightOneLine='N', PrintAreaType='C', AD_PrintFormat_ID=50001, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=0, LineAlignmentType='X', XSpace=0, FieldAlignmentType='L', IsRelativePosition='Y', PrintName='Resource', YPosition=0, IsSummarized='N', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='N', IsMinCalc='N', Name='Resource', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=0, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType=NULL, LineWidth=0, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:57:24','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50044
;

-- May 20, 2009 6:57:24 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50044
;

-- May 20, 2009 6:57:25 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='N', IsHeightOneLine='N', PrintAreaType='C', AD_PrintFormat_ID=50001, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=0, LineAlignmentType='X', XSpace=0, FieldAlignmentType='L', IsRelativePosition='Y', PrintName='Trx Organization', YPosition=0, IsSummarized='N', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='N', IsMinCalc='N', Name='Trx Organization', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=0, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType=NULL, LineWidth=0, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:57:25','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50072
;

-- May 20, 2009 6:57:25 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50072
;

-- May 20, 2009 6:57:25 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='N', IsHeightOneLine='N', PrintAreaType='C', AD_PrintFormat_ID=50001, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=0, LineAlignmentType='X', XSpace=0, FieldAlignmentType='L', IsRelativePosition='Y', PrintName='UOM', YPosition=0, IsSummarized='N', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='N', IsMinCalc='N', Name='UOM', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=0, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType=NULL, LineWidth=0, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:57:25','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50066
;

-- May 20, 2009 6:57:25 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50066
;

-- May 20, 2009 6:57:26 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='N', IsHeightOneLine='N', PrintAreaType='C', AD_PrintFormat_ID=50001, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=0, LineAlignmentType='X', XSpace=0, FieldAlignmentType='L', IsRelativePosition='Y', PrintName='User2', YPosition=0, IsSummarized='N', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='N', IsMinCalc='N', Name='User List 2', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=0, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType=NULL, LineWidth=0, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:57:26','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50074
;

-- May 20, 2009 6:57:26 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50074
;

-- May 20, 2009 6:57:27 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='N', IsHeightOneLine='N', PrintAreaType='C', AD_PrintFormat_ID=50001, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=0, LineAlignmentType='X', XSpace=0, FieldAlignmentType='L', IsRelativePosition='Y', PrintName='Printed', YPosition=0, IsSummarized='N', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='N', IsMinCalc='N', Name='Printed', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=0, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType=NULL, LineWidth=0, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:57:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50046
;

-- May 20, 2009 6:57:27 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50046
;

-- May 20, 2009 6:57:27 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='N', IsHeightOneLine='N', PrintAreaType='C', AD_PrintFormat_ID=50001, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=0, LineAlignmentType='X', XSpace=0, FieldAlignmentType='L', IsRelativePosition='Y', PrintName='Updated', YPosition=0, IsSummarized='N', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='N', IsMinCalc='N', Name='Updated', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=0, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType='N', LineWidth=1, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:57:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50053
;

-- May 20, 2009 6:57:27 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50053
;

-- May 20, 2009 6:57:27 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='N', IsHeightOneLine='N', PrintAreaType='C', AD_PrintFormat_ID=50001, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=0, LineAlignmentType='X', XSpace=0, FieldAlignmentType='L', IsRelativePosition='Y', PrintName='Updated By', YPosition=0, IsSummarized='N', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='N', IsMinCalc='N', Name='Updated By', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=0, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType='N', LineWidth=1, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:57:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50054
;

-- May 20, 2009 6:57:27 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50054
;

-- May 20, 2009 6:57:28 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='N', IsHeightOneLine='N', PrintAreaType='C', AD_PrintFormat_ID=50001, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=0, LineAlignmentType='X', XSpace=0, FieldAlignmentType='L', IsRelativePosition='Y', PrintName='User1', YPosition=0, IsSummarized='N', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='N', IsMinCalc='N', Name='User List 1', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=0, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType=NULL, LineWidth=0, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:57:28','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50073
;

-- May 20, 2009 6:57:28 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50073
;

-- May 20, 2009 6:57:28 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='N', IsHeightOneLine='N', PrintAreaType='C', AD_PrintFormat_ID=50001, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=0, LineAlignmentType='X', XSpace=0, FieldAlignmentType='L', IsRelativePosition='Y', PrintName='Approved', YPosition=0, IsSummarized='N', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='N', IsMinCalc='N', Name='Approved', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=0, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType=NULL, LineWidth=0, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:57:28','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50045
;

-- May 20, 2009 6:57:28 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50045
;

-- May 20, 2009 6:57:29 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='N', IsHeightOneLine='N', PrintAreaType='C', AD_PrintFormat_ID=50001, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=0, LineAlignmentType='X', XSpace=0, FieldAlignmentType='T', IsRelativePosition='Y', PrintName='Float Befored', YPosition=0, IsSummarized='N', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='N', IsMinCalc='N', Name='Float Befored', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=0, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType=NULL, LineWidth=0, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:57:29','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50058
;

-- May 20, 2009 6:57:29 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50058
;

-- May 20, 2009 6:57:29 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='N', IsHeightOneLine='N', PrintAreaType='C', AD_PrintFormat_ID=50001, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=0, LineAlignmentType='X', XSpace=0, FieldAlignmentType='L', IsRelativePosition='Y', PrintName='Order Type', YPosition=0, IsSummarized='N', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='N', IsMinCalc='N', Name='OrderType', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=0, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType=NULL, LineWidth=0, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:57:29','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50056
;

-- May 20, 2009 6:57:29 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50056
;

-- May 20, 2009 6:57:30 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='N', IsHeightOneLine='N', PrintAreaType='C', AD_PrintFormat_ID=50001, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=0, LineAlignmentType='X', XSpace=0, FieldAlignmentType='T', IsRelativePosition='Y', PrintName='Quantity Assay', YPosition=0, IsSummarized='N', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='N', IsMinCalc='N', Name='Quantity Assay', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=0, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType=NULL, LineWidth=0, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:57:30','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50062
;

-- May 20, 2009 6:57:30 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50062
;

-- May 20, 2009 6:57:30 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='N', IsHeightOneLine='N', PrintAreaType='C', AD_PrintFormat_ID=50001, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=0, LineAlignmentType='X', XSpace=0, FieldAlignmentType='T', IsRelativePosition='Y', PrintName='Yield %', YPosition=0, IsSummarized='N', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='N', IsMinCalc='N', Name='Yield %', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=0, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType=NULL, LineWidth=0, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:57:30','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50061
;

-- May 20, 2009 6:57:30 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50061
;

-- May 20, 2009 6:57:31 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='N', IsHeightOneLine='N', PrintAreaType='C', AD_PrintFormat_ID=50001, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=0, LineAlignmentType='X', XSpace=0, FieldAlignmentType='L', IsRelativePosition='Y', PrintName='Attribute Set Instance', YPosition=0, IsSummarized='N', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='N', IsMinCalc='N', Name='Attribute Set Instance', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=0, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType=NULL, LineWidth=0, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:57:31','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50043
;

-- May 20, 2009 6:57:31 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50043
;

-- May 20, 2009 6:57:32 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='N', IsHeightOneLine='N', PrintAreaType='C', AD_PrintFormat_ID=50001, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=0, LineAlignmentType='X', XSpace=0, FieldAlignmentType='T', IsRelativePosition='Y', PrintName='Float After', YPosition=0, IsSummarized='N', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='N', IsMinCalc='N', Name='Float After', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=0, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType=NULL, LineWidth=0, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:57:32','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50060
;

-- May 20, 2009 6:57:32 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50060
;

-- May 20, 2009 6:57:32 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='N', IsHeightOneLine='N', PrintAreaType='C', AD_PrintFormat_ID=50001, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=0, LineAlignmentType='X', XSpace=0, FieldAlignmentType='L', IsRelativePosition='Y', PrintName='Organization', YPosition=0, IsSummarized='N', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='N', IsMinCalc='N', Name='Organization', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=0, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType=NULL, LineWidth=0, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:57:32','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50038
;

-- May 20, 2009 6:57:32 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50038
;

-- May 20, 2009 6:57:33 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='N', IsHeightOneLine='N', PrintAreaType='C', AD_PrintFormat_ID=50001, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=0, LineAlignmentType='X', XSpace=0, FieldAlignmentType='L', IsRelativePosition='Y', PrintName='Priority', YPosition=0, IsSummarized='N', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='N', IsMinCalc='N', Name='Priority', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=0, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType=NULL, LineWidth=0, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:57:33','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50036
;

-- May 20, 2009 6:57:33 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50036
;

-- May 20, 2009 6:57:33 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='N', IsHeightOneLine='N', PrintAreaType='C', AD_PrintFormat_ID=50001, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=0, LineAlignmentType='X', XSpace=0, FieldAlignmentType='L', IsRelativePosition='Y', PrintName='Schedule Type', YPosition=0, IsSummarized='N', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='N', IsMinCalc='N', Name='Schedule Type', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=0, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType=NULL, LineWidth=0, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:57:33','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50034
;

-- May 20, 2009 6:57:33 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50034
;

-- May 20, 2009 6:57:34 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='N', IsHeightOneLine='N', PrintAreaType='C', AD_PrintFormat_ID=50001, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=0, LineAlignmentType='X', XSpace=0, FieldAlignmentType='L', IsRelativePosition='Y', PrintName='Target Doc Type', YPosition=0, IsSummarized='N', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='N', IsMinCalc='N', Name='Target Document Type', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=0, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType=NULL, LineWidth=0, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:57:34','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50040
;

-- May 20, 2009 6:57:34 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50040
;

-- May 20, 2009 6:57:34 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='N', IsHeightOneLine='N', PrintAreaType='C', AD_PrintFormat_ID=50001, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=0, LineAlignmentType='X', XSpace=0, FieldAlignmentType='L', IsRelativePosition='Y', PrintName='Serial No', YPosition=0, IsSummarized='N', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='N', IsMinCalc='N', Name='Serial No', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=0, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType=NULL, LineWidth=0, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:57:34','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50035
;

-- May 20, 2009 6:57:34 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50035
;

-- May 20, 2009 6:57:35 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='N', IsHeightOneLine='N', PrintAreaType='C', AD_PrintFormat_ID=50001, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=0, LineAlignmentType='X', XSpace=0, FieldAlignmentType='L', IsRelativePosition='Y', PrintName='Active', YPosition=0, IsSummarized='N', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='N', IsMinCalc='N', Name='Active', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=0, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType=NULL, LineWidth=0, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:57:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50041
;

-- May 20, 2009 6:57:35 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50041
;

-- May 20, 2009 6:57:35 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='N', IsHeightOneLine='N', PrintAreaType='C', AD_PrintFormat_ID=50001, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=0, LineAlignmentType='X', XSpace=0, FieldAlignmentType='L', IsRelativePosition='Y', PrintName='Client', YPosition=0, IsSummarized='N', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='N', IsMinCalc='N', Name='Client', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=0, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType=NULL, LineWidth=0, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:57:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50037
;

-- May 20, 2009 6:57:35 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50037
;

-- May 20, 2009 6:57:36 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='N', IsHeightOneLine='N', PrintAreaType='C', AD_PrintFormat_ID=50001, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=0, LineAlignmentType='X', XSpace=0, FieldAlignmentType='L', IsRelativePosition='Y', PrintName='Activity', YPosition=0, IsSummarized='N', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='N', IsMinCalc='N', Name='Activity', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=0, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType=NULL, LineWidth=0, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:57:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50071
;

-- May 20, 2009 6:57:36 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50071
;

-- May 20, 2009 6:57:36 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='N', IsHeightOneLine='N', PrintAreaType='C', AD_PrintFormat_ID=50001, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=0, LineAlignmentType='X', XSpace=0, FieldAlignmentType='L', IsRelativePosition='Y', PrintName='Created', YPosition=0, IsSummarized='N', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='N', IsMinCalc='N', Name='Created', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=0, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType='N', LineWidth=1, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:57:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50055
;

-- May 20, 2009 6:57:36 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50055
;

-- May 20, 2009 6:57:36 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='N', IsHeightOneLine='N', PrintAreaType='C', AD_PrintFormat_ID=50001, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=0, LineAlignmentType='X', XSpace=0, FieldAlignmentType='L', IsRelativePosition='Y', PrintName='Doc Type', YPosition=0, IsSummarized='N', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='N', IsMinCalc='N', Name='Document Type', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=0, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType=NULL, LineWidth=0, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:57:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50049
;

-- May 20, 2009 6:57:36 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50049
;

-- May 20, 2009 6:57:37 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='Y', IsHeightOneLine='N', PrintAreaType='H', AD_PrintFormat_ID=50001, IsGroupBy='N', MaxHeight=0, AD_PrintColor_ID=100, PrintFormatType='T', ImageIsAttached='N', SeqNo=10, LineAlignmentType='C', XSpace=0, FieldAlignmentType='C', IsRelativePosition='Y', PrintName='G A R D E N   W O R L D', YPosition=0, AD_PrintFont_ID=117, IsSummarized='N', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='Y', IsMinCalc='N', Name='G A R D E N   W O R L D', IsAveraged='N', BelowColumn=0, YSpace=20, IsSetNLPosition='Y', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=20, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType=NULL, LineWidth=0, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:57:37','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50075
;

-- May 20, 2009 6:57:37 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50075
;

-- May 20, 2009 6:57:37 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=10, XPosition=5, IsOrderBy='Y', ImageURL=NULL, IsCounted='N', IsPrinted='Y', IsHeightOneLine='N', PrintAreaType='H', AD_PrintFormat_ID=50001, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=20, LineAlignmentType='X', XSpace=0, FieldAlignmentType='L', IsRelativePosition='N', PrintName='Document No:', YPosition=40, IsSummarized='N', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='N', IsMinCalc='N', Name='Document No', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=0, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType=NULL, LineWidth=0, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:57:37','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50076
;

-- May 20, 2009 6:57:37 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50076
;

-- May 20, 2009 6:57:38 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=200, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='Y', IsHeightOneLine='Y', PrintAreaType='H', AD_PrintFormat_ID=50001, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=30, LineAlignmentType='X', XSpace=0, FieldAlignmentType='L', IsRelativePosition='N', PrintName='Planner', YPosition=40, IsSummarized='N', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='N', IsMinCalc='N', Name='Planner', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=0, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType='N', LineWidth=1, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:57:38','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50057
;

-- May 20, 2009 6:57:38 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50057
;

-- May 20, 2009 6:57:39 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=5, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='Y', IsHeightOneLine='N', PrintAreaType='H', AD_PrintFormat_ID=50001, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=40, LineAlignmentType='X', XSpace=0, FieldAlignmentType='L', IsRelativePosition='N', PrintName='Document Status:', YPosition=50, IsSummarized='N', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='N', IsMinCalc='N', Name='Document Status', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=0, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType=NULL, LineWidth=0, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:57:39','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50078
;

-- May 20, 2009 6:57:39 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50078
;

-- May 20, 2009 6:57:39 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=100, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='Y', IsHeightOneLine='N', PrintAreaType='C', AD_PrintFormat_ID=50001, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=50, LineAlignmentType='X', XSpace=0, FieldAlignmentType='L', IsRelativePosition='N', PrintName='Reserved Qty:', YPosition=50, IsSummarized='N', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='N', IsMinCalc='N', Name='Reserved Quantity', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=0, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType='N', LineWidth=1, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:57:39','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50080
;

-- May 20, 2009 6:57:39 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50080
;

-- May 20, 2009 6:57:40 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:57:39','YYYY-MM-DD HH24:MI:SS'),0,0,200,'N','N','Y','N','H',50001,'N',0,'F','N',60,'X',0,0,0,'L','N','Warehouse:',50,0,'N','N','N','N','N','N','Warehouse Address','N',0,TO_TIMESTAMP('2009-05-20 18:57:39','YYYY-MM-DD HH24:MI:SS'),0,'N',51248,'Y',0,'N',0,'N','N','N','N',0,0)
;

-- May 20, 2009 6:57:40 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51248 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:57:40 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=5, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='Y', IsHeightOneLine='N', PrintAreaType='H', AD_PrintFormat_ID=50001, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=70, LineAlignmentType='X', XSpace=0, FieldAlignmentType='L', IsRelativePosition='N', PrintName='Product :', YPosition=70, IsSummarized='N', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='N', IsMinCalc='N', Name='Product', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=0, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType=NULL, LineWidth=0, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:57:40','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50082
;

-- May 20, 2009 6:57:40 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50082
;

-- May 20, 2009 6:57:41 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=5, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='Y', IsHeightOneLine='N', PrintAreaType='H', AD_PrintFormat_ID=50001, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=80, LineAlignmentType='X', XSpace=0, FieldAlignmentType='T', IsRelativePosition='N', PrintName='Quantity :', YPosition=80, IsSummarized='N', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='N', IsMinCalc='N', Name='Quantity', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=0, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType=NULL, LineWidth=0, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:57:41','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50083
;

-- May 20, 2009 6:57:41 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50083
;

-- May 20, 2009 6:57:41 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=150, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='Y', IsHeightOneLine='N', PrintAreaType='H', AD_PrintFormat_ID=50001, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=90, LineAlignmentType='X', XSpace=0, FieldAlignmentType='T', IsRelativePosition='N', PrintName='Qty Reject:', YPosition=80, IsSummarized='N', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='N', IsMinCalc='N', Name='Qty Reject', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=0, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType=NULL, LineWidth=0, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:57:41','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50084
;

-- May 20, 2009 6:57:41 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50084
;

-- May 20, 2009 6:57:42 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=250, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='Y', IsHeightOneLine='N', PrintAreaType='H', AD_PrintFormat_ID=50001, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=100, LineAlignmentType='X', XSpace=0, FieldAlignmentType='T', IsRelativePosition='N', PrintName='% Scrap', YPosition=80, IsSummarized='N', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='N', IsMinCalc='N', Name='QtyScrap', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=0, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType=NULL, LineWidth=0, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:57:42','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50085
;

-- May 20, 2009 6:57:42 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50085
;

-- May 20, 2009 6:57:42 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=350, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='Y', IsHeightOneLine='N', PrintAreaType='H', AD_PrintFormat_ID=50001, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=110, LineAlignmentType='X', XSpace=0, FieldAlignmentType='D', IsRelativePosition='N', PrintName='# Batch:', YPosition=80, IsSummarized='N', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='Y', IsMinCalc='N', Name='Qty Batchs', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=20, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType=NULL, LineWidth=0, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:57:42','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50086
;

-- May 20, 2009 6:57:42 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50086
;

-- May 20, 2009 6:57:43 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=450, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='Y', IsHeightOneLine='N', PrintAreaType='H', AD_PrintFormat_ID=50001, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=120, LineAlignmentType='X', XSpace=0, FieldAlignmentType='D', IsRelativePosition='N', PrintName='Qty Batch Size:', YPosition=80, IsSummarized='N', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='Y', IsMinCalc='N', Name='Qty Batch Size', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=20, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType=NULL, LineWidth=0, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:57:43','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50079
;

-- May 20, 2009 6:57:43 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50079
;

-- May 20, 2009 6:57:43 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=5, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='Y', IsHeightOneLine='N', PrintAreaType='H', AD_PrintFormat_ID=50001, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=130, LineAlignmentType='X', XSpace=0, FieldAlignmentType='L', IsRelativePosition='N', PrintName='Date:', YPosition=100, IsSummarized='N', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='N', IsMinCalc='N', Name='Date Ordered', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=0, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType=NULL, LineWidth=0, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:57:43','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50087
;

-- May 20, 2009 6:57:43 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50087
;

-- May 20, 2009 6:57:44 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=330, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='Y', IsHeightOneLine='N', PrintAreaType='H', AD_PrintFormat_ID=50001, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=140, LineAlignmentType='X', XSpace=0, FieldAlignmentType='L', IsRelativePosition='N', PrintName='Promised:', YPosition=100, IsSummarized='N', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='N', IsMinCalc='N', Name='Date Promised', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=0, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType=NULL, LineWidth=0, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:57:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50088
;

-- May 20, 2009 6:57:44 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50088
;

-- May 20, 2009 6:57:44 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=5, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='Y', IsHeightOneLine='N', PrintAreaType='H', AD_PrintFormat_ID=50001, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=150, LineAlignmentType='X', XSpace=0, FieldAlignmentType='L', IsRelativePosition='N', PrintName='Date Start', YPosition=110, IsSummarized='N', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='N', IsMinCalc='N', Name='DateStart', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=0, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType=NULL, LineWidth=0, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:57:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50089
;

-- May 20, 2009 6:57:44 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50089
;

-- May 20, 2009 6:57:45 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=330, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='Y', IsHeightOneLine='N', PrintAreaType='H', AD_PrintFormat_ID=50001, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=160, LineAlignmentType='X', XSpace=0, FieldAlignmentType='L', IsRelativePosition='N', PrintName='Date Start Schedule', YPosition=110, IsSummarized='N', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='N', IsMinCalc='N', Name='DateStartSchedule', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=0, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType=NULL, LineWidth=0, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:57:45','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50090
;

-- May 20, 2009 6:57:45 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50090
;

-- May 20, 2009 6:57:45 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=5, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='Y', IsHeightOneLine='N', PrintAreaType='H', AD_PrintFormat_ID=50001, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=170, LineAlignmentType='X', XSpace=0, FieldAlignmentType='L', IsRelativePosition='N', PrintName='Finish Date', YPosition=120, IsSummarized='N', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='N', IsMinCalc='N', Name='Finish Date', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=0, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType=NULL, LineWidth=0, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:57:45','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50091
;

-- May 20, 2009 6:57:45 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50091
;

-- May 20, 2009 6:57:46 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=330, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='Y', IsHeightOneLine='N', PrintAreaType='H', AD_PrintFormat_ID=50001, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=180, LineAlignmentType='X', XSpace=0, FieldAlignmentType='L', IsRelativePosition='N', PrintName='Date Finish Schedule', YPosition=120, IsSummarized='N', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='N', IsMinCalc='N', Name='DateFinishSchedule', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=0, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType=NULL, LineWidth=0, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:57:46','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50092
;

-- May 20, 2009 6:57:46 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50092
;

-- May 20, 2009 6:57:46 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=5, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='Y', IsHeightOneLine='N', PrintAreaType='H', AD_PrintFormat_ID=50001, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=190, LineAlignmentType='X', XSpace=0, FieldAlignmentType='L', IsRelativePosition='N', PrintName='Date Confirm', YPosition=130, IsSummarized='N', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='N', IsMinCalc='N', Name='DateConfirm', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=0, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType=NULL, LineWidth=0, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:57:46','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50093
;

-- May 20, 2009 6:57:46 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50093
;

-- May 20, 2009 6:57:47 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=330, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='Y', IsHeightOneLine='N', PrintAreaType='H', AD_PrintFormat_ID=50001, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=200, LineAlignmentType='X', XSpace=0, FieldAlignmentType='L', IsRelativePosition='N', PrintName='Delivered Date:', YPosition=130, IsSummarized='N', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='N', IsMinCalc='N', Name='Date Delivered', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=0, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType=NULL, LineWidth=0, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:57:47','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50094
;

-- May 20, 2009 6:57:47 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50094
;

-- May 20, 2009 6:57:48 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='Y', IsHeightOneLine='N', PrintAreaType='C', AD_PrintFormat_ID=50001, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=210, LineAlignmentType='X', XSpace=5, FieldAlignmentType='L', IsRelativePosition='Y', PrintName='Lot', YPosition=0, IsSummarized='N', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='N', IsMinCalc='N', Name='Lot No', IsAveraged='N', BelowColumn=0, YSpace=145, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=0, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType=NULL, LineWidth=0, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:57:48','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50095
;

-- May 20, 2009 6:57:48 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50095
;

-- May 20, 2009 6:57:48 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='Y', IsHeightOneLine='N', PrintAreaType='C', AD_PrintFormat_ID=50001, AD_PrintFormatChild_ID=50040, IsGroupBy='N', MaxHeight=0, PrintFormatType='P', ImageIsAttached='N', SeqNo=220, LineAlignmentType='X', XSpace=0, FieldAlignmentType='L', IsRelativePosition='Y', PrintName='BOM & Formula', YPosition=0, IsSummarized='N', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='N', IsMinCalc='N', Name='Manufacturing Order', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=0, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType=NULL, LineWidth=0, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:57:48','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50096
;

-- May 20, 2009 6:57:48 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50096
;

-- May 20, 2009 6:57:49 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='Y', IsHeightOneLine='N', PrintAreaType='C', AD_PrintFormat_ID=50001, AD_PrintFormatChild_ID=50037, IsGroupBy='N', MaxHeight=0, PrintFormatType='P', ImageIsAttached='N', SeqNo=230, LineAlignmentType='X', XSpace=0, FieldAlignmentType='L', IsRelativePosition='Y', PrintName='Workflow:', YPosition=0, IsSummarized='N', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='N', IsMinCalc='N', Name='Manufacturing Order', IsAveraged='N', BelowColumn=0, YSpace=10, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=0, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType=NULL, LineWidth=0, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:57:49','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50096
;

-- May 20, 2009 6:57:49 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50096
;

-- May 20, 2009 6:57:49 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormat SET AD_PrintColor_ID=100, AD_PrintFont_ID=119, AD_PrintPaper_ID=101, AD_PrintTableFormat_ID=100, AD_Table_ID=53198, CreateCopy='N', Description='Manufacturing Order BOM Line', FooterMargin=0, HeaderMargin=0, IsActive='Y', IsDefault='N', IsForm='N', IsStandardHeaderFooter='N', IsTableBased='Y', Name='Manufacturing_Order_BOMLine_Component ** TEMPLATE **', PrinterName=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:57:49','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormat_ID=50040
;

-- May 20, 2009 6:57:49 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='N', IsHeightOneLine='N', PrintAreaType='C', AD_PrintFormat_ID=50040, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=0, AD_Column_ID=57415, LineAlignmentType='X', XSpace=0, FieldAlignmentType='L', IsRelativePosition='Y', PrintName='Manufacturing Order', YPosition=0, IsSummarized='N', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='N', IsMinCalc='N', Name='Manufacturing Order', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=0, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType=NULL, LineWidth=0, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:57:49','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=51077
;

-- May 20, 2009 6:57:49 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=51077
;

-- May 20, 2009 6:57:50 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='N', IsHeightOneLine='N', PrintAreaType='C', AD_PrintFormat_ID=50040, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=0, AD_Column_ID=57393, LineAlignmentType='X', XSpace=0, FieldAlignmentType='L', IsRelativePosition='Y', PrintName='Updated', YPosition=0, IsSummarized='N', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='N', IsMinCalc='N', Name='Updated', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=0, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType=NULL, LineWidth=0, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:57:50','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=51078
;

-- May 20, 2009 6:57:50 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=51078
;

-- May 20, 2009 6:57:50 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='N', IsHeightOneLine='N', PrintAreaType='C', AD_PrintFormat_ID=50040, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=0, AD_Column_ID=57394, LineAlignmentType='X', XSpace=0, FieldAlignmentType='L', IsRelativePosition='Y', PrintName='Updated By', YPosition=0, IsSummarized='N', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='N', IsMinCalc='N', Name='Updated By', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=0, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType=NULL, LineWidth=0, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:57:50','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=51079
;

-- May 20, 2009 6:57:50 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=51079
;

-- May 20, 2009 6:57:51 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='N', IsHeightOneLine='N', PrintAreaType='C', AD_PrintFormat_ID=50040, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=0, AD_Column_ID=57416, LineAlignmentType='X', XSpace=0, FieldAlignmentType='T', IsRelativePosition='Y', PrintName='Quantity', YPosition=0, IsSummarized='Y', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='N', IsMinCalc='N', Name='Quantity', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=0, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType=NULL, LineWidth=0, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:57:51','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=51080
;

-- May 20, 2009 6:57:51 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=51080
;

-- May 20, 2009 6:57:51 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='N', IsHeightOneLine='N', PrintAreaType='C', AD_PrintFormat_ID=50040, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=0, AD_Column_ID=57404, LineAlignmentType='X', XSpace=0, FieldAlignmentType='L', IsRelativePosition='Y', PrintName='Is Critical Component', YPosition=0, IsSummarized='N', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='N', IsMinCalc='N', Name='Is Critical Component', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=0, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType=NULL, LineWidth=0, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:57:51','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=51081
;

-- May 20, 2009 6:57:51 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=51081
;

-- May 20, 2009 6:57:52 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='N', IsHeightOneLine='N', PrintAreaType='C', AD_PrintFormat_ID=50040, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=0, AD_Column_ID=57414, LineAlignmentType='X', XSpace=0, FieldAlignmentType='L', IsRelativePosition='Y', PrintName='Manufacturing Order BOM Line', YPosition=0, IsSummarized='N', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='N', IsMinCalc='N', Name='Manufacturing Order BOM Line', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=0, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType=NULL, LineWidth=0, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:57:52','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=51082
;

-- May 20, 2009 6:57:52 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=51082
;

-- May 20, 2009 6:57:53 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='N', IsHeightOneLine='N', PrintAreaType='C', AD_PrintFormat_ID=50040, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=0, AD_Column_ID=57413, LineAlignmentType='X', XSpace=0, FieldAlignmentType='L', IsRelativePosition='Y', PrintName='Manufacturing Order BOM', YPosition=0, IsSummarized='N', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='N', IsMinCalc='N', Name='Manufacturing Order BOM', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=0, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType='N', LineWidth=1, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:57:53','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=51083
;

-- May 20, 2009 6:57:53 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=51083
;

-- May 20, 2009 6:57:53 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='N', IsHeightOneLine='N', PrintAreaType='C', AD_PrintFormat_ID=50040, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=0, AD_Column_ID=57417, LineAlignmentType='X', XSpace=0, FieldAlignmentType='T', IsRelativePosition='Y', PrintName='Quantity in %', YPosition=0, IsSummarized='N', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='N', IsMinCalc='N', Name='Quantity in %', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=0, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType='N', LineWidth=1, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:57:53','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=51084
;

-- May 20, 2009 6:57:53 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=51084
;

-- May 20, 2009 6:57:54 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='N', IsHeightOneLine='N', PrintAreaType='C', AD_PrintFormat_ID=50040, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=0, AD_Column_ID=57405, LineAlignmentType='X', XSpace=0, FieldAlignmentType='L', IsRelativePosition='Y', PrintName='Is Qty Percentage', YPosition=0, IsSummarized='N', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='N', IsMinCalc='N', Name='Is Qty Percentage', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=0, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType=NULL, LineWidth=0, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:57:54','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=51085
;

-- May 20, 2009 6:57:54 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=51085
;

-- May 20, 2009 6:57:54 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='N', IsHeightOneLine='N', PrintAreaType='C', AD_PrintFormat_ID=50040, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=0, AD_Column_ID=57392, LineAlignmentType='X', XSpace=0, FieldAlignmentType='L', IsRelativePosition='Y', PrintName='Created By', YPosition=0, IsSummarized='N', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='N', IsMinCalc='N', Name='Created By', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=0, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType=NULL, LineWidth=0, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:57:54','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=51086
;

-- May 20, 2009 6:57:54 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=51086
;

-- May 20, 2009 6:57:55 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='N', IsHeightOneLine='N', PrintAreaType='C', AD_PrintFormat_ID=50040, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=0, AD_Column_ID=57389, LineAlignmentType='X', XSpace=0, FieldAlignmentType='L', IsRelativePosition='Y', PrintName='Organization', YPosition=0, IsSummarized='N', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='N', IsMinCalc='N', Name='Organization', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=0, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType=NULL, LineWidth=0, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:57:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=51087
;

-- May 20, 2009 6:57:55 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=51087
;

-- May 20, 2009 6:57:55 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='N', IsHeightOneLine='N', PrintAreaType='C', AD_PrintFormat_ID=50040, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=0, AD_Column_ID=57390, LineAlignmentType='X', XSpace=0, FieldAlignmentType='L', IsRelativePosition='Y', PrintName='Active', YPosition=0, IsSummarized='N', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='N', IsMinCalc='N', Name='Active', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=0, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType=NULL, LineWidth=0, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:57:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=51088
;

-- May 20, 2009 6:57:55 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=51088
;

-- May 20, 2009 6:57:56 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='N', IsHeightOneLine='N', PrintAreaType='C', AD_PrintFormat_ID=50040, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=0, AD_Column_ID=57388, LineAlignmentType='X', XSpace=0, FieldAlignmentType='L', IsRelativePosition='Y', PrintName='Client', YPosition=0, IsSummarized='N', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='N', IsMinCalc='N', Name='Client', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=0, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType=NULL, LineWidth=0, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:57:56','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=51089
;

-- May 20, 2009 6:57:56 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=51089
;

-- May 20, 2009 6:57:56 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='N', IsHeightOneLine='N', PrintAreaType='C', AD_PrintFormat_ID=50040, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=0, AD_Column_ID=57400, LineAlignmentType='X', XSpace=0, FieldAlignmentType='L', IsRelativePosition='Y', PrintName='Component Type', YPosition=0, IsSummarized='N', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='N', IsMinCalc='N', Name='Component Type', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=0, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType='N', LineWidth=1, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:57:56','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=51090
;

-- May 20, 2009 6:57:56 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=51090
;

-- May 20, 2009 6:57:57 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='N', IsHeightOneLine='N', PrintAreaType='C', AD_PrintFormat_ID=50040, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=0, AD_Column_ID=57391, LineAlignmentType='X', XSpace=0, FieldAlignmentType='L', IsRelativePosition='Y', PrintName='Created', YPosition=0, IsSummarized='N', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='N', IsMinCalc='N', Name='Created', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=0, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType=NULL, LineWidth=0, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:57:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=51091
;

-- May 20, 2009 6:57:57 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=51091
;

-- May 20, 2009 6:57:58 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='N', IsHeightOneLine='N', PrintAreaType='C', AD_PrintFormat_ID=50040, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=0, AD_Column_ID=57552, LineAlignmentType='X', XSpace=0, FieldAlignmentType='T', IsRelativePosition='Y', PrintName='Qty Available', YPosition=0, IsSummarized='N', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='N', IsMinCalc='N', Name='Available Quantity', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=0, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType=NULL, LineWidth=0, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:57:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=51092
;

-- May 20, 2009 6:57:58 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=51092
;

-- May 20, 2009 6:57:58 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='N', IsHeightOneLine='N', PrintAreaType='C', AD_PrintFormat_ID=50040, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=0, AD_Column_ID=57553, LineAlignmentType='X', XSpace=0, FieldAlignmentType='T', IsRelativePosition='Y', PrintName='On Hand Qty', YPosition=0, IsSummarized='N', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='N', IsMinCalc='N', Name='On Hand Quantity', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=0, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType=NULL, LineWidth=0, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:57:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=51093
;

-- May 20, 2009 6:57:58 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=51093
;

-- May 20, 2009 6:57:59 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='N', IsHeightOneLine='N', PrintAreaType='C', AD_PrintFormat_ID=50040, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=0, AD_Column_ID=57422, LineAlignmentType='X', XSpace=0, FieldAlignmentType='T', IsRelativePosition='Y', PrintName='Reserved Qty', YPosition=0, IsSummarized='N', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='N', IsMinCalc='N', Name='Reserved Quantity', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=0, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType=NULL, LineWidth=0, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:57:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=51094
;

-- May 20, 2009 6:57:59 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=51094
;

-- May 20, 2009 6:57:59 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='N', IsHeightOneLine='N', PrintAreaType='C', AD_PrintFormat_ID=50040, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=0, AD_Column_ID=57412, LineAlignmentType='X', XSpace=0, FieldAlignmentType='L', IsRelativePosition='Y', PrintName='Warehouse', YPosition=0, IsSummarized='N', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='N', IsMinCalc='N', Name='Warehouse', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=0, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType=NULL, LineWidth=0, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:57:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=51095
;

-- May 20, 2009 6:57:59 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=51095
;

-- May 20, 2009 6:58:00 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='Y', IsHeightOneLine='N', PrintAreaType='C', AD_PrintFormat_ID=50040, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=10, AD_Column_ID=57397, LineAlignmentType='X', XSpace=0, FieldAlignmentType='L', IsRelativePosition='Y', PrintName='Product', YPosition=0, IsSummarized='N', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='N', IsMinCalc='N', Name='Product', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=0, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType=NULL, LineWidth=0, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:58:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=51096
;

-- May 20, 2009 6:58:00 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=51096
;

-- May 20, 2009 6:58:00 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='Y', IsHeightOneLine='Y', PrintAreaType='C', AD_PrintFormat_ID=50040, IsGroupBy='N', MaxHeight=0, AD_PrintColor_ID=100, PrintFormatType='F', ImageIsAttached='N', SeqNo=20, AD_Column_ID=57395, LineAlignmentType='X', XSpace=0, FieldAlignmentType='D', IsRelativePosition='Y', PrintName='Description', YPosition=0, AD_PrintFont_ID=130, IsSummarized='N', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='Y', IsMinCalc='N', Name='Description', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=20, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType='N', LineWidth=1, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:58:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=51097
;

-- May 20, 2009 6:58:00 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=51097
;

-- May 20, 2009 6:58:01 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='Y', IsHeightOneLine='Y', PrintAreaType='C', AD_PrintFormat_ID=50040, IsGroupBy='N', MaxHeight=0, AD_PrintColor_ID=100, PrintFormatType='F', ImageIsAttached='N', SeqNo=30, AD_Column_ID=57403, LineAlignmentType='X', XSpace=0, FieldAlignmentType='D', IsRelativePosition='Y', PrintName='Help', YPosition=0, AD_PrintFont_ID=130, IsSummarized='N', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='Y', IsMinCalc='N', Name='Help', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=20, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType='N', LineWidth=1, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:58:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=51098
;

-- May 20, 2009 6:58:01 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=51098
;

-- May 20, 2009 6:58:01 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='Y', IsHeightOneLine='N', PrintAreaType='C', AD_PrintFormat_ID=50040, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=40, AD_Column_ID=57399, LineAlignmentType='X', XSpace=0, FieldAlignmentType='L', IsRelativePosition='Y', PrintName='UOM', YPosition=0, IsSummarized='N', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='N', IsMinCalc='N', Name='UOM', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=0, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType=NULL, LineWidth=0, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:58:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=51099
;

-- May 20, 2009 6:58:01 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=51099
;

-- May 20, 2009 6:58:02 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='Y', IsHeightOneLine='N', PrintAreaType='C', AD_PrintFormat_ID=50040, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=50, AD_Column_ID=57554, LineAlignmentType='X', XSpace=0, FieldAlignmentType='T', IsRelativePosition='Y', PrintName='Qty Batch Size', YPosition=0, IsSummarized='Y', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='N', IsMinCalc='N', Name='Qty Batch Size', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=0, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType=NULL, LineWidth=0, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:58:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=51100
;

-- May 20, 2009 6:58:02 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=51100
;

-- May 20, 2009 6:58:03 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='Y', IsHeightOneLine='N', PrintAreaType='C', AD_PrintFormat_ID=50040, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=60, AD_Column_ID=57421, LineAlignmentType='X', XSpace=0, FieldAlignmentType='T', IsRelativePosition='Y', PrintName='Qty Required', YPosition=0, IsSummarized='Y', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='N', IsMinCalc='N', Name='Qty Requiered', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=0, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType=NULL, LineWidth=0, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:58:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=51101
;

-- May 20, 2009 6:58:03 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=51101
;

-- May 20, 2009 6:58:03 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormat SET AD_PrintColor_ID=100, AD_PrintFont_ID=119, AD_PrintPaper_ID=100, AD_PrintTableFormat_ID=100, AD_Table_ID=53200, CreateCopy='N', Description='Manufacturing Order Node', FooterMargin=0, HeaderMargin=0, IsActive='Y', IsDefault='N', IsForm='N', IsStandardHeaderFooter='N', IsTableBased='Y', Name='Manufacturing_Order_Node ** TEMPLATE **', PrinterName=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:58:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormat_ID=50037
;

-- May 20, 2009 6:58:03 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='N', IsHeightOneLine='N', PrintAreaType='C', AD_PrintFormat_ID=50037, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=0, AD_Column_ID=57500, LineAlignmentType='X', XSpace=0, FieldAlignmentType='L', IsRelativePosition='Y', PrintName='Client', YPosition=0, IsSummarized='N', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='N', IsMinCalc='N', Name='Client', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=0, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType='N', LineWidth=1, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:58:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50965
;

-- May 20, 2009 6:58:03 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50965
;

-- May 20, 2009 6:58:04 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='N', IsHeightOneLine='N', PrintAreaType='C', AD_PrintFormat_ID=50037, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=0, AD_Column_ID=57503, LineAlignmentType='X', XSpace=0, FieldAlignmentType='L', IsRelativePosition='Y', PrintName='Created', YPosition=0, IsSummarized='N', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='N', IsMinCalc='N', Name='Created', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=0, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType='N', LineWidth=1, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:58:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50966
;

-- May 20, 2009 6:58:04 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50966
;

-- May 20, 2009 6:58:04 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='N', IsHeightOneLine='N', PrintAreaType='C', AD_PrintFormat_ID=50037, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=0, AD_Column_ID=57506, LineAlignmentType='X', XSpace=0, FieldAlignmentType='L', IsRelativePosition='Y', PrintName='Updated By', YPosition=0, IsSummarized='N', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='N', IsMinCalc='N', Name='Updated By', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=0, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType='N', LineWidth=1, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:58:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50983
;

-- May 20, 2009 6:58:04 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50983
;

-- May 20, 2009 6:58:05 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='N', IsHeightOneLine='N', PrintAreaType='C', AD_PrintFormat_ID=50037, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=0, AD_Column_ID=57505, LineAlignmentType='X', XSpace=0, FieldAlignmentType='L', IsRelativePosition='Y', PrintName='Updated', YPosition=0, IsSummarized='N', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='N', IsMinCalc='N', Name='Updated', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=0, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType='N', LineWidth=1, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:58:05','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50982
;

-- May 20, 2009 6:58:05 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50982
;

-- May 20, 2009 6:58:06 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='N', IsHeightOneLine='N', PrintAreaType='C', AD_PrintFormat_ID=50037, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=0, AD_Column_ID=57525, LineAlignmentType='X', XSpace=0, FieldAlignmentType='L', IsRelativePosition='Y', PrintName='Manufacturing Order Workflow', YPosition=0, IsSummarized='N', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='N', IsMinCalc='N', Name='Manufacturing Order Workflow', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=0, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType='N', LineWidth=1, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:58:06','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50976
;

-- May 20, 2009 6:58:06 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50976
;

-- May 20, 2009 6:58:06 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='N', IsHeightOneLine='N', PrintAreaType='C', AD_PrintFormat_ID=50037, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=0, AD_Column_ID=57527, LineAlignmentType='X', XSpace=0, FieldAlignmentType='T', IsRelativePosition='Y', PrintName='Delivered Qty', YPosition=0, IsSummarized='N', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='N', IsMinCalc='N', Name='Delivered Quantity', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=0, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType='N', LineWidth=1, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:58:06','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50970
;

-- May 20, 2009 6:58:06 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50970
;

-- May 20, 2009 6:58:07 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='N', IsHeightOneLine='N', PrintAreaType='C', AD_PrintFormat_ID=50037, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=0, AD_Column_ID=57517, LineAlignmentType='X', XSpace=0, FieldAlignmentType='T', IsRelativePosition='Y', PrintName='Duration Real', YPosition=0, IsSummarized='N', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='N', IsMinCalc='N', Name='Duration Real', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=0, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType='N', LineWidth=1, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:58:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50973
;

-- May 20, 2009 6:58:07 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50973
;

-- May 20, 2009 6:58:07 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='N', IsHeightOneLine='N', PrintAreaType='C', AD_PrintFormat_ID=50037, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=0, AD_Column_ID=57501, LineAlignmentType='X', XSpace=0, FieldAlignmentType='L', IsRelativePosition='Y', PrintName='Organization', YPosition=0, IsSummarized='N', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='N', IsMinCalc='N', Name='Organization', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=0, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType='N', LineWidth=1, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:58:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50977
;

-- May 20, 2009 6:58:07 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50977
;

-- May 20, 2009 6:58:08 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='N', IsHeightOneLine='N', PrintAreaType='C', AD_PrintFormat_ID=50037, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=0, AD_Column_ID=57529, LineAlignmentType='X', XSpace=0, FieldAlignmentType='T', IsRelativePosition='Y', PrintName='% Scrap', YPosition=0, IsSummarized='N', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='N', IsMinCalc='N', Name='QtyScrap', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=0, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType='N', LineWidth=1, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:58:08','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50979
;

-- May 20, 2009 6:58:08 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50979
;

-- May 20, 2009 6:58:08 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='N', IsHeightOneLine='N', PrintAreaType='C', AD_PrintFormat_ID=50037, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=0, AD_Column_ID=57502, LineAlignmentType='X', XSpace=0, FieldAlignmentType='L', IsRelativePosition='Y', PrintName='Active', YPosition=0, IsSummarized='N', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='N', IsMinCalc='N', Name='Active', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=0, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType='N', LineWidth=1, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:58:08','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50964
;

-- May 20, 2009 6:58:08 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50964
;

-- May 20, 2009 6:58:09 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='N', IsHeightOneLine='N', PrintAreaType='C', AD_PrintFormat_ID=50037, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=0, AD_Column_ID=57504, LineAlignmentType='X', XSpace=0, FieldAlignmentType='L', IsRelativePosition='Y', PrintName='Created By', YPosition=0, IsSummarized='N', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='N', IsMinCalc='N', Name='Created By', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=0, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType='N', LineWidth=1, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:58:09','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50967
;

-- May 20, 2009 6:58:09 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50967
;

-- May 20, 2009 6:58:10 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='N', IsHeightOneLine='N', PrintAreaType='C', AD_PrintFormat_ID=50037, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=0, AD_Column_ID=57524, LineAlignmentType='X', XSpace=0, FieldAlignmentType='L', IsRelativePosition='Y', PrintName='Manufacturing Order', YPosition=0, IsSummarized='N', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='N', IsMinCalc='N', Name='Manufacturing Order', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=0, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType='N', LineWidth=1, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:58:10','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50975
;

-- May 20, 2009 6:58:10 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50975
;

-- May 20, 2009 6:58:10 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='Y', IsHeightOneLine='Y', PrintAreaType='C', AD_PrintFormat_ID=50037, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=10, AD_Column_ID=57531, LineAlignmentType='X', XSpace=0, FieldAlignmentType='L', IsRelativePosition='Y', PrintName='Resource', YPosition=0, IsSummarized='N', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='Y', IsNextLine='N', IsMinCalc='N', Name='Resource', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=40, IsNextPage='N', RunningTotalLines=0, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType='N', LineWidth=1, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:58:10','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50980
;

-- May 20, 2009 6:58:10 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50980
;

-- May 20, 2009 6:58:11 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='Y', IsHeightOneLine='N', PrintAreaType='C', AD_PrintFormat_ID=50037, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=20, AD_Column_ID=57537, LineAlignmentType='X', XSpace=0, FieldAlignmentType='L', IsRelativePosition='Y', PrintName='Search Key', YPosition=0, IsSummarized='N', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='N', IsMinCalc='N', Name='Search Key', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=0, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType='N', LineWidth=1, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:58:11','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50981
;

-- May 20, 2009 6:58:11 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50981
;

-- May 20, 2009 6:58:11 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='Y', IsHeightOneLine='N', PrintAreaType='C', AD_PrintFormat_ID=50037, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=30, AD_Column_ID=57507, LineAlignmentType='X', XSpace=0, FieldAlignmentType='L', IsRelativePosition='Y', PrintName='Name', YPosition=0, IsSummarized='N', IsSuppressNull='Y', IsPageBreak='N', IsFixedWidth='N', IsNextLine='N', IsMinCalc='N', Name='Name', IsAveraged='N', BelowColumn=2, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=0, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType='N', LineWidth=1, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:58:11','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50984
;

-- May 20, 2009 6:58:11 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50984
;

-- May 20, 2009 6:58:12 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='Y', IsHeightOneLine='N', PrintAreaType='C', AD_PrintFormat_ID=50037, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=40, AD_Column_ID=57514, LineAlignmentType='X', XSpace=0, FieldAlignmentType='B', IsRelativePosition='Y', PrintName='Description', YPosition=0, IsSummarized='N', IsSuppressNull='Y', IsPageBreak='N', IsFixedWidth='N', IsNextLine='Y', IsMinCalc='N', Name='Description', IsAveraged='N', BelowColumn=3, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=20, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType='N', LineWidth=1, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:58:12','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50985
;

-- May 20, 2009 6:58:12 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50985
;

-- May 20, 2009 6:58:13 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='Y', IsHeightOneLine='Y', PrintAreaType='C', AD_PrintFormat_ID=50037, IsGroupBy='N', MaxHeight=0, AD_PrintColor_ID=100, PrintFormatType='F', ImageIsAttached='N', SeqNo=50, AD_Column_ID=57519, LineAlignmentType='X', XSpace=0, FieldAlignmentType='D', IsRelativePosition='Y', PrintName='Help', YPosition=0, AD_PrintFont_ID=130, IsSummarized='N', IsSuppressNull='Y', IsPageBreak='N', IsFixedWidth='N', IsNextLine='Y', IsMinCalc='N', Name='Help', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=20, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType='N', LineWidth=1, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:58:13','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=51076
;

-- May 20, 2009 6:58:13 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=51076
;

-- May 20, 2009 6:58:13 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='Y', IsHeightOneLine='N', PrintAreaType='C', AD_PrintFormat_ID=50037, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=60, AD_Column_ID=57511, LineAlignmentType='X', XSpace=0, FieldAlignmentType='L', IsRelativePosition='Y', PrintName='DateFinishSchedule', YPosition=0, IsSummarized='N', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='N', IsMinCalc='N', Name='DateFinishSchedule', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=0, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType='N', LineWidth=1, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:58:13','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50968
;

-- May 20, 2009 6:58:13 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50968
;

-- May 20, 2009 6:58:14 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='Y', IsHeightOneLine='N', PrintAreaType='C', AD_PrintFormat_ID=50037, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=70, AD_Column_ID=57511, LineAlignmentType='X', XSpace=0, FieldAlignmentType='L', IsRelativePosition='Y', PrintName='DateFinishSchedule', YPosition=0, IsSummarized='N', IsSuppressNull='Y', IsPageBreak='N', IsFixedWidth='N', IsNextLine='Y', IsMinCalc='N', Name='DateFinishSchedule', IsAveraged='N', BelowColumn=5, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=0, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType='N', LineWidth=1, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:58:14','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50968
;

-- May 20, 2009 6:58:14 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50968
;

-- May 20, 2009 6:58:14 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='Y', IsHeightOneLine='N', PrintAreaType='C', AD_PrintFormat_ID=50037, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=80, AD_Column_ID=57538, LineAlignmentType='X', XSpace=0, FieldAlignmentType='L', IsRelativePosition='Y', PrintName='Waiting Time', YPosition=0, IsSummarized='Y', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='Y', IsMinCalc='N', Name='Waiting Time', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=20, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType='N', LineWidth=1, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:58:14','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50991
;

-- May 20, 2009 6:58:14 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50991
;

-- May 20, 2009 6:58:15 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='Y', IsHeightOneLine='N', PrintAreaType='C', AD_PrintFormat_ID=50037, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=90, AD_Column_ID=57532, LineAlignmentType='X', XSpace=0, FieldAlignmentType='L', IsRelativePosition='Y', PrintName='Setup', YPosition=0, IsSummarized='Y', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='N', IsMinCalc='N', Name='Setup T', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=20, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType='N', LineWidth=1, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:58:15','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50992
;

-- May 20, 2009 6:58:15 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50992
;

-- May 20, 2009 6:58:16 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='Y', IsHeightOneLine='N', PrintAreaType='C', AD_PrintFormat_ID=50037, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=100, AD_Column_ID=57516, LineAlignmentType='X', XSpace=0, FieldAlignmentType='L', IsRelativePosition='Y', PrintName='Duration', YPosition=0, IsSummarized='Y', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='N', IsMinCalc='N', Name='Duration', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=0, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType='N', LineWidth=1, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:58:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50972
;

-- May 20, 2009 6:58:16 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50972
;

-- May 20, 2009 6:58:16 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='Y', IsHeightOneLine='N', PrintAreaType='C', AD_PrintFormat_ID=50037, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=110, AD_Column_ID=57518, LineAlignmentType='X', XSpace=0, FieldAlignmentType='L', IsRelativePosition='Y', PrintName='Duration Requiered', YPosition=0, IsSummarized='Y', IsSuppressNull='Y', IsPageBreak='N', IsFixedWidth='N', IsNextLine='Y', IsMinCalc='N', Name='Duration Requiered', IsAveraged='N', BelowColumn=9, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=0, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType='N', LineWidth=1, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:58:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50974
;

-- May 20, 2009 6:58:16 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50974
;

-- May 20, 2009 6:58:17 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='Y', IsHeightOneLine='N', PrintAreaType='C', AD_PrintFormat_ID=50037, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=120, AD_Column_ID=57522, LineAlignmentType='X', XSpace=0, FieldAlignmentType='L', IsRelativePosition='Y', PrintName='Moving Time', YPosition=0, IsSummarized='Y', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='Y', IsMinCalc='N', Name='Moving Time', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=20, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType='N', LineWidth=1, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:58:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50989
;

-- May 20, 2009 6:58:17 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50989
;

-- May 20, 2009 6:58:17 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='Y', IsHeightOneLine='N', PrintAreaType='C', AD_PrintFormat_ID=50037, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=130, AD_Column_ID=57530, LineAlignmentType='X', XSpace=0, FieldAlignmentType='L', IsRelativePosition='Y', PrintName='Queuing Time', YPosition=0, IsSummarized='Y', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='Y', IsMinCalc='N', Name='Queuing Time', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=20, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType='N', LineWidth=1, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:58:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50990
;

-- May 20, 2009 6:58:17 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50990
;

-- May 20, 2009 6:58:18 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='Y', IsHeightOneLine='N', PrintAreaType='C', AD_PrintFormat_ID=50037, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=140, AD_Column_ID=57551, LineAlignmentType='X', XSpace=0, FieldAlignmentType='L', IsRelativePosition='Y', PrintName='Doc Status', YPosition=0, IsSummarized='Y', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='N', IsMinCalc='N', Name='Document Status', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=0, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType='N', LineWidth=1, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:58:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50971
;

-- May 20, 2009 6:58:18 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50971
;

-- May 20, 2009 6:58:19 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='Y', IsHeightOneLine='N', PrintAreaType='C', AD_PrintFormat_ID=50037, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=150, AD_Column_ID=57520, LineAlignmentType='X', XSpace=0, FieldAlignmentType='L', IsRelativePosition='Y', PrintName='Is Milestone', YPosition=0, IsSummarized='N', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='Y', IsMinCalc='N', Name='Is Milestone', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=20, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType='N', LineWidth=1, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:58:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50987
;

-- May 20, 2009 6:58:19 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50987
;

-- May 20, 2009 6:58:20 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='Y', IsHeightOneLine='N', PrintAreaType='C', AD_PrintFormat_ID=50037, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=160, AD_Column_ID=57521, LineAlignmentType='X', XSpace=0, FieldAlignmentType='L', IsRelativePosition='Y', PrintName='Is Subcontracting', YPosition=0, IsSummarized='N', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='N', IsMinCalc='N', Name='Is Subcontracting', IsAveraged='N', BelowColumn=14, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=20, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType='N', LineWidth=1, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:58:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50988
;

-- May 20, 2009 6:58:20 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50988
;

-- May 20, 2009 6:58:20 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='Y', IsHeightOneLine='N', PrintAreaType='C', AD_PrintFormat_ID=50037, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=170, AD_Column_ID=57508, LineAlignmentType='X', XSpace=0, FieldAlignmentType='L', IsRelativePosition='Y', PrintName='Business Partner ', YPosition=0, IsSummarized='N', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='Y', IsMinCalc='N', Name='Business Partner ', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=20, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType='N', LineWidth=1, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 18:58:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50986
;

-- May 20, 2009 6:58:20 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50986
;

-- May 20, 2009 6:58:57 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormat (AD_Client_ID,AD_Org_ID,AD_PrintColor_ID,AD_PrintFont_ID,AD_PrintFormat_ID,AD_PrintPaper_ID,AD_PrintTableFormat_ID,AD_ReportView_ID,AD_Table_ID,CreateCopy,Created,CreatedBy,Description,FooterMargin,HeaderMargin,IsActive,IsDefault,IsForm,IsStandardHeaderFooter,IsTableBased,Name,Updated,UpdatedBy) VALUES (0,0,100,119,50044,101,100,53026,53198,'N',TO_TIMESTAMP('2009-05-20 18:58:56','YYYY-MM-DD HH24:MI:SS'),0,'Manufacturing Order BOM Line',0,0,'Y','N','N','N','Y','Manufacturing_Order_BOMLine_Packing** TEMPLATE **',TO_TIMESTAMP('2009-05-20 18:58:56','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 20, 2009 6:58:58 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:58:57','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50044,'N',0,'F','N',0,57415,'X',0,0,0,'L','Y','Manufacturing Order',0,0,'N','N','N','N','N','N','Manufacturing Order','N',0,TO_TIMESTAMP('2009-05-20 18:58:57','YYYY-MM-DD HH24:MI:SS'),0,'N',51249,'Y',0,'N',0,'N','N','N','N',0,0)
;

-- May 20, 2009 6:58:58 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51249 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:59:01 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:58:58','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50044,'N',0,'F','N',0,57413,'X',0,0,0,'L','Y','Manufacturing Order BOM',0,0,'N','N','N','N','N','N','Manufacturing Order BOM','N',0,TO_TIMESTAMP('2009-05-20 18:58:58','YYYY-MM-DD HH24:MI:SS'),0,'N',51250,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:59:01 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51250 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:59:02 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:59:01','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50044,'N',0,'F','N',0,57414,'X',0,0,0,'L','Y','Manufacturing Order BOM Line',0,0,'N','N','N','N','N','N','Manufacturing Order BOM Line','N',0,TO_TIMESTAMP('2009-05-20 18:59:01','YYYY-MM-DD HH24:MI:SS'),0,'N',51251,'Y',0,'N',0,'N','N','N','N',0,0)
;

-- May 20, 2009 6:59:02 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51251 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:59:03 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:59:02','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50044,'N',0,'F','N',0,57404,'X',0,0,0,'L','Y','Is Critical Component',0,0,'N','N','N','N','N','N','Is Critical Component','N',0,TO_TIMESTAMP('2009-05-20 18:59:02','YYYY-MM-DD HH24:MI:SS'),0,'N',51252,'Y',0,'N',0,'N','N','N','N',0,0)
;

-- May 20, 2009 6:59:03 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51252 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:59:05 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:59:03','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50044,'N',0,'F','N',0,57416,'X',0,0,0,'T','Y','Quantity',0,0,'Y','N','N','N','N','N','Quantity','N',0,TO_TIMESTAMP('2009-05-20 18:59:03','YYYY-MM-DD HH24:MI:SS'),0,'N',51253,'Y',0,'N',0,'N','N','N','N',0,0)
;

-- May 20, 2009 6:59:05 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51253 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:59:07 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:59:05','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50044,'N',0,'F','N',0,57394,'X',0,0,0,'L','Y','Updated By',0,0,'N','N','N','N','N','N','Updated By','N',0,TO_TIMESTAMP('2009-05-20 18:59:05','YYYY-MM-DD HH24:MI:SS'),0,'N',51254,'Y',0,'N',0,'N','N','N','N',0,0)
;

-- May 20, 2009 6:59:07 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51254 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:59:08 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:59:07','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50044,'N',0,'F','N',0,57393,'X',0,0,0,'L','Y','Updated',0,0,'N','N','N','N','N','N','Updated','N',0,TO_TIMESTAMP('2009-05-20 18:59:07','YYYY-MM-DD HH24:MI:SS'),0,'N',51255,'Y',0,'N',0,'N','N','N','N',0,0)
;

-- May 20, 2009 6:59:08 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51255 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:59:09 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:59:08','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50044,'N',0,'F','N',0,57412,'X',0,0,0,'L','Y','Warehouse',0,0,'N','N','N','N','N','N','Warehouse','N',0,TO_TIMESTAMP('2009-05-20 18:59:08','YYYY-MM-DD HH24:MI:SS'),0,'N',51256,'Y',0,'N',0,'N','N','N','N',0,0)
;

-- May 20, 2009 6:59:09 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51256 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:59:11 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:59:09','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50044,'N',0,'F','N',0,57422,'X',0,0,0,'T','Y','Reserved Qty',0,0,'N','N','N','N','N','N','Reserved Quantity','N',0,TO_TIMESTAMP('2009-05-20 18:59:09','YYYY-MM-DD HH24:MI:SS'),0,'N',51257,'Y',0,'N',0,'N','N','N','N',0,0)
;

-- May 20, 2009 6:59:11 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51257 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:59:12 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:59:11','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50044,'N',0,'F','N',0,57553,'X',0,0,0,'T','Y','On Hand Qty',0,0,'N','N','N','N','N','N','On Hand Quantity','N',0,TO_TIMESTAMP('2009-05-20 18:59:11','YYYY-MM-DD HH24:MI:SS'),0,'N',51258,'Y',0,'N',0,'N','N','N','N',0,0)
;

-- May 20, 2009 6:59:12 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51258 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:59:14 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:59:12','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50044,'N',0,'F','N',0,57552,'X',0,0,0,'T','Y','Qty Available',0,0,'N','N','N','N','N','N','Available Quantity','N',0,TO_TIMESTAMP('2009-05-20 18:59:12','YYYY-MM-DD HH24:MI:SS'),0,'N',51259,'Y',0,'N',0,'N','N','N','N',0,0)
;

-- May 20, 2009 6:59:14 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51259 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:59:14 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:59:14','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50044,'N',0,'F','N',0,57391,'X',0,0,0,'L','Y','Created',0,0,'N','N','N','N','N','N','Created','N',0,TO_TIMESTAMP('2009-05-20 18:59:14','YYYY-MM-DD HH24:MI:SS'),0,'N',51260,'Y',0,'N',0,'N','N','N','N',0,0)
;

-- May 20, 2009 6:59:14 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51260 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:59:16 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:59:14','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50044,'N',0,'F','N',0,57400,'X',0,0,0,'L','Y','Component Type',0,0,'N','N','N','N','N','N','Component Type','N',0,TO_TIMESTAMP('2009-05-20 18:59:14','YYYY-MM-DD HH24:MI:SS'),0,'N',51261,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:59:16 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51261 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:59:20 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:59:16','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50044,'N',0,'F','N',0,57388,'X',0,0,0,'L','Y','Client',0,0,'N','N','N','N','N','N','Client','N',0,TO_TIMESTAMP('2009-05-20 18:59:16','YYYY-MM-DD HH24:MI:SS'),0,'N',51262,'Y',0,'N',0,'N','N','N','N',0,0)
;

-- May 20, 2009 6:59:20 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51262 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:59:20 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:59:20','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50044,'N',0,'F','N',0,57390,'X',0,0,0,'L','Y','Active',0,0,'N','N','N','N','N','N','Active','N',0,TO_TIMESTAMP('2009-05-20 18:59:20','YYYY-MM-DD HH24:MI:SS'),0,'N',51263,'Y',0,'N',0,'N','N','N','N',0,0)
;

-- May 20, 2009 6:59:20 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51263 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:59:22 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:59:20','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50044,'N',0,'F','N',0,57389,'X',0,0,0,'L','Y','Organization',0,0,'N','N','N','N','N','N','Organization','N',0,TO_TIMESTAMP('2009-05-20 18:59:20','YYYY-MM-DD HH24:MI:SS'),0,'N',51264,'Y',0,'N',0,'N','N','N','N',0,0)
;

-- May 20, 2009 6:59:22 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51264 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:59:22 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:59:22','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50044,'N',0,'F','N',0,57392,'X',0,0,0,'L','Y','Created By',0,0,'N','N','N','N','N','N','Created By','N',0,TO_TIMESTAMP('2009-05-20 18:59:22','YYYY-MM-DD HH24:MI:SS'),0,'N',51265,'Y',0,'N',0,'N','N','N','N',0,0)
;

-- May 20, 2009 6:59:22 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51265 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:59:24 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:59:22','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50044,'N',0,'F','N',0,57405,'X',0,0,0,'L','Y','Is Qty Percentage',0,0,'N','N','N','N','N','N','Is Qty Percentage','N',0,TO_TIMESTAMP('2009-05-20 18:59:22','YYYY-MM-DD HH24:MI:SS'),0,'N',51266,'Y',0,'N',0,'N','N','N','N',0,0)
;

-- May 20, 2009 6:59:24 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51266 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:59:25 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:59:24','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50044,'N',0,'F','N',0,57417,'X',0,0,0,'T','Y','Quantity in %',0,0,'N','N','N','N','N','N','Quantity in %','N',0,TO_TIMESTAMP('2009-05-20 18:59:24','YYYY-MM-DD HH24:MI:SS'),0,'N',51267,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:59:25 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51267 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:59:26 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:59:25','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','Y','N','C',50044,'N',0,'F','N',10,57397,'X',0,0,0,'L','Y','Product',0,0,'N','N','N','N','N','N','Product','N',0,TO_TIMESTAMP('2009-05-20 18:59:25','YYYY-MM-DD HH24:MI:SS'),0,'N',51268,'Y',0,'N',0,'N','N','N','N',0,0)
;

-- May 20, 2009 6:59:26 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51268 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:59:27 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,AD_PrintColor_ID,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,AD_PrintFont_ID,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:59:26','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','Y','Y','C',50044,'N',0,100,'F','N',20,57395,'X',0,0,0,'D','Y','Description',0,130,0,'N','N','N','N','Y','N','Description','N',0,TO_TIMESTAMP('2009-05-20 18:59:26','YYYY-MM-DD HH24:MI:SS'),0,'N',51269,'Y',0,'N',20,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:59:27 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51269 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:59:39 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,AD_PrintColor_ID,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,AD_PrintFont_ID,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:59:27','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','Y','Y','C',50044,'N',0,100,'F','N',30,57403,'X',0,0,0,'D','Y','Help',0,130,0,'N','N','N','N','Y','N','Help','N',0,TO_TIMESTAMP('2009-05-20 18:59:27','YYYY-MM-DD HH24:MI:SS'),0,'N',51270,'Y',0,'N',20,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:59:39 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51270 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:59:40 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:59:39','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','Y','N','C',50044,'N',0,'F','N',40,57399,'X',0,0,0,'L','Y','UOM',0,0,'N','N','N','N','N','N','UOM','N',0,TO_TIMESTAMP('2009-05-20 18:59:39','YYYY-MM-DD HH24:MI:SS'),0,'N',51271,'Y',0,'N',0,'N','N','N','N',0,0)
;

-- May 20, 2009 6:59:40 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51271 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:59:40 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:59:40','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','Y','N','C',50044,'N',0,'F','N',50,57554,'X',0,0,0,'T','Y','Qty Batch Size',0,0,'Y','N','N','N','N','N','Qty Batch Size','N',0,TO_TIMESTAMP('2009-05-20 18:59:40','YYYY-MM-DD HH24:MI:SS'),0,'N',51272,'Y',0,'N',0,'N','N','N','N',0,0)
;

-- May 20, 2009 6:59:40 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51272 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:59:41 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:59:40','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','Y','N','C',50044,'N',0,'F','N',60,57421,'X',0,0,0,'T','Y','Qty Required',0,0,'Y','N','N','N','N','N','Qty Requiered','N',0,TO_TIMESTAMP('2009-05-20 18:59:40','YYYY-MM-DD HH24:MI:SS'),0,'N',51273,'Y',0,'N',0,'N','N','N','N',0,0)
;

-- May 20, 2009 6:59:41 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51273 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:59:41 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormat (AD_Client_ID,AD_Org_ID,AD_PrintColor_ID,AD_PrintFont_ID,AD_PrintFormat_ID,AD_PrintPaper_ID,AD_PrintTableFormat_ID,AD_Table_ID,CreateCopy,Created,CreatedBy,FooterMargin,HeaderMargin,IsActive,IsDefault,IsForm,IsStandardHeaderFooter,IsTableBased,Name,Updated,UpdatedBy) VALUES (0,0,100,119,50045,101,100,53197,'N',TO_TIMESTAMP('2009-05-20 18:59:41','YYYY-MM-DD HH24:MI:SS'),0,0,0,'Y','N','Y','N','Y','Manufacturing_Order_BOM_Header_Packing ** TEMPLATE **',TO_TIMESTAMP('2009-05-20 18:59:41','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 20, 2009 6:59:43 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:59:41','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50045,'N',0,'F','N',0,57338,'X',0,0,0,'L','Y','Org Address',0,0,'N','N','N','N','N','N','Org Address','N',0,TO_TIMESTAMP('2009-05-20 18:59:41','YYYY-MM-DD HH24:MI:SS'),0,'N',51274,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:59:43 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51274 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:59:44 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:59:43','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50045,'N',0,'F','N',0,57353,'X',0,0,0,'L','Y','UOM',0,0,'N','N','N','N','N','N','UOM','N',0,TO_TIMESTAMP('2009-05-20 18:59:43','YYYY-MM-DD HH24:MI:SS'),0,'N',51275,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:59:44 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51275 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:59:45 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:59:44','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50045,'N',0,'F','N',0,57339,'X',0,0,0,'L','Y','Tax ID',0,0,'N','N','N','N','N','N','Tax ID','N',0,TO_TIMESTAMP('2009-05-20 18:59:44','YYYY-MM-DD HH24:MI:SS'),0,'N',51276,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:59:45 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51276 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:59:47 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:59:45','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50045,'N',0,'F','N',0,57352,'X',0,0,0,'L','Y','Serial No',0,0,'N','N','N','N','N','N','Serial No','N',0,TO_TIMESTAMP('2009-05-20 18:59:45','YYYY-MM-DD HH24:MI:SS'),0,'N',51277,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:59:47 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51277 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:59:48 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:59:47','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50045,'N',0,'F','N',0,57345,'X',0,0,0,'L','Y','Sales Rep',0,0,'N','N','N','N','N','N','Sales Representative','N',0,TO_TIMESTAMP('2009-05-20 18:59:47','YYYY-MM-DD HH24:MI:SS'),0,'N',51278,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:59:48 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51278 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:59:52 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:59:48','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50045,'N',0,'F','N',0,57358,'X',0,0,0,'L','Y','Order Line',0,0,'N','N','N','N','N','N','Sales Order Line','N',0,TO_TIMESTAMP('2009-05-20 18:59:48','YYYY-MM-DD HH24:MI:SS'),0,'N',51279,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:59:52 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51279 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:59:53 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:59:52','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50045,'N',0,'F','N',0,57357,'X',0,0,0,'T','Y','Quantity Assay',0,0,'N','N','N','N','N','N','Quantity Assay','N',0,TO_TIMESTAMP('2009-05-20 18:59:52','YYYY-MM-DD HH24:MI:SS'),0,'N',51280,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:59:53 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51280 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:59:54 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:59:53','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50045,'N',0,'F','N',0,57328,'X',0,0,0,'L','Y','Active',0,0,'N','N','N','N','N','N','Active','N',0,TO_TIMESTAMP('2009-05-20 18:59:53','YYYY-MM-DD HH24:MI:SS'),0,'N',51281,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:59:54 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51281 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:59:55 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:59:54','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50045,'N',0,'F','N',0,57364,'X',0,0,0,'T','Y','Ordered Qty',0,0,'N','N','N','N','N','N','Ordered Quantity','N',0,TO_TIMESTAMP('2009-05-20 18:59:54','YYYY-MM-DD HH24:MI:SS'),0,'N',51282,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:59:55 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51282 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:59:55 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:59:55','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50045,'N',0,'F','N',0,57362,'X',0,0,0,'T','Y','Delivered Qty',0,0,'N','N','N','N','N','N','Delivered Quantity','N',0,TO_TIMESTAMP('2009-05-20 18:59:55','YYYY-MM-DD HH24:MI:SS'),0,'N',51283,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:59:55 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51283 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:59:56 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:59:55','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50045,'N',0,'F','N',0,57381,'X',0,0,0,'B','Y','Comment',0,0,'N','N','N','N','N','N','Comment/Help','N',0,TO_TIMESTAMP('2009-05-20 18:59:55','YYYY-MM-DD HH24:MI:SS'),0,'N',51284,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:59:56 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51284 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:59:57 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:59:56','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50045,'N',0,'F','N',0,57375,'X',0,0,0,'L','Y','Campaign',0,0,'N','N','N','N','N','N','Campaign','N',0,TO_TIMESTAMP('2009-05-20 18:59:56','YYYY-MM-DD HH24:MI:SS'),0,'N',51285,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:59:57 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51285 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:59:57 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:59:57','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50045,'N',0,'F','N',0,57377,'X',0,0,0,'L','Y','Activity',0,0,'N','N','N','N','N','N','Activity','N',0,TO_TIMESTAMP('2009-05-20 18:59:57','YYYY-MM-DD HH24:MI:SS'),0,'N',51286,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:59:57 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51286 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 6:59:58 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:59:57','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50045,'N',0,'F','N',0,57331,'X',0,0,0,'L','Y','Updated',0,0,'N','N','N','N','N','N','Updated','N',0,TO_TIMESTAMP('2009-05-20 18:59:57','YYYY-MM-DD HH24:MI:SS'),0,'N',51287,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 6:59:58 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51287 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 7:00:00 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 18:59:58','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50045,'N',0,'F','N',0,57327,'X',0,0,0,'L','Y','Organization',0,0,'N','N','N','N','N','N','Organization','N',0,TO_TIMESTAMP('2009-05-20 18:59:58','YYYY-MM-DD HH24:MI:SS'),0,'N',51288,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 7:00:00 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51288 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 7:00:01 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 19:00:00','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50045,'N',0,'F','N',0,57330,'X',0,0,0,'L','Y','Created By',0,0,'N','N','N','N','N','N','Created By','N',0,TO_TIMESTAMP('2009-05-20 19:00:00','YYYY-MM-DD HH24:MI:SS'),0,'N',51289,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 7:00:01 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51289 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 7:00:02 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 19:00:01','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50045,'N',0,'F','N',0,57329,'X',0,0,0,'L','Y','Created',0,0,'N','N','N','N','N','N','Created','N',0,TO_TIMESTAMP('2009-05-20 19:00:01','YYYY-MM-DD HH24:MI:SS'),0,'N',51290,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 7:00:02 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51290 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 7:00:09 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 19:00:02','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50045,'N',0,'F','N',0,57326,'X',0,0,0,'L','Y','Client',0,0,'N','N','N','N','N','N','Client','N',0,TO_TIMESTAMP('2009-05-20 19:00:02','YYYY-MM-DD HH24:MI:SS'),0,'N',51291,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 7:00:09 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51291 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 7:00:10 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 19:00:09','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50045,'N',0,'F','N',0,57384,'X',0,0,0,'L','Y','Name',0,0,'N','N','N','N','N','N','Name','N',0,TO_TIMESTAMP('2009-05-20 19:00:09','YYYY-MM-DD HH24:MI:SS'),0,'N',51292,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 7:00:10 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51292 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 7:00:35 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 19:00:11','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50045,'N',0,'F','N',0,57379,'X',0,0,0,'L','Y','BOM Use',0,0,'N','N','N','N','N','N','BOM Use','N',0,TO_TIMESTAMP('2009-05-20 19:00:11','YYYY-MM-DD HH24:MI:SS'),0,'N',51293,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 7:00:35 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51293 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 7:00:36 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 19:00:35','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50045,'N',0,'F','N',0,57378,'X',0,0,0,'L','Y','BOM Type',0,0,'N','N','N','N','N','N','BOM Type','N',0,TO_TIMESTAMP('2009-05-20 19:00:35','YYYY-MM-DD HH24:MI:SS'),0,'N',51294,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 7:00:36 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51294 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 7:00:39 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 19:00:36','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50045,'N',0,'F','N',0,57355,'X',0,0,0,'L','Y','BOM & Formula',0,0,'N','N','N','N','N','N','BOM & Formula','N',0,TO_TIMESTAMP('2009-05-20 19:00:36','YYYY-MM-DD HH24:MI:SS'),0,'N',51295,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 7:00:39 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51295 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 7:00:40 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 19:00:39','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50045,'N',0,'F','N',0,57382,'X',0,0,0,'L','Y','Attribute Set Instance',0,0,'N','N','N','N','N','N','Attribute Set Instance','N',0,TO_TIMESTAMP('2009-05-20 19:00:39','YYYY-MM-DD HH24:MI:SS'),0,'N',51296,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 7:00:40 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51296 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 7:00:42 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 19:00:40','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50045,'N',0,'F','N',0,57385,'X',0,0,0,'L','Y','Revision',0,0,'N','N','N','N','N','N','Revision','N',0,TO_TIMESTAMP('2009-05-20 19:00:40','YYYY-MM-DD HH24:MI:SS'),0,'N',51297,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 7:00:42 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51297 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 7:00:43 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 19:00:42','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50045,'N',0,'F','N',0,57354,'X',0,0,0,'L','Y','Resource',0,0,'N','N','N','N','N','N','Resource','N',0,TO_TIMESTAMP('2009-05-20 19:00:42','YYYY-MM-DD HH24:MI:SS'),0,'N',51298,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 7:00:43 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51298 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 7:00:44 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 19:00:43','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50045,'N',0,'F','N',0,57341,'X',0,0,0,'L','Y','Warehouse Address',0,0,'N','N','N','N','N','N','Warehouse Address','N',0,TO_TIMESTAMP('2009-05-20 19:00:43','YYYY-MM-DD HH24:MI:SS'),0,'N',51299,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 7:00:44 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51299 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 7:00:46 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 19:00:44','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50045,'N',0,'F','N',0,57387,'X',0,0,0,'L','Y','Valid to',0,0,'N','N','N','N','N','N','Valid to','N',0,TO_TIMESTAMP('2009-05-20 19:00:44','YYYY-MM-DD HH24:MI:SS'),0,'N',51300,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 7:00:46 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51300 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 7:00:48 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 19:00:46','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50045,'N',0,'F','N',0,57386,'X',0,0,0,'L','Y','Valid from',0,0,'N','N','N','N','N','N','Valid from','N',0,TO_TIMESTAMP('2009-05-20 19:00:46','YYYY-MM-DD HH24:MI:SS'),0,'N',51301,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 7:00:48 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51301 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 7:00:52 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 19:00:48','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50045,'N',0,'F','N',0,57376,'X',0,0,0,'L','Y','Project',0,0,'N','N','N','N','N','N','Project','N',0,TO_TIMESTAMP('2009-05-20 19:00:48','YYYY-MM-DD HH24:MI:SS'),0,'N',51302,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 7:00:52 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51302 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 7:00:54 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 19:00:52','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50045,'N',0,'F','N',0,57359,'X',0,0,0,'L','Y','Priority',0,0,'N','N','N','N','N','N','Priority','N',0,TO_TIMESTAMP('2009-05-20 19:00:52','YYYY-MM-DD HH24:MI:SS'),0,'N',51303,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 7:00:54 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51303 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 7:00:56 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 19:00:54','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50045,'N',0,'F','N',0,57334,'X',0,0,0,'L','Y','Manufacturing Order',0,0,'N','N','N','N','N','N','Manufacturing Order','N',0,TO_TIMESTAMP('2009-05-20 19:00:54','YYYY-MM-DD HH24:MI:SS'),0,'N',51304,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 7:00:56 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51304 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 7:00:57 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 19:00:56','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50045,'N',0,'F','N',0,57350,'X',0,0,0,'T','Y','Line No',0,0,'N','N','N','N','N','N','Line No','N',0,TO_TIMESTAMP('2009-05-20 19:00:56','YYYY-MM-DD HH24:MI:SS'),0,'N',51305,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 7:00:57 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51305 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 7:00:59 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 19:00:57','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50045,'N',0,'F','N',0,57333,'X',0,0,0,'B','Y','Language',0,0,'N','N','N','N','N','N','Language','N',0,TO_TIMESTAMP('2009-05-20 19:00:57','YYYY-MM-DD HH24:MI:SS'),0,'N',51306,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 7:00:59 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51306 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 7:01:04 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 19:00:59','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50045,'N',0,'F','N',0,57349,'X',0,0,0,'T','Y','Float Befored',0,0,'N','N','N','N','N','N','Float Befored','N',0,TO_TIMESTAMP('2009-05-20 19:00:59','YYYY-MM-DD HH24:MI:SS'),0,'N',51307,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 7:01:04 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51307 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 7:01:08 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 19:01:04','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50045,'N',0,'F','N',0,57348,'X',0,0,0,'T','Y','Float After',0,0,'N','N','N','N','N','N','Float After','N',0,TO_TIMESTAMP('2009-05-20 19:01:04','YYYY-MM-DD HH24:MI:SS'),0,'N',51308,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 7:01:09 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51308 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 7:01:15 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 19:01:09','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50045,'N',0,'F','N',0,57343,'X',0,0,0,'B','Y','Document Type Note',0,0,'N','N','N','N','N','N','Document Type Note','N',0,TO_TIMESTAMP('2009-05-20 19:01:09','YYYY-MM-DD HH24:MI:SS'),0,'N',51309,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 7:01:15 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51309 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 7:01:19 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 19:01:15','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50045,'N',0,'F','N',0,57342,'X',0,0,0,'L','Y','Document Type',0,0,'N','N','N','N','N','N','Document Type','N',0,TO_TIMESTAMP('2009-05-20 19:01:15','YYYY-MM-DD HH24:MI:SS'),0,'N',51310,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 7:01:19 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51310 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 7:01:20 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsOrderBy='N', ImageURL=NULL, IsCounted='N', IsPrinted='N', IsHeightOneLine='N', PrintAreaType='C', AD_PrintFormat_ID=50045, IsGroupBy='N', MaxHeight=0, PrintFormatType='F', ImageIsAttached='N', SeqNo=0, AD_Column_ID=57337, LineAlignmentType='X', XSpace=0, FieldAlignmentType='L', IsRelativePosition='Y', PrintName='Doc Type', YPosition=0, IsSummarized='N', IsSuppressNull='N', IsPageBreak='N', IsFixedWidth='N', IsNextLine='N', IsMinCalc='N', Name='Document Type', IsAveraged='N', BelowColumn=0, YSpace=0, IsSetNLPosition='N', IsActive='Y', MaxWidth=0, IsNextPage='N', RunningTotalLines=0, PrintNameSuffix=NULL, IsMaxCalc='N', IsRunningTotal='N', IsVarianceCalc='N', IsDeviationCalc='N', ArcDiameter=0, ShapeType='N', LineWidth=1, BarcodeType=NULL,Updated=TO_TIMESTAMP('2009-05-20 19:01:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=51310
;

-- May 20, 2009 7:01:20 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=51310
;

-- May 20, 2009 7:01:25 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 19:01:20','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50045,'N',0,'F','N',0,57380,'X',0,0,0,'L','Y','Description',0,0,'N','N','N','N','N','N','Description','N',0,TO_TIMESTAMP('2009-05-20 19:01:20','YYYY-MM-DD HH24:MI:SS'),0,'N',51311,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 7:01:25 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51311 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 7:01:31 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 19:01:25','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50045,'N',0,'F','N',0,57332,'X',0,0,0,'L','Y','Updated By',0,0,'N','N','N','N','N','N','Updated By','N',0,TO_TIMESTAMP('2009-05-20 19:01:25','YYYY-MM-DD HH24:MI:SS'),0,'N',51312,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 7:01:31 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51312 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 7:01:47 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 19:01:31','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50045,'N',0,'F','N',0,57374,'X',0,0,0,'T','Y','Yield %',0,0,'N','N','N','N','N','N','Yield %','N',0,TO_TIMESTAMP('2009-05-20 19:01:31','YYYY-MM-DD HH24:MI:SS'),0,'N',51313,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 7:01:47 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51313 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 7:01:52 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 19:01:47','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','N','N','C',50045,'N',0,'F','N',0,57356,'X',0,0,0,'L','Y','Workflow',0,0,'N','N','N','N','N','N','Workflow','N',0,TO_TIMESTAMP('2009-05-20 19:01:47','YYYY-MM-DD HH24:MI:SS'),0,'N',51314,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 7:01:52 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51314 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 7:01:57 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,AD_PrintColor_ID,PrintFormatType,ImageIsAttached,SeqNo,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,AD_PrintFont_ID,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 19:01:52','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','Y','Y','H',50045,'N',0,100,'T','N',10,'C',0,0,0,'C','Y','G A R D E N  W O R L D',0,117,0,'N','N','N','N','Y','N','G A R D E N  W O R L D','N',0,TO_TIMESTAMP('2009-05-20 19:01:52','YYYY-MM-DD HH24:MI:SS'),20,'Y',51315,'Y',0,'N',20,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 7:01:57 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51315 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 7:02:02 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 19:01:57','YYYY-MM-DD HH24:MI:SS'),0,10,5,'Y','N','Y','N','H',50045,'N',0,'F','N',20,57335,'X',0,0,0,'L','N','M Order No:',40,0,'N','N','N','N','N','N','Document No','N',0,TO_TIMESTAMP('2009-05-20 19:01:57','YYYY-MM-DD HH24:MI:SS'),0,'N',51316,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 7:02:02 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51316 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 7:02:08 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 19:02:02','YYYY-MM-DD HH24:MI:SS'),0,0,200,'N','N','Y','N','H',50045,'N',0,'F','N',30,57344,'X',0,0,0,'L','N','Planner:',40,0,'N','N','N','N','N','N','Planner','N',0,TO_TIMESTAMP('2009-05-20 19:02:02','YYYY-MM-DD HH24:MI:SS'),0,'N',51317,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 7:02:08 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51317 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 7:02:13 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 19:02:08','YYYY-MM-DD HH24:MI:SS'),0,0,5,'N','N','Y','N','H',50045,'N',0,'F','N',40,57336,'X',0,0,0,'L','N','Doc Status:',50,0,'N','N','N','N','Y','N','Document Status','N',0,TO_TIMESTAMP('2009-05-20 19:02:08','YYYY-MM-DD HH24:MI:SS'),0,'N',51318,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 7:02:13 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51318 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 7:02:19 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 19:02:13','YYYY-MM-DD HH24:MI:SS'),0,0,200,'N','N','Y','N','H',50045,'N',0,'F','N',50,57340,'X',0,0,0,'L','N','Warehouse:',50,0,'N','N','N','N','N','N','Warehouse','N',0,TO_TIMESTAMP('2009-05-20 19:02:13','YYYY-MM-DD HH24:MI:SS'),0,'N',51319,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 7:02:19 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51319 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 7:02:24 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 19:02:19','YYYY-MM-DD HH24:MI:SS'),0,0,5,'N','N','Y','N','H',50045,'N',0,'F','N',60,57383,'X',0,0,0,'L','N','Product:',70,0,'N','N','N','N','N','N','Product','N',0,TO_TIMESTAMP('2009-05-20 19:02:19','YYYY-MM-DD HH24:MI:SS'),0,'N',51320,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 7:02:24 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51320 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 7:02:29 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 19:02:24','YYYY-MM-DD HH24:MI:SS'),0,0,350,'N','N','Y','N','H',50045,'N',0,'F','N',70,57372,'X',0,0,0,'L','N','Reserved:',70,0,'N','N','N','N','N','N','Reserved Quantity','N',0,TO_TIMESTAMP('2009-05-20 19:02:24','YYYY-MM-DD HH24:MI:SS'),0,'N',51321,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 7:02:29 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51321 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 7:02:34 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 19:02:29','YYYY-MM-DD HH24:MI:SS'),0,0,450,'N','N','Y','N','H',50045,'N',0,'F','N',80,57351,'X',0,0,0,'L','N','Lot No:',70,0,'N','N','N','N','N','N','Lot No','N',0,TO_TIMESTAMP('2009-05-20 19:02:29','YYYY-MM-DD HH24:MI:SS'),0,'N',51322,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 7:02:34 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51322 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 7:02:34 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 19:02:34','YYYY-MM-DD HH24:MI:SS'),0,0,5,'N','N','Y','N','H',50045,'N',0,'F','N',90,57363,'X',0,0,0,'T','N','Quantity:',80,0,'N','N','N','N','N','N','Quantity','N',0,TO_TIMESTAMP('2009-05-20 19:02:34','YYYY-MM-DD HH24:MI:SS'),0,'N',51323,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 7:02:34 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51323 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 7:02:35 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 19:02:34','YYYY-MM-DD HH24:MI:SS'),0,0,150,'N','N','Y','N','H',50045,'N',0,'F','N',100,57371,'X',0,0,0,'T','N','Qty Reject:',80,0,'N','N','N','N','N','N','Qty Reject','N',0,TO_TIMESTAMP('2009-05-20 19:02:34','YYYY-MM-DD HH24:MI:SS'),0,'N',51324,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 7:02:35 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51324 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 7:02:36 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 19:02:35','YYYY-MM-DD HH24:MI:SS'),0,0,250,'N','N','Y','N','H',50045,'N',0,'F','N',110,57373,'X',0,0,0,'T','N','% Scrap:',80,0,'N','N','N','N','N','N','QtyScrap','N',0,TO_TIMESTAMP('2009-05-20 19:02:35','YYYY-MM-DD HH24:MI:SS'),0,'N',51325,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 7:02:36 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51325 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 7:02:37 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 19:02:36','YYYY-MM-DD HH24:MI:SS'),0,0,350,'N','N','Y','N','H',50045,'N',0,'F','N',120,57361,'X',0,0,0,'T','N','# Batch:',80,0,'N','N','N','N','N','N','Qty Batchs','N',0,TO_TIMESTAMP('2009-05-20 19:02:36','YYYY-MM-DD HH24:MI:SS'),0,'N',51326,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 7:02:37 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51326 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 7:02:37 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 19:02:37','YYYY-MM-DD HH24:MI:SS'),0,0,450,'N','N','Y','N','H',50045,'N',0,'F','N',130,57360,'X',0,0,0,'D','N','Qty Batch Size:',80,0,'N','N','N','N','N','N','Qty Batch Size','N',0,TO_TIMESTAMP('2009-05-20 19:02:37','YYYY-MM-DD HH24:MI:SS'),0,'N',51327,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 7:02:37 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51327 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 7:02:39 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 19:02:37','YYYY-MM-DD HH24:MI:SS'),0,0,5,'N','N','Y','N','H',50045,'N',0,'F','N',140,57369,'X',0,0,0,'L','N','Date:',100,0,'N','N','N','N','N','N','Date Ordered','N',0,TO_TIMESTAMP('2009-05-20 19:02:37','YYYY-MM-DD HH24:MI:SS'),0,'N',51328,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 7:02:39 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51328 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 7:02:39 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 19:02:39','YYYY-MM-DD HH24:MI:SS'),0,0,330,'N','N','Y','N','H',50045,'N',0,'F','N',150,57370,'X',0,0,0,'L','N','Promised:',100,0,'N','N','N','N','N','N','Date Promised','N',0,TO_TIMESTAMP('2009-05-20 19:02:39','YYYY-MM-DD HH24:MI:SS'),0,'N',51329,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 7:02:39 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51329 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 7:02:40 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 19:02:39','YYYY-MM-DD HH24:MI:SS'),0,0,5,'N','N','Y','N','H',50045,'N',0,'F','N',160,57346,'X',0,0,0,'L','N','Date Start:',110,0,'N','N','N','N','N','N','DateStart','N',0,TO_TIMESTAMP('2009-05-20 19:02:39','YYYY-MM-DD HH24:MI:SS'),0,'N',51330,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 7:02:40 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51330 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 7:02:41 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 19:02:40','YYYY-MM-DD HH24:MI:SS'),0,0,330,'N','N','Y','N','H',50045,'N',0,'F','N',170,57347,'X',0,0,0,'L','N','DateStartSchedule:',110,0,'N','N','N','N','N','N','DateStartSchedule','N',0,TO_TIMESTAMP('2009-05-20 19:02:40','YYYY-MM-DD HH24:MI:SS'),0,'N',51331,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 7:02:41 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51331 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 7:02:42 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 19:02:41','YYYY-MM-DD HH24:MI:SS'),0,0,5,'N','N','Y','N','H',50045,'N',0,'F','N',180,57367,'X',0,0,0,'L','N','Finish Date:',120,0,'N','N','N','N','N','N','Finish Date','N',0,TO_TIMESTAMP('2009-05-20 19:02:41','YYYY-MM-DD HH24:MI:SS'),0,'N',51332,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 7:02:42 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51332 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 7:02:44 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 19:02:42','YYYY-MM-DD HH24:MI:SS'),0,0,330,'N','N','Y','N','H',50045,'N',0,'F','N',190,57368,'X',0,0,0,'L','N','Finish D. Schedule:',120,0,'N','N','N','N','N','N','DateFinishSchedule','N',0,TO_TIMESTAMP('2009-05-20 19:02:42','YYYY-MM-DD HH24:MI:SS'),0,'N',51333,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 7:02:44 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51333 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 7:02:45 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 19:02:44','YYYY-MM-DD HH24:MI:SS'),0,0,5,'N','N','Y','N','H',50045,'N',0,'F','N',200,57365,'X',0,0,0,'L','N','DateConfirm:',130,0,'N','N','N','N','N','N','DateConfirm','N',0,TO_TIMESTAMP('2009-05-20 19:02:44','YYYY-MM-DD HH24:MI:SS'),0,'N',51334,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 7:02:45 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51334 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 7:02:46 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 19:02:45','YYYY-MM-DD HH24:MI:SS'),0,0,330,'N','N','Y','N','H',50045,'N',0,'F','N',210,57366,'X',0,0,0,'L','N','Date Delivered:',130,0,'N','N','N','N','N','N','Date Delivered','N',0,TO_TIMESTAMP('2009-05-20 19:02:45','YYYY-MM-DD HH24:MI:SS'),0,'N',51335,'Y',0,'N',0,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 7:02:46 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51335 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 7:02:46 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,AD_PrintFormatChild_ID,IsGroupBy,MaxHeight,AD_PrintColor_ID,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,YPosition,AD_PrintFont_ID,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,LineWidth) VALUES (TO_TIMESTAMP('2009-05-20 19:02:46','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','Y','Y','C',50045,50044,'N',0,100,'P','N',220,57334,'X',0,0,0,'D','Y',0,130,0,'N','N','N','N','Y','N','Manufacturing Order BOM Line','N',0,TO_TIMESTAMP('2009-05-20 19:02:46','YYYY-MM-DD HH24:MI:SS'),160,'Y',51336,'Y',0,'N',20,'N','N','N','N',0,'N',1)
;

-- May 20, 2009 7:02:46 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51336 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- May 20, 2009 7:17:25 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_ReportView (AD_Client_ID,AD_Org_ID,AD_ReportView_ID,AD_Table_ID,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy,WhereClause) VALUES (0,0,53028,53198,TO_TIMESTAMP('2009-05-20 19:17:24','YYYY-MM-DD HH24:MI:SS'),0,'EE01','Y','PP_Order_BOMLine Components',TO_TIMESTAMP('2009-05-20 19:17:24','YYYY-MM-DD HH24:MI:SS'),0,'ComponentType NOT IN(''PK'',''TL'')')
;

-- May 20, 2009 7:17:45 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormat SET AD_ReportView_ID=53028,Updated=TO_TIMESTAMP('2009-05-20 19:17:45','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormat_ID=50040
;

-- May 20, 2009 7:23:02 PM ECT
-- Manufacturing Print Format
UPDATE AD_Column SET AD_Reference_ID=18, AD_Reference_Value_ID=286,Updated=TO_TIMESTAMP('2009-05-20 19:23:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=57344
;

-- May 20, 2009 7:32:41 PM ECT
-- Manufacturing Print Format
UPDATE AD_Column SET AD_Reference_ID=18, AD_Reference_Value_ID=286,Updated=TO_TIMESTAMP('2009-05-20 19:32:41','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=57446
;

-- May 20, 2009 7:57:17 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormat SET Name='Manufacturing Order Activity Product ** TEMPLATE **',Updated=TO_TIMESTAMP('2009-05-20 19:57:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormat_ID=50043
;

-- May 20, 2009 7:57:48 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormat SET Name='Manufacturing_Order_Node_Product ** TEMPLATE **',Updated=TO_TIMESTAMP('2009-05-20 19:57:48','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormat_ID=50043
;

-- May 20, 2009 7:58:31 PM ECT
-- Manufacturing Print Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsGroupBy='N', YPosition=0, IsPageBreak='N', Name='Manufacturing_Order_Node_Product ** TEMPLATE **',Updated=TO_TIMESTAMP('2009-05-20 19:58:31','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=51247
;

-- May 20, 2009 8:04:33 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57648,53285,0,19,53200,'PP_Order_Node_ID',TO_TIMESTAMP('2009-05-20 20:04:32','YYYY-MM-DD HH24:MI:SS'),0,'EE01',10,'Y','N','N','N','N','N','N','N','N','Y','Manufacturing Order Activity',TO_TIMESTAMP('2009-05-20 20:04:32','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- May 20, 2009 8:04:33 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57648 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- May 20, 2009 8:06:07 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,AD_PrintFormatChild_ID,IsGroupBy,MaxHeight,AD_PrintColor_ID,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,YPosition,AD_PrintFont_ID,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsCentrallyMaintained,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,IsFilledRectangle,LineWidth,IsImageField,IsSuppressRepeats) VALUES (TO_TIMESTAMP('2009-05-20 20:06:06','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','Y','Y','C',50037,50043,'N',0,100,'P','N',180,57648,'X',0,0,0,'D','Y',0,130,0,'N','N','N','N','Y','N','PP_Order_Node_ID','N',0,TO_TIMESTAMP('2009-05-20 20:06:06','YYYY-MM-DD HH24:MI:SS'),0,'N',51337,'Y',0,'N',20,'N','N','N','N','N',0,'N','N',1,'N','N')
;

-- May 20, 2009 8:06:07 PM ECT
-- Manufacturing Print Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51337 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

