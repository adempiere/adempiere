XML File
* adempiere-model.xml
- DB independent adempiere schema
* adempiere-data.jar
- DB independent adempiere seed data

Datatype difference between Oracle and PostgreSQL
* ID Field map to Integer in postgreSQL and Number(10) in Oracle.
* Integer field map to Integer in postgreSQL, Number(10) in Oracle
  ( Oracle's Integer datatype map to number(38) which is not standard)
* PostgreSQL have no native support for CLOB and BLOB, BYTEA is use instead.
* Timestamp field map to Timestamp in PostgreSQL, DATE in Oracle.

Issue
* Fyracle script need to be tested by someone with access to the developer kit.
* XML model and data file to be separated by table to ease maintenance.

Note
* SQL standard varchar datatype is use in place of Oracle's non standard nvarchar 
datatype. This changes means you must create your database to use UTF8 as default
character set.
* Integer instead of BigInt will be use as datatype for ID field to maintain compatibility
with existing schema.