#!/bin/sh
#
echo Install ADempiere Server

if [ $ADEMPIERE_HOME ]; then
  cd $ADEMPIERE_HOME/utils
fi
. ./myEnvironment.sh Server

JAVA=$JAVA_HOME/bin/java

echo ===================================
echo Sign Database Build
echo ===================================
CP=$ADEMPIERE_HOME/lib/CInstall.jar:$ADEMPIERE_HOME/lib/Adempiere.jar:$ADEMPIERE_HOME/lib/CCTools.jar:$ADEMPIERE_HOME/lib/oracle.jar:$ADEMPIERE_HOME/lib/jboss.jar:$ADEMPIERE_HOME/lib/postgresql.jar:$ADEMPIERE_HOME/lib/mysql-connector-java-5.1.13-bin.jar:

$JAVA -classpath $CP -DADEMPIERE_HOME=$ADEMPIERE_HOME org.adempiere.process.SignDatabaseBuild
