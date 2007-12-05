-- Nov 11, 2007 1:54:50 AM COT
-- FR 1829798 - Easy generation of migration scripts
INSERT INTO AD_MESSAGE (AD_Client_ID,AD_Message_ID,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,MsgText,MsgTip,MsgType,Updated,UpdatedBy,VALUE) VALUES (0,53007,0,TO_TIMESTAMP('2007-11-11 01:54:49','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Log Migration Script','Log Migration Script - Save migration scripts file in %TEMP%/migration_script_*.sql','I',TO_TIMESTAMP('2007-11-11 01:54:49','YYYY-MM-DD HH24:MI:SS'),100,'LogMigrationScript')
;

-- Nov 11, 2007 1:54:50 AM COT
-- FR 1829798 - Easy generation of migration scripts
INSERT INTO AD_MESSAGE_TRL (AD_LANGUAGE,AD_Message_ID, MsgText,MsgTip, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Message_ID, t.MsgText,t.MsgTip, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_MESSAGE t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Message_ID=53007 AND EXISTS (SELECT * FROM AD_MESSAGE_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Message_ID!=t.AD_Message_ID)
;