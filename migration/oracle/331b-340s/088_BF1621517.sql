-- Fix [ 1621517 ] Duplicate vendor invoice document numbers permitted

/*
WARNING - The create index on this script could fail if you have already wrong data on your DB
If this script fails you need to detect the duplicate data and change the DocumentNo column to a unique value

To review your duplicate data you can execute next query:

SELECT c1.c_invoice_id, c2.c_invoice_id, c1.c_bpartner_id, c1.documentno,
       c1.c_doctypetarget_id
  FROM C_INVOICE c1, C_INVOICE c2
 WHERE c1.c_bpartner_id = c2.c_bpartner_id
   AND c1.documentno = c2.documentno
   AND c1.c_doctypetarget_id = c2.c_doctypetarget_id
   AND c1.c_invoice_id <> c2.c_invoice_id;

*/

CREATE UNIQUE INDEX c_invoice_documentno_target ON C_INVOICE
  (c_bpartner_id, documentno, c_doctypetarget_id);

DROP INDEX c_invoice_bpartner;
