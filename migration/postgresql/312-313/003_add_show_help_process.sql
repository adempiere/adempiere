INSERT INTO ad_element
            (ad_element_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             columnname, entitytype, NAME, printname
            )
     VALUES (50038, 0, 0, 'Y',
             TO_DATE ('12/19/2006 03:57:24', 'MM/DD/YYYY HH24:MI:SS'), 0,
             TO_DATE ('12/19/2006 03:57:24', 'MM/DD/YYYY HH24:MI:SS'), 0,
             'ShowHelp', 'D', 'Show Help', 'Show Help'
            );

INSERT INTO ad_reference
            (ad_reference_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             NAME, validationtype, entitytype
            )
     VALUES (50007, 0, 0, 'Y',
             TO_DATE ('12/19/2006 04:00:42', 'MM/DD/YYYY HH24:MI:SS'), 0,
             TO_DATE ('12/19/2006 04:00:42', 'MM/DD/YYYY HH24:MI:SS'), 0,
             'ShowHelp List', 'L', 'D'
            );

INSERT INTO ad_ref_list
            (ad_ref_list_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             VALUE, NAME, ad_reference_id, entitytype
            )
     VALUES (50037, 0, 0, 'N',
             TO_DATE ('12/19/2006 04:01:20', 'MM/DD/YYYY HH24:MI:SS'), 0,
             TO_DATE ('12/19/2006 04:01:42', 'MM/DD/YYYY HH24:MI:SS'), 0,
             'A', 'Ask user (for future use)', 50007, 'D'
            );

INSERT INTO ad_ref_list
            (ad_ref_list_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             VALUE, NAME, ad_reference_id, entitytype
            )
     VALUES (50038, 0, 0, 'Y',
             TO_DATE ('12/19/2006 04:02:01', 'MM/DD/YYYY HH24:MI:SS'), 0,
             TO_DATE ('12/19/2006 04:02:01', 'MM/DD/YYYY HH24:MI:SS'), 0,
             'N', 'Don''t show help', 50007, 'D'
            );

INSERT INTO ad_ref_list
            (ad_ref_list_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             VALUE, NAME, ad_reference_id, entitytype
            )
     VALUES (50039, 0, 0, 'Y',
             TO_DATE ('12/19/2006 04:02:25', 'MM/DD/YYYY HH24:MI:SS'), 0,
             TO_DATE ('12/19/2006 04:02:25', 'MM/DD/YYYY HH24:MI:SS'), 0,
             'Y', 'Show Help', 50007, 'D'
            );

INSERT INTO ad_column
            (ad_column_id, ad_client_id, ad_org_id, isactive,
             created,
             updated, createdby, updatedby,
             NAME, description, VERSION,
             entitytype, columnname, ad_table_id, ad_reference_id,
             ad_reference_value_id, fieldlength, defaultvalue, iskey,
             isparent, ismandatory, isupdateable, isidentifier, seqno,
             istranslated, isencrypted, isselectioncolumn, ad_element_id,
             issyncdatabase, isalwaysupdateable
            )
     VALUES (50181, 0, 0, 'Y',
             TO_DATE ('12/19/2006 04:03:24', 'MM/DD/YYYY HH24:MI:SS'),
             TO_DATE ('12/19/2006 04:04:52', 'MM/DD/YYYY HH24:MI:SS'), 0, 0,
             'Show Help', 'To show or hide help for reports / processes', 0,
             'D', 'ShowHelp', 284, 17,
             50007, 1, 'Y', 'N',
             'N', 'N', 'Y', 'N', 0,
             'N', 'N', 'N', 50038,
             'N', 'N'
            );

INSERT INTO ad_field
            (ad_field_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             NAME, description,
             iscentrallymaintained, ad_tab_id, ad_column_id, isdisplayed,
             displaylength, isreadonly, seqno, issameline, isheading,
             isfieldonly, isencrypted, entitytype
            )
     VALUES (50155, 0, 0, 'Y',
             TO_DATE ('12/19/2006 04:05:56', 'MM/DD/YYYY HH24:MI:SS'), 0,
             TO_DATE ('12/19/2006 04:08:45', 'MM/DD/YYYY HH24:MI:SS'), 0,
             'Show Help', 'To show or hide help for reports / processes',
             'Y', 245, 50181, 'Y',
             1, 'N', 185, 'N', 'N',
             'N', 'N', 'D'
            );

COMMIT ;

ALTER TABLE ad_process ADD showhelp CHAR(1) DEFAULT 'Y';

-- run the sequence check after this  