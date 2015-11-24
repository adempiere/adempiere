; Language strings for ADempiere Windows installer
; Overrides of default language strings
; The English text is provided in comments and followed by the language of
; the file.

; Important! Encode as ANSI!!

; Define the language

!ifdef _LANGUAGE_
  !undef _LANGUAGE_
!endif
!define _LANGUAGE_ ${LANG_FRENCH}

; Language strings for ADempiere Windows installer
; Overrides of default language strings
!ifdef MUI_WELCOMEPAGE
;WELCOME_INFO_TEXT  "Setup will guide you through the installation of $(^NameDA) and, optionally, \
;                the required JAVA Development Kit (JDK) and PostgreSQL Database.$\r$\n$\r$\nThe install process will install the necessary \
;                software, build the software for your system, import and migrate a seed database and install the Adempiere server as a windows \
;                service. $\r$\n$\r$\nIt is recommended that you backup any existing Adempiere installation and database before proceeding.$\r$\n$\r$\n$_CLICK"
  LangString WELCOME_INFO_TEXT ${_LANGUAGE_} "Le programme d'installation vous guidera � travers l'installation de $(^ NameDA) et, �ventuellement, \
                le Kit au D�veloppement JAVA ( JDK ) n�cessaire et la base de donn�es PostgreSQL. $\r$\n$\r$\nLe processus d'installation va installer le n�cessaire \
                logiciel, la construction du logiciel pour votre syst�me, l'importation et migrer la base de donn�es de semences et d'installer le serveur Adempiere comme Service au Windows. \
                $\r$\n$\r$\nIl est recommand� de sauvegarder toute installation existante de Adempiere et de la base de donn�es avant de proc�der.$\r$\n$\r$\n$_CLICK"
!endif


; Adempiere Specific strings
; LocS_Admin  "You must be an administrator to proceed with the installtion."
LangString LocS_Admin ${_LANGUAGE_}  "Vous devez �tre un administrateur pour proc�der � l' installtion."

; LocS_CompHeadLong  "Choose the componets to install.  Deselect JAVA and PostgreSQL if you already have these software components installed."
LangString LocS_CompHeadLong ${_LANGUAGE_}  "Choisissez les composants � installer. D�s�lectionner JAVA et PostgreSQL si vous avez d�j� ces composants logiciels install�s."

; LocS_CompHeadShort  "Choose the components to install."
LangString LocS_CompHeadShort ${_LANGUAGE_} "Choisissez les composants � installer."

; LocS_DestHeadLong  "Choose the folder for the installation of $(^NameDA)."
LangString LocS_DestHeadLong ${_LANGUAGE_}  "Choisissez le dossier pour l'installation de $(^NameDA)."

; LocS_DestHeadShort  "Choose Install Location"
LangString LocS_DestHeadShort ${_LANGUAGE_} "Choisissez l'emplacement d'installation"

; LocS_DestDesc  "Please select the destination directories for different components:"
;LangString LocS_DestDesc ${_LANGUAGE_}  ""

; LocS_Installed  "Already installed under:"
;LangString LocS_Installed ${_LANGUAGE_} ""

; LocS_PostgresOld  "The installed version of PostgreSQL is too old (<${PG_MIN_VERSION}). Please uninstall this version or try a manual installtion of PostgreSQL."
;LangString LocS_PostgresOld ${_LANGUAGE_} "Die installierte Version von PostgresSQL is zu alt (<${PG_MIN_VERSION}). Bitte entfernen Sie diese Version oder versuchen Sie eine manuelle Installtion von PostgreSQL."

; LocS_PGFound  "The installed version of PostgreSQL is older than the suggested version but is acceptable. Do you still want to install the suggested version?" 
LangString LocS_PGFound ${_LANGUAGE_} "La version install�e de PostgreSQL est plus ancienne que la version sugg�r�e mais est acceptable. Voulez-vous toujours d'installer la version propos�e?"

; LocS_JavaHome  "The enviroment variable JAVA_HOME is already set to $JAVA_HOME. It should be set to the installation folder of the JDK ($JDK_DIR). Should I correct this? (Say 'yes' if you are not sure.)"
LangString LocS_JavaHome ${_LANGUAGE_} "La variable d'environnement JAVA_HOME est d�j� d�fini comme �tant $JAVA_HOME. Il doit �tre r�gl� au dossier d'installation du JDK ($JDK_DIR) . Devrais-je corriger cela? (Dites �oui� si vous n'�tes pas s�r .)"


; LocS_AdempiereDownload  "Downloading and installing Adempiere ... Please wait, this could take a while."
LangString LocS_AdempiereDownload ${_LANGUAGE_} "T�l�chargement et installation d'Adempiere ... S'il vous pla�t patienter, cela pourrait prendre certain temps."

; LocS_AdempiereBuild  "Building ADempiere ... Please wait, this could take some minutes."
LangString LocS_AdempiereBuild ${_LANGUAGE_} "D�velopper ADempiere ... S'il vous pla�t patienter, cela pourrait prendre certain temps."

; LocS_AdempiereImport1  "Importing Database ... Please wait, this could take a while."
LangString LocS_AdempiereImport1 ${_LANGUAGE_} "Importation de base de donn�es ... S'il vous pla�t patienter, cela pourrait prendre certain temps."

; LocS_AdempiereImport2  "For problems, please check the log file: $ADEMPIERE_HOME\Import.log"
LangString LocS_AdempiereImport2 ${_LANGUAGE_} "Pour les probl�mes , s'il vous pla�t v�rifier le fichier: $ADEMPIERE_HOME\Import.log"

; LocS_AdempiereMigrate1  "Migrating the Database ... Please wait, this could take a while."
LangString LocS_AdempiereMigrate1 ${_LANGUAGE_} "Mettre � niveau le base de donn�es,  ... S'il vous pla�t patienter, cela pourrait prendre certain temps."

; LocS_AdempiereMigrate2  "For problems, please check the log file: $ADEMPIERE_HOME\MigrationXML.log"
LangString LocS_AdempiereMigrate2 ${_LANGUAGE_} "Pour les probl�mes , s'il vous pla�t v�rifier le fichier: $ADEMPIERE_HOME\MigrationXML.log"

; LocS_StartingService  "Installing ADempiere as a Windows service.$\r$\nPlease allow a few minutes for the server to start before launching$\r$\nthe client or accessing the web interface."
LangString LocS_StartingService ${_LANGUAGE_} "Installation ADempiere tant que Service Windows.$\r$\nS'il vous pla�t permettre le serveur quelques minutes pour commencer avant de lancer$\r$\nle client ou d'acc�der � l'interface web."

; LocS_JavaFound  "A suitable version of Java is already installed.  Do you still want to install the suggested version?"
LangString LocS_JavaFound ${_LANGUAGE_} "Une version appropri�e de Java est d�j� install� . Voulez-vous toujours d'installer la version propos�e?"

; LocS_NoJavaFound  "No suitable version of Java JDK was found.  Java JDK version ${JDK_MIN_VERSION} or higher is required.  Please reinstall with the JDK.  Aborting."
LangString LocS_NoJavaFound ${_LANGUAGE_} "Aucune version appropri�e de Java JDK a �t� trouv� . Java JDK Version ${JDK_MIN_VERSION} ou sup�rieure est n�cessaire. S'il vous pla�t r�installer avec le JDK. Abandonnant."

; LocS_JavaDownload  "Downloading Java ... Please wait, this could take a while."
LangString LocS_JavaDownload ${_LANGUAGE_} "T�l�chargement Java... S'il vous pla�t patienter, cela pourrait prendre un certain temps."

; LocS_JavaInstall  "Installing Java... Please wait, this could take a while."
LangString LocS_JavaInstall ${_LANGUAGE_} "Installation Java... S'il vous pla�t patienter, cela pourrait prendre un certain temps."

; LocS_PGDownload  "Downloading PostgreSQL... Please wait, this could take a while."
LangString LocS_PGDownload ${_LANGUAGE_} "T�l�chargement PostgreSQL ... S'il vous pla�t patienter, cela pourrait prendre un certain temps."

; LocS_PGInstall  "Installing PostgreSQL... Please wait, this could take a while."
LangString LocS_PGInstall ${_LANGUAGE_} "Installation PostgreSQL... S'il vous pla�t patienter, cela pourrait prendre un certain temps."

; LocS_AdempiereDownloadFailed  "The ADempiere download failed"
LangString LocS_AdempiereDownloadFailed ${_LANGUAGE_} "Le t�l�chargement ADempiere a �chou�"

; LocS_JDKDownloadFailed  "The JDK download failed"
LangString LocS_JDKDownloadFailed ${_LANGUAGE_} "Le t�l�chargement JDK a �chou�"

; LocS_PGDownloadFailed  "The PostgreSQL download failed"
LangString LocS_PGDownloadFailed ${_LANGUAGE_} "Le t�l�chargement PostgreSQL a �chou�"

; LocS_JDKIsRequired  "The installation of the JDK is not reflected in the registry.  Please verify the JDK install and setup ADempiere manually."
LangString LocS_JDKIsRequired ${_LANGUAGE_} "L'installation du JDK ne se refl�te pas dans le Registre. S'il vous pla�t v�rifier le installation JDK et configurer ADempiere manuellement."

; LocS_PGIsRequired  "The installation of PostgreSQL is not reflected in the registry.  Please verify the PostgreSQL install and setup ADempiere manually."
LangString LocS_PGIsRequired ${_LANGUAGE_} "L'installation du PostgreSQL ne se refl�te pas dans le Registre. S'il vous pla�t v�rifier le installation PostgreSQL et configurer ADempiere manuellement."

; LocS_SettingEnvironment  "Setting environment variables:"
LangString LocS_SettingEnvironment ${_LANGUAGE_} "D�finition des variables d' environnement:"

; Section Descriptions
; DESC_AD_SECTION  "${PRODUCT_NAME} Version installation."
LangString DESC_AD_SECTION ${_LANGUAGE_} "Installation version ${PRODUCT_NAME}."

; DESC_JDK_SECTION  "Installs the Java JDK. Not required if another suitable JDK is installed."
LangString DESC_JDK_SECTION ${_LANGUAGE_} "Installe le JDK Java. Non requis si un autre version JDK appropri� est install�."

; DESC_PG_SECTION  "Install the PostgreSQL database. Not required if another PostgreSQL database is installed"
LangString DESC_PG_SECTION ${_LANGUAGE_} "Installe le PostgreSQL. Non requis si un autre version PostgreSQL appropri� est install� ."

; LocS_ADSetupOptionsHeader  "ADempiere Setup Options"
LangString LocS_ADSetupOptionsHeader ${_LANGUAGE_} "Options de configuration ADempiere"

; LocS_ADSetupOptions  "Please verify or change the setup options. If using an existing database, provide the current config.  For a new installation, its recommended to keep the defaults."
LangString LocS_ADSetupOptions ${_LANGUAGE_} "S'il vous pla�t v�rifier ou modifier les options de configuration. Si vous utilisez une base de donn�es existante, fournira la configuration actuelle. Pour une nouvelle installation, son recommand� de conserver les param�tres par d�faut."

; LocS_PGSystemUser  "Postgres system user [$PG_USER]:"
LangString LocS_PGSystemUser ${_LANGUAGE_} "Utilisateur du syst�me Postgres [$PG_USER]:"

; LocS_PGAdminPassword  "Postgres admin password [$PG_PASSWORD]:"
LangString LocS_PGAdminPassword ${_LANGUAGE_} "Mot de passe admin Postgres [$PG_PASSWORD]:"

; LocS_PGPort  "Postgres database port [$PG_PORT]:"
LangString LocS_PGPort ${_LANGUAGE_} "Le port Postgres [$PG_PORT]:"

; LocS_ADUserPassword  "Adempiere user password [$AD_PASSWORD]:"
LangString LocS_ADUserPassword ${_LANGUAGE_} "Mot de passe de l'utilisateur [$AD_PASSWORD]:"

; LocS_ShowPassword  "Show"
LangString LocS_ShowPassword ${_LANGUAGE_} "Montrer"

; LocS_ADWebPort  "Adempiere web port [$AD_PORT]:"
LangString LocS_ADWebPort ${_LANGUAGE_} "Port web ADempiere [$AD_PORT]:"

; LocS_ADSSLPort  "Adempiere SSL port [$AD_SSL_PORT]:"
LangString LocS_ADSSLPort ${_LANGUAGE_} "Port SSL ADempiere [$AD_SSL_PORT]:"

; LocS_InstallFinishHeader  "ADempiere Install Complete"
LangString LocS_InstallFinishHeader ${_LANGUAGE_} "Installation ADempiere est achev�"

; LocS_Install_Finished  "Adempiere has been successfully installed.  The ADempiere Server has been installed as a service and started.  Please allow a few minutes for the server to boot before lauching the client or accessing the web user interface."
LangString LocS_Install_Finished ${_LANGUAGE_} "Adempiere a �t� install� avec succ�s. Le serveur ADempiere a �t� install� en tant que service et a commenc�. S'il vous pla�t permettre � quelques minutes pour que le serveur d�marre avant de lancer le client ou d'acc�der � l'interface utilisateur Web."

; LocS_LogginIn_Instructions  "Open instructions on logging in to ADempiere."
LangString LocS_LogginIn_Instructions ${_LANGUAGE_} "Ouvrez les instructions sur la connexion � ADempiere (en anglais)."

; LocS_ADLoginHelpLink  "http://www.adempiere.com/Launching_the_ADempiere_Application"
LangString LocS_ADLoginHelpLink ${_LANGUAGE_} "http://www.adempiere.com/Launching_the_ADempiere_Application"

; LocS_UninstallConfirm  "Are you sure you want to completely remove $(^Name) and all of its components? The JAVA JDK and PostgreSQL will not be uninstalled. They can be uninstalled using Add/Remove Programs."
LangString LocS_UninstallConfirm ${_LANGUAGE_} "�tes-vous s�r que vous voulez supprimer compl�tement $(^Name) et tous ses composants? Java JDK et PostgreSQL ne seront pas d�sinstall�s. Ils peuvent �tre d�sinstall�s � l'aide Ajout / Suppression de programmes."

; LocS_UninstallSuccess  "$(^Name) was successfully removed from your computer. The JAVA JDK and PostgreSQL installs remain.$\r$\nPlease uninstall Java and PostgreSQL manually."
LangString LocS_UninstallSuccess ${_LANGUAGE_} "$(^Name) a �t� supprim� de votre ordinateur. Installe Java JDK et PostgreSQL restent.$\r$\nVeuillez d�sinstaller Java et PostgreSQL manuellement."
