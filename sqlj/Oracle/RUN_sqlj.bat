@if (%ADEMPIERE_HOME%) == () (CALL ..\myEnvironment.bat Server) else (CALL %ADEMPIERE_HOME%\utils\myEnvironment.bat Server)
@Title Create Oracle SQLJ - %ADEMPIERE_HOME% (%ADEMPIERE_DB_NAME%)
@Rem	
@Rem	Author + Copyright 1999-2005 Jorg Janke
@Rem	$Id: RUN_sqlj.bat,v 1.1 2005/04/27 05:21:46 jjanke Exp $
@Rem

call create %ADEMPIERE_DB_USER%/%ADEMPIERE_DB_PASSWORD%

@pause
