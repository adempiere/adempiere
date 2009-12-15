SET SQLBLANKLINES ON
SET DEFINE OFF

REM ======================================================================
CREATE TABLE C_OrderSource
  (
    C_OrderSource_ID  NUMBER(10,0),
    AD_Client_ID      NUMBER(10,0)      NOT NULL,
    AD_Org_ID         NUMBER(10,0)      NOT NULL,
    IsActive          CHAR(1)           DEFAULT 'Y' NOT NULL,
    Created           DATE              DEFAULT SYSDATE NOT NULL,
    CreatedBy         NUMBER(10,0)      NOT NULL,
    Updated           DATE              DEFAULT SYSDATE NOT NULL,
    UpdatedBy         NUMBER(10,0)      NOT NULL,
    Value             NVARCHAR2(40)     NOT NULL,
    Name              NVARCHAR2(60)     NOT NULL,
    Description       NVARCHAR2(255),
    Help              NVARCHAR2(2000),

    primary key(C_OrderSource_ID),
    UNIQUE(AD_Client_ID,Value),

    foreign key(AD_Client_ID) references AD_Client(AD_Client_ID),
    foreign key(AD_Org_ID) references AD_Org(AD_Org_ID),
    foreign key(CreatedBy) references AD_User(AD_User_ID),
    foreign key(UpdatedBy) references AD_User(AD_User_ID),

    CHECK(IsActive IN ('Y', 'N'))
  );


--- Table: C_Order -------------------------------------------------------------
ALTER TABLE C_Order 
ADD C_OrderSource_ID NUMBER(10,0);

ALTER TABLE C_Order 
ADD CONSTRAINT C_Order__C_OrderS_C_OrderSou FOREIGN KEY(C_OrderSource_ID)  REFERENCES C_OrderSource(C_OrderSource_ID);



-- Sep 18, 2009 1:05:42 PM CEST
-- FF [2913307] - New table: Order Source
-- https://sourceforge.net/tracker/?func=detail&aid=2913307&group_id=176962&atid=883808
INSERT INTO AD_Table (AccessLevel,AD_Client_ID,AD_Org_ID,AD_Table_ID,Created,CreatedBy,EntityType,IsActive,IsChangeLog,IsDeleteable,IsHighVolume,IsSecurityEnabled,IsView,Name,ReplicationType,TableName,Updated,UpdatedBy) VALUES ('3',0,0,53244,TO_DATE('2009-09-18 13:05:40','YYYY-MM-DD HH24:MI:SS'),0,'A','Y','N','Y','N','N','N','Order Source','L','C_OrderSource',TO_DATE('2009-09-18 13:05:40','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 18, 2009 1:05:42 PM CEST
-- FF [2913307] - New table: Order Source
-- https://sourceforge.net/tracker/?func=detail&aid=2913307&group_id=176962&atid=883808
INSERT INTO AD_Table_Trl (AD_Language,AD_Table_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Table_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Table t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Table_ID=53244 AND EXISTS (SELECT * FROM AD_Table_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Table_ID!=t.AD_Table_ID)
;

-- Sep 18, 2009 1:05:43 PM CEST
-- FF [2913307] - New table: Order Source
-- https://sourceforge.net/tracker/?func=detail&aid=2913307&group_id=176962&atid=883808
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53352,TO_DATE('2009-09-18 13:05:42','YYYY-MM-DD HH24:MI:SS'),0,1000000,50000,'Table C_OrderSource',1,'Y','N','Y','Y','C_OrderSource','N',1000000,TO_DATE('2009-09-18 13:05:42','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 18, 2009 1:05:43 PM CEST
-- FF [2913307] - New table: Order Source
-- https://sourceforge.net/tracker/?func=detail&aid=2913307&group_id=176962&atid=883808
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53353,TO_DATE('2009-09-18 13:05:43','YYYY-MM-DD HH24:MI:SS'),0,1000000,100,'Table C_OrderSource',1,'Y','N','Y','Y','C_OrderSource','N',1000000,TO_DATE('2009-09-18 13:05:43','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 18, 2009 1:05:44 PM CEST
-- FF [2913307] - New table: Order Source
-- https://sourceforge.net/tracker/?func=detail&aid=2913307&group_id=176962&atid=883808
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53942,0,'C_OrderSource_ID',TO_DATE('2009-09-18 13:05:44','YYYY-MM-DD HH24:MI:SS'),0,'A','Y','C_OrderSource_ID','C_OrderSource_ID',TO_DATE('2009-09-18 13:05:44','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 18, 2009 1:05:44 PM CEST
-- FF [2913307] - New table: Order Source
-- https://sourceforge.net/tracker/?func=detail&aid=2913307&group_id=176962&atid=883808
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53942 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Sep 18, 2009 1:05:45 PM CEST
-- FF [2913307] - New table: Order Source
-- https://sourceforge.net/tracker/?func=detail&aid=2913307&group_id=176962&atid=883808
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,58397,53942,0,10,53244,'C_OrderSource_ID',TO_DATE('2009-09-18 13:05:44','YYYY-MM-DD HH24:MI:SS'),0,'A',10,'Y','N','N','N','N','N','N','N','N','Y','C_OrderSource_ID',10,TO_DATE('2009-09-18 13:05:44','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Sep 18, 2009 1:05:45 PM CEST
-- FF [2913307] - New table: Order Source
-- https://sourceforge.net/tracker/?func=detail&aid=2913307&group_id=176962&atid=883808
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58397 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 18, 2009 1:05:46 PM CEST
-- FF [2913307] - New table: Order Source
-- https://sourceforge.net/tracker/?func=detail&aid=2913307&group_id=176962&atid=883808
UPDATE AD_Column SET AD_Reference_ID=13, IsKey='Y', IsMandatory='Y', IsUpdateable='N', Name='Order Source',Updated=TO_DATE('2009-09-18 13:05:46','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=58397
;

-- Sep 18, 2009 1:05:46 PM CEST
-- FF [2913307] - New table: Order Source
-- https://sourceforge.net/tracker/?func=detail&aid=2913307&group_id=176962&atid=883808
UPDATE AD_Column_Trl SET IsTranslated='N' WHERE AD_Column_ID=58397
;

-- Sep 18, 2009 1:05:46 PM CEST
-- FF [2913307] - New table: Order Source
-- https://sourceforge.net/tracker/?func=detail&aid=2913307&group_id=176962&atid=883808
UPDATE AD_Field SET Name='Order Source', Description=NULL, Help=NULL WHERE AD_Column_ID=58397 AND IsCentrallyMaintained='Y'
;

-- Sep 18, 2009 1:05:47 PM CEST
-- FF [2913307] - New table: Order Source
-- https://sourceforge.net/tracker/?func=detail&aid=2913307&group_id=176962&atid=883808
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,58398,102,0,19,53244,129,'AD_Client_ID',TO_DATE('2009-09-18 13:05:46','YYYY-MM-DD HH24:MI:SS'),0,'@AD_Client_ID@','Client/Tenant for this installation.','A',22,'A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','N','N','N','N','N','N','N','N','N','Client',20,TO_DATE('2009-09-18 13:05:46','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Sep 18, 2009 1:05:47 PM CEST
-- FF [2913307] - New table: Order Source
-- https://sourceforge.net/tracker/?func=detail&aid=2913307&group_id=176962&atid=883808
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58398 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 18, 2009 1:05:47 PM CEST
-- FF [2913307] - New table: Order Source
-- https://sourceforge.net/tracker/?func=detail&aid=2913307&group_id=176962&atid=883808
UPDATE AD_Column SET DefaultValue='@#AD_Client_ID@', IsMandatory='Y',Updated=TO_DATE('2009-09-18 13:05:47','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=58398
;

-- Sep 18, 2009 1:05:48 PM CEST
-- FF [2913307] - New table: Order Source
-- https://sourceforge.net/tracker/?func=detail&aid=2913307&group_id=176962&atid=883808
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,58399,113,0,19,53244,104,'AD_Org_ID',TO_DATE('2009-09-18 13:05:47','YYYY-MM-DD HH24:MI:SS'),0,'@AD_Org_ID@','Organizational entity within client','A',22,'An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','N','N','N','N','N','N','N','N','N','Organization',30,TO_DATE('2009-09-18 13:05:47','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Sep 18, 2009 1:05:48 PM CEST
-- FF [2913307] - New table: Order Source
-- https://sourceforge.net/tracker/?func=detail&aid=2913307&group_id=176962&atid=883808
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58399 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 18, 2009 1:05:48 PM CEST
-- FF [2913307] - New table: Order Source
-- https://sourceforge.net/tracker/?func=detail&aid=2913307&group_id=176962&atid=883808
UPDATE AD_Column SET DefaultValue='@#AD_Org_ID@', IsMandatory='Y',Updated=TO_DATE('2009-09-18 13:05:48','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=58399
;

-- Sep 18, 2009 1:05:49 PM CEST
-- FF [2913307] - New table: Order Source
-- https://sourceforge.net/tracker/?func=detail&aid=2913307&group_id=176962&atid=883808
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,58400,348,0,10,53244,'IsActive',TO_DATE('2009-09-18 13:05:48','YYYY-MM-DD HH24:MI:SS'),0,'The record is active in the system','A',1,'There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','N','N','N','N','N','N','N','N','Y','Active',40,TO_DATE('2009-09-18 13:05:48','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Sep 18, 2009 1:05:49 PM CEST
-- FF [2913307] - New table: Order Source
-- https://sourceforge.net/tracker/?func=detail&aid=2913307&group_id=176962&atid=883808
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58400 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 18, 2009 1:05:49 PM CEST
-- FF [2913307] - New table: Order Source
-- https://sourceforge.net/tracker/?func=detail&aid=2913307&group_id=176962&atid=883808
UPDATE AD_Column SET AD_Reference_ID=20, DefaultValue='''Y''', IsMandatory='Y',Updated=TO_DATE('2009-09-18 13:05:49','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=58400
;

-- Sep 18, 2009 1:05:50 PM CEST
-- FF [2913307] - New table: Order Source
-- https://sourceforge.net/tracker/?func=detail&aid=2913307&group_id=176962&atid=883808
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,58401,245,0,16,53244,'Created',TO_DATE('2009-09-18 13:05:49','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was created','A',7,'The Created field indicates the date that this record was created.','Y','N','N','N','N','N','N','N','N','N','Created',50,TO_DATE('2009-09-18 13:05:49','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Sep 18, 2009 1:05:50 PM CEST
-- FF [2913307] - New table: Order Source
-- https://sourceforge.net/tracker/?func=detail&aid=2913307&group_id=176962&atid=883808
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58401 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 18, 2009 1:05:50 PM CEST
-- FF [2913307] - New table: Order Source
-- https://sourceforge.net/tracker/?func=detail&aid=2913307&group_id=176962&atid=883808
UPDATE AD_Column SET DefaultValue='SYSDATE', IsMandatory='Y',Updated=TO_DATE('2009-09-18 13:05:50','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=58401
;

-- Sep 18, 2009 1:05:51 PM CEST
-- FF [2913307] - New table: Order Source
-- https://sourceforge.net/tracker/?func=detail&aid=2913307&group_id=176962&atid=883808
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,58402,246,0,18,110,53244,'CreatedBy',TO_DATE('2009-09-18 13:05:50','YYYY-MM-DD HH24:MI:SS'),0,'User who created this records','A',10,'The Created By field indicates the user who created this record.','Y','N','N','N','N','N','N','N','N','N','Created By',60,TO_DATE('2009-09-18 13:05:50','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Sep 18, 2009 1:05:51 PM CEST
-- FF [2913307] - New table: Order Source
-- https://sourceforge.net/tracker/?func=detail&aid=2913307&group_id=176962&atid=883808
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58402 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 18, 2009 1:05:51 PM CEST
-- FF [2913307] - New table: Order Source
-- https://sourceforge.net/tracker/?func=detail&aid=2913307&group_id=176962&atid=883808
UPDATE AD_Column SET IsMandatory='Y',Updated=TO_DATE('2009-09-18 13:05:51','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=58402
;

-- Sep 18, 2009 1:05:51 PM CEST
-- FF [2913307] - New table: Order Source
-- https://sourceforge.net/tracker/?func=detail&aid=2913307&group_id=176962&atid=883808
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,58403,607,0,16,53244,'Updated',TO_DATE('2009-09-18 13:05:51','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was updated','A',7,'The Updated field indicates the date that this record was updated.','Y','N','N','N','N','N','N','N','N','N','Updated',70,TO_DATE('2009-09-18 13:05:51','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Sep 18, 2009 1:05:51 PM CEST
-- FF [2913307] - New table: Order Source
-- https://sourceforge.net/tracker/?func=detail&aid=2913307&group_id=176962&atid=883808
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58403 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 18, 2009 1:05:51 PM CEST
-- FF [2913307] - New table: Order Source
-- https://sourceforge.net/tracker/?func=detail&aid=2913307&group_id=176962&atid=883808
UPDATE AD_Column SET DefaultValue='SYSDATE', IsMandatory='Y',Updated=TO_DATE('2009-09-18 13:05:51','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=58403
;

-- Sep 18, 2009 1:05:52 PM CEST
-- FF [2913307] - New table: Order Source
-- https://sourceforge.net/tracker/?func=detail&aid=2913307&group_id=176962&atid=883808
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,58404,608,0,18,110,53244,'UpdatedBy',TO_DATE('2009-09-18 13:05:51','YYYY-MM-DD HH24:MI:SS'),0,'User who updated this records','A',10,'The Updated By field indicates the user who updated this record.','Y','N','N','N','N','N','N','N','N','N','Updated By',80,TO_DATE('2009-09-18 13:05:51','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Sep 18, 2009 1:05:52 PM CEST
-- FF [2913307] - New table: Order Source
-- https://sourceforge.net/tracker/?func=detail&aid=2913307&group_id=176962&atid=883808
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58404 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 18, 2009 1:05:52 PM CEST
-- FF [2913307] - New table: Order Source
-- https://sourceforge.net/tracker/?func=detail&aid=2913307&group_id=176962&atid=883808
UPDATE AD_Column SET IsMandatory='Y',Updated=TO_DATE('2009-09-18 13:05:52','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=58404
;

-- Sep 18, 2009 1:05:53 PM CEST
-- FF [2913307] - New table: Order Source
-- https://sourceforge.net/tracker/?func=detail&aid=2913307&group_id=176962&atid=883808
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,58405,620,0,10,53244,'Value',TO_DATE('2009-09-18 13:05:52','YYYY-MM-DD HH24:MI:SS'),0,'Search key for the record in the format required - must be unique','A',40,'A search key allows you a fast method of finding a particular record.
If you leave the search key empty, the system automatically creates a numeric number.  The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).','Y','N','N','N','N','N','N','N','N','Y','Search Key',90,TO_DATE('2009-09-18 13:05:52','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Sep 18, 2009 1:05:53 PM CEST
-- FF [2913307] - New table: Order Source
-- https://sourceforge.net/tracker/?func=detail&aid=2913307&group_id=176962&atid=883808
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58405 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 18, 2009 1:05:53 PM CEST
-- FF [2913307] - New table: Order Source
-- https://sourceforge.net/tracker/?func=detail&aid=2913307&group_id=176962&atid=883808
UPDATE AD_Column SET IsMandatory='Y',Updated=TO_DATE('2009-09-18 13:05:53','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=58405
;

-- Sep 18, 2009 1:05:54 PM CEST
-- FF [2913307] - New table: Order Source
-- https://sourceforge.net/tracker/?func=detail&aid=2913307&group_id=176962&atid=883808
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,58406,469,0,10,53244,'Name',TO_DATE('2009-09-18 13:05:53','YYYY-MM-DD HH24:MI:SS'),0,'Alphanumeric identifier of the entity','A',60,'The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.','Y','N','N','Y','N','N','N','N','N','Y','Name',100,TO_DATE('2009-09-18 13:05:53','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Sep 18, 2009 1:05:54 PM CEST
-- FF [2913307] - New table: Order Source
-- https://sourceforge.net/tracker/?func=detail&aid=2913307&group_id=176962&atid=883808
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58406 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 18, 2009 1:05:54 PM CEST
-- FF [2913307] - New table: Order Source
-- https://sourceforge.net/tracker/?func=detail&aid=2913307&group_id=176962&atid=883808
UPDATE AD_Column SET IsMandatory='Y',Updated=TO_DATE('2009-09-18 13:05:54','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=58406
;

-- Sep 18, 2009 1:05:54 PM CEST
-- FF [2913307] - New table: Order Source
-- https://sourceforge.net/tracker/?func=detail&aid=2913307&group_id=176962&atid=883808
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,58407,275,0,10,53244,'Description',TO_DATE('2009-09-18 13:05:54','YYYY-MM-DD HH24:MI:SS'),0,'Optional short description of the record','A',255,'A description is limited to 255 characters.','Y','N','N','N','N','N','N','N','N','Y','Description',110,TO_DATE('2009-09-18 13:05:54','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Sep 18, 2009 1:05:54 PM CEST
-- FF [2913307] - New table: Order Source
-- https://sourceforge.net/tracker/?func=detail&aid=2913307&group_id=176962&atid=883808
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58407 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 18, 2009 1:05:55 PM CEST
-- FF [2913307] - New table: Order Source
-- https://sourceforge.net/tracker/?func=detail&aid=2913307&group_id=176962&atid=883808
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,58408,326,0,10,53244,'Help',TO_DATE('2009-09-18 13:05:54','YYYY-MM-DD HH24:MI:SS'),0,'Comment or Hint','A',2000,'The Help field contains a hint, comment or help about the use of this item.','Y','N','N','N','N','N','N','N','N','Y','Comment/Help',120,TO_DATE('2009-09-18 13:05:54','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Sep 18, 2009 1:05:55 PM CEST
-- FF [2913307] - New table: Order Source
-- https://sourceforge.net/tracker/?func=detail&aid=2913307&group_id=176962&atid=883808
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58408 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 18, 2009 1:05:55 PM CEST
-- FF [2913307] - New table: Order Source
-- https://sourceforge.net/tracker/?func=detail&aid=2913307&group_id=176962&atid=883808
UPDATE AD_Column SET AD_Reference_ID=14,Updated=TO_DATE('2009-09-18 13:05:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=58408
;

-- Sep 18, 2009 1:05:56 PM CEST
-- FF [2913307] - New table: Order Source
-- https://sourceforge.net/tracker/?func=detail&aid=2913307&group_id=176962&atid=883808
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,58409,53942,0,10,259,'C_OrderSource_ID',TO_DATE('2009-09-18 13:05:55','YYYY-MM-DD HH24:MI:SS'),0,'A',10,'Y','N','N','N','N','N','N','N','N','Y','C_OrderSource_ID',10,TO_DATE('2009-09-18 13:05:55','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Sep 18, 2009 1:05:56 PM CEST
-- FF [2913307] - New table: Order Source
-- https://sourceforge.net/tracker/?func=detail&aid=2913307&group_id=176962&atid=883808
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58409 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;




-- Sep 18, 2009 1:25:40 PM CEST
-- FF [2913307] - New table: Order Source
-- https://sourceforge.net/tracker/?func=detail&aid=2913307&group_id=176962&atid=883808
UPDATE AD_Column SET FieldLength=10,Updated=TO_DATE('2009-09-18 13:25:40','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=58398
;

-- Sep 18, 2009 1:25:40 PM CEST
-- FF [2913307] - New table: Order Source
-- https://sourceforge.net/tracker/?func=detail&aid=2913307&group_id=176962&atid=883808
UPDATE AD_Column SET FieldLength=10,Updated=TO_DATE('2009-09-18 13:25:40','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=58399
;

-- Sep 18, 2009 1:25:40 PM CEST
-- FF [2913307] - New table: Order Source
-- https://sourceforge.net/tracker/?func=detail&aid=2913307&group_id=176962&atid=883808
UPDATE AD_Column SET AD_Reference_ID=19,Updated=TO_DATE('2009-09-18 13:25:40','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=58409
;



-- Sep 18, 2009 1:27:43 PM CEST
-- FF [2913307] - New table: Order Source
-- https://sourceforge.net/tracker/?func=detail&aid=2913307&group_id=176962&atid=883808
INSERT INTO AD_Menu (Action,AD_Client_ID,AD_Menu_ID,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,IsReadOnly,IsSOTrx,IsSummary,Name,Updated,UpdatedBy) VALUES ('W',0,53249,0,TO_DATE('2009-09-18 13:27:41','YYYY-MM-DD HH24:MI:SS'),0,'D','Y','N','N','N','Order Source',TO_DATE('2009-09-18 13:27:41','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 18, 2009 1:27:43 PM CEST
-- FF [2913307] - New table: Order Source
-- https://sourceforge.net/tracker/?func=detail&aid=2913307&group_id=176962&atid=883808
INSERT INTO AD_Menu_Trl (AD_Language,AD_Menu_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Menu_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Menu t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Menu_ID=53249 AND EXISTS (SELECT * FROM AD_Menu_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Menu_ID!=t.AD_Menu_ID)
;

-- Sep 18, 2009 1:27:43 PM CEST
-- FF [2913307] - New table: Order Source
-- https://sourceforge.net/tracker/?func=detail&aid=2913307&group_id=176962&atid=883808
INSERT INTO AD_TreeNodeMM (AD_Client_ID,AD_Org_ID, IsActive,Created,CreatedBy,Updated,UpdatedBy, AD_Tree_ID, Node_ID, Parent_ID, SeqNo) SELECT t.AD_Client_ID,0, 'Y', SysDate, 0, SysDate, 0,t.AD_Tree_ID, 53249, 0, 999 FROM AD_Tree t WHERE t.AD_Client_ID=0 AND t.IsActive='Y' AND t.IsAllNodes='Y' AND t.TreeType='MM' AND NOT EXISTS (SELECT * FROM AD_TreeNodeMM e WHERE e.AD_Tree_ID=t.AD_Tree_ID AND Node_ID=53249)
;

-- Sep 18, 2009 1:27:44 PM CEST
-- FF [2913307] - New table: Order Source
-- https://sourceforge.net/tracker/?func=detail&aid=2913307&group_id=176962&atid=883808
INSERT INTO AD_Window (AD_Client_ID,AD_Org_ID,AD_Window_ID,Created,CreatedBy,EntityType,IsActive,IsBetaFunctionality,IsDefault,IsSOTrx,Name,Processing,Updated,UpdatedBy,WindowType) VALUES (0,0,53101,TO_DATE('2009-09-18 13:27:44','YYYY-MM-DD HH24:MI:SS'),0,'D','Y','N','N','Y','Order Source','N',TO_DATE('2009-09-18 13:27:44','YYYY-MM-DD HH24:MI:SS'),0,'M')
;

-- Sep 18, 2009 1:27:45 PM CEST
-- FF [2913307] - New table: Order Source
-- https://sourceforge.net/tracker/?func=detail&aid=2913307&group_id=176962&atid=883808
INSERT INTO AD_Window_Trl (AD_Language,AD_Window_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Window_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Window t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Window_ID=53101 AND EXISTS (SELECT * FROM AD_Window_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Window_ID!=t.AD_Window_ID)
;

-- Sep 18, 2009 1:27:46 PM CEST
-- FF [2913307] - New table: Order Source
-- https://sourceforge.net/tracker/?func=detail&aid=2913307&group_id=176962&atid=883808
INSERT INTO AD_Tab (AD_Client_ID,AD_Org_ID,AD_Tab_ID,AD_Table_ID,AD_Window_ID,Created,CreatedBy,EntityType,HasTree,IsActive,IsAdvancedTab,IsInsertRecord,IsReadOnly,IsSingleRow,IsSortTab,IsTranslationTab,Name,Processing,SeqNo,TabLevel,Updated,UpdatedBy) VALUES (0,0,53284,53244,53101,TO_DATE('2009-09-18 13:27:45','YYYY-MM-DD HH24:MI:SS'),0,'D','N','Y','N','Y','N','N','N','N','Order Source','N',10,0,TO_DATE('2009-09-18 13:27:45','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 18, 2009 1:27:46 PM CEST
-- FF [2913307] - New table: Order Source
-- https://sourceforge.net/tracker/?func=detail&aid=2913307&group_id=176962&atid=883808
INSERT INTO AD_Tab_Trl (AD_Language,AD_Tab_ID, CommitWarning,Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Tab_ID, t.CommitWarning,t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Tab t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Tab_ID=53284 AND EXISTS (SELECT * FROM AD_Tab_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Tab_ID!=t.AD_Tab_ID)
;

-- Sep 18, 2009 1:27:47 PM CEST
-- FF [2913307] - New table: Order Source
-- https://sourceforge.net/tracker/?func=detail&aid=2913307&group_id=176962&atid=883808
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,58397,58029,0,53284,TO_DATE('2009-09-18 13:27:46','YYYY-MM-DD HH24:MI:SS'),0,'D','Y','Y','Y','N','N','N','N','N','C_OrderSource_ID',10,TO_DATE('2009-09-18 13:27:46','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 18, 2009 1:27:47 PM CEST
-- FF [2913307] - New table: Order Source
-- https://sourceforge.net/tracker/?func=detail&aid=2913307&group_id=176962&atid=883808
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58029 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 18, 2009 1:27:47 PM CEST
-- FF [2913307] - New table: Order Source
-- https://sourceforge.net/tracker/?func=detail&aid=2913307&group_id=176962&atid=883808
UPDATE AD_Field SET IsDisplayed='N', IsReadOnly='N', Name='Order Source', SeqNo=10,Updated=TO_DATE('2009-09-18 13:27:47','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=58029
;

-- Sep 18, 2009 1:27:47 PM CEST
-- FF [2913307] - New table: Order Source
-- https://sourceforge.net/tracker/?func=detail&aid=2913307&group_id=176962&atid=883808
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=58029
;

-- Sep 18, 2009 1:27:48 PM CEST
-- FF [2913307] - New table: Order Source
-- https://sourceforge.net/tracker/?func=detail&aid=2913307&group_id=176962&atid=883808
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,58398,58030,0,53284,TO_DATE('2009-09-18 13:27:47','YYYY-MM-DD HH24:MI:SS'),0,'Client/Tenant for this installation.','D','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','Y','N','N','N','N','N','Client',20,TO_DATE('2009-09-18 13:27:47','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 18, 2009 1:27:48 PM CEST
-- FF [2913307] - New table: Order Source
-- https://sourceforge.net/tracker/?func=detail&aid=2913307&group_id=176962&atid=883808
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58030 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 18, 2009 1:27:49 PM CEST
-- FF [2913307] - New table: Order Source
-- https://sourceforge.net/tracker/?func=detail&aid=2913307&group_id=176962&atid=883808
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,58399,58031,0,53284,TO_DATE('2009-09-18 13:27:48','YYYY-MM-DD HH24:MI:SS'),0,'Organizational entity within client','D','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','Y','N','N','N','N','N','Organization',30,TO_DATE('2009-09-18 13:27:48','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 18, 2009 1:27:49 PM CEST
-- FF [2913307] - New table: Order Source
-- https://sourceforge.net/tracker/?func=detail&aid=2913307&group_id=176962&atid=883808
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58031 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 18, 2009 1:27:49 PM CEST
-- FF [2913307] - New table: Order Source
-- https://sourceforge.net/tracker/?func=detail&aid=2913307&group_id=176962&atid=883808
UPDATE AD_Field SET IsDisplayed='Y', IsReadOnly='N', IsSameLine='Y', Name='Organization', SeqNo=30,Updated=TO_DATE('2009-09-18 13:27:49','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=58031
;

-- Sep 18, 2009 1:27:50 PM CEST
-- FF [2913307] - New table: Order Source
-- https://sourceforge.net/tracker/?func=detail&aid=2913307&group_id=176962&atid=883808
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,58405,58032,0,53284,TO_DATE('2009-09-18 13:27:49','YYYY-MM-DD HH24:MI:SS'),0,'Search key for the record in the format required - must be unique','D','A search key allows you a fast method of finding a particular record.
If you leave the search key empty, the system automatically creates a numeric number.  The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).','Y','Y','Y','N','N','N','N','N','Search Key',40,TO_DATE('2009-09-18 13:27:49','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 18, 2009 1:27:50 PM CEST
-- FF [2913307] - New table: Order Source
-- https://sourceforge.net/tracker/?func=detail&aid=2913307&group_id=176962&atid=883808
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58032 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 18, 2009 1:27:51 PM CEST
-- FF [2913307] - New table: Order Source
-- https://sourceforge.net/tracker/?func=detail&aid=2913307&group_id=176962&atid=883808
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,58406,58033,0,53284,TO_DATE('2009-09-18 13:27:51','YYYY-MM-DD HH24:MI:SS'),0,'Alphanumeric identifier of the entity','D','The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.','Y','Y','Y','N','N','N','N','N','Name',50,TO_DATE('2009-09-18 13:27:51','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 18, 2009 1:27:51 PM CEST
-- FF [2913307] - New table: Order Source
-- https://sourceforge.net/tracker/?func=detail&aid=2913307&group_id=176962&atid=883808
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58033 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 18, 2009 1:27:52 PM CEST
-- FF [2913307] - New table: Order Source
-- https://sourceforge.net/tracker/?func=detail&aid=2913307&group_id=176962&atid=883808
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,58407,58034,0,53284,TO_DATE('2009-09-18 13:27:51','YYYY-MM-DD HH24:MI:SS'),0,'Optional short description of the record','D','A description is limited to 255 characters.','Y','Y','Y','N','N','N','N','N','Description',60,TO_DATE('2009-09-18 13:27:51','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 18, 2009 1:27:52 PM CEST
-- FF [2913307] - New table: Order Source
-- https://sourceforge.net/tracker/?func=detail&aid=2913307&group_id=176962&atid=883808
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58034 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 18, 2009 1:27:52 PM CEST
-- FF [2913307] - New table: Order Source
-- https://sourceforge.net/tracker/?func=detail&aid=2913307&group_id=176962&atid=883808
UPDATE AD_Field SET DisplayLength=30, IsDisplayed='Y', IsReadOnly='N', Name='Description', SeqNo=60,Updated=TO_DATE('2009-09-18 13:27:52','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=58034
;

-- Sep 18, 2009 1:27:53 PM CEST
-- FF [2913307] - New table: Order Source
-- https://sourceforge.net/tracker/?func=detail&aid=2913307&group_id=176962&atid=883808
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,58400,58035,0,53284,TO_DATE('2009-09-18 13:27:52','YYYY-MM-DD HH24:MI:SS'),0,'The record is active in the system','D','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','Y','N','N','N','N','N','Active',70,TO_DATE('2009-09-18 13:27:52','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 18, 2009 1:27:53 PM CEST
-- FF [2913307] - New table: Order Source
-- https://sourceforge.net/tracker/?func=detail&aid=2913307&group_id=176962&atid=883808
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58035 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 18, 2009 1:27:53 PM CEST
-- FF [2913307] - New table: Order Source
-- https://sourceforge.net/tracker/?func=detail&aid=2913307&group_id=176962&atid=883808
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,58408,58036,0,53284,TO_DATE('2009-09-18 13:27:53','YYYY-MM-DD HH24:MI:SS'),0,'Comment or Hint','D','The Help field contains a hint, comment or help about the use of this item.','Y','Y','Y','N','N','N','N','N','Comment/Help',80,TO_DATE('2009-09-18 13:27:53','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 18, 2009 1:27:53 PM CEST
-- FF [2913307] - New table: Order Source
-- https://sourceforge.net/tracker/?func=detail&aid=2913307&group_id=176962&atid=883808
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58036 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 18, 2009 1:27:53 PM CEST
-- FF [2913307] - New table: Order Source
-- https://sourceforge.net/tracker/?func=detail&aid=2913307&group_id=176962&atid=883808
UPDATE AD_Field SET DisplayLength=60, IsDisplayed='Y', IsReadOnly='N', Name='Help', SeqNo=80,Updated=TO_DATE('2009-09-18 13:27:53','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=58036
;

-- Sep 18, 2009 1:27:53 PM CEST
-- FF [2913307] - New table: Order Source
-- https://sourceforge.net/tracker/?func=detail&aid=2913307&group_id=176962&atid=883808
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=58036
;

-- Sep 18, 2009 1:27:53 PM CEST
-- FF [2913307] - New table: Order Source
-- https://sourceforge.net/tracker/?func=detail&aid=2913307&group_id=176962&atid=883808
UPDATE AD_Menu SET Action='W', AD_Window_ID=53101, IsSummary='N',Updated=TO_DATE('2009-09-18 13:27:53','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Menu_ID=53249
;

-- Sep 18, 2009 1:27:54 PM CEST
-- FF [2913307] - New table: Order Source
-- https://sourceforge.net/tracker/?func=detail&aid=2913307&group_id=176962&atid=883808
UPDATE AD_TreeNodeMM SET SeqNo=110,Updated=TO_DATE('2009-09-18 13:27:54','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Tree_ID=10 AND Node_ID=53249
;

-- Sep 18, 2009 1:27:54 PM CEST
-- FF [2913307] - New table: Order Source
-- https://sourceforge.net/tracker/?func=detail&aid=2913307&group_id=176962&atid=883808
UPDATE AD_Tab SET IsSingleRow='N', TabLevel=0,Updated=TO_DATE('2009-09-18 13:27:54','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Tab_ID=186
;

-- Sep 18, 2009 1:27:54 PM CEST
-- FF [2913307] - New table: Order Source
-- https://sourceforge.net/tracker/?func=detail&aid=2913307&group_id=176962&atid=883808
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,58409,58037,0,186,TO_DATE('2009-09-18 13:27:54','YYYY-MM-DD HH24:MI:SS'),0,'D','Y','Y','Y','N','N','N','N','N','C_OrderSource_ID',10,TO_DATE('2009-09-18 13:27:54','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 18, 2009 1:27:54 PM CEST
-- FF [2913307] - New table: Order Source
-- https://sourceforge.net/tracker/?func=detail&aid=2913307&group_id=176962&atid=883808
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58037 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 18, 2009 1:27:54 PM CEST
-- FF [2913307] - New table: Order Source
-- https://sourceforge.net/tracker/?func=detail&aid=2913307&group_id=176962&atid=883808
UPDATE AD_Field SET IsDisplayed='Y', IsReadOnly='N', Name='Order Source', SeqNo=75,Updated=TO_DATE('2009-09-18 13:27:54','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=58037
;

-- Sep 18, 2009 1:27:54 PM CEST
-- FF [2913307] - New table: Order Source
-- https://sourceforge.net/tracker/?func=detail&aid=2913307&group_id=176962&atid=883808
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=58037
;



-- Nov 27, 2009 2:30:13 PM CET
-- FF [2913307] - New table: Order Source
-- https://sourceforge.net/tracker/?func=detail&aid=2913307&group_id=176962&atid=883808
-- Set Proper window (Order Source)
UPDATE AD_Table SET AD_Window_ID=53101,Updated=TO_DATE('2009-11-27 14:30:13','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Table_ID=53244
;



-- Dec 12, 2009 4:59:35 PM CET
-- FF [2913307] - New table: Order Source
-- https://sourceforge.net/tracker/?func=detail&aid=2913307&group_id=176962&atid=883808

UPDATE AD_TreeNodeMM SET Parent_ID=457, SeqNo=0, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=52001
;

-- Dec 12, 2009 4:59:35 PM CET
-- FF [2913307] - New table: Order Source
-- https://sourceforge.net/tracker/?func=detail&aid=2913307&group_id=176962&atid=883808

UPDATE AD_TreeNodeMM SET Parent_ID=457, SeqNo=1, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=460
;

-- Dec 12, 2009 4:59:35 PM CET
-- FF [2913307] - New table: Order Source
-- https://sourceforge.net/tracker/?func=detail&aid=2913307&group_id=176962&atid=883808

UPDATE AD_TreeNodeMM SET Parent_ID=457, SeqNo=2, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=301
;

-- Dec 12, 2009 4:59:35 PM CET
-- FF [2913307] - New table: Order Source
-- https://sourceforge.net/tracker/?func=detail&aid=2913307&group_id=176962&atid=883808

UPDATE AD_TreeNodeMM SET Parent_ID=457, SeqNo=3, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53249
;

-- Dec 12, 2009 4:59:35 PM CET
-- FF [2913307] - New table: Order Source
-- https://sourceforge.net/tracker/?func=detail&aid=2913307&group_id=176962&atid=883808

UPDATE AD_TreeNodeMM SET Parent_ID=457, SeqNo=4, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=129
;

-- Dec 12, 2009 4:59:35 PM CET
-- FF [2913307] - New table: Order Source
-- https://sourceforge.net/tracker/?func=detail&aid=2913307&group_id=176962&atid=883808

UPDATE AD_TreeNodeMM SET Parent_ID=457, SeqNo=5, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=543
;

-- Dec 12, 2009 4:59:35 PM CET
-- FF [2913307] - New table: Order Source
-- https://sourceforge.net/tracker/?func=detail&aid=2913307&group_id=176962&atid=883808

UPDATE AD_TreeNodeMM SET Parent_ID=457, SeqNo=6, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=195
;

-- Dec 12, 2009 4:59:35 PM CET
-- FF [2913307] - New table: Order Source
-- https://sourceforge.net/tracker/?func=detail&aid=2913307&group_id=176962&atid=883808

UPDATE AD_TreeNodeMM SET Parent_ID=457, SeqNo=7, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53223
;

-- Dec 12, 2009 4:59:35 PM CET
-- FF [2913307] - New table: Order Source
-- https://sourceforge.net/tracker/?func=detail&aid=2913307&group_id=176962&atid=883808

UPDATE AD_TreeNodeMM SET Parent_ID=457, SeqNo=8, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=407
;

-- Dec 12, 2009 4:59:35 PM CET
-- FF [2913307] - New table: Order Source
-- https://sourceforge.net/tracker/?func=detail&aid=2913307&group_id=176962&atid=883808

UPDATE AD_TreeNodeMM SET Parent_ID=457, SeqNo=9, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=406
;

-- Dec 12, 2009 4:59:35 PM CET
-- FF [2913307] - New table: Order Source
-- https://sourceforge.net/tracker/?func=detail&aid=2913307&group_id=176962&atid=883808

UPDATE AD_TreeNodeMM SET Parent_ID=457, SeqNo=10, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=335
;

-- Dec 12, 2009 4:59:35 PM CET
-- FF [2913307] - New table: Order Source
-- https://sourceforge.net/tracker/?func=detail&aid=2913307&group_id=176962&atid=883808

UPDATE AD_TreeNodeMM SET Parent_ID=457, SeqNo=11, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=436
;

-- Dec 12, 2009 4:59:35 PM CET
-- FF [2913307] - New table: Order Source
-- https://sourceforge.net/tracker/?func=detail&aid=2913307&group_id=176962&atid=883808

UPDATE AD_TreeNodeMM SET Parent_ID=457, SeqNo=12, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=507
;

-- Dec 12, 2009 4:59:35 PM CET
-- FF [2913307] - New table: Order Source
-- https://sourceforge.net/tracker/?func=detail&aid=2913307&group_id=176962&atid=883808

UPDATE AD_TreeNodeMM SET Parent_ID=457, SeqNo=13, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=448
;

-- Dec 12, 2009 4:59:35 PM CET
-- FF [2913307] - New table: Order Source
-- https://sourceforge.net/tracker/?func=detail&aid=2913307&group_id=176962&atid=883808

UPDATE AD_TreeNodeMM SET Parent_ID=457, SeqNo=14, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=449
;

-- Dec 12, 2009 4:59:35 PM CET
-- FF [2913307] - New table: Order Source
-- https://sourceforge.net/tracker/?func=detail&aid=2913307&group_id=176962&atid=883808

UPDATE AD_TreeNodeMM SET Parent_ID=457, SeqNo=15, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=492
;

-- Dec 12, 2009 4:59:35 PM CET
-- FF [2913307] - New table: Order Source
-- https://sourceforge.net/tracker/?func=detail&aid=2913307&group_id=176962&atid=883808

UPDATE AD_TreeNodeMM SET Parent_ID=457, SeqNo=16, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=491
;

-- Dec 12, 2009 4:59:35 PM CET
-- FF [2913307] - New table: Order Source
-- https://sourceforge.net/tracker/?func=detail&aid=2913307&group_id=176962&atid=883808

UPDATE AD_TreeNodeMM SET Parent_ID=457, SeqNo=17, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=419
;

