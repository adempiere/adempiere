XML File
* model/*.xml
- Adempiere database schema
* data/*.xml
- Adempiere seed data

Datatype difference between Oracle and PostgreSQL
* ID Field map to Integer in postgreSQL and Number(10) in Oracle.
* Integer field map to Integer in postgreSQL, Number(10) in Oracle
  ( Oracle's Integer datatype map to number(38) which is not standard)
* PostgreSQL have no native support for CLOB and BLOB, BYTEA is use instead.
* Timestamp field map to Timestamp in PostgreSQL, DATE in Oracle.
* String field map to varchar in PostgreSQL, nvarchar2 in Oracle

Issue
* Fyracle script need to be tested by someone with access to the developer kit.
* XML model and data file to be separated by table to ease maintenance.

Note
* Integer instead of BigInt will be use as datatype for ID field to maintain compatibility
with existing schema.
* The script for Oracle create plsql functions instead of sqlj functions to support Oracle XE. 
* The script is not compatible with the original ddlutils library.
