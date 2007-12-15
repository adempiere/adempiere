# $Id: PostMigration.sh
echo	Postgresql Post Migration Scripts

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

export PGPASSWORD=$3
echo -------------------------------------
echo Add missing translations
echo -------------------------------------
psql -d $ADEMPIERE_DB_NAME -U $2 -f 01_add_missing_translations.sql
export PGPASSWORD=
