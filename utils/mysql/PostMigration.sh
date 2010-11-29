#!/bin/sh

echo	MySQL Post Migration Scripts

if [ $# -le 2 ] 
  then
    echo "Usage:	$0 <systemAccount> <AdempiereID> <AdempierePWD> <MySQLPwd>"
    echo "Example:	$0 root adempiere adempiere mysqlPwd"
    exit 1
fi
if [ "$ADEMPIERE_HOME" = "" -o  "$ADEMPIERE_DB_NAME" = "" -o "$ADEMPIERE_DB_SERVER" = "" -o "$ADEMPIERE_DB_PORT" = "" ]
  then
    echo "Please make sure that the environment variables are set correctly:"
    echo "	ADEMPIERE_HOME	e.g. /Adempiere"
    echo "	ADEMPIERE_DB_NAME	e.g. adempiere or xe"
    echo "  ADEMPIERE_DB_SERVER e.g. dbserver.adempiere.org"
    echo "  ADEMPIERE_DB_PORT e.g. 5432 or 1521"
    exit 1
fi

echo -------------------------------------
echo Add missing translations
echo -------------------------------------
 MYSQL_ROOT="mysql -h $ADEMPIERE_DB_SERVER -P $ADEMPIERE_DB_PORT -u root --password=$3 -D $ADEMPIERE_DB_NAME"
#MYSQL_ROOT="mysql -h $ADEMPIERE_DB_SERVER -P $ADEMPIERE_DB_PORT -u root mysql"

$MYSQL_ROOT --skip-column-names -Be "source $ADEMPIERE_HOME/utils/$ADEMPIERE_DB_PATH/01_add_missing_translations.sql;"
MYSQL_ROOT=
