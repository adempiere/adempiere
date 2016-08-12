#!/bin/bash
#
# Name:			RUN_ImportAdempiere.sh
# Description:	script to import Adempiere database
# Created:		2010-05-11
# Vendor:		K.K. Alice
# Author:		Stefan Christians
#
# FileTarget:	~/development/sandbox/adempiere/utils/RUN_ImportAdempiere.sh
# FileOwner:	spc.dvp
# FilePerms:	0755
#

# database binaries are expected to be in the PATH
# for oracle: sqlplus, imp, loadjava ...
# for postgresql: psql, dropdb, createdb, dropuser ...

# exit codes:
errorSuccess=0			# successful execution
errorGeneral=1			# general unspecified error
errorNoEnvironment=10	# environment settings could not be loaded
errorNoSeedFile=11		# data seed file could not be loaded
errorNoImportScript=12	# database dependent import script could not be executed
errorNoSqljFile=13		# sqlj libraries could not be loaded
errorWrongVendor=20		# this script is not suitable for the named database vendor
errorDropDatabase=30	# the database could not be dropped
errorCreateDatabase=31	# the database could not be created
errorRenameDatabase=32	# the database could not be renamed
errorModifyDatabase=33	# the database could not be modified
errorDropUser=40		# the user could not be dropped
errorCreateUser=41		# the user could not be created
errorRenameUser=42		# the user could not be renamed
errorModifyUser=43		# the user could not be modified
errorDropSchema=50		# the schema could not be dropped
errorCreateSchema=51	# the schema could not be created
errorRenameSchema=52	# the schema could not be renamed
errorModifySchema=53	# the schema could not be modified
errorImportData=60		# an error occured while trying to import data
errorImportSqlj=61		# an error occured while trying to import sqlj


# if called with any argument, import the reference database
# otherwise import the seed database
importMode="Seed"
if [ $# -gt 0 ] 
then
	importMode="Reference"
fi

# identify this script
echo
echo "Import ADempiere $importMode Database"
echo

# change to directory in which this script resides
DIR_SAV=$(pwd)
cd $(dirname $0)

# load environment
if [ -r "myEnvironment.sh" ]
then
	source myEnvironment.sh nosave &> /dev/null
fi

# set local variables
dbImportScript="$ADEMPIERE_DB_PATH/ImportAdempiere.sh"
dbSignScript="RUN_SignDatabaseBuild.sh"
dbSeedFile="(undefined)"
dbSqljFile=$ADEMPIERE_HOME/lib/sqlj.jar
dbVendor=$ADEMPIERE_DB_PATH
dbHost=$ADEMPIERE_DB_SERVER
dbPort=$ADEMPIERE_DB_PORT
dbUser=$ADEMPIERE_DB_USER
dbPwd=$ADEMPIERE_DB_PASSWORD
sysUser="(undefined)"
sysPwd=$ADEMPIERE_DB_SYSTEM
dbName=$ADEMPIERE_DB_NAME
dbCatalog="(undefined)"
dbSchema=$ADEMPIERE_DB_USER

# variables depending on database vendor
if [ "$dbVendor" == "PostgreSQL" ]
then
	dbSeedFile="$ADEMPIERE_HOME/data/Adempiere_pg.dmp"
	sysUser="postgres"
elif [ "$dbVendor" == "oracle" ]
then
	dbSeedFile="$ADEMPIERE_HOME/data/Adempiere.dmp"
	sysUser="system"
elif [ "$dbVendor" == "oracleXE" ]
then
	dbSeedFile="$ADEMPIERE_HOME/data/Adempiere.dmp"
	sysUser="system"
else
	unSupportedVendor=1	
fi

# variables depending on import mode
if [ "$importMode" == "Reference" ]
then
	dbUser="reference"
	dbPwd="adempiere"
	dbSchema="reference"
	if [ "$dbVendor" == "PostgreSQL" ]
	then
		dbName="reference"
	elif [ "$dbVendor" == "oracle" ]
	then
		dbName=$ADEMPIERE_DB_NAME
	elif [ "$dbVendor" == "oracleXE" ]
	then
		dbName=$ADEMPIERE_DB_NAME
	fi
fi

# sanity checks
sanityCheck=$errorSuccess

# make sure environment is properly defined
if [[ -z $ADEMPIERE_HOME || -z $ADEMPIERE_DB_PATH 
	|| -z $ADEMPIERE_DB_NAME || -z $ADEMPIERE_DB_SERVER || -z $ADEMPIERE_DB_PORT 
	|| -z $ADEMPIERE_DB_SYSTEM 
	|| -z $ADEMPIERE_DB_USER || -z $ADEMPIERE_DB_PASSWORD ]]
then
	cat <<-EOF
	Please make sure that the environment variables are set correctly:
	  ADEMPIERE_HOME	e.g. "/Adempiere"
	  ADEMPIERE_DB_PATH	e.g. "PostgreSQL" or "oracle"
	  ADEMPIERE_DB_NAME	e.g. "adempiere" or "xe"
	  ADEMPIERE_DB_SERVER	e.g. "dbserver.adempiere.org"
	  ADEMPIERE_DB_PORT	e.g. "5432" or "1521"
	  ADEMPIERE_DB_SYSTEM	system user password, e.g. "postgres" or "manager"
	  ADEMPIERE_DB_USER	e.g. "adempiere"
	  ADEMPIERE_DB_PASSWORD	e.g. "adempiere"
	When in doubt, please run RUN_Setup.sh
	EOF
	sanityCheck=$errorNoEnvironment
fi

# make sure the database vendor is supported
if [ $unSupportedVendor ]
then
	echo "The database vendor $dbVendor is not supported."
	sanityCheck=$errorWrongVendor
fi

# make sure the database seed file exists
if [ ! -r $dbSeedFile ]
then
	echo "The seed file $dbSeedFile could not be loaded."
	echo "Please make sure it exists and you have read permissions."
	sanityCheck=$errorNoSeedFile	
fi

# make sure the database dependent import script can be executed
if [ ! -x $dbImportScript ]
then
	echo "The import script $dbImportScript could not be run."
	echo "Please make sure it exists and you have execute permissions."
	sanityCheck=$errorNoImportScript
fi

# call database dependent import script
if [ $sanityCheck -eq 0 ]
then
	if [ "$importMode" == "Seed" ]
	then
		cat <<-EOF
		WARNING: The live database will be overwritten!
		         Make sure you have a backup!
		         
		EOF
	fi      
	echo "Re-create user $dbUser"
	echo "and import $dbSeedFile"
	echo
	echo "== The import will show warnings. This is OK =="
	echo "Press enter to continue ..."
	read in
	
	$dbImportScript "$dbVendor" "$dbHost" "$dbPort" "$dbUser" "$dbPwd" "$sysUser" "$sysPwd" "$dbName" "$dbCatalog" "$dbSchema" "$dbSeedFile" "$dbSqljFile"
	result=$? 
		
	if [[ $result -eq 0 && "$importMode" == "Seed" && -f $dbSignScript && -x $dbSignScript ]]
	then
		./$dbSignScript
	fi

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
