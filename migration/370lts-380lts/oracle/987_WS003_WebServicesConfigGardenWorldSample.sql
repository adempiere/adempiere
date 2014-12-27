-- Jan 30, 2009 7:44:20 PM COT
-- Web Service Definition and Security
INSERT INTO AD_Role (AD_Client_ID,AD_Org_ID,AD_Role_ID,Allow_Info_Account,Allow_Info_Asset,Allow_Info_BPartner,Allow_Info_CashJournal,Allow_Info_InOut,Allow_Info_Invoice,Allow_Info_Order,Allow_Info_Payment,Allow_Info_Product,Allow_Info_Resource,Allow_Info_Schedule,AmtApproval,C_Currency_ID,ConfirmQueryRecords,Created,CreatedBy,IsAccessAllOrgs,IsActive,IsCanApproveOwnDoc,IsCanExport,IsCanReport,IsChangeLog,IsManual,IsPersonalAccess,IsPersonalLock,IsShowAcct,IsUseUserOrgAccess,MaxQueryRecords,Name,OverwritePriceLimit,PreferenceType,Supervisor_ID,Updated,UpdatedBy,UserDiscount,UserLevel) VALUES (11,0,50004,'N','N','N','N','N','N','N','N','N','N','N',0,100,0,TO_DATE('2009-01-30 19:44:19','YYYY-MM-DD HH24:MI:SS'),100,'N','Y','N','N','N','Y','Y','N','N','N','N',0,'Web Service Execution','N','N',101,TO_DATE('2009-01-30 19:44:19','YYYY-MM-DD HH24:MI:SS'),100,0.00,' CO')
;

INSERT INTO AD_User_Roles (AD_Client_ID,AD_Org_ID,AD_Role_ID,AD_User_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (11,11,50004,100,TO_DATE('2009-01-30 19:44:20','YYYY-MM-DD HH24:MI:SS'),100,'Y',TO_DATE('2009-01-30 19:44:20','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Role_OrgAccess (AD_Client_ID,AD_Org_ID,AD_Role_ID,Created,CreatedBy,IsActive,IsReadOnly,Updated,UpdatedBy) VALUES (11,0,50004,TO_DATE('2009-01-30 19:44:28','YYYY-MM-DD HH24:MI:SS'),100,'Y','N',TO_DATE('2009-01-30 19:44:28','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Role_OrgAccess (AD_Client_ID,AD_Org_ID,AD_Role_ID,Created,CreatedBy,IsActive,IsReadOnly,Updated,UpdatedBy) VALUES (11,11,50004,TO_DATE('2009-01-30 19:44:33','YYYY-MM-DD HH24:MI:SS'),100,'Y','N',TO_DATE('2009-01-30 19:44:33','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Role_OrgAccess (AD_Client_ID,AD_Org_ID,AD_Role_ID,Created,CreatedBy,IsActive,IsReadOnly,Updated,UpdatedBy) VALUES (11,12,50004,TO_DATE('2009-01-30 19:44:37','YYYY-MM-DD HH24:MI:SS'),100,'Y','N',TO_DATE('2009-01-30 19:44:37','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_User (AD_Client_ID,AD_Org_ID,AD_User_ID,Created,CreatedBy,IsActive,IsFullBPAccess,Name,NotificationType,Password,Processing,Updated,UpdatedBy,Value) VALUES (11,0,50001,TO_DATE('2009-01-30 19:44:55','YYYY-MM-DD HH24:MI:SS'),100,'Y','Y','WebService','X','WebService','N',TO_DATE('2009-01-30 19:44:55','YYYY-MM-DD HH24:MI:SS'),100,'webserv')
;

INSERT INTO AD_User_Roles (AD_Client_ID,AD_Org_ID,AD_Role_ID,AD_User_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (11,0,50004,50001,TO_DATE('2009-01-30 19:45:11','YYYY-MM-DD HH24:MI:SS'),100,'Y',TO_DATE('2009-01-30 19:45:11','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Process_Access (AD_Client_ID,AD_Org_ID,AD_Process_ID,AD_Role_ID,Created,CreatedBy,IsActive,IsReadWrite,Updated,UpdatedBy) VALUES (11,0,111,50004,TO_DATE('2009-01-30 19:45:27','YYYY-MM-DD HH24:MI:SS'),100,'Y','Y',TO_DATE('2009-01-30 19:45:27','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO WS_WebServiceType (AD_Client_ID,AD_Org_ID,Created,CreatedBy,Description,IsActive,Name,Updated,UpdatedBy,Value,WS_WebService_ID,WS_WebServiceMethod_ID,WS_WebServiceType_ID) VALUES (11,0,TO_DATE('2009-01-30 19:46:34','YYYY-MM-DD HH24:MI:SS'),100,'Configuration of run process web service to process invoices','Y','Process Invoice',TO_DATE('2009-01-30 19:46:34','YYYY-MM-DD HH24:MI:SS'),100,'ProcessInvoice',50001,50022,50000)
;

INSERT INTO WS_WebService_Para (AD_Client_ID,AD_Org_ID,ConstantValue,Created,CreatedBy,IsActive,ParameterName,ParameterType,Updated,UpdatedBy,WS_WebService_Para_ID,WS_WebServiceType_ID) VALUES (11,0,'111',TO_DATE('2009-01-30 19:47:23','YYYY-MM-DD HH24:MI:SS'),100,'Y','AD_Process_ID','C',TO_DATE('2009-01-30 19:47:23','YYYY-MM-DD HH24:MI:SS'),100,50000,50000)
;

INSERT INTO WS_WebService_Para (AD_Client_ID,AD_Org_ID,ConstantValue,Created,CreatedBy,IsActive,ParameterName,ParameterType,Updated,UpdatedBy,WS_WebService_Para_ID,WS_WebServiceType_ID) VALUES (11,0,'CO',TO_DATE('2009-01-30 19:47:43','YYYY-MM-DD HH24:MI:SS'),100,'Y','DocAction','C',TO_DATE('2009-01-30 19:47:43','YYYY-MM-DD HH24:MI:SS'),100,50001,50000)
;

INSERT INTO WS_WebService_Para (AD_Client_ID,AD_Org_ID,ConstantValue,Created,CreatedBy,IsActive,ParameterName,ParameterType,Updated,UpdatedBy,WS_WebService_Para_ID,WS_WebServiceType_ID) VALUES (11,0,'0',TO_DATE('2009-01-30 19:47:51','YYYY-MM-DD HH24:MI:SS'),100,'Y','AD_Menu_ID','C',TO_DATE('2009-01-30 19:47:51','YYYY-MM-DD HH24:MI:SS'),100,50002,50000)
;

INSERT INTO WS_WebService_Para (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,ParameterName,ParameterType,Updated,UpdatedBy,WS_WebService_Para_ID,WS_WebServiceType_ID) VALUES (11,0,TO_DATE('2009-01-30 19:47:59','YYYY-MM-DD HH24:MI:SS'),100,'Y','AD_Record_ID','F',TO_DATE('2009-01-30 19:47:59','YYYY-MM-DD HH24:MI:SS'),100,50003,50000)
;

INSERT INTO WS_WebServiceTypeAccess (AD_Client_ID,AD_Org_ID,AD_Role_ID,Created,CreatedBy,IsActive,IsReadWrite,Updated,UpdatedBy,WS_WebServiceType_ID) VALUES (11,0,50004,TO_DATE('2009-01-30 19:48:12','YYYY-MM-DD HH24:MI:SS'),100,'Y','Y',TO_DATE('2009-01-30 19:48:12','YYYY-MM-DD HH24:MI:SS'),100,50000)
;

INSERT INTO WS_WebServiceType (AD_Client_ID,AD_Org_ID,AD_Table_ID,Created,CreatedBy,Description,IsActive,Name,Updated,UpdatedBy,Value,WS_WebService_ID,WS_WebServiceMethod_ID,WS_WebServiceType_ID) VALUES (11,0,291,TO_DATE('2009-01-30 19:48:50','YYYY-MM-DD HH24:MI:SS'),100,'Configuration of Create Data web service to create business partners','Y','Create BPartner',TO_DATE('2009-01-30 19:48:50','YYYY-MM-DD HH24:MI:SS'),100,'CreateBPartner',50001,50024,50001)
;

INSERT INTO WS_WebService_Para (AD_Client_ID,AD_Org_ID,ConstantValue,Created,CreatedBy,IsActive,ParameterName,ParameterType,Updated,UpdatedBy,WS_WebService_Para_ID,WS_WebServiceType_ID) VALUES (11,0,'C_BPartner',TO_DATE('2009-01-30 19:49:05','YYYY-MM-DD HH24:MI:SS'),100,'Y','TableName','C',TO_DATE('2009-01-30 19:49:05','YYYY-MM-DD HH24:MI:SS'),100,50004,50001)
;

INSERT INTO WS_WebService_Para (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,ParameterName,ParameterType,Updated,UpdatedBy,WS_WebService_Para_ID,WS_WebServiceType_ID) VALUES (11,0,TO_DATE('2009-01-30 19:49:12','YYYY-MM-DD HH24:MI:SS'),100,'Y','RecordID','F',TO_DATE('2009-01-30 19:49:12','YYYY-MM-DD HH24:MI:SS'),100,50005,50001)
;

INSERT INTO WS_WebService_Para (AD_Client_ID,AD_Org_ID,ConstantValue,Created,CreatedBy,IsActive,ParameterName,ParameterType,Updated,UpdatedBy,WS_WebService_Para_ID,WS_WebServiceType_ID) VALUES (11,0,'Create',TO_DATE('2009-01-30 19:49:22','YYYY-MM-DD HH24:MI:SS'),100,'Y','Action','C',TO_DATE('2009-01-30 19:49:22','YYYY-MM-DD HH24:MI:SS'),100,50006,50001)
;

INSERT INTO WS_WebServiceFieldInput (AD_Client_ID,AD_Column_ID,AD_Org_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy,WS_WebServiceFieldInput_ID,WS_WebServiceType_ID) VALUES (11,2901,0,TO_DATE('2009-01-30 19:49:39','YYYY-MM-DD HH24:MI:SS'),100,'Y',TO_DATE('2009-01-30 19:49:39','YYYY-MM-DD HH24:MI:SS'),100,50000,50001)
;

INSERT INTO WS_WebServiceFieldInput (AD_Client_ID,AD_Column_ID,AD_Org_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy,WS_WebServiceFieldInput_ID,WS_WebServiceType_ID) VALUES (11,2902,0,TO_DATE('2009-01-30 19:49:46','YYYY-MM-DD HH24:MI:SS'),100,'Y',TO_DATE('2009-01-30 19:49:46','YYYY-MM-DD HH24:MI:SS'),100,50001,50001)
;

INSERT INTO WS_WebServiceFieldInput (AD_Client_ID,AD_Column_ID,AD_Org_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy,WS_WebServiceFieldInput_ID,WS_WebServiceType_ID) VALUES (11,2909,0,TO_DATE('2009-01-30 19:49:53','YYYY-MM-DD HH24:MI:SS'),100,'Y',TO_DATE('2009-01-30 19:49:53','YYYY-MM-DD HH24:MI:SS'),100,50002,50001)
;

INSERT INTO WS_WebServiceFieldInput (AD_Client_ID,AD_Column_ID,AD_Org_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy,WS_WebServiceFieldInput_ID,WS_WebServiceType_ID) VALUES (11,2915,0,TO_DATE('2009-01-30 19:50:00','YYYY-MM-DD HH24:MI:SS'),100,'Y',TO_DATE('2009-01-30 19:50:00','YYYY-MM-DD HH24:MI:SS'),100,50003,50001)
;

INSERT INTO WS_WebServiceFieldInput (AD_Client_ID,AD_Column_ID,AD_Org_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy,WS_WebServiceFieldInput_ID,WS_WebServiceType_ID) VALUES (11,2916,0,TO_DATE('2009-01-30 19:50:05','YYYY-MM-DD HH24:MI:SS'),100,'Y',TO_DATE('2009-01-30 19:50:05','YYYY-MM-DD HH24:MI:SS'),100,50004,50001)
;

INSERT INTO WS_WebServiceFieldInput (AD_Client_ID,AD_Column_ID,AD_Org_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy,WS_WebServiceFieldInput_ID,WS_WebServiceType_ID) VALUES (11,3082,0,TO_DATE('2009-01-30 19:50:12','YYYY-MM-DD HH24:MI:SS'),100,'Y',TO_DATE('2009-01-30 19:50:12','YYYY-MM-DD HH24:MI:SS'),100,50005,50001)
;

INSERT INTO WS_WebServiceFieldInput (AD_Client_ID,AD_Column_ID,AD_Org_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy,WS_WebServiceFieldInput_ID,WS_WebServiceType_ID) VALUES (11,4216,0,TO_DATE('2009-01-30 19:50:17','YYYY-MM-DD HH24:MI:SS'),100,'Y',TO_DATE('2009-01-30 19:50:17','YYYY-MM-DD HH24:MI:SS'),100,50006,50001)
;

INSERT INTO WS_WebServiceFieldInput (AD_Client_ID,AD_Column_ID,AD_Org_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy,WS_WebServiceFieldInput_ID,WS_WebServiceType_ID) VALUES (11,4940,0,TO_DATE('2009-01-30 19:50:24','YYYY-MM-DD HH24:MI:SS'),100,'Y',TO_DATE('2009-01-30 19:50:24','YYYY-MM-DD HH24:MI:SS'),100,50007,50001)
;

INSERT INTO WS_WebServiceTypeAccess (AD_Client_ID,AD_Org_ID,AD_Role_ID,Created,CreatedBy,IsActive,IsReadWrite,Updated,UpdatedBy,WS_WebServiceType_ID) VALUES (11,0,50004,TO_DATE('2009-01-30 19:50:41','YYYY-MM-DD HH24:MI:SS'),100,'Y','Y',TO_DATE('2009-01-30 19:50:41','YYYY-MM-DD HH24:MI:SS'),100,50001)
;

