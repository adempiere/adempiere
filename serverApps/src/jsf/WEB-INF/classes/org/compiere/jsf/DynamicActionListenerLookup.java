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

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.compiere.model.GridTab;
import org.compiere.model.GridWindow;
import org.compiere.util.CLogger;

/**
 * this class does the actual work for variables that start with actionListener_lookup 
 * basically every method here should have a void return type and take an ActionEvent as argument
 * TODO: implement all methods for the dropdown and button menus, list them here
 * gridToggle
 * 
 * @author rfreden
 *
 */
public class DynamicActionListenerLookup
{
    // this class makes use of the hidden variable "tabNo"
    // which contains tab0Grid or somesuch, identifying the panel that's currently on top
    // where the digit in the middle is the tabNo for adempiere

    // in case it will matter, it will also have a windowNo, a unique window identifier for adempiere
    private static final CLogger log=CLogger.getCLogger(DynamicActionListenerLookup.class);

    private FacesContext facesContext;

    private Long tabNo;
    
    private TabStateManager tabStateManager;

    public DynamicActionListenerLookup(FacesContext ctx)
    {
        facesContext=ctx;
        tabStateManager=new TabStateManager(facesContext);
    }

    // FIXME: implement all these method stubs
    public void undo(ActionEvent ae)
    {
        throw new UnsupportedOperationException();
    }

    public void help(ActionEvent ae)
    {
        throw new UnsupportedOperationException();
    }

    public void createNew(ActionEvent ae)
    {
        throw new UnsupportedOperationException();

    }

    public void delete(ActionEvent ae)
    {
        throw new UnsupportedOperationException();
    }

    /**
     * tab control actionListener method; use actionListener="actionListener_lookup.focus"
     * to raise the tab described by currentTabNo
     * @param ae
     */
    public void focus(ActionEvent ae)
    {
        log.info("focusing on tabNo "+tabNo);
        tabStateManager.getUIState().setTabNo(tabNo.intValue());
    }

    public void save(ActionEvent ae)
    {
        // save tab on window
        // check if this is called during the
        GridTab gridTab=getCurrentGridTab();
        gridTab.getTableModel().open(0);
        log.info("saving tab "+gridTab.getTabNo());
        if(!gridTab.dataSave(true))
            log.info("failed to save tab");
    }

    public void refresh(ActionEvent ae)
    {
        log.info("called");
        // FIXME: this open method is hackish at best
        getCurrentGridTab().getTableModel().open(0);
        getCurrentGridTab().dataRefresh();
    }

    public void lookup(ActionEvent ae)
    {
        throw new UnsupportedOperationException();
    }

    public void attachment(ActionEvent ae)
    {
        throw new UnsupportedOperationException();
    }
/*
    public void gridToggle(ActionEvent ae)
    {
        log.info("entered");
        tabStateManager.gridToggle();
    }
*/
    public void history(ActionEvent ae)
    {
        throw new UnsupportedOperationException();
    }

    public void menu(ActionEvent ae)
    {
        throw new UnsupportedOperationException();
    }

    public void parent(ActionEvent ae)
    {
        throw new UnsupportedOperationException();
    }

    public void detail(ActionEvent ae)
    {
        throw new UnsupportedOperationException();
    }

    public void first(ActionEvent ae)
    {
        throw new UnsupportedOperationException();
    }

    public void previous(ActionEvent ae)
    {
        throw new UnsupportedOperationException();
    }

    public void next(ActionEvent ae)
    {
        throw new UnsupportedOperationException();
    }

    public void last(ActionEvent ae)
    {
        throw new UnsupportedOperationException();
    }

    public void report(ActionEvent ae)
    {
        throw new UnsupportedOperationException();
    }

    public void archived(ActionEvent ae)
    {
        throw new UnsupportedOperationException();
    }

    public void print(ActionEvent ae)
    {
        throw new UnsupportedOperationException();
    }

    public void zoom(ActionEvent ae)
    {
        throw new UnsupportedOperationException();
    }

    public void active(ActionEvent ae)
    {
        throw new UnsupportedOperationException();
    }

    public void check(ActionEvent ae)
    {
        throw new UnsupportedOperationException();
    }

    public void product(ActionEvent ae)
    {
        throw new UnsupportedOperationException();
    }

    public void exit(ActionEvent ae)
    {
        throw new UnsupportedOperationException();
    }

    public void setTabNo(Long l)
    {
        log.info("setting tabNo to "+l);
        tabNo=l;
    }

    // these three parse the tabNo string
    // FIXME: check them
    private GridTab getCurrentGridTab()
    {
        Integer key=new Integer(tabStateManager.getUIState().getWindowNo());
        // get window from hashmap "grids"
        GridWindow gridWindow=((HashMap<Integer, GridWindow>)facesContext.getExternalContext()
                .getSessionMap().get("grids")).get(key);
        return gridWindow.getTab(tabStateManager.getUIState().getTabNo().intValue());
    }
}
