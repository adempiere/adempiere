-- Mar 24, 2010 4:18:52 PM CET
-- Deactivate not used AD_Elements.
UPDATE AD_Element SET IsActive='N',Updated=TO_DATE('2010-03-24 16:18:52','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=279
;

-- Mar 24, 2010 4:19:25 PM CET
-- Deactivate not used AD_Elements.
UPDATE AD_Element SET IsActive='N',Updated=TO_DATE('2010-03-24 16:19:25','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=54079
;

DELETE FROM AD_Element_Trl WHERE AD_Element_ID=54079
;

DELETE FROM AD_Element_Trl WHERE AD_Element_ID=279
;