#	This script compile Adempiere 
#	Ported from Windows script Marek Mosiewicz<marek.mosiewicz@jotel.com.pl>


SAVED_DIR=`pwd`			#save current dir
cd `dirname $0`/../utils_dev	#change dir to place where script resides - doesn not work with sym links
UTILS_DEV=`pwd`			#this is adempiere source
cd $SAVED_DIR			#back to the saved directory

.  $UTILS_DEV/myDevEnv.sh	#call environment

if [ ! $ADEMPIERE_ENV==Y ] ; then
    echo "Can't set developemeent environemnt - check myDevEnv.sh"
    exit 1
fi

echo "Stop Apps Server (waiting)"
$ADEMPIERE_HOME/utils/RUN_Server2Stop.sh
sleep 5

echo Building ...
$JAVA_HOME/bin/java -Dant.home="." $ANT_PROPERTIES org.apache.tools.ant.Main update

ls $ADEMPIERE_INSTALL

echo	Cleaning up ...
rm -r -f $ADEMPIERE_HOME/jboss/server/adempiere/tmp

echo	Starting Apps Server ...
$ADEMPIERE_HOME/utils/RUN_Server2.sh

exit 0
