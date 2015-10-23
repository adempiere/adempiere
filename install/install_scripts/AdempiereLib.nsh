; StrReplaceV4

!define Var0 $R0
!define Var1 $R1
!define Var2 $R2
!define Var3 $R3
!define Var4 $R4
!define Var5 $R5
!define Var6 $R6
!define Var7 $R7
!define Var8 $R8

!macro ClearStack
    ${Do}
        Pop $0
        IfErrors send
    ${Loop}
send:
!macroend

!define ClearStack "!insertmacro ClearStack"

!macro StrReplaceV4 Var Replace With In
 Push $R0
 Push $R2
 Push `${Replace}`
 Push `${With}`
 Push `${In}`
  Call StrReplaceV4
 Pop `${Var}`
 Pop $R2
 Pop $R1
!macroend

!define StrReplaceV4 `!insertmacro StrReplaceV4`

Function StrReplaceV4
Exch ${Var0} #in
Exch 1
Exch ${Var1} #with
Exch 2
Exch ${Var2} #replace
Push ${Var3}
Push ${Var4}
Push ${Var5}
Push ${Var6}
Push ${Var7}
Push ${Var8}

 StrCpy ${Var3} -1
 StrLen ${Var5} ${Var0}
 StrLen ${Var6} ${Var1}
 StrLen ${Var7} ${Var2}
 Loop:
  IntOp ${Var3} ${Var3} + 1
  StrCpy ${Var4} ${Var0} ${Var7} ${Var3}
  StrCmp ${Var3} ${Var5} End
  StrCmp ${Var4} ${Var2} 0 Loop

   StrCpy ${Var4} ${Var0} ${Var3}
   IntOp ${Var8} ${Var3} + ${Var7}
   StrCpy ${Var8} ${Var0} "" ${Var8}
   StrCpy ${Var0} ${Var4}${Var1}${Var8}
   IntOp ${Var3} ${Var3} + ${Var6}
   IntOp ${Var3} ${Var3} - 1
   IntOp ${Var5} ${Var5} - ${Var7}
   IntOp ${Var5} ${Var5} + ${Var6}

 Goto Loop
 End:

Pop ${Var8}
Pop ${Var7}
Pop ${Var6}
Pop ${Var5}
Pop ${Var4}
Pop ${Var3}
Pop ${Var2}
Pop ${Var1}
Exch ${Var0} #out
FunctionEnd

!undef Var8
!undef Var7
!undef Var6
!undef Var5
!undef Var4
!undef Var3
!undef Var2
!undef Var1
!undef Var0


!define MSWAuthenticate '!insertmacro MSWAuthenticate'

!macro MSWAuthenticate SERVER_NAME USERNAME PASSWORD RESULT
    Push ${USERNAME}
    Push ${SERVER_NAME}
	Push ${PASSWORD}
	Call MSWAuthenticate
	Pop ${RESULT} ; = "success" on succes, or "Logon failure: ..." otherwise.
!macroend

#
# MSWAuthenticate.nsh - by HotButteredSoul
#
# Function for checking to see if a Microsoft Windows Username/password
# pair authenticate.
#

!ifndef _MSWAuthenticate_nsh
!define _MSWAuthenticate_nsh

#
# MSWAuthenticate - authenticates username/password pair
#
# Example:
#
# Push "bob.username"
# Push "ADOMAIN"
# Push "bobs.password"
# Call MSWAuthenticate
# Pop $0 ; = "success" on succes, or "Logon failure: ..." otherwise.
#
# Uses advapi32.lib LogonUserA
#
Function MSWAuthenticate
    Exch $0 ; password (IN)
    Exch
    Exch $1 ; Domain (IN) / "success" (OUT)
    Exch 2
    Exch $2 ; Username (IN)
    Push $3 ; LogonUserA return code
    Push $4 ; GetLastError() code

    ; LOGON32_LOGON_NETWORK = 3
    ; LOGON32_PROVIDER_DEFAULT = 0
    System::Call "advapi32::LogonUserA(t r2, t r1, t r0, i 3, i 0, *i) i .r3 ?e"
    Pop $4 ; the ?e flag from System::Call pushes the result of GetLastError() onto the stack.
    MessageBox MB_OK $4
    IntCmp 0 $3 reject ; return value of 0 is failure.
    StrCpy $1 "success"
    GoTo done

    reject:
    IntCmp   87 $4 ERROR_LOGON_FAILURE
    IntCmp 1326 $4 ERROR_LOGON_FAILURE
    IntCmp 1327 $4 ERROR_ACCOUNT_RESTRICTION
    IntCmp 1328 $4 ERROR_INVALID_LOGON_HOURS
    IntCmp 1329 $4 ERROR_INVALID_WORKSTATION
    IntCmp 1330 $4 ERROR_PASSWORD_EXPIRED
    IntCmp 1331 $4 ERROR_ACCOUNT_DISABLED
    ;an error of some other sort
        StrCpy $1 "Logon failure: $4"
        GoTo done
    ERROR_LOGON_FAILURE:
        StrCpy $1 "Logon failure: unknown user name or bad password."
        GoTo done
    ERROR_ACCOUNT_RESTRICTION:
        StrCpy $1 "Logon failure: user account restriction."
        GoTo done
    ERROR_INVALID_LOGON_HOURS:
        StrCpy $1 "Logon failure: account logon time restriction violation."
        GoTo done
    ERROR_INVALID_WORKSTATION:
        StrCpy $1 "Logon failure: user not allowed to log on to this computer."
        GoTo done
    ERROR_PASSWORD_EXPIRED:
        StrCpy $1 "Logon failure: the specified account password has expired."
        GoTo done
    ERROR_ACCOUNT_DISABLED:
        StrCpy $1 "Logon failure: account currently disabled."
        GoTo done

    done:
    Pop $4
    Pop $3
    Pop $2
    Pop $0
    Exch $1
FunctionEnd
!endif ; _MSWAuthenticate_nsh


!define UserExists '!insertmacro UserExists'
;   !insertmacro UserExists "kai" $0
!macro UserExists USERNAME RESULT
    Push `${USERNAME}`
    Push `Server-Name` ; Dummy for Servername not implemented yet
    Call UserExists
    Pop `${RESULT}`
!macroend

Function UserExists
    Exch $0 ; SERVER_NAME (IN)
    Exch 1
    Exch $1 ; USERNAME (IN) / "success" (OUT)
	Push $2
	Push $3
    System::Call 'advapi32::LookupAccountName(i 0 ,t r1, i 0, *i 0, i 0, *i 0, *i .r2)i .r3 ?e'
    Pop $2 ; the ?e flag from System::Call pushes the result of GetLastError() onto the stack.
	; LastError = 122 means User exists but buffer is to small. That's what we want.
	IntCmp 122 $2 ERROR_INSUFFICIENT_BUFFER
	StrCpy $0 '0'
	Goto done

	ERROR_INSUFFICIENT_BUFFER:
	StrCpy $0 '1'

	done:
    Pop $3
    Pop $2
    Pop $1
    Exch $0
FunctionEnd

!define IsUserAdmin '!insertmacro IsUserAdmin'
; $0 = 1 if the user belongs to the administrator's group
; $0 = 0 if not
; $0 = -1 if there was an error 
!macro IsUserAdmin RESULT
 !define Index "Line${__LINE__}"
   StrCpy ${RESULT} 0
   System::Call '*(&i1 0,&i4 0,&i1 5)i.r0'
   System::Call 'advapi32::AllocateAndInitializeSid(i r0,i 2,i 32,i 544,i 0,i 0,i 0,i 0,i 0, \
   i 0,*i .R0)i.r5'
   System::Free $0
   System::Call 'advapi32::CheckTokenMembership(i n,i R0,*i .R1)i.r5'
   StrCmp $5 0 ${Index}_Error
   StrCpy ${RESULT} $R1
   Goto ${Index}_End
 ${Index}_Error:
   StrCpy ${RESULT} -1
 ${Index}_End:
   System::Call 'advapi32::FreeSid(i R0)i.r5'
 !undef Index
!macroend

