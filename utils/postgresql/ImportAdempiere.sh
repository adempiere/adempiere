# $Id: ImportAdempiere.sh,v 1.10 2005/12/20 07:12:17 jjanke Exp $
echo	Adempiere Database Import		$Revision: 1.10 $

echo	Importing Adempiere DB from $ADEMPIERE_HOME/data/Adempiere_pg.dmp 

if [ $# -le 2 ] 
  then
    echo "Usage:		$0 <systemAccount> <AdempiereID> <AdempierePWD> <PostgresPwd>"
    echo "Example:	$0 postgres adempiere adempiere postgresPwd"
    exit 1
fi
if [ "$ADEMPIERE_HOME" = "" -o  "$ADEMPIERE_DB_NAME" = "" ]
  then
    echo "Please make sure that the environment variables are set correctly:"
    echo "	ADEMPIERE_HOME	e.g. /Adempiere"
    echo "	ADEMPIERE_DB_NAME	e.g. adempiere.adempiere.org"
    exit 1
fi


export PGPASSWORD=$4
echo -------------------------------------
echo Recreate user and database
echo -------------------------------------
dropdb -U postgres $ADEMPIERE_DB_NAME

dropuser -U postgres $2

ADEMPIERE_CREATE_ROLE_SQL="CREATE ROLE $2 SUPERUSER LOGIN PASSWORD '$3'"
psql -U postgres -c "$ADEMPIERE_CREATE_ROLE_SQL"
ADEMPIERE_CREATE_ROLE_SQL=

export PGPASSWORD=$3
createdb $ADEMPIERE_DB_NAME -E UNICODE -O $2 -U $2

echo -------------------------------------
echo Import Adempiere_pg.dmp
echo -------------------------------------
ADEMPIERE_ALTER_ROLE_SQL="ALTER ROLE $2 SET search_path TO adempiere, sqlj, pg_catalog"
psql -d $ADEMPIERE_DB_NAME -U $2 -c "$ADEMPIERE_ALTER_ROLE_SQL"
psql -d $ADEMPIERE_DB_NAME -U $2 -c "drop schema sqlj cascade"
psql -d $ADEMPIERE_DB_NAME -U $2 -f $ADEMPIERE_HOME/data/Adempiere_pg.dmp
ADEMPIERE_ALTER_ROLE_SQL=
export PGPASSWORD=
