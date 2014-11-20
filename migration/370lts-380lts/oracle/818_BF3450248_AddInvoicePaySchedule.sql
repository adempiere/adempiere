-- Column: c_invoicepayschedule_id
-- BF3450248 - Partially paid invoices do not appear in payment selection window
ALTER TABLE c_payselectionline ADD (c_invoicepayschedule_id number(10,0));
