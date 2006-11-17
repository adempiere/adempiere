#!/bin/sh
#
# $Id: RUN_Migrate.sh,v 1.7 2006/01/23 04:56:12 jjanke Exp $

if [ $ADEMPIERE_HOME ]; then
  cd $ADEMPIERE_HOME/utils
fi
. ./myEnvironment.sh Server
echo	Adempiere Version Migration - $ADEMPIERE_HOME \($ADEMPIERE_DB_NAME\)
echo	.
echo	Version Migration is an optional service for a fee.
echo	Please check http://www.adempiere.com/migrate/
echo	.

# Optional Set Source/Refence Database
# This requires that RUN_ImportReference was completed
# The default is:
# SOURCEDB="$ADEMPIERE_DB_URL reference reference"
# export SOURCEDB

# Optionally Set Target Database
# If you set the target database, the Source Database need to be set too!!
# If not, the default connection will be used:
# TARGETDB="$ADEMPIERE_DB_URL $ADEMPIERE_DB_USER $ADEMPIERE_DB_PASSWORD"
# export TARGETDB

$ADEMPIERE_JAVA $ADEMPIERE_JAVA_OPTIONS -cp $CLASSPATH com.adempiere.client.StartMaintain $SOURCEDB $TARGETDB



