/**********************************************************************
* This file is part of Adempiere ERP Bazaar                           *
* http://www.adempiere.org                                            *
*                                                                     *
* Copyright (C) Carlos Ruiz - globalqss                               *
* Copyright (C) Contributors                                          *
*                                                                     *
* This program is free software; you can redistribute it and/or       *
* modify it under the terms of the GNU General Public License         *
* as published by the Free Software Foundation; either version 2      *
* of the License, or (at your option) any later version.              *
*                                                                     *
* This program is distributed in the hope that it will be useful,     *
* but WITHOUT ANY WARRANTY; without even the implied warranty of      *
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the        *
* GNU General Public License for more details.                        *
*                                                                     *
* You should have received a copy of the GNU General Public License   *
* along with this program; if not, write to the Free Software         *
* Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,          *
* MA 02110-1301, USA.                                                 *
*                                                                     *
* Contributors:                                                       *
* - Carlos Ruiz  (globalqss@users.sourceforge.net)                    *
*                                                                     * 
* Sponsors:                                                           *
* - GlobalQSS (http://www.globalqss.com)                              *
***********************************************************************/

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
