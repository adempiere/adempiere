; *** Install Options

Var Dialog
Var Label
Var hwnd
Var AD_Password_Control
Var AD_Port_Control
Var AD_SSL_Port_Control
Var PG_Password_Control
Var PG_User_Control
Var PG_Port_Control
Var loginLink

Function nsDialogsOptions1

    !insertmacro MUI_HEADER_TEXT $(LocS_ADSetupOptionsHeader) ""
    
    ; If PostgreSQL will be installed we ask for the password for everything. If PostgreSQL
    ; is already installed we ask for the password for the "postgres" database user.

    nsDialogs::Create /NOUNLOAD 1018
    Pop $Dialog

    ${If} $Dialog == error
        Abort
    ${EndIf}

    StrCpy $R0 "25"
    
    ${NSD_CreateLabel} 0 0 100% $R0u  $(LocS_ADSetupOptions)
    Pop $Label

    IntOp $R0 $R0 + 15
    ${NSD_CreateLabel} 0u $R0u 130u 12u $(LocS_PGSystemUser)
    ${NSD_CreateText} 131u $R0u 80u 12u $PG_USER
    Pop $PG_USER_Control

    IntOp $R0 $R0 + 15   
    ${NSD_CreateLabel} 0u $R0u 130u 12u $(LocS_PGAdminPassword)
    ${NSD_CreatePassword} 131u $R0u 80u 12u $PG_PASSWORD
    Pop $PG_Password_Control
    ${NSD_CreateCheckbox} 212u $R0u 100% 12u $(LocS_ShowPassword)
    Pop $hwnd
    ${NSD_OnClick} $hwnd ShowPGPassword
    
    IntOp $R0 $R0 + 15
    ${NSD_CreateLabel} 0u $R0u 130u 12u $(LocS_PGPort)
    ${NSD_CreateText} 131u $R0u 80u 12u $PG_PORT
    Pop $PG_Port_Control

    IntOp $R0 $R0 + 15
    ${NSD_CreateLabel} 0u $R0u 130u 12u $(LocS_ADUserPassword)
    ${NSD_CreatePassword} 131u $R0u 80u 12u $AD_PASSWORD
    Pop $AD_Password_Control
    ${NSD_CreateCheckbox} 212u $R0u 100% 12u $(LocS_ShowPassword)
    Pop $hwnd
    ${NSD_OnClick} $hwnd ShowADPassword

    IntOp $R0 $R0 + 15
    ${NSD_CreateLabel} 0u $R0u 130u 12u $(LocS_ADWebPort)
    ${NSD_CreateText} 131u $R0u 80u 12u $AD_PORT
    Pop $AD_Port_Control

    IntOp $R0 $R0 + 15
    ${NSD_CreateLabel} 0u $R0u 130u 12u $(LocS_ADSSLPort)
    ${NSD_CreateText} 131u $R0u 80u 12u $AD_SSL_PORT
    Pop $AD_SSL_Port_Control

    nsDialogs::Show

FunctionEnd

Function ShowPGPassword
    Pop $hwnd
    ${NSD_GetState} $hwnd $0
    ShowWindow $PG_Password_Control ${SW_HIDE}
    ${If} $0 == 1
        SendMessage $PG_Password_Control ${EM_SETPASSWORDCHAR} 0 0
    ${Else}
        SendMessage $PG_Password_Control ${EM_SETPASSWORDCHAR} 42 0
    ${EndIf}
    ShowWindow $PG_Password_Control ${SW_SHOW}
FunctionEnd

Function ShowADPassword
    Pop $hwnd
    ${NSD_GetState} $hwnd $0
    ShowWindow $AD_Password_Control ${SW_HIDE}
    ${If} $0 == 1
        SendMessage $AD_Password_Control ${EM_SETPASSWORDCHAR} 0 0
    ${Else}
        SendMessage $AD_Password_Control ${EM_SETPASSWORDCHAR} 42 0
    ${EndIf}
    ShowWindow $AD_Password_Control ${SW_SHOW}
FunctionEnd

Function nsDialogsOptions1Leave

    ; If PostgreSQL will be installed, check if the entered passwords are equal and long enough.
    ${NSD_GetText} $PG_USER_Control     $PG_USER
    ${NSD_GetText} $PG_Password_Control $PG_PASSWORD
    ${NSD_GetText} $PG_Port_Control     $PG_PORT
    ${NSD_GetText} $AD_Password_Control $AD_PASSWORD
    ${NSD_GetText} $AD_Port_Control     $AD_PORT
    ${NSD_GetText} $AD_SSL_Port_Control $AD_SSL_PORT
FunctionEnd

Function installFinish
    !insertmacro MUI_HEADER_TEXT $(LocS_InstallFinishHeader) ""
    
    nsDialogs::Create /NOUNLOAD 1018
    Pop $Dialog

    ${If} $Dialog == error
        Abort
    ${EndIf}

    StrCpy $R0 "25"
    
    ${NSD_CreateLabel} 0 0 100% $R0u  $(LocS_Install_Finished)
    Pop $Label

    IntOp $R0 $R0 + 40
    ${NSD_CreateCheckbox} 0u $R0u 100% 12u $(LocS_LogginIn_Instructions)
    Pop $loginLink

    nsDialogs::Show    
FunctionEnd

Function installFinishLeave
    ; If the loginLink checkbox is selected, open the link.
    ${NSD_GetState} $loginLink $0
    ${If} $0 == ${BST_CHECKED}
        ExecShell "open" $(LocS_ADLoginHelpLink)   
    ${EndIf}
FunctionEnd
