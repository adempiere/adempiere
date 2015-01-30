#!/bin/bash
#
# Name:			RUN_ImportAdempiere.sh
# Description:	Description:script to update the database to the next seed.

# XML files are expected to be found in the %ADEMPIERE_HOME%/migration folder

# exit codes:
errorSuccess=0			# successful execution
errorGeneral=1			# general unspecified error
errorNoEnvironment=10	# environment settings could not be loaded
errorNoSeedFile=11		# data seed file could not be loaded

# identify this script
echo "==================================="
echo "Migrate ADempiere using XML"
echo "==================================="
echo

# Can be called with argument "clean" to mark the applied dictionary migrations as processed
# and delete the steps and data to reduce the database size.
CLEAN_MODE=$1

# change to directory in which this script resides
DIR_SAV=$(pwd)
cd $(dirname $0)

# load environment
if [ -r "myEnvironment.sh" ]
then
	source myEnvironment.sh nosave &> /dev/null
fi

# sanity checks
sanityCheck=$errorSuccess

# make sure environment is properly defined
if [[ -z $ADEMPIERE_HOME || -z $JAVA_HOME ]]
then
	cat <<-EOF
	Please make sure that the environment variables are set correctly:
	  ADEMPIERE_HOME	e.g. "/Adempiere"
	  JAVA_HOME			e.g. "/usr/java/jdk1.8.0_25"
	When in doubt, please run RUN_Setup.sh
	EOF
	sanityCheck=$errorNoEnvironment
fi

# call database dependent import script
if [ $sanityCheck -eq 0 ]
then

	echo "It is recommended that production databases be migrated using "
	echo "RUN_Migrate.sh"
	echo
	echo Usage: RUN_Migrate.bat
	echo
	echo Optional argument "clean" will mark all dictionary migrations that have
	echo been applied as processed and will delete the steps and data to save 
	echo space.
	echo
	echo "WARNING: If the database is not a fresh import of the seed, make sure "
	echo "you have a backup!"
	echo
	echo "Press enter to continue ..."
	read in
	
	CP=$ADEMPIERE_HOME/lib/CInstall.jar:$ADEMPIERE_HOME/lib/Adempiere.jar:$ADEMPIERE_HOME/lib/CCTools.jar:$ADEMPIERE_HOME/lib/oracle.jar:$ADEMPIERE_HOME/lib/jboss.jar:$ADEMPIERE_HOME/lib/postgresql.jar:
	JAVA=$JAVA_HOME/bin/java

	$JAVA -classpath $CP -DADEMPIERE_HOME=$ADEMPIERE_HOME org.adempiere.process.MigrationLoader $CLEAN_MODE
	result=$? 
		
else
	result=$sanityCheck
fi

# change back to calling directory
if [ -n $DIR_SAV ]
then
	cd $DIR_SAV
fi

# end of script
echo
if [ $result -eq 0 ]
then
	echo "done."
else
	echo "terminated abnormally"
fi
exit $result
