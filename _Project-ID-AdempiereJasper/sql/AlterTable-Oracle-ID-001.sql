--------------------------------------------------------------------------------
---   ALTER TABLE ... ADD ... File for Database : ID-002-252e-Oracle DB (Build 396)
---
---   Date of creation: 2005-12-20 15:54:30
--------------------------------------------------------------------------------

---------------------------------------------------------------
--- EVERY SQL STATEMENT must be separated from other by ';' ---
---------------------------------------------------------------


--- Table: AD_Process ----------------------------------------------------------
ALTER TABLE AD_Process 
ADD JasperReport NVARCHAR2(255);

ALTER TABLE AD_Process 
MODIFY JasperReport NVARCHAR2(255);