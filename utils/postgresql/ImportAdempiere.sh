# $Id: ImportAdempiere.sh,v 1.10 2005/12/20 07:12:17 jjanke Exp $
echo	Adempiere Database Import		$Revision: 1.10 $

echo	Importing Adempiere DB from $ADEMPIERE_HOME/data/Adempiere.dmp 

if [ $# -le 2 ] 
  then
    echo "Usage:		$0 <systemAccount> <AdempiereID> <AdempierePWD>"
    echo "Example:	$0 system/manager adempiere adempiere"
    exit 1
fi
if [ "$ADEMPIERE_HOME" = "" -o  "$ADEMPIERE_DB_NAME" = "" ]
  then
    echo "Please make sure that the environment variables are set correctly:"
    echo "	ADEMPIERE_HOME	e.g. /Adempiere"
    echo "	ADEMPIERE_DB_NAME	e.g. adempiere.adempiere.org"
    exit 1
fi


echo -------------------------------------
echo Recreate user and database
echo -------------------------------------
dropdb -U postgres $ADEMPIERE_DB_NAME

dropuser -U postgres $ADEMPIERE_DB_USER

createuser -U postgres -a -d $ADEMPIERE_DB_USER -P

createdb $ADEMPIERE_DB_NAME -E UNICODE -O $ADEMPIERE_DB_USER -U $ADEMPIERE_DB_USER

echo -------------------------------------
echo Import Adempiere_pg.dmp
echo -------------------------------------
psql -d $ADEMPIERE_DB_NAME -U $ADEMPIERE_DB_USER -f $ADEMPIERE_HOME/data/Adempiere_pg.dmp

echo -------------------------------------
echo Create SQLJ 
echo -------------------------------------
# $ADEMPIERE_HOME/utils/$ADEMPIERE_DB_PATH/create.sh $ADEMPIERE_DB_USER/$ADEMPIERE_DB_PASSWORD
