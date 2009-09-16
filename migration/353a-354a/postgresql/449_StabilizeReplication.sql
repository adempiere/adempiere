--- EXP_FormatLine 
ALTER TABLE EXP_FormatLine
ALTER COLUMN AD_Column_ID DROP NOT NULL;
commit;


--- Table: AD_Org
ALTER TABLE AD_Org 
ADD column AD_ReplicationStrategy_ID NUMERIC;
commit;

ALTER TABLE AD_Org 
ADD CONSTRAINT AD_Org__AD_Repli_AD_Replica FOREIGN KEY(AD_ReplicationStrategy_ID)  REFERENCES AD_ReplicationStrategy(AD_ReplicationStrategy_ID);
commit;


--- Table: AD_ReplicationStrategy
ALTER TABLE AD_ReplicationStrategy 
ADD COLUMN Value VARCHAR(40);
commit;

UPDATE AD_ReplicationStrategy
 Set Value = nextidfunc( (SELECT AD_Sequence_ID FROM AD_Sequence WHERE AD_Client_ID = 0 AND Name = 'AD_ReplicationStrategy')::integer, 'N'::varchar)
WHERE AD_ReplicationStrategy.Value IS NULL
 AND AD_ReplicationStrategy.AD_Client_ID IN (0, 11);
commit;
 
ALTER TABLE AD_ReplicationStrategy
 ADD Constraint AD_ReplicationStrategy_Value UNIQUE(AD_Client_ID, Value);
commit;


--- Table: AD_ReplicationTable
ALTER TABLE AD_ReplicationTable 
ADD COLUMN Description VARCHAR(255);
commit;



-- Apr 18, 2009 11:38:16 PM EEST
-- Replication stabilization
UPDATE AD_EntityType SET ModelPackage='org.compiere.model',Updated=TO_TIMESTAMP('2009-04-18 23:38:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_EntityType_ID=50003
;

-- Apr 18, 2009 11:43:34 PM EEST
-- Replication stabilization
INSERT INTO AD_Column (AD_Column_ID,AD_Table_ID,EntityType,Version,IsMandatory,IsTranslated,IsIdentifier,SeqNo,CreatedBy,Updated,Help,IsParent,AD_Client_ID,AD_Org_ID,Description,Name,IsActive,FieldLength,IsSelectionColumn,AD_Reference_ID,Created,IsUpdateable,IsKey,AD_Element_ID,UpdatedBy,IsEncrypted,IsAlwaysUpdateable,ColumnName) VALUES (57227,155,'A',0,'N','N','N',10,0,TO_TIMESTAMP('2009-04-18 23:43:26','YYYY-MM-DD HH24:MI:SS'),'The Data Replication Strategy determines what and how tables are replicated ','N',0,0,'Data Replication Strategy','Replication Strategy','Y',10,'N',10,TO_TIMESTAMP('2009-04-18 23:43:26','YYYY-MM-DD HH24:MI:SS'),'Y','N',2133,0,'N','N','AD_ReplicationStrategy_ID')
;

-- Apr 18, 2009 11:43:35 PM EEST
-- Replication stabilization
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57227 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 18, 2009 11:43:36 PM EEST
-- Replication stabilization
INSERT INTO AD_Column (AD_Column_ID,AD_Table_ID,EntityType,Version,IsMandatory,IsTranslated,IsIdentifier,SeqNo,CreatedBy,Updated,Help,IsParent,AD_Client_ID,AD_Org_ID,Description,Name,IsActive,FieldLength,IsSelectionColumn,AD_Reference_ID,Created,IsUpdateable,IsKey,AD_Element_ID,UpdatedBy,IsEncrypted,IsAlwaysUpdateable,ColumnName) VALUES (57228,602,'A',0,'N','N','N',10,0,TO_TIMESTAMP('2009-04-18 23:43:35','YYYY-MM-DD HH24:MI:SS'),'A search key allows you a fast method of finding a particular record.
If you leave the search key empty, the system automatically creates a numeric number.  The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).','N',0,0,'Search key for the record in the format required - must be unique','Search Key','Y',40,'N',10,TO_TIMESTAMP('2009-04-18 23:43:35','YYYY-MM-DD HH24:MI:SS'),'Y','N',620,0,'N','N','Value')
;

-- Apr 18, 2009 11:43:36 PM EEST
-- Replication stabilization
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57228 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 18, 2009 11:43:37 PM EEST
-- Replication stabilization
INSERT INTO AD_Column (AD_Column_ID,AD_Table_ID,EntityType,Version,IsMandatory,IsTranslated,IsIdentifier,SeqNo,CreatedBy,Updated,Help,IsParent,AD_Client_ID,AD_Org_ID,Description,Name,IsActive,FieldLength,IsSelectionColumn,AD_Reference_ID,Created,IsUpdateable,IsKey,AD_Element_ID,UpdatedBy,IsEncrypted,IsAlwaysUpdateable,ColumnName) VALUES (57229,601,'A',0,'N','N','N',10,0,TO_TIMESTAMP('2009-04-18 23:43:36','YYYY-MM-DD HH24:MI:SS'),'A description is limited to 255 characters.','N',0,0,'Optional short description of the record','Description','Y',255,'N',10,TO_TIMESTAMP('2009-04-18 23:43:36','YYYY-MM-DD HH24:MI:SS'),'Y','N',275,0,'N','N','Description')
;

-- Apr 18, 2009 11:43:37 PM EEST
-- Replication stabilization
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57229 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;



-- Apr 19, 2009 1:08:16 AM EEST
-- Replication stabilization
INSERT INTO AD_Field (IsEncrypted,UpdatedBy,AD_Org_ID,Description,IsActive,Created,AD_Client_ID,Name,IsDisplayed,IsSameLine,IsHeading,AD_Column_ID,IsFieldOnly,SeqNo,IsCentrallyMaintained,AD_Tab_ID,AD_Field_ID,CreatedBy,Updated,IsReadOnly,Help,EntityType) VALUES ('N',0,0,'Data Replication Strategy','Y',TO_TIMESTAMP('2009-04-19 01:08:10','YYYY-MM-DD HH24:MI:SS'),0,'Replication Strategy','Y','N','N',57227,'N',10,'Y',143,56981,0,TO_TIMESTAMP('2009-04-19 01:08:10','YYYY-MM-DD HH24:MI:SS'),'N','The Data Replication Strategy determines what and how tables are replicated ','EE05')
;

-- Apr 19, 2009 1:08:16 AM EEST
-- Replication stabilization
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Name,Help, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Name,t.Help, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56981 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Apr 19, 2009 1:08:18 AM EEST
-- Replication stabilization
INSERT INTO AD_Field (IsEncrypted,UpdatedBy,AD_Org_ID,Description,IsActive,Created,AD_Client_ID,Name,IsDisplayed,IsSameLine,IsHeading,AD_Column_ID,IsFieldOnly,SeqNo,IsCentrallyMaintained,AD_Tab_ID,AD_Field_ID,CreatedBy,Updated,IsReadOnly,Help,EntityType) VALUES ('N',0,0,'Search key for the record in the format required - must be unique','Y',TO_TIMESTAMP('2009-04-19 01:08:18','YYYY-MM-DD HH24:MI:SS'),0,'Search Key','Y','N','N',57228,'N',10,'Y',524,56982,0,TO_TIMESTAMP('2009-04-19 01:08:18','YYYY-MM-DD HH24:MI:SS'),'N','A search key allows you a fast method of finding a particular record.
If you leave the search key empty, the system automatically creates a numeric number.  The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).','EE05')
;

-- Apr 19, 2009 1:08:19 AM EEST
-- Replication stabilization
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Name,Help, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Name,t.Help, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56982 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Apr 19, 2009 1:08:19 AM EEST
-- Replication stabilization
UPDATE AD_Field SET DisplayLength=20, Name='Search Key', IsDisplayed='Y', SeqNo=35, IsReadOnly='N', EntityType='EE05',Updated=TO_TIMESTAMP('2009-04-19 01:08:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56982
;

-- Apr 19, 2009 1:08:20 AM EEST
-- Replication stabilization
INSERT INTO AD_Field (IsEncrypted,UpdatedBy,AD_Org_ID,Description,IsActive,Created,AD_Client_ID,Name,IsDisplayed,IsSameLine,IsHeading,AD_Column_ID,IsFieldOnly,SeqNo,IsCentrallyMaintained,AD_Tab_ID,AD_Field_ID,CreatedBy,Updated,IsReadOnly,Help,EntityType) VALUES ('N',0,0,'Optional short description of the record','Y',TO_TIMESTAMP('2009-04-19 01:08:19','YYYY-MM-DD HH24:MI:SS'),0,'Description','Y','N','N',57229,'N',20,'Y',525,56983,0,TO_TIMESTAMP('2009-04-19 01:08:19','YYYY-MM-DD HH24:MI:SS'),'N','A description is limited to 255 characters.','EE05')
;

-- Apr 19, 2009 1:08:20 AM EEST
-- Replication stabilization
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Name,Help, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Name,t.Help, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56983 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;


-- Apr 19, 2009 9:45:34 AM EEST
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=7516
;

-- Apr 19, 2009 9:45:34 AM EEST
UPDATE AD_Field SET SeqNo=10,IsDisplayed='Y' WHERE AD_Field_ID=7517
;

-- Apr 19, 2009 9:45:34 AM EEST
UPDATE AD_Field SET SeqNo=20,IsDisplayed='Y' WHERE AD_Field_ID=7518
;

-- Apr 19, 2009 9:45:34 AM EEST
UPDATE AD_Field SET SeqNo=30,IsDisplayed='Y' WHERE AD_Field_ID=56982
;

-- Apr 19, 2009 9:45:34 AM EEST
UPDATE AD_Field SET SeqNo=90,IsDisplayed='Y' WHERE AD_Field_ID=54569
;

-- Apr 19, 2009 9:46:51 AM EEST
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=7524
;

-- Apr 19, 2009 9:46:51 AM EEST
UPDATE AD_Field SET SeqNo=10,IsDisplayed='Y' WHERE AD_Field_ID=7525
;

-- Apr 19, 2009 9:46:51 AM EEST
UPDATE AD_Field SET SeqNo=20,IsDisplayed='Y' WHERE AD_Field_ID=7527
;

-- Apr 19, 2009 9:46:51 AM EEST
UPDATE AD_Field SET SeqNo=30,IsDisplayed='Y' WHERE AD_Field_ID=7523
;

-- Apr 19, 2009 9:46:51 AM EEST
UPDATE AD_Field SET SeqNo=40,IsDisplayed='Y' WHERE AD_Field_ID=7522
;

-- Apr 19, 2009 9:46:51 AM EEST
UPDATE AD_Field SET SeqNo=50,IsDisplayed='Y' WHERE AD_Field_ID=7528
;

-- Apr 19, 2009 9:46:51 AM EEST
UPDATE AD_Field SET SeqNo=60,IsDisplayed='Y' WHERE AD_Field_ID=56983
;

-- Apr 19, 2009 9:50:46 AM EEST
UPDATE AD_Field SET DisplayLength=60,Updated=TO_TIMESTAMP('2009-04-19 09:50:46','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56983
;


