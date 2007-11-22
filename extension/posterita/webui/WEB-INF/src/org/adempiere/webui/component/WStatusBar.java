/******************************************************************************
 * Product: Posterita Ajax UI 												  *
 * Copyright (C) 2007 Posterita Ltd.  All Rights Reserved.                    *
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
 * Posterita Ltd., 3, Draper Avenue, Quatre Bornes, Mauritius                 *
 * or via info@posterita.org or http://www.posterita.org/                     *
 *****************************************************************************/

package org.adempiere.webui.component;

import java.awt.Color;

import org.adempiere.webui.component.Label;
import org.compiere.model.DataStatusEvent;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;

/**
 * Web UI status bar. Based upon the rich client's
 * {@link org.compiere.apps.StatusBar}. The basic status bar contains one or
 * both of (a) a general status description and (b) a database status
 * description. In addition, components can be added to the status bar to extend
 * its functionaility
 * 
 * @author Andrew Kimball
 */
public class WStatusBar extends Grid implements EventListener
{
    private static final long serialVersionUID = 1L;
    
    /** label */
    private Label statusLine = new Label();
    
    private Label statusDB = new Label();
    
    private Row statusBar = new Row();
    
    private String m_text;
    
    private DataStatusEvent m_dse = null;
    
    private boolean mt_error;
    
    private String mt_text;
    
    /**
     * Constructor for a Standard Status Bar.
     */
    public WStatusBar()
    {
        super();
        
        Rows rows = new Rows();
        try
        {
            initialise();
            // this.setBorder("normal");
            rows.appendChild(statusBar);
            this.appendChild(rows);
            this.setWidth("98%");
        }
        catch (Exception e)
        {
            
        }
        // this.setName("statusBar");
    } // StatusBar
    
    /**
     * Static Initialisation.
     * 
     * @throws Exception
     */
    private void initialise() throws Exception
    {
        statusLine.setValue("statusLine");
        statusBar.appendChild(statusLine);
        
        statusDB.setValue("#");
        statusDB.setStyle("text-align:right; " + "color:"
                + ZkCssHelper.createHexColorString(Color.blue));
        statusDB.addEventListener(Events.ON_CLICK, this);
        
        statusBar.appendChild(statusDB);
    } // jbInit
    
    /**
     * Get Status Line text.
     * 
     * @return StatusLine text
     */
    public final String getStatusLine()
    {
        return statusLine.getValue().trim();
    } // getStatusLine
    
    /**
     * Set Status Line
     * 
     * @param text
     *            text
     * @param error
     *            error
     */
    public final void setStatusLine(final String text, final boolean error)
    {
        mt_error = error;
        mt_text = text;
        if (mt_error)
        {
/*            ZkCssHelper.appendStyle(statusLine, ZkCssHelper
                    .createHexColorString(AdempierePLAF.getTextColor_Issue()));
*/        }
        else
        {
/*            ZkCssHelper.appendStyle(statusLine, ZkCssHelper
                    .createHexColorString(AdempierePLAF.getTextColor_OK()));
*/        }
        statusLine.setValue(mt_text);
        //
        Thread.yield();
    } // setStatusLine
    
    /**
     * Set ToolTip of StatusLine
     * 
     * @param tip
     *            tooltip text
     */
    public final void setStatusToolTip(final String tip)
    {
        statusLine.setTooltiptext(tip);
    } // setStatusToolTip
    
    /**
     * Set Standard Status Line (non error)
     * 
     * @param text
     *            text to display on the status line
     */
    public final void setStatusLine(final String text)
    {
        if (text == null)
        {
            setStatusLine("", false);
        }
        else
        {
            setStatusLine(text, false);
        }
    } // setStatusLine
    
    /**
     * Set Status DB Info
     * 
     * @param text
     *            description of database status
     * @param dse
     *            data status event
     */
    public final void setStatusDB(final String text, final DataStatusEvent dse)
    {
        if (text == null || text.length() == 0)
        {
            statusDB.setValue("");
            statusDB.setVisible(false);
            statusDB.detach();
        }
        else
        {
            StringBuffer sb = new StringBuffer(" ");
            sb.append(text).append(" ");
            statusDB.setValue(sb.toString());
            if (!statusDB.isVisible())
            {
                statusDB.setVisible(true);
            }
            statusDB.setParent(statusBar);
        }
        
        // Save
        m_text = text;
        m_dse = dse;
    } // setStatusDB
    
    /**
     * Set Status DB Info
     * 
     * @param text
     *            description of database status
     */
    public final void setStatusDB(final String text)
    {
        setStatusDB(text, null);
    } // setStatusDB
    
    /**
     * Set Status DB Info
     * 
     * @param no
     *            Database status identifier
     */
    public final void setStatusDB(final int no)
    {
        setStatusDB(String.valueOf(no), null);
    } // setStatusDB
    
    /**
     * Add Component to East of StatusBar
     * 
     * @param component
     *            component
     */
    public final void addStatusComponent(final Component component)
    {
        this.appendChild(component);
    } // addStatusComponent
    
    /*
     * (non-Javadoc)
     * 
     * @see org.zkoss.zk.ui.event.EventListener#onEvent(org.zkoss.zk.ui.event.Event)
     */
    public void onEvent(final Event evt) throws Exception
    {
        // TODO Auto-generated method stub
    }
    
}
