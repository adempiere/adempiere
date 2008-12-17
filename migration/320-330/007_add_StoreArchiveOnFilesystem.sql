INSERT INTO ad_element
            (ad_element_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             columnname, entitytype, NAME,
             printname
            )
     VALUES (50071, 0, 0, 'Y',
             TO_DATE ('02/26/2007 12:30:00', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('02/26/2007 12:30:00', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'StoreArchiveOnFileSystem', 'D', 'Store Archive On File System',
             'Store Archive On File System'
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
     VALUES (50214, 0, 0, 'Y',
             TO_DATE ('02/26/2007 12:30:00', 'MM/DD/YYYY HH24:MI:SS'),
             TO_DATE ('02/26/2007 12:30:00', 'MM/DD/YYYY HH24:MI:SS'), 100,
             100, 'Store Archive On File System', 'Store Archive On File System', 1,
             'D', 'StoreArchiveOnFileSystem', 112, 20,
             1, 'N', 'N', 'Y', 'Y',
             'N', 0, 'N', 'N',
             'N', 50071, 'org.compiere.model.CalloutClient.storeArchiveOnFileSystem', 'N',
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
     VALUES (50184, 0, 0, 'Y',
             TO_DATE ('02/26/2007 12:30:00', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('02/26/2007 12:30:00', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'Store Archive On File System', 'Store Archive On File System', 'Y', 250, 145,
             50214, 'Y', 1, 'N',
             'N', 'N', 'N', 'N', 'D'
            );
            
            
            
INSERT INTO ad_element
            (ad_element_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             columnname, entitytype, NAME,
             printname
            )
     VALUES (50072, 0, 0, 'Y',
             TO_DATE ('02/26/2007 12:30:00', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('02/26/2007 12:30:00', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'WindowsArchivePath', 'D', 'Windows Archive Path',
             'Windows Archive Path'
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
     VALUES (50215, 0, 0, 'Y',
             TO_DATE ('02/26/2007 12:30:00', 'MM/DD/YYYY HH24:MI:SS'),
             TO_DATE ('02/26/2007 12:30:00', 'MM/DD/YYYY HH24:MI:SS'), 100,
             100, 'Windows Archive Path', 'Windows Archive Path - If you change this value make sure to copy the archive entries to the new path!', 
             'Path of the adempiere archive entries in the file system. If you change this value make sure to copy the archive entries to the new path!', 1,
             'D', 'WindowsArchivePath', 112, 10,
             255, 'N', 'N', 'N', 'Y',
             'N', 0, 'N', 'N',
             'N', 50072, 'N',
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
     VALUES (50185, 0, 0, 'Y',
             TO_DATE ('02/26/2007 12:30:00', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('02/26/2007 12:30:00', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'Windows Archive Path', 'Windows Archive Path',
             'If you change this value make sure to copy the archive entries to the new path!',
             'Y', 260, 145,
             50215, 'Y', 1, 'N',
             'N', 'N', 'N', 'N', 'D','@StoreArchiveOnFileSystem@=''Y'''
            );
            
            
INSERT INTO ad_element
            (ad_element_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             columnname, entitytype, NAME,
             printname
            )
     VALUES (50073, 0, 0, 'Y',
             TO_DATE ('02/26/2007 12:30:00', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('02/26/2007 12:30:00', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'UnixArchivePath', 'D', 'Unix Archive Path',
             'Unix Archive Path'
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
     VALUES (50216, 0, 0, 'Y',
             TO_DATE ('02/26/2007 12:30:00', 'MM/DD/YYYY HH24:MI:SS'),
             TO_DATE ('02/26/2007 12:30:00', 'MM/DD/YYYY HH24:MI:SS'), 100,
             100, 'Unix Archive Path', 'Unix Archive Path - If you change this value make sure to copy the archive entries to the new path!', 
             'Path of the adempiere archive entries in the file system. If you change this value make sure to copy the archive entries to the new path!', 1,
             'D', 'UnixArchivePath', 112, 10,
             255, 'N', 'N', 'N', 'Y',
             'N', 0, 'N', 'N',
             'N', 50073, 'N',
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
     VALUES (50186, 0, 0, 'Y',
             TO_DATE ('02/26/2007 12:30:00', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('02/26/2007 12:30:00', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'Unix Archive Path', 'Unix Archive Path',
             'If you change this value make sure to copy the archive entries to the new path!',
             'Y', 270 ,145,
             50216, 'Y', 1, 'N',
             'Y', 'N', 'N', 'N', 'D','@StoreArchiveOnFileSystem@=''Y'''
            );            
            
INSERT INTO ad_message
	(ad_message_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             value, msgtext, msgtype
            )
     VALUES (50015, 0, 0, 'Y',
             TO_DATE ('02/26/2007 12:30:00', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('02/26/2007 12:30:00', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'StoreArchiveWarning',
             'If you change the archive storage method, the old archive entries are no longer available to your client.','I'
            );   
            
INSERT INTO ad_message
	(ad_message_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             value, msgtext, msgtype
            )
     VALUES (50016, 0, 0, 'Y',
             TO_DATE ('02/26/2007 12:30:00', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('02/26/2007 12:30:00', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'ArchivePathWarning','Make sure to copy the archive entries to the new path!','I'
            );     
            
INSERT INTO ad_message_trl
            (ad_message_id, ad_language, ad_client_id, ad_org_id, isactive,
             created, createdby, updated, updatedby, msgtext, msgtip,
             istranslated)
   SELECT m.ad_message_id, lang.ad_language, m.ad_client_id, m.ad_org_id, 'Y',
          m.created, m.createdby, m.updated, m.updatedby, m.msgtext, m.msgtip,
          'N'
     FROM ad_message m, ad_language lang
    WHERE m.ad_message_id in (50015, 50016)
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


ALTER TABLE ad_client ADD StoreArchiveOnFilesystem CHAR(1 BYTE) DEFAULT 'N' NOT NULL;
ALTER TABLE ad_client ADD WindowsArchivePath NVARCHAR2(255);
ALTER TABLE ad_client ADD UnixArchivePath NVARCHAR2(255);

COMMIT ;
