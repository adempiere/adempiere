INSERT INTO ad_element
            (ad_element_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             columnname, entitytype, NAME,
             printname
            )
     VALUES (50041, 0, 0, 'Y',
             TO_DATE ('02/26/2007 12:30:00', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('02/26/2007 12:30:00', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'StoreAttachmentsOnFileSystem', 'D', 'Store Attachments On File System',
             'Store Attachments On File System'
            );


INSERT INTO ad_column
            (ad_column_id, ad_client_id, ad_org_id, isactive,
             created,
             updated, createdby,
             updatedby, NAME, description, VERSION,
             entitytype, columnname, ad_table_id, ad_reference_id,
             fieldlength, iskey, isparent, ismandatory, isupdateable,
             isidentifier, seqno, istranslated, isencrypted,
             isselectioncolumn, ad_element_id, callout, issyncdatabase,
             isalwaysupdateable
            )
     VALUES (50184, 0, 0, 'Y',
             TO_DATE ('02/26/2007 12:30:00', 'MM/DD/YYYY HH24:MI:SS'),
             TO_DATE ('02/26/2007 12:30:00', 'MM/DD/YYYY HH24:MI:SS'), 100,
             100, 'Store Attachments On File System', 'Store Attachments On File System', 1,
             'D', 'StoreAttachmentsOnFileSystem', 112, 20,
             1, 'N', 'N', 'Y', 'Y',
             'N', 0, 'N', 'N',
             'N', 50041, 'org.compiere.model.CalloutClient.storeAttachmentOnFileSystem', 'N',
             'N'
            );

INSERT INTO ad_field
            (ad_field_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             NAME, description, iscentrallymaintained, seqno, ad_tab_id,
             ad_column_id, isdisplayed, displaylength, isreadonly,
             issameline, isheading, isfieldonly, isencrypted, entitytype
            )
     VALUES (50158, 0, 0, 'Y',
             TO_DATE ('02/26/2007 12:30:00', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('02/26/2007 12:30:00', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'Store Attachments On File System', 'Store Attachments On File System', 'Y', 220, 145,
             50184, 'Y', 1, 'N',
             'N', 'N', 'N', 'N', 'D'
            );
            
            
            
INSERT INTO ad_element
            (ad_element_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             columnname, entitytype, NAME,
             printname
            )
     VALUES (50042, 0, 0, 'Y',
             TO_DATE ('02/26/2007 12:30:00', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('02/26/2007 12:30:00', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'WindowsAttachmentPath', 'D', 'Windows Attachment Path',
             'Windows Attachment Path'
            );


INSERT INTO ad_column
            (ad_column_id, ad_client_id, ad_org_id, isactive,
             created,
             updated, createdby,
             updatedby, NAME, description, 
             help, VERSION,
             entitytype, columnname, ad_table_id, ad_reference_id,
             fieldlength, iskey, isparent, ismandatory, isupdateable,
             isidentifier, seqno, istranslated, isencrypted,
             isselectioncolumn, ad_element_id,  issyncdatabase,
             isalwaysupdateable
            )
     VALUES (50185, 0, 0, 'Y',
             TO_DATE ('02/26/2007 12:30:00', 'MM/DD/YYYY HH24:MI:SS'),
             TO_DATE ('02/26/2007 12:30:00', 'MM/DD/YYYY HH24:MI:SS'), 100,
             100, 'Windows Attachment Path', 'Windows Attachment Path - If you change this value make sure to copy the attachments to the new path!', 
             'Path of the Adempiere attachments in the file system. If you change this value make sure to copy the attachments to the new path!', 1,
             'D', 'WindowsAttachmentPath', 112, 10,
             255, 'N', 'N', 'N', 'Y',
             'N', 0, 'N', 'N',
             'N', 50042, 'N',
             'N'
            );

INSERT INTO ad_field
            (ad_field_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             NAME, description, 
             help,
             iscentrallymaintained, seqno, ad_tab_id,
             ad_column_id, isdisplayed, displaylength, isreadonly,
             issameline, isheading, isfieldonly, isencrypted, entitytype, displaylogic
            )
     VALUES (50159, 0, 0, 'Y',
             TO_DATE ('02/26/2007 12:30:00', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('02/26/2007 12:30:00', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'Windows Attachment Path', 'Windows Attachment Path',
             'If you change this value make sure to copy the attachments to the new path!',
             'Y', 230, 145,
             50185, 'Y', 1, 'N',
             'N', 'N', 'N', 'N', 'D','@StoreAttachmentsOnFileSystem@=''Y'''
            );
            
            
INSERT INTO ad_element
            (ad_element_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             columnname, entitytype, NAME,
             printname
            )
     VALUES (50043, 0, 0, 'Y',
             TO_DATE ('02/26/2007 12:30:00', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('02/26/2007 12:30:00', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'UnixAttachmentPath', 'D', 'Unix Attachment Path',
             'Unix Attachment Path'
            );


INSERT INTO ad_column
            (ad_column_id, ad_client_id, ad_org_id, isactive,
             created,
             updated, createdby,
             updatedby, NAME, description, 
             help, VERSION,
             entitytype, columnname, ad_table_id, ad_reference_id,
             fieldlength, iskey, isparent, ismandatory, isupdateable,
             isidentifier, seqno, istranslated, isencrypted,
             isselectioncolumn, ad_element_id,  issyncdatabase,
             isalwaysupdateable
            )
     VALUES (50186, 0, 0, 'Y',
             TO_DATE ('02/26/2007 12:30:00', 'MM/DD/YYYY HH24:MI:SS'),
             TO_DATE ('02/26/2007 12:30:00', 'MM/DD/YYYY HH24:MI:SS'), 100,
             100, 'Unix Attachment Path', 'Unix Attachment Path - If you change this value make sure to copy the attachments to the new path!', 
             'Path of the Adempiere attachments in the file system. If you change this value make sure to copy the attachments to the new path!', 1,
             'D', 'UnixAttachmentPath', 112, 10,
             255, 'N', 'N', 'N', 'Y',
             'N', 0, 'N', 'N',
             'N', 50043, 'N',
             'N'
            );

INSERT INTO ad_field
            (ad_field_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             NAME, description, 
             help,
             iscentrallymaintained, seqno, ad_tab_id,
             ad_column_id, isdisplayed, displaylength, isreadonly,
             issameline, isheading, isfieldonly, isencrypted, entitytype, displaylogic
            )
     VALUES (50160, 0, 0, 'Y',
             TO_DATE ('02/26/2007 12:30:00', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('02/26/2007 12:30:00', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'Unix Attachment Path', 'Unix Attachment Path',
             'If you change this value make sure to copy the attachments to the new path!',
             'Y', 240 ,145,
             50186, 'Y', 1, 'N',
             'Y', 'N', 'N', 'N', 'D','@StoreAttachmentsOnFileSystem@=''Y'''
            );            
            
INSERT INTO ad_message
	(ad_message_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             value, msgtext, msgtype
            )
     VALUES (50010, 0, 0, 'Y',
             TO_DATE ('02/26/2007 12:30:00', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('02/26/2007 12:30:00', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'StoreAttachmentWarning',
             'If you change the attachment storage method, the old attachments are no longer available to your client.','I'
            );   
            
INSERT INTO ad_message
	(ad_message_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             value, msgtext, msgtype
            )
     VALUES (50011, 0, 0, 'Y',
             TO_DATE ('02/26/2007 12:30:00', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('02/26/2007 12:30:00', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'AttachmentPathWarning','Make sure to copy the attachments to the new path!','I'
            );     
            
INSERT INTO ad_message_trl
            (ad_message_id, ad_language, ad_client_id, ad_org_id, isactive,
             created, createdby, updated, updatedby, msgtext, msgtip,
             istranslated)
   SELECT m.ad_message_id, lang.ad_language, m.ad_client_id, m.ad_org_id, 'Y',
          m.created, m.createdby, m.updated, m.updatedby, m.msgtext, m.msgtip,
          'N'
     FROM ad_message m, ad_language lang
    WHERE m.ad_message_id in (50010, 50011)
      AND lang.issystemlanguage = 'Y'
      AND lang.isbaselanguage = 'N'
      AND NOT EXISTS (
             SELECT *
               FROM ad_message_trl m2
              WHERE m2.ad_message_id = m.ad_message_id
                AND m2.ad_language = lang.ad_language);

COMMIT ;            

UPDATE ad_sequence
   SET currentnextsys = (SELECT MAX (ad_element_id) + 1
                           FROM ad_element
                          WHERE ad_element_id < 1000000)
 WHERE NAME = 'AD_Element';

UPDATE ad_sequence
   SET currentnextsys = (SELECT MAX (ad_column_id) + 1
                           FROM ad_column
                          WHERE ad_column_id < 1000000)
 WHERE NAME = 'AD_Column';

UPDATE ad_sequence
   SET currentnextsys = (SELECT MAX (ad_field_id) + 1
                           FROM ad_field
                          WHERE ad_field_id < 1000000)
 WHERE NAME = 'AD_Field';
 
 UPDATE ad_sequence
    SET currentnextsys = (SELECT MAX (ad_message_id) + 1
                            FROM ad_message
                           WHERE ad_message_id < 1000000)
 WHERE NAME = 'AD_Message';


ALTER TABLE ad_client ADD StoreAttachmentsOnFilesystem CHAR(1) DEFAULT 'N' NOT NULL;
ALTER TABLE ad_client ADD WindowsAttachmentPath VARCHAR(255);
ALTER TABLE ad_client ADD UnixAttachmentPath VARCHAR(255);

COMMIT ;
