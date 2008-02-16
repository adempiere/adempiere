@Title Install Adempiere Server
@Rem  $Header: /cvsroot/adempiere/install/Adempiere/RUN_setup.bat,v 1.19 2005/09/08 21:54:12 jjanke Exp $
@Echo off


@if not "%JAVA_HOME%" == "" goto JAVA_HOME_OK
@Set JAVA=java
@Echo JAVA_HOME is not set.  
@Echo You may not be able to start the required Setup window !!
@Echo Set JAVA_HOME to the directory of your local 1.5 JDK.
@Echo If you experience problems, run utils/WinEnv.js
@Echo Example: cscript utils\WinEnv.js C:\Adempiere "C:\Program Files\Java\jdk1.5.0_04"
goto START

:JAVA_HOME_OK
@Set JAVA=%JAVA_HOME%\bin\java


:START
@Echo =======================================
@Echo Sign Database Build
@Echo =======================================
@SET CP=%ADEMPIERE_HOME%\lib\CInstall.jar;%ADEMPIERE_HOME%\lib\Adempiere.jar;%ADEMPIERE_HOME%\lib\CCTools.jar;%ADEMPIERE_HOME%\lib\oracle.jar;%ADEMPIERE_HOME%\lib\fyracle.jar;%ADEMPIERE_HOME%\lib\derby.jar;%ADEMPIERE_HOME%\lib\jboss.jar;%ADEMPIERE_HOME%\lib\postgresql.jar;

@"%JAVA%" -classpath %CP% -DADEMPIERE_HOME=%ADEMPIERE_HOME% org.adempiere.process.SignDatabaseBuild