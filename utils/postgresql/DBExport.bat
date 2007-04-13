@Echo	ADEMPIERE Database Export 	$Revision: 1.2 $

@Rem $Id: DBExport.bat,v 1.2 2005/01/22 21:59:15 jjanke Exp $

@Echo Saving database %1@%ADEMPIERE_DB_NAME% to %ADEMPIERE_HOME%\data\ExpDat.dmp

@if (%ADEMPIERE_HOME%) == () goto environment
@if (%ADEMPIERE_DB_NAME%) == () goto environment
@Rem Must have parameter: userAccount
@if (%1) == () goto usage

pg_dump -U %1 %ADEMPIERE_DB_NAME% > %ADEMPIERE_HOME%\data\ExpDat.dmp

@cd %ADEMPIERE_HOME%\Data
@jar cvfM ExpDat.jar ExpDat.dmp

@goto end

:environment
@Echo Please make sure that the enviroment variables are set correctly:
@Echo		ADEMPIERE_HOME	e.g. D:\ADEMPIERE2
@Echo		ADEMPIERE_DB_NAME 	e.g. ADEMPIERE.ADEMPIERE.org

:usage
@echo Usage:		%0 <userAccount>
@echo Examples:	%0 ADEMPIERE/ADEMPIERE

:end
