#!/bin/bash
#
# Name:			ImportAdempiere.sh
# Description:	script to import database into postgresql
# Created:		2010-05-11
# Vendor:		K.K. Alice
# Author:		Stefan Christians
#
# FileTarget:	~/development/sandbox/adempiere/utils/postgresql/ImportAdempiere.sh
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

echo	ADempiere Database Import

echo	Importing ADempiere DB from $ADEMPIERE_HOME/data/Adempiere_pg.dmp

if [ $# -le 2 ]
	then
		echo "Usage:		$0 <systemAccount> <AdempiereID> <AdempierePWD> <PostgresPwd>"
		echo "Example:	$0 postgres adempiere adempiere postgresPwd"
		exit 1
fi
if [ "$ADEMPIERE_HOME" = "" -o  "$ADEMPIERE_DB_NAME" = "" -o "$ADEMPIERE_DB_SERVER" = "" -o "$ADEMPIERE_DB_PORT" = "" ]
	then
		echo "Please make sure that the environment variables are set correctly:"
		echo "	ADEMPIERE_HOME	e.g. /Adempiere"
		echo "	ADEMPIERE_DB_NAME	e.g. adempiere or xe"
		echo "  ADEMPIERE_DB_SERVER e.g. dbserver.adempiere.org"
		echo "  ADEMPIERE_DB_PORT e.g. 5432 or 1521"
		exit 1
fi

PGPASSWORD=$4
export PGPASSWORD
echo -------------------------------------
echo Recreate user and database
echo -------------------------------------
dropdb -h $ADEMPIERE_DB_SERVER -p $ADEMPIERE_DB_PORT -U postgres $ADEMPIERE_DB_NAME

dropuser -h $ADEMPIERE_DB_SERVER -p $ADEMPIERE_DB_PORT -U postgres $2

ADEMPIERE_CREATE_ROLE_SQL="CREATE ROLE $2 SUPERUSER LOGIN PASSWORD '$3'"
psql -h $ADEMPIERE_DB_SERVER -p $ADEMPIERE_DB_PORT -U postgres -c "$ADEMPIERE_CREATE_ROLE_SQL"
ADEMPIERE_CREATE_ROLE_SQL=

PGPASSWORD=$3
export PGPASSWORD
createdb -h $ADEMPIERE_DB_SERVER -p $ADEMPIERE_DB_PORT -E UNICODE -O $2 -U $2 $ADEMPIERE_DB_NAME

echo -------------------------------------
echo Import Adempiere_pg.dmp
echo -------------------------------------
ADEMPIERE_ALTER_ROLE_SQL="ALTER ROLE $2 SET search_path TO adempiere, sqlj, pg_catalog"
psql -h $ADEMPIERE_DB_SERVER -p $ADEMPIERE_DB_PORT -d $ADEMPIERE_DB_NAME -U $2 -c "$ADEMPIERE_ALTER_ROLE_SQL"
psql -h $ADEMPIERE_DB_SERVER -p $ADEMPIERE_DB_PORT -d $ADEMPIERE_DB_NAME -U $2 -c "drop schema sqlj cascade"
psql -h $ADEMPIERE_DB_SERVER -p $ADEMPIERE_DB_PORT -d $ADEMPIERE_DB_NAME -U $2 -f $ADEMPIERE_HOME/data/Adempiere_pg.dmp
ADEMPIERE_ALTER_ROLE_SQL=
PGPASSWORD=
export PGPASSWORD

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
dbCatalog="$9" # ignored for postgresql
dbSchema="${10}"
dbSeedFile="${11}"
dbSqljFile="${12}" # ignored for postgresql

# make sure this script is called for the correct vendor
if [ "$dbVendor" != "PostgreSQL" ]
then
	echo "wrong vendor"
	exit $errorWrongVendor
fi

# drop existing database
# (allowed to fail in case database does not exist)
echo "drop database \"$dbName\""
PGPASSWORD=$sysPwd dropdb -h $dbHost -p $dbPort -U $sysUser $dbName 2> /dev/null

# drop existing user
# (allowed to fail in case user does not exist)
echo "drop user \"$dbUser\""
PGPASSWORD=$sysPwd dropuser -h $dbHost -p $dbPort -U $sysUser $dbUser 2> /dev/null

# recreate user
# (using psql instead of createuser so that we can supply user's password)
# (Allowed to fail if user role still exists - may be associated with other databases.)
echo "recreate user \"$dbUser\""
PGPASSWORD=$sysPwd psql -h $dbHost -p $dbPort -U $sysUser -c "CREATE ROLE $dbUser SUPERUSER LOGIN PASSWORD '$dbPwd'" 1> /dev/null
result=$?
if [ $result -ne 0 ] && [ $result -ne 1 ]
then
	echo "create user failed with exit code $result"
	exit $errorCreateUser
fi

# recreate database
echo "recreate database \"$dbName\""
PGPASSWORD=$dbPwd createdb -h $dbHost -p $dbPort -E UNICODE -O $dbUser -U $dbUser $dbName
if [ $result -ne 0 ]
then
	echo "create database failed with exit code $result"
	exit $errorCreateDatabase
fi

# create plpgsql language
echo "create language \"plpgsql\""
PGPASSWORD=$dbPwd psql -h $dbHost -p $dbPort -d $dbName -U $dbUser -c "CREATE LANGUAGE plpgsql" &> /dev/null

# import database
echo "----------------------------------------"
echo "Importing $dbSeedFile"
echo "----------------------------------------"
PGPASSWORD=$dbPwd psql -h $dbHost -p $dbPort -d $dbName -U $dbUser -f "$dbSeedFile"
result=$?
if [ $result -ne 0 ]
then
	echo "import failed with exit code $result"
	exit $errorImportData
fi

# If we are running in a distribution built from trunk head,
# the seed database must still be updated from last release to head.
# The relevant migration scripts will be located in
# ADEMPIERE_HOME/data/migration/postgresql/...
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
			PGOPTIONS='-c search_path=adempiere' PGPASSWORD=$dbPwd psql -h $dbHost -p $dbPort -d $dbName -U $dbUser -f "$scriptHome/$migrationScript"
		done
	fi
fi

# Adempiere_pg.dmp always imports to schema "adempiere" owned by user "adempiere"
# so we need to rename it to $dbSchema and change ownership to $dbUser

# rename schema
if [ "$dbSchema" != "adempiere" ]
then
	echo "rename schema \"adempiere\" to \"$dbSchema\""
	PGPASSWORD=$dbPwd psql -h $dbHost -p $dbPort -d $dbName -U $dbUser -c "ALTER SCHEMA adempiere RENAME TO $dbSchema" 1> /dev/null
	result=$?
	if [ $result -ne 0 ]
	then
		echo "rename schema failed with exit code $result"
		exit $errorRenameSchema
	fi
fi

# change schema ownership
if [ "$dbUser" != "adempiere" ]
then
	echo "change schema ownership to \"$dbUser\""
	# change all objects in $dbName database owned by adempiere to ownership of $dbUser
	PGPASSWORD=$dbPwd psql -h $dbHost -p $dbPort -d $dbName -U $dbUser -c "REASSIGN OWNED BY adempiere TO $dbUser" 1> /dev/null
	result=$?
	if [ $result -ne 0 ]
	then
		echo "change schema ownership failed with exit code $result"
		exit $errorModifySchema
	fi
fi

# set searchpath
echo "set default schema to \"$dbSchema\""
PGPASSWORD=$dbPwd psql -h $dbHost -p $dbPort -d $dbName -U $dbUser -c "ALTER ROLE $dbUser SET search_path TO $dbSchema, pg_catalog" 1> /dev/null
result=$?
if [ $result -ne 0 ]
then
	echo "set default schema failed with exit code $result"
	exit $errorModifyUser
fi

# done
exit $errorSuccess
