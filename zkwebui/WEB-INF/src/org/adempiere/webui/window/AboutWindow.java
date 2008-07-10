package org.adempiere.webui.window;

import org.adempiere.webui.component.Button;
import org.adempiere.webui.component.ToolBarButton;
import org.adempiere.webui.component.Window;
import org.compiere.Adempiere;
import org.zkoss.zhtml.Text;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Separator;

public class AboutWindow extends Window implements EventListener {

	public AboutWindow() {
		super();
		init();
	}

	private void init() {
		Text text = new Text("ADempiere ERP & CRM");
		text.setParent(this);
		Separator separator = new Separator();
		separator.setParent(this);
		
		text = new Text(Adempiere.MAIN_VERSION + "<br>");
		text.setParent(this);
		text = new Text(Adempiere.DB_VERSION +"<br>");
		text.setParent(this);
		
		separator = new Separator();
		separator.setParent(this);
		ToolBarButton link = new ToolBarButton();
		link.setLabel("Sourceforge.net Project Site");
		link.setHref("http://www.sourceforge.net/projects/adempiere");
		link.setTarget("_blank");
		link.setParent(this);
		
		separator = new Separator();
		separator.setParent(this);
		link = new ToolBarButton();
		link.setLabel("ADempiere Wiki");
		link.setHref("http://www.adempiere.com/wiki/index.php");
		link.setTarget("_blank");
		link.setParent(this);
		
		separator = new Separator();
		separator.setParent(this);
		link = new ToolBarButton();
		link.setLabel("ADempiere Bazaar");
		link.setHref("http://www.adempiere.org");
		link.setTarget("_blank");
		link.setParent(this);
		
		separator = new Separator();
		separator.setParent(this);
		link = new ToolBarButton();
		link.setLabel("ADempiere.com");
		link.setHref("http://www.adempiere.com");
		link.setTarget("_blank");
		link.setParent(this);
		
		separator = new Separator();
		separator.setBar(true);
		separator.setHeight("20px");
		separator.setParent(this);
		
		text = new Text("Initial code contributed by: ");
		text.setParent(this);
		link = new ToolBarButton();
		link.setLabel("Posterita");
		link.setHref("http://www.posterita.org");
		link.setTarget("_blank");
		link.setParent(this);
		separator = new Separator();
		separator.setParent(this);
		
		separator = new Separator();
		separator.setParent(this);
		
		Button btnOk = new Button();
		btnOk.setLabel("OK");
		btnOk.setImage("/images/Ok24.gif");
		btnOk.addEventListener(Events.ON_CLICK, this);
		btnOk.setParent(this);
		
		this.setBorder("normal");
	}

	public void onEvent(Event event) throws Exception {
		this.detach();
	}
	
}
