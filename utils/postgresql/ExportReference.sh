echo	Adempiere PostgreSQL Database Export 	$Revision: 1.3 $

# $Id: DBExport.sh,v 1.3 2005/01/22 21:59:15 jjanke Exp $

echo Saving database reference@$ADEMPIERE_DB_NAME to $ADEMPIERE_HOME/data/Adempiere_pg.dmp

if [ "$ADEMPIERE_HOME" = "" -o  "$ADEMPIERE_DB_NAME" = "" ]
  then
    echo "Please make sure that the environment variables are set correctly:"
    echo "	ADEMPIERE_HOME	e.g. /Adempiere"
    echo "	ADEMPIERE_DB_NAME	e.g. adempiere.adempiere.org"
    exit 1
fi

export PGPASSWORD=reference
pg_dump --no-owner -U reference $ADEMPIERE_DB_NAME > $ADEMPIERE_HOME/data/Adempiere_pg.dmp 
export PGPASSWORD=

cd $ADEMPIERE_HOME/data
jar cvfM Adempiere_pg.jar Adempiere_pg.dmp
