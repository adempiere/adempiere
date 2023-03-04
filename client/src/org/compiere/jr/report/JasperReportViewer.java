package org.compiere.jr.report;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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
import net.sf.jasperreports.engine.export.JRRtfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.swing.JRViewer;
import org.compiere.apps.EMailDialog;
import org.compiere.model.MUser;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Msg;

public class JasperReportViewer extends JRViewer {

	private static final long serialVersionUID = -7988455595896562947L;
	private JasperPrint jasperPrint;
	private JComboBox<Object> comboBox;

	public JasperReportViewer(final JasperViewer jasperViewer, final JasperPrint jasperPrint) throws JRException {
		super( jasperPrint);
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

		comboBox = new JComboBox<Object>( new String[] {"PDF","HTML", "XLS", "RTF"});
		comboBox.setPreferredSize(new java.awt.Dimension(80, 23));
		comboBox.setMaximumSize(new java.awt.Dimension(80, 23));
		comboBox.setMinimumSize(new java.awt.Dimension(80, 23));
		comboBox.addActionListener(new ReportView(this , comboBox ));
		tlbToolBar.add(comboBox);

		// Set default viewer zoom level
		setFitWidthZoomRatio();
		setFitPageZoomRatio();
	}

	public JasperPrint getJasperPrint() {
		return jasperPrint;
	}

	public String getFormat() {
		return (String) comboBox.getSelectedItem();
	}

}

class ReportView implements ActionListener {

	private JasperReportViewer viewer;
	private JComboBox<Object> comboBox;
	/**	Logger */
	private static final CLogger s_log = CLogger.getCLogger(ReportView.class);

	public ReportView(JasperReportViewer viewer, JComboBox<Object> comboBox) {
		this.viewer = viewer;
		this.comboBox = comboBox;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setSelectedFile(new File(viewer.getJasperPrint().getName() +
				"." + viewer.getFormat().toLowerCase()));
		if (fileChooser.showSaveDialog( viewer)==JFileChooser.APPROVE_OPTION) {

			File file = fileChooser.getSelectedFile();

			try {

				if (viewer.getFormat().equals("PDF")) {
					JasperExportManager.exportReportToPdfFile( viewer.getJasperPrint(), file.getAbsolutePath());
				} else if (comboBox.getSelectedItem().equals("HTML")) {
					JasperExportManager.exportReportToHtmlFile( viewer.getJasperPrint(), file.getAbsolutePath());
				} else if (comboBox.getSelectedItem().equals("XLS")) {

					FileOutputStream fos = new FileOutputStream(file);
					JRXlsExporter exporterXLS = new JRXlsExporter();
					exporterXLS.setParameter(JRXlsExporterParameter.JASPER_PRINT, viewer.getJasperPrint());
					exporterXLS.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, fos);
					exporterXLS.setParameter(JRXlsExporterParameter.OUTPUT_FILE, file.getAbsolutePath());
					exporterXLS.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
					exporterXLS.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
					exporterXLS.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE );
					exporterXLS.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS, Boolean.TRUE );
					exporterXLS.setParameter(JRXlsExporterParameter.IS_COLLAPSE_ROW_SPAN, Boolean.TRUE);
					exporterXLS.setParameter(JRXlsExporterParameter.IS_IGNORE_GRAPHICS, Boolean.FALSE);
					exporterXLS.exportReport();
				}
				else if (comboBox.getSelectedItem().equals("RTF"))
				{
					FileOutputStream fos = new FileOutputStream(file);
		            JRRtfExporter rtfExporter = new JRRtfExporter();
		            rtfExporter.setParameter(JRExporterParameter.JASPER_PRINT, viewer.getJasperPrint());
		            rtfExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, fos);
		            rtfExporter.setParameter(JRExporterParameter.CHARACTER_ENCODING, "UTF-8");
		            rtfExporter.exportReport();
				}

			} catch (JRException e) {
				s_log.severe("Failed to generate Excel report : " + e.getMessage() ) ;

			} catch (IOException e) {
				s_log.severe("Failed to generate Excel report : " + e.getMessage() ) ;
			}
		}
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
