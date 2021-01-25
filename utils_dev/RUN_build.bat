@Title Build Adempiere Clean
@Rem $Header: /cvsroot/adempiere/utils_dev/RUN_build.bat,v 1.22 2005/09/08 21:56:11 jjanke Exp $

@Rem Check java home
@IF NOT EXIST "%JAVA_HOME%\bin" ECHO "** JAVA_HOME NOT found"
@SET PATH="%JAVA_HOME%\bin";%PATH%

@Rem Check jdk
@IF NOT EXIST "%JAVA_HOME%\lib\tools.jar" ECHO "** Need Full Java SDK **"

@Rem Set classpath
@SET JAVA_CLASSPATH=%CLASSPATH%;..\tools\lib\ant\org.apache.ant_1.10.8.v20200515-1239\lib\ant-launcher.jar;..\tools\lib\commons-net-1.4.0.jar
@SET JAVA_CLASSPATH="%JAVA_CLASSPATH%";"%JAVA_HOME%\lib\tools.jar"
@SET ANT_HOME="..\tools\lib\ant\org.apache.ant_1.10.8.v20200515-1239"

@SET JAVA_OPTS=-Xms128m -Xmx512m

@echo Cleanup ...
@"%JAVA_HOME%\bin\java" %JAVA_OPTS% -classpath %JAVA_CLASSPATH% -Dant.home=%ANT_HOME% org.apache.tools.ant.launch.Launcher clean

@echo Building ...
@"%JAVA_HOME%\bin\java" %JAVA_OPTS% -classpath %JAVA_CLASSPATH% -Dant.home=%ANT_HOME% org.apache.tools.ant.launch.Launcher complete
@IF ERRORLEVEL 1 goto ERROR

@Echo Done ...
@Pause
@exit

:ERROR
@Color fc

@Pause