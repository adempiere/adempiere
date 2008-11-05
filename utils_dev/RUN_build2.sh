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

# check jdk
if  [ ! -f $JAVA_HOME/lib/tools.jar ] ; then
   echo "** Need full Java SDK **"
   exit
fi

if [ $# -eq 0 ] 
  then
    echo "Usage:    $0 target"
    echo "Example:  $0 complete"
    exit 1
fi

#classpath
export ANT_CLASSPATH=$CLASSPATH:../tools/lib/ant.jar:../tools/lib/ant-launcher.jar:../tools/lib/ant-swing.jar:../tools/lib/ant-commons-net.jar:../tools/lib/commons-net.jar:$JAVA_HOME/lib/tools.jar

export ANT_OPTS="-Xms128m -Xmx512m"

echo Building ...
$JAVA_HOME/bin/java $ANT_OPTS -classpath $ANT_CLASSPATH -Dant.home="." org.apache.tools.ant.Main $1

echo Done ...

exit 0

