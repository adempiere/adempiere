@Echo	Adempiere Database Import		$Revision: 1.3 $

@Rem $Id: ImportAdempiere.bat,v 1.3 2005/01/22 21:59:15 jjanke Exp $

@Echo	Importing Adempiere DB from %ADEMPIERE_HOME%\data\Adempiere.dmp (%ADEMPIERE_DB_NAME%)

@if (%ADEMPIERE_HOME%) == () goto environment
@if (%ADEMPIERE_DB_NAME%) == () goto environment
@Rem Must have parameters systemAccount AdempiereID AdempierePwd
@if (%1) == () goto usage
@if (%2) == () goto usage
@if (%3) == () goto usage

@echo -------------------------------------
@echo Re-Create user and database
@echo -------------------------------------
@dropdb -U postgres %ADEMPIERE_DB_NAME%
@dropuser -U postgres %2
@set ADEMPIERE_CREATE_ROLE_SQL=CREATE ROLE %2 SUPERUSER LOGIN PASSWORD '%3'
@psql -U postgres -c "%ADEMPIERE_CREATE_ROLE_SQL%"
@set ADEMPIERE_CREATE_ROLE_SQL=

PGPASSWORD=%3
@createdb %ADEMPIERE_DB_NAME% -E UNICODE -O %2 -U %2

@echo -------------------------------------
@echo Import Adempiere_pg.dmp
@echo -------------------------------------
@psql -d %ADEMPIERE_DB_NAME% -U %2 -c "drop schema sqlj cascade"
@psql -d %ADEMPIERE_DB_NAME% -U %2 -f %ADEMPIERE_HOME%/data/Adempiere_pg.dmp

PGPASSWORD=
@goto end

:environment
@Echo Please make sure that the enviroment variables are set correctly:
@Echo		ADEMPIERE_HOME	e.g. D:\Adempiere
@Echo		ADEMPIERE_DB_NAME	e.g. dev1.adempiere.org

:usage
@echo Usage:		%0 <systemAccount> <AdempiereID> <AdempierePwd>
@echo Example:	%0 postgres Adempiere Adempiere

:end
