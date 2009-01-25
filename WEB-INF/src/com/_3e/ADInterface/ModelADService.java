package com._3e.ADInterface;

import org.codehaus.xfire.fault.XFireFault;

import pl.x3E.adInterface.ADLoginRequestDocument;
import pl.x3E.adInterface.ModelGetListRequestDocument;
import pl.x3E.adInterface.ModelRunProcessRequestDocument;
import pl.x3E.adInterface.RunProcessResponseDocument;
import pl.x3E.adInterface.StandardResponseDocument;
import pl.x3E.adInterface.WindowTabDataDocument;

public interface ModelADService {

    /* Model oriented web services */ 

    public StandardResponseDocument setDocAction(String tableName, int recordID, String docAction, ADLoginRequestDocument reqlogin) throws XFireFault;

    public RunProcessResponseDocument runProcess(ModelRunProcessRequestDocument req) throws XFireFault;

    public WindowTabDataDocument getList(ModelGetListRequestDocument req) throws XFireFault;

}
