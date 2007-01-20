/*
 * Class JRViewer.
 */
package org.compiere.report;

import net.sf.jasperreports.view.JRViewer;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporter;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;

public class JasperReportViewer extends JRViewer {

	private static final long serialVersionUID = -7988455595896562947L;
	
	private JasperPrint jasperPrint;
    private JComboBox comboBox;

    public JasperReportViewer(final JasperPrint jasperPrint) throws JRException {
        super( jasperPrint);
        this.jasperPrint = jasperPrint;
        JButton btnExport = new JButton();
        btnExport.setToolTipText("Export to");
        btnExport.setText("Export to");
        btnExport.setPreferredSize(new java.awt.Dimension(70, 23));
        btnExport.setMaximumSize(new java.awt.Dimension(70, 23));
        btnExport.setMinimumSize(new java.awt.Dimension(70, 23));
        btnExport.addActionListener( new ExportListener( this));
       // tlbToolBar.addSeparator();
        tlbToolBar.add(btnExport);
        comboBox = new JComboBox( new String[] {"PDF","HTML", "XLS"});
        comboBox.setPreferredSize(new java.awt.Dimension(70, 23));
        comboBox.setMaximumSize(new java.awt.Dimension(70, 23));
        comboBox.setMinimumSize(new java.awt.Dimension(70, 23));
        tlbToolBar.add(comboBox);
    }

    public JasperPrint getJasperPrint() {
        return jasperPrint;
    }

    public String getFormat() {
        return (String) comboBox.getSelectedItem();
    }
}

class ExportListener implements ActionListener {
    private JasperReportViewer viewer;

    public ExportListener(JasperReportViewer viewer) {
        this.viewer = viewer;
    }

    public void actionPerformed(ActionEvent event) {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showSaveDialog( viewer)==JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                if (viewer.getFormat().equals("PDF")) {
                    JasperExportManager.exportReportToPdfFile( viewer.getJasperPrint(), file.getAbsolutePath());
                } else if (viewer.getFormat().equals("HTML")) {
                    JasperExportManager.exportReportToHtmlFile( viewer.getJasperPrint(), file.getAbsolutePath());
                } else if (viewer.getFormat().equals("XLS")) {
                    JRXlsExporter exporter = new  net.sf.jasperreports.engine.export.JRXlsExporter();
                    exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, file.getAbsolutePath());
                    exporter.setParameter(JRExporterParameter.JASPER_PRINT, viewer.getJasperPrint());
                    exporter.exportReport();
                }
            } catch (JRException e) {
                e.printStackTrace();
            }
        }
    }
}
