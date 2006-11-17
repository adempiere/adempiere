# Create DB2 SQLJ
#
# Parameter: <adempiereDBuser>/<adempiereDBpassword>

echo .
echo Load DB2 SQLJ ...
db2 "CALL sqlj.install_jar('$ADEMPIERE_HOME/lib/sqlj.jar', 'adempiere_sqlj')"

echo .
echo Create DB2 Functions ...
db2 -f$ADEMPIERE_HOME/utils/db2/createSQLJ.sql
