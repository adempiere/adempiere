-- Nov 23, 2008 1:14:30 AM EET
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE U_POSTerminal SET CardTransferBankAccount_ID=100, Card_BankAccount_ID=100, CashTransferBankAccount_ID=100, CheckTransferBankAccount_ID=100, Check_BankAccount_ID=100,Updated=TO_DATE('2008-11-23 01:14:30','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE U_POSTerminal_ID=50000
;

