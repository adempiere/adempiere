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
; This NSIS script will create a windows 32-bit installer for ADempiere with 
; Java and PostgreSQL.  
; 
; NSIS, and the installers for Java and Postgres are not included in the 
; repository but are required.
; 
; Please read the Readme.txt file for more information on using this script.

; *************************************
; Configure as a off-lne or on-line (light) install
; *************************************
; Uncomment the following line for an off-line install
;!define OFF-LINE
;!define SKIP_AD

; *********************************
; Windows x32 specific defines
; *********************************
!define AD_WIN32_INSTALL  ; 64bit is the default

; *********************************
; Include the main install script 
!include Adempiere_winx64.nsi
