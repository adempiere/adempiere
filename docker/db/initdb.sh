#!/bin/bash
if [ "$( psql -U postgres -tAc "SELECT 1 FROM pg_database WHERE datname='$DB_NAME'" )" != '1' ]
then
	cd /docker-entrypoint-initdb.d/
    createuser -U postgres adempiere -d -s -l
    echo "adempiere user Created"
    psql -U postgres -tAc "alter user adempiere password '$ADEMPERE_PASSWORD';"
    echo "password for adempiere user created"
    createdb -U adempiere $DB_NAME
    echo "Database $DB_NAME created"
    psql -U adempiere -d $DB_NAME < Adempiere_pg.dmp
    echo "Database $DB_NAME restored"
fi