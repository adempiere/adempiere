/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 Adempiere, Inc. All Rights Reserved.                *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 *
 * Copyright (C) 2005 Robert Klein. robeklein@hotmail.com
 * Contributor(s): Low Heng Sin hengsin@avantz.com
 *****************************************************************************/
package org.adempiere.pipo.handler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

import javax.xml.transform.sax.TransformerHandler;

import org.adempiere.pipo.AbstractElementHandler;
import org.adempiere.pipo.Element;
import org.compiere.model.X_AD_Package_Exp_Detail;
import org.compiere.model.X_AD_Workbench;
import org.compiere.model.X_AD_WorkbenchWindow;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

public class WorkbenchElementHandler extends AbstractElementHandler {

	public void startElement(Properties ctx, Element element) throws SAXException {
		String elementValue = element.getElementValue();
		int AD_Backup_ID = -1;
		String Object_Status = null;
		Attributes atts = element.attributes;
		log.info(elementValue+" "+atts.getValue("ADWorkbenchNameID"));
		String entitytype = atts.getValue("EntityType");		
		String name = atts.getValue("ADWorkbenchNameID");
		if (entitytype.compareTo("U") == 0 || entitytype.compareTo("D") == 0 && getUpdateMode(ctx).compareTo("true") == 0 ) {
			int id = get_ID(ctx, "AD_Workbench", name);
			X_AD_Workbench m_Workbench = new X_AD_Workbench(ctx, id, getTrxName(ctx));
			if (id > 0){
				AD_Backup_ID = copyRecord(ctx, "AD_Workbench",m_Workbench);
				Object_Status = "Update";			
			}
			else{
				Object_Status = "New";
				AD_Backup_ID =0;
			}	    
			
			int tableid = get_IDWithColumn(ctx, "AD_Table", "TableName", atts.getValue("ADTableNameID"));	
			int columnid  = get_IDWithMasterAndColumn (ctx, "AD_Column","ColumnName", atts.getValue("ADColumnNameID"), "AD_Table", tableid);		    
			m_Workbench.setAD_Column_ID(columnid);
			m_Workbench.setDescription(atts.getValue("Description").replaceAll("'","''").replaceAll(",",""));
			m_Workbench.setEntityType(atts.getValue("EntityType"));
			m_Workbench.setHelp(atts.getValue("Help").replaceAll("'","''").replaceAll(",",""));
			m_Workbench.setIsActive(atts.getValue("isActive") != null ? Boolean.valueOf(atts.getValue("isActive")).booleanValue():true);
			m_Workbench.setName(atts.getValue("Name"));
			//m_Workbench.setPA_Goal_ID(Integer.parseInt(atts.getValue("PAGoalID")));
			if (m_Workbench.save(getTrxName(ctx)) == true){		    	
				record_log (ctx, 1, m_Workbench.getName(),"Workbench", m_Workbench.get_ID(),AD_Backup_ID, Object_Status,"AD_Workbench",get_IDWithColumn(ctx, "AD_Table", "TableName", "AD_Workbench"));           		        		
			}
			else{
				record_log (ctx, 0, m_Workbench.getName(),"Workbench", m_Workbench.get_ID(),AD_Backup_ID, Object_Status,"AD_Workbench",get_IDWithColumn(ctx, "AD_Table", "TableName", "AD_Workbench"));
			}
		}
	}

	public void endElement(Properties ctx, Element element) throws SAXException {
	}

	public void create(Properties ctx, TransformerHandler document)
			throws SAXException {
		int AD_Workbench_ID = Env.getContextAsInt(ctx, X_AD_Package_Exp_Detail.COLUMNNAME_AD_Workbench_ID);
		X_AD_Workbench m_Workbench = new X_AD_Workbench (ctx, AD_Workbench_ID, null);					
		AttributesImpl atts = new AttributesImpl();
		createWorkbenchBinding(atts,m_Workbench);
		document.startElement("","","workbench",atts);
		//Workbenchwindow tags
		String sqlP = "SELECT * FROM AD_WorkbenchWindow WHERE AD_WORKBENCH_ID = " + AD_Workbench_ID;							
		PreparedStatement pstmtP = null;
		pstmtP = DB.prepareStatement (sqlP, getTrxName(ctx));		
		try {
			ResultSet rsP = pstmtP.executeQuery();		
			while (rsP.next())
			{
				X_AD_WorkbenchWindow m_Workbenchwindow = new X_AD_WorkbenchWindow (ctx, rsP.getInt("AD_Workbench_Window_ID"), null);
				atts = createWorkbenchWindowBinding(atts,m_Workbenchwindow);
				document.startElement("","","workbenchwindow",atts);
				document.endElement("","","workbenchwindow");
			}								
			rsP.close();
			pstmtP.close();
			pstmtP = null;
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE,"getWorkbench_Window", e);
		}
		finally
		{
			try {
				if (pstmtP != null)
					pstmtP.close ();
			}
			catch (Exception e)
			{}
			pstmtP = null;
		}
		document.endElement("","","workbench");
		
	}

	private AttributesImpl createWorkbenchBinding( AttributesImpl atts, X_AD_Workbench m_Workbench) 
	{
		String sql = null;
        String name = null;
        atts.clear();
        if (m_Workbench.getAD_Workbench_ID()> 0 ){
                sql = "SELECT Name FROM AD_Workbench WHERE AD_Workbench_ID=?";
                name = DB.getSQLValueString(null,sql,m_Workbench.getAD_Workbench_ID());
        }        
        if (name != null )
                atts.addAttribute("","","ADWorkbenchNameID","CDATA",name);
        else
        		atts.addAttribute("","","ADWorkbenchNameID","CDATA","");
        if (m_Workbench.getAD_Column_ID()> 0 ){
                sql = "SELECT ColumnName FROM AD_Column WHERE AD_Column_ID=?";
                name = DB.getSQLValueString(null,sql,m_Workbench.getAD_Column_ID());
        }        
        if (name != null )
                atts.addAttribute("","","ADColumnNameID","CDATA",name);
        else
        		atts.addAttribute("","","ADColumnNameID","CDATA","");
        atts.addAttribute("","","Description","CDATA",(m_Workbench.getDescription () != null ? m_Workbench.getDescription ():""));    
        atts.addAttribute("","","EntityType","CDATA",(m_Workbench.getEntityType () != null ? m_Workbench.getEntityType ():""));
        atts.addAttribute("","","Help","CDATA",(m_Workbench.getHelp () != null ? m_Workbench.getHelp ():""));
        atts.addAttribute("","","isActive","CDATA",(m_Workbench.isActive()== true ? "true":"false"));
        atts.addAttribute("","","Name","CDATA",(m_Workbench.getName () != null ? m_Workbench.getName ():""));
        //atts.addAttribute("","","PA_Goal_ID","CDATA",(m_Workbench.getPA_Goal_ID() > 0 ? "" + m_Workbench.getPA_Goal_ID ():""));
        return atts;
	}

	private AttributesImpl createWorkbenchWindowBinding( AttributesImpl atts, X_AD_WorkbenchWindow m_Workbenchwindow) 
	{
		String sql = null;
		String name = null;
		atts.clear();
		if (m_Workbenchwindow.getAD_Process_ID()> 0 ){
			sql = "SELECT Name FROM AD_Process WHERE AD_Process_ID=?";
			name = DB.getSQLValueString(null,sql,m_Workbenchwindow.getAD_Process_ID());       
			atts.addAttribute("","","name","CDATA",name);
		}
		else
			atts.addAttribute("","","name","CDATA","");
		if (m_Workbenchwindow.getAD_Workbench_ID()> 0 ){
			sql = "SELECT Name FROM AD_Workbench WHERE AD_Workbench_ID=?";
			name = DB.getSQLValueString(null,sql,m_Workbenchwindow.getAD_Workbench_ID());
		}
		if (name != null )
			atts.addAttribute("","","ADWorkbenchNameID","CDATA",name);
		else
			atts.addAttribute("","","ADWorkbenchNameID","CDATA","");   
		if (m_Workbenchwindow.getAD_Form_ID()> 0 ){
			sql = "SELECT Name FROM AD_Form WHERE AD_Form_ID=?";
			name = DB.getSQLValueString(null,sql,m_Workbenchwindow.getAD_Form_ID());
		}
		if (name != null )
			atts.addAttribute("","","ADFormNameID","CDATA",name);
		else
			atts.addAttribute("","","ADFormNameID","CDATA","");   
		if (m_Workbenchwindow.getAD_Window_ID()> 0 ){
			sql = "SELECT Name FROM AD_Window WHERE AD_Window_ID=?";
			name = DB.getSQLValueString(null,sql,m_Workbenchwindow.getAD_Window_ID());
		}
		if (name != null )
			atts.addAttribute("","","ADWindowNameID","CDATA",name);            
		else
			atts.addAttribute("","","ADWindowNameID","CDATA","");     
		if (m_Workbenchwindow.getAD_Task_ID()> 0 ){
			sql = "SELECT Name FROM AD_Task WHERE AD_Task_ID=?";
			name = DB.getSQLValueString(null,sql,m_Workbenchwindow.getAD_Task_ID());
		}
		if (name != null )
			atts.addAttribute("","","ADTaskNameID","CDATA",name);
		else
			atts.addAttribute("","","ADTaskNameID","CDATA","");       
		atts.addAttribute("","","EntityType","CDATA",(m_Workbenchwindow.getEntityType () != null ? m_Workbenchwindow.getEntityType ():""));
		atts.addAttribute("","","SeqNo","CDATA",(m_Workbenchwindow.getSeqNo () > 0 ? "" + m_Workbenchwindow.getSeqNo ():""));
		atts.addAttribute("","","isActive","CDATA",(m_Workbenchwindow.isActive()== true ? "true":"false"));
		atts.addAttribute("","","isPrimary","CDATA",(m_Workbenchwindow.isPrimary()== true ? "true":"false"));
		return atts;
	}
}
