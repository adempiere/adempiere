package com._3e.ADInterface;

import org.codehaus.xfire.fault.XFireFault;

import pl.x3E.adInterface.ADLoginRequestDocument;
import pl.x3E.adInterface.ModelRunProcessDocument;
import pl.x3E.adInterface.RunProcessResponseDocument;
import pl.x3E.adInterface.StandardResponseDocument;

public interface ModelADService {

    /* Model oriented web services, the web services above are UI oriented, below we define the model oriented web services */

    public StandardResponseDocument modelSetDocAction(String tableName, int recordID, String docAction, ADLoginRequestDocument reqlogin) throws XFireFault;

    public RunProcessResponseDocument modelRunProcess(ModelRunProcessDocument req) throws XFireFault;

}
