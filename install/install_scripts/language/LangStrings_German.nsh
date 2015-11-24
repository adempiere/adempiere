; Language strings for ADempiere Windows installer
; Overrides of default language strings
; The English text is provided in comments and followed by the language of
; the file.

; IMPORTANT - Encode in ANSI!!

; Define the language

!ifdef _LANGUAGE_
  !undef _LANGUAGE_
!endif
!define _LANGUAGE_ ${LANG_GERMAN}

; Language strings for ADempiere Windows installer
; Overrides of default language strings
!ifdef MUI_WELCOMEPAGE
 ;WELCOME_INFO_TEXT  "Setup will guide you through the installation of $(^NameDA) and, optionally, \
 ;               the required JAVA Development Kit (JDK) and PostgreSQL Database.$\r$\n$\r$\nThe install process will install the necessary \
 ;               software, build the software for your system, import and migrate a seed database and install the Adempiere server as a windows \
 ;               service. $\r$\n$\r$\nIt is recommended that you backup any existing Adempiere installation and database before proceeding.$\r$\n$\r$\n$_CLICK"
  LangString WELCOME_INFO_TEXT ${_LANGUAGE_} "??$\r$\n$\r$\n$_CLICK"
!endif


; Adempiere Specific strings
; LocS_Admin  "You must be an administrator to proceed with the installtion."
LangString LocS_Admin ${_LANGUAGE_}  "Um die Installation fortzusetzen, müssen Sie über Administratorrechte verfügen."

; LocS_CompHeadLong  "Choose the componets to install.  Deselect JAVA and PostgreSQL if you already have these software components installed."
LangString LocS_CompHeadLong ${_LANGUAGE_}  "??"

; LocS_CompHeadShort  "Choose the components to install."
LangString LocS_CompHeadShort ${_LANGUAGE_} "??"

; LocS_DestHeadLong  "Choose the folder for the installation of $(^NameDA)."
LangString LocS_DestHeadLong ${_LANGUAGE_}  "Bitten wï¿½hlen Sie die Verzeichnisse fï¿½r die Installation von $(^NameDA)."

; LocS_DestHeadShort  "Choose Install Location"
LangString LocS_DestHeadShort ${_LANGUAGE_} "Installationsverzeichnisse wï¿½hlen"

; LocS_DestDesc  "Please select the destination directories for different components:"
;LangString LocS_DestDesc ${_LANGUAGE_}  "Bitte wï¿½hlen Sie die Zielverzeichnisse fï¿½r die Verschiedenen Komponenten:"

; LocS_Installed  "Already installed under:"
;LangString LocS_Installed ${_LANGUAGE_} "Bereits installiert unter:"


; LocS_PostgresOld  "The installed version of PostgreSQL is too old (<${PG_MIN_VERSION}). Please uninstall this version or try a manual installtion of PostgreSQL."
;LangString LocS_PostgresOld ${_LANGUAGE_} "Die installierte Version von PostgresSQL is zu alt (<${PG_MIN_VERSION}). Bitte entfernen Sie diese Version oder versuchen Sie eine manuelle Installtion von PostgreSQL."

; LocS_PGFound  "The installed version of PostgreSQL is older than the suggested version but is acceptable. Do you still want to install the suggested version?" 
LangString LocS_PGFound ${_LANGUAGE_} "??"

; LocS_JavaHome  "The enviroment variable JAVA_HOME is already set to $JAVA_HOME. It should be set to the installation folder of the JDK ($JDK_DIR). Should I correct this? (Say 'yes' if you are not sure.)"
LangString LocS_JavaHome ${_LANGUAGE_} "Die Umgebungsvariable JAVA_HOME ist bereits auf $JAVA_HOME gesetzt. Sie sollte aber auf das Installationsverzeichnis des JDK gesetzt werden ($JDK_DIR). Soll dies korrigiert werden? (Antworten Sie mit 'Ja' wenn Sie unsichern sind.)"


; LocS_AdempiereDownload  "Downloading and installing Adempiere ... Please wait, this could take a while."
LangString LocS_AdempiereDownload ${_LANGUAGE_} "?? ... Bitte warten, dies kann einen Moment dauern."

; LocS_AdempiereBuild  "Building ADempiere ... Please wait, this could take some minutes."
LangString LocS_AdempiereBuild ${_LANGUAGE_} "Erzeuge ADempiere-Umgebung ... Bitte warten, dies kann einige Minuten dauern."

; LocS_AdempiereImport1  "Importing Database ... Please wait, this could take a while."
LangString LocS_AdempiereImport1 ${_LANGUAGE_} "Importiere Datenbank ... Bitte warten, dies kann einen Moment dauern."

; LocS_AdempiereImport2  "For problems, please check the log file: $ADEMPIERE_HOME\Import.log"
LangString LocS_AdempiereImport2 ${_LANGUAGE_} "Sollten Probleme auftreten, schauen Sie bitte in die Log-Datei: $INSTDIR\Import.log"

; LocS_AdempiereMigrate1  "Migrating the Database ... Please wait, this could take a while."
LangString LocS_AdempiereMigrate1 ${_LANGUAGE_} "?? Datenbank ... Bitte warten, dies kann einen Moment dauern."

; LocS_AdempiereMigrate2  "For problems, please check the log file: $ADEMPIERE_HOME\MigrationXML.log"
LangString LocS_AdempiereMigrate2 ${_LANGUAGE_} "Sollten Probleme auftreten, schauen Sie bitte in die Log-Datei: $INSTDIR\MigrationXML.log"

; LocS_StartingService  "Installing ADempiere as a Windows service.$\r$\nPlease allow a few minutes for the server to start before launching$\r$\nthe client or accessing the web interface."
LangString LocS_StartingService ${_LANGUAGE_} "??"

; LocS_JavaFound  "A suitable version of Java is already installed.  Do you still want to install the suggested version?"
LangString LocS_JavaFound ${_LANGUAGE_} "??"

; LocS_NoJavaFound  "No suitable version of Java JDK was found.  Java JDK version ${JDK_MIN_VERSION} or higher is required.  Please reinstall with the JDK.  Aborting."
LangString LocS_NoJavaFound ${_LANGUAGE_} "??"

; LocS_JavaDownload  "Downloading Java ... Please wait, this could take a while."
LangString LocS_JavaDownload ${_LANGUAGE_} "?? ... Bitte warten, dies kann einen Moment dauern."

; LocS_JavaInstall  "Installing Java ... Please wait, this could take a while."
LangString LocS_JavaInstall ${_LANGUAGE_} "?? ... Bitte warten, dies kann einen Moment dauern."

; LocS_PGDownload  "Downloading PostgreSQL ... Please wait, this could take a while."
LangString LocS_PGDownload ${_LANGUAGE_} "?? ... Bitte warten, dies kann einen Moment dauern."

; LocS_PGInstall  "Installing PostgreSQL ... Please wait, this could take a while."
LangString LocS_PGInstall ${_LANGUAGE_} "?? ... Bitte warten, dies kann einen Moment dauern."

; LocS_AdempiereDownloadFailed  "The ADempiere download failed"
LangString LocS_AdempiereDownloadFailed ${_LANGUAGE_} "??"

; LocS_JDKDownloadFailed  "The JDK download failed"
LangString LocS_JDKDownloadFailed ${_LANGUAGE_} "??"

; LocS_PGDownloadFailed  "The PostgreSQL download failed"
LangString LocS_PGDownloadFailed ${_LANGUAGE_} "??"

; LocS_JDKIsRequired  "The installation of the JDK is not reflected in the registry.  Please verify the JDK install and setup ADempiere manually."
LangString LocS_JDKIsRequired ${_LANGUAGE_} "??"

; LocS_PGIsRequired  "The installation of PostgreSQL is not reflected in the registry.  Please verify the PostgreSQL install and setup ADempiere manually."
LangString LocS_PGIsRequired ${_LANGUAGE_} "??"

; LocS_SettingEnvironment  "Setting environment variables:"
LangString LocS_SettingEnvironment ${_LANGUAGE_} "??"

; Section Descriptions
; DESC_AD_SECTION  "${PRODUCT_NAME} Version installation."
LangString DESC_AD_SECTION ${_LANGUAGE_} "${PRODUCT_NAME} ??."

; DESC_JDK_SECTION  "Installs the Java JDK. Not required if another suitable JDK is installed."
LangString DESC_JDK_SECTION ${_LANGUAGE_} "??"

; DESC_PG_SECTION  "Install the PostgreSQL database. Not required if another PostgreSQL database is installed"
LangString DESC_PG_SECTION ${_LANGUAGE_} "??"

; LocS_ADSetupOptionsHeader  "ADempiere Setup Options"
LangString LocS_ADSetupOptionsHeader ${_LANGUAGE_} "??"

; LocS_ADSetupOptions  "Please verify or change the setup options. If using an existing database, provide the current config.  For a new installation, its recommended to keep the defaults."
LangString LocS_ADSetupOptions ${_LANGUAGE_} "??"

; LocS_PGSystemUser  "Postgres system user [$PG_USER]:"
LangString LocS_PGSystemUser ${_LANGUAGE_} "??"

; LocS_PGAdminPassword  "Postgres admin password [$PG_PASSWORD]:"
LangString LocS_PGAdminPassword ${_LANGUAGE_} "??"

; LocS_PGPort  "Postgres database port [$PG_PORT]:"
LangString LocS_PGPort ${_LANGUAGE_} "??"

; LocS_ADUserPassword  "Adempiere user password [$AD_PASSWORD]:"
LangString LocS_ADUserPassword ${_LANGUAGE_} "??"

; LocS_ShowPassword  "Show" ;Password
LangString LocS_ShowPassword ${_LANGUAGE_} "??"

; LocS_ADWebPort  "Adempiere web port [$AD_PORT]:"
LangString LocS_ADWebPort ${_LANGUAGE_} "??"

; LocS_ADSSLPort  "Adempiere SSL port [$AD_SSL_PORT]:"
LangString LocS_ADSSLPort ${_LANGUAGE_} "??"

; LocS_InstallFinishHeader  "ADempiere Install Complete"
LangString LocS_InstallFinishHeader ${_LANGUAGE_} "??"

; LocS_Install_Finished  "Adempiere has been successfully installed.  The ADempiere Server has been installed as a service and started.  Please allow a few minutes for the server to boot before lauching the client or accessing the web user interface."
LangString LocS_Install_Finished ${_LANGUAGE_} "??"

; LocS_LogginIn_Instructions  "Open instructions on logging in to ADempiere."
LangString LocS_LogginIn_Instructions ${_LANGUAGE_} "??"

; LocS_ADLoginHelpLink  "http://www.adempiere.com/Launching_the_ADempiere_Application"
LangString LocS_ADLoginHelpLink ${_LANGUAGE_} "http://www.adempiere.com/Launching_the_ADempiere_Application"

; LocS_UninstallConfirm  "Are you sure you want to completely remove $(^Name) and all of its components? The JAVA JDK and PostgreSQL will not be uninstalled. They can be uninstalled using Add/Remove Programs."
LangString LocS_UninstallConfirm ${_LANGUAGE_} "??"

; LocS_UninstallSuccess  "$(^Name) was successfully removed from your computer. The JAVA JDK and PostgreSQL installs remain.$\r$\nPlease uninstall Java and PostgreSQL manually."
LangString LocS_UninstallSuccess ${_LANGUAGE_} "??"
