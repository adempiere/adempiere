echo	Adempiere PostgreSQL Database Export 	$Revision: 1.3 $

# $Id: DBExport.sh,v 1.3 2005/01/22 21:59:15 jjanke Exp $

echo Saving database $1@$ADEMPIERE_DB_NAME to $ADEMPIERE_HOME/data/ExpDat.dump.tar.gz

pg_dump -F c -f $ADEMPIERE_HOME/data/ExpDat.dump.tar.gz adempiere 

