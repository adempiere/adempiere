/*
 * Class JRViewer.
 */
package org.compiere.report;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.logging.Level;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.view.JRViewer;

import org.compiere.apps.EMailDialog;
import org.compiere.model.MUser;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Msg;

public class JasperReportViewer extends JRViewer {

	private static final long serialVersionUID = -7988455595896562947L;
	
	/**	Logger */
	private static CLogger log = CLogger.getCLogger(JasperReportViewer.class);
	
	private JasperViewer jasperViewer;
	private JasperPrint jasperPrint;
    private JComboBox comboBox;

    public JasperReportViewer(final JasperViewer jasperViewer, final JasperPrint jasperPrint) throws JRException {
        super( jasperPrint);
		this.jasperViewer = jasperViewer;
        this.jasperPrint = jasperPrint;
		
		tlbToolBar.add(new JSeparator(SwingConstants.VERTICAL));
		JButton btnSendByEmail = new JButton();
        btnSendByEmail.setToolTipText(Msg.getMsg(Env.getCtx(), "SendMail"));
		btnSendByEmail.setText(Msg.getMsg(Env.getCtx(), "SendMail"));
		btnSendByEmail.setPreferredSize(new java.awt.Dimension(85, 23));
        btnSendByEmail.setMaximumSize(new java.awt.Dimension(85, 23));
		btnSendByEmail.setMinimumSize(new java.awt.Dimension(85, 23));
		btnSendByEmail.addActionListener( new SendByEmailListener(jasperViewer, this));
        tlbToolBar.add(btnSendByEmail);
		tlbToolBar.add(new JSeparator(SwingConstants.VERTICAL));
		
		JButton btnExport = new JButton();
        btnExport.setToolTipText(Msg.getMsg(Env.getCtx(), "Export"));
        btnExport.setText(Msg.getMsg(Env.getCtx(), "Export"));
        btnExport.setPreferredSize(new java.awt.Dimension(85, 23));
        btnExport.setMaximumSize(new java.awt.Dimension(85, 23));
        btnExport.setMinimumSize(new java.awt.Dimension(85, 23));
        btnExport.addActionListener( new ExportListener( this));
        tlbToolBar.add(btnExport);
        
		comboBox = new JComboBox( new String[] {"PDF","HTML", "XLS"});
        comboBox.setPreferredSize(new java.awt.Dimension(80, 23));
        comboBox.setMaximumSize(new java.awt.Dimension(80, 23));
        comboBox.setMinimumSize(new java.awt.Dimension(80, 23));
        tlbToolBar.add(comboBox);
		
		// Set default viewer zoom level
		btnFitPage.setSelected(true);
		setZooms();
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
		fileChooser.setSelectedFile(new File(viewer.getJasperPrint().getName() +
				"." + viewer.getFormat().toLowerCase()));
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

class SendByEmailListener implements ActionListener {
	/**	Logger */
	private static CLogger log = CLogger.getCLogger(SendByEmailListener.class);
	
	private JasperViewer jasperViewer;
    private JasperReportViewer viewer;

    public SendByEmailListener(JasperViewer jasperViewer, JasperReportViewer viewer) {
		this.jasperViewer = jasperViewer;
        this.viewer = viewer;
    }

    public void actionPerformed(ActionEvent event) {
        String to = "";
		MUser from = MUser.get(Env.getCtx(), Env.getAD_User_ID(Env.getCtx()));
		String subject = viewer.getJasperPrint().getName();
		String message = "";
		File attachment = null;
		
		try
		{
			attachment = File.createTempFile("mail", ".pdf");
			JasperExportManager.exportReportToPdfFile(viewer.getJasperPrint(), attachment.getAbsolutePath());
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, "", e);
		}
		
		EMailDialog emd = new EMailDialog ((JFrame)jasperViewer,
				Msg.getMsg(Env.getCtx(), "SendMail"),
				from, to, subject, message, attachment);
    }
}
