-- Oct 3, 2009 9:06:52 AM COT
-- FR2872010-Dunning Run for a complete Dunning (not just level)
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,58559,838,0,19,526,'C_Dunning_ID',TO_DATE('2009-10-03 09:06:47','YYYY-MM-DD HH24:MI:SS'),100,'Dunning Rules for overdue invoices','D',22,'The Dunning indicates the rules and method of dunning for past due payments.','Y','Y','N','N','N','N','N','Y','N','N','N','N','N','Dunning',0,TO_DATE('2009-10-03 09:06:47','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- Oct 3, 2009 9:06:52 AM COT
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58559 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Oct 3, 2009 9:07:19 AM COT
UPDATE AD_Column SET IsMandatory='N',Updated=TO_DATE('2009-10-03 09:07:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=58559
;

-- Oct 3, 2009 9:07:21 AM COT
ALTER TABLE C_DunningRun ADD C_Dunning_ID NUMBER(10) DEFAULT NULL 
;

-- Fill dunning_id to make the column mandatory
UPDATE c_dunningrun 
SET c_dunning_id = (SELECT c_dunning_id 
FROM c_dunninglevel 
WHERE c_dunninglevel.c_dunninglevel_id=c_dunningrun.c_dunninglevel_id 
)
;


-- Oct 3, 2009 9:09:09 AM COT
UPDATE AD_Column SET IsMandatory='Y',Updated=TO_DATE('2009-10-03 09:09:09','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=58559
;

-- Oct 3, 2009 9:09:12 AM COT
ALTER TABLE C_DunningRun MODIFY C_Dunning_ID NUMBER(10)
;

-- Oct 3, 2009 9:09:12 AM COT
ALTER TABLE C_DunningRun MODIFY C_Dunning_ID NOT NULL
;

-- Oct 3, 2009 9:09:19 AM COT
UPDATE AD_Column SET IsMandatory='N',Updated=TO_DATE('2009-10-03 09:09:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=7684
;

-- Oct 3, 2009 9:09:22 AM COT
ALTER TABLE C_DunningRun MODIFY C_DunningLevel_ID NUMBER(10) DEFAULT NULL 
;

-- Oct 3, 2009 9:09:22 AM COT
ALTER TABLE C_DunningRun MODIFY C_DunningLevel_ID NULL
;

-- Oct 3, 2009 9:10:14 AM COT
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,58559,58047,0,633,TO_DATE('2009-10-03 09:10:14','YYYY-MM-DD HH24:MI:SS'),100,'Dunning Rules for overdue invoices',14,'D','The Dunning indicates the rules and method of dunning for past due payments.','Y','Y','Y','N','N','N','N','N','Dunning',45,TO_DATE('2009-10-03 09:10:14','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Oct 3, 2009 9:10:14 AM COT
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58047 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Oct 3, 2009 9:10:31 AM COT
UPDATE AD_Field SET SeqNo=50,IsDisplayed='Y' WHERE AD_Field_ID=58047
;

-- Oct 3, 2009 9:10:31 AM COT
UPDATE AD_Field SET SeqNo=60,IsDisplayed='Y' WHERE AD_Field_ID=9808
;

-- Oct 3, 2009 9:10:31 AM COT
UPDATE AD_Field SET SeqNo=70,IsDisplayed='Y' WHERE AD_Field_ID=10637
;

-- Oct 3, 2009 9:10:31 AM COT
UPDATE AD_Field SET SeqNo=80,IsDisplayed='Y' WHERE AD_Field_ID=10620
;

-- Oct 3, 2009 9:10:31 AM COT
UPDATE AD_Field SET SeqNo=90,IsDisplayed='Y' WHERE AD_Field_ID=10619
;

-- Oct 3, 2009 9:10:31 AM COT
UPDATE AD_Field SET SeqNo=100,IsDisplayed='Y' WHERE AD_Field_ID=10618
;

-- Oct 3, 2009 9:12:29 AM COT
INSERT INTO AD_Val_Rule (AD_Client_ID,AD_Org_ID,AD_Val_Rule_ID,Code,Created,CreatedBy,EntityType,IsActive,Name,Type,Updated,UpdatedBy) VALUES (0,0,52069,'C_Dunning_ID=@C_Dunning_ID@',TO_DATE('2009-10-03 09:12:28','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','C_DunningLevel of Dunning','S',TO_DATE('2009-10-03 09:12:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Oct 3, 2009 9:12:50 AM COT
UPDATE AD_Val_Rule SET Code='C_DunningLevel.C_Dunning_ID=@C_Dunning_ID@',Updated=TO_DATE('2009-10-03 09:12:50','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Val_Rule_ID=52069
;

-- Oct 3, 2009 9:13:00 AM COT
UPDATE AD_Column SET AD_Val_Rule_ID=52069,Updated=TO_DATE('2009-10-03 09:13:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=7684
;

-- Oct 3, 2009 9:16:04 AM COT
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,58560,1075,0,19,527,52069,'C_DunningLevel_ID',TO_DATE('2009-10-03 09:16:04','YYYY-MM-DD HH24:MI:SS'),100,'D',22,'Y','Y','N','N','N','N','N','N','Y','N','N','N','N','Dunning Level',0,TO_DATE('2009-10-03 09:16:04','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- Oct 3, 2009 9:16:04 AM COT
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58560 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Oct 3, 2009 9:16:06 AM COT
ALTER TABLE C_DunningRunEntry ADD C_DunningLevel_ID NUMBER(10) DEFAULT NULL 
;

-- Fill dunning level to make the column mandatory
UPDATE c_dunningrunentry 
SET c_dunninglevel_id = (SELECT c_dunninglevel_id 
FROM c_dunningrun 
WHERE c_dunningrun.c_dunningrun_id = c_dunningrunentry.c_dunningrun_id
)
;

-- Oct 3, 2009 9:19:17 AM COT
UPDATE AD_Column SET IsMandatory='Y', IsUpdateable='N',Updated=TO_DATE('2009-10-03 09:19:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=58560
;

-- Oct 3, 2009 9:19:20 AM COT
ALTER TABLE C_DunningRunEntry MODIFY C_DunningLevel_ID NUMBER(10)
;

-- Oct 3, 2009 9:19:20 AM COT
ALTER TABLE C_DunningRunEntry MODIFY C_DunningLevel_ID NOT NULL
;

-- Oct 3, 2009 9:20:47 AM COT
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58560,58048,0,634,TO_DATE('2009-10-03 09:20:46','YYYY-MM-DD HH24:MI:SS'),100,14,'D','Y','Y','Y','N','N','N','N','N','Dunning Level',140,0,TO_DATE('2009-10-03 09:20:46','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Oct 3, 2009 9:20:47 AM COT
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58048 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Oct 3, 2009 9:21:12 AM COT
UPDATE AD_Field SET SeqNo=40,IsDisplayed='Y' WHERE AD_Field_ID=58048
;

-- Oct 3, 2009 9:21:12 AM COT
UPDATE AD_Field SET SeqNo=50,IsDisplayed='Y' WHERE AD_Field_ID=9818
;

-- Oct 3, 2009 9:21:12 AM COT
UPDATE AD_Field SET SeqNo=60,IsDisplayed='Y' WHERE AD_Field_ID=9814
;

-- Oct 3, 2009 9:21:12 AM COT
UPDATE AD_Field SET SeqNo=70,IsDisplayed='Y' WHERE AD_Field_ID=10623
;

-- Oct 3, 2009 9:21:12 AM COT
UPDATE AD_Field SET SeqNo=80,IsDisplayed='Y' WHERE AD_Field_ID=10625
;

-- Oct 3, 2009 9:21:12 AM COT
UPDATE AD_Field SET SeqNo=90,IsDisplayed='Y' WHERE AD_Field_ID=10624
;

-- Oct 3, 2009 9:21:12 AM COT
UPDATE AD_Field SET SeqNo=100,IsDisplayed='Y' WHERE AD_Field_ID=9816
;

-- Oct 3, 2009 9:21:12 AM COT
UPDATE AD_Field SET SeqNo=110,IsDisplayed='Y' WHERE AD_Field_ID=10621
;

-- Oct 3, 2009 9:21:12 AM COT
UPDATE AD_Field SET SeqNo=120,IsDisplayed='Y' WHERE AD_Field_ID=9820
;

-- Oct 3, 2009 9:21:12 AM COT
UPDATE AD_Field SET SeqNo=130,IsDisplayed='Y' WHERE AD_Field_ID=10626
;

-- Oct 3, 2009 9:21:12 AM COT
UPDATE AD_Field SET SeqNo=140,IsDisplayed='Y' WHERE AD_Field_ID=10622
;

-- Oct 3, 2009 9:22:02 AM COT
UPDATE AD_Column SET DefaultValue='@C_DunningLevel_ID@', IsUpdateable='N',Updated=TO_DATE('2009-10-03 09:22:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=58560
;

-- Oct 3, 2009 9:28:51 AM COT
INSERT INTO AD_Val_Rule (AD_Client_ID,AD_Org_ID,AD_Val_Rule_ID,Code,Created,CreatedBy,EntityType,IsActive,Name,Type,Updated,UpdatedBy) VALUES (0,0,52070,'C_DunningLevel.C_Dunning_ID=@C_Dunning_ID@ AND 
((SELECT C_DunningLevel_ID FROM C_DunningRun WHERE C_DunningRun.C_DunningRun_ID=@C_DunningRun_ID@) IS NULL OR
C_DunningLevel.C_DunningLevel_ID IN (SELECT C_DunningLevel_ID FROM C_DunningRun WHERE C_DunningRun.C_DunningRun_ID=@C_DunningRun_ID@))',TO_DATE('2009-10-03 09:28:49','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','C_DunningLevel of Dunning - restrict to level of dunning','S',TO_DATE('2009-10-03 09:28:49','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Oct 3, 2009 9:28:59 AM COT
UPDATE AD_Column SET AD_Val_Rule_ID=52070, IsUpdateable='N',Updated=TO_DATE('2009-10-03 09:28:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=58560
;

-- Oct 4, 2009 6:58:01 PM COT
UPDATE AD_Column SET IsIdentifier='Y', SeqNo=2,Updated=TO_DATE('2009-10-04 18:58:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=58559
;

-- Oct 4, 2009 6:58:05 PM COT
UPDATE AD_Column SET IsIdentifier='Y', SeqNo=3,Updated=TO_DATE('2009-10-04 18:58:05','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=7684
;

CREATE OR REPLACE VIEW C_DUNNING_HEADER_V
(AD_CLIENT_ID, AD_ORG_ID, ISACTIVE, CREATED, CREATEDBY, 
 UPDATED, UPDATEDBY, AD_LANGUAGE, C_DUNNINGRUN_ID, C_DUNNINGRUNENTRY_ID, 
 DUNNINGDATE, PRINTNAME, DOCUMENTNOTE, C_BPARTNER_ID, BPVALUE, 
 BPTAXID, NAICS, DUNS, ORG_LOCATION_ID, TAXID, 
 SALESREP_ID, SALESREP_NAME, BPGREETING, NAME, NAME2, 
 BPCONTACTGREETING, TITLE, PHONE, CONTACTNAME, C_LOCATION_ID, 
 REFERENCENO, POSTAL, AMT, QTY, NOTE, LOGO_ID)
AS 
SELECT dr.AD_Client_ID, dr.AD_Org_ID, dr.IsActive, dr.Created, dr.CreatedBy, dr.Updated, dr.UpdatedBy, 
	cast('en_US' as varchar2(6)) AS AD_Language, dr.C_DunningRun_ID, C_DunningRunEntry_ID,
    dr.DunningDate, dl.PrintName, dl.Note AS DocumentNote,
    dre.C_BPartner_ID, bp.Value AS BPValue, bp.TaxID AS BPTaxID, bp.NAICS, bp.DUNS,
	oi.C_Location_ID AS Org_Location_ID, oi.TaxID,
	dre.SalesRep_ID, COALESCE(ubp.Name, u.Name) AS SalesRep_Name,
	bpg.Greeting AS BPGreeting,
	bp.Name, bp.Name2,
	bpcg.Greeting AS BPContactGreeting,
	bpc.Title, bpc.Phone,
	NULLIF (bpc.Name, bp.Name) AS ContactName,
	bpl.C_Location_ID, bp.ReferenceNo, l.Postal || l.Postal_Add AS Postal,
    dre.Amt, dre.Qty, dre.Note, COALESCE(oi.Logo_ID, ci.Logo_ID) AS Logo_ID
FROM C_DunningRun dr
    INNER JOIN C_DunningRunEntry dre ON (dr.C_DunningRun_ID=dre.C_DunningRun_ID)
    INNER JOIN C_DunningLevel dl ON (dre.C_DunningLevel_ID=dl.C_DunningLevel_ID)
	INNER JOIN C_BPartner bp ON (dre.C_BPartner_ID=bp.C_BPartner_ID)
	LEFT OUTER JOIN C_Greeting bpg on (bp.C_Greeting_ID=bpg.C_Greeting_ID)
	INNER JOIN C_BPartner_Location bpl ON (dre.C_BPartner_Location_ID=bpl.C_BPartner_Location_ID)
    INNER JOIN C_Location l ON (bpl.C_Location_ID=l.C_Location_ID)
	LEFT OUTER JOIN AD_User bpc ON (dre.AD_User_ID=bpc.AD_User_ID)
	LEFT OUTER JOIN C_Greeting bpcg on (bpc.C_Greeting_ID=bpcg.C_Greeting_ID)
	INNER JOIN AD_OrgInfo oi ON (dr.AD_Org_ID=oi.AD_Org_ID)
	INNER JOIN AD_ClientInfo ci ON (dr.AD_Client_ID=ci.AD_Client_ID)
	LEFT OUTER JOIN AD_User u ON (dre.SalesRep_ID=u.AD_User_ID)
	LEFT OUTER JOIN C_BPartner ubp ON (u.C_BPartner_ID=ubp.C_BPartner_ID)
;

CREATE OR REPLACE VIEW C_DUNNING_HEADER_VT
(AD_CLIENT_ID, AD_ORG_ID, ISACTIVE, CREATED, CREATEDBY, 
 UPDATED, UPDATEDBY, AD_LANGUAGE, C_DUNNINGRUN_ID, C_DUNNINGRUNENTRY_ID, 
 DUNNINGDATE, PRINTNAME, DOCUMENTNOTE, C_BPARTNER_ID, BPVALUE, 
 BPTAXID, NAICS, DUNS, ORG_LOCATION_ID, TAXID, 
 SALESREP_ID, SALESREP_NAME, BPGREETING, NAME, NAME2, 
 BPCONTACTGREETING, TITLE, PHONE, CONTACTNAME, C_LOCATION_ID, 
 REFERENCENO, POSTAL, AMT, QTY, NOTE, LOGO_ID)
AS 
SELECT dr.AD_Client_ID, dr.AD_Org_ID, dr.IsActive, dr.Created, dr.CreatedBy, dr.Updated, dr.UpdatedBy, 
	dlt.AD_Language, dr.C_DunningRun_ID, C_DunningRunEntry_ID,
    dr.DunningDate, dlt.PrintName, dlt.Note AS DocumentNote,
    dre.C_BPartner_ID, bp.Value AS BPValue, bp.TaxID AS BPTaxID, bp.NAICS, bp.DUNS,
	oi.C_Location_ID AS Org_Location_ID, oi.TaxID,
	dre.SalesRep_ID, COALESCE(ubp.Name, u.Name) AS SalesRep_Name,
	bpg.Greeting AS BPGreeting,
	bp.Name, bp.Name2,
	bpcg.Greeting AS BPContactGreeting,
	bpc.Title, bpc.Phone,
	NULLIF (bpc.Name, bp.Name) AS ContactName,
	bpl.C_Location_ID, bp.ReferenceNo, l.Postal || l.Postal_Add AS Postal,
    dre.Amt, dre.Qty, dre.Note, COALESCE(oi.Logo_ID, ci.Logo_ID) AS Logo_ID
FROM C_DunningRun dr
    INNER JOIN C_DunningRunEntry dre ON (dr.C_DunningRun_ID=dre.C_DunningRun_ID)
    INNER JOIN C_DunningLevel dl ON (dre.C_DunningLevel_ID=dl.C_DunningLevel_ID)
    INNER JOIN C_DunningLevel_Trl dlt ON (dl.C_DunningLevel_ID=dlt.C_DunningLevel_ID)
	INNER JOIN C_BPartner bp ON (dre.C_BPartner_ID=bp.C_BPartner_ID)
	LEFT OUTER JOIN C_Greeting_Trl bpg on (bp.C_Greeting_ID=bpg.C_Greeting_ID
        AND dlt.AD_Language=bpg.AD_Language)
	INNER JOIN C_BPartner_Location bpl ON (dre.C_BPartner_Location_ID=bpl.C_BPartner_Location_ID)
    INNER JOIN C_Location l ON (bpl.C_Location_ID=l.C_Location_ID)
	LEFT OUTER JOIN AD_User bpc ON (dre.AD_User_ID=bpc.AD_User_ID)
	LEFT OUTER JOIN C_Greeting_Trl bpcg on (bpc.C_Greeting_ID=bpcg.C_Greeting_ID
        AND dlt.AD_Language=bpcg.AD_Language)
	INNER JOIN AD_OrgInfo oi ON (dr.AD_Org_ID=oi.AD_Org_ID)
	INNER JOIN AD_ClientInfo ci ON (dr.AD_Client_ID=ci.AD_Client_ID)
	LEFT OUTER JOIN AD_User u ON (dre.SalesRep_ID=u.AD_User_ID)
	LEFT OUTER JOIN C_BPartner ubp ON (u.C_BPartner_ID=ubp.C_BPartner_ID)
;
