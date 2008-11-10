@echo off

rem $Id: wsprovide.bat 2885 2008-03-22 23:05:16Z richard.opalka@jboss.com $

@if not "%ECHO%" == ""  echo %ECHO%
@if "%OS%" == "Windows_NT"  setlocal

set DIRNAME=.\
if "%OS%" == "Windows_NT" set DIRNAME=%~dp0%
set PROGNAME=run.bat
if "%OS%" == "Windows_NT" set PROGNAME=%~nx0%

rem Read all command line arguments

REM
REM The %ARGS% env variable commented out in favor of using %* to include
REM all args in java command line. See bug #840239. [jpl]
REM
REM set ARGS=
REM :loop
REM if [%1] == [] goto endloop
REM         set ARGS=%ARGS% %1
REM         shift
REM         goto loop
REM :endloop

set JAVA=%JAVA_HOME%\bin\java
set JBOSS_HOME=%DIRNAME%\..
rem Setup the java endorsed dirs
set JBOSS_ENDORSED_DIRS=%JBOSS_HOME%\lib\endorsed

rem shared libs
set WSPROVIDE_CLASSPATH=%WSPROVIDE_CLASSPATH%;%JAVA_HOME%/lib/tools.jar
set WSPROVIDE_CLASSPATH=%WSPROVIDE_CLASSPATH%;%JBOSS_HOME%/client/jbossws-spi.jar
set WSPROVIDE_CLASSPATH=%WSPROVIDE_CLASSPATH%;%JBOSS_HOME%/client/activation.jar
set WSPROVIDE_CLASSPATH=%WSPROVIDE_CLASSPATH%;%JBOSS_HOME%/client/getopt.jar
set WSPROVIDE_CLASSPATH=%WSPROVIDE_CLASSPATH%;%JBOSS_HOME%/client/jbossall-client.jar
set WSPROVIDE_CLASSPATH=%WSPROVIDE_CLASSPATH%;%JBOSS_HOME%/client/log4j.jar
set WSPROVIDE_CLASSPATH=%WSPROVIDE_CLASSPATH%;%JBOSS_HOME%/client/mail.jar

rem shared jaxws libs
set WSPROVIDE_CLASSPATH=%WSPROVIDE_CLASSPATH%;%JBOSS_HOME%/client/jaxb-api.jar
set WSPROVIDE_CLASSPATH=%WSPROVIDE_CLASSPATH%;%JBOSS_HOME%/client/jaxb-impl.jar
set WSPROVIDE_CLASSPATH=%WSPROVIDE_CLASSPATH%;%JBOSS_HOME%/client/jaxb-xjc.jar
set WSPROVIDE_CLASSPATH=%WSPROVIDE_CLASSPATH%;%JBOSS_HOME%/client/jaxws-tools.jar
set WSPROVIDE_CLASSPATH=%WSPROVIDE_CLASSPATH%;%JBOSS_HOME%/client/jaxws-rt.jar
set WSPROVIDE_CLASSPATH=%WSPROVIDE_CLASSPATH%;%JBOSS_HOME%/client/streambuffer.jar
set WSPROVIDE_CLASSPATH=%WSPROVIDE_CLASSPATH%;%JBOSS_HOME%/client/stax-ex.jar

rem stack specific dependencies
if exist %JBOSS_HOME%/client/jbossws-client.jar goto else
   rem JBossWS-Metro stack libraries
   echo JBossWS-Metro stack deployed
   set WSPROVIDE_CLASSPATH=%WSPROVIDE_CLASSPATH%;%JBOSS_HOME%/client/jbossws-metro-client.jar
   set WSPROVIDE_CLASSPATH=%WSPROVIDE_CLASSPATH%;%JBOSS_HOME%/client/jaxws-api.jar
   set WSPROVIDE_CLASSPATH=%WSPROVIDE_CLASSPATH%;%JBOSS_HOME%/client/jsr181-api.jar
   set WSPROVIDE_CLASSPATH=%WSPROVIDE_CLASSPATH%;%JBOSS_HOME%/client/saaj-api.jar
   set WSPROVIDE_CLASSPATH=%WSPROVIDE_CLASSPATH%;%JBOSS_HOME%/client/saaj-impl.jar
   goto endif
:else
   rem JBossWS-Native stack libraries
   echo JBossWS-Native stack deployed
   set WSPROVIDE_CLASSPATH=%WSPROVIDE_CLASSPATH%;%JBOSS_HOME%/client/jboss-xml-binding.jar
   set WSPROVIDE_CLASSPATH=%WSPROVIDE_CLASSPATH%;%JBOSS_HOME%/client/javassist.jar
   set WSPROVIDE_CLASSPATH=%WSPROVIDE_CLASSPATH%;%JBOSS_HOME%/client/jbossall-client.jar
   set WSPROVIDE_CLASSPATH=%WSPROVIDE_CLASSPATH%;%JBOSS_HOME%/client/jbossws-client.jar
   set WSPROVIDE_CLASSPATH=%WSPROVIDE_CLASSPATH%;%JBOSS_HOME%/client/jboss-jaxws.jar
   set WSPROVIDE_CLASSPATH=%WSPROVIDE_CLASSPATH%;%JBOSS_HOME%/client/jboss-jaxrpc.jar
   set WSPROVIDE_CLASSPATH=%WSPROVIDE_CLASSPATH%;%JBOSS_HOME%/client/jboss-saaj.jar
   set WSPROVIDE_CLASSPATH=%WSPROVIDE_CLASSPATH%;%JBOSS_HOME%/client/policy.jar
   set WSPROVIDE_CLASSPATH=%WSPROVIDE_CLASSPATH%;%JBOSS_HOME%/client/wsdl4j.jar
:endif

rem Execute the JVM
"%JAVA%" %JAVA_OPTS% -Djava.endorsed.dirs="%JBOSS_ENDORSED_DIRS%" -Dlog4j.configuration=wstools-log4j.xml -classpath "%WSPROVIDE_CLASSPATH%" org.jboss.wsf.spi.tools.cmd.WSProvide %*
