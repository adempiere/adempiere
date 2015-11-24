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

!define PRODUCT_DIR_REGKEY "Software\Microsoft\Windows\CurrentVersion\App Paths\${PRODUCT_NAME}"
!define PRODUCT_UNINST_KEY "Software\Microsoft\Windows\CurrentVersion\Uninstall\${PRODUCT_NAME}"
!define PRODUCT_UNINST_ROOT_KEY "HKLM"

!define SEPARATOR "$\r$\n-----------------------------"

!include "MUI2.nsh"
!include "LogicLib.nsh"
!include "EnvVarUpdate.nsh"
!include "nsDialogs.nsh"
!include "AdempiereLib.nsh" ; TODO Check if this file is required
!include "TextFunc.nsh"
!include "WordFunc.nsh"
!include "Sections.nsh"
!include "ZipDLL.nsh"

!insertmacro ConfigWrite
!insertmacro ConfigRead


; Needed Variables
VAR AD_INSTALL_DRIVE ; Drive to install adempiere
VAR ADEMPIERE_HOME  ; Path of the Adempiere installation
VAR AD_PASSWORD     ; Password for Adempiere installation
VAR AD_PORT         ; Port for the ADempiere web server
VAR AD_SSL_PORT     ; SSL Port for the ADempiere web server 

VAR JAVA_HOME       ; Path of the JDK installation
VAR JDK_VERSION     ; Version of the installed JDK
VAR JDK_DIR         ; Directory of the installed JDK (empty string if no JDK)

;VAR DATABASE_HOME   ; Path of the database installation
VAR PG_INSTDIR      ; Directory to install PostgreSQL
VAR PG_PASSWORD     ; Password for PostgreSQL installtion
VAR PG_INSTALLED_VERSION  ; Version of an installed PG
VAR PG_VERSION_DETAIL ; The detailed string version id
VAR PG_PORT         ; PostgreSQL Port (5432)
;VAR PG_CREATEUSER   ; 1=Create PG system account, 0=Don't create
VAR PG_USER         ; Username of PG system account

VAR COMPUTER_NAME

Var PRODUCT_VERSION
Var EXPECTED_LINES_BUILD
Var EXPECTED_LINES_IMPORT
Var EXPECTED_LINES_MIGRATION

!insertmacro VersionCompare

;Temp variable
!define TEMP1 $R0 