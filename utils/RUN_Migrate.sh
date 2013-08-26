#!/bin/sh
#
echo Migrate Admpiere

if [ $ADEMPIERE_HOME ]; then
  cd $ADEMPIERE_HOME/utils
fi
. ./myEnvironment.sh Server

JAVA=$JAVA_HOME/bin/java

echo ===================================
echo Migrate Adempiere
echo ===================================
CP=$ADEMPIERE_HOME/lib/CInstall.jar:$ADEMPIERE_HOME/lib/Adempiere.jar:$ADEMPIERE_HOME/lib/CCTools.jar:$ADEMPIERE_HOME/lib/oracle.jar:$ADEMPIERE_HOME/lib/jboss.jar:$ADEMPIERE_HOME/lib/postgresql.jar:

$JAVA -classpath $CP -DADEMPIERE_HOME=$ADEMPIERE_HOME org.adempiere.process.MigrationLoader
