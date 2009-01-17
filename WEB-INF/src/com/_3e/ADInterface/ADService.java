package com._3e.ADInterface;

import org.codehaus.xfire.fault.XFireFault;

import pl.x3E.adInterface.*;

public interface ADService {

    public WindowDocument getADWindow(int WindowNo, int AD_Window_ID, int AD_Menu_ID) throws XFireFault;
    //public WindowTabDataDocument getWindowTabData(int WindowNo, int AD_Window_ID, int AD_Menu_ID, int TabNo, int PrevTabNo, int PrevRecNo, boolean GetData) throws XFireFault;
    //public WindowTabDataDocument getWindowTabData(int WindowNo, int AD_Window_ID, int AD_Menu_ID, int TabNo, int PrevTabNo, int PrevRecNo, boolean GetData, int RowStart, int RowCount) throws XFireFault;
    public WindowTabDataDocument getWindowTabData(WindowTabDataReqDocument reqd) throws XFireFault;
    
    public WindowTabDataDocument getDataRow(int WindowNo, int TabNo, int RowNo ) throws XFireFault;
    public WindowTabDataDocument updateDataRow(int WindowNo, int TabNo, int RowNo, WindowTabDataDocument data ) throws XFireFault;
    public WindowTabDataDocument saveDataRow(int WindowNo, int TabNo, int RowNo, WindowTabDataDocument data ) throws XFireFault;
    public WindowTabDataDocument addNewDataRow(int WindowNo, int TabNo ) throws XFireFault;
    public WindowTabDataDocument deleteDataRow(int WindowNo, int TabNo, int RowNo ) throws XFireFault;
    public WindowTabDataDocument ignoreDataRow(int WindowNo, int TabNo, int RowNo ) throws XFireFault;
    public WindowTabDataDocument refreshDataRow(int WindowNo, int TabNo, int RowNo ) throws XFireFault;
    
    public WindowTabDataDocument getLookupSearchData(GetLookupSearchDataReqDocument req) throws XFireFault;
    public WindowTabDataDocument getLookupData(int WindowNo, int TabNo, int RowNo, String columnName ) throws XFireFault;
    
    public ADMenuDocument getADMenu(int AD_Role_ID) throws XFireFault;
    
    public ADLoginResponseDocument login( ADLoginRequestDocument req ) throws XFireFault;
    
    public ProcessParamsDocument getProcessParams( GetProcessParamsDocument req ) throws XFireFault;
    public RunProcessResponseDocument runProcess( RunProcessDocument req )  throws XFireFault;

    public StandardResponseDocument saveLocation( LocationDocument req );
    public LocationDocument getLocation( LocationDocument req );
    
    public DocActionDocument getDocAction(int WindowNo, int TabNo, int RowNo, String ColName ) throws XFireFault;
    public StandardResponseDocument setDocAction(int WindowNo, int TabNo, int RowNo, String ColName, String docAction ) throws XFireFault;    

    public String getVersion();
    
    public boolean isLoggedIn();
    
    /* Model oriented web services, the web services above are UI oriented, below we define the model oriented web services */
    
    public StandardResponseDocument modelSetDocAction(String tableName, int recordID, String newDocStatus, ADLoginRequestDocument reqlogin) throws XFireFault;
    
}
