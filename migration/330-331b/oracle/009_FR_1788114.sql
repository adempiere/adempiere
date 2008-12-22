UPDATE AD_VAL_RULE
   SET code =
          '(C_Order.DocStatus=''WP'' OR (C_Order.DocStatus=''CO'' AND EXISTS (SELECT * FROM C_DocType dt WHERE C_Order.C_DocType_ID=dt.C_DocType_ID AND (dt.DocSubTypeSO=''SO'' OR dt.DocBaseType=''POO'')) AND EXISTS (SELECT * FROM C_OrderLine ol WHERE C_Order.C_Order_ID=ol.C_Order_ID AND ol.QtyInvoiced<>ol.QtyOrdered)))'
 WHERE ad_val_rule_id = 218;
 
COMMIT;