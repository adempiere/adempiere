#!/bin/sh

echo	ADempiere MySQL Database Import

echo	Importing ADempiere DB from $ADEMPIERE_HOME/data/Adempiere_mysql.dmp 

if [ $# -le 2 ] 
  then
    echo "Usage:	$0 <systemAccount> <AdempiereID> <AdempierePWD> <MySQLPwd>"
    echo "Example:	$0 root adempiere adempiere mysqlPwd"
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

echo -------------------------------------
echo Recreate user and database
echo ADEMPIERE_DB_SERVER = $ADEMPIERE_DB_SERVER
echo -------------------------------------
     MYSQL="mysql -h $ADEMPIERE_DB_SERVER -P $ADEMPIERE_DB_PORT -u $2 --password=$3 $ADEMPIERE_DB_NAME"
MYSQL_ROOT="mysql -h $ADEMPIERE_DB_SERVER -P $ADEMPIERE_DB_PORT -u root mysql"

# drop database
echo Drop database $ADEMPIERE_DB_NAME
$MYSQL_ROOT --skip-column-names -Be "SET FOREIGN_KEY_CHECKS = 0; DROP DATABASE IF EXISTS $ADEMPIERE_DB_NAME;"

# drop user
echo Drop user $2
$MYSQL_ROOT --skip-column-names -Be "DROP USER $2;"

# create user
echo Create user $2
# PRIVILEGES=SELECT,INSERT,UPDATE,DELETE,CREATE,DROP
# PRIVILEGES=ALL PRIVILEGES
$MYSQL_ROOT --skip-column-names -Be "CREATE USER '$2'@'localhost' IDENTIFIED BY '$3';"
$MYSQL_ROOT --skip-column-names -Be "GRANT ALL PRIVILEGES ON $ADEMPIERE_DB_NAME.* TO '$2'@'localhost';"
$MYSQL_ROOT --skip-column-names -Be "GRANT SUPER ON *.* TO '$2'@'localhost';"
$MYSQL_ROOT --skip-column-names -Be "GRANT SELECT ON mysql.proc TO '$2'@'localhost';"

$MYSQL_ROOT --skip-column-names -Be "CREATE USER '$2'@'%' IDENTIFIED BY '$3';"
$MYSQL_ROOT --skip-column-names -Be "GRANT ALL PRIVILEGES ON $ADEMPIERE_DB_NAME.* TO '$2'@'%';"
$MYSQL_ROOT --skip-column-names -Be "GRANT SUPER ON *.* TO '$2'@'%';"
$MYSQL_ROOT --skip-column-names -Be "GRANT SELECT ON mysql.proc TO '$2'@'%';"

# create DB
echo Create DB $ADEMPIERE_DB_NAME
$MYSQL_ROOT --skip-column-names -Be "CREATE DATABASE $ADEMPIERE_DB_NAME DEFAULT CHARACTER SET = utf8 DEFAULT COLLATE = utf8_bin;"

echo -------------------------------------
echo Import Adempiere_mysql.dmp
echo -------------------------------------
$MYSQL < $ADEMPIERE_HOME/data/Adempiere_mysql.dmp

MYSQL_ROOT=
MYSQL=
