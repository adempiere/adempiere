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

#echo ===================================
#echo Update Adempiere Server Environment
#echo ===================================
CP=lib/CInstall.jar:lib/Adempiere.jar:lib/CCTools.jar:lib/oracle.jar:lib/jboss.jar:lib/postgresql.jar:lib/mysql-connector-java-5.1.13-bin.jar:
$JAVA -classpath $CP -DADEMPIERE_HOME=$ADEMPIERE_HOME -Dant.home="." org.apache.tools.ant.launch.Launcher update
