1. adempiere-model.xml
- DB independent adempiere schema

2. adempiere-data.jar
- DB independent adempiere seed data

Changes from existing schema
* Use SQL standard varchar instead of Oracle proprietary nvarchar datatype.
* Use Integer datatype for _ID field instead of Number(10)

Issue
* Global Temporary Table is different between Oracle and PostgreSQL
* Integer datatype size is different between Oracle and Postgresql
- 4 byte in PostgreSQL, Number(38) in Oracle
* Fyracle script need to be tested by someone with access to the developer kit.
* Integer datatype for primary key is too small in Java(sqlj) and PostgreSQL
- 32 bit signed, probably should use bigint in PostgreSQL and long in Java (sqlj)
