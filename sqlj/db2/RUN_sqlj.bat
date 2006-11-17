@if (%ADEMPIERE_HOME%) == () (CALL ..\myEnvironment.bat Server) else (CALL %ADEMPIERE_HOME%\utils\myEnvironment.bat Server)
@Title Create DB2 SQLJ - %ADEMPIERE_HOME% (%ADEMPIERE_DB_NAME%)
@Rem

call create %ADEMPIERE_DB_USER%/%ADEMPIERE_DB_PASSWORD%

@pause
