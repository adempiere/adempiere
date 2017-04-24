#!/bin/bash
#
# Name:			create.sh
# Description:	script to load SQLJ into oracle
# Created:		2010-07-04
# Vendor:		K.K. Alice
# Author:		Stefan Christians
#
# FileTarget:	~/development/sandbox/adempiere/sqlj/oracle/create.sh
# FileOwner:	spc.dvp
# FilePerms:	0755
#

# if 12 parameters are passed, all information we need is included in the parameters
# and we can run the "new style" script.
# otherwise just execute the "old" code to ensure backward compatibility

if [ $# -ne 12 ]
then
	# begin original "old style" script --->>>

# Create Oracle SQLJ
# Author + Copyright 1999-2005 Jorg Janke
# $Id: create.sh,v 1.3 2005/06/28 18:55:38 jjanke Exp $
#
# Parameter: <adempiereDBuser>/<adempiereDBpassword>

# unset CLASSPATH=

echo .
echo Load Oracle SQLJ ...
loadjava -user $1@$ADEMPIERE_DB_SERVER/$ADEMPIERE_DB_NAME -verbose -force -resolve $ADEMPIERE_HOME/lib/sqlj.jar

echo .
echo Create Oracle Functions ...
sqlplus $1@$ADEMPIERE_DB_SERVER/$ADEMPIERE_DB_NAME @$ADEMPIERE_HOME/utils/oracle/createSQLJ.sql

	# <<<--- end original "old style" script
	exit 0
fi


# NEW STYLE SCRIPT STARTS HERE
# ============================

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
dbCatalog="$9"
dbSchema="${10}"
dbSeedFile="${11}"
dbSqljFile="${12}"


# make sure this script is called for the correct vendor
if [ "$dbVendor" != "oracle" ]
then
	echo "wrong vendor"
	exit $errorWrongVendor
fi

# make sure the sqlj libraries exist
if [ ! -r $dbSqljFile ]
then
	echo "The library file $dbSqljFile could not be loaded."
	echo "Please make sure it exists and you have read permissions."
	exit $errorNoSqljFile	
fi

# load oracle sqlj
echo "Load Oracle SQLJ ..."
loadjava -user $dbUser/$dbPwd@$dbHost:$dbPort/$dbName -verbose -force -resolve "$dbSqljFile"
result=$?
if [ $result -ne 0 ]
then
	echo "loadjava failed with exit code $result"
	exit $errorImportSqlj
fi

# create SQLJ functions
if [ -r $(dirname $0)/createSQLJ.sql ]
then
	echo "Create SQLJ Functions ..."
	sqlplus -S $dbUser/$dbPwd@$dbHost:$dbPort/$dbName @"$(dirname $0)/createSQLJ.sql"
	result=$?
	if [ $result -ne 0 ]
	then
		echo "create SQLJ functions failed with exit code $result"
		exit $errorImportSqlj
	fi
fi
