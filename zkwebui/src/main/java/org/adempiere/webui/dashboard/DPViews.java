/******************************************************************************
 * Copyright (C) 2008 Elaine Tan                                              *
 * Copyright (C) 2008 Idalica Corporation
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
package org.adempiere.webui.dashboard;

import org.adempiere.webui.apps.AEnv;
import org.adempiere.webui.component.ToolBarButton;
import org.adempiere.webui.panel.InfoPanel;
import org.adempiere.webui.theme.ITheme;
import org.adempiere.webui.window.InfoSchedule;
import org.compiere.model.MRole;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Util;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Box;
import org.zkoss.zul.Separator;
import org.zkoss.zul.Vbox;

/**
 * Dashboard item: Info views
 * @author Elaine
 * @date November 20, 2008
 */
public class DPViews extends DashboardPanel implements EventListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8375414665766937581L;

	public DPViews()
	{
		super();
        this.appendChild(createViewPanel());
	}
	
	private Box createViewPanel()
	{
		Vbox vbox = new Vbox();
				
		if (MRole.getDefault().isAllow_Info_Product())
		{
			ToolBarButton btnViewItem = new ToolBarButton("InfoProduct");
			btnViewItem.setLabel(Util.cleanAmp(Msg.getMsg(Env.getCtx(), "InfoProduct")));
			btnViewItem.setImage(ITheme.IMAGE_FOLDER_DARK + "InfoProduct16.png");
			btnViewItem.addEventListener(Events.ON_CLICK, this);
			vbox.appendChild(btnViewItem);
		}
		if (MRole.getDefault().isAllow_Info_BPartner())
		{
			ToolBarButton btnViewItem = new ToolBarButton("InfoBPartner");
			btnViewItem.setLabel(Util.cleanAmp(Msg.getMsg(Env.getCtx(), "InfoBPartner")));
			btnViewItem.setImage(ITheme.IMAGE_FOLDER_DARK + "InfoBPartner16.png");
			btnViewItem.addEventListener(Events.ON_CLICK, this);
			vbox.appendChild(btnViewItem);
		}
		if (MRole.getDefault().isShowAcct() && MRole.getDefault().isAllow_Info_Account())
		{
			ToolBarButton btnViewItem = new ToolBarButton("InfoAccount");
			btnViewItem.setLabel(Util.cleanAmp(Msg.getMsg(Env.getCtx(), "InfoAccount")));
			btnViewItem.setImage(ITheme.IMAGE_FOLDER_DARK + "InfoAccount16.png");
			btnViewItem.addEventListener(Events.ON_CLICK, this);
			vbox.appendChild(btnViewItem);
		}
		if (MRole.getDefault().isAllow_Info_Schedule())
		{
			ToolBarButton btnViewItem = new ToolBarButton("InfoSchedule");
			btnViewItem.setLabel(Util.cleanAmp(Msg.getMsg(Env.getCtx(), "InfoSchedule")));
			btnViewItem.setImage(ITheme.IMAGE_FOLDER_DARK + "InfoSchedule16.png");
			btnViewItem.addEventListener(Events.ON_CLICK, this);
			vbox.appendChild(btnViewItem);
		}
		vbox.appendChild(new Separator("horizontal"));
		if (MRole.getDefault().isAllow_Info_Order())
		{
			ToolBarButton btnViewItem = new ToolBarButton("InfoOrder");
			btnViewItem.setLabel(Util.cleanAmp(Msg.getMsg(Env.getCtx(), "InfoOrder")));
			btnViewItem.setImage(ITheme.IMAGE_FOLDER_DARK + "Info16.png");
			btnViewItem.addEventListener(Events.ON_CLICK, this);
			vbox.appendChild(btnViewItem);
		}
		if (MRole.getDefault().isAllow_Info_Invoice())
		{
			ToolBarButton btnViewItem = new ToolBarButton("InfoInvoice");
			btnViewItem.setLabel(Util.cleanAmp(Msg.getMsg(Env.getCtx(), "InfoInvoice")));
			btnViewItem.setImage(ITheme.IMAGE_FOLDER_DARK + "Info16.png");
			btnViewItem.addEventListener(Events.ON_CLICK, this);
			vbox.appendChild(btnViewItem);
		}
		if (MRole.getDefault().isAllow_Info_InOut())
		{
			ToolBarButton btnViewItem = new ToolBarButton("InfoInOut");
			btnViewItem.setLabel(Util.cleanAmp(Msg.getMsg(Env.getCtx(), "InfoInOut")));
			btnViewItem.setImage(ITheme.IMAGE_FOLDER_DARK + "Info16.png");
			btnViewItem.addEventListener(Events.ON_CLICK, this);
			vbox.appendChild(btnViewItem);
		}
		if (MRole.getDefault().isAllow_Info_Payment())
		{
			ToolBarButton btnViewItem = new ToolBarButton("InfoPayment");
			btnViewItem.setLabel(Util.cleanAmp(Msg.getMsg(Env.getCtx(), "InfoPayment")));
			btnViewItem.setImage(ITheme.IMAGE_FOLDER_DARK + "Info16.png");
			btnViewItem.addEventListener(Events.ON_CLICK, this);
			vbox.appendChild(btnViewItem);
		}
		if (MRole.getDefault().isAllow_Info_CashJournal())
		{
			ToolBarButton btnViewItem = new ToolBarButton("InfoCashLine");
			btnViewItem.setLabel(Util.cleanAmp(Msg.getMsg(Env.getCtx(), "InfoCashLine")));
			btnViewItem.setImage(ITheme.IMAGE_FOLDER_DARK + "Info16.png");
			btnViewItem.addEventListener(Events.ON_CLICK, this);
			vbox.appendChild(btnViewItem);
		}
		if (MRole.getDefault().isAllow_Info_Resource())
		{
			ToolBarButton btnViewItem = new ToolBarButton("InfoAssignment");
			btnViewItem.setLabel(Util.cleanAmp(Msg.getMsg(Env.getCtx(), "InfoAssignment")));
			btnViewItem.setImage(ITheme.IMAGE_FOLDER_DARK + "Info16.png");
			btnViewItem.addEventListener(Events.ON_CLICK, this);
			vbox.appendChild(btnViewItem);
		}
		if (MRole.getDefault().isAllow_Info_Asset())
		{
			ToolBarButton btnViewItem = new ToolBarButton("InfoAsset");
			btnViewItem.setLabel(Util.cleanAmp(Msg.getMsg(Env.getCtx(), "InfoAsset")));
			btnViewItem.setImage(ITheme.IMAGE_FOLDER_DARK + "Info16.png");
			btnViewItem.addEventListener(Events.ON_CLICK, this);
			vbox.appendChild(btnViewItem);
		}
		
		return vbox;
	}
        
    public void onEvent(Event event)
    {
        Component comp = event.getTarget();
        String eventName = event.getName();
        
        if(eventName.equals(Events.ON_CLICK))
        {
            if(comp instanceof ToolBarButton)
            {
            	ToolBarButton btn = (ToolBarButton) comp;
        		String actionCommand = btn.getName();
        		int WindowNo = 0;
        		
        		if (actionCommand.equals("InfoProduct") && AEnv.canAccessInfo("PRODUCT"))
        		{
        			InfoPanel.showProduct(WindowNo);
        		}
        		else if (actionCommand.equals("InfoBPartner") && AEnv.canAccessInfo("BPARTNER"))
        		{
        			InfoPanel.showBPartner(WindowNo);
        		}
        		else if (actionCommand.equals("InfoAsset") && AEnv.canAccessInfo("ASSET"))
        		{
        			InfoPanel.showAsset(WindowNo);
        		}
        		else if (actionCommand.equals("InfoAccount") && 
        				  MRole.getDefault().isShowAcct() &&
        				  AEnv.canAccessInfo("ACCOUNT"))
        		{
        			new org.adempiere.webui.acct.WAcctViewer();
        		}
        		else if (actionCommand.equals("InfoSchedule") && AEnv.canAccessInfo("SCHEDULE"))
        		{
        			new InfoSchedule(null, false);
        		}
        		else if (actionCommand.equals("InfoOrder") && AEnv.canAccessInfo("ORDER"))
        		{
        			InfoPanel.showOrder(WindowNo, "");
        		}
        		else if (actionCommand.equals("InfoInvoice") && AEnv.canAccessInfo("INVOICE"))
        		{
        			InfoPanel.showInvoice(WindowNo, "");
        		}
        		else if (actionCommand.equals("InfoInOut") && AEnv.canAccessInfo("INOUT"))
        		{
        			InfoPanel.showInOut(WindowNo, "");
        		}
        		else if (actionCommand.equals("InfoPayment") && AEnv.canAccessInfo("PAYMENT"))
        		{
        			InfoPanel.showPayment(WindowNo, "");
        		}
        		else if (actionCommand.equals("InfoCashLine") && AEnv.canAccessInfo("CASHJOURNAL"))
        		{
        			InfoPanel.showCashLine(WindowNo, "");
        		}
        		else if (actionCommand.equals("InfoAssignment") && AEnv.canAccessInfo("RESOURCE"))
        		{
        			InfoPanel.showAssignment(WindowNo, "");
        		}
            }
        }
	}
}
