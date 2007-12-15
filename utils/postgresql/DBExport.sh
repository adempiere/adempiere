echo	Adempiere PostgreSQL Database Export 	$Revision: 1.3 $

# $Id: DBExport.sh,v 1.3 2005/01/22 21:59:15 jjanke Exp $

echo Saving database $1@$ADEMPIERE_DB_NAME to $ADEMPIERE_HOME/data/ExpDat.dmp

if [ $# -eq 0 ] 
  then
    echo "Usage:		$0 <userAccount>"
    echo "Example:	$0 adempiere adempiere"
    exit 1
fi
if [ "$ADEMPIERE_HOME" = "" -o  "$ADEMPIERE_DB_NAME" = "" ]
  then
    echo "Please make sure that the environment variables are set correctly:"
    echo "	ADEMPIERE_HOME	e.g. /Adempiere"
    echo "	ADEMPIERE_DB_NAME	e.g. adempiere.adempiere.org"
    exit 1
fi

export PGPASSWORD=$2
pg_dump --no-owner -U $1 $ADEMPIERE_DB_NAME > $ADEMPIERE_HOME/data/ExpDat.dmp 
export PGPASSWORD=

cd $ADEMPIERE_HOME/data
jar cvfM ExpDat.jar ExpDat.dmp
