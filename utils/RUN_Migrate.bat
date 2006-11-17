@Rem $Id: RUN_Migrate.bat,v 1.13 2006/01/23 04:56:03 jjanke Exp $

@if (%ADEMPIERE_HOME%) == () (CALL myEnvironment.bat Server) else (CALL %ADEMPIERE_HOME%\utils\myEnvironment.bat Server)
@Title Adempiere Version Migration - %ADEMPIERE_HOME% (%ADEMPIERE_DB_NAME%)

@Echo Version Migration is an optional service for a fee.
@Echo Please check http://www.adempiere.org/migrate/

@REM	Optional Set Source/Reference Database
@REM	This requires that RUN_ImportReference was completed
@REM	The default is:
@REM SET SOURCEDB=%ADEMPIERE_DB_URL% reference reference

@REM	Optionally Set Target Database
@REM	If you set the target database, the Source Database need to be set too!!
@REM	If not, the default connection will be used:
@REM SET TARGETDB=%ADEMPIERE_DB_URL% adempiere adempiere

@Rem	Example connecting to database user adempiere/adempierePw in instance dev2 on server dev2server 
@REM SET TARGETDB=jdbc:oracle:thin:@//dev2server:1521:dev2.adempiere.org adempiere adempierePw

@echo -------------------------------------
@echo Start UI
@echo -------------------------------------
@"%ADEMPIERE_JAVA%" %ADEMPIERE_JAVA_OPTIONS% -cp %CLASSPATH% com.adempiere.client.StartMaintain %SOURCEDB% %TARGETDB%

@echo -------------------------------------
@echo Create SQLJ 
@echo -------------------------------------
@call %ADEMPIERE_HOME%\Utils\%ADEMPIERE_DB_PATH%\create %ADEMPIERE_DB_USER%/%ADEMPIERE_DB_PASSWORD%

@echo -------------------------------------
@echo Check System
@echo -------------------------------------
@sqlplus %ADEMPIERE_DB_USER%/%ADEMPIERE_DB_PASSWORD%@%ADEMPIERE_DB_NAME% @%ADEMPIERE_HOME%\Utils\%ADEMPIERE_DB_PATH%\AfterImport.sql


@pause


