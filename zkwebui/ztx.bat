@echo off
rem ZK Default Theme Extractor Utility
rem ==================================
rem This batch file extracts the default theme from zk library files.
rem Specifically, it will extract all the stylesheets and images for styling 
rem zk components from zul.jar, zkex.jar, and zkmax.jar. The extracted
rem files will be packaged into a folder and archived into a jar file.
rem The resulting jar file contained the default theme resource and it formed
rem the basis for a new theme.

rem Validating the parameters
if "%1"=="" goto USAGE
if "%2"=="" goto USAGE
set ztx_lib_path=%1
set ztx_theme_name=%2
set ztx_oldcwd=%CD%

echo zxt_lib_path=%ztx_lib_path%
echo zxt_theme_name=%ztx_theme_name%
echo zxt_oldcwd=%ztx_oldcwd%

rem Normalize the zk library file names
C:
if not exist ztx_temp (
  mkdir \ztx_temp
)
chdir \ztx_temp
if exist %ztx_lib_path%\zul.jar (
	copy /b /y %ztx_lib_path%\zul.jar zul.jar
) else (
	copy /b /y %ztx_lib_path%\zul-*.jar zul.jar	
)
if exist %ztx_lib_path%\zkex.jar (
	copy /b /y %ztx_lib_path%\zkex.jar zkex.jar
) else (
	copy /b /y %ztx_lib_path%\zkex-*.jar zkex.jar
)
if exist %ztx_lib_path%\zkmax.jar (
	copy /b /y %ztx_lib_path%\zkmax.jar zkmax.jar
) else (
	copy /b /y %ztx_lib_path%\zkmax-*.jar zkmax.jar
)

rem Extract default theme resource files
if exist zul.jar (
	jar tf zul.jar | findstr ".less" > zul.list
	jar tf zul.jar | findstr ".css.dsp" > zul.list
	jar tf zul.jar | findstr ".gif" >> zul.list
	jar tf zul.jar | findstr ".png" >> zul.list
	jar xf zul.jar @zul.list
)
if exist zkex.jar (
	jar tf zkex.jar | findstr ".less" > zkex.list
	jar tf zkex.jar | findstr ".css.dsp" > zkex.list
	jar tf zkex.jar | findstr ".gif" >> zkex.list
	jar tf zkex.jar | findstr ".png" >> zkex.list
	jar xf zkex.jar @zkex.list
)
if exist zkmax.jar (
	jar tf zkmax.jar | findstr ".less" > zkmax.list
	jar tf zkmax.jar | findstr ".css.dsp" > zkmax.list
	jar tf zkmax.jar | findstr ".gif" >> zkmax.list
	jar tf zkmax.jar | findstr ".png" >> zkmax.list
	jar tf zkmax.jar | findstr "default.theme-properties" >> zkmax.list
	jar xf zkmax.jar @zkmax.list	
)
move web %ztx_theme_name%
jar cMf %ztx_theme_name%.jar %ztx_theme_name%

:CLEANUP
move %ztx_theme_name%.jar %ztx_oldcwd%
chdir %ztx_oldcwd%
rmdir /s /q c:\ztx_temp
set ztx_lib_path=
set ztx_theme_name=
set ztx_oldcwd=
GOTO END

:USAGE
echo Usage: ztx lib_path theme_name
echo   lib_path The path where zul.jar, zkex.jar, and/or zkmax.jar are located
echo   theme_name The name of the new theme based on the default theme (breeze)
:END
