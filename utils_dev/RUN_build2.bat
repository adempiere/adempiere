@Title Build Adempiere Clean
@Rem $Header: /cvsroot/adempiere/utils_dev/RUN_build.bat,v 1.22 2005/09/08 21:56:11 jjanke Exp $

@Rem Check java home
@IF NOT EXIST "%JAVA_HOME%\bin" goto javahome
@SET PATH="%JAVA_HOME%\bin";%PATH%

@Rem Check jdk
@IF NOT EXIST "%JAVA_HOME%\lib\tools.jar" goto jdk

@if (%1) == () goto usage

@Rem Set ant classpath
@SET ANT_CLASSPATH=%CLASSPATH%;..\tools\lib\ant.jar;..\tools\lib\ant-launcher.jar;..\tools\lib\ant-swing.jar;..\tools\lib\ant-commons-net.jar;..\tools\lib\commons-net-1.4.0.jar
@SET ANT_CLASSPATH=%ANT_CLASSPATH%;"%JAVA_HOME%\lib\tools.jar"

@SET ANT_OPTS=-Xms128m -Xmx512m

@echo Building ...
@"%JAVA_HOME%\bin\java" %ANT_OPTS% -classpath %ANT_CLASSPATH% -Dant.home="." org.apache.tools.ant.Main %1
@IF ERRORLEVEL 1 goto ERROR

@Echo Done ...
@Pause
@exit

:usage
@echo Usage:    %0 target
@echo Examples: %0 complete
@pause
@exit

:javahome
@echo JAVA_HOME is not set.
@echo You may not be able to build Adempiere
@echo Set JAVA_HOME to the directory of your local JDK.
@pause
@exit

:jdk
@echo "** Need full Java SDK **"
@pause
@exit

:ERROR
@Color fc

@Pause