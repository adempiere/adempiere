insert into ad_element
        (ad_element_id,ad_client_id,ad_org_id,isactive,created,createdby,updated,updatedby,
        columnname,entitytype,name,printname,description,help,
        po_name,po_printname,po_description,po_help
        )
    values 
        (50063,0,0,'Y',to_date('28-02-2007','DD-MM-RRRR'),0,to_date('28-02-2007','DD-MM-RRRR'),0,
        'p_ErrorFolder','D','Error Folder','Error Folder',null,null,
        null,null,null,null
        );

insert into ad_element
        (ad_element_id,ad_client_id,ad_org_id,isactive,created,createdby,updated,updatedby,
        columnname,entitytype,name,printname,description,help,
        po_name,po_printname,po_description,po_help
        )
    values
        (50062,0,0,'Y',to_date('28-02-2007','DD-MM-RRRR'),0,to_date('28-02-2007','DD-MM-RRRR'),0,
        'p_RequestFolder','D','Request Folder','Request Folder',null,null,
        null,null,null,null
        );

insert into ad_element
        (ad_element_id,ad_client_id,ad_org_id,isactive,created,createdby,updated,updatedby,
        columnname,entitytype,name,printname,description,help,
        po_name,po_printname,po_description,po_help
        )
    values
        (50061,0,0,'Y',to_date('28-02-2007','DD-MM-RRRR'),0,to_date('28-02-2007','DD-MM-RRRR'),0,
        'p_DefaultPriority','D','User Importance','User Importance','Priority of the issue for the User',null,null,
        null,null,null
        );

insert into ad_element
        (ad_element_id,ad_client_id,ad_org_id,isactive,created,createdby,updated,updatedby,
        columnname,entitytype,name,printname,description,help,
        po_name,po_printname,po_description,po_help
        )
    values
        (50060,0,0,'Y',to_date('28-02-2007','DD-MM-RRRR'),0,to_date('28-02-2007','DD-MM-RRRR'),0,
        'p_IMAPHost','D','IMAP Host','IMAP Host',null,null,
        null,null,null,null
        );

insert into ad_element
        (ad_element_id,ad_client_id,ad_org_id,isactive,created,createdby,updated,updatedby,
        columnname,entitytype,name,printname,description,help,
        po_name,po_printname,po_description,po_help
        )
    values
        (50059,0,0,'Y',to_date('28-02-2007','DD-MM-RRRR'),0,to_date('28-02-2007','DD-MM-RRRR'),0,
        'p_IMAPPwd','D','IMAP Password','IMAP Password',null,null,
        null,null,null,null
        );

insert into ad_element
        (ad_element_id,ad_client_id,ad_org_id,isactive,created,createdby,updated,updatedby,
        columnname,entitytype,name,printname,description,help,
        po_name,po_printname,po_description,po_help
        )
    values
        (50058,0,0,'Y',to_date('28-02-2007','DD-MM-RRRR'),0,to_date('28-02-2007','DD-MM-RRRR'),0,
        'p_IMAPUser','D','IMAP User','IMAP User',null,null,
        null,null,null,null
        );

insert into ad_element
        (ad_element_id,ad_client_id,ad_org_id,isactive,created,createdby,updated,updatedby,
        columnname,entitytype,name,printname,description,help,
        po_name,po_printname,po_description,po_help
        )
    values (
        50057,0,0,'Y',to_date('28-02-2007','DD-MM-RRRR'),0,to_date('28-02-2007','DD-MM-RRRR'),0,
        'p_DefaultConfidentiality','D','Confidentiality','Confidentiality','Type of Confidentiality',null,null,
        null,null,null
        );

insert into ad_element
        (ad_element_id,ad_client_id,ad_org_id,isactive,created,createdby,updated,updatedby,
        columnname,entitytype,name,printname,description,help,
        po_name,po_printname,po_description,po_help
        )
    values
        (50056,0,0,'Y',to_date('28-02-2007','DD-MM-RRRR'),0,to_date('28-02-2007','DD-MM-RRRR'),0,
        'p_InboxFolder','D','Inbox Folder','Inbox Folder',null,null,
        null,null,null,null
        );

commit;

UPDATE ad_sequence
   SET currentnextsys = (SELECT MAX (ad_element_id) + 1
                           FROM ad_element
                          WHERE ad_element_id < 1000000)
 WHERE NAME = 'AD_Element';

UPDATE ad_sequence
   SET currentnextsys = (SELECT MAX (ad_process_id) + 1
                           FROM ad_process
                          WHERE ad_process_id < 1000000)
 WHERE NAME = 'AD_Process';

UPDATE ad_sequence
   SET currentnextsys = (SELECT MAX (ad_process_para_id) + 1
                           FROM ad_process_para
                          WHERE ad_process_para_id < 1000000)
 WHERE NAME = 'AD_Process_Para';

UPDATE ad_sequence
   SET currentnextsys = (SELECT MAX (ad_menu_id) + 1
                           FROM ad_menu
                          WHERE ad_menu_id < 1000000)
 WHERE NAME = 'AD_Menu';

commit;
