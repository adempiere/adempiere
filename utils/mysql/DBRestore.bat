@Echo	ADempiere MySQL Database Import

@Echo	Importing ADempiere DB from %ADEMPIERE_HOME%\data\ExpDat.dmp (%ADEMPIERE_DB_NAME%)

@if (%ADEMPIERE_HOME%) == () goto environment
@if (%ADEMPIERE_DB_NAME%) == () goto environment
@if (%ADEMPIERE_DB_SERVER%) == () goto environment
@if (%ADEMPIERE_DB_PORT%) == () goto environment
@Rem Must have parameters systemAccount AdempiereID AdempierePwd mysqlPwd
@if (%1) == () goto usage
@if (%2) == () goto usage
@if (%3) == () goto usage
@if (%4) == () goto usage

@echo -------------------------------------
@echo Re-Create user and database
@echo -------------------------------------
@SET      MYSQL="mysql -h %ADEMPIERE_DB_SERVER% -P %ADEMPIERE_DB_PORT% -u %2 --password=%3 %ADEMPIERE_DB_NAME%"
@SET MYSQL_ROOT="mysql -h %ADEMPIERE_DB_SERVER% -P %ADEMPIERE_DB_PORT% -u root mysql"

@REM drop database
@echo Drop database %ADEMPIERE_DB_NAME%
%MYSQL_ROOT% --skip-column-names -Be "SET FOREIGN_KEY_CHECKS = 0; DROP DATABASE IF EXISTS %ADEMPIERE_DB_NAME%;"

@REM drop user
@echo Drop user %2
%MYSQL_ROOT% --skip-column-names -Be "DROP USER %2;"

@REM create user
@echo Create user %2
@REM PRIVILEGES=SELECT,INSERT,UPDATE,DELETE,CREATE,DROP
@REM PRIVILEGES=ALL PRIVILEGES
%MYSQL_ROOT% --skip-column-names -Be "CREATE USER '%2'@'localhost' IDENTIFIED BY '%3';"
%MYSQL_ROOT% --skip-column-names -Be "GRANT ALL PRIVILEGES ON %ADEMPIERE_DB_NAME%.* TO '%2'@'localhost';"
%MYSQL_ROOT% --skip-column-names -Be "GRANT SUPER ON *.* TO '%2'@'localhost';"
%MYSQL_ROOT% --skip-column-names -Be "GRANT SELECT ON mysql.proc TO '%2'@'localhost';"

%MYSQL_ROOT% --skip-column-names -Be "CREATE USER '%2'@'%' IDENTIFIED BY '%3';"
%MYSQL_ROOT% --skip-column-names -Be "GRANT ALL PRIVILEGES ON %ADEMPIERE_DB_NAME%.* TO '%2'@'%';"
%MYSQL_ROOT% --skip-column-names -Be "GRANT SUPER ON *.* TO '%2'@'%';"
%MYSQL_ROOT% --skip-column-names -Be "GRANT SELECT ON mysql.proc TO '%2'@'%';"

@REM create DB
@echo Create DB %ADEMPIERE_DB_NAME%
%MYSQL_ROOT% --skip-column-names -Be "CREATE DATABASE %ADEMPIERE_DB_NAME% DEFAULT CHARACTER SET = utf8 DEFAULT COLLATE = utf8_bin;"

@echo -------------------------------------
@echo Import ExpDat.dmp
@echo -------------------------------------
%MYSQL% < %ADEMPIERE_HOME%\data\ExpDat.dmp
@set MYSQL_ROOT=
@set MYSQL=

@goto end

:environment
@Echo Please make sure that the environment variables are set correctly:
@Echo		ADEMPIERE_HOME	e.g. D:\ADEMPIERE2
@Echo		ADEMPIERE_DB_NAME 	e.g. adempiere or xe
@Echo		ADEMPIERE_DB_SERVER 	e.g. dbserver.adempiere.org
@Echo		ADEMPIERE_DB_PORT 	e.g. 5432 or 1521

:usage
@echo Usage:	%0 <systemAccount> <AdempiereID> <AdempierePwd> <mysqlPwd>
@echo Example:	%0 root adempiere adempiere mysqlPwd

:end
