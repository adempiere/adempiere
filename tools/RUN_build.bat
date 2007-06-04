@Title Build Tools
@Rem   $Header: /cvsroot/adempiere/tools/RUN_build.bat,v 1.16 2005/09/11 02:28:11 jjanke Exp $

@Rem Check java home
@IF NOT EXIST "%JAVA_HOME%\bin" ECHO "** JAVA_HOME NOT found"
@SET PATH=%JAVA_HOME%\bin;%PATH%

@Rem Set ant classpath
@SET ANT_CLASSPATH=%CLASSPATH%;..\tools\lib\ant.jar;..\tools\lib\ant-launcher.jar;..\tools\lib\ant-swing.jar;..\tools\lib\ant-commons-net.jar;..\tools\lib\commons-net-1.4.0.jar
@SET ANT_CLASSPATH=%ANT_CLASSPATH%;"%JAVA_HOME%\lib\tools.jar"
@SET ANT_OPTS=-Xms512m -Xmx512m

@echo Cleanup ...
@"%JAVA_HOME%\bin\java" %ANT_OPTS% -classpath %ANT_CLASSPATH% -Dant.home="." %ANT_PROPERTIES% org.apache.tools.ant.launch.Launcher clean

@echo Building ...
@"%JAVA_HOME%\bin\java" %ANT_OPTS% -classpath %ANT_CLASSPATH% -Dant.home="." %ANT_PROPERTIES% org.apache.tools.ant.launch.Launcher toolsDistribution

@Echo Done ...
@pause
@exit

:NOBUILD
@Pause