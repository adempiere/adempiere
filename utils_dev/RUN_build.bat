@Title Build Adempiere Clean
@Rem $Header: /cvsroot/adempiere/utils_dev/RUN_build.bat,v 1.22 2005/09/08 21:56:11 jjanke Exp $
@Rem Modified by Horacio Miranda, hmiranda@prolinux.cl, 2021-10-30

@REM CALLED_WITH_CMD_C [YES|NO]


@CALL ..\utils\functions.bat

@SET JAVA_OPTS=-Xms512m -Xmx512m

@echo Building ...
echo %JAVA_HOME%
@"%JAVA_HOME%\bin\java" %JAVA_OPTS% -classpath %JAVA_CLASSPATH% -Dant.home=%ANT_HOME% org.apache.tools.ant.launch.Launcher
@IF ERRORLEVEL 1 goto ERROR

@Echo Done ...
@IF "%CALLED_WITH_CMD_C%"=="NO"  (
  @pause
  @exit
)

:ERROR
@Color FC

:RETRY
@pause
@color
