-- Apr 11, 2013 9:52:34 AM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64858,2019,0,35,53021,'M_AttributeSetInstance_ID',TO_TIMESTAMP('2013-04-11 09:52:30','YYYY-MM-DD HH24:MI:SS'),100,'Product Attribute Set Instance','EE01',10,'The values of the actual Product Attribute Instances.  The product level attributes are defined on Product level.','Y','N','N','N','N','N','N','N','N','N','Attribute Set Instance',TO_TIMESTAMP('2013-04-11 09:52:30','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Apr 11, 2013 9:52:34 AM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64858 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Apr 11, 2013 9:52:57 AM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64859,2019,0,35,53436,'M_AttributeSetInstance_ID',TO_TIMESTAMP('2013-04-11 09:52:56','YYYY-MM-DD HH24:MI:SS'),100,'Product Attribute Set Instance','EE01',10,'The values of the actual Product Attribute Instances.  The product level attributes are defined on Product level.','Y','N','N','N','N','N','N','N','N','N','Attribute Set Instance',TO_TIMESTAMP('2013-04-11 09:52:56','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Apr 11, 2013 9:52:57 AM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64859 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Apr 11, 2013 9:52:58 AM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64860,208,0,19,53436,'C_Project_ID',TO_TIMESTAMP('2013-04-11 09:52:57','YYYY-MM-DD HH24:MI:SS'),100,'Financial Project','EE01',131089,'A Project allows you to track and control internal or external activities.','Y','N','N','N','N','N','N','N','N','N','Project',TO_TIMESTAMP('2013-04-11 09:52:57','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Apr 11, 2013 9:52:58 AM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64860 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Apr 11, 2013 9:52:59 AM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64861,2073,0,19,53436,'C_ProjectPhase_ID',TO_TIMESTAMP('2013-04-11 09:52:58','YYYY-MM-DD HH24:MI:SS'),100,'Phase of a Project','EE01',131089,'Y','N','N','N','N','N','N','N','N','N','Project Phase',TO_TIMESTAMP('2013-04-11 09:52:58','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Apr 11, 2013 9:52:59 AM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64861 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Apr 11, 2013 9:53:00 AM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64862,2074,0,19,53436,'C_ProjectTask_ID',TO_TIMESTAMP('2013-04-11 09:52:59','YYYY-MM-DD HH24:MI:SS'),100,'Actual Project Task in a Phase','EE01',131089,'A Project Task in a Project Phase represents the actual work.','Y','N','N','N','N','N','N','N','N','N','Project Task',TO_TIMESTAMP('2013-04-11 09:52:59','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Apr 11, 2013 9:53:00 AM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64862 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Apr 11, 2013 9:53:01 AM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64863,53281,0,16,53436,'DateStartSchedule',TO_TIMESTAMP('2013-04-11 09:53:00','YYYY-MM-DD HH24:MI:SS'),100,'Scheduled start date for this Order','EE01',35,'Y','N','N','N','N','N','N','N','N','N','Date Start Schedule',TO_TIMESTAMP('2013-04-11 09:53:00','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Apr 11, 2013 9:53:01 AM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64863 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Apr 11, 2013 9:53:02 AM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64864,53278,0,16,53436,'DateFinishSchedule',TO_TIMESTAMP('2013-04-11 09:53:01','YYYY-MM-DD HH24:MI:SS'),100,'Scheduled Finish date for this Order','EE01',35,'Y','N','N','N','N','N','N','N','N','N','Date Finish Schedule',TO_TIMESTAMP('2013-04-11 09:53:01','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Apr 11, 2013 9:53:02 AM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64864 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Apr 11, 2013 9:53:48 AM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64865,2019,0,35,53437,'M_AttributeSetInstance_ID',TO_TIMESTAMP('2013-04-11 09:53:47','YYYY-MM-DD HH24:MI:SS'),100,'Product Attribute Set Instance','EE01',10,'The values of the actual Product Attribute Instances.  The product level attributes are defined on Product level.','Y','N','N','N','N','N','N','N','N','N','Attribute Set Instance',TO_TIMESTAMP('2013-04-11 09:53:47','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Apr 11, 2013 9:53:48 AM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64865 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Apr 11, 2013 9:53:49 AM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64866,208,0,19,53437,'C_Project_ID',TO_TIMESTAMP('2013-04-11 09:53:48','YYYY-MM-DD HH24:MI:SS'),100,'Financial Project','EE01',131089,'A Project allows you to track and control internal or external activities.','Y','N','N','N','N','N','N','N','N','N','Project',TO_TIMESTAMP('2013-04-11 09:53:48','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Apr 11, 2013 9:53:49 AM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64866 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Apr 11, 2013 9:53:49 AM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64867,2073,0,19,53437,'C_ProjectPhase_ID',TO_TIMESTAMP('2013-04-11 09:53:49','YYYY-MM-DD HH24:MI:SS'),100,'Phase of a Project','EE01',131089,'Y','N','N','N','N','N','N','N','N','N','Project Phase',TO_TIMESTAMP('2013-04-11 09:53:49','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Apr 11, 2013 9:53:49 AM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64867 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Apr 11, 2013 9:53:51 AM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64868,2074,0,19,53437,'C_ProjectTask_ID',TO_TIMESTAMP('2013-04-11 09:53:49','YYYY-MM-DD HH24:MI:SS'),100,'Actual Project Task in a Phase','EE01',131089,'A Project Task in a Project Phase represents the actual work.','Y','N','N','N','N','N','N','N','N','N','Project Task',TO_TIMESTAMP('2013-04-11 09:53:49','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Apr 11, 2013 9:53:51 AM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64868 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Apr 11, 2013 9:53:51 AM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64869,53281,0,16,53437,'DateStartSchedule',TO_TIMESTAMP('2013-04-11 09:53:51','YYYY-MM-DD HH24:MI:SS'),100,'Scheduled start date for this Order','EE01',35,'Y','N','N','N','N','N','N','N','N','N','Date Start Schedule',TO_TIMESTAMP('2013-04-11 09:53:51','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Apr 11, 2013 9:53:51 AM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64869 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Apr 11, 2013 9:53:52 AM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64870,53278,0,16,53437,'DateFinishSchedule',TO_TIMESTAMP('2013-04-11 09:53:51','YYYY-MM-DD HH24:MI:SS'),100,'Scheduled Finish date for this Order','EE01',35,'Y','N','N','N','N','N','N','N','N','N','Date Finish Schedule',TO_TIMESTAMP('2013-04-11 09:53:51','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Apr 11, 2013 9:53:52 AM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64870 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Apr 11, 2013 10:11:14 AM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64871,208,0,19,53438,'C_Project_ID',TO_TIMESTAMP('2013-04-11 10:11:12','YYYY-MM-DD HH24:MI:SS'),100,'Financial Project','EE01',131089,'A Project allows you to track and control internal or external activities.','Y','N','N','N','N','N','N','N','N','N','Project',TO_TIMESTAMP('2013-04-11 10:11:12','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Apr 11, 2013 10:11:14 AM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64871 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Apr 11, 2013 10:11:16 AM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64872,2073,0,19,53438,'C_ProjectPhase_ID',TO_TIMESTAMP('2013-04-11 10:11:14','YYYY-MM-DD HH24:MI:SS'),100,'Phase of a Project','EE01',131089,'Y','N','N','N','N','N','N','N','N','N','Project Phase',TO_TIMESTAMP('2013-04-11 10:11:14','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Apr 11, 2013 10:11:16 AM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64872 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Apr 11, 2013 10:11:17 AM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64873,2074,0,19,53438,'C_ProjectTask_ID',TO_TIMESTAMP('2013-04-11 10:11:16','YYYY-MM-DD HH24:MI:SS'),100,'Actual Project Task in a Phase','EE01',131089,'A Project Task in a Project Phase represents the actual work.','Y','N','N','N','N','N','N','N','N','N','Project Task',TO_TIMESTAMP('2013-04-11 10:11:16','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Apr 11, 2013 10:11:17 AM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64873 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Apr 11, 2013 10:11:18 AM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64874,53281,0,16,53438,'DateStartSchedule',TO_TIMESTAMP('2013-04-11 10:11:17','YYYY-MM-DD HH24:MI:SS'),100,'Scheduled start date for this Order','EE01',35,'Y','N','N','N','N','N','N','N','N','N','Date Start Schedule',TO_TIMESTAMP('2013-04-11 10:11:17','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Apr 11, 2013 10:11:18 AM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64874 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Apr 11, 2013 10:11:19 AM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64875,53278,0,16,53438,'DateFinishSchedule',TO_TIMESTAMP('2013-04-11 10:11:18','YYYY-MM-DD HH24:MI:SS'),100,'Scheduled Finish date for this Order','EE01',35,'Y','N','N','N','N','N','N','N','N','N','Date Finish Schedule',TO_TIMESTAMP('2013-04-11 10:11:18','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Apr 11, 2013 10:11:19 AM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64875 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Apr 11, 2013 10:11:55 AM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64876,208,0,19,53439,'C_Project_ID',TO_TIMESTAMP('2013-04-11 10:11:54','YYYY-MM-DD HH24:MI:SS'),100,'Financial Project','EE01',131089,'A Project allows you to track and control internal or external activities.','Y','N','N','N','N','N','N','N','N','N','Project',TO_TIMESTAMP('2013-04-11 10:11:54','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Apr 11, 2013 10:11:55 AM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64876 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Apr 11, 2013 10:11:56 AM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64877,2073,0,19,53439,'C_ProjectPhase_ID',TO_TIMESTAMP('2013-04-11 10:11:55','YYYY-MM-DD HH24:MI:SS'),100,'Phase of a Project','EE01',131089,'Y','N','N','N','N','N','N','N','N','N','Project Phase',TO_TIMESTAMP('2013-04-11 10:11:55','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Apr 11, 2013 10:11:56 AM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64877 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Apr 11, 2013 10:11:57 AM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64878,2074,0,19,53439,'C_ProjectTask_ID',TO_TIMESTAMP('2013-04-11 10:11:56','YYYY-MM-DD HH24:MI:SS'),100,'Actual Project Task in a Phase','EE01',131089,'A Project Task in a Project Phase represents the actual work.','Y','N','N','N','N','N','N','N','N','N','Project Task',TO_TIMESTAMP('2013-04-11 10:11:56','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Apr 11, 2013 10:11:57 AM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64878 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Apr 11, 2013 10:11:58 AM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64879,53281,0,16,53439,'DateStartSchedule',TO_TIMESTAMP('2013-04-11 10:11:57','YYYY-MM-DD HH24:MI:SS'),100,'Scheduled start date for this Order','EE01',35,'Y','N','N','N','N','N','N','N','N','N','Date Start Schedule',TO_TIMESTAMP('2013-04-11 10:11:57','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Apr 11, 2013 10:11:58 AM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64879 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Apr 11, 2013 10:11:59 AM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,64880,53278,0,16,53439,'DateFinishSchedule',TO_TIMESTAMP('2013-04-11 10:11:58','YYYY-MM-DD HH24:MI:SS'),100,'Scheduled Finish date for this Order','EE01',35,'Y','N','N','N','N','N','N','N','N','N','Date Finish Schedule',TO_TIMESTAMP('2013-04-11 10:11:58','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Apr 11, 2013 10:11:59 AM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64880 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Apr 11, 2013 10:13:02 AM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,64858,66117,0,53180,TO_TIMESTAMP('2013-04-11 10:13:00','YYYY-MM-DD HH24:MI:SS'),100,'Product Attribute Set Instance',10,'EE01','The values of the actual Product Attribute Instances.  The product level attributes are defined on Product level.','Y','Y','Y','N','N','N','N','N','Attribute Set Instance',TO_TIMESTAMP('2013-04-11 10:13:00','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 11, 2013 10:13:02 AM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=66117 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 11, 2013 10:13:03 AM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,64228,66118,0,53180,TO_TIMESTAMP('2013-04-11 10:13:02','YYYY-MM-DD HH24:MI:SS'),100,'Financial Project',22,'EE01','A Project allows you to track and control internal or external activities.','Y','Y','Y','N','N','N','N','N','Project',TO_TIMESTAMP('2013-04-11 10:13:02','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 11, 2013 10:13:03 AM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=66118 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 11, 2013 10:14:59 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=310,Updated=TO_TIMESTAMP('2013-04-11 10:14:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=66118
;

-- Apr 11, 2013 10:14:59 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=360,Updated=TO_TIMESTAMP('2013-04-11 10:14:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56435
;

-- Apr 11, 2013 10:14:59 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=370,Updated=TO_TIMESTAMP('2013-04-11 10:14:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56437
;

-- Apr 11, 2013 10:14:59 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=380,Updated=TO_TIMESTAMP('2013-04-11 10:14:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56414
;

-- Apr 11, 2013 10:14:59 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=390,Updated=TO_TIMESTAMP('2013-04-11 10:14:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56419
;

-- Apr 11, 2013 10:14:59 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=400,Updated=TO_TIMESTAMP('2013-04-11 10:14:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56415
;

-- Apr 11, 2013 10:14:59 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=410,Updated=TO_TIMESTAMP('2013-04-11 10:14:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56420
;

-- Apr 11, 2013 10:14:59 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=420,Updated=TO_TIMESTAMP('2013-04-11 10:14:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56417
;

-- Apr 11, 2013 10:14:59 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=430,Updated=TO_TIMESTAMP('2013-04-11 10:14:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56416
;

-- Apr 11, 2013 10:15:00 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=440,Updated=TO_TIMESTAMP('2013-04-11 10:15:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56418
;

-- Apr 11, 2013 10:15:00 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=450,Updated=TO_TIMESTAMP('2013-04-11 10:15:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=66117
;

-- Apr 11, 2013 10:15:19 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=80,Updated=TO_TIMESTAMP('2013-04-11 10:15:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=66117
;

-- Apr 11, 2013 10:15:19 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=90,Updated=TO_TIMESTAMP('2013-04-11 10:15:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=65120
;

-- Apr 11, 2013 10:15:19 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=100,Updated=TO_TIMESTAMP('2013-04-11 10:15:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=65113
;

-- Apr 11, 2013 10:15:19 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=110,Updated=TO_TIMESTAMP('2013-04-11 10:15:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=65121
;

-- Apr 11, 2013 10:15:19 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=120,Updated=TO_TIMESTAMP('2013-04-11 10:15:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56440
;

-- Apr 11, 2013 10:15:19 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=130,Updated=TO_TIMESTAMP('2013-04-11 10:15:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56445
;

-- Apr 11, 2013 10:15:19 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=140,Updated=TO_TIMESTAMP('2013-04-11 10:15:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=58573
;

-- Apr 11, 2013 10:15:19 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=150,Updated=TO_TIMESTAMP('2013-04-11 10:15:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=58574
;

-- Apr 11, 2013 10:15:20 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=160,Updated=TO_TIMESTAMP('2013-04-11 10:15:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=65181
;

-- Apr 11, 2013 10:15:20 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=170,Updated=TO_TIMESTAMP('2013-04-11 10:15:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=65180
;

-- Apr 11, 2013 10:15:20 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=180,Updated=TO_TIMESTAMP('2013-04-11 10:15:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=58572
;

-- Apr 11, 2013 10:15:20 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=190,Updated=TO_TIMESTAMP('2013-04-11 10:15:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56424
;

-- Apr 11, 2013 10:15:20 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=200,Updated=TO_TIMESTAMP('2013-04-11 10:15:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56443
;

-- Apr 11, 2013 10:15:20 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=210,Updated=TO_TIMESTAMP('2013-04-11 10:15:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56432
;

-- Apr 11, 2013 10:15:20 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=220,Updated=TO_TIMESTAMP('2013-04-11 10:15:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56431
;

-- Apr 11, 2013 10:15:20 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=230,Updated=TO_TIMESTAMP('2013-04-11 10:15:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56441
;

-- Apr 11, 2013 10:15:20 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=240,Updated=TO_TIMESTAMP('2013-04-11 10:15:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56427
;

-- Apr 11, 2013 10:15:20 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=250,Updated=TO_TIMESTAMP('2013-04-11 10:15:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56428
;

-- Apr 11, 2013 10:15:20 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=260,Updated=TO_TIMESTAMP('2013-04-11 10:15:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56422
;

-- Apr 11, 2013 10:15:20 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=270,Updated=TO_TIMESTAMP('2013-04-11 10:15:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56423
;

-- Apr 11, 2013 10:15:20 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=280,Updated=TO_TIMESTAMP('2013-04-11 10:15:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56438
;

-- Apr 11, 2013 10:15:20 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=290,Updated=TO_TIMESTAMP('2013-04-11 10:15:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56439
;

-- Apr 11, 2013 10:15:20 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=300,Updated=TO_TIMESTAMP('2013-04-11 10:15:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56425
;

-- Apr 11, 2013 10:15:20 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=310,Updated=TO_TIMESTAMP('2013-04-11 10:15:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56426
;

-- Apr 11, 2013 10:15:20 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=320,Updated=TO_TIMESTAMP('2013-04-11 10:15:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=66118
;

-- Apr 11, 2013 10:15:20 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=330,Updated=TO_TIMESTAMP('2013-04-11 10:15:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=65185
;

-- Apr 11, 2013 10:15:20 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=340,Updated=TO_TIMESTAMP('2013-04-11 10:15:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=65186
;

-- Apr 11, 2013 10:15:20 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=350,Updated=TO_TIMESTAMP('2013-04-11 10:15:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56434
;

-- Apr 11, 2013 10:15:21 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=360,Updated=TO_TIMESTAMP('2013-04-11 10:15:21','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56412
;

-- Apr 11, 2013 10:15:21 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=370,Updated=TO_TIMESTAMP('2013-04-11 10:15:21','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56435
;

-- Apr 11, 2013 10:15:21 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=380,Updated=TO_TIMESTAMP('2013-04-11 10:15:21','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56437
;

-- Apr 11, 2013 10:15:21 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=390,Updated=TO_TIMESTAMP('2013-04-11 10:15:21','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56414
;

-- Apr 11, 2013 10:15:21 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=400,Updated=TO_TIMESTAMP('2013-04-11 10:15:21','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56419
;

-- Apr 11, 2013 10:15:21 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=410,Updated=TO_TIMESTAMP('2013-04-11 10:15:21','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56415
;

-- Apr 11, 2013 10:15:21 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=420,Updated=TO_TIMESTAMP('2013-04-11 10:15:21','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56420
;

-- Apr 11, 2013 10:15:21 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=430,Updated=TO_TIMESTAMP('2013-04-11 10:15:21','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56417
;

-- Apr 11, 2013 10:15:21 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=440,Updated=TO_TIMESTAMP('2013-04-11 10:15:21','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56416
;

-- Apr 11, 2013 10:15:21 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=450,Updated=TO_TIMESTAMP('2013-04-11 10:15:21','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56418
;

-- Apr 11, 2013 10:15:31 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_TIMESTAMP('2013-04-11 10:15:31','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=66117
;

-- Apr 11, 2013 10:17:27 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsSameLine='N',Updated=TO_TIMESTAMP('2013-04-11 10:17:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=65120
;

-- Apr 11, 2013 10:17:56 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=350,Updated=TO_TIMESTAMP('2013-04-11 10:17:56','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56435
;

-- Apr 11, 2013 10:17:56 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=360,Updated=TO_TIMESTAMP('2013-04-11 10:17:56','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56434
;

-- Apr 11, 2013 10:17:56 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=370,Updated=TO_TIMESTAMP('2013-04-11 10:17:56','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56412
;

-- Apr 11, 2013 10:19:03 AM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,64859,66119,0,53567,TO_TIMESTAMP('2013-04-11 10:19:01','YYYY-MM-DD HH24:MI:SS'),100,'Product Attribute Set Instance',10,'EE01','The values of the actual Product Attribute Instances.  The product level attributes are defined on Product level.','Y','Y','Y','N','N','N','N','N','Attribute Set Instance',TO_TIMESTAMP('2013-04-11 10:19:01','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 11, 2013 10:19:03 AM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=66119 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 11, 2013 10:19:04 AM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,64864,66120,0,53567,TO_TIMESTAMP('2013-04-11 10:19:03','YYYY-MM-DD HH24:MI:SS'),100,'Scheduled Finish date for this Order',35,'EE01','Y','Y','Y','N','N','N','N','N','Date Finish Schedule',TO_TIMESTAMP('2013-04-11 10:19:03','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 11, 2013 10:19:04 AM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=66120 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 11, 2013 10:19:05 AM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,64863,66121,0,53567,TO_TIMESTAMP('2013-04-11 10:19:04','YYYY-MM-DD HH24:MI:SS'),100,'Scheduled start date for this Order',35,'EE01','Y','Y','Y','N','N','N','N','N','Date Start Schedule',TO_TIMESTAMP('2013-04-11 10:19:04','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 11, 2013 10:19:05 AM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=66121 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 11, 2013 10:19:09 AM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,64860,66122,0,53567,TO_TIMESTAMP('2013-04-11 10:19:05','YYYY-MM-DD HH24:MI:SS'),100,'Financial Project',131089,'EE01','A Project allows you to track and control internal or external activities.','Y','Y','Y','N','N','N','N','N','Project',TO_TIMESTAMP('2013-04-11 10:19:05','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 11, 2013 10:19:09 AM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=66122 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 11, 2013 10:19:10 AM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,64861,66123,0,53567,TO_TIMESTAMP('2013-04-11 10:19:09','YYYY-MM-DD HH24:MI:SS'),100,'Phase of a Project',131089,'EE01','Y','Y','Y','N','N','N','N','N','Project Phase',TO_TIMESTAMP('2013-04-11 10:19:09','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 11, 2013 10:19:10 AM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=66123 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 11, 2013 10:19:11 AM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,64862,66124,0,53567,TO_TIMESTAMP('2013-04-11 10:19:10','YYYY-MM-DD HH24:MI:SS'),100,'Actual Project Task in a Phase',131089,'EE01','A Project Task in a Project Phase represents the actual work.','Y','Y','Y','N','N','N','N','N','Project Task',TO_TIMESTAMP('2013-04-11 10:19:10','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 11, 2013 10:19:11 AM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=66124 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 11, 2013 10:20:47 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=130,Updated=TO_TIMESTAMP('2013-04-11 10:20:47','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=66120
;

-- Apr 11, 2013 10:20:47 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=140,Updated=TO_TIMESTAMP('2013-04-11 10:20:47','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=66121
;

-- Apr 11, 2013 10:20:48 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=150,Updated=TO_TIMESTAMP('2013-04-11 10:20:48','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=65250
;

-- Apr 11, 2013 10:20:48 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=160,Updated=TO_TIMESTAMP('2013-04-11 10:20:48','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=65251
;

-- Apr 11, 2013 10:20:48 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=170,Updated=TO_TIMESTAMP('2013-04-11 10:20:48','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=65252
;

-- Apr 11, 2013 10:20:48 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=180,Updated=TO_TIMESTAMP('2013-04-11 10:20:48','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=65253
;

-- Apr 11, 2013 10:20:48 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=190,Updated=TO_TIMESTAMP('2013-04-11 10:20:48','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=65254
;

-- Apr 11, 2013 10:20:48 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=200,Updated=TO_TIMESTAMP('2013-04-11 10:20:48','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=65255
;

-- Apr 11, 2013 10:20:48 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=210,Updated=TO_TIMESTAMP('2013-04-11 10:20:48','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=65256
;

-- Apr 11, 2013 10:20:48 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=220,Updated=TO_TIMESTAMP('2013-04-11 10:20:48','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=65257
;

-- Apr 11, 2013 10:20:48 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=230,Updated=TO_TIMESTAMP('2013-04-11 10:20:48','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=65258
;

-- Apr 11, 2013 10:20:48 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=240,Updated=TO_TIMESTAMP('2013-04-11 10:20:48','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=65259
;

-- Apr 11, 2013 10:20:48 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=250,Updated=TO_TIMESTAMP('2013-04-11 10:20:48','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=65260
;

-- Apr 11, 2013 10:20:48 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=260,Updated=TO_TIMESTAMP('2013-04-11 10:20:48','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=65261
;

-- Apr 11, 2013 10:20:48 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=270,Updated=TO_TIMESTAMP('2013-04-11 10:20:48','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=66119
;

-- Apr 11, 2013 10:20:48 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=280,Updated=TO_TIMESTAMP('2013-04-11 10:20:48','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=66122
;

-- Apr 11, 2013 10:20:48 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=290,Updated=TO_TIMESTAMP('2013-04-11 10:20:48','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=66123
;

-- Apr 11, 2013 10:20:48 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=300,Updated=TO_TIMESTAMP('2013-04-11 10:20:48','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=66124
;

-- Apr 11, 2013 10:21:02 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=160,Updated=TO_TIMESTAMP('2013-04-11 10:21:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=66119
;

-- Apr 11, 2013 10:21:02 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=170,Updated=TO_TIMESTAMP('2013-04-11 10:21:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=65251
;

-- Apr 11, 2013 10:21:02 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=180,Updated=TO_TIMESTAMP('2013-04-11 10:21:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=65252
;

-- Apr 11, 2013 10:21:02 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=190,Updated=TO_TIMESTAMP('2013-04-11 10:21:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=65253
;

-- Apr 11, 2013 10:21:02 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=200,Updated=TO_TIMESTAMP('2013-04-11 10:21:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=65254
;

-- Apr 11, 2013 10:21:02 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=210,Updated=TO_TIMESTAMP('2013-04-11 10:21:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=65255
;

-- Apr 11, 2013 10:21:02 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=220,Updated=TO_TIMESTAMP('2013-04-11 10:21:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=65256
;

-- Apr 11, 2013 10:21:02 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=230,Updated=TO_TIMESTAMP('2013-04-11 10:21:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=65257
;

-- Apr 11, 2013 10:21:02 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=240,Updated=TO_TIMESTAMP('2013-04-11 10:21:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=65258
;

-- Apr 11, 2013 10:21:03 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=250,Updated=TO_TIMESTAMP('2013-04-11 10:21:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=65259
;

-- Apr 11, 2013 10:21:03 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=260,Updated=TO_TIMESTAMP('2013-04-11 10:21:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=65260
;

-- Apr 11, 2013 10:21:03 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=270,Updated=TO_TIMESTAMP('2013-04-11 10:21:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=65261
;

-- Apr 11, 2013 10:21:53 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=150,Updated=TO_TIMESTAMP('2013-04-11 10:21:53','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=66122
;

-- Apr 11, 2013 10:21:53 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=160,Updated=TO_TIMESTAMP('2013-04-11 10:21:53','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=66123
;

-- Apr 11, 2013 10:21:53 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=170,Updated=TO_TIMESTAMP('2013-04-11 10:21:53','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=66124
;

-- Apr 11, 2013 10:21:53 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=180,Updated=TO_TIMESTAMP('2013-04-11 10:21:53','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=65250
;

-- Apr 11, 2013 10:21:53 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=190,Updated=TO_TIMESTAMP('2013-04-11 10:21:53','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=66119
;

-- Apr 11, 2013 10:21:53 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=200,Updated=TO_TIMESTAMP('2013-04-11 10:21:53','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=65251
;

-- Apr 11, 2013 10:21:53 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=210,Updated=TO_TIMESTAMP('2013-04-11 10:21:53','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=65252
;

-- Apr 11, 2013 10:21:53 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=220,Updated=TO_TIMESTAMP('2013-04-11 10:21:53','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=65253
;

-- Apr 11, 2013 10:21:53 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=230,Updated=TO_TIMESTAMP('2013-04-11 10:21:53','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=65254
;

-- Apr 11, 2013 10:21:53 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=240,Updated=TO_TIMESTAMP('2013-04-11 10:21:53','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=65255
;

-- Apr 11, 2013 10:21:54 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=250,Updated=TO_TIMESTAMP('2013-04-11 10:21:54','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=65256
;

-- Apr 11, 2013 10:21:54 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=260,Updated=TO_TIMESTAMP('2013-04-11 10:21:54','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=65257
;

-- Apr 11, 2013 10:21:54 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=270,Updated=TO_TIMESTAMP('2013-04-11 10:21:54','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=65258
;

-- Apr 11, 2013 10:21:54 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=280,Updated=TO_TIMESTAMP('2013-04-11 10:21:54','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=65259
;

-- Apr 11, 2013 10:21:54 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=290,Updated=TO_TIMESTAMP('2013-04-11 10:21:54','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=65260
;

-- Apr 11, 2013 10:21:54 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=300,Updated=TO_TIMESTAMP('2013-04-11 10:21:54','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=65261
;

-- Apr 11, 2013 10:22:02 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_TIMESTAMP('2013-04-11 10:22:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=66121
;

-- Apr 11, 2013 10:22:09 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_TIMESTAMP('2013-04-11 10:22:09','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=66123
;

-- Apr 11, 2013 10:22:21 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_TIMESTAMP('2013-04-11 10:22:21','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=66119
;

-- Apr 11, 2013 10:22:25 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsSameLine='N',Updated=TO_TIMESTAMP('2013-04-11 10:22:25','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=65251
;

-- Apr 11, 2013 10:22:29 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_TIMESTAMP('2013-04-11 10:22:29','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=65252
;

-- Apr 11, 2013 10:22:35 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsSameLine='N',Updated=TO_TIMESTAMP('2013-04-11 10:22:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=65253
;

-- Apr 11, 2013 10:24:49 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=230,Updated=TO_TIMESTAMP('2013-04-11 10:24:49','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=65260
;

-- Apr 11, 2013 10:24:49 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=240,Updated=TO_TIMESTAMP('2013-04-11 10:24:49','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=65254
;

-- Apr 11, 2013 10:24:49 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=250,Updated=TO_TIMESTAMP('2013-04-11 10:24:49','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=65255
;

-- Apr 11, 2013 10:24:49 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=260,Updated=TO_TIMESTAMP('2013-04-11 10:24:49','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=65256
;

-- Apr 11, 2013 10:24:49 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=270,Updated=TO_TIMESTAMP('2013-04-11 10:24:49','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=65257
;

-- Apr 11, 2013 10:24:49 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=280,Updated=TO_TIMESTAMP('2013-04-11 10:24:49','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=65258
;

-- Apr 11, 2013 10:24:49 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=290,Updated=TO_TIMESTAMP('2013-04-11 10:24:49','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=65259
;

-- Apr 11, 2013 10:25:05 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_TIMESTAMP('2013-04-11 10:25:05','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=65260
;

-- Apr 11, 2013 10:25:17 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET DisplayLength=7,Updated=TO_TIMESTAMP('2013-04-11 10:25:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=66120
;

-- Apr 11, 2013 10:25:21 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET DisplayLength=7,Updated=TO_TIMESTAMP('2013-04-11 10:25:21','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=66121
;

-- Apr 11, 2013 10:25:28 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET DisplayLength=29,Updated=TO_TIMESTAMP('2013-04-11 10:25:28','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=66122
;

-- Apr 11, 2013 10:25:33 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET DisplayLength=29,Updated=TO_TIMESTAMP('2013-04-11 10:25:33','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=66123
;

-- Apr 11, 2013 10:25:40 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET DisplayLength=29,Updated=TO_TIMESTAMP('2013-04-11 10:25:40','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=66124
;

-- Apr 11, 2013 10:25:51 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Column SET FieldLength=22,Updated=TO_TIMESTAMP('2013-04-11 10:25:51','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=64861
;

-- Apr 11, 2013 10:25:54 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Column SET FieldLength=22,Updated=TO_TIMESTAMP('2013-04-11 10:25:54','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=64862
;

-- Apr 11, 2013 10:25:56 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Column SET FieldLength=22,Updated=TO_TIMESTAMP('2013-04-11 10:25:56','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=64860
;

-- Apr 11, 2013 10:27:24 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET DisplayLength=29,Updated=TO_TIMESTAMP('2013-04-11 10:27:24','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=65253
;

-- Apr 11, 2013 10:28:38 AM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,64865,66125,0,53569,TO_TIMESTAMP('2013-04-11 10:28:36','YYYY-MM-DD HH24:MI:SS'),100,'Product Attribute Set Instance',10,'EE01','The values of the actual Product Attribute Instances.  The product level attributes are defined on Product level.','Y','Y','Y','N','N','N','N','N','Attribute Set Instance',TO_TIMESTAMP('2013-04-11 10:28:36','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 11, 2013 10:28:38 AM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=66125 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 11, 2013 10:28:39 AM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,64870,66126,0,53569,TO_TIMESTAMP('2013-04-11 10:28:38','YYYY-MM-DD HH24:MI:SS'),100,'Scheduled Finish date for this Order',35,'EE01','Y','Y','Y','N','N','N','N','N','Date Finish Schedule',TO_TIMESTAMP('2013-04-11 10:28:38','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 11, 2013 10:28:39 AM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=66126 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 11, 2013 10:28:40 AM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,64869,66127,0,53569,TO_TIMESTAMP('2013-04-11 10:28:39','YYYY-MM-DD HH24:MI:SS'),100,'Scheduled start date for this Order',35,'EE01','Y','Y','Y','N','N','N','N','N','Date Start Schedule',TO_TIMESTAMP('2013-04-11 10:28:39','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 11, 2013 10:28:40 AM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=66127 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 11, 2013 10:28:41 AM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,64866,66128,0,53569,TO_TIMESTAMP('2013-04-11 10:28:40','YYYY-MM-DD HH24:MI:SS'),100,'Financial Project',131089,'EE01','A Project allows you to track and control internal or external activities.','Y','Y','Y','N','N','N','N','N','Project',TO_TIMESTAMP('2013-04-11 10:28:40','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 11, 2013 10:28:41 AM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=66128 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 11, 2013 10:28:42 AM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,64867,66129,0,53569,TO_TIMESTAMP('2013-04-11 10:28:41','YYYY-MM-DD HH24:MI:SS'),100,'Phase of a Project',131089,'EE01','Y','Y','Y','N','N','N','N','N','Project Phase',TO_TIMESTAMP('2013-04-11 10:28:41','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 11, 2013 10:28:42 AM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=66129 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 11, 2013 10:28:43 AM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,64868,66130,0,53569,TO_TIMESTAMP('2013-04-11 10:28:42','YYYY-MM-DD HH24:MI:SS'),100,'Actual Project Task in a Phase',131089,'EE01','A Project Task in a Project Phase represents the actual work.','Y','Y','Y','N','N','N','N','N','Project Task',TO_TIMESTAMP('2013-04-11 10:28:42','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 11, 2013 10:28:43 AM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=66130 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 11, 2013 10:29:32 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=130,Updated=TO_TIMESTAMP('2013-04-11 10:29:32','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=66126
;

-- Apr 11, 2013 10:29:32 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=140,Updated=TO_TIMESTAMP('2013-04-11 10:29:32','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=66127
;

-- Apr 11, 2013 10:29:32 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=150,Updated=TO_TIMESTAMP('2013-04-11 10:29:32','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=66128
;

-- Apr 11, 2013 10:29:32 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=160,Updated=TO_TIMESTAMP('2013-04-11 10:29:32','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=66129
;

-- Apr 11, 2013 10:29:32 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=170,Updated=TO_TIMESTAMP('2013-04-11 10:29:32','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=66130
;

-- Apr 11, 2013 10:29:33 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=180,Updated=TO_TIMESTAMP('2013-04-11 10:29:33','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=65272
;

-- Apr 11, 2013 10:29:33 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=190,Updated=TO_TIMESTAMP('2013-04-11 10:29:33','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=66125
;

-- Apr 11, 2013 10:29:33 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=200,Updated=TO_TIMESTAMP('2013-04-11 10:29:33','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=65273
;

-- Apr 11, 2013 10:29:33 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=210,Updated=TO_TIMESTAMP('2013-04-11 10:29:33','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=65274
;

-- Apr 11, 2013 10:29:33 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=220,Updated=TO_TIMESTAMP('2013-04-11 10:29:33','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=65275
;

-- Apr 11, 2013 10:29:33 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=240,Updated=TO_TIMESTAMP('2013-04-11 10:29:33','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=65276
;

-- Apr 11, 2013 10:29:33 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=250,Updated=TO_TIMESTAMP('2013-04-11 10:29:33','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=65277
;

-- Apr 11, 2013 10:29:33 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=260,Updated=TO_TIMESTAMP('2013-04-11 10:29:33','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=65278
;

-- Apr 11, 2013 10:29:33 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=270,Updated=TO_TIMESTAMP('2013-04-11 10:29:33','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=65279
;

-- Apr 11, 2013 10:29:33 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=280,Updated=TO_TIMESTAMP('2013-04-11 10:29:33','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=65280
;

-- Apr 11, 2013 10:29:33 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=290,Updated=TO_TIMESTAMP('2013-04-11 10:29:33','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=65281
;

-- Apr 11, 2013 10:29:33 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=300,Updated=TO_TIMESTAMP('2013-04-11 10:29:33','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=65283
;

-- Apr 11, 2013 10:29:47 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET DisplayLength=29,Updated=TO_TIMESTAMP('2013-04-11 10:29:47','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=66126
;

-- Apr 11, 2013 10:29:51 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET DisplayLength=29,Updated=TO_TIMESTAMP('2013-04-11 10:29:51','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=66127
;

-- Apr 11, 2013 10:29:58 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET DisplayLength=29,Updated=TO_TIMESTAMP('2013-04-11 10:29:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=66128
;

-- Apr 11, 2013 10:30:01 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET DisplayLength=29,Updated=TO_TIMESTAMP('2013-04-11 10:30:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=66129
;

-- Apr 11, 2013 10:30:06 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET DisplayLength=29,Updated=TO_TIMESTAMP('2013-04-11 10:30:06','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=66130
;

-- Apr 11, 2013 10:30:14 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_TIMESTAMP('2013-04-11 10:30:14','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=66125
;

-- Apr 11, 2013 10:30:21 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_TIMESTAMP('2013-04-11 10:30:21','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=66129
;

-- Apr 11, 2013 10:30:33 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=130,Updated=TO_TIMESTAMP('2013-04-11 10:30:33','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=66127
;

-- Apr 11, 2013 10:30:33 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=140,Updated=TO_TIMESTAMP('2013-04-11 10:30:33','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=66126
;

-- Apr 11, 2013 10:30:41 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_TIMESTAMP('2013-04-11 10:30:41','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=66126
;

-- Apr 11, 2013 10:31:25 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsSameLine='N',Updated=TO_TIMESTAMP('2013-04-11 10:31:25','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=65273
;

-- Apr 11, 2013 10:31:27 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_TIMESTAMP('2013-04-11 10:31:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=65274
;

-- Apr 11, 2013 10:31:30 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsSameLine='N',Updated=TO_TIMESTAMP('2013-04-11 10:31:30','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=65275
;

-- Apr 11, 2013 10:31:34 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_TIMESTAMP('2013-04-11 10:31:34','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=65282
;

-- Apr 11, 2013 10:32:50 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=130,Updated=TO_TIMESTAMP('2013-04-11 10:32:50','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=66121
;

-- Apr 11, 2013 10:32:50 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=140,Updated=TO_TIMESTAMP('2013-04-11 10:32:50','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=66120
;

-- Apr 11, 2013 10:33:01 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsSameLine='N',Updated=TO_TIMESTAMP('2013-04-11 10:33:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=66121
;

-- Apr 11, 2013 10:33:03 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_TIMESTAMP('2013-04-11 10:33:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=66120
;

-- Apr 11, 2013 10:33:22 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Column SET FieldLength=22,Updated=TO_TIMESTAMP('2013-04-11 10:33:22','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=64867
;

-- Apr 11, 2013 10:33:25 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Column SET FieldLength=22,Updated=TO_TIMESTAMP('2013-04-11 10:33:25','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=64868
;

-- Apr 11, 2013 10:33:29 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Column SET FieldLength=22,Updated=TO_TIMESTAMP('2013-04-11 10:33:29','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=64866
;

-- Apr 11, 2013 10:35:05 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_TreeNodeMM SET Parent_ID=53034, SeqNo=7,Updated=TO_TIMESTAMP('2013-04-11 10:35:05','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Tree_ID=10 AND Node_ID=53040
;

-- Apr 11, 2013 10:35:05 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_TreeNodeMM SET Parent_ID=53034, SeqNo=8,Updated=TO_TIMESTAMP('2013-04-11 10:35:05','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Tree_ID=10 AND Node_ID=53224
;

-- Apr 11, 2013 10:35:05 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_TreeNodeMM SET Parent_ID=53034, SeqNo=9,Updated=TO_TIMESTAMP('2013-04-11 10:35:05','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Tree_ID=10 AND Node_ID=53451
;

-- Apr 11, 2013 10:35:05 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_TreeNodeMM SET Parent_ID=53034, SeqNo=10,Updated=TO_TIMESTAMP('2013-04-11 10:35:05','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Tree_ID=10 AND Node_ID=53257
;

-- Apr 11, 2013 10:35:05 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_TreeNodeMM SET Parent_ID=53034, SeqNo=13,Updated=TO_TIMESTAMP('2013-04-11 10:35:05','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Tree_ID=10 AND Node_ID=53041
;

-- Apr 11, 2013 10:35:05 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_TreeNodeMM SET Parent_ID=53034, SeqNo=14,Updated=TO_TIMESTAMP('2013-04-11 10:35:05','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Tree_ID=10 AND Node_ID=53459
;

-- Apr 11, 2013 10:35:05 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_TreeNodeMM SET Parent_ID=53034, SeqNo=15,Updated=TO_TIMESTAMP('2013-04-11 10:35:05','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Tree_ID=10 AND Node_ID=53042
;

-- Apr 11, 2013 10:35:05 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_TreeNodeMM SET Parent_ID=53034, SeqNo=16,Updated=TO_TIMESTAMP('2013-04-11 10:35:05','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Tree_ID=10 AND Node_ID=53459
;

-- Apr 11, 2013 10:35:05 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_TreeNodeMM SET Parent_ID=53034, SeqNo=17,Updated=TO_TIMESTAMP('2013-04-11 10:35:05','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Tree_ID=10 AND Node_ID=53452
;

-- Apr 11, 2013 10:35:06 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_TreeNodeMM SET Parent_ID=53034, SeqNo=18,Updated=TO_TIMESTAMP('2013-04-11 10:35:06','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Tree_ID=10 AND Node_ID=53457
;

-- Apr 11, 2013 10:35:06 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_TreeNodeMM SET Parent_ID=53034, SeqNo=19,Updated=TO_TIMESTAMP('2013-04-11 10:35:06','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Tree_ID=10 AND Node_ID=53454
;

-- Apr 11, 2013 10:35:06 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_TreeNodeMM SET Parent_ID=53034, SeqNo=20,Updated=TO_TIMESTAMP('2013-04-11 10:35:06','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Tree_ID=10 AND Node_ID=53453
;

-- Apr 11, 2013 10:35:19 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_TreeNodeMM SET Parent_ID=53034, SeqNo=13,Updated=TO_TIMESTAMP('2013-04-11 10:35:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Tree_ID=10 AND Node_ID=53459
;

-- Apr 11, 2013 10:35:19 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_TreeNodeMM SET Parent_ID=53034, SeqNo=14,Updated=TO_TIMESTAMP('2013-04-11 10:35:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Tree_ID=10 AND Node_ID=53041
;

-- Apr 11, 2013 10:35:19 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_TreeNodeMM SET Parent_ID=53034, SeqNo=15,Updated=TO_TIMESTAMP('2013-04-11 10:35:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Tree_ID=10 AND Node_ID=53459
;

-- Apr 11, 2013 10:35:19 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_TreeNodeMM SET Parent_ID=53034, SeqNo=16,Updated=TO_TIMESTAMP('2013-04-11 10:35:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Tree_ID=10 AND Node_ID=53042
;

-- Apr 11, 2013 10:35:22 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_TreeNodeMM SET Parent_ID=53034, SeqNo=13,Updated=TO_TIMESTAMP('2013-04-11 10:35:22','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Tree_ID=10 AND Node_ID=53459
;

-- Apr 11, 2013 10:35:22 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_TreeNodeMM SET Parent_ID=53034, SeqNo=14,Updated=TO_TIMESTAMP('2013-04-11 10:35:22','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Tree_ID=10 AND Node_ID=53457
;

-- Apr 11, 2013 10:35:22 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_TreeNodeMM SET Parent_ID=53034, SeqNo=15,Updated=TO_TIMESTAMP('2013-04-11 10:35:22','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Tree_ID=10 AND Node_ID=53041
;

-- Apr 11, 2013 10:35:22 AM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_TreeNodeMM SET Parent_ID=53034, SeqNo=18,Updated=TO_TIMESTAMP('2013-04-11 10:35:22','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Tree_ID=10 AND Node_ID=53457
;

-- Apr 11, 2013 11:03:13 AM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,IsActive,Name,Updated,UpdatedBy) VALUES (0,64861,0,51394,50029,50010,'DEMAND_C_ProjectPhase_ID','demand.C_ProjectPhase_ID',TO_TIMESTAMP('2013-04-11 11:03:10','YYYY-MM-DD HH24:MI:SS'),100,'Phase of a Project','EE01','Y','Project Phase',TO_TIMESTAMP('2013-04-11 11:03:10','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 11, 2013 11:03:21 AM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,64862,0,51395,50029,50010,'DEMAND_C_ProjectTask_ID','demand.C_ProjectTask_ID',TO_TIMESTAMP('2013-04-11 11:03:19','YYYY-MM-DD HH24:MI:SS'),100,'Actual Project Task in a Phase','EE01','A Project Task in a Project Phase represents the actual work.','Y','Project Task',TO_TIMESTAMP('2013-04-11 11:03:19','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 11, 2013 11:03:27 AM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,64860,0,51396,50029,50010,'DEMAND_C_Project_ID','demand.C_Project_ID',TO_TIMESTAMP('2013-04-11 11:03:25','YYYY-MM-DD HH24:MI:SS'),100,'Financial Project','EE01','A Project allows you to track and control internal or external activities.','Y','Project',TO_TIMESTAMP('2013-04-11 11:03:25','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 11, 2013 11:03:32 AM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,IsActive,Name,Updated,UpdatedBy) VALUES (0,64864,0,51397,50029,50010,'DEMAND_DateFinishSchedule','demand.DateFinishSchedule',TO_TIMESTAMP('2013-04-11 11:03:32','YYYY-MM-DD HH24:MI:SS'),100,'Scheduled Finish date for this Order','EE01','Y','Date Finish Schedule',TO_TIMESTAMP('2013-04-11 11:03:32','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 11, 2013 11:03:44 AM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,IsActive,Name,Updated,UpdatedBy) VALUES (0,64863,0,51398,50029,50010,'DEMAND_DateStartSchedule','demand.DateStartSchedule',TO_TIMESTAMP('2013-04-11 11:03:42','YYYY-MM-DD HH24:MI:SS'),100,'Scheduled start date for this Order','EE01','Y','Date Start Schedule',TO_TIMESTAMP('2013-04-11 11:03:42','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 11, 2013 11:03:51 AM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,64859,0,51399,50029,50010,'DEMAND_M_AttributeSetInstance_','demand.M_AttributeSetInstance_ID',TO_TIMESTAMP('2013-04-11 11:03:51','YYYY-MM-DD HH24:MI:SS'),100,'Product Attribute Set Instance','EE01','The values of the actual Product Attribute Instances.  The product level attributes are defined on Product level.','Y','Attribute Set Instance',TO_TIMESTAMP('2013-04-11 11:03:51','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 11, 2013 11:04:25 AM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,IsActive,Name,Updated,UpdatedBy) VALUES (0,64867,0,51400,50030,50011,'SUPPLY_C_ProjectPhase_ID','supply.C_ProjectPhase_ID',TO_TIMESTAMP('2013-04-11 11:04:24','YYYY-MM-DD HH24:MI:SS'),100,'Phase of a Project','EE01','Y','Project Phase',TO_TIMESTAMP('2013-04-11 11:04:24','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 11, 2013 11:04:28 AM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,64868,0,51401,50030,50011,'SUPPLY_C_ProjectTask_ID','supply.C_ProjectTask_ID',TO_TIMESTAMP('2013-04-11 11:04:27','YYYY-MM-DD HH24:MI:SS'),100,'Actual Project Task in a Phase','EE01','A Project Task in a Project Phase represents the actual work.','Y','Project Task',TO_TIMESTAMP('2013-04-11 11:04:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 11, 2013 11:04:30 AM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,64866,0,51402,50030,50011,'SUPPLY_C_Project_ID','supply.C_Project_ID',TO_TIMESTAMP('2013-04-11 11:04:29','YYYY-MM-DD HH24:MI:SS'),100,'Financial Project','EE01','A Project allows you to track and control internal or external activities.','Y','Project',TO_TIMESTAMP('2013-04-11 11:04:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 11, 2013 11:04:31 AM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,IsActive,Name,Updated,UpdatedBy) VALUES (0,64870,0,51403,50030,50011,'SUPPLY_DateFinishSchedule','supply.DateFinishSchedule',TO_TIMESTAMP('2013-04-11 11:04:31','YYYY-MM-DD HH24:MI:SS'),100,'Scheduled Finish date for this Order','EE01','Y','Date Finish Schedule',TO_TIMESTAMP('2013-04-11 11:04:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 11, 2013 11:04:34 AM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,IsActive,Name,Updated,UpdatedBy) VALUES (0,64869,0,51404,50030,50011,'SUPPLY_DateStartSchedule','supply.DateStartSchedule',TO_TIMESTAMP('2013-04-11 11:04:32','YYYY-MM-DD HH24:MI:SS'),100,'Scheduled start date for this Order','EE01','Y','Date Start Schedule',TO_TIMESTAMP('2013-04-11 11:04:32','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 11, 2013 11:04:35 AM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,64865,0,51405,50030,50011,'SUPPLY_M_AttributeSetInstance_','supply.M_AttributeSetInstance_ID',TO_TIMESTAMP('2013-04-11 11:04:35','YYYY-MM-DD HH24:MI:SS'),100,'Product Attribute Set Instance','EE01','The values of the actual Product Attribute Instances.  The product level attributes are defined on Product level.','Y','Attribute Set Instance',TO_TIMESTAMP('2013-04-11 11:04:35','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 11, 2013 11:05:29 AM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,IsActive,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (51658,50011,0,2073,0,19,51394,TO_TIMESTAMP('2013-04-11 11:05:28','YYYY-MM-DD HH24:MI:SS'),100,'Phase of a Project','C','Y','N','N','N','N','N','N','Project Phase',10,TO_TIMESTAMP('2013-04-11 11:05:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 11, 2013 11:05:29 AM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=51658 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Apr 11, 2013 11:05:30 AM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (51659,50011,0,2074,0,19,51395,TO_TIMESTAMP('2013-04-11 11:05:29','YYYY-MM-DD HH24:MI:SS'),100,'Actual Project Task in a Phase','C','A Project Task in a Project Phase represents the actual work.','Y','N','N','N','N','N','N','Project Task',11,TO_TIMESTAMP('2013-04-11 11:05:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 11, 2013 11:05:30 AM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=51659 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Apr 11, 2013 11:05:31 AM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (51660,50011,0,208,0,19,51396,TO_TIMESTAMP('2013-04-11 11:05:30','YYYY-MM-DD HH24:MI:SS'),100,'Financial Project','C','A Project allows you to track and control internal or external activities.','Y','N','N','N','N','N','N','Project',12,TO_TIMESTAMP('2013-04-11 11:05:30','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 11, 2013 11:05:31 AM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=51660 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Apr 11, 2013 11:05:32 AM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,IsActive,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (51661,50011,0,53278,0,16,51397,TO_TIMESTAMP('2013-04-11 11:05:31','YYYY-MM-DD HH24:MI:SS'),100,'Scheduled Finish date for this Order','C','Y','N','N','N','N','N','N','Date Finish Schedule',13,TO_TIMESTAMP('2013-04-11 11:05:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 11, 2013 11:05:32 AM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=51661 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Apr 11, 2013 11:05:33 AM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,IsActive,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (51662,50011,0,53281,0,16,51398,TO_TIMESTAMP('2013-04-11 11:05:32','YYYY-MM-DD HH24:MI:SS'),100,'Scheduled start date for this Order','C','Y','N','N','N','N','N','N','Date Start Schedule',14,TO_TIMESTAMP('2013-04-11 11:05:32','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 11, 2013 11:05:33 AM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=51662 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Apr 11, 2013 11:05:34 AM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (51663,50011,0,2019,0,35,51399,TO_TIMESTAMP('2013-04-11 11:05:33','YYYY-MM-DD HH24:MI:SS'),100,'Product Attribute Set Instance','C','The values of the actual Product Attribute Instances.  The product level attributes are defined on Product level.','Y','N','N','N','N','N','N','Attribute Set Instance',15,TO_TIMESTAMP('2013-04-11 11:05:33','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 11, 2013 11:05:34 AM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=51663 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Apr 11, 2013 11:06:07 AM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,IsActive,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (51664,50012,0,2073,0,19,51400,TO_TIMESTAMP('2013-04-11 11:06:06','YYYY-MM-DD HH24:MI:SS'),100,'Phase of a Project','EE01','Y','N','N','N','N','N','N','Project Phase',10,TO_TIMESTAMP('2013-04-11 11:06:06','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 11, 2013 11:06:07 AM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=51664 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Apr 11, 2013 11:06:08 AM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (51665,50012,0,2074,0,19,51401,TO_TIMESTAMP('2013-04-11 11:06:07','YYYY-MM-DD HH24:MI:SS'),100,'Actual Project Task in a Phase','EE01','A Project Task in a Project Phase represents the actual work.','Y','N','N','N','N','N','N','Project Task',11,TO_TIMESTAMP('2013-04-11 11:06:07','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 11, 2013 11:06:08 AM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=51665 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Apr 11, 2013 11:06:11 AM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (51666,50012,0,208,0,19,51402,TO_TIMESTAMP('2013-04-11 11:06:08','YYYY-MM-DD HH24:MI:SS'),100,'Financial Project','EE01','A Project allows you to track and control internal or external activities.','Y','N','N','N','N','N','N','Project',12,TO_TIMESTAMP('2013-04-11 11:06:08','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 11, 2013 11:06:11 AM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=51666 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Apr 11, 2013 11:06:12 AM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,IsActive,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (51667,50012,0,53278,0,16,51403,TO_TIMESTAMP('2013-04-11 11:06:11','YYYY-MM-DD HH24:MI:SS'),100,'Scheduled Finish date for this Order','EE01','Y','N','N','N','N','N','N','Date Finish Schedule',13,TO_TIMESTAMP('2013-04-11 11:06:11','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 11, 2013 11:06:12 AM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=51667 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Apr 11, 2013 11:06:13 AM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,IsActive,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (51668,50012,0,53281,0,16,51404,TO_TIMESTAMP('2013-04-11 11:06:12','YYYY-MM-DD HH24:MI:SS'),100,'Scheduled start date for this Order','EE01','Y','N','N','N','N','N','N','Date Start Schedule',14,TO_TIMESTAMP('2013-04-11 11:06:12','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 11, 2013 11:06:13 AM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=51668 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Apr 11, 2013 11:06:14 AM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsQueryCriteria,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (51669,50012,0,2019,0,35,51405,TO_TIMESTAMP('2013-04-11 11:06:13','YYYY-MM-DD HH24:MI:SS'),100,'Product Attribute Set Instance','EE01','The values of the actual Product Attribute Instances.  The product level attributes are defined on Product level.','Y','N','N','N','N','N','N','Attribute Set Instance',15,TO_TIMESTAMP('2013-04-11 11:06:13','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 11, 2013 11:06:14 AM CDT
-- MFG-38 Muli-Level Pegging
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=51669 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Apr 11, 2013 9:26:19 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Browse_Field SET IsDisplayed='N', SeqNo=0,Updated=TO_TIMESTAMP('2013-04-11 21:26:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Browse_Field_ID=51661
;

-- Apr 11, 2013 9:26:19 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Browse_Field SET IsDisplayed='N', SeqNo=0,Updated=TO_TIMESTAMP('2013-04-11 21:26:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Browse_Field_ID=51662
;

-- Apr 11, 2013 9:26:19 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Browse_Field SET IsDisplayed='Y', SeqNo=130,Updated=TO_TIMESTAMP('2013-04-11 21:26:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Browse_Field_ID=51663
;

-- Apr 11, 2013 9:26:19 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Browse_Field SET IsDisplayed='Y', SeqNo=140,Updated=TO_TIMESTAMP('2013-04-11 21:26:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Browse_Field_ID=51480
;

-- Apr 11, 2013 9:26:19 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Browse_Field SET IsDisplayed='Y', SeqNo=150,Updated=TO_TIMESTAMP('2013-04-11 21:26:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Browse_Field_ID=51489
;

-- Apr 11, 2013 9:26:19 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Browse_Field SET IsDisplayed='Y', SeqNo=160,Updated=TO_TIMESTAMP('2013-04-11 21:26:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Browse_Field_ID=51484
;

-- Apr 11, 2013 9:26:19 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Browse_Field SET IsDisplayed='Y', SeqNo=170,Updated=TO_TIMESTAMP('2013-04-11 21:26:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Browse_Field_ID=51496
;

-- Apr 11, 2013 9:26:19 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Browse_Field SET IsDisplayed='Y', SeqNo=180,Updated=TO_TIMESTAMP('2013-04-11 21:26:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Browse_Field_ID=51495
;

-- Apr 11, 2013 9:26:19 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Browse_Field SET IsDisplayed='Y', SeqNo=190,Updated=TO_TIMESTAMP('2013-04-11 21:26:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Browse_Field_ID=51497
;

-- Apr 11, 2013 9:26:19 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Browse_Field SET IsDisplayed='Y', SeqNo=200,Updated=TO_TIMESTAMP('2013-04-11 21:26:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Browse_Field_ID=51488
;

-- Apr 11, 2013 9:26:19 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Browse_Field SET IsDisplayed='Y', SeqNo=210,Updated=TO_TIMESTAMP('2013-04-11 21:26:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Browse_Field_ID=51482
;

-- Apr 11, 2013 9:26:19 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Browse_Field SET IsDisplayed='Y', SeqNo=220,Updated=TO_TIMESTAMP('2013-04-11 21:26:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Browse_Field_ID=51660
;

-- Apr 11, 2013 9:26:19 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Browse_Field SET IsDisplayed='Y', SeqNo=230,Updated=TO_TIMESTAMP('2013-04-11 21:26:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Browse_Field_ID=51658
;

-- Apr 11, 2013 9:26:20 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Browse_Field SET IsDisplayed='Y', SeqNo=240,Updated=TO_TIMESTAMP('2013-04-11 21:26:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Browse_Field_ID=51659
;

-- Apr 11, 2013 9:26:36 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Browse_Field SET IsQueryCriteria='Y',Updated=TO_TIMESTAMP('2013-04-11 21:26:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Browse_Field_ID=51660
;

-- Apr 11, 2013 9:26:39 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Browse_Field SET IsQueryCriteria='Y',Updated=TO_TIMESTAMP('2013-04-11 21:26:39','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Browse_Field_ID=51658
;

-- Apr 11, 2013 9:26:42 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Browse_Field SET IsQueryCriteria='Y',Updated=TO_TIMESTAMP('2013-04-11 21:26:42','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Browse_Field_ID=51659
;

-- Apr 11, 2013 9:28:45 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Browse_Field SET IsDisplayed='N', SeqNo=0,Updated=TO_TIMESTAMP('2013-04-11 21:28:45','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Browse_Field_ID=51667
;

-- Apr 11, 2013 9:28:45 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Browse_Field SET IsDisplayed='N', SeqNo=0,Updated=TO_TIMESTAMP('2013-04-11 21:28:45','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Browse_Field_ID=51668
;

-- Apr 11, 2013 9:28:45 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Browse_Field SET IsDisplayed='Y', SeqNo=130,Updated=TO_TIMESTAMP('2013-04-11 21:28:45','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Browse_Field_ID=51669
;

-- Apr 11, 2013 9:28:45 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Browse_Field SET IsDisplayed='Y', SeqNo=140,Updated=TO_TIMESTAMP('2013-04-11 21:28:45','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Browse_Field_ID=51516
;

-- Apr 11, 2013 9:28:45 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Browse_Field SET IsDisplayed='Y', SeqNo=150,Updated=TO_TIMESTAMP('2013-04-11 21:28:45','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Browse_Field_ID=51505
;

-- Apr 11, 2013 9:28:45 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Browse_Field SET IsDisplayed='Y', SeqNo=160,Updated=TO_TIMESTAMP('2013-04-11 21:28:45','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Browse_Field_ID=51506
;

-- Apr 11, 2013 9:28:45 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Browse_Field SET IsDisplayed='Y', SeqNo=170,Updated=TO_TIMESTAMP('2013-04-11 21:28:45','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Browse_Field_ID=51507
;

-- Apr 11, 2013 9:28:45 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Browse_Field SET IsDisplayed='Y', SeqNo=180,Updated=TO_TIMESTAMP('2013-04-11 21:28:45','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Browse_Field_ID=51508
;

-- Apr 11, 2013 9:28:45 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Browse_Field SET IsDisplayed='Y', SeqNo=190,Updated=TO_TIMESTAMP('2013-04-11 21:28:45','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Browse_Field_ID=51504
;

-- Apr 11, 2013 9:28:45 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Browse_Field SET IsDisplayed='Y', SeqNo=200,Updated=TO_TIMESTAMP('2013-04-11 21:28:45','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Browse_Field_ID=51509
;

-- Apr 11, 2013 9:28:45 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Browse_Field SET IsDisplayed='Y', SeqNo=210,Updated=TO_TIMESTAMP('2013-04-11 21:28:45','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Browse_Field_ID=51517
;

-- Apr 11, 2013 9:28:45 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Browse_Field SET IsDisplayed='Y', SeqNo=220,Updated=TO_TIMESTAMP('2013-04-11 21:28:45','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Browse_Field_ID=51666
;

-- Apr 11, 2013 9:28:45 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Browse_Field SET IsDisplayed='Y', SeqNo=230,Updated=TO_TIMESTAMP('2013-04-11 21:28:45','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Browse_Field_ID=51664
;

-- Apr 11, 2013 9:28:45 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Browse_Field SET IsDisplayed='Y', SeqNo=240,Updated=TO_TIMESTAMP('2013-04-11 21:28:45','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Browse_Field_ID=51665
;

-- Apr 11, 2013 9:29:04 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Browse_Field SET IsQueryCriteria='Y',Updated=TO_TIMESTAMP('2013-04-11 21:29:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Browse_Field_ID=51664
;

-- Apr 11, 2013 9:29:07 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Browse_Field SET IsQueryCriteria='Y',Updated=TO_TIMESTAMP('2013-04-11 21:29:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Browse_Field_ID=51666
;

-- Apr 11, 2013 9:29:37 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Browse_Field SET IsQueryCriteria='Y',Updated=TO_TIMESTAMP('2013-04-11 21:29:37','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Browse_Field_ID=51665
;

-- Apr 11, 2013 9:34:02 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Browse_Field SET IsDisplayed='Y', SeqNo=90,Updated=TO_TIMESTAMP('2013-04-11 21:32:49','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Browse_Field_ID=51668
;

-- Apr 11, 2013 9:39:42 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Browse_Field SET IsDisplayed='Y', SeqNo=100,Updated=TO_TIMESTAMP('2013-04-11 21:34:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Browse_Field_ID=51667
;

-- Apr 11, 2013 9:39:58 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Browse_Field SET IsDisplayed='Y', SeqNo=110,Updated=TO_TIMESTAMP('2013-04-11 21:39:48','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Browse_Field_ID=51511
;

-- Apr 11, 2013 9:39:59 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Browse_Field SET IsDisplayed='Y', SeqNo=120,Updated=TO_TIMESTAMP('2013-04-11 21:39:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Browse_Field_ID=51512
;

-- Apr 11, 2013 9:40:00 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Browse_Field SET IsDisplayed='Y', SeqNo=130,Updated=TO_TIMESTAMP('2013-04-11 21:39:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Browse_Field_ID=51522
;

-- Apr 11, 2013 9:40:01 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Browse_Field SET IsDisplayed='Y', SeqNo=140,Updated=TO_TIMESTAMP('2013-04-11 21:40:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Browse_Field_ID=51513
;

-- Apr 11, 2013 9:40:02 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Browse_Field SET IsDisplayed='Y', SeqNo=150,Updated=TO_TIMESTAMP('2013-04-11 21:40:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Browse_Field_ID=51669
;

-- Apr 11, 2013 9:40:03 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Browse_Field SET IsDisplayed='Y', SeqNo=160,Updated=TO_TIMESTAMP('2013-04-11 21:40:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Browse_Field_ID=51516
;

-- Apr 11, 2013 9:40:04 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Browse_Field SET IsDisplayed='Y', SeqNo=170,Updated=TO_TIMESTAMP('2013-04-11 21:40:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Browse_Field_ID=51505
;

-- Apr 11, 2013 9:40:05 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Browse_Field SET IsDisplayed='Y', SeqNo=180,Updated=TO_TIMESTAMP('2013-04-11 21:40:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Browse_Field_ID=51506
;

-- Apr 11, 2013 9:40:06 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Browse_Field SET IsDisplayed='Y', SeqNo=190,Updated=TO_TIMESTAMP('2013-04-11 21:40:05','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Browse_Field_ID=51507
;

-- Apr 11, 2013 9:40:07 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Browse_Field SET IsDisplayed='Y', SeqNo=200,Updated=TO_TIMESTAMP('2013-04-11 21:40:06','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Browse_Field_ID=51508
;

-- Apr 11, 2013 9:40:08 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Browse_Field SET IsDisplayed='Y', SeqNo=210,Updated=TO_TIMESTAMP('2013-04-11 21:40:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Browse_Field_ID=51504
;

-- Apr 11, 2013 9:42:23 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Browse_Field SET IsDisplayed='Y', SeqNo=220,Updated=TO_TIMESTAMP('2013-04-11 21:40:09','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Browse_Field_ID=51509
;

-- Apr 11, 2013 9:42:27 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Browse_Field SET IsDisplayed='Y', SeqNo=230,Updated=TO_TIMESTAMP('2013-04-11 21:42:23','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Browse_Field_ID=51517
;

-- Apr 11, 2013 9:42:27 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Browse_Field SET IsDisplayed='Y', SeqNo=240,Updated=TO_TIMESTAMP('2013-04-11 21:42:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Browse_Field_ID=51666
;

-- Apr 11, 2013 9:42:27 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Browse_Field SET IsDisplayed='Y', SeqNo=250,Updated=TO_TIMESTAMP('2013-04-11 21:42:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Browse_Field_ID=51664
;

-- Apr 11, 2013 9:42:27 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Browse_Field SET IsDisplayed='Y', SeqNo=260,Updated=TO_TIMESTAMP('2013-04-11 21:42:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Browse_Field_ID=51665
;

-- Apr 11, 2013 9:43:00 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Browse_Field SET IsDisplayed='Y', SeqNo=90,Updated=TO_TIMESTAMP('2013-04-11 21:43:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Browse_Field_ID=51662
;

-- Apr 11, 2013 9:43:00 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Browse_Field SET IsDisplayed='Y', SeqNo=100,Updated=TO_TIMESTAMP('2013-04-11 21:43:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Browse_Field_ID=51661
;

-- Apr 11, 2013 9:43:00 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Browse_Field SET IsDisplayed='Y', SeqNo=110,Updated=TO_TIMESTAMP('2013-04-11 21:43:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Browse_Field_ID=51493
;

-- Apr 11, 2013 9:43:00 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Browse_Field SET IsDisplayed='Y', SeqNo=120,Updated=TO_TIMESTAMP('2013-04-11 21:43:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Browse_Field_ID=51485
;

-- Apr 11, 2013 9:43:00 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Browse_Field SET IsDisplayed='Y', SeqNo=130,Updated=TO_TIMESTAMP('2013-04-11 21:43:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Browse_Field_ID=51500
;

-- Apr 11, 2013 9:43:00 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Browse_Field SET IsDisplayed='Y', SeqNo=140,Updated=TO_TIMESTAMP('2013-04-11 21:43:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Browse_Field_ID=51492
;

-- Apr 11, 2013 9:43:00 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Browse_Field SET IsDisplayed='Y', SeqNo=150,Updated=TO_TIMESTAMP('2013-04-11 21:43:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Browse_Field_ID=51663
;

-- Apr 11, 2013 9:43:00 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Browse_Field SET IsDisplayed='Y', SeqNo=160,Updated=TO_TIMESTAMP('2013-04-11 21:43:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Browse_Field_ID=51480
;

-- Apr 11, 2013 9:43:00 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Browse_Field SET IsDisplayed='Y', SeqNo=170,Updated=TO_TIMESTAMP('2013-04-11 21:43:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Browse_Field_ID=51489
;

-- Apr 11, 2013 9:43:00 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Browse_Field SET IsDisplayed='Y', SeqNo=180,Updated=TO_TIMESTAMP('2013-04-11 21:43:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Browse_Field_ID=51484
;

-- Apr 11, 2013 9:43:00 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Browse_Field SET IsDisplayed='Y', SeqNo=190,Updated=TO_TIMESTAMP('2013-04-11 21:43:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Browse_Field_ID=51496
;

-- Apr 11, 2013 9:43:00 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Browse_Field SET IsDisplayed='Y', SeqNo=200,Updated=TO_TIMESTAMP('2013-04-11 21:43:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Browse_Field_ID=51495
;

-- Apr 11, 2013 9:43:00 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Browse_Field SET IsDisplayed='Y', SeqNo=210,Updated=TO_TIMESTAMP('2013-04-11 21:43:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Browse_Field_ID=51497
;

-- Apr 11, 2013 9:43:01 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Browse_Field SET IsDisplayed='Y', SeqNo=220,Updated=TO_TIMESTAMP('2013-04-11 21:43:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Browse_Field_ID=51488
;

-- Apr 11, 2013 9:43:01 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Browse_Field SET IsDisplayed='Y', SeqNo=230,Updated=TO_TIMESTAMP('2013-04-11 21:43:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Browse_Field_ID=51482
;

-- Apr 11, 2013 9:43:01 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Browse_Field SET IsDisplayed='Y', SeqNo=240,Updated=TO_TIMESTAMP('2013-04-11 21:43:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Browse_Field_ID=51660
;

-- Apr 11, 2013 9:43:01 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Browse_Field SET IsDisplayed='Y', SeqNo=250,Updated=TO_TIMESTAMP('2013-04-11 21:43:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Browse_Field_ID=51658
;

-- Apr 11, 2013 9:43:01 PM CDT
-- MFG-38 Muli-Level Pegging
UPDATE AD_Browse_Field SET IsDisplayed='Y', SeqNo=260,Updated=TO_TIMESTAMP('2013-04-11 21:43:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Browse_Field_ID=51659
;

DROP VIEW rv_pp_mrp;
CREATE OR REPLACE VIEW rv_pp_mrp AS 
SELECT 
mrp.pp_mrp_id,
mrp.ad_client_id,
mrp.ad_org_id,
mrp.created,
mrp.createdby,
mrp.isactive,
mrp.isavailable,
mrp.updated,
mrp.updatedby,
pp.ismps,
pp.IsRequiredMRP,
pp.IsRequiredDRP,
p.IsBOM,
p.IsPurchased,
p.M_Product_Category_ID,
p.M_AttributeSetInstance_ID,
mrp.name,
mrp.description,
mrp.c_order_id,
mrp.c_orderline_id,
mrp.dateordered,
mrp.dateconfirm,
mrp.datepromised,
mrp.datestartschedule,
mrp.datefinishschedule,
mrp.datestart,
mrp.datesimulation,
mrp.docstatus,
mrp.m_forecast_id,
mrp.m_forecastline_id,
mrp.value,
mrp.m_product_id,
mrp.m_requisition_id,
mrp.m_requisitionline_id,
mrp.m_warehouse_id,
mrp.pp_order_id,
mrp.pp_order_bomline_id,
mrp.dd_order_id,
mrp.dd_orderline_id,
mrp.qty,
mrp.s_resource_id,
mrp.planner_id,
mrp.priority,
mrp.ordertype,
mrp.typemrp,
p.LowLevel,
mrp.C_BPartner_ID,
mrp.version,
documentNo(mrp.pp_mrp_id) AS documentNo,
mrp.c_project_id,
mrp.c_projectphase_id,
mrp.c_projecttask_id
FROM pp_mrp mrp
INNER JOIN M_Product p ON (mrp.M_Product_ID = p.M_Product_ID)
LEFT JOIN pp_product_planning pp ON (pp.m_product_id = mrp.m_product_id AND mrp.m_warehouse_id = pp.m_warehouse_id) WHERE mrp.Qty<>0
UNION
SELECT 
0 ,
pp.ad_client_id,
pp.ad_org_id,
pp.created,
pp.createdby,
pp.isactive,
'Y',--mrp.isavailable
pp.updated,
pp.updatedby,
pp.ismps,
pp.IsRequiredMRP,
pp.IsRequiredDRP,
p.IsBOM,
p.IsPurchased,
p.M_Product_Category_ID,
p.M_AttributeSetInstance_ID,
null, --name
null, --description
null, --mrp.c_order_id
null, --mrp.c_orderline_id
CURRENT_TIMESTAMP, --mrp.dateordered,
CURRENT_TIMESTAMP, --mrp.dateconfirm,
CURRENT_TIMESTAMP, --mrp.datepromised,
CURRENT_TIMESTAMP, --mrp.datestartschedule,
CURRENT_TIMESTAMP, --mrp.datefinishschedule,
CURRENT_TIMESTAMP, --mrp.datestart,
CURRENT_TIMESTAMP, --mrp.datesimulation,
'CO',  --mrp.docstatus,
null, --mrp.m_forecast_id,
null, --mrp.m_forecastline_id,
null, --mrp.value,
pp.m_product_id,
null, --mrp.m_requisition_id,
null, --mrp.m_requisitionline_id,
pp.m_warehouse_id,
null, --mrp.pp_order_id,
null, --pp_order_bomline_id
null, --mrp.dd_order_id,
null, --mrp.dd_orderline_id,
pp.safetystock - bomqtyonhand(pp.M_Product_ID,pp.M_Warehouse_ID, 0) AS qty, --mrp.qty,
pp.s_resource_id,
null, --planner_id
null, --mrp.priority,
'STK', --mrp.ordertype,
'D' , --mrp.typemrp,
p.LowLevel,
null, --C_BPartner_ID
null,
'Safety Strock',   --documentNo(mrp.pp_mrp_id) AS documentNo
null,
null,
null
FROM pp_product_planning pp 
INNER JOIN M_Product p ON (pp.M_Product_ID = p.M_Product_ID)
WHERE bomqtyonhand(pp.M_Product_ID,pp.M_Warehouse_ID, 0) < pp.safetystock 
;


CREATE VIEW RV_PP_MRP_Demand AS 
SELECT
mrp.AD_Client_ID,
mrp.AD_Org_ID,
mrp.Created,
mrp.CreatedBy,
mrp.Updated,
mrp.UpdatedBy,
mrp.isActive,
mrp.PP_MRP_ID, 
mrp.DocumentNo , 
mrp.OrderType , 
mrp.DocStatus,
mrp.C_Bpartner_ID,
mrp.Planner_ID , 
mrp.S_Resource_ID , 
mrp.M_Warehouse_ID , 
mrp.DateOrdered,
mrp.DatePromised,
mrp.Priority , 
mrp.M_Product_ID,
p.M_AttributeSetInstance_ID,
p.sku , p.C_UOM_ID , p.IsSold,
mrp.M_Product_Category_ID, 
mrp.isBOM, mrp.IsPurchased, 
mrp.qty,
mrp.isMPS,
mrp.isRequiredMRP,
mrp.IsRequiredDRP,
mrp.C_Project_ID,
mrp.C_ProjectPhase_ID,
mrp.C_ProjectTask_ID,
mrp.DateStartSchedule,
mrp.DateFinishSchedule
FROM RV_PP_MRP mrp INNER JOIN M_Product p ON (p.M_Product_ID=mrp.M_Product_ID)
WHERE mrp.TypeMRP='D' AND mrp.Qty > 0 ORDER BY mrp.DatePromised;


CREATE VIEW RV_PP_MRP_Supply AS 
SELECT
mrp.AD_Client_ID,
mrp.AD_Org_ID,
mrp.Created,
mrp.CreatedBy,
mrp.Updated,
mrp.UpdatedBy,
mrp.isActive,
mrp.PP_MRP_ID, 
mrp.DocumentNo,
mrp.OrderType, 
mrp.DocStatus,
mrp.C_Bpartner_ID,
mrp.Planner_ID , 
mrp.S_Resource_ID , 
mrp.M_Warehouse_ID , 
mrp.DateOrdered,
mrp.DatePromised,
mrp.Priority, 
mrp.M_Product_ID,
p.M_AttributeSetInstance_ID,
p.sku , p.C_UOM_ID , p.IsSold,
mrp.M_Product_Category_ID, 
mrp.isBOM, mrp.IsPurchased, 
mrp.qty,
mrp.isMPS,
mrp.isRequiredMRP,
mrp.IsRequiredDRP,
mrp.C_Project_ID,
mrp.C_ProjectPhase_ID,
mrp.C_ProjectTask_ID,
mrp.DateStartSchedule,
mrp.DateFinishSchedule
FROM RV_PP_MRP mrp INNER JOIN M_Product p ON (p.M_Product_ID=mrp.M_Product_ID)
WHERE mrp.TypeMRP='S' AND mrp.Qty > 0 ORDER BY mrp.DatePromised;


CREATE VIEW RV_PP_MRP_Detail_Demand AS
SELECT
mrp_detail.AD_Client_ID,
mrp_detail.AD_Org_ID,
mrp_detail.Created,
mrp_detail.CreatedBy,
mrp_detail.Updated,
mrp_detail.UpdatedBy,
mrp_detail.isActive,
mrp_detail.MRP_Demand_ID,
mrp_detail.MRP_Supply_ID,
mrp_detail.Qty,
demand.OrderType,
demand.DocStatus,
demand.DateOrdered,
demand.DatePromised,
demand.Priority,
demand.S_Resource_ID,
demand.M_Warehouse_ID,
demand.C_BPartner_ID,
demand.Planner_ID,
demand.C_Project_ID,
demand.C_ProjectPhase_ID,
demand.C_ProjectTask_ID,
demand.DateStartSchedule,
demand.DateFinishSchedule
FROM PP_MRP_Detail mrp_detail
LEFT JOIN RV_PP_MRP demand ON (demand.PP_MRP_ID=mrp_detail.MRP_demand_ID);

CREATE VIEW RV_PP_MRP_Detail_Supply AS
SELECT
mrp_detail.AD_Client_ID,
mrp_detail.AD_Org_ID,
mrp_detail.Created,
mrp_detail.CreatedBy,
mrp_detail.Updated,
mrp_detail.UpdatedBy,
mrp_detail.isActive,
mrp_detail.MRP_Demand_ID,
mrp_detail.MRP_Supply_ID,
mrp_detail.Qty,
supply.OrderType,
supply.DocStatus,
supply.DateOrdered,
supply.DatePromised,
supply.Priority,
supply.S_Resource_ID,
supply.M_Warehouse_ID,
supply.C_BPartner_ID,
supply.Planner_ID,
supply.C_Project_ID,
supply.C_ProjectPhase_ID,
supply.C_ProjectTask_ID,
supply.DateStartSchedule,
supply.DateFinishSchedule
FROM PP_MRP_Detail mrp_detail  
LEFT JOIN RV_PP_MRP supply ON (supply.PP_MRP_ID=mrp_detail.MRP_Supply_ID);

