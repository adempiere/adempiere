SET DEFINE OFF
SET SQLBLANKLINES ON
SET SCAN OFF
INSERT INTO AD_Process VALUES (53356, 0, 0, 'Y', TO_DATE('2013-12-05 19:11:38','YYYY-MM-DD HH24:MI:SS'), 0, TO_DATE('2013-12-05 19:11:38','YYYY-MM-DD HH24:MI:SS'), 101, 'RV_T_CombinedAgingRevalue', 'Aging with payments (revalued)', 'Aging Report with payments (revalued)', 'The aging report allows you to report on Open Items (Invoices and Payments) as at the date selected. Select the aging buckets, you want to have in your report. All amounts are converted to the currency of the select accounting schema, using the rate for the selected currency conversion type and as at the statement date. If you do not select a Statement Date, the system date is used. If you do not list the individual invoices, the Due Date is the earliest due date for the business partner and the Due Days are the average due days of all invoices.<br>', '3', 'D', NULL, 'Y', 'N', 53035, 'org.compiere.process.CombinedAgingRevalue', 2, 4, NULL, NULL, NULL, 'N', 'N', 'Y', NULL, NULL, NULL, NULL)
;
INSERT INTO AD_Process_Para VALUES (53863, 0, 0, 'Y', TO_DATE('2013-12-05 19:11:38','YYYY-MM-DD HH24:MI:SS'), 0, TO_DATE('2013-12-05 19:11:38','YYYY-MM-DD HH24:MI:SS'), 0, 'Accounting Schema', NULL, NULL, 53356, 10, 19, NULL, NULL, 'C_AcctSchema_ID', 'Y', 0, 'Y', 'N', NULL, NULL, NULL, NULL, NULL, 181, 'D', NULL, NULL)
;
INSERT INTO AD_Process_Para VALUES (53864, 0, 0, 'Y', TO_DATE('2013-12-05 19:11:38','YYYY-MM-DD HH24:MI:SS'), 0, TO_DATE('2013-12-05 19:11:38','YYYY-MM-DD HH24:MI:SS'), 0, 'Currency Type', 'Currency Conversion Rate Type', NULL, 53356, 20, 19, NULL, NULL, 'C_ConversionType_ID', 'Y', 22, 'N', 'N', '@SQL=SELECT C_ConversionType_ID AS DefaultValue FROM C_ConversionType WHERE Value = ''P''', NULL, NULL, NULL, NULL, 2278, 'D', NULL, NULL)
;
INSERT INTO AD_Process_Para VALUES (53865, 0, 0, 'Y', TO_DATE('2013-12-05 19:11:38','YYYY-MM-DD HH24:MI:SS'), 0, TO_DATE('2013-12-05 19:11:38','YYYY-MM-DD HH24:MI:SS'), 0, 'Statement date', 'Date of the statement', 'The Statement Date field defines the date of the statement.', 53356, 30, 15, NULL, NULL, 'StatementDate', 'Y', 0, 'N', 'N', NULL, NULL, NULL, NULL, NULL, 1434, 'D', NULL, NULL)
;
INSERT INTO AD_Process_Para VALUES (53866, 0, 0, 'Y', TO_DATE('2013-12-05 19:11:38','YYYY-MM-DD HH24:MI:SS'), 0, TO_DATE('2013-12-05 19:11:38','YYYY-MM-DD HH24:MI:SS'), 0, 'Sales Transaction', 'This is a Sales Transaction', 'The Sales Transaction checkbox indicates if this item is a Sales Transaction.', 53356, 40, 20, NULL, NULL, 'IsSOTrx', 'Y', 0, 'N', 'N', 'Y', NULL, NULL, NULL, NULL, 1106, 'D', NULL, NULL)
;
INSERT INTO AD_Process_Para VALUES (53867, 0, 0, 'Y', TO_DATE('2013-12-05 19:11:38','YYYY-MM-DD HH24:MI:SS'), 0, TO_DATE('2013-12-05 19:11:38','YYYY-MM-DD HH24:MI:SS'), 0, 'Business Partner Group', 'Business Partner Group', 'The Business Partner Group provides a method of defining defaults to be used for individual Business Partners.', 53356, 50, 19, NULL, NULL, 'C_BP_Group_ID', 'Y', 0, 'N', 'N', '-1', NULL, NULL, NULL, NULL, 1383, 'D', NULL, NULL)
;
INSERT INTO AD_Process_Para VALUES (53868, 0, 0, 'Y', TO_DATE('2013-12-05 19:11:38','YYYY-MM-DD HH24:MI:SS'), 0, TO_DATE('2013-12-05 19:11:38','YYYY-MM-DD HH24:MI:SS'), 0, 'Organisation', 'Organisational entity within client', 'An organisation is a unit of your client or legal entity - examples are store, department. You can share data between organisations.', 53356, 60, 19, NULL, 130, 'AD_Org_ID', 'Y', 0, 'N', 'N', '-1', NULL, NULL, NULL, NULL, 113, 'D', NULL, NULL)
;
INSERT INTO AD_Process_Para VALUES (53869, 0, 0, 'Y', TO_DATE('2013-12-05 19:11:38','YYYY-MM-DD HH24:MI:SS'), 0, TO_DATE('2013-12-05 19:11:38','YYYY-MM-DD HH24:MI:SS'), 0, 'Business Partner ', 'Identifies a Business Partner', 'A Business Partner is anyone with whom you transact.  This can include Vendor, Customer, Employee or Salesperson', 53356, 70, 30, NULL, NULL, 'C_BPartner_ID', 'Y', 0, 'N', 'N', NULL, NULL, NULL, NULL, NULL, 187, 'D', NULL, NULL)
;
INSERT INTO AD_Process_Para VALUES (53870, 0, 0, 'Y', TO_DATE('2013-12-05 19:11:38','YYYY-MM-DD HH24:MI:SS'), 0, TO_DATE('2013-12-05 19:11:38','YYYY-MM-DD HH24:MI:SS'), 0, 'List Sources', 'List Report Line Sources', 'List the Source Accounts for Summary Accounts selected', 53356, 80, 20, NULL, NULL, 'ListSources', 'Y', 0, 'N', 'N', 'Y', NULL, NULL, NULL, NULL, NULL, 'D', NULL, NULL)
;
INSERT INTO AD_Process_Para VALUES (53871, 0, 0, 'Y', TO_DATE('2013-12-05 19:11:38','YYYY-MM-DD HH24:MI:SS'), 0, TO_DATE('2013-12-05 19:11:38','YYYY-MM-DD HH24:MI:SS'), 0, 'Include Payments', 'Include payments in the aging report', NULL, 53356, 90, 20, NULL, NULL, 'IsIncludePayments', 'Y', 1, 'N', 'N', 'Y', NULL, NULL, NULL, NULL, 55220, 'D', NULL, NULL)
;

