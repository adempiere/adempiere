# Create Oracle SQLJ
# Author + Copyright 1999-2005 Jorg Janke
# $Id: create.sh,v 1.3 2005/06/28 18:55:38 jjanke Exp $
#
# Parameter: <adempiereDBuser>/<adempiereDBpassword>

# unset CLASSPATH=

echo .
echo Load Oracle SQLJ ...
loadjava -user $1@$ADEMPIERE_DB_SERVER/$ADEMPIERE_DB_NAME -verbose -force -resolve $ADEMPIERE_HOME/lib/sqlj.jar

echo .
echo Create Oracle Functions ...
sqlplus $1@$ADEMPIERE_DB_SERVER/$ADEMPIERE_DB_NAME @$ADEMPIERE_HOME/utils/oracle/createSQLJ.sql
