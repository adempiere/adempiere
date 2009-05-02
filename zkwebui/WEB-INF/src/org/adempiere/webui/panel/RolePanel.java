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
import java.util.ResourceBundle;

import org.adempiere.webui.component.ConfirmPanel;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.Window;
import org.adempiere.webui.exception.ApplicationException;
import org.adempiere.webui.session.SessionManager;
import org.adempiere.webui.util.UserPreference;
import org.adempiere.webui.window.LoginWindow;
import org.compiere.db.CConnection;
import org.compiere.model.MRole;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Language;
import org.compiere.util.Login;
import org.zkoss.zk.au.out.AuFocus;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Button;
import org.zkoss.zul.Div;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Image;
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
    /**
	 * 
	 */
	private static final long serialVersionUID = 2808473294679524383L;

	private static final String RESOURCE = "org.compiere.apps.ALoginRes";

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
        
        //set app server credentials
        CConnection.get().setAppServerCredential(userName, password);
        
        initComponents();
        init();
        this.setId("rolePanel");
        
        AuFocus auf = new AuFocus(lstRole);
        Clients.response(auf);
    }

    private void init()
    {
        Grid grid = new Grid();
        grid.setId("grdChooseRole");
        grid.setOddRowSclass("even");
        Rows rows = new Rows();
        
        Row logo = new Row();
        logo.setSpans("2");
        Image image = new Image();
        image.setSrc("images/logo.png");
        logo.appendChild(image);
        
        Row rowRole = new Row();
        Row rowClient = new Row();
        Row rowOrg = new Row();
        Row rowWarehouse = new Row();

        rowRole.appendChild(lblRole.rightAlign());
        rowRole.appendChild(lstRole);

        rowClient.appendChild(lblClient.rightAlign());
        rowClient.appendChild(lstClient);

        rowOrg.appendChild(lblOrganisation.rightAlign());
        rowOrg.appendChild(lstOrganisation);

        rowWarehouse.appendChild(lblWarehouse.rightAlign());
        rowWarehouse.appendChild(lstWarehouse);

        Row rowButtons = new Row();
        ConfirmPanel pnlButtons = new ConfirmPanel(true);
        pnlButtons.addActionListener(this);
        rowButtons.setSpans("2");
        rowButtons.appendChild(pnlButtons);

        rows.appendChild(logo);
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
    	Language language = Env.getLanguage(Env.getCtx());
    	
    	ResourceBundle res = ResourceBundle.getBundle(RESOURCE, language.getLocale());
    	
        lblErrorMsg = new Label();
        lblErrorMsg.setValue(" ");

        lblRole = new Label();
        lblRole.setId("lblRole");
        lblRole.setValue(res.getString("Role"));

        lblClient = new Label();
        lblClient.setId("lblClient");
        lblClient.setValue(res.getString("Client"));

        lblOrganisation = new Label();
        lblOrganisation.setId("lblOrganisation");
        lblOrganisation.setValue(res.getString("Organization"));

        lblWarehouse = new Label();
        lblWarehouse.setId("lblWarehouse");
        lblWarehouse.setValue(res.getString("Warehouse"));

        lstRole = new Listbox();
        lstRole.setId("lstRole");
        lstRole.setRows(1);
        lstRole.setMold("select");
        lstRole.addEventListener(Events.ON_SELECT, this);
        lstRole.setWidth("220px");

        lstClient = new Listbox();
        lstClient.setId("lstClient");
        lstClient.setRows(1);
        lstClient.setMold("select");
        lstClient.addEventListener(Events.ON_SELECT, this);
        lstClient.setWidth("220px");

        lstOrganisation = new Listbox();
        lstOrganisation.setId("lstOrganisation");
        lstOrganisation.setRows(1);
        lstOrganisation.setMold("select");
        lstOrganisation.addEventListener(Events.ON_SELECT, this);
        lstOrganisation.setWidth("220px");

        lstWarehouse = new Listbox();
        lstWarehouse.setId("lstWarehouse");
        lstWarehouse.setRows(1);
        lstWarehouse.setMold("select");
        lstWarehouse.addEventListener(Events.ON_SELECT, this);
        lstWarehouse.setWidth("220px");

        btnOk = new Button();
        btnOk.setId("btnOk");
        btnOk.setLabel("Ok");
        btnOk.addEventListener("onClick", this);

        btnCancel = new Button();
        btnCancel.setId("btnCancel");
        btnCancel.setLabel("Cancel");
        btnCancel.addEventListener("onClick", this);

        // initial role - Elaine 2009/02/06
        UserPreference userPreference = SessionManager.getSessionApplication().getUserPreference();
        String initDefault = userPreference.getProperty(UserPreference.P_ROLE);
        for(int i = 0; i < rolesKNPairs.length; i++)
        {
        	Listitem li = lstRole.appendItem(rolesKNPairs[i].getName(), rolesKNPairs[i].getID());
        	if(rolesKNPairs[i].getID().equals(initDefault))
        		lstRole.setSelectedItem(li);
        }
        if (lstRole.getSelectedIndex() == -1)
        	lstRole.setSelectedIndex(0);
        //
        updateClientList();
    }

    private void updateClientList()
    {
        lstClient.getItems().clear();
        Listitem lstItemRole = lstRole.getSelectedItem();
        if(lstItemRole != null)
        {
        	//  initial client - Elaine 2009/02/06
        	UserPreference userPreference = SessionManager.getSessionApplication().getUserPreference();
			String initDefault = userPreference.getProperty(UserPreference.P_CLIENT);
            KeyNamePair roleKNPair = new KeyNamePair(new Integer((String)lstItemRole.getValue()), lstItemRole.getLabel());
            KeyNamePair clientKNPairs[] = login.getClients(roleKNPair);
            if(clientKNPairs != null && clientKNPairs.length > 0)
            {
                for(int i = 0; i < clientKNPairs.length; i++)
                {
                	Listitem li = lstClient.appendItem(clientKNPairs[i].getName(), clientKNPairs[i].getID());
                    if(clientKNPairs[i].getID().equals(initDefault))
                    	lstClient.setSelectedItem(li);
                }
                if (lstClient.getSelectedIndex() == -1)
                	lstClient.setSelectedIndex(0);
            }
            //
            
            //force reload of default role
            MRole.getDefault(Env.getCtx(), true);
        }
        updateOrganisationList();
    }

    private void updateOrganisationList()
    {
        lstOrganisation.getItems().clear();
        Listitem lstItemClient = lstClient.getSelectedItem();
        if(lstItemClient != null)
        {
			//  initial organisation - Elaine 2009/02/06
        	UserPreference userPreference = SessionManager.getSessionApplication().getUserPreference();
			String initDefault = userPreference.getProperty(UserPreference.P_ORG);
            KeyNamePair clientKNPair = new KeyNamePair(new Integer((String)lstItemClient.getValue()), lstItemClient.getLabel());
            KeyNamePair orgKNPairs[] = login.getOrgs(clientKNPair);
            if(orgKNPairs != null && orgKNPairs.length > 0)
            {
                for(int i = 0; i < orgKNPairs.length; i++)
                {
                	Listitem li = lstOrganisation.appendItem(orgKNPairs[i].getName(), orgKNPairs[i].getID());
                    if(orgKNPairs[i].getID().equals(initDefault))
                    	lstOrganisation.setSelectedItem(li);
                }
                if (lstOrganisation.getSelectedIndex() == -1)
                	lstOrganisation.setSelectedIndex(0);
            }
            //
        }
        updateWarehouseList();
    }

    private void updateWarehouseList()
    {
        lstWarehouse.getItems().clear();
        Listitem lstItemOrganisation = lstOrganisation.getSelectedItem();
        if(lstItemOrganisation != null)
        {
			//  initial warehouse - Elaine 2009/02/06
        	UserPreference userPreference = SessionManager.getSessionApplication().getUserPreference();
			String initDefault = userPreference.getProperty(UserPreference.P_WAREHOUSE);
            KeyNamePair organisationKNPair = new KeyNamePair(new Integer((String)lstItemOrganisation.getValue()), lstItemOrganisation.getLabel());
            KeyNamePair warehouseKNPairs[] = login.getWarehouses(organisationKNPair);
            if(warehouseKNPairs != null && warehouseKNPairs.length > 0)
            {
                for(int i = 0; i < warehouseKNPairs.length; i++)
                {
                    Listitem li = lstWarehouse.appendItem(warehouseKNPairs[i].getName(), warehouseKNPairs[i].getID());
                    if(warehouseKNPairs[i].getID().equals(initDefault))
                    	lstWarehouse.setSelectedItem(li);
                }
                if (lstWarehouse.getSelectedIndex() == -1)
                	lstWarehouse.setSelectedIndex(0);
            }
            //
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
        if (event.getTarget().getId().equals(ConfirmPanel.A_OK))
        {
            validateRoles();
        }
        else if (event.getTarget().getId().equals(ConfirmPanel.A_CANCEL))
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
        Listitem lstItemWarehouse = lstWarehouse.getSelectedItem();

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

        String msg = login.validateLogin(orgKNPair);
		if (msg != null && msg.length() > 0)
		{
			lblErrorMsg.setValue("Error for user login: " + msg);
			return;
		}
		
        msg = login.loadPreferences(orgKNPair, warehouseKNPair, null, null);
        if(!(msg == null || msg.length() == 0))
        {
            lblErrorMsg.setValue("Error for user login: " + msg);
            return ;
        }
        wndLogin.loginCompleted();
        
        // Elaine 2009/02/06 save preference to AD_Preference
        UserPreference userPreference = SessionManager.getSessionApplication().getUserPreference();
        userPreference.setProperty(UserPreference.P_LANGUAGE, Env.getContext(Env.getCtx(), UserPreference.LANGUAGE_NAME));
        userPreference.setProperty(UserPreference.P_ROLE, lstItemRole != null ? (String) lstItemRole.getValue() : "0");
        userPreference.setProperty(UserPreference.P_CLIENT, lstItemClient != null ? (String) lstItemClient.getValue() : "0");
        userPreference.setProperty(UserPreference.P_ORG, lstItemOrg != null ? (String) lstItemOrg.getValue() : "0");
        userPreference.setProperty(UserPreference.P_WAREHOUSE, lstItemWarehouse != null ? (String) lstItemWarehouse.getValue() : "0");
        userPreference.savePreference();
        //
    }
}
