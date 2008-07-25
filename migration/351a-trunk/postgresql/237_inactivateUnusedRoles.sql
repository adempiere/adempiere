-- 25-jul-2008 10:39:47 COT
-- Default comment for updating dictionary
UPDATE AD_Role SET IsActive='N', UserLevel='S  ',Updated=TO_TIMESTAMP('2008-07-25 10:39:47','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Role_ID=50002
;

-- 25-jul-2008 10:39:50 COT
-- Default comment for updating dictionary
UPDATE AD_Role SET IsActive='N', UserLevel='S  ',Updated=TO_TIMESTAMP('2008-07-25 10:39:50','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Role_ID=50001
;

-- 25-jul-2008 10:39:50 COT
-- Default comment for updating dictionary
DELETE FROM AD_Window_Access WHERE AD_Role_ID=50001
;

-- 25-jul-2008 10:39:50 COT
-- Default comment for updating dictionary
INSERT INTO AD_Window_Access (AD_Window_ID, AD_Role_ID, AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,IsReadWrite) SELECT DISTINCT w.AD_Window_ID, 50001,0,0,'Y', CURRENT_TIMESTAMP,100, CURRENT_TIMESTAMP,100,'Y' FROM AD_Window w INNER JOIN AD_Tab t ON (w.AD_Window_ID=t.AD_Window_ID) INNER JOIN AD_Table tt ON (t.AD_Table_ID=tt.AD_Table_ID) WHERE t.SeqNo=(SELECT MIN(SeqNo) FROM AD_Tab xt WHERE xt.AD_Window_ID=w.AD_Window_ID)AND tt.AccessLevel IN ('4','7','6')
;

-- 25-jul-2008 10:39:50 COT
-- Default comment for updating dictionary
DELETE FROM AD_Process_Access WHERE AD_Role_ID=50001
;

-- 25-jul-2008 10:39:50 COT
-- Default comment for updating dictionary
INSERT INTO AD_Process_Access (AD_Process_ID, AD_Role_ID, AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,IsReadWrite) SELECT DISTINCT p.AD_Process_ID, 50001,0,0,'Y', CURRENT_TIMESTAMP,100, CURRENT_TIMESTAMP,100,'Y' FROM AD_Process p WHERE AccessLevel IN ('4','7','6')
;

-- 25-jul-2008 10:39:50 COT
-- Default comment for updating dictionary
DELETE FROM AD_Form_Access WHERE AD_Role_ID=50001
;

-- 25-jul-2008 10:39:50 COT
-- Default comment for updating dictionary
INSERT INTO AD_Form_Access (AD_Form_ID, AD_Role_ID, AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,IsReadWrite) SELECT f.AD_Form_ID, 50001,0,0,'Y', CURRENT_TIMESTAMP,100, CURRENT_TIMESTAMP,100,'Y' FROM AD_Form f WHERE AccessLevel IN ('4','7','6')
;

-- 25-jul-2008 10:39:50 COT
-- Default comment for updating dictionary
DELETE FROM AD_WorkFlow_Access WHERE AD_Role_ID=50001
;

-- 25-jul-2008 10:39:50 COT
-- Default comment for updating dictionary
INSERT INTO AD_WorkFlow_Access (AD_WorkFlow_ID, AD_Role_ID, AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,IsReadWrite) SELECT w.AD_WorkFlow_ID, 50001,0,0,'Y', CURRENT_TIMESTAMP,100, CURRENT_TIMESTAMP,100,'Y' FROM AD_WorkFlow w WHERE AccessLevel IN ('4','7','6')
;

-- 25-jul-2008 10:39:50 COT
-- Default comment for updating dictionary
DELETE FROM AD_Document_Action_Access WHERE AD_Role_ID=50001
;

-- 25-jul-2008 10:39:50 COT
-- Default comment for updating dictionary
INSERT INTO AD_Document_Action_Access (AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,C_DocType_ID , AD_Ref_List_ID, AD_Role_ID) (SELECT 0,0,'Y', CURRENT_TIMESTAMP,100, CURRENT_TIMESTAMP,100, doctype.C_DocType_ID, "action".AD_Ref_List_ID, rol.AD_Role_ID FROM AD_Client client INNER JOIN C_DocType doctype ON (doctype.AD_Client_ID=client.AD_Client_ID) INNER JOIN AD_Ref_List "action" ON ("action".AD_Reference_ID=135) INNER JOIN AD_Role rol ON (rol.AD_Client_ID=client.AD_Client_ID AND rol.AD_Role_ID=50001) )
;

