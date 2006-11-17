#!/bin/sh
#
# $Id: RUN_Adempiere.sh,v 1.18 2005/07/21 16:44:54 jjanke Exp $
echo Adempiere Client $ADEMPIERE_HOME

#	Set directly to overwrite
#ADEMPIERE_HOME=/Adempiere
#JAVA_HOME=/usr/lib/java

##	Check Java Home
if [ $JAVA_HOME ]; then
  JAVA=$JAVA_HOME/bin/java
else
  JAVA=java
  echo JAVA_HOME is not set.
  echo   You may not be able to start Adempiere
  echo   Set JAVA_HOME to the directory of your local JDK.
fi

## Check Adempiere Home
if [ $ADEMPIERE_HOME ]; then
  CLASSPATH=$ADEMPIERE_HOME/lib/Adempiere.jar:$ADEMPIERE_HOME/lib/AdempiereCLib.jar:$CLASSPATH
else
  CLASSPATH=lib/Adempiere.jar:lib/AdempiereCLib.jar:$CLASSPATH
  echo ADEMPIERE_HOME is not set
  echo   You may not be able to start Adempiere
  echo   Set ADEMPIERE_HOME to the directory of Adempiere.
fi


# To switch between multiple installs, copy the created Adempiere.properties file
# Select the configuration by setting the PROP variable
PROP=
#PROP=-DPropertyFile=test.properties

#  To use your own Encryption class (implementing org.compiere.util.SecureInterface),
#  you need to set it here (and in the server start script) - example:
#  SECURE=-DADEMPIERE_SECURE=org.compiere.util.Secure
SECURE=

$JAVA -Xms32m -Xmx512m -DADEMPIERE_HOME=$ADEMPIERE_HOME $PROP $SECURE -classpath $CLASSPATH org.compiere.Adempiere

