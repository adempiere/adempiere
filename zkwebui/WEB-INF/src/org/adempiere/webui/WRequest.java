/******************************************************************************
 * Copyright (C) 2008 Low Heng Sin                                            *
 * Copyright (C) 2009 Idalica Corporation                                     *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 *****************************************************************************/

package org.adempiere.webui;

import org.adempiere.webui.session.SessionManager;
import org.adempiere.webui.window.ADWindow;
import org.compiere.model.MQuery;
import org.compiere.request.apps.Request;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.ValueNamePair;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Menuitem;
import org.zkoss.zul.Menupopup;
import org.zkoss.zul.Menuseparator;

import java.util.ArrayList;

/**
 * Request Button Action.
 * Popup Menu
 *
 * @author Jorg Janke
 *
 * @author Teo Sarca, SC ARHIPAC SERVICE SRL
 *      <li>BF [ 1904928 ] Request: Related Request field not filled
 * @author OpenUp Solutions Sylvie Bouissa, sylvie.bouissa@openupsolutions.com, http://www.openupsolutions.com
 *      <li>#1394 Add a submenu with details of each request, in the request icon on the window toolbar
 *      <li>Reference to issue https://github.com/adempiere/adempiere/issues/1394
 *
 * @author eEvolution author Victor Perez <victor.perez@e-evolution.com>, http://www.e-evolution.com
 * <li>#1394 Add a submenu with details of each request, in the request icon on the window toolbar
 * <li>Reference to issue https://github.com/adempiere/adempiere/issues/1394
 */
public class WRequest extends Request implements EventListener {
    /**
     * Constructor
     *
     * @param invoker   invoker button
     * @param tableId   table
     * @param recordId  record
     * @param partnerId optional bp
     */
    public WRequest(Component invoker, int tableId, int recordId, int partnerId) {
        log.config("AD_Table_ID=" + tableId + ", Record_ID=" + recordId);
        this.tableId = tableId;
        this.recordId = recordId;
        this.partnerId = partnerId;
        getRequests(invoker);
    }    //	AReport


    /**
     * The Popup
     */
    private Menupopup menuPopup = new Menupopup();
    private Menuitem newMenuItem = null;
    private Menuitem activeMenuItem = null;
    private Menuitem allMenuItem = null;
    private ArrayList<Menuitem> menuItems = new ArrayList<>();

    protected static CLogger log = CLogger.getCLogger(WRequest.class);

    /**
     * Display Request Options - New/Existing.
     *
     * @param invoker button
     */
    private void getRequests(Component invoker) {
        newMenuItem = new Menuitem(Msg.getMsg(Env.getCtx(), "RequestNew"));
        newMenuItem.setImage("/images/dark/New16.png");
        newMenuItem.addEventListener(Events.ON_CLICK, this);
        menuPopup.appendChild(newMenuItem);

        buildWhereClause();

        Long processingCount = getProcessingCount();
        Long inactiveCount = getUnprocessingCount();

        if (processingCount > 0) {
            activeMenuItem = new Menuitem(Msg.getMsg(Env.getCtx(), "RequestActive")
                    + " (" + processingCount + ")");
            activeMenuItem.addEventListener(Events.ON_CLICK, this);
            menuPopup.appendChild(activeMenuItem);
        }
        if (inactiveCount > 0) {
            allMenuItem = new Menuitem(Msg.getMsg(Env.getCtx(), "RequestAll")
                    + " (" + (processingCount + inactiveCount) + ")");
            allMenuItem.addEventListener(Events.ON_CLICK, this);
            menuPopup.appendChild(allMenuItem);
        }

        fillRequestList();

        menuPopup.setPage(invoker.getPage());
        menuPopup.open(invoker);
    }    //	getZoomTargets

    private void fillRequestList() {
        if (getCount() == 0)
            return;

        menuPopup.appendChild(new Menuseparator());
        getRequestList().entrySet().stream()
                .forEach(requestEntry -> {
                    ValueNamePair requetInfo = requestEntry.getValue();
                    Menuitem requestInfoMenu = new Menuitem(requetInfo.getName());
                    requestInfoMenu.setAttribute("Name", requestEntry.getKey());
                    requestInfoMenu.addEventListener(Events.ON_CLICK, this);
                    menuItems.add(requestInfoMenu);
                    menuPopup.appendChild(requestInfoMenu);
                });
        menuPopup.appendChild(new Menuseparator());
    }

    public void onEvent(Event event) throws Exception {
        if (event.getTarget() instanceof Menuitem) {
            MQuery query = null;
            if (event.getTarget() == activeMenuItem) {
                query = new MQuery("");
                String where = "(" + whereClause + ") AND Processed='N'";
                query.addRestriction(where);
                query.setRecordCount(0);
            } else if (event.getTarget() == allMenuItem) {
                query = new MQuery("");
                query.addRestriction(whereClause.toString());
                query.setRecordCount(0);
            } else if (event.getTarget() == newMenuItem) {
                query = new MQuery("");
                query.addRestriction("1=2");
                query.setRecordCount(0);
            } else if (menuItems.contains(event.getTarget())) {
                Menuitem menuItem = (Menuitem) event.getTarget();
                query = new MQuery("");
                String where = "(" + whereClause + ") AND R_Request_ID=" + menuItem.getAttribute("Name").toString();
                query.addRestriction(where);
                query.setRecordCount(0);
            }
            int windowId = 232;        //	232=all - 201=my
            ADWindow window = SessionManager.getAppDesktop().openWindow(windowId, query);
            if (window == null)
                return;
            //	New - set Table/Record
            if (event.getTarget() == newMenuItem)
                defineGridTab(window.getADWindowPanel().getActiveGridTab());

            window = null;
        }
    }
}
