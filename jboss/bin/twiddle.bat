@echo off
rem -------------------------------------------------------------------------
rem JBoss JVM Launcher
rem -------------------------------------------------------------------------

rem $Id: twiddle.bat 72204 2008-04-15 09:34:39Z dimitris@jboss.org $

if not "%ECHO%" == ""  echo %ECHO%
if "%OS%" == "Windows_NT"  setlocal

set MAIN_JAR_NAME=twiddle.jar
set MAIN_CLASS=org.jboss.console.twiddle.Twiddle

set DIRNAME=.\
if "%OS%" == "Windows_NT" set DIRNAME=%~dp0%
set PROGNAME=run.bat
if "%OS%" == "Windows_NT" set PROGNAME=%~nx0%

rem Find MAIN_JAR, or we can't continue

set MAIN_JAR=%DIRNAME%\%MAIN_JAR_NAME%
if exist "%MAIN_JAR%" goto FOUND_MAIN_JAR
echo Could not locate %MAIN_JAR%. Please check that you are in the
echo bin directory when running this script.
goto END

:FOUND_MAIN_JAR

if not "%JAVA_HOME%" == "" goto HAVE_JAVA_HOME

set JAVA=java

echo JAVA_HOME is not set.  Unexpected results may occur.
echo Set JAVA_HOME to the directory of your local JDK to avoid this message.
goto SKIP_SET_JAVA_HOME

:HAVE_JAVA_HOME

set JAVA=%JAVA_HOME%\bin\java

:SKIP_SET_JAVA_HOME

rem only include jbossall-client.jar in classpath, if
rem JBOSS_CLASSPATH was not yet set
if not "%JBOSS_CLASSPATH%" == "" GOTO HAVE_JB_CP
set JBOSS_CLASSPATH=%DIRNAME%\..\client\jbossall-client.jar
set JBOSS_CLASSPATH=%JBOSS_CLASSPATH%;%DIRNAME%\..\client\getopt.jar
set JBOSS_CLASSPATH=%JBOSS_CLASSPATH%;%DIRNAME%\..\client\log4j.jar
set JBOSS_CLASSPATH=%JBOSS_CLASSPATH%;%DIRNAME%\..\lib\jboss-jmx.jar
:HAVE_JB_CP

set JBOSS_CLASSPATH=%JBOSS_CLASSPATH%;%MAIN_JAR%

rem Setup JBoss sepecific properties
set JBOSS_HOME=%DIRNAME%\..
set JBOSS_ENDORSED_DIRS=%JBOSS_HOME%\lib\endorsed
set JAVA_OPTS=%JAVA_OPTS% -Djboss.boot.loader.name=%PROGNAME%

"%JAVA%" %JAVA_OPTS% "-Djava.endorsed.dirs=%JBOSS_ENDORSED_DIRS%" -classpath "%JBOSS_CLASSPATH%" %MAIN_CLASS% %*

:END
