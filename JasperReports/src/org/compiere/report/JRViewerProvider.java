package org.compiere.report;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;

public interface JRViewerProvider {

	public void openViewer(JasperPrint jasperPrint, String title) throws JRException;
}
