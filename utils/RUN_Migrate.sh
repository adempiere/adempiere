#!/bin/bash
#
# Name:			RUN_Migrate.sh
# Description:	script to migrate version
# Created:		2010-05-12
# Vendor:		K.K. Alice
# Author:		Stefan Christians
#
# FileTarget:	~/development/sandbox/adempiere/utils/RUN_Migrate.sh
# FileOwner:	spc.dvp
# FilePerms:	0755
#

# exit codes:
errorSuccess=0			# successful execution
errorGeneral=1			# general unspecified error
errorNoEnvironment=10	# environment settings could not be loaded
errorNoJarFile=11		# the migration tool could not be loaded
errorNoLibraries=12		# required libraries could not be found


# possible parameters:
# text	 run in text mode (no GUI, output on console)
# silent run in silent mode (suppress console output, implies text mode)
isText=
isSilent=
if [ $# -gt 0 ]
then
	for argument in "$@"
	do
		if [ "$argument" == "text" ]
		then
			isText="-DisText"
		elif [ "$argument" == "silent" ]
		then
			isText="-DisText"
			isSilent="-DisSilent"
		else
			cat <<-EOF
			usage: $(basename $0)		run migration interactively
						 $(basename $0) text	run migration in text mode
						 $(basename $0) silent	run migration without any output
			EOF
			exit $errorGeneral
		fi
	done
fi

# identify this script
if [ "$isSilent" != "-DisSilent" ]
then
	echo "Adempiere Migration Tool"
fi

# change to directory in which this script resides
DIR_SAV=$(pwd)
cd $(dirname $0)

# load environment
if [ -r "myEnvironment.sh" ]
then
	source myEnvironment.sh nosave &> /dev/null
fi


# set local variables
dbSignScript="RUN_SignDatabaseBuild.sh"
javaCommand="$ADEMPIERE_JAVA"
javaOptions="$ADEMPIERE_JAVA_OPTIONS"
javaLibs="$ADEMPIERE_HOME/lib"
javaJar="$javaLibs/migrate.jar"
javaClass="com.kkalice.adempiere.migrate.Migrate"
classPath=

# sanity checks
sanityCheck=$errorSuccess

# make sure environment is properly defined
if [[ -z $ADEMPIERE_HOME || -z $ADEMPIERE_JAVA ]]
then
	cat <<-EOF
	Please make sure that the environment variables are set correctly:
		ADEMPIERE_HOME	e.g. "/Adempiere"
		ADEMPIERE_JAVA	e.g. "\$JAVA_HOME/bin/java"
	When in doubt, please run RUN_Setup.sh
	EOF
	sanityCheck=$errorNoEnvironment
fi

# make sure the migration tool exists
if [ ! -r $javaJar ]
then
	echo "The migration tool $javaJar could not be loaded."
	echo "Please make sure it exists and you have read permissions."
	sanityCheck=$errorNoJarFile
fi

# make sure required libraries exist
if [ -z "$(ls $javaLibs/*.jar 2>/dev/null)" ]
then
	echo "The required libraries could not be found in $javaLibs."
	echo "Please make sure the directory exists and you have read permissions for the jar files."
	sanityCheck=$errorNoLibraries
fi

# make sure at least memory usage is set in java options
if [ -z "$javaOptions" ]
then
	javaOptions="-Xms64M -Xmx512M"
fi



# start migration tool
if [ $sanityCheck -eq 0 ]
then

	# construct the classpath

	# first point to our migration tool
	classPath=$javaJar

	# then add jar files in ADEMPIERE_HOME/lib
	# exclude known patterns of other files so that only database drivers are included
	# (it does not matter if more than the database drivers are included,
	# things just speed up if the classpath is limited to only a few files)
	for jarFile in $javaLibs/*.jar
	do
		validJarFile=$(echo $(basename $jarFile) | grep -iv 'migrate\|adempiere\|packages\|jboss\|tools\|install\|patches\|jasper\|posterita\|^zk.*\|glassfish\|custom\|sqlj')
		if [ -n "$validJarFile" ]
		then
			classPath=$classPath:$javaLibs/$validJarFile
		fi
	done

	# then add any already existing classpath
	if [ -n $CLASSPATH ]
	then
		classPath=$classPath:$CLASSPATH
	fi

	# run the migration tool
	$javaCommand $javaOptions -cp $classPath $isText $isSilent $javaClass
	result=$?

else
	result=$sanityCheck
fi

# sign the database
if [ -x $dbSignScript ]
then
	./$dbSignScript &>/dev/null
fi

# change back to calling directory
if [ -n $DIR_SAV ]
then
	cd $DIR_SAV
fi

# end of script
if [ "$isSilent" != "-DisSilent" ]
then
	if [ $result -eq 0 ]
	then
		echo "done."
	else
		echo "terminated abnormally"
	fi
echo
fi
exit $result
