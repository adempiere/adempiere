/* ADempiere Windows installer, Copyright (C) 2008  Kai Schaeffer - Schaeffer AG
 * http://www.adempiere.io
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  US
 */

; *********************************
; This NSIS script will create a windows 64-bit installer for ADempiere with 
; Java and PostgreSQL.  
; 
; NSIS, and the installers for Java and Postgres are not included in the 
; repository but are required.
; 
; Please read the Readme.txt file for more information on using this script.

; Include files
!include "Adempiere_winx64.nsh"
!include "CustomPages.nsh"

; *************************************
; Configure as a off-lne or on-line (light) install
; *************************************
; Uncomment the following line for a off-line install
;!define OFF-LINE
;!define SKIP_AD

; *********************************
; Common basic defines
; *********************************

; Product name, version and patch number are used in the .exe file name
!define PRODUCT_NAME "ADempiere"
!define PRODUCT_VERSION "380LTS"
!define PRODUCT_PATCH "2"
!define PRODUCT_PUBLISHER "ADempiere Deutschland e.V."
!define PRODUCT_WEB_SITE "http://www.adempiere.io"
!define ADEMPIERE_WIKI_INSTALL_LINK_TEXT "Adempiere Windows Installer"
!define ADEMPIERE_WIKI_INSTALL_LINK "http://www.adempiere.com/Windows_Installer"

; *********************************
; Installer defines
; Source is the unzipped Adempiere files
!define SOURCE_FILE_DIR "..\build\Adempiere"
!define TOOL_FILE_DIR ".\tools"
!define UTILS_FILE_DIR ".\utils"
!define INSTALLER_SOURCE_DIR "."
!define OUT_DIR ".\bin"

; *********************************
; Adempiere defines
; Source of web zip
!define ADEMPIERE_DOWNLOAD_LINK "https://bintray.com/artifact/download/mckayerp/ADempiere-444-Window-Install-Test/Adempiere_380LTS_2.zip"
!define ADEMPIERE_INSTALLER "Adempiere_380LTS_2.zip"
!define AD_INSTALL_DRIVE "C:"
!define ADEMPIERE_HOME "${AD_INSTALL_DRIVE}\Adempiere"
!define AD_PASSWORD "adempiere"     ; Password for Adempiere installtion
!define AD_PORT "8080"              ; Port used by the web server
!define AD_SSL_PORT "8443"          ; Port used for SSL
!define AD_SIZE 1760000             ; The required size in kb of the AD install. Need if downloading zip.

; *********************************
; JDK defines - name of the install executable and 
; the default install location
!define JDK_NAME "Java JDK 1.8 Update 60 (Win x64)"
!define JDK_DOWNLOAD_LINK "http://download.oracle.com/otn-pub/java/jdk/8u60-b27/jdk-8u60-windows-x64.exe"
!define JDK_INSTALLER "jdk-8u60-windows-x64.exe"
!define JDK_DEFAULT_DIR "$PROGRAMFILES64\Java\jdk1.8.0_60"
!define JAVA_HOME "$PROGRAMFILES64\Java\jdk1.8.0_60"
!define JDK_MIN_VERSION "1.7"
!define JDK_TARGET_VERSION "1.8"
!define JDK_SIZE 335000             ; The required size in kb of the JDK install. Need if downloading zip.

; *********************************
; PostgreSQL defines - name of the install executable and 
; the default install location
!define PG_NAME "PostgreSQL 9.4.5-1 Win x64"
!define PG_DOWNLOAD_LINK "http://get.enterprisedb.com/postgresql/postgresql-9.4.5-1-windows-x64.exe"
!define PG_INSTALLER "postgresql-9.4.5-1-windows-x64.exe"
!define PG_DEFAULT_DIR "$PROGRAMFILES64\PostgreSQL\9.4"
!define PG_SERVICE_ID "postgresql-x64-9.4"
!define PG_VERSION_DETAIL "9.4.5-1"
!define PG_USER "postgres"          ; Username of PG system account
!define PG_PASSWORD "postgres"      ; Password for PostgreSQL installtion
!define PG_PORT "5432"              ; PostgreSQL Port (5432)
!define PG_MIN_VERSION "9.0"        ; Minimun acceptable PG Version
!define PG_SIZE  465000             ; The required size in kb of the PG install. Need if downloading zip.

; Installer Attributes
Name "${PRODUCT_NAME} $PRODUCT_VERSION"
!ifdef OFF-LINE
    OutFile "${OUT_DIR}\Adempiere_${PRODUCT_VERSION}_${PRODUCT_PATCH}_windows_x64_offline.exe"
!endif
!ifndef OFF-LINE
    OutFile "${OUT_DIR}\Adempiere_${PRODUCT_VERSION}_${PRODUCT_PATCH}_windows_x64.exe"
!endif
InstallDirRegKey HKLM "${PRODUCT_DIR_REGKEY}" ""
AllowRootDirInstall true
ShowInstDetails show
ShowUnInstDetails show

; Page customizations
; MUI Settings
    !define MUI_ABORTWARNING
; Header
    !define MUI_ICON "${SOURCE_FILE_DIR}\lib\Adempiere.ico"
    !define MUI_HEADERIMAGE
    !define MUI_TEXT_WELCOME_INFO_TEXT $(WELCOME_INFO_TEXT)
; Order of pages
; Welcome page
  !insertmacro MUI_PAGE_WELCOME
  !insertmacro MUI_PAGE_LICENSE ${INSTALLER_SOURCE_DIR}\license.txt
  !insertmacro MUI_PAGE_COMPONENTS
;Add a directory page to let the user specify the Adempiere install drive\directory
;Store the folder in $AD_INSTALL_DRIVE. $ADEMPIERE_HOME will be $AD_INSTALL_DRIVE\Adempiere
  !define MUI_DIRECTORYPAGE_VARIABLE $AD_INSTALL_DRIVE  
  !insertmacro MUI_PAGE_DIRECTORY
  Page custom nsDialogsOptions1 nsDialogsOptions1Leave
  !insertmacro MUI_PAGE_INSTFILES
  Page custom installFinish installFinishLeave
  UninstPage instfiles

; Add this after all other defines
!include "Language.nsh"
 
Section "${PRODUCT_NAME} ${PRODUCT_VERSION}_${PRODUCT_PATCH}" AD_SECTION
  SectionIn RO
  StrCpy $ADEMPIERE_HOME "$AD_INSTALL_DRIVE\Adempiere"
    ;Store installation folder
  WriteRegStr HKLM "${PRODUCT_DIR_REGKEY}" "" $ADEMPIERE_HOME
  SetOutPath "$ADEMPIERE_HOME"
  
!ifndef SKIP_AD
  !ifdef OFF-LINE
      ; Extract the ADempiere files
      File /r "${SOURCE_FILE_DIR}\*.*"
  !endif
  !ifndef OFF-LINE
    ; Find and extract the zip from the URL
        ; Adempiere Download
        ;${ClearStack}
        DetailPrint ${SEPARATOR}
        DetailPrint $(LocS_AdempiereDownload)
        inetc::get /POPUP "${ADEMPIERE_INSTALLER}" \
        "${ADEMPIERE_DOWNLOAD_LINK}" "$PLUGINSDIR\${ADEMPIERE_INSTALLER}" /END
        Pop $0 # return value = exit code, "OK" means OK
        
        ${if} $0 != "OK"
            MessageBox MB_OK "$(LocS_AdempiereDownloadFailed): $0"
            abort
        ${endif}
        
        ; Extract the zip
        !insertmacro ZIPDLL_EXTRACT "$PLUGINSDIR\${ADEMPIERE_INSTALLER}" "$AD_INSTALL_DRIVE" "<ALL>"
  !endif
      
  WriteIniStr "$ADEMPIERE_HOME\${PRODUCT_NAME}.url" "InternetShortcut" "URL" "${PRODUCT_WEB_SITE}"
    
  WriteUninstaller "$ADEMPIERE_HOME\uninst.exe"
  WriteRegStr HKLM "${PRODUCT_DIR_REGKEY}" "" "$ADEMPIERE_HOME\RUN_ADempiere.bat"
  WriteRegStr ${PRODUCT_UNINST_ROOT_KEY} "${PRODUCT_UNINST_KEY}" "DisplayName" "$(^Name)"
  WriteRegStr ${PRODUCT_UNINST_ROOT_KEY} "${PRODUCT_UNINST_KEY}" "UninstallString" "$ADEMPIERE_HOME\uninst.exe"
  WriteRegStr ${PRODUCT_UNINST_ROOT_KEY} "${PRODUCT_UNINST_KEY}" "DisplayIcon" "$ADEMPIERE_HOME\lib\Adempiere.exe"
  WriteRegStr ${PRODUCT_UNINST_ROOT_KEY} "${PRODUCT_UNINST_KEY}" "DisplayVersion" "${PRODUCT_VERSION}"
  WriteRegStr ${PRODUCT_UNINST_ROOT_KEY} "${PRODUCT_UNINST_KEY}" "URLInfoAbout" "${PRODUCT_WEB_SITE}"
  WriteRegStr ${PRODUCT_UNINST_ROOT_KEY} "${PRODUCT_UNINST_KEY}" "Publisher" "${PRODUCT_PUBLISHER}"
  
  ; Create entries in the start menu
  SetShellVarContext all    
  CreateDirectory "$SMPROGRAMS\ADempiere"
  CreateShortCut "$SMPROGRAMS\ADempiere\ADempiere Client.lnk" "$ADEMPIERE_HOME\lib\Adempiere.exe" "" "$ADEMPIERE_HOME\lib\Adempiere.ico"
  CreateShortCut "$SMPROGRAMS\ADempiere\ADempiere WebUI.lnk" "http://$COMPUTER_NAME:$AD_PORT/webui" "" "$ADEMPIERE_HOME\lib\AdWWW.ico"
  CreateShortCut "$SMPROGRAMS\ADempiere\About ADempiere.lnk" "$ADEMPIERE_HOME\index.html" "" "$ADEMPIERE_HOME\lib\AdHelp.ico"
  CreateShortCut "$SMPROGRAMS\ADempiere\Website.lnk" "$ADEMPIERE_HOME\${PRODUCT_NAME}.url" "" "$ADEMPIERE_HOME\lib\AdWWW.ico"
  CreateShortCut "$SMPROGRAMS\ADempiere\Uninstall.lnk" "$ADEMPIERE_HOME\uninst.exe"
  CreateDirectory "$SMPROGRAMS\ADempiere\Server"
  CreateShortCut "$SMPROGRAMS\ADempiere\Server\ADempiere Admin.lnk" "http://$COMPUTER_NAME:$AD_PORT/admin" "" "$ADEMPIERE_HOME\lib\AdWWW.ico"
  CreateShortCut "$SMPROGRAMS\ADempiere\Server\Start ADempiere server.lnk" "$ADEMPIERE_HOME\utils\windows\Adempiere_Service_Start.bat" "" "$ADEMPIERE_HOME\lib\AdServerStart.ico"
  CreateShortCut "$SMPROGRAMS\ADempiere\Server\Stop ADempiere server.lnk" "$ADEMPIERE_HOME\utils\windows\Adempiere_Service_Stop.bat" "" "$ADEMPIERE_HOME\lib\AdServerStop.ico"
  ShellLink::SetRunAsAdministrator "$SMPROGRAMS\ADempiere\Server\Start ADempiere server.lnk"
  ShellLink::SetRunAsAdministrator "$SMPROGRAMS\ADempiere\Server\Stop ADempiere server.lnk"
  ShellLink::SetShortCutWorkingDirectory "$SMPROGRAMS\ADempiere\ADempiere Client.lnk" "$ADEMPIERE_HOME\lib"
  ;CreateShortCut "$SMPROGRAMS\ADempiere\Server\Install ADempiere server as a service.lnk" "$ADEMPIERE_HOME\utils\windows\Adempiere_Service_Install_64.bat"
  ;CreateShortCut "$SMPROGRAMS\ADempiere\Server\Remove ADempiere server service.lnk" "$ADEMPIERE_HOME\utils\windows\Adempiere_Service_Uninstall_64.bat"
!endif
SectionEnd

Section "${JDK_NAME}" JDK_SECTION
    SetOutPath $PLUGINSDIR
    !ifdef OFF-LINE
        File "${TOOL_FILE_DIR}\java\${JDK_INSTALLER}"
    !endif
    !ifndef OFF-LINE
        ; JAVA Download
        DetailPrint ${SEPARATOR}
        DetailPrint $(LocS_JavaDownload)
        inetc::get /POPUP "${JDK_NAME}" /WEAKSECURITY /NOCOOKIES /HEADER "Cookie: oraclelicense=accept-securebackup-cookie" \
        "${JDK_DOWNLOAD_LINK}" "$PLUGINSDIR\${JDK_INSTALLER}" /end        
        Pop $0 # return value = exit code, "OK" means OK
        ${if} $0 != "OK"
            MessageBox MB_OK "$(LocS_JDKDownloadFailed): $0"
            abort
        ${endif}
    !endif

    ; Install the JDK silently
    DetailPrint $(LocS_JavaInstall)
    ExecWait '"$PLUGINSDIR\${JDK_INSTALLER}" /s ADDLOCAL="ToolsFeature"'
    
    Delete "$PLUGINSDIR\${JDK_INSTALLER}"
    
    ; Check if JDK was installed
    ; TODO Stronger check required
    ReadRegStr $JDK_VERSION HKLM "Software\JavaSoft\Java Development Kit" "CurrentVersion"
    ${if} $JDK_VERSION != "1.8"
        MessageBox MB_OK "$(LocS_JDKDownloadFailed): $(LocS_JDKIsRequired)"
        abort
    ${endif}
SectionEnd

Section "${PG_NAME}" PG_SECTION
    SetOutPath $PLUGINSDIR
    !ifdef OFF-LINE
        File "${TOOL_FILE_DIR}\postgres\${PG_INSTALLER}"
    !endif
    !ifndef OFF-LINE
        ; Postgres download
        DetailPrint ${SEPARATOR}
        DetailPrint $(LocS_PGDownload)
        inetc::get /POPUP "${PG_NAME}" "${PG_DOWNLOAD_LINK}" "$PLUGINSDIR\${PG_INSTALLER}" /END
        Pop $0 # return value = exit code, "OK" means OK
        
        ${if} $0 != "OK"
            MessageBox MB_OK "$(LocS_PGDownloadFailed): $0"
            abort
        ${endif}
    !endif
    DetailPrint $(LocS_PGInstall)
	ExecWait '"$PLUGINSDIR\${PG_INSTALLER}" --mode unattended --superpassword "$PG_PASSWORD" --servicepassword "$PG_PASSWORD"'
	;/qr INTERNALLAUNCH=1 ADDLOCAL=server,psql,pgadmin,jdbc,npgsql,psqlodbc,pgoledb SERVICEDOMAIN="$COMPUTER_NAME" SERVICEACCOUNT="$PG_USER" SERVICEPASSWORD="$PG_PASSWORD" SUPERPASSWORD="$PG_PASSWORD" CREATESERVICEUSER=$PG_CREATEUSER PERMITREMOTE=1 ENCODING=UTF-8 BASEDIR="$PG_INSTDIR"
	Delete "$PLUGINSDIR\${PG_INSTALLER}"
    
    ; Check if Postgres was installed - check the registry for the specific PG Service ID
    ReadRegStr $PG_VERSION_DETAIL HKLM "SOFTWARE\PostgreSQL\Installations\${PG_SERVICE_ID}" "Version"
    ${if} $PG_VERSION_DETAIL != ${PG_VERSION_DETAIL} 
        MessageBox MB_OK "$(LocS_PGDownloadFailed): $(LocS_PGIsRequired)"
        abort
    ${endif}
    StrCpy $PG_INSTALLED_VERSION ${PG_SERVICE_ID}
    
SectionEnd


Section -AdditionalIcons
SectionEnd

Section -Environment
    ; Set the environment accordingly
    ; Set ADempiere home enviroment var
    
    SetDetailsPrint both
    DetailPrint ${SEPARATOR}
    DetailPrint $(LocS_SettingEnvironment)
    DetailPrint "  ADEMPIER_HOME = $ADEMPIERE_HOME"
    WriteRegExpandStr HKLM "SYSTEM\CurrentControlSet\Control\Session Manager\Environment" "ADEMPIERE_HOME" $ADEMPIERE_HOME
    System::Call 'Kernel32::SetEnvironmentVariableA(t, t) i("ADEMPIERE_HOME", "$ADEMPIERE_HOME").r0'

    ; Get the details of the JAVA install directory
    ; $JDK_VERSION should be set to the current or the installed version
    ReadRegStr $JDK_DIR HKLM "Software\JavaSoft\Java Development Kit\$JDK_VERSION" "JavaHome"
    ${if} $JDK_DIR == ""
        MessageBox MB_OK $(LocS_NoJavaFound)
         Abort
    ${endif}      
    ; Check JAVA_HOME environment variable. If already set to another destination we should correct it
    ReadEnvStr $JAVA_HOME JAVA_HOME
    ${if} $JAVA_HOME != $JDK_DIR
    ${andif} $JAVA_HOME != ""
        ; JAVA_HOME is different than the current JDK version.  
        ; Let the user decide if this is correct
        MessageBox MB_YESNO $(LocS_JavaHome) /SD IDYES IDNO JAVA_HOME_NOT_SET
            ; Yes - user agrees to correct JAVA_HOME to point to JDK_DIR
            StrCpy $JAVA_HOME $JDK_DIR
            ; Set JAVA_HOME on the system ...
            WriteRegExpandStr HKLM "SYSTEM\CurrentControlSet\Control\Session Manager\Environment" "JAVA_HOME" $JAVA_HOME
            ; ... and for this context
            System::Call 'Kernel32::SetEnvironmentVariableA(t, t) i("JAVA_HOME", "$JAVA_HOME").r0'
        JAVA_HOME_NOT_SET:
            ; No - do nothing and leave JAVA_HOME as it is
            ; The user is always right.
    ${else}
      ${if} $JAVA_HOME == ""
            ; JAVA_HOME is not set.  Use the current JDK as the default
            ; Don't bother asking for permission
            StrCpy $JAVA_HOME $JDK_DIR
            ; Set JAVA_HOME on the system ...
            WriteRegExpandStr HKLM "SYSTEM\CurrentControlSet\Control\Session Manager\Environment" "JAVA_HOME" $JAVA_HOME
            ; ... and for this context
            System::Call 'Kernel32::SetEnvironmentVariableA(t, t) i("JAVA_HOME", "$JAVA_HOME").r0'
      ${endif}
      ; Else in the case where JAVA_HOME == JDK_DIR nothing needs to be done
    ${endif}
    DetailPrint "  JAVA_HOME = $JAVA_HOME"

    ; Check if POSTGRES is installed. $PG_INSTALLED_VERSION should point to the current installation
    ; Remove the base directory of other installs from the path
    StrCpy $0 0
    loop:
      EnumRegKey $1 HKLM "SOFTWARE\PostgreSQL\Installations" $0
      StrCmp $1 "" done
      ReadRegStr $2 HKLM "SOFTWARE\PostgreSQL\Installations\$1" "Base Directory"
      ${EnvVarUpdate} $0 "PATH" "R" "HKLM" "$2\bin" ; remove
      IntOp $0 $0 + 1
      Goto loop
    done:
    ; Now, set the path for the correct installation
    ReadRegStr $PG_INSTDIR HKLM "SOFTWARE\PostgreSQL\Installations\$PG_INSTALLED_VERSION" "Base Directory"
    
    ${if} $PG_INSTDIR != ""
        ; Include the bin dir to the path. Is needed by some ADempiere batch files.
        ${EnvVarUpdate} $0 "PATH" "A" "HKLM" "$PG_INSTDIR\bin" ; Append
        ReadEnvStr $0 "PATH"
        StrCpy $1 "$0;$PG_INSTDIR\bin"
        System::Call 'Kernel32::SetEnvironmentVariableA(t, t) i("PATH", "$1").r0'
    ${endif}    
    DetailPrint "  PostgreSQL Path = $PG_INSTDIR\bin"
    
SectionEnd

Section -Setup
    ; Get ADempiereEnv.properties template for the silent setup
    CopyFiles /SILENT "$ADEMPIERE_HOME\AdempiereEnvTemplate.properties" "$ADEMPIERE_HOME\AdempiereEnv.properties"

    ; Set values in the properties file
    ${StrReplaceV4} $R0 "\" "\\" $ADEMPIERE_HOME
    ${StrReplaceV4} $R1 ":" "\:" $R0
    ${ConfigWrite} "$ADEMPIERE_HOME\AdempiereEnv.properties" "ADEMPIERE_HOME=" $R1 $R0
    ${StrReplaceV4} $R9 "\" "\\" $JAVA_HOME
    ${ConfigWrite} "$ADEMPIERE_HOME\AdempiereEnv.properties" "JAVA_HOME=" $R9 $R0
    ${ConfigWrite} "$ADEMPIERE_HOME\AdempiereEnv.properties" "ADEMPIERE_DB_PASSWORD=" $PG_PASSWORD $R0
    ${ConfigWrite} "$ADEMPIERE_HOME\AdempiereEnv.properties" "ADEMPIERE_DB_SYSTEM=" $PG_USER $R0
    ${ConfigWrite} "$ADEMPIERE_HOME\AdempiereEnv.properties" "ADEMPIERE_DB_PORT=" $PG_PORT $R0

    ${ConfigWrite} "$ADEMPIERE_HOME\AdempiereEnv.properties" "ADEMPIERE_WEB_PORT=" $AD_PORT $R0
    ${ConfigWrite} "$ADEMPIERE_HOME\AdempiereEnv.properties" "ADEMPIERE_SSL_PORT=" $AD_SSL_PORT $R0
    ${ConfigWrite} "$ADEMPIERE_HOME\AdempiereEnv.properties" "ADEMPIERE_APPS_SERVER=" $COMPUTER_NAME $R0

    ${ConfigWrite} "$ADEMPIERE_HOME\AdempiereEnv.properties" "ADEMPIERE_KEYSTORE=" "$R9\\keystore\\myKeystore" $R0
    ${ConfigWrite} "$ADEMPIERE_HOME\AdempiereEnv.properties" "ADEMPIERE_KEYSTOREPASS=" $PG_PASSWORD $R0
    ${ConfigWrite} "$ADEMPIERE_HOME\AdempiereEnv.properties" "ADEMPIERE_CERT_CN=" $COMPUTER_NAME $R0
    ${ConfigWrite} "$ADEMPIERE_HOME\AdempiereEnv.properties" "ADEMPIERE_CERT_COUNTRY=" "US" $R0

    CopyFiles /SILENT "$ADEMPIERE_HOME\AdempiereEnv.properties" "$ADEMPIERE_HOME\AdempiereEnv.properties.save"

    ; Now start the silent setup. The output is pushed to the function "BuildProgress"
    SetDetailsPrint both
    DetailPrint ${SEPARATOR}
    DetailPrint $(LocS_AdempiereBuild)

    StrCpy $R8 "0"
    GetFunctionAddress $0 BuildProgress
    ExecDos::exec /NOUNLOAD /TOFUNC "$ADEMPIERE_HOME\RUN_silentsetup.bat" "" $0
    Pop $0

    SetDetailsPrint listonly
    ; Could be used the determine the total number for the "EXPECTED_LINES_*" defines
    ;DetailPrint "Total lines (Build): $R8"

    ; Now start the database import. The output is pushed to the function "ImportProgress"
    DetailPrint ${SEPARATOR}
    DetailPrint $(LocS_AdempiereImport1)
    DetailPrint $(LocS_AdempiereImport2)

    StrCpy $R8 "0"
    FileOpen $R9 "$ADEMPIERE_HOME\Import.log" w
    GetFunctionAddress $0 ImportProgress
    ExecDos::exec /NOUNLOAD /TOFUNC "$ADEMPIERE_HOME\utils\RUN_ImportAdempiere.bat silent" "" $0
    Pop $0
    FileClose $R9

    SetDetailsPrint both
    ;DetailPrint "Total lines (Import): $R8"

    ; Migrate the seed import using any xml files
    DetailPrint ${SEPARATOR}
    DetailPrint $(LocS_AdempiereMigrate1)
    DetailPrint $(LocS_AdempiereMigrate2)

    SetDetailsPrint Listonly
    StrCpy $R8 "0"
    FileOpen $R9 "$ADEMPIERE_HOME\MigrationXML.log" w
    GetFunctionAddress $0 MigrateProgress
    ExecDos::exec /NOUNLOAD /TOFUNC "$ADEMPIERE_HOME\utils\RUN_migrateXML.bat silent" "" $0
    Pop $0
    FileClose $R9

    SetDetailsPrint both
    ;DetailPrint "Total lines (Migration): $R8"

    ; Install windows as a service
    DetailPrint ${SEPARATOR}
    DetailPrint $(LocS_StartingService)
    DetailPrint ${SEPARATOR}
    ExecDos::exec /NOUNLOAD "$ADEMPIERE_HOME\utils\windows\Adempiere_Service_Install_64.bat" "" "$ADEMPIERE_HOME\ServiceInstall.log"
    ExecDos::exec /NOUNLOAD "$ADEMPIERE_HOME\utils\windows\Adempiere_Service_Start.bat" "" "$ADEMPIERE_HOME\ServiceStart.log"

SectionEnd

Section "Uninstall"
  SetOutPath $INSTDIR ; Will be same as $ADEMPIERE_HOME
  
  ; delete self  
  Delete $INSTDIR\Uninst.exe 
  ; Delete any remaining files in the installation location 
  Delete "$INSTDIR\*.*"
  ; Remove the installation directory, all subdirectories and all files
  rmDir /r "$INSTDIR\*.*"  ; Can't actually remove the $INSTDIR as it is the OutPath
       
  # Remove uninstaller information from the registry
  DeleteRegKey ${PRODUCT_UNINST_ROOT_KEY} "${PRODUCT_UNINST_KEY}"
  DeleteRegKey /ifempty HKLM "${PRODUCT_DIR_REGKEY}"
  
  ; Redirect the OutPath and delete the $Instdir
  SetOutPath "c:\"
  rmDir $INSTDIR
  
  # Remove Start Menu launcher - saved in the all user start menu
  SetShellVarContext all
  delete "$SMPROGRAMS\ADempiere\ADempiere Client.lnk"
  delete "$SMPROGRAMS\ADempiere\ADempiere WebUI.lnk"
  delete "$SMPROGRAMS\ADempiere\About ADempiere.lnk"
  delete "$SMPROGRAMS\ADempiere\Website.lnk"
  delete "$SMPROGRAMS\ADempiere\Uninstall.lnk"
  delete "$SMPROGRAMS\ADempiere\Server\Start ADempiere server.lnk"
  delete "$SMPROGRAMS\ADempiere\Server\Stop ADempiere server.lnk"
  delete "$SMPROGRAMS\ADempiere\Server\ADempiere Admin.lnk"
  ;delete "$SMPROGRAMS\ADempiere\Server\Install ADempiere server as a service.lnk"
  ;delete "$SMPROGRAMS\ADempiere\Server\Remove ADempiere server service.lnk"
  # Try to remove the Start Menu folder - this will only happen if it is empty
  rmDir "$SMPROGRAMS\ADempiere\Server"
  rmDir "$SMPROGRAMS\ADempiere"
  
  ; TODO Delete the ADEMPIERE_HOME variable
  ;SetAutoClose true
SectionEnd

Function .onInit
    ; Choose the language
    !insertmacro MUI_LANGDLL_DISPLAY
    
    ;Extract InstallOptions files
    ;$PLUGINSDIR will automatically be removed when the installer closes
  
    InitPluginsDir

    ;Read Settings from .ini-File
    File /oname=$PLUGINSDIR\InstallerSettings.ini "${INSTALLER_SOURCE_DIR}\InstallerSettings.ini"
    ;File /oname=$PLUGINSDIR\Options.ini "${INSTALLER_SOURCE_DIR}\Options.ini"

    ${ConfigRead} "$PLUGINSDIR\InstallerSettings.ini" "Version=" $PRODUCT_VERSION
    IfErrors 0 +2
    strcpy $PRODUCT_VERSION ${PRODUCT_VERSION}

    ${ConfigRead} "$PLUGINSDIR\InstallerSettings.ini" "ExpectedLinesBuild=" $EXPECTED_LINES_BUILD
    IfErrors 0 +2
    strcpy $EXPECTED_LINES_BUILD "1099"
    
    ${ConfigRead} "$PLUGINSDIR\InstallerSettings.ini" "ExpectedLinesImport=" $EXPECTED_LINES_IMPORT
    IfErrors 0 +2
    strcpy $EXPECTED_LINES_IMPORT "5384"

    ${ConfigRead} "$PLUGINSDIR\InstallerSettings.ini" "ExpectedLinesMigration=" $EXPECTED_LINES_MIGRATION
    IfErrors 0 +2
    strcpy $EXPECTED_LINES_MIGRATION "5046"

    Delete "$PLUGINSDIR\InstallerSettings.ini"

    !insertmacro IsUserAdmin $0
    ; Check is User is admin
    ${if} $0 != "1"
         MessageBox MB_ICONEXCLAMATION|MB_OK $(LocS_Admin)
         Abort
    ${endif}

    SetRegView 64 ; needed to view the x64 entries in the registry
    ; Get computer name
    ReadRegStr $COMPUTER_NAME HKLM "SYSTEM\CurrentControlSet\Control\ComputerName\ComputerName" "ComputerName"

    ; Check if JDK is installed  (1.7 and 1.8 are OK).
    ReadRegStr $JDK_VERSION HKLM "Software\JavaSoft\Java Development Kit" "CurrentVersion"
    ${if} $JDK_VERSION != ""
        ${VersionCompare} ${JDK_MIN_VERSION} $JDK_VERSION $0
                    ;    $0=0  Versions are equal. Installed version is OK.
                    ;    $0=1  Installed version is too old to be used
                    ;    $0=2  Installed version is newer.
        ${if} $0 != "1"
            ; The current installed java will do the job.
            ; Turn off the JDK Section option by setting the text to ""
            SectionSetText ${JDK_SECTION} ""
            SectionSetFlags ${JDK_SECTION} 0
        ${endif}
    ${else}
        ; require the JDK install
        IntOp $0 ${SF_SELECTED} | ${SF_RO}
        SectionSetFlags ${JDK_SECTION} $0
    ${endif}
    

    ; Check if POSTGRES is installed.
    ; Find the max version
    StrCpy $0 0
    loop:
      EnumRegKey $1 HKLM "SOFTWARE\PostgreSQL\Installations" $0
      StrCmp $1 "" done
      IntOp $0 $0 + 1
      Goto loop
    done:
    ${if} $0 == 0
        StrCpy $PG_INSTALLED_VERSION ""
        StrCpy $PG_VERSION_DETAIL ""
        StrCpy $PG_INSTDIR ""
    ${else}
      IntOp $0 $0 - 1
      EnumRegKey $PG_INSTALLED_VERSION HKLM "SOFTWARE\PostgreSQL\Installations" $0
      ReadRegStr $PG_VERSION_DETAIL HKLM "SOFTWARE\PostgreSQL\Installations\$PG_INSTALLED_VERSION" "Version"
      ReadRegStr $PG_INSTDIR HKLM "SOFTWARE\PostgreSQL\Installations\$PG_INSTALLED_VERSION" "Base Directory"
    ${endif}
    
    ${if} $PG_VERSION_DETAIL != ""
        ${VersionCompare} ${PG_MIN_VERSION} $PG_VERSION_DETAIL $0
                    ;    $var=0  Versions are equal. Installed version is OK.
                    ;    $var=1  Installed version is too old to be used
                    ;    $var=2  Installed version is OK.
        ${if} $0 != "1"
            ; The current installed postgres will do the job.
            ; Turn off the PG Section option by setting the text to ""
            SectionSetText ${PG_SECTION} ""
            SectionSetFlags ${PG_SECTION} 0
        ${endif}
    ${else}
        ; require the Postgres install
        IntOp $0 ${SF_SELECTED} | ${SF_RO}
        SectionSetFlags ${PG_SECTION} $0
    ${endif}    
    
    ; Set defaults for AD installation
    StrCpy $AD_INSTALL_DRIVE ${AD_INSTALL_DRIVE}
    StrCpy $ADEMPIERE_HOME ${ADEMPIERE_HOME}
    StrCpy $AD_PASSWORD ${AD_PASSWORD}
    StrCpy $AD_PORT ${AD_PORT}    
    StrCpy $AD_SSL_PORT ${AD_SSL_PORT}
    
    ; Set defaults for PG installation
    StrCpy $PG_PASSWORD ${PG_PASSWORD}
    StrCpy $PG_USER ${PG_USER}
    StrCpy $PG_PORT ${PG_PORT}
    
    ; Set the section sizes
    SectionSetSize ${AD_Section} ${AD_Size}
    SectionSetSize ${JDK_Section} ${JDK_Size}
    SectionSetSize ${PG_Section} ${PG_Size}
    
FunctionEnd

; Helper function for ADempiere database import
; It's called for every line of the output. It writes these lines to
; a log file and updates the %-display. For newer versions the
; "EXPECTED_LINES_*" defines may be adjusted.
Function ImportProgress
	Pop $0 ; Get output line
	IntOp $R8 $R8 + 1
	FileWrite $R9 $0
	FileWriteByte $R9 "13"
    FileWriteByte $R9 "10"

	SetDetailsPrint textonly
	IntOp $1 $R8 * 100
	IntOp $1 $1 / $EXPECTED_LINES_IMPORT
	DetailPrint "Importing Database ... $1%"

FunctionEnd

; Helper function for ADempiere build 
; It's called for every line of the output. It writes these lines to
; the DetailPrint and updates the %-display. For newer versions the
; "EXPECTED_LINES_*" defines may be adjusted.
Function BuildProgress
	Pop $0
	SetDetailsPrint listonly
	DetailPrint $0
	IntOp $R8 $R8 + 1
	SetDetailsPrint textonly
	IntOp $1 $R8 * 100
	IntOp $1 $1 / $EXPECTED_LINES_BUILD
	DetailPrint "Building Adempiere ... $1%"
FunctionEnd

; Helper function for ADempiere database import
; It's called for every line of the output. It writes these lines to
; a log file and updates the %-display. For newer versions the
; "EXPECTED_LINES_*" defines may be adjusted.
Function MigrateProgress
    Pop $0 ; Get output line
    IntOp $R8 $R8 + 1
    FileWrite $R9 $0
    FileWriteByte $R9 "13"
    FileWriteByte $R9 "10"

    SetDetailsPrint textonly
    IntOp $1 $R8 * 100
    IntOp $1 $1 / $EXPECTED_LINES_MIGRATION
    DetailPrint "Migrating Database ... $1%"

FunctionEnd


Function un.onUninstSuccess
  HideWindow
  MessageBox MB_ICONINFORMATION|MB_OK $(LocS_UninstallSuccess)
FunctionEnd

Function un.onInit
  !insertmacro MUI_UNGETLANGUAGE
  ; Abort question - final chance to stop the uninstall
  MessageBox MB_ICONQUESTION|MB_YESNO|MB_DEFBUTTON2 $(LocS_UninstallConfirm) IDYES PROCEED
    Abort
  PROCEED:
    ; Load the ADEMPIERE_HOME environment variable
    SetRegView 64 ; needed to view the x64 entries in the registry
    ReadEnvStr $ADEMPIERE_HOME ADEMPIERE_HOME
    ; Stop and uninstall the Adempiere service before the files are deleted.
    ; Don't include /NOUNLOAD so the batch file can be deleted after it exits.
    ; Execute synchronously - wait for completion of the batch
    ExecDos::Exec "$ADEMPIERE_HOME\utils\windows\Adempiere_Service_Uninstall_64.bat" "" ""
FunctionEnd

!insertmacro MUI_FUNCTION_DESCRIPTION_BEGIN
  !insertmacro MUI_DESCRIPTION_TEXT ${AD_SECTION} $(DESC_AD_SECTION)
  !insertmacro MUI_DESCRIPTION_TEXT ${JDK_SECTION} $(DESC_JDK_SECTION)
  !insertmacro MUI_DESCRIPTION_TEXT ${PG_SECTION} $(DESC_PG_SECTION)
!insertmacro MUI_FUNCTION_DESCRIPTION_END

