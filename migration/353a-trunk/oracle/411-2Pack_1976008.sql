-- Aug 6, 2008 10:39:44 AM SGT
-- Packin to any client
UPDATE AD_Table SET AccessLevel='6',Updated=TO_DATE('2008-08-06 10:39:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Table_ID=50008
;

-- Aug 6, 2008 10:39:57 AM SGT
-- Packin to any client
UPDATE AD_Table SET AccessLevel='6',Updated=TO_DATE('2008-08-06 10:39:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Table_ID=50003
;

-- Aug 6, 2008 10:40:31 AM SGT
-- Packin to any client
DELETE FROM AD_Window_Access WHERE AD_Role_ID=102
;

-- Aug 6, 2008 10:40:32 AM SGT
-- Packin to any client
INSERT INTO AD_Window_Access (AD_Window_ID, AD_Role_ID, AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,IsReadWrite) SELECT DISTINCT w.AD_Window_ID, 102,11,0,'Y', SysDate,100, SysDate,100,'Y' FROM AD_Window w INNER JOIN AD_Tab t ON (w.AD_Window_ID=t.AD_Window_ID) INNER JOIN AD_Table tt ON (t.AD_Table_ID=tt.AD_Table_ID) WHERE t.SeqNo=(SELECT MIN(SeqNo) FROM AD_Tab xt WHERE xt.AD_Window_ID=w.AD_Window_ID)AND tt.AccessLevel IN ('7','6','3','2','1')
;

-- Aug 6, 2008 10:40:32 AM SGT
-- Packin to any client
DELETE FROM AD_Process_Access WHERE AD_Role_ID=102
;

-- Aug 6, 2008 10:40:32 AM SGT
-- Packin to any client
INSERT INTO AD_Process_Access (AD_Process_ID, AD_Role_ID, AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,IsReadWrite) SELECT DISTINCT p.AD_Process_ID, 102,11,0,'Y', SysDate,100, SysDate,100,'Y' FROM AD_Process p WHERE AccessLevel IN ('7','6','3','2','1')
;

