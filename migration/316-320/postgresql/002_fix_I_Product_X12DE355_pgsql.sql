-- Fix I_Product.X12DE355 FieldLength:
ALTER TABLE i_product ALTER x12de355 TYPE character varying(4);
UPDATE AD_Column SET FieldLength=4 WHERE AD_Column_ID=7862;
COMMIT;
