
-- 29/06/2011 1:33:27 PM
-- -
UPDATE AD_Column SET FieldLength=1024,Updated=TO_DATE('2011-06-29 13:33:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=417
;

-- 29/06/2011 1:33:31 PM
-- -
ALTER TABLE AD_User MODIFY Password NVARCHAR2(1024) DEFAULT NULL 
;

-- 29/06/2011 1:34:52 PM
-- -
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,55218,0,'Salt',TO_DATE('2011-06-29 13:34:50','YYYY-MM-DD HH24:MI:SS'),100,'Random data added to improve password hash effectiveness','D','Y','Salt','Salt',TO_DATE('2011-06-29 13:34:50','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 29/06/2011 1:34:52 PM
-- -
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=55218 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- 29/06/2011 1:36:39 PM
-- -
ALTER TABLE AD_User ADD Salt NVARCHAR2(16) DEFAULT NULL 
;


-- 29/06/2011 1:37:48 PM
-- -
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,61756,55218,0,10,114,'Salt',TO_DATE('2011-06-29 13:37:47','YYYY-MM-DD HH24:MI:SS'),100,'Random data added to improve password hash effectiveness','D',16,'Y','Y','N','N','N','N','N','N','N','N','N','N','N','Salt',0,TO_DATE('2011-06-29 13:37:47','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 29/06/2011 1:37:48 PM
-- -
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=61756 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;


-- 29/06/2011 4:59:43 PM
-- -
INSERT INTO AD_Process (AccessLevel,AD_Client_ID,AD_Org_ID,AD_Process_ID,Classname,CopyFromProcess,Created,CreatedBy,Description,EntityType,Help,IsActive,IsBetaFunctionality,IsDirectPrint,IsReport,IsServerProcess,Name,ShowHelp,Statistic_Count,Statistic_Seconds,Updated,UpdatedBy,Value) VALUES ('4',0,0,53259,'org.compiere.process.HashPasswords','N',TO_DATE('2011-06-29 16:59:41','YYYY-MM-DD HH24:MI:SS'),100,'Convert existing plain text/encrypted user passwords to one way hash','D','This process will overwrite existing user passwords with a salted SHA-512 hash of the password so that they cannot be recovered if your database is compromised.

(Note: If your password column is currently encrypted, the hash will also be encrypted.)','Y','N','N','N','N','Convert passwords to hashes','Y',0,0,TO_DATE('2011-06-29 16:59:41','YYYY-MM-DD HH24:MI:SS'),100,'AD_User_HashPassword')
;

-- 29/06/2011 4:59:43 PM
-- -
INSERT INTO AD_Process_Trl (AD_Language,AD_Process_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_ID=53259 AND NOT EXISTS (SELECT * FROM AD_Process_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Process_ID=t.AD_Process_ID)
;

-- 29/06/2011 5:00:28 PM
-- -
INSERT INTO AD_Menu (Action,AD_Client_ID,AD_Menu_ID,AD_Org_ID,AD_Process_ID,Created,CreatedBy,EntityType,IsActive,IsCentrallyMaintained,IsReadOnly,IsSOTrx,IsSummary,Name,Updated,UpdatedBy) VALUES ('P',0,53348,0,53259,TO_DATE('2011-06-29 17:00:27','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Y','N','N','N','Hash Passwords',TO_DATE('2011-06-29 17:00:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 29/06/2011 5:00:28 PM
-- -
INSERT INTO AD_Menu_Trl (AD_Language,AD_Menu_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Menu_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Menu t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Menu_ID=53348 AND NOT EXISTS (SELECT * FROM AD_Menu_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Menu_ID=t.AD_Menu_ID)
;

-- 29/06/2011 5:00:28 PM
-- -
INSERT INTO AD_TreeNodeMM (AD_Client_ID,AD_Org_ID, IsActive,Created,CreatedBy,Updated,UpdatedBy, AD_Tree_ID, Node_ID, Parent_ID, SeqNo) SELECT t.AD_Client_ID, 0, 'Y', SysDate, 100, SysDate, 100,t.AD_Tree_ID, 53348, 0, 999 FROM AD_Tree t WHERE t.AD_Client_ID=0 AND t.IsActive='Y' AND t.IsAllNodes='Y' AND t.TreeType='MM' AND NOT EXISTS (SELECT * FROM AD_TreeNodeMM e WHERE e.AD_Tree_ID=t.AD_Tree_ID AND Node_ID=53348)
;

-- 29/06/2011 5:00:53 PM
-- -
UPDATE AD_TreeNodeMM SET Parent_ID=367, SeqNo=0, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=147
;

-- 29/06/2011 5:00:53 PM
-- -
UPDATE AD_TreeNodeMM SET Parent_ID=367, SeqNo=1, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53246
;

-- 29/06/2011 5:00:53 PM
-- -
UPDATE AD_TreeNodeMM SET Parent_ID=367, SeqNo=2, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=487
;

-- 29/06/2011 5:00:53 PM
-- -
UPDATE AD_TreeNodeMM SET Parent_ID=367, SeqNo=3, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53348
;

-- 29/06/2011 5:00:53 PM
-- -
UPDATE AD_TreeNodeMM SET Parent_ID=367, SeqNo=4, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=150
;

-- 29/06/2011 5:00:53 PM
-- -
UPDATE AD_TreeNodeMM SET Parent_ID=367, SeqNo=5, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=495
;

-- 29/06/2011 5:00:53 PM
-- -
UPDATE AD_TreeNodeMM SET Parent_ID=367, SeqNo=6, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=50007
;

-- 29/06/2011 5:00:53 PM
-- -
UPDATE AD_TreeNodeMM SET Parent_ID=367, SeqNo=7, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=362
;

-- 29/06/2011 5:00:53 PM
-- -
UPDATE AD_TreeNodeMM SET Parent_ID=367, SeqNo=8, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=475
;

-- 29/06/2011 5:00:53 PM
-- -
UPDATE AD_TreeNodeMM SET Parent_ID=367, SeqNo=9, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=366
;

-- 29/06/2011 5:00:53 PM
-- -
UPDATE AD_TreeNodeMM SET Parent_ID=367, SeqNo=10, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=483
;

-- 29/06/2011 5:00:53 PM
-- -
UPDATE AD_TreeNodeMM SET Parent_ID=367, SeqNo=11, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=368
;

-- 29/06/2011 5:00:53 PM
-- -
UPDATE AD_TreeNodeMM SET Parent_ID=367, SeqNo=12, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=508
;

