-- Nov 29, 2009 10:18:48 PM CET
-- BF[1698281] - Wrong name and description for DiscontinuedBy field
UPDATE AD_Column SET ColumnName='DiscontinuedAt',Updated=TO_DATE('2009-11-29 22:18:48','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=2704
;

-- Nov 29, 2009 10:18:52 PM CET
-- BF[1698281] - Wrong name and description for DiscontinuedBy field
ALTER TABLE M_Product ADD DiscontinuedAt DATE DEFAULT NULL 
;

-- Nov 29, 2009 10:23:53 PM CET
-- BF[1698281] - Wrong name and description for DiscontinuedBy field
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,54086,0,'DiscontinuedAt',TO_DATE('2009-11-29 22:23:52','YYYY-MM-DD HH24:MI:SS'),100,'Discontinued At indicates Date when product was discontinued','D','Y','Discontinued At','Discontinued At',TO_DATE('2009-11-29 22:23:52','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 29, 2009 10:23:53 PM CET
-- BF[1698281] - Wrong name and description for DiscontinuedBy field
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=54086 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Nov 29, 2009 10:24:02 PM CET
-- BF[1698281] - Wrong name and description for DiscontinuedBy field
UPDATE AD_Column SET AD_Element_ID=54086, ColumnName='DiscontinuedAt', Description='Discontinued At indicates Date when product was discontinued', Help=NULL, Name='Discontinued At',Updated=TO_DATE('2009-11-29 22:24:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=2704
;

-- Nov 29, 2009 10:24:02 PM CET
-- BF[1698281] - Wrong name and description for DiscontinuedBy field
UPDATE AD_Column_Trl SET IsTranslated='N' WHERE AD_Column_ID=2704
;

-- Nov 29, 2009 10:24:02 PM CET
-- BF[1698281] - Wrong name and description for DiscontinuedBy field
UPDATE AD_Field SET Name='Discontinued At', Description='Discontinued At indicates Date when product was discontinued', Help=NULL WHERE AD_Column_ID=2704 AND IsCentrallyMaintained='Y'
;

-- Nov 29, 2009 10:24:13 PM CET
-- BF[1698281] - Wrong name and description for DiscontinuedBy field
ALTER TABLE M_Product MODIFY DiscontinuedAt DATE DEFAULT NULL 
;

-- Nov 29, 2009 10:29:51 PM CET
-- BF[1698281] - Wrong name and description for DiscontinuedBy field
UPDATE AD_Column SET AD_Reference_ID=15, AD_Reference_Value_ID=NULL,Updated=TO_DATE('2009-11-29 22:29:51','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=2712
;

-- Nov 29, 2009 10:30:03 PM CET
-- BF[1698281] - Wrong name and description for DiscontinuedBy field
UPDATE AD_Column SET AD_Element_ID=54086, ColumnName='DiscontinuedAt', Description='Discontinued At indicates Date when product was discontinued', Help=NULL, Name='Discontinued At',Updated=TO_DATE('2009-11-29 22:30:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=2712
;

-- Nov 29, 2009 10:30:03 PM CET
-- BF[1698281] - Wrong name and description for DiscontinuedBy field
UPDATE AD_Column_Trl SET IsTranslated='N' WHERE AD_Column_ID=2712
;

-- Nov 29, 2009 10:30:03 PM CET
-- BF[1698281] - Wrong name and description for DiscontinuedBy field
UPDATE AD_Field SET Name='Discontinued At', Description='Discontinued At indicates Date when product was discontinued', Help=NULL WHERE AD_Column_ID=2712 AND IsCentrallyMaintained='Y'
;

-- Nov 29, 2009 10:30:09 PM CET
-- BF[1698281] - Wrong name and description for DiscontinuedBy field
ALTER TABLE M_Product_PO ADD DiscontinuedAt DATE DEFAULT NULL 
;

-- Nov 29, 2009 10:32:50 PM CET
-- BF[1698281] - Wrong name and description for DiscontinuedBy field
UPDATE AD_Column SET AD_Element_ID=54086, ColumnName='DiscontinuedAt', Description='Discontinued At indicates Date when product was discontinued', Help=NULL, Name='Discontinued At',Updated=TO_DATE('2009-11-29 22:32:50','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=7851
;

-- Nov 29, 2009 10:32:50 PM CET
-- BF[1698281] - Wrong name and description for DiscontinuedBy field
UPDATE AD_Column_Trl SET IsTranslated='N' WHERE AD_Column_ID=7851
;

-- Nov 29, 2009 10:32:50 PM CET
-- BF[1698281] - Wrong name and description for DiscontinuedBy field
UPDATE AD_Field SET Name='Discontinued At', Description='Discontinued At indicates Date when product was discontinued', Help=NULL WHERE AD_Column_ID=7851 AND IsCentrallyMaintained='Y'
;

-- Nov 29, 2009 10:32:57 PM CET
-- BF[1698281] - Wrong name and description for DiscontinuedBy field
ALTER TABLE I_Product ADD DiscontinuedAt DATE DEFAULT NULL 
;

