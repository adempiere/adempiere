#!/bin/sh

echo	ADempiere MySQL Database Export

echo Saving database $1@$ADEMPIERE_DB_NAME to $ADEMPIERE_HOME/data/ExpDat.dmp

if [ $# -eq 0 ] 
  then
    echo "Usage:	$0 <userAccount>"
    echo "Example:	$0 adempiere adempiere"
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
 
mysqldump -h $ADEMPIERE_DB_SERVER -P $ADEMPIERE_DB_PORT -u $1 --password=$2 --single-transaction --routines --no-create-db $ADEMPIERE_DB_NAME > $ADEMPIERE_HOME/data/ExpDat.dmp

cd $ADEMPIERE_HOME/data
jar cvfM ExpDat.jar ExpDat.dmp
