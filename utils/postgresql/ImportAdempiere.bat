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
@dropuser -U postgres %ADEMPIERE_DB_USER%
@createuser -U postgres -a -d %ADEMPIERE_DB_USER%
@createdb %ADEMPIERE_DB_NAME% -E UNICODE -O %ADEMPIERE_DB_USER% -U %ADEMPIERE_DB_USER%

@echo -------------------------------------
@echo Import Adempiere_pg.dmp
@echo -------------------------------------
@psql -d %ADEMPIERE_DB_NAME% -U %ADEMPIERE_DB_USER% -f %ADEMPIERE_HOME%/data/Adempiere_pg.dmp

@goto end

:environment
@Echo Please make sure that the enviroment variables are set correctly:
@Echo		ADEMPIERE_HOME	e.g. D:\Adempiere
@Echo		ADEMPIERE_DB_NAME	e.g. dev1.adempiere.org

:usage
@echo Usage:		%0 <systemAccount> <AdempiereID> <AdempierePwd>
@echo Example:	%0 system/manager Adempiere Adempiere

:end
