#!/bin/sh

# This formats all the SQL files in the specified directory so that
# they can be executed by SQL*Plus. There are two modes -- a 'testing'
# mode (the default mode -- this stips out all the "commit" statements)
# and a commit mode for deployment. You need to pipe the output of this
# script into sqlplus, for example:
# ./migrate.sh 313-314 commit | sqlplus compiere/compiere

# Contributed by Chris Farley - northernbrewer

if [ -z "$1" ]; then
   echo "Usage: migrate DIRECTORY [commit]"
   exit 0
fi
echo "SET SQLBLANKLINES ON;"
echo "SET DEFINE OFF;"
for file in `ls $1`; do
   echo "SELECT '$file' AS Filename FROM dual;"
   cat $1/$file | sed 's/commit[ ]*;//I'
   echo
done
if [ "$2" = "commit" ]; then
   echo "COMMIT;"
else
   echo "ROLLBACK;"
fi
