@Echo	Adempiere Full Database Export 	$Revision: 1.2 $

@Rem $Id: DBExportFull.bat,v 1.2 2005/01/22 21:59:15 jjanke Exp $

@Echo Saving database %1@%ADEMPIERE_DB_NAME% to %ADEMPIERE_HOME%\data\ExpDatFull.dmp

@if (%ADEMPIERE_HOME%) == () goto environment
@if (%ADEMPIERE_DB_NAME%) == () goto environment
@Rem Must have parameter: systemAccount
@if (%1) == () goto usage

@exp %1@%ADEMPIERE_DB_NAME% FILE=%ADEMPIERE_HOME%\data\ExpDatFull.dmp Log=%ADEMPIERE_HOME%\data\ExpDat.log CONSISTENT=Y FULL=Y

@cd %ADEMPIERE_HOME%\data
@jar cvfM data\ExpDatFull.jar ExpDatFull.dmp

@goto end

:environment
@Echo Please make sure that the enviroment variables are set correctly:
@Echo		ADEMPIERE_HOME	e.g. D:\Adempiere
@Echo		ADEMPIERE_DB_NAME	e.g. dev1.adempiere.org

:usage
@echo Usage:		%0 <systemAccount>
@echo Examples:	%0 system/manager

:end
