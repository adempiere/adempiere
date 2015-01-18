SET DEFINE OFF
SET SQLBLANKLINES ON

-- Mar 16, 2013 12:11:37 PM EDT
-- Adding System Configuration for Info Panel changes
UPDATE M_Product SET Description='Azaleas are flowering shrubs. Azaleas bloom in spring in the Northern hemisphere and in winter in the Southern hemisphere, their flowers often lasting several weeks. Shade tolerant, they prefer living near or under trees.',Updated=TO_DATE('2013-03-16 12:11:37','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=101 WHERE M_Product_ID=128
;

-- Mar 16, 2013 12:11:37 PM EDT
-- Adding System Configuration for Info Panel changes
UPDATE M_Product_Trl SET Description='Azaleas are flowering shrubs. Azaleas bloom in spring in the Northern hemisphere and in winter in the Southern hemisphere, their flowers often lasting several weeks. Shade tolerant, they prefer living near or under trees.',DocumentNote=NULL,Name='Azalea Bush',IsTranslated='Y' WHERE M_Product_ID=128
;

-- Mar 16, 2013 12:11:37 PM EDT
-- Adding System Configuration for Info Panel changes
UPDATE A_Asset a SET (Name, Description)=(SELECT SUBSTR((SELECT bp.Name FROM C_BPartner bp WHERE bp.C_BPartner_ID=a.C_BPartner_ID) || ' - ' || p.Name,1,60), p.Description FROM M_Product p WHERE p.M_Product_ID=a.M_Product_ID) WHERE IsActive='Y'  AND M_Product_ID=128
;

-- Mar 16, 2013 12:27:24 PM EDT
-- Adding System Configuration for Info Panel changes
INSERT INTO M_Substitute (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,M_Product_ID,Name,Substitute_ID,Updated,UpdatedBy) VALUES (11,0,TO_DATE('2013-03-16 12:27:24','YYYY-MM-DD HH24:MI:SS'),101,'Y',129,'Azalea Bush',128,TO_DATE('2013-03-16 12:27:24','YYYY-MM-DD HH24:MI:SS'),101)
;

-- Mar 16, 2013 12:27:49 PM EDT
-- Adding System Configuration for Info Panel changes
INSERT INTO M_Substitute (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,M_Product_ID,Name,Substitute_ID,Updated,UpdatedBy) VALUES (11,0,TO_DATE('2013-03-16 12:27:49','YYYY-MM-DD HH24:MI:SS'),101,'Y',128,'Holly Bush',129,TO_DATE('2013-03-16 12:27:49','YYYY-MM-DD HH24:MI:SS'),101)
;

-- Mar 16, 2013 12:30:14 PM EDT
-- Adding System Configuration for Info Panel changes
INSERT INTO M_RelatedProduct (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,M_Product_ID,Name,RelatedProduct_ID,RelatedProductType,Updated,UpdatedBy) VALUES (11,0,TO_DATE('2013-03-16 12:30:14','YYYY-MM-DD HH24:MI:SS'),101,'Y',128,'Mulch will help it grow',137,'S',TO_DATE('2013-03-16 12:30:14','YYYY-MM-DD HH24:MI:SS'),101)
;

-- Mar 16, 2013 12:31:13 PM EDT
-- Adding System Configuration for Info Panel changes
INSERT INTO M_RelatedProduct (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,M_Product_ID,Name,RelatedProduct_ID,RelatedProductType,Updated,UpdatedBy) VALUES (11,0,TO_DATE('2013-03-16 12:31:13','YYYY-MM-DD HH24:MI:SS'),101,'Y',128,'How To Plant',146,'S',TO_DATE('2013-03-16 12:31:13','YYYY-MM-DD HH24:MI:SS'),101)
;

-- Mar 16, 2013 12:38:31 PM EDT
-- Adding System Configuration for Info Panel changes
DELETE FROM M_RelatedProduct WHERE M_Product_ID=128 AND RelatedProduct_ID=129 AND RelatedProductType='A'
;

-- Mar 16, 2013 1:00:58 PM EDT
-- Adding System Configuration for Info Panel changes
UPDATE M_Substitute SET Name='Consider a Holly Bush',Updated=TO_DATE('2013-03-16 13:00:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=101 WHERE M_Product_ID=128 AND Substitute_ID=129
;

-- Mar 16, 2013 1:01:15 PM EDT
-- Adding System Configuration for Info Panel changes
UPDATE M_RelatedProduct SET Name='A book on How To Plant',Updated=TO_DATE('2013-03-16 13:01:15','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=101 WHERE M_Product_ID=128 AND RelatedProduct_ID=146 AND RelatedProductType='S'
;

