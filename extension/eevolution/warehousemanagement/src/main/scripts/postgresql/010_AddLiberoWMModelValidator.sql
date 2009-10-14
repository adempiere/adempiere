-- Sep 21, 2009 12:18:04 PM ECT
-- Warehouse Management
INSERT INTO AD_ModelValidator (AD_Client_ID,AD_ModelValidator_ID,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,ModelValidationClass,Name,SeqNo,Updated,UpdatedBy) VALUES (0,50003,0,TO_TIMESTAMP('2009-09-21 12:18:03','YYYY-MM-DD HH24:MI:SS'),0,'EE03','Y','org.eevolution.model.LiberoWMValidator','Model Validator to Libero Warehouse Management',20,TO_TIMESTAMP('2009-09-21 12:18:03','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 12, 2009 3:52:27 PM CDT
-- Distribution Management
insert into t_alter_column values('dd_orderline','WM_InOutBoundLine_ID','NUMERIC(10)',null,'NULL')
;


