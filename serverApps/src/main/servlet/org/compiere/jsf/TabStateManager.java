/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                        *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.compiere.jsf;

import java.util.HashMap;
import java.util.Map;

import javax.faces.context.FacesContext;

import org.compiere.util.CLogger;

/**
 * manages the session scoped map for "tabNo", which is of type Map<Long,String>, for windowNo and tab state string <tab[0-9]+Detail|Table>
 * but... this class should be the only interface to that (currently there is an el expression that looks like #{sessionScope.tabNo[windowNo]} )
 * this object is _only valid for the windowNo it is instantiated from_
 * the class furthermore should be the interface to windowNo, which is a request parameter
 * @author rfreden
 *
 */
public class TabStateManager
{
    private static final CLogger log=CLogger.getCLogger(TabStateManager.class);

    // the map should be a non clobberable singleton
    private static Map<Long,String> tabNo;
    
    private FacesContext facesContext;

    public TabStateManager(FacesContext fc)
    {
        facesContext=fc;
        if(tabNo==null)
        {
            // if it doesn't exist in the session, then instantiate it and put it there
            if((tabNo=(Map<Long,String>)facesContext.getExternalContext().getSessionMap().get("tabNo"))==null)
            {
                tabNo=new HashMap<Long,String>();
                facesContext.getExternalContext().getSessionMap().put("tabNo",tabNo);
            }
        }
    }
    
    public UIStateBean getUIState()
    {
        return (UIStateBean)facesContext.getApplication().getVariableResolver().resolveVariable(facesContext,"uiStateBean");
    }
}
