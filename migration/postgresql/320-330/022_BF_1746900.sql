-- Update help for "C_AcctSchema_Default_Copy" process
 UPDATE AD_PROCESS 
   SET classname = 'org.compiere.process.AcctSchemaDefaultCopy',
   help = 'Either add missing accounts - or copy and overwrite all default accounts.  If you copy and overwrite the current default values, you may have to repeat previous updates (e.g. set the bank account asset accounts, ...).  If no Accounting Schema is selected all Accounting Schemas will be updated / inserted.'
 WHERE AD_Process_ID=108;

-- Update Accounting Schema parameter for "C_AcctSchema_Default_Copy" process
UPDATE AD_PROCESS_PARA 
   SET ismandatory = 'Y'
 WHERE ad_process_para_id= 669;

-- Update help for "M_Product_Category_Acct_Copy" process
UPDATE AD_PROCESS 
   SET classname = 'org.compiere.process.ProductCategoryAcctCopy', 
   help = 'If you copy and overwrite the current default values, you may have to repeat previous updates (e.g. set the revenue account, ...). If no Accounting Schema is selected all Accounting Schemas will be updated / inserted for products of this category.'
 WHERE AD_Process_ID=140;

-- Update help for "M_Product_Category_Acct_Copy" process (SPANISH translation)
UPDATE AD_PROCESS_TRL
   SET help ='El proceso de copiar cuentas tomará las cuentas definidas para una categoría de producto y las copiará a cualquier producto que que haga referencia a esta categoría. Si una cuenta existe a nivel de producto sera sobreescrita. Si no selecciona un Esquema Contable serán actualizados para todos los que estén definidos.'
 WHERE AD_Process_ID=140
   AND AD_LANGUAGE LIKE 'es_%';

-- Update Accounting Schema parameter for "M_Product_Category_Acct_Copy" process.
UPDATE AD_PROCESS_PARA 
   SET ismandatory = 'Y'
 WHERE AD_Process_Para_ID=668;

 UPDATE AD_PROCESS 
   SET classname = 'org.compiere.process.BPGroupAcctCopy'
 WHERE AD_Process_ID=112;

COMMIT;