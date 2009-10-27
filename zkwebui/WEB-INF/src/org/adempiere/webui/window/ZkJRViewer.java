package org.adempiere.webui.window;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;

import org.adempiere.webui.component.Window;
import org.zkoss.util.media.AMedia;
import org.zkoss.zkex.zul.Borderlayout;
import org.zkoss.zkex.zul.Center;
import org.zkoss.zkex.zul.North;
import org.zkoss.zul.Iframe;
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Toolbarbutton;

public class ZkJRViewer extends Window {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7657218073670612078L;
	private JasperPrint jasperPrint;

	public ZkJRViewer(JasperPrint jasperPrint, String title) {
		this.setTitle(title);
		this.jasperPrint = jasperPrint;
		init();
	}
	
	private void init() {
		Borderlayout layout = new Borderlayout();
		layout.setStyle("position: absolute; height: 99%; width: 99%");
		this.appendChild(layout);
		this.setStyle("width: 100%; height: 100%; position: absolute");

		Toolbar toolbar = new Toolbar();
		toolbar.setHeight("26px");
		Toolbarbutton button = new Toolbarbutton();
		button.setImage("/images/Print24.png");
		button.setTooltiptext("Print");
		toolbar.appendChild(button);

		North north = new North();
		layout.appendChild(north);
		north.appendChild(toolbar);

		Center center = new Center();
		center.setFlex(true);
		layout.appendChild(center);
		Iframe iframe = new Iframe();
		iframe.setId("reportFrame");
		iframe.setHeight("100%");
		iframe.setWidth("100%");

		byte[] data = null;
		try {
			data = JasperExportManager.exportReportToPdf(jasperPrint);
		} catch (JRException e) {
			e.printStackTrace();
		}
		AMedia media = new AMedia(getTitle(), "pdf", "application/pdf", data);
		iframe.setContent(media);
		center.appendChild(iframe);

		this.setBorder("normal");
	}		
}
