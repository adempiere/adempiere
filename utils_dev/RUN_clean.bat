@Title Adempiere Clean
@Rem $Header: /cvsroot/adempiere/utils_dev/RUN_clean.bat,v 1.4 2005/09/16 00:49:29 jjanke Exp $

@CALL ..\utils\functions.bat
@Rem Set ant classpath
@SET ANT_CLASSPATH=%CLASSPATH%;..\tools\lib\ant.jar;..\tools\lib\ant-launcher.jar;..\tools\lib\ant-swing.jar;..\tools\lib\ant-commons-net.jar;..\tools\lib\commons-net-1.4.0.jar
@SET ANT_CLASSPATH=%ANT_CLASSPATH%;"%JAVA_HOME%\lib\tools.jar"

@echo Cleanup ...
@"%JAVA_HOME%\bin\java" -classpath %ANT_CLASSPATH% -Dant.home="." %ANT_PROPERTIES% org.apache.tools.ant.Main clean

IF "%CALLED_WITH_CMD_C%"=="NO"  (
@pause
@exit
)