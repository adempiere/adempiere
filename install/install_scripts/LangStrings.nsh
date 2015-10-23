; Language Selection Dialog Settings
!define MUI_LANGDLL_REGISTRY_ROOT "${PRODUCT_UNINST_ROOT_KEY}"
!define MUI_LANGDLL_REGISTRY_KEY "${PRODUCT_UNINST_KEY}"
!define MUI_LANGDLL_REGISTRY_VALUENAME "NSIS:Language"

; Language files
!insertmacro MUI_LANGUAGE "English"
!insertmacro MUI_LANGUAGE "German"

; Language strings for ADempiere Windows installer
; Overrides of default language strings
!ifdef MUI_WELCOMEPAGE
  LangString WELCOME_INFO_TEXT ${LANG_ENGLISH} "Setup will guide you through the installation of $(^NameDA) and, optionally, \
                the required JAVA Development Kit (JDK) and PostgreSQL Database.$\r$\n$\r$\nThe install process will install the necessary \
                software, build the software for your system, import and migrate a seed database and install the Adempiere server as a windows \
                service. $\r$\n$\r$\nIt is recommended that you backup any existing Adempiere installation and database before proceeding.$\r$\n$\r$\n$_CLICK"
  LangString WELCOME_INFO_TEXT ${LANG_GERMAN} "??$\r$\n$\r$\n$_CLICK"
!endif


; Adempiere Specific strings
LangString LocS_Admin ${LANG_GERMAN}  "Sie m�ssen �ber Administratorrechte verf�gen um mit der Installation fortfahren zu k�nnen."
LangString LocS_Admin ${LANG_ENGLISH} "You must be an administrator to proceed with the installtion."

LangString LocS_CompHeadLong ${LANG_ENGLISH} "Choose the componets to install.  Deselect JAVA and PostgreSQL if you already have these software components installed."
LangString LocS_CompHeadLong ${LANG_GERMAN}  "??"

LangString LocS_CompHeadShort ${LANG_ENGLISH} "Choose the components to install."
LangString LocS_CompHeadShort ${LANG_GERMAN} "??"

LangString LocS_DestHeadLong ${LANG_ENGLISH} "Choose the folders for the installation of $(^NameDA)."
LangString LocS_DestHeadLong ${LANG_GERMAN}  "Bitten w�hlen Sie die Verzeichnisse f�r die Installation von $(^NameDA)."

LangString LocS_DestHeadShort ${LANG_ENGLISH} "Choose Install Location"
LangString LocS_DestHeadShort ${LANG_GERMAN} "Installationsverzeichnisse w�hlen"

LangString LocS_DestDesc ${LANG_ENGLISH} "Please select the destination directories for different components:"
LangString LocS_DestDesc ${LANG_GERMAN}  "Bitte w�hlen Sie die Zielverzeichnisse f�r die Verschiedenen Komponenten:"

LangString LocS_Installed ${LANG_ENGLISH} "Already installed under:"
LangString LocS_Installed ${LANG_GERMAN} "Bereits installiert unter:"


LangString LocS_PostgresOld ${LANG_ENGLISH} "The installed version of PostgreSQL is too old (<${PG_MIN_VERSION}). Please uninstall this version or try a manual installtion of PostgreSQL."
LangString LocS_PostgresOld ${LANG_GERMAN} "Die installierte Version von PostgresSQL is zu alt (<${PG_MIN_VERSION}). Bitte entfernen Sie diese Version oder versuchen Sie eine manuelle Installtion von PostgreSQL."

LangString LocS_PGFound ${LANG_ENGLISH} "The installed version of PostgreSQL is older than the suggested version but is acceptable. Do you still want to install the suggested version?" 
LangString LocS_PGFound ${LANG_GERMAN} "??"

LangString LocS_JavaHome ${LANG_ENGLISH} "The enviroment variable JAVA_HOME is already set to $JAVA_HOME. It should be set to the installation folder of the JDK ($JDK_DIR). Should I correct this? (Say 'yes' if you are not sure.)"
LangString LocS_JavaHome ${LANG_GERMAN} "Die Umgebungsvariable JAVA_HOME ist bereits auf $JAVA_HOME gesetzt. Sie sollte aber auf das Installationsverzeichnis des JDK gesetzt werden ($JDK_DIR). Soll dies korrigiert werden? (Antworten Sie mit 'Ja' wenn Sie unsichern sind.)"


LangString LocS_AdempiereDownload ${LANG_ENGLISH} "Downloading and installing Adempiere ... Please wait, this could take a while."
LangString LocS_AdempiereDownload ${LANG_GERMAN} "?? ... Bitte warten, dies kann einen Moment dauern."

LangString LocS_AdempiereBuild ${LANG_ENGLISH} "Building ADempiere ... Please wait, this could take some minutes."
LangString LocS_AdempiereBuild ${LANG_GERMAN} "Erzeuge ADempiere-Umgebung ... Bitte warten, dies kann einige Minuten dauern."


LangString LocS_AdempiereImport1 ${LANG_ENGLISH} "Importing Database ... Please wait, this could take a while."
LangString LocS_AdempiereImport1 ${LANG_GERMAN} "Importiere Datenbank ... Bitte warten, dies kann einen Moment dauern."

LangString LocS_AdempiereImport2 ${LANG_ENGLISH} "For problems, please check the log file: $ADEMPIERE_HOME\Import.log"
LangString LocS_AdempiereImport2 ${LANG_GERMAN} "Sollten Probleme auftreten, schauen Sie bitte in die Log-Datei: $INSTDIR\Import.log"

LangString LocS_AdempiereMigrate1 ${LANG_ENGLISH} "Migrating the Database ... Please wait, this could take a while."
LangString LocS_AdempiereMigrate1 ${LANG_GERMAN} "?? Datenbank ... Bitte warten, dies kann einen Moment dauern."

LangString LocS_AdempiereMigrate2 ${LANG_ENGLISH} "For problems, please check the log file: $ADEMPIERE_HOME\MigrationXML.log"
LangString LocS_AdempiereMigrate2 ${LANG_GERMAN} "Sollten Probleme auftreten, schauen Sie bitte in die Log-Datei: $INSTDIR\MigrationXML.log"

LangString LocS_StartingService ${LANG_ENGLISH} "Installing ADempiere as a Windows service.$\r$\nPlease allow a few minutes for the server to start before launching$\r$\nthe client or accessing the web interface."
LangString LocS_StartingService ${LANG_GERMAN} "??"

LangString LocS_JavaFound ${LANG_ENGLISH} "A suitable version of Java is already installed.  Do you still want to install the suggested version?"
LangString LocS_JavaFound ${LANG_GERMAN} "??"

LangString LocS_NoJavaFound ${LANG_ENGLISH} "No suitable version of Java JDK was found.  Java JDK version ${JDK_MIN_VERSION} or higher is required.  Please reinstall with the JDK.  Aborting."
LangString LocS_NoJavaFound ${LANG_GERMAN} "??"

LangString LocS_JavaDownload ${LANG_ENGLISH} "Downloading Java ... Please wait, this could take a while."
LangString LocS_JavaDownload ${LANG_GERMAN} "?? ... Bitte warten, dies kann einen Moment dauern."

LangString LocS_JavaInstall ${LANG_ENGLISH} "Installing Java ... Please wait, this could take a while."
LangString LocS_JavaInstall ${LANG_GERMAN} "?? ... Bitte warten, dies kann einen Moment dauern."

LangString LocS_PGDownload ${LANG_ENGLISH} "Downloading PostgreSQL ... Please wait, this could take a while."
LangString LocS_PGDownload ${LANG_GERMAN} "?? ... Bitte warten, dies kann einen Moment dauern."

LangString LocS_PGInstall ${LANG_ENGLISH} "Installing PostgreSQL ... Please wait, this could take a while."
LangString LocS_PGInstall ${LANG_GERMAN} "?? ... Bitte warten, dies kann einen Moment dauern."

LangString LocS_AdempiereDownloadFailed ${LANG_ENGLISH} "The ADempiere download failed"
LangString LocS_AdempiereDownloadFailed ${LANG_GERMAN} "??"

LangString LocS_JDKDownloadFailed ${LANG_ENGLISH} "The JDK download failed"
LangString LocS_JDKDownloadFailed ${LANG_GERMAN} "??"

LangString LocS_PGDownloadFailed ${LANG_ENGLISH} "The PostgreSQL download failed"
LangString LocS_PGDownloadFailed ${LANG_GERMAN} "??"

LangString LocS_JDKIsRequired ${LANG_ENGLISH} "The installation of the JDK is not reflected in the registry.  Please verify the JDK install and setup ADempiere manually."
LangString LocS_JDKIsRequired ${LANG_GERMAN} "??"

LangString LocS_PGIsRequired ${LANG_ENGLISH} "The installation of PostgreSQL is not reflected in the registry.  Please verify the PostgreSQL install and setup ADempiere manually."
LangString LocS_PGIsRequired ${LANG_GERMAN} "??"

LangString LocS_SettingEnvironment ${LANG_ENGLISH} "Setting environment variables:"
LangString LocS_SettingEnvironment ${LANG_GERMAN} "??"

; Section Descriptions
LangString DESC_AD_SECTION ${LANG_ENGLISH} "${PRODUCT_NAME} Version installation."
LangString DESC_AD_SECTION ${LANG_GERMAN} "${PRODUCT_NAME} ??."

LangString DESC_JDK_SECTION ${LANG_ENGLISH} "Installs the Java JDK. Not required if another suitable JDK is installed."
LangString DESC_JDK_SECTION ${LANG_GERMAN} "??"

LangString DESC_PG_SECTION ${LANG_ENGLISH} "Install the PostgreSQL database. Not required if another PostgreSQL database is installed"
LangString DESC_PG_SECTION ${LANG_GERMAN} "??"

LangString LocS_ADSetupOptionsHeader ${LANG_ENGLISH} "ADempiere Setup Options"
LangString LocS_ADSetupOptionsHeader ${LANG_GERMAN} "??"

LangString LocS_ADSetupOptions ${LANG_ENGLISH} "Please verify or change the setup options. If using an existing database, provide the current config.  For a new installation, its recommended to keep the defaults."
LangString LocS_ADSetupOptions ${LANG_GERMAN} "??"

LangString LocS_PGSystemUser ${LANG_ENGLISH} "Postgres system user [$PG_USER]:"
LangString LocS_PGSystemUser ${LANG_GERMAN} "??"

LangString LocS_PGAdminPassword ${LANG_ENGLISH} "Postgres admin password [$PG_PASSWORD]:"
LangString LocS_PGAdminPassword ${LANG_GERMAN} "??"

LangString LocS_PGPort ${LANG_ENGLISH} "Postgres database port [$PG_PORT]:"
LangString LocS_PGPort ${LANG_GERMAN} "??"

LangString LocS_ADUserPassword ${LANG_ENGLISH} "Adempiere user password [$AD_PASSWORD]:"
LangString LocS_ADUserPassword ${LANG_GERMAN} "??"

LangString LocS_ShowPassword ${LANG_ENGLISH} "Show password"
LangString LocS_ShowPassword ${LANG_GERMAN} "??"

LangString LocS_ADWebPort ${LANG_ENGLISH} "Adempiere web port [$AD_PORT]:"
LangString LocS_ADWebPort ${LANG_GERMAN} "??"

LangString LocS_ADSSLPort ${LANG_ENGLISH} "Adempiere SSL port [$AD_SSL_PORT]:"
LangString LocS_ADSSLPort ${LANG_GERMAN} "??"

LangString LocS_InstallFinishHeader ${LANG_ENGLISH} "ADempiere Install Complete"
LangString LocS_InstallFinishHeader ${LANG_GERMAN} "??"

LangString LocS_Install_Finished ${LANG_ENGLISH} "Adempiere has been successfully installed.  The ADempiere Server has been installed as a service and started.  Please allow a few minutes for the server to boot before lauching the client or accessing the web user interface."
LangString LocS_Install_Finished ${LANG_GERMAN} "??"

LangString LocS_LogginIn_Instructions ${LANG_ENGLISH} "Open instructions on logging in to ADempiere."
LangString LocS_LogginIn_Instructions ${LANG_GERMAN} "??"

LangString LocS_ADLoginHelpLink ${LANG_ENGLISH} "http://www.adempiere.com/Launching_the_ADempiere_Application"
LangString LocS_ADLoginHelpLink ${LANG_GERMAN} "http://www.adempiere.com/Launching_the_ADempiere_Application"

LangString LocS_UninstallConfirm ${LANG_ENGLISH} "Are you sure you want to completely remove $(^Name) and all of its components? The JAVA JDK and PostgreSQL will not be uninstalled. They can be uninstalled using Add/Remove Programs."
LangString LocS_UninstallConfirm ${LANG_GERMAN} "??"

LangString LocS_UninstallSuccess ${LANG_ENGLISH} "$(^Name) was successfully removed from your computer. The JAVA JDK and PostgreSQL installs remain.$\r$\nPlease uninstall Java and PostgreSQL manually."
LangString LocS_UninstallSuccess ${LANG_GERMAN} "??"

;LangString LocS_ ${LANG_ENGLISH}
;LangString LocS_ ${LANG_GERMAN}
