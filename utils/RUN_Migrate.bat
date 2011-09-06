@Title Migrate Adempiere
@Echo off

@if (%ADEMPIERE_HOME%) == () (CALL myEnvironment.bat) else (CALL %ADEMPIERE_HOME%\utils\myEnvironment.bat)

@Set JAVA=%JAVA_HOME%\bin\java

@Echo =======================================
@Echo Migrate Adempiere
@Echo =======================================
@SET CP=%ADEMPIERE_HOME%\lib\CInstall.jar;%ADEMPIERE_HOME%\lib\Adempiere.jar;%ADEMPIERE_HOME%\lib\CCTools.jar;%ADEMPIERE_HOME%\lib\oracle.jar;%ADEMPIERE_HOME%\lib\jboss.jar;%ADEMPIERE_HOME%\lib\postgresql.jar;

@"%JAVA%" -classpath %CP% -DADEMPIERE_HOME=%ADEMPIERE_HOME% org.adempiere.process.MigrationLoader
