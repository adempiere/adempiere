#!/bin/sh
#
echo Install ADempiere Server

if [ $JAVA_HOME ]; then
  JAVA=$JAVA_HOME/bin/java
  KEYTOOL=$JAVA_HOME/bin/keytool
else
  JAVA=java
  KEYTOOL=keytool
  echo JAVA_HOME is not set.
  echo You may not be able to start the Setup
  echo Set JAVA_HOME to the directory of your local JDK.
fi


echo ===================================
echo Setup Dialog
echo ===================================
CP=lib/CInstall.jar:lib/Adempiere.jar:lib/CCTools.jar:lib/oracle.jar:lib/jboss.jar:lib/postgresql.jar:lib/mysql-connector-java-5.1.13-bin.jar:

# Trace Level Parameter, e.g. ARGS=ALL
ARGS=CONFIG

# To test the OCI driver, add -DTestOCI=Y to the command - example:
# $JAVA -classpath $CP -DADEMPIERE_HOME=$ADEMPIERE_HOME -DTestOCI=Y org.compiere.install.Setup $ARGS

$JAVA -classpath $CP -DADEMPIERE_HOME=$ADEMPIERE_HOME org.compiere.install.Setup $ARGS

# Sign database build
cd utils
. ./RUN_SignDatabaseBuild.sh 

. ./RUN_UnixEnv.sh

#echo ================================
#echo	Test local Connection
#echo ================================
#%JAVA% -classpath lib/Adempiere.jar:lib/AdempiereCLib.jar org.compiere.install.ConnectTest localhost

echo .
echo For problems, check log file in base directory
