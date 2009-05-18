-- Feb 20, 2009 12:22:31 PM CET
-- Advanced Search
INSERT INTO AD_SearchDefinition (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_SearchDefinition_ID,AD_Table_ID,AD_Window_ID,Created,CreatedBy,DataType,Description,IsActive,IsDefault,Name,PO_Window_ID,SearchType,TransactionCode,Updated,UpdatedBy) VALUES (0,2169,0,50000,259,143,TO_TIMESTAMP('2009-02-20 12:22:31','YYYY-MM-DD HH24:MI:SS'),100,'S','Searches for an order with a fitting documentNo','Y','Y','Order Search',181,'T','O',TO_TIMESTAMP('2009-02-20 12:22:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Feb 20, 2009 12:26:12 PM CET
-- Advanced Search
INSERT INTO AD_SearchDefinition (AD_Client_ID,AD_Org_ID,AD_SearchDefinition_ID,AD_Table_ID,AD_Window_ID,Created,CreatedBy,DataType,Description,IsActive,IsDefault,Name,Query,SearchType,TransactionCode,Updated,UpdatedBy) VALUES (0,0,50001,291,123,TO_TIMESTAMP('2009-02-20 12:26:12','YYYY-MM-DD HH24:MI:SS'),100,'S','Searches for a Business Partner with fitting BPName or ContactName','Y','N','Business Partner Search','SELECT b.C_BPartner_ID FROM C_BPartner b
INNER JOIN AD_User u ON (b.C_BPartner_ID = u.C_BPartner_ID)
WHERE UPPER(b.name) LIKE UPPER(?) OR UPPER(u.name) LIKE UPPER(?)','Q','BP',TO_TIMESTAMP('2009-02-20 12:26:12','YYYY-MM-DD HH24:MI:SS'),100)
;

