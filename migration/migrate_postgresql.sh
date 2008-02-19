#!/bin/sh

# This formats all the SQL files in the specified directory so that
# they can be executed by psql. There are two modes -- a 'testing'
# mode (the default mode -- this strips out all the "commit" statements)
# and a commit mode for deployment. You need to pipe the output of this
# script into sqlplus, for example:
# ./migrate.sh 313-314 commit | psql -U adempiere -d adempiere > 313-314.lst

# Original contribution by by Chris Farley - northernbrewer
# Adapted to postgresql by Carlos Ruiz - globalqss

if [ -z "$1" ]; then
   echo "Usage: $0 DIRECTORY [commit]"
   exit 0
fi
for file in $1/postgresql/*.sql; do
   echo "SELECT '`basename $file`' AS Filename;"
   cat $file | dos2unix | sed 's/commit[ ]*;//I'
   echo
done
if [ -d $1/../processes_post_migration/postgresql ]
then
   for file in $1/../processes_post_migration/postgresql/*.sql; do
      echo "SELECT '`basename $file`' AS Filename;"
      cat $file | dos2unix | sed 's/commit[ ]*;//I'
      echo
   done
fi
if [ "$2" = "commit" ]; then
   echo "COMMIT;"
else
   echo "ROLLBACK;"
fi
echo
echo "quit"
