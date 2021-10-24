#!/bin/sh

#	This script rebuilds Adempiere 
#	Ported from Windows script Marek Mosiewicz<marek.mosiewicz@jotel.com.pl>
#	If you have difficulties, compare it with the Windows version.
#
#	$Header: /cvsroot/adempiere/utils_dev/myDevEnvTemplate.sh,v 1.6 2003/04/27 12:34:16 marekmosiewicz Exp $

#check java home
if [ $JAVA_HOME ]; then
  export PATH=$JAVA_HOME/bin:$PATH	
else
  echo JAVA_HOME is not set.
  echo You may not be able to build Adempiere
  echo Set JAVA_HOME to the directory of your local JDK.
  exit
fi

if [ $# -eq 0 ] 
  then
    echo "Usage:    $0 target"
    echo "Example:  $0 complete"
    exit 1
fi

#classpath
export ANT_HOME="../tools/lib/ant/apache-ant-1.10.10"
export JAVA_CLASSPATH=$CLASSPATH:$ANT_HOME/lib/ant-launcher.jar:../tools/lib/commons-net.jar:$JAVA_HOME/lib/tools.jar
export JAVA_OPTS="-Xms128m -Xmx512m"

echo Launching Ant target $1 ...
$JAVA_HOME/bin/java $JAVA_OPTS -classpath $JAVA_CLASSPATH -Dant.home=$ANT_HOME org.apache.tools.ant.launch.Launcher $1

echo Done ...

exit 0

