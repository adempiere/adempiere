//	jlaunch.cpp : Defines the entry point for the application.
//

#include "stdafx.h"
#include "resource.h"
#include <shellapi.h>
#include <stdlib.h>
#include <direct.h>

//main window handle
HWND hwnd;

//instance of application handle
HINSTANCE hInst;

//application name
char szAppName[]="Adempiere Launcher";

//path to program to run
char program_to_run[300];
//working directory
char workingdir[300];
//parameters to send
char parameters[500];

//the procedure that handle all messages sent to the window
LRESULT CALLBACK WndProc(HWND,UINT,WPARAM,LPARAM);

//main program
int APIENTRY WinMain(HINSTANCE hInstance,
                     HINSTANCE hPrevInstance,
                     LPSTR     lpCmdLine,
                     int       nCmdShow)
{
	//structure for creating the application window
	WNDCLASSEX wndclass;

	wndclass.cbSize=sizeof(WNDCLASSEX);								//	size of structure
	wndclass.style=CS_HREDRAW|CS_VREDRAW;							//	type of redrawing
	wndclass.lpfnWndProc=WndProc;		//	address of procedure that handle messages sent to the window
	wndclass.cbClsExtra=0;
	wndclass.cbWndExtra=0;
	wndclass.hInstance=hInstance;									//	instance handle
	wndclass.hIcon=LoadIcon(hInstance,MAKEINTRESOURCE(IDI_JLUNCHM));//	window icon
	wndclass.hCursor=LoadCursor(NULL,IDC_ARROW);					//	default cursor
	wndclass.hbrBackground=(HBRUSH)GetStockObject(WHITE_BRUSH);		//	default background
	wndclass.lpszMenuName=NULL;										//	no menu
	wndclass.lpszClassName=szAppName;								//	name of class=name of application
	wndclass.hIconSm=LoadIcon(hInstance,MAKEINTRESOURCE(IDI_JLUNCH));	//	small icon for window

	//	register window class into system
	RegisterClassEx(&wndclass);

	//	copying instance handle into another variable
	hInst=hInstance;

	//	initially program_to run is empty
	program_to_run[0]='\0';
	//	initial working directory is blank
	workingdir[0]='\0';
	//	initial no parameter
	parameters[0]='\0';

	//	creating main window
	hwnd=CreateWindow(szAppName,"Adempiere Lancher",WS_OVERLAPPEDWINDOW,CW_USEDEFAULT,
		CW_USEDEFAULT,CW_USEDEFAULT,CW_USEDEFAULT,NULL,NULL,hInst,NULL);

	//	showing main window minimized
	ShowWindow(hwnd,SW_MINIMIZE);
	

	//	getting "adempiereHome" environment variable value
	char* adempiereHome;
	adempiereHome=getenv("ADEMPIERE_HOME");
	if (adempiereHome == NULL)
	{
		MessageBox(hwnd, "ADEMPIERE_HOME environment variable not set","Adempiere: Error",MB_OK|MB_ICONSTOP);
		return 0;
	}
//	MessageBox(hwnd, adempiereHome, "ADEMPIERE_HOME", MB_OK|MB_ICONSTOP);
	strcpy(workingdir,adempiereHome);
	strcat(workingdir,"\\lib");

	//	change the working directory to %ADEMPIERE_HOME%\lib
	int rez=_chdir(workingdir);
	if(rez==-1)
	{
		char mess[300]="Cannot change to working directory:\n\t";
		strcat(mess, workingdir);
		MessageBox(hwnd, mess,"Adempiere: Error",MB_OK|MB_ICONSTOP);
		return 0;
	}

	//	Get Java_Home
	char* javaHome;
	javaHome=getenv("JAVA_HOME");
	if (javaHome == NULL)
	{
		MessageBox(hwnd, "JAVA_HOME environment variable not set","Adempiere: Error",MB_OK|MB_ICONSTOP);
		return 0;
	}
	strcpy(program_to_run,javaHome);

	//	search for -debug parameter
	char *pos;
	pos = strstr (lpCmdLine,"-debug");  //	get the position of -debug
	//	it is not present so run javaw
	if (pos == NULL)
		strcat(program_to_run,"\\bin\\javaw.exe");
	else
	{
	//	for(int i=0; i<7; i++)			//	erase -debug
	//		*(pos+i) = ' ';
		strcat(program_to_run,"\\bin\\java.exe");
	}
//	MessageBox(hwnd, program_to_run, "Program", MB_OK|MB_ICONSTOP);

	//	Parameters	------------------------------------------------------------

	//	Environment variables License & Product
	char* envInfo;
	envInfo=getenv("ADEMPIERE_PRODUCT");
	if (envInfo == NULL)
	{
	//	MessageBox(hwnd, "No Product Info", "Environment", MB_OK|MB_ICONSTOP);
		strcat(parameters," -DADEMPIERE_PRODUCT=0");
	}
	else
	{
		strcat(parameters," -DADEMPIERE_PRODUCT=");
		strcat(parameters, envInfo);
	}
	strcat(parameters," -DADEMPIERE_HOME=");
	strcat(parameters, adempiereHome);


	//	Parmeter Adempiere - 32-512 MB Allocation Pool
	strcat(parameters," -Xms32m -Xmx512m");
	strcat(parameters," -cp Adempiere.jar;AdempiereCLib.jar ");
	strcat(parameters," org.compiere.Adempiere ");

	//	runtime parameter except
	strcat(parameters, lpCmdLine);

//	MessageBox(hwnd, parameters, "Parameters", MB_OK|MB_ICONSTOP);

	//	Execute command
	long result=(long)ShellExecute (hwnd,"open", program_to_run, parameters, NULL, SW_MINIMIZE);	//	SW_SHOW

	//	Check Error
	if(result<=32)
	{
		char message[200];
		switch(result)
		{
		case 0:
			strcpy(message,"The operating system is out of memory or resources.");
			break;
		case ERROR_FILE_NOT_FOUND:
			strcpy(message,"The specified file was not found: ");
			strcat(message, program_to_run); 
			break;
		case ERROR_PATH_NOT_FOUND:
			strcpy(message,"The specified path was not found: ");
			strcat(message, program_to_run); 
			break;
		case ERROR_BAD_FORMAT:
			strcpy(message,"The .exe file is invalid (non-Win32� .exe or error in .exe image).");
			break;
		case SE_ERR_ACCESSDENIED:
			strcpy(message,"The operating system denied access to the specified file.");
			break;
		case SE_ERR_ASSOCINCOMPLETE:
			strcpy(message,"The file name association is incomplete or invalid.");
			break;
		case SE_ERR_DDEBUSY:
			strcpy(message,"The DDE transaction could not be completed because other DDE transactions were being processed.");
			break;
		case SE_ERR_DDEFAIL:
			strcpy(message,"The DDE transaction failed.");
			break;
		case SE_ERR_DDETIMEOUT:
			strcpy(message,"The DDE transaction could not be completed because the request timed out.");
			break;
		case SE_ERR_DLLNOTFOUND:
			strcpy(message,"The specified dynamic-link library was not found.");
			break;
		case SE_ERR_NOASSOC:
			strcpy(message,"There is no application associated with the given file name extension.");
			break;
		case SE_ERR_OOM:
			strcpy(message,"There was not enough memory to complete the operation.");
			break;
		case SE_ERR_SHARE:
			strcpy(message,"A sharing violation occurred.");
			break;
		default:
			strcpy(message,"An unspecified error occured!");
		}
		MessageBox(hwnd,message,"Adempiere: error",MB_OK|MB_ICONSTOP);
	}

	//	quitting form this application
	PostQuitMessage(0);

	return 0;
}


//	this function handles all messages received by my window 
LRESULT CALLBACK WndProc(HWND hwnd,UINT iMsg,WPARAM wParam,LPARAM lParam)
{
	static long cxChar,cyChar;
	HDC hDC;
	TEXTMETRIC tm;

	switch(iMsg)
	{
	case WM_CREATE:
		hDC=GetDC(hwnd);
		GetTextMetrics(hDC,&tm);
		cxChar=tm.tmAveCharWidth;
		cyChar=tm.tmHeight+tm.tmExternalLeading;
		ReleaseDC(hwnd,hDC);
		return 0;
	case WM_PAINT:
		return 0;
	case WM_CLOSE:
		break;
	case WM_DESTROY:
		PostQuitMessage(0);
		return 0;
	case WM_SYSCOMMAND:
		break;
	case WM_COMMAND:
		break;
	}
	return DefWindowProc(hwnd,iMsg,wParam,lParam);
}

