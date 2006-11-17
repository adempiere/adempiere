#!/bin/sh
#
# $Id: RUN_ImportAdempiere.sh,v 1.9 2005/01/22 21:59:15 jjanke Exp $

if [ $ADEMPIERE_HOME ]; then
  cd $ADEMPIERE_HOME/utils
fi
. ./myEnvironment.sh Server
echo Import Adempiere - $ADEMPIERE_HOME \($ADEMPIERE_DB_NAME\)


echo Re-Create Adempiere User and import $ADEMPIERE_HOME/data/Adempiere.dmp - \($ADEMPIERE_DB_NAME\)
echo == The import will show warnings. This is OK ==
ls -lsa $ADEMPIERE_HOME/data/Adempiere.dmp
echo Press enter to continue ...
read in

# Parameter: <systemAccount> <AdempiereID> <AdempierePwd>
sh $ADEMPIERE_DB_PATH/ImportAdempiere.sh system/$ADEMPIERE_DB_SYSTEM $ADEMPIERE_DB_USER $ADEMPIERE_DB_PASSWORD
