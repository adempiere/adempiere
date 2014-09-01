#!/bin/bash
#
# Name:			Run_build.sh
# Description:	script to build Adempiere Migrate subproject
# Sponsor:		K.K. Alice
# Created:      2010-05-07
# Author:		Stefan Christians
#				(original script by Marek Mosiewicz)
#
# FileTarget:	~/development/sandbox/adempiere/migrate/RUN_build.sh
# FileOwner:	root.root
# FilePerms:	0755
#

# check java home
if [ $JAVA_HOME ]; then
	export PATH=$JAVA_HOME/bin:$PATH
else
	echo "JAVA_HOME is not set."
	echo "You may not be able to build Adempiere."
	echo "Set JAVA_HOME to the directory of your local JDK."
	exit
fi

# check jdk
if  [ ! -f $JAVA_HOME/lib/tools.jar ] ; then
	echo "** Need full Java SDK **"
	exit
fi

# classpath
export ANT_CLASSPATH=$CLASSPATH:../tools/lib/ant.jar:../tools/lib/ant-launcher.jar:$JAVA_HOME/lib/tools.jar

# compile using Ant
echo running Ant
$JAVA_HOME/bin/java -Xms512m -Xmx512m -classpath $ANT_CLASSPATH -Dant.home="." $ANT_PROPERTIES org.apache.tools.ant.Main

