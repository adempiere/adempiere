package org.compiere.report;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;

public class SwingJRViewerProvider implements JRViewerProvider {

	public void openViewer(JasperPrint jasperPrint, String title) throws JRException {
		JasperViewer jasperViewer = new JasperViewer( jasperPrint, title);
		jasperViewer.setExtendedState(jasperViewer.getExtendedState() | javax.swing.JFrame.MAXIMIZED_BOTH);
        jasperViewer.setVisible(true);
	}

}
