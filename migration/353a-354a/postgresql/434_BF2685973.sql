-- Mar 13, 2009 12:05:46 PM EET
-- [ 2685973 ] Missing columns in Product cost report
INSERT INTO AD_Column (Help,Created,CreatedBy,Updated,Version,IsActive,AD_Reference_ID,IsMandatory,IsIdentifier,ColumnName,AD_Column_ID,IsParent,AD_Table_ID,FieldLength,Description,IsKey,IsTranslated,AD_Client_ID,AD_Org_ID,AD_Element_ID,IsSelectionColumn,IsUpdateable,Name,EntityType,UpdatedBy,IsAlwaysUpdateable,IsEncrypted) VALUES ('Current Price Lower Level is used for get the total costs for lower level the a product manufactured.

The Current Price Lower Level always will be calculated.

You can see the Current Cost Price and Current Cost Price Lower Level with Cost  Bill of Material & Formula Detail Report.
 
The sum the Current Cost Price + Current Cost Price Lower Level is the total cost to a product manufactured.
',TO_TIMESTAMP('2009-03-13 12:05:39','YYYY-MM-DD HH24:MI:SS'),0,TO_TIMESTAMP('2009-03-13 12:05:39','YYYY-MM-DD HH24:MI:SS'),0,'Y',22,'Y','N','CurrentCostPriceLL',56913,'N',805,22,'Current Price Lower Level Is the sum of the costs of the components of this product manufactured for this level.','N','N',0,0,53296,'N','N','Current Cost Price Lower Level','D',0,'N','N')
;

-- Mar 13, 2009 12:05:46 PM EET
-- [ 2685973 ] Missing columns in Product cost report
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56913 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 13, 2009 12:05:58 PM EET
-- [ 2685973 ] Missing columns in Product cost report
INSERT INTO AD_Column (Created,CreatedBy,Updated,Version,IsActive,AD_Reference_ID,IsMandatory,IsIdentifier,ColumnName,AD_Column_ID,IsParent,AD_Table_ID,FieldLength,IsKey,IsTranslated,AD_Client_ID,AD_Org_ID,AD_Element_ID,IsSelectionColumn,IsUpdateable,Name,EntityType,UpdatedBy,IsAlwaysUpdateable,IsEncrypted) VALUES (TO_TIMESTAMP('2009-03-13 12:05:49','YYYY-MM-DD HH24:MI:SS'),0,TO_TIMESTAMP('2009-03-13 12:05:49','YYYY-MM-DD HH24:MI:SS'),0,'Y',22,'N','N','FutureCostPriceLL',56914,'N',805,22,'N','N',0,0,53763,'N','N','Future Cost Price Lower Level','D',0,'N','N')
;

-- Mar 13, 2009 12:05:58 PM EET
-- [ 2685973 ] Missing columns in Product cost report
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56914 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 13, 2009 12:06:03 PM EET
-- [ 2685973 ] Missing columns in Product cost report
INSERT INTO AD_Column (Created,CreatedBy,Updated,Version,IsActive,AD_Reference_ID,IsMandatory,IsIdentifier,ColumnName,AD_Column_ID,IsParent,AD_Table_ID,FieldLength,Description,IsKey,IsTranslated,AD_Client_ID,AD_Org_ID,AD_Element_ID,IsSelectionColumn,IsUpdateable,Name,EntityType,UpdatedBy,IsAlwaysUpdateable,IsEncrypted) VALUES (TO_TIMESTAMP('2009-03-13 12:05:59','YYYY-MM-DD HH24:MI:SS'),0,TO_TIMESTAMP('2009-03-13 12:05:59','YYYY-MM-DD HH24:MI:SS'),0,'Y',20,'N','N','IsCostFrozen',56915,'N',805,1,'Indicated that the Standard Cost is frozen','N','N',0,0,53764,'N','N','Cost Frozen','D',0,'N','N')
;

-- Mar 13, 2009 12:06:03 PM EET
-- [ 2685973 ] Missing columns in Product cost report
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56915 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

DROP VIEW RV_COST;

CREATE OR REPLACE VIEW RV_COST
(AD_CLIENT_ID, AD_ORG_ID, ISACTIVE, CREATED, CREATEDBY, 
 UPDATED, UPDATEDBY, M_PRODUCT_ID, VALUE, NAME, 
 UPC, ISBOM, PRODUCTTYPE, M_PRODUCT_CATEGORY_ID, M_COSTTYPE_ID, 
 M_COSTELEMENT_ID, COSTELEMENTTYPE, COSTINGMETHOD, ISCALCULATED, C_ACCTSCHEMA_ID, 
 C_CURRENCY_ID, CURRENTCOSTPRICE, FUTURECOSTPRICE, DESCRIPTION,
 CURRENTCOSTPRICELL, FUTURECOSTPRICELL, ISCOSTFROZEN)
AS 
SELECT 	c.AD_Client_ID, c.AD_Org_ID, c.IsActive, c.Created,c.CreatedBy,c.Updated,c.UpdatedBy,
    p.M_Product_ID, p.Value, p.Name, p.UPC, p.IsBOM, p.ProductType, p.M_Product_Category_ID,
    c.M_CostType_ID, 
    ce.M_CostElement_ID, ce.CostElementType, ce.CostingMethod, ce.IsCalculated, 
    acct.C_AcctSchema_ID, acct.C_Currency_ID,
    c.CurrentCostPrice, c.FutureCostPrice, c.Description,
    c.CurrentCostPriceLL, c.FutureCostPriceLL, c.IsCostFrozen
FROM M_Cost c
  INNER JOIN M_Product p ON (c.M_Product_ID=p.M_Product_ID)
  INNER JOIN M_CostElement ce ON (c.M_CostElement_ID=ce.M_CostElement_ID)
  INNER JOIN C_AcctSchema acct ON (c.C_AcctSchema_ID=acct.C_AcctSchema_ID);



