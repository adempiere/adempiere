#!/bin/sh
#
echo Install Adempiere Server
# $Header: /cvsroot/adempiere/install/Adempiere/RUN_setup.sh,v 1.19 2005/09/08 21:54:12 jjanke Exp $

if [ $JAVA_HOME ]; then
  JAVA=$JAVA_HOME/bin/java
else
  JAVA=java
  echo JAVA_HOME is not set.
  echo You may not be able to start the Setup
  echo Set JAVA_HOME to the directory of your local JDK.
fi


echo ===================================
echo Sign Database Build
echo ===================================
CP=$ADEMPIERE_HOME/lib/CInstall.jar:$ADEMPIERE_HOME/lib/Adempiere.jar:$ADEMPIERE_HOME/lib/CCTools.jar:$ADEMPIERE_HOME/lib/oracle.jar:$ADEMPIERE_HOME/lib/derby.jar:$ADEMPIERE_HOME/lib/fyracle.jar:$ADEMPIERE_HOME/lib/jboss.jar:$ADEMPIERE_HOME/lib/postgresql.jar:

$JAVA -classpath $CP -DADEMPIERE_HOME=$ADEMPIERE_HOME org.adempiere.process.SignDatabaseBuild
