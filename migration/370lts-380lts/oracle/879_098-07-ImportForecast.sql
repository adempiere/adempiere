SET DEFINE OFF
SET SQLBLANKLINES ON
SET SCAN OFF

-- Aug 3, 2012 8:46:38 AM CDT
-- ADEMPIERE-98
UPDATE AD_Column SET IsMandatory='N',Updated=TO_DATE('2012-08-03 08:46:38','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=11912
;

-- Aug 3, 2012 8:46:39 AM CDT
-- ADEMPIERE-98
ALTER TABLE M_Forecast MODIFY C_Calendar_ID NUMBER(10) DEFAULT NULL 
;

-- Aug 3, 2012 8:46:40 AM CDT
-- ADEMPIERE-98
ALTER TABLE M_Forecast MODIFY C_Calendar_ID NULL
;

-- Aug 3, 2012 8:46:50 AM CDT
-- ADEMPIERE-98
UPDATE AD_Column SET IsMandatory='N',Updated=TO_DATE('2012-08-03 08:46:50','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=11909
;

-- Aug 3, 2012 8:46:50 AM CDT
-- ADEMPIERE-98
ALTER TABLE M_Forecast MODIFY C_Year_ID NUMBER(10) DEFAULT NULL 
;

-- Aug 3, 2012 8:46:50 AM CDT
-- ADEMPIERE-98
ALTER TABLE M_Forecast MODIFY C_Year_ID NULL
;

-- Aug 3, 2012 8:47:24 AM CDT
-- ADEMPIERE-98
UPDATE AD_Column SET IsMandatory='N',Updated=TO_DATE('2012-08-03 08:47:24','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=11934
;

-- Aug 3, 2012 8:47:27 AM CDT
-- ADEMPIERE-98
ALTER TABLE M_ForecastLine MODIFY C_Period_ID NUMBER(10) DEFAULT NULL 
;

-- Aug 3, 2012 8:47:27 AM CDT
-- ADEMPIERE-98
ALTER TABLE M_ForecastLine MODIFY C_Period_ID NULL
;

-- Aug 3, 2012 8:52:03 AM CDT
-- ADEMPIERE-98
UPDATE AD_Column SET AD_Process_ID=53299,Updated=TO_DATE('2012-08-03 08:52:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=11916
;

-- Aug 3, 2012 8:55:13 AM CDT
-- ADEMPIERE-98
UPDATE AD_Window SET Name='Forecast Simulation',Updated=TO_DATE('2012-08-03 08:55:13','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Window_ID=53183
;

-- Aug 3, 2012 8:55:13 AM CDT
-- ADEMPIERE-98
UPDATE AD_Window_Trl SET IsTranslated='N' WHERE AD_Window_ID=53183
;

-- Aug 3, 2012 8:55:13 AM CDT
-- ADEMPIERE-98
UPDATE AD_Menu SET Description='Process to Calculate Forecast simulation', IsActive='Y', Name='Forecast Simulation',Updated=TO_DATE('2012-08-03 08:55:13','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Menu_ID=53423
;

-- Aug 3, 2012 8:55:13 AM CDT
-- ADEMPIERE-98
UPDATE AD_Menu_Trl SET IsTranslated='N' WHERE AD_Menu_ID=53423
;

-- Aug 3, 2012 8:55:53 AM CDT
-- ADEMPIERE-98
UPDATE AD_Tab SET Name='Forecast Simulation',Updated=TO_DATE('2012-08-03 08:55:53','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Tab_ID=53521
;

-- Aug 3, 2012 8:55:53 AM CDT
-- ADEMPIERE-98
UPDATE AD_Tab_Trl SET IsTranslated='N' WHERE AD_Tab_ID=53521
;

-- Aug 3, 2012 8:57:04 AM CDT
-- ADEMPIERE-98
UPDATE AD_Field SET DisplayLogic='@Processed@=''N''',Updated=TO_DATE('2012-08-03 08:57:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=64513
;

-- Aug 3, 2012 9:12:27 AM CDT
-- ADEMPIERE-98
INSERT INTO AD_Val_Rule (AD_Client_ID,AD_Org_ID,AD_Val_Rule_ID,Created,CreatedBy,Description,EntityType,IsActive,Name,Type,Updated,UpdatedBy) VALUES (0,0,52131,TO_DATE('2012-08-03 09:12:26','YYYY-MM-DD HH24:MI:SS'),100,'Forecast Simulation unprocessed','EE01','Y','PP_ForecastRun Unprocessed ','S',TO_DATE('2012-08-03 09:12:26','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 3, 2012 9:13:34 AM CDT
-- ADEMPIERE-98
UPDATE AD_Val_Rule SET Code='Processed=''N''', Name='PP_ForecastRun Unprocessed',Updated=TO_DATE('2012-08-03 09:13:34','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Val_Rule_ID=52131
;

-- Aug 3, 2012 9:13:46 AM CDT
-- ADEMPIERE-98
UPDATE AD_Process_Para SET AD_Val_Rule_ID=52131,Updated=TO_DATE('2012-08-03 09:13:46','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=53688
;

-- Aug 3, 2012 9:19:46 AM CDT
-- ADEMPIERE-98
UPDATE AD_Column SET AD_Val_Rule_ID=52122,Updated=TO_DATE('2012-08-03 09:19:46','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=63367
;

-- Aug 3, 2012 9:20:25 AM CDT
-- ADEMPIERE-98
UPDATE AD_Column SET AD_Val_Rule_ID=52122,Updated=TO_DATE('2012-08-03 09:20:25','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=63360
;

-- Aug 3, 2012 9:21:34 AM CDT
-- ADEMPIERE-98
UPDATE AD_Process_Para SET AD_Reference_ID=30,Updated=TO_DATE('2012-08-03 09:21:34','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=53705
;

-- Aug 3, 2012 9:23:19 AM CDT
-- ADEMPIERE-98
UPDATE AD_Process_Para SET AD_Reference_ID=30,Updated=TO_DATE('2012-08-03 09:23:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=53189
;

-- Aug 3, 2012 9:23:40 AM CDT
-- ADEMPIERE-98
UPDATE AD_Process_Para SET AD_Reference_ID=30,Updated=TO_DATE('2012-08-03 09:23:40','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=53195
;

-- Aug 3, 2012 9:24:18 AM CDT
-- ADEMPIERE-98
UPDATE AD_Column SET AD_Reference_ID=40,Updated=TO_DATE('2012-08-03 09:24:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=63463
;

-- Aug 3, 2012 9:24:30 AM CDT
-- ADEMPIERE-98
UPDATE AD_Column SET AD_Reference_ID=30,Updated=TO_DATE('2012-08-03 09:24:30','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=63463
;

-- Aug 3, 2012 9:32:39 AM CDT
-- ADEMPIERE-98
UPDATE AD_Column SET IsUpdateable='N',Updated=TO_DATE('2012-08-03 09:32:39','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=63281
;

-- Aug 3, 2012 9:32:48 AM CDT
-- ADEMPIERE-98
UPDATE AD_Column SET IsUpdateable='N',Updated=TO_DATE('2012-08-03 09:32:48','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=63304
;

-- Aug 3, 2012 9:32:54 AM CDT
-- ADEMPIERE-98
UPDATE AD_Column SET IsUpdateable='N',Updated=TO_DATE('2012-08-03 09:32:54','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=63291
;

-- Aug 3, 2012 9:37:27 AM CDT
-- ADEMPIERE-98
UPDATE AD_Field SET DisplayLength=15,Updated=TO_DATE('2012-08-03 09:37:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=64575
;

-- Aug 3, 2012 9:38:59 AM CDT
-- ADEMPIERE-98
UPDATE AD_Tab SET Name='Forecast Simulation Detail',Updated=TO_DATE('2012-08-03 09:38:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Tab_ID=53523
;

-- Aug 3, 2012 9:38:59 AM CDT
-- ADEMPIERE-98
UPDATE AD_Tab_Trl SET IsTranslated='N' WHERE AD_Tab_ID=53523
;

-- Aug 3, 2012 9:43:25 AM CDT
-- ADEMPIERE-98
UPDATE AD_Column SET IsUpdateable='N',Updated=TO_DATE('2012-08-03 09:43:25','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=63476
;

-- Aug 3, 2012 9:43:43 AM CDT
-- ADEMPIERE-98
DELETE  FROM  AD_Column_Trl WHERE AD_Column_ID=63483
;

-- Aug 3, 2012 9:43:43 AM CDT
-- ADEMPIERE-98
DELETE FROM AD_Column WHERE AD_Column_ID=63483
;

-- Aug 3, 2012 9:43:58 AM CDT
-- ADEMPIERE-98
UPDATE AD_Column SET AD_Reference_ID=30,Updated=TO_DATE('2012-08-03 09:43:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=63659
;

-- Aug 3, 2012 9:50:14 AM CDT
-- ADEMPIERE-98
UPDATE AD_Column SET AD_Reference_ID=30,Updated=TO_DATE('2012-08-03 09:50:14','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=63380
;

-- Aug 3, 2012 9:58:31 AM CDT
-- ADEMPIERE-98
INSERT INTO PP_ForecastRule (AD_Client_ID,AD_Org_ID,CalculationClass,Created,CreatedBy,IsActive,Name,PP_ForecastRule_ID,Updated,UpdatedBy) VALUES (0,0,'MovingAverage',TO_DATE('2012-08-03 09:58:28','YYYY-MM-DD HH24:MI:SS'),100,'Y','Moving Average',50008,TO_DATE('2012-08-03 09:58:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 3, 2012 9:59:17 AM CDT
-- ADEMPIERE-98
UPDATE PP_ForecastRule SET Description='Moving Average Model',Updated=TO_DATE('2012-08-03 09:59:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_ForecastRule_ID=50008
;

-- Aug 3, 2012 10:11:52 AM CDT
-- ADEMPIERE-98
UPDATE PP_ForecastRule SET Help='    <p>A moving average forecast model is based on an artificially constructed time series in which the value for a given time period is replaced by the mean of that value and the values for some number of preceding and succeeding time periods. As you may have guessed from the description, this model is best suited to time-series data; i.e. data that changes over time.</p><p>For example, many charts of individual stocks on the stock market show 20, 50, 100 or 200 day moving averages as a way to show trends.</p><p>Since the forecast value for any given period is an average of the previous periods, then the forecast will always appear to &quot;lag&quot; behind either increases or decreases in the observed (dependent) values.</p><p>For example, if a data series has a noticeable upward trend then a moving average forecast will generally provide an underestimate of the values of the dependent variable.</p><p>The moving average method has an advantage over other forecasting models in that it does smooth out peaks and troughs (or valleys) in a set of observations. However, it also has several disadvantages. </p><p>In particular this model does not produce an actual equation. Therefore, it is not all that useful as a medium-long range forecasting tool. It can only reliably be used to forecast one or two periods into the future.</p><p>The moving average model is a special case of the more general weighted moving average. In the simple moving average, all weights are equal.</p> <p>Author Steven R. Gould</p>',Updated=TO_DATE('2012-08-03 10:11:52','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_ForecastRule_ID=50008
;

-- Aug 3, 2012 10:00:11 AM CDT
-- ADEMPIERE-98
UPDATE PP_ForecastRule SET UpdatedBy=100,Updated=TO_DATE('2012-08-03 10:00:11','YYYY-MM-DD HH24:MI:SS') WHERE PP_ForecastRule_ID=50001
;

-- Aug 3, 2012 10:00:53 AM CDT
-- ADEMPIERE-98
UPDATE PP_ForecastRule SET UpdatedBy=100,Updated=TO_DATE('2012-08-03 10:00:53','YYYY-MM-DD HH24:MI:SS') WHERE PP_ForecastRule_ID=50008
;

-- Aug 3, 2012 11:22:41 AM CDT
-- ADEMPIERE-98
UPDATE AD_Column SET IsSelectionColumn='Y',Updated=TO_DATE('2012-08-03 11:22:41','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=63326
;

-- Aug 3, 2012 11:22:49 AM CDT
-- ADEMPIERE-98
UPDATE AD_Column SET IsSelectionColumn='Y',Updated=TO_DATE('2012-08-03 11:22:49','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=63346
;

-- Aug 3, 2012 11:22:56 AM CDT
-- ADEMPIERE-98
UPDATE AD_Column SET IsSelectionColumn='Y',Updated=TO_DATE('2012-08-03 11:22:56','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=63325
;

-- Aug 3, 2012 11:23:04 AM CDT
-- ADEMPIERE-98
UPDATE AD_Column SET IsSelectionColumn='Y',Updated=TO_DATE('2012-08-03 11:23:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=63329
;

-- Aug 3, 2012 11:23:15 AM CDT
-- ADEMPIERE-98
UPDATE AD_Column SET IsSelectionColumn='Y',Updated=TO_DATE('2012-08-03 11:23:15','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=63328
;

-- Aug 3, 2012 11:23:21 AM CDT
-- ADEMPIERE-98
UPDATE AD_Column SET IsIdentifier='Y',Updated=TO_DATE('2012-08-03 11:23:21','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=63345
;

-- Aug 3, 2012 11:24:54 AM CDT
-- ADEMPIERE-98
UPDATE AD_Column SET IsSelectionColumn='N',Updated=TO_DATE('2012-08-03 11:24:54','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=63328
;

-- Aug 3, 2012 11:25:33 AM CDT
-- ADEMPIERE-98
UPDATE AD_Column SET IsSelectionColumn='Y',Updated=TO_DATE('2012-08-03 11:25:33','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=63328
;

-- Aug 3, 2012 11:28:10 AM CDT
-- ADEMPIERE-98
UPDATE AD_Column SET AD_Val_Rule_ID=189,Updated=TO_DATE('2012-08-03 11:28:10','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=63368
;

-- Aug 3, 2012 11:29:29 AM CDT
-- ADEMPIERE-98
UPDATE AD_Process_Para SET SeqNo=5,Updated=TO_DATE('2012-08-03 11:29:29','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=53688
;

-- Aug 3, 2012 11:30:41 AM CDT
-- ADEMPIERE-98
UPDATE AD_Column SET IsAlwaysUpdateable='Y',Updated=TO_DATE('2012-08-03 11:30:41','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=11913
;

-- Aug 7, 2012 9:50:32 AM CDT
-- ADEMPIERE-98
INSERT INTO AD_Window (AD_Client_ID,AD_Org_ID,AD_Window_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsBetaFunctionality,IsDefault,IsSOTrx,Name,Processing,Updated,UpdatedBy,WindowType) VALUES (0,0,53190,TO_DATE('2012-08-07 09:50:26','YYYY-MM-DD HH24:MI:SS'),100,'This process allows to import the forecast by using a predefined import format,  inside the application.','EE01','The parameters are the default values to import the records.<br><br>The maintained information inside the forecast are: the Forecast ID , product , warehouse ,  quantities, promised data<br><br>The forecast information is used to MRP calculus','Y','N','N','N','Import Forecast','N',TO_DATE('2012-08-07 09:50:26','YYYY-MM-DD HH24:MI:SS'),100,'M')
;

-- Aug 7, 2012 9:50:32 AM CDT
-- ADEMPIERE-98
INSERT INTO AD_Window_Trl (AD_Language,AD_Window_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Window_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Window t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Window_ID=53190 AND NOT EXISTS (SELECT * FROM AD_Window_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Window_ID=t.AD_Window_ID)
;

-- Aug 7, 2012 9:50:37 AM CDT
-- ADEMPIERE-98
INSERT INTO AD_Table (AD_Client_ID,AD_Org_ID,AD_Table_ID,AccessLevel,Created,CreatedBy,Description,EntityType,IsActive,IsChangeLog,IsDeleteable,IsHighVolume,IsSecurityEnabled,IsView,Name,ReplicationType,TableName,Updated,UpdatedBy) VALUES (0,0,53414,'2',TO_DATE('2012-08-07 09:50:32','YYYY-MM-DD HH24:MI:SS'),100,'Import Forecast','EE01','Y','N','Y','N','N','N','Import Forecast','L','I_Forecast',TO_DATE('2012-08-07 09:50:32','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 7, 2012 9:50:37 AM CDT
-- ADEMPIERE-98
INSERT INTO AD_Table_Trl (AD_Language,AD_Table_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Table_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Table t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Table_ID=53414 AND NOT EXISTS (SELECT * FROM AD_Table_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Table_ID=t.AD_Table_ID)
;

-- Aug 7, 2012 9:50:39 AM CDT
-- ADEMPIERE-98
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63811,102,0,19,53414,129,'AD_Client_ID',TO_DATE('2012-08-07 09:50:37','YYYY-MM-DD HH24:MI:SS'),100,'@#AD_Client_ID@','Client/Tenant for this installation.','EE01',22,'A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','N','N','N','N','Y','N','N','N','Y','Client',TO_DATE('2012-08-07 09:50:37','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- Aug 7, 2012 9:50:39 AM CDT
-- ADEMPIERE-98
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63811 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 7, 2012 9:50:40 AM CDT
-- ADEMPIERE-98
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63812,113,0,19,53414,104,'AD_Org_ID',TO_DATE('2012-08-07 09:50:39','YYYY-MM-DD HH24:MI:SS'),100,'@#AD_Org_ID@','Organizational entity within client','EE01',22,'An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','N','N','N','N','Y','N','N','N','Y','Organization',TO_DATE('2012-08-07 09:50:39','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- Aug 7, 2012 9:50:40 AM CDT
-- ADEMPIERE-98
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63812 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 7, 2012 9:50:45 AM CDT
-- ADEMPIERE-98
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63813,348,0,20,53414,'IsActive',TO_DATE('2012-08-07 09:50:40','YYYY-MM-DD HH24:MI:SS'),100,'Y','The record is active in the system','EE01',1,'There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','N','N','N','N','Y','N','N','N','Y','Active',TO_DATE('2012-08-07 09:50:40','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- Aug 7, 2012 9:50:45 AM CDT
-- ADEMPIERE-98
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63813 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 7, 2012 9:50:49 AM CDT
-- ADEMPIERE-98
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63814,245,0,16,53414,'Created',TO_DATE('2012-08-07 09:50:45','YYYY-MM-DD HH24:MI:SS'),100,NULL,'Date this record was created','EE01',7,'The Created field indicates the date that this record was created.','Y','Y','N','N','N','N','Y','N','N','N','Y','Created',TO_DATE('2012-08-07 09:50:45','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- Aug 7, 2012 9:50:49 AM CDT
-- ADEMPIERE-98
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63814 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 7, 2012 9:50:51 AM CDT
-- ADEMPIERE-98
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63815,607,0,16,53414,'Updated',TO_DATE('2012-08-07 09:50:49','YYYY-MM-DD HH24:MI:SS'),100,NULL,'Date this record was updated','EE01',7,'The Updated field indicates the date that this record was updated.','Y','Y','N','N','N','N','Y','N','N','N','Y','Updated',TO_DATE('2012-08-07 09:50:49','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- Aug 7, 2012 9:50:51 AM CDT
-- ADEMPIERE-98
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63815 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 7, 2012 9:50:52 AM CDT
-- ADEMPIERE-98
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63816,246,0,19,110,53414,'CreatedBy',TO_DATE('2012-08-07 09:50:51','YYYY-MM-DD HH24:MI:SS'),100,NULL,'User who created this records','EE01',22,'The Created By field indicates the user who created this record.','Y','Y','N','N','N','N','Y','N','N','N','Y','Created By',TO_DATE('2012-08-07 09:50:51','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- Aug 7, 2012 9:50:52 AM CDT
-- ADEMPIERE-98
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63816 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 7, 2012 9:50:54 AM CDT
-- ADEMPIERE-98
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63817,608,0,19,110,53414,'UpdatedBy',TO_DATE('2012-08-07 09:50:52','YYYY-MM-DD HH24:MI:SS'),100,NULL,'User who updated this records','EE01',22,'The Updated By field indicates the user who updated this record.','Y','Y','N','N','N','N','Y','N','N','N','Y','Updated By',TO_DATE('2012-08-07 09:50:52','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- Aug 7, 2012 9:50:54 AM CDT
-- ADEMPIERE-98
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63817 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 7, 2012 9:50:55 AM CDT
-- ADEMPIERE-98
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,55560,0,'I_Forecast_ID',TO_DATE('2012-08-07 09:50:54','YYYY-MM-DD HH24:MI:SS'),100,'EE01','Y','Import Forecast ID','Import Forecast ID',TO_DATE('2012-08-07 09:50:54','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 7, 2012 9:50:55 AM CDT
-- ADEMPIERE-98
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=55560 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- Aug 7, 2012 9:50:57 AM CDT
-- ADEMPIERE-98
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63818,55560,0,13,53414,'I_Forecast_ID',TO_DATE('2012-08-07 09:50:55','YYYY-MM-DD HH24:MI:SS'),100,NULL,'EE01',22,'Y','Y','N','N','N','Y','Y','N','N','N','N','Import Forecast ID',TO_DATE('2012-08-07 09:50:55','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- Aug 7, 2012 9:50:57 AM CDT
-- ADEMPIERE-98
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63818 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 7, 2012 9:50:59 AM CDT
-- ADEMPIERE-98
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53563,TO_DATE('2012-08-07 09:50:57','YYYY-MM-DD HH24:MI:SS'),100,1000000,50000,'Table I_Forecast',1,'Y','N','Y','Y','I_Forecast','N',1000000,TO_DATE('2012-08-07 09:50:57','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 7, 2012 9:50:59 AM CDT
-- ADEMPIERE-98
UPDATE AD_Element SET ColumnName='I_Forecast_ID', Description=NULL, EntityType='EE01', Help=NULL, IsActive='Y', Name='Import Forecast ID', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Import Forecast ID',Updated=TO_DATE('2012-08-07 09:50:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=55560
;

-- Aug 7, 2012 9:50:59 AM CDT
-- ADEMPIERE-98
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=55560
;

-- Aug 7, 2012 9:50:59 AM CDT
-- ADEMPIERE-98
UPDATE AD_Reference SET Description='10 Digit Identifier', EntityType='D', Help=NULL, IsActive='Y', Name='ID', ValidationType='D',Updated=TO_DATE('2012-08-07 09:50:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=13
;

-- Aug 7, 2012 9:50:59 AM CDT
-- ADEMPIERE-98
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=13
;

-- Aug 7, 2012 9:50:59 AM CDT
-- ADEMPIERE-98
CREATE TABLE I_Forecast (AD_Client_ID NUMBER(10) NOT NULL, AD_Org_ID NUMBER(10) NOT NULL, Created DATE NOT NULL, CreatedBy NUMBER(10) NOT NULL, I_Forecast_ID NUMBER(10) NOT NULL, IsActive CHAR(1) DEFAULT 'Y' CHECK (IsActive IN ('Y','N')) NOT NULL, Updated DATE NOT NULL, UpdatedBy NUMBER(10) NOT NULL, CONSTRAINT I_Forecast_Key PRIMARY KEY (I_Forecast_ID))
;

-- Aug 7, 2012 9:50:59 AM CDT
-- ADEMPIERE-98
UPDATE AD_Element SET ColumnName='AD_Client_ID', Description='Client/Tenant for this installation.', EntityType='D', Help='A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.', IsActive='Y', Name='Client', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Client',Updated=TO_DATE('2012-08-07 09:50:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=102
;

-- Aug 7, 2012 9:50:59 AM CDT
-- ADEMPIERE-98
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=102
;

-- Aug 7, 2012 9:50:59 AM CDT
-- ADEMPIERE-98
UPDATE AD_Reference SET Description='Direct Table Access', EntityType='D', Help=NULL, IsActive='Y', Name='Table Direct', ValidationType='D',Updated=TO_DATE('2012-08-07 09:50:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=19
;

-- Aug 7, 2012 9:50:59 AM CDT
-- ADEMPIERE-98
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=19
;

-- Aug 7, 2012 9:50:59 AM CDT
-- ADEMPIERE-98
UPDATE AD_Val_Rule SET Code='AD_Client.AD_Client_ID <> 0', Description=NULL, EntityType='D', IsActive='Y', Name='AD_Client Trx Security validation', Type='S',Updated=TO_DATE('2012-08-07 09:50:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Val_Rule_ID=129
;

-- Aug 7, 2012 9:51:00 AM CDT
-- ADEMPIERE-98
UPDATE AD_Element SET ColumnName='AD_Org_ID', Description='Organizational entity within client', EntityType='D', Help='An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.', IsActive='Y', Name='Organization', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Organization',Updated=TO_DATE('2012-08-07 09:51:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=113
;

-- Aug 7, 2012 9:51:00 AM CDT
-- ADEMPIERE-98
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=113
;

-- Aug 7, 2012 9:51:00 AM CDT
-- ADEMPIERE-98
UPDATE AD_Element SET ColumnName='IsActive', Description='The record is active in the system', EntityType='D', Help='There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.', IsActive='Y', Name='Active', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Active',Updated=TO_DATE('2012-08-07 09:51:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=348
;

-- Aug 7, 2012 9:51:00 AM CDT
-- ADEMPIERE-98
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=348
;

-- Aug 7, 2012 9:51:00 AM CDT
-- ADEMPIERE-98
UPDATE AD_Reference SET Description='CheckBox', EntityType='D', Help=NULL, IsActive='Y', Name='Yes-No', ValidationType='D',Updated=TO_DATE('2012-08-07 09:51:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=20
;

-- Aug 7, 2012 9:51:00 AM CDT
-- ADEMPIERE-98
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=20
;

-- Aug 7, 2012 9:51:00 AM CDT
-- ADEMPIERE-98
UPDATE AD_Element SET ColumnName='Created', Description='Date this record was created', EntityType='D', Help='The Created field indicates the date that this record was created.', IsActive='Y', Name='Created', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Created',Updated=TO_DATE('2012-08-07 09:51:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=245
;

-- Aug 7, 2012 9:51:00 AM CDT
-- ADEMPIERE-98
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=245
;

-- Aug 7, 2012 9:51:00 AM CDT
-- ADEMPIERE-98
UPDATE AD_Reference SET Description='Date with time', EntityType='D', Help=NULL, IsActive='Y', Name='Date+Time', ValidationType='D',Updated=TO_DATE('2012-08-07 09:51:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=16
;

-- Aug 7, 2012 9:51:00 AM CDT
-- ADEMPIERE-98
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=16
;

-- Aug 7, 2012 9:51:01 AM CDT
-- ADEMPIERE-98
UPDATE AD_Element SET ColumnName='Updated', Description='Date this record was updated', EntityType='D', Help='The Updated field indicates the date that this record was updated.', IsActive='Y', Name='Updated', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Updated',Updated=TO_DATE('2012-08-07 09:51:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=607
;

-- Aug 7, 2012 9:51:01 AM CDT
-- ADEMPIERE-98
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=607
;

-- Aug 7, 2012 9:51:01 AM CDT
-- ADEMPIERE-98
UPDATE AD_Element SET ColumnName='CreatedBy', Description='User who created this records', EntityType='D', Help='The Created By field indicates the user who created this record.', IsActive='Y', Name='Created By', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Created By',Updated=TO_DATE('2012-08-07 09:51:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=246
;

-- Aug 7, 2012 9:51:01 AM CDT
-- ADEMPIERE-98
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=246
;

-- Aug 7, 2012 9:51:01 AM CDT
-- ADEMPIERE-98
UPDATE AD_Reference SET Description='User selection', EntityType='D', Help=NULL, IsActive='Y', Name='AD_User', ValidationType='T',Updated=TO_DATE('2012-08-07 09:51:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=110
;

-- Aug 7, 2012 9:51:01 AM CDT
-- ADEMPIERE-98
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=110
;

-- Aug 7, 2012 9:51:01 AM CDT
-- ADEMPIERE-98
UPDATE AD_Ref_Table SET AD_Table_ID = 114, AD_Display = 213, AD_Key = 212, isValueDisplayed = 'N', OrderByClause = 'AD_User.Name', EntityType ='D', WhereClause = '' WHERE AD_Reference_ID = 110
;

-- Aug 7, 2012 9:51:01 AM CDT
-- ADEMPIERE-98
UPDATE AD_Element SET ColumnName='UpdatedBy', Description='User who updated this records', EntityType='D', Help='The Updated By field indicates the user who updated this record.', IsActive='Y', Name='Updated By', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Updated By',Updated=TO_DATE('2012-08-07 09:51:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=608
;

-- Aug 7, 2012 9:51:01 AM CDT
-- ADEMPIERE-98
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=608
;

-- Aug 7, 2012 9:51:01 AM CDT
-- ADEMPIERE-98
UPDATE AD_Element SET ColumnName='I_ErrorMsg', Description='Messages generated from import process', EntityType='D', Help='The Import Error Message displays any error messages generated during the import process.', IsActive='Y', Name='Import Error Message', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Import Error Message',Updated=TO_DATE('2012-08-07 09:51:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=912
;

-- Aug 7, 2012 9:51:01 AM CDT
-- ADEMPIERE-98
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=912
;

-- Aug 7, 2012 9:51:01 AM CDT
-- ADEMPIERE-98
UPDATE AD_Reference SET Description='Character String', EntityType='D', Help=NULL, IsActive='Y', Name='String', ValidationType='D',Updated=TO_DATE('2012-08-07 09:51:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=10
;

-- Aug 7, 2012 9:51:01 AM CDT
-- ADEMPIERE-98
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=10
;

-- Aug 7, 2012 9:51:03 AM CDT
-- ADEMPIERE-98
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63819,912,0,10,53414,'I_ErrorMsg',TO_DATE('2012-08-07 09:51:01','YYYY-MM-DD HH24:MI:SS'),100,'Messages generated from import process','EE01',2000,'The Import Error Message displays any error messages generated during the import process.','Y','N','N','N','N','N','N','N','Y','N','Y','Import Error Message',TO_DATE('2012-08-07 09:51:01','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Aug 7, 2012 9:51:03 AM CDT
-- ADEMPIERE-98
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63819 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 7, 2012 9:51:03 AM CDT
-- ADEMPIERE-98
ALTER TABLE I_Forecast ADD I_ErrorMsg NVARCHAR2(2000) DEFAULT NULL 
;

-- Aug 7, 2012 9:51:03 AM CDT
-- ADEMPIERE-98
UPDATE AD_Element SET ColumnName='I_IsImported', Description='Has this import been processed', EntityType='D', Help='The Imported check box indicates if this import has been processed.', IsActive='Y', Name='Imported', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Imported',Updated=TO_DATE('2012-08-07 09:51:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=913
;

-- Aug 7, 2012 9:51:03 AM CDT
-- ADEMPIERE-98
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=913
;

-- Aug 7, 2012 9:51:05 AM CDT
-- ADEMPIERE-98
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63820,913,0,20,53414,'I_IsImported',TO_DATE('2012-08-07 09:51:03','YYYY-MM-DD HH24:MI:SS'),100,'Has this import been processed','EE01',1,'The Imported check box indicates if this import has been processed.','Y','N','N','N','N','N','N','N','Y','N','Y','Imported',TO_DATE('2012-08-07 09:51:03','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Aug 7, 2012 9:51:05 AM CDT
-- ADEMPIERE-98
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63820 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 7, 2012 9:51:05 AM CDT
-- ADEMPIERE-98
ALTER TABLE I_Forecast ADD I_IsImported CHAR(1) DEFAULT NULL  CHECK (I_IsImported IN ('Y','N'))
;

-- Aug 7, 2012 9:51:05 AM CDT
-- ADEMPIERE-98
UPDATE AD_Element SET ColumnName='Processed', Description='The document has been processed', EntityType='D', Help='The Processed checkbox indicates that a document has been processed.', IsActive='Y', Name='Processed', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Processed',Updated=TO_DATE('2012-08-07 09:51:05','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=1047
;

-- Aug 7, 2012 9:51:05 AM CDT
-- ADEMPIERE-98
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=1047
;

-- Aug 7, 2012 9:51:07 AM CDT
-- ADEMPIERE-98
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63821,1047,0,20,53414,'Processed',TO_DATE('2012-08-07 09:51:05','YYYY-MM-DD HH24:MI:SS'),100,'The document has been processed','EE01',1,'The Processed checkbox indicates that a document has been processed.','Y','N','N','N','N','N','N','N','Y','N','Y','Processed',TO_DATE('2012-08-07 09:51:05','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Aug 7, 2012 9:51:07 AM CDT
-- ADEMPIERE-98
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63821 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 7, 2012 9:51:07 AM CDT
-- ADEMPIERE-98
ALTER TABLE I_Forecast ADD Processed CHAR(1) DEFAULT NULL  CHECK (Processed IN ('Y','N'))
;

-- Aug 7, 2012 9:51:07 AM CDT
-- ADEMPIERE-98
UPDATE AD_Element SET ColumnName='Processing', Description=NULL, EntityType='D', Help=NULL, IsActive='Y', Name='Process Now', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Process Now',Updated=TO_DATE('2012-08-07 09:51:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=524
;

-- Aug 7, 2012 9:51:07 AM CDT
-- ADEMPIERE-98
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=524
;

-- Aug 7, 2012 9:51:07 AM CDT
-- ADEMPIERE-98
UPDATE AD_Reference SET Description='Command Button - starts a process', EntityType='D', Help=NULL, IsActive='Y', Name='Button', ValidationType='D',Updated=TO_DATE('2012-08-07 09:51:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=28
;

-- Aug 7, 2012 9:51:07 AM CDT
-- ADEMPIERE-98
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=28
;

-- Aug 7, 2012 9:51:08 AM CDT
-- ADEMPIERE-98
INSERT INTO AD_Process (AD_Client_ID,AD_Org_ID,AD_Process_ID,AccessLevel,Classname,Created,CreatedBy,Description,EntityType,Help,IsActive,IsBetaFunctionality,IsDirectPrint,IsReport,Name,ShowHelp,Statistic_Count,Statistic_Seconds,Updated,UpdatedBy,Value,WorkflowValue) VALUES (0,0,53308,'3','org.eevolution.process.ImportForecast',TO_DATE('2012-08-07 09:51:07','YYYY-MM-DD HH24:MI:SS'),100,'Imports Forecast from a file into the application','EE01','The parameters are the default values to import the records.<br><br>The maintained information inside the forecast are: the Forecast ID , product , warehouse ,  quantities, promised data<br><br>The forecast information is used to MRP calculus','Y','N','N','N','Import Forecast','Y',0,0,TO_DATE('2012-08-07 09:51:07','YYYY-MM-DD HH24:MI:SS'),100,'Import_Forecast',NULL)
;

-- Aug 7, 2012 9:51:08 AM CDT
-- ADEMPIERE-98
INSERT INTO AD_Process_Trl (AD_Language,AD_Process_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_ID=53308 AND NOT EXISTS (SELECT * FROM AD_Process_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Process_ID=t.AD_Process_ID)
;

-- Aug 7, 2012 9:51:10 AM CDT
-- ADEMPIERE-98
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,1922,0,53308,53725,20,'DeleteOldImported',TO_DATE('2012-08-07 09:51:08','YYYY-MM-DD HH24:MI:SS'),100,'Before processing delete old imported records in the import table','EE01',0,'Y','Y','N','N','Delete old imported records',10,TO_DATE('2012-08-07 09:51:08','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 7, 2012 9:51:10 AM CDT
-- ADEMPIERE-98
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53725 AND NOT EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Process_Para_ID=t.AD_Process_Para_ID)
;

-- Aug 7, 2012 9:51:14 AM CDT
-- ADEMPIERE-98
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,2169,0,53308,53726,20,'IsImportOnlyNoErrors',TO_DATE('2012-08-07 09:51:10','YYYY-MM-DD HH24:MI:SS'),100,'Y','Only start the import, if there are no validation Errors','EE01',1,'Y','Y','N','N','Import only if No Errors',20,TO_DATE('2012-08-07 09:51:10','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 7, 2012 9:51:14 AM CDT
-- ADEMPIERE-98
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53726 AND NOT EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Process_Para_ID=t.AD_Process_Para_ID)
;

-- Aug 7, 2012 9:51:16 AM CDT
-- ADEMPIERE-98
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63822,524,0,53308,28,53414,'Processing',TO_DATE('2012-08-07 09:51:14','YYYY-MM-DD HH24:MI:SS'),100,'EE01',1,'Y','N','N','N','N','N','N','N','Y','N','Y','Process Now',TO_DATE('2012-08-07 09:51:14','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Aug 7, 2012 9:51:16 AM CDT
-- ADEMPIERE-98
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63822 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 7, 2012 9:51:16 AM CDT
-- ADEMPIERE-98
ALTER TABLE I_Forecast ADD Processing CHAR(1) DEFAULT NULL 
;

-- Aug 7, 2012 9:51:16 AM CDT
-- ADEMPIERE-98
UPDATE AD_Element SET ColumnName='OrgValue', Description='Key of the Organization', EntityType='D', Help=NULL, IsActive='Y', Name='Org Key', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Org Key',Updated=TO_DATE('2012-08-07 09:51:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=2115
;

-- Aug 7, 2012 9:51:16 AM CDT
-- ADEMPIERE-98
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=2115
;

-- Aug 7, 2012 9:51:20 AM CDT
-- ADEMPIERE-98
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63823,2115,0,10,53414,'OrgValue',TO_DATE('2012-08-07 09:51:16','YYYY-MM-DD HH24:MI:SS'),100,'Key of the Organization','EE01',40,'Y','N','N','N','N','N','N','N','Y','N','Y','Org Key',TO_DATE('2012-08-07 09:51:16','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Aug 7, 2012 9:51:20 AM CDT
-- ADEMPIERE-98
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63823 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 7, 2012 9:51:20 AM CDT
-- ADEMPIERE-98
ALTER TABLE I_Forecast ADD OrgValue NVARCHAR2(40) DEFAULT NULL 
;

-- Aug 7, 2012 9:51:20 AM CDT
-- ADEMPIERE-98
UPDATE AD_Element SET ColumnName='M_Product_ID', Description='Product, Service, Item', EntityType='D', Help='Identifies an item which is either purchased or sold in this organization.', IsActive='Y', Name='Product', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Product',Updated=TO_DATE('2012-08-07 09:51:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=454
;

-- Aug 7, 2012 9:51:20 AM CDT
-- ADEMPIERE-98
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=454
;

-- Aug 7, 2012 9:51:21 AM CDT
-- ADEMPIERE-98
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63824,454,0,19,53414,'M_Product_ID',TO_DATE('2012-08-07 09:51:20','YYYY-MM-DD HH24:MI:SS'),100,'Product, Service, Item','EE01',22,'Identifies an item which is either purchased or sold in this organization.','Y','N','N','N','N','N','N','N','Y','N','Y','Product',TO_DATE('2012-08-07 09:51:20','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Aug 7, 2012 9:51:21 AM CDT
-- ADEMPIERE-98
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63824 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 7, 2012 9:51:21 AM CDT
-- ADEMPIERE-98
ALTER TABLE I_Forecast ADD M_Product_ID NUMBER(10) DEFAULT NULL 
;

-- Aug 7, 2012 9:51:22 AM CDT
-- ADEMPIERE-98
UPDATE AD_Element SET ColumnName='M_Warehouse_ID', Description='Storage Warehouse and Service Point', EntityType='D', Help='The Warehouse identifies a unique Warehouse where products are stored or Services are provided.', IsActive='Y', Name='Warehouse', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Warehouse',Updated=TO_DATE('2012-08-07 09:51:22','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=459
;

-- Aug 7, 2012 9:51:22 AM CDT
-- ADEMPIERE-98
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=459
;

-- Aug 7, 2012 9:51:26 AM CDT
-- ADEMPIERE-98
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63825,459,0,19,53414,'M_Warehouse_ID',TO_DATE('2012-08-07 09:51:22','YYYY-MM-DD HH24:MI:SS'),100,'Storage Warehouse and Service Point','EE01',22,'The Warehouse identifies a unique Warehouse where products are stored or Services are provided.','Y','N','N','N','N','N','N','N','Y','N','Y','Warehouse',TO_DATE('2012-08-07 09:51:22','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Aug 7, 2012 9:51:26 AM CDT
-- ADEMPIERE-98
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63825 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 7, 2012 9:51:26 AM CDT
-- ADEMPIERE-98
ALTER TABLE I_Forecast ADD M_Warehouse_ID NUMBER(10) DEFAULT NULL 
;

-- Aug 7, 2012 9:51:26 AM CDT
-- ADEMPIERE-98
UPDATE AD_Element SET ColumnName='ProductValue', Description='Key of the Product', EntityType='D', Help=NULL, IsActive='Y', Name='Product Key', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Product Key',Updated=TO_DATE('2012-08-07 09:51:26','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=1675
;

-- Aug 7, 2012 9:51:26 AM CDT
-- ADEMPIERE-98
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=1675
;

-- Aug 7, 2012 9:51:30 AM CDT
-- ADEMPIERE-98
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63826,1675,0,10,53414,'ProductValue',TO_DATE('2012-08-07 09:51:26','YYYY-MM-DD HH24:MI:SS'),100,'Key of the Product','EE01',40,'Y','N','N','N','N','N','N','N','Y','N','Y','Product Key',TO_DATE('2012-08-07 09:51:26','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Aug 7, 2012 9:51:30 AM CDT
-- ADEMPIERE-98
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63826 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 7, 2012 9:51:30 AM CDT
-- ADEMPIERE-98
ALTER TABLE I_Forecast ADD ProductValue NVARCHAR2(40) DEFAULT NULL 
;

-- Aug 7, 2012 9:51:30 AM CDT
-- ADEMPIERE-98
UPDATE AD_Element SET ColumnName='SalesRep_Name', Description=NULL, EntityType='D', Help=NULL, IsActive='Y', Name='Sales Representative', PO_Description=NULL, PO_Help=NULL, PO_Name='Company Agent', PO_PrintName='Company Agent', PrintName='Sales Rep',Updated=TO_DATE('2012-08-07 09:51:30','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=1886
;

-- Aug 7, 2012 9:51:30 AM CDT
-- ADEMPIERE-98
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=1886
;

-- Aug 7, 2012 9:51:32 AM CDT
-- ADEMPIERE-98
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63827,1886,0,10,53414,'SalesRep_Name',TO_DATE('2012-08-07 09:51:30','YYYY-MM-DD HH24:MI:SS'),100,'EE01',60,'Y','N','N','N','N','N','N','Y','Y','N','Y','Sales Representative',TO_DATE('2012-08-07 09:51:30','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Aug 7, 2012 9:51:32 AM CDT
-- ADEMPIERE-98
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63827 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 7, 2012 9:51:32 AM CDT
-- ADEMPIERE-98
ALTER TABLE I_Forecast ADD SalesRep_Name NVARCHAR2(60) DEFAULT NULL 
;

-- Aug 7, 2012 9:51:32 AM CDT
-- ADEMPIERE-98
UPDATE AD_Reference SET Description='Table List', EntityType='D', Help=NULL, IsActive='Y', Name='Table', ValidationType='D',Updated=TO_DATE('2012-08-07 09:51:32','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=18
;

-- Aug 7, 2012 9:51:32 AM CDT
-- ADEMPIERE-98
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=18
;

-- Aug 7, 2012 9:51:32 AM CDT
-- ADEMPIERE-98
UPDATE AD_Reference SET Description='Sales Representative', EntityType='D', Help=NULL, IsActive='Y', Name='AD_User - SalesRep', ValidationType='T',Updated=TO_DATE('2012-08-07 09:51:32','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=190
;

-- Aug 7, 2012 9:51:32 AM CDT
-- ADEMPIERE-98
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=190
;

-- Aug 7, 2012 9:51:32 AM CDT
-- ADEMPIERE-98
UPDATE AD_Ref_Table SET AD_Table_ID = 114, AD_Display = 213, AD_Key = 212, isValueDisplayed = 'N', OrderByClause = '', EntityType ='D', WhereClause = 'EXISTS (SELECT * FROM C_BPartner bp WHERE AD_User.C_BPartner_ID=bp.C_BPartner_ID AND bp.IsSalesRep=''Y'')' WHERE AD_Reference_ID = 190
;

-- Aug 7, 2012 9:51:33 AM CDT
-- ADEMPIERE-98
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63828,1063,0,18,190,53414,'SalesRep_ID',TO_DATE('2012-08-07 09:51:32','YYYY-MM-DD HH24:MI:SS'),100,'Sales Representative or Company Agent','EE01',22,'The Sales Representative indicates the Sales Rep for this Region.  Any Sales Rep must be a valid internal user.','Y','N','N','N','N','N','N','N','Y','N','Y','Sales Representative',TO_DATE('2012-08-07 09:51:32','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Aug 7, 2012 9:51:33 AM CDT
-- ADEMPIERE-98
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63828 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 7, 2012 9:51:33 AM CDT
-- ADEMPIERE-98
ALTER TABLE I_Forecast ADD SalesRep_ID NUMBER(10) DEFAULT NULL 
;

-- Aug 7, 2012 9:51:33 AM CDT
-- ADEMPIERE-98
UPDATE AD_Element SET ColumnName='DatePromised', Description='Date Order was promised', EntityType='D', Help='The Date Promised indicates the date, if any, that an Order was promised for.', IsActive='Y', Name='Date Promised', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Date Promised',Updated=TO_DATE('2012-08-07 09:51:33','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=269
;

-- Aug 7, 2012 9:51:33 AM CDT
-- ADEMPIERE-98
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=269
;

-- Aug 7, 2012 9:51:34 AM CDT
-- ADEMPIERE-98
UPDATE AD_Reference SET Description='Date mm/dd/yyyy', EntityType='D', Help=NULL, IsActive='Y', Name='Date', ValidationType='D',Updated=TO_DATE('2012-08-07 09:51:34','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=15
;

-- Aug 7, 2012 9:51:34 AM CDT
-- ADEMPIERE-98
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=15
;

-- Aug 7, 2012 9:51:35 AM CDT
-- ADEMPIERE-98
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63829,269,0,15,53414,'DatePromised',TO_DATE('2012-08-07 09:51:34','YYYY-MM-DD HH24:MI:SS'),100,'Date Order was promised','EE01',7,'The Date Promised indicates the date, if any, that an Order was promised for.','Y','N','N','N','N','N','N','N','Y','N','Y','Date Promised',TO_DATE('2012-08-07 09:51:34','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Aug 7, 2012 9:51:35 AM CDT
-- ADEMPIERE-98
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63829 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 7, 2012 9:51:35 AM CDT
-- ADEMPIERE-98
ALTER TABLE I_Forecast ADD DatePromised DATE DEFAULT NULL 
;

-- Aug 7, 2012 9:51:35 AM CDT
-- ADEMPIERE-98
UPDATE AD_Element SET ColumnName='WarehouseValue', Description='Key of the Warehouse', EntityType='D', Help='Key to identify the Warehouse', IsActive='Y', Name='Warehouse Key', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Warehouse Key',Updated=TO_DATE('2012-08-07 09:51:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=2070
;

-- Aug 7, 2012 9:51:35 AM CDT
-- ADEMPIERE-98
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=2070
;

-- Aug 7, 2012 9:51:40 AM CDT
-- ADEMPIERE-98
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63830,2070,0,10,53414,'WarehouseValue',TO_DATE('2012-08-07 09:51:35','YYYY-MM-DD HH24:MI:SS'),100,'Key of the Warehouse','EE01',40,'Key to identify the Warehouse','Y','N','N','N','N','N','N','N','Y','N','Y','Warehouse Key',TO_DATE('2012-08-07 09:51:35','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Aug 7, 2012 9:51:40 AM CDT
-- ADEMPIERE-98
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63830 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 7, 2012 9:51:40 AM CDT
-- ADEMPIERE-98
ALTER TABLE I_Forecast ADD WarehouseValue NVARCHAR2(40) DEFAULT NULL 
;

-- Aug 7, 2012 9:51:40 AM CDT
-- ADEMPIERE-98
UPDATE AD_Element SET ColumnName='Qty', Description='Quantity', EntityType='D', Help='The Quantity indicates the number of a specific product or item for this document.', IsActive='Y', Name='Quantity', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Qty',Updated=TO_DATE('2012-08-07 09:51:40','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=526
;

-- Aug 7, 2012 9:51:40 AM CDT
-- ADEMPIERE-98
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=526
;

-- Aug 7, 2012 9:51:40 AM CDT
-- ADEMPIERE-98
UPDATE AD_Reference SET Description='Number with 4 decimals', EntityType='D', Help=NULL, IsActive='Y', Name='Amount', ValidationType='D',Updated=TO_DATE('2012-08-07 09:51:40','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=12
;

-- Aug 7, 2012 9:51:40 AM CDT
-- ADEMPIERE-98
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=12
;

-- Aug 7, 2012 9:51:42 AM CDT
-- ADEMPIERE-98
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63831,526,0,12,53414,'Qty',TO_DATE('2012-08-07 09:51:40','YYYY-MM-DD HH24:MI:SS'),100,'Quantity','EE01',22,'The Quantity indicates the number of a specific product or item for this document.','Y','N','N','N','N','N','N','N','Y','N','Y','Quantity',TO_DATE('2012-08-07 09:51:40','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Aug 7, 2012 9:51:42 AM CDT
-- ADEMPIERE-98
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63831 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 7, 2012 9:51:42 AM CDT
-- ADEMPIERE-98
ALTER TABLE I_Forecast ADD Qty NUMBER DEFAULT NULL 
;

-- Aug 7, 2012 9:51:42 AM CDT
-- ADEMPIERE-98
UPDATE AD_Element SET ColumnName='M_Forecast_ID', Description='Material Forecast', EntityType='D', Help='Material Forecast', IsActive='Y', Name='Forecast', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Forecast',Updated=TO_DATE('2012-08-07 09:51:42','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=2498
;

-- Aug 7, 2012 9:51:42 AM CDT
-- ADEMPIERE-98
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=2498
;

-- Aug 7, 2012 9:51:44 AM CDT
-- ADEMPIERE-98
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63832,2498,0,19,53414,'M_Forecast_ID',TO_DATE('2012-08-07 09:51:42','YYYY-MM-DD HH24:MI:SS'),100,'Material Forecast','EE01',22,'Material Forecast','Y','N','N','N','N','N','N','N','Y','N','N','Forecast',TO_DATE('2012-08-07 09:51:42','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Aug 7, 2012 9:51:44 AM CDT
-- ADEMPIERE-98
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63832 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 7, 2012 9:51:44 AM CDT
-- ADEMPIERE-98
ALTER TABLE I_Forecast ADD M_Forecast_ID NUMBER(10) DEFAULT NULL 
;

-- Aug 7, 2012 9:51:44 AM CDT
-- ADEMPIERE-98
UPDATE AD_Element SET ColumnName='M_ForecastLine_ID', Description='Forecast Line', EntityType='D', Help='Forecast of Product Qyantity by Period', IsActive='Y', Name='Forecast Line', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Forecast Line',Updated=TO_DATE('2012-08-07 09:51:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=2499
;

-- Aug 7, 2012 9:51:44 AM CDT
-- ADEMPIERE-98
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=2499
;

-- Aug 7, 2012 9:51:45 AM CDT
-- ADEMPIERE-98
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63833,2499,0,19,53414,'M_ForecastLine_ID',TO_DATE('2012-08-07 09:51:44','YYYY-MM-DD HH24:MI:SS'),100,'Forecast Line','EE01',22,'Forecast of Product Qyantity by Period','Y','N','N','N','N','N','N','N','Y','N','N','Forecast Line',TO_DATE('2012-08-07 09:51:44','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Aug 7, 2012 9:51:45 AM CDT
-- ADEMPIERE-98
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63833 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 7, 2012 9:51:45 AM CDT
-- ADEMPIERE-98
ALTER TABLE I_Forecast ADD M_ForecastLine_ID NUMBER(10) DEFAULT NULL 
;

-- Aug 7, 2012 9:51:49 AM CDT
-- ADEMPIERE-98
INSERT INTO AD_Tab (AD_Client_ID,AD_Org_ID,AD_Tab_ID,AD_Table_ID,AD_Window_ID,CommitWarning,Created,CreatedBy,Description,EntityType,HasTree,Help,IsActive,IsAdvancedTab,IsInfoTab,IsInsertRecord,IsReadOnly,IsSingleRow,IsSortTab,IsTranslationTab,Name,Processing,SeqNo,TabLevel,Updated,UpdatedBy) VALUES (0,0,53544,53414,53190,NULL,TO_DATE('2012-08-07 09:51:45','YYYY-MM-DD HH24:MI:SS'),100,'Import Forecast','EE01','N','The parameters are the default values to import the records.<br><br>The maintained information inside the forecast are: the Forecast ID , product , warehouse ,  quantities, promised data<br><br>The forecast information is used to MRP calculus','Y','N','N','Y','N','Y','N','N','Forecast','N',10,0,TO_DATE('2012-08-07 09:51:45','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 7, 2012 9:51:49 AM CDT
-- ADEMPIERE-98
INSERT INTO AD_Tab_Trl (AD_Language,AD_Tab_ID, CommitWarning,Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Tab_ID, t.CommitWarning,t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Tab t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Tab_ID=53544 AND NOT EXISTS (SELECT * FROM AD_Tab_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Tab_ID=t.AD_Tab_ID)
;

-- Aug 7, 2012 9:51:51 AM CDT
-- ADEMPIERE-98
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,63813,64810,0,53544,TO_DATE('2012-08-07 09:51:49','YYYY-MM-DD HH24:MI:SS'),100,NULL,'The record is active in the system',1,'EE01','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','N','Y','Y','N','N','N','N','N','Active',0,0,0,TO_DATE('2012-08-07 09:51:49','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 7, 2012 9:51:51 AM CDT
-- ADEMPIERE-98
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64810 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 7, 2012 9:51:52 AM CDT
-- ADEMPIERE-98
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,63811,64811,0,53544,TO_DATE('2012-08-07 09:51:51','YYYY-MM-DD HH24:MI:SS'),100,NULL,'Client/Tenant for this installation.',22,'EE01','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','N','Y','Y','N','N','N','N','N','Client',0,0,0,TO_DATE('2012-08-07 09:51:51','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 7, 2012 9:51:52 AM CDT
-- ADEMPIERE-98
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64811 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 7, 2012 9:51:54 AM CDT
-- ADEMPIERE-98
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,63820,64812,0,53544,TO_DATE('2012-08-07 09:51:52','YYYY-MM-DD HH24:MI:SS'),100,NULL,'Has this import been processed',1,'EE01','The Imported check box indicates if this import has been processed.','N','Y','Y','Y','N','N','Y','Y','Imported',0,10,0,TO_DATE('2012-08-07 09:51:52','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 7, 2012 9:51:54 AM CDT
-- ADEMPIERE-98
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64812 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 7, 2012 9:51:55 AM CDT
-- ADEMPIERE-98
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,63819,64813,0,53544,TO_DATE('2012-08-07 09:51:54','YYYY-MM-DD HH24:MI:SS'),100,NULL,'Messages generated from import process',2000,'EE01','The Import Error Message displays any error messages generated during the import process.','N','Y','Y','Y','N','N','Y','N','Import Error Message',0,20,0,TO_DATE('2012-08-07 09:51:54','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 7, 2012 9:51:55 AM CDT
-- ADEMPIERE-98
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64813 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 7, 2012 9:51:57 AM CDT
-- ADEMPIERE-98
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,63832,64814,0,53544,TO_DATE('2012-08-07 09:51:55','YYYY-MM-DD HH24:MI:SS'),100,NULL,'Material Forecast',22,'EE01','Material Forecast','N','Y','Y','Y','N','N','N','Y','Forecast',0,30,0,TO_DATE('2012-08-07 09:51:55','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 7, 2012 9:51:57 AM CDT
-- ADEMPIERE-98
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64814 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 7, 2012 9:51:59 AM CDT
-- ADEMPIERE-98
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,63833,64815,0,53544,TO_DATE('2012-08-07 09:51:57','YYYY-MM-DD HH24:MI:SS'),100,NULL,'Forecast Line',22,'EE01','Forecast of Product Qyantity by Period','N','Y','Y','Y','N','N','Y','Y','Forecast Line',0,40,0,TO_DATE('2012-08-07 09:51:57','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 7, 2012 9:51:59 AM CDT
-- ADEMPIERE-98
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64815 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 7, 2012 9:52:00 AM CDT
-- ADEMPIERE-98
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,63823,64816,0,53544,TO_DATE('2012-08-07 09:51:59','YYYY-MM-DD HH24:MI:SS'),100,NULL,'Key of the Organization',20,'EE01','N','Y','Y','Y','N','N','N','N','Org Key',0,50,0,TO_DATE('2012-08-07 09:51:59','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 7, 2012 9:52:00 AM CDT
-- ADEMPIERE-98
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64816 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 7, 2012 9:52:05 AM CDT
-- ADEMPIERE-98
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,63812,64817,0,53544,TO_DATE('2012-08-07 09:52:00','YYYY-MM-DD HH24:MI:SS'),100,NULL,'Organizational entity within client',22,'EE01','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','N','Y','Y','Y','N','N','N','Y','Organization',0,60,0,TO_DATE('2012-08-07 09:52:00','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 7, 2012 9:52:05 AM CDT
-- ADEMPIERE-98
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64817 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 7, 2012 9:52:07 AM CDT
-- ADEMPIERE-98
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,63826,64818,0,53544,TO_DATE('2012-08-07 09:52:05','YYYY-MM-DD HH24:MI:SS'),100,NULL,'Key of the Product',20,'EE01','N','Y','Y','Y','N','N','N','N','Product Key',0,70,0,TO_DATE('2012-08-07 09:52:05','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 7, 2012 9:52:07 AM CDT
-- ADEMPIERE-98
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64818 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 7, 2012 9:52:09 AM CDT
-- ADEMPIERE-98
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,63824,64819,0,53544,TO_DATE('2012-08-07 09:52:07','YYYY-MM-DD HH24:MI:SS'),100,NULL,'Product, Service, Item',22,'EE01','Identifies an item which is either purchased or sold in this organization.','N','Y','Y','Y','N','N','N','Y','Product',0,80,0,TO_DATE('2012-08-07 09:52:07','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 7, 2012 9:52:09 AM CDT
-- ADEMPIERE-98
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64819 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 7, 2012 9:52:10 AM CDT
-- ADEMPIERE-98
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,63830,64820,0,53544,TO_DATE('2012-08-07 09:52:09','YYYY-MM-DD HH24:MI:SS'),100,NULL,'Key of the Warehouse',20,'EE01','Key to identify the Warehouse','N','Y','Y','Y','N','N','N','N','Warehouse Key',0,90,0,TO_DATE('2012-08-07 09:52:09','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 7, 2012 9:52:10 AM CDT
-- ADEMPIERE-98
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64820 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 7, 2012 9:52:12 AM CDT
-- ADEMPIERE-98
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,63825,64821,0,53544,TO_DATE('2012-08-07 09:52:10','YYYY-MM-DD HH24:MI:SS'),100,NULL,'Storage Warehouse and Service Point',22,'EE01','The Warehouse identifies a unique Warehouse where products are stored or Services are provided.','N','Y','Y','Y','N','N','N','Y','Warehouse',0,100,0,TO_DATE('2012-08-07 09:52:10','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 7, 2012 9:52:12 AM CDT
-- ADEMPIERE-98
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64821 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 7, 2012 9:52:19 AM CDT
-- ADEMPIERE-98
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,63831,64822,0,53544,TO_DATE('2012-08-07 09:52:12','YYYY-MM-DD HH24:MI:SS'),100,NULL,'Quantity',22,'EE01','The Quantity indicates the number of a specific product or item for this document.','N','Y','Y','Y','N','N','N','N','Quantity',0,110,0,TO_DATE('2012-08-07 09:52:12','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 7, 2012 9:52:19 AM CDT
-- ADEMPIERE-98
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64822 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 7, 2012 9:52:21 AM CDT
-- ADEMPIERE-98
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,63829,64823,0,53544,TO_DATE('2012-08-07 09:52:19','YYYY-MM-DD HH24:MI:SS'),100,NULL,'Date Order was promised',7,'EE01','The Date Promised indicates the date, if any, that an Order was promised for.','N','Y','Y','Y','N','N','N','Y','Date Promised',0,120,0,TO_DATE('2012-08-07 09:52:19','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 7, 2012 9:52:21 AM CDT
-- ADEMPIERE-98
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64823 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 7, 2012 9:52:22 AM CDT
-- ADEMPIERE-98
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,DisplayLength,EntityType,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,63827,64824,0,53544,TO_DATE('2012-08-07 09:52:21','YYYY-MM-DD HH24:MI:SS'),100,NULL,20,'EE01','N','Y','N','Y','N','N','N','N','Sales Representative Name',0,130,0,TO_DATE('2012-08-07 09:52:21','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 7, 2012 9:52:22 AM CDT
-- ADEMPIERE-98
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64824 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 7, 2012 9:52:27 AM CDT
-- ADEMPIERE-98
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,63828,64825,0,53544,TO_DATE('2012-08-07 09:52:22','YYYY-MM-DD HH24:MI:SS'),100,NULL,'Sales Representative or Company Agent',22,'EE01','The Sales Representative indicates the Sales Rep for this Region.  Any Sales Rep must be a valid internal user.','N','Y','Y','Y','N','N','N','Y','Sales Representative',0,140,0,TO_DATE('2012-08-07 09:52:22','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 7, 2012 9:52:27 AM CDT
-- ADEMPIERE-98
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64825 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 7, 2012 9:52:43 AM CDT
-- ADEMPIERE-98
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,DisplayLength,EntityType,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,63822,64826,0,53544,TO_DATE('2012-08-07 09:52:27','YYYY-MM-DD HH24:MI:SS'),100,NULL,1,'EE01','N','Y','N','Y','N','N','N','N','Import Forecast',0,150,0,TO_DATE('2012-08-07 09:52:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 7, 2012 9:52:43 AM CDT
-- ADEMPIERE-98
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64826 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 7, 2012 9:52:44 AM CDT
-- ADEMPIERE-98
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,Description,DisplayLength,EntityType,Help,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,63821,64827,0,53544,TO_DATE('2012-08-07 09:52:43','YYYY-MM-DD HH24:MI:SS'),100,NULL,'The document has been processed',1,'EE01','The Processed checkbox indicates that a document has been processed.','N','Y','Y','Y','N','N','Y','N','Processed',0,160,0,TO_DATE('2012-08-07 09:52:43','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 7, 2012 9:52:44 AM CDT
-- ADEMPIERE-98
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64827 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 7, 2012 9:52:46 AM CDT
-- ADEMPIERE-98
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DefaultValue,DisplayLength,EntityType,HideInListView,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,PreferredWidth,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,63818,64828,0,53544,TO_DATE('2012-08-07 09:52:44','YYYY-MM-DD HH24:MI:SS'),100,NULL,22,'EE01','N','Y','Y','N','N','N','N','N','Import Forecast ID',0,0,0,TO_DATE('2012-08-07 09:52:44','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 7, 2012 9:52:46 AM CDT
-- ADEMPIERE-98
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64828 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 7, 2012 9:52:48 AM CDT
-- ADEMPIERE-98
INSERT INTO AD_ImpFormat (AD_Client_ID,AD_ImpFormat_ID,AD_Org_ID,AD_Table_ID,Created,CreatedBy,Description,FormatType,IsActive,Name,Processing,Updated,UpdatedBy) VALUES (0,50017,0,53414,TO_DATE('2012-08-07 09:52:46','YYYY-MM-DD HH24:MI:SS'),100,'Forecast Example','C','Y','Import Forecast','N',TO_DATE('2012-08-07 09:52:46','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 7, 2012 9:52:52 AM CDT
-- ADEMPIERE-98
INSERT INTO AD_ImpFormat_Row (AD_Client_ID,AD_Column_ID,AD_ImpFormat_ID,AD_ImpFormat_Row_ID,AD_Org_ID,Callout,ConstantValue,Created,CreatedBy,DataFormat,DataType,DecimalPoint,DivideBy100,EndNo,IsActive,Name,Script,SeqNo,StartNo,Updated,UpdatedBy) VALUES (0,63832,50017,50292,0,NULL,NULL,TO_DATE('2012-08-07 09:52:48','YYYY-MM-DD HH24:MI:SS'),100,NULL,'N','.','N',0,'Y','Forecast ID',NULL,10,1,TO_DATE('2012-08-07 09:52:48','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 7, 2012 9:52:53 AM CDT
-- ADEMPIERE-98
INSERT INTO AD_ImpFormat_Row (AD_Client_ID,AD_Column_ID,AD_ImpFormat_ID,AD_ImpFormat_Row_ID,AD_Org_ID,Callout,ConstantValue,Created,CreatedBy,DataFormat,DataType,DecimalPoint,DivideBy100,EndNo,IsActive,Name,Script,SeqNo,StartNo,Updated,UpdatedBy) VALUES (0,63823,50017,50293,0,NULL,NULL,TO_DATE('2012-08-07 09:52:52','YYYY-MM-DD HH24:MI:SS'),100,NULL,'S','.','N',0,'Y','Org Key',NULL,20,2,TO_DATE('2012-08-07 09:52:52','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 7, 2012 9:53:02 AM CDT
-- ADEMPIERE-98
INSERT INTO AD_ImpFormat_Row (AD_Client_ID,AD_Column_ID,AD_ImpFormat_ID,AD_ImpFormat_Row_ID,AD_Org_ID,Callout,ConstantValue,Created,CreatedBy,DataFormat,DataType,DecimalPoint,DivideBy100,EndNo,IsActive,Name,Script,SeqNo,StartNo,Updated,UpdatedBy) VALUES (0,63826,50017,50294,0,NULL,NULL,TO_DATE('2012-08-07 09:52:53','YYYY-MM-DD HH24:MI:SS'),100,NULL,'S','.','N',0,'Y','Product Key',NULL,30,3,TO_DATE('2012-08-07 09:52:53','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 7, 2012 9:53:03 AM CDT
-- ADEMPIERE-98
INSERT INTO AD_ImpFormat_Row (AD_Client_ID,AD_Column_ID,AD_ImpFormat_ID,AD_ImpFormat_Row_ID,AD_Org_ID,Callout,ConstantValue,Created,CreatedBy,DataFormat,DataType,DecimalPoint,DivideBy100,EndNo,IsActive,Name,Script,SeqNo,StartNo,Updated,UpdatedBy) VALUES (0,63830,50017,50295,0,NULL,NULL,TO_DATE('2012-08-07 09:53:02','YYYY-MM-DD HH24:MI:SS'),100,NULL,'S','.','N',0,'Y','Warehouse Key',NULL,40,4,TO_DATE('2012-08-07 09:53:02','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 7, 2012 9:53:05 AM CDT
-- ADEMPIERE-98
INSERT INTO AD_ImpFormat_Row (AD_Client_ID,AD_Column_ID,AD_ImpFormat_ID,AD_ImpFormat_Row_ID,AD_Org_ID,Callout,ConstantValue,Created,CreatedBy,DataFormat,DataType,DecimalPoint,DivideBy100,EndNo,IsActive,Name,Script,SeqNo,StartNo,Updated,UpdatedBy) VALUES (0,63831,50017,50296,0,NULL,NULL,TO_DATE('2012-08-07 09:53:03','YYYY-MM-DD HH24:MI:SS'),100,NULL,'N','.','N',0,'Y','Quantity',NULL,50,5,TO_DATE('2012-08-07 09:53:03','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 7, 2012 9:53:13 AM CDT
-- ADEMPIERE-98
INSERT INTO AD_ImpFormat_Row (AD_Client_ID,AD_Column_ID,AD_ImpFormat_ID,AD_ImpFormat_Row_ID,AD_Org_ID,Callout,ConstantValue,Created,CreatedBy,DataFormat,DataType,DecimalPoint,DivideBy100,EndNo,IsActive,Name,Script,SeqNo,StartNo,Updated,UpdatedBy) VALUES (0,63829,50017,50297,0,NULL,NULL,TO_DATE('2012-08-07 09:53:05','YYYY-MM-DD HH24:MI:SS'),100,'dd/MM/yyyy','D','.','N',0,'Y','Date Promised',NULL,60,6,TO_DATE('2012-08-07 09:53:05','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 7, 2012 9:53:15 AM CDT
-- ADEMPIERE-98
INSERT INTO AD_ImpFormat_Row (AD_Client_ID,AD_Column_ID,AD_ImpFormat_ID,AD_ImpFormat_Row_ID,AD_Org_ID,Callout,ConstantValue,Created,CreatedBy,DataFormat,DataType,DecimalPoint,DivideBy100,EndNo,IsActive,Name,Script,SeqNo,StartNo,Updated,UpdatedBy) VALUES (0,63827,50017,50298,0,NULL,NULL,TO_DATE('2012-08-07 09:53:13','YYYY-MM-DD HH24:MI:SS'),100,NULL,'S','.','N',0,'Y','Sales Rep Name',NULL,70,7,TO_DATE('2012-08-07 09:53:13','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 7, 2012 9:53:16 AM CDT
-- ADEMPIERE-98
INSERT INTO AD_Menu (AD_Client_ID,AD_Menu_ID,AD_Org_ID,AD_Window_ID,Action,Created,CreatedBy,Description,EntityType,IsActive,IsReadOnly,IsSOTrx,IsSummary,Name,Updated,UpdatedBy) VALUES (0,53434,0,53190,'W',TO_DATE('2012-08-07 09:53:15','YYYY-MM-DD HH24:MI:SS'),100,'This process allows to import the forecast by using a predefined import format,  inside the application.','EE01','Y','N','N','N','Import Forecast',TO_DATE('2012-08-07 09:53:15','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 7, 2012 9:53:16 AM CDT
-- ADEMPIERE-98
INSERT INTO AD_Menu_Trl (AD_Language,AD_Menu_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Menu_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Menu t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Menu_ID=53434 AND NOT EXISTS (SELECT * FROM AD_Menu_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Menu_ID=t.AD_Menu_ID)
;

-- Aug 7, 2012 9:53:16 AM CDT
-- ADEMPIERE-98
INSERT INTO AD_TREENODEMM(AD_Client_ID, AD_Org_ID, CreatedBy, UpdatedBy, Parent_ID, SeqNo, AD_Tree_ID, Node_ID)VALUES(0, 0, 0, 0, 163,8, 10, 53434)
;

-- Aug 7, 2012 10:44:21 AM CDT
-- ADEMPIERE-98
UPDATE AD_Column SET DefaultValue=NULL,Updated=TO_DATE('2012-08-07 10:44:21','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=63812
;

-- Aug 7, 2012 10:50:45 AM CDT
-- ADEMPIERE-98
UPDATE AD_Column SET IsMandatory='N',Updated=TO_DATE('2012-08-07 10:50:45','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=63812
;

-- Aug 7, 2012 10:51:14 AM CDT
-- ADEMPIERE-98
ALTER TABLE I_Forecast MODIFY AD_Org_ID NUMBER(10) DEFAULT NULL 
;

-- Aug 7, 2012 10:51:14 AM CDT
-- ADEMPIERE-98
ALTER TABLE I_Forecast MODIFY AD_Org_ID NULL
;

ALTER TABLE I_Forecast ADD (CONSTRAINT SalesRep_IForecast FOREIGN KEY (SalesRep_ID) REFERENCES AD_User);                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              

ALTER TABLE I_Forecast ADD (CONSTRAINT MForecast_IForecast FOREIGN KEY (M_Forecast_ID) REFERENCES M_Forecast);                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              

ALTER TABLE I_Forecast ADD (CONSTRAINT MForecastLine_IForecast FOREIGN KEY (M_ForecastLine_ID) REFERENCES M_ForecastLine);                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              

ALTER TABLE I_Forecast ADD (CONSTRAINT MProduct_IForecast FOREIGN KEY (M_Product_ID) REFERENCES M_Product);                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              

ALTER TABLE I_Forecast ADD (CONSTRAINT MWarehouse_IForecast FOREIGN KEY (M_Warehouse_ID) REFERENCES M_Warehouse);

-- Aug 20, 2012 9:37:06 AM CDT
-- ADEMPIERE-098
UPDATE AD_Column SET AD_Val_Rule_ID=52124,Updated=TO_DATE('2012-08-20 09:37:06','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=63274
;

-- Aug 20, 2012 10:00:19 AM CDT
-- ADEMPIERE-098
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,55619,0,20,'IsConsumesForecast',TO_DATE('2012-08-20 10:00:17','YYYY-MM-DD HH24:MI:SS'),100,'Indicates if the sales order line will generate or not a demand for MPS','EE01',1,'Y','Is Consumes Forecast','Is Consumes Forecast',TO_DATE('2012-08-20 10:00:17','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 20, 2012 10:00:20 AM CDT
-- ADEMPIERE-098
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=55619 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- Aug 20, 2012 10:01:00 AM CDT
-- ADEMPIERE-098
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,64175,55619,0,20,260,'IsConsumesForecast',TO_DATE('2012-08-20 10:00:59','YYYY-MM-DD HH24:MI:SS'),100,'Indicates if the sales order line will generate or not a demand for MPS','EE01',1,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Is Consumes Forecast',0,TO_DATE('2012-08-20 10:00:59','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Aug 20, 2012 10:01:00 AM CDT
-- ADEMPIERE-098
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64175 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 20, 2012 10:01:05 AM CDT
-- ADEMPIERE-098
ALTER TABLE C_OrderLine ADD IsConsumesForecast CHAR(1) DEFAULT NULL  CHECK (IsConsumesForecast IN ('Y','N'))
;

-- Aug 20, 2012 10:01:43 AM CDT
-- ADEMPIERE-098
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,64175,65080,0,187,TO_DATE('2012-08-20 10:01:41','YYYY-MM-DD HH24:MI:SS'),100,'Indicates if the sales order line will generate or not a demand for MPS',1,'EE01','Y','Y','Y','N','N','N','N','N','Is Consumes Forecast',TO_DATE('2012-08-20 10:01:41','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 20, 2012 10:01:43 AM CDT
-- ADEMPIERE-098
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65080 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 20, 2012 10:01:45 AM CDT
-- ADEMPIERE-098
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,55323,65081,0,187,TO_DATE('2012-08-20 10:01:43','YYYY-MM-DD HH24:MI:SS'),100,'This field links a sales order line to the purchase order line that is generated from it.',22,'D','Y','Y','Y','N','N','N','N','N','Linked Order Line',TO_DATE('2012-08-20 10:01:43','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 20, 2012 10:01:45 AM CDT
-- ADEMPIERE-098
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65081 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 20, 2012 10:01:46 AM CDT
-- ADEMPIERE-098
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56532,65082,0,187,TO_DATE('2012-08-20 10:01:45','YYYY-MM-DD HH24:MI:SS'),100,22,'EE01','Y','Y','Y','N','N','N','N','N','Manufacturing Cost Collector',TO_DATE('2012-08-20 10:01:45','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 20, 2012 10:01:46 AM CDT
-- ADEMPIERE-098
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65082 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 20, 2012 10:01:47 AM CDT
-- ADEMPIERE-098
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,57128,65083,0,187,TO_DATE('2012-08-20 10:01:46','YYYY-MM-DD HH24:MI:SS'),100,22,'D','Y','Y','Y','N','N','N','N','N','Promotion',TO_DATE('2012-08-20 10:01:46','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 20, 2012 10:01:47 AM CDT
-- ADEMPIERE-098
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=65083 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 20, 2012 10:02:41 AM CDT
-- ADEMPIERE-098
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=65081
;

-- Aug 20, 2012 10:02:41 AM CDT
-- ADEMPIERE-098
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=65082
;

-- Aug 20, 2012 10:02:41 AM CDT
-- ADEMPIERE-098
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=65083
;

-- Aug 20, 2012 10:02:41 AM CDT
-- ADEMPIERE-098
UPDATE AD_Field SET SeqNo=370,IsDisplayed='Y' WHERE AD_Field_ID=65080
;

-- Aug 20, 2012 10:04:38 AM CDT
-- ADEMPIERE-098
UPDATE AD_Field SET SeqNo=190,IsDisplayed='Y' WHERE AD_Field_ID=65080
;

-- Aug 20, 2012 10:04:38 AM CDT
-- ADEMPIERE-098
UPDATE AD_Field SET SeqNo=200,IsDisplayed='Y' WHERE AD_Field_ID=1131
;

-- Aug 20, 2012 10:04:38 AM CDT
-- ADEMPIERE-098
UPDATE AD_Field SET SeqNo=210,IsDisplayed='Y' WHERE AD_Field_ID=1133
;

-- Aug 20, 2012 10:04:38 AM CDT
-- ADEMPIERE-098
UPDATE AD_Field SET SeqNo=220,IsDisplayed='Y' WHERE AD_Field_ID=1135
;

-- Aug 20, 2012 10:04:38 AM CDT
-- ADEMPIERE-098
UPDATE AD_Field SET SeqNo=230,IsDisplayed='Y' WHERE AD_Field_ID=10829
;

-- Aug 20, 2012 10:04:38 AM CDT
-- ADEMPIERE-098
UPDATE AD_Field SET SeqNo=240,IsDisplayed='Y' WHERE AD_Field_ID=1138
;

-- Aug 20, 2012 10:04:38 AM CDT
-- ADEMPIERE-098
UPDATE AD_Field SET SeqNo=250,IsDisplayed='Y' WHERE AD_Field_ID=1137
;

-- Aug 20, 2012 10:04:38 AM CDT
-- ADEMPIERE-098
UPDATE AD_Field SET SeqNo=260,IsDisplayed='Y' WHERE AD_Field_ID=2115
;

-- Aug 20, 2012 10:04:38 AM CDT
-- ADEMPIERE-098
UPDATE AD_Field SET SeqNo=270,IsDisplayed='Y' WHERE AD_Field_ID=1141
;

-- Aug 20, 2012 10:04:38 AM CDT
-- ADEMPIERE-098
UPDATE AD_Field SET SeqNo=280,IsDisplayed='Y' WHERE AD_Field_ID=3124
;

-- Aug 20, 2012 10:04:38 AM CDT
-- ADEMPIERE-098
UPDATE AD_Field SET SeqNo=290,IsDisplayed='Y' WHERE AD_Field_ID=12745
;

-- Aug 20, 2012 10:04:38 AM CDT
-- ADEMPIERE-098
UPDATE AD_Field SET SeqNo=300,IsDisplayed='Y' WHERE AD_Field_ID=13644
;

-- Aug 20, 2012 10:04:38 AM CDT
-- ADEMPIERE-098
UPDATE AD_Field SET SeqNo=310,IsDisplayed='Y' WHERE AD_Field_ID=13645
;

-- Aug 20, 2012 10:04:38 AM CDT
-- ADEMPIERE-098
UPDATE AD_Field SET SeqNo=320,IsDisplayed='Y' WHERE AD_Field_ID=13691
;

-- Aug 20, 2012 10:04:38 AM CDT
-- ADEMPIERE-098
UPDATE AD_Field SET SeqNo=330,IsDisplayed='Y' WHERE AD_Field_ID=13650
;

-- Aug 20, 2012 10:04:38 AM CDT
-- ADEMPIERE-098
UPDATE AD_Field SET SeqNo=340,IsDisplayed='Y' WHERE AD_Field_ID=13651
;

-- Aug 20, 2012 10:04:38 AM CDT
-- ADEMPIERE-098
UPDATE AD_Field SET SeqNo=350,IsDisplayed='Y' WHERE AD_Field_ID=2880
;

-- Aug 20, 2012 10:04:38 AM CDT
-- ADEMPIERE-098
UPDATE AD_Field SET SeqNo=360,IsDisplayed='Y' WHERE AD_Field_ID=12744
;

-- Aug 20, 2012 10:04:38 AM CDT
-- ADEMPIERE-098
UPDATE AD_Field SET SeqNo=370,IsDisplayed='Y' WHERE AD_Field_ID=10332
;

-- Aug 20, 2012 10:04:53 AM CDT
-- ADEMPIERE-098
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_DATE('2012-08-20 10:04:53','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=65080
;

-- Aug 20, 2012 10:05:34 AM CDT
-- ADEMPIERE-098
UPDATE AD_Element SET Description='Is Consumes Forecast', Help='Indicates if the sales order line will generate or not a demand for MPS',Updated=TO_DATE('2012-08-20 10:05:34','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=55619
;

-- Aug 20, 2012 10:05:34 AM CDT
-- ADEMPIERE-098
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=55619
;

-- Aug 20, 2012 10:05:34 AM CDT
-- ADEMPIERE-098
UPDATE AD_Column SET ColumnName='IsConsumesForecast', Name='Is Consumes Forecast', Description='Is Consumes Forecast', Help='Indicates if the sales order line will generate or not a demand for MPS' WHERE AD_Element_ID=55619
;

-- Aug 20, 2012 10:05:35 AM CDT
-- ADEMPIERE-098
UPDATE AD_Process_Para SET ColumnName='IsConsumesForecast', Name='Is Consumes Forecast', Description='Is Consumes Forecast', Help='Indicates if the sales order line will generate or not a demand for MPS', AD_Element_ID=55619 WHERE UPPER(ColumnName)='ISCONSUMESFORECAST' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Aug 20, 2012 10:05:35 AM CDT
-- ADEMPIERE-098
UPDATE AD_Process_Para SET ColumnName='IsConsumesForecast', Name='Is Consumes Forecast', Description='Is Consumes Forecast', Help='Indicates if the sales order line will generate or not a demand for MPS' WHERE AD_Element_ID=55619 AND IsCentrallyMaintained='Y'
;

-- Aug 20, 2012 10:05:35 AM CDT
-- ADEMPIERE-098
UPDATE AD_Field SET Name='Is Consumes Forecast', Description='Is Consumes Forecast', Help='Indicates if the sales order line will generate or not a demand for MPS' WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=55619) AND IsCentrallyMaintained='Y'
;

-- Aug 20, 2012 10:06:09 AM CDT
-- ADEMPIERE-098
UPDATE AD_Column SET DefaultValue='Y',Updated=TO_DATE('2012-08-20 10:06:09','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=64175
;

-- Aug 20, 2012 10:06:11 AM CDT
-- ADEMPIERE-098
ALTER TABLE C_OrderLine MODIFY IsConsumesForecast CHAR(1) DEFAULT 'Y'
;
                                                                                                                  