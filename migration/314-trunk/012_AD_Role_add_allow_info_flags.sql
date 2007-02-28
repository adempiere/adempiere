ALTER TABLE ad_role ADD (
   allow_info_account CHAR(1 BYTE) DEFAULT 'Y'
);

ALTER TABLE ad_role ADD (
   allow_info_asset CHAR(1 BYTE) DEFAULT 'Y'
);

ALTER TABLE ad_role ADD (
   allow_info_bpartner CHAR(1 BYTE) DEFAULT 'Y'
);

ALTER TABLE ad_role ADD (
   allow_info_cashjournal CHAR(1 BYTE) DEFAULT 'Y'
);

ALTER TABLE ad_role ADD (
   allow_info_inout CHAR(1 BYTE) DEFAULT 'Y'
);

ALTER TABLE ad_role ADD (
   allow_info_invoice CHAR(1 BYTE) DEFAULT 'Y'
);

ALTER TABLE ad_role ADD (
   allow_info_order CHAR(1 BYTE) DEFAULT 'Y'
);

ALTER TABLE ad_role ADD (
   allow_info_payment CHAR(1 BYTE) DEFAULT 'Y'
);

ALTER TABLE ad_role ADD (
   allow_info_product CHAR(1 BYTE) DEFAULT 'Y'
);

ALTER TABLE ad_role ADD (
   allow_info_resource CHAR(1 BYTE) DEFAULT 'Y'
);

ALTER TABLE ad_role ADD (
   allow_info_schedule CHAR(1 BYTE) DEFAULT 'Y'
);

INSERT INTO ad_element
            (ad_element_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             columnname, entitytype, NAME,
             printname
            )
     VALUES (50045, 0, 0, 'Y',
             TO_DATE ('02/28/2007 02:23:55', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('02/28/2007 02:24:32', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'Allow_Info_Account', 'D', 'Allow Info Account',
             'Allow Info Account'
            );

INSERT INTO ad_element
            (ad_element_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             columnname, entitytype, NAME,
             printname
            )
     VALUES (50047, 0, 0, 'Y',
             TO_DATE ('02/28/2007 02:23:55', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('02/28/2007 02:29:51', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'Allow_Info_BPartner', 'D', 'Allow Info BPartner',
             'Allow Info BPartner'
            );

INSERT INTO ad_element
            (ad_element_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             columnname, entitytype, NAME, printname
            )
     VALUES (50046, 0, 0, 'Y',
             TO_DATE ('02/28/2007 02:23:55', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('02/28/2007 02:27:15', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'Allow_Info_Asset', 'D', 'Allow Info Asset', 'Allow Info Asset'
            );

INSERT INTO ad_element
            (ad_element_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             columnname, entitytype, NAME, printname
            )
     VALUES (50051, 0, 0, 'Y',
             TO_DATE ('02/28/2007 02:23:56', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('02/28/2007 02:31:43', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'Allow_Info_Order', 'D', 'Allow Info Order', 'Allow Info Order'
            );

INSERT INTO ad_element
            (ad_element_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             columnname, entitytype, NAME,
             printname
            )
     VALUES (50052, 0, 0, 'Y',
             TO_DATE ('02/28/2007 02:23:56', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('02/28/2007 02:32:00', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'Allow_Info_Payment', 'D', 'Allow Info Payment',
             'Allow Info Payment'
            );

INSERT INTO ad_element
            (ad_element_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             columnname, entitytype, NAME,
             printname
            )
     VALUES (50053, 0, 0, 'Y',
             TO_DATE ('02/28/2007 02:23:56', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('02/28/2007 02:32:15', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'Allow_Info_Product', 'D', 'Allow Info Product',
             'Allow Info Product'
            );

INSERT INTO ad_element
            (ad_element_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             columnname, entitytype, NAME,
             printname
            )
     VALUES (50054, 0, 0, 'Y',
             TO_DATE ('02/28/2007 02:23:56', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('02/28/2007 02:32:29', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'Allow_Info_Resource', 'D', 'Allow Info Resource',
             'Allow Info Resource'
            );

INSERT INTO ad_element
            (ad_element_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             columnname, entitytype, NAME,
             printname
            )
     VALUES (50055, 0, 0, 'Y',
             TO_DATE ('02/28/2007 02:23:56', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('02/28/2007 02:32:46', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'Allow_Info_Schedule', 'D', 'Allow Info Schedule',
             'Allow Info Schedule'
            );

INSERT INTO ad_element
            (ad_element_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             columnname, entitytype, NAME, printname
            )
     VALUES (50049, 0, 0, 'Y',
             TO_DATE ('02/28/2007 02:23:56', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('02/28/2007 02:31:13', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'Allow_Info_InOut', 'D', 'Allow Info InOut', 'Allow Info InOut'
            );

INSERT INTO ad_element
            (ad_element_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             columnname, entitytype, NAME,
             printname
            )
     VALUES (50048, 0, 0, 'Y',
             TO_DATE ('02/28/2007 02:23:56', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('02/28/2007 02:30:56', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'Allow_Info_CashJournal', 'D', 'Allow Info CashJournal',
             'Allow Info CashJournal'
            );

INSERT INTO ad_element
            (ad_element_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             columnname, entitytype, NAME,
             printname
            )
     VALUES (50050, 0, 0, 'Y',
             TO_DATE ('02/28/2007 02:23:56', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('02/28/2007 02:31:30', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'Allow_Info_Invoice', 'D', 'Allow Info Invoice',
             'Allow Info Invoice'
            );

INSERT INTO ad_column
            (ad_column_id, ad_client_id, ad_org_id, isactive,
             created,
             updated, createdby,
             updatedby, NAME, VERSION, entitytype, columnname, ad_table_id,
             ad_reference_id, fieldlength, iskey, isparent, ismandatory,
             isupdateable, isidentifier, istranslated, isencrypted,
             isselectioncolumn, ad_element_id, issyncdatabase,
             isalwaysupdateable
            )
     VALUES (50198, 0, 0, 'Y',
             TO_DATE ('02/28/2007 02:23:55', 'MM/DD/YYYY HH24:MI:SS'),
             TO_DATE ('02/28/2007 02:26:49', 'MM/DD/YYYY HH24:MI:SS'), 100,
             100, 'Allow Info Account', 0, 'D', 'Allow_Info_Account', 156,
             20, 1, 'N', 'N', 'N',
             'Y', 'N', 'N', 'N',
             'N', 50045, 'N',
             'N'
            );

INSERT INTO ad_column
            (ad_column_id, ad_client_id, ad_org_id, isactive,
             created,
             updated, createdby,
             updatedby, NAME, VERSION, entitytype, columnname, ad_table_id,
             ad_reference_id, fieldlength, iskey, isparent, ismandatory,
             isupdateable, isidentifier, istranslated, isencrypted,
             isselectioncolumn, ad_element_id, issyncdatabase,
             isalwaysupdateable
            )
     VALUES (50200, 0, 0, 'Y',
             TO_DATE ('02/28/2007 02:23:55', 'MM/DD/YYYY HH24:MI:SS'),
             TO_DATE ('02/28/2007 02:30:02', 'MM/DD/YYYY HH24:MI:SS'), 100,
             100, 'Allow Info BPartner', 0, 'D', 'Allow_Info_BPartner', 156,
             20, 1, 'N', 'N', 'N',
             'Y', 'N', 'N', 'N',
             'N', 50047, 'N',
             'N'
            );

INSERT INTO ad_column
            (ad_column_id, ad_client_id, ad_org_id, isactive,
             created,
             updated, createdby,
             updatedby, NAME, VERSION, entitytype, columnname, ad_table_id,
             ad_reference_id, fieldlength, iskey, isparent, ismandatory,
             isupdateable, isidentifier, istranslated, isencrypted,
             isselectioncolumn, ad_element_id, issyncdatabase,
             isalwaysupdateable
            )
     VALUES (50199, 0, 0, 'Y',
             TO_DATE ('02/28/2007 02:23:55', 'MM/DD/YYYY HH24:MI:SS'),
             TO_DATE ('02/28/2007 02:28:11', 'MM/DD/YYYY HH24:MI:SS'), 100,
             100, 'Allow Info Asset', 0, 'D', 'Allow_Info_Asset', 156,
             20, 1, 'N', 'N', 'N',
             'Y', 'N', 'N', 'N',
             'N', 50046, 'N',
             'N'
            );

INSERT INTO ad_column
            (ad_column_id, ad_client_id, ad_org_id, isactive,
             created,
             updated, createdby,
             updatedby, NAME, VERSION, entitytype, columnname, ad_table_id,
             ad_reference_id, fieldlength, iskey, isparent, ismandatory,
             isupdateable, isidentifier, istranslated, isencrypted,
             isselectioncolumn, ad_element_id, issyncdatabase,
             isalwaysupdateable
            )
     VALUES (50204, 0, 0, 'Y',
             TO_DATE ('02/28/2007 02:23:56', 'MM/DD/YYYY HH24:MI:SS'),
             TO_DATE ('02/28/2007 02:33:20', 'MM/DD/YYYY HH24:MI:SS'), 100,
             100, 'Allow Info Order', 0, 'D', 'Allow_Info_Order', 156,
             20, 1, 'N', 'N', 'N',
             'Y', 'N', 'N', 'N',
             'N', 50051, 'N',
             'N'
            );

INSERT INTO ad_column
            (ad_column_id, ad_client_id, ad_org_id, isactive,
             created,
             updated, createdby,
             updatedby, NAME, VERSION, entitytype, columnname, ad_table_id,
             ad_reference_id, fieldlength, iskey, isparent, ismandatory,
             isupdateable, isidentifier, istranslated, isencrypted,
             isselectioncolumn, ad_element_id, issyncdatabase,
             isalwaysupdateable
            )
     VALUES (50205, 0, 0, 'Y',
             TO_DATE ('02/28/2007 02:23:56', 'MM/DD/YYYY HH24:MI:SS'),
             TO_DATE ('02/28/2007 02:33:21', 'MM/DD/YYYY HH24:MI:SS'), 100,
             100, 'Allow Info Payment', 0, 'D', 'Allow_Info_Payment', 156,
             20, 1, 'N', 'N', 'N',
             'Y', 'N', 'N', 'N',
             'N', 50052, 'N',
             'N'
            );

INSERT INTO ad_column
            (ad_column_id, ad_client_id, ad_org_id, isactive,
             created,
             updated, createdby,
             updatedby, NAME, VERSION, entitytype, columnname, ad_table_id,
             ad_reference_id, fieldlength, iskey, isparent, ismandatory,
             isupdateable, isidentifier, istranslated, isencrypted,
             isselectioncolumn, ad_element_id, issyncdatabase,
             isalwaysupdateable
            )
     VALUES (50206, 0, 0, 'Y',
             TO_DATE ('02/28/2007 02:23:56', 'MM/DD/YYYY HH24:MI:SS'),
             TO_DATE ('02/28/2007 02:33:22', 'MM/DD/YYYY HH24:MI:SS'), 100,
             100, 'Allow Info Product', 0, 'D', 'Allow_Info_Product', 156,
             20, 1, 'N', 'N', 'N',
             'Y', 'N', 'N', 'N',
             'N', 50053, 'N',
             'N'
            );

INSERT INTO ad_column
            (ad_column_id, ad_client_id, ad_org_id, isactive,
             created,
             updated, createdby,
             updatedby, NAME, VERSION, entitytype, columnname, ad_table_id,
             ad_reference_id, fieldlength, iskey, isparent, ismandatory,
             isupdateable, isidentifier, istranslated, isencrypted,
             isselectioncolumn, ad_element_id, issyncdatabase,
             isalwaysupdateable
            )
     VALUES (50207, 0, 0, 'Y',
             TO_DATE ('02/28/2007 02:23:56', 'MM/DD/YYYY HH24:MI:SS'),
             TO_DATE ('02/28/2007 02:33:26', 'MM/DD/YYYY HH24:MI:SS'), 100,
             100, 'Allow Info Resource', 0, 'D', 'Allow_Info_Resource', 156,
             20, 1, 'N', 'N', 'N',
             'Y', 'N', 'N', 'N',
             'N', 50054, 'N',
             'N'
            );

INSERT INTO ad_column
            (ad_column_id, ad_client_id, ad_org_id, isactive,
             created,
             updated, createdby,
             updatedby, NAME, VERSION, entitytype, columnname, ad_table_id,
             ad_reference_id, fieldlength, iskey, isparent, ismandatory,
             isupdateable, isidentifier, istranslated, isencrypted,
             isselectioncolumn, ad_element_id, issyncdatabase,
             isalwaysupdateable
            )
     VALUES (50208, 0, 0, 'Y',
             TO_DATE ('02/28/2007 02:23:56', 'MM/DD/YYYY HH24:MI:SS'),
             TO_DATE ('02/28/2007 02:33:27', 'MM/DD/YYYY HH24:MI:SS'), 100,
             100, 'Allow Info Schedule', 0, 'D', 'Allow_Info_Schedule', 156,
             20, 1, 'N', 'N', 'N',
             'Y', 'N', 'N', 'N',
             'N', 50055, 'N',
             'N'
            );

INSERT INTO ad_column
            (ad_column_id, ad_client_id, ad_org_id, isactive,
             created,
             updated, createdby,
             updatedby, NAME, VERSION, entitytype, columnname, ad_table_id,
             ad_reference_id, fieldlength, iskey, isparent, ismandatory,
             isupdateable, isidentifier, istranslated, isencrypted,
             isselectioncolumn, ad_element_id, issyncdatabase,
             isalwaysupdateable
            )
     VALUES (50202, 0, 0, 'Y',
             TO_DATE ('02/28/2007 02:23:56', 'MM/DD/YYYY HH24:MI:SS'),
             TO_DATE ('02/28/2007 02:33:16', 'MM/DD/YYYY HH24:MI:SS'), 100,
             100, 'Allow Info InOut', 0, 'D', 'Allow_Info_InOut', 156,
             20, 1, 'N', 'N', 'N',
             'Y', 'N', 'N', 'N',
             'N', 50049, 'N',
             'N'
            );

INSERT INTO ad_column
            (ad_column_id, ad_client_id, ad_org_id, isactive,
             created,
             updated, createdby,
             updatedby, NAME, VERSION, entitytype, columnname,
             ad_table_id, ad_reference_id, fieldlength, iskey, isparent,
             ismandatory, isupdateable, isidentifier, istranslated,
             isencrypted, isselectioncolumn, ad_element_id, issyncdatabase,
             isalwaysupdateable
            )
     VALUES (50201, 0, 0, 'Y',
             TO_DATE ('02/28/2007 02:23:56', 'MM/DD/YYYY HH24:MI:SS'),
             TO_DATE ('02/28/2007 02:33:14', 'MM/DD/YYYY HH24:MI:SS'), 100,
             100, 'Allow Info CashJournal', 0, 'D', 'Allow_Info_CashJournal',
             156, 20, 1, 'N', 'N',
             'N', 'Y', 'N', 'N',
             'N', 'N', 50048, 'N',
             'N'
            );

INSERT INTO ad_column
            (ad_column_id, ad_client_id, ad_org_id, isactive,
             created,
             updated, createdby,
             updatedby, NAME, VERSION, entitytype, columnname, ad_table_id,
             ad_reference_id, fieldlength, iskey, isparent, ismandatory,
             isupdateable, isidentifier, istranslated, isencrypted,
             isselectioncolumn, ad_element_id, issyncdatabase,
             isalwaysupdateable
            )
     VALUES (50203, 0, 0, 'Y',
             TO_DATE ('02/28/2007 02:23:56', 'MM/DD/YYYY HH24:MI:SS'),
             TO_DATE ('02/28/2007 02:33:19', 'MM/DD/YYYY HH24:MI:SS'), 100,
             100, 'Allow Info Invoice', 0, 'D', 'Allow_Info_Invoice', 156,
             20, 1, 'N', 'N', 'N',
             'Y', 'N', 'N', 'N',
             'N', 50050, 'N',
             'N'
            );

INSERT INTO ad_fieldgroup
            (ad_fieldgroup_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             NAME, entitytype
            )
     VALUES (50000, 0, 0, 'Y',
             TO_DATE ('02/28/2007 02:35:11', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('02/28/2007 02:35:11', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'Allow Info In Role', 'D'
            );

INSERT INTO ad_field
            (ad_field_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             NAME, iscentrallymaintained, ad_tab_id, ad_column_id,
             ad_fieldgroup_id, isdisplayed, displaylength, isreadonly, seqno,
             issameline, isheading, isfieldonly, isencrypted, entitytype
            )
     VALUES (50168, 0, 0, 'Y',
             TO_DATE ('02/28/2007 02:33:56', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('02/28/2007 02:37:40', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'Allow Info Account', 'Y', 119, 50198,
             50000, 'Y', 1, 'N', 270,
             'N', 'N', 'N', 'N', 'D'
            );

INSERT INTO ad_field
            (ad_field_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             NAME, iscentrallymaintained, ad_tab_id, ad_column_id,
             ad_fieldgroup_id, isdisplayed, displaylength, isreadonly, seqno,
             issameline, isheading, isfieldonly, isencrypted, entitytype
            )
     VALUES (50170, 0, 0, 'Y',
             TO_DATE ('02/28/2007 02:33:56', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('02/28/2007 02:35:31', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'Allow Info BPartner', 'Y', 119, 50200,
             50000, 'Y', 1, 'N', 290,
             'N', 'N', 'N', 'N', 'D'
            );

INSERT INTO ad_field
            (ad_field_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             NAME, iscentrallymaintained, ad_tab_id, ad_column_id,
             ad_fieldgroup_id, isdisplayed, displaylength, isreadonly, seqno,
             issameline, isheading, isfieldonly, isencrypted, entitytype
            )
     VALUES (50169, 0, 0, 'Y',
             TO_DATE ('02/28/2007 02:33:56', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('02/28/2007 02:37:40', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'Allow Info Asset', 'Y', 119, 50199,
             50000, 'Y', 1, 'N', 280,
             'Y', 'N', 'N', 'N', 'D'
            );

INSERT INTO ad_field
            (ad_field_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             NAME, iscentrallymaintained, ad_tab_id, ad_column_id,
             ad_fieldgroup_id, isdisplayed, displaylength, isreadonly, seqno,
             issameline, isheading, isfieldonly, isencrypted, entitytype
            )
     VALUES (50174, 0, 0, 'Y',
             TO_DATE ('02/28/2007 02:33:57', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('02/28/2007 02:37:43', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'Allow Info Order', 'Y', 119, 50204,
             50000, 'Y', 1, 'N', 330,
             'N', 'N', 'N', 'N', 'D'
            );

INSERT INTO ad_field
            (ad_field_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             NAME, iscentrallymaintained, ad_tab_id, ad_column_id,
             ad_fieldgroup_id, isdisplayed, displaylength, isreadonly, seqno,
             issameline, isheading, isfieldonly, isencrypted, entitytype
            )
     VALUES (50175, 0, 0, 'Y',
             TO_DATE ('02/28/2007 02:33:57', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('02/28/2007 02:35:44', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'Allow Info Payment', 'Y', 119, 50205,
             50000, 'Y', 1, 'N', 340,
             'Y', 'N', 'N', 'N', 'D'
            );

INSERT INTO ad_field
            (ad_field_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             NAME, iscentrallymaintained, ad_tab_id, ad_column_id,
             ad_fieldgroup_id, isdisplayed, displaylength, isreadonly, seqno,
             issameline, isheading, isfieldonly, isencrypted, entitytype
            )
     VALUES (50176, 0, 0, 'Y',
             TO_DATE ('02/28/2007 02:33:57', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('02/28/2007 02:36:06', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'Allow Info Product', 'Y', 119, 50206,
             50000, 'Y', 1, 'N', 350,
             'N', 'N', 'N', 'N', 'D'
            );

INSERT INTO ad_field
            (ad_field_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             NAME, iscentrallymaintained, ad_tab_id, ad_column_id,
             ad_fieldgroup_id, isdisplayed, displaylength, isreadonly, seqno,
             issameline, isheading, isfieldonly, isencrypted, entitytype
            )
     VALUES (50177, 0, 0, 'Y',
             TO_DATE ('02/28/2007 02:33:57', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('02/28/2007 02:37:44', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'Allow Info Resource', 'Y', 119, 50207,
             50000, 'Y', 1, 'N', 360,
             'Y', 'N', 'N', 'N', 'D'
            );

INSERT INTO ad_field
            (ad_field_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             NAME, iscentrallymaintained, ad_tab_id, ad_column_id,
             ad_fieldgroup_id, isdisplayed, displaylength, isreadonly, seqno,
             issameline, isheading, isfieldonly, isencrypted, entitytype
            )
     VALUES (50178, 0, 0, 'Y',
             TO_DATE ('02/28/2007 02:33:57', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('02/28/2007 02:36:09', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'Allow Info Schedule', 'Y', 119, 50208,
             50000, 'Y', 1, 'N', 370,
             'N', 'N', 'N', 'N', 'D'
            );

INSERT INTO ad_field
            (ad_field_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             NAME, iscentrallymaintained, ad_tab_id, ad_column_id,
             ad_fieldgroup_id, isdisplayed, displaylength, isreadonly, seqno,
             issameline, isheading, isfieldonly, isencrypted, entitytype
            )
     VALUES (50172, 0, 0, 'Y',
             TO_DATE ('02/28/2007 02:33:57', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('02/28/2007 02:37:42', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'Allow Info InOut', 'Y', 119, 50202,
             50000, 'Y', 1, 'N', 310,
             'N', 'N', 'N', 'N', 'D'
            );

INSERT INTO ad_field
            (ad_field_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             NAME, iscentrallymaintained, ad_tab_id, ad_column_id,
             ad_fieldgroup_id, isdisplayed, displaylength, isreadonly, seqno,
             issameline, isheading, isfieldonly, isencrypted, entitytype
            )
     VALUES (50171, 0, 0, 'Y',
             TO_DATE ('02/28/2007 02:33:57', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('02/28/2007 02:37:41', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'Allow Info CashJournal', 'Y', 119, 50201,
             50000, 'Y', 1, 'N', 300,
             'Y', 'N', 'N', 'N', 'D'
            );

INSERT INTO ad_field
            (ad_field_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             NAME, iscentrallymaintained, ad_tab_id, ad_column_id,
             ad_fieldgroup_id, isdisplayed, displaylength, isreadonly, seqno,
             issameline, isheading, isfieldonly, isencrypted, entitytype
            )
     VALUES (50173, 0, 0, 'Y',
             TO_DATE ('02/28/2007 02:33:57', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('02/28/2007 02:35:34', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'Allow Info Invoice', 'Y', 119, 50203,
             50000, 'Y', 1, 'N', 320,
             'Y', 'N', 'N', 'N', 'D'
            );

COMMIT ;