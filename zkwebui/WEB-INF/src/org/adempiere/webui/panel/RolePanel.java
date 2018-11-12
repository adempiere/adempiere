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
 *                                                                            *
 * Contributors:                                                              *
 * - Heng Sin Low                                                             *
 *                                                                            *
 * Sponsors:                                                                  *
 * - Idalica Corporation                                                      *
 *****************************************************************************/

package org.adempiere.webui.panel;

import java.util.Properties;
import java.util.ResourceBundle;

import org.adempiere.webui.LayoutUtils;
import org.adempiere.webui.component.ComboItem;
import org.adempiere.webui.component.Combobox;
import org.adempiere.webui.component.ConfirmPanel;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.Window;
import org.adempiere.webui.exception.ApplicationException;
import org.adempiere.webui.session.SessionManager;
import org.adempiere.webui.theme.ITheme;
import org.adempiere.webui.theme.ThemeManager;
import org.adempiere.webui.util.UserPreference;
import org.adempiere.webui.window.LoginWindow;
import org.compiere.model.MRole;
import org.compiere.model.MSysConfig;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Language;
import org.compiere.util.Login;
import org.compiere.util.Msg;
import org.zkoss.zhtml.Table;
import org.zkoss.zhtml.Td;
import org.zkoss.zhtml.Tr;
import org.zkoss.zk.au.out.AuFocus;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.event.Deferrable;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Button;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Div;
import org.zkoss.zul.Image;

/**
 *
 * @author  <a href="mailto:agramdass@gmail.com">Ashley G Ramdass</a>
 * @date    Feb 25, 2007
 * @version $Revision: 0.10 $
 * @author <a href="mailto:sendy.yagambrum@posterita.org">Sendy Yagambrum</a>
 * @author Raul Muñoz, rMunoz@erpya.com , http://www.erpya.com
 * <li> FR [ 1769 ] Add option to restore the password from the login
 * @see https://github.com/adempiere/adempiere/issues/1769
 * @date    July 18, 2007
 */
public class RolePanel extends Window implements EventListener, Deferrable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4485820129703005679L;

	private static final String RESOURCE = "org.compiere.apps.ALoginRes";

    private LoginWindow wndLogin;
    private Login login;
    private KeyNamePair rolesKNPairs[];

    private Combobox lstRole, lstClient, lstOrganisation, lstWarehouse;
    private Label lblRole, lblClient, lblOrganisation, lblWarehouse;
    private Button btnOk, btnCancel;

	/** Context					*/
	private Properties      m_ctx;
	/** Username					*/
	private String			m_userName;
	/** Password					*/
	private String			m_password;
	
	public RolePanel(Properties ctx, LoginWindow loginWindow, String userName, String password)
    {
        this.wndLogin = loginWindow;
        m_ctx = ctx;
        m_userName = userName;
        m_password = password;
        login = new Login(ctx);
        rolesKNPairs = login.getRoles(userName, password);
        if(rolesKNPairs == null)
            throw new ApplicationException("Login is invalid, UserName: " + userName + " and Password:" + password);

        initComponents();
        init();
        this.setId("rolePanel");

        AuFocus auf = new AuFocus(lstRole);
        Clients.response(auf);
    }

    private void init()
    {
    	Div div = new Div();
    	div.setSclass(ITheme.LOGIN_BOX_HEADER_CLASS);
    	Label label = new Label("Login");
    	label.setSclass(ITheme.LOGIN_BOX_HEADER_TXT_CLASS);
    	div.appendChild(label);
    	this.appendChild(div);

        Table table = new Table();
        table.setId("grdChooseRole");
        table.setDynamicProperty("cellpadding", "0");
    	table.setDynamicProperty("cellspacing", "5");
    	table.setSclass(ITheme.LOGIN_BOX_BODY_CLASS);

    	this.appendChild(table);

    	Tr tr = new Tr();
    	table.appendChild(tr);
    	Td td = new Td();
    	td.setSclass(ITheme.LOGIN_BOX_HEADER_LOGO_CLASS);
    	tr.appendChild(td);
    	td.setDynamicProperty("colspan", "2");
    	Image image = new Image();
        image.setSrc(ThemeManager.getLargeLogo());
        td.appendChild(image);

        tr = new Tr();
        tr.setId("rowRole");
        table.appendChild(tr);
    	td = new Td();
    	tr.appendChild(td);
    	td.setSclass(ITheme.LOGIN_LABEL_CLASS);
    	td.appendChild(lblRole.rightAlign());
    	td = new Td();
    	td.setSclass(ITheme.LOGIN_FIELD_CLASS);
    	tr.appendChild(td);
    	td.appendChild(lstRole);

    	tr = new Tr();
        tr.setId("rowclient");
        table.appendChild(tr);
    	td = new Td();
    	tr.appendChild(td);
    	td.setSclass(ITheme.LOGIN_LABEL_CLASS);
    	td.appendChild(lblClient.rightAlign());
    	td = new Td();
    	td.setSclass(ITheme.LOGIN_FIELD_CLASS);
    	tr.appendChild(td);
    	td.appendChild(lstClient);

    	tr = new Tr();
        tr.setId("rowOrganisation");
        table.appendChild(tr);
    	td = new Td();
    	tr.appendChild(td);
    	td.setSclass(ITheme.LOGIN_LABEL_CLASS);
    	td.appendChild(lblOrganisation.rightAlign());
    	td = new Td();
    	td.setSclass(ITheme.LOGIN_FIELD_CLASS);
    	tr.appendChild(td);
    	td.appendChild(lstOrganisation);

    	tr = new Tr();
        tr.setId("rowWarehouse");
        table.appendChild(tr);
    	td = new Td();
    	tr.appendChild(td);
    	td.setSclass(ITheme.LOGIN_LABEL_CLASS);
    	td.appendChild(lblWarehouse.rightAlign());
    	td = new Td();
    	td.setSclass(ITheme.LOGIN_FIELD_CLASS);
    	tr.appendChild(td);
    	td.appendChild(lstWarehouse);

    	div = new Div();
    	div.setSclass(ITheme.LOGIN_BOX_FOOTER_CLASS);
        ConfirmPanel pnlButtons = new ConfirmPanel(true);
        pnlButtons.addActionListener(this);
        LayoutUtils.addSclass(ITheme.LOGIN_BOX_FOOTER_PANEL_CLASS, pnlButtons);
        pnlButtons.setWidth(null);
        pnlButtons.getButton(ConfirmPanel.A_OK).setSclass(ITheme.LOGIN_BUTTON_CLASS);
        pnlButtons.getButton(ConfirmPanel.A_CANCEL).setSclass(ITheme.LOGIN_BUTTON_CLASS);
        div.appendChild(pnlButtons);
        this.appendChild(div);
    }

    private void initComponents()
    {
    	Language language = Env.getLanguage(m_ctx);

    	ResourceBundle res = ResourceBundle.getBundle(RESOURCE, language.getLocale());

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

        lstRole = new Combobox();
        lstRole.setAutocomplete(true);
        lstRole.setAutodrop(true);
        lstRole.setId("lstRole");
        lstRole.addEventListener(Events.ON_SELECT, this);
        lstRole.setWidth("220px");

        lstClient = new Combobox();
        lstClient.setAutocomplete(true);
        lstClient.setAutodrop(true);
        lstClient.setId("lstClient");
        lstClient.addEventListener(Events.ON_SELECT, this);
        lstClient.setWidth("220px");

        lstOrganisation = new Combobox();
        lstOrganisation.setAutocomplete(true);
        lstOrganisation.setAutodrop(true);
        lstOrganisation.setId("lstOrganisation");
        lstOrganisation.addEventListener(Events.ON_SELECT, this);
        lstOrganisation.setWidth("220px");

        lstWarehouse = new Combobox();
        lstWarehouse.setAutocomplete(true);
        lstWarehouse.setAutodrop(true);
        lstWarehouse.setId("lstWarehouse");
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
        updateRoleList();
    }

	/**
	 * 
	 */
	private void updateRoleList()
	{
		lstRole.getItems().clear();
		UserPreference userPreference = SessionManager.getSessionApplication().getUserPreference();
        String initDefault = userPreference.getProperty(UserPreference.P_ROLE);
        for(int i = 0; i < rolesKNPairs.length; i++)
        {
        	ComboItem ci = new ComboItem(rolesKNPairs[i].getName(), rolesKNPairs[i].getID());
        	lstRole.appendChild(ci);
        	if(rolesKNPairs[i].getID().equals(initDefault))
        		lstRole.setSelectedItem(ci);
        }
        if (lstRole.getSelectedIndex() == -1 && lstRole.getItemCount() > 0)
        	lstRole.setSelectedIndex(0);
        //

		// If we have only one role, we can hide the combobox - metas-2009_0021_AP1_G94
		if (lstRole.getItemCount() == 1 && ! MSysConfig.getBooleanValue("ALogin_ShowOneRole", true))
		{
			lstRole.setSelectedIndex(0);
			lblRole.setVisible(false);
			lstRole.setVisible(false);
		}
		else
		{
			lblRole.setVisible(true);
			lstRole.setVisible(true);
		}

        updateClientList();
    }

    private void updateClientList()
    {
        lstClient.getItems().clear();
        Comboitem lstItemRole = lstRole.getSelectedItem();
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
                	ComboItem ci = new ComboItem(clientKNPairs[i].getName(), clientKNPairs[i].getID());
                	lstClient.appendChild(ci);
                    if(clientKNPairs[i].getID().equals(initDefault))
                    	lstClient.setSelectedItem(ci);
                }
                if (lstClient.getSelectedIndex() == -1 && lstClient.getItemCount() > 0)
                	lstClient.setSelectedIndex(0);
            }
            //

            //force reload of default role
            MRole.getDefault(m_ctx, true);
        }
        login.getRoles(m_userName, m_password);
        updateOrganisationList();
    }

    private void updateOrganisationList()
    {
        lstOrganisation.getItems().clear();
        lstOrganisation.setText("");
        Comboitem lstItemClient = lstClient.getSelectedItem();
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
                	ComboItem ci = new ComboItem(orgKNPairs[i].getName(), orgKNPairs[i].getID());
                	lstOrganisation.appendChild(ci);
                    if(orgKNPairs[i].getID().equals(initDefault))
                    	lstOrganisation.setSelectedItem(ci);

                }
                if (lstOrganisation.getSelectedIndex() == -1 && lstOrganisation.getItemCount() > 0)
                	lstOrganisation.setSelectedIndex(0);
            }
            //
        }
        updateWarehouseList();
    }

    private void updateWarehouseList()
    {
        lstWarehouse.getItems().clear();
        lstWarehouse.setText("");
        Comboitem lstItemOrganisation = lstOrganisation.getSelectedItem();
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
                	ComboItem ci = new ComboItem(warehouseKNPairs[i].getName(), warehouseKNPairs[i].getID());
                	lstWarehouse.appendChild(ci);
                    if(warehouseKNPairs[i].getID().equals(initDefault))
                    	lstWarehouse.setSelectedItem(ci);
                }
                if (lstWarehouse.getSelectedIndex() == -1 && lstWarehouse.getItemCount() > 0)
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
            else if(eventCompId.equals(lstClient.getId())) {
                login.getRoles(m_userName, m_password);
                updateOrganisationList();
            }
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
    	Comboitem lstItemRole = lstRole.getSelectedItem();
    	Comboitem lstItemClient = lstClient.getSelectedItem();
    	Comboitem lstItemOrg = lstOrganisation.getSelectedItem();
    	Comboitem lstItemWarehouse = lstWarehouse.getSelectedItem();

        if(lstItemRole == null || lstItemRole.getValue() == null)
        {
            throw new WrongValueException(lstRole, Msg.getMsg(m_ctx, "FillMandatory") + lblRole.getValue());
        }
        else if(lstItemClient == null || lstItemClient.getValue() == null)
        {
        	throw new WrongValueException(lstClient, Msg.getMsg(m_ctx, "FillMandatory") + lblClient.getValue());
        }
        else if(lstItemOrg == null || lstItemOrg.getValue() == null)
        {
        	throw new WrongValueException(lstOrganisation, Msg.getMsg(m_ctx, "FillMandatory") + lblOrganisation.getValue());
        }
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
			throw new WrongValueException(msg);
		}

        msg = login.loadPreferences(orgKNPair, warehouseKNPair, null, null);
        if(!(msg == null || msg.length() == 0))
        {
        	throw new WrongValueException(msg);
        }
        if(wndLogin.isPassReset()) 
        	Executions.sendRedirect("index.zul");
        else 
        	wndLogin.loginCompleted();

        // Elaine 2009/02/06 save preference to AD_Preference
        UserPreference userPreference = SessionManager.getSessionApplication().getUserPreference();
        userPreference.setProperty(UserPreference.P_LANGUAGE, Env.getContext(m_ctx, UserPreference.LANGUAGE_NAME));
        userPreference.setProperty(UserPreference.P_ROLE, lstItemRole != null ? (String) lstItemRole.getValue() : "0");
        userPreference.setProperty(UserPreference.P_CLIENT, lstItemClient != null ? (String) lstItemClient.getValue() : "0");
        userPreference.setProperty(UserPreference.P_ORG, lstItemOrg != null ? (String) lstItemOrg.getValue() : "0");
        userPreference.setProperty(UserPreference.P_WAREHOUSE, lstItemWarehouse != null ? (String) lstItemWarehouse.getValue() : "0");
        userPreference.savePreference();
        //
    }

	public boolean isDeferrable() {
		return false;
	}	
	
	/**
	 * @param ctx
	 */
	public void changeRole(Properties ctx)
	{
		updateRoleList();
		int AD_Role_ID = Env.getAD_Role_ID(ctx);
		lstRole.setValue(AD_Role_ID);
		updateClientList();
		int AD_Client_ID = Env.getAD_Client_ID(ctx);
		lstClient.setValue(AD_Client_ID);
		updateOrganisationList();
		int AD_Org_ID = Env.getAD_Org_ID(ctx);
		lstOrganisation.setValue(AD_Org_ID);
		updateWarehouseList();
		int M_Warehouse_ID = Env.getContextAsInt(ctx, Env.M_WAREHOUSE_ID);
		lstWarehouse.setValue(M_Warehouse_ID);
	}
}
