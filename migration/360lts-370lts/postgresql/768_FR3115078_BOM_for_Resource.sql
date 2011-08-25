-- Nov 19, 2010 7:20:13 PM CST
-- Recurrence Manufaturing & Maintenance Order
UPDATE AD_Ref_List SET IsActive='Y',Updated=TO_TIMESTAMP('2010-11-19 19:20:13','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Ref_List_ID=747
;

-- Nov 19, 2010 11:33:26 PM CST
-- Recurrence Manufaturing & Maintenance Order
UPDATE AD_Column SET ReadOnlyLogic='@ProductType@=E | @ProductType@=O',Updated=TO_TIMESTAMP('2010-11-19 23:33:26','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=4708
;

-- Nov 19, 2010 11:40:28 PM CST
-- Recurrence Manufaturing & Maintenance Order
UPDATE AD_Field SET DisplayLogic='@IsSummary@=''N'' & @ProductType@=I | @ProductType@=S | @ProductType@=R',Updated=TO_TIMESTAMP('2010-11-19 23:40:28','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=3743
;

-- Nov 20, 2010 12:17:33 AM CST
-- Recurrence Manufaturing & Maintenance Order
UPDATE AD_Ref_Table SET WhereClause='((M_Product.IsBOM=''Y'' AND M_Product.IsStocked=''Y'' AND M_Product.IsSummary=''N'') OR (M_Product.IsBOM=''Y'' AND M_Product.ProductType=''R''  AND M_Product.IsStocked=''N'' AND M_Product.IsSummary=''N''))',Updated=TO_TIMESTAMP('2010-11-20 00:17:33','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=211
;

