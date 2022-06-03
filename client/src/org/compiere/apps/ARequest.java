/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
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
package org.compiere.apps;

import java.awt.GraphicsConfiguration;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JPopupMenu;

import org.compiere.model.MQuery;
import org.compiere.request.apps.Request;
import org.compiere.swing.CMenuItem;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.ValueNamePair;

/**
 * Request Button Action.
 * Popup Menu
 *
 * @author Jorg Janke
 *
 * @author Teo Sarca, SC ARHIPAC SERVICE SRL
 *      <li>BF [ 1904928 ] Request: Related Request field not filled
 *
 * @author OpenUp Solutions Sylvie Bouissa, sylvie.bouissa@openupsolutions.com, http://www.openupsolutions.com
 *      <li>#1394 Add a submenu with details of each request, in the request icon on the window toolbar
 *      <li>Reference to issue https://github.com/adempiere/adempiere/issues/1394
 *
 * @author eEvolution author Victor Perez <victor.perez@e-evolution.com>, http://www.e-evolution.com
 *      <li>#1394 Add a submenu with details of each request, in the request icon on the window toolbar
 *      <li>Reference to issue https://github.com/adempiere/adempiere/issues/1394
 */
public class ARequest extends Request implements ActionListener {

    /**
     * The Popup
     */
    private JPopupMenu popupMenu = new JPopupMenu("RequestMenu");
    private CMenuItem newMenuItem = null;
    private CMenuItem activeMenuItem = null;
    private CMenuItem allMenuItem = null;
    private GraphicsConfiguration graphicsConfiguration = null;
    private ArrayList<CMenuItem> menuItems = new ArrayList<CMenuItem>();

    protected static CLogger log = CLogger.getCLogger(ARequest.class);

    /**
     * Constructor
     *
     * @param invoker       invoker button
     * @param tableId   table
     * @param recordId     record
     * @param partnerId optional bp
     */
    public ARequest(JComponent invoker, int tableId, int recordId, int partnerId) {
        super();
        log.config("AD_Table_ID=" + tableId + ", Record_ID=" + recordId);
        this.tableId = tableId;
        this.recordId = recordId;
        this.partnerId = partnerId;
        getRequests(invoker);
    }    //	ARequest

    /**
     * Display Request Options - New/Existing.
     *
     * @param invoker button
     */
    private void getRequests(JComponent invoker) {
        newMenuItem = new CMenuItem(Msg.getMsg(Env.getCtx(), "RequestNew"));
        newMenuItem.setIcon(Env.getImageIcon("New16.gif"));
        popupMenu.add(newMenuItem).addActionListener(this);
        graphicsConfiguration = invoker.getGraphicsConfiguration();

        buildWhereClause();

        Long processingCount = getProcessingCount();
        if (processingCount > 0) {
            activeMenuItem = new CMenuItem(Msg.getMsg(Env.getCtx(), "RequestActive")
                    + " (" + processingCount + ")");
            popupMenu.add(activeMenuItem).addActionListener(this);

        }
        Long unprocessingCount = getUnprocessingCount();
        if (unprocessingCount > 0) {
            allMenuItem = new CMenuItem(Msg.getMsg(Env.getCtx(), "RequestAll")
                    + " (" + (processingCount + unprocessingCount) + ")");
            popupMenu.add(allMenuItem).addActionListener(this);
        }

        fillRequestList();
        //
        if (invoker.isShowing())
            popupMenu.show(invoker, 0, invoker.getHeight());    //	below button
    }    //	getZoomTargets


    private void fillRequestList() {
        if (getCount() == 0)
            return;

        popupMenu.addSeparator();
        getRequestList().entrySet().stream()
                .forEach(requestEntry  -> {
                    ValueNamePair requetInfo = requestEntry.getValue();
                    CMenuItem requestInfoMenu = new CMenuItem(requetInfo.getName());
                    requestInfoMenu.setName(requestEntry.getKey());
                    menuItems.add(requestInfoMenu);
                    popupMenu.add(requestInfoMenu).addActionListener(this);
                });
        popupMenu.addSeparator();
    }

    /**
     * Listner
     *
     * @param actionEvent event
     */
    public void actionPerformed(ActionEvent actionEvent) {
        MQuery query = null;
        if (actionEvent.getSource() == activeMenuItem) {
            query = new MQuery("");
            String where = "(" + whereClause + ") AND Processed='N'";
            query.addRestriction(where);
            query.setRecordCount(0);
        } else if (actionEvent.getSource() == allMenuItem) {
            query = new MQuery("");
            query.addRestriction(whereClause.toString());
            query.setRecordCount(0);
        } else if (actionEvent.getSource() == newMenuItem) {
            query = new MQuery("");
            query.addRestriction("1=2");
            query.setRecordCount(0);
        } else if (menuItems.contains(actionEvent.getSource())) {
            CMenuItem menuItem = (CMenuItem) actionEvent.getSource();
            query = new MQuery("");
            String where = "(" + whereClause + ") AND R_Request_ID=" + menuItem.getName();
            query.addRestriction(where);
            query.setRecordCount(0);

        }
        //
        int windowId = 232;        //	232=all - 201=my
        AWindow window = new AWindow(graphicsConfiguration);
        if (!window.initWindow(windowId, query))
            return;
        AEnv.addToWindowManager(window);
        //	New - set Table/Record
        if (actionEvent.getSource() == newMenuItem)
            defineGridTab(window.getAPanel().getCurrentTab());

        AEnv.showCenterScreen(window);
        window = null;
    }    //	actionPerformed

}    //	ARequest
