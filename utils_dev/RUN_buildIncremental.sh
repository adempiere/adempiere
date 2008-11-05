#!/bin/sh

#	This script compile Adempiere 
#	Ported from Windows script Marek Mosiewicz<marek.mosiewicz@jotel.com.pl>

# check java home
if [ $JAVA_HOME ]; then
  export PATH=$JAVA_HOME/bin:$PATH	
else
  echo JAVA_HOME is not set.
  echo You may not be able to build Adempiere
  echo Set JAVA_HOME to the directory of your local JDK.
  exit
fi

# check jdk
if  [ ! -f $JAVA_HOME/lib/tools.jar ] ; then
   echo "** Need full Java SDK **"
   exit
fi

# check adempiere home
if [ ! $ADEMPIERE_HOME ] ; then
  echo Warning: ADEMPIERE_HOME is not set.
  echo You will not be able to update your local Adempiere Deployment.
  echo Set ADEMPIERE_HOME to the directory of your local Adempiere Deployment.
fi

# stop running adempiere server
if [ $ADEMPIERE_HOME ] ; then
   echo "Stop Apps Server (waiting)"
   $ADEMPIERE_HOME/utils/RUN_Server2Stop.sh
   sleep 5
fi

#classpath
export ANT_CLASSPATH=$CLASSPATH:../tools/lib/ant.jar:../tools/lib/ant-launcher.jar:../tools/lib/ant-swing.jar:../tools/lib/ant-commons-net.jar:../tools/lib/commons-net.jar:$JAVA_HOME/lib/tools.jar

export ANT_OPTS="-Xms128m -Xmx512m"

echo Building ...
$JAVA_HOME/bin/java $ANT_OPTS -classpath $ANT_CLASSPATH -Dant.home="." org.apache.tools.ant.Main update

# start adempiere server
if [ $ADEMPIERE_HOME ] ; then
   echo	Cleaning up ...
   rm -r -f $ADEMPIERE_HOME/jboss/server/adempiere/tmp

   echo	Starting Apps Server ...
   $ADEMPIERE_HOME/utils/RUN_Server2.sh
fi

exit 0
