ALTER TABLE ad_process ADD jasperreport NVARCHAR2(255);

DELETE FROM ad_field
      WHERE ad_column_id =
                  (SELECT ad_column_id
                     FROM ad_column
                    WHERE ad_element_id = (SELECT ad_element_id
                                             FROM ad_element
                                            WHERE columnname = 'JasperReport'));

DELETE FROM ad_changelog
      WHERE ad_column_id =
                  (SELECT ad_column_id
                     FROM ad_column
                    WHERE ad_element_id = (SELECT ad_element_id
                                             FROM ad_element
                                            WHERE columnname = 'JasperReport'));

DELETE FROM ad_column
      WHERE ad_element_id = (SELECT ad_element_id
                               FROM ad_element
                              WHERE columnname = 'JasperReport');

DELETE FROM ad_element
      WHERE columnname = 'JasperReport';

INSERT INTO ad_element
            (ad_element_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             columnname, entitytype, NAME, printname
            )
     VALUES (50039, 0, 0, 'Y',
             TO_DATE ('01/24/2007 00:55:01', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 00:57:04', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'JasperReport', 'D', 'Jasper Report', 'Jasper Report'
            );

UPDATE ad_sequence
   SET currentnextsys = (SELECT MAX (ad_element_id) + 1
                           FROM ad_element
                          WHERE ad_element_id < 1000000)
 WHERE NAME = 'AD_Element';

INSERT INTO ad_column
            (ad_column_id, ad_client_id, ad_org_id, isactive,
             created,
             updated, createdby,
             updatedby, NAME,
             description, VERSION, entitytype,
             columnname, ad_table_id, ad_reference_id, fieldlength, iskey,
             isparent, ismandatory, isupdateable, isidentifier, istranslated,
             isencrypted, isselectioncolumn, ad_element_id, issyncdatabase,
             isalwaysupdateable
            )
     VALUES (50182, 0, 0, 'Y',
             TO_DATE ('01/24/2007 00:55:01', 'MM/DD/YYYY HH24:MI:SS'),
             TO_DATE ('01/24/2007 00:58:01', 'MM/DD/YYYY HH24:MI:SS'), 100,
             100, 'Jasper Report',
             'In this column you store the JasperReport filename', 0, 'D',
             'JasperReport', 284, 14, 255, 'N',
             'N', 'N', 'Y', 'N', 'N',
             'N', 'N', 50039, 'N',
             'N'
            );

UPDATE ad_sequence
   SET currentnextsys = (SELECT MAX (ad_column_id) + 1
                           FROM ad_column
                          WHERE ad_column_id < 1000000)
 WHERE NAME = 'AD_Column';


INSERT INTO ad_field
            (ad_field_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             NAME,
             description, iscentrallymaintained, ad_tab_id,
             ad_column_id, isdisplayed, displaylength, isreadonly, seqno,
             issameline, isheading, isfieldonly, isencrypted, entitytype
            )
     VALUES (50156, 0, 0, 'Y',
             TO_DATE ('01/24/2007 01:00:09', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('01/24/2007 01:01:01', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'Jasper Report',
             'In this column you store the JasperReport filename', 'Y', 245,
             50182, 'Y', 60, 'N', 210,
             'N', 'N', 'N', 'N', 'D'
            );

UPDATE ad_sequence
   SET currentnextsys = (SELECT MAX (ad_field_id) + 1
                           FROM ad_field
                          WHERE ad_field_id < 1000000)
 WHERE NAME = 'AD_Field';

COMMIT ;
