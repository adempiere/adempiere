@echo off

REM $Id: Windows_Service_Uninstall.bat,v 1.1 2003/11/01 17:42:47 comdivisionys Exp $

if (%ADEMPIERE_HOME%) == () (CALL myEnvironment.bat Server) else (CALL %ADEMPIERE_HOME%\utils\myEnvironment.bat Server)

NET STOP Adempiere
%ADEMPIERE_HOME%\utils\windows\JavaService.exe -uninstall Adempiere
