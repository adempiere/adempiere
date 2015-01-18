#	This script rebuilds ADInterface
#

AD_SRC=../globalqss/adempiere355a/
BASEDIR=.

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

#classpath
export ANT_CLASSPATH=$CLASSPATH:$AD_SRC/tools/lib/ant.jar:$AD_SRC/tools/lib/ant-launcher.jar:$AD_SRC/tools/lib/ant-commons-net.jar:$JAVA_HOME/lib/tools.jar

export ANT_OPTS="-Xms128m -Xmx512m"
export VERBOSITY=$1  #  -verbose or -debug

echo Generate jar bean file from xsd ...
$JAVA_HOME/bin/java $ANT_OPTS -classpath $ANT_CLASSPATH -Dant.home="." org.apache.tools.ant.Main $VERBOSITY xmlbean

echo Done ...

exit 0

