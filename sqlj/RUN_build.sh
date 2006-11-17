# Module compiling script
# Ported from Windows script 
echo Title Build SQLJ
#   $Header: /cvsroot/compiere/sqlj/RUN_build.bat,v 1.4 2005/02/03 07:10:24 jjanke Exp $
#
#    Note that the SQLJ build is not part of the normal build cycle.
#    You need to build the sqlj.jar file either with this script
#    or with the 'compile' script for older Java versions
#    You then deploy it with the database dependent 'create' script

SAVED_DIR=`pwd`			#save current dir
cd `dirname $0`/../utils_dev	#change dir to place where script resides - doesn not work with sym links
UTILS_DEV=`pwd`			#this is compiere source
cd $SAVED_DIR			#back to the saved directory

.  $UTILS_DEV/myDevEnv.sh	#call environment
echo done
if [ ! $COMPIERE_ENV==Y ] ; then
    echo "Can't set developemeent environemnt - check myDevEnv.sh"
    exit 1
fi

echo running Ant
$JAVA_HOME/bin/java -Dant.home="." $ANT_PROPERTIES org.apache.tools.ant.Main clean
echo Building ...
$JAVA_HOME/bin/java -Dant.home="." $ANT_PROPERTIES org.apache.tools.ant.Main sqljDist
