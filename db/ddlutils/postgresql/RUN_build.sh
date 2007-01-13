#	This script rebuilds Adempiere 
#	Ported from Windows script Marek Mosiewicz<marek.mosiewicz@jotel.com.pl>
#	If you have difficulties, compare it with the Windows version.
#
#	$Header: /cvsroot/adempiere/utils_dev/myDevEnvTemplate.sh,v 1.6 2003/04/27 12:34:16 marekmosiewicz Exp $

SAVED_DIR=`pwd`			#save current dir
export ANT_HOME=/e-evolution/adempiere/trunk/tools/lib/
cd `dirname $0`			#change dir to place where script resides - does not work with sym links
export CP=$ANT_HOME/ant.jar:$ANT_HOME/ant-launcher.jar:$ANT_HOME/ant-commons-net.jar:$ANT_HOME/ant-swing.jar:/e-evolution/adempiere/trunk/tools/lib/log4j.jar 
echo $CLASSPATH
echo Building ...
#$JAVA_HOME/bin/java -cp $CP -Dant.home="." $ANT_PROPERTIES org.apache.tools.ant.Main create
$JAVA_HOME/bin/java -cp $CP -Dant.home="." $ANT_PROPERTIES org.apache.tools.ant.Main create

echo Done ...

exit 0

