#!/bin/bash
#
# Name:			RUN_ImportReference.sh
# Description:	script to import Reference database
# Created:		2010-05-11
# Vendor:		K.K. Alice
# Author:		Stefan Christians
#
# FileTarget:	~/development/sandbox/adempiere/utils/RUN_ImportReference.sh
# FileOwner:	spc.dvp
# FilePerms:	0755
#

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

# local variables
importAdempiereScript="RUN_ImportAdempiere.sh"

# execute the general import script
if [ -x $(dirname $0)/$importAdempiereScript ]
then
	$(dirname $0)/$importAdempiereScript "reference"
	result=$?
else
	echo
	echo "Import Reference"
	echo
	echo "The script $importAdempiereScript could not be run."
	echo "Please make sure it exists and you have execute permissions."
	echo
	result=$errorGeneral
fi

# end of script
exit $result
