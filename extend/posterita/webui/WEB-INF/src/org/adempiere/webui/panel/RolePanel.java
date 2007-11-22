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

package org.adempiere.webui.panel;

import java.util.Properties;

import org.adempiere.webui.component.WConfirmPanel;
import org.adempiere.webui.component.Window;
import org.adempiere.webui.exception.ApplicationException;
import org.adempiere.webui.window.LoginWindow;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Login;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Button;
import org.zkoss.zul.Div;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Row;
import org.zkoss.zul.Rows;

/**
 *
 * @author  <a href="mailto:agramdass@gmail.com">Ashley G Ramdass</a>
 * @date    Feb 25, 2007
 * @version $Revision: 0.10 $
 * @author <a href="mailto:sendy.yagambrum@posterita.org">Sendy Yagambrum</a>
 * @date    July 18, 2007
 */
public class RolePanel extends Window implements EventListener
{
    private static final long serialVersionUID = 1L;

    private LoginWindow wndLogin;
    private Login login;
    private KeyNamePair rolesKNPairs[];

    private Label lblErrorMsg;
    private Listbox lstRole, lstClient, lstOrganisation, lstWarehouse;
    private Label lblRole, lblClient, lblOrganisation, lblWarehouse;
    private Button btnOk, btnCancel;


    public RolePanel(Properties ctx, LoginWindow loginWindow, String userName, String password)
    {
        this.wndLogin = loginWindow;
        login = new Login(ctx);
        rolesKNPairs = login.getRoles(userName, password);
        if(rolesKNPairs == null)
            throw new ApplicationException("Login is invalid, UserName: " + userName + " and Password:" + password);
        initComponents();
        init();
        this.setId("rolePanel");
    }

    private void init()
    {
        Grid grid = new Grid();
        grid.setId("grdChooseRole");
        Rows rows = new Rows();
        Row rowRole = new Row();
        Row rowClient = new Row();
        Row rowOrg = new Row();
        Row rowWarehouse = new Row();

        rowRole.appendChild(lblRole);
        rowRole.appendChild(lstRole);

        rowClient.appendChild(lblClient);
        rowClient.appendChild(lstClient);

        rowOrg.appendChild(lblOrganisation);
        rowOrg.appendChild(lstOrganisation);

        rowWarehouse.appendChild(lblWarehouse);
        rowWarehouse.appendChild(lstWarehouse);

        Row rowButtons = new Row();
        //rowButtons.setAlign("right");
        //Label lblButtons = new Label();
        //rowButtons.appendChild(lblButtons);
        //Panel pnlButtons = new Panel();
        //divButtons.appendChild(btnOk);
        //divButtons.appendChild(btnCancel);
        WConfirmPanel pnlButtons = new WConfirmPanel(true, false, false, false, false, false, false);
        pnlButtons.addEventListener(this);
        rowButtons.setSpans("2");
        rowButtons.appendChild(pnlButtons);

        rows.appendChild(rowRole);
        rows.appendChild(rowClient);
        rows.appendChild(rowOrg);
        rows.appendChild(rowWarehouse);
        rows.appendChild(rowButtons);

        grid.appendChild(rows);

        Div divErr = new Div();
        divErr.appendChild(lblErrorMsg);
        this.appendChild(divErr);
        this.appendChild(grid);
    }

    private void initComponents()
    {
        lblErrorMsg = new Label();
        lblErrorMsg.setValue(" ");

        lblRole = new Label();
        lblRole.setId("lblRole");
        lblRole.setValue("Role: ");

        lblClient = new Label();
        lblClient.setId("lblClient");
        lblClient.setValue("Client: ");

        lblOrganisation = new Label();
        lblOrganisation.setId("lblOrganisation");
        lblOrganisation.setValue("Organisation: ");

        lblWarehouse = new Label();
        lblWarehouse.setId("lblWarehouse");
        lblWarehouse.setValue("Warehouse: ");

        lstRole = new Listbox();
        lstRole.setId("lstRole");
        lstRole.setRows(1);
        lstRole.setMold("select");
        lstRole.addEventListener(Events.ON_SELECT, this);
        lstRole.setWidth("180px");

        lstClient = new Listbox();
        lstClient.setId("lstClient");
        lstClient.setRows(1);
        lstClient.setMold("select");
        lstClient.addEventListener(Events.ON_SELECT, this);
        lstClient.setWidth("180px");

        lstOrganisation = new Listbox();
        lstOrganisation.setId("lstOrganisation");
        lstOrganisation.setRows(1);
        lstOrganisation.setMold("select");
        lstOrganisation.addEventListener(Events.ON_SELECT, this);
        lstOrganisation.setWidth("180px");

        lstWarehouse = new Listbox();
        lstWarehouse.setId("lstWarehouse");
        lstWarehouse.setRows(1);
        lstWarehouse.setMold("select");
        lstWarehouse.addEventListener(Events.ON_SELECT, this);
        lstWarehouse.setWidth("180px");

        btnOk = new Button();
        btnOk.setId("btnOk");
        btnOk.setLabel("Ok");
        btnOk.addEventListener("onClick", this);

        btnCancel = new Button();
        btnCancel.setId("btnCancel");
        btnCancel.setLabel("Cancel");
        btnCancel.addEventListener("onClick", this);

        for(int i = 0; i < rolesKNPairs.length; i++)
            lstRole.appendItem(rolesKNPairs[i].getName(), rolesKNPairs[i].getID());
        lstRole.setSelectedIndex(0);
        updateClientList();
    }

    private void updateClientList()
    {
        lstClient.getItems().clear();
        Listitem lstItemRole = lstRole.getSelectedItem();
        if(lstItemRole != null)
        {
            KeyNamePair roleKNPair = new KeyNamePair(new Integer((String)lstItemRole.getValue()), lstItemRole.getLabel());
            KeyNamePair clientKNPairs[] = login.getClients(roleKNPair);
            if(clientKNPairs != null && clientKNPairs.length > 0)
            {
                for(int i = 0; i < clientKNPairs.length; i++)
                    lstClient.appendItem(clientKNPairs[i].getName(), clientKNPairs[i].getID());
                lstClient.setSelectedIndex(0);
            }
        }
        updateOrganisationList();
    }

    private void updateOrganisationList()
    {
        lstOrganisation.getItems().clear();
        Listitem lstItemClient = lstClient.getSelectedItem();
        if(lstItemClient != null)
        {
            KeyNamePair clientKNPair = new KeyNamePair(new Integer((String)lstItemClient.getValue()), lstItemClient.getLabel());
            KeyNamePair orgKNPairs[] = login.getOrgs(clientKNPair);
            if(orgKNPairs != null && orgKNPairs.length > 0)
            {
                for(int i = 0; i < orgKNPairs.length; i++)
                    lstOrganisation.appendItem(orgKNPairs[i].getName(), orgKNPairs[i].getID());
                lstOrganisation.setSelectedIndex(0);
            }
        }
        updateWarehouseList();
    }

    private void updateWarehouseList()
    {
        lstWarehouse.getItems().clear();
        Listitem lstItemOrganisation = lstOrganisation.getSelectedItem();
        if(lstItemOrganisation != null)
        {
            KeyNamePair organisationKNPair = new KeyNamePair(new Integer((String)lstItemOrganisation.getValue()), lstItemOrganisation.getLabel());
            KeyNamePair warehouseKNPairs[] = login.getWarehouses(organisationKNPair);
            if(warehouseKNPairs != null && warehouseKNPairs.length > 0)
            {
                for(int i = 0; i < warehouseKNPairs.length; i++)
                    lstWarehouse.appendItem(warehouseKNPairs[i].getName(), warehouseKNPairs[i].getID());
            }
        }
    }

    public void onEvent(Event event)
    {
        String eventCompId = event.getTarget().getId();
        String eventName = event.getName();
        if(eventName.equals("onSelect"))
        {
            if(eventCompId.equals(lstRole.getId()))
                updateClientList();
            else if(eventCompId.equals(lstClient.getId()))
                updateOrganisationList();
            else if(eventCompId.equals(lstOrganisation.getId()))
                updateWarehouseList();
        }
/*        else if(eventName.equals("onClick"))
        {
            if(eventCompId.equals(btnOk.getId()))
            {
               validateRoles();
            }
            else if(eventCompId.equals(btnCancel.getId()))
            {

            }
        }*/
        if (event.getName().equals(WConfirmPanel.A_OK))
        {
            validateRoles();
        }
        else if (event.getName().equals(WConfirmPanel.A_CANCEL))
        {
            wndLogin.loginCancelled();
        }
    }
    /**
     *  validate Roles
     *
    **/
    public void validateRoles()
    {
        Listitem lstItemRole = lstRole.getSelectedItem();
        Listitem lstItemClient = lstClient.getSelectedItem();
        Listitem lstItemOrg = lstOrganisation.getSelectedItem();
        Listitem lstItemWarehouse = lstOrganisation.getSelectedItem();

        if(lstItemRole == null || lstItemRole.getValue() == null)
        {
            lblErrorMsg.setValue("Role is mandatory!!!");
            return ;
        }
        else if(lstItemClient == null || lstItemClient.getValue() == null)
        {
            lblErrorMsg.setValue("Client is mandatory!!!");
            return ;
        }
        else if(lstItemOrg == null || lstItemOrg.getValue() == null)
        {
            lblErrorMsg.setValue("Organisation is mandatory!!!");
            return ;
        }
        lblErrorMsg.setValue(" ");
        int orgId = 0, warehouseId = 0;
        orgId = Integer.parseInt((String)lstItemOrg.getValue());
        KeyNamePair orgKNPair = new KeyNamePair(orgId, lstItemOrg.getLabel());
        KeyNamePair warehouseKNPair = null;
        if(lstItemWarehouse != null && lstItemWarehouse.getValue() != null)
        {
            warehouseId = Integer.parseInt((String)lstItemWarehouse.getValue());
            warehouseKNPair = new KeyNamePair(warehouseId, lstItemWarehouse.getLabel());
        }

        String msg = login.loadPreferences(orgKNPair, warehouseKNPair, null, null);
        if(!(msg == null || msg.length() == 0))
        {
            lblErrorMsg.setValue("Error for user login: " + msg);
            return ;
        }
        wndLogin.loginCompleted();
    }

    public boolean isAsap()
    {
        return true;
    }
}
