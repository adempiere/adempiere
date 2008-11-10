@echo off

rem $Id: wsrunclient.bat 3813 2008-03-31 12:03:40Z richard.opalka@jboss.com $

@if not "%ECHO%" == ""  echo %ECHO%
@if "%OS%" == "Windows_NT"  setlocal

set DIRNAME=.\
if "%OS%" == "Windows_NT" set DIRNAME=%~dp0%
set PROGNAME=run.bat
if "%OS%" == "Windows_NT" set PROGNAME=%~nx0%


if not [%1] == [] goto start
    echo %PROGNAME% is a command line tool that invokes a JBossWS JAX-WS Web Service client.
    echo It builds the correct classpath and endorsed libs for you. Feel free to use
    echo the code for this script to make your own shell scripts. It is open source
    echo after all.
    echo.
    echo usage: %PROGNAME% [-classpath ^<additional class path^>] ^<java-main-class^> [arguments...]
    goto EOF 
:start 
set ARGS=
:loop
if [%1] == [] goto endloop
    if not %1 == -classpath goto argset 
      set WSRUNCLIENT_CLASSPATH=%2
      shift 
      shift
      goto loop
    :argset
      set ARGS=%ARGS% %1 
      shift
      goto loop
:endloop

set JAVA=%JAVA_HOME%\bin\java
set JBOSS_HOME=%DIRNAME%\..
rem Setup the java endorsed dirs

rem Setup the java endorsed dirs
set JBOSS_ENDORSED_DIRS=%JBOSS_HOME%\lib\endorsed

rem Setup the client classpath
set WSRUNCLIENT_CLASSPATH=%WSRUNCLIENT_CLASSPATH%;%JBOSS_HOME%/client/log4j.jar
set WSRUNCLIENT_CLASSPATH=%WSRUNCLIENT_CLASSPATH%;%JBOSS_HOME%/client/jbossws-client.jar
set WSRUNCLIENT_CLASSPATH=%WSRUNCLIENT_CLASSPATH%;%JBOSS_HOME%/client/xmlsec.jar

rem JBossAS-5.0 subset of jbossall-client.jar
set WSRUNCLIENT_CLASSPATH=%WSRUNCLIENT_CLASSPATH%;%JBOSS_HOME%/client/jboss-logging-spi.jar
set WSRUNCLIENT_CLASSPATH=%WSRUNCLIENT_CLASSPATH%;%JBOSS_HOME%/client/jboss-common-core.jar

rem JBossAS-4.2 subset of jbossall-client.jar
set WSRUNCLIENT_CLASSPATH=%WSRUNCLIENT_CLASSPATH%;%JBOSS_HOME%/client/jboss-common-client.jar

rem Execute the JVM
"%JAVA%" %JAVA_OPTS% -Djava.endorsed.dirs="%JBOSS_ENDORSED_DIRS%" -Dlog4j.configuration=wstools-log4j.xml -classpath "%WSRUNCLIENT_CLASSPATH%" %ARGS%
:EOF
