-- Mar 16, 2013 12:11:37 PM EDT
-- Adding System Configuration for Info Panel changes
UPDATE M_Product SET Description='Azaleas are flowering shrubs. Azaleas bloom in spring in the Northern hemisphere and in winter in the Southern hemisphere, their flowers often lasting several weeks. Shade tolerant, they prefer living near or under trees.',Updated=TO_TIMESTAMP('2013-03-16 12:11:37','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=101 WHERE M_Product_ID=128
;

-- Mar 16, 2013 12:11:37 PM EDT
-- Adding System Configuration for Info Panel changes
UPDATE M_Product_Trl SET Description='Azaleas are flowering shrubs. Azaleas bloom in spring in the Northern hemisphere and in winter in the Southern hemisphere, their flowers often lasting several weeks. Shade tolerant, they prefer living near or under trees.',DocumentNote=NULL,Name='Azalea Bush',IsTranslated='Y' WHERE M_Product_ID=128
;

-- Mar 16, 2013 12:11:37 PM EDT
-- Adding System Configuration for Info Panel changes
UPDATE A_Asset SET Name=SUBSTR((SELECT bp.Name FROM C_BPartner bp WHERE bp.C_BPartner_ID=A_Asset.C_BPartner_ID) || ' - ' || p.Name,1,60),Description=p.Description FROM M_Product p WHERE p.M_Product_ID=A_Asset.M_Product_ID AND A_Asset.IsActive='Y' AND A_Asset.M_Product_ID=128
;

-- Mar 16, 2013 12:27:24 PM EDT
-- Adding System Configuration for Info Panel changes
INSERT INTO M_Substitute (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,M_Product_ID,Name,Substitute_ID,Updated,UpdatedBy) VALUES (11,0,TO_TIMESTAMP('2013-03-16 12:27:24','YYYY-MM-DD HH24:MI:SS'),101,'Y',129,'Azalea Bush',128,TO_TIMESTAMP('2013-03-16 12:27:24','YYYY-MM-DD HH24:MI:SS'),101)
;

-- Mar 16, 2013 12:27:49 PM EDT
-- Adding System Configuration for Info Panel changes
INSERT INTO M_Substitute (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,M_Product_ID,Name,Substitute_ID,Updated,UpdatedBy) VALUES (11,0,TO_TIMESTAMP('2013-03-16 12:27:49','YYYY-MM-DD HH24:MI:SS'),101,'Y',128,'Holly Bush',129,TO_TIMESTAMP('2013-03-16 12:27:49','YYYY-MM-DD HH24:MI:SS'),101)
;

-- Mar 16, 2013 12:28:55 PM EDT
-- Adding System Configuration for Info Panel changes
--(SELECT s.M_Product_ID, w.Name as warehouse, l.value as locator, 0 as ID, now() as TIMESTAMP, sum(s.QtyOnHand) as AvailQty, null as DeltaQty, null as QtyOrdered, null as QtyReserved, null as sumPASI, 0 as ASI, null as BP_Name, null as DocumentNo, 10 as SeqNo FROM (SELECT M_Product_ID, M_Locator_ID, QtyOnHand, QtyReserved, QtyOrdered, COALESCE(productAttribute(M_AttributeSetInstance_ID)::varchar, '') as PASI, COALESCE(M_AttributeSetInstance_ID,0) as M_AttributeSetInstance_ID FROM M_Storage) s INNER JOIN M_Locator l ON (s.M_Locator_ID=l.M_Locator_ID) INNER JOIN M_Warehouse w ON (l.M_Warehouse_ID=w.M_Warehouse_ID) WHERE s.M_Product_ID=? AND l.M_Warehouse_ID=? AND (s.QtyOnHand<>0) GROUP BY s.M_Product_ID, w.Name, l.value, s.M_Locator_ID, sumPASI, ASI, BP_Name, DocumentNo, SeqNo UNION ALL SELECT ol.M_Product_ID, w.Name as warehouse, null as locator, ol.M_AttributeSetInstance_ID as ID, o.DatePromised as TIMESTAMP, null as AvailQty, CASE WHEN dt.DocBaseType = 'POO' THEN ol.QtyOrdered ELSE -ol.QtyReserved END as DeltaQty, CASE WHEN dt.DocBaseType = 'POO' THEN ol.QtyOrdered ELSE null END as QtyOrdered, CASE WHEN dt.DocBaseType = 'POO' THEN null ELSE ol.QtyReserved END as QtyReserved, productAttribute(ol.M_AttributeSetInstance_ID) as sumPASI, ol.M_AttributeSetInstance_ID as ASI, bp.Name as BP_Name, dt.PrintName || ' ' || o.DocumentNo As DocumentNo, 20 as SeqNo FROM C_Order o INNER JOIN C_OrderLine ol ON (o.C_Order_ID=ol.C_Order_ID) INNER JOIN C_DocType dt ON (o.C_DocType_ID=dt.C_DocType_ID) INNER JOIN M_Warehouse w ON (ol.M_Warehouse_ID=w.M_Warehouse_ID) INNER JOIN C_BPartner bp ON (o.C_BPartner_ID=bp.C_BPartner_ID) WHERE ol.QtyReserved<>0 AND ol.M_Product_ID=? AND ol.M_Warehouse_ID=? ORDER BY M_Product_ID, SeqNo, ID, TIMESTAMP, locator)
--;

-- Mar 16, 2013 12:30:14 PM EDT
-- Adding System Configuration for Info Panel changes
INSERT INTO M_RelatedProduct (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,M_Product_ID,Name,RelatedProduct_ID,RelatedProductType,Updated,UpdatedBy) VALUES (11,0,TO_TIMESTAMP('2013-03-16 12:30:14','YYYY-MM-DD HH24:MI:SS'),101,'Y',128,'Mulch will help it grow',137,'S',TO_TIMESTAMP('2013-03-16 12:30:14','YYYY-MM-DD HH24:MI:SS'),101)
;

-- Mar 16, 2013 12:31:13 PM EDT
-- Adding System Configuration for Info Panel changes
INSERT INTO M_RelatedProduct (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,M_Product_ID,Name,RelatedProduct_ID,RelatedProductType,Updated,UpdatedBy) VALUES (11,0,TO_TIMESTAMP('2013-03-16 12:31:13','YYYY-MM-DD HH24:MI:SS'),101,'Y',128,'How To Plant',146,'S',TO_TIMESTAMP('2013-03-16 12:31:13','YYYY-MM-DD HH24:MI:SS'),101)
;

-- Mar 16, 2013 12:38:31 PM EDT
-- Adding System Configuration for Info Panel changes
DELETE FROM M_RelatedProduct WHERE M_Product_ID=128 AND RelatedProduct_ID=129 AND RelatedProductType='A'
;

-- Mar 16, 2013 1:00:58 PM EDT
-- Adding System Configuration for Info Panel changes
UPDATE M_Substitute SET Name='Consider a Holly Bush',Updated=TO_TIMESTAMP('2013-03-16 13:00:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=101 WHERE M_Product_ID=128 AND Substitute_ID=129
;

-- Mar 16, 2013 1:01:15 PM EDT
-- Adding System Configuration for Info Panel changes
UPDATE M_RelatedProduct SET Name='A book on How To Plant',Updated=TO_TIMESTAMP('2013-03-16 13:01:15','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=101 WHERE M_Product_ID=128 AND RelatedProduct_ID=146 AND RelatedProductType='S'
;
