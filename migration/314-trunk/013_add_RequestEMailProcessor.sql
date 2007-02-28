INSERT INTO ad_process
            (ad_process_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             VALUE, NAME, accesslevel, entitytype,
             isreport, isdirectprint, classname, statistic_count,
             statistic_seconds, isbetafunctionality, isserverprocess, showhelp
            )
     VALUES (50012, 0, 0, 'Y',
             TO_DATE ('02/28/2007 02:57:59', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('02/28/2007 02:57:59', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'Request EMail Processor', 'Request EMail Processor', '3', 'D',
             'N', 'N', 'org.compiere.process.RequestEMailProcessor', 0,
             0, 'N', 'N', 'Y'
            );

INSERT INTO ad_process_access
            (ad_process_id, ad_role_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             isreadwrite
            )
     VALUES (50012, 0, 0, 0, 'Y',
             TO_DATE ('02/28/2007 02:57:59', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('02/28/2007 02:57:59', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'Y'
            );

INSERT INTO ad_process_access
            (ad_process_id, ad_role_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             isreadwrite
            )
     VALUES (50012, 50001, 0, 0, 'Y',
             TO_DATE ('02/28/2007 02:57:59', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('02/28/2007 02:57:59', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'Y'
            );

INSERT INTO ad_process_access
            (ad_process_id, ad_role_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             isreadwrite
            )
     VALUES (50012, 103, 0, 0, 'Y',
             TO_DATE ('02/28/2007 02:57:59', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('02/28/2007 02:57:59', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'Y'
            );

INSERT INTO ad_process_access
            (ad_process_id, ad_role_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             isreadwrite
            )
     VALUES (50012, 102, 0, 0, 'Y',
             TO_DATE ('02/28/2007 02:57:59', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('02/28/2007 02:57:59', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'Y'
            );

INSERT INTO ad_process_para
            (ad_process_para_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             NAME, ad_process_id, seqno, ad_reference_id, columnname,
             iscentrallymaintained, fieldlength, ismandatory, isrange,
             entitytype
            )
     VALUES (50006, 0, 0, 'Y',
             TO_DATE ('02/28/2007 02:59:01', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('02/28/2007 03:11:16', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'IMAP Host', 50012, 10, 10, 'p_IMAPHost',
             'Y', 60, 'Y', 'N',
             'D'
            );

INSERT INTO ad_process_para
            (ad_process_para_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             NAME, ad_process_id, seqno, ad_reference_id, columnname,
             iscentrallymaintained, fieldlength, ismandatory, isrange,
             entitytype
            )
     VALUES (50007, 0, 0, 'Y',
             TO_DATE ('02/28/2007 02:59:22', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('02/28/2007 03:11:32', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'IMAP User', 50012, 20, 10, 'p_IMAPUser',
             'Y', 60, 'Y', 'N',
             'D'
            );

INSERT INTO ad_process_para
            (ad_process_para_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             NAME, ad_process_id, seqno, ad_reference_id, columnname,
             iscentrallymaintained, fieldlength, ismandatory, isrange,
             entitytype
            )
     VALUES (50008, 0, 0, 'Y',
             TO_DATE ('02/28/2007 02:59:34', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('02/28/2007 03:11:41', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'IMAP Password', 50012, 30, 10, 'p_IMAPPwd',
             'Y', 60, 'Y', 'N',
             'D'
            );

INSERT INTO ad_process_para
            (ad_process_para_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             NAME, ad_process_id, seqno, ad_reference_id, columnname,
             iscentrallymaintained, fieldlength, ismandatory, isrange,
             entitytype
            )
     VALUES (50009, 0, 0, 'Y',
             TO_DATE ('02/28/2007 03:00:31', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('02/28/2007 03:11:48', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'Request Folder', 50012, 40, 10, 'p_RequestFolder',
             'Y', 60, 'Y', 'N',
             'D'
            );

INSERT INTO ad_process_para
            (ad_process_para_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             NAME, ad_process_id, seqno, ad_reference_id, columnname,
             iscentrallymaintained, fieldlength, ismandatory, isrange,
             entitytype
            )
     VALUES (50010, 0, 0, 'Y',
             TO_DATE ('02/28/2007 03:01:02', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('02/28/2007 03:11:56', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'Inbox Folder', 50012, 50, 10, 'p_InboxFolder',
             'Y', 60, 'Y', 'N',
             'D'
            );

INSERT INTO ad_process_para
            (ad_process_para_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             NAME, ad_process_id, seqno, ad_reference_id, columnname,
             iscentrallymaintained, fieldlength, ismandatory, isrange,
             entitytype
            )
     VALUES (50011, 0, 0, 'Y',
             TO_DATE ('02/28/2007 03:01:20', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('02/28/2007 03:12:02', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'Error Folder', 50012, 60, 10, 'p_ErrorFolder',
             'Y', 60, 'Y', 'N',
             'D'
            );

INSERT INTO ad_process_para
            (ad_process_para_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             NAME, description,
             HELP,
             ad_process_id, seqno, ad_reference_id, columnname,
             iscentrallymaintained, fieldlength, ismandatory, isrange,
             entitytype
            )
     VALUES (50012, 0, 0, 'Y',
             TO_DATE ('02/28/2007 03:02:11', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('02/28/2007 03:12:11', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'Business Partner', 'Identifies a Business Partner',
             'A Business Partner is anyone with whom you transact.  This can include Vendor, Customer, Employee or Salesperson',
             50012, 70, 30, 'C_BPartner_ID',
             'Y', 10, 'N', 'N',
             'D'
            );

INSERT INTO ad_process_para
            (ad_process_para_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             NAME,
             description,
             HELP,
             ad_process_id, seqno, ad_reference_id, ad_val_rule_id,
             columnname, iscentrallymaintained, fieldlength, ismandatory,
             isrange, defaultvalue, entitytype
            )
     VALUES (50013, 0, 0, 'Y',
             TO_DATE ('02/28/2007 03:02:49', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('02/28/2007 03:12:20', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'User/Contact',
             'User within the system - Internal or Business Partner Contact',
             'The User identifies a unique user in the system. This could be an internal user or a business partner contact',
             50012, 80, 19, 123,
             'AD_User_ID', 'Y', 10, 'N',
             'N', '-1', 'D'
            );

INSERT INTO ad_process_para
            (ad_process_para_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             NAME, description,
             HELP,
             ad_process_id, seqno, ad_reference_id, columnname,
             iscentrallymaintained, fieldlength, ismandatory, isrange,
             defaultvalue, entitytype
            )
     VALUES (50014, 0, 0, 'Y',
             TO_DATE ('02/28/2007 03:03:31', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('02/28/2007 03:12:28', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'Role', 'Responsibility Role',
             'The Role determines security and access a user who has this Role will have in the System.',
             50012, 90, 19, 'AD_Role_ID',
             'Y', 10, 'N', 'N',
             '-1', 'D'
            );

INSERT INTO ad_process_para
            (ad_process_para_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             NAME, description,
             HELP,
             ad_process_id, seqno, ad_reference_id, ad_reference_value_id,
             columnname, iscentrallymaintained, fieldlength, ismandatory,
             isrange, defaultvalue, entitytype
            )
     VALUES (50015, 0, 0, 'Y',
             TO_DATE ('02/28/2007 03:03:54', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('02/28/2007 03:12:35', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'Sales Representative', 'Sales Representative or Company Agent',
             'The Sales Representative indicates the Sales Rep for this Region.  Any Sales Rep must be a valid internal user.',
             50012, 100, 18, 286,
             'SalesRep_ID', 'Y', 10, 'N',
             'N', '@AD_User_ID@', 'D'
            );

INSERT INTO ad_process_para
            (ad_process_para_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             NAME, description,
             HELP,
             ad_process_id, seqno, ad_reference_id, columnname,
             iscentrallymaintained, fieldlength, ismandatory, isrange,
             entitytype
            )
     VALUES (50016, 0, 0, 'Y',
             TO_DATE ('02/28/2007 03:06:47', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('02/28/2007 03:12:44', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'Request Type', 'Type of request (e.g. Inquiry, Complaint, ..)',
             'Request Types are used for processing and categorizing requests. Options are Account Inquiry, Warranty Issue, etc.',
             50012, 110, 19, 'R_RequestType_ID',
             'Y', 10, 'N', 'N',
             'D'
            );

INSERT INTO ad_process_para
            (ad_process_para_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             NAME, description, ad_process_id,
             seqno, ad_reference_id, ad_reference_value_id, columnname,
             iscentrallymaintained, fieldlength, ismandatory, isrange,
             entitytype
            )
     VALUES (50017, 0, 0, 'Y',
             TO_DATE ('02/28/2007 03:09:52', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('02/28/2007 03:12:58', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'User Importance', 'Priority of the issue for the User', 50012,
             120, 17, 154, 'p_DefaultPriority',
             'Y', 1, 'N', 'N',
             'D'
            );

INSERT INTO ad_process_para
            (ad_process_para_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             NAME, description, ad_process_id, seqno, ad_reference_id,
             ad_reference_value_id, columnname, iscentrallymaintained,
             fieldlength, ismandatory, isrange, entitytype
            )
     VALUES (50018, 0, 0, 'Y',
             TO_DATE ('02/28/2007 03:10:19', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('02/28/2007 03:13:05', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'Confidentiality', 'Type of Confidentiality', 50012, 130, 17,
             340, 'p_DefaultConfidentiality', 'Y',
             1, 'N', 'N', 'D'
            );

INSERT INTO ad_menu
            (ad_menu_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated,
             NAME, updatedby, issummary, issotrx, isreadonly, action,
             ad_process_id, entitytype
            )
     VALUES (50009, 0, 0, 'Y',
             TO_DATE ('02/28/2007 03:14:03', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('02/28/2007 03:14:03', 'MM/DD/YYYY HH24:MI:SS'),
             'Request EMail Processor', 100, 'N', 'N', 'N', 'P',
             50012, 'D'
            );

INSERT INTO ad_treenodemm
            (ad_tree_id, node_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             parent_id, seqno
            )
     VALUES (10, 50009, 0, 0, 'Y',
             TO_DATE ('02/28/2007 03:14:02', 'MM/DD/YYYY HH24:MI:SS'), 0,
             TO_DATE ('02/28/2007 03:14:10', 'MM/DD/YYYY HH24:MI:SS'), 0,
             456, 5
            );

COMMIT ;
