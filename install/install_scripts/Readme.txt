Windows Installer - Readme
**************************

1 - Introduction

The idea of this installer is, to create a series of easy to use Windows setup 
wizards for installing ADempiere with some clicks. The scripts are based on the NSIS 
(Nullsoft Scriptable Install System, http://nsis.sourceforge.net). It 
does the following steps: 

  * Install JDK (Optional if not installed)
  * Install PostgreSQL (Optional if not installed)
  * Install ADempiere
  * Set all necessary environment variables
  * Run an ADempiere build (RUN_SilentSetup)
  * Import the database (RUN_ImportAdempiere)
  * Apply migration scripts (RUN_MigrateXML)
  * Install the server as a service and start the service
  * Create program icons

2 - Using the NSIS scripts

The main script is file "Adempiere_winx64.nsi". It includes all the other 
files. It expected an directory with all the source files which are 
included into the setup.exe. There are serveral folders 

  bin/	 :	Contains the final output executable installer which can be 
  			published.  (Do not include in the repository.)
  tools/ : 	Containing the setup programs for the JDK and PostgreSQL.  These 
  			binaries are not/should not be included in the repository but 
  			will be required if the installer is executed with !define OFF-LINE.
  			If OFF-LINE is not defined, the installer will not include any binaries
  			but will instead download from the respective web sites.
  utils  :	Contains batch files and other supporting files  
		  
Please look into the "Adempiere_winx64.nsi" file before running this script. 
There are some "!define" lines, which should be adjusted for the specific 
versions of ADempiere, JAVA and Postgres.  

3 - Necessary NSIS software and plugins

The scripts are based on the NSIS version 3.0b2.

The following NSIS plugins are required and must be installed:

  - ExecDos: http://nsis.sourceforge.net/ExecDos_plug-in
  - ShellLink: http://nsis.sourceforge.net/ShellLink_plug-in
  - ZipDLL: http://nsis.sourceforge.net/ZipDLL_plug-in
  - Inetc: http://nsis.sourceforge.net/Inetc_plug-in
    
There is a NSIS plugin for Eclipse available at:

  - http://eclipsensis.sourceforge.net/update

Read more about it at http://eclipsensis.sourceforge.net/index.shtml

4 - Contributions

Original installer scripts: Kai Schaeffer, Schaeffer AG (http://www.schaeffer-ag.de)
Upgrades for 361: Red1
Upgrades for 380+: Michael McKay 
