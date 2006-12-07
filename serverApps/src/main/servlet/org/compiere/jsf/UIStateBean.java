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

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.compiere.util.CLogger;

/**
 * holds state for a window; currently it tracks only the windowNo attribute, provided on initial load
 * an instance of this class should be available for any window page, as it is persisted throughout the view existence via t:saveState
 * @author rfreden
 *
 */
public class UIStateBean implements Serializable
{
    private static final long serialVersionUID=2930846164351L;

    private static final CLogger log=CLogger.getCLogger(UIStateBean.class);

    // TODO: this should be an Integer
    private String windowNo;
    
    private boolean gridView;

    private int tabNo;

    public UIStateBean()
    {
        // int and boolean default initializers put this initial state at tab0Detail
        String[] s=(String[])FacesContext.getCurrentInstance().getExternalContext().getRequestParameterValuesMap().get("windowNo");
        if(s!=null)
        {
            windowNo=s[0];
            log.info("got windowNo "+windowNo);
        }
    }

    public void setWindowNo(String w)
    {
        log.info("setting windowNo "+w);
        windowNo=w;
    }

    public String getWindowNo()
    {
        //log.info("getting windowNo "+windowNo);
        return windowNo;
    }

    public Boolean getGridView()
    {
        return gridView;
    }
    
    public void setGridView(Boolean b)
    {
        gridView=b;
    }

    public Integer getTabNo()
    {
        return tabNo;
    }

    public void setTabNo(Integer i)
    {
        tabNo=i;
    }

    public String getTabState()
    {
        String s="tab"+String.valueOf(tabNo)+(gridView?"Table":"Detail");
        log.info("returning "+s);
        return s;
    }
    
    public void toggleGridView(ActionEvent ae)
    {
        log.info("called");
        gridView=!gridView;
    }
}
