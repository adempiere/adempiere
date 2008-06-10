-- Jun 9, 2008 8:18:14 PM COT
-- [ 1989305 ] Missing GL Journal Batch in GardenWorld
INSERT INTO AD_Sequence (UpdatedBy,IsAutoSequence,AD_Org_ID,AD_Sequence_ID,Updated,CurrentNext,IsTableID,Created,StartNewYear,IsAudited,IncrementNo,AD_Client_ID,StartNo,Name,CurrentNextSys,CreatedBy,IsActive) VALUES (100,'Y',0,53162,TO_TIMESTAMP('2008-06-09 20:18:12','YYYY-MM-DD HH24:MI:SS'),100,'N',TO_TIMESTAMP('2008-06-09 20:18:12','YYYY-MM-DD HH24:MI:SS'),'N','N',1,11,1000,'GL Journal Batch',1002,100,'Y')
;

-- Jun 9, 2008 8:18:33 PM COT
INSERT INTO C_DocType (IsSOTrx,UpdatedBy,IsDocNoControlled,AD_Org_ID,DocNoSequence_ID,Updated,DocumentCopies,HasCharges,C_DocType_ID,Created,IsDefault,GL_Category_ID,PrintName,AD_Client_ID,HasProforma,Name,CreatedBy,IsShipConfirm,IsPickQAConfirm,DocBaseType,IsDefaultCounterDoc,IsActive,IsSplitWhenDifference,IsCreateCounter,IsInTransit,IsOverwriteDateOnComplete,IsOverwriteSeqOnComplete,IsIndexed) VALUES ('N',100,'Y',0,53162,TO_TIMESTAMP('2008-06-09 20:18:32','YYYY-MM-DD HH24:MI:SS'),0,'N',50001,TO_TIMESTAMP('2008-06-09 20:18:32','YYYY-MM-DD HH24:MI:SS'),'N',108,'Journal Batch',11,'N','GL Journal Batch',100,'N','N','GLJ','N','Y','N','Y','N','N','N','Y')
;

-- Jun 9, 2008 8:18:34 PM COT
INSERT INTO C_DocType_Trl (AD_Language,C_DocType_ID, DocumentNote,PrintName,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.C_DocType_ID, t.DocumentNote,t.PrintName,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, C_DocType t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.C_DocType_ID=50001 AND EXISTS (SELECT * FROM C_DocType_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.C_DocType_ID!=t.C_DocType_ID)
;

-- Jun 9, 2008 8:18:34 PM COT
INSERT INTO AD_Document_Action_Access (AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,C_DocType_ID , AD_Ref_List_ID, AD_Role_ID) (SELECT 11,0,'Y', CURRENT_TIMESTAMP,100, CURRENT_TIMESTAMP,100, doctype.C_DocType_ID, "action".AD_Ref_List_ID, rol.AD_Role_ID FROM AD_Client client INNER JOIN C_DocType doctype ON (doctype.AD_Client_ID=client.AD_Client_ID) INNER JOIN AD_Ref_List "action" ON ("action".AD_Reference_ID=135) INNER JOIN AD_Role rol ON (rol.AD_Client_ID=client.AD_Client_ID) WHERE client.AD_Client_ID=11 AND doctype.C_DocType_ID=50001 AND rol.IsManual='N')
;

