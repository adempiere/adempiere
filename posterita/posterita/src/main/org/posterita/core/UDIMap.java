/**
 *  Product: Posterita Web-Based POS and Adempiere Plugin
 *  Copyright (C) 2007  Posterita Ltd
 *  This file is part of POSterita
 *  
 *  POSterita is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License along
 *  with this program; if not, write to the Free Software Foundation, Inc.,
 *  51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 * Created on Aug 15, 2005 by praveen
 *
 */
package org.posterita.core;

import java.util.HashMap;

import org.compiere.process.DocAction;

public class UDIMap 
{
    public static final HashMap<String, String> docStatusMap;
    
    static
    {
        docStatusMap = new HashMap<String, String>();
        docStatusMap.put(DocAction.STATUS_Approved,"Approved");
        docStatusMap.put(DocAction.STATUS_Completed,"Completed");
        docStatusMap.put(DocAction.STATUS_Closed,"Closed");
        docStatusMap.put(DocAction.STATUS_Drafted,"Drafted");
        docStatusMap.put(DocAction.STATUS_InProgress,"InProgress");
        docStatusMap.put(DocAction.STATUS_Invalid,"Invalid");
        docStatusMap.put(DocAction.STATUS_NotApproved,"Not Approved");
        docStatusMap.put(DocAction.STATUS_Reversed,"Reversed");
        docStatusMap.put(DocAction.STATUS_Unknown,"UnKnown");
        docStatusMap.put(DocAction.STATUS_Voided,"Voided");
        docStatusMap.put(DocAction.STATUS_WaitingConfirmation,"Waiting Confirmation");
        docStatusMap.put(DocAction.STATUS_WaitingPayment,"Waiting payment");
    }

}
