INSERT INTO ad_element
            (ad_element_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             columnname, entitytype, NAME,
             printname
            )
     VALUES (50070, 0, 0, 'Y',
             TO_DATE ('04/24/2007 12:30:00', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('04/24/2007 12:30:00', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'M_Product_Category_Parent_ID', 'D', 'Parent Product Category',
             'Parent Product Category'
            );


INSERT INTO ad_column
            (ad_column_id, ad_client_id, ad_org_id, isactive,
             created,
             updated, createdby,
             updatedby, name, description, help, version,
             entitytype, columnname, ad_table_id, ad_reference_id,
             ad_reference_value_id,
             fieldlength, iskey, isparent, ismandatory, isupdateable,
             isidentifier, seqno, istranslated, isencrypted,
             isselectioncolumn, ad_element_id, callout, issyncdatabase,
             isalwaysupdateable
            )
     VALUES (50211, 0, 0, 'Y',
             TO_DATE ('04/24/2007 12:30:00', 'MM/DD/YYYY HH24:MI:SS'),
             TO_DATE ('04/24/2007 12:30:00', 'MM/DD/YYYY HH24:MI:SS'), 100,
             100, 'Parent Product Category', 'Parent Product Category', 'The parent product category is used to build a category tree.', 1,
             'D', 'M_Product_Category_Parent_ID', 209, 18,
             163,
             22, 'N', 'N', 'N', 'Y',
             'N', 0, 'N', 'N',
             'N', 50070, 'org.compiere.model.CalloutProductCategory.testForLoop', 'N',
             'N'
            );


INSERT INTO ad_field
            (ad_field_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             name, description, iscentrallymaintained, seqno, ad_tab_id,
             ad_column_id, isdisplayed, displaylength, isreadonly,
             issameline, isheading, isfieldonly, isencrypted, entitytype
            )
     VALUES (50181, 0, 0, 'Y',
             TO_DATE ('04/24/2007 12:30:00', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('04/24/2007 12:30:00', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'Parent Product Category', 'Parent Product Category', 'Y', 60, 189,
             50211, 'Y', 22, 'N',
             'N', 'N', 'N', 'N', 'D'
            );
            
INSERT INTO ad_message
	(ad_message_id, ad_client_id, ad_org_id, isactive,
             created, createdby,
             updated, updatedby,
             value, msgtext, msgtype
            )
     VALUES (50014, 0, 0, 'Y',
             TO_DATE ('04/24/2007 12:30:00', 'MM/DD/YYYY HH24:MI:SS'), 100,
             TO_DATE ('04/24/2007 12:30:00', 'MM/DD/YYYY HH24:MI:SS'), 100,
             'ProductCategoryLoopDetected',
             'A loop in the product category tree has been detected - the old value will be restored','E'
            ); 
            
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


ALTER TABLE M_Product_Category ADD M_Product_Category_Parent_ID  NUMBER(10,0);
ALTER TABLE M_Product_Category ADD  CONSTRAINT MProductCat_ParentCat FOREIGN KEY (M_Product_Category_Parent_ID)
   	  REFERENCES M_Product_Category (M_Product_Category_ID);
UPDATE AD_Column SET IsSelectionColumn='Y' WHERE AD_Column_ID=2012;

COMMIT ;            