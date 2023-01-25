@Title Build Adempiere Clean
@Rem $Header: /cvsroot/adempiere/utils_dev/RUN_build.bat,v 1.22 2005/09/08 21:56:11 jjanke Exp $

@CALL ..\utils\functions.bat

@if (%1) == () goto usage

@SET JAVA_OPTS=-Xms512m -Xmx512m

@echo Cleanup ...
@"%JAVA_HOME%\bin\java" %JAVA_OPTS% -classpath %JAVA_CLASSPATH% -Dant.home=%ANT_HOME% org.apache.tools.ant.launch.Launcher %1
@IF ERRORLEVEL 1 goto ERROR

@Echo Done ...
@GOTO DONE

:usage
@echo Usage:    %0 target
@echo Examples: %0 complete
@GOTO RETRY

:DONE
@pause
@exit

:ERROR
@color FC

:RETRY
@pause
@color
