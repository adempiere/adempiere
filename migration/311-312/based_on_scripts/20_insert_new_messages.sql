INSERT INTO ad_message
            (ad_message_id, ad_client_id, ad_org_id, isactive, created,
             createdby, updated, updatedby, VALUE, msgtext, msgtype,
             entitytype
            )
     VALUES (50000, 0, 0, 'Y', SYSDATE,
             0, SYSDATE, 0, 'CloseAllWindows', 'Close All Windows', 'I',
             'D'
            );

INSERT INTO ad_message
            (ad_message_id, ad_client_id, ad_org_id, isactive, created,
             createdby, updated, updatedby, VALUE, msgtext, msgtype,
             entitytype
            )
     VALUES (50001, 0, 0, 'Y', SYSDATE,
             0, SYSDATE, 0, 'CloseOtherWindows', 'Close Other Windows', 'I',
             'D'
            );

INSERT INTO ad_message
            (ad_message_id, ad_client_id, ad_org_id, isactive, created,
             createdby, updated, updatedby, VALUE,
             msgtext, msgtype, entitytype
            )
     VALUES (50002, 0, 0, 'Y', SYSDATE,
             0, SYSDATE, 0, 'ValidateConnectionOnStartup',
             'Validate Connection on Startup', 'I', 'D'
            );

INSERT INTO ad_message
            (ad_message_id, ad_client_id, ad_org_id, isactive, created,
             createdby, updated, updatedby, VALUE,
             msgtext, msgtype, entitytype
            )
     VALUES (50003, 0, 0, 'Y', SYSDATE,
             0, SYSDATE, 0, 'SingleInstancePerWindow',
             'Single Instance per Window', 'I', 'D'
            );

INSERT INTO ad_message
            (ad_message_id, ad_client_id, ad_org_id, isactive, created,
             createdby, updated, updatedby, VALUE, msgtext,
             msgtype, entitytype
            )
     VALUES (50004, 0, 0, 'Y', SYSDATE,
             0, SYSDATE, 0, 'OpenWindowMaximized', 'Open Window Maximized',
             'I', 'D'
            );
            
INSERT INTO ad_message
            (ad_message_id, ad_client_id, ad_org_id, isactive, created,
             createdby, updated, updatedby, VALUE, msgtext,
             msgtype, entitytype
            )
     VALUES (50005, 0, 0, 'Y', SYSDATE,
             0, SYSDATE, 0, 'DeleteSelection', 'Delete Selected Items',
             'I', 'D'
            );         

INSERT INTO ad_message
            (ad_message_id, ad_client_id, ad_org_id, isactive, created,
             createdby, updated, updatedby, VALUE, msgtext,
             msgtype, entitytype
            )
     VALUES (50006, 0, 0, 'Y', SYSDATE,
             0, SYSDATE, 0, 'SaveParentFirst', 'Save Parent Tab First',
             'I', 'D'
            );         

UPDATE ad_sequence
   SET currentnextsys = 50007
 WHERE NAME = 'AD_Message';

COMMIT ;
