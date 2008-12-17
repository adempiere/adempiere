-- Mar 6, 2008 6:40:40 PM CST
-- Change P_String
UPDATE AD_Column SET FieldLength=255,Updated=TO_DATE('2008-03-06 18:40:40','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=2791
;

-- Mar 6, 2008 6:40:40 PM CST
-- Change P_String
UPDATE AD_Field SET Name='Process String', Description='Process Parameter', Help=NULL WHERE AD_Column_ID=2791 AND IsCentrallyMaintained='Y'
;

-- Mar 6, 2008 6:40:45 PM CST
-- Change P_String
ALTER TABLE AD_PInstance_Para MODIFY P_String NVARCHAR2(255) DEFAULT  NULL 
;

-- Mar 6, 2008 6:41:54 PM CST
-- Change P_String
UPDATE AD_Column SET FieldLength=255,Updated=TO_DATE('2008-03-06 18:41:54','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=2792
;

-- Mar 6, 2008 6:41:54 PM CST
-- Change P_String
UPDATE AD_Field SET Name='Process String To', Description='Process Parameter', Help=NULL WHERE AD_Column_ID=2792 AND IsCentrallyMaintained='Y'
;

-- Mar 6, 2008 6:41:58 PM CST
-- Change P_String
ALTER TABLE AD_PInstance_Para MODIFY P_String_To NVARCHAR2(255) DEFAULT  NULL 
;

