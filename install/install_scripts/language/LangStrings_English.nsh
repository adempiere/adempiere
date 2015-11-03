; Language strings for ADempiere Windows installer
; Overrides of default language strings
; The English text is provided in comments and followed by the language of
; the file.

; Define the language

!ifdef _LANGUAGE_
  !undef _LANGUAGE_
!endif
!define _LANGUAGE_ ${LANG_ENGLISH}

; Lanugage Strings

!ifdef MUI_WELCOMEPAGE
  LangString WELCOME_INFO_TEXT ${_LANGUAGE_} "Setup will guide you through the installation of $(^NameDA) and, optionally, \
                the required JAVA Development Kit (JDK) and PostgreSQL Database.$\r$\n$\r$\nThe install process will install the necessary \
                software, build the software for your system, import and migrate a seed database and install the Adempiere server as a windows \
                service. $\r$\n$\r$\nIt is recommended that you backup any existing Adempiere installation and database before proceeding.$\r$\n$\r$\n$_CLICK"
!endif


; Adempiere Specific strings
LangString LocS_Admin ${_LANGUAGE_} "You must be an administrator to proceed with the installtion."

LangString LocS_CompHeadLong ${_LANGUAGE_} "Choose the componets to install.  Deselect JAVA and PostgreSQL if you already have these software components installed."

LangString LocS_CompHeadShort ${_LANGUAGE_} "Choose the components to install."

LangString LocS_DestHeadLong ${_LANGUAGE_} "Choose the folder for the installation of $(^NameDA)."

LangString LocS_DestHeadShort ${_LANGUAGE_} "Choose Install Location"

;LangString LocS_DestDesc ${_LANGUAGE_} "Please select the destination directories for different components:"

;LangString LocS_Installed ${_LANGUAGE_} "Already installed under:"

;LangString LocS_PostgresOld ${_LANGUAGE_} "The installed version of PostgreSQL is too old (<${PG_MIN_VERSION}). Please uninstall this version or try a manual installtion of PostgreSQL."

LangString LocS_PGFound ${_LANGUAGE_} "The installed version of PostgreSQL is older than the suggested version but is acceptable. Do you still want to install the suggested version?" 

LangString LocS_JavaHome ${_LANGUAGE_} "The enviroment variable JAVA_HOME is already set to $JAVA_HOME. It should be set to the installation folder of the JDK ($JDK_DIR). Should I correct this? (Say 'yes' if you are not sure.)"

LangString LocS_AdempiereDownload ${_LANGUAGE_} "Downloading and installing Adempiere ... Please wait, this could take a while."

LangString LocS_AdempiereBuild ${_LANGUAGE_} "Building ADempiere ... Please wait, this could take some minutes."

LangString LocS_AdempiereImport1 ${_LANGUAGE_} "Importing Database ... Please wait, this could take a while."

LangString LocS_AdempiereImport2 ${_LANGUAGE_} "For problems, please check the log file: $ADEMPIERE_HOME\Import.log"

LangString LocS_AdempiereMigrate1 ${_LANGUAGE_} "Migrating the Database ... Please wait, this could take a while."

LangString LocS_AdempiereMigrate2 ${_LANGUAGE_} "For problems, please check the log file: $ADEMPIERE_HOME\MigrationXML.log"

LangString LocS_StartingService ${_LANGUAGE_} "Installing ADempiere as a Windows service.$\r$\nPlease allow a few minutes for the server to start before launching$\r$\nthe client or accessing the web interface."

LangString LocS_JavaFound ${_LANGUAGE_} "A suitable version of Java is already installed.  Do you still want to install the suggested version?"

LangString LocS_NoJavaFound ${_LANGUAGE_} "No suitable version of Java JDK was found.  Java JDK version ${JDK_MIN_VERSION} or higher is required.  Please reinstall with the JDK.  Aborting."

LangString LocS_JavaDownload ${_LANGUAGE_} "Downloading Java ... Please wait, this could take a while."

LangString LocS_JavaInstall ${_LANGUAGE_} "Installing Java ... Please wait, this could take a while."

LangString LocS_PGDownload ${_LANGUAGE_} "Downloading PostgreSQL ... Please wait, this could take a while."

LangString LocS_PGInstall ${_LANGUAGE_} "Installing PostgreSQL ... Please wait, this could take a while."

LangString LocS_AdempiereDownloadFailed ${_LANGUAGE_} "The ADempiere download failed"

LangString LocS_JDKDownloadFailed ${_LANGUAGE_} "The JDK download failed"

LangString LocS_PGDownloadFailed ${_LANGUAGE_} "The PostgreSQL download failed"

LangString LocS_JDKIsRequired ${_LANGUAGE_} "The installation of the JDK is not reflected in the registry.  Please verify the JDK install and setup ADempiere manually."

LangString LocS_PGIsRequired ${_LANGUAGE_} "The installation of PostgreSQL is not reflected in the registry.  Please verify the PostgreSQL install and setup ADempiere manually."

LangString LocS_SettingEnvironment ${_LANGUAGE_} "Setting environment variables:"

; Section Descriptions
LangString DESC_AD_SECTION ${_LANGUAGE_} "${PRODUCT_NAME} Version installation."

LangString DESC_JDK_SECTION ${_LANGUAGE_} "Installs the Java JDK. Not required if another suitable JDK is installed."

LangString DESC_PG_SECTION ${_LANGUAGE_} "Install the PostgreSQL database. Not required if another PostgreSQL database is installed"

LangString LocS_ADSetupOptionsHeader ${_LANGUAGE_} "ADempiere Setup Options"

LangString LocS_ADSetupOptions ${_LANGUAGE_} "Please verify or change the setup options. If using an existing database, provide the current config.  For a new installation, its recommended to keep the defaults."

LangString LocS_PGSystemUser ${_LANGUAGE_} "Postgres system user [$PG_USER]:"

LangString LocS_PGAdminPassword ${_LANGUAGE_} "Postgres admin password [$PG_PASSWORD]:"

LangString LocS_PGPort ${_LANGUAGE_} "Postgres database port [$PG_PORT]:"

LangString LocS_ADUserPassword ${_LANGUAGE_} "Adempiere user password [$AD_PASSWORD]:"

LangString LocS_ShowPassword ${_LANGUAGE_} "Show" ;Password
 
LangString LocS_ADWebPort ${_LANGUAGE_} "Adempiere web port [$AD_PORT]:"

LangString LocS_ADSSLPort ${_LANGUAGE_} "Adempiere SSL port [$AD_SSL_PORT]:"

LangString LocS_InstallFinishHeader ${_LANGUAGE_} "ADempiere Install Complete"

LangString LocS_Install_Finished ${_LANGUAGE_} "Adempiere has been successfully installed.  The ADempiere Server has been installed as a service and started.  Please allow a few minutes for the server to boot before launching the client or accessing the web user interface."

LangString LocS_LogginIn_Instructions ${_LANGUAGE_} "Open instructions on logging in to ADempiere."

LangString LocS_ADLoginHelpLink ${_LANGUAGE_} "http://www.adempiere.com/Launching_the_ADempiere_Application"

LangString LocS_UninstallConfirm ${_LANGUAGE_} "Are you sure you want to completely remove $(^Name) and all of its components? The JAVA JDK and PostgreSQL will not be uninstalled. They can be uninstalled using Add/Remove Programs."

LangString LocS_UninstallSuccess ${_LANGUAGE_} "$(^Name) was successfully removed from your computer. The JAVA JDK and PostgreSQL installs remain.$\r$\nPlease uninstall Java and PostgreSQL manually."
