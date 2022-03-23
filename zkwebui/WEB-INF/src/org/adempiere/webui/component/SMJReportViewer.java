package org.adempiere.webui.component;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.LinkedList;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.excel.SmjReportLogic;
import org.adempiere.excel.SmjXlsReport;
import org.adempiere.pdf.SmjPdfReport;
import org.adempiere.webui.panel.StatusBarPanel;
import org.adempiere.webui.window.FDialog;
import org.adempiere.webui.window.ZkReportViewer;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.adempiere.util.StringUtils;
import org.compiere.model.MClientInfo;
import org.compiere.model.MOrgInfo;
import org.compiere.model.MRole;
import org.compiere.report.MReportColumn;
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
import org.zkoss.zul.Separator;
import org.zkoss.zul.Toolbar;

import org.compiere.model.ReportTO;

/**
 *	Print View Frame
 *
 * 	@author 	Jorg Janke
 * 	@version 	$Id: Viewer.java,v 1.2 2006/07/30 00:51:28 jjanke Exp $
 * globalqss: integrate phib contribution from 
 *   http://sourceforge.net/tracker/index.php?func=detail&aid=1566335&group_id=176962&atid=879334
 * globalqss: integrate Teo Sarca bug fixing
 * Colin Rooney 2007/03/20 RFE#1670185 & BUG#1684142
 *                         Extend security to Info queries
 *
 * @author Teo Sarca, SC ARHIPAC SERVICE SRL
 * 				<li>FR [ 1762466 ] Add "Window" menu to report viewer.
 * 				<li>FR [ 1894640 ] Report Engine: Excel Export support
 * @author Low Heng Sin
 * 
 * @version <li> SmartJSP: SMJReportViewer.java, 2011/03/01 <ul TYPE ="circle">
 * <li> Toma de base la clase "ZkReportViewer" para crear la clase que muestra la ventana con el reporte 
 * <li> The Class "ZkReportViewer" is changed to create this class, this class shows report window </ul>
 * @author <li> Freddy Rodriguez - "SmartJSP" - http://www.smartjsp.com/
 * 
 */
public class SMJReportViewer extends Window implements EventListener {
			
			private static final long serialVersionUID = 4640088641140012438L;
			/** Window No					*/
			private int m_WindowNo;
			/**	Print Context				*/
			private Properties m_ctx;
			/** Table ID					*/
			private int m_AD_Table_ID = 0;

			/**	Logger			*/
			private static CLogger log = CLogger.getCLogger(ZkReportViewer.class);

			private StatusBarPanel statusBar = new StatusBarPanel();
			private Iframe iframe;
			private Integer reportId;
			private Integer m_AD_PInstance_ID;
			private File filePdf;
			private File fileXls;
			private ByteArrayOutputStream baosPDF; 
			private String trxName = "";
			//private Integer reportLineSetId;
			private Integer p_C_Period_ID;
			private Integer p_AD_PrintFont_ID;
			
			//barra seleccion tipo del informe PDF o EXL
			//toolbar to choice report type formar PDF or XLS
			private  LinkedList<ReportTO> data = null;
			private String[] generalTitle;
			private String clientName;
			private String clientNIT;
			private String periodName;
			private String currencyName;
			private String codeFont;
			private Toolbar toolBar = new Toolbar();
			private Listbox previewType = new Listbox();
			private MReportColumn[] m_columns;
			private String city;
			private Integer logoId;
			
			/**
			 * 	Static Layout
			 * 	@throws Exception
			 */
			public SMJReportViewer(Integer AD_PInstance_ID, String nameTrx, Integer idReport, Integer C_Period_ID, 
					Integer AD_PrintFont_ID, MReportColumn[] columns) {		
				super();
				reportId = idReport;					// Goodwill - this become PA_Report_ID
				m_AD_PInstance_ID = AD_PInstance_ID;	// Goodwill - this is the instance
				trxName = nameTrx;
				//reportLineSetId = idReportLineSet;	// Goodwill - BF: report title is taken wrongly from other report if report line set is shared
				p_C_Period_ID = C_Period_ID;
				p_AD_PrintFont_ID  = AD_PrintFont_ID;
				m_columns = columns;
				if (!MRole.getDefault().isCanReport(m_AD_Table_ID))	{
					this.onClose();
				}
				try	{
					m_ctx = Env.getCtx();
					dynInit();
					init();
				}catch(Exception e){
					log.log(Level.SEVERE, "", e);
					FDialog.error(m_WindowNo, this, "LoadError", e.getLocalizedMessage());
					this.onClose();
				}//try / catch
			}//SMJReportViewer

			/**
			 * init
			 */
			private void init() {
				Borderlayout layout = new Borderlayout();
				layout.setStyle("position: absolute; height: 99%; width: 99%");
				this.appendChild(layout);
				this.setStyle("width: 100%; height: 100%; position: absolute");

				//-------------Toolbar -------------
				toolBar.setHeight("26px");
				
				previewType.setMold("select");
				previewType.appendItem("PDF", "PDF");
//				previewType.appendItem("HTML", "HTML");
				previewType.appendItem("Excel", "XLS");
				toolBar.appendChild(previewType);		
				previewType.addEventListener(Events.ON_SELECT,  this);
				toolBar.appendChild(new Separator("vertical"));
				
				previewType.setSelectedIndex(0); //fallback to PDF
				
				North north = new North();
				layout.appendChild(north);
				north.appendChild(toolBar);
				
				Center center = new Center();
				center.setFlex(true);
				layout.appendChild(center);
				iframe = new Iframe();
				iframe.setId("reportFrame");
				iframe.setHeight("100%");
				iframe.setWidth("100%");
				center.appendChild(iframe);

				try {
					renderReport("PDF");
				} catch (Exception e) {
					throw new AdempiereException("Failed to render report", e);
				}
						
				iframe.setAutohide(true);

				this.setBorder("normal");
			}//init

			//despliega el reporte en pantalla
			//show report
			//SMJReport
			private void renderReport(String type) throws Exception {
				AMedia media = null;
					String path = System.getProperty("java.io.tmpdir");
					String prefix = StringUtils.makePrefix("financial");
					if (log.isLoggable(Level.FINE))
					{
						log.log(Level.FINE, "Path="+path + " Prefix="+prefix);
					}
					if (type.equals("PDF")){
						media = new AMedia(generalTitle[0], "pdf", "application/pdf", filePdf, true);
					}else if(type.equals("XLS")){
						media = new AMedia(generalTitle[0], "xls", "application/xls", fileXls, true);
					}else{
						media = new AMedia(generalTitle[0], "pdf", "application/pdf", filePdf, true);
					}
				iframe.setContent(media);
			}//renderReport
			
			/**
			 * 	Dynamic Init
			 */
			private void dynInit(){
				SmjReportLogic logic = new SmjReportLogic();
				data = logic.getDataReport(m_AD_PInstance_ID, trxName);
				generalTitle = logic.getGeneralTitle(reportId, trxName);
				clientName = logic.getOrgName(trxName);
//				if (clientName.equals("") || clientName.length()<=0){
//					clientName = logic.getClientName(trxName);
//				}
				clientNIT = logic.getOrgNIT(trxName);
				periodName = logic.getPeriodName(p_C_Period_ID, trxName);
				currencyName = logic.getCurrency(trxName);
				codeFont = logic.getCodeFont(trxName, p_AD_PrintFont_ID);
				city = logic.getClientCity(trxName);
				//logo
				Properties prop = Env.getCtx();
				MOrgInfo oi = MOrgInfo.get(prop, Env.getAD_Org_ID(prop), null);
				logoId = oi.getLogo_ID();
				if (logoId <= 0){
					MClientInfo ci = MClientInfo.get(prop);
					logoId = ci.getLogoReport_ID();
					if (logoId <= 0) 
						logoId = ci.getLogo_ID();
				}
				SmjPdfReport pdf = new SmjPdfReport();
				baosPDF = pdf.generate(data, trxName, generalTitle, clientName, clientNIT, periodName, currencyName, m_columns, codeFont, city, logoId);
				filePdf = pdf.tofile(baosPDF.toByteArray(), generalTitle);
				revalidate();
			}	//	dynInit
			
			/**
			 * crea el reporte XLS - Create XLS report
			 */
			private void createXlsReport (){
				SmjXlsReport xls = new SmjXlsReport(); 
				HSSFWorkbook book =  xls.generate(data, generalTitle, clientName, clientNIT, periodName, currencyName, m_columns, city, logoId);
				fileXls = xls.tofile(book, generalTitle);
				revalidate();
			}//
			
			/**
			 * 	Revalidate settings after change of environment
			 */
			private void revalidate(){
				//	Report Info
				setTitle(Msg.translate(Env.getCtx(), "PA_Report_ID"));
				StringBuffer sb = new StringBuffer ();
				sb.append(Msg.getMsg(m_ctx, "DataCols")).append("=")
					.append(", ").append(Msg.getMsg(m_ctx, "DataRows")).append("=")	;
				statusBar.setStatusLine(sb.toString());
			}	//	revalidate

			/**
			 * onClose
			 */
			public void onClose(){
				Env.clearWinContext(m_WindowNo);
				m_ctx = null;
				super.onClose();
			}	//	dispose

			@Override
			public void onEvent(Event ev) throws Exception {
				if (ev.getTarget().equals(previewType)&& previewType!=null){
					int index = previewType.getSelectedIndex();
					if (index == 1){
						createXlsReport();
						try {
							renderReport("XLS");
						} catch (Exception e) {
							throw new AdempiereException("Failed to render report", e);
						}
					}else if (index == 0){
						try {
							renderReport("PDF");
						} catch (Exception e) {
							throw new AdempiereException("Failed to render report", e);
						}
					}else{
						try {
							renderReport("PDF");
						} catch (Exception e) {
							throw new AdempiereException("Failed to render report", e);
						}
					}
				}//if 
			}//onEvent
			
}//SMJReportViewer
