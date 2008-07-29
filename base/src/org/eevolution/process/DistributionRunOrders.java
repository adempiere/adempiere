/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * Copyright (C) 2003-2007 e-Evolution,SC. All Rights Reserved.               *
 * Contributor(s): Victor Perez www.e-evolution.com                           *
 *****************************************************************************/

package org.eevolution.process;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.logging.Level;

import org.compiere.apps.ProcessCtl;
import org.compiere.model.MDistributionRun;
import org.compiere.model.MDistributionRunLine;
import org.compiere.model.MDocType;
import org.compiere.model.MMessage;
import org.compiere.model.MNote;
import org.compiere.model.MPInstance;
import org.compiere.model.MPInstancePara;
import org.compiere.model.MProcess;
import org.compiere.model.MProcessPara;
import org.compiere.model.MRequisition;
import org.compiere.model.MRequisitionLine;
import org.compiere.model.MStorage;
import org.compiere.model.MWarehouse;
import org.compiere.model.X_ASP_Process;
import org.compiere.model.X_ASP_Process_Para;
import org.compiere.process.ProcessInfo;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.ASyncProcess;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Msg;
import org.compiere.util.TimeUtil;
import org.compiere.util.Trx;
import org.eevolution.model.MPPMRP;
import org.eevolution.model.MPPOrder;

/**
 *	MRPUpdate
 *	
 *  @author Victor Perez, e-Evolution, S.C.
 */
public class DistributionRunOrders extends SvrProcess 
{
	/**	The Run to execute		*/

	private int 				p_M_DistributionList_ID = 0;

	
	/**	Date Promised			*/
	private Timestamp			p_DatePromised = null;	
	/** Organization 			*/
	private int     p_AD_Org_ID     = 0;
	/** Is Only Test 			*/
	private String p_IsTest = "N";
	/** Warehouse 				*/
	private int 				p_M_Warehouse_ID = 0;
	/** Create Ordered DO Yes Create No Update	*/
	private String				p_CreateDO = "N";
	
	private MDistributionRun m_run = null;
	private int	m_M_DistributionRun_ID = 0;
	
        
	/**
	 *  Prepare - e.g., get Parameters.
	 */
	protected void prepare()
	{
		ProcessInfoParameter[] para = getParameter();

		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();

			if (para[i].getParameter() == null)
				;
			else if (name.equals("AD_Org_ID"))
			{    
				p_AD_Org_ID = ((BigDecimal)para[i].getParameter()).intValue();

			}                       
			else if (name.equals("M_Warehouse_ID"))
			{    
				p_M_Warehouse_ID = ((BigDecimal)para[i].getParameter()).intValue();                
			}
			else if (name.equals("M_DistributionList_ID"))
			{    
				p_M_DistributionList_ID = ((BigDecimal)para[i].getParameter()).intValue();                
			}
			else if (name.equals("DatePromised"))
				p_DatePromised = (Timestamp)para[i].getParameter();
			else if (name.equals("CreateDO"))
				p_CreateDO = (String)para[i].getParameter();
			else if (name.equals("IsTest"))
				p_IsTest = (String)para[i].getParameter();
			else
				log.log(Level.SEVERE,"prepare - Unknown Parameter: " + name);
		}            
              
	}	//	prepare


	/**
	 *  doIT - run process
	 */       
     protected String doIt() throws Exception                
     {
    	/*if(p_M_Warehouse_ID==0)
 		{
 			MWarehouse[] ws = MWarehouse.getForOrg(getCtx(), p_AD_Org_ID);
 			for(MWarehouse w : ws)
 			{	 
 				if(!deleteRecord(m_AD_Client_ID,p_AD_Org_ID,p_S_Resource_ID,w.getM_Warehouse_ID()))
 					throw new Exception(Msg.getMsg(getCtx(), "ProcessFailed"),CLogger.retrieveException());
 				if(!createRecord(m_AD_Client_ID,p_AD_Org_ID,p_S_Resource_ID,w.getM_Warehouse_ID()))
 					throw new Exception(Msg.getMsg(getCtx(), "ProcessFailed"),CLogger.retrieveException());
 			}
 		}
 		else
 		{
 			if(!deleteRecord(m_AD_Client_ID,p_AD_Org_ID,p_S_Resource_ID,p_M_Warehouse_ID))
 				throw new Exception(Msg.getMsg(getCtx(), "ProcessFailed"),CLogger.retrieveException());
 			if(!createRecord(m_AD_Client_ID,p_AD_Org_ID,p_S_Resource_ID,p_M_Warehouse_ID))
 				throw new Exception(Msg.getMsg(getCtx(), "ProcessFailed"),CLogger.retrieveException());
 		}*/
    	
    	 if(!generateDistribution())
				throw new Exception(Msg.getMsg(getCtx(), "ProcessFailed"),CLogger.retrieveException());
    	 if(!executeDistribution())
				throw new Exception(Msg.getMsg(getCtx(), "ProcessFailed"),CLogger.retrieveException());

        return Msg.getMsg(getCtx(), "ProcessOK");
     } 
     
     //Create Distribution Run Line
     public boolean generateDistribution()
     {
    	m_run = new MDistributionRun(this.getCtx(), 0 , this.get_TrxName());
    	m_run.setName("Generate from DRP " + p_DatePromised);
    	//m_run.setDescription("Generate from DRP");
    	m_run.save();
    	
    	StringBuffer sql = new StringBuffer("SELECT M_Product_ID , SUM (QtyOrdered) AS TotalQty, l.M_Warehouse_ID FROM DD_OrderLine ol INNER JOIN M_Locator l ON (l.M_Locator_ID=ol.M_Locator_ID) INNER JOIN DD_Order o ON (o.DD_Order_ID=ol.DD_Order_ID) ");
    	sql.append(" WHERE o.DocStatus IN ('DR','IN') AND ol.DatePromised BETWEEN ? AND ? AND l.M_Warehouse_ID=? GROUP BY M_Product_ID, l.M_Warehouse_ID");

    	
 	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    Timestamp today = new Timestamp (System.currentTimeMillis());  
 	    try
 	    {
 	            pstmt = DB.prepareStatement (sql.toString(),get_TrxName());
 	    		pstmt.setTimestamp(1, today);
 	    		pstmt.setTimestamp(2, p_DatePromised);
 	    		pstmt.setInt(3, p_M_Warehouse_ID);
 	    		
 	            rs = pstmt.executeQuery();
 	            int line = 10;
 	            while (rs.next())
 	            {
 	            	int M_Product_ID = rs.getInt("M_Product_ID");
 	            	BigDecimal QtyAvailable = MStorage.getQtyAvailable(p_M_Warehouse_ID,0 , M_Product_ID , 0, get_TrxName());
 	            	BigDecimal QtyOrdered = rs.getBigDecimal("TotalQty");
     	
 	            	MDistributionRunLine m_runLine = new MDistributionRunLine(getCtx(),0 ,get_TrxName());
 	            	m_runLine.setM_DistributionRun_ID(m_run.getM_DistributionRun_ID());
 	            	m_runLine.setAD_Org_ID(p_AD_Org_ID);
 	            	m_runLine.setM_DistributionList_ID(p_M_DistributionList_ID);
 	            	m_runLine.setLine(line);
 	            	m_runLine.setM_Product_ID(M_Product_ID);
 	            	m_runLine.setDescription(Msg.getMsg(getCtx(), "QtyAvailable") +" : " + QtyAvailable + " " +Msg.getMsg(getCtx(), "QtyOrdered") + " : " + QtyOrdered);
 	            	if(QtyOrdered.compareTo(QtyAvailable) > 0)
 	            	{	
 	            		QtyOrdered = QtyAvailable;
 	            	}	
 	            	m_runLine.setTotalQty(QtyOrdered);
 	            	m_runLine.save();
 	            	line += 10;
 	            }
 		}
 	    catch (Exception e)
 		{
 	            	log.log(Level.SEVERE,"doIt - " + sql, e);
 	                return false;
 		}
 		finally
 		{
 			DB.close(rs, pstmt);
 			rs = null;
 			pstmt = null;
 		}	    
    	 
    	return true; 
     }
     
     public boolean executeDistribution() throws Exception
     {
    	 
    	 int	M_DocType_ID  = 0;
    	 MDocType[] doc = MDocType.getOfDocBaseType(getCtx(), MDocType.DOCBASETYPE_DistributionOrder);

 		if (doc==null || doc.length == 0) {
 			log.severe ("Not found default document type for docbasetype " + MDocType.DOCBASETYPE_DistributionOrder);
 			throw new Exception(Msg.getMsg(getCtx(), "SequenceDocNotFound"),CLogger.retrieveException());
 		}
 		else
 			M_DocType_ID  = doc[0].getC_DocType_ID();
 		
 		String trxName = Trx.createTrxName("Run Distribution to DRP");	
 		Trx trx = Trx.get(trxName, true);	//trx needs to be committed too
 		
    	//Prepare Process
		int AD_Process_ID = 271;	  

        
		AD_Process_ID = MProcess.getProcess_ID("M_DistributionRun Create",get_TrxName());
		
		MPInstance instance = new MPInstance(Env.getCtx(), AD_Process_ID, 0);
		if (!instance.save())
		{
			throw new Exception(Msg.getMsg(getCtx(), "ProcessNoInstance"),CLogger.retrieveException());
		}
		
    	//call process
		ProcessInfo pi = new ProcessInfo ("CreateDistributionFromDRP", AD_Process_ID);
		pi.setAD_PInstance_ID (instance.getAD_PInstance_ID());
		pi.setRecord_ID(m_run.getM_DistributionRun_ID());
		//	Add Parameter - Selection=Y
		MPInstancePara ip = new MPInstancePara(instance, 10);
		ip.setParameter("C_DocType_ID", M_DocType_ID );
		if (!ip.save())
		{
			String msg = "No Parameter added";  //  not translated
			throw new Exception(msg,CLogger.retrieveException()); 
		}
		
		//	Add Parameter - DatePromised
		ip = new MPInstancePara(instance, 20);
		ip.setParameter("DatePromised", "");
		ip.setP_Date(p_DatePromised);
		if (!ip.save())
		{
			String msg = "No Parameter added";  //  not translated
			throw new Exception(msg,CLogger.retrieveException()); 
		}
		
		//	Add Parameter - M_Warehouse_ID
		ip = new MPInstancePara(instance, 30);
		ip.setParameter("M_Warehouse_ID",p_M_Warehouse_ID);
		if (!ip.save())
		{
			String msg = "No Parameter added";  //  not translated
			throw new Exception(msg,CLogger.retrieveException()); 
		}
		
		//	Add Parameter - CreateDO
		ip = new MPInstancePara(instance, 40);
		ip.setParameter("CreateDO",p_CreateDO);
		if (!ip.save())
		{
			String msg = "No Parameter added";  //  not translated
			throw new Exception(msg,CLogger.retrieveException()); 
		}
		
		//	Add Parameter - IsTest=Y
		ip = new MPInstancePara(instance, 50);
		ip.setParameter("IsTest",p_IsTest);
		if (!ip.save())
		{
			String msg = "No Parameter added";  //  not translated
			throw new Exception(msg,CLogger.retrieveException()); 
		}
		

		//	Execute Process
		MProcess worker = new MProcess(getCtx(),AD_Process_ID,get_TrxName());
		worker.processIt(pi, Trx.get(get_TrxName(), true));
		//ProcessCtl worker = new ProcessCtl(this, 0 , pi, trx);
		//worker.start();     //  complete tasks in unlockUI / generateShipments_complete
    	 return true;
     }
}
