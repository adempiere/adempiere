#!/bin/bash
#
# Name:			ImportAdempiere.sh
# Description:	script to import database into oracle
# Created:		2010-05-12
# Vendor:		K.K. Alice
# Author:		Stefan Christians
#
# FileTarget:	~/development/sandbox/adempiere/utils/oracle/ImportAdempiere.sh
# FileOwner:	spc.dvp
# FilePerms:	0755
#

# if 12 parameters are passed, all information we need is included in the parameters
# and we can run the "new style" script.
# otherwise just execute the "old" code to ensure backward compatibility

if [ $# -ne 12 ]
then
	# begin original "old style" script --->>>

#!/bin/sh

# $Id: ImportAdempiere.sh,v 1.10 2005/12/20 07:12:17 jjanke Exp $
echo	Adempiere Database Import		$Revision: 1.10 $

echo	Importing Adempiere DB from $ADEMPIERE_HOME/data/Adempiere.dmp 

if [ $# -le 2 ] 
  then
    echo "Usage:		$0 <systemAccount> <AdempiereID> <AdempierePWD>"
    echo "Example:	$0 system/manager adempiere adempiere"
    exit 1
fi
if [ "$ADEMPIERE_HOME" = "" -o  "$ADEMPIERE_DB_NAME" = "" ]
  then
    echo "Please make sure that the environment variables are set correctly:"
    echo "	ADEMPIERE_HOME	e.g. /Adempiere"
    echo "	ADEMPIERE_DB_NAME	e.g. adempiere.adempiere.org"
    exit 1
fi


echo -------------------------------------
echo Re-Create DB user
echo -------------------------------------
echo sqlplus $1@$ADEMPIERE_DB_SERVER:$ADEMPIERE_DB_PORT/$ADEMPIERE_DB_NAME @$ADEMPIERE_HOME/utils/$ADEMPIERE_DB_PATH/CreateUser.sql $2 $3
sqlplus $1@$ADEMPIERE_DB_SERVER:$ADEMPIERE_DB_PORT/$ADEMPIERE_DB_NAME @$ADEMPIERE_HOME/utils/$ADEMPIERE_DB_PATH/CreateUser.sql $2 $3

echo -------------------------------------
echo Import Adempiere.dmp
echo -------------------------------------
echo "imp $1@$ADEMPIERE_DB_SERVER:$ADEMPIERE_DB_PORT/$ADEMPIERE_DB_NAME FILE=$ADEMPIERE_HOME/data/Adempiere.dmp FROMUSER=\(reference\) TOUSER=$2"
imp $1@$ADEMPIERE_DB_SERVER:$ADEMPIERE_DB_PORT/$ADEMPIERE_DB_NAME FILE=$ADEMPIERE_HOME/data/Adempiere.dmp FROMUSER=\(reference\) TOUSER=$2 

echo -------------------------------------
echo Create SQLJ 
echo -------------------------------------
$ADEMPIERE_HOME/utils/$ADEMPIERE_DB_PATH/create.sh $ADEMPIERE_DB_USER/$ADEMPIERE_DB_PASSWORD

echo -------------------------------------
echo Check System
echo Import may show some warnings. This is OK as long as the following does not show errors
echo -------------------------------------
echo sqlplus $2/$3@$ADEMPIERE_DB_SERVER:$ADEMPIERE_DB_PORT/$ADEMPIERE_DB_NAME @$ADEMPIERE_HOME/utils/$ADEMPIERE_DB_PATH/AfterImport.sql
sqlplus $2/$3@$ADEMPIERE_DB_SERVER:$ADEMPIERE_DB_PORT/$ADEMPIERE_DB_NAME @$ADEMPIERE_HOME/utils/$ADEMPIERE_DB_PATH/AfterImport.sql
	
	# <<<--- end original "old style" script
	exit 0
fi


# NEW STYLE IMPORT SCRIPT STARTS HERE
# ===================================

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

# set local variables
dbVendor="$1"
dbHost="$2"
dbPort="$3"
dbUser="$4"
dbPwd="$5"
sysUser="$6"
sysPwd="$7"
dbName="$8"
dbCatalog="$9" # ignored for oracle
dbSchema="${10}" # ignored for oracle
dbSeedFile="${11}"
dbSqljFile="${12}"

# make sure this script is called for the correct vendor
if [ "$dbVendor" != "oracle" ]
then
	echo "wrong vendor"
	exit $errorWrongVendor
fi

# drop existing user
echo "drop user \"$dbUser\""
sqlplus -S /nolog &> /dev/null <<-EOF
	CONNECT $sysUser/$sysPwd@$dbHost:$dbPort/$dbName;
	DROP USER $dbUser CASCADE;
	EXIT;
	EOF
result=$?
if [ $result -ne 0 ]
then
	echo "drop user failed with exit code $result"
	exit $errorDropUser
fi

# recreate user 
echo "recreate user \"$dbUser\""
sqlplus -S /nolog &> /dev/null <<-EOF
	CONNECT $sysUser/$sysPwd@$dbHost:$dbPort/$dbName;
	CREATE USER $dbUser IDENTIFIED BY $dbPwd
	DEFAULT TABLESPACE USERS
	TEMPORARY TABLESPACE TEMP
	PROFILE DEFAULT
	ACCOUNT UNLOCK;
	GRANT CONNECT TO $dbUser;
	GRANT DBA TO $dbUser;
	GRANT RESOURCE TO $dbUser;
	GRANT UNLIMITED TABLESPACE TO $dbUser;
	ALTER USER $dbUser DEFAULT ROLE CONNECT, RESOURCE, DBA;
	GRANT CREATE TABLE TO $dbUser;
	EXIT;
	EOF
result=$?
if [ $result -ne 0 ]
then
	echo "create user failed with exit code $result"
	exit $errorCreateUser
fi

# import database
echo "----------------------------------------"
echo "Importing $dbSeedFile"
echo "----------------------------------------"
imp $sysUser/$sysPwd@$dbHost:$dbPort/$dbName SILENT=Y FILE=\'$dbSeedFile\' FROMUSER=\(reference\) TOUSER=$dbUser
result=$?
if [ $result -ne 0 ]
then
	echo "import failed with exit code $result"
	exit $errorImportData
fi

# create sqlj
if [ -x $(dirname $0)/create.sh ]
then
	echo "----------------------------------------"
	echo "Creating SQLJ"
	echo "----------------------------------------"
	$(dirname $0)/create.sh "$dbVendor" "$dbHost" "$dbPort" "$dbUser" "$dbPwd" "$sysUser" "$sysPwd" "$dbName" "$dbCatalog" "$dbSchema" "$dbSeedFile" "$dbSqljFile"
	result=$?
	if [ $result -ne 0 ]
	then
		echo "create sqlj failed with exit code $result"
		exit $errorImportSqlj
	fi
fi

# If we are running in a distribution built from trunk head,
# the seed database must still be updated from last release to head.
# The relevant migration scripts will be located in
# ADEMPIERE_HOME/data/migration/oracle/...
# It can only happen if the adempiere code is a custom-built RPM package,
# so this feature is only included in the relevant *.sh scripts.
if [ "$dbUser" != "adempiere" ]
then
	scriptHome=$(dirname "$dbSeedFile")/migration/$dbVendor
	if [ -d "$scriptHome" ]
	then
		echo "upgrade seed database to head version"
		for migrationScript in $(find $scriptHome -type f -name '*.sql' -exec basename '{}' \; | sort -n)
		do
			echo "$migrationScript:";
			sqlplus -S /nolog <<-EOF
			CONNECT $dbUser/$dbPwd@$dbHost:$dbPort/$dbName;
			@"$scriptHome/$migrationScript";
			EXIT;
			EOF
		done
	fi
fi


# check system
# call AfterImport.sql
if [ -r $(dirname $0)/AfterImport.sql ]
then
	echo ----------------------------------------
	echo Check System
	echo Import may show some warnings. This is OK as long as the following does not show errors
	echo ----------------------------------------
	sqlplus -S $dbUser/$dbPwd@$dbHost:$dbPort/$dbName @"$(dirname $0)/AfterImport.sql"
	result=$?
	if [ $result -ne 0 ]
	then
		echo "check system failed with exit code $result"
		exit $errorImportData
	fi
fi


# done
exit $errorSuccess
