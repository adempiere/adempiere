@Title Install ADempiere Server
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
@Echo Starting Setup ...
@Echo =======================================
@Rem Change directory. The class path is relative to %ADEMPIERE_HOME%
@Pushd %ADEMPIERE_HOME%

@SET CP=lib\CInstall.jar;lib\Adempiere.jar;lib\CCTools.jar;lib\oracle.jar;lib\jboss.jar;lib\postgresql.jar;lib\mysql-connector-java-5.1.13-bin.jar;

@Rem Trace Level Parameter, e.g. SET ARGS=ALL
@SET ARGS=CONFIG

@Rem To test the OCI driver, add -DTestOCI=Y to the command - example:
@Rem %JAVA% -classpath %CP% -DADEMPIERE_HOME=%ADEMPIERE_HOME% -DTestOCI=Y org.compiere.install.Setup %ARGS%

@"%JAVA%" -classpath %CP% -DADEMPIERE_HOME=%ADEMPIERE_HOME% org.compiere.install.SilentSetup %ARGS%
@Echo ErrorLevel = %ERRORLEVEL%
@IF NOT ERRORLEVEL = 1 GOTO NEXT
@Echo ***************************************
@Echo Check the error message above.
@Echo ***************************************
@Echo Make sure that the environment is set correctly!
@Echo Set environment variable JAVA_HOME manually
@Echo or use WinEnv.js in the util directory
@Echo ***************************************
@Rem Wait 10 second
@PING 1.1.1.1 -n 1 -w 10000 > NUL
@Rem Return to the calling directory
@Popd
@Exit


:NEXT

cd utils

@Rem ===================================
@Rem Sign Database Build
@Rem ===================================
@Call RUN_SignDatabaseBuild.bat > NUL 2>&1

@Rem ===================================
@Rem Setup ADempiere Environment
@Rem ===================================
@Call RUN_WinEnv.bat

@Echo .
@Echo For problems, check log file in base directory
@Rem Wait 10 second
@PING 1.1.1.1 -n 1 -w 10000 > NUL
@Rem - don't return to the calling directory