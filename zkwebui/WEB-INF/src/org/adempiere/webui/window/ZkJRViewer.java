package org.adempiere.webui.window;

import java.io.File;
import java.util.logging.Level;

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.webui.apps.AEnv;
import org.adempiere.webui.component.ConfirmPanel;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.ListItem;
import org.adempiere.webui.component.Listbox;
import org.adempiere.webui.component.Window;
import org.compiere.model.MRole;
import org.compiere.model.MUser;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.zkoss.util.media.AMedia;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zkex.zul.Borderlayout;
import org.zkoss.zkex.zul.Center;
import org.zkoss.zkex.zul.North;
import org.zkoss.zul.Div;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Iframe;
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Vbox;

/*
 * @author victor.perez@e-evolution.com, www.e-evolution.com
 * 				<li>FR[3434743] Add Export icon for Jasper Report
 * 				<li>http://sourceforge.net/tracker/?func=detail&aid=3434743&group_id=176962&atid=879335
 */
public class ZkJRViewer extends Window implements EventListener {

	private static final long serialVersionUID = 2021796699437770927L;

	private JasperPrint jasperPrint;

	/** Logger */
	private static CLogger log = CLogger.getCLogger(ZkJRViewer.class);
	// FR[3434743]
	private Toolbarbutton bExport = new Toolbarbutton();
	private Toolbarbutton bSendMail = new Toolbarbutton();
	private boolean m_isCanExport;
	private Window winExportFile = null;
	private ConfirmPanel confirmPanel = new ConfirmPanel(true);
	private Listbox cboType = new Listbox();

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

		// FR[3434743]
		bSendMail.setImage("/images/SendMail24.png");
		bSendMail.setTooltiptext(Msg.getMsg(Env.getCtx(), "SendMail"));
		toolbar.appendChild(bSendMail);
		bSendMail.addEventListener(Events.ON_CLICK, this);

		m_isCanExport = MRole.getDefault().isCanExport();

		if (m_isCanExport) {
			bExport.setImage("/images/ExportX24.png");
			bExport.setTooltiptext(Msg.getMsg(Env.getCtx(), "Export"));
			toolbar.appendChild(bExport);
			bExport.addEventListener(Events.ON_CLICK, this);
		}

		try {
			String path = System.getProperty("java.io.tmpdir");
			String prefix = makePrefix(jasperPrint.getName());
			if (log.isLoggable(Level.FINE)) {
				log.log(Level.FINE, "Path=" + path + " Prefix=" + prefix);
			}
			File file = File.createTempFile(prefix, ".pdf", new File(path));
			JasperExportManager.exportReportToPdfFile(jasperPrint,
					file.getAbsolutePath());
			AMedia media = new AMedia(getTitle(), "pdf", "application/pdf",
					file, true);
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

	// {-> FR[3434743]

	/**************************************************************************
	 * Action Listener
	 * 
	 * @param e
	 *            event
	 */
	public void actionPerformed(Event e) {
		if (e.getTarget() == bSendMail)
			cmd_sendMail();
		else if (e.getTarget() == bExport)
			cmd_export();
	}

	/**
	 * Export
	 * 
	 */
	private void cmd_export() {
		log.config("");
		if (!m_isCanExport) {
			FDialog.error(0, this, "AccessCannotExport", getTitle());
			return;
		}

		if (winExportFile == null) {
			winExportFile = new Window();
			winExportFile.setTitle(Msg.getMsg(Env.getCtx(), "Export") + ": "
					+ getTitle());
			winExportFile.setWidth("450px");
			winExportFile.setClosable(true);
			winExportFile.setBorder("normal");
			winExportFile.setStyle("position:absolute");

			cboType.setMold("select");

			cboType.getItems().clear();
			cboType.appendItem(
					"xml" + " - " + Msg.getMsg(Env.getCtx(), "FileXML"), "xml");
			ListItem li = cboType.appendItem(
					"pdf" + " - " + Msg.getMsg(Env.getCtx(), "FilePDF"), "pdf");
			cboType.appendItem(
					"html" + " - " + Msg.getMsg(Env.getCtx(), "FileHTML"),
					"html");
			cboType.appendItem(
					"csv" + " - " + Msg.getMsg(Env.getCtx(), "FileCSV"), "csv");
			cboType.appendItem(
					"xls" + " - " + Msg.getMsg(Env.getCtx(), "FileXLS"), "xls");
			cboType.setSelectedItem(li);

			Hbox hb = new Hbox();
			Div div = new Div();
			div.setAlign("right");
			div.appendChild(new Label(Msg.getMsg(Env.getCtx(), "FilesOfType")));
			hb.appendChild(div);
			hb.appendChild(cboType);
			cboType.setWidth("100%");

			Vbox vb = new Vbox();
			vb.setWidth("390px");
			winExportFile.appendChild(vb);
			vb.appendChild(hb);
			vb.appendChild(confirmPanel);
			confirmPanel.addActionListener(this);
		}

		AEnv.showCenterScreen(winExportFile);

	} // cmd_export

	@Override
	public void onEvent(Event event) throws Exception {
		if (event.getTarget().getId().equals(ConfirmPanel.A_CANCEL))
			winExportFile.onClose();
		else if (event.getTarget().getId().equals(ConfirmPanel.A_OK))
			exportFile();
		else if (event.getName().equals(Events.ON_CLICK)
				|| event.getName().equals(Events.ON_SELECT))
			actionPerformed(event);

	}

	private void exportFile() {
		try {
			ListItem li = cboType.getSelectedItem();
			if (li == null || li.getValue() == null) {
				FDialog.error(0, winExportFile, "FileInvalidExtension");
				return;
			}

			String ext = li.getValue().toString();
			File file = null;
			AMedia media = null;
			String path = System.getProperty("java.io.tmpdir");
			String prefix = makePrefix(jasperPrint.getName());
			if (log.isLoggable(Level.FINE)) {
				log.log(Level.FINE, "Path=" + path + " Prefix=" + prefix);
			}

			file = File.createTempFile(prefix, "." + ext, new File(path));

			if (ext.equals("pdf")) {
				JasperExportManager.exportReportToPdfFile(jasperPrint,
						file.getAbsolutePath());
				media = new AMedia(getTitle(), "pdf", "application/pdf", file,
						true);
			} else if (ext.equals("xml")) {
				JasperExportManager.exportReportToXmlFile(jasperPrint,
						file.getAbsolutePath(), false);
				media = new AMedia(getTitle(), "xml", "application/xml", file,
						true);
			} else if (ext.equals("csv")) {
				JRCsvExporter exporter = new net.sf.jasperreports.engine.export.JRCsvExporter();
				exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,
						file.getAbsolutePath());
				exporter.setParameter(JRExporterParameter.JASPER_PRINT,
						jasperPrint);
				exporter.exportReport();
				media = new AMedia(getTitle(), "csv", "application/csv", file,
						true);
			} else if (ext.equals("html") || ext.equals("htm")) {
				JasperExportManager.exportReportToHtmlFile(jasperPrint,
						file.getAbsolutePath());
				media = new AMedia(getTitle(), "html", "application/html",
						file, true);
			} else if (ext.equals("xls")) {
				JRXlsExporter exporter = new net.sf.jasperreports.engine.export.JRXlsExporter();
				exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,
						file.getAbsolutePath());
				exporter.setParameter(JRExporterParameter.JASPER_PRINT,
						jasperPrint);
				exporter.exportReport();
				media = new AMedia(getTitle(), "xls", "application/msexcel",
						file, true);
			} else {
				FDialog.error(0, winExportFile, "FileInvalidExtension");
				return;
			}

			winExportFile.onClose();

			Filedownload.save(media, jasperPrint.getName() + "." + ext);
		} catch (Exception e) {
			log.log(Level.SEVERE, "Failed to export content.", e);
		}
	}

	/**
	 * Send Mail
	 */
	private void cmd_sendMail() {
		String to = "";
		MUser from = MUser.get(Env.getCtx(), Env.getAD_User_ID(Env.getCtx()));
		String subject = getTitle();
		String message = "";
		File attachment = null;

		try {
			attachment = File.createTempFile("mail", ".pdf");
			JasperExportManager.exportReportToPdfFile(jasperPrint,
					attachment.getAbsolutePath());
		} catch (Exception e) {
			log.log(Level.SEVERE, "", e);
		}

		new WEMailDialog(this, Msg.getMsg(Env.getCtx(), "SendMail"), from, to,
				subject, message, attachment);
	} // cmd_sendMail

	// FR[3434743] <-}
}
