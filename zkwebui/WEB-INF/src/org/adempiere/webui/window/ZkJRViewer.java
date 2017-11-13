package org.adempiere.webui.window;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRRtfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import org.adempiere.webui.component.Listbox;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.webui.component.Window;
import org.compiere.model.MArchive;
import org.compiere.model.MUser;
import org.compiere.model.PrintInfo;
import org.compiere.process.ProcessInfo;
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
import org.zkoss.zul.Iframe;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Separator;
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Toolbarbutton;

/**
 * 
 * @author                             Modification
 * WalkingTree ( www.walkingtree.in )  Made code changes to have Excel option for Jasper Reports.
 *
 */
public class ZkJRViewer extends Window implements EventListener {

	private static final long serialVersionUID = 2021796699437770927L;

	// Initialization of global variables

	private JasperPrint jasperPrint;
    
	//
	// Added Preview Type listbox to have PDF and Excel options for jasper report
	//
	private Listbox previewType = new Listbox();
	private Iframe iframe;
	private AMedia media;
	private Toolbarbutton  sendMail, archive;
	private Toolbar toolbar         = null;
	private Properties ctx          = null;
	private File file               = null;
	private String title            = null;			
	private ProcessInfo processInfo = null; 



	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(ZkJRViewer.class);

	public ZkJRViewer(JasperPrint jasperPrint, String title) {
		super();
		this.setTitle(title);

		this.title = title.substring(0,title.indexOf(".pdf")) ;
		ctx = Env.getCtx();
		this.jasperPrint = jasperPrint;
		// Getting AD_Process_ID from Context
		processInfo	=	new ProcessInfo(jasperPrint.getName(), Env.getContextAsInt(ctx, "AD_Process_ID"));
		init();
	}

	// initialization
	private void init() {
		Borderlayout layout = new Borderlayout();
		layout.setStyle("position: absolute; height: 99%; width: 99%");
		this.appendChild(layout);
		this.setStyle("width: 100%; height: 100%; position: absolute");


		toolbar = new Toolbar();
		toolbar.setHeight("26px");
		sendMail = new Toolbarbutton();
		archive = new Toolbarbutton();


		sendMail.setImage("/images/dark/SendMail24.png");
		sendMail.setTooltiptext("Send Mail");
		toolbar.appendChild(sendMail);
		sendMail.addEventListener(Events.ON_CLICK, this);

		archive.setImage("/images/dark/Archive24.png");
		archive.setTooltiptext("Archived Documents/Reports");
		toolbar.appendChild(archive);
		archive.addEventListener(Events.ON_CLICK, this);

		toolbar.appendChild(new Separator("vertical"));

		//
		// we have drop down in toolbar to select PDF or XLS
		//
		
		previewType.setMold("select");
		previewType.appendItem("PDF", "PDF");
		previewType.appendItem("Excel", "XLS");
		previewType.appendItem("RTF", "RTF");

		
		toolbar.appendChild(previewType);
		previewType.addEventListener(Events.ON_SELECT, this);

		North north = new North();
		layout.appendChild(north);
		north.appendChild(toolbar);

		Center center = new Center();
		center.setFlex(true);
		layout.appendChild(center);
		iframe = new Iframe();
		iframe.setId(jasperPrint.getName());
		iframe.setHeight("100%");
		iframe.setWidth("100%");
		iframe.setAutohide( Boolean.TRUE );

		try {
			renderReport() ; 		
		} 
		catch (Exception e) {
			log.log(Level.SEVERE, e.getLocalizedMessage(), e.getMessage());
			throw new AdempiereException("Failed to render report.", e);
		}
		center.appendChild(iframe);

		this.setBorder("normal");
	}		

	/***
	 * 
	 * This method is used for generate report in PDF or XLS based on previewType selection.
	 * 
	 * @throws Exception
	 */

	private void renderReport() throws Exception {

		Listitem selected = previewType.getSelectedItem();
		
		//
		// Place Common code outside the If else block
		//
		
		String path = System.getProperty("java.io.tmpdir");
		String prefix = makePrefix(jasperPrint.getName());
		if ( log.isLoggable(Level.FINE) )  {
			log.log(Level.FINE, "Path="+path + " Prefix="+prefix);
		}

		if ( selected == null || "PDF".equals(selected.getValue() ) )  {

			file = File.createTempFile(prefix, ".pdf", new File(path));
			JasperExportManager.exportReportToPdfFile(jasperPrint, file.getAbsolutePath());
			media = new AMedia(this.title, "pdf", "application/pdf", file, true);
		}

		else if ("XLS".equals(previewType.getSelectedItem().getValue())){

			file = File.createTempFile(prefix, ".xls", new File(path));
			FileOutputStream fos = new FileOutputStream(file);
			JRXlsExporter exporterXLS = new JRXlsExporter();
			exporterXLS.setParameter(JRXlsExporterParameter.JASPER_PRINT, jasperPrint);
			exporterXLS.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, fos);
			exporterXLS.setParameter(JRXlsExporterParameter.OUTPUT_FILE, file.getAbsolutePath());
			exporterXLS.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
			exporterXLS.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
			exporterXLS.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE );
			exporterXLS.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS, Boolean.TRUE );
			exporterXLS.setParameter(JRXlsExporterParameter.IS_COLLAPSE_ROW_SPAN, Boolean.TRUE);
			exporterXLS.setParameter(JRXlsExporterParameter.IS_IGNORE_GRAPHICS, Boolean.FALSE);
			exporterXLS.exportReport();
			media = new AMedia(this.title, "xls", "application/vnd.ms-excel", file, true);
		}
		else if ("RTF".equals(previewType.getSelectedItem().getValue()))
		{

			FileOutputStream fos = new FileOutputStream(file);
            JRRtfExporter rtfExporter = new JRRtfExporter();
            rtfExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            rtfExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, fos);
            rtfExporter.setParameter(JRExporterParameter.CHARACTER_ENCODING, "UTF-8");
            rtfExporter.exportReport();
			media = new AMedia(this.title, "doc", "application/vnd.ms-word", file, true);
		}
		iframe.setContent(media);
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

	public void actionPerformed(Event e) {

		if (e.getTarget() == sendMail) {
			cmd_sendMail();
		} 
		else if (e.getTarget() == archive) {
			cmd_archive();

		//
		// If select drop down then event raised
			
		}else if (e.getTarget() == previewType){
			cmd_render();
		}
	}

	public void onEvent(Event event) throws Exception {

		if (event.getName().equals(Events.ON_CLICK) || event.getName().equals(Events.ON_SELECT)) {
			actionPerformed(event);
		}
	}

	private void cmd_sendMail()
	{
		String to = "";
		MUser from = MUser.get(Env.getCtx(), Env.getAD_User_ID(Env.getCtx()));
		String subject = jasperPrint.getName();	
		String message = "";
		File attachment = file;
		new WEMailDialog (this, Msg.getMsg(Env.getCtx(), "SendMail"), from, to, subject, message, attachment);
	}	//	cmd_sendMail

	/**
	 * 
	 * Create a Archive for specified report
	 * Create an entry at AD_Archive table
	 * 
	 */
	private void cmd_archive() {
		boolean success = false;
		byte[] data = getPDFAsArray(file);
		MArchive arc = null;
		if (data != null) {
			PrintInfo printInfo = null;
			if (null != processInfo) {
				printInfo = new PrintInfo(processInfo);
			}
			if (null != printInfo) {
				arc = new MArchive(Env.getCtx(), printInfo, null);
			}
			if (null != arc) {
				arc.setBinaryData(data);
				arc.setAD_Process_ID(processInfo.getAD_Process_ID());
				arc.setRecord_ID(processInfo.getRecord_ID());
				arc.setName(processInfo.getTitle());
				success = arc.save();
			}
		}
		if (success)
		{
			FDialog.info(0, this, Msg.getMsg(ctx, "ArchiveSuccess", new Object[] { arc.getName() }));
			log.log(Level.FINE, arc.getName()+ " Archived Into " +arc.getAD_Archive_ID());
			archive.setDisabled( Boolean.TRUE );
		}
		else {
			throw new AdempiereException("ArchiveError");
		}
	}

	/***
	 * This method is responsible for Call rendorReport() when event raised
	 */
	private void cmd_render() {
		try {
			renderReport();
		} catch (Exception e) {
			throw new AdempiereException("Failed to render report", e);
		}
	}


	/**
	 * 
	 * @return bytes of data
	 */
	private byte[] getPDFAsArray(File f) {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(f);
		} 
		catch (FileNotFoundException e) {
			log.log(Level.SEVERE, "Unable to create FileInputStream object. " +e.getLocalizedMessage(), e.getMessage());
		}
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		byte[] buf = new byte[(int) f.length()];
		try {
			for (int readNum; (readNum = fis.read(buf)) != -1;) {
				bos.write(buf, 0, readNum);
			}
		}
		catch (FileNotFoundException e) {
			log.log(Level.SEVERE, "File Not Found Exception. " + e.getLocalizedMessage(), e.getMessage());
		}
		catch (IOException e) {
			log.log(Level.SEVERE, "File Not Found Exception. " + e.getLocalizedMessage(), e.getMessage());
		}

		byte[] bytes = bos.toByteArray();

		return bytes;

	}


}
