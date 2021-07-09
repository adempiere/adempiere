/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2021 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * or via info@adempiere.net or http://www.adempiere.net/license.html         *
 *****************************************************************************/
package org.adempiere.webui.desktop;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import java.util.logging.Level;

import org.adempiere.test.CommonUnitTestSetup;
import org.adempiere.webui.dashboard.DashboardRunnable;
import org.adempiere.webui.panel.HeaderPanel;
import org.adempiere.webui.panel.MenuPanel;
import org.adempiere.webui.panel.SidePanel;
import org.adempiere.webui.util.UserPreference;
import org.compiere.model.MDashboardContent;
import org.compiere.model.MRole;
import org.compiere.util.CLogger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.zkoss.zk.ui.AbstractPage;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Desktop;
import org.zkoss.zkex.zul.Borderlayout;
import org.zkoss.zkmax.zul.Portallayout;
import org.zkoss.zul.Row;

@Tag("ZK")
@Tag("DefaultDesktop")
@DisplayName("DefaultDesktop Unit Tests")
class DefaultDesktop_Test extends CommonUnitTestSetup {

    DashboardRunnable dashboardRunnableMock;
    Desktop desktopMock;
    DefaultDesktop desktop;
    SidePanel sidePanelMock;
    MenuPanel menuPanelMock;
    HeaderPanel headerPanelMock;
    UserPreference userPreferenceMock;
    AbstractPage pageMock;
    MDashboardContent dashboardContentMock;
    MRole roleMock;
    CLogger loggerMock;
    Portallayout portalLayout;

    MDashboardContent[] dashboardArray;

    void verifyNoMessagesLogged() {
    
        verify(loggerMock, never()).log(any(Level.class), anyString(),
                any(Exception.class));
    
    }

    void verifyDashboardPanelCreationLoopCreatesNothing() {
    
        verify(dashboardContentMock, never()).getColumnNo();
    
    }

    @BeforeEach
    void localSetup() {

        sidePanelMock = mock(SidePanel.class);
        headerPanelMock = mock(HeaderPanel.class);
        menuPanelMock = mock(MenuPanel.class);
        userPreferenceMock = mock(UserPreference.class);
        desktopMock = mock(Desktop.class);
        pageMock = mock(AbstractPage.class);
        dashboardRunnableMock = mock(DashboardRunnable.class);
        dashboardContentMock = mock(MDashboardContent.class);
        roleMock = mock(MRole.class);
        loggerMock = mock(CLogger.class);
        portalLayout = spy(Portallayout.class);
        doReturn(menuPanelMock).when(sidePanelMock).getMenuPanel();
        doReturn(false).when(desktopMock).isServerPushEnabled();
        doReturn(desktopMock).when(portalLayout).getDesktop();


        desktop = spy(DefaultDesktop.class);
        doReturn(sidePanelMock).when(desktop).createSidePanel();
        doReturn(headerPanelMock).when(desktop).createHeaderPanel();
        doReturn(userPreferenceMock).when(desktop).getUserPreference();
        doReturn(desktopMock).when(portalLayout).getDesktop();
        doReturn(dashboardRunnableMock).when(desktop).createDashboardRunnable();
        doReturn(null).when(desktop).getSysConfigValue(anyString());
        doReturn(1).when(desktop).getSessionColumnCount();
        doReturn(dashboardArray).when(desktop).getDashboardContent();
        doReturn(1).when(dashboardContentMock).getPA_DashboardContent_ID();
        doReturn(portalLayout).when(desktop).createPortalLayout();
        doReturn(roleMock).when(desktop).getDefaultRole();
        doReturn(loggerMock).when(desktop).getLogger();

    }

    @Test
    @DisplayName("Issue #3361 Fix. Given user does not have role access to "
            + "dashboard content, then no dashboard content is created.")
    final void givenRoleHasNoDashboardAccess_thenNoCreation() {

        dashboardArray = new MDashboardContent[] { dashboardContentMock };
        doReturn(dashboardArray).when(desktop).getDashboardContent();
        doReturn(false).when(roleMock).getDashboardAccess(anyInt());
        Row parent = new Row();
        Component comp = desktop.doCreatePart(parent);
        assertTrue(comp instanceof Borderlayout);
        assertEquals(parent, comp.getParent());
        verifyDashboardPanelCreationLoopCreatesNothing();
        verifyNoMessagesLogged();

    }

    @Test
    @DisplayName("Issue #3361 Fix. Given user has no dashboard content defined, "
            + "then no dashboard content is created.")
    final void givenRoleHasNoDashboardContent_thenNoCreation() {

        dashboardArray = new MDashboardContent[] { };
        doReturn(dashboardArray).when(desktop).getDashboardContent();
        Row parent = new Row();
        Component comp = desktop.doCreatePart(parent);
        assertTrue(comp instanceof Borderlayout);
        assertEquals(parent, comp.getParent());
        verifyDashboardPanelCreationLoopCreatesNothing();
        verifyNoMessagesLogged();

    }


}
