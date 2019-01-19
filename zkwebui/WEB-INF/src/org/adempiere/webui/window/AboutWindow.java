/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2008 Low Heng Sin. All Rights Reserved.                      *
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
package org.adempiere.webui.window;

import java.util.Vector;

import org.adempiere.webui.component.Button;
import org.adempiere.webui.component.Checkbox;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.ListHead;
import org.adempiere.webui.component.ListHeader;
import org.adempiere.webui.component.Listbox;
import org.adempiere.webui.component.SimpleListModel;
import org.adempiere.webui.component.Tab;
import org.adempiere.webui.component.Tabbox;
import org.adempiere.webui.component.Tabpanel;
import org.adempiere.webui.component.Tabpanels;
import org.adempiere.webui.component.Tabs;
import org.adempiere.webui.component.ToolBarButton;
import org.adempiere.webui.component.Window;
import org.adempiere.webui.theme.ThemeManager;
import org.compiere.Adempiere;
import org.compiere.model.MUser;
import org.compiere.util.CLogErrorBuffer;
import org.compiere.util.CLogMgt;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.zkoss.util.media.AMedia;
import org.zkoss.zhtml.Pre;
import org.zkoss.zhtml.Text;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.SizeEvent;
import org.zkoss.zul.Div;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Image;
import org.zkoss.zul.Separator;
import org.zkoss.zul.Vbox;

/**
 *
 * @author Low Heng Sin
 * @athor Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<li> FR [ 379 ] Change URL in About
 *		@see https://github.com/adempiere/adempiere/issues/379
 *
 */
public class AboutWindow extends Window implements EventListener {

	/**
	 *
	 */
	private static final long serialVersionUID = -257313771447940626L;
	private Checkbox bErrorsOnly;
	private Listbox logTable;
	private Tabbox tabbox;
	private Tabpanels tabPanels;
	private Button btnDownload;
	private Button btnErrorEmail;
	private Tab tabLog;

	public AboutWindow() {
		super();
		init();
	}

	private void init() {
		this.setWidth("500px");
		this.setHeight("490px");
		this.setPosition("center");
		this.setTitle(ThemeManager.getBrowserTitle());
		this.setClosable(true);
		this.setSizable(true);

		this.addEventListener(Events.ON_SIZE, this);

		Vbox layout = new Vbox();
		layout.setWidth("100%");
		layout.setParent(this);

		tabbox = new Tabbox();
		tabbox.setParent(layout);
		tabbox.setWidth("480px");
		tabbox.setHeight("420px");
//		tabbox.setSclass("lite");
		Tabs tabs = new Tabs();
		tabs.setParent(tabbox);
		tabPanels = new Tabpanels();
		tabPanels.setParent(tabbox);
		tabPanels.setWidth("480px");

		//about
		Tab tab = new Tab();
		tab.setLabel(Msg.getMsg(Env.getCtx(), "About"));
		tab.setParent(tabs);
		Tabpanel tabPanel = createAbout();
		tabPanel.setParent(tabPanels);

		//Credit
		tab = new Tab();
		tab.setLabel(Msg.getMsg(Env.getCtx(), "Credits"));
		tab.setParent(tabs);
		tabPanel = createCredit();
		tabPanel.setParent(tabPanels);

		//Info
		tab = new Tab();
		tab.setLabel(Msg.getMsg(Env.getCtx(), "Info"));
		tab.setParent(tabs);
		tabPanel = createInfo();
		tabPanel.setParent(tabPanels);

		//Trace
		tab = new Tab();
		tab.setLabel("Errors");
		tabLog = tab;
		tab.setParent(tabs);
		tabPanel = createTrace();
		tabPanel.setParent(tabPanels);

		Hbox hbox = new Hbox();
		hbox.setParent(layout);
		hbox.setPack("end");
		hbox.setWidth("100%");
		Button btnOk = new Button();
		btnOk.setImage("/images/Ok16.png");
		btnOk.addEventListener(Events.ON_CLICK, this);
		btnOk.setParent(hbox);

		this.setBorder("normal");
	}

	private Tabpanel createTrace() {
		Tabpanel tabPanel = new Tabpanel();
		Vbox vbox = new Vbox();
		vbox.setParent(tabPanel);
		vbox.setWidth("100%");
		vbox.setHeight("100%");

		Hbox hbox = new Hbox();
		bErrorsOnly = new Checkbox();
		bErrorsOnly.setLabel(Msg.getMsg(Env.getCtx(), "ErrorsOnly"));
		//default only show error
		bErrorsOnly.setChecked(true);
		bErrorsOnly.addEventListener(Events.ON_CHECK, this);
		hbox.appendChild(bErrorsOnly);
		btnDownload = new Button(Msg.getMsg(Env.getCtx(), "SaveFile"));
		btnDownload.addEventListener(Events.ON_CLICK, this);
		hbox.appendChild(btnDownload);
		btnErrorEmail = new Button(Msg.getMsg(Env.getCtx(), "SendEMail"));
		btnErrorEmail.addEventListener(Events.ON_CLICK, this);
		hbox.appendChild(btnErrorEmail);
		vbox.appendChild(hbox);

		Vector<String> columnNames = CLogErrorBuffer.get(true).getColumnNames(Env.getCtx());

		logTable = new Listbox();
		ListHead listHead = new ListHead();
		listHead.setParent(logTable);
		listHead.setSizable(true);
		for (Object obj : columnNames) {
			ListHeader header = new ListHeader(obj.toString());
			header.setWidth("100px");
			listHead.appendChild(header);
		}

		vbox.appendChild(logTable);
		logTable.setWidth("480px");
		logTable.setHeight("310px");
		logTable.setVflex(false);

		updateLogTable();

		return tabPanel;
	}

	private void updateLogTable() {
		Vector<Vector<Object>> data = CLogErrorBuffer.get(true).getLogData(bErrorsOnly.isChecked());
		SimpleListModel model = new SimpleListModel(data);
		model.setMaxLength(new int[]{0, 0, 0, 200, 0, 200});
		logTable.setItemRenderer(model);
		logTable.setModel(model);
		if (bErrorsOnly.isSelected())
			tabLog.setLabel(Msg.getMsg(Env.getCtx(), "Errors") + " (" + data.size() + ")");
		else
			tabLog.setLabel(Msg.getMsg(Env.getCtx(), "TraceInfo") + " (" + data.size() + ")");
	}

	private Tabpanel createInfo() {
		Tabpanel tabPanel = new Tabpanel();
		Div div = new Div();
		div.setParent(tabPanel);
		div.setHeight("100%");
		div.setStyle("overflow: auto;");
		Pre pre = new Pre();
		pre.setParent(div);
		Text text = new Text(CLogMgt.getInfo(null).toString());
		text.setParent(pre);

		return tabPanel;
	}

	private Tabpanel createCredit() {
		Tabpanel tabPanel = new Tabpanel();
		Vbox vbox = new Vbox();
		vbox.setParent(tabPanel);
		vbox.setWidth("100%");
		Hbox hbox = new Hbox();
		hbox.setParent(vbox);
		ToolBarButton link = new ToolBarButton();
		link.setImage("images/Posterita.jpg");
		link.setParent(hbox);
		link.setHref("http://www.posterita.org");
		link.setTarget("_blank");
		Label label= new Label("Contributed the initial Zk Web Client code.");
		label.setParent(hbox);

		Separator separator = new Separator();
		separator.setParent(vbox);

		Div div = new Div();
		div.setParent(vbox);
		div.setWidth("100%");
		Label caption = new Label("Sponsors");
		caption.setStyle("font-weight: bold;");
		div.appendChild(caption);
		separator = new Separator();
		separator.setBar(true);
		separator.setParent(div);
		Vbox content = new Vbox();
		content.setWidth("100%");
		content.setParent(div);
		link = new ToolBarButton();
		link = new ToolBarButton();

		link.setLabel("e-Evolution");
		link.setHref("http://www.e-evolution.com/");
		link.setTarget("_blank");
		link.setParent(content);

		link = new ToolBarButton();
		link.setLabel("ERPCyA");
		link.setHref("http://www.erpcya.com/");
		link.setTarget("_blank");
		link.setParent(content);

		link = new ToolBarButton();
		link.setLabel("Westfalia IT");
		link.setHref("http://www.westfalia-it.com");
		link.setTarget("_blank");
		link.setParent(content);

		link = new ToolBarButton();
		link.setLabel("Openup Solutions");
		link.setHref("http://www.openupsolutions.com/");
		link.setTarget("_blank");
		link.setParent(content);

		link = new ToolBarButton();
		link.setLabel("Adaxa");
		link.setHref("http://www.adaxa.com/");
		link.setTarget("_blank");
		link.setParent(content);

		link = new ToolBarButton();
		link.setLabel("Walkingtree ");
		link.setHref("http://www.walkingtree.in");
		link.setTarget("_blank");
		link.setParent(content);

		link.setLabel("Sysnova");
		link.setHref("http://www.sysnova.com/");
		link.setTarget("_blank");
		link.setParent(content);

		link = new ToolBarButton();
		link.setLabel("Idalica");
		link.setHref("http://www.idalica.com/");
		link.setTarget("_blank");
		link.setParent(content);

		separator = new Separator();
		separator.setParent(vbox);

		div = new Div();
		div.setParent(vbox);
		div.setWidth("100%");
		caption = new Label("Contributors");
		caption.setStyle("font-weight: bold;");
		div.appendChild(caption);
		separator = new Separator();
		separator.setBar(true);
		separator.setParent(div);
		content = new Vbox();
		content.setWidth("100%");
		content.setParent(div);

		link = new ToolBarButton();
		link.setLabel("Víctor Pérez");
		link.setHref("https://github.com/e-Evolution");
		link.setTarget("_blank");
		link.setParent(content);

		link = new ToolBarButton();
		link.setLabel("Yamel Senih");
		link.setHref("https://github.com/yamelsenih");
		link.setTarget("_blank");
		link.setParent(content);

		link = new ToolBarButton();
		link.setLabel("Mario Calderon");
		link.setHref("https://github.com/marcalwestf");
		link.setTarget("_blank");
		link.setParent(content);

		link = new ToolBarButton();
		link.setLabel("Michael McKay");
		link.setHref("https://github.com/mckayERP");
		link.setTarget("_blank");
		link.setParent(content);

		link = new ToolBarButton();
		link.setLabel("Susanne Calderon");
		link.setHref("https://github.com/SusanneCalderon");
		link.setTarget("_blank");
		link.setParent(content);

		link = new ToolBarButton();
		link.setLabel("Raul Muñoz");
		link.setHref("https://github.com/Raul-mz");
		link.setTarget("_blank");
		link.setParent(content);

		link = new ToolBarButton();
		link.setLabel("Eduardo López");
		link.setHref("https://www.linkedin.com/in/eduardo-lopez-vessena-9076951b/");
		link.setTarget("_blank");
		link.setParent(content);

		link = new ToolBarButton();
		link.setLabel("Nicolás López");
		link.setHref("https://www.linkedin.com/in/nicolas-lopez-94338148/");
		link.setTarget("_blank");
		link.setParent(content);

		link = new ToolBarButton();
		link.setLabel("Raúl Capecce");
		link.setHref("https://github.com/rcapecce");
		link.setTarget("_blank");
		link.setParent(content);

		link = new ToolBarButton();
		link.setLabel("Eugen");
		link.setHref("https://github.com/homebeaver");
		link.setTarget("_blank");
		link.setParent(content);

		link = new ToolBarButton();
		link.setLabel("Ilya Tsaturov");
		link.setHref("https://www.ptsecurity.com");
		link.setTarget("_blank");
		link.setParent(content);

		link = new ToolBarButton();
		link.setLabel("Carlos Parada");
		link.setHref("https://github.com/carlosaparadam");
		link.setTarget("_blank");
		link.setParent(content);

		link = new ToolBarButton();
		link.setLabel("Paul Bowden");
		link.setHref("http://wiki.adempiere.net/User:Phib");
		link.setTarget("_blank");
		link.setParent(content);

		link = new ToolBarButton();
		link.setLabel("Ashley G Ramdass");
		link.setHref("http://wiki.adempiere.net/User:Agramdass");
		link.setTarget("_blank");
		link.setParent(content);

		link = new ToolBarButton();
		link.setLabel("Low Heng Sin");
		link.setHref("http://wiki.adempiere.net/User:Hengsin");
		link.setTarget("_blank");
		link.setParent(content);

		link = new ToolBarButton();
		link.setLabel("Carlos Ruiz");
		link.setHref("http://wiki.adempiere.net/User:CarlosRuiz");
		link.setTarget("_blank");
		link.setParent(content);

		link = new ToolBarButton();
		link.setLabel("Teo Sarca");
		link.setHref("http://wiki.adempiere.net/User:Teo_sarca");
		link.setTarget("_blank");
		link.setParent(content);

		link = new ToolBarButton();
		link.setLabel("Trifon Trifonov");
		link.setHref("http://wiki.adempiere.net/User:Trifonnt");
		link.setTarget("_blank");
		link.setParent(content);

		return tabPanel;
	}

	private Tabpanel createAbout() {
		Tabpanel tabPanel = new Tabpanel();

		Vbox vbox = new Vbox();
		vbox.setWidth("100%");
		vbox.setHeight("100%");
		vbox.setAlign("center");
		vbox.setPack("center");
		vbox.setParent(tabPanel);

		Image image = new Image(ThemeManager.getSmallLogo());
		image.setParent(vbox);

		Text text = new Text(Adempiere.getSubtitle());
		text.setParent(vbox);
		Separator separator = new Separator();
		separator.setParent(vbox);

		text = new Text(Adempiere.getVersion());
		text.setParent(vbox);

		separator = new Separator();
		separator.setParent(vbox);
		ToolBarButton link = new ToolBarButton();
		link.setLabel("Github Project Site");
		link.setHref("http://github.com/adempiere");
		link.setTarget("_blank");
		link.setParent(vbox);

		separator = new Separator();
		separator.setParent(vbox);
		link = new ToolBarButton();
		link.setLabel("ADempiere.io");
		link.setHref("http://www.adempiere.io");
		link.setTarget("_blank");
		link.setParent(vbox);

		separator = new Separator();
		separator.setParent(vbox);
		link = new ToolBarButton();
		link.setLabel("ADempiere.net");
		link.setHref("http://www.adempiere.net");
		link.setTarget("_blank");
		link.setParent(vbox);

		separator = new Separator();
		separator.setParent(vbox);
		link = new ToolBarButton();
		link.setLabel("ADempiere Wiki");
		link.setHref("http://wiki.adempiere.net");
		link.setTarget("_blank");
		link.setParent(vbox);

		separator = new Separator();
		separator.setParent(vbox);
		link = new ToolBarButton();
		link.setLabel("ADempiere Documentation");
		link.setHref("http://docs.adempiere.io/");
		link.setTarget("_blank");
		link.setParent(vbox);

		return tabPanel;
	}

	public void onEvent(Event event) throws Exception {
		if (event.getTarget() == bErrorsOnly) {
			this.updateLogTable();
		}
		else if (event.getTarget() == btnDownload)
			downloadLog();
		else if (event.getTarget() == btnErrorEmail)
			cmd_errorEMail();
		else if (event instanceof SizeEvent)
			doResize((SizeEvent)event);
		else if (Events.ON_CLICK.equals(event.getName()))
			this.detach();
	}

	private void doResize(SizeEvent event) {
		int width = Integer.parseInt(event.getWidth().substring(0, event.getWidth().length() - 2));
		int height = Integer.parseInt(event.getHeight().substring(0, event.getHeight().length() - 2));

		tabbox.setWidth((width - 20) + "px");
		tabbox.setHeight((height - 70) + "px");

		tabPanels.setWidth((width - 20) + "px");

		logTable.setHeight((height - 140) + "px");
		logTable.setWidth((width - 30) + "px");
	}

	private void downloadLog() {
		String log = CLogErrorBuffer.get(true).getErrorInfo(Env.getCtx(), bErrorsOnly.isChecked());
		AMedia media = new AMedia("trace.log", null, "text/plain", log.getBytes());
		Filedownload.save(media);
	}

	/**
	 * 	EMail Errors
	 */
	private void cmd_errorEMail()
	{
		new WEMailDialog(this,
			"EMail Trace",
			MUser.get(Env.getCtx()),
			"",			//	to
			"Adempiere Trace Info",
			CLogErrorBuffer.get(true).getErrorInfo(Env.getCtx(), bErrorsOnly.isSelected()),
			null);

	}	//	cmd_errorEMail
}
