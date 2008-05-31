SET DEFINE OFF;
SET SQLBLANKLINES ON;
-- Dec 19, 2007 4:07:05 PM EST
-- fix entitytype column for PP_Order_Node, PP_Order_NodeNext & PP_Order_Workflow 
UPDATE AD_Column SET AD_Reference_ID=18, DefaultValue='U', FieldLength=40, ReadOnlyLogic='@EntityType@=D',Updated=TO_DATE('2007-12-19 16:07:05','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=53474
;

UPDATE AD_Field SET Name='Entity Type', Description='Dictionary Entity Type; Determines ownership and synchronization', Help='The Entity Types "Dictionary", "Adempiere" and "Application" might be automatically synchronized and customizations deleted or overwritten.  

FOR customizations, copy THE entity AND SELECT "User"!' WHERE AD_Column_ID=53474 AND IsCentrallyMaintained='Y'
;

UPDATE AD_Column SET AD_Reference_ID=18, DefaultValue='U', FieldLength=40, ReadOnlyLogic='@EntityType@=D',Updated=TO_DATE('2007-12-19 16:07:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=53520
;

UPDATE AD_Field SET Name='Entity Type', Description='Dictionary Entity Type; Determines ownership and synchronization', Help='The Entity Types "Dictionary", "Adempiere" and "Application" might be automatically synchronized and customizations deleted or overwritten.  

FOR customizations, copy THE entity AND SELECT "User"!' WHERE AD_Column_ID=53520 AND IsCentrallyMaintained='Y'
;

UPDATE AD_Column SET AD_Reference_ID=18, DefaultValue='U', FieldLength=40, ReadOnlyLogic='@EntityType@=D',Updated=TO_DATE('2007-12-19 16:08:40','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=53701
;

UPDATE AD_Field SET Name='Entity Type', Description='Dictionary Entity Type; Determines ownership and synchronization', Help='The Entity Types "Dictionary", "Adempiere" and "Application" might be automatically synchronized and customizations deleted or overwritten.  

FOR customizations, copy THE entity AND SELECT "User"!' WHERE AD_Column_ID=53701 AND IsCentrallyMaintained='Y'
;

