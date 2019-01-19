package org.compiere.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;

import org.adempiere.pipo.IDFinder;
import org.adempiere.pipo.PackIn;
import org.adempiere.pipo.handler.PrintFormatElementHandler;
import org.adempiere.util.DateUtil;
import org.compiere.model.X_AD_Package_Exp_Detail;
import org.compiere.print.MPrintFormat;
import org.compiere.print.ReportEngine;
import org.spin.util.XMLUtils;
import org.xml.sax.helpers.AttributesImpl;


/**
 * Utility to import/export System related records
 * @author jobriant
 *
 */
public class ImpExpUtil {
	
	private static CLogger log = CLogger.getCLogger(ImpExpUtil.class);

	public static File exportPrintFormat(File file, ReportEngine reportEngine)
	{
		int AD_PrintFormat_ID = reportEngine.getPrintFormat().get_ID();
		String trxName = Trx.createTrxName();
		Env.setContext(Env.getCtx(), MPrintFormat.COLUMNNAME_AD_PrintFormat_ID, AD_PrintFormat_ID);
		Env.setContext(Env.getCtx(),"TrxName", trxName);
		
		try {
			FileOutputStream fos = new FileOutputStream(file);
			StreamResult streamResult_document = new StreamResult(new OutputStreamWriter(fos,"ISO-8859-1"));
		    PrintFormatElementHandler printFormatHandler = new PrintFormatElementHandler();
			SAXTransformerFactory tf_menu = (SAXTransformerFactory) SAXTransformerFactory.newInstance();
			//	Default features
			XMLUtils.setDefaultFeatures(tf_menu);
			tf_menu.setAttribute("indent-number", new Integer(4));
			TransformerHandler packOutDocument = tf_menu.newTransformerHandler();		
			Transformer serializer_document = packOutDocument.getTransformer();		
			serializer_document.setOutputProperty(OutputKeys.ENCODING,"ISO-8859-1");		
			serializer_document.setOutputProperty(OutputKeys.INDENT,"yes");		
			packOutDocument.setResult(streamResult_document);
			packOutDocument.startDocument();
			AttributesImpl atts = new AttributesImpl();
			atts.clear();
			atts.addAttribute("","","Name","CDATA","PrintFormat");
			atts.addAttribute("","","Version","CDATA","1");
			atts.addAttribute("","","CompVer","CDATA","all");
			atts.addAttribute("","","DataBase","CDATA","all");
			atts.addAttribute("","","Description","CDATA","Created from Print Format Import/Export");
			atts.addAttribute("","","creator","CDATA","PrintFormat Importer");
			atts.addAttribute("","","creatorcontact","CDATA","PrintFormat Importer");
			atts.addAttribute("","","createddate","CDATA", DateUtil.getDateString());
			atts.addAttribute("","","updateddate","CDATA", DateUtil.getDateString());
			atts.addAttribute("","","PackOutVer","CDATA","1");		
			
			packOutDocument.startElement("","","adempiereAD",atts);		
			printFormatHandler.create(Env.getCtx(), packOutDocument);
			packOutDocument.endElement("","","adempiereAD");
			packOutDocument.endDocument();
			
		} catch (Exception e) {
			
		}
		Env.getCtx().remove(X_AD_Package_Exp_Detail.COLUMNNAME_AD_PrintFormat_ID);
		Env.getCtx().remove("TrxName");
		Trx x = Trx.get(trxName, false);
		x.close();
		return file;
		
	}
	
	public static boolean importPrintFormat(File arxml)
	{
		
		
		PackIn.m_UpdateMode = "false";
		PackIn.m_Package_Dir = arxml.getParentFile().getAbsolutePath();
		
		File doc = new File(PackIn.m_Package_Dir + File.separator + "doc");
		doc.mkdir();
		
		if (DB.isOracle())
			PackIn.m_Database = "Oracle";
		else if (DB.isPostgreSQL())
			PackIn.m_Database = "PostgreSQL";

		// call XML Handler
		String trxName = Trx.createTrxName();
		Env.setContext(Env.getCtx(),"TrxName", trxName);
		// clear cache of previous runs
		IDFinder.clearIDCache();
		PackIn packIn = new PackIn();
		try {
			String msg = packIn.importXML(arxml.getAbsolutePath(), Env.getCtx(), trxName);
			log.fine(msg);
		} catch (Exception e) {
			log.severe(e.getMessage());
			return false;
		}
		Env.getCtx().remove("TrxName");
		try {
			DB.commit(true, trxName);
		} catch (Exception e) {
			log.severe(e.getMessage());
		}
		Trx x = Trx.get(trxName, false);
		x.close();
		return true;
	}

}
