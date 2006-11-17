#	This script rebuilds Adempiere 
#	Ported from Windows script Marek Mosiewicz<marek.mosiewicz@jotel.com.pl>
#	If you have difficulties, compare it with the Windows version.
#
#	$Header: /cvsroot/adempiere/utils_dev/myDevEnvTemplate.sh,v 1.6 2003/04/27 12:34:16 marekmosiewicz Exp $

SAVED_DIR=`pwd`			#save current dir
cd `dirname $0`			#change dir to place where script resides - does not work with sym links
UTILS_DEV=`pwd`			#this is adempiere source
cd $SAVED_DIR			#back to the saved directory

.  $UTILS_DEV/myDevEnv.sh	#call environment


if [ ! $ADEMPIERE_ENV==Y ] ; then
    echo "Can't set development environemnt - check myDevEnv.sh"
    exit 1
fi

echo Cleanup ...
$JAVA_HOME/bin/java -Dant.home="." $ANT_PROPERTIES org.apache.tools.ant.Main clean

echo Building ...
$JAVA_HOME/bin/java   -Xmx500m  -Dant.home="." $ANT_PROPERTIES org.apache.tools.ant.Main -logger org.apache.tools.ant.listener.MailLogger complete

ls $ADEMPIERE_INSTALL

echo Done ...

exit 0

