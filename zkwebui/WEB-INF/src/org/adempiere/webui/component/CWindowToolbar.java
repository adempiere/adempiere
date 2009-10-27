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
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;

import org.adempiere.webui.LayoutUtils;
import org.adempiere.webui.event.ToolbarListener;
import org.adempiere.webui.session.SessionManager;
import org.compiere.model.MRole;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.KeyEvent;
import org.zkoss.zul.Space;

/**
 *
 * @author  <a href="mailto:agramdass@gmail.com">Ashley G Ramdass</a>
 * @date    Feb 25, 2007
 * @version $Revision: 0.10 $
 *
 * @author Cristina Ghita, www.arhipac.ro
 * 				<li>FR [ 2076330 ] Add new methods in CWindowToolbar class
 */
public class CWindowToolbar extends FToolbar implements EventListener
{
    /**
	 *
	 */
	private static final long serialVersionUID = -8259762910508209764L;

	private static final String TOOLBAR_BUTTON_STYLE = "background-color: transparent; display:inline-block; margin-left: 1px; margin-right: 1px; width: 26px; height: 24px;";

	private static final String EMBEDDED_TOOLBAR_BUTTON_STYLE = "background-color: transparent; display:inline-block; margin-left: 1px; margin-right: 1px; width: 20px; height: 18px;";
	
    private static CLogger log = CLogger.getCLogger(CWindowToolbar.class);

    private ToolBarButton btnIgnore;

    private ToolBarButton btnHelp, btnNew, btnCopy, btnDelete, btnDeleteSelection, btnSave;

    private ToolBarButton btnRefresh, btnFind, btnLock, btnAttachment;

    private ToolBarButton btnGridToggle;

    private ToolBarButton btnHistoryRecords, btnParentRecord, btnDetailRecord;

    private ToolBarButton btnFirst, btnPrevious, btnNext, btnLast;

    private ToolBarButton btnReport, btnArchive, btnPrint;

    private ToolBarButton btnZoomAcross, btnActiveWorkflows, btnRequests, btnProductInfo;

    private HashMap<String, ToolBarButton> buttons = new HashMap<String, ToolBarButton>();

//    private ToolBarButton btnExit;

    private ArrayList<ToolbarListener> listeners = new ArrayList<ToolbarListener>();

    private Event event;

    private Map<Integer, ToolBarButton> keyMap = new HashMap<Integer, ToolBarButton>();
    private Map<Integer, ToolBarButton> altKeyMap = new HashMap<Integer, ToolBarButton>();
    private Map<Integer, ToolBarButton> ctrlKeyMap = new HashMap<Integer, ToolBarButton>();

	private boolean embedded;

	// Elaine 2008/12/04
	/** Show Personal Lock								*/
	public boolean isPersonalLock = MRole.getDefault().isPersonalLock();
	private boolean isAllowProductInfo = MRole.getDefault().isAllow_Info_Product();

	private int windowNo = 0;

	private long prevKeyEventTime = 0;

	private KeyEvent prevKeyEvent;

	/**	Last Modifier of Action Event					*/
//	public int 				lastModifiers;
	//

    public CWindowToolbar()
    {
    	this(false);
    }

    public CWindowToolbar(boolean embedded)
    {
    	this.embedded = embedded;
        init();
    }

    private void init()
    {
    	LayoutUtils.addSclass("adwindow-toolbar", this);

        btnIgnore = createButton("Ignore", "Ignore", "Ignore");
        addSeparator();
        btnHelp = createButton("Help", "Help","Help");
        btnNew = createButton("New", "New", "New");
        btnCopy = createButton("Copy", "Copy", "Copy");
        btnDelete = createButton("Delete", "Delete", "Delete");
        btnDeleteSelection = createButton("DeleteSelection", "DeleteSelection", "DeleteSelection");
        btnSave = createButton("Save", "Save", "Save");
        addSeparator();
        btnRefresh = createButton("Refresh", "Refresh", "Refresh");
        btnFind = createButton("Find", "Find", "Find");
        btnAttachment = createButton("Attachment", "Attachment", "Attachment");
        btnGridToggle = createButton("Toggle", "Multi", "Multi");
        btnHistoryRecords = createButton("HistoryRecords", "HistoryX", "History");
        addSeparator();
        btnParentRecord = createButton("ParentRecord", "Parent", "Parent");
        btnDetailRecord = createButton("DetailRecord", "Detail", "Detail");
        btnFirst = createButton("First", "First", "First");
        btnPrevious = createButton("Previous", "Previous", "Previous");
        btnNext = createButton("Next", "Next", "Next");
        btnLast = createButton("Last", "Last", "Last");
        addSeparator();
        btnReport = createButton("Report", "Report", "Report");
        btnArchive = createButton("Archive", "Archive", "Archive");
        btnPrint = createButton("Print", "Print", "Print");
        addSeparator();
        btnLock = createButton("Lock", "Lock", "Lock"); // Elaine 2008/12/04
		btnLock.setVisible(isPersonalLock);
		btnZoomAcross = createButton("ZoomAcross", "ZoomAcross", "ZoomAcross");
        btnActiveWorkflows = createButton("ActiveWorkflows", "WorkFlow", "WorkFlow");
        btnRequests = createButton("Requests", "Request", "Request");
        btnProductInfo = createButton("ProductInfo", "Product", "InfoProduct");
        btnProductInfo.setVisible(isAllowProductInfo);

        // Help and Exit should always be enabled
        btnHelp.setDisabled(false);
        btnGridToggle.setDisabled(false);
        btnZoomAcross.setDisabled(false);

        btnActiveWorkflows.setDisabled(false); // Elaine 2008/07/17
        btnRequests.setDisabled(false); // Elaine 2008/07/22
        btnProductInfo.setDisabled(!isAllowProductInfo); // Elaine 2008/07/22
        btnArchive.setDisabled(false); // Elaine 2008/07/28
        btnLock.setDisabled(!isPersonalLock); // Elaine 2008/12/04

        configureKeyMap();

        if (embedded)
        {
        	btnParentRecord.setVisible(false);
    		btnDetailRecord.setVisible(false);
    		btnActiveWorkflows.setVisible(false);
    		btnHistoryRecords.setVisible(false);
    		btnProductInfo.setVisible(false);
    		setAlign("end");
    		setWidth("100%");
    		setStyle("background: transparent none; ");
        }
        else
        {
        	setWidth("100%");
        }
    }


    private ToolBarButton createButton(String name, String image, String tooltip)
    {
    	ToolBarButton btn = new ToolBarButton("");
        btn.setName("btn"+name);
        btn.setImage("/images/"+image + (embedded ? "16.png" : "24.png"));
        btn.setTooltiptext(Msg.getMsg(Env.getCtx(),tooltip));
        if (embedded)
        {
        	btn.setStyle(EMBEDDED_TOOLBAR_BUTTON_STYLE);
        	btn.setSclass("embedded-toolbar-button");
        }
        else
        {
        	btn.setStyle(TOOLBAR_BUTTON_STYLE);
        	btn.setSclass("toolbar-button");
        }
        buttons.put(name, btn);
        this.appendChild(btn);
        //make toolbar button last to receive focus
        btn.setTabindex(0);
        btn.addEventListener(Events.ON_CLICK, this);
        btn.setDisabled(true);

        return btn;
    }

    public ToolBarButton getButton(String name)
    {
    	return buttons.get(name);
    }

    /** VK_A thru VK_Z are the same as ASCII 'A' thru 'Z' (0x41 - 0x5A) */
    public static final int VK_A              = 0x41;
    public static final int VK_B              = 0x42;
    public static final int VK_C              = 0x43;
    public static final int VK_D              = 0x44;
    public static final int VK_E              = 0x45;
    public static final int VK_F              = 0x46;
    public static final int VK_G              = 0x47;
    public static final int VK_H              = 0x48;
    public static final int VK_I              = 0x49;
    public static final int VK_J              = 0x4A;
    public static final int VK_K              = 0x4B;
    public static final int VK_L              = 0x4C;
    public static final int VK_M              = 0x4D;
    public static final int VK_N              = 0x4E;
    public static final int VK_O              = 0x4F;
    public static final int VK_P              = 0x50;
    public static final int VK_Q              = 0x51;
    public static final int VK_R              = 0x52;
    public static final int VK_S              = 0x53;
    public static final int VK_T              = 0x54;
    public static final int VK_U              = 0x55;
    public static final int VK_V              = 0x56;
    public static final int VK_W              = 0x57;
    public static final int VK_X              = 0x58;
    public static final int VK_Y              = 0x59;
    public static final int VK_Z              = 0x5A;

    private void configureKeyMap()
    {
		keyMap.put(KeyEvent.F1, btnHelp);
		keyMap.put(KeyEvent.F2, btnNew);
		keyMap.put(KeyEvent.F3, btnDelete);
		keyMap.put(KeyEvent.F4, btnSave);
		keyMap.put(KeyEvent.F5, btnRefresh);
		keyMap.put(KeyEvent.F6, btnFind);
		keyMap.put(KeyEvent.F7, btnAttachment);
		keyMap.put(KeyEvent.F8, btnGridToggle);
		keyMap.put(KeyEvent.F9, btnHistoryRecords);
		keyMap.put(KeyEvent.F11, btnReport);
		keyMap.put(KeyEvent.F12, btnPrint);

		altKeyMap.put(KeyEvent.LEFT, btnParentRecord);
		altKeyMap.put(KeyEvent.RIGHT, btnDetailRecord);
		altKeyMap.put(KeyEvent.UP, btnPrevious);
		altKeyMap.put(KeyEvent.DOWN, btnNext);
		altKeyMap.put(KeyEvent.PAGE_UP, btnFirst);
		altKeyMap.put(KeyEvent.PAGE_DOWN, btnLast);
		altKeyMap.put(VK_P, btnReport);
		altKeyMap.put(VK_Z, btnIgnore);

		ctrlKeyMap.put(VK_I, btnProductInfo);
		ctrlKeyMap.put(VK_P, btnPrint);
		ctrlKeyMap.put(VK_N, btnNew);
		ctrlKeyMap.put(VK_S, btnSave);
		ctrlKeyMap.put(VK_D, btnDelete);
		ctrlKeyMap.put(VK_F, btnFind);
	}

	protected void addSeparator()
    {
		Space s = new Space();
		if (embedded)
			s.setSpacing("3px");
		else
			s.setSpacing("6px");
		s.setOrient("vertical");
		this.appendChild(s);
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

        if(eventName.equals(Events.ON_CLICK))
        {
            if(event.getTarget() instanceof ToolBarButton)
            {
            	doOnClick(event);
            }
        } else if (eventName.equals(Events.ON_CTRL_KEY))
        {
        	KeyEvent keyEvent = (KeyEvent) event;
        	if (isRealVisible()) {
	        	//filter same key event that is too close
	        	//firefox fire key event twice when grid is visible
	        	long time = System.currentTimeMillis();
	        	if (prevKeyEvent != null && prevKeyEventTime > 0 &&
	        			prevKeyEvent.getKeyCode() == keyEvent.getKeyCode() &&
	    				prevKeyEvent.getTarget() == keyEvent.getTarget() &&
	    				prevKeyEvent.isAltKey() == keyEvent.isAltKey() &&
	    				prevKeyEvent.isCtrlKey() == keyEvent.isCtrlKey() &&
	    				prevKeyEvent.isShiftKey() == keyEvent.isShiftKey()) {
	        		if ((time - prevKeyEventTime) <= 300) {
	        			return;
	        		}
	        	}
	        	this.onCtrlKeyEvent(keyEvent);
        	}
        }
    }

	private void doOnClick(Event event) {
		this.event = event;
		ToolBarButton cComponent = (ToolBarButton) event.getTarget();
		String compName = cComponent.getName();
		String methodName = "on" + compName.substring(3);
		Iterator<ToolbarListener> listenerIter = listeners.iterator();
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
		this.event = null;
	}

    public void enableHistoryRecords(boolean enabled)
    {
    	this.btnHistoryRecords.setDisabled(!enabled);
    }

    public void enableNavigation(boolean enabled)
    {
        this.btnFirst.setDisabled(!enabled);
        this.btnPrevious.setDisabled(!enabled);
        this.btnNext.setDisabled(!enabled);
        this.btnLast.setDisabled(!enabled);
    }

    public void enableTabNavigation(boolean enabled)
    {
        enableTabNavigation(enabled, enabled);
    }

    public void enableTabNavigation(boolean enableParent, boolean enableDetail)
    {
        this.btnParentRecord.setDisabled(!enableParent);
        this.btnDetailRecord.setDisabled(!enableDetail);
    }

    public void enableFirstNavigation(boolean enabled)
    {
        this.btnFirst.setDisabled(!enabled);
        this.btnPrevious.setDisabled(!enabled);
    }

    public void enableLastNavigation(boolean enabled)
    {
        this.btnLast.setDisabled(!enabled);
        this.btnNext.setDisabled(!enabled);
    }

    public void enabledNew(boolean enabled)
    {
        this.btnNew.setDisabled(!enabled);
    }

   /* public void enableEdit(boolean enabled)
    {
        this.btnEdit.setEnabled(enabled);
    }*/

    public void enableRefresh(boolean enabled)
    {
        this.btnRefresh.setDisabled(!enabled);
    }

    public void enableSave(boolean enabled)
    {
        this.btnSave.setDisabled(!enabled);
    }

    public boolean isSaveEnable() {
    	return !btnSave.isDisabled();
    }

//    public void enableExit(boolean enabled)
//    {
//    	this.btnExit.setDisabled(!enabled);
//    }

    public void enableDelete(boolean enabled)
    {
        this.btnDelete.setDisabled(!enabled);
    }

    // Elaine 2008/12/01
    public void enableDeleteSelection(boolean enabled)
    {
        this.btnDeleteSelection.setDisabled(!enabled);
    }
    //

    public void enableChanges(boolean enabled)
    {
        this.btnNew.setDisabled(!enabled);
        this.btnCopy.setDisabled(!enabled);
    }

    public void enableIgnore(boolean enabled)
    {
        this.btnIgnore.setDisabled(!enabled);
    }

    public void enableNew(boolean enabled)
    {
        this.btnNew.setDisabled(!enabled);
    }

    public void enableAttachment(boolean enabled)
    {
        this.btnAttachment.setDisabled(!enabled);
    }

    public void enablePrint(boolean enabled)
    {
    	this.btnPrint.setDisabled(!enabled);
    }

    public void enableReport(boolean enabled)
    {
    	this.btnReport.setDisabled(!enabled);
    }

    public void enableCopy(boolean enabled)
    {
    	this.btnCopy.setDisabled(!enabled);
    }

    public void enableFind(boolean enabled)
    {
        this.btnFind.setDisabled(!enabled);
    }

    public void enableGridToggle(boolean enabled)
    {
    	btnGridToggle.setDisabled(!enabled);
    }

    public void lock(boolean locked)
    {
    	this.btnLock.setPressed(locked);

    	String imgURL = "/images/"+ (this.btnLock.isPressed() ? "LockX" : "Lock") + (embedded ? "16.png" : "24.png");
		this.btnLock.setImage(imgURL);
    }

    public Event getEvent()
    {
    	return event;
    }

	private void onCtrlKeyEvent(KeyEvent keyEvent) {
		ToolBarButton btn = null;
		if (keyEvent.isAltKey() && !keyEvent.isCtrlKey() && !keyEvent.isShiftKey())
		{
			if (keyEvent.getKeyCode() == VK_X)
			{
				if (windowNo > 0)
				{
					prevKeyEventTime = System.currentTimeMillis();
		        	prevKeyEvent = keyEvent;
					keyEvent.stopPropagation();
					SessionManager.getAppDesktop().closeWindow(windowNo);
				}
			}
			else
			{
				btn = altKeyMap.get(keyEvent.getKeyCode());
			}
		}
		else if (!keyEvent.isAltKey() && keyEvent.isCtrlKey() && !keyEvent.isShiftKey())
			btn = ctrlKeyMap.get(keyEvent.getKeyCode());
		else if (!keyEvent.isAltKey() && !keyEvent.isCtrlKey() && !keyEvent.isShiftKey())
			btn = keyMap.get(keyEvent.getKeyCode());

		if (btn != null) {
			prevKeyEventTime = System.currentTimeMillis();
        	prevKeyEvent = keyEvent;
			keyEvent.stopPropagation();
			if (!btn.isDisabled() && btn.isVisible()) {
				Events.sendEvent(btn, new Event(Events.ON_CLICK, btn));
			}
		}
	}

	private boolean isRealVisible() {
		if (!isVisible())
			return false;
		Component parent = this.getParent();
		while (parent != null) {
			if (!parent.isVisible())
				return false;
			parent = parent.getParent();
		}
		return true;
	}

	/**
	 *
	 * @param visible
	 */
	public void setVisibleAll(boolean visible)
	{
		for (ToolBarButton btn : buttons.values())
		{
			btn.setVisible(visible);
		}
	}

	/**
	 *
	 * @param buttonName
	 * @param visible
	 */
	public void setVisible(String buttonName, boolean visible)
	{
		ToolBarButton btn = buttons.get(buttonName);
		if (btn != null)
		{
			btn.setVisible(visible);
		}
	}

	/**
	 *
	 * @param windowNo
	 */
	public void setWindowNo(int windowNo) {
		this.windowNo = windowNo;
	}
}
