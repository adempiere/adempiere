#!/bin/sh

# This formats all the SQL files in the specified directory so that
# they can be executed by SQL*Plus. There are two modes -- a 'testing'
# mode (the default mode -- this strips out all the "commit" statements)
# and a commit mode for deployment. You need to pipe the output of this
# script into sqlplus, for example:
# ./migrate.sh 313-314 commit | sqlplus adempiere/adempiere

# Contributed by Chris Farley - northernbrewer

if [ -z "$1" ]; then
   echo "Usage: $0 DIRECTORY [commit]"
   exit 0
fi
echo "SET SQLBLANKLINES ON"
echo "SET DEFINE OFF"
echo "SPOOL `basename $1`"
for file in $1/*.sql; do
   echo "SELECT '`basename $file`' AS Filename FROM dual;"
   cat $file | dos2unix | sed 's/commit[ ]*;//I'
   echo
done
if [ -d $1/../processes_post_migration ]
then
   for file in $1/../processes_post_migration/*.sql; do
      echo "SELECT '`basename $file`' AS Filename FROM dual;"
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