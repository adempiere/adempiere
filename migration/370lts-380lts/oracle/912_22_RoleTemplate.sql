SET DEFINE OFF
SET SQLBLANKLINES ON
SET SCAN OFF
-- Nov 2, 2013 12:13:15 PM IST
-- Create Template Role
INSERT INTO AD_Role (AD_Client_ID,AD_Org_ID,AD_Role_ID,Allow_Info_Account,Allow_Info_Asset,Allow_Info_BPartner,Allow_Info_CashJournal,Allow_Info_CRP,Allow_Info_InOut,Allow_Info_Invoice,Allow_Info_MRP,Allow_Info_Order,Allow_Info_Payment,Allow_Info_Product,Allow_Info_Resource,Allow_Info_Schedule,AmtApproval,ConfirmQueryRecords,Created,CreatedBy,IsAccessAllOrgs,IsActive,IsCanApproveOwnDoc,IsCanExport,IsCanReport,IsChangeLog,IsManual,IsPersonalAccess,IsPersonalLock,IsShowAcct,IsUseUserOrgAccess,MaxQueryRecords,Name,OverwritePriceLimit,PreferenceType,Updated,UpdatedBy,UserDiscount,UserLevel) VALUES (0,0,50008,'Y','N','Y','N','N','Y','Y','N','Y','Y','Y','N','N',0,0,TO_DATE('2013-11-02 12:13:14','YYYY-MM-DD HH24:MI:SS'),0,'N','Y','N','Y','Y','N','N','N','N','N','N',0,'Role Template','N','O',TO_DATE('2013-11-02 12:13:14','YYYY-MM-DD HH24:MI:SS'),0,0,'S  ')
;

-- Nov 2, 2013 12:13:15 PM IST
-- Create Template Role
INSERT INTO AD_User_Roles (AD_Client_ID,AD_Org_ID,AD_Role_ID,AD_User_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,50008,100,TO_DATE('2013-11-02 12:13:15','YYYY-MM-DD HH24:MI:SS'),0,'Y',TO_DATE('2013-11-02 12:13:15','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Nov 2, 2013 12:13:15 PM IST
-- Create Template Role
INSERT INTO AD_User_Roles (AD_Client_ID,AD_Org_ID,AD_Role_ID,AD_User_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,50008,0,TO_DATE('2013-11-02 12:13:15','YYYY-MM-DD HH24:MI:SS'),0,'Y',TO_DATE('2013-11-02 12:13:15','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Nov 2, 2013 12:13:15 PM IST
-- Create Template Role
DELETE FROM AD_Browse_Access WHERE AD_Role_ID=50008
;

-- Nov 2, 2013 12:13:15 PM IST
-- Create Template Role
INSERT INTO AD_Browse_Access (AD_Browse_ID, AD_Role_ID, AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,IsReadWrite) SELECT b.AD_Browse_ID, 50008,0,0,'Y', SysDate,0, SysDate,0,'Y' FROM AD_Browse b WHERE AccessLevel IN ('4','7','6')
;

-- Nov 2, 2013 12:13:15 PM IST
-- Create Template Role
DELETE FROM AD_Document_Action_Access WHERE AD_Role_ID=50008
;

-- Nov 2, 2013 12:13:15 PM IST
-- Create Template Role
INSERT INTO AD_Document_Action_Access (AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,C_DocType_ID , AD_Ref_List_ID, AD_Role_ID) (SELECT 0,0,'Y', SysDate,0, SysDate,0, doctype.C_DocType_ID, action.AD_Ref_List_ID, rol.AD_Role_ID FROM AD_Client client INNER JOIN C_DocType doctype ON (doctype.AD_Client_ID=client.AD_Client_ID) INNER JOIN AD_Ref_List action ON (action.AD_Reference_ID=135) INNER JOIN AD_Role rol ON (rol.AD_Client_ID=client.AD_Client_ID AND rol.AD_Role_ID=50008) )
;

UPDATE AD_ROLE SET USERLEVEL=' CO' WHERE AD_ROLE_ID = 50008
;

INSERT INTO AD_Window_Access (AD_Window_ID, AD_Role_ID, AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,IsReadWrite) SELECT DISTINCT w.AD_Window_ID,50008,0,0,'Y',SYSDATE,0,SYSDATE,0,'Y' FROM AD_Window w INNER JOIN AD_Tab t ON (w.AD_Window_ID=t.AD_Window_ID) INNER JOIN AD_Table tt ON (t.AD_Table_ID=tt.AD_Table_ID) WHERE t.SeqNo=(SELECT MIN(SeqNo) FROM AD_Tab xt WHERE xt.AD_Window_ID=w.AD_Window_ID) AND tt.AccessLevel IN ('7','6','3','2','1') 
;

INSERT INTO AD_Process_Access (AD_Process_ID, AD_Role_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,IsReadWrite) SELECT DISTINCT p.AD_Process_ID,50008,0,0,'Y',SYSDATE,0,SYSDATE,0,'Y' FROM AD_Process p WHERE AccessLevel IN ('7','6','3','2','1')
;

INSERT INTO AD_Form_Access (AD_Form_ID, AD_Role_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,IsReadWrite) SELECT f.AD_Form_ID,50008,0,0,'Y',SYSDATE,0,SYSDATE,0,'Y' FROM AD_Form f WHERE AccessLevel IN ('7','6','3','2','1')
;

INSERT INTO AD_WorkFlow_Access (AD_WorkFlow_ID, AD_Role_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,IsReadWrite) SELECT w.AD_WorkFlow_ID,50008,0,0,'Y',SYSDATE,0,SYSDATE,0,'Y' FROM AD_WorkFlow w WHERE AccessLevel IN ('7','6','3','2','1')
;

UPDATE AD_WINDOW_ACCESS SET IsActive='N' WHERE AD_Role_ID=50008
;

UPDATE AD_BROWSE_ACCESS SET IsActive='N' WHERE AD_Role_ID=50008
;

UPDATE AD_PROCESS_ACCESS SET IsActive='N' WHERE AD_Role_ID=50008
;

UPDATE AD_FORM_ACCESS SET IsActive='N' WHERE AD_Role_ID=50008
;

UPDATE AD_WORKFLOW_ACCESS SET IsActive='N' WHERE AD_Role_ID=50008
;

UPDATE AD_WORKFLOW_ACCESS WA SET IsActive='Y' WHERE AD_Role_ID=50008 AND NOT EXISTS (SELECT * FROM AD_MENU M WHERE M.AD_WORKFLOW_ID=WA.AD_WORKFLOW_ID)
;

UPDATE AD_PROCESS_ACCESS PA SET IsActive='Y' WHERE AD_Role_ID=50008 AND NOT EXISTS (SELECT * FROM AD_MENU M WHERE M.AD_PROCESS_ID=PA.AD_PROCESS_ID)
;

-- Nov 2, 2013 10:54:18 AM IST
-- Create Template Role
UPDATE AD_Role SET IsManual='Y',Updated=TO_TIMESTAMP('2013-11-02 10:54:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Role_ID=50008
;

-- Nov 2, 2013 2:15:56 PM IST
-- Create Template Role
INSERT INTO AD_Role_OrgAccess (AD_Client_ID,AD_Org_ID,AD_Role_ID,Created,CreatedBy,IsActive,IsReadOnly,Updated,UpdatedBy) VALUES (0,0,50008,TO_DATE('2013-11-02 14:15:55','YYYY-MM-DD HH24:MI:SS'),0,'Y','N',TO_DATE('2013-11-02 14:15:55','YYYY-MM-DD HH24:MI:SS'),0)
;