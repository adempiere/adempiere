@Rem Create DB2 SQLJ
@Rem
@Rem Parameter: <adempiereDBuser>/<adempiereDBpassword>

@Echo .
@Echo Load DB2 SQLJ ...
@db2cmd db2 "CALL sqlj.install_jar('%ADEMPIERE_HOME%\lib\sqlj.jar', 'adempiere_sqlj')"
 
@Echo .
@Echo Create DB2 Functions ...
@db2cmd db2 -f%ADEMPIERE_HOME%\utils\db2\createSQLJ.sql
