package org.adempiere.webui.window;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;

import org.adempiere.webui.apps.AEnv;
import org.adempiere.webui.component.Window;
import org.compiere.report.JRViewerProvider;

public class ZkJRViewerProvider implements JRViewerProvider {

	public void openViewer(JasperPrint jasperPrint, String title)
			throws JRException {
		Window viewer = new ZkJRViewer(jasperPrint, title);
		viewer.setAttribute("mode", "modal");
		viewer.setClosable(true);
		viewer.setWidth("95%");
		AEnv.showWindow(viewer);
	}

}
