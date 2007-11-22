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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;

import org.adempiere.webui.constants.EventConstants;
import org.adempiere.webui.event.ToolbarListener;
import org.compiere.util.CLogger;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Label;

/**
 *
 * @author  <a href="mailto:agramdass@gmail.com">Ashley G Ramdass</a>
 * @date    Feb 25, 2007
 * @version $Revision: 0.10 $
 */
public class CWindowToolbar extends FToolbar implements EventListener
{
    private static final long serialVersionUID = 1L;
    
    private static final String BUTTON_WIDTH = "32px";
    
    private static CLogger log = CLogger.getCLogger(CWindowToolbar.class);

    private Button btnIgnore;

    private Button btnHelp, btnNew, btnDelete, btnSave;

    private Button btnRefresh, btnFind, btnAttachment;
    
    private Button btnGridToggle;

    private Button btnHistoryRecords, btnMenu, btnParentRecord, btnDetailRecord;

    private Button btnFirst, btnPrevious, btnNext, btnLast;

    private Button btnReport, btnArchive, btnPrint;

    private Button btnZoomAcross, btnActiveWorkflows, btnRequests, btnProductInfo;

    private Button btnExit;

    private ArrayList<ToolbarListener> listeners = new ArrayList<ToolbarListener>();

    public CWindowToolbar()
    {
        init();
    }

    private void init()
    {
        btnIgnore = new Button("");
        btnIgnore.setName("btnIgnore");
        btnIgnore.setImage("/images/Ignore16.gif");
        btnIgnore.setTooltiptext("Ignore Changes");
        // --
        btnHelp = new Button("");
        btnHelp.setName("btnHelp");
        btnHelp.setImage("/images/Help24.gif");
        btnHelp.setTooltiptext("Help");

        btnNew = new Button("");
        btnNew.setName("btnNew");
        btnNew.setImage("/images/New24.gif");
        btnNew.setTooltiptext("New Record");
        
        btnDelete = new Button("");
        btnDelete.setName("btnDelete");
        btnDelete.setImage("/images/Delete24.gif");
        btnDelete.setTooltiptext("Delete current record");

        btnSave = new Button("");
        btnSave.setName("btnSave");
        btnSave.setImage("/images/Save24.gif");
        btnSave.setTooltiptext("Save changes");
        // --
        btnRefresh = new Button("");
        btnRefresh.setName("btnRefresh");
        btnRefresh.setImage("/images/Refresh24.gif");
        btnRefresh.setTooltiptext("Refresh Data");

        btnFind = new Button("");
        btnFind.setName("btnFind");
        btnFind.setImage("/images/Find24.gif");
        btnFind.setTooltiptext("Find Records");

        btnAttachment = new Button("");
        btnAttachment.setName("btnAttachment");
        btnAttachment.setImage("/images/Attachment24.gif");
        btnAttachment.setTooltiptext("Attachments");
        // --
        
        btnGridToggle = new Button("");
        btnGridToggle.setName("btnGridToggle");
        btnGridToggle.setImage("/images/ZoomAcross24.gif");
        btnGridToggle.setTooltiptext("Grid Toggle");
        
        btnHistoryRecords = new Button("");
        btnHistoryRecords.setName("btnHistoryRecords");
        btnHistoryRecords.setImage("/images/HistoryX24.gif");
        btnHistoryRecords.setTooltiptext("History Records");

        btnMenu = new Button("");
        btnMenu.setName("btnHome");
        btnMenu.setImage("/images/Home24.gif");
        btnMenu.setTooltiptext("Home");

        btnParentRecord = new Button("");
        btnParentRecord.setName("btnParentRecord");
        btnParentRecord.setImage("/images/Parent24.gif");

        btnDetailRecord = new Button("");
        btnDetailRecord.setName("btnDetailRecord");
        btnDetailRecord.setImage("/images/Detail24.gif");
        // --
        btnFirst = new Button("");
        btnFirst.setName("btnFirst");
        btnFirst.setImage("/images/First24.gif");

        btnPrevious = new Button("");
        btnPrevious.setName("btnPrevious");
        btnPrevious.setImage("/images/Previous24.gif");

        btnNext = new Button("");
        btnNext.setName("btnNext");
        btnNext.setImage("/images/Next24.gif");

        btnLast = new Button("");
        btnLast.setName("btnLast");
        btnLast.setImage("/images/Last24.gif");
        // --
        btnReport = new Button("");
        btnReport.setName("btnReport");
        btnReport.setImage("/images/Report24.gif");

        btnArchive = new Button("");
        btnArchive.setName("btnArchive");
        btnArchive.setImage("/images/Archive24.gif");

        btnPrint = new Button("");
        btnPrint.setName("btnPrint");
        btnPrint.setImage("/images/Print24.gif");
        // --
        btnZoomAcross = new Button("");
        btnZoomAcross.setName("btnZoomAcross");
        btnZoomAcross.setImage("/images/ZoomAcross24.gif");

        btnActiveWorkflows = new Button("");
        btnActiveWorkflows.setName("btnActiveWorkflows");
        btnActiveWorkflows.setImage("/images/WorkFlow24.gif");

        btnRequests = new Button("");
        btnRequests.setName("btnRequests");
        btnRequests.setImage("/images/Request24.gif");

        btnProductInfo = new Button("");
        btnProductInfo.setName("btnProductInfo");
        btnProductInfo.setImage("/images/Product24.gif");

        btnExit = new Button("");
        btnExit.setName("btnExit");
        btnExit.setImage("/images/End24.gif");

        this.appendChild(btnIgnore);
        addSeparator();
        this.appendChild(btnHelp);
        this.appendChild(btnNew);
//        this.appendChild(btnEdit);
        this.appendChild(btnDelete);
        this.appendChild(btnSave);
        addSeparator();
        this.appendChild(btnRefresh);
        this.appendChild(btnFind);
        this.appendChild(btnAttachment);
        this.appendChild(btnGridToggle);
        addSeparator();
        this.appendChild(btnHistoryRecords);
        this.appendChild(btnMenu);
        this.appendChild(btnParentRecord);
        this.appendChild(btnDetailRecord);
        addSeparator();
        this.appendChild(btnFirst);
        this.appendChild(btnPrevious);
        this.appendChild(btnNext);
        this.appendChild(btnLast);
        addSeparator();
        this.appendChild(btnReport);
        this.appendChild(btnArchive);
        this.appendChild(btnPrint);
        addSeparator();
        //this.appendChild(btnZoomAcross);
        this.appendChild(btnActiveWorkflows);
        this.appendChild(btnRequests);
        this.appendChild(btnProductInfo);
        addSeparator();
        this.appendChild(btnExit);
        
        for (Object obj : this.getChildren())
        {
            if (obj instanceof Button)
            {
                ((Button)obj).setWidth(BUTTON_WIDTH);
                ((Button)obj).addEventListener(Events.ON_CLICK, this);
                ((Button)obj).setEnabled(false);
            }
        }
        
        // Help and Exit should always be enabled
        btnHelp.setEnabled(true);
        btnExit.setEnabled(true);
        
        // Testing Purposes
        
        btnGridToggle.setEnabled(true);
    }

    protected void addSeparator()
    {
        Label lblSeparator = new Label();
        lblSeparator.setWidth("3px");
        lblSeparator.setHeight("16px");
        lblSeparator.setValue(" ");
        this.appendChild(lblSeparator);
    }

    public void addListener(ToolbarListener toolbarListener)
    {
        listeners.add(toolbarListener);
    }

    public void removeListener(ToolbarListener toolbarListener)
    {
        listeners.remove(toolbarListener);
    }

    public void onEvent(Event event)
    {
        String eventName = event.getName();
        Component eventComp = event.getTarget();

        Iterator<ToolbarListener> listenerIter = listeners.iterator();
        if(eventName.equals(EventConstants.ONCLICK))
        {
            if(eventComp instanceof Button)
            {
                Button cComponent = (Button) eventComp;
                String compName = cComponent.getName();
                String methodName = "on" + compName.substring(3);
                while(listenerIter.hasNext())
                {
                    try
                    {
                        ToolbarListener tListener = listenerIter.next();
                        Method method = tListener.getClass().getMethod(methodName, (Class[]) null);
                        method.invoke(tListener, (Object[]) null);
                    }
                    catch(SecurityException e)
                    {
                        log.log(Level.SEVERE, "Could not invoke Toolbar listener method: " + methodName + "()", e);
                    }
                    catch(NoSuchMethodException e)
                    {
                        log.log(Level.SEVERE, "Could not invoke Toolbar listener method: " + methodName + "()", e);
                    }
                    catch(IllegalArgumentException e)
                    {
                        log.log(Level.SEVERE, "Could not invoke Toolbar listener method: " + methodName + "()", e);
                    }
                    catch(IllegalAccessException e)
                    {
                        log.log(Level.SEVERE, "Could not invoke Toolbar listener method: " + methodName + "()", e);
                    }
                    catch(InvocationTargetException e)
                    {
                        log.log(Level.SEVERE, "Could not invoke Toolbar listener method: " + methodName + "()", e);
                    }
                }
            }
        }
    }
    
    public void enableHistoryRecords(boolean enabled)
    {
    	this.btnHistoryRecords.setEnabled(enabled);
    }
    
    public void enableNavigation(boolean enabledd)
    {
        this.btnFirst.setEnabled(enabledd);
        this.btnPrevious.setEnabled(enabledd);
        this.btnNext.setEnabled(enabledd);
        this.btnLast.setEnabled(enabledd);
    }
    
    public void enableTabNavigation(boolean enabled)
    {
        enableTabNavigation(enabled, enabled);
    }
    
    public void enableTabNavigation(boolean enableParent, boolean enableDetail)
    {
        this.btnParentRecord.setEnabled(enableParent);
        this.btnDetailRecord.setEnabled(enableDetail);
    }
    
    public void enableFirstNavigation(boolean enabled)
    {
        this.btnFirst.setEnabled(enabled);
        this.btnPrevious.setEnabled(enabled);
    }
    
    public void enableLastNavigation(boolean enabled)
    {
        this.btnLast.setEnabled(enabled);
        this.btnNext.setEnabled(enabled);
    }
    
    public void enabledNew(boolean enabled)
    {
        this.btnNew.setEnabled(enabled);
    }
    
   /* public void enableEdit(boolean enabled)
    {
        this.btnEdit.setEnabled(enabled);
    }*/
    
    public void enableRefresh(boolean enabled)
    {
        this.btnRefresh.setEnabled(enabled);
    }
    
    public void enableSave(boolean enabled)
    {
        this.btnSave.setEnabled(enabled);
    }
    
    public void enableExit(boolean enabled)
    {
    	this.btnExit.setEnabled(enabled);
    }
    
    public void enableDelete(boolean enabled)
    {
        this.btnDelete.setEnabled(enabled);
    }
    
    public void enableDeleteSelection(boolean enabled)
    {
        // TODO add delete selection button
    }
    
    public void enableChanges(boolean enabled)
    {
        this.btnNew.setEnabled(enabled);
        this.btnIgnore.setEnabled(enabled);
    }
    
    public void enableIgnore(boolean enabled)
    {
        this.btnIgnore.setEnabled(enabled);
    }
    
    public void enableNew(boolean enabled)
    {
        this.btnNew.setEnabled(enabled);
    }
    
    public void enableAttachment(boolean enabled)
    {
        this.btnAttachment.setEnabled(enabled);
    }
    
    public void enablePrint(boolean enabled)
    {
    	this.btnPrint.setEnabled(enabled);
    }
    
    public void enableReport(boolean enabled)
    {
    	this.btnReport.setEnabled(enabled);
    }
    
    public void enableCopy(boolean enabled)
    {
    }
    
    public void enableFind(boolean enabled)
    {
        this.btnFind.setEnabled(enabled);
        
    }
    
    public void enableGridToggle(boolean enabled)
    {
    	btnGridToggle.setEnabled(enabled);
    }
    
    public boolean isAsap()
    {
        return true;
    }    
}
