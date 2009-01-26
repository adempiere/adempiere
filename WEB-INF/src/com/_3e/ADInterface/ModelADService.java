package com._3e.ADInterface;

import org.codehaus.xfire.fault.XFireFault;

import pl.x3E.adInterface.ModelCRUDRequestDocument;
import pl.x3E.adInterface.ModelGetListRequestDocument;
import pl.x3E.adInterface.ModelRunProcessRequestDocument;
import pl.x3E.adInterface.ModelSetDocActionRequestDocument;
import pl.x3E.adInterface.RunProcessResponseDocument;
import pl.x3E.adInterface.StandardResponseDocument;
import pl.x3E.adInterface.WindowTabDataDocument;

public interface ModelADService {

    /* Model oriented web services */ 

    public StandardResponseDocument setDocAction(ModelSetDocActionRequestDocument req) throws XFireFault;

    public RunProcessResponseDocument runProcess(ModelRunProcessRequestDocument req) throws XFireFault;

    public WindowTabDataDocument getList(ModelGetListRequestDocument req) throws XFireFault;

    public StandardResponseDocument createData(ModelCRUDRequestDocument req) throws XFireFault;

    public StandardResponseDocument updateData(ModelCRUDRequestDocument req) throws XFireFault;

    public StandardResponseDocument deleteData(ModelCRUDRequestDocument req) throws XFireFault;

    public WindowTabDataDocument readData(ModelCRUDRequestDocument req) throws XFireFault;

    public WindowTabDataDocument queryData(ModelCRUDRequestDocument req) throws XFireFault;

}
