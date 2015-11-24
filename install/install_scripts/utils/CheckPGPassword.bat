@Rem $Id: CheckPGPassword.bat, 2008-11-21 - Schaeffer AG - Kai Schaeffer  $

@if (%1) == () goto usage
@if (%2) == () goto usage

@set PGPASSWORD=%2
@psql -U %1 -c "select 'OK'" > NUL 2> NUL
@set PGPASSWORD=
@if ERRORLEVEL 1 GOTO error
@goto end 


:usage
@echo Usage:		%0 <PostgresAccount> <PostgresPwd>
@echo Example:	%0 postgres adempiere

:error 
@echo Error
@exit /B 1

:end
@echo OK
@exit /B 0
