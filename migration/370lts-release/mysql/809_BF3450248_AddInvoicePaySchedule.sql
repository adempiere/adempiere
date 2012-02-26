-- Column: c_invoicepayschedule_id
-- BF3450248 - Partially paid invoices do not appear in payment selection window
ALTER TABLE adempiere.c_payselectionline ADD COLUMN c_invoicepayschedule_id numeric(10,0);
