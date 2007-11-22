package org.adempiere.webui.window;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;

import org.adempiere.webui.component.Grid;
import org.adempiere.webui.component.Row;
import org.adempiere.webui.component.Rows;
import org.adempiere.webui.component.Window;
import org.adempiere.webui.session.SessionManager;
import org.zkoss.util.media.AMedia;
import org.zkoss.zul.Iframe;
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Toolbarbutton;

public class ZkJRViewer extends Window {

	private JasperPrint jasperPrint;

	public ZkJRViewer(JasperPrint jasperPrint, String title) {
		this.setTitle(title);
		this.jasperPrint = jasperPrint;
		init();
	}
	
	private void init() {
		Grid grid = new Grid();
		grid.setWidth("100%");
		Rows rows = new Rows();
		Row row = new Row();
		Toolbar toolbar = new Toolbar();
		toolbar.setHeight("26px");
		Toolbarbutton button = new Toolbarbutton();
		button.setImage("/images/Print24.gif");
		button.setTooltip("Print");
		toolbar.appendChild(button);
		row.appendChild(toolbar);
		rows.appendChild(row);
		
		row = new Row();
		Iframe iframe = new Iframe();
		iframe.setId("reportFrame");
		int height = Double.valueOf(SessionManager.getAppDesktop().getClientInfo().desktopHeight * 0.85).intValue();
		height = height - 30;
		iframe.setHeight(height + "px");
		iframe.setWidth("100%");
		byte[] data = null;
		try {
			data = JasperExportManager.exportReportToPdf(jasperPrint);
		} catch (JRException e) {
			e.printStackTrace();
		}
		AMedia media = new AMedia(getTitle(), "pdf", "application/pdf", data);
		iframe.setContent(media);
		row.appendChild(iframe);
		rows.appendChild(row);
		
		grid.appendChild(rows);
		this.appendChild(grid);
		
		this.setBorder("normal");
	}	
}
