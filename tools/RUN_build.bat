@Title Build Tools
@Rem   $Header: /cvsroot/adempiere/tools/RUN_build.bat,v 1.16 2005/09/11 02:28:11 jjanke Exp $

@Rem Check java home
@IF NOT EXIST "%JAVA_HOME%\bin" goto JAVAHOME
@SET PATH=%JAVA_HOME%\bin;%PATH%

@Rem Check jdk
@IF NOT EXIST "%JAVA_HOME%\lib\tools.jar" goto JDK

@Rem Set classpath
@SET ANT_HOME=..\tools\lib\ant\apache-ant-1.10.10
@SET JAVA_CLASSPATH=%CLASSPATH%;%ANT_HOME%\lib\ant-launcher.jar;..\tools\lib\commons-net-1.4.0.jar
@SET JAVA_CLASSPATH="%JAVA_CLASSPATH%";"%JAVA_HOME%\lib\tools.jar"

@SET JAVA_OPTS=-Xms128m -Xmx512m

@echo Cleanup ...
@"%JAVA_HOME%\bin\java" %JAVA_OPTS% -classpath %JAVA_CLASSPATH% -Dant.home=%ANT_HOME% org.apache.tools.ant.launch.Launcher clean
@IF ERRORLEVEL 1 goto ERROR

@echo Building toolsDistribution ...
@"%JAVA_HOME%\bin\java" %JAVA_OPTS% -classpath %JAVA_CLASSPATH% -Dant.home=%ANT_HOME% org.apache.tools.ant.launch.Launcher toolsDistribution
@IF ERRORLEVEL 1 goto ERROR

@echo Done ...
@goto DONE

:JAVAHOME
@echo JAVA_HOME is not set.
@echo You may not be able to build Adempiere
@echo Set JAVA_HOME to the directory of your local JDK.
@goto DONE

:JDK
@echo "** Need full Java SDK **"
goto DONE

:DONE
@pause
@exit

:ERROR
@color FC

:RETRY
@pause
@color
