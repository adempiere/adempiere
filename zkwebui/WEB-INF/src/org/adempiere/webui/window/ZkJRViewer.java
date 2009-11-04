package org.adempiere.webui.window;

import java.io.File;
import java.util.logging.Level;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.webui.component.Window;
import org.compiere.util.CLogger;
import org.zkoss.util.media.AMedia;
import org.zkoss.zkex.zul.Borderlayout;
import org.zkoss.zkex.zul.Center;
import org.zkoss.zkex.zul.North;
import org.zkoss.zul.Iframe;
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Toolbarbutton;

public class ZkJRViewer extends Window {

	private static final long serialVersionUID = 2021796699437770927L;

	private JasperPrint jasperPrint;

	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(ZkJRViewer.class);
	
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

		try {
			String path = System.getProperty("java.io.tmpdir");
			String prefix = makePrefix(jasperPrint.getName());
			if (log.isLoggable(Level.FINE))
			{
				log.log(Level.FINE, "Path="+path + " Prefix="+prefix);
			}
			File file = File.createTempFile(prefix, ".pdf", new File(path));
			JasperExportManager.exportReportToPdfFile(jasperPrint, file.getAbsolutePath());
			AMedia media = new AMedia(getTitle(), "pdf", "application/pdf", file, true);
			iframe.setContent(media);
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getLocalizedMessage(), e);
			throw new AdempiereException("Failed to render report.", e);
		}		
		center.appendChild(iframe);

		this.setBorder("normal");
	}		
	
	private String makePrefix(String name) {
		StringBuffer prefix = new StringBuffer();
		char[] nameArray = name.toCharArray();
		for (char ch : nameArray) {
			if (Character.isLetterOrDigit(ch)) {
				prefix.append(ch);
			} else {
				prefix.append("_");
			}
		}
		return prefix.toString();
	}
}
